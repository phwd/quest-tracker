package com.oculus.systemutilities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.oculus.panellib.ShellIPC;

public class DebugIntentReceiver extends BroadcastReceiver {
    private static final String ACTION_NAME = "com.oculus.systemutilities.send_shell_ipc";
    private static final String IPC_INTENT_KEY = "shell_ipc";
    private static final String TAG = DebugIntentReceiver.class.getSimpleName();
    private final Context mContext;

    public DebugIntentReceiver(Context context) {
        this.mContext = context;
        this.mContext.registerReceiver(this, new IntentFilter(ACTION_NAME));
    }

    public void destroy() {
        this.mContext.unregisterReceiver(this);
    }

    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "DebugIntentReceiver.onReceive");
        if (intent.getAction().equals(ACTION_NAME)) {
            String ipcMsg = intent.getStringExtra(IPC_INTENT_KEY);
            if (ipcMsg == null) {
                Log.e(TAG, "No message given.");
                return;
            }
            Log.d(TAG, "Sending: `" + ipcMsg + "`");
            ShellIPC.addToCommandBuffer(ipcMsg);
        }
    }
}
