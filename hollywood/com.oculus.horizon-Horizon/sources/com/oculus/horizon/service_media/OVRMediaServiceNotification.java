package com.oculus.horizon.service_media;

import X.AbstractC06640p5;
import X.AnonymousClass006;
import X.AnonymousClass03h;
import X.AnonymousClass04J;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass0Rg;
import X.AnonymousClass117;
import X.C003108z;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat$BigTextStyle;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.common.collect.ImmutableList;
import com.oculus.common.vrshell.Constants;
import com.oculus.common.vrshell.SystemUXRoute;
import com.oculus.horizon.R;
import com.oculus.horizon.notifications.core.NotificationBuilder;
import com.oculus.horizon.service.ExternalPlatformLocal;
import java.util.LinkedList;
import java.util.Queue;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_notifications_core_NotificationBuilder_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_service_ExternalPlatformLocal_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_android_app_NotificationManager_ULSEP_BINDING_ID"})
public class OVRMediaServiceNotification {
    public static final Queue<Integer> CURRENT_CAPTURE_NOTIFS = new LinkedList();
    public static final String KEY_AUI_INTENT_DATA = "intent_data";
    public static final String KEY_AUI_URI_EXTRAS = "uri";
    public static final int MAX_CAPTURE_NOTIFS = 1;
    public static final int MAX_CAPTURE_THUMBNAIL_NOTIFS = 10;
    public static final String TAG = "OVRMediaServiceNotification";
    public AnonymousClass0QC _UL_mInjectionContext;
    public final ImmutableList<String> mBlacklistLivestreamingPackages = ImmutableList.A07("com.samsung.android.hmt.vrsystem");
    public final ImmutableList<String> mBlacklistNowStreamingPackages = ImmutableList.A03();
    @UnsafeContextInjection
    @Inject
    @Eager
    public final Context mContext;
    @Inject
    @Eager
    public final ExternalPlatformLocal mExternalPlatformLocal;
    @Inject
    @Eager
    public final NotificationBuilder mNotificationBuilder;

    @Nullable
    @VisibleForTesting
    public static final Bitmap A00(@Nullable OVRMediaServiceNotification oVRMediaServiceNotification, String str, boolean z) {
        if (str != null) {
            if (!z) {
                return MediaStore.Images.Thumbnails.getThumbnail(oVRMediaServiceNotification.mContext.getContentResolver(), (long) Integer.parseInt(str, 10), 1, null);
            }
            try {
                return MediaStore.Video.Thumbnails.getThumbnail(oVRMediaServiceNotification.mContext.getContentResolver(), (long) Integer.parseInt(str, 10), 1, null);
            } catch (NumberFormatException e) {
                AnonymousClass0NO.A0F(TAG, "could not parse media ID int: %s, isVideo: %b", str, Boolean.valueOf(z), e);
            }
        }
        return null;
    }

    private void A01(int i) {
        while (CURRENT_CAPTURE_NOTIFS.size() > i) {
            NotificationBuilder notificationBuilder = this.mNotificationBuilder;
            ((NotificationManager) AnonymousClass0J2.A03(1, 39, notificationBuilder._UL_mInjectionContext)).cancel(NotificationBuilder.CAMERAROLL_TAG, CURRENT_CAPTURE_NOTIFS.remove().intValue());
        }
    }

    public static void A02(OVRMediaServiceNotification oVRMediaServiceNotification, String str, String str2) {
        NotificationBuilder.Builder builder = new NotificationBuilder.Builder(str, str2);
        builder.suppressSound = true;
        builder.A01();
    }

    @VisibleForTesting
    public static final void A03(OVRMediaServiceNotification oVRMediaServiceNotification, @Nullable String str, String str2) {
        new NotificationBuilder.Builder(str, str2).A01();
    }

    public static void A04(OVRMediaServiceNotification oVRMediaServiceNotification, String str, @Nullable String str2, @Nullable Bitmap bitmap, String str3) {
        String str4;
        boolean A36 = ((AnonymousClass0Rg) AnonymousClass0J2.A03(0, 399, oVRMediaServiceNotification._UL_mInjectionContext)).A36(36310293470511114L);
        int incrementAndGet = oVRMediaServiceNotification.mNotificationBuilder.mNextNotificationId.incrementAndGet();
        if (str3 != null) {
            str4 = AnonymousClass006.A05("/?source=aui_notification_max_view&filepath=", str3);
        } else {
            str4 = "/?com.oculus.vrshell";
        }
        CURRENT_CAPTURE_NOTIFS.add(Integer.valueOf(incrementAndGet));
        if (!A36) {
            oVRMediaServiceNotification.A01(1);
        } else {
            oVRMediaServiceNotification.A01(10);
        }
        Bundle bundle = new Bundle();
        bundle.putString("oculus_notification_type", NotificationBuilder.CAMERAROLL_TAG);
        bundle.putBoolean("vrshell_aui_persist", true);
        bundle.putString("oculus_category", "social");
        Intent intent = new Intent(Constants.ACTION_LAUNCH);
        intent.setPackage("com.oculus.vrshell");
        intent.putExtra("intent_data", SystemUXRoute.MEDIA_PREVIEW.path());
        intent.putExtra("uri", str4);
        PendingIntent broadcast = PendingIntent.getBroadcast(oVRMediaServiceNotification.mContext, incrementAndGet, intent, 0);
        AnonymousClass03h r3 = new AnonymousClass03h(oVRMediaServiceNotification.mContext, null);
        r3.A0E = AnonymousClass03h.A00(str);
        r3.A09.icon = R.drawable.status_icon;
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(oVRMediaServiceNotification.mContext.getResources(), R.drawable.status_icon);
        }
        r3.A04(bitmap);
        r3.A07(str);
        r3.A0D = AnonymousClass03h.A00(str2);
        NotificationCompat$BigTextStyle notificationCompat$BigTextStyle = new NotificationCompat$BigTextStyle();
        notificationCompat$BigTextStyle.bigText(str2);
        r3.A06(notificationCompat$BigTextStyle);
        r3.A05 = AnonymousClass04J.A00(oVRMediaServiceNotification.mContext, R.color.oculus_black_10);
        r3.A02();
        r3.A06 = 1;
        r3.A0A = broadcast;
        r3.A05(bundle);
        r3.A03(-1, NotificationBuilder.ACCEPT_CAMERAROLL, broadcast);
        ((NotificationManager) AnonymousClass0J2.A03(1, 39, oVRMediaServiceNotification._UL_mInjectionContext)).notify(incrementAndGet, r3.A01());
    }

    public final void A05(@Nullable String str) {
        if (str != null && !this.mBlacklistLivestreamingPackages.contains(str)) {
            A02(this, this.mContext.getString(R.string.livestreaming_unsupported_application_message), null);
        }
    }

    @Inject
    public OVRMediaServiceNotification(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
        this.mContext = C003108z.A02(r3);
        this.mNotificationBuilder = (NotificationBuilder) AnonymousClass117.A00(14, r3);
        this.mExternalPlatformLocal = ExternalPlatformLocal._UL__ULSEP_com_oculus_horizon_service_ExternalPlatformLocal_ULSEP_ACCESS_METHOD(r3);
    }
}
