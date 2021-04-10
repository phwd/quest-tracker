package defpackage;

/* renamed from: Ik0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0518Ik0 implements Runnable {
    public final C0640Kk0 F;
    public final ZH0 G;
    public final AbstractC4448qj H;
    public final C1371Wk0 I;

    public RunnableC0518Ik0(C0640Kk0 kk0, ZH0 zh0, AbstractC4448qj qjVar, C1371Wk0 wk0) {
        this.F = kk0;
        this.G = zh0;
        this.H = qjVar;
        this.I = wk0;
    }

    public void run() {
        C0640Kk0 kk0 = this.F;
        ZH0 zh0 = this.G;
        AbstractC4448qj qjVar = this.H;
        C1371Wk0 wk0 = this.I;
        View$OnClickListenerC1249Uk0 uk0 = kk0.f8383a;
        uk0.M.removeCallbacksAndMessages(null);
        uk0.L.cancel();
        zh0.b();
        C5638xj xjVar = (C5638xj) qjVar;
        xjVar.r(kk0.f8383a);
        xjVar.p(wk0, true, 0);
    }
}
