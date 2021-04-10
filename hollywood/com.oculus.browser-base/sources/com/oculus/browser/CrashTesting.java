package com.oculus.browser;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CrashTesting {
    public static void throwJNIExceptionForTesting() {
        throw new Error("JNI Exception for testing");
    }

    public static void throwJavaExceptionFromBackgroundThreadForTesting() {
        new Thread(new RunnableC2311eB()).start();
    }
}
