package com.facebook.profilo.logger.api;

public final class ProfiloLogger {
    public static int classLoadFailed() {
        return -1;
    }

    public static int classLoadStart() {
        return -1;
    }

    public static int classLoadEnd(Class cls) {
        return -1;
    }
}
