package X;

import android.os.Trace;

/* renamed from: X.pZ  reason: case insensitive filesystem */
public abstract class AbstractC0957pZ implements AbstractC0210Jy, K3 {
    public final K8 A00;

    public AbstractC0957pZ(K8 k8) {
        C0514bB.A02(k8, "config");
        this.A00 = k8;
    }

    public final C0958pa A00() {
        C0954pW pWVar = ((K5) this.A00.A00.getValue()).A01;
        C0138Cy cy = pWVar.A00;
        Trace.beginSection("DirectTransactionExecutor.startTransaction");
        C0836jg jgVar = new C0836jg(cy.A01, cy.A00);
        jgVar.A01.beginTransaction();
        C0136Cw A002 = C0836jg.A00(jgVar);
        A002.A00++;
        if (A002.A01 == null) {
            A002.A01 = jgVar;
        }
        return new C0958pa(this, new C0953pV(pWVar, jgVar));
    }
}
