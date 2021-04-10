package X;

import java.io.IOException;

/* renamed from: X.0db  reason: invalid class name and case insensitive filesystem */
public class C01320db extends AnonymousClass13Y<Number> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.14L, java.lang.Object] */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r1, Number number) throws IOException {
        r1.A0B(number);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass13Y
    public final Number A02(AnonymousClass14I r4) throws IOException {
        Integer A0G = r4.A0G();
        switch (A0G.intValue()) {
            case 5:
            case 6:
                return new C057713o(r4.A0J());
            case 7:
            default:
                throw new AnonymousClass0eR(AnonymousClass006.A07("Expecting number, got: ", AnonymousClass14K.A00(A0G)));
            case 8:
                r4.A0P();
                return null;
        }
    }
}
