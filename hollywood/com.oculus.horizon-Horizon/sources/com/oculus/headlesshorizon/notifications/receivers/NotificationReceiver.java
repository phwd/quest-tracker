package com.oculus.headlesshorizon.notifications.receivers;

import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import com.facebook.ultralight.Inject;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.horizon.push.FbnsPushNotificationHandler;
import javax.inject.Provider;

public class NotificationReceiver extends FbnsPushNotificationHandler {
    public static final String CUSTOM_DATA_ACCEPTER_USER_ID = "accepter_id";
    public static final String CUSTOM_DATA_APP_GROUP_ID = "app_grouping_id";
    public static final String CUSTOM_DATA_APP_ID = "app_id";
    public static final String CUSTOM_DATA_NOTIF_REQUEST_ID = "notif_request_id";
    public static final String CUSTOM_DATA_PARTY_ACTION = "action";
    public static final String CUSTOM_DATA_PARTY_ALIAS = "user_alias";
    public static final String CUSTOM_DATA_PARTY_ID = "party_id";
    public static final String CUSTOM_DATA_PARTY_NAME = "user_name";
    public static final String CUSTOM_DATA_PARTY_USER_ID = "user_id";
    public static final String CUSTOM_DATA_PARTY_WAS_USER_KICKED = "was_user_kicked";
    public static final String CUSTOM_DATA_PUSH_TOKEN = "push_token";
    public static final String CUSTOM_DATA_SENDER_USER_ID = "sender_id";
    public static final String CUSTOM_DATA_UPDATED_USER = "user_with_updated_status";
    public static final String PARTY_ACTION_INVITE = "Invite";
    public static final String PARTY_ACTION_JOIN = "Join";
    public static final String PARTY_ACTION_LEAVE = "Leave";
    public static final String TAG = "NotificationReceiver";
    public static final String TYPE_PUSH_REACHABILITY = "oc_push_reachability_check";
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    public Provider<Credentials> mCredentialsProvider;

    @Override // com.oculus.horizon.push.FbnsPushNotificationHandler
    public final void onCreate() {
        super.onCreate();
        AnonymousClass0J2 r2 = AnonymousClass0J2.get(this);
        this._UL_mInjectionContext = new AnonymousClass0QC(5, r2);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(r2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:210:0x061e, code lost:
        if (new com.oculus.headlesshorizon.notifications.receivers.SocialNotificationManager.EventStartNotificationData(r18, r3, new android.net.Uri.Builder().scheme(com.oculus.headlesshorizon.notifications.receivers.NotificationsDeepLinkUriCreationUtils.SCHEME).authority("link").appendPath("events").appendQueryParameter(com.oculus.horizon.deeplinking.contract.EventsContract.EXTRA_EVENT_DATA, r11.toString()).appendQueryParameter("referrer", com.oculus.headlesshorizon.notifications.receivers.NotificationsLogger.REFERRERS.NOTIF_EVENT_START).build()).mDeeplinkUri != null) goto L_0x0643;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x01ca, code lost:
        if (r9.equals(com.oculus.headlesshorizon.notifications.receivers.NotificationReceiver.PARTY_ACTION_LEAVE) != false) goto L_0x01cc;
     */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x019a A[Catch:{ JSONException -> 0x01db }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x01b0 A[Catch:{ JSONException -> 0x01db }] */
    @Override // com.oculus.horizon.push.FbnsPushNotificationHandler
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A04(java.lang.String r28, long r29, @javax.annotation.Nullable java.lang.String r31, @javax.annotation.Nullable java.lang.String r32, @javax.annotation.Nullable java.lang.String r33, @javax.annotation.Nullable java.lang.String r34, @javax.annotation.Nullable java.lang.String r35, @javax.annotation.Nullable java.lang.String r36, @javax.annotation.Nullable java.lang.String r37, @javax.annotation.Nullable java.lang.String r38) {
        /*
        // Method dump skipped, instructions count: 1845
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.headlesshorizon.notifications.receivers.NotificationReceiver.A04(java.lang.String, long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }
}
