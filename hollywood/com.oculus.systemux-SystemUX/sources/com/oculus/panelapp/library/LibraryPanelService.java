package com.oculus.panelapp.library;

import android.os.Bundle;
import android.view.Surface;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panelservice.PanelService;
import java.util.Map;

public class LibraryPanelService extends PanelService {
    private static final String TAG = LoggingUtil.tag(LibraryPanelService.class);
    LibraryPanelApp mApp;

    @Override // com.oculus.vrshell.panelservice.PanelService
    public void onCreate() {
        System.loadLibrary("vrshelllibrarypanel");
        super.onCreate();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panelservice.PanelService
    public long createNativePanelAppInstance(Surface surface, Map<String, Surface> map, Bundle bundle) {
        this.mApp = new LibraryPanelApp(getApplication(), getApplicationContext(), surface, map, bundle);
        return this.mApp.getNativePointer();
    }
}
