package com.oculus.horizon.push;

import X.AbstractIntentServiceC02350aK;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QB;
import X.AnonymousClass0QC;
import android.content.pm.PackageInfo;
import android.os.Build;
import com.oculus.common.serial.BuildSerialUtil;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.headlesshorizon.notifications.receivers.NotificationReceiver;
import com.oculus.horizon.api.ApiRequestManager;
import com.oculus.horizon.api.push.PushToken;
import com.oculus.horizon.api.push.PushTokenResponse;
import com.oculus.horizon.api.push.RegisterPushTokenRequest;
import com.oculus.http.core.base.ApiCallback;
import com.oculus.http.core.base.ApiError;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import com.oculus.util.device.DeviceUtils;
import javax.annotation.Nullable;

public abstract class FbnsPushNotificationHandler extends AbstractIntentServiceC02350aK implements AnonymousClass0QB {
    public static final String EVENT_PUSH_DELETED = "oculus_mobile_push_deleted";
    public static final String EVENT_PUSH_RECEIVED = "oculus_mobile_push_received";
    public static final String EVENT_PUSH_REGISTERED = "oculus_mobile_push_registered";
    public static final String EVENT_PUSH_REGISTER_FAILURE = "oculus_mobile_push_register_failure";
    public static final String EVENT_PUSH_REGISTER_SERVER_FAILURE = "oculus_mobile_push_register_server_failure";
    public static final String EVENT_PUSH_REGISTER_SERVER_SUCCESS = "oculus_mobile_push_register_server_success";
    public static final String EXTRA_ERROR = "error";
    public static final String EXTRA_PUSH_DELETE_COUNT = "count";
    public static final String EXTRA_PUSH_FBID = "fbid";
    public static final String EXTRA_PUSH_NDID = "ndid";
    public static final String EXTRA_PUSH_TOKEN_ID = "push_token_id";
    public static final String EXTRA_PUSH_TYPE = "type";
    public static final String EXTRA_PUSH_UUID = "uuid";
    public static final String MSG_CUSTOM_DATA_FIELD = "extra_data";
    public static final String MSG_DATA_FIELD = "data";
    public static final String MSG_MESSAGE_FIELD = "message";
    public static final String MSG_NOTIFICATION_CATEGORY = "category";
    public static final String MSG_NOTIFICATION_DEEPLINK = "deeplink_uri";
    public static final String MSG_NOTIFICATION_DESTINATION = "destination";
    public static final String MSG_NOTIFICATION_FBID = "persisted_id";
    public static final String MSG_NOTIFICATION_FEED_KEY = "is_in_feed";
    public static final String MSG_NOTIFICATION_HIGH_PRI = "high_priority";
    public static final String MSG_NOTIFICATION_NDID = "ndid";
    public static final String MSG_NOTIFICATION_UUID = "PushNotifID";
    public static final String MSG_PARAMS_FIELD = "params";
    public static final String MSG_TITLE_FIELD = "title";
    public static final String MSG_TYPE_FIELD = "type";
    public static final String TAG = "FbnsPushNotificationHandler";
    public AnonymousClass0QC _UL_mInjectionContext;

    public abstract void A04(String str, long j, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9);

