package defpackage;

/* renamed from: af  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC1693af implements Runnable {
    public final /* synthetic */ boolean F;
    public final /* synthetic */ C1873bf G;

    public RunnableC1693af(C1873bf bfVar, boolean z) {
        this.G = bfVar;
        this.F = z;
    }

    public void run() {
        C1873bf bfVar = this.G;
        if (!(bfVar.f9551a.G.get(Integer.valueOf(bfVar.c.getJobId())) == bfVar.b)) {
            AbstractC1220Ua0.a("BkgrdTaskJS", "Tried finishing non-current BackgroundTask.", new Object[0]);
            return;
        }
        C1873bf bfVar2 = this.G;
        bfVar2.f9551a.G.remove(Integer.valueOf(bfVar2.c.getJobId()));
        C1873bf bfVar3 = this.G;
        bfVar3.f9551a.jobFinished(bfVar3.c, this.F);
    }
}
