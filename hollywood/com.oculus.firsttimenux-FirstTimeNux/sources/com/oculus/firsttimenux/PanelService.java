package com.oculus.firsttimenux;

import com.oculus.modules.QuickExperimentsModuleImpl;
import com.oculus.panellib.ReactVRPanelService;
import com.oculus.vrshell.home.module.GateKeepers;

public class PanelService extends ReactVRPanelService {
    public static final String FB_APP_ID = "1745759385591114";
    public static final String FB_APP_NAME = "oculus-first-time-nux";
    public static final String FB_APP_SECRET = "e4ae047ed1992c3a882d5ff4f3bbe902";
    private static final String TAG = "FirstTimeNuxPanelService";

    static {
        System.loadLibrary("firsttimenux");
    }

    public PanelService() {
        super("firsttimenux", FB_APP_ID, FB_APP_NAME);
    }

    @Override // com.oculus.panellib.ReactVRPanelService
    public void createService() {
        GateKeepers.initialFetchAsync(this);
        QuickExperimentsModuleImpl.initialFetchAsync(this);
    }
}
