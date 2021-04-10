package com.oculus.companion.server;

import com.google.protobuf.Internal;

public enum Protocol$ResponseCode implements Internal.EnumLite {
    SUCCESS(0),
    FAIL(1),
    FAIL_RETRY(2);
    
    private static final Internal.EnumLiteMap<Protocol$ResponseCode> internalValueMap = new Internal.EnumLiteMap<Protocol$ResponseCode>() {
        /* class com.oculus.companion.server.Protocol$ResponseCode.AnonymousClass1 */
    };
    private final int value;

    public final int getNumber() {
        return this.value;
    }

    public static Protocol$ResponseCode forNumber(int i) {
        if (i == 0) {
            return SUCCESS;
        }
        if (i == 1) {
            return FAIL;
        }
        if (i != 2) {
            return null;
        }
        return FAIL_RETRY;
    }

    private Protocol$ResponseCode(int i) {
        this.value = i;
    }
}
