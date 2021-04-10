package com.oculus.panelapp.dogfood;

import android.os.Bundle;
import android.view.Surface;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panelservice.PanelService;
import java.util.Map;

public class DogfoodPanelService extends PanelService {
    private static String TAG = LoggingUtil.tag(DogfoodPanelService.class);
    DogfoodPanelApp mApp;

    @Override // com.oculus.vrshell.panelservice.PanelService
    public void onCreate() {
        System.loadLibrary("vrshelldogfoodpanel");
        super.onCreate();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panelservice.PanelService
    public long createNativePanelAppInstance(Surface surface, Map<String, Surface> map, Bundle bundle) {
        this.mApp = new DogfoodPanelApp(getApplication(), getApplicationContext(), surface, map, bundle);
        return this.mApp.getNativePointer();
    }

    @Override // com.oculus.vrshell.panelservice.PanelService
    public void onDestroy() {
        super.onDestroy();
        this.mApp.onDestroy();
    }
}
