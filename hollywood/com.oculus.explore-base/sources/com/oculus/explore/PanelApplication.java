package com.oculus.explore;

import com.oculus.device.AccessTokenForwarder;
import com.oculus.device.NetworkHeaders;
import com.oculus.device.UserAgentBuilder;
import com.oculus.modules.AccountLinkingModule;
import com.oculus.modules.AppLaunchModuleImpl;
import com.oculus.modules.AuthModuleImpl;
import com.oculus.modules.DeviceConfigModuleImpl;
import com.oculus.modules.DeviceEnvironmentModuleImpl;
import com.oculus.modules.DialogsModuleImpl;
import com.oculus.modules.ExploreGraphQLPrefetchModule;
import com.oculus.modules.ExploreServiceModuleImpl;
import com.oculus.modules.InputDeviceModuleImpl;
import com.oculus.modules.LibraryModuleImpl;
import com.oculus.modules.PanelCookiesModuleImpl;
import com.oculus.modules.PartyCallsManagerModuleImpl;
import com.oculus.modules.QuickExperimentsModuleImpl;
import com.oculus.modules.QuickPromotionModuleImpl;
import com.oculus.modules.SettingsManagerModuleImpl;
import com.oculus.modules.SocialModule;
import com.oculus.modules.SystemTooltipModuleImpl;
import com.oculus.modules.ToasterModuleImpl;
import com.oculus.panellib.ReactVRApplication;
import com.oculus.vrshell.home.HomeEventLogger;
import com.oculus.vrshell.home.module.GateKeepers;

public class PanelApplication extends ReactVRApplication {
    private static final AccessTokenForwarder ACCESS_TOKEN_FORWARDER = new AccessTokenForwarder();

    public PanelApplication() {
        super("release");
    }

    @Override // com.oculus.panellib.ReactVRApplication
    public void onCreate() {
        createModule(ExploreGraphQLPrefetchModule.class);
        HomeEventLogger.init(this);
        super.onCreate();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panellib.ReactVRApplication
    public void createModules() {
        NetworkHeaders.init(this, UserAgentBuilder.newBuilder(), "OculusExplore");
        String accessToken = ((PanelCookiesModuleImpl) createModule(PanelCookiesModuleImpl.class)).getAccessToken();
        if (accessToken != null) {
            ACCESS_TOKEN_FORWARDER.onGetAccessToken(accessToken);
        }
        createModule(AccountLinkingModule.class);
        createModule(AppLaunchModuleImpl.class);
        createModule(AuthModuleImpl.class);
        createModule(DeviceConfigModuleImpl.class);
        createModule(DeviceEnvironmentModuleImpl.class);
        createModule(DialogsModuleImpl.class);
        createModule(ExploreServiceModuleImpl.class);
        createModule(GateKeepers.class);
        createModule(InputDeviceModuleImpl.class);
        createModule(LibraryModuleImpl.class);
        createModule(PartyCallsManagerModuleImpl.class);
        createModule(QuickExperimentsModuleImpl.class);
        createModule(QuickPromotionModuleImpl.class);
        createModule(SettingsManagerModuleImpl.class);
        createModule(SocialModule.class);
        createModule(SystemTooltipModuleImpl.class);
        createModule(ToasterModuleImpl.class);
    }
}
