package com.facebook.messenger.assistant.thrift;

import X.AnonymousClass08;
import com.facebook.hyperthrift.HyperThriftBase;

public final class AssistantAction extends HyperThriftBase {
    public final int A03() {
        int i = this.A00;
        if (i != -1) {
            return i;
        }
        throw new IllegalStateException(AnonymousClass08.A04(this.A02, " is not a union!"));
    }
}
