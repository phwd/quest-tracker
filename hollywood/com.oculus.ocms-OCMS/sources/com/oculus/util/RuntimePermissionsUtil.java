package com.oculus.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class RuntimePermissionsUtil {
    public static void launchAppDetailsSettings(Context context) {
        Intent settingsLaunchIntent = getSettingsLaunchIntent(context);
        settingsLaunchIntent.addFlags(268435456);
        context.startActivity(settingsLaunchIntent);
    }

    private static Intent getSettingsLaunchIntent(Context context) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        return intent;
    }
}
