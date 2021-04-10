package com.oculus.appmanager.installer.events;

import com.facebook.debug.log.LoggingUtil;
import com.google.common.base.MoreObjects;
import com.oculus.appmanager.info.model.InstallerResult;
import com.oculus.horizon.logging.LoggingKeys;

public class InstallResponse {
    public final String errorMsg;
    public final String installIdentifier;
    public final InstallerResult installerResult;
    public final boolean isSuccess;
    public final boolean isUpdate;
    public final long updateId;
    public final int versionCode;

    public InstallResponse(long j, boolean z, boolean z2, String str, int i, String str2, InstallerResult installerResult2) {
        this.updateId = j;
        this.isSuccess = z;
        this.isUpdate = z2;
        this.installIdentifier = str;
        this.versionCode = i;
        this.errorMsg = str2;
        this.installerResult = installerResult2;
    }

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper((Class<?>) InstallResponse.class);
        stringHelper.add("identifier", this.installIdentifier);
        stringHelper.add(LoggingKeys.SUCCESS, this.isSuccess);
        stringHelper.add("error", this.installerResult.error == null ? LoggingUtil.NO_HASHCODE : this.installerResult.error.name());
        return stringHelper.toString();
    }
}
