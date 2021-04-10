package com.oculus.appmanager.installer.events;

import com.google.common.base.MoreObjects;

public class InstallCancelledResponse {
    public final String installIdentifier;

    public InstallCancelledResponse(String str) {
        this.installIdentifier = str;
    }

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper((Class<?>) InstallCancelledResponse.class);
        stringHelper.add("identifier", this.installIdentifier);
        return stringHelper.toString();
    }
}
