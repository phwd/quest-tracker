package defpackage;

import android.os.Handler;
import android.os.Looper;
import org.chromium.base.ContextUtils;

/* renamed from: ME0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ME0 implements Runnable {
    public final Handler F = new Handler(Looper.getMainLooper());
    public int G = 0;

    public void run() {
        if (this.G == 1) {
            this.G = 3;
            C3279js0.f(ContextUtils.getApplicationContext(), 0);
        }
    }
}
