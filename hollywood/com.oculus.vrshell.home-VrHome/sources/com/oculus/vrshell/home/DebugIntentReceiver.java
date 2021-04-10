package com.oculus.vrshell.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.oculus.modules.CompanionServerModuleImpl;
import com.oculus.panellib.ShellIPC;

public class DebugIntentReceiver extends BroadcastReceiver {
    private static final String ACTION_CS_OVERRIDE_NAME = "com.oculus.vrshell.home.cs_override";
    private static final String ACTION_IPC_NAME = "com.oculus.vrshell.home.send_shell_ipc";
    private static final String CS_CS_ACTION_KEY = "cs_action";
    private static final String CS_OPERATION_KEY = "operation";
    private static final String CS_VALUE_KEY = "value";
    private static final String IPC_INTENT_KEY = "shell_ipc";
    private static final String TAG = DebugIntentReceiver.class.getSimpleName();
    private final Context mContext;

    public DebugIntentReceiver(Context context) {
        this.mContext = context;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_IPC_NAME);
        intentFilter.addAction(ACTION_CS_OVERRIDE_NAME);
        this.mContext.registerReceiver(this, intentFilter);
        CompanionServerModuleImpl.loadOverrides(context);
    }

    public void destroy() {
        this.mContext.unregisterReceiver(this);
    }

    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "DebugIntentReceiver.onReceive");
        if (intent.getAction().equals(ACTION_IPC_NAME)) {
            String ipcMsg = intent.getStringExtra(IPC_INTENT_KEY);
            if (ipcMsg == null) {
                Log.e(TAG, "No message given.");
                return;
            }
            Log.d(TAG, "Sending: `" + ipcMsg + "`.");
            ShellIPC.addToCommandBuffer(ipcMsg);
        } else if (intent.getAction().equals(ACTION_CS_OVERRIDE_NAME)) {
            String operation = intent.getStringExtra(CS_OPERATION_KEY);
            if ("clear".equals(operation)) {
                Log.d(TAG, "Clearing CS overrides.");
                CompanionServerModuleImpl.clearOverrides(context);
            } else if ("set".equals(operation)) {
                String csAction = intent.getStringExtra(CS_CS_ACTION_KEY);
                String value = intent.getStringExtra(CS_VALUE_KEY);
                if (csAction != null) {
                    Log.d(TAG, "Setting CS override: `" + csAction + "`=" + value);
                    CompanionServerModuleImpl.setOverride(context, csAction, "true".equals(value));
                    return;
                }
                Log.e(TAG, "No action given.");
            } else {
                Log.e(TAG, "No valid operation given.");
            }
        }
    }
}
