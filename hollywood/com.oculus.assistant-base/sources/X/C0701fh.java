package X;

/* renamed from: X.fh  reason: case insensitive filesystem */
public final class C0701fh implements AbstractC0105Aj {
    public final /* synthetic */ C0740gP A00;

    public C0701fh(C0740gP gPVar) {
        this.A00 = gPVar;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C00929w.A00.A00("OacrTtsStartMessage", ai.A00);
        C00879r.A00.A05(EnumC00909u.ACP_TTS_START_RECEIVED);
        C1004qX qXVar = new C1004qX();
        C0820iQ iQVar = (C0820iQ) ak.A2L();
        qXVar.mMimeType = iQVar.A01;
        l0 l0Var = (l0) iQVar.A00;
        if (l0Var != null) {
            l0Var.A05(qXVar);
            l0Var.A01();
        }
    }
}
