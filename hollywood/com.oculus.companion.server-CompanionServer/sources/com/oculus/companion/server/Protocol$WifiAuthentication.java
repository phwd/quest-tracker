package com.oculus.companion.server;

import com.google.protobuf.Internal;

public enum Protocol$WifiAuthentication implements Internal.EnumLite {
    NONE(1),
    EAP(2),
    WPA(3),
    WEP(4);
    
    private static final Internal.EnumLiteMap<Protocol$WifiAuthentication> internalValueMap = new Internal.EnumLiteMap<Protocol$WifiAuthentication>() {
        /* class com.oculus.companion.server.Protocol$WifiAuthentication.AnonymousClass1 */
    };
    private final int value;

    public final int getNumber() {
        return this.value;
    }

    public static Protocol$WifiAuthentication forNumber(int i) {
        if (i == 1) {
            return NONE;
        }
        if (i == 2) {
            return EAP;
        }
        if (i == 3) {
            return WPA;
        }
        if (i != 4) {
            return null;
        }
        return WEP;
    }

    private Protocol$WifiAuthentication(int i) {
        this.value = i;
    }
}
