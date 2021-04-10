package defpackage;

/* renamed from: Yn  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC1498Yn implements Runnable {
    public final /* synthetic */ C5653xo F;
    public final /* synthetic */ C1559Zn G;

    public RunnableC1498Yn(C1559Zn zn, C5653xo xoVar) {
        this.G = zn;
        this.F = xoVar;
    }

    public void run() {
        Runnable runnable;
        AbstractC2412eo eoVar = this.G.b;
        eoVar.d(this.F);
        if (!eoVar.b.isEmpty()) {
            ((Runnable) eoVar.b.remove()).run();
            if (!eoVar.b.isEmpty() && (runnable = eoVar.f9880a) != null) {
                runnable.run();
            }
        }
    }
}
