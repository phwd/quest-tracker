package X;

import java.lang.reflect.Array;
import java.util.HashSet;

/* renamed from: X.0rG  reason: invalid class name and case insensitive filesystem */
public final class C04790rG {
    public C01800h5 A00 = null;
    public C01790h4 A01 = null;
    public C01780h3 A02 = null;
    public AnonymousClass0h2 A03 = null;
    public C01770h1 A04 = null;
    public AnonymousClass0h0 A05 = null;
    public AnonymousClass0gz A06 = null;

    public static <T> T[] A01(T[] tArr, T t) {
        T[] tArr2;
        int length = tArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                tArr2 = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), length + 1));
                if (length > 0) {
                    System.arraycopy(tArr, 0, tArr2, 1, length);
                }
                tArr2[0] = t;
            } else if (tArr[i] != t) {
                i++;
            } else if (i == 0) {
                return tArr;
            } else {
                tArr2 = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), length));
                System.arraycopy(tArr, 0, tArr2, 1, i);
                tArr2[0] = t;
                int i2 = i + 1;
                int i3 = length - i2;
                if (i3 > 0) {
                    System.arraycopy(tArr, i2, tArr2, i2, i3);
                    return tArr2;
                }
            }
        }
        return tArr2;
    }

    public static <T> HashSet<T> A00(T[] tArr) {
        HashSet<T> hashSet = new HashSet<>();
        if (tArr != null) {
            for (T t : tArr) {
                hashSet.add(t);
            }
        }
        return hashSet;
    }
}
