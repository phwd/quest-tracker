package X;

import java.io.IOException;
import java.util.HashMap;

/* renamed from: X.1T  reason: invalid class name */
public final class AnonymousClass1T {
    public final int A00;
    public final HashMap<String, AQ> A01;
    public final Object[] A02;

    public final void A00(AnonymousClass1Y r6) throws IOException {
        Object obj;
        Object[] objArr = this.A02;
        if (objArr != null) {
            Object[] objArr2 = r6.A03;
            int length = objArr2.length;
            for (int i = 0; i < length; i++) {
                if (objArr2[i] == null && (obj = objArr[i]) != null) {
                    objArr2[i] = obj;
                }
            }
        }
        throw null;
    }
}
