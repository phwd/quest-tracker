package X;

import java.io.IOException;

public class Tx extends AbstractC0131Ob<T> {
    public final /* synthetic */ AbstractC0131Ob A00;

    public Tx(AbstractC0131Ob ob) {
        this.A00 = ob;
    }

    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, T t) throws IOException {
        if (t == null) {
            mmVar.A0B();
        } else {
            this.A00.A03(mmVar, t);
        }
    }

    @Override // X.AbstractC0131Ob
    public final T A02(lk lkVar) throws IOException {
        if (lkVar.A0G() != AnonymousClass07.A08) {
            return (T) this.A00.A02(lkVar);
        }
        lkVar.A0P();
        return null;
    }
}
