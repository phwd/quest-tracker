package com.oculus.panelapp.debug;

import android.os.Bundle;
import android.view.Surface;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panelservice.PanelService;
import java.util.Map;

public final class ShellDebugPanelService extends PanelService {
    private static String TAG = LoggingUtil.tag(ShellDebugPanelService.class);

    @Override // com.oculus.vrshell.panelservice.PanelService
    public void onCreate() {
        System.loadLibrary("vrshelldebugpanel");
        super.onCreate();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panelservice.PanelService
    public long createNativePanelAppInstance(Surface surface, Map<String, Surface> map, Bundle bundle) {
        return new ShellDebugPanelApp(getApplication(), getApplicationContext(), surface, map, bundle).getNativePointer();
    }
}
