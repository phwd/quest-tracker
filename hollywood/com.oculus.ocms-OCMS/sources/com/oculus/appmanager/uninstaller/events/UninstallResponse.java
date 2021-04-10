package com.oculus.appmanager.uninstaller.events;

import android.os.ResultReceiver;
import com.facebook.debug.log.LoggingUtil;
import com.google.common.base.MoreObjects;
import com.oculus.appmanager.info.model.InstallerResultError;
import com.oculus.horizon.logging.LoggingKeys;
import javax.annotation.Nullable;

public class UninstallResponse {
    public final String installerIdentifier;
    @Nullable
    public final InstallerResultError installerResultError;
    public final boolean isSuccess;
    @Nullable
    public final ResultReceiver resultReceiver;

    public static UninstallResponse success(String str, @Nullable ResultReceiver resultReceiver2) {
        return new UninstallResponse(true, str, null, resultReceiver2);
    }

    public static UninstallResponse failure(String str, InstallerResultError installerResultError2, @Nullable ResultReceiver resultReceiver2) {
        return new UninstallResponse(false, str, installerResultError2, resultReceiver2);
    }

    private UninstallResponse(boolean z, String str, @Nullable InstallerResultError installerResultError2, @Nullable ResultReceiver resultReceiver2) {
        this.isSuccess = z;
        this.installerIdentifier = str;
        this.installerResultError = installerResultError2;
        this.resultReceiver = resultReceiver2;
    }

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper((Class<?>) UninstallResponse.class);
        stringHelper.add("identifier", this.installerIdentifier);
        stringHelper.add(LoggingKeys.SUCCESS, this.isSuccess);
        InstallerResultError installerResultError2 = this.installerResultError;
        stringHelper.add("error", installerResultError2 == null ? LoggingUtil.NO_HASHCODE : installerResultError2.name());
        return stringHelper.toString();
    }
}
