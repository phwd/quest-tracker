package javax.crypto;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CipherInputStream extends FilterInputStream {
    private Cipher cipher;
    private boolean closed;
    private boolean done;
    private byte[] ibuffer;
    private InputStream input;
    private byte[] obuffer;
    private int ofinish;
    private int ostart;

    private int getMoreData() throws IOException {
        if (this.done) {
            return -1;
        }
        this.ofinish = 0;
        this.ostart = 0;
        int expectedOutputSize = this.cipher.getOutputSize(this.ibuffer.length);
        byte[] bArr = this.obuffer;
        if (bArr == null || expectedOutputSize > bArr.length) {
            this.obuffer = new byte[expectedOutputSize];
        }
        int readin = this.input.read(this.ibuffer);
        if (readin == -1) {
            this.done = true;
            try {
                this.ofinish = this.cipher.doFinal(this.obuffer, 0);
            } catch (BadPaddingException | IllegalBlockSizeException e) {
                this.obuffer = null;
                throw new IOException(e);
            } catch (ShortBufferException e2) {
                this.obuffer = null;
                throw new IllegalStateException("ShortBufferException is not expected", e2);
            }
        } else {
            try {
                this.ofinish = this.cipher.update(this.ibuffer, 0, readin, this.obuffer, 0);
            } catch (IllegalStateException e3) {
                this.obuffer = null;
                throw e3;
            } catch (ShortBufferException e4) {
                this.obuffer = null;
                throw new IllegalStateException("ShortBufferException is not expected", e4);
            }
        }
        return this.ofinish;
    }

    public CipherInputStream(InputStream is, Cipher c) {
        super(is);
        this.ibuffer = new byte[512];
        this.done = false;
        this.ostart = 0;
        this.ofinish = 0;
        this.closed = false;
        this.input = is;
        this.cipher = c;
    }

    protected CipherInputStream(InputStream is) {
        super(is);
        this.ibuffer = new byte[512];
        this.done = false;
        this.ostart = 0;
        this.ofinish = 0;
        this.closed = false;
        this.input = is;
        this.cipher = new NullCipher();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (this.ostart >= this.ofinish) {
            int i = 0;
            while (i == 0) {
                i = getMoreData();
            }
            if (i == -1) {
                return -1;
            }
        }
        byte[] bArr = this.obuffer;
        int i2 = this.ostart;
        this.ostart = i2 + 1;
        return bArr[i2] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        if (this.ostart >= this.ofinish) {
            int i = 0;
            while (i == 0) {
                i = getMoreData();
            }
            if (i == -1) {
                return -1;
            }
        }
        if (len <= 0) {
            return 0;
        }
        int available = this.ofinish - this.ostart;
        if (len < available) {
            available = len;
        }
        if (b != null) {
            System.arraycopy(this.obuffer, this.ostart, b, off, available);
        }
        this.ostart += available;
        return available;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long n) throws IOException {
        int available = this.ofinish - this.ostart;
        if (n > ((long) available)) {
            n = (long) available;
        }
        if (n < 0) {
            return 0;
        }
        this.ostart = (int) (((long) this.ostart) + n);
        return n;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        return this.ofinish - this.ostart;
    }

    @Override // java.io.FilterInputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            this.input.close();
            if (!this.done) {
                try {
                    this.cipher.doFinal();
                } catch (BadPaddingException | IllegalBlockSizeException ex) {
                    if (ex instanceof AEADBadTagException) {
                        throw new IOException(ex);
                    }
                }
            }
            this.ostart = 0;
            this.ofinish = 0;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }
}
