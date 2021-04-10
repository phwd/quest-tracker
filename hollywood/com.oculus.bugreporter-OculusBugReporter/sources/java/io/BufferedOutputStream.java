package java.io;

public class BufferedOutputStream extends FilterOutputStream {
    protected byte[] buf;
    protected int count;

    public BufferedOutputStream(OutputStream out) {
        this(out, 8192);
    }

    public BufferedOutputStream(OutputStream out, int size) {
        super(out);
        if (size > 0) {
            this.buf = new byte[size];
            return;
        }
        throw new IllegalArgumentException("Buffer size <= 0");
    }

    private void flushBuffer() throws IOException {
        if (this.count > 0) {
            this.out.write(this.buf, 0, this.count);
            this.count = 0;
        }
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public synchronized void write(int b) throws IOException {
        if (this.count >= this.buf.length) {
            flushBuffer();
        }
        byte[] bArr = this.buf;
        int i = this.count;
        this.count = i + 1;
        bArr[i] = (byte) b;
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public synchronized void write(byte[] b, int off, int len) throws IOException {
        if (len >= this.buf.length) {
            flushBuffer();
            this.out.write(b, off, len);
            return;
        }
        if (len > this.buf.length - this.count) {
            flushBuffer();
        }
        System.arraycopy(b, off, this.buf, this.count, len);
        this.count += len;
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream, java.io.Flushable
    public synchronized void flush() throws IOException {
        flushBuffer();
        this.out.flush();
    }
}
