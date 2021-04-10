package defpackage;

/* renamed from: iV  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC3044iV implements Runnable {
    public final /* synthetic */ C3214jV F;

    public RunnableC3044iV(C3214jV jVVar) {
        this.F = jVVar;
    }

    public void run() {
        try {
            C3214jV jVVar = this.F;
            jVVar.f10208a.removeUpdates(jVVar);
        } catch (Exception e) {
            if (!this.F.d) {
                throw e;
            }
        }
        AbstractC3385kV.f10283a = null;
    }
}
