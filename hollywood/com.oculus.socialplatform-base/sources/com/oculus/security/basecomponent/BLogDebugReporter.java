package com.oculus.security.basecomponent;

import X.AbstractC02660jW;
import javax.annotation.Nullable;

public class BLogDebugReporter implements AbstractC02660jW {
    public String mTag;

    public BLogDebugReporter(String str) {
        this.mTag = str;
    }

    @Override // X.AbstractC02660jW
    public void report(String str) {
    }

    @Override // X.AbstractC02660jW
    public void report(String str, String str2, @Nullable Throwable th) {
    }
}
