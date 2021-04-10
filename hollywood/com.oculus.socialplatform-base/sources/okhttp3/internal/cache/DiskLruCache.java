package okhttp3.internal.cache;

import X.AnonymousClass006;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import okhttp3.internal.Util;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.RealBufferedSink;
import okio.Sink;
import okio.Source;

public final class DiskLruCache implements Closeable, Flushable {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final long ANY_SEQUENCE_NUMBER = -1;
    public static final String CLEAN = "CLEAN";
    public static final String DIRTY = "DIRTY";
    public static final String JOURNAL_FILE = "journal";
    public static final String JOURNAL_FILE_BACKUP = "journal.bkp";
    public static final String JOURNAL_FILE_TEMP = "journal.tmp";
    public static final Pattern LEGAL_KEY_PATTERN = Pattern.compile("[a-z0-9_-]{1,120}");
    public static final String MAGIC = "libcore.io.DiskLruCache";
    public static final String READ = "READ";
    public static final String REMOVE = "REMOVE";
    public static final String VERSION_1 = "1";
    public final int appVersion;
    public final Runnable cleanupRunnable = new Runnable() {
        /* class okhttp3.internal.cache.DiskLruCache.AnonymousClass1 */

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
                    } catch (IOException unused) {
                        DiskLruCache.this.mostRecentTrimFailed = true;
                    }
                    try {
                        DiskLruCache diskLruCache2 = DiskLruCache.this;
                        if (diskLruCache2.journalRebuildRequired()) {
                            diskLruCache2.rebuildJournal();
                            DiskLruCache.this.redundantOpCount = 0;
                        }
                    } catch (IOException unused2) {
                        DiskLruCache diskLruCache3 = DiskLruCache.this;
                        diskLruCache3.mostRecentRebuildFailed = true;
                        diskLruCache3.journalWriter = new RealBufferedSink(new Sink() {
                            /* class okio.Okio.AnonymousClass3 */

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
                        });
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
    public boolean mostRecentRebuildFailed;
    public boolean mostRecentTrimFailed;
    public long nextSequenceNumber = 0;
    public int redundantOpCount;
    public long size = 0;
    public final int valueCount;

    public final class Editor {
        public boolean done;
        public final Entry entry;
        public final boolean[] written;

        public Editor(Entry entry2) {
            boolean[] zArr;
            this.entry = entry2;
            if (entry2.readable) {
                zArr = null;
            } else {
                zArr = new boolean[DiskLruCache.this.valueCount];
            }
            this.written = zArr;
        }

        public void abort() throws IOException {
            synchronized (DiskLruCache.this) {
                if (!this.done) {
                    if (this.entry.currentEditor == this) {
                        DiskLruCache.this.completeEdit(this, false);
                    }
                    this.done = true;
                } else {
                    throw new IllegalStateException();
                }
            }
        }

        public void abortUnlessCommitted() {
            synchronized (DiskLruCache.this) {
                if (!this.done && this.entry.currentEditor == this) {
                    try {
                        DiskLruCache.this.completeEdit(this, false);
                    } catch (IOException unused) {
                    }
                }
            }
        }

        public void commit() throws IOException {
            synchronized (DiskLruCache.this) {
                if (!this.done) {
                    if (this.entry.currentEditor == this) {
                        DiskLruCache.this.completeEdit(this, true);
                    }
                    this.done = true;
                } else {
                    throw new IllegalStateException();
                }
            }
        }

        public void detach() {
            if (this.entry.currentEditor == this) {
                int i = 0;
                while (true) {
                    DiskLruCache diskLruCache = DiskLruCache.this;
                    if (i < diskLruCache.valueCount) {
                        try {
                            diskLruCache.fileSystem.delete(this.entry.dirtyFiles[i]);
                        } catch (IOException unused) {
                        }
                        i++;
                    } else {
                        this.entry.currentEditor = null;
                        return;
                    }
                }
            }
        }

        public Sink newSink(int i) {
            Okio.AnonymousClass3 r0;
            synchronized (DiskLruCache.this) {
                if (!this.done) {
                    Entry entry2 = this.entry;
                    if (entry2.currentEditor != this) {
                        r0 = new Sink() {
                            /* class okio.Okio.AnonymousClass3 */

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
                    } else {
                        if (!entry2.readable) {
                            this.written[i] = true;
                        }
                        try {
                            return new FaultHidingSink(DiskLruCache.this.fileSystem.sink(entry2.dirtyFiles[i])) {
                                /* class okhttp3.internal.cache.DiskLruCache.Editor.AnonymousClass1 */

                                @Override // okhttp3.internal.cache.FaultHidingSink
                                public void onException(IOException iOException) {
                                    synchronized (DiskLruCache.this) {
                                        Editor.this.detach();
                                    }
                                }
                            };
                        } catch (FileNotFoundException unused) {
                            r0 = new Sink() {
                                /* class okio.Okio.AnonymousClass3 */

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
                        }
                    }
                    return r0;
                }
                throw new IllegalStateException();
            }
        }

        public Source newSource(int i) {
            synchronized (DiskLruCache.this) {
                if (!this.done) {
                    Entry entry2 = this.entry;
                    if (!entry2.readable || entry2.currentEditor != this) {
                        return null;
                    }
                    try {
                        return DiskLruCache.this.fileSystem.source(entry2.cleanFiles[i]);
                    } catch (FileNotFoundException unused) {
                        return null;
                    }
                } else {
                    throw new IllegalStateException();
                }
            }
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

        public void setLengths(String[] strArr) throws IOException {
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
                        while (true) {
                            DiskLruCache diskLruCache2 = DiskLruCache.this;
                            if (i >= diskLruCache2.valueCount || sourceArr[i] == null) {
                                try {
                                    diskLruCache2.removeEntry(this);
                                    return null;
                                } catch (IOException unused2) {
                                    return null;
                                }
                            } else {
                                Util.closeQuietly(sourceArr[i]);
                                i++;
                            }
                        }
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
    }

    public final class Snapshot implements Closeable {
        public final String key;
        public final long[] lengths;
        public final long sequenceNumber;
        public final Source[] sources;

        public Snapshot(String str, long j, Source[] sourceArr, long[] jArr) {
            this.key = str;
            this.sequenceNumber = j;
            this.sources = sourceArr;
            this.lengths = jArr;
        }

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
    }

    private synchronized void checkNotClosed() {
        if (isClosed()) {
            throw new IllegalStateException("cache is closed");
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

    public synchronized void completeEdit(Editor editor, boolean z) throws IOException {
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

    public synchronized void evictAll() throws IOException {
        initialize();
        for (Entry entry : (Entry[]) this.lruEntries.values().toArray(new Entry[this.lruEntries.size()])) {
            removeEntry(entry);
        }
        this.mostRecentTrimFailed = false;
    }

    @Override // java.io.Flushable
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
                    platform.log(5, sb.toString(), e);
                    delete();
                    this.closed = false;
                } catch (Throwable th) {
                    this.closed = false;
                    throw th;
                }
            }
            rebuildJournal();
            this.initialized = true;
        }
    }

    public synchronized boolean isClosed() {
        return this.closed;
    }

    /* JADX INFO: finally extract failed */
    public synchronized void rebuildJournal() throws IOException {
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
            this.mostRecentRebuildFailed = false;
        } catch (Throwable th) {
            realBufferedSink.close();
            throw th;
        }
    }

    public synchronized boolean remove(String str) throws IOException {
        initialize();
        checkNotClosed();
        validateKey(str);
        Entry entry = this.lruEntries.get(str);
        if (entry == null) {
            return false;
        }
        boolean removeEntry = removeEntry(entry);
        if (removeEntry && this.size <= this.maxSize) {
            this.mostRecentTrimFailed = false;
        }
        return removeEntry;
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
            /* class okhttp3.internal.cache.DiskLruCache.AnonymousClass3 */
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
            return new DiskLruCache(fileSystem2, file, i, i2, j, new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), 
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x002a: RETURN  
                  (wrap: okhttp3.internal.cache.DiskLruCache : 0x0027: CONSTRUCTOR  (r4v0 okhttp3.internal.cache.DiskLruCache) = 
                  (r11v0 'fileSystem2' okhttp3.internal.io.FileSystem)
                  (r12v0 'file' java.io.File)
                  (r13v0 'i' int)
                  (r14v0 'i2' int)
                  (r15v0 'j' long)
                  (wrap: java.util.concurrent.ThreadPoolExecutor : 0x001e: CONSTRUCTOR  (r0v4 java.util.concurrent.ThreadPoolExecutor) = 
                  (0 int)
                  (1 int)
                  (60 long)
                  (wrap: java.util.concurrent.TimeUnit : 0x000e: SGET  (r5v0 java.util.concurrent.TimeUnit) =  java.util.concurrent.TimeUnit.SECONDS java.util.concurrent.TimeUnit)
                  (wrap: java.util.concurrent.LinkedBlockingQueue : 0x0012: CONSTRUCTOR  (r6v0 java.util.concurrent.LinkedBlockingQueue) =  call: java.util.concurrent.LinkedBlockingQueue.<init>():void type: CONSTRUCTOR)
                  (wrap: okhttp3.internal.Util$1 : 0x0019: CONSTRUCTOR  (r7v0 okhttp3.internal.Util$1) = ("OkHttp DiskLruCache"), true call: okhttp3.internal.Util.1.<init>(java.lang.String, boolean):void type: CONSTRUCTOR)
                 call: java.util.concurrent.ThreadPoolExecutor.<init>(int, int, long, java.util.concurrent.TimeUnit, java.util.concurrent.BlockingQueue, java.util.concurrent.ThreadFactory):void type: CONSTRUCTOR)
                 call: okhttp3.internal.cache.DiskLruCache.<init>(okhttp3.internal.io.FileSystem, java.io.File, int, int, long, java.util.concurrent.Executor):void type: CONSTRUCTOR)
                 in method: okhttp3.internal.cache.DiskLruCache.create(okhttp3.internal.io.FileSystem, java.io.File, int, int, long):okhttp3.internal.cache.DiskLruCache, file: classes.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:217)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:110)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:56)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:99)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:143)
                	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:176)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:153)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:244)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:237)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:342)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:295)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:264)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.util.ArrayList.forEach(ArrayList.java:1259)
                	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0027: CONSTRUCTOR  (r4v0 okhttp3.internal.cache.DiskLruCache) = 
                  (r11v0 'fileSystem2' okhttp3.internal.io.FileSystem)
                  (r12v0 'file' java.io.File)
                  (r13v0 'i' int)
                  (r14v0 'i2' int)
                  (r15v0 'j' long)
                  (wrap: java.util.concurrent.ThreadPoolExecutor : 0x001e: CONSTRUCTOR  (r0v4 java.util.concurrent.ThreadPoolExecutor) = 
                  (0 int)
                  (1 int)
                  (60 long)
                  (wrap: java.util.concurrent.TimeUnit : 0x000e: SGET  (r5v0 java.util.concurrent.TimeUnit) =  java.util.concurrent.TimeUnit.SECONDS java.util.concurrent.TimeUnit)
                  (wrap: java.util.concurrent.LinkedBlockingQueue : 0x0012: CONSTRUCTOR  (r6v0 java.util.concurrent.LinkedBlockingQueue) =  call: java.util.concurrent.LinkedBlockingQueue.<init>():void type: CONSTRUCTOR)
                  (wrap: okhttp3.internal.Util$1 : 0x0019: CONSTRUCTOR  (r7v0 okhttp3.internal.Util$1) = ("OkHttp DiskLruCache"), true call: okhttp3.internal.Util.1.<init>(java.lang.String, boolean):void type: CONSTRUCTOR)
                 call: java.util.concurrent.ThreadPoolExecutor.<init>(int, int, long, java.util.concurrent.TimeUnit, java.util.concurrent.BlockingQueue, java.util.concurrent.ThreadFactory):void type: CONSTRUCTOR)
                 call: okhttp3.internal.cache.DiskLruCache.<init>(okhttp3.internal.io.FileSystem, java.io.File, int, int, long, java.util.concurrent.Executor):void type: CONSTRUCTOR in method: okhttp3.internal.cache.DiskLruCache.create(okhttp3.internal.io.FileSystem, java.io.File, int, int, long):okhttp3.internal.cache.DiskLruCache, file: classes.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:313)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
                	... 21 more
                Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001e: CONSTRUCTOR  (r0v4 java.util.concurrent.ThreadPoolExecutor) = 
                  (0 int)
                  (1 int)
                  (60 long)
                  (wrap: java.util.concurrent.TimeUnit : 0x000e: SGET  (r5v0 java.util.concurrent.TimeUnit) =  java.util.concurrent.TimeUnit.SECONDS java.util.concurrent.TimeUnit)
                  (wrap: java.util.concurrent.LinkedBlockingQueue : 0x0012: CONSTRUCTOR  (r6v0 java.util.concurrent.LinkedBlockingQueue) =  call: java.util.concurrent.LinkedBlockingQueue.<init>():void type: CONSTRUCTOR)
                  (wrap: okhttp3.internal.Util$1 : 0x0019: CONSTRUCTOR  (r7v0 okhttp3.internal.Util$1) = ("OkHttp DiskLruCache"), true call: okhttp3.internal.Util.1.<init>(java.lang.String, boolean):void type: CONSTRUCTOR)
                 call: java.util.concurrent.ThreadPoolExecutor.<init>(int, int, long, java.util.concurrent.TimeUnit, java.util.concurrent.BlockingQueue, java.util.concurrent.ThreadFactory):void type: CONSTRUCTOR in method: okhttp3.internal.cache.DiskLruCache.create(okhttp3.internal.io.FileSystem, java.io.File, int, int, long):okhttp3.internal.cache.DiskLruCache, file: classes.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:663)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                	... 25 more
                Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0019: CONSTRUCTOR  (r7v0 okhttp3.internal.Util$1) = ("OkHttp DiskLruCache"), true call: okhttp3.internal.Util.1.<init>(java.lang.String, boolean):void type: CONSTRUCTOR in method: okhttp3.internal.cache.DiskLruCache.create(okhttp3.internal.io.FileSystem, java.io.File, int, int, long):okhttp3.internal.cache.DiskLruCache, file: classes.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:663)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                	... 31 more
                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: okhttp3.internal.Util, state: GENERATED_AND_UNLOADED
                	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                	... 37 more
                */
            /*
                r1 = 0
                r9 = r15
                int r0 = (r15 > r1 ? 1 : (r15 == r1 ? 0 : -1))
                if (r0 <= 0) goto L_0x0033
                r8 = r14
                if (r14 <= 0) goto L_0x002b
                r1 = 0
                r2 = 1
                r3 = 60
                java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.SECONDS
                java.util.concurrent.LinkedBlockingQueue r6 = new java.util.concurrent.LinkedBlockingQueue
                r6.<init>()
                java.lang.String r0 = "OkHttp DiskLruCache"
                okhttp3.internal.Util$1 r7 = new okhttp3.internal.Util$1
                r7.<init>(r0, r2)
                java.util.concurrent.ThreadPoolExecutor r0 = new java.util.concurrent.ThreadPoolExecutor
                r0.<init>(r1, r2, r3, r5, r6, r7)
                r6 = r12
                r5 = r11
                r7 = r13
                r11 = r0
                okhttp3.internal.cache.DiskLruCache r4 = new okhttp3.internal.cache.DiskLruCache
                r4.<init>(r5, r6, r7, r8, r9, r11)
                return r4
            L_0x002b:
                java.lang.String r1 = "valueCount <= 0"
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                r0.<init>(r1)
                throw r0
            L_0x0033:
                java.lang.String r1 = "maxSize <= 0"
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.create(okhttp3.internal.io.FileSystem, java.io.File, int, int, long):okhttp3.internal.cache.DiskLruCache");
        }

        private BufferedSink newJournalWriter() throws FileNotFoundException {
            return new RealBufferedSink(new FaultHidingSink(this.fileSystem.appendingSink(this.journalFile)) {
                /* class okhttp3.internal.cache.DiskLruCache.AnonymousClass2 */
                public static final /* synthetic */ boolean $assertionsDisabled = false;

                @Override // okhttp3.internal.cache.FaultHidingSink
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
            if (r4 == -1) goto L_0x0078;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void readJournalLine(java.lang.String r8) throws java.io.IOException {
            /*
            // Method dump skipped, instructions count: 152
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.readJournalLine(java.lang.String):void");
        }

        private void validateKey(String str) {
            if (!LEGAL_KEY_PATTERN.matcher(str).matches()) {
                throw new IllegalArgumentException(AnonymousClass006.A09("keys must match regex [a-z0-9_-]{1,120}: \"", str, "\""));
            }
        }

        public boolean journalRebuildRequired() {
            int i = this.redundantOpCount;
            if (i < 2000 || i < this.lruEntries.size()) {
                return false;
            }
            return true;
        }

        public boolean removeEntry(Entry entry) throws IOException {
            Editor editor = entry.currentEditor;
            if (editor != null) {
                editor.detach();
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

        public void trimToSize() throws IOException {
            while (this.size > this.maxSize) {
                removeEntry(this.lruEntries.values().iterator().next());
            }
            this.mostRecentTrimFailed = false;
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

        public Editor edit(String str) throws IOException {
            return edit(str, -1);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0022, code lost:
            if (r2 != null) goto L_0x0024;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized okhttp3.internal.cache.DiskLruCache.Editor edit(java.lang.String r7, long r8) throws java.io.IOException {
            /*
            // Method dump skipped, instructions count: 114
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.edit(java.lang.String, long):okhttp3.internal.cache.DiskLruCache$Editor");
        }
    }
