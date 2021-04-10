package com.oculus.systemutilities;

import android.os.Handler;
import android.util.Log;
import com.google.gson.Gson;
import com.oculus.device.AccessTokenForwarder;
import com.oculus.device.NetworkHeaders;
import com.oculus.device.UserAgentBuilder;
import com.oculus.modules.AuthModuleImpl;
import com.oculus.modules.BatteryManagerModuleImpl;
import com.oculus.modules.CompanionServerModuleImpl;
import com.oculus.modules.ConfigOverrideModuleImpl;
import com.oculus.modules.ControllerManagerModuleImpl;
import com.oculus.modules.ControllerStateModuleImpl;
import com.oculus.modules.DeviceConfigModuleImpl;
import com.oculus.modules.DeviceEnvironmentModuleImpl;
import com.oculus.modules.DeviceUtilitiesModuleImpl;
import com.oculus.modules.DialogsModuleImpl;
import com.oculus.modules.GuardianSettingsModuleImpl;
import com.oculus.modules.LanguageModuleImpl;
import com.oculus.modules.LibraryModuleImpl;
import com.oculus.modules.MediaStoreFileLoaderModuleImpl;
import com.oculus.modules.MicrophoneManagerModuleImpl;
import com.oculus.modules.NightShiftManagerModuleImpl;
import com.oculus.modules.NuxPreferencesFetcherModuleImpl;
import com.oculus.modules.OSUpdaterModuleImpl;
import com.oculus.modules.PanelCookiesModuleImpl;
import com.oculus.modules.PreferencesStoreModuleImpl;
import com.oculus.modules.QuickPromotionModuleImpl;
import com.oculus.modules.SettingsManagerModuleImpl;
import com.oculus.modules.SleepManagerModuleImpl;
import com.oculus.modules.StorageManagerModuleImpl;
import com.oculus.modules.SwapSystemButtonHandednessModuleImpl;
import com.oculus.modules.SystemActivitiesModuleImpl;
import com.oculus.modules.SystemThemeModuleImpl;
import com.oculus.modules.SystemTooltipModuleImpl;
import com.oculus.modules.SystemUtilitiesServiceModuleImpl;
import com.oculus.modules.UserManagerModuleImpl;
import com.oculus.modules.WifiManagerModuleImpl;
import com.oculus.panellib.AppDetails;
import com.oculus.panellib.ReactVRApplication;

public class SystemUtilitiesApplication extends ReactVRApplication {
    public static final String ACTIVITY_NAME_SHELL = "com.oculus.vrshell.MainActivity";
    public static final String FB_SYSTEM_UTILITIES_APP_ID = "1817688451682869";
    public static final String FB_SYSTEM_UTILITIES_APP_NAME = "SystemUtilities";
    public static final String FB_SYSTEM_UTILITIES_APP_SECRET = "1955525aa89aa9559a7e0835e6af9e09";
    public static final String OCULUS_SYSTEM_UTILITIES_APP_ID = "1689311011174858";
    public static final String PACKAGE_NAME_BROWSER = "com.oculus.browser";
    public static final String PACKAGE_NAME_HORIZON = "com.oculus.horizon";
    public static final String PACKAGE_NAME_SHELL = "com.oculus.vrshell";
    public static final String PACKAGE_NAME_SYSTEM_ACTIVITIES = "com.oculus.systemactivities";
    public static final String PACKAGE_NAME_SYSTEM_DRIVER = "com.oculus.systemdriver";
    public static final String PACKAGE_NAME_VRDRIVER = "com.oculus.vrdriver";
    private static final String TAG = SystemUtilitiesApplication.class.getSimpleName();
    private static Gson gson;
    public static final Handler handler = new Handler();
    public static SystemUtilitiesApplication instance;
    private static AccessTokenForwarder mAccessTokenForwarder = new AccessTokenForwarder();
    private static AppDetails mAppDetails;
    private String mUserId;

    /* access modifiers changed from: package-private */
    public static class RequiredMobileVersion {
        private int minimum_version;
        private String package_name;
    }

    public SystemUtilitiesApplication() {
        super("release");
    }

    @Override // com.oculus.panellib.ReactVRApplication
    public void createApplication() {
        instance = this;
        mAppDetails = AppDetails.get(this, getPackageName());
    }

    public void onUserId(String userId) {
        Log.i(TAG, "onUserId");
        if (this.mUserId == null) {
            this.mUserId = userId;
        } else if (!this.mUserId.equals(userId)) {
            throw new RuntimeException(String.format("userId changed from %s to %s", this.mUserId, userId));
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panellib.ReactVRApplication
    public void createModules() {
        NetworkHeaders.init(this, new UserAgentBuilder(), "OculusSystemUtilities");
        String accessToken = ((PanelCookiesModuleImpl) createModule(PanelCookiesModuleImpl.class)).getAccessToken();
        if (accessToken != null) {
            mAccessTokenForwarder.onGetAccessToken(accessToken);
        }
        if (getPackageManager().hasSystemFeature("oculus.software.guardian")) {
            createModule(GuardianSettingsModuleImpl.class);
            createModule(SwapSystemButtonHandednessModuleImpl.class);
        }
        createModule(AuthModuleImpl.class);
        createModule(BatteryManagerModuleImpl.class);
        createModule(CompanionServerModuleImpl.class);
        createModule(ConfigOverrideModuleImpl.class);
        createModule(ControllerManagerModuleImpl.class);
        createModule(ControllerStateModuleImpl.class);
        createModule(DeviceConfigModuleImpl.class);
        createModule(DeviceEnvironmentModuleImpl.class);
        createModule(DeviceUtilitiesModuleImpl.class);
        createModule(DialogsModuleImpl.class);
        createModule(LanguageModuleImpl.class);
        createModule(LibraryModuleImpl.class);
        createModule(MediaStoreFileLoaderModuleImpl.class);
        createModule(MicrophoneManagerModuleImpl.class);
        createModule(NightShiftManagerModuleImpl.class);
        createModule(NuxPreferencesFetcherModuleImpl.class);
        createModule(OSUpdaterModuleImpl.class);
        createModule(PreferencesStoreModuleImpl.class);
        createModule(QuickPromotionModuleImpl.class);
        createModule(SettingsManagerModuleImpl.class);
        createModule(SleepManagerModuleImpl.class);
        createModule(StorageManagerModuleImpl.class);
        createModule(SystemActivitiesModuleImpl.class);
        createModule(SystemThemeModuleImpl.class);
        createModule(SystemTooltipModuleImpl.class);
        createModule(SystemUtilitiesServiceModuleImpl.class);
        createModule(UserManagerModuleImpl.class);
        createModule(WifiManagerModuleImpl.class);
    }

    public static Object getAccessTokenForwarder() {
        return mAccessTokenForwarder;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0096, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0097, code lost:
        r6 = r5;
        r5 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00ab, code lost:
        r5 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ac, code lost:
        r6 = null;
     */
    @com.oculus.systemutilities.UsedByNative
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean getHasRequiredMobileVersions(java.lang.String r14) {
        /*
        // Method dump skipped, instructions count: 177
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.systemutilities.SystemUtilitiesApplication.getHasRequiredMobileVersions(java.lang.String):boolean");
    }

    static class RequiredMobileVersions {
        private RequiredMobileVersion[] required_versions;

        RequiredMobileVersions() {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005a, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005b, code lost:
        r5 = r4;
        r4 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x006f, code lost:
        r4 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void clearApplicationData(android.content.Context r8) {
        /*
        // Method dump skipped, instructions count: 113
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.systemutilities.SystemUtilitiesApplication.clearApplicationData(android.content.Context):void");
    }
}
