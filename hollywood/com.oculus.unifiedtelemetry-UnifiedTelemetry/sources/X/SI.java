package X;

import java.io.IOException;

public class SI extends AbstractC0131Ob<T1> {
    public final /* synthetic */ SH A00;
    public final /* synthetic */ Class A01;

    public SI(SH sh, Class cls) {
        this.A00 = sh;
        this.A01 = cls;
    }

    @Override // X.AbstractC0131Ob
    public final T1 A02(lk lkVar) throws IOException {
        T1 t1 = (T1) this.A00.A00.A02(lkVar);
        if (t1 != null) {
            Class cls = this.A01;
            if (!cls.isInstance(t1)) {
                throw new U0(AnonymousClass06.A06("Expected a ", cls.getName(), " but was ", t1.getClass().getName()));
            }
        }
        return t1;
    }

    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, T1 t1) throws IOException {
        this.A00.A00.A03(mmVar, t1);
    }
}
