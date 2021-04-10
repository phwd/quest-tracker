package defpackage;

import android.os.StrictMode;
import java.util.concurrent.Callable;

/* renamed from: VF1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class VF1 {
    /* JADX INFO: finally extract failed */
    public static Object a(Callable callable) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        try {
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
            Object call = callable.call();
            StrictMode.setThreadPolicy(threadPolicy);
            return call;
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(threadPolicy);
            throw th;
        }
    }
}
