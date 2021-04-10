package com.oculus.panelapp.keyboardv2;

import android.os.Bundle;
import android.view.Surface;
import com.oculus.vrshell.panelservice.PanelService;
import java.util.Map;

public class KeyboardPanelService extends PanelService {
    private static String TAG = "KeyboardPanelService";
    private KeyboardPanelApp mApp;

    @Override // com.oculus.vrshell.panelservice.PanelService
    public void onCreate() {
        System.loadLibrary("vrshellkeyboardv2");
        super.onCreate();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panelservice.PanelService
    public long createNativePanelAppInstance(Surface surface, Map<String, Surface> map, Bundle bundle) {
        this.mApp = new KeyboardPanelApp(getApplication(), getApplicationContext(), surface, map, bundle);
        return this.mApp.getNativePointer();
    }
}
