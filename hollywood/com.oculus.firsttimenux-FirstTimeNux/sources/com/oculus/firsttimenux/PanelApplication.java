package com.oculus.firsttimenux;

import android.text.TextUtils;
import com.oculus.device.AccessTokenForwarder;
import com.oculus.device.NetworkHeaders;
import com.oculus.device.UserAgentBuilder;
import com.oculus.modules.AccountLinkingModule;
import com.oculus.modules.AuthModuleImpl;
import com.oculus.modules.BatteryStateModuleImpl;
import com.oculus.modules.CompanionServerModuleImpl;
import com.oculus.modules.ConnectivityStateModuleImpl;
import com.oculus.modules.ControllerStateModuleImpl;
import com.oculus.modules.DeviceEnvironmentModuleImpl;
import com.oculus.modules.DeviceUnlockStatusModuleImpl;
import com.oculus.modules.FirstTimeNUXSettingsModuleImpl;
import com.oculus.modules.FirstTimeNuxOtaModuleImpl;
import com.oculus.modules.FirstTimeNuxServiceModuleImpl;
import com.oculus.modules.LanguageModuleImpl;
import com.oculus.modules.PanelCookiesModuleImpl;
import com.oculus.modules.QuickExperimentsModuleImpl;
import com.oculus.modules.SettingsManagerModuleImpl;
import com.oculus.modules.SystemPropertiesModuleImpl;
import com.oculus.modules.SystemTooltipModuleImpl;
import com.oculus.modules.UserManagerModuleImpl;
import com.oculus.modules.UserModuleImpl;
import com.oculus.modules.WebTaskLaunchModuleImpl;
import com.oculus.modules.WifiManagerModuleImpl;
import com.oculus.panellib.ReactVRApplication;
import com.oculus.vrshell.home.HomeEventLogger;
import com.oculus.vrshell.home.module.GateKeepers;

public class PanelApplication extends ReactVRApplication {
    private static final String TAG = "FirstTimeNuxPanelApplication";
    private AccessTokenForwarder mAccessTokenForwarder = new AccessTokenForwarder();

    public PanelApplication() {
        super("release");
    }

    @Override // com.oculus.panellib.ReactVRApplication
    public void onCreate() {
        HomeEventLogger.init(this);
        super.onCreate();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panellib.ReactVRApplication
    public void createApplication() {
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panellib.ReactVRApplication
    public void createModules() {
        NetworkHeaders.init(this, UserAgentBuilder.newBuilder(), "OculusFirstTimeNux");
        String accessToken = ((PanelCookiesModuleImpl) createModule(PanelCookiesModuleImpl.class)).getAccessToken();
        if (!TextUtils.isEmpty(accessToken)) {
            this.mAccessTokenForwarder.onGetAccessToken(accessToken);
        }
        createModule(DeviceEnvironmentModuleImpl.class);
        createModule(AuthModuleImpl.class);
        createModule(BatteryStateModuleImpl.class);
        createModule(CompanionServerModuleImpl.class);
        createModule(ControllerStateModuleImpl.class);
        createModule(FirstTimeNuxOtaModuleImpl.class);
        createModule(FirstTimeNUXSettingsModuleImpl.class);
        createModule(LanguageModuleImpl.class);
        createModule(UserModuleImpl.class);
        createModule(UserManagerModuleImpl.class);
        createModule(WebTaskLaunchModuleImpl.class);
        createModule(QuickExperimentsModuleImpl.class);
        createModule(SystemTooltipModuleImpl.class);
        createModule(AccountLinkingModule.class);
        createModule(WifiManagerModuleImpl.class);
        createModule(ConnectivityStateModuleImpl.class);
        createModule(GateKeepers.class);
        createModule(SettingsManagerModuleImpl.class);
        createModule(SystemPropertiesModuleImpl.class);
        createModule(DeviceUnlockStatusModuleImpl.class);
        createModule(FirstTimeNuxServiceModuleImpl.class);
    }
}
