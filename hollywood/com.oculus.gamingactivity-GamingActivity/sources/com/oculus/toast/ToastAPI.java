package com.oculus.toast;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.view.MotionEventCompat;
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

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.toast.ToastAPI$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$modules$codegen$ToasterModule$ToastIcon = new int[ToasterModule.ToastIcon.values().length];

        static {
            try {
                $SwitchMap$com$oculus$modules$codegen$ToasterModule$ToastIcon[ToasterModule.ToastIcon.chat.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$oculus$modules$codegen$ToasterModule$ToastIcon[ToasterModule.ToastIcon.check_alt.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$oculus$modules$codegen$ToasterModule$ToastIcon[ToasterModule.ToastIcon.download.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$oculus$modules$codegen$ToasterModule$ToastIcon[ToasterModule.ToastIcon.fitness.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$oculus$modules$codegen$ToasterModule$ToastIcon[ToasterModule.ToastIcon.gear.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$oculus$modules$codegen$ToasterModule$ToastIcon[ToasterModule.ToastIcon.home.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$oculus$modules$codegen$ToasterModule$ToastIcon[ToasterModule.ToastIcon.info.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$oculus$modules$codegen$ToasterModule$ToastIcon[ToasterModule.ToastIcon.library.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$oculus$modules$codegen$ToasterModule$ToastIcon[ToasterModule.ToastIcon.mediagallery.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$oculus$modules$codegen$ToasterModule$ToastIcon[ToasterModule.ToastIcon.microphone.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$oculus$modules$codegen$ToasterModule$ToastIcon[ToasterModule.ToastIcon.pacific_controller.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$oculus$modules$codegen$ToasterModule$ToastIcon[ToasterModule.ToastIcon.party.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$oculus$modules$codegen$ToasterModule$ToastIcon[ToasterModule.ToastIcon.phone.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$oculus$modules$codegen$ToasterModule$ToastIcon[ToasterModule.ToastIcon.store.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$oculus$modules$codegen$ToasterModule$ToastIcon[ToasterModule.ToastIcon.tv.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
        }
    }

    private static int getIconResourceId(ToasterModule.ToastIcon icon) {
        switch (AnonymousClass1.$SwitchMap$com$oculus$modules$codegen$ToasterModule$ToastIcon[icon.ordinal()]) {
            case 1:
                return R.drawable.ic_notif_chat;
            case 2:
                return R.drawable.ic_notif_check_alt;
            case 3:
                return R.drawable.ic_notif_download;
            case 4:
                return R.drawable.ic_notif_fitness;
            case 5:
                return R.drawable.ic_notif_gear;
            case 6:
                return R.drawable.ic_notif_home;
            case 7:
                return R.drawable.ic_notif_info;
            case 8:
                return R.drawable.ic_notif_library;
            case 9:
                return R.drawable.ic_notif_gallery;
            case 10:
                return R.drawable.ic_notif_microphone;
            case 11:
                return R.drawable.ic_notif_pac_cont;
            case 12:
                return R.drawable.ic_notif_party;
            case 13:
                return R.drawable.ic_notif_phone;
            case MotionEventCompat.AXIS_RZ:
                return R.drawable.ic_notif_store;
            case MotionEventCompat.AXIS_HAT_X:
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
