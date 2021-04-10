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

    public String toString() {
        return MoreObjects.toStringHelper((Class<?>) DownloadInfo.class).add("download_id", this.id).add("status", OculusDownloadManagerUtils.stringifyStatus(this.status)).add("reason", OculusDownloadManagerUtils.stringifyReason(this.reason)).add("total_size_bytes", this.totalSizeBytes).add("downloaded_bytes", this.downloadedBytes).add("uri", this.localUri).add("description", this.description).add("extras", this.extras).toString();
    }
}
