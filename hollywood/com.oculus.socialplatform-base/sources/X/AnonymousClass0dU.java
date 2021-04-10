package X;

import java.io.IOException;

/* renamed from: X.0dU  reason: invalid class name */
public class AnonymousClass0dU extends AnonymousClass13Y<StringBuffer> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.14L, java.lang.Object] */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r2, StringBuffer stringBuffer) throws IOException {
        String obj;
        if (stringBuffer == null) {
            obj = null;
        } else {
            obj = stringBuffer.toString();
        }
        r2.A0D(obj);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass13Y
    public final StringBuffer A02(AnonymousClass14I r3) throws IOException {
        if (r3.A0G() != AnonymousClass007.A09) {
            return new StringBuffer(r3.A0J());
        }
        r3.A0P();
        return null;
    }
}
