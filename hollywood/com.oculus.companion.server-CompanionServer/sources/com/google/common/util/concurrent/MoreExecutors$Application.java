package com.google.common.util.concurrent;

class MoreExecutors$Application {
    MoreExecutors$Application() {
    }

    /* access modifiers changed from: package-private */
    public void addShutdownHook(Thread thread) {
        Runtime.getRuntime().addShutdownHook(thread);
    }
}
