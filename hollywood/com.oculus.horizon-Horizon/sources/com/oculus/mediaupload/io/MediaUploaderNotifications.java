package com.oculus.mediaupload.io;

import X.AnonymousClass03h;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.NotificationCompat$InboxStyle;
import com.facebook.ultralight.Dependencies;
import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.R;
import javax.annotation.Nullable;

@Dependencies({})
public class MediaUploaderNotifications {
    public static final String ACTION_ACCEPT = "accept";
    public static final String CATEGORY_SOCIAL = "social";
    public static final String EXTRA_DISMISS_FLAG = "hide_dismiss";
    public static final String EXTRA_DOWNLOAD_IN_PROGRESS = "download_in_progress";
    public static final String EXTRA_IS_DOWNLOAD = "is_download";
    public static final String EXTRA_OCULUS_CATEGORY = "oculus_category";
    public static final String EXTRA_PERSIST_FLAG = "vrshell_aui_persist";
    public static final String EXTRA_PROGRESS_BAR_RATIO = "progress_bar_ratio";
    public static final String EXTRA_SHOW_TITLE_IN_TOAST = "title_as_description_in_toast";
    public static final int FB_UPLOAD_NOTIFICATION_ID = 500000;
    public static final ImmutableMap<IconType, Integer> ICONS;
    public static final int OCULUS_UPLOAD_NOTIFICATION_ID = 500500;
    public static final int REQUEST_FACEBOOK_GAMING_LOGIN_PENDING_INTENT_ID = 500700;
    public static final int REQUEST_FB_GAMING_LOGIN_NOTIFICATION_ID = 500600;
    public static int mMessengerUploadNotificationId = 500800;

    public enum IconType {
        OCULUS,
        FACEBOOK,
        MESSENGER
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ea  */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.oculus.mediaupload.io.MediaUploaderNotifications.MediaUploaderNotification A01(android.content.Context r12, com.oculus.mediaupload.model.NotificationRequest r13, com.oculus.mediaupload.model.MediaUploaderBatchInfo r14, @javax.annotation.Nullable java.lang.String r15) {
        /*
        // Method dump skipped, instructions count: 426
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.mediaupload.io.MediaUploaderNotifications.A01(android.content.Context, com.oculus.mediaupload.model.NotificationRequest, com.oculus.mediaupload.model.MediaUploaderBatchInfo, java.lang.String):com.oculus.mediaupload.io.MediaUploaderNotifications$MediaUploaderNotification");
    }

    /* renamed from: com.oculus.mediaupload.io.MediaUploaderNotifications$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$mediaupload$model$NotificationRequest$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(30:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|(3:29|30|32)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(32:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0036 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0048 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0052 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x005c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0066 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0070 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x007a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x008e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002d */
        static {
            /*
            // Method dump skipped, instructions count: 153
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.mediaupload.io.MediaUploaderNotifications.AnonymousClass1.<clinit>():void");
        }
    }

    public static class MediaUploaderNotification {
        public final AnonymousClass03h builder;
        public final int notificationId;

        public MediaUploaderNotification(AnonymousClass03h r1, int i) {
            this.builder = r1;
            this.notificationId = i;
        }
    }

    public static MediaUploaderNotification A00(Context context, int i, String str, String str2, @Nullable Integer num, IconType iconType, @Nullable PendingIntent pendingIntent) {
        AnonymousClass03h r2 = new AnonymousClass03h(context, "social");
        NotificationCompat$InboxStyle notificationCompat$InboxStyle = new NotificationCompat$InboxStyle();
        notificationCompat$InboxStyle.addLine(str);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, i, new Intent(), 1140850688);
        Bundle bundle = new Bundle();
        bundle.putString("oculus_category", "social");
        bundle.putBoolean(EXTRA_DISMISS_FLAG, true);
        bundle.putBoolean("vrshell_aui_persist", true);
        bundle.putBoolean(EXTRA_SHOW_TITLE_IN_TOAST, true);
        if (num != null) {
            int intValue = num.intValue();
            r2.A08 = 100;
            r2.A07 = intValue;
            float f = ((float) intValue) / 100.0f;
            if (f > 1.0f) {
                f = 1.0f;
            }
            bundle.putFloat(EXTRA_PROGRESS_BAR_RATIO, f);
            bundle.putBoolean(EXTRA_DOWNLOAD_IN_PROGRESS, true);
        }
        if (pendingIntent != null) {
            r2.A03(-1, ACTION_ACCEPT, pendingIntent);
        }
        r2.A05(bundle);
        Integer num2 = ICONS.get(iconType);
        if (num2 == null) {
            num2 = Integer.valueOf((int) R.drawable.facebook);
        }
        r2.A09.icon = num2.intValue();
        r2.A07(str2);
        r2.A0E = AnonymousClass03h.A00(str2);
        r2.A06(notificationCompat$InboxStyle);
        r2.A0A = broadcast;
        r2.A02();
        return new MediaUploaderNotification(r2, i);
    }

    static {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put(IconType.OCULUS, Integer.valueOf((int) R.drawable.status_icon));
        A01.put(IconType.FACEBOOK, Integer.valueOf((int) R.drawable.facebook));
        A01.put(IconType.MESSENGER, Integer.valueOf((int) R.drawable.messenger));
        ICONS = A01.build();
    }
}
