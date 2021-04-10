package javax.crypto;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CipherOutputStream extends FilterOutputStream {
    private Cipher cipher;
    private boolean closed;
    private byte[] ibuffer;
    private byte[] obuffer;
    private OutputStream output;

    public CipherOutputStream(OutputStream os, Cipher c) {
        super(os);
        this.ibuffer = new byte[1];
        this.closed = false;
        this.output = os;
        this.cipher = c;
    }

    protected CipherOutputStream(OutputStream os) {
        super(os);
        this.ibuffer = new byte[1];
        this.closed = false;
        this.output = os;
        this.cipher = new NullCipher();
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(int b) throws IOException {
        byte[] bArr = this.ibuffer;
        bArr[0] = (byte) b;
        this.obuffer = this.cipher.update(bArr, 0, 1);
        byte[] bArr2 = this.obuffer;
        if (bArr2 != null) {
            this.output.write(bArr2);
            this.obuffer = null;
        }
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        this.obuffer = this.cipher.update(b, off, len);
        byte[] bArr = this.obuffer;
        if (bArr != null) {
            this.output.write(bArr);
            this.obuffer = null;
        }
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream, java.io.Flushable
    public void flush() throws IOException {
        byte[] bArr = this.obuffer;
        if (bArr != null) {
            this.output.write(bArr);
            this.obuffer = null;
        }
        this.output.flush();
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.io.FilterOutputStream, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            try {
                this.obuffer = this.cipher.doFinal();
                try {
                    flush();
                } catch (IOException e) {
                }
                this.out.close();
            } catch (BadPaddingException | IllegalBlockSizeException e2) {
                this.obuffer = null;
                throw new IOException(e2);
            }
        }
    }
}
