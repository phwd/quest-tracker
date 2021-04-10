package com.facebook.proxygen;

import X.AnonymousClass08;
import java.io.IOException;
import java.io.InputStream;

public class ReadBufferInputStream extends InputStream {
    public static final long WAIT_FOR_BODY_TIMEOUT = 1000;
    public final ReadBuffer mBuffer;
    public boolean mClosed;
    public HttpNetworkException mNetworkException;

    @Override // java.io.InputStream
    public synchronized int available() {
        return this.mBuffer.size();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public synchronized void close() {
        if (!this.mClosed) {
            this.mBuffer.close();
            this.mClosed = true;
        }
    }

    public boolean markSupported() {
        return false;
    }

    public synchronized void onBody() {
        notifyAll();
    }

    public synchronized void onEOM() {
        notifyAll();
    }

    public synchronized void setError(HttpNetworkException httpNetworkException) {
        this.mNetworkException = httpNetworkException;
        notifyAll();
    }

    private void checkNotClosed() {
        if (this.mClosed) {
            throw new IOException("Buffer is Closed");
        }
    }

    public void mark(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.InputStream
    public void reset() {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        throw new UnsupportedOperationException();
    }

    public ReadBufferInputStream(ReadBuffer readBuffer) {
        this.mBuffer = readBuffer;
    }

    @Override // java.io.InputStream
    public synchronized int read() {
        byte b;
        byte[] bArr = new byte[1];
        int read = read(bArr, 0, 1);
        b = -1;
        if (read != -1) {
            if (read == 1) {
                b = bArr[0];
            } else {
                throw new IllegalStateException(AnonymousClass08.A00("n=", read));
            }
        }
        return b;
    }

    @Override // java.io.InputStream
    public synchronized int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public synchronized int read(byte[] bArr, int i, int i2) {
        int read;
        checkNotClosed();
        HttpNetworkException httpNetworkException = this.mNetworkException;
        if (httpNetworkException == null) {
            read = this.mBuffer.read(bArr, i, i2);
            while (read == 0) {
                try {
                    wait(1000);
                } catch (InterruptedException unused) {
                }
                HttpNetworkException httpNetworkException2 = this.mNetworkException;
                if (httpNetworkException2 == null) {
                    read = this.mBuffer.read(bArr, i, i2);
                } else {
                    throw httpNetworkException2;
                }
            }
        } else {
            throw httpNetworkException;
        }
        return read;
    }
}
