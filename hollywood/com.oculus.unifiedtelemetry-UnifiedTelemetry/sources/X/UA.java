package X;

import java.io.IOException;

public class UA extends AbstractC0131Ob<Number> {
    public final /* synthetic */ HY A00;

    public UA(HY hy) {
        this.A00 = hy;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, Number number) throws IOException {
        Number number2 = number;
        if (number2 == null) {
            mmVar.A0B();
            return;
        }
        HY.A01((double) number2.floatValue());
        mmVar.A0E(number2);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final Number A02(lk lkVar) throws IOException {
        if (lkVar.A0G() != AnonymousClass07.A08) {
            return Float.valueOf((float) lkVar.A0C());
        }
        lkVar.A0P();
        return null;
    }
}
