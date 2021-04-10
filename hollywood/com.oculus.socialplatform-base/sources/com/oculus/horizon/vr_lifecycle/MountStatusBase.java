package com.oculus.horizon.vr_lifecycle;

import android.content.Context;

public abstract class MountStatusBase {

    public interface Callback {
        void onMountStatusChanged(boolean z);
    }

    public abstract boolean isRunning();

    public abstract void start(Context context, Callback callback);

    public abstract void teardown();
}
