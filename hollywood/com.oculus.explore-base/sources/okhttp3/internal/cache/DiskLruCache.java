package okhttp3.internal.cache;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;
import okhttp3.internal.io.FileSystem;
import okio.BufferedSink;

public final class DiskLruCache implements Closeable, Flushable {
    static final /* synthetic */ boolean $assertionsDisabled = (!DiskLruCache.class.desiredAssertionStatus());
    static final Pattern LEGAL_KEY_PATTERN = Pattern.compile("[a-z0-9_-]{1,120}");
    private final Runnable cleanupRunnable;
    boolean closed;
    private final Executor executor;
    final FileSystem fileSystem;
    boolean initialized;
    BufferedSink journalWriter;
    final LinkedHashMap<String, Entry> lruEntries;
    private long maxSize;
    boolean mostRecentTrimFailed;
    private long nextSequenceNumber;
    int redundantOpCount;
    private long size;
    final int valueCount;

    /* access modifiers changed from: package-private */
    public synchronized void completeEdit(Editor editor, boolean success) throws IOException {
        Entry entry = editor.entry;
        if (entry.currentEditor != editor) {
            throw new IllegalStateException();
        }
        if (success && !entry.readable) {
            int i = 0;
            while (true) {
                if (i >= this.valueCount) {
                    break;
                } else if (!editor.written[i]) {
                    editor.abort();
                    throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                } else if (!this.fileSystem.exists(entry.dirtyFiles[i])) {
                    editor.abort();
                    break;
                } else {
                    i++;
                }
            }
        }
        for (int i2 = 0; i2 < this.valueCount; i2++) {
            File dirty = entry.dirtyFiles[i2];
            if (!success) {
                this.fileSystem.delete(dirty);
            } else if (this.fileSystem.exists(dirty)) {
                File clean = entry.cleanFiles[i2];
                this.fileSystem.rename(dirty, clean);
                long oldLength = entry.lengths[i2];
                long newLength = this.fileSystem.size(clean);
                entry.lengths[i2] = newLength;
                this.size = (this.size - oldLength) + newLength;
            }
        }
        this.redundantOpCount++;
        entry.currentEditor = null;
        if (entry.readable || success) {
            entry.readable = true;
            this.journalWriter.writeUtf8("CLEAN").writeByte(32);
            this.journalWriter.writeUtf8(entry.key);
            entry.writeLengths(this.journalWriter);
            this.journalWriter.writeByte(10);
            if (success) {
                long j = this.nextSequenceNumber;
                this.nextSequenceNumber = 1 + j;
                entry.sequenceNumber = j;
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
    }

    /* access modifiers changed from: package-private */
    public boolean journalRebuildRequired() {
        return this.redundantOpCount >= 2000 && this.redundantOpCount >= this.lruEntries.size();
    }

    /* access modifiers changed from: package-private */
    public boolean removeEntry(Entry entry) throws IOException {
        if (entry.currentEditor != null) {
            entry.currentEditor.detach();
        }
        for (int i = 0; i < this.valueCount; i++) {
            this.fileSystem.delete(entry.cleanFiles[i]);
            this.size -= entry.lengths[i];
            entry.lengths[i] = 0;
        }
        this.redundantOpCount++;
        this.journalWriter.writeUtf8("REMOVE").writeByte(32).writeUtf8(entry.key).writeByte(10);
        this.lruEntries.remove(entry.key);
        if (!journalRebuildRequired()) {
            return true;
        }
        this.executor.execute(this.cleanupRunnable);
        return true;
    }

    public synchronized boolean isClosed() {
        return this.closed;
    }

    private synchronized void checkNotClosed() {
        if (isClosed()) {
            throw new IllegalStateException("cache is closed");
        }
    }

    @Override // java.io.Flushable
    public synchronized void flush() throws IOException {
        if (this.initialized) {
            checkNotClosed();
            trimToSize();
            this.journalWriter.flush();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        if (!this.initialized || this.closed) {
            this.closed = true;
        } else {
            Entry[] entryArr = (Entry[]) this.lruEntries.values().toArray(new Entry[this.lruEntries.size()]);
            for (Entry entry : entryArr) {
                if (entry.currentEditor != null) {
                    entry.currentEditor.abort();
                }
            }
            trimToSize();
            this.journalWriter.close();
            this.journalWriter = null;
            this.closed = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            removeEntry(this.lruEntries.values().iterator().next());
        }
        this.mostRecentTrimFailed = false;
    }

    public final class Editor {
        private boolean done;
        final Entry entry;
        final /* synthetic */ DiskLruCache this$0;
        final boolean[] written;

        /* access modifiers changed from: package-private */
        public void detach() {
            if (this.entry.currentEditor == this) {
                for (int i = 0; i < this.this$0.valueCount; i++) {
                    try {
                        this.this$0.fileSystem.delete(this.entry.dirtyFiles[i]);
                    } catch (IOException e) {
                    }
                }
                this.entry.currentEditor = null;
            }
        }

        public void abort() throws IOException {
            synchronized (this.this$0) {
                if (this.done) {
                    throw new IllegalStateException();
                }
                if (this.entry.currentEditor == this) {
                    this.this$0.completeEdit(this, false);
                }
                this.done = true;
            }
        }
    }

    /* access modifiers changed from: private */
    public final class Entry {
        final File[] cleanFiles;
        Editor currentEditor;
        final File[] dirtyFiles;
        final String key;
        final long[] lengths;
        boolean readable;
        long sequenceNumber;

        /* access modifiers changed from: package-private */
        public void writeLengths(BufferedSink writer) throws IOException {
            for (long length : this.lengths) {
                writer.writeByte(32).writeDecimalLong(length);
            }
        }
    }
}
