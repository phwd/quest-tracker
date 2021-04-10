package com.oculus.panellib.videoplayer;

import android.media.MediaDataSource;
import java.io.IOException;
import java.io.InputStream;

public class VideoPlayerDataSource extends MediaDataSource {
    private byte[] mBuffer;

    VideoPlayerDataSource(InputStream inputStream, int fileSize) {
        try {
            this.mBuffer = new byte[fileSize];
            int offset = 0;
            while (offset < fileSize) {
                int amountRead = inputStream.read(this.mBuffer, offset, fileSize - offset);
                if (amountRead != -1) {
                    offset += amountRead;
                } else {
                    return;
                }
            }
        } catch (Exception e) {
        }
    }

    @Override // android.media.MediaDataSource
    public long getSize() throws IOException {
        return (long) this.mBuffer.length;
    }

    @Override // android.media.MediaDataSource
    public int readAt(long position, byte[] outputBuffer, int offset, int size) throws IOException {
        int fileSize = this.mBuffer.length;
        if (position >= ((long) fileSize)) {
            return -1;
        }
        if (((long) size) + position > ((long) fileSize)) {
            size = fileSize - ((int) position);
        }
        System.arraycopy(this.mBuffer, (int) position, outputBuffer, offset, size);
        return size;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }
}
