package defpackage;

/* renamed from: Di0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0208Di0 implements Runnable {
    public final C0330Fi0 F;

    public RunnableC0208Di0(C0330Fi0 fi0) {
        this.F = fi0;
    }

    public void run() {
        C0330Fi0 fi0 = this.F;
        if (!((Boolean) fi0.o.get()).booleanValue() && fi0.m) {
            if (C2249dq1.a().f.b != null) {
                fi0.b(true);
                fi0.i.run();
            } else if (((C0391Gi0) fi0.h.g(AbstractC0513Ii0.g)).f8103a) {
                fi0.a(true);
            }
        }
    }
}
