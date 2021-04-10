package com.google.protobuf;

import com.google.protobuf.MessageLite;

public abstract class ExtensionLite<ContainingType extends MessageLite, Type> {
    /* access modifiers changed from: package-private */
    public boolean isLite() {
        return true;
    }
}
