package com.oculus.appmanager.installer.events;

import com.google.common.base.MoreObjects;

public class DownloadQueuedResponse {
    public final String installIdentifier;

    public DownloadQueuedResponse(String str) {
        this.installIdentifier = str;
    }

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper((Class<?>) DownloadQueuedResponse.class);
        stringHelper.add("identifier", this.installIdentifier);
        return stringHelper.toString();
    }
}
