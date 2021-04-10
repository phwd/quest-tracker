package com.oculus.micservice;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.oculus.os.SettingsManager;
import com.oculus.os.SettingsObserverCallback;
import oculus.internal.BinderClient;
import oculus.internal.power.IVrPowerManager;
import oculus.internal.power.IVrPowerManagerClient;

public class MicService extends Service {
    private static final String TAG = "MicService";
    private AudioManager mAudioManager;
    private final BinderClient<IVrPowerManager> mBinderClient = new BinderClient<IVrPowerManager>("vrpowermanager", $$Lambda$MicService$NS1CDEmEMa7l0JVVi549JnqEc20.INSTANCE) {
        /* class com.oculus.micservice.MicService.AnonymousClass2 */

        /* access modifiers changed from: protected */
        public void onServiceConnected(IVrPowerManager iVrPowerManager) {
            try {
                iVrPowerManager.registerClient(MicService.this.mPowerManagerClient);
            } catch (RemoteException e) {
                Log.e(MicService.TAG, "Failed to register with VrPowerManager", e);
            }
        }
    };
    private int mCurrentPowerState;
    private final VrPowerManagerClient mPowerManagerClient = new VrPowerManagerClient();
    private SettingsManager mSettingsManager;
    private final SettingsObserverCallback mSettingsObserver = new SettingsObserverCallback() {
        /* class com.oculus.micservice.MicService.AnonymousClass1 */

        public void onSettingChange(String str) {
            MicService.this.updateMuteStatus();
        }
    };

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }

    public void onCreate() {
        this.mAudioManager = (AudioManager) getSystemService("audio");
        this.mSettingsManager = new SettingsManager(this);
        this.mSettingsManager.registerSettingsObserver("mic_muted", this.mSettingsObserver, new Handler());
        updateMuteStatus();
    }

    public void onDestroy() {
        this.mSettingsManager.unregisterSettingsObserver("mic_muted", this.mSettingsObserver);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateMuteStatus() {
        boolean z = false;
        if (this.mCurrentPowerState == 3 || this.mSettingsManager.getBoolean("mic_muted", false, ActivityManager.getCurrentUser())) {
            z = true;
        }
        this.mAudioManager.setMicrophoneMute(z);
    }

    /* access modifiers changed from: private */
    public final class VrPowerManagerClient extends IVrPowerManagerClient.Stub {
        private VrPowerManagerClient() {
        }

        public void onStateChange(int i) {
            MicService.this.mCurrentPowerState = i;
            MicService.this.updateMuteStatus();
        }
    }
}
