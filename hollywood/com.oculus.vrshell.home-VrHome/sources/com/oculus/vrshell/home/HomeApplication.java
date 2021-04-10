package com.oculus.vrshell.home;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.oculus.device.AccessTokenForwarder;
import com.oculus.device.NetworkHeaders;
import com.oculus.device.UserAgentBuilder;
import com.oculus.modules.AbuseReportModule;
import com.oculus.modules.AccountLinkingModule;
import com.oculus.modules.AppLaunchModuleImpl;
import com.oculus.modules.AuthModuleImpl;
import com.oculus.modules.BatteryStateModuleImpl;
import com.oculus.modules.CompanionServerModuleImpl;
import com.oculus.modules.ConfigOverrideModuleImpl;
import com.oculus.modules.ConnectivityStateModuleImpl;
import com.oculus.modules.ControllerStateModuleImpl;
import com.oculus.modules.DeviceConfigModuleImpl;
import com.oculus.modules.DeviceEnvironmentModuleImpl;
import com.oculus.modules.DeviceUnlockStatusModuleImpl;
import com.oculus.modules.DialogsModuleImpl;
import com.oculus.modules.FirstTimeNUXSettingsModuleImpl;
import com.oculus.modules.FirstTimeNuxOtaModuleImpl;
import com.oculus.modules.HandTrackingSettingsModuleImpl;
import com.oculus.modules.HomeJSBundleModuleImpl;
import com.oculus.modules.InputDeviceModuleImpl;
import com.oculus.modules.LanguageModuleImpl;
import com.oculus.modules.LibraryModuleImpl;
import com.oculus.modules.LivestreamingCommentsModule;
import com.oculus.modules.LivestreamingModuleImpl;
import com.oculus.modules.MobileConfigModuleImpl;
import com.oculus.modules.OVRPlatformGenericDeeplinkModule;
import com.oculus.modules.PanelCookiesModuleImpl;
import com.oculus.modules.PartyCallsManagerModuleImpl;
import com.oculus.modules.QuickExperimentsModuleImpl;
import com.oculus.modules.QuickPromotionModuleImpl;
import com.oculus.modules.RemoteLivestreamingErrorModuleImpl;
import com.oculus.modules.ServiceModuleImpl;
import com.oculus.modules.SettingsManagerModuleImpl;
import com.oculus.modules.SharedMemoryModuleImpl;
import com.oculus.modules.SocialModule;
import com.oculus.modules.SystemActivitiesModuleImpl;
import com.oculus.modules.SystemPropertiesModuleImpl;
import com.oculus.modules.SystemTooltipModuleImpl;
import com.oculus.modules.ToasterModuleImpl;
import com.oculus.modules.UserManagerModuleImpl;
import com.oculus.modules.UserModuleImpl;
import com.oculus.modules.WebTaskLaunchModuleImpl;
import com.oculus.modules.WifiManagerModuleImpl;
import com.oculus.panellib.AppDetails;
import com.oculus.panellib.CrashReporting;
import com.oculus.panellib.ReactVRApplication;
import com.oculus.platform.CoreAppAccessTokenFetcherModule;
import com.oculus.util.constants.OculusConstants;
import com.oculus.vrshell.home.module.GateKeepers;
import com.oculus.vrshell.home.module.HomeGraphQLPrefetchModule;
import com.oculus.vrshell.home.module.NuxPreferencesModule;
import com.oculus.vrshell.home.module.OverlaysModule;
import com.oculus.vrshell.home.module.ShutdownIntentModule;
import com.oculus.vrshell.home.module.audio.MicRecorder;
import com.oculus.vrshell.home.module.audio.SpeakerPlayer;
import java.io.File;
import java.util.Locale;

public class HomeApplication extends ReactVRApplication {
    public static final String ACTIVITY_NAME_SHELL = "com.oculus.vrshell.MainActivity";
    public static final String FB_HOME_APP_ID = "285486028533521";
    public static final String FB_HOME_APP_NAME = "ShellHome";
    public static final String FB_HOME_APP_SECRET = "e7d2f844007ab5b1caedd7cc78c99ec2";
    public static final String OCULUS_HOME_APP_ID = "1481000308606657";
    private static final String TAG = HomeApplication.class.getSimpleName();
    private static Gson gson;
    public static final Handler handler = new Handler();
    public static HomeApplication instance;
    private static AccessTokenForwarder mAccessTokenForwarder = new AccessTokenForwarder();
    private static AppDetails mAppDetails;
    private String mAccessToken;
    private String mUserId;

    public HomeApplication() {
        super("release");
    }

