package java.util.zip;

import dalvik.system.CloseGuard;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.WeakHashMap;

public class ZipFile implements ZipConstants, Closeable {
    private static final boolean usemmap = true;
    private volatile boolean closeRequested;
    private final File fileToRemoveOnClose;
    private final CloseGuard guard;
    private Deque inflaterCache;
    private long jzfile;
    private final boolean locsig;
    private final String name;
    private final Map streams;
    private final int total;
    private ZipCoder zc;

    private static native void close(long j);

    /* access modifiers changed from: private */
    public static native void freeEntry(long j, long j2);

    private static native long getEntry(long j, byte[] bArr, boolean z);

    private static native byte[] getEntryBytes(long j, int i);

    /* access modifiers changed from: private */
    public static native long getEntryCSize(long j);

    private static native long getEntryCrc(long j);

    private static native int getEntryFlag(long j);

    private static native int getEntryMethod(long j);

    /* access modifiers changed from: private */
    public static native long getEntrySize(long j);

    private static native long getEntryTime(long j);

    /* access modifiers changed from: private */
    public static native long getNextEntry(long j, int i);

    private static native int getTotal(long j);

    /* access modifiers changed from: private */
    public static native String getZipMessage(long j);

    private static native long open(String str, int i, long j, boolean z);

    /* access modifiers changed from: private */
    public static native int read(long j, long j2, long j3, byte[] bArr, int i, int i2);

    private static native boolean startsWithLOC(long j);

    public ZipFile(File file, int i) {
        this(file, i, StandardCharsets.UTF_8);
    }

    public ZipFile(File file, int i, Charset charset) {
        this.closeRequested = false;
        this.guard = CloseGuard.get();
        this.streams = new WeakHashMap();
        this.inflaterCache = new ArrayDeque();
        File file2 = null;
        if ((i & 1) == 0 || (i & -6) != 0) {
            new StringBuilder().append("Illegal mode: 0x");
            Integer.toHexString(i);
            throw null;
        }
        String path = file.getPath();
        this.fileToRemoveOnClose = (i & 4) != 0 ? file : file2;
        if (charset != null) {
            this.zc = ZipCoder.get(charset);
            this.jzfile = open(path, i, file.lastModified(), usemmap);
            this.name = path;
            this.total = getTotal(this.jzfile);
            this.locsig = startsWithLOC(this.jzfile);
            this.guard.open("close");
            return;
        }
        throw new NullPointerException("charset is null");
    }

    public ZipEntry getEntry(String str) {
        if (str != null) {
            synchronized (this) {
                ensureOpen();
                long entry = getEntry(this.jzfile, this.zc.getBytes(str), usemmap);
                if (entry == 0) {
                    return null;
                }
                ZipEntry zipEntry = getZipEntry(str, entry);
                freeEntry(this.jzfile, entry);
                return zipEntry;
            }
        }
        throw new NullPointerException("name");
    }

    public InputStream getInputStream(ZipEntry zipEntry) {
        long j;
        if (zipEntry != null) {
            synchronized (this) {
                ensureOpen();
                if (this.zc.isUTF8() || (zipEntry.flag & 2048) == 0) {
                    j = getEntry(this.jzfile, this.zc.getBytes(zipEntry.name), usemmap);
                } else {
                    j = getEntry(this.jzfile, this.zc.getBytesUTF8(zipEntry.name), usemmap);
                }
                if (j == 0) {
                    return null;
                }
                ZipFileInputStream zipFileInputStream = new ZipFileInputStream(j);
                int entryMethod = getEntryMethod(j);
                if (entryMethod == 0) {
                    synchronized (this.streams) {
                        this.streams.put(zipFileInputStream, null);
                    }
                    return zipFileInputStream;
                } else if (entryMethod == 8) {
                    long entrySize = getEntrySize(j) + 2;
                    if (entrySize > 65536) {
                        entrySize = 65536;
                    }
                    if (entrySize <= 0) {
                        entrySize = 4096;
                    }
                    Inflater inflater = getInflater();
                    ZipFileInflaterInputStream zipFileInflaterInputStream = new ZipFileInflaterInputStream(zipFileInputStream, inflater, (int) entrySize);
                    synchronized (this.streams) {
                        this.streams.put(zipFileInflaterInputStream, inflater);
                    }
                    return zipFileInflaterInputStream;
                } else {
                    throw new ZipException("invalid compression method");
                }
            }
        } else {
            throw new NullPointerException("entry");
        }
    }

