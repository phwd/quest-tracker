package X;

import com.google.common.base.Absent;

/* renamed from: X.qZ  reason: case insensitive filesystem */
public final class C1006qZ implements AbstractC0238Ma {
    public final /* synthetic */ VQ A00;

    public C1006qZ(VQ vq) {
        this.A00 = vq;
    }

    @Override // X.AbstractC0238Ma
    public final void A43() {
        C0861ky kyVar = this.A00.A00;
        if (kyVar != null) {
            C0139Dd.A09("StreamingTtsPlayer", "Done");
            g1 g1Var = kyVar.A00.A00;
            if (g1Var != null) {
                C0740gP gPVar = g1Var.A00;
                gPVar.A0B = null;
                gPVar.A0h.A01(new i0(AnonymousClass09.A0J));
            }
        }
    }

    @Override // X.AbstractC0238Ma
    public final void onError(Exception exc) {
        C0861ky kyVar = this.A00.A00;
        if (kyVar != null) {
            kyVar.A00(exc);
        }
    }

    @Override // X.AbstractC0238Ma
    public final void onStart() {
        C0861ky kyVar = this.A00.A00;
        if (kyVar != null) {
            C0139Dd.A09("StreamingTtsPlayer", "Start");
            g1 g1Var = kyVar.A00.A00;
            if (g1Var != null) {
                g1Var.A00.A0h.A01(new i0(AnonymousClass09.A0C));
                C00949y.A00.A02(Absent.INSTANCE);
            }
        }
    }
}
