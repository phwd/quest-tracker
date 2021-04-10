package java.util.zip;

import dalvik.system.CloseGuard;

public class Deflater {
    private byte[] buf;
    private long bytesRead;
    private long bytesWritten;
    private boolean finish;
    private final CloseGuard guard;
    private int len;
    private int level;
    private int off;
    private int strategy;
    private final ZStreamRef zsRef;

    private native int deflateBytes(long j, byte[] bArr, int i, int i2, int i3);

    private static native void end(long j);

    private static native long init(int i, int i2, boolean z);

    private static native void setDictionary(long j, byte[] bArr, int i, int i2);

    public Deflater(int i, boolean z) {
        this.buf = new byte[0];
        this.guard = CloseGuard.get();
        this.level = i;
        this.strategy = 0;
        this.zsRef = new ZStreamRef(init(i, 0, z));
        this.guard.open("end");
    }

    public Deflater() {
        this(-1, false);
    }

    public void setInput(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new NullPointerException();
        } else if (i < 0 || i2 < 0 || i > bArr.length - i2) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            synchronized (this.zsRef) {
                this.buf = bArr;
                this.off = i;
                this.len = i2;
            }
        }
    }

    public void setDictionary(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new NullPointerException();
        } else if (i < 0 || i2 < 0 || i > bArr.length - i2) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            synchronized (this.zsRef) {
                ensureOpen();
                setDictionary(this.zsRef.address(), bArr, i, i2);
            }
        }
    }

    public void setDictionary(byte[] bArr) {
        setDictionary(bArr, 0, bArr.length);
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

    public int deflate(byte[] bArr, int i, int i2) {
        return deflate(bArr, i, i2, 0);
    }

    public int deflate(byte[] bArr, int i, int i2, int i3) {
        int deflateBytes;
        if (bArr == null) {
            throw new NullPointerException();
        } else if (i < 0 || i2 < 0 || i > bArr.length - i2) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            synchronized (this.zsRef) {
                ensureOpen();
                if (!(i3 == 0 || i3 == 2)) {
                    if (i3 != 3) {
                        throw new IllegalArgumentException();
                    }
                }
                int i4 = this.len;
                deflateBytes = deflateBytes(this.zsRef.address(), bArr, i, i2, i3);
                this.bytesWritten += (long) deflateBytes;
                this.bytesRead += (long) (i4 - this.len);
            }
            return deflateBytes;
        }
    }

    public void end() {
        synchronized (this.zsRef) {
            this.guard.close();
            long address = this.zsRef.address();
            this.zsRef.clear();
            if (address != 0) {
                end(address);
                this.buf = null;
            }
        }
    }

    private void ensureOpen() {
        if (this.zsRef.address() == 0) {
            throw new NullPointerException("Deflater has been closed");
        }
    }
}
