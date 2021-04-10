package X;

import java.io.IOException;
import java.util.Map;

public final class T6<T> extends AbstractC0131Ob<T> {
    public final VE<T> A00;
    public final Map<String, AbstractC0419hU> A01;

    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, T t) throws IOException {
        if (t == null) {
            mmVar.A0B();
            return;
        }
        mmVar.A08();
        try {
            for (AbstractC0419hU hUVar : this.A01.values()) {
                if (hUVar.A02(t)) {
                    mmVar.A0F(hUVar.A02);
                    hUVar.A01(mmVar, t);
                }
            }
            mmVar.A0A();
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }

    public T6(VE<T> ve, Map<String, AbstractC0419hU> map) {
        this.A00 = ve;
        this.A01 = map;
    }

    @Override // X.AbstractC0131Ob
    public final T A02(lk lkVar) throws IOException {
        if (lkVar.A0G() == AnonymousClass07.A08) {
            lkVar.A0P();
            return null;
        }
        T A1b = this.A00.A1b();
        try {
            lkVar.A0M();
            while (lkVar.A0R()) {
                AbstractC0419hU hUVar = this.A01.get(lkVar.A0I());
                if (hUVar == null || !hUVar.A00) {
                    lkVar.A0Q();
                } else {
                    hUVar.A00(lkVar, A1b);
                }
            }
            lkVar.A0O();
            return A1b;
        } catch (IllegalStateException e) {
            throw new U0(e);
        } catch (IllegalAccessException e2) {
            throw new AssertionError(e2);
        }
    }
}
