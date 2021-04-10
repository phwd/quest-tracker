package com.oculus.notification_proxy;

import android.app.NotificationManager;
import android.content.Context;
import android.media.AudioManager;
import android.media.IVolumeController;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.concurrent.TimeUnit;

public class VolumeNotifications {
    private static final int MSG_SAFE_VOLUME_WARNING = 1;
    private static final long STATE_RESET_PERIOD = TimeUnit.SECONDS.toNanos(10);
    private static final String TAG = "VolumeNotifications";
    private final Context mContext;
    private Handler mHandler = new Handler() {
        /* class com.oculus.notification_proxy.VolumeNotifications.AnonymousClass1 */

        public void handleMessage(Message message) {
            if (message.what != VolumeNotifications.MSG_SAFE_VOLUME_WARNING) {
                String str = VolumeNotifications.TAG;
                Log.e(str, "Invalid message received: " + message);
                return;
            }
            long nanoTime = System.nanoTime();
            if (nanoTime > VolumeNotifications.this.mLastStateChange + VolumeNotifications.STATE_RESET_PERIOD) {
                VolumeNotifications.this.mState = State.INITIAL;
            }
            int i = AnonymousClass2.$SwitchMap$com$oculus$notification_proxy$VolumeNotifications$State[VolumeNotifications.this.mState.ordinal()];
            if (i == VolumeNotifications.MSG_SAFE_VOLUME_WARNING) {
                VolumeNotifications.this.postSafeVolumeWarningNotification(R.string.safe_volume_warning_text_1);
                VolumeNotifications.this.mState = State.FIRST_WARNING_SHOWN;
            } else if (i == 2) {
                VolumeNotifications.this.postSafeVolumeWarningNotification(R.string.safe_volume_warning_text_2);
                VolumeNotifications.this.mState = State.SECOND_WARNING_SHOWN;
            } else if (i == 3) {
                VolumeNotifications.this.disableSafeVolumeWarning();
            }
            VolumeNotifications.this.mLastStateChange = nanoTime;
        }
    };
    private long mLastStateChange = System.nanoTime();
    private State mState = State.INITIAL;
    private final VC mVolumeController = new VC();

    /* access modifiers changed from: private */
    public enum State {
        INITIAL,
        FIRST_WARNING_SHOWN,
        SECOND_WARNING_SHOWN
    }

    /* renamed from: com.oculus.notification_proxy.VolumeNotifications$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$notification_proxy$VolumeNotifications$State = new int[State.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.oculus.notification_proxy.VolumeNotifications$State[] r0 = com.oculus.notification_proxy.VolumeNotifications.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.notification_proxy.VolumeNotifications.AnonymousClass2.$SwitchMap$com$oculus$notification_proxy$VolumeNotifications$State = r0
                int[] r0 = com.oculus.notification_proxy.VolumeNotifications.AnonymousClass2.$SwitchMap$com$oculus$notification_proxy$VolumeNotifications$State     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.notification_proxy.VolumeNotifications$State r1 = com.oculus.notification_proxy.VolumeNotifications.State.INITIAL     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.notification_proxy.VolumeNotifications.AnonymousClass2.$SwitchMap$com$oculus$notification_proxy$VolumeNotifications$State     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.notification_proxy.VolumeNotifications$State r1 = com.oculus.notification_proxy.VolumeNotifications.State.FIRST_WARNING_SHOWN     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.notification_proxy.VolumeNotifications.AnonymousClass2.$SwitchMap$com$oculus$notification_proxy$VolumeNotifications$State     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.notification_proxy.VolumeNotifications$State r1 = com.oculus.notification_proxy.VolumeNotifications.State.SECOND_WARNING_SHOWN     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.notification_proxy.VolumeNotifications.AnonymousClass2.<clinit>():void");
        }
    }

    public VolumeNotifications(Context context) {
        this.mContext = context;
        try {
            ((AudioManager) this.mContext.getSystemService("audio")).setVolumeController(this.mVolumeController);
        } catch (SecurityException e) {
            Log.e(TAG, "Unable to set the volume controller", e);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void postSafeVolumeWarningNotification(int i) {
        NotificationBuildingHelper.notify(this.mContext, R.string.safe_volume_warning_title, i, NotificationIds.SAFE_VOLUME_WARNING);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void disableSafeVolumeWarning() {
        ((AudioManager) this.mContext.getSystemService("audio")).disableSafeMediaVolume();
        ((NotificationManager) this.mContext.getSystemService("notification")).cancel(NotificationIds.SAFE_VOLUME_WARNING);
    }

    private final class VC extends IVolumeController.Stub {
        public void dismiss() {
        }

        public void masterMuteChanged(int i) {
        }

        public void setA11yMode(int i) {
        }

        public void setLayoutDirection(int i) {
        }

        public void volumeChanged(int i, int i2) {
        }

        private VC() {
        }

        public void displaySafeVolumeWarning(int i) {
            VolumeNotifications.this.mHandler.sendEmptyMessage(VolumeNotifications.MSG_SAFE_VOLUME_WARNING);
        }
    }
}
