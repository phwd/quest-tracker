package com.oculus.companion.server;

import com.google.protobuf.Internal;

public enum Protocol$ReachabilityStatus implements Internal.EnumLite {
    OK(1),
    OCULUS_SERVER_UNREACHABLE(2),
    INTERNET_UNREACHABLE(3),
    ROUTER_UNREACHABLE(4);
    
    private static final Internal.EnumLiteMap<Protocol$ReachabilityStatus> internalValueMap = new Internal.EnumLiteMap<Protocol$ReachabilityStatus>() {
        /* class com.oculus.companion.server.Protocol$ReachabilityStatus.AnonymousClass1 */
    };
    private final int value;

    public final int getNumber() {
        return this.value;
    }

    public static Protocol$ReachabilityStatus forNumber(int i) {
        if (i == 1) {
            return OK;
        }
        if (i == 2) {
            return OCULUS_SERVER_UNREACHABLE;
        }
        if (i == 3) {
            return INTERNET_UNREACHABLE;
        }
        if (i != 4) {
            return null;
        }
        return ROUTER_UNREACHABLE;
    }

    private Protocol$ReachabilityStatus(int i) {
        this.value = i;
    }
}
