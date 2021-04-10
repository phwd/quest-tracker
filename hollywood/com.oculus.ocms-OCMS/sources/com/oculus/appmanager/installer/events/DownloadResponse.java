package com.oculus.appmanager.installer.events;

import com.google.common.base.MoreObjects;
import com.oculus.appmanager.info.model.InstallerResult;
import com.oculus.horizon.logging.LoggingKeys;
import com.oculus.libraryapi.OVRLibrary;

public class DownloadResponse {
    public static final int ERROR_DOWNLOAD_MANAGER_NOT_FOUND = 10001;
    public final int errorCode;
    public final String installIdentifier;
    public InstallerResult installerResult;
    public final boolean isSuccess;
    public final boolean isUpdate;
    public final long updateId;

    public DownloadResponse(long j, boolean z, boolean z2, String str, int i, InstallerResult installerResult2) {
        this.updateId = j;
        this.isSuccess = z;
        this.isUpdate = z2;
        this.installIdentifier = str;
        this.errorCode = i;
        this.installerResult = installerResult2;
    }

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper((Class<?>) DownloadResponse.class);
        stringHelper.add("identifier", this.installIdentifier);
        stringHelper.add(LoggingKeys.SUCCESS, this.isSuccess);
        stringHelper.add(OVRLibrary.EXTRA_ERROR_CODE, this.errorCode);
        return stringHelper.toString();
    }
}
