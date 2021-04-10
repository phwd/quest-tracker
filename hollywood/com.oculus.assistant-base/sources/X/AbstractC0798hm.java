package X;

/* renamed from: X.hm  reason: case insensitive filesystem */
public abstract class AbstractC0798hm implements AbstractC0103Ah {
    private final void A00(C0102Ag ag) {
        if (!(this instanceof C0648ds)) {
            C0651dw dwVar = (C0651dw) this;
            C0514bB.A02(ag, "opExecutor");
            ag.A00(C0815iI.class, dwVar.A02);
            ag.A00(C0822iS.class, dwVar.A04);
            ag.A00(C0816iL.class, dwVar.A03);
            return;
        }
        C0514bB.A02(ag, "opExecutor");
        C0790hd hdVar = ((C0648ds) this).A01;
        C0514bB.A02(hdVar, "subscriber");
        AbstractC0101Af af = ag.A00;
        if (af instanceof C0796hk) {
            C0112Aq.A00().A02(C0821iR.class, hdVar);
        } else if (af instanceof C0797hl) {
            C0112Aq.A00().A04(C0821iR.class, hdVar);
        }
    }

    @Override // X.AbstractC0103Ah
    public final void A3e() {
        A00(new C0102Ag(C0796hk.A00));
    }

    @Override // X.AbstractC0103Ah
    public final void A3g() {
        A00(new C0102Ag(C0797hl.A00));
    }
}
