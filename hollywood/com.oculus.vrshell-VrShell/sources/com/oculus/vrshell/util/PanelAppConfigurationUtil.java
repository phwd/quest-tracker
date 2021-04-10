package com.oculus.vrshell.util;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.packageutils.PackageHelpers;
import com.oculus.vrshell.config.PanelAppConfiguration;
import com.oculus.vrshell.config.PanelAppConfigurationConstants;
import com.oculus.vrshell.panels.AndroidPanelApp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PanelAppConfigurationUtil {
    private static final String TAG = LoggingUtil.tag(PanelAppConfigurationUtil.class);
    private static final List<String> needsActivityTokenList = Arrays.asList("com.oculus.tv/com.oculus.tv.PanelService", "com.oculus.vrshell.desktop/com.oculus.vrshell.desktop.PanelService");

    public static boolean needsActivityToken(String str, String str2) {
        String str3 = str + "/" + str2;
        if (needsActivityTokenList.contains(str3)) {
            Log.i(TAG, "Sending Android activity token to component " + str3);
            return true;
        }
        Log.i(TAG, "Not sending Android activity token to component " + str3);
        return false;
    }

    public static PanelAppConfiguration buildPanelAppConfiguration(String str, String str2, Map<String, PanelAppConfiguration> map, PackageManager packageManager) throws PackageManager.NameNotFoundException, IllegalArgumentException {
        PackageInfo packageInfo = packageManager.getPackageInfo(str, 132);
        if (str2.isEmpty()) {
            str2 = tryResolvingServiceName(str, packageInfo, packageManager);
        }
        if (!str2.isEmpty()) {
            String str3 = str + "/" + str2;
            String valueOf = String.valueOf(packageInfo.versionCode);
            PanelAppConfiguration panelAppConfiguration = map.get(str3);
            if (panelAppConfiguration == null || panelAppConfiguration.lastUpdateTime != packageInfo.lastUpdateTime || panelAppConfiguration.getPackageUid() != packageInfo.applicationInfo.uid || !valueOf.equals(panelAppConfiguration.getVersionCode())) {
                if (panelAppConfiguration != null) {
                    Log.d(TAG, String.format("Refreshing APK info for %s; UID or version code changed", str3));
                }
                PanelAppConfiguration.Builder withLastUpdateTime = new PanelAppConfiguration.Builder().withPackageName(str).withPackageUid(packageInfo.applicationInfo.uid).withServiceName(str2).withLastUpdateTime(packageInfo.lastUpdateTime);
                withLastUpdateTime.withNeedsActivityToken(needsActivityToken(str, str2));
                withLastUpdateTime.withVersionName(packageInfo.versionName == null ? "" : packageInfo.versionName);
                withLastUpdateTime.withVersionCode(valueOf);
                Intent intent = new Intent(PackageHelpers.INTENT_ACTION_PANEL);
                intent.setComponent(new ComponentName(str, str2));
                List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 128);
                if (queryIntentServices != null && queryIntentServices.size() == 1) {
                    withLastUpdateTime.withValidService(true);
                    Bundle bundle = queryIntentServices.get(0).serviceInfo.metaData;
                    if (bundle != null) {
                        addSurfaceInfo(withLastUpdateTime, bundle.getString("com.oculus.vrshell.required_panel_surfaces"));
                        withLastUpdateTime.withAllowMultiInstance(bundle.getBoolean("com.oculus.vrshell.supports_multi_instance"));
                        withLastUpdateTime.withAllowImeComposition(bundle.getBoolean("com.oculus.vrshell.supports_ime_composition"));
                        withLastUpdateTime.withSupportsLayers(bundle.getBoolean("com.oculus.vrshell.supports_layers"));
                        withLastUpdateTime.withSupportsMultiApp(bundle.getBoolean("com.oculus.vrshell.supports_multiapp"));
                        withLastUpdateTime.withClearOnAppLaunch(bundle.getBoolean("com.oculus.vrshell.clear_on_app_launch"));
                        withLastUpdateTime.withInitialCooperativeState(bundle.getString("com.oculus.vrshell.initial_cooperative_state"));
                        int i = bundle.getInt("com.oculus.vrshell.layer_definition", -1);
                        if (i != -1) {
                            parseLayerOptionResource(packageManager, str, withLastUpdateTime, i);
                        }
                    }
                    map.put(str3, withLastUpdateTime.build());
                } else if (queryIntentServices == null || queryIntentServices.size() == 0) {
                    throw new IllegalArgumentException("Specified component is not a valid panel service launch intent - " + str3);
                } else {
                    throw new IllegalArgumentException("Specified component resolved to multiple service entries - " + str3);
                }
            }
            return map.get(str3);
        }
        throw new IllegalArgumentException("No default service found in application. Package " + str + " is not a valid Panel Service.");
    }

    private static String tryResolvingServiceName(String str, PackageInfo packageInfo, PackageManager packageManager) {
        if (packageInfo.services == null) {
            return "";
        }
        ServiceInfo[] serviceInfoArr = packageInfo.services;
        String str2 = "";
        boolean z = false;
        for (ServiceInfo serviceInfo : serviceInfoArr) {
            if (serviceInfo.metaData != null && serviceInfo.metaData.getBoolean("com.oculus.vrshell.is_default_service")) {
                if (!z) {
                    str2 = serviceInfo.name;
                    z = true;
                } else {
                    throw new IllegalArgumentException("Multiple default services found in package " + packageInfo.packageName + " at " + str2 + " and " + serviceInfo.name);
                }
            }
        }
        if (!z && packageInfo.services.length > 0) {
            Intent intent = new Intent(PackageHelpers.INTENT_ACTION_PANEL);
            intent.setPackage(str);
            List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
            if (queryIntentServices != null && queryIntentServices.size() == 1) {
                return queryIntentServices.get(0).serviceInfo.name;
            }
        }
        return str2;
    }

    private static void parseLayerOptionResource(PackageManager packageManager, String str, PanelAppConfiguration.Builder builder, int i) {
        try {
            Resources resourcesForApplication = packageManager.getResourcesForApplication(str);
            TypedArray obtainTypedArray = resourcesForApplication.obtainTypedArray(i);
            try {
                String[] strArr = new String[obtainTypedArray.length()];
                int[] iArr = new int[obtainTypedArray.length()];
                boolean z = false;
                for (int i2 = 0; i2 < obtainTypedArray.length(); i2++) {
                    int resourceId = obtainTypedArray.getResourceId(i2, -1);
                    if (resourceId != -1) {
                        String[] stringArray = resourcesForApplication.getStringArray(resourceId);
                        boolean z2 = false;
                        boolean z3 = z;
                        for (String str2 : stringArray) {
                            if (str2.startsWith("name:")) {
                                String substring = str2.substring(5);
                                if (AndroidPanelApp.MAIN_LAYER.equals(substring)) {
                                    z3 = true;
                                }
                                strArr[i2] = substring;
                                z2 = true;
                            } else {
                                iArr[i2] = PanelAppConfigurationConstants.ConfigOptions.getValueOfOption(str2) | iArr[i2];
                            }
                        }
                        if (z2) {
                            z = z3;
                        } else {
                            throw new Exception("There is no name for this surface.");
                        }
                    }
                }
                if (z) {
                    builder.withSurfaceNames(strArr);
                    builder.withLayerOptions(iArr);
                    return;
                }
                Log.e(TAG, "Incorrect xml format, no main layer found.");
            } finally {
                obtainTypedArray.recycle();
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed parsing " + e);
        }
    }

    private static void addSurfaceInfo(PanelAppConfiguration.Builder builder, String str) {
        if (str != null) {
            String[] split = str.split(",");
            builder.withSurfaceNames(split);
            for (String str2 : split) {
                if (!str2.trim().matches("^[a-zA-Z][a-zA-Z0-9]*$")) {
                    Log.e(TAG, "Invalid surface name specified in com.oculus.vrshell.required_panel_surfaces - " + str2);
                    builder.withValidService(false);
                    builder.withSurfaceNames(null);
                    return;
                }
            }
        }
    }
}
