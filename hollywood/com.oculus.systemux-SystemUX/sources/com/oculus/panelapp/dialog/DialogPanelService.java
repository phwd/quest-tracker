package com.oculus.panelapp.dialog;

import android.os.Bundle;
import android.view.Surface;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panelservice.PanelService;
import java.util.Map;

public class DialogPanelService extends PanelService {
    private static final String TAG = LoggingUtil.tag(DialogPanelService.class);
    DialogPanelApp mDialogPanelApp;

    @Override // com.oculus.vrshell.panelservice.PanelService
    public void onCreate() {
        System.loadLibrary("vrshelldialogpanel");
        super.onCreate();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panelservice.PanelService
    public long createNativePanelAppInstance(Surface surface, Map<String, Surface> map, Bundle bundle) {
        this.mDialogPanelApp = new DialogPanelApp(getApplication(), getApplicationContext(), surface, map, bundle);
        return this.mDialogPanelApp.getNativePointer();
    }
}
