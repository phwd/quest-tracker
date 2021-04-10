package com.oculus.panelapp.socialreauth;

import android.os.Bundle;
import android.view.Surface;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panelservice.PanelService;
import java.util.Map;

public class SocialReauthTabletPanelService extends PanelService {
    public static final String TAG = LoggingUtil.tag(SocialReauthTabletPanelService.class);
    public SocialReauthTabletPanelApp mApp;

    @Override // com.oculus.vrshell.panelservice.PanelService
    public void onCreate() {
        System.loadLibrary("vrshellsocialreauthtabletpanel");
        super.onCreate();
    }

    @Override // com.oculus.vrshell.panelservice.PanelService
    public long createNativePanelAppInstance(Surface surface, Map<String, Surface> map, Bundle bundle) {
        SocialReauthTabletPanelApp socialReauthTabletPanelApp = new SocialReauthTabletPanelApp(getApplication(), getApplicationContext(), surface, map, bundle);
        this.mApp = socialReauthTabletPanelApp;
        return socialReauthTabletPanelApp.getNativePointer();
    }
}
