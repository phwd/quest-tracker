package com.oculus.companion.server.casting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import com.oculus.companion.server.SecureStorage;
import java.util.concurrent.TimeUnit;

public class CastDialog {
    private static final String TAG = "CastDialog";
    private static final long TIMEOUT_MS = TimeUnit.SECONDS.toMillis(60);
    private BroadcastReceiver mCastVRDialogBroadcastReceiver;
    private final Context mContext;
    private PowerManager mPowerManager = null;
    private boolean mReceivedPrivacyResponse = false;
    private final SecureStorage mSecureStorage;

    public CastDialog(Context context, SecureStorage secureStorage) {
        this.mContext = context;
        this.mSecureStorage = secureStorage;
    }

    private synchronized PowerManager getPowerManager() {
        if (this.mPowerManager == null) {
            this.mPowerManager = (PowerManager) this.mContext.getSystemService("power");
        }
        if (this.mPowerManager == null) {
            Log.e(TAG, "Failed to get PowerManager object");
        }
        return this.mPowerManager;
    }

    public void showCastDialog(final Runnable runnable, final Runnable runnable2, Runnable runnable3) {
        wakeUpHeadset();
        if (this.mSecureStorage.getBooleanValue("disable_stream_request_privacy_check")) {
            runnable.run();
            return;
        }
        Log.d(TAG, "Show in VR privacy dialogю");
        Looper.prepare();
        final Handler handler = new Handler();
        this.mCastVRDialogBroadcastReceiver = new BroadcastReceiver() {
            /* class com.oculus.companion.server.casting.CastDialog.AnonymousClass1 */

            public void onReceive(Context context, Intent intent) {
                CastDialog.this.mReceivedPrivacyResponse = true;
                Log.d(CastDialog.TAG, "Show in VR privacy dialog, received user response.");
                boolean z = false;
                if (intent != null) {
                    int intExtra = intent.getIntExtra("allowed", -1);
                    if (intExtra >= 1) {
                        z = true;
                    }
                    if (intExtra == 2) {
                        CastDialog.this.mSecureStorage.storeValue("disable_stream_request_privacy_check", true);
                    }
                } else {
                    Log.d(CastDialog.TAG, "Received null intent.");
                }
                Log.d(CastDialog.TAG, "In VR privacy dialog response = " + z);
                if (z) {
                    runnable.run();
                } else {
                    runnable2.run();
                }
                CastDialog.this.unregisterDialogBroadcastReceiver();
                handler.getLooper().quit();
            }
        };
        this.mReceivedPrivacyResponse = false;
        registerDialogBroadcastReceiver(handler);
        showCastDialogInVR();
        scheduleTimeout(runnable3, handler);
        Looper.loop();
    }

    private void scheduleTimeout(Runnable runnable, Handler handler) {
        handler.postDelayed(new Runnable(runnable, handler) {
            /* class com.oculus.companion.server.casting.$$Lambda$CastDialog$Ocy9ndGulA7_mKjnk0D6tkj2SdU */
            private final /* synthetic */ Runnable f$1;
            private final /* synthetic */ Handler f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                CastDialog.this.lambda$scheduleTimeout$0$CastDialog(this.f$1, this.f$2);
            }
        }, TIMEOUT_MS);
    }

    public /* synthetic */ void lambda$scheduleTimeout$0$CastDialog(Runnable runnable, Handler handler) {
        if (!this.mReceivedPrivacyResponse) {
            runnable.run();
            unregisterDialogBroadcastReceiver();
            handler.getLooper().quit();
        }
    }

    private void showCastDialogInVR() {
        Intent intent = new Intent("com.oculus.systemactivities.LOCAL_STREAM_PRIVACY_CHECK");
        intent.setPackage("com.oculus.vrshell");
        this.mContext.sendBroadcast(intent);
    }

    private void registerDialogBroadcastReceiver(Handler handler) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.oculus.systemactivities.LOCAL_STREAM_PRIVACY_CHECK.RESPONSE");
        this.mContext.registerReceiver(this.mCastVRDialogBroadcastReceiver, intentFilter, "com.oculus.companion.server.permission.SEND_INTENTS", handler);
    }

    private void wakeUpHeadset() {
        PowerManager powerManager = getPowerManager();
        if (powerManager != null) {
            powerManager.wakeUp(SystemClock.uptimeMillis(), "Companion:cast-dialog");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void unregisterDialogBroadcastReceiver() {
        try {
            if (this.mCastVRDialogBroadcastReceiver != null) {
                this.mContext.unregisterReceiver(this.mCastVRDialogBroadcastReceiver);
            }
        } catch (IllegalArgumentException e) {
            String str = TAG;
            Log.e(str, "mCastVRDialogBroadcastReceiver not registered. Message: " + e.getLocalizedMessage());
        }
        this.mCastVRDialogBroadcastReceiver = null;
    }
}
