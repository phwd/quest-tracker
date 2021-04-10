package X;

import java.io.IOException;

public class U6<T> extends AbstractC0131Ob<T> {
    public AbstractC0131Ob<T> A00;

    @Override // X.AbstractC0131Ob
    public final T A02(lk lkVar) throws IOException {
        AbstractC0131Ob<T> ob = this.A00;
        if (ob != null) {
            return ob.A02(lkVar);
        }
        throw new IllegalStateException();
    }

    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, T t) throws IOException {
        AbstractC0131Ob<T> ob = this.A00;
        if (ob != null) {
            ob.A03(mmVar, t);
            return;
        }
        throw new IllegalStateException();
    }
}
