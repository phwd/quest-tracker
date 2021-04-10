package com.facebook.common.build;

import android.os.Build;
import com.facebook.annotations.DoNotInline;
import com.facebook.common.build.config.BuildConfig;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.redex.annotations.ForceInline;
import com.oculus.secure.trustedapp.AllFamilyPackageNames;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class BuildConstants {
    public static final String ALOHA_ABILITY_MANAGER_PACKAGE_NAME = "com.facebook.alohaservices.abilitymanager";
    public static final long ALOHA_APP_ID = 1348564698517390L;
    public static final long ALOHA_CLOUD_GAMING_APP_ID = 227574485003624L;
    public static final long ALOHA_WORK_VC_APP_ID = 1665147590233624L;
    public static final long ANDROIDTV_APP_ID = 437340816620806L;
    public static final String APPLICATION_API_KEY = "882a8490361da98702bf97a021ddc14d";
    public static final String APP_SECRET = "62f8ce9f74b12f84c123cc23437a4a32";
    public static final long ARSTUDIO_PLAYER_APP_ID = 387958744923079L;
    public static final long ARSTUDIO_VR_PLAYER_APP_ID = 1426171990867874L;
    public static final long BELL_APP_ID = 2077218195884634L;
    public static final long BIZAPP_APP_ID = 121876164619130L;
    public static final String BIZAPP_APP_SECRET = "1ab2c5c902faedd339c14b2d58e929dc";
    public static final long BIZAPP_DEV_APP_ID = 310060339087338L;
    public static final String BIZAPP_INTERNAL_URL_SCHEME = BuildConfig.BIZAPP_INTERNAL_URL_SCHEME;
    public static final String BIZAPP_PACKAGE_NAME = "com.facebook.pages.app";
    public static final long BLINK_APP_ID = 411820155957400L;
    public static final String BUILD_SIGNATURE_DEBUG = "Xo8WBi6jzSxKDVR4drqm84yr9iU";
    public static final String BUILD_SIGNATURE_IN_HOUSE = "pLdFLi7Y9fGRBYynu_0msNMhS_w";
    public static final String BUILD_SIGNATURE_PROD = "ijxLJi1yGs1JpL-X1SExmchvork";
    public static final long CONTACTS_APP_ID = 615857748495393L;
    private static final String CPU_FILTERS = BuildConfig.CPU_FILTERS;
    public static final String CRS_URL_SCHEMA = BuildConfig.CRS_URL_SCHEMA;
    public static final String CSMOBILE_URL_SCHEME = BuildConfig.CSMOBILE_URL_SCHEME;
    public static final String DIALTONE_URL_SCHEME = BuildConfig.DIALTONE_URL_SCHEME;
    public static final long FACEBOOK_APP_ID = 350685531728L;
    public static final String FB4A_APP_PACKAGE_NAME = (IS_INTERNAL_BUILD ? AllFamilyPackageNames.WAKIZASHI : AllFamilyPackageNames.KATANA);
    public static final long FB4TV_JIO_APP_ID = 373176396723625L;
    public static final String FBINTERNAL_URL_SCHEME = BuildConfig.FBINTERNAL_URL_SCHEME;
    public static final String FBLITE_APP_PACKAGE_NAME = "com.facebook.lite";
    public static final long FBTV_FOR_PORTAL_APP_ID = 2109768222478229L;
    public static final String FB_SERVICE_URL_SCHEME = BuildConfig.FB_SERVICE_URL_SCHEME;
    public static final String FB_URL_SCHEME = BuildConfig.FB_URL_SCHEME;
    public static final String FB_USER_AGENT = BuildConfig.FB_USER_AGENT;
    public static final long FIRETV_APP_ID = 867777633323150L;
    public static final String GAMES_URL_SCHEME = BuildConfig.GAMES_URL_SCHEME;
    public static final long GEMSTONE_ONLY_APP_ID = 464630460838590L;
    public static final String GOOGLE_MAPS_API_KEY_DEBUG = "0SmP9AZrwrsbrHR2RyVaQ-sqRoukl2MAjk04Ibg";
    public static final String GOOGLE_MAPS_API_KEY_IN_HOUSE = "0e1ruJ7mZbBXS0h1Ffa7wWK4SMcshAyydjcm1qg";
    public static final String GOOGLE_MAPS_API_KEY_PROD = "0e1ruJ7mZbBWslnLnQQ5RPITlJs7QBrg8JYbTyg";
    public static final long GRAPHCURSORSAMPLE_APP_ID = 452901811525500L;
    public static final long GROUPS_APP_ID = 358698234273213L;
    public static final String HTTPS_SCHEME = BuildConfig.HTTPS_SCHEME;
    public static final String HTTP_SCHEME = BuildConfig.HTTP_SCHEME;
    public static final long INSTAGRAM_APP_ID = 567067343352427L;
    public static final String INSTAGRAM_URL_SCHEME = BuildConfig.INSTAGRAM_URL_SCHEME;
    public static final long INTERNAL_CAMERA_APP_ID = 314368878911397L;
    public static final long INTERNAL_SIGNAL_APP_ID = 321296361825371L;
    public static final long IORG_APP_ID = 1429883500624960L;
    private static final boolean IS_ALOHA_WORK_VC_BUILD = BuildConfig.IS_ALOHA_WORK_VC;
    private static final boolean IS_ASAN_BUILD = BuildConfig.IS_ASAN_BUILD;
    private static final boolean IS_CREATOR_STUDIO_BUILD = BuildConfig.IS_CREATOR_STUDIO;
    private static final boolean IS_DEBUG_BUILD = BuildConfig.DEBUG;
    private static final boolean IS_E2E = BuildConfig.IS_E2E;
    private static final boolean IS_EXOPACKAGE_BUILD = BuildConfig.IS_EXOPACKAGE;
    private static final boolean IS_INTERNAL_BUILD = BuildConfig.IS_INTERNAL_BUILD;
    private static final boolean IS_LIBCXX_BUILD = BuildConfig.IS_LIBCXX_BUILD;
    private static final boolean IS_LOCAL_APP_BUILD = BuildConfig.IS_LOCAL_APP;
    private static final boolean IS_LOG_RELEASE = BuildConfig.IS_LOG_RELEASE;
    private static final boolean IS_MESSENGER_BUILD = BuildConfig.IS_MESSENGER;
    private static final boolean IS_MESSENGER_OR_WORKCHAT_BUILD = (BuildConfig.IS_MESSENGER || BuildConfig.IS_WORK);
    private static final boolean IS_NEW_SIGNATURE_BUILD = BuildConfig.IS_NEW_SIGNATURE_BUILD;
    private static final boolean IS_PERF_TEST = BuildConfig.IS_PERF_TEST;
    private static final boolean IS_QUICKORDER_BUILD = BuildConfig.IS_QUICKORDER_BUILD;
    public static final boolean IS_STAGING_BUILD = BuildConfig.IS_STAGING_BUILD;
    private static final boolean IS_TALK_BUILD = BuildConfig.IS_TALK_BUILD;
    private static final boolean IS_TALK_KINDLE_BUILD = BuildConfig.IS_TALK_KINDLE;
    private static final boolean IS_TALK_PLAYSTORE_TEST_BUILD = BuildConfig.IS_TALK_PLAYSTORE_TEST_BUILD;
    private static final boolean IS_UBSAN_BUILD = BuildConfig.IS_UBSAN_BUILD;
    private static final boolean IS_WORK_BUILD = BuildConfig.IS_WORK;
    private static final boolean IS_XRAY_BUILD = BuildConfig.IS_XRAY_BUILD;
    public static final long LASSO_APP_ID = 644155909280383L;
    public static final long MAPS_APP_ID = 1448326565427347L;
    public static final long MESSENGER_APP_ID = 256002347743983L;
    public static final String MESSENGER_APP_PACKAGE_NAME;
    public static final long MESSENGER_DEV_APP_ID = 181425161904154L;
    public static final long MESSENGER_IN_HOUSE_APP_ID = 105910932827969L;
    public static final String MESSENGER_LITE_APP_PACKAGE_NAME = "com.facebook.mlite";
    public static final String MESSENGER_SAMETASK_URL_SCHEME = BuildConfig.MESSENGER_SAMETASK_URL_SCHEME;
    public static final String MESSENGER_SECURE_URL_SCHEME = BuildConfig.MESSENGER_SECURE_URL_SCHEME;
    public static final String MESSENGER_URL_SCHEME = BuildConfig.MESSENGER_URL_SCHEME;
    public static final String MLITE_SECURE_URL_SCHEME = BuildConfig.MLITE_SECURE_URL_SCHEME;
    public static final String MLITE_URL_SCHEME = BuildConfig.MLITE_URL_SCHEME;
    public static final String MOBILE_HOME_PACKAGE_NAME = "com.facebook.mobilehome";
    private static final int MODULAR_EXOPACKAGE_FLAG = 8;
    private static final int NATIVE_LIBRARY_EXOPACKAGE_FLAG = 2;
    public static final long OCULUS_APP_ID = 155327495133707L;
    public static final long PARTIES_APP_ID = 1355626171143913L;
    public static final String PORTAL_URL_SCHEME = BuildConfig.PORTAL_URL_SCHEME;
    public static final long QUERYEXECUTORSAMPLE_APP_ID = 1379841549003325L;
    private static final int RESOURCES_EXOPACKAGE_FLAG = 4;
    public static final String[] SECONDARY_PROCESSES_WITH_MULTIDEX = {"aura", "browser", "videoplayer", "adnw", "quicksilver", "wifi", "papaya", "remotecodec"};
    public static final String SOCAL_APP_PACKAGE_NAME = "com.facebook.Socal";
    public static final long TALK_APP_ID = 1104941186305379L;
    public static final String TALK_APP_PACKAGE_NAME;
    public static final long TALK_KINDLE_APP_ID = 628551730674460L;
    public static final String TALK_URL_SCHEME = BuildConfig.TALK_URL_SCHEME;
    public static final long WORKSPEED_APP_ID = 470242087083497L;
    public static final long WORK_APP_ID = 1442593136019356L;
    public static final String WORK_APP_PACKAGE_NAME = (IS_INTERNAL_BUILD ? AllFamilyPackageNames.WORKDEV : AllFamilyPackageNames.WORK);
    public static final long WORK_CHAT_APP_ID = 312713275593566L;
    public static final String WORK_CHAT_APP_PACKAGE_NAME = "com.facebook.workchat";

    static {
        String str;
        String str2;
        if (IS_WORK_BUILD) {
            str = "com.facebook.workchat";
        } else {
            str = IS_ALOHA_WORK_VC_BUILD ? "com.facebook.alohaworkvc" : AllFamilyPackageNames.MESSENGER;
        }
        MESSENGER_APP_PACKAGE_NAME = str;
        if (IS_INTERNAL_BUILD) {
            str2 = AllFamilyPackageNames.MESSENGER_KIDS_DEV;
        } else {
            str2 = AllFamilyPackageNames.MESSENGER_KIDS;
        }
        TALK_APP_PACKAGE_NAME = str2;
    }

    public static final String[] getLocales() {
        return BuildConfig.LOCALES;
    }

    public static final boolean areDownloadableStringsEnabled() {
        return BuildConfig.IS_DOWNLOADABLE_STRINGS_ENABLED;
    }

    public static final boolean areAppModulesEnabled() {
        return BuildConfig.ARE_APP_MODULES_ENABLED;
    }

    public static final boolean areStringAssetsEnabled() {
        return BuildConfig.IS_STRING_ASSETS_ENABLED;
    }

    public static final boolean hasCompiledJS() {
        return BuildConfig.HAS_COMPILED_JS;
    }

    public static final boolean hasHermesBytecodeBundle() {
        return BuildConfig.HAS_HERMES_BYTECODE_BUNDLE;
    }

    public static final boolean hasSplitResources() {
        return BuildConfig.HAS_SPLIT_ARSC;
    }

    public static final boolean hasBundledLayouts() {
        return BuildConfig.HAS_BUNDLED_LAYOUTS;
    }

    @ForceInline
    public static final boolean isDebugBuild() {
        return IS_DEBUG_BUILD;
    }

    public static final boolean isPerfTestBuild() {
        return IS_PERF_TEST;
    }

    public static final boolean isE2EBuild() {
        return IS_E2E;
    }

    public static final boolean isLogReleaseBuild() {
        return IS_LOG_RELEASE;
    }

    public static final boolean isInternalBuild() {
        return IS_INTERNAL_BUILD;
    }

    public static final boolean isExopackageBuild() {
        return IS_EXOPACKAGE_BUILD;
    }

    public static final boolean isNativeExopackageEnabled() {
        return (BuildConfig.EXOPACKAGE_FLAGS & 2) != 0;
    }

    public static final boolean isResourcesExopackageEnabled() {
        return (BuildConfig.EXOPACKAGE_FLAGS & 4) != 0;
    }

    public static final boolean isModularExopackageEnabled() {
        return (BuildConfig.EXOPACKAGE_FLAGS & 8) != 0;
    }

    @Deprecated
    public static final boolean isWorkBuild() {
        return IS_WORK_BUILD;
    }

    public static final boolean isAlohaWorkVcBuild() {
        return IS_ALOHA_WORK_VC_BUILD;
    }

    public static final boolean isMessengerBuild() {
        return IS_MESSENGER_BUILD;
    }

    public static final boolean isMessengerOrWorkchatBuild() {
        return IS_MESSENGER_OR_WORKCHAT_BUILD;
    }

    public static final boolean isLocalAppBuild() {
        return IS_LOCAL_APP_BUILD;
    }

    public static final boolean isNewSignatureBuild() {
        return IS_NEW_SIGNATURE_BUILD;
    }

    public static final boolean isTalkKindleBuild() {
        return IS_TALK_KINDLE_BUILD;
    }

    public static final boolean isIsTalkPlaystoreTestBuild() {
        return IS_TALK_PLAYSTORE_TEST_BUILD;
    }

    public static final boolean isTalkBuild() {
        return IS_TALK_BUILD;
    }

    public static final String getCpuFilters() {
        return CPU_FILTERS;
    }

    public static final String getMessengerUrlScheme() {
        return MESSENGER_URL_SCHEME;
    }

    public static final String getMessengerSecureUrlScheme() {
        return MESSENGER_SECURE_URL_SCHEME;
    }

    public static final String getMessengerSametaskUrlScheme() {
        return MESSENGER_SAMETASK_URL_SCHEME;
    }

    public static final String getFb4aAppPackageName() {
        return FB4A_APP_PACKAGE_NAME;
    }

    public static final String getWorkAppPackageName() {
        return WORK_APP_PACKAGE_NAME;
    }

    public static final String getBizAppPackageName() {
        return "com.facebook.pages.app";
    }

    public static final String getMainFbAppPackageName() {
        if (isWorkBuild()) {
            return WORK_APP_PACKAGE_NAME;
        }
        return FB4A_APP_PACKAGE_NAME;
    }

    public static final String getFb4aShortName() {
        if (isInternalBuild()) {
            return "wakizashi";
        }
        return "katana";
    }

    public static final String getUpgradeURL() {
        if (isInternalBuild()) {
            return "https://m.intern.facebook.com/intern/mobile_builds/";
        }
        return "https://market.android.com/details?id=com.facebook.katana";
    }

    public static final String getComponentScriptBundle() {
        return BuildConfig.COMPONENT_SCRIPT_BUNDLE;
    }

    public static final String getComponentScriptBundleURL() {
        return BuildConfig.COMPONENT_SCRIPT_BUNDLE_URL;
    }

    public static final boolean isCompressedOreoBuild() {
        return Build.VERSION.SDK_INT >= 26 && (BuildConfig.IS_COMPRESSED_OREO_BUILD || BuildConfig.IS_VARIABLE_VMSAFEMODE_BUILD);
    }

    public static final boolean isIGDexCompressionEnabled() {
        return BuildConfig.IS_INSTAGRAM_DEX_COMPRESSION_ENABLED;
    }

    public static final boolean isIGCompressedICSBuild() {
        return isIGDexCompressionEnabled() && BuildConfig.IS_COMPRESSED_ICS_BUILD;
    }

    public static final boolean isIGICSControlBuild() {
        return BuildConfig.IS_INSTAGRAM_ICSCONTROL_BUILD;
    }

    public static final boolean isPyTorchQueryCodegenEnabled() {
        return BuildConfig.IS_PYTORCH_QUERY_CODEGEN_ENABLED;
    }

    public static final boolean isExperimentSnapshotBuild() {
        return BuildConfig.IS_EXPERIMENT_SNAPSHOT_BUILD;
    }

    public static final boolean isMegatestingBuild() {
        return BuildConfig.IS_MEGATESTING_BUILD;
    }

    @DoNotInline
    public static final int getAPKVersionCode() {
        return BuildConfig.VERSION_CODE;
    }

    @DoNotInline
    public static final int getBuildID() {
        return BuildConfig.BUILD_ID;
    }

    @DoNotInline
    public static final boolean isRelabeled() {
        return false;
    }

    public static final boolean is64BitBuild() {
        String cpuFilters = getCpuFilters();
        if (cpuFilters != null) {
            return cpuFilters.contains("64");
        }
        return false;
    }

    public static final boolean isAsanBuild() {
        return IS_ASAN_BUILD;
    }

    public static final boolean isUbsanBuild() {
        return IS_UBSAN_BUILD;
    }

    public static final boolean isQuickOrderBuild() {
        return IS_QUICKORDER_BUILD;
    }

    public static final boolean isXrayBuild() {
        return IS_XRAY_BUILD;
    }

    public static final String getCxxStdLibName() {
        return IS_LIBCXX_BUILD ? "c++_shared" : "gnustl_shared";
    }

    public static String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    public static final boolean isStagingBuild() {
        return IS_STAGING_BUILD;
    }

    public static final boolean isCreatorStudioBuild() {
        return IS_CREATOR_STUDIO_BUILD;
    }
}
