package com.oculus.panelapp.socialandroidbackpanel;

import android.os.Bundle;
import android.view.Surface;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panelservice.PanelService;
import java.util.Map;

public class SocialAndroidBackPanelService extends PanelService {
    public static final String TAG = LoggingUtil.tag(SocialAndroidBackPanelService.class);
    public SocialAndroidBackPanelApp mSocialAndroidBackPanelApp;

    @Override // com.oculus.vrshell.panelservice.PanelService
    public void onCreate() {
        System.loadLibrary("socialbackpanel");
        super.onCreate();
    }

    @Override // com.oculus.vrshell.panelservice.PanelService
    public long createNativePanelAppInstance(Surface surface, Map<String, Surface> map, Bundle bundle) {
        SocialAndroidBackPanelApp socialAndroidBackPanelApp = new SocialAndroidBackPanelApp(getApplication(), getApplicationContext(), surface, map, bundle);
        this.mSocialAndroidBackPanelApp = socialAndroidBackPanelApp;
        return socialAndroidBackPanelApp.getNativePointer();
    }
}
