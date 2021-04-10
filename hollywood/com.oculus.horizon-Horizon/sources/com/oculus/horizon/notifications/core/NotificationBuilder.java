package com.oculus.horizon.notifications.core;

import X.AbstractC06640p5;
import X.AnonymousClass03h;
import X.AnonymousClass04J;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass0b4;
import X.AnonymousClass0p2;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.app.NotificationCompat$BigTextStyle;
import com.facebook.common.callercontext.CallerContext;
import com.facebook.common.callercontext.CallerContextable;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.google.common.collect.HashBiMap;
import com.oculus.executors.OculusThreadExecutor;
import com.oculus.headlesshorizon.notifications.receivers.SocialNotificationManager;
import com.oculus.horizon.R;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

@Dependencies({"_UL__ULSEP_com_facebook_imagepipeline_core_ImagePipeline_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_camera_bitmap_CameraBitmapHelper_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_android_app_NotificationManager_ULSEP_BINDING_ID"})
@ApplicationScoped
public class NotificationBuilder implements CallerContextable {
    public static final String ABUSE_CAPTURE_RECORDING = "abuse_capture_recording";
    public static final String ACCEPT_CAMERAROLL = "accept_cameraroll";
    public static final String ACCEPT_CTA = "accept_party_invite";
    public static final String ACCEPT_INVITE_ACTION = "com.oculus.horizon.ACCEPT_PARTY_INVITE";
    public static final String AUI_MAXVIEW_SOURCE = "aui_notification_max_view";
    public static final String AUI_NOTIFICATION_CTA = "com.oculus.horizon.AUI_NOTIFICATION_CTA";
    public static final CallerContext CALLER_CONTEXT = new CallerContext(NotificationBuilder.class, null, null, null, null);
    public static final String CAMERAROLL_TAG = "cameraroll";
    public static final int CANCELLABLE_NOTIFICATION_FIRST_ID = 10000;
    public static final Uri DEFAULT_URI = Uri.parse("oculus.store://link/home");
    public static final String EXTRA_CAPTURE_FILEPATH = "capture_filepath";
    public static final String EXTRA_CUSTOM_DATA = "extra_custom_data";
    public static final String EXTRA_FILEPATH = "filepath";
    public static final String EXTRA_IS_CAPTURE_SUCCESS = "is_capture_success";
    public static final String EXTRA_LARGE_IMAGE_TYPE = "large_image_type";
    public static final String EXTRA_NOTIFICATION_CATEGORY = "oculus_notification_category";
    public static final String EXTRA_NOTIFICATION_CUSTOM_DATA = "oculus_notification_custom_data";
    public static final String EXTRA_NOTIFICATION_FBID = "oculus_notification_fbid";
    public static final String EXTRA_NOTIFICATION_ID = "oculus_notification_id";
    public static final String EXTRA_NOTIFICATION_NDID = "oculus_notification_ndid";
    public static final String EXTRA_NOTIFICATION_TYPE = "oculus_notification_type";
    public static final String EXTRA_NOTIFICATION_UUID = "oculus_notification_uuid";
    public static final String EXTRA_OCULUS_BUTTON_OVERRIDE_URI = "oculus_button_override_uri";
    public static final String EXTRA_PARTY_ID = "party_id";
    public static final String EXTRA_PREVENT_SOUND = "prevent_sound";
    public static final String HORIZON_PACKAGE = "com.oculus.horizon";
    public static final String INTENT_ACTION_CONTENT = "action_content";
    public static final String INTENT_ACTION_DISMISS = "action_dismiss";
    public static final String KEY_OCULUS_CATEGORY = "oculus_category";
    public static final String LARGE_IMAGE_TYPE_DETAIL = "detail";
    public static final int MAX_SOCIAL_NOTIFICATIONS = 5;
    public static final String NOTIFICATION_ACTION_SERVICE = "com.oculus.horizon.notifications.legacy.OnNotificationsActionService";
    public static final int NOTIFICATION_SMALL_ICON = 2131099659;
    public static final String NOTIF_REQUEST_ID = "notif_request_id";
    public static final String OCULUS_CATEGORY_SOCIAL = "social";
    public static final String SOCIAL_NOTIFICATION_ACTION = "com.oculus.SEE_SOCIAL_NOTIFICATIONS";
    public static final String SYSTEM_ACTIVITIES_PACKAGE = "com.oculus.systemactivities";
    public static final String SYSTEM_UTILITIES_PACKAGE = "com.oculus.vrshell.home";
    public static final String TAG = "NotificationBuilder";
    public static final String VRSHELL_PACKAGE = "com.oculus.vrshell";
    public static final int VR_NOTIFICATION_REMOVAL_DELAY_MS = 5000;
    public static final String VR_NOTIFICATION_TAG = "vr_notification";
    public static volatile NotificationBuilder _UL__ULSEP_com_oculus_horizon_notifications_core_NotificationBuilder_ULSEP_INSTANCE;
    public AnonymousClass0QC _UL_mInjectionContext;
    public final OculusThreadExecutor mExecutor;
    public final AtomicInteger mNextNotificationId = new AtomicInteger(CANCELLABLE_NOTIFICATION_FIRST_ID);
    public final AnonymousClass0p2<String, Integer> mPackagesToNotificationIds = new HashBiMap();

