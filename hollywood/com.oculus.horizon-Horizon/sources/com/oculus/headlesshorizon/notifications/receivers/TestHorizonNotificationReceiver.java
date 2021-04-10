package com.oculus.headlesshorizon.notifications.receivers;

import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QB;
import X.AnonymousClass0QC;
import X.AnonymousClass0b9;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.oculus.horizon.notifications.core.NotificationBuilder;
import com.oculus.horizon.notifications.core.NotificationsProperties;
import com.oculus.security.basecomponent.OculusPublicBroadcastReceiver;
import org.json.JSONException;
import org.json.JSONObject;

public class TestHorizonNotificationReceiver extends OculusPublicBroadcastReceiver implements AnonymousClass0QB {
    public static final String ACTION = "com.oculus.horizon.TEST_HORIZON_NOTIFICATION";
    public static final String CUSTOM_DATA = "custom_data";
    public static final String DEEPLINK = "deeplink";
    public static final String DESTINATION = "destination";
    public static final String MESSAGE = "message";
    public static final String TAG = "TestHorizonNotificationReceiver";
    public static final String TITLE = "title";
    public static final String TYPE = "notification_type";
    public AnonymousClass0QC _UL_mInjectionContext;

    public TestHorizonNotificationReceiver() {
        super(ACTION);
    }

    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
    public final void onReceive(Context context, Intent intent, AnonymousClass0b9 r16) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        this._UL_mInjectionContext = new AnonymousClass0QC(1, AnonymousClass0J2.get(context));
        if (intent != null && !TextUtils.isEmpty(intent.getAction()) && ACTION.equals(intent.getAction())) {
            if (!TextUtils.isEmpty(intent.getStringExtra("title"))) {
                str = intent.getStringExtra("title");
            } else {
                str = "Fake Title";
            }
            if (!TextUtils.isEmpty(intent.getStringExtra("message"))) {
                str2 = intent.getStringExtra("message");
            } else {
                str2 = "Fake Message";
            }
            if (!TextUtils.isEmpty(intent.getStringExtra("notification_type"))) {
                str3 = intent.getStringExtra("notification_type");
            } else {
                str3 = "oculus_test_notification";
            }
            if (!TextUtils.isEmpty(intent.getStringExtra("deeplink"))) {
                str4 = Uri.parse(intent.getStringExtra("deeplink")).toString();
            } else {
                str4 = "systemux://store";
            }
            String str6 = "{}";
            if (!TextUtils.isEmpty(intent.getStringExtra("destination"))) {
                intent.getStringExtra("destination");
                str5 = intent.getStringExtra("destination");
            } else {
                str5 = str6;
            }
            if (!TextUtils.isEmpty(intent.getStringExtra(CUSTOM_DATA))) {
                try {
                    str6 = new JSONObject(intent.getStringExtra(CUSTOM_DATA)).toString();
                } catch (JSONException e) {
                    AnonymousClass0NO.A0B(TAG, "Failure to parse custom data json", e);
                }
            }
            NotificationsProperties notificationsProperties = new NotificationsProperties("", "abc12345", str3, str6, "afakendidhere", str4, str5);
            intent.getExtras();
            NotificationBuilder.Builder builder = new NotificationBuilder.Builder(str, str2);
            builder.A02(notificationsProperties);
            builder.A00();
        }
    }
}
