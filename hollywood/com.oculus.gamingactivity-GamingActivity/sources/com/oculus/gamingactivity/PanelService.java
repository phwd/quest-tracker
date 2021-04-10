package com.oculus.gamingactivity;

import android.os.Build;
import com.oculus.modules.LibraryModuleImpl;
import com.oculus.panellib.ReactVRPanelService;
import com.oculus.util.StringUtil;

public class PanelService extends ReactVRPanelService {
    public static final String FB_APP_ID = "403145643722513";
    public static final String FB_APP_NAME = "oculus-gaming-activity";
    public static final String FB_APP_SECRET = "64d81bdb73ef9959dc1cd4489620ab52";
    private static final String TAG = "GamingActivityPanelService";

    static {
        System.loadLibrary("gamingactivity");
    }

    public PanelService() {
        super("gamingactivity", FB_APP_ID, FB_APP_NAME);
    }

    @Override // com.oculus.panellib.ReactVRPanelService
    public void createService() {
        LibraryModuleImpl.registerLibraryChangeListener(getApplicationContext());
        LibraryModuleImpl.fetchInitialLibraryAsync(getApplicationContext());
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return StringUtil.capitalize(model);
        }
        return StringUtil.capitalize(manufacturer) + " " + model;
    }
}