    @Override // X.AbstractIntentServiceC02350aK
    public final void A00(int i) {
        Event A22 = ((EventManager) AnonymousClass0J2.A03(3, 242, this._UL_mInjectionContext)).A22(EVENT_PUSH_DELETED);
        A22.A13("count", i);
        A22.A5L();
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00c5 A[Catch:{ JSONException -> 0x0196 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00cf A[Catch:{ JSONException -> 0x0196 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00d9 A[Catch:{ JSONException -> 0x0196 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00de A[Catch:{ JSONException -> 0x0196 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00e1 A[Catch:{ JSONException -> 0x0196 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00e4 A[Catch:{ JSONException -> 0x0196 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00eb A[Catch:{ JSONException -> 0x0197 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00f0 A[Catch:{ JSONException -> 0x0197 }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00f7 A[Catch:{ JSONException -> 0x0198 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00fc A[Catch:{ JSONException -> 0x0198 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0103 A[Catch:{ JSONException -> 0x0199 }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x010d A[Catch:{ JSONException -> 0x0199 }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x011f A[Catch:{ JSONException -> 0x0199 }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x012b A[Catch:{ JSONException -> 0x0199 }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0134 A[Catch:{ JSONException -> 0x0199 }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0137 A[Catch:{ JSONException -> 0x0199 }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0139 A[Catch:{ JSONException -> 0x0199 }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x013b A[Catch:{ JSONException -> 0x0199 }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0144 A[Catch:{ JSONException -> 0x0154 }] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x014e A[Catch:{ JSONException -> 0x0154 }] */
    @Override // X.AbstractIntentServiceC02350aK
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A01(android.content.Intent r28) {
        /*
        // Method dump skipped, instructions count: 536
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.push.FbnsPushNotificationHandler.A01(android.content.Intent):void");
    }

    @Override // X.AbstractIntentServiceC02350aK
    public final void A02(String str) {
        if (!(this instanceof NotificationReceiver)) {
            ((IErrorReporter) AnonymousClass0J2.A03(2, 428, this._UL_mInjectionContext)).A96(TAG, str);
            Event A22 = ((EventManager) AnonymousClass0J2.A03(3, 242, this._UL_mInjectionContext)).A22(EVENT_PUSH_REGISTER_FAILURE);
            A22.A15("error", str);
            A22.A5L();
            return;
        }
        AnonymousClass0NO.A0E(NotificationReceiver.TAG, "FBNS Handler onRegistrationError %s", str);
    }

    @Override // X.AbstractIntentServiceC02350aK
    public final void A03(final String str, boolean z) {
        ((EventManager) AnonymousClass0J2.A03(3, 242, this._UL_mInjectionContext)).A22(EVENT_PUSH_REGISTERED).A5L();
        ((ApiRequestManager) AnonymousClass0J2.A03(0, 407, this._UL_mInjectionContext)).post(new RegisterPushTokenRequest(str, DeviceUtils.A03(getApplicationContext()), Build.VERSION.RELEASE, ((PackageInfo) AnonymousClass0J2.A04(529, this._UL_mInjectionContext)).versionName, BuildSerialUtil.A00()), new ApiCallback<PushTokenResponse>() {
            /* class com.oculus.horizon.push.FbnsPushNotificationHandler.AnonymousClass1 */

            @Override // com.oculus.http.core.base.ApiCallback
            public final void onError(ApiError apiError) {
                ((IErrorReporter) AnonymousClass0J2.A03(2, 428, FbnsPushNotificationHandler.this._UL_mInjectionContext)).A96(FbnsPushNotificationHandler.TAG, apiError.getMessage());
                Event A22 = ((EventManager) AnonymousClass0J2.A03(3, 242, FbnsPushNotificationHandler.this._UL_mInjectionContext)).A22(FbnsPushNotificationHandler.EVENT_PUSH_REGISTER_SERVER_FAILURE);
                A22.A15("error", apiError.getMessage());
                A22.A5L();
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            @Override // com.oculus.http.core.base.ApiCallback
            public final void onResponse(PushTokenResponse pushTokenResponse) {
                PushToken.IndividualToken individualToken;
                FbnsPushNotificationHandler.this.getApplicationContext().getSharedPreferences(PushTokenSharedPreferencesHelper.SHARED_PREFS_NAME, 0).edit().putString(PushTokenSharedPreferencesHelper.PUSH_TOKEN_KEY_NAME, str).apply();
                PushToken pushToken = pushTokenResponse.pushToken;
                if (pushToken != null && (individualToken = pushToken.token) != null && individualToken.id > 0) {
                    Event A22 = ((EventManager) AnonymousClass0J2.A03(3, 242, FbnsPushNotificationHandler.this._UL_mInjectionContext)).A22(FbnsPushNotificationHandler.EVENT_PUSH_REGISTER_SERVER_SUCCESS);
                    A22.A14("token_id", pushToken.token.id);
                    A22.A5L();
                    FbnsPushNotificationHandler.this.getApplicationContext().getSharedPreferences(PushTokenSharedPreferencesHelper.SHARED_PREFS_NAME, 0).edit().putLong("push_token_id", individualToken.id).apply();
                }
            }
        });
    }

    public void onCreate() {
        super.onCreate();
        this._UL_mInjectionContext = new AnonymousClass0QC(4, AnonymousClass0J2.get(this));
    }
}
