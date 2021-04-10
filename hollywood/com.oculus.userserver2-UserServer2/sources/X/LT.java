package X;

import java.io.IOException;
import java.io.Reader;

public final class LT extends h5 {
    public static final Object A04 = new Object();
    public static final Reader A05 = new hF();
    public int A00 = 0;
    public int[] A01 = new int[32];
    public Object[] A02 = new Object[32];
    public String[] A03 = new String[32];

    public LT(AbstractC0241hm hmVar) {
        super(A05);
        A02(this, hmVar);
    }

    public static Object A00(LT lt) {
        Object[] objArr = lt.A02;
        int i = lt.A00 - 1;
        lt.A00 = i;
        Object obj = objArr[i];
        objArr[i] = null;
        return obj;
    }

    public static void A02(LT lt, Object obj) {
        int i = lt.A00;
        Object[] objArr = lt.A02;
        if (i == objArr.length) {
            int i2 = i << 1;
            Object[] objArr2 = new Object[i2];
            int[] iArr = new int[i2];
            String[] strArr = new String[i2];
            System.arraycopy(objArr, 0, objArr2, 0, i);
            System.arraycopy(lt.A01, 0, iArr, 0, lt.A00);
            System.arraycopy(lt.A03, 0, strArr, 0, lt.A00);
            lt.A02 = objArr2;
            objArr = objArr2;
            lt.A01 = iArr;
            lt.A03 = strArr;
        }
        int i3 = lt.A00;
        lt.A00 = i3 + 1;
        objArr[i3] = obj;
    }

    public static void A01(LT lt, Integer num) throws IOException {
        if (lt.A0G() != num) {
            throw new IllegalStateException(AnonymousClass06.A06("Expected ", h4.A00(num), " but was ", h4.A00(lt.A0G()), AnonymousClass06.A03(" at path ", lt.A0H())));
        }
    }

    @Override // X.h5
    public final String toString() {
        return getClass().getSimpleName();
    }
}
