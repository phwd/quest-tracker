package com.oculus.companion.server;

import com.google.protobuf.Internal;

public enum Protocol$ControllerState implements Internal.EnumLite {
    NOT_PAIRED(1),
    PAIRED_BUT_INACTIVE(2),
    PAIRED_AND_ACTIVE(3);
    
    private static final Internal.EnumLiteMap<Protocol$ControllerState> internalValueMap = new Internal.EnumLiteMap<Protocol$ControllerState>() {
        /* class com.oculus.companion.server.Protocol$ControllerState.AnonymousClass1 */
    };
    private final int value;

    public final int getNumber() {
        return this.value;
    }

    public static Protocol$ControllerState forNumber(int i) {
        if (i == 1) {
            return NOT_PAIRED;
        }
        if (i == 2) {
            return PAIRED_BUT_INACTIVE;
        }
        if (i != 3) {
            return null;
        }
        return PAIRED_AND_ACTIVE;
    }

    private Protocol$ControllerState(int i) {
        this.value = i;
    }
}
