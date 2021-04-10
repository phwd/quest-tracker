package org.chromium.mojo.system.impl;

import java.io.Closeable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BaseRunLoop implements Closeable {
    public static void runRunnable(Runnable runnable) {
        runnable.run();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }
}