    public class Builder {
        @Nullable
        public JSONObject customData = null;
        public int iconId = R.drawable.status_icon;
        @Nullable
        public Uri imageUri = null;
        @Nullable
        public String message;
        @Nullable
        public NotificationsProperties properties = null;
        public boolean suppressSound = false;
        public String title;

        public Builder(String str, @Nullable String str2) {
            this.title = str;
            this.message = str2;
        }

        public final int A00() {
            String str;
            int incrementAndGet = NotificationBuilder.this.mNextNotificationId.incrementAndGet();
            int nextInt = incrementAndGet + ThreadLocalRandom.current().nextInt(100000, 200000);
            PendingIntent pendingIntent = null;
            AnonymousClass03h r15 = new AnonymousClass03h((Context) AnonymousClass0J2.A03(0, 294, NotificationBuilder.this._UL_mInjectionContext), null);
            Bundle bundle = new Bundle();
            Boolean bool = true;
            NotificationsProperties notificationsProperties = this.properties;
            if (notificationsProperties != null) {
                if (notificationsProperties.A00() != null) {
                    Intent intent = new Intent("com.oculus.horizon.AUI_NOTIFICATION_CTA");
                    intent.setPackage("com.oculus.horizon");
                    intent.putExtra(NotificationsProperties.NOTIFICATION_PROPERTIES, this.properties);
                    intent.putExtra("oculus_notification_type", this.properties.mType);
                    JSONObject jSONObject = this.customData;
                    if (jSONObject != null) {
                        intent.putExtra("extra_custom_data", jSONObject.toString());
                    }
                    AnonymousClass0b4 r7 = new AnonymousClass0b4();
                    r7.A04(intent, null);
                    r7.A01 |= 1;
                    pendingIntent = r7.A03((Context) AnonymousClass0J2.A03(0, 294, NotificationBuilder.this._UL_mInjectionContext), nextInt);
                }
                NotificationsProperties notificationsProperties2 = this.properties;
                String str2 = notificationsProperties2.mType;
                if (notificationsProperties2.A01().booleanValue()) {
                    bundle.putBoolean("vrshell_aui_persist", true);
                }
                if (SocialNotificationType.isSocialNotification(str2) || NotificationBuilder.CAMERAROLL_TAG.equals(str2)) {
                    bundle.putString("oculus_category", "social");
                }
                if (SocialNotificationType.PARTY_INVITE_RECEIVED.graphQLName.equals(this.properties.mType) && pendingIntent != null) {
                    r15.A03(R.drawable.ic_call_black_24, NotificationBuilder.ACCEPT_CTA, pendingIntent);
                }
                String str3 = this.properties.mType;
                if (str3 != null) {
                    bundle.putString("oculus_notification_type", str3);
                }
                String str4 = this.properties.mNDID;
                if (str4 != null) {
                    bundle.putString("oculus_notification_ndid", str4);
                }
                String str5 = this.properties.mFBid;
                if (str5 != null) {
                    bundle.putString("oculus_notification_fbid", str5);
                }
                if (this.properties.A02() != null) {
                    bundle.putString(NotificationBuilder.EXTRA_NOTIFICATION_CATEGORY, this.properties.A02());
                }
                if (this.properties.A03() != null) {
                    bundle.putString(NotificationBuilder.EXTRA_OCULUS_BUTTON_OVERRIDE_URI, this.properties.A03());
                }
                try {
                    JSONObject A04 = this.properties.A04();
                    if (A04.has("high_priority")) {
                        bool = Boolean.valueOf(A04.getBoolean("high_priority"));
                    }
                } catch (JSONException unused) {
                    AnonymousClass0NO.A08(NotificationsProperties.TAG, "Notification priority not set");
                }
                notificationsProperties = this.properties;
                str = notificationsProperties.mType;
            } else {
                str = "vr_notification";
            }
            if (notificationsProperties != null && SocialNotificationType.isSaveToVRNotification(notificationsProperties.mType)) {
                bundle.putString(NotificationBuilder.EXTRA_LARGE_IMAGE_TYPE, NotificationBuilder.LARGE_IMAGE_TYPE_DETAIL);
            }
            if (this.suppressSound) {
                bundle.putBoolean(NotificationBuilder.EXTRA_PREVENT_SOUND, true);
            }
            r15.A09.icon = this.iconId;
            r15.A07(this.title);
            r15.A0E = AnonymousClass03h.A00(this.title);
            NotificationCompat$BigTextStyle notificationCompat$BigTextStyle = new NotificationCompat$BigTextStyle();
            notificationCompat$BigTextStyle.bigText(this.message);
            r15.A06(notificationCompat$BigTextStyle);
            r15.A05 = AnonymousClass04J.A00((Context) AnonymousClass0J2.A03(0, 294, NotificationBuilder.this._UL_mInjectionContext), R.color.oculus_black_10);
            r15.A02();
            r15.A06 = bool.booleanValue() ? 1 : 0;
            r15.A05(bundle);
            if (pendingIntent != null) {
                r15.A0A = pendingIntent;
            } else {
                r15.A0A = null;
            }
            String str6 = this.message;
            if (str6 != null) {
                r15.A0D = AnonymousClass03h.A00(str6);
            }
            Uri uri = this.imageUri;
            if (uri == null) {
                ((NotificationManager) AnonymousClass0J2.A03(1, 39, NotificationBuilder.this._UL_mInjectionContext)).notify(str, incrementAndGet, r15.A01());
                return incrementAndGet;
            }
            NotificationsProperties notificationsProperties3 = this.properties;
            if (notificationsProperties3 == null || !SocialNotificationType.isSaveToVRNotification(notificationsProperties3.mType)) {
                NotificationBuilder.A00(NotificationBuilder.this, str, incrementAndGet, r15, uri, true);
                return incrementAndGet;
            }
            NotificationBuilder.A00(NotificationBuilder.this, str, incrementAndGet, r15, uri, false);
            return incrementAndGet;
        }

