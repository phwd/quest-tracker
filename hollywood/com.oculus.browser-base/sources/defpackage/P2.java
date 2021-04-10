package defpackage;

import android.util.Log;
import java.lang.reflect.Method;

/* renamed from: P2  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class P2 implements Runnable {
    public final /* synthetic */ Object F;
    public final /* synthetic */ Object G;

    public P2(Object obj, Object obj2) {
        this.F = obj;
        this.G = obj2;
    }

    public void run() {
        try {
            Method method = R2.d;
            if (method != null) {
                method.invoke(this.F, this.G, Boolean.FALSE, "AppCompat recreation");
                return;
            }
            R2.e.invoke(this.F, this.G, Boolean.FALSE);
        } catch (RuntimeException e) {
            if (e.getClass() == RuntimeException.class && e.getMessage() != null && e.getMessage().startsWith("Unable to stop")) {
                throw e;
            }
        } catch (Throwable th) {
            Log.e("ActivityRecreator", "Exception while invoking performStopActivity", th);
        }
    }
}
