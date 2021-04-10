package com.oculus.os;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.ServiceConnection;
import android.view.Window;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogContext extends ContextWrapper {
    public DialogContext(Context context, Window window) {
        super(null);
        throw new RuntimeException("Stub!");
    }

    public Window getWindow() {
        throw new RuntimeException("Stub!");
    }

    public void unbindService(ServiceConnection serviceConnection) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterReceiver(BroadcastReceiver broadcastReceiver) {
        throw new RuntimeException("Stub!");
    }
}
