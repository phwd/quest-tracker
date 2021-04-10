package com.oculus.panelapp.people;

import android.os.Bundle;
import android.view.Surface;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panelservice.PanelService;
import java.util.Map;

public class FBPeopleTabletPanelService extends PanelService {
    public static final String TAG = LoggingUtil.tag(FBPeopleTabletPanelService.class);
    public PeopleTabletPanelApp mApp;

    @Override // com.oculus.vrshell.panelservice.PanelService
    public void onCreate() {
        System.loadLibrary("vrshellpeopletabletpanel");
        super.onCreate();
    }

    @Override // com.oculus.vrshell.panelservice.PanelService
    public long createNativePanelAppInstance(Surface surface, Map<String, Surface> map, Bundle bundle) {
        FBPeopleTabletPanelApp fBPeopleTabletPanelApp = new FBPeopleTabletPanelApp(getApplication(), getApplicationContext(), surface, map, bundle);
        this.mApp = fBPeopleTabletPanelApp;
        return fBPeopleTabletPanelApp.getNativePointer();
    }
}
