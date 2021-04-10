package com.facebook.tigon.iface;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class TigonErrorCodeUtil {
    public static String toString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "<Unknown>" : "RequestNotSupported" : "InvalidRequest" : "FatalError" : "TransientError" : "Cancel" : "None";
    }
}
