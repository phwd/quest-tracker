package com.oculus.horizon.notifications.core;

import X.AnonymousClass03m;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import javax.annotation.Nullable;
import org.json.JSONObject;

public class NotificationsIntentCreationUtils {
    public static final String EXTRA_DEEPLINK_MESSAGE = "deeplink_message";
    public static final String EXTRA_INTENT_DATA = "intent_data";
    public static final String EXTRA_PACKAGE_NAME = "package_name";
    public static final String EXTRA_URI = "uri";
    public static final String LAUNCH_APPLICATION_ACTION = "com.oculus.systemux.download.LAUNCH_APPLICATION";
    public static final String LAUNCH_CTA = "launch";
    public static final String SYSTEMUX_PACKAGE = "com.oculus.systemux";
    public static final String TAG = "NotificationsIntentCreationUtils";

    @Nullable
    public static PendingIntent addLaunchPackageIntent(Context context, int i, AnonymousClass03m r6, @Nullable JSONObject jSONObject) {
        if (jSONObject != null) {
            String optString = jSONObject.optString("package_name", "");
            String optString2 = jSONObject.optString("deeplink_message", "");
            if (!TextUtils.isEmpty(optString)) {
                PendingIntent broadcast = PendingIntent.getBroadcast(context, i, createLaunchPackageIntent(optString, optString2), 268435456);
                r6.A02(-1, LAUNCH_CTA, broadcast);
                return broadcast;
            }
        }
        return null;
    }

    public static Intent createLaunchPackageIntent(String str, String str2) {
        Intent intent = new Intent();
        intent.setAction(LAUNCH_APPLICATION_ACTION);
        intent.setPackage("com.oculus.systemux");
        intent.putExtra("intent_data", str);
        intent.putExtra("uri", str2);
        return intent;
    }
}
