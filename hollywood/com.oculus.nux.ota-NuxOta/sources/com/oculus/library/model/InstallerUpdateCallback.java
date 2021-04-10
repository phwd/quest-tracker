package com.oculus.library.model;

import com.oculus.appmanager.info.model.InstallerUpdateResult;

public interface InstallerUpdateCallback {
    void onDownloaderResult(InstallerUpdateResult installerUpdateResult);
}
