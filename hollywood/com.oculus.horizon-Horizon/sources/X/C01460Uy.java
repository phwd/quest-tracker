package X;

import java.io.IOException;

/* renamed from: X.0Uy  reason: invalid class name and case insensitive filesystem */
public class C01460Uy extends AnonymousClass0yl<StringBuffer> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0zU, java.lang.Object] */
    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r2, StringBuffer stringBuffer) throws IOException {
        String obj;
        if (stringBuffer == null) {
            obj = null;
        } else {
            obj = stringBuffer.toString();
        }
        r2.A0D(obj);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0yl
    public final StringBuffer A02(C09120zR r3) throws IOException {
        if (r3.A0G() != AnonymousClass007.A0I) {
            return new StringBuffer(r3.A0J());
        }
        r3.A0P();
        return null;
    }
}