    @Override // com.oculus.panellib.ReactVRApplication
    public void onCreate() {
        ((HomeGraphQLPrefetchModule) createModule(HomeGraphQLPrefetchModule.class)).setProcessName(getProcessName(this));
        HomeEventLogger.init(this);
        super.onCreate();
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
            CrashReporting.setUserId(userId);
        } else if (!this.mUserId.equals(userId)) {
            throw new RuntimeException(String.format("userId changed from %s to %s", this.mUserId, userId));
        }
    }

    public void onAccessToken(String accessToken) {
        Log.i(TAG, "onAccessToken");
        if (this.mAccessToken == null) {
            this.mAccessToken = accessToken;
        } else if (!this.mAccessToken.equals(accessToken)) {
            throw new RuntimeException("accessToken changed");
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panellib.ReactVRApplication
    public void createModules() {
        boolean isOculusQuest;
        boolean isOculusHollywood = true;
        NetworkHeaders.init(this, new UserAgentBuilder(), OculusConstants.HOME_SHELL_USER_AGENT_APP_NAME);
        String accessToken = ((PanelCookiesModuleImpl) createModule(PanelCookiesModuleImpl.class)).getAccessToken();
        if (accessToken != null) {
            mAccessTokenForwarder.onGetAccessToken(accessToken);
        }
        boolean isOculusDevice = Build.MANUFACTURER.equals("Oculus");
        if (!isOculusDevice || !Build.DEVICE.equals("monterey")) {
            isOculusQuest = false;
        } else {
            isOculusQuest = true;
        }
        if (!isOculusDevice || !Build.DEVICE.equals("hollywood")) {
            isOculusHollywood = false;
        }
        if (isOculusQuest || isOculusHollywood) {
            createModule(WifiManagerModuleImpl.class);
            createModule(ConnectivityStateModuleImpl.class);
        }
        if (isOculusDevice) {
            createModule(AuthModuleImpl.class);
            createModule(BatteryStateModuleImpl.class);
            createModule(CompanionServerModuleImpl.class);
            createModule(ControllerStateModuleImpl.class);
            createModule(DeviceUnlockStatusModuleImpl.class);
            createModule(FirstTimeNuxOtaModuleImpl.class);
            createModule(FirstTimeNUXSettingsModuleImpl.class);
            createModule(HandTrackingSettingsModuleImpl.class);
            createModule(HomeJSBundleModuleImpl.class);
            createModule(LanguageModuleImpl.class);
            createModule(SettingsManagerModuleImpl.class);
            createModule(SystemPropertiesModuleImpl.class);
            createModule(UserModuleImpl.class);
            createModule(UserManagerModuleImpl.class);
            createModule(WebTaskLaunchModuleImpl.class);
        }
        createModule(AppLaunchModuleImpl.class);
        createModule(ConfigOverrideModuleImpl.class);
        createModule(DeviceConfigModuleImpl.class);
        createModule(DeviceEnvironmentModuleImpl.class);
        createModule(DialogsModuleImpl.class);
        createModule(InputDeviceModuleImpl.class);
        createModule(LibraryModuleImpl.class);
        createModule(MobileConfigModuleImpl.class);
        createModule(PartyCallsManagerModuleImpl.class);
        createModule(QuickExperimentsModuleImpl.class);
        createModule(QuickPromotionModuleImpl.class);
        createModule(ServiceModuleImpl.class);
        createModule(SystemActivitiesModuleImpl.class);
        createModule(SystemTooltipModuleImpl.class);
        createModule(ToasterModuleImpl.class);
        createModule(CoreAppAccessTokenFetcherModule.class);
        createModule(AbuseReportModule.class);
        createModule(AccountLinkingModule.class);
        createModule(GateKeepers.class);
        createModule(LivestreamingModuleImpl.class);
        createModule(LivestreamingCommentsModule.class);
        createModule(NuxPreferencesModule.class);
        createModule(OverlaysModule.class);
        createModule(OVRPlatformGenericDeeplinkModule.class);
        createModule(RemoteLivestreamingErrorModuleImpl.class);
        createModule(SharedMemoryModuleImpl.class);
        createModule(ShutdownIntentModule.class);
        createModule(SocialModule.class);
        createModule(MicRecorder.class);
        createModule(SpeakerPlayer.class);
    }

    public static Object getAccessTokenForwarder() {
        return mAccessTokenForwarder;
    }

    @UsedByNative
    public static boolean getHasRequiredMobileVersions(String requiredMobileVersionsJson) {
        RequiredMobileVersions requiredMobileVersions = null;
        try {
            if (gson == null) {
                gson = new Gson();
            }
            requiredMobileVersions = (RequiredMobileVersions) gson.fromJson(requiredMobileVersionsJson, RequiredMobileVersions.class);
        } catch (JsonSyntaxException e) {
            Log.e(TAG, "Error parsing required mobile versions: invalid structure.", e);
        } catch (JsonParseException e2) {
            Log.e(TAG, "Error parsing required mobile versions: invalid json.", e2);
        }
        if (!(requiredMobileVersions == null || requiredMobileVersions.required_versions == null)) {
            RequiredMobileVersion[] requiredMobileVersionArr = requiredMobileVersions.required_versions;
            for (RequiredMobileVersion requiredMobileVersion : requiredMobileVersionArr) {
                if (requiredMobileVersion.package_name != null) {
                    AppDetails details = AppDetails.get(instance, requiredMobileVersion.package_name);
                    if (details.versionCode < requiredMobileVersion.minimum_version) {
                        Log.i(TAG, String.format(Locale.US, "Requires version %d of %s but found version %d", Integer.valueOf(requiredMobileVersion.minimum_version), requiredMobileVersion.package_name, Integer.valueOf(details.versionCode)));
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public static class RequiredMobileVersion {
        private String display_name;
        private String download_uri;
        private int minimum_version;
        private String package_name;

        RequiredMobileVersion() {
        }
    }

    static class RequiredMobileVersions {
        private RequiredMobileVersion[] required_versions;

        RequiredMobileVersions() {
        }
    }

    public static void clearApplicationData(Context context) {
        String[] children;
        File asyncStorageDirectory = new File(context.getFilesDir() + "/asynclocal");
        if (asyncStorageDirectory.exists() && asyncStorageDirectory.isDirectory()) {
            for (String str : asyncStorageDirectory.list()) {
                new File(asyncStorageDirectory, str).delete();
            }
        }
    }
}
