package defpackage;

/* renamed from: pF1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class RunnableC4205pF1 implements Runnable {
    public final /* synthetic */ C3350kF1 F;
    public final /* synthetic */ String G;
    public final /* synthetic */ String H;

    public RunnableC4205pF1(C3350kF1 kf1, String str, String str2) {
        this.F = kf1;
        this.G = str;
        this.H = str2;
    }

    public final void run() {
        AbstractC1313Vm vm;
        synchronized (this.F.f10268J) {
            vm = (AbstractC1313Vm) this.F.f10268J.get(this.G);
        }
        if (vm != null) {
            vm.a(this.F.H, this.G, this.H);
            return;
        }
        NF1 nf1 = C3350kF1.D;
        Object[] objArr = {this.G};
        if (nf1.d()) {
            nf1.c("Discarded message for unknown namespace '%s'", objArr);
        }
    }
}
