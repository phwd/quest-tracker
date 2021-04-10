package com.oculus.browser;

import android.content.Context;
import android.os.StrictMode;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class VrApi {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f9713a;

    public static void a(Context context) {
        AbstractC1220Ua0.d("VrApi", "initialize, context = " + context, new Object[0]);
        if (!f9713a || !nativeIsUsingContextVrApi(context)) {
            AbstractC1220Ua0.d("VrApi", "initializeWithContext, context = " + context, new Object[0]);
            StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().penaltyLog().build());
            try {
                nativeInitVrApi(context);
            } catch (IllegalArgumentException e) {
                AbstractC1220Ua0.a("VrApi", "Unable to init VrApi", e);
            } catch (Throwable th) {
                StrictMode.setThreadPolicy(threadPolicy);
                throw th;
            }
            StrictMode.setThreadPolicy(threadPolicy);
            f9713a = true;
        }
    }

    public static native void nativeInitVrApi(Context context);

    public static native boolean nativeIsUsingContextVrApi(Context context);

    public static native void nativeShutdownVrApi();
}
