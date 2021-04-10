package com.oculus.svclib.receiver;

import android.os.ResultReceiver;

public class OVREntitlementReceiver extends ResultReceiver {
    public final Listener mOnReceiveResult;

    public interface Listener {
    }
}
