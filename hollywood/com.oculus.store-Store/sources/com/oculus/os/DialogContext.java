package com.oculus.os;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.ServiceConnection;
import android.view.Window;

public class DialogContext extends ContextWrapper {
    public DialogContext(Context context, Window window) {
        super(null);
        throw new RuntimeException("Stub!");
    }

    public void unregisterReceiver(BroadcastReceiver receiver) {
        throw new RuntimeException("Stub!");
    }

    public void unbindService(ServiceConnection conn) {
        throw new RuntimeException("Stub!");
    }

    public Window getWindow() {
        throw new RuntimeException("Stub!");
    }
}
