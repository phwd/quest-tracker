package com.oculus.common.packageutils;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import java.util.List;

public final class PackageHelpers {
    public static final String INTENT_ACTION_PANEL = "com.oculus.vrshell.SHELL_MAIN";
    private static final String INTENT_CATEGORY_VR = "com.oculus.intent.category.VR";
    public static final String METADATA_PWA_WEB_MANIFEST_URL_KEY = "web_manifest_url";
    private static final String METADATA_VR_MODE_DUAL = "dual";
    private static final String METADATA_VR_MODE_KEY = "com.samsung.android.vr.application.mode";
    private static final String METADATA_VR_MODE_VR_ONLY = "vr_only";
    public static final String PACKAGE_NAME_BROWSER = "com.oculus.browser";
    public static final String PACKAGE_NAME_HORIZON = "com.oculus.horizon";
    public static final String PACKAGE_NAME_OCMS = "com.oculus.ocms";
    public static final String PACKAGE_NAME_SHELL = "com.oculus.vrshell";
    public static final String PACKAGE_NAME_SHELL_ENV = "com.oculus.shellenv";
    public static final String PACKAGE_NAME_SHELL_HOME = "com.oculus.vrshell.home";
    public static final String PACKAGE_NAME_SOCIAL_PLATFORM = "com.oculus.socialplatform";
    public static final String PACKAGE_NAME_SYSTEM_DRIVER = "com.oculus.systemdriver";
    public static final String PACKAGE_NAME_SYSTEM_UTILITIES = "com.oculus.systemutilities";
    public static final String PACKAGE_NAME_SYSTEM_UX = "com.oculus.systemux";
    public static final String PACKAGE_NAME_TV = "com.oculus.tv";
    public static final String PACKAGE_NAME_XRSTREAMING_CLIENT = "com.oculus.xrstreamingclient";
    private static String TAG = LoggingUtil.tag(PackageHelpers.class);

    private PackageHelpers() {
    }

    public static Bundle getPackageMetaDataBundle(PackageManager packageManager, String str) {
        try {
            return packageManager.getPackageInfo(str, 128).applicationInfo.metaData;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static String getPackageMetaDataString(PackageManager packageManager, String str, String str2) {
        Bundle packageMetaDataBundle = getPackageMetaDataBundle(packageManager, str);
        if (packageMetaDataBundle != null) {
            return packageMetaDataBundle.getString(str2);
        }
        return null;
    }

    private static boolean hasOpenXRAppCategories(PackageManager packageManager, String str) {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory(INTENT_CATEGORY_VR);
        intent.setPackage(str);
        return !packageManager.queryIntentActivities(intent, 0).isEmpty();
    }

    private static boolean hasLegacyVrAppMetaData(ApplicationInfo applicationInfo) {
        if (applicationInfo == null || applicationInfo.metaData == null) {
            return false;
        }
        String string = applicationInfo.metaData.getString(METADATA_VR_MODE_KEY);
        if (METADATA_VR_MODE_VR_ONLY.equals(string) || METADATA_VR_MODE_DUAL.equals(string)) {
            return true;
        }
        return false;
    }

    public static boolean isVrApp(PackageManager packageManager, String str) throws PackageManager.NameNotFoundException {
        return hasLegacyVrAppMetaData(packageManager.getPackageInfo(str, 128).applicationInfo) || hasOpenXRAppCategories(packageManager, str);
    }

    public static boolean isVrApp(PackageManager packageManager, ApplicationInfo applicationInfo) {
        return hasLegacyVrAppMetaData(applicationInfo) || hasOpenXRAppCategories(packageManager, applicationInfo.packageName);
    }

    public static boolean isShellApp(String str) {
        return "com.oculus.vrshell".equals(str) || "com.oculus.shellenv".equals(str);
    }

    @Nullable
    public static List<ResolveInfo> queryPanelServices(PackageManager packageManager, String str) {
        Intent intent = new Intent(INTENT_ACTION_PANEL);
        intent.setPackage(str);
        return packageManager.queryIntentServices(intent, 64);
    }
}
