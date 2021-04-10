package com.oculus.systemutilities;

import com.oculus.modules.codegen.SystemUtilitiesServiceModule;
import com.oculus.panellib.ReactVRPanelService;

public class FileManagerService extends ReactVRPanelService {
    public FileManagerService() {
        super("systemutilities", SystemUtilitiesApplication.FB_SYSTEM_UTILITIES_APP_ID, SystemUtilitiesApplication.FB_SYSTEM_UTILITIES_APP_NAME, SystemUtilitiesServiceModule.ServiceType.FILE_MANAGER.toString());
        BaseService.loadLibrary();
    }

    @Override // com.oculus.panellib.ReactVRPanelService
    public void createService() {
        BaseService.initialize(this, SystemUtilitiesServiceModule.ServiceType.FILE_MANAGER, this.mMainHandler);
    }

    @Override // com.oculus.vrshell.panel.PanelService, com.oculus.panellib.ReactVRPanelService
    public void onDestroy() {
        super.onDestroy();
        BaseService.destroy();
    }
}
