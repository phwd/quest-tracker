package com.oculus.alpenglow.util;

import X.AnonymousClass074;
import X.C03920dW;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.oculus.alpenglow.R;
import javax.annotation.Nullable;

@Dependencies({})
@ApplicationScoped
public class ForegroundNotificationManager {
    public static final String TAG = "EnterpriseServer.ForegroundNotificationManager";
    public static volatile ForegroundNotificationManager _UL__ULSEP_com_oculus_alpenglow_util_ForegroundNotificationManager_ULSEP_INSTANCE;
    @Nullable
    public Notification mNotification;

    public final Notification A00(Context context) {
        Notification notification = this.mNotification;
        if (notification != null) {
            return notification;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(TAG, "EnterpriseServer", 0);
            notificationChannel.setLockscreenVisibility(-1);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        AnonymousClass074 r2 = new AnonymousClass074(context, TAG);
        Notification notification2 = r2.A02;
        notification2.flags = 2 | notification2.flags;
        r2.A07 = AnonymousClass074.A00(context.getString(R.string.app_name));
        r2.A06 = AnonymousClass074.A00(context.getString(R.string.app_short_name));
        r2.A02.icon = 17301514;
        String string = context.getString(R.string.app_name);
        r2.A02.tickerText = AnonymousClass074.A00(string);
        Notification A00 = new C03920dW(r2).A00();
        this.mNotification = A00;
        return A00;
    }
}
