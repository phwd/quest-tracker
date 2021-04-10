package com.oculus.headlesshorizon.notifications.receivers;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import X.C003108z;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.horizon.events.time.EventTimeFormat;
import com.oculus.horizon.notifications.core.NotificationBuilder;
import com.oculus.horizon.notifications.core.NotificationsProperties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.inject.Provider;
import org.json.JSONObject;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_notifications_core_NotificationBuilder_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_events_time_EventTimeFormat_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_social_SocialMethods_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_platformsdk_DeeplinkIntentUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_remotelaunchlogger_RemoteLaunchLogger_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_headlesshorizon_remotelaunch_QueuedRemoteLaunchProcessor_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_fbconnect_FBConnectHelper_ULSEP_BINDING_ID"})
@ApplicationScoped
public class SocialNotificationManager {
    public static final String ACTION_UPDATE_CURRENT_FACEBOOK_ACCOUNT = "com.oculus.fbconnect.UPDATE_CURRENT_FACEBOOK_ACCOUNT";
    public static final String CUSTOM_DATA_ACCEPTER_PHOTO = "accepter_photo";
    public static final String CUSTOM_DATA_EVENT_APPLICATIONS_FOR_VIEWER = "applications_for_viewer2";
    public static final String CUSTOM_DATA_EVENT_DEEPLINK_MESSAGE = "deeplink_message";
    public static final String CUSTOM_DATA_EVENT_ID = "event_id";
    public static final String CUSTOM_DATA_EVENT_SECONDS_UNTIL_START_TIME = "seconds_until_start_time";
    public static final String CUSTOM_DATA_EVENT_START_TIME = "start_time";
    public static final String CUSTOM_DATA_EVENT_TITLE = "title";
    public static final String CUSTOM_DATA_SENDER_PHOTO = "sender_photo";
    public static final String REMOTE_LAUNCH_LOGGING_SOURCE = "queued_remote_launch";
    public static final String TAG = "SocialNotificationManager";
    public static volatile SocialNotificationManager _UL__ULSEP_com_oculus_headlesshorizon_notifications_receivers_SocialNotificationManager_ULSEP_INSTANCE;
    public AnonymousClass0QC _UL_mInjectionContext;
    @UnsafeContextInjection
    @Inject
    @Eager
    public final Context mContext;
    @Inject
    public final Provider<Credentials> mCredentialsProvider;
    @Inject
    @Eager
    public final EventTimeFormat mEventTimeFormat;
    @Inject
    @Eager
    public final NotificationBuilder mNotificationBuilder;
    public final HashMap<String, HashMap<String, Integer>> mPartyInviteNotifications = new HashMap<>();
    public final List<Integer> mSocialNotificationIds = new ArrayList(5);

    public static class EventStartNotificationData {
        public final Uri mDeeplinkUri;
        public final String mMessage;
        public final String mTitle;

        public EventStartNotificationData(String str, String str2, Uri uri) {
            this.mTitle = str;
            this.mMessage = str2;
            this.mDeeplinkUri = uri;
        }
    }

    public static void A00(SocialNotificationManager socialNotificationManager, String str, NotificationsProperties notificationsProperties, JSONObject jSONObject) {
        Intent intent = new Intent("com.oculus.SEE_SOCIAL_NOTIFICATIONS");
        intent.setPackage(str);
        intent.putExtra(NotificationBuilder.EXTRA_NOTIFICATION_ID, notificationsProperties.mFBid);
        intent.putExtra("oculus_notification_uuid", notificationsProperties.mUUID);
        intent.putExtra("oculus_notification_type", notificationsProperties.mType);
        intent.putExtra("extra_custom_data", jSONObject.toString());
        socialNotificationManager.mContext.sendBroadcast(intent);
    }

    @Inject
    public SocialNotificationManager(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(5, r3);
        this.mContext = C003108z.A02(r3);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(r3);
        this.mNotificationBuilder = (NotificationBuilder) AnonymousClass117.A00(14, r3);
        this.mEventTimeFormat = (EventTimeFormat) AnonymousClass117.A00(110, r3);
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/util/HashMap<Ljava/lang/String;Ljava/lang/Integer;>;Ljava/lang/String;)Z */
    public static boolean A01(SocialNotificationManager socialNotificationManager, HashMap hashMap, String str) {
        Number number = (Number) hashMap.get(str);
        if (number == null) {
            return false;
        }
        NotificationBuilder notificationBuilder = socialNotificationManager.mNotificationBuilder;
        ((NotificationManager) AnonymousClass0J2.A03(1, 39, notificationBuilder._UL_mInjectionContext)).cancel("oculus_party_invite", number.intValue());
        socialNotificationManager.mSocialNotificationIds.remove(number);
        return true;
    }
}
