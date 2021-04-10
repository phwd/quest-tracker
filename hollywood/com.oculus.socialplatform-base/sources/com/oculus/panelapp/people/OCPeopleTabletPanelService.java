package com.oculus.panelapp.people;

import android.os.Bundle;
import android.view.Surface;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panelservice.PanelService;
import java.util.Map;

public class OCPeopleTabletPanelService extends PanelService {
    public static final String TAG = LoggingUtil.tag(OCPeopleTabletPanelService.class);
    public PeopleTabletPanelApp mApp;

    @Override // com.oculus.vrshell.panelservice.PanelService
    public void onCreate() {
        System.loadLibrary("vrshellpeopletabletpanel");
        super.onCreate();
    }

    @Override // com.oculus.vrshell.panelservice.PanelService
    public long createNativePanelAppInstance(Surface surface, Map<String, Surface> map, Bundle bundle) {
        OCPeopleTabletPanelApp oCPeopleTabletPanelApp = new OCPeopleTabletPanelApp(getApplication(), getApplicationContext(), surface, map, bundle);
        this.mApp = oCPeopleTabletPanelApp;
        return oCPeopleTabletPanelApp.getNativePointer();
    }
}
