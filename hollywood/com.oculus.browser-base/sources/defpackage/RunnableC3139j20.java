package defpackage;

import java.util.Objects;
import org.chromium.base.task.PostTask;
import org.chromium.url.GURL;

/* renamed from: j20  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3139j20 implements Runnable {
    public final C4677s20 F;
    public final C4506r20 G;
    public final int H;
    public final C5922zL0 I;

    /* renamed from: J  reason: collision with root package name */
    public final GURL f10182J;

    public RunnableC3139j20(C4677s20 s20, C4506r20 r20, int i, C5922zL0 zl0, GURL gurl) {
        this.F = s20;
        this.G = r20;
        this.H = i;
        this.I = zl0;
        this.f10182J = gurl;
    }

    public void run() {
        C4677s20 s20 = this.F;
        C4506r20 r20 = this.G;
        int i = this.H;
        C5922zL0 zl0 = this.I;
        GURL gurl = this.f10182J;
        Objects.requireNonNull(s20);
        int f0 = s20.f0(zl0.f);
        N20 n20 = s20.H;
        gurl.h();
        "instantapp:holdback".equals(zl0.f);
        Objects.requireNonNull(n20);
        PostTask.b(Zo1.f9374a, new RunnableC3823n20(r20, i, f0), 0);
    }
}
