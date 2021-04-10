package com.facebook.soloader;

/* access modifiers changed from: package-private */
public class MergedSoMapping {
    static String mapLibName(String preMergedLibName) {
        return null;
    }

    static void invokeJniOnload(String preMergedLibName) {
        throw new IllegalArgumentException("Unknown library: " + preMergedLibName);
    }
}
