package com.oculus.companion.server;

import com.google.protobuf.Internal;

public enum Protocol$ControllerHandedness implements Internal.EnumLite {
    UNSPECIFIED(0),
    LEFT(1),
    RIGHT(2);
    
    private static final Internal.EnumLiteMap<Protocol$ControllerHandedness> internalValueMap = new Internal.EnumLiteMap<Protocol$ControllerHandedness>() {
        /* class com.oculus.companion.server.Protocol$ControllerHandedness.AnonymousClass1 */
    };
    private final int value;

    public final int getNumber() {
        return this.value;
    }

    public static Protocol$ControllerHandedness forNumber(int i) {
        if (i == 0) {
            return UNSPECIFIED;
        }
        if (i == 1) {
            return LEFT;
        }
        if (i != 2) {
            return null;
        }
        return RIGHT;
    }

    private Protocol$ControllerHandedness(int i) {
        this.value = i;
    }
}
