package com.oculus.os;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.IBinder;
import android.view.View;

public class WindowTokenContext extends ContextWrapper {
    public WindowTokenContext(Context context, View view) {
        super(null);
        throw new RuntimeException("Stub!");
    }

    public IBinder getWindowToken() {
        throw new RuntimeException("Stub!");
    }
}
