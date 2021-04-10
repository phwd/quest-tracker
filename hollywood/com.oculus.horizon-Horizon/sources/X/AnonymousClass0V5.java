package X;

import java.io.IOException;

/* renamed from: X.0V5  reason: invalid class name */
public class AnonymousClass0V5 extends AnonymousClass0yl<Number> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0zU, java.lang.Object] */
    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r1, Number number) throws IOException {
        r1.A0B(number);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0yl
    public final Number A02(C09120zR r4) throws IOException {
        Integer A0G = r4.A0G();
        switch (A0G.intValue()) {
            case 5:
            case 6:
                return new C08950z1(r4.A0J());
            case 7:
            default:
                throw new C03080c5(AnonymousClass006.A05("Expecting number, got: ", AnonymousClass0zT.A00(A0G)));
            case 8:
                r4.A0P();
                return null;
        }
    }
}