        public final void A02(NotificationsProperties notificationsProperties) {
            this.properties = notificationsProperties;
            this.customData = notificationsProperties.A04();
            HashSet hashSet = new HashSet(Arrays.asList(NotificationsProperties.IMAGE_URI_KEY, SocialNotificationManager.CUSTOM_DATA_SENDER_PHOTO, SocialNotificationManager.CUSTOM_DATA_ACCEPTER_PHOTO, "friend_photo", "application_photo"));
            Uri uri = null;
            try {
                JSONObject A04 = notificationsProperties.A04();
                Iterator it = hashSet.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    String str = (String) it.next();
                    if (A04.has(str)) {
                        uri = Uri.parse(A04.getString(str));
                        break;
                    }
                }
            } catch (JSONException e) {
                AnonymousClass0NO.A0B(NotificationsProperties.TAG, "Unable to find key for any image uris in custom data", e);
            }
            this.imageUri = uri;
        }

        public final void A01() {
            final int A00 = A00();
            OculusThreadExecutor oculusThreadExecutor = NotificationBuilder.this.mExecutor;
            oculusThreadExecutor.mExecutorService.A8S(new Runnable() {
                /* class com.oculus.horizon.notifications.core.NotificationBuilder.Builder.AnonymousClass1 */

                public final void run() {
                    NotificationBuilder notificationBuilder = NotificationBuilder.this;
                    ((NotificationManager) AnonymousClass0J2.A03(1, 39, notificationBuilder._UL_mInjectionContext)).cancel(A00);
                }
            }, (long) 5000, TimeUnit.MILLISECONDS);
        }
    }

    @Inject
    public NotificationBuilder(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
        this.mExecutor = OculusThreadExecutor.A00();
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x02a3, code lost:
        if (r0 != false) goto L_0x02b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x02a9, code lost:
        if (r3.A05() != false) goto L_0x02b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x02ab, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x02b0, code lost:
        if (X.AnonymousClass1r1.A01(r3) == false) goto L_0x02b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x02b2, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x020e, code lost:
        if (X.AnonymousClass0MP.A02(r3) == false) goto L_0x0210;
     */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x0284  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x02d7  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x01dc  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01e6  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0208 A[Catch:{ Exception -> 0x0230 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A00(com.oculus.horizon.notifications.core.NotificationBuilder r21, final java.lang.String r22, final int r23, final X.AnonymousClass03h r24, android.net.Uri r25, final boolean r26) {
        /*
        // Method dump skipped, instructions count: 760
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.notifications.core.NotificationBuilder.A00(com.oculus.horizon.notifications.core.NotificationBuilder, java.lang.String, int, X.03h, android.net.Uri, boolean):void");
    }
}
