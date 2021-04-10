package com.facebook.assistant.oacr.trims;

import X.BQ;

public class PassThroughTrim implements BQ {
    public static native void loadNative();

    @Override // X.BQ
    public void load() {
        loadNative();
    }
}
