package com.oculus.panelapp.androiddialog;

import android.os.Bundle;
import android.view.Surface;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panelservice.PanelService;
import java.util.Map;

public class AndroidDialogPanelService extends PanelService {
    private static final String TAG = LoggingUtil.tag(AndroidDialogPanelService.class);
    AndroidDialogPanelApp mAndroidDialogPanelApp;

    @Override // com.oculus.vrshell.panelservice.PanelService
    public void onCreate() {
        System.loadLibrary("vrshellandroiddialog");
        super.onCreate();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panelservice.PanelService
    public long createNativePanelAppInstance(Surface surface, Map<String, Surface> map, Bundle bundle) {
        this.mAndroidDialogPanelApp = new AndroidDialogPanelApp(getApplication(), getApplicationContext(), surface, map, bundle);
        return this.mAndroidDialogPanelApp.getNativePointer();
    }
}
