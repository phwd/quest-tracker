package X;

/* renamed from: X.pb  reason: case insensitive filesystem */
public class C0959pb implements AbstractC0211Jz {
    public final KB A00;

    public C0959pb(KB kb) {
        C0514bB.A02(kb, "mutateOp");
        this.A00 = kb;
    }

    @Override // X.AbstractC0211Jz
    public final long A1o() {
        KB kb = this.A00;
        return ((Number) kb.A00.A00(AnonymousClass09.A0J, new CE(kb))).longValue();
    }
}
