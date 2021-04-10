package com.facebook.stetho.server;

import com.facebook.stetho.common.LogUtil;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class CompositeInputStream extends InputStream {
    private int mCurrentIndex;
    private final InputStream[] mStreams;

    public boolean markSupported() {
        return false;
    }

    public CompositeInputStream(InputStream[] inputStreamArr) {
        if (inputStreamArr == null || inputStreamArr.length < 2) {
            throw new IllegalArgumentException("Streams must be non-null and have more than 1 entry");
        }
        this.mStreams = inputStreamArr;
        this.mCurrentIndex = 0;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.mStreams[this.mCurrentIndex].available();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public void close() throws IOException {
        closeAll(this.mCurrentIndex);
    }

    private void closeAll(int i) throws IOException {
        IOException iOException = null;
        int i2 = 0;
        while (true) {
            InputStream[] inputStreamArr = this.mStreams;
            if (i2 < inputStreamArr.length) {
                try {
                    inputStreamArr[i2].close();
                } catch (IOException e) {
                    e = e;
                    if (!(i2 == i || iOException == null)) {
                        e = iOException;
                    }
                    if (!(iOException == null || iOException == e)) {
                        LogUtil.w(iOException, "Suppressing exception");
                    }
                    iOException = e;
                }
                i2++;
            } else {
                return;
            }
        }
    }

    public void mark(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.InputStream
    public void reset() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read;
        do {
            read = this.mStreams[this.mCurrentIndex].read(bArr, i, i2);
            if (read != -1) {
                break;
            }
        } while (tryMoveToNextStream());
        return read;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int read;
        do {
            read = this.mStreams[this.mCurrentIndex].read();
            if (read != -1) {
                break;
            }
        } while (tryMoveToNextStream());
        return read;
    }

    private boolean tryMoveToNextStream() {
        int i = this.mCurrentIndex;
        if (i + 1 >= this.mStreams.length) {
            return false;
        }
        this.mCurrentIndex = i + 1;
        return true;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        int read = read(new byte[((int) j)]);
        if (read >= 0) {
            return (long) read;
        }
        return -1;
    }
}
