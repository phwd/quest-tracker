package com.oculus.horizon.cloudstorage2;

import com.facebook.common.stringformat.StringFormatUtil;
import com.oculus.downloader.model.DownloadInfo;
import javax.annotation.concurrent.Immutable;

@Immutable
public class DownloadFailureException extends Exception {
    public final long mDownloadId;
    public final String mFailureReason;
    public final int mReason;
    public final int mStatus;

    public final String getMessage() {
        return StringFormatUtil.formatStrLocaleSafe("Download %d failed! Reason: %s Status: %d Reason: %d", Long.valueOf(this.mDownloadId), this.mFailureReason, Integer.valueOf(this.mStatus), Integer.valueOf(this.mReason));
    }

    public DownloadFailureException(String str, DownloadInfo downloadInfo) {
        super(str);
        this.mFailureReason = str;
        this.mDownloadId = downloadInfo.id;
        this.mStatus = downloadInfo.status;
        this.mReason = downloadInfo.reason;
    }
}
