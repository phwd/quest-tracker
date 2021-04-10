package com.oculus.library.model;

import com.oculus.appmanager.info.model.InstallerResult;

public interface InstallerCallback {
    void onInstallerResult(InstallerResult installerResult);
}
