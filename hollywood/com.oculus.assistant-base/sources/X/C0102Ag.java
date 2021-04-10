package X;

/* renamed from: X.Ag  reason: case insensitive filesystem */
public final class C0102Ag {
    public final AbstractC0101Af A00;

    public C0102Ag(AbstractC0101Af af) {
        C0514bB.A02(af, "op");
        this.A00 = af;
    }

    public final void A00(Class cls, AbstractC0105Aj aj) {
        C0514bB.A02(cls, "messageType");
        C0514bB.A02(aj, "subscriber");
        AbstractC0101Af af = this.A00;
        if (af instanceof C0796hk) {
            C0112Aq.A00().A03(cls, aj);
        } else if (af instanceof C0797hl) {
            C0112Aq.A00().A04(cls, aj);
        }
    }
}
