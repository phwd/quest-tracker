package java.io;

public class BufferedOutputStream extends FilterOutputStream {
    protected byte[] buf;
    protected int count;

    public BufferedOutputStream(OutputStream outputStream) {
        this(outputStream, 8192);
    }

    public BufferedOutputStream(OutputStream outputStream, int i) {
        super(outputStream);
        if (i > 0) {
            this.buf = new byte[i];
            return;
        }
        throw new IllegalArgumentException("Buffer size <= 0");
    }

    private void flushBuffer() {
        int i = this.count;
        if (i > 0) {
            this.out.write(this.buf, 0, i);
            this.count = 0;
        }
    }

    @Override // java.io.OutputStream
    public synchronized void write(int i) {
        if (this.count >= this.buf.length) {
            flushBuffer();
        }
        byte[] bArr = this.buf;
        int i2 = this.count;
        this.count = i2 + 1;
        bArr[i2] = (byte) i;
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public synchronized void write(byte[] bArr, int i, int i2) {
        if (i2 >= this.buf.length) {
            flushBuffer();
            this.out.write(bArr, i, i2);
            return;
        }
        if (i2 > this.buf.length - this.count) {
            flushBuffer();
        }
        System.arraycopy(bArr, i, this.buf, this.count, i2);
        this.count += i2;
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public synchronized void flush() {
        flushBuffer();
        this.out.flush();
    }
}
