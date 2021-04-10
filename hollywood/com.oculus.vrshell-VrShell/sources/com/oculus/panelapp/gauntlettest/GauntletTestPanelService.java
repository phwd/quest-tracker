package com.oculus.panelapp.gauntlettest;

import android.os.Bundle;
import android.view.Surface;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panelservice.PanelService;
import java.util.Map;

public class GauntletTestPanelService extends PanelService {
    private static String TAG = LoggingUtil.tag(GauntletTestPanelService.class);
    GauntletTestPanelApp mApp;

    @Override // com.oculus.vrshell.panelservice.PanelService
    public void onCreate() {
        System.loadLibrary("vrshellgauntlettestpanel");
        super.onCreate();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panelservice.PanelService
    public long createNativePanelAppInstance(Surface surface, Map<String, Surface> map, Bundle bundle) {
        this.mApp = new GauntletTestPanelApp(getApplication(), getApplicationContext(), surface, map, bundle);
        this.mApp.inflateDefaultLayers();
        return this.mApp.getNativePointer();
    }
}
