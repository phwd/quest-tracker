package com.facebook.assistant.oacr.trims;

import X.BQ;

public class StellaTrim implements BQ {
    public static native void loadNative();

    @Override // X.BQ
    public final void load() {
        loadNative();
    }
}
