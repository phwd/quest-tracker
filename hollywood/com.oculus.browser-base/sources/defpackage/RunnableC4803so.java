package defpackage;

/* renamed from: so  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4803so implements Runnable {
    public final /* synthetic */ int F;
    public final /* synthetic */ BinderC5143uo G;

    public RunnableC4803so(BinderC5143uo uoVar, int i) {
        this.G = uoVar;
        this.F = i;
    }

    public void run() {
        C5653xo xoVar = this.G.b;
        int i = this.F;
        int i2 = xoVar.r;
        if (i2 != 0) {
            AbstractC1220Ua0.a("ChildProcessConn", "sendPid was called more than once: pid=%d", Integer.valueOf(i2));
            return;
        }
        xoVar.r = i;
        C0098Bo bo = xoVar.m;
        if (bo != null) {
            bo.a(xoVar);
        }
        xoVar.m = null;
    }
}
