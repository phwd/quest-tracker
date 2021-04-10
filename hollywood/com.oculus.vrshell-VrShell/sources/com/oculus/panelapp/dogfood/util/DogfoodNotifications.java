package com.oculus.panelapp.dogfood.util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.util.Pair;
import com.oculus.common.httpclient.GraphQLRequest;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.notifications.R;
import com.oculus.notifications.NotificationConstants;
import com.oculus.notifications.NotificationSender;
import com.oculus.notifications.NotificationsDisplayDuration;
import com.oculus.os.PreferencesManager;
import com.oculus.panelapp.dogfood.GraphQLRequester;
import java.util.Arrays;
import java.util.Locale;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class DogfoodNotifications {
    private static final String ASSIGNMENTS_UPDATE_NOTIFICATION_ANALYTICS_ID = "oculus_dogfood_assignments_update_notification";
    private static final String DOGFOOD_NOTIF_TAG = "dogfood";
    private static final String OTA_UPDATE_NOTIFICATION_ANALYTICS_ID = "oculus_dogfood_ota_update_notification";
    private static final String PANIC_PRESS_NOTIFICATION_ANALYTICS_ID = "oculus_dogfood_panic_press_notification";
    private static final String PREF_DOGFOOD_PANEL_LAST_ASSIGNMENTS_HASH = "dogfood_panel_last_assignments_hash";
    private static final String PREF_DOGFOOD_PANEL_LAST_OTA_BUILD = "dogfood_panel_last_ota_build";
    private static String TAG = LoggingUtil.tag(DogfoodNotifications.class);
    private static final PreferencesManager preferencesManager = new PreferencesManager();

    public static void checkForDogfoodUpdates(final Context context, OkHttpClient okHttpClient, String str) {
        Log.d(TAG, "Checking for updated Dogfood Notifications.");
        GraphQLRequest.get(okHttpClient, str, String.format(GraphQLRequester.GRAPHQL_ASSIGNMENTS, Build.SERIAL, Build.VERSION.INCREMENTAL), new GraphQLRequest.GraphQLResponse() {
            /* class com.oculus.panelapp.dogfood.util.DogfoodNotifications.AnonymousClass1 */

            @Override // com.oculus.common.httpclient.GraphQLRequest.GraphQLResponse
            public void onFailure(String str) {
                DogfoodNotifications.selectDogfoodNotificationForSession(0, context);
            }

            @Override // com.oculus.common.httpclient.GraphQLRequest.GraphQLResponse
            public void onSuccess(JSONObject jSONObject) {
                int i = 0;
                try {
                    JSONArray jSONArray = jSONObject.getJSONArray("assignments");
                    if (jSONArray.length() > 0) {
                        String[] strArr = new String[jSONArray.length()];
                        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                            strArr[i2] = jSONArray.getJSONObject(i2).getString("oid");
                        }
                        i = Arrays.hashCode(strArr);
                    }
                } catch (JSONException e) {
                    Log.e(DogfoodNotifications.TAG, "Received Invalid JSON response: " + e.getMessage());
                }
                DogfoodNotifications.selectDogfoodNotificationForSession(i, context);
            }
        });
    }

    /* access modifiers changed from: private */
    public static void selectDogfoodNotificationForSession(int i, Context context) {
        Log.d(TAG, "Selecting session based Dogfood Notification.");
        if (checkForNewAssignments(i)) {
            sendAssignmentsUpdatedNotification(context);
        } else if (checkForNewBuild()) {
            sendOTAUpdatedNotification(context);
        } else {
            sendPanicPressNotification(context);
        }
    }

    private static boolean checkForNewAssignments(int i) {
        if (i == 0) {
            return false;
        }
        Pair integer = preferencesManager.getInteger(PREF_DOGFOOD_PANEL_LAST_ASSIGNMENTS_HASH);
        if (!((Boolean) integer.first).booleanValue() || ((Integer) integer.second).intValue() == i) {
            return false;
        }
        preferencesManager.set(PREF_DOGFOOD_PANEL_LAST_ASSIGNMENTS_HASH, i);
        return true;
    }

    private static boolean checkForNewBuild() {
        Pair string = preferencesManager.getString(PREF_DOGFOOD_PANEL_LAST_OTA_BUILD);
        if (!((Boolean) string.first).booleanValue()) {
            return false;
        }
        if (Build.DISPLAY.equals((String) string.second)) {
            return false;
        }
        preferencesManager.set(PREF_DOGFOOD_PANEL_LAST_OTA_BUILD, Build.DISPLAY);
        return true;
    }

    private static PendingIntent constructDogfoodPanelLaunchIntent(Context context) {
        Intent intent = new Intent("com.oculus.vrshell.intent.action.LAUNCH");
        intent.setPackage("com.oculus.vrshell");
        intent.putExtra("intent_data", Uri.parse("com.oculus.vrshell/com.oculus.panelapp.dogfood.DogfoodPanelService"));
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    private static void sendDogfoodNotification(Context context, NotificationSender.Builder builder) {
        builder.setDisplayDuration(NotificationsDisplayDuration.LONG).setIconImageType(NotificationConstants.LargeImageType.ICON).setSuppressSound(true).send(context);
    }

    private static void sendOTAUpdatedNotification(Context context) {
        PendingIntent constructDogfoodPanelLaunchIntent = constructDogfoodPanelLaunchIntent(context);
        sendDogfoodNotification(context, NotificationSender.build(OTA_UPDATE_NOTIFICATION_ANALYTICS_ID, String.format(Locale.ROOT, "Build Updated! Check Dogfood Panel for Details on build %s.", Build.DISPLAY), null, DOGFOOD_NOTIF_TAG, -1, R.drawable.ic_notif_alert).setIsPersistent(true).setContentIntent(constructDogfoodPanelLaunchIntent).setAcceptIntent(constructDogfoodPanelLaunchIntent));
    }

    private static void sendAssignmentsUpdatedNotification(Context context) {
        PendingIntent constructDogfoodPanelLaunchIntent = constructDogfoodPanelLaunchIntent(context);
        sendDogfoodNotification(context, NotificationSender.build(ASSIGNMENTS_UPDATE_NOTIFICATION_ANALYTICS_ID, "New Dogfood Assignments! Check the Dogfood Panel for new assignments.", null, DOGFOOD_NOTIF_TAG, -1, R.drawable.ic_notif_alert).setIsPersistent(true).setContentIntent(constructDogfoodPanelLaunchIntent).setAcceptIntent(constructDogfoodPanelLaunchIntent));
    }

    private static void sendPanicPressNotification(Context context) {
        sendDogfoodNotification(context, NotificationSender.build(PANIC_PRESS_NOTIFICATION_ANALYTICS_ID, "[FB-INTERNAL Only] Press the Oculus button 5 times to submit a bug report.", null, DOGFOOD_NOTIF_TAG, -1, R.drawable.ic_notif_alert).setIsPersistent(false));
    }
}
