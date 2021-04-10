package org.chromium.chrome.modules.stack_unwinder;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class StackUnwinderModuleProvider {
    public static void ensureNativeLoaded() {
        JZ0.f8297a.a();
    }

    public static long getCreateMemoryRegionsMapFunction() {
        return ((KZ0) JZ0.f8297a.b()).b();
    }

    public static long getCreateNativeUnwinderFunction() {
        return ((KZ0) JZ0.f8297a.b()).a();
    }

    public static void installModule() {
        JZ0.f8297a.d(new LZ0());
    }

    public static boolean isModuleInstalled() {
        return JZ0.f8297a.f();
    }
}
