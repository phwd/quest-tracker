package com.oculus.toast;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.oculus.modules.codegen.ToasterModule;
import com.oculus.toastermodule.R;

public class ToastAPI {
    private static final String TAG = ToastAPI.class.getSimpleName();
    private static int mToastID = 0;

    public static void createToast(Context context, String id, ToasterModule.ToastDuration duration, ToasterModule.ToastIcon icon, String title, String message, boolean suppressSound) {
        String channelId;
        if (Build.VERSION.SDK_INT >= 26) {
            channelId = ensureNotificationChannel(context);
        } else {
            channelId = "0";
        }
        Bundle extras = new Bundle();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId).setPriority(1);
        builder.setContentTitle(title);
        if (message != null && !message.isEmpty()) {
            builder.setContentText(message);
        }
        if (icon != null) {
            builder.setSmallIcon(getIconResourceId(icon));
            extras.putString("large_image_type", "icon");
        }
        if (duration != null) {
            extras.putString("aui_notif_duration", duration.name());
        }
        if (suppressSound) {
            extras.putBoolean("prevent_sound", true);
        }
        extras.putString("oculus_notification_type", id);
        Log.d(TAG, String.format("Toasting with duration: %s, icon: %s, title: %s, message: %s", duration, icon, title, message));
        builder.setExtras(extras);
        int i = mToastID;
        mToastID = i + 1;
        ((NotificationManager) context.getSystemService("notification")).notify(Constants.NOTIF_TAG, i, builder.build());
    }

    public static void createToast(Context context, ToasterModule.Bread bread) {
        createToast(context, bread.id, bread.duration, bread.icon, bread.title, bread.message, Boolean.TRUE.equals(bread.suppressSound));
    }

    private static int getIconResourceId(ToasterModule.ToastIcon icon) {
        switch (icon) {
            case chat:
                return R.drawable.ic_notif_chat;
            case check_alt:
                return R.drawable.ic_notif_check_alt;
            case download:
                return R.drawable.ic_notif_download;
            case fitness:
                return R.drawable.ic_notif_fitness;
            case gear:
                return R.drawable.ic_notif_gear;
            case home:
                return R.drawable.ic_notif_home;
            case info:
                return R.drawable.ic_notif_info;
            case library:
                return R.drawable.ic_notif_library;
            case mediagallery:
                return R.drawable.ic_notif_gallery;
            case microphone:
                return R.drawable.ic_notif_microphone;
            case pacific_controller:
                return R.drawable.ic_notif_pac_cont;
            case party:
                return R.drawable.ic_notif_party;
            case phone:
                return R.drawable.ic_notif_phone;
            case store:
                return R.drawable.ic_notif_store;
            case tv:
                return R.drawable.ic_notif_tv;
            default:
                throw new IllegalArgumentException("Invalid toast icon: " + icon.toString());
        }
    }

    private static String ensureNotificationChannel(Context context) {
        NotificationChannel chan = new NotificationChannel(context.getPackageName(), "Toasty!", 3);
        NotificationManager manager = (NotificationManager) context.getSystemService("notification");
        if (manager != null) {
            manager.createNotificationChannel(chan);
        }
        return chan.getId();
    }
}
