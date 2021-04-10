package java.util.zip;

import android.support.v4.media.session.PlaybackStateCompat;
import dalvik.system.CloseGuard;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
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
import java.util.Spliterators;
import java.util.WeakHashMap;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ZipFile implements ZipConstants, Closeable {
    private static final int DEFLATED = 8;
    private static final int JZENTRY_COMMENT = 2;
    private static final int JZENTRY_EXTRA = 1;
    private static final int JZENTRY_NAME = 0;
    public static final int OPEN_DELETE = 4;
    public static final int OPEN_READ = 1;
    private static final int STORED = 0;
    private static final boolean usemmap = true;
    private volatile boolean closeRequested;
    private final File fileToRemoveOnClose;
    private final CloseGuard guard;
    private Deque<Inflater> inflaterCache;
    private long jzfile;
    private final boolean locsig;
    private final String name;
    private final Map<InputStream, Inflater> streams;
    private final int total;
    private ZipCoder zc;

    private static native void close(long j);

    /* access modifiers changed from: private */
    public static native void freeEntry(long j, long j2);

    private static native byte[] getCommentBytes(long j);

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

    private static native int getFileDescriptor(long j);

    /* access modifiers changed from: private */
    public static native long getNextEntry(long j, int i);

    private static native int getTotal(long j);

    /* access modifiers changed from: private */
    public static native String getZipMessage(long j);

    private static native long open(String str, int i, long j, boolean z) throws IOException;

    /* access modifiers changed from: private */
    public static native int read(long j, long j2, long j3, byte[] bArr, int i, int i2);

    private static native boolean startsWithLOC(long j);

    public ZipFile(String name2) throws IOException {
        this(new File(name2), 1);
    }

    public ZipFile(File file, int mode) throws IOException {
        this(file, mode, StandardCharsets.UTF_8);
    }

    public ZipFile(File file) throws ZipException, IOException {
        this(file, 1);
    }

    public ZipFile(File file, int mode, Charset charset) throws IOException {
        this.closeRequested = false;
        this.guard = CloseGuard.get();
        this.streams = new WeakHashMap();
        this.inflaterCache = new ArrayDeque();
        if ((mode & 1) == 0 || (mode & -6) != 0) {
            throw new IllegalArgumentException("Illegal mode: 0x" + Integer.toHexString(mode));
        }
        String name2 = file.getPath();
        this.fileToRemoveOnClose = (mode & 4) != 0 ? file : null;
        if (charset != null) {
            this.zc = ZipCoder.get(charset);
            this.jzfile = open(name2, mode, file.lastModified(), usemmap);
            this.name = name2;
            this.total = getTotal(this.jzfile);
            this.locsig = startsWithLOC(this.jzfile);
            this.guard.open("close");
            return;
        }
        throw new NullPointerException("charset is null");
    }

    public ZipFile(String name2, Charset charset) throws IOException {
        this(new File(name2), 1, charset);
    }

    public ZipFile(File file, Charset charset) throws IOException {
        this(file, 1, charset);
    }

    public String getComment() {
        synchronized (this) {
            ensureOpen();
            byte[] bcomm = getCommentBytes(this.jzfile);
            if (bcomm == null) {
                return null;
            }
            return this.zc.toString(bcomm, bcomm.length);
        }
    }

    public ZipEntry getEntry(String name2) {
        if (name2 != null) {
            synchronized (this) {
                ensureOpen();
                long jzentry = getEntry(this.jzfile, this.zc.getBytes(name2), true);
                if (jzentry == 0) {
                    return null;
                }
                ZipEntry ze = getZipEntry(name2, jzentry);
                freeEntry(this.jzfile, jzentry);
                return ze;
            }
        }
        throw new NullPointerException("name");
    }

    public InputStream getInputStream(ZipEntry entry) throws IOException {
        long jzentry;
        if (entry != null) {
            synchronized (this) {
                ensureOpen();
                if (this.zc.isUTF8() || (entry.flag & 2048) == 0) {
                    jzentry = getEntry(this.jzfile, this.zc.getBytes(entry.name), true);
                } else {
                    jzentry = getEntry(this.jzfile, this.zc.getBytesUTF8(entry.name), true);
                }
                if (jzentry == 0) {
                    return null;
                }
                ZipFileInputStream in = new ZipFileInputStream(jzentry);
                int entryMethod = getEntryMethod(jzentry);
                if (entryMethod == 0) {
                    synchronized (this.streams) {
                        this.streams.put(in, null);
                    }
                    return in;
                } else if (entryMethod == 8) {
                    long size = getEntrySize(jzentry) + 2;
                    if (size > PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                        size = PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                    }
                    if (size <= 0) {
                        size = PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                    }
                    Inflater inf = getInflater();
                    InputStream is = new ZipFileInflaterInputStream(in, inf, (int) size);
                    synchronized (this.streams) {
                        this.streams.put(is, inf);
                    }
                    return is;
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

        ZipFileInflaterInputStream(ZipFileInputStream zfin2, Inflater inf, int size) {
            super(zfin2, inf, size);
            this.zfin = zfin2;
        }

        @Override // java.io.FilterInputStream, java.util.zip.InflaterInputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
        public void close() throws IOException {
            Inflater inf;
            if (!this.closeRequested) {
                this.closeRequested = true;
                super.close();
                synchronized (ZipFile.this.streams) {
                    inf = (Inflater) ZipFile.this.streams.remove(this);
                }
                if (inf != null) {
                    ZipFile.this.releaseInflater(inf);
                }
            }
        }

        /* access modifiers changed from: protected */
        @Override // java.util.zip.InflaterInputStream
        public void fill() throws IOException {
            if (!this.eof) {
                this.len = this.in.read(this.buf, 0, this.buf.length);
                if (this.len == -1) {
                    this.buf[0] = 0;
                    this.len = 1;
                    this.eof = true;
                }
                this.inf.setInput(this.buf, 0, this.len);
                return;
            }
            throw new EOFException("Unexpected end of ZLIB input stream");
        }

        @Override // java.io.FilterInputStream, java.util.zip.InflaterInputStream, java.io.InputStream
        public int available() throws IOException {
            if (this.closeRequested) {
                return 0;
            }
            long avail = this.zfin.size() - this.inf.getBytesWritten();
            if (avail > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            return (int) avail;
        }

        /* access modifiers changed from: protected */
        public void finalize() throws Throwable {
            close();
        }
    }

    private Inflater getInflater() {
        Inflater inf;
        synchronized (this.inflaterCache) {
            do {
                inf = this.inflaterCache.poll();
                if (inf == null) {
                    return new Inflater(true);
                }
            } while (inf.ended());
            return inf;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void releaseInflater(Inflater inf) {
        if (!inf.ended()) {
            inf.reset();
            synchronized (this.inflaterCache) {
                this.inflaterCache.add(inf);
            }
        }
    }

    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: private */
    public class ZipEntryIterator implements Enumeration<ZipEntry>, Iterator<ZipEntry> {
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
                z = this.i < ZipFile.this.total;
            }
            return z;
        }

        @Override // java.util.Enumeration
        public ZipEntry nextElement() {
            return next();
        }

        @Override // java.util.Iterator
        public ZipEntry next() {
            ZipEntry ze;
            String message;
            synchronized (ZipFile.this) {
                ZipFile.this.ensureOpen();
                if (this.i < ZipFile.this.total) {
                    long j = ZipFile.this.jzfile;
                    int i2 = this.i;
                    this.i = i2 + 1;
                    long jzentry = ZipFile.getNextEntry(j, i2);
                    if (jzentry == 0) {
                        if (ZipFile.this.closeRequested) {
                            message = "ZipFile concurrently closed";
                        } else {
                            message = ZipFile.getZipMessage(ZipFile.this.jzfile);
                        }
                        throw new ZipError("jzentry == 0,\n jzfile = " + ZipFile.this.jzfile + ",\n total = " + ZipFile.this.total + ",\n name = " + ZipFile.this.name + ",\n i = " + this.i + ",\n message = " + message);
                    }
                    ze = ZipFile.this.getZipEntry(null, jzentry);
                    ZipFile.freeEntry(ZipFile.this.jzfile, jzentry);
                } else {
                    throw new NoSuchElementException();
                }
            }
            return ze;
        }
    }

    public Enumeration<? extends ZipEntry> entries() {
        return new ZipEntryIterator();
    }

    public Stream<? extends ZipEntry> stream() {
        return StreamSupport.stream(Spliterators.spliterator(new ZipEntryIterator(), (long) size(), 1297), false);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private ZipEntry getZipEntry(String name2, long jzentry) {
        ZipEntry e = new ZipEntry();
        e.flag = getEntryFlag(jzentry);
        if (name2 != null) {
            e.name = name2;
        } else {
            byte[] bname = getEntryBytes(jzentry, 0);
            if (this.zc.isUTF8() || (e.flag & 2048) == 0) {
                e.name = this.zc.toString(bname, bname.length);
            } else {
                e.name = this.zc.toStringUTF8(bname, bname.length);
            }
        }
        e.xdostime = getEntryTime(jzentry);
        e.crc = getEntryCrc(jzentry);
        e.size = getEntrySize(jzentry);
        e.csize = getEntryCSize(jzentry);
        e.method = getEntryMethod(jzentry);
        e.setExtra0(getEntryBytes(jzentry, 1), false);
        byte[] bcomm = getEntryBytes(jzentry, 2);
        if (bcomm == null) {
            e.comment = null;
        } else if (this.zc.isUTF8() || (e.flag & 2048) == 0) {
            e.comment = this.zc.toString(bcomm, bcomm.length);
        } else {
            e.comment = this.zc.toStringUTF8(bcomm, bcomm.length);
        }
        return e;
    }

    public int size() {
        ensureOpen();
        return this.total;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.closeRequested) {
            CloseGuard closeGuard = this.guard;
            if (closeGuard != null) {
                closeGuard.close();
            }
            this.closeRequested = true;
            synchronized (this) {
                if (this.streams != null) {
                    synchronized (this.streams) {
                        if (!this.streams.isEmpty()) {
                            Map<InputStream, Inflater> copy = new HashMap<>(this.streams);
                            this.streams.clear();
                            for (Map.Entry<InputStream, Inflater> e : copy.entrySet()) {
                                e.getKey().close();
                                Inflater inf = e.getValue();
                                if (inf != null) {
                                    inf.end();
                                }
                            }
                        }
                    }
                }
                if (this.inflaterCache != null) {
                    synchronized (this.inflaterCache) {
                        while (true) {
                            Inflater inf2 = this.inflaterCache.poll();
                            if (inf2 != null) {
                                inf2.end();
                            }
                        }
                    }
                    break;
                }
                if (this.jzfile != 0) {
                    long zf = this.jzfile;
                    this.jzfile = 0;
                    close(zf);
                }
                if (this.fileToRemoveOnClose != null) {
                    this.fileToRemoveOnClose.delete();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws IOException {
        CloseGuard closeGuard = this.guard;
        if (closeGuard != null) {
            closeGuard.warnIfOpen();
        }
        close();
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
    private void ensureOpenOrZipException() throws IOException {
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

        ZipFileInputStream(long jzentry2) {
            this.rem = ZipFile.getEntryCSize(jzentry2);
            this.size = ZipFile.getEntrySize(jzentry2);
            this.jzentry = jzentry2;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0049, code lost:
            if (r19.rem != 0) goto L_0x004e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x004b, code lost:
            close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x004e, code lost:
            return r0;
         */
        @Override // java.io.InputStream
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int read(byte[] r20, int r21, int r22) throws java.io.IOException {
            /*
                r19 = this;
                r1 = r19
                r2 = r22
                java.util.zip.ZipFile r0 = java.util.zip.ZipFile.this
                java.util.zip.ZipFile.access$1300(r0)
                java.util.zip.ZipFile r3 = java.util.zip.ZipFile.this
                monitor-enter(r3)
                long r4 = r1.rem     // Catch:{ all -> 0x004f }
                long r6 = r1.pos     // Catch:{ all -> 0x004f }
                r17 = 0
                int r0 = (r4 > r17 ? 1 : (r4 == r17 ? 0 : -1))
                if (r0 != 0) goto L_0x0019
                r0 = -1
                monitor-exit(r3)     // Catch:{ all -> 0x004f }
                return r0
            L_0x0019:
                if (r2 > 0) goto L_0x001e
                r0 = 0
                monitor-exit(r3)     // Catch:{ all -> 0x004f }
                return r0
            L_0x001e:
                long r8 = (long) r2
                int r0 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
                if (r0 <= 0) goto L_0x0025
                int r0 = (int) r4
                r2 = r0
            L_0x0025:
                java.util.zip.ZipFile r0 = java.util.zip.ZipFile.this     // Catch:{ all -> 0x0052 }
                long r8 = java.util.zip.ZipFile.access$400(r0)     // Catch:{ all -> 0x0052 }
                long r10 = r1.jzentry     // Catch:{ all -> 0x0052 }
                r12 = r6
                r14 = r20
                r15 = r21
                r16 = r2
                int r0 = java.util.zip.ZipFile.access$1400(r8, r10, r12, r14, r15, r16)     // Catch:{ all -> 0x0052 }
                r2 = r0
                if (r2 <= 0) goto L_0x0044
                long r8 = (long) r2     // Catch:{ all -> 0x0052 }
                long r8 = r8 + r6
                r1.pos = r8     // Catch:{ all -> 0x0052 }
                long r8 = (long) r2     // Catch:{ all -> 0x0052 }
                long r8 = r4 - r8
                r1.rem = r8     // Catch:{ all -> 0x0052 }
            L_0x0044:
                monitor-exit(r3)     // Catch:{ all -> 0x0052 }
                long r3 = r1.rem
                int r0 = (r3 > r17 ? 1 : (r3 == r17 ? 0 : -1))
                if (r0 != 0) goto L_0x004e
                r19.close()
            L_0x004e:
                return r2
            L_0x004f:
                r0 = move-exception
            L_0x0050:
                monitor-exit(r3)
                throw r0
            L_0x0052:
                r0 = move-exception
                goto L_0x0050
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.zip.ZipFile.ZipFileInputStream.read(byte[], int, int):int");
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            byte[] b = new byte[1];
            if (read(b, 0, 1) == 1) {
                return b[0] & 255;
            }
            return -1;
        }

        @Override // java.io.InputStream
        public long skip(long n) {
            if (n > this.rem) {
                n = this.rem;
            }
            this.pos += n;
            this.rem -= n;
            if (this.rem == 0) {
                close();
            }
            return n;
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
                this.zfisCloseRequested = true;
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

        /* access modifiers changed from: protected */
        public void finalize() {
            close();
        }
    }

    public boolean startsWithLocHeader() {
        return this.locsig;
    }

    public int getFileDescriptor() {
        return getFileDescriptor(this.jzfile);
    }
}
