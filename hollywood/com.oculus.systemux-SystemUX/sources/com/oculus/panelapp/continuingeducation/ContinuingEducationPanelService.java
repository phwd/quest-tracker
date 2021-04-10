package com.oculus.panelapp.continuingeducation;

import android.os.Bundle;
import android.view.Surface;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panelservice.PanelService;
import java.util.Map;

public class ContinuingEducationPanelService extends PanelService {
    private static String TAG = LoggingUtil.tag(ContinuingEducationPanelService.class);

    @Override // com.oculus.vrshell.panelservice.PanelService
    public void onCreate() {
        System.loadLibrary("continuingeducationpanel");
        super.onCreate();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panelservice.PanelService
    public long createNativePanelAppInstance(Surface surface, Map<String, Surface> map, Bundle bundle) {
        return new ContinuingEducationPanelApp(getApplication(), getApplicationContext(), surface, map, bundle).getNativePointer();
    }
}
