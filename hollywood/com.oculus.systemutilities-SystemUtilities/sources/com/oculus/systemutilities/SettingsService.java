package com.oculus.systemutilities;

import com.oculus.modules.codegen.SystemUtilitiesServiceModule;
import com.oculus.panellib.ReactVRPanelService;

public class SettingsService extends ReactVRPanelService {
    public SettingsService() {
        super("systemutilities", SystemUtilitiesApplication.FB_SYSTEM_UTILITIES_APP_ID, SystemUtilitiesApplication.FB_SYSTEM_UTILITIES_APP_NAME, SystemUtilitiesServiceModule.ServiceType.SETTINGS.toString());
        BaseService.loadLibrary();
    }

    @Override // com.oculus.panellib.ReactVRPanelService
    public void createService() {
        BaseService.initialize(this, SystemUtilitiesServiceModule.ServiceType.SETTINGS, this.mMainHandler);
    }

    @Override // com.oculus.vrshell.panel.PanelService, com.oculus.panellib.ReactVRPanelService
    public void onDestroy() {
        super.onDestroy();
        BaseService.destroy();
    }
}
