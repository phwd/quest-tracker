package com.oculus.appmanager.installer.events;

import com.google.common.base.MoreObjects;

public class DownloadingResponse {
    public final long downloadSize;
    public final String installIdentifier;

    public DownloadingResponse(String str, long j) {
        this.installIdentifier = str;
        this.downloadSize = j;
    }

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper((Class<?>) DownloadingResponse.class);
        stringHelper.add("identifier", this.installIdentifier);
        stringHelper.add("downloadSize", this.downloadSize);
        return stringHelper.toString();
    }
}
