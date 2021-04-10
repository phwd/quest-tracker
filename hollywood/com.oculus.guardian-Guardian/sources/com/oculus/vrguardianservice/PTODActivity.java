package com.oculus.vrguardianservice;

import android.app.NativeActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class PTODActivity extends NativeActivity {
    public static final String ACTION_STRING = "com.oculus.vrshell.intent.action.GUARDIAN";
    public static final int BROADCAST_NOTIF = 1;
    public static final int BROADCAST_NOTIF_CANCEL = 2;
    public static final String TAG = "PTODActivity";
    public static final String kNotifPTOnDemand = "systemux://guardian/notif_ptondemand";
    private PTODBroadcastReceiver receiver;
    private boolean showDoubleTapNotif;

    static {
        try {
            System.loadLibrary("vrapi");
        } catch (UnsatisfiedLinkError e) {
            Log.d(TAG, "vrapi library not found");
        }
    }

    private class PTODBroadcastReceiver extends BroadcastReceiver {
        public PTODBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            PTODActivity.this.finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.oculus.vrguardianservice.PTODBroadcast");
        this.receiver = new PTODBroadcastReceiver();
        registerReceiver(this.receiver, filter);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.showDoubleTapNotif = extras.getBoolean("showDoubleTapNotif", true);
        }
        if (this.showDoubleTapNotif) {
            new Handler().postDelayed(new Runnable() {
                /* class com.oculus.vrguardianservice.PTODActivity.AnonymousClass1 */

                public void run() {
                    Context context = PTODActivity.this.getApplicationContext();
                    Intent intent = new Intent(PTODActivity.ACTION_STRING);
                    intent.putExtra(VrGuardianService.INTENT_KEY_DATA, PTODActivity.kNotifPTOnDemand);
                    intent.putExtra(VrGuardianService.KEY_GUARDIAN_ACTION_TYPE, 1);
                    intent.putExtra(VrGuardianService.KEY_GUARDIAN_EXTRA_DATA, "");
                    intent.setPackage("com.oculus.vrshell");
                    intent.addFlags(65536);
                    context.sendBroadcast(intent);
                }
            }, 100);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.receiver);
        Context context = getApplicationContext();
        Intent intent = new Intent(ACTION_STRING);
        intent.putExtra(VrGuardianService.INTENT_KEY_DATA, kNotifPTOnDemand);
        intent.putExtra(VrGuardianService.KEY_GUARDIAN_ACTION_TYPE, 2);
        intent.putExtra(VrGuardianService.KEY_GUARDIAN_EXTRA_DATA, "");
        intent.setPackage("com.oculus.vrshell");
        intent.addFlags(65536);
        context.sendBroadcast(intent);
    }
}
