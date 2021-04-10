package com.oculus.companion.server;

import com.google.protobuf.Internal;

public enum Protocol$ProvisionType implements Internal.EnumLite {
    UNPROVISIONED(0),
    PROTOTYPE_PROVISIONED(1),
    FACTORY_PROVISIONED(2),
    UNKNOWN_PROVISIONING(3);
    
    private static final Internal.EnumLiteMap<Protocol$ProvisionType> internalValueMap = new Internal.EnumLiteMap<Protocol$ProvisionType>() {
        /* class com.oculus.companion.server.Protocol$ProvisionType.AnonymousClass1 */
    };
    private final int value;

    public final int getNumber() {
        return this.value;
    }

    public static Protocol$ProvisionType forNumber(int i) {
        if (i == 0) {
            return UNPROVISIONED;
        }
        if (i == 1) {
            return PROTOTYPE_PROVISIONED;
        }
        if (i == 2) {
            return FACTORY_PROVISIONED;
        }
        if (i != 3) {
            return null;
        }
        return UNKNOWN_PROVISIONING;
    }

    private Protocol$ProvisionType(int i) {
        this.value = i;
    }
}
