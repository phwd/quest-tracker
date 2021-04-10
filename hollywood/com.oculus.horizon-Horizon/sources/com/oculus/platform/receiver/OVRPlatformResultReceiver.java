package com.oculus.platform.receiver;

import android.os.Bundle;
import android.os.ResultReceiver;

public class OVRPlatformResultReceiver extends ResultReceiver {
    public final Listener mOnReceiveResult;

    public interface Listener {
        void onReceiveResult(int i, Bundle bundle);
    }

    public OVRPlatformResultReceiver(Listener listener) {
        super(null);
        this.mOnReceiveResult = listener;
    }

    public void onReceiveResult(int i, Bundle bundle) {
        super.onReceiveResult(i, bundle);
        this.mOnReceiveResult.onReceiveResult(i, bundle);
    }
}
