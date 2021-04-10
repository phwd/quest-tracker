package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.app.ChromeActivity;

/* renamed from: sz0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4837sz0 implements Runnable {
    public final C5177uz0 F;
    public final ZH0 G;
    public final AbstractC4448qj H;
    public final View$OnLayoutChangeListenerC6027zz0 I;

    /* renamed from: J  reason: collision with root package name */
    public final C0913Oz0 f11312J;
    public final AbstractC5007tz0 K;
    public final ChromeActivity L;
    public final AbstractC1422Xg1 M;

    public RunnableC4837sz0(C5177uz0 uz0, ZH0 zh0, AbstractC4448qj qjVar, View$OnLayoutChangeListenerC6027zz0 zz0, C0913Oz0 oz0, AbstractC5007tz0 tz0, ChromeActivity chromeActivity, AbstractC1422Xg1 xg1) {
        this.F = uz0;
        this.G = zh0;
        this.H = qjVar;
        this.I = zz0;
        this.f11312J = oz0;
        this.K = tz0;
        this.L = chromeActivity;
        this.M = xg1;
    }

    public void run() {
        C5177uz0 uz0 = this.F;
        ZH0 zh0 = this.G;
        AbstractC4448qj qjVar = this.H;
        View$OnLayoutChangeListenerC6027zz0 zz0 = this.I;
        C0913Oz0 oz0 = this.f11312J;
        AbstractC5007tz0 tz0 = this.K;
        ChromeActivity chromeActivity = this.L;
        AbstractC1422Xg1 xg1 = this.M;
        Objects.requireNonNull(uz0);
        zh0.b();
        C5638xj xjVar = (C5638xj) qjVar;
        xjVar.r(zz0);
        xjVar.p(oz0, true, 0);
        AB0 ab0 = (AB0) tz0;
        C5894zB0 zb0 = ab0.t;
        zb0.f11732a = false;
        zb0.b();
        ab0.j = null;
        chromeActivity.getWindow().getDecorView().removeOnLayoutChangeListener(zz0);
        zz0.destroy();
        ((C1544Zg1) xg1).b();
        uz0.b.destroy();
    }
}
