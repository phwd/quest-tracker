package com.facebook.common.build;

import com.facebook.common.build.config.BuildConfig;
import com.oculus.secure.trustedapp.AllFamilyPackageNames;

public class BuildConstants {
    public static final String BIZAPP_INTERNAL_URL_SCHEME = BuildConfig.BIZAPP_INTERNAL_URL_SCHEME;
    private static final String CPU_FILTERS = BuildConfig.CPU_FILTERS;
    public static final String CRS_URL_SCHEMA = BuildConfig.CRS_URL_SCHEMA;
    public static final String CSMOBILE_URL_SCHEME = BuildConfig.CSMOBILE_URL_SCHEME;
    public static final String DIALTONE_URL_SCHEME = BuildConfig.DIALTONE_URL_SCHEME;
    public static final String FB4A_APP_PACKAGE_NAME = (IS_INTERNAL_BUILD ? AllFamilyPackageNames.WAKIZASHI : AllFamilyPackageNames.KATANA);
    public static final String FBINTERNAL_URL_SCHEME = BuildConfig.FBINTERNAL_URL_SCHEME;
    public static final String FB_SERVICE_URL_SCHEME = BuildConfig.FB_SERVICE_URL_SCHEME;
    public static final String FB_URL_SCHEME = BuildConfig.FB_URL_SCHEME;
    public static final String FB_USER_AGENT = BuildConfig.FB_USER_AGENT;
    public static final String GAMES_URL_SCHEME = BuildConfig.GAMES_URL_SCHEME;
    public static final String HTTPS_SCHEME = BuildConfig.HTTPS_SCHEME;
    public static final String HTTP_SCHEME = BuildConfig.HTTP_SCHEME;
    public static final String INSTAGRAM_URL_SCHEME = BuildConfig.INSTAGRAM_URL_SCHEME;
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
    public static final String MESSENGER_APP_PACKAGE_NAME;
    public static final String MESSENGER_SAMETASK_URL_SCHEME = BuildConfig.MESSENGER_SAMETASK_URL_SCHEME;
    public static final String MESSENGER_SECURE_URL_SCHEME = BuildConfig.MESSENGER_SECURE_URL_SCHEME;
    public static final String MESSENGER_URL_SCHEME = BuildConfig.MESSENGER_URL_SCHEME;
    public static final String MLITE_SECURE_URL_SCHEME = BuildConfig.MLITE_SECURE_URL_SCHEME;
    public static final String MLITE_URL_SCHEME = BuildConfig.MLITE_URL_SCHEME;
    public static final String PORTAL_URL_SCHEME = BuildConfig.PORTAL_URL_SCHEME;
    public static final String[] SECONDARY_PROCESSES_WITH_MULTIDEX = {"aura", "browser", "videoplayer", "adnw", "quicksilver", "wifi", "papaya", "remotecodec"};
    public static final String TALK_APP_PACKAGE_NAME;
    public static final String TALK_URL_SCHEME = BuildConfig.TALK_URL_SCHEME;
    public static final String WORK_APP_PACKAGE_NAME = (IS_INTERNAL_BUILD ? AllFamilyPackageNames.WORKDEV : AllFamilyPackageNames.WORK);

    static {
        String str;
        String str2;
        if (IS_WORK_BUILD) {
            str = AllFamilyPackageNames.WORK_CHAT;
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

    public static final boolean isDebugBuild() {
        return IS_DEBUG_BUILD;
    }

    public static final boolean isInternalBuild() {
        return IS_INTERNAL_BUILD;
    }

    public static final String getCpuFilters() {
        return CPU_FILTERS;
    }

    public static final int getAPKVersionCode() {
        return BuildConfig.VERSION_CODE;
    }

    public static final int getBuildID() {
        return BuildConfig.BUILD_ID;
    }

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

    public static String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }
}
