package com.facebook.common.iolite;

import com.facebook.infer.annotation.Nullsafe;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class TeedInputStream extends FilterInputStream {
    private static final int BUFFER_SIZE = 4096;
    @GuardedBy("this")
    private boolean mExhausted;
    private final OutputStream[] mOutputStreams;

    public void mark(int i) {
    }

    public boolean markSupported() {
        return false;
    }

    public TeedInputStream(InputStream inputStream, OutputStream... outputStreamArr) {
        super(inputStream);
        this.mOutputStreams = outputStreamArr;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        ensureUsable();
        int read = this.in.read();
        if (read != -1) {
            writeToAllOutputStreams(read);
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        ensureUsable();
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        ensureUsable();
        int read = this.in.read(bArr, i, i2);
        if (read != -1) {
            writeToAllOutputStreams(bArr, i, read);
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        ensureUsable();
        byte[] bArr = new byte[4096];
        long j2 = 0;
        while (j2 < j) {
            int read = read(bArr, 0, (int) Math.min((long) bArr.length, j - j2));
            if (read == -1) {
                break;
            }
            j2 += (long) read;
        }
        return j2;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
        throw new UnsupportedOperationException("Mark not supported");
    }

    @Override // java.io.FilterInputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public void close() throws IOException {
        try {
            super.close();
        } finally {
            closeAllOutputStreamsQuietly();
        }
    }

    private synchronized void ensureUsable() throws IOException {
        if (this.mExhausted) {
            throw new IOException("This InputStream can no longer be used if an exception was thrown or if is already closed");
        }
    }

    private synchronized void setExhausted() {
        this.mExhausted = true;
    }

    private void closeAllOutputStreamsQuietly() {
        for (OutputStream outputStream : this.mOutputStreams) {
            try {
                Closeables.close(outputStream, true);
            } catch (IOException unused) {
            }
        }
        setExhausted();
    }

    private void writeToAllOutputStreams(int i) throws IOException {
        for (OutputStream outputStream : this.mOutputStreams) {
            try {
                outputStream.write(i);
            } catch (IOException e) {
                closeAllOutputStreamsQuietly();
                throw e;
            }
        }
    }

    private void writeToAllOutputStreams(byte[] bArr, int i, int i2) throws IOException {
        for (OutputStream outputStream : this.mOutputStreams) {
            try {
                outputStream.write(bArr, i, i2);
            } catch (IOException e) {
                closeAllOutputStreamsQuietly();
                throw e;
            }
        }
    }
}
