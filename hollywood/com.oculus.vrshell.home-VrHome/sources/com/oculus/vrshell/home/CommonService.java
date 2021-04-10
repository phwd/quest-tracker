package com.oculus.vrshell.home;

import android.content.ComponentCallbacks2;
import android.content.Intent;
import com.oculus.modules.codegen.ServiceModule;
import com.oculus.panellib.ReactVRPanelService;

public class CommonService extends ReactVRPanelService implements ComponentCallbacks2 {
    final ServiceModule.ServiceType mServiceType;

    public CommonService(ServiceModule.ServiceType serviceType) {
        super("home", HomeApplication.FB_HOME_APP_ID, HomeApplication.FB_HOME_APP_NAME, serviceType.toString());
        this.mServiceType = serviceType;
        BaseService.loadLibrary();
    }

    @Override // com.oculus.panellib.ReactVRPanelService
    public void createService() {
        BaseService.initialize(this, this.mServiceType, this.mMainHandler);
    }

    @Override // com.oculus.vrshell.panel.PanelService
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override // com.oculus.vrshell.panel.PanelService, com.oculus.panellib.ReactVRPanelService
    public void onDestroy() {
        super.onDestroy();
        BaseService.destroy();
    }
}
