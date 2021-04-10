package com.oculus.appmanager.installer.events;

import com.oculus.appmanager.info.model.InstallerResult;

public class InstallResponse {
    public final String errorMsg;
    public final String installIdentifier;
    public final InstallerResult installerResult;
    public final boolean isSuccess;
    public final boolean isUpdate;
    public final long updateId;
    public final int versionCode;
}
