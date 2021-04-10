package defpackage;

/* renamed from: We  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC1353We implements Runnable {
    public final /* synthetic */ boolean F;
    public final /* synthetic */ C1414Xe G;

    public RunnableC1353We(C1414Xe xe, boolean z) {
        this.G = xe;
        this.F = z;
    }

    public void run() {
        C1475Ye ye = this.G.f9223a;
        ye.c = this.F;
        ye.f9284a.countDown();
    }
}
