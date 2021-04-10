package com.oculus.explore;

import com.facebook.soloader.SoLoader;
import com.oculus.device.APIInterceptor;
import com.oculus.modules.LibraryModuleImpl;
import com.oculus.modules.QuickExperimentsModuleImpl;
import com.oculus.modules.QuickPromotionModuleImpl;
import com.oculus.modules.SocialModule;
import com.oculus.panellib.ReactVRPanelService;
import com.oculus.vrshell.home.module.GateKeepers;

public class PanelService extends ReactVRPanelService {
    static {
        SoLoader.loadLibrary("explore");
    }

    public PanelService() {
        super("899822547133980", "oculus-explore");
    }

    @Override // com.oculus.panellib.ReactVRPanelService
    public void createService() {
        LibraryModuleImpl.registerLibraryChangeListener(getApplicationContext());
        LibraryModuleImpl.fetchInitialLibraryAsync(getApplicationContext());
        SocialModule.registerPartyChangeListener(this);
        SocialModule.registerFriendsChangeListener(this);
        GateKeepers.initialFetchAsync(this);
        QuickExperimentsModuleImpl.initialFetchAsync(this);
        QuickPromotionModuleImpl.setHttpClientInterceptor(new APIInterceptor(getApplicationContext()));
    }
}
