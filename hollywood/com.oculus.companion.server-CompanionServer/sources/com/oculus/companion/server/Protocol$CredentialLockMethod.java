package com.oculus.companion.server;

import com.google.protobuf.Internal;

public enum Protocol$CredentialLockMethod implements Internal.EnumLite {
    PATTERN(0),
    PASSWORD(1);
    
    private static final Internal.EnumLiteMap<Protocol$CredentialLockMethod> internalValueMap = new Internal.EnumLiteMap<Protocol$CredentialLockMethod>() {
        /* class com.oculus.companion.server.Protocol$CredentialLockMethod.AnonymousClass1 */
    };
    private final int value;

    public final int getNumber() {
        return this.value;
    }

    public static Protocol$CredentialLockMethod forNumber(int i) {
        if (i == 0) {
            return PATTERN;
        }
        if (i != 1) {
            return null;
        }
        return PASSWORD;
    }

    private Protocol$CredentialLockMethod(int i) {
        this.value = i;
    }
}
