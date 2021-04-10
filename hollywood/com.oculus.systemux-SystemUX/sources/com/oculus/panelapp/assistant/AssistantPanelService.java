package com.oculus.panelapp.assistant;

import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panelservice.PanelService;
import java.util.Map;

public class AssistantPanelService extends PanelService {
    private static final String TAG = LoggingUtil.tag(AssistantPanelService.class);
    AssistantPanelApp mApp;

    static {
        LoggingUtil.initPrefix("[OAP] ");
    }

    @Override // com.oculus.vrshell.panelservice.PanelService
    public void onCreate() {
        System.loadLibrary("vrshellassistantpanel");
        super.onCreate();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panelservice.PanelService
    public long createNativePanelAppInstance(Surface surface, Map<String, Surface> map, Bundle bundle) {
        this.mApp = new AssistantPanelApp(getApplication(), getApplicationContext(), this, surface, map, bundle);
        return this.mApp.getNativePointer();
    }

    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        if (i == 5 || i == 15 || (!this.mApp.isShowing() && (i == 60 || i == 80))) {
            String str = TAG;
            Log.d(str, "Assistant UI Service not in use, stopping to free memory at level " + i);
            this.mApp.stopService();
        }
    }
}
