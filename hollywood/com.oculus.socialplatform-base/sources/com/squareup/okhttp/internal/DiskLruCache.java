package com.squareup.okhttp.internal;

import X.AnonymousClass006;
import com.squareup.okhttp.internal.io.FileSystem;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.RealBufferedSink;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class DiskLruCache implements Closeable {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final long ANY_SEQUENCE_NUMBER = -1;
    public static final String CLEAN = "CLEAN";
    public static final String DIRTY = "DIRTY";
    public static final String JOURNAL_FILE = "journal";
    public static final String JOURNAL_FILE_BACKUP = "journal.bkp";
    public static final String JOURNAL_FILE_TEMP = "journal.tmp";
    public static final Pattern LEGAL_KEY_PATTERN = Pattern.compile("[a-z0-9_-]{1,120}");
    public static final String MAGIC = "libcore.io.DiskLruCache";
    public static final Sink NULL_SINK = new Sink() {
        /* class com.squareup.okhttp.internal.DiskLruCache.AnonymousClass4 */

        @Override // java.io.Closeable, java.lang.AutoCloseable, okio.Sink
        public void close() throws IOException {
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return Timeout.NONE;
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            buffer.skip(j);
        }
    };
    public static final String READ = "READ";
    public static final String REMOVE = "REMOVE";
    public static final String VERSION_1 = "1";
    public final int appVersion;
    public final Runnable cleanupRunnable = new Runnable() {
        /* class com.squareup.okhttp.internal.DiskLruCache.AnonymousClass1 */

        public void run() {
            synchronized (DiskLruCache.this) {
                DiskLruCache diskLruCache = DiskLruCache.this;
                boolean z = false;
                if (!diskLruCache.initialized) {
                    z = true;
                }
                if (!z && !diskLruCache.closed) {
                    try {
                        diskLruCache.trimToSize();
                        DiskLruCache diskLruCache2 = DiskLruCache.this;
                        if (diskLruCache2.journalRebuildRequired()) {
                            diskLruCache2.rebuildJournal();
                            DiskLruCache.this.redundantOpCount = 0;
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    };
    public boolean closed;
    public final File directory;
    public final Executor executor;
    public final FileSystem fileSystem;
    public boolean hasJournalErrors;
    public boolean initialized;
    public final File journalFile;
    public final File journalFileBackup;
    public final File journalFileTmp;
    public BufferedSink journalWriter;
    public final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap<>(0, 0.75f, true);
    public long maxSize;
    public long nextSequenceNumber = 0;
    public int redundantOpCount;
    public long size = 0;
    public final int valueCount;

    public final class Editor {
        public boolean committed;
        public final Entry entry;
        public boolean hasErrors;
        public final boolean[] written;

        public void abort() throws IOException {
            synchronized (DiskLruCache.this) {
                DiskLruCache.this.completeEdit(this, false);
            }
        }

        public void abortUnlessCommitted() {
            synchronized (DiskLruCache.this) {
                if (!this.committed) {
                    try {
                        DiskLruCache.this.completeEdit(this, false);
                    } catch (IOException unused) {
                    }
                }
            }
        }

        public void commit() throws IOException {
            synchronized (DiskLruCache.this) {
                if (this.hasErrors) {
                    DiskLruCache.this.completeEdit(this, false);
                    DiskLruCache.this.removeEntry(this.entry);
                } else {
                    DiskLruCache.this.completeEdit(this, true);
                }
                this.committed = true;
            }
        }

        public Sink newSink(int i) throws IOException {
            AnonymousClass1 r0;
            synchronized (DiskLruCache.this) {
                Entry entry2 = this.entry;
                if (entry2.currentEditor == this) {
                    if (!entry2.readable) {
                        this.written[i] = true;
                    }
                    try {
                        r0 = new FaultHidingSink(DiskLruCache.this.fileSystem.sink(entry2.dirtyFiles[i])) {
                            /* class com.squareup.okhttp.internal.DiskLruCache.Editor.AnonymousClass1 */

                            @Override // com.squareup.okhttp.internal.FaultHidingSink
                            public void onException(IOException iOException) {
                                synchronized (DiskLruCache.this) {
                                    Editor.this.hasErrors = true;
                                }
                            }
                        };
                    } catch (FileNotFoundException unused) {
                        return DiskLruCache.NULL_SINK;
                    }
                } else {
                    throw new IllegalStateException();
                }
            }
            return r0;
        }

        public Source newSource(int i) throws IOException {
            synchronized (DiskLruCache.this) {
                Entry entry2 = this.entry;
                if (entry2.currentEditor != this) {
                    throw new IllegalStateException();
                } else if (!entry2.readable) {
                    return null;
                } else {
                    try {
                        return DiskLruCache.this.fileSystem.source(entry2.cleanFiles[i]);
                    } catch (FileNotFoundException unused) {
                        return null;
                    }
                }
            }
        }

        public Editor(Entry entry2) {
            this.entry = entry2;
            this.written = entry2.readable ? null : new boolean[DiskLruCache.this.valueCount];
        }
    }

    public final class Entry {
        public final File[] cleanFiles;
        public Editor currentEditor;
        public final File[] dirtyFiles;
        public final String key;
        public final long[] lengths;
        public boolean readable;
        public long sequenceNumber;

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLengths(String[] strArr) throws IOException {
            int length = strArr.length;
            if (length == DiskLruCache.this.valueCount) {
                for (int i = 0; i < length; i++) {
                    try {
                        this.lengths[i] = Long.parseLong(strArr[i]);
                    } catch (NumberFormatException unused) {
                        invalidLengths(strArr);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
                return;
            }
            invalidLengths(strArr);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }

        private IOException invalidLengths(String[] strArr) throws IOException {
            throw new IOException(AnonymousClass006.A07("unexpected journal line: ", Arrays.toString(strArr)));
        }

        public Snapshot snapshot() {
            if (Thread.holdsLock(DiskLruCache.this)) {
                Source[] sourceArr = new Source[DiskLruCache.this.valueCount];
                long[] jArr = (long[]) this.lengths.clone();
                int i = 0;
                int i2 = 0;
                while (true) {
                    try {
                        DiskLruCache diskLruCache = DiskLruCache.this;
                        if (i2 >= diskLruCache.valueCount) {
                            return new Snapshot(this.key, this.sequenceNumber, sourceArr, jArr);
                        }
                        sourceArr[i2] = diskLruCache.fileSystem.source(this.cleanFiles[i2]);
                        i2++;
                    } catch (FileNotFoundException unused) {
                        while (i < DiskLruCache.this.valueCount && sourceArr[i] != null) {
                            Util.closeQuietly(sourceArr[i]);
                            i++;
                        }
                        return null;
                    }
                }
            } else {
                throw new AssertionError();
            }
        }

        public void writeLengths(BufferedSink bufferedSink) throws IOException {
            for (long j : this.lengths) {
                bufferedSink.writeByte(32).writeDecimalLong(j);
            }
        }

        public Entry(String str) {
            this.key = str;
            int i = DiskLruCache.this.valueCount;
            this.lengths = new long[i];
            this.cleanFiles = new File[i];
            this.dirtyFiles = new File[i];
            StringBuilder sb = new StringBuilder(str);
            sb.append('.');
            int length = sb.length();
            for (int i2 = 0; i2 < DiskLruCache.this.valueCount; i2++) {
                sb.append(i2);
                this.cleanFiles[i2] = new File(DiskLruCache.this.directory, sb.toString());
                sb.append(".tmp");
                this.dirtyFiles[i2] = new File(DiskLruCache.this.directory, sb.toString());
                sb.setLength(length);
            }
        }
    }

    public final class Snapshot implements Closeable {
        public final String key;
        public final long[] lengths;
        public final long sequenceNumber;
        public final Source[] sources;

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            for (Source source : this.sources) {
                Util.closeQuietly(source);
            }
        }

        public Editor edit() throws IOException {
            return DiskLruCache.this.edit(this.key, this.sequenceNumber);
        }

        public long getLength(int i) {
            return this.lengths[i];
        }

        public Source getSource(int i) {
            return this.sources[i];
        }

        public String key() {
            return this.key;
        }

        public Snapshot(String str, long j, Source[] sourceArr, long[] jArr) {
            this.key = str;
            this.sequenceNumber = j;
            this.sources = sourceArr;
            this.lengths = jArr;
        }
    }

    private synchronized void checkNotClosed() {
        if (isClosed()) {
            throw new IllegalStateException("cache is closed");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void completeEdit(Editor editor, boolean z) throws IOException {
        Entry entry = editor.entry;
        if (entry.currentEditor == editor) {
            if (z && !entry.readable) {
                int i = 0;
                while (true) {
                    if (i >= this.valueCount) {
                        break;
                    } else if (!editor.written[i]) {
                        editor.abort();
                        throw new IllegalStateException(AnonymousClass006.A03("Newly created entry didn't create value for index ", i));
                    } else if (!this.fileSystem.exists(entry.dirtyFiles[i])) {
                        editor.abort();
                        break;
                    } else {
                        i++;
                    }
                }
            }
            for (int i2 = 0; i2 < this.valueCount; i2++) {
                File file = entry.dirtyFiles[i2];
                if (!z) {
                    this.fileSystem.delete(file);
                } else if (this.fileSystem.exists(file)) {
                    File file2 = entry.cleanFiles[i2];
                    this.fileSystem.rename(file, file2);
                    long j = entry.lengths[i2];
                    long size2 = this.fileSystem.size(file2);
                    entry.lengths[i2] = size2;
                    this.size = (this.size - j) + size2;
                }
            }
            this.redundantOpCount++;
            entry.currentEditor = null;
            if (entry.readable || z) {
                entry.readable = true;
                this.journalWriter.writeUtf8("CLEAN").writeByte(32);
                this.journalWriter.writeUtf8(entry.key);
                entry.writeLengths(this.journalWriter);
                this.journalWriter.writeByte(10);
                if (z) {
                    long j2 = this.nextSequenceNumber;
                    this.nextSequenceNumber = 1 + j2;
                    entry.sequenceNumber = j2;
                }
            } else {
                this.lruEntries.remove(entry.key);
                this.journalWriter.writeUtf8("REMOVE").writeByte(32);
                this.journalWriter.writeUtf8(entry.key);
                this.journalWriter.writeByte(10);
            }
            this.journalWriter.flush();
            if (this.size > this.maxSize || journalRebuildRequired()) {
                this.executor.execute(this.cleanupRunnable);
            }
        } else {
            throw new IllegalStateException();
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void rebuildJournal() throws IOException {
        BufferedSink bufferedSink = this.journalWriter;
        if (bufferedSink != null) {
            bufferedSink.close();
        }
        RealBufferedSink realBufferedSink = new RealBufferedSink(this.fileSystem.sink(this.journalFileTmp));
        try {
            realBufferedSink.writeUtf8("libcore.io.DiskLruCache").writeByte(10);
            realBufferedSink.writeUtf8("1").writeByte(10);
            realBufferedSink.writeDecimalLong((long) this.appVersion).writeByte(10);
            realBufferedSink.writeDecimalLong((long) this.valueCount).writeByte(10);
            realBufferedSink.writeByte(10);
            for (Entry entry : this.lruEntries.values()) {
                if (entry.currentEditor != null) {
                    realBufferedSink.writeUtf8("DIRTY").writeByte(32);
                    realBufferedSink.writeUtf8(entry.key);
                } else {
                    realBufferedSink.writeUtf8("CLEAN").writeByte(32);
                    realBufferedSink.writeUtf8(entry.key);
                    entry.writeLengths(realBufferedSink);
                }
                realBufferedSink.writeByte(10);
            }
            realBufferedSink.close();
            if (this.fileSystem.exists(this.journalFile)) {
                this.fileSystem.rename(this.journalFile, this.journalFileBackup);
            }
            this.fileSystem.rename(this.journalFileTmp, this.journalFile);
            this.fileSystem.delete(this.journalFileBackup);
            this.journalWriter = newJournalWriter();
            this.hasJournalErrors = false;
        } catch (Throwable th) {
            realBufferedSink.close();
            throw th;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        if (this.initialized && !this.closed) {
            Entry[] entryArr = (Entry[]) this.lruEntries.values().toArray(new Entry[this.lruEntries.size()]);
            for (Entry entry : entryArr) {
                if (entry.currentEditor != null) {
                    entry.currentEditor.abort();
                }
            }
            trimToSize();
            this.journalWriter.close();
            this.journalWriter = null;
        }
        this.closed = true;
    }

    public synchronized void evictAll() throws IOException {
        initialize();
        for (Entry entry : (Entry[]) this.lruEntries.values().toArray(new Entry[this.lruEntries.size()])) {
            removeEntry(entry);
        }
    }

    public synchronized void flush() throws IOException {
        if (this.initialized) {
            checkNotClosed();
            trimToSize();
            this.journalWriter.flush();
        }
    }

    public synchronized Snapshot get(String str) throws IOException {
        Snapshot snapshot;
        initialize();
        checkNotClosed();
        validateKey(str);
        Entry entry = this.lruEntries.get(str);
        if (entry == null || !entry.readable || (snapshot = entry.snapshot()) == null) {
            return null;
        }
        this.redundantOpCount++;
        this.journalWriter.writeUtf8("READ").writeByte(32).writeUtf8(str).writeByte(10);
        if (journalRebuildRequired()) {
            this.executor.execute(this.cleanupRunnable);
        }
        return snapshot;
    }

    public synchronized long getMaxSize() {
        return this.maxSize;
    }

    public synchronized void initialize() throws IOException {
        if (!this.initialized) {
            if (this.fileSystem.exists(this.journalFileBackup)) {
                if (this.fileSystem.exists(this.journalFile)) {
                    this.fileSystem.delete(this.journalFileBackup);
                } else {
                    this.fileSystem.rename(this.journalFileBackup, this.journalFile);
                }
            }
            if (this.fileSystem.exists(this.journalFile)) {
                try {
                    readJournal();
                    processJournal();
                    this.initialized = true;
                } catch (IOException e) {
                    Platform platform = Platform.PLATFORM;
                    StringBuilder sb = new StringBuilder();
                    sb.append("DiskLruCache ");
                    sb.append(this.directory);
                    sb.append(" is corrupt: ");
                    sb.append(e.getMessage());
                    sb.append(", removing");
                    platform.logW(sb.toString());
                    delete();
                    this.closed = false;
                }
            }
            rebuildJournal();
            this.initialized = true;
        }
    }

    public synchronized boolean isClosed() {
        return this.closed;
    }

    public synchronized boolean remove(String str) throws IOException {
        boolean z;
        initialize();
        checkNotClosed();
        validateKey(str);
        Entry entry = this.lruEntries.get(str);
        if (entry == null) {
            z = false;
        } else {
            z = removeEntry(entry);
        }
        return z;
    }

    public synchronized void setMaxSize(long j) {
        this.maxSize = j;
        if (this.initialized) {
            this.executor.execute(this.cleanupRunnable);
        }
    }

    public synchronized long size() throws IOException {
        initialize();
        return this.size;
    }

    public synchronized Iterator<Snapshot> snapshots() throws IOException {
        initialize();
        return new Iterator<Snapshot>() {
            /* class com.squareup.okhttp.internal.DiskLruCache.AnonymousClass3 */
            public final Iterator<Entry> delegate;
            public Snapshot nextSnapshot;
            public Snapshot removeSnapshot;

            {
                this.delegate = new ArrayList(DiskLruCache.this.lruEntries.values()).iterator();
            }

            public boolean hasNext() {
                if (this.nextSnapshot != null) {
                    return true;
                }
                synchronized (DiskLruCache.this) {
                    if (!DiskLruCache.this.closed) {
                        while (this.delegate.hasNext()) {
                            Snapshot snapshot = this.delegate.next().snapshot();
                            if (snapshot != null) {
                                this.nextSnapshot = snapshot;
                                return true;
                            }
                        }
                    }
                    return false;
                }
            }

            public void remove() {
                Snapshot snapshot = this.removeSnapshot;
                if (snapshot != null) {
                    try {
                        DiskLruCache.this.remove(snapshot.key);
                    } catch (IOException unused) {
                    } catch (Throwable th) {
                        this.removeSnapshot = null;
                        throw th;
                    }
                    this.removeSnapshot = null;
                    return;
                }
                throw new IllegalStateException("remove() before next()");
            }

            @Override // java.util.Iterator
            public Snapshot next() {
                if (hasNext()) {
                    Snapshot snapshot = this.nextSnapshot;
                    this.removeSnapshot = snapshot;
                    this.nextSnapshot = null;
                    return snapshot;
                }
                throw new NoSuchElementException();
            }
        };
    }

    public static DiskLruCache create(FileSystem fileSystem2, File file, int i, int i2, long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i2 > 0) {
            return new DiskLruCache(fileSystem2, file, i, i2, j, new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactory("OkHttp DiskLruCache", true) {
                /* class com.squareup.okhttp.internal.Util.AnonymousClass1 */

                public Thread newThread(Runnable runnable) {
                    Thread thread = new Thread(runnable, r1);
                    thread.setDaemon(r2);
                    return thread;
                }
            }));
        } else {
            throw new IllegalArgumentException("valueCount <= 0");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean journalRebuildRequired() {
        int i = this.redundantOpCount;
        if (i < 2000 || i < this.lruEntries.size()) {
            return false;
        }
        return true;
    }

    private BufferedSink newJournalWriter() throws FileNotFoundException {
        return new RealBufferedSink(new FaultHidingSink(this.fileSystem.appendingSink(this.journalFile)) {
            /* class com.squareup.okhttp.internal.DiskLruCache.AnonymousClass2 */
            public static final /* synthetic */ boolean $assertionsDisabled = false;

            @Override // com.squareup.okhttp.internal.FaultHidingSink
            public void onException(IOException iOException) {
                DiskLruCache.this.hasJournalErrors = true;
            }
        });
    }

    private void processJournal() throws IOException {
        this.fileSystem.delete(this.journalFileTmp);
        Iterator<Entry> it = this.lruEntries.values().iterator();
        while (it.hasNext()) {
            Entry next = it.next();
            int i = 0;
            if (next.currentEditor != null) {
                next.currentEditor = null;
                while (i < this.valueCount) {
                    this.fileSystem.delete(next.cleanFiles[i]);
                    this.fileSystem.delete(next.dirtyFiles[i]);
                    i++;
                }
                it.remove();
            } else {
                while (i < this.valueCount) {
                    this.size += next.lengths[i];
                    i++;
                }
            }
        }
    }

    private void readJournal() throws IOException {
        BufferedSource buffer = Okio.buffer(this.fileSystem.source(this.journalFile));
        try {
            String readUtf8LineStrict = buffer.readUtf8LineStrict();
            String readUtf8LineStrict2 = buffer.readUtf8LineStrict();
            String readUtf8LineStrict3 = buffer.readUtf8LineStrict();
            String readUtf8LineStrict4 = buffer.readUtf8LineStrict();
            String readUtf8LineStrict5 = buffer.readUtf8LineStrict();
            if (!"libcore.io.DiskLruCache".equals(readUtf8LineStrict) || !"1".equals(readUtf8LineStrict2) || !Integer.toString(this.appVersion).equals(readUtf8LineStrict3) || !Integer.toString(this.valueCount).equals(readUtf8LineStrict4) || !"".equals(readUtf8LineStrict5)) {
                StringBuilder sb = new StringBuilder();
                sb.append("unexpected journal header: [");
                sb.append(readUtf8LineStrict);
                sb.append(", ");
                sb.append(readUtf8LineStrict2);
                sb.append(", ");
                sb.append(readUtf8LineStrict4);
                sb.append(", ");
                sb.append(readUtf8LineStrict5);
                sb.append("]");
                throw new IOException(sb.toString());
            }
            int i = 0;
            while (true) {
                try {
                    readJournalLine(buffer.readUtf8LineStrict());
                    i++;
                } catch (EOFException unused) {
                    this.redundantOpCount = i - this.lruEntries.size();
                    if (!buffer.exhausted()) {
                        rebuildJournal();
                    } else {
                        this.journalWriter = newJournalWriter();
                    }
                    return;
                }
            }
        } finally {
            Util.closeQuietly(buffer);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0076, code lost:
        if (r5 == -1) goto L_0x0078;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void readJournalLine(java.lang.String r9) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 152
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.DiskLruCache.readJournalLine(java.lang.String):void");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean removeEntry(Entry entry) throws IOException {
        Editor editor = entry.currentEditor;
        if (editor != null) {
            editor.hasErrors = true;
        }
        for (int i = 0; i < this.valueCount; i++) {
            this.fileSystem.delete(entry.cleanFiles[i]);
            long j = this.size;
            long[] jArr = entry.lengths;
            this.size = j - jArr[i];
            jArr[i] = 0;
        }
        this.redundantOpCount++;
        this.journalWriter.writeUtf8("REMOVE").writeByte(32).writeUtf8(entry.key).writeByte(10);
        this.lruEntries.remove(entry.key);
        if (journalRebuildRequired()) {
            this.executor.execute(this.cleanupRunnable);
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            removeEntry(this.lruEntries.values().iterator().next());
        }
    }

    private void validateKey(String str) {
        if (!LEGAL_KEY_PATTERN.matcher(str).matches()) {
            throw new IllegalArgumentException(AnonymousClass006.A09("keys must match regex [a-z0-9_-]{1,120}: \"", str, "\""));
        }
    }

    public DiskLruCache(FileSystem fileSystem2, File file, int i, int i2, long j, Executor executor2) {
        this.fileSystem = fileSystem2;
        this.directory = file;
        this.appVersion = i;
        this.journalFile = new File(file, "journal");
        this.journalFileTmp = new File(file, "journal.tmp");
        this.journalFileBackup = new File(file, "journal.bkp");
        this.valueCount = i2;
        this.maxSize = j;
        this.executor = executor2;
    }

    public void delete() throws IOException {
        close();
        this.fileSystem.deleteContents(this.directory);
    }

    public File getDirectory() {
        return this.directory;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0022, code lost:
        if (r2 != null) goto L_0x0024;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized com.squareup.okhttp.internal.DiskLruCache.Editor edit(java.lang.String r7, long r8) throws java.io.IOException {
        /*
            r6 = this;
            monitor-enter(r6)
            r6.initialize()     // Catch:{ all -> 0x0060 }
            r6.checkNotClosed()     // Catch:{ all -> 0x0060 }
            r6.validateKey(r7)     // Catch:{ all -> 0x0060 }
            java.util.LinkedHashMap<java.lang.String, com.squareup.okhttp.internal.DiskLruCache$Entry> r0 = r6.lruEntries     // Catch:{ all -> 0x0060 }
            java.lang.Object r2 = r0.get(r7)     // Catch:{ all -> 0x0060 }
            com.squareup.okhttp.internal.DiskLruCache$Entry r2 = (com.squareup.okhttp.internal.DiskLruCache.Entry) r2     // Catch:{ all -> 0x0060 }
            r3 = -1
            r5 = 0
            int r0 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r0 == 0) goto L_0x0022
            if (r2 == 0) goto L_0x005e
            long r3 = r2.sequenceNumber     // Catch:{ all -> 0x0060 }
            int r0 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r0 == 0) goto L_0x0024
            goto L_0x005e
        L_0x0022:
            if (r2 == 0) goto L_0x0029
        L_0x0024:
            com.squareup.okhttp.internal.DiskLruCache$Editor r0 = r2.currentEditor     // Catch:{ all -> 0x0060 }
            if (r0 == 0) goto L_0x0029
            goto L_0x005e
        L_0x0029:
            okio.BufferedSink r1 = r6.journalWriter     // Catch:{ all -> 0x0060 }
            java.lang.String r0 = "DIRTY"
            okio.BufferedSink r1 = r1.writeUtf8(r0)     // Catch:{ all -> 0x0060 }
            r0 = 32
            okio.BufferedSink r0 = r1.writeByte(r0)     // Catch:{ all -> 0x0060 }
            okio.BufferedSink r1 = r0.writeUtf8(r7)     // Catch:{ all -> 0x0060 }
            r0 = 10
            r1.writeByte(r0)     // Catch:{ all -> 0x0060 }
            okio.BufferedSink r0 = r6.journalWriter     // Catch:{ all -> 0x0060 }
            r0.flush()     // Catch:{ all -> 0x0060 }
            boolean r0 = r6.hasJournalErrors     // Catch:{ all -> 0x0060 }
            if (r0 != 0) goto L_0x005e
            if (r2 != 0) goto L_0x0055
            com.squareup.okhttp.internal.DiskLruCache$Entry r2 = new com.squareup.okhttp.internal.DiskLruCache$Entry     // Catch:{ all -> 0x0060 }
            r2.<init>(r7)     // Catch:{ all -> 0x0060 }
            java.util.LinkedHashMap<java.lang.String, com.squareup.okhttp.internal.DiskLruCache$Entry> r0 = r6.lruEntries     // Catch:{ all -> 0x0060 }
            r0.put(r7, r2)     // Catch:{ all -> 0x0060 }
        L_0x0055:
            com.squareup.okhttp.internal.DiskLruCache$Editor r0 = new com.squareup.okhttp.internal.DiskLruCache$Editor     // Catch:{ all -> 0x0060 }
            r0.<init>(r2)     // Catch:{ all -> 0x0060 }
            r2.currentEditor = r0     // Catch:{ all -> 0x0060 }
            monitor-exit(r6)
            return r0
        L_0x005e:
            monitor-exit(r6)
            return r5
        L_0x0060:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.DiskLruCache.edit(java.lang.String, long):com.squareup.okhttp.internal.DiskLruCache$Editor");
    }

    public Editor edit(String str) throws IOException {
        return edit(str, -1);
    }
}
