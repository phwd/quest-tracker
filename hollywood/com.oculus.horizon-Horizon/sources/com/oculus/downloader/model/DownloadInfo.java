package com.oculus.downloader.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.oculus.downloader.util.OculusDownloadManagerUtils;
import com.oculus.extras.Extras;
import javax.annotation.concurrent.Immutable;

@Immutable
public class DownloadInfo {
    public final Optional<String> description;
    public final long downloadedBytes;
    public final Extras extras;
    public final long id;
    public final Optional<String> localUri;
    public final int reason;
    public final int status;
    public final long totalSizeBytes;

    public final String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(DownloadInfo.class);
        stringHelper.add("download_id", this.id);
        stringHelper.add("status", OculusDownloadManagerUtils.A01(this.status));
        stringHelper.add("reason", OculusDownloadManagerUtils.A00(this.reason));
        stringHelper.add("total_size_bytes", this.totalSizeBytes);
        stringHelper.add("downloaded_bytes", this.downloadedBytes);
        stringHelper.add("uri", this.localUri);
        stringHelper.add("description", this.description);
        stringHelper.add("extras", this.extras);
        return stringHelper.toString();
    }

    public DownloadInfo(long j, int i, int i2, long j2, long j3, Optional<String> optional, Optional<String> optional2, Extras extras2) {
        this.id = j;
        this.status = i;
        this.reason = i2;
        this.totalSizeBytes = j2;
        this.downloadedBytes = j3;
        this.localUri = optional;
        this.description = optional2;
        this.extras = extras2;
    }
}
