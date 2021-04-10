package X;

import java.io.IOException;

/* renamed from: X.0Wa  reason: invalid class name and case insensitive filesystem */
public class C02070Wa extends AnonymousClass0Bd<Number> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r1, Number number) throws IOException {
        r1.A0C(number);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final Number A02(AnonymousClass0Fo r4) throws IOException {
        Integer A0D = r4.A0D();
        switch (A0D.intValue()) {
            case 5:
            case 6:
                return new AnonymousClass0DB(r4.A0F());
            case 7:
            default:
                throw new AnonymousClass0XQ(AnonymousClass006.A05("Expecting number, got: ", AnonymousClass0GA.A00(A0D)));
            case 8:
                r4.A0L();
                return null;
        }
    }
}
