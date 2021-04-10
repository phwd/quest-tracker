package com.oculus.toast;

import X.AnonymousClass006;
import X.AnonymousClass03m;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import com.oculus.modules.codegen.ToasterModule;
import com.oculus.notifications.NotificationConstants;
import com.oculus.socialplatform.R;

public class ToastAPI {
    public static final String TAG = "ToastAPI";
    public static int mToastID;

    /* renamed from: com.oculus.toast.ToastAPI$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$modules$codegen$ToasterModule$ToastIcon;

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
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.toast.ToastAPI.AnonymousClass1.<clinit>():void");
        }
    }

    public static String ensureNotificationChannel(Context context) {
        NotificationChannel notificationChannel = new NotificationChannel(context.getPackageName(), "Toasty!", 3);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
        return notificationChannel.getId();
    }

    public static int getIconResourceId(ToasterModule.ToastIcon toastIcon) {
        switch (toastIcon.ordinal()) {
            case 0:
                return R.drawable.ic_notif_chat;
            case 1:
                return R.drawable.ic_notif_check_alt;
            case 2:
                return R.drawable.ic_notif_download;
            case 3:
                return R.drawable.ic_notif_fitness;
            case 4:
                return R.drawable.ic_notif_gear;
            case 5:
                return R.drawable.ic_notif_home;
            case 6:
                return R.drawable.ic_notif_info;
            case 7:
                return R.drawable.ic_notif_library;
            case 8:
                return R.drawable.ic_notif_gallery;
            case 9:
                return R.drawable.ic_notif_microphone;
            case 10:
                return R.drawable.ic_notif_pac_cont;
            case 11:
                return R.drawable.ic_notif_party;
            case 12:
                return R.drawable.ic_notif_phone;
            case 13:
                return R.drawable.ic_notif_store;
            case 14:
                return R.drawable.ic_notif_tv;
            default:
                throw new IllegalArgumentException(AnonymousClass006.A07("Invalid toast icon: ", toastIcon.toString()));
        }
    }

    public static void createToast(Context context, ToasterModule.Bread bread) {
        createToast(context, bread.id, bread.duration, bread.icon, bread.title, bread.message, Boolean.TRUE.equals(bread.suppressSound));
    }

    public static void createToast(Context context, String str, ToasterModule.ToastDuration toastDuration, ToasterModule.ToastIcon toastIcon, String str2, String str3, boolean z) {
        String str4;
        if (Build.VERSION.SDK_INT >= 26) {
            str4 = ensureNotificationChannel(context);
        } else {
            str4 = "0";
        }
        Bundle bundle = new Bundle();
        AnonymousClass03m r4 = new AnonymousClass03m(context, str4);
        r4.A05 = 1;
        r4.A0C = AnonymousClass03m.A00(str2);
        if (str3 != null && !str3.isEmpty()) {
            r4.A0B = AnonymousClass03m.A00(str3);
        }
        if (toastIcon != null) {
            r4.A06.icon = getIconResourceId(toastIcon);
            bundle.putString("large_image_type", "icon");
        }
        if (toastDuration != null) {
            bundle.putString(NotificationConstants.KEY_AUI_NOTIF_DURATION, toastDuration.name());
        }
        if (z) {
            bundle.putBoolean("prevent_sound", true);
        }
        bundle.putString("oculus_notification_type", str);
        r4.A09 = bundle;
        int i = mToastID;
        mToastID = i + 1;
        ((NotificationManager) context.getSystemService("notification")).notify("system", i, r4.A01());
    }
}
