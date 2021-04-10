package com.facebook.common.iolite;

import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.io.OutputStream;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class TeedOutputStream extends OutputStream {
    private boolean mExhausted;
    private final OutputStream[] mOutputStreams;

    public TeedOutputStream(OutputStream... outputStreamArr) {
        this.mOutputStreams = outputStreamArr;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            super.close();
        } finally {
            closeAllOutputStreams();
        }
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        writeToAllOutputStreams(i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        writeToAllOutputStreams(bArr, i, i2);
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        int i = 0;
        while (true) {
            OutputStream[] outputStreamArr = this.mOutputStreams;
            if (i < outputStreamArr.length) {
                try {
                    outputStreamArr[i].flush();
                    i++;
                } catch (IOException e) {
                    closeAllOutputStreams();
                    throw e;
                }
            } else {
                return;
            }
        }
    }

    private void ensureUsable() throws IOException {
        if (this.mExhausted) {
            throw new IOException("This OutputStream can no longer be used if an exception was thrown or if is already closed");
        }
    }

    private void setClosed() {
        this.mExhausted = true;
    }

    private void closeAllOutputStreams() throws IOException {
        setClosed();
        IOException iOException = null;
        int i = 0;
        while (true) {
            OutputStream[] outputStreamArr = this.mOutputStreams;
            if (i < outputStreamArr.length) {
                try {
                    outputStreamArr[i].close();
                } catch (IOException e) {
                    if (iOException == null) {
                        iOException = e;
                    }
                }
                i++;
            } else {
                return;
            }
        }
    }

    private void writeToAllOutputStreams(int i) throws IOException {
        ensureUsable();
        int i2 = 0;
        while (true) {
            OutputStream[] outputStreamArr = this.mOutputStreams;
            if (i2 < outputStreamArr.length) {
                try {
                    outputStreamArr[i2].write(i);
                    i2++;
                } catch (IOException e) {
                    closeAllOutputStreams();
                    throw e;
                }
            } else {
                return;
            }
        }
    }

    private void writeToAllOutputStreams(byte[] bArr, int i, int i2) throws IOException {
        ensureUsable();
        int i3 = 0;
        while (true) {
            OutputStream[] outputStreamArr = this.mOutputStreams;
            if (i3 < outputStreamArr.length) {
                try {
                    outputStreamArr[i3].write(bArr, i, i2);
                    i3++;
                } catch (IOException e) {
                    closeAllOutputStreams();
                    throw e;
                }
            } else {
                return;
            }
        }
    }
}
