package com.oculus.companion.server;

import com.google.protobuf.Internal;

public enum Protocol$ControllerType implements Internal.EnumLite {
    PRIMARY(0),
    SECONDARY(1),
    THIRD_PARTY(2);
    
    private static final Internal.EnumLiteMap<Protocol$ControllerType> internalValueMap = new Internal.EnumLiteMap<Protocol$ControllerType>() {
        /* class com.oculus.companion.server.Protocol$ControllerType.AnonymousClass1 */
    };
    private final int value;

    public final int getNumber() {
        return this.value;
    }

    public static Protocol$ControllerType forNumber(int i) {
        if (i == 0) {
            return PRIMARY;
        }
        if (i == 1) {
            return SECONDARY;
        }
        if (i != 2) {
            return null;
        }
        return THIRD_PARTY;
    }

    private Protocol$ControllerType(int i) {
        this.value = i;
    }
}
