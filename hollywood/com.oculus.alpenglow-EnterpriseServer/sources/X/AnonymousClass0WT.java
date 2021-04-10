package X;

import java.io.IOException;

/* renamed from: X.0WT  reason: invalid class name */
public class AnonymousClass0WT extends AnonymousClass0Bd<StringBuffer> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r2, StringBuffer stringBuffer) throws IOException {
        String stringBuffer2;
        StringBuffer stringBuffer3 = stringBuffer;
        if (stringBuffer3 == null) {
            stringBuffer2 = null;
        } else {
            stringBuffer2 = stringBuffer3.toString();
        }
        r2.A0E(stringBuffer2);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final StringBuffer A02(AnonymousClass0Fo r3) throws IOException {
        if (r3.A0D() != AnonymousClass007.A0I) {
            return new StringBuffer(r3.A0F());
        }
        r3.A0L();
        return null;
    }
}
