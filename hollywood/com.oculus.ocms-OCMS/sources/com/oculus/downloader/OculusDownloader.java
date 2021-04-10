package com.oculus.downloader;

import com.facebook.inject.RequiresBinding;
import com.oculus.downloader.model.DownloadConfig;
import com.oculus.downloader.model.DownloadInfo;
import com.oculus.extras.Extras;
import java.util.List;
import javax.annotation.Nullable;

@RequiresBinding
public interface OculusDownloader {
    long enqueue(DownloadConfig downloadConfig, Extras extras);

    @Nullable
    DownloadInfo query(long j);

    List<DownloadInfo> queryAll();

    int queryRunningDownloads();

    boolean remove(long j);
}
