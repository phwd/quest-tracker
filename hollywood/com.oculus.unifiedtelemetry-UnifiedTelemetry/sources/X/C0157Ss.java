package X;

import java.io.IOException;

/* renamed from: X.Ss  reason: case insensitive filesystem */
public class C0157Ss extends AbstractC0131Ob<Number> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, Number number) throws IOException {
        mmVar.A0E(number);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final Number A02(lk lkVar) throws IOException {
        Integer A0G = lkVar.A0G();
        switch (A0G.intValue()) {
            case 5:
            case 6:
                return new C0190Ut(lkVar.A0J());
            case 7:
            default:
                throw new U0(AnonymousClass06.A04("Expecting number, got: ", mj.A00(A0G)));
            case 8:
                lkVar.A0P();
                return null;
        }
    }
}
