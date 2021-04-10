package com.oculus.os;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.IBinder;
import android.view.View;

public class WindowTokenContext extends ContextWrapper {
    private final View overlayView;

    public WindowTokenContext(Context context, View view) {
        super(context);
        this.overlayView = view;
    }

    public IBinder getWindowToken() {
        return this.overlayView.getWindowId().getTarget().asBinder();
    }
}
