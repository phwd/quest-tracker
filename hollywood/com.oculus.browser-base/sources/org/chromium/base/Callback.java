package org.chromium.base;

@FunctionalInterface
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public interface Callback {

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public abstract class Helper {
        public static void onBooleanResultFromNative(Callback callback, boolean z) {
            callback.onResult(Boolean.valueOf(z));
        }

        public static void onIntResultFromNative(Callback callback, int i) {
            callback.onResult(Integer.valueOf(i));
        }

        public static void onObjectResultFromNative(Callback callback, Object obj) {
            callback.onResult(obj);
        }

        public static void runRunnable(Runnable runnable) {
            runnable.run();
        }
    }

    Runnable a(Object obj);

    void onResult(Object obj);
}
