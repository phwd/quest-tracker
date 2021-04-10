package com.oculus.companion.server;

import com.google.protobuf.Internal;

public enum Protocol$VerifySuccessReason implements Internal.EnumLite {
    CONNECTED(0),
    UPDATING(1),
    UPDATE_PENDING(2),
    RECENTLY_CONNECTED(3);
    
    private static final Internal.EnumLiteMap<Protocol$VerifySuccessReason> internalValueMap = new Internal.EnumLiteMap<Protocol$VerifySuccessReason>() {
        /* class com.oculus.companion.server.Protocol$VerifySuccessReason.AnonymousClass1 */
    };
    private final int value;

    public final int getNumber() {
        return this.value;
    }

    public static Protocol$VerifySuccessReason forNumber(int i) {
        if (i == 0) {
            return CONNECTED;
        }
        if (i == 1) {
            return UPDATING;
        }
        if (i == 2) {
            return UPDATE_PENDING;
        }
        if (i != 3) {
            return null;
        }
        return RECENTLY_CONNECTED;
    }

    private Protocol$VerifySuccessReason(int i) {
        this.value = i;
    }
}
