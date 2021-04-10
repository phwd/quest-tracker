package com.oculus.os.clearactivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;

public class ClearActivity extends Activity implements IBinder.DeathRecipient {
    private static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static final String TAG = "ClearActivity";
    private static int sInstanceCount = 0;
    private static boolean sVrAlertServiceDialog = DEBUG;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        /* class com.oculus.os.clearactivity.ClearActivity.AnonymousClass1 */

        public void onServiceDisconnected(ComponentName componentName) {
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                iBinder.linkToDeath(ClearActivity.this, 0);
            } catch (RemoteException unused) {
            }
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.translucent_window);
        handleIntent(getIntent(), true);
        bindToVrAlertService();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        unbindFromVrAlertService();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        handleIntent(intent, DEBUG);
    }

    public void binderDied() {
        if (DEBUG) {
            Log.d(TAG, "binderDied()");
        }
        runOnUiThread(new Runnable() {
            /* class com.oculus.os.clearactivity.$$Lambda$ClearActivity$YN6zRmJqqqXjdYDLgXLXIrGA3Eo */

            public final void run() {
                ClearActivity.this.lambda$binderDied$0$ClearActivity();
            }
        });
    }

    public /* synthetic */ void lambda$binderDied$0$ClearActivity() {
        if (sVrAlertServiceDialog) {
            onActionFinishIntentReceived();
            sVrAlertServiceDialog = DEBUG;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0050  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleIntent(android.content.Intent r6, boolean r7) {
        /*
        // Method dump skipped, instructions count: 101
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.os.clearactivity.ClearActivity.handleIntent(android.content.Intent, boolean):void");
    }

    private void onActionMainIntentReceived(boolean z) {
        if (!z || sInstanceCount == 0) {
            sInstanceCount++;
            if (DEBUG) {
                Log.d(TAG, "onActionMainIntentReceived: sInstanceCount: " + sInstanceCount);
                return;
            }
            return;
        }
        throw new IllegalStateException("Non-zero ClearActivity instance count " + sInstanceCount);
    }

    private void onActionFinishIntentReceived() {
        sInstanceCount--;
        if (DEBUG) {
            Log.d(TAG, "onActionFinishIntentReceived: sInstanceCount: " + sInstanceCount);
        }
        int i = sInstanceCount;
        if (i < 0) {
            throw new IllegalStateException("Negative ClearActivity instance count " + sInstanceCount);
        } else if (i == 0) {
            finish();
        }
    }

    private void bindToVrAlertService() {
        Intent intent = new Intent();
        intent.setClassName("com.oculus.vralertservice", "com.oculus.vralertservice.VrAlertService");
        bindServiceAsUser(intent, this.mServiceConnection, 1, UserHandle.SYSTEM);
    }

    private void unbindFromVrAlertService() {
        unbindService(this.mServiceConnection);
    }
}
