package defpackage;

/* renamed from: Xa  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1402Xa implements Runnable {
    public final AbstractC2032cb F;
    public final Object G;

    public RunnableC1402Xa(AbstractC2032cb cbVar, Object obj) {
        this.F = cbVar;
        this.G = obj;
    }

    public void run() {
        AbstractC2032cb cbVar = this.F;
        Object obj = this.G;
        if (cbVar.h()) {
            cbVar.j(obj);
        } else {
            cbVar.k(obj);
        }
        cbVar.f = 2;
    }
}
