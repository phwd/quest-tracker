package com.oculus.vrapi;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExtraRefreshRatesUtil {
    private static final String TAG = "ExtraRefreshRatesUtil";

    public static List<Integer> getActivityExtraRefreshRates(Context context, String packageName, ComponentName componentName) {
        Log.d(TAG, "getActivityExtraRefreshRates");
        List<Integer> extraRefreshRates = new ArrayList<>();
        PackageManager packageMgr = context.getPackageManager();
        if (packageMgr == null) {
            Log.e(TAG, "getActivityExtraRefreshRates with unavailable getPackageManager.");
            return extraRefreshRates;
        }
        String extraRefreshRatesValue = "";
        boolean manifestEntryExists = false;
        if (componentName != null) {
            Log.d(TAG, "componentName non null : " + componentName.flattenToString());
            try {
                ActivityInfo activityInfo = packageMgr.getActivityInfo(componentName, 129);
                if (!(activityInfo == null || activityInfo.metaData == null)) {
                    Log.d(TAG, "Checking activity manifest entry");
                    if (activityInfo.metaData.containsKey("com.oculus.vr.extra_refresh_rates")) {
                        manifestEntryExists = true;
                        extraRefreshRatesValue = activityInfo.metaData.get("com.oculus.vr.extra_refresh_rates").toString();
                    }
                }
            } catch (Exception e) {
                Log.w(TAG, "getActivityExtraRefreshRates failed in activity check with exception: " + e);
                return extraRefreshRates;
            }
        }
        if (manifestEntryExists) {
            Log.d(TAG, "Activity manifest entry exists and = \"" + extraRefreshRatesValue + "\" for " + packageName);
            try {
                Scanner scanner = new Scanner(extraRefreshRatesValue).useDelimiter("\\|");
                while (scanner.hasNextInt()) {
                    extraRefreshRates.add(Integer.valueOf(scanner.nextInt()));
                }
                return extraRefreshRates;
            } catch (Exception e2) {
                Log.w(TAG, "getActivityExtraRefreshRates failed in string scanning with exception: " + e2);
                return extraRefreshRates;
            }
        } else {
            Log.d(TAG, "No Activity manifest entry for " + packageName);
            return extraRefreshRates;
        }
    }
}
