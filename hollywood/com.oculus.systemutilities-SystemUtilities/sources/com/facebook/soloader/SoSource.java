package com.facebook.soloader;

import android.os.StrictMode;
import java.io.IOException;

public abstract class SoSource {
    public abstract int loadLibrary(String str, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException;

    /* access modifiers changed from: protected */
    public void prepare(int flags) throws IOException {
    }

    public String toString() {
        return getClass().getName();
    }
}
