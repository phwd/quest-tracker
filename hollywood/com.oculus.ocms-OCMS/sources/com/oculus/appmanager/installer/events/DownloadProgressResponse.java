package com.oculus.appmanager.installer.events;

import com.google.common.base.MoreObjects;

public class DownloadProgressResponse {
    public final long downloadingBytes;
    public final String installIdentifier;
    public final long totalBytes;

    public DownloadProgressResponse(String str, long j, long j2) {
        this.installIdentifier = str;
        this.totalBytes = j;
        this.downloadingBytes = j2;
    }

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper((Class<?>) DownloadProgressResponse.class);
        stringHelper.add("identifier", this.installIdentifier);
        stringHelper.add("totalBytes", this.totalBytes);
        stringHelper.add("downloadingBytes", this.downloadingBytes);
        return stringHelper.toString();
    }
}
