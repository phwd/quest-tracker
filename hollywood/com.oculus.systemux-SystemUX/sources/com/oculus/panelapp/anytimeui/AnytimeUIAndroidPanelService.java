package com.oculus.panelapp.anytimeui;

import android.os.Bundle;
import android.view.Surface;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.systemux.SystemUXApplication;
import com.oculus.vrshell.panelservice.PanelService;
import java.util.Map;

public class AnytimeUIAndroidPanelService extends PanelService {
    private long mServiceStartMillis;

    @Override // com.oculus.vrshell.panelservice.PanelService
    public void onCreate() {
        this.mServiceStartMillis = System.currentTimeMillis();
        System.loadLibrary("vrshellanytimeui");
        super.onCreate();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panelservice.PanelService
    public long createNativePanelAppInstance(Surface surface, Map<String, Surface> map, Bundle bundle) {
        AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2 = new AnytimeUIAndroidPanelAppV2(this, (SystemUXApplication) getApplication(), getApplicationContext(), surface, map, bundle);
        anytimeUIAndroidPanelAppV2.inflateDefaultLayers();
        return anytimeUIAndroidPanelAppV2.getNativePointer();
    }

    public long getServiceUptimeSecs() {
        return (System.currentTimeMillis() - this.mServiceStartMillis) / 1000;
    }
}
