package com.oculus.appmanager.installer.service;

import com.oculus.appmanager.info.ApkUpdateInfo;

public interface InstallFlow {
    boolean continueUpdate(ApkUpdateInfo apkUpdateInfo);
}
