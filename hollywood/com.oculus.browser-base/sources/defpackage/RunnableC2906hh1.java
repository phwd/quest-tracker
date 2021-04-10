package defpackage;

import J.N;
import android.os.SystemClock;
import org.chromium.content.browser.input.ImeAdapterImpl;

/* renamed from: hh1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC2906hh1 implements Runnable {
    public final /* synthetic */ int F;
    public final /* synthetic */ int G;
    public final /* synthetic */ C5464wh1 H;

    public RunnableC2906hh1(C5464wh1 wh1, int i, int i2) {
        this.H = wh1;
        this.F = i;
        this.G = i2;
    }

    public void run() {
        C5464wh1 wh1 = this.H;
        if (wh1.j != 0) {
            C5464wh1.a(wh1);
        }
        ImeAdapterImpl imeAdapterImpl = this.H.f;
        int i = this.F;
        int i2 = this.G;
        imeAdapterImpl.w0();
        if (imeAdapterImpl.v0()) {
            N.M1qwlrOP(imeAdapterImpl.F, imeAdapterImpl, null, 7, 0, SystemClock.uptimeMillis(), 229, 0, false, 0);
            N.Mvb046o_(imeAdapterImpl.F, imeAdapterImpl, i, i2);
            N.M1qwlrOP(imeAdapterImpl.F, imeAdapterImpl, null, 9, 0, SystemClock.uptimeMillis(), 229, 0, false, 0);
        }
    }
}
