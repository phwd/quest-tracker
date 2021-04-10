package java.util.zip;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DeflaterOutputStream extends FilterOutputStream {
    protected byte[] buf;
    private boolean closed;
    protected Deflater def;
    private final boolean syncFlush;
    boolean usesDefaultDeflater;

    public DeflaterOutputStream(OutputStream out, Deflater def2, int size, boolean syncFlush2) {
        super(out);
        this.closed = false;
        this.usesDefaultDeflater = false;
        if (out == null || def2 == null) {
            throw new NullPointerException();
        } else if (size > 0) {
            this.def = def2;
            this.buf = new byte[size];
            this.syncFlush = syncFlush2;
        } else {
            throw new IllegalArgumentException("buffer size <= 0");
        }
    }

    public DeflaterOutputStream(OutputStream out, Deflater def2, int size) {
        this(out, def2, size, false);
    }

    public DeflaterOutputStream(OutputStream out, Deflater def2, boolean syncFlush2) {
        this(out, def2, 512, syncFlush2);
    }

    public DeflaterOutputStream(OutputStream out, Deflater def2) {
        this(out, def2, 512, false);
    }

    public DeflaterOutputStream(OutputStream out, boolean syncFlush2) {
        this(out, new Deflater(), 512, syncFlush2);
        this.usesDefaultDeflater = true;
    }

    public DeflaterOutputStream(OutputStream out) {
        this(out, false);
        this.usesDefaultDeflater = true;
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(int b) throws IOException {
        write(new byte[]{(byte) (b & 255)}, 0, 1);
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        if (this.def.finished()) {
            throw new IOException("write beyond end of stream");
        } else if ((off | len | (off + len) | (b.length - (off + len))) < 0) {
            throw new IndexOutOfBoundsException();
        } else if (len != 0 && !this.def.finished()) {
            this.def.setInput(b, off, len);
            while (!this.def.needsInput()) {
                deflate();
            }
        }
    }

    public void finish() throws IOException {
        if (!this.def.finished()) {
            this.def.finish();
            while (!this.def.finished()) {
                deflate();
            }
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.io.FilterOutputStream, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.closed) {
            finish();
            if (this.usesDefaultDeflater) {
                this.def.end();
            }
            this.out.close();
            this.closed = true;
        }
    }

    /* access modifiers changed from: protected */
    public void deflate() throws IOException {
        while (true) {
            Deflater deflater = this.def;
            byte[] bArr = this.buf;
            int len = deflater.deflate(bArr, 0, bArr.length);
            if (len > 0) {
                this.out.write(this.buf, 0, len);
            } else {
                return;
            }
        }
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream, java.io.Flushable
    public void flush() throws IOException {
        int len;
        if (this.syncFlush && !this.def.finished()) {
            do {
                Deflater deflater = this.def;
                byte[] bArr = this.buf;
                len = deflater.deflate(bArr, 0, bArr.length, 2);
                if (len <= 0) {
                    break;
                }
                this.out.write(this.buf, 0, len);
            } while (len >= this.buf.length);
        }
        this.out.flush();
    }
}
