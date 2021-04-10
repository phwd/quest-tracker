package com.oculus.panelapp.socialsettings;

import android.os.Bundle;
import android.view.Surface;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panelservice.PanelService;
import java.util.Map;

public class SocialSettingsTabletPanelService extends PanelService {
    public static final String TAG = LoggingUtil.tag(SocialSettingsTabletPanelService.class);
    public SocialSettingsTabletPanelApp mApp;

    @Override // com.oculus.vrshell.panelservice.PanelService
    public void onCreate() {
        System.loadLibrary("vrshellsocialsettingstabletpanel");
        super.onCreate();
    }

    @Override // com.oculus.vrshell.panelservice.PanelService
    public long createNativePanelAppInstance(Surface surface, Map<String, Surface> map, Bundle bundle) {
        SocialSettingsTabletPanelApp socialSettingsTabletPanelApp = new SocialSettingsTabletPanelApp(getApplication(), getApplicationContext(), surface, map, bundle);
        this.mApp = socialSettingsTabletPanelApp;
        return socialSettingsTabletPanelApp.getNativePointer();
    }
}
