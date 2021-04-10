package com.facebook.soloader;

import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
public class MergedSoMapping {
    MergedSoMapping() {
    }

    @Nullable
    static String mapLibName(String preMergedLibName) {
        return null;
    }

    static void invokeJniOnload(String preMergedLibName) {
        throw new IllegalArgumentException("Unknown library: " + preMergedLibName);
    }
}
