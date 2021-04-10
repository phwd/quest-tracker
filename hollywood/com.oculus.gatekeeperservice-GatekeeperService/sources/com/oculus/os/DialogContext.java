package com.oculus.os;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.ServiceConnection;
import android.view.Window;
import oculus.internal.ui.VrBase;

public class DialogContext extends ContextWrapper {
    private final Window mWindow;

    public DialogContext(Context context, Window window) {
        super(context);
        VrBase.scaleDensity(context);
        this.mWindow = window;
    }

    public void unregisterReceiver(BroadcastReceiver receiver) {
        try {
            super.unregisterReceiver(receiver);
        } catch (IllegalArgumentException e) {
        }
    }

    public void unbindService(ServiceConnection conn) {
        try {
            super.unbindService(conn);
        } catch (IllegalArgumentException e) {
        }
    }

    public Window getWindow() {
        return this.mWindow;
    }
}
