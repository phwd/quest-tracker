package com.oculus.vrapilayers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class AutoDriverBroadcastReceiver extends BroadcastReceiver {
    private static final String BEGIN_SCENE = "com.oculus.vrapilayers.AutoDriver.BEGIN_SCENE";
    private static final String END_SCENE = "com.oculus.vrapilayers.AutoDriver.END_SCENE";
    private static final String INITIALIZE = "com.oculus.vrapilayers.AutoDriver.INITIALIZE";
    private static final String SHUTDOWN = "com.oculus.vrapilayers.AutoDriver.SHUTDOWN";
    private static final String TAG = AutoDriverBroadcastReceiver.class.getSimpleName();
    private static Context sContext;
    private static AutoDriverBroadcastReceiver sReceiver;

    private static class ShutdownHook extends Thread {
        private ShutdownHook() {
        }

        public void run() {
            if (AutoDriverBroadcastReceiver.sReceiver != null) {
                Log.d(AutoDriverBroadcastReceiver.TAG, "ShutdownHook");
                AutoDriverBroadcastReceiver.sContext.unregisterReceiver(AutoDriverBroadcastReceiver.sReceiver);
                Context unused = AutoDriverBroadcastReceiver.sContext = null;
                AutoDriverBroadcastReceiver unused2 = AutoDriverBroadcastReceiver.sReceiver = null;
            }
        }
    }

    public static void RegisterReceiver(Context context) {
        if (sReceiver == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(INITIALIZE);
            intentFilter.addAction(BEGIN_SCENE);
            intentFilter.addAction(END_SCENE);
            intentFilter.addAction(SHUTDOWN);
            AutoDriverBroadcastReceiver receiver = new AutoDriverBroadcastReceiver();
            context.registerReceiver(receiver, intentFilter);
            sContext = context;
            sReceiver = receiver;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public void onReceive(Context context, Intent intent) {
        char c;
        String action = intent.getAction();
        if (AutoDriver.AutoDriverAppEnabled(context) && AutoDriver.AutoDriverBroadcastsEnabled(context)) {
            String str = TAG;
            Log.d(str, "Received intent " + intent);
            switch (action.hashCode()) {
                case -1762196319:
                    if (action.equals(INITIALIZE)) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -1106160729:
                    if (action.equals(SHUTDOWN)) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case -404020041:
                    if (action.equals(END_SCENE)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1005456965:
                    if (action.equals(BEGIN_SCENE)) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c == 0) {
                String name = intent.getStringExtra("name");
                if (name == null || name == "") {
                    name = "default";
                }
                int mode = intent.getIntExtra("mode", -1);
                if (mode < 0 || mode > 2) {
                    mode = 0;
                    String modeStr = intent.getStringExtra("mode");
                    if ("Record".equalsIgnoreCase(modeStr) || "1".equals(modeStr)) {
                        mode = 1;
                    } else if ("Playback".equalsIgnoreCase(modeStr) || "2".equals(modeStr)) {
                        mode = 2;
                    }
                }
                AutoDriver.Initialize(name, mode);
            } else if (c == 1) {
                AutoDriver.BeginScene(intent.getIntExtra("scene", 0));
            } else if (c == 2) {
                AutoDriver.EndScene();
            } else if (c != 3) {
                String str2 = TAG;
                Log.w(str2, "Unhandled action " + action);
            } else {
                AutoDriver.Shutdown();
            }
        }
    }
}
