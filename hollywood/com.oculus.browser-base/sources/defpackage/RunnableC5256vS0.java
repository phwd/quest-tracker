package defpackage;

import android.os.Handler;
import java.util.concurrent.Callable;

/* renamed from: vS0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5256vS0 implements Runnable {
    public final /* synthetic */ Callable F;
    public final /* synthetic */ Handler G;
    public final /* synthetic */ AbstractC5596xS0 H;

    public RunnableC5256vS0(C5766yS0 ys0, Callable callable, Handler handler, AbstractC5596xS0 xs0) {
        this.F = callable;
        this.G = handler;
        this.H = xs0;
    }

    public void run() {
        Object obj;
        try {
            obj = this.F.call();
        } catch (Exception unused) {
            obj = null;
        }
        this.G.post(new RunnableC5086uS0(this, obj));
    }
}
