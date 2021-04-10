package com.oculus.panellib.videoplayer;

import android.media.MediaDataSource;
import java.io.IOException;
import java.io.InputStream;

public class VideoPlayerDataSource extends MediaDataSource {
    public byte[] mBuffer;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    @Override // android.media.MediaDataSource
    public long getSize() throws IOException {
        return (long) this.mBuffer.length;
    }

    @Override // android.media.MediaDataSource
    public int readAt(long j, byte[] bArr, int i, int i2) throws IOException {
        byte[] bArr2 = this.mBuffer;
        int length = bArr2.length;
        long j2 = (long) length;
        if (j >= j2) {
            return -1;
        }
        if (((long) i2) + j > j2) {
            i2 = length - ((int) j);
        }
        System.arraycopy(bArr2, (int) j, bArr, i, i2);
        return i2;
    }

    public VideoPlayerDataSource(InputStream inputStream, int i) {
        try {
            this.mBuffer = new byte[i];
            int i2 = 0;
            while (i2 < i) {
                int read = inputStream.read(this.mBuffer, i2, i - i2);
                if (read != -1) {
                    i2 += read;
                } else {
                    return;
                }
            }
        } catch (Exception unused) {
        }
    }
}
