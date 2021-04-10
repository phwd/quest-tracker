package com.oculus.fitnesstracker.provider;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.core.R;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationCompatBuilder;

public class FitnessToast {
    private static final String TAG = "FitnessToast";
    private static int mToastID;

    public enum ToastType {
        daily_goal_complete,
        daily_goal_half_complete,
        active_time_target_complete,
        calorie_target_complete
    }

    private static String ensureNotificationChannel(Context context) {
        NotificationChannel notificationChannel = new NotificationChannel(context.getPackageName(), "Toasty!", 3);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
        return notificationChannel.getId();
    }

    private static void createToast(Context context, int i, int i2, String str) {
        String ensureNotificationChannel = Build.VERSION.SDK_INT >= 26 ? ensureNotificationChannel(context) : "0";
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (notificationManager == null) {
            Log.w(TAG, "Fitness notification not sent, null NotificationManager");
            return;
        }
        Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), i);
        if (decodeResource == null) {
            Log.w(TAG, "Null icon bitmap in notification");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("oculus_notification_type", str);
        NotificationCompat.Builder contentTitle = new NotificationCompat.Builder(context, ensureNotificationChannel).setContentTitle(context.getString(i2));
        if (Build.VERSION.SDK_INT < 27) {
            Resources resources = contentTitle.mContext.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.compat_notification_large_icon_max_width);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.compat_notification_large_icon_max_height);
            if (decodeResource.getWidth() > dimensionPixelSize || decodeResource.getHeight() > dimensionPixelSize2) {
                double d = (double) dimensionPixelSize;
                double max = (double) Math.max(1, decodeResource.getWidth());
                Double.isNaN(d);
                Double.isNaN(max);
                double d2 = d / max;
                double d3 = (double) dimensionPixelSize2;
                double max2 = (double) Math.max(1, decodeResource.getHeight());
                Double.isNaN(d3);
                Double.isNaN(max2);
                double min = Math.min(d2, d3 / max2);
                double width = (double) decodeResource.getWidth();
                Double.isNaN(width);
                double height = (double) decodeResource.getHeight();
                Double.isNaN(height);
                decodeResource = Bitmap.createScaledBitmap(decodeResource, (int) Math.ceil(width * min), (int) Math.ceil(height * min), true);
            }
        }
        contentTitle.mLargeIcon = decodeResource;
        contentTitle.mNotification.icon = i;
        contentTitle.mPriority = 1;
        contentTitle.mNotification.flags |= 16;
        if (contentTitle.mExtras == null) {
            contentTitle.mExtras = new Bundle(bundle);
        } else {
            contentTitle.mExtras.putAll(bundle);
        }
        Notification build = new NotificationCompatBuilder(contentTitle).build();
        int i3 = mToastID;
        mToastID = i3 + 1;
        notificationManager.notify("system", i3, build);
    }

    /* renamed from: com.oculus.fitnesstracker.provider.FitnessToast$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$fitnesstracker$provider$FitnessToast$ToastType = new int[ToastType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                com.oculus.fitnesstracker.provider.FitnessToast$ToastType[] r0 = com.oculus.fitnesstracker.provider.FitnessToast.ToastType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.fitnesstracker.provider.FitnessToast.AnonymousClass1.$SwitchMap$com$oculus$fitnesstracker$provider$FitnessToast$ToastType = r0
                int[] r0 = com.oculus.fitnesstracker.provider.FitnessToast.AnonymousClass1.$SwitchMap$com$oculus$fitnesstracker$provider$FitnessToast$ToastType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.fitnesstracker.provider.FitnessToast$ToastType r1 = com.oculus.fitnesstracker.provider.FitnessToast.ToastType.daily_goal_complete     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.fitnesstracker.provider.FitnessToast.AnonymousClass1.$SwitchMap$com$oculus$fitnesstracker$provider$FitnessToast$ToastType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.fitnesstracker.provider.FitnessToast$ToastType r1 = com.oculus.fitnesstracker.provider.FitnessToast.ToastType.daily_goal_half_complete     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.fitnesstracker.provider.FitnessToast.AnonymousClass1.$SwitchMap$com$oculus$fitnesstracker$provider$FitnessToast$ToastType     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.fitnesstracker.provider.FitnessToast$ToastType r1 = com.oculus.fitnesstracker.provider.FitnessToast.ToastType.active_time_target_complete     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.oculus.fitnesstracker.provider.FitnessToast.AnonymousClass1.$SwitchMap$com$oculus$fitnesstracker$provider$FitnessToast$ToastType     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.oculus.fitnesstracker.provider.FitnessToast$ToastType r1 = com.oculus.fitnesstracker.provider.FitnessToast.ToastType.calorie_target_complete     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.fitnesstracker.provider.FitnessToast.AnonymousClass1.<clinit>():void");
        }
    }

    public static void send(Context context, ToastType toastType) {
        int i = AnonymousClass1.$SwitchMap$com$oculus$fitnesstracker$provider$FitnessToast$ToastType[toastType.ordinal()];
        if (i == 1) {
            createToast(context, com.oculus.fitnesstracker.R.drawable.trophy_notif_icon, com.oculus.fitnesstracker.R.string.toast_daily_goal_complete, "oculus_mobile_fitness_tracker_daily_goal_complete");
        } else if (i == 2) {
            createToast(context, com.oculus.fitnesstracker.R.drawable.daily_goal_half_notif_icon, com.oculus.fitnesstracker.R.string.toast_daily_goal_half_complete, "oculus_mobile_fitness_tracker_daily_goal_half_complete");
        } else if (i == 3) {
            createToast(context, com.oculus.fitnesstracker.R.drawable.active_time_notif_icon, com.oculus.fitnesstracker.R.string.toast_active_time_target_complete, "oculus_mobile_fitness_tracker_active_time_target_complete");
        } else if (i != 4) {
            Log.w(TAG, "Fitness toast type not found!");
        } else {
            createToast(context, com.oculus.fitnesstracker.R.drawable.calorie_notif_icon, com.oculus.fitnesstracker.R.string.toast_calorie_target_complete, "oculus_mobile_fitness_tracker_calorie_target_complete");
        }
    }
}
