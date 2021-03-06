package java.util.zip;

import dalvik.system.CloseGuard;

public class Deflater {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int BEST_COMPRESSION = 9;
    public static final int BEST_SPEED = 1;
    public static final int DEFAULT_COMPRESSION = -1;
    public static final int DEFAULT_STRATEGY = 0;
    public static final int DEFLATED = 8;
    public static final int FILTERED = 1;
    public static final int FULL_FLUSH = 3;
    public static final int HUFFMAN_ONLY = 2;
    public static final int NO_COMPRESSION = 0;
    public static final int NO_FLUSH = 0;
    public static final int SYNC_FLUSH = 2;
    private byte[] buf;
    private long bytesRead;
    private long bytesWritten;
    private boolean finish;
    private boolean finished;
    private final CloseGuard guard;
    private int len;
    private int level;
    private int off;
    private boolean setParams;
    private int strategy;
    private final ZStreamRef zsRef;

    private native int deflateBytes(long j, byte[] bArr, int i, int i2, int i3);

    private static native void end(long j);

    private static native int getAdler(long j);

    private static native long init(int i, int i2, boolean z);

    private static native void reset(long j);

    private static native void setDictionary(long j, byte[] bArr, int i, int i2);

    public Deflater(int level2, boolean nowrap) {
        this.buf = new byte[0];
        this.guard = CloseGuard.get();
        this.level = level2;
        this.strategy = 0;
        this.zsRef = new ZStreamRef(init(level2, 0, nowrap));
        this.guard.open("end");
    }

    public Deflater(int level2) {
        this(level2, false);
    }

    public Deflater() {
        this(-1, false);
    }

    public void setInput(byte[] b, int off2, int len2) {
        if (b == null) {
            throw new NullPointerException();
        } else if (off2 < 0 || len2 < 0 || off2 > b.length - len2) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            synchronized (this.zsRef) {
                this.buf = b;
                this.off = off2;
                this.len = len2;
            }
        }
    }

    public void setInput(byte[] b) {
        setInput(b, 0, b.length);
    }

    public void setDictionary(byte[] b, int off2, int len2) {
        if (b == null) {
            throw new NullPointerException();
        } else if (off2 < 0 || len2 < 0 || off2 > b.length - len2) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            synchronized (this.zsRef) {
                ensureOpen();
                setDictionary(this.zsRef.address(), b, off2, len2);
            }
        }
    }

    public void setDictionary(byte[] b) {
        setDictionary(b, 0, b.length);
    }

    public void setStrategy(int strategy2) {
        if (strategy2 == 0 || strategy2 == 1 || strategy2 == 2) {
            synchronized (this.zsRef) {
                if (this.strategy != strategy2) {
                    this.strategy = strategy2;
                    this.setParams = true;
                }
            }
            return;
        }
        throw new IllegalArgumentException();
    }

    public void setLevel(int level2) {
        if ((level2 < 0 || level2 > 9) && level2 != -1) {
            throw new IllegalArgumentException("invalid compression level");
        }
        synchronized (this.zsRef) {
            if (this.level != level2) {
                this.level = level2;
                this.setParams = true;
            }
        }
    }

    public boolean needsInput() {
        boolean z;
        synchronized (this.zsRef) {
            z = this.len <= 0;
        }
        return z;
    }

    public void finish() {
        synchronized (this.zsRef) {
            this.finish = true;
        }
    }

    public boolean finished() {
        boolean z;
        synchronized (this.zsRef) {
            z = this.finished;
        }
        return z;
    }

    public int deflate(byte[] b, int off2, int len2) {
        return deflate(b, off2, len2, 0);
    }

    public int deflate(byte[] b) {
        return deflate(b, 0, b.length, 0);
    }

    public int deflate(byte[] b, int off2, int len2, int flush) {
        int n;
        if (b == null) {
            throw new NullPointerException();
        } else if (off2 < 0 || len2 < 0 || off2 > b.length - len2) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            synchronized (this.zsRef) {
                ensureOpen();
                if (!(flush == 0 || flush == 2)) {
                    if (flush != 3) {
                        throw new IllegalArgumentException();
                    }
                }
                int thisLen = this.len;
                n = deflateBytes(this.zsRef.address(), b, off2, len2, flush);
                this.bytesWritten += (long) n;
                this.bytesRead += (long) (thisLen - this.len);
            }
            return n;
        }
    }

    public int getAdler() {
        int adler;
        synchronized (this.zsRef) {
            ensureOpen();
            adler = getAdler(this.zsRef.address());
        }
        return adler;
    }

    public int getTotalIn() {
        return (int) getBytesRead();
    }

    public long getBytesRead() {
        long j;
        synchronized (this.zsRef) {
            ensureOpen();
            j = this.bytesRead;
        }
        return j;
    }

    public int getTotalOut() {
        return (int) getBytesWritten();
    }

    public long getBytesWritten() {
        long j;
        synchronized (this.zsRef) {
            ensureOpen();
            j = this.bytesWritten;
        }
        return j;
    }

    public void reset() {
        synchronized (this.zsRef) {
            ensureOpen();
            reset(this.zsRef.address());
            this.finish = false;
            this.finished = false;
            this.len = 0;
            this.off = 0;
            this.bytesWritten = 0;
            this.bytesRead = 0;
        }
    }

    public void end() {
        synchronized (this.zsRef) {
            this.guard.close();
            long addr = this.zsRef.address();
            this.zsRef.clear();
            if (addr != 0) {
                end(addr);
                this.buf = null;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        CloseGuard closeGuard = this.guard;
        if (closeGuard != null) {
            closeGuard.warnIfOpen();
        }
        end();
    }

    private void ensureOpen() {
        if (this.zsRef.address() == 0) {
            throw new NullPointerException("Deflater has been closed");
        }
    }
}
