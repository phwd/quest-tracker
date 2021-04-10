package com.oculus.clay.app;

import android.content.Intent;
import android.content.IntentFilter;

public class IPCDebugCommandHandler {
    public static final IntentFilter a = new IntentFilter("com.oculus.shellenv.SHELL_ENV_CMD");

    public static boolean a(Intent intent) {
        if (!a(intent.getAction())) {
            return false;
        }
        String stringExtra = intent.getStringExtra("cmd");
        new StringBuilder("received debug IPC command ").append(stringExtra);
        if (stringExtra == null) {
            return true;
        }
        nativeSendIPCCommand(stringExtra);
        return true;
    }

    private static boolean a(String str) {
        return str != null && "com.oculus.shellenv.SHELL_ENV_CMD".equals(str);
    }

    public static boolean b(Intent intent) {
        return a(intent.getAction()) && intent.getBooleanExtra("assetBrowserMode", false);
    }

    private static native void nativeSendIPCCommand(String str);
}
