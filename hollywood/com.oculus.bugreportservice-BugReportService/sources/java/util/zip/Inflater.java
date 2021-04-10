package java.util.zip;

import dalvik.system.CloseGuard;

public class Inflater {
    private static final byte[] defaultBuf = new byte[0];
    private byte[] buf;
    private long bytesRead;
    private long bytesWritten;
    private boolean finished;
    private final CloseGuard guard;
    private int len;
    private boolean needDict;
    private int off;
    private final ZStreamRef zsRef;

    private static native void end(long j);

    private native int inflateBytes(long j, byte[] bArr, int i, int i2);

    private static native long init(boolean z);

    private static native void reset(long j);

    private static native void setDictionary(long j, byte[] bArr, int i, int i2);

    public Inflater(boolean z) {
        this.buf = defaultBuf;
        this.guard = CloseGuard.get();
        this.zsRef = new ZStreamRef(init(z));
        this.guard.open("end");
    }

    public Inflater() {
        this(false);
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
                this.needDict = false;
            }
        }
    }

    public void setDictionary(byte[] bArr) {
        setDictionary(bArr, 0, bArr.length);
    }

    public int getRemaining() {
        int i;
        synchronized (this.zsRef) {
            i = this.len;
        }
        return i;
    }

    public boolean needsInput() {
        boolean z;
        synchronized (this.zsRef) {
            z = this.len <= 0;
        }
        return z;
    }

    public boolean needsDictionary() {
        boolean z;
        synchronized (this.zsRef) {
            z = this.needDict;
        }
        return z;
    }

    public boolean finished() {
        boolean z;
        synchronized (this.zsRef) {
            z = this.finished;
        }
        return z;
    }

    public int inflate(byte[] bArr, int i, int i2) {
        int inflateBytes;
        if (bArr == null) {
            throw new NullPointerException();
        } else if (i < 0 || i2 < 0 || i > bArr.length - i2) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            synchronized (this.zsRef) {
                ensureOpen();
                int i3 = this.len;
                inflateBytes = inflateBytes(this.zsRef.address(), bArr, i, i2);
                this.bytesWritten += (long) inflateBytes;
                this.bytesRead += (long) (i3 - this.len);
            }
            return inflateBytes;
        }
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
            this.buf = defaultBuf;
            this.finished = false;
            this.needDict = false;
            this.len = 0;
            this.off = 0;
            this.bytesWritten = 0;
            this.bytesRead = 0;
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
            throw new NullPointerException("Inflater has been closed");
        }
    }

    /* access modifiers changed from: package-private */
    public boolean ended() {
        boolean z;
        synchronized (this.zsRef) {
            z = this.zsRef.address() == 0;
        }
        return z;
    }
}
