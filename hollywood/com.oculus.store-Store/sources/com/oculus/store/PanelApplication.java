package com.oculus.store;

import com.oculus.device.AccessTokenForwarder;
import com.oculus.device.NetworkHeaders;
import com.oculus.device.UserAgentBuilder;
import com.oculus.modules.AppLaunchModuleImpl;
import com.oculus.modules.AuthModuleImpl;
import com.oculus.modules.DeviceEnvironmentModuleImpl;
import com.oculus.modules.DialogsModuleImpl;
import com.oculus.modules.LibraryModuleImpl;
import com.oculus.modules.PanelCookiesModuleImpl;
import com.oculus.modules.QuickPromotionModuleImpl;
import com.oculus.modules.SettingsManagerModuleImpl;
import com.oculus.modules.SocialModule;
import com.oculus.modules.StoreGraphQLPrefetchModule;
import com.oculus.modules.StoreServiceModuleImpl;
import com.oculus.modules.ToasterModuleImpl;
import com.oculus.modules.UserManagerModuleImpl;
import com.oculus.panellib.ReactVRApplication;
import com.oculus.vrshell.home.HomeEventLogger;

public class PanelApplication extends ReactVRApplication {
    private static final AccessTokenForwarder ACCESS_TOKEN_FORWARDER = new AccessTokenForwarder();
    private static final String TAG = "PanelApplication";

    public PanelApplication() {
        super("release");
    }

    @Override // com.oculus.panellib.ReactVRApplication
    public void onCreate() {
        createModule(StoreGraphQLPrefetchModule.class);
        HomeEventLogger.init(this);
        super.onCreate();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panellib.ReactVRApplication
    public void createModules() {
        NetworkHeaders.init(this, UserAgentBuilder.newBuilder(), "OculusStore");
        String accessToken = ((PanelCookiesModuleImpl) createModule(PanelCookiesModuleImpl.class)).getAccessToken();
        if (accessToken != null) {
            ACCESS_TOKEN_FORWARDER.onGetAccessToken(accessToken);
        }
        createModule(AppLaunchModuleImpl.class);
        createModule(DeviceEnvironmentModuleImpl.class);
        createModule(LibraryModuleImpl.class);
        createModule(QuickPromotionModuleImpl.class);
        createModule(SettingsManagerModuleImpl.class);
        createModule(SocialModule.class);
        createModule(DialogsModuleImpl.class);
        createModule(ToasterModuleImpl.class);
        createModule(StoreServiceModuleImpl.class);
        createModule(AuthModuleImpl.class);
        createModule(UserManagerModuleImpl.class);
    }
}
