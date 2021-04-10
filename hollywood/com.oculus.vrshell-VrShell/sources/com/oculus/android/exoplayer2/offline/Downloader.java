package com.oculus.android.exoplayer2.offline;

import androidx.annotation.Nullable;
import java.io.IOException;

public interface Downloader {

    public interface ProgressListener {
        void onDownloadProgress(Downloader downloader, float f, long j);
    }

    void download(@Nullable ProgressListener progressListener) throws InterruptedException, IOException;

    float getDownloadPercentage();

    long getDownloadedBytes();

    void init() throws InterruptedException, IOException;

    void remove() throws InterruptedException;
}
