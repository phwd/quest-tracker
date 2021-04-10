package X;

public final class A1 extends AbstractC1279wZ {
    public static final Wd A01 = new Wd();
    public AbstractC0105Aj A00;

    @Override // X.AbstractC0400Wb
    public final void A06() {
        this.A00 = new C1270wQ(this);
        C0112Aq A002 = C0112Aq.A00();
        AbstractC0105Aj aj = this.A00;
        if (aj == null) {
            C0514bB.A03("assistantTimeSubscriber");
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } else {
            A002.A02(C1296wq.class, aj);
        }
    }
}
