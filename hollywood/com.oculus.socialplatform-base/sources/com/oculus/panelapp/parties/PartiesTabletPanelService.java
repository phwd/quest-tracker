package com.oculus.panelapp.parties;

import android.os.Bundle;
import android.view.Surface;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panelservice.PanelService;
import java.util.Map;

public class PartiesTabletPanelService extends PanelService {
    public static final String TAG = LoggingUtil.tag(PartiesTabletPanelService.class);
    public PartiesTabletPanelApp mApp;

    @Override // com.oculus.vrshell.panelservice.PanelService
    public void onCreate() {
        System.loadLibrary("vrshellpartiestabletpanel");
        super.onCreate();
    }

    @Override // com.oculus.vrshell.panelservice.PanelService
    public long createNativePanelAppInstance(Surface surface, Map<String, Surface> map, Bundle bundle) {
        PartiesTabletPanelApp partiesTabletPanelApp = new PartiesTabletPanelApp(getApplication(), getApplicationContext(), surface, map, bundle);
        this.mApp = partiesTabletPanelApp;
        return partiesTabletPanelApp.getNativePointer();
    }
}
