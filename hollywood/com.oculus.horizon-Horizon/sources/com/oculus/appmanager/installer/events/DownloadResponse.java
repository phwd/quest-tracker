package com.oculus.appmanager.installer.events;

import com.oculus.appmanager.info.model.InstallerResult;

public class DownloadResponse {
    public static final int ERROR_DOWNLOAD_MANAGER_NOT_FOUND = 10001;
    public final int errorCode;
    public final String installIdentifier;
    public InstallerResult installerResult;
    public final boolean isSuccess;
    public final boolean isUpdate;
    public final long updateId;
}
