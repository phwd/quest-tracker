package com.oculus.fitnesstracker;

import android.os.Build;
import com.oculus.common.build.BuildConfig;
import com.oculus.modules.LibraryModuleImpl;
import com.oculus.panellib.ReactVRPanelService;

public class PanelService extends ReactVRPanelService {
    public static final String FB_APP_ID = "218916652582925";
    public static final String FB_APP_NAME = "oculus-fitness-tracker";
    private static final String TAG = "FitnessTrackerPanelService";

    static {
        System.loadLibrary("fitnesstracker");
    }

    public PanelService() {
        super(FB_APP_ID, FB_APP_NAME);
    }

    @Override // com.oculus.panellib.ReactVRPanelService
    public void createService() {
        LibraryModuleImpl.registerLibraryChangeListener(getApplicationContext());
        LibraryModuleImpl.fetchInitialLibraryAsync(getApplicationContext());
    }

    public static String getDeviceName() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.startsWith(str)) {
            return capitalize(str2);
        }
        return capitalize(str) + " " + str2;
    }

    private static String capitalize(String str) {
        if (str == null || str.length() == 0) {
            return BuildConfig.PROVIDER_SUFFIX;
        }
        if (str.length() == 1) {
            return str.toUpperCase();
        }
        char charAt = str.charAt(0);
        if (Character.isUpperCase(charAt)) {
            return str;
        }
        return Character.toUpperCase(charAt) + str.substring(1);
    }
}