    /* access modifiers changed from: private */
    public class ZipFileInflaterInputStream extends InflaterInputStream {
        private volatile boolean closeRequested = false;
        private boolean eof = false;
        private final ZipFileInputStream zfin;

        ZipFileInflaterInputStream(ZipFileInputStream zipFileInputStream, Inflater inflater, int i) {
            super(zipFileInputStream, inflater, i);
            this.zfin = zipFileInputStream;
        }

        @Override // java.io.FilterInputStream, java.util.zip.InflaterInputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
        public void close() {
            Inflater inflater;
            if (!this.closeRequested) {
                this.closeRequested = ZipFile.usemmap;
                super.close();
                synchronized (ZipFile.this.streams) {
                    inflater = (Inflater) ZipFile.this.streams.remove(this);
                }
                if (inflater != null) {
                    ZipFile.this.releaseInflater(inflater);
                }
            }
        }

        /* access modifiers changed from: protected */
        @Override // java.util.zip.InflaterInputStream
        public void fill() {
            if (!this.eof) {
                InputStream inputStream = this.in;
                byte[] bArr = this.buf;
                this.len = inputStream.read(bArr, 0, bArr.length);
                if (this.len == -1) {
                    this.buf[0] = 0;
                    this.len = 1;
                    this.eof = ZipFile.usemmap;
                }
                this.inf.setInput(this.buf, 0, this.len);
                return;
            }
            throw new EOFException("Unexpected end of ZLIB input stream");
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int available() {
            if (this.closeRequested) {
                return 0;
            }
            long size = this.zfin.size() - this.inf.getBytesWritten();
            if (size > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            return (int) size;
        }
    }

    private Inflater getInflater() {
        Inflater inflater;
        synchronized (this.inflaterCache) {
            do {
                inflater = (Inflater) this.inflaterCache.poll();
                if (inflater == null) {
                    return new Inflater(usemmap);
                }
            } while (inflater.ended());
            return inflater;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void releaseInflater(Inflater inflater) {
        if (!inflater.ended()) {
            inflater.reset();
            synchronized (this.inflaterCache) {
                this.inflaterCache.add(inflater);
            }
        }
    }

    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: private */
    public class ZipEntryIterator implements Enumeration, Iterator {
        private int i = 0;

        public ZipEntryIterator() {
            ZipFile.this.ensureOpen();
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return hasNext();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            boolean z;
            synchronized (ZipFile.this) {
                ZipFile.this.ensureOpen();
                z = this.i < ZipFile.this.total ? ZipFile.usemmap : false;
            }
            return z;
        }

        @Override // java.util.Enumeration
        public ZipEntry nextElement() {
            return next();
        }

        @Override // java.util.Iterator
        public ZipEntry next() {
            ZipEntry zipEntry;
            String str;
            synchronized (ZipFile.this) {
                ZipFile.this.ensureOpen();
                if (this.i < ZipFile.this.total) {
                    long j = ZipFile.this.jzfile;
                    int i2 = this.i;
                    this.i = i2 + 1;
                    long nextEntry = ZipFile.getNextEntry(j, i2);
                    if (nextEntry == 0) {
                        if (ZipFile.this.closeRequested) {
                            str = "ZipFile concurrently closed";
                        } else {
                            str = ZipFile.getZipMessage(ZipFile.this.jzfile);
                        }
                        throw new ZipError("jzentry == 0,\n jzfile = " + ZipFile.this.jzfile + ",\n total = " + ZipFile.this.total + ",\n name = " + ZipFile.this.name + ",\n i = " + this.i + ",\n message = " + str);
                    }
                    zipEntry = ZipFile.this.getZipEntry(null, nextEntry);
                    ZipFile.freeEntry(ZipFile.this.jzfile, nextEntry);
                } else {
                    throw new NoSuchElementException();
                }
            }
            return zipEntry;
        }
    }

    public Enumeration entries() {
        return new ZipEntryIterator();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private ZipEntry getZipEntry(String str, long j) {
        ZipEntry zipEntry = new ZipEntry();
        zipEntry.flag = getEntryFlag(j);
        if (str != null) {
            zipEntry.name = str;
            zipEntry.xdostime = getEntryTime(j);
            zipEntry.crc = getEntryCrc(j);
            zipEntry.size = getEntrySize(j);
            zipEntry.csize = getEntryCSize(j);
            zipEntry.method = getEntryMethod(j);
            zipEntry.setExtra0(getEntryBytes(j, 1), false);
            byte[] entryBytes = getEntryBytes(j, 2);
            if (entryBytes == null) {
                zipEntry.comment = null;
                return zipEntry;
            } else if (this.zc.isUTF8() || (zipEntry.flag & 2048) == 0) {
                this.zc.toString(entryBytes, entryBytes.length);
                throw null;
            } else {
                this.zc.toStringUTF8(entryBytes, entryBytes.length);
                throw null;
            }
        } else {
            byte[] entryBytes2 = getEntryBytes(j, 0);
            if (this.zc.isUTF8() || (zipEntry.flag & 2048) == 0) {
                this.zc.toString(entryBytes2, entryBytes2.length);
                throw null;
            }
            this.zc.toStringUTF8(entryBytes2, entryBytes2.length);
            throw null;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (!this.closeRequested) {
            CloseGuard closeGuard = this.guard;
            if (closeGuard != null) {
                closeGuard.close();
            }
            this.closeRequested = usemmap;
            synchronized (this) {
                if (this.streams != null) {
                    synchronized (this.streams) {
                        if (!this.streams.isEmpty()) {
                            HashMap hashMap = new HashMap(this.streams);
                            this.streams.clear();
                            for (Map.Entry entry : hashMap.entrySet()) {
                                ((InputStream) entry.getKey()).close();
                                Inflater inflater = (Inflater) entry.getValue();
                                if (inflater != null) {
                                    inflater.end();
                                }
                            }
                        }
                    }
                }
                if (this.inflaterCache != null) {
                    synchronized (this.inflaterCache) {
                        while (true) {
                            Inflater inflater2 = (Inflater) this.inflaterCache.poll();
                            if (inflater2 != null) {
                                inflater2.end();
                            }
                        }
                    }
                    break;
                }
                if (this.jzfile != 0) {
                    long j = this.jzfile;
                    this.jzfile = 0;
                    close(j);
                }
                if (this.fileToRemoveOnClose != null) {
                    this.fileToRemoveOnClose.delete();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void ensureOpen() {
        if (this.closeRequested) {
            throw new IllegalStateException("zip file closed");
        } else if (this.jzfile == 0) {
            throw new IllegalStateException("The object is not initialized.");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void ensureOpenOrZipException() {
        if (this.closeRequested) {
            throw new ZipException("ZipFile closed");
        }
    }

    /* access modifiers changed from: private */
    public class ZipFileInputStream extends InputStream {
        protected long jzentry;
        private long pos = 0;
        protected long rem;
        protected long size;
        private volatile boolean zfisCloseRequested = false;

        ZipFileInputStream(long j) {
            this.rem = ZipFile.getEntryCSize(j);
            this.size = ZipFile.getEntrySize(j);
            this.jzentry = j;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0044, code lost:
            if (r18.rem != 0) goto L_0x0049;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0046, code lost:
            close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0049, code lost:
            return r1;
         */
        @Override // java.io.InputStream
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int read(byte[] r19, int r20, int r21) {
            /*
                r18 = this;
                r0 = r18
                r1 = r21
                java.util.zip.ZipFile r2 = java.util.zip.ZipFile.this
                java.util.zip.ZipFile.access$1300(r2)
                java.util.zip.ZipFile r2 = java.util.zip.ZipFile.this
                monitor-enter(r2)
                long r3 = r0.rem     // Catch:{ all -> 0x004a }
                long r14 = r0.pos     // Catch:{ all -> 0x004a }
                r16 = 0
                int r5 = (r3 > r16 ? 1 : (r3 == r16 ? 0 : -1))
                if (r5 != 0) goto L_0x0019
                r0 = -1
                monitor-exit(r2)     // Catch:{ all -> 0x004a }
                return r0
            L_0x0019:
                if (r1 > 0) goto L_0x001e
                r0 = 0
                monitor-exit(r2)     // Catch:{ all -> 0x004a }
                return r0
            L_0x001e:
                long r5 = (long) r1     // Catch:{ all -> 0x004a }
                int r5 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
                if (r5 <= 0) goto L_0x0024
                int r1 = (int) r3     // Catch:{ all -> 0x004a }
            L_0x0024:
                r13 = r1
                java.util.zip.ZipFile r1 = java.util.zip.ZipFile.this     // Catch:{ all -> 0x004a }
                long r5 = java.util.zip.ZipFile.access$400(r1)     // Catch:{ all -> 0x004a }
                long r7 = r0.jzentry     // Catch:{ all -> 0x004a }
                r9 = r14
                r11 = r19
                r12 = r20
                int r1 = java.util.zip.ZipFile.access$1400(r5, r7, r9, r11, r12, r13)     // Catch:{ all -> 0x004a }
                if (r1 <= 0) goto L_0x003f
                long r5 = (long) r1     // Catch:{ all -> 0x004a }
                long r14 = r14 + r5
                r0.pos = r14     // Catch:{ all -> 0x004a }
                long r3 = r3 - r5
                r0.rem = r3     // Catch:{ all -> 0x004a }
            L_0x003f:
                monitor-exit(r2)     // Catch:{ all -> 0x004a }
                long r2 = r0.rem
                int r2 = (r2 > r16 ? 1 : (r2 == r16 ? 0 : -1))
                if (r2 != 0) goto L_0x0049
                r18.close()
            L_0x0049:
                return r1
            L_0x004a:
                r0 = move-exception
                monitor-exit(r2)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.zip.ZipFile.ZipFileInputStream.read(byte[], int, int):int");
        }

        @Override // java.io.InputStream
        public int read() {
            byte[] bArr = new byte[1];
            if (read(bArr, 0, 1) == 1) {
                return bArr[0] & 255;
            }
            return -1;
        }

        @Override // java.io.InputStream
        public long skip(long j) {
            long j2 = this.rem;
            if (j > j2) {
                j = j2;
            }
            this.pos += j;
            this.rem -= j;
            if (this.rem == 0) {
                close();
            }
            return j;
        }

        @Override // java.io.InputStream
        public int available() {
            long j = this.rem;
            if (j > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            return (int) j;
        }

        public long size() {
            return this.size;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
        public void close() {
            if (!this.zfisCloseRequested) {
                this.zfisCloseRequested = ZipFile.usemmap;
                this.rem = 0;
                synchronized (ZipFile.this) {
                    if (!(this.jzentry == 0 || ZipFile.this.jzfile == 0)) {
                        ZipFile.freeEntry(ZipFile.this.jzfile, this.jzentry);
                        this.jzentry = 0;
                    }
                }
                synchronized (ZipFile.this.streams) {
                    ZipFile.this.streams.remove(this);
                }
            }
        }
    }
}
