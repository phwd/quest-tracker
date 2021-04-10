package com.oculus.panelapp.managedlauncher;

import android.os.Bundle;
import android.view.Surface;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panelservice.PanelService;
import java.util.Map;

public class ManagedLauncherPanelService extends PanelService {
    private static String TAG = LoggingUtil.tag(ManagedLauncherPanelService.class);
    ManagedLauncherPanelApp mApp;

    static {
        LoggingUtil.initPrefix("[OEP] ");
    }

    @Override // com.oculus.vrshell.panelservice.PanelService
    public void onCreate() {
        System.loadLibrary("managed_launcher");
        super.onCreate();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panelservice.PanelService
    public long createNativePanelAppInstance(Surface surface, Map<String, Surface> map, Bundle bundle) {
        this.mApp = new ManagedLauncherPanelApp(getApplication(), getApplicationContext(), surface, map, bundle);
        this.mApp.inflateDefaultLayers();
        return this.mApp.getNativePointer();
    }
}
