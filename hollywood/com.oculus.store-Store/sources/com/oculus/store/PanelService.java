package com.oculus.store;

import com.oculus.modules.LibraryModuleImpl;
import com.oculus.panellib.ReactVRPanelService;

public class PanelService extends ReactVRPanelService {
    public static final String FB_APP_ID = "1184135028631542";
    public static final String FB_APP_NAME = "oculus-store";
    public static final String FB_APP_SECRET = "1829c3aa5482b0bc330f228f239e7abc";
    private static final String TAG = "StorePanelService";

    static {
        System.loadLibrary("store");
    }

    public PanelService() {
        super("store", FB_APP_ID, FB_APP_NAME);
    }

    @Override // com.oculus.panellib.ReactVRPanelService
    public void createService() {
        LibraryModuleImpl.registerLibraryChangeListener(getApplicationContext());
        LibraryModuleImpl.fetchInitialLibraryAsync(getApplicationContext());
    }
}
