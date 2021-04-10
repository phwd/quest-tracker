package com.oculus.externalstorage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.UserHandle;
import android.util.Slog;
import com.oculus.externalstorage.StorageLauncherActivity;
import com.oculus.os.ActivityManagerUtils;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.SettingsManager;
import com.oculus.os.UnifiedTelemetryLogger;

public class OtgReceiver extends BroadcastReceiver {
    private static final boolean DEBUG = false;
    private static final String TAG = OtgReceiver.class.getSimpleName();
    private final SettingsManager mSettingsManager = new SettingsManager();

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            char c = 65535;
            int hashCode = action.hashCode();
            if (hashCode != -2114103349) {
                if (hashCode != -1608292967) {
                    if (hashCode == -575271864 && action.equals("android.os.storage.action.DISK_SCANNED")) {
                        c = 2;
                    }
                } else if (action.equals("android.hardware.usb.action.USB_DEVICE_DETACHED")) {
                    c = 1;
                }
            } else if (action.equals("android.hardware.usb.action.USB_DEVICE_ATTACHED")) {
                c = 0;
            }
            if (c == 0) {
                playChime(context, "usb_attached.wav");
            } else if (c == 1) {
                playChime(context, "usb_detached.wav");
            } else if (c == 2) {
                handleDiskScanned(context, intent);
            }
        }
    }

    private boolean doNotDisturb() {
        return this.mSettingsManager.getBoolean("do_not_disturb", (boolean) DEBUG, ActivityManagerUtils.getUserIdFromHandle(ActivityManagerUtils.getCurrentUser()));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005e, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005f, code lost:
        if (r2 != null) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0065, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0066, code lost:
        r3.addSuppressed(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0069, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void playChime(android.content.Context r7, java.lang.String r8) {
        /*
        // Method dump skipped, instructions count: 130
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.externalstorage.OtgReceiver.playChime(android.content.Context, java.lang.String):void");
    }

    static /* synthetic */ boolean lambda$playChime$0(String filename, MediaPlayer mp, int what, int extra) {
        String str = TAG;
        Slog.e(str, "Failed to play: " + filename + " what=" + what + " extra=" + extra);
        if (mp == null) {
            return true;
        }
        mp.release();
        return true;
    }

    private void handleDiskScanned(Context context, Intent intent) {
        if (!intent.hasExtra("android.os.storage.extra.DISK_ID") || !intent.hasExtra("android.os.storage.extra.VOLUME_COUNT")) {
            Slog.w(TAG, "invalid ACTION_DISK_SCANNED intent");
            return;
        }
        String diskId = intent.getStringExtra("android.os.storage.extra.DISK_ID");
        int volumes = intent.getIntExtra("android.os.storage.extra.VOLUME_COUNT", 0);
        String str = TAG;
        Slog.d(str, "new device mounted: diskId=" + diskId + " volumes=" + volumes);
        UnifiedTelemetryLogger logger = UnifiedTelemetryLogger.getInstance(context);
        AnalyticsEvent event = new AnalyticsEvent("oculus_mobile_otg_enabled");
        event.setExtra("volume_count", Integer.valueOf(volumes));
        if (logger != null) {
            logger.reportEvent(event, (boolean) DEBUG);
        }
        if (volumes == 0) {
            Intent wizard = new Intent(context, StorageLauncherActivity.class);
            wizard.setFlags(536870912);
            wizard.putExtra("android.os.storage.extra.DISK_ID", diskId);
            wizard.putExtra(StorageLauncherActivity.INTENT_KEY_LAUNCH_TYPE, StorageLauncherActivity.LaunchType.FORMAT);
            context.startActivityAsUser(wizard, UserHandle.SYSTEM);
        }
    }
}
