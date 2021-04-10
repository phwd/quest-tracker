package com.facebook.assistant.oacr.trims;

import X.BQ;

public class PortalTrim implements BQ {
    public static native void loadNative();

    @Override // X.BQ
    public final void load() {
        loadNative();
    }
}
