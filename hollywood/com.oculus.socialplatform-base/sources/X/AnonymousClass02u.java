package X;

import androidx.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.Map;

/* renamed from: X.02u  reason: invalid class name */
public abstract class AnonymousClass02u<K, V> {
    @Nullable
    public AnonymousClass02u<K, V>.EntrySet A00;
    @Nullable
    public AnonymousClass02u<K, V>.KeySet A01;
    @Nullable
    public AnonymousClass02u<K, V>.ValuesCollection A02;

    public abstract int A00();

    public abstract int A01(Object obj);

    public abstract int A02(Object obj);

    public abstract Object A03(int i, int i2);

    public abstract V A04(int i, V v);

    public abstract Map<K, V> A05();

    public abstract void A06();

    public abstract void A07(int i);

    public abstract void A08(K k, V v);

    /* JADX DEBUG: Multi-variable search result rejected for r4v7, resolved type: T[] */
    /* JADX WARN: Multi-variable type inference failed */
    public final <T> T[] A09(T[] tArr, int i) {
        int A002 = A00();
        if (tArr.length < A002) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), A002));
        }
        for (int i2 = 0; i2 < A002; i2++) {
            tArr[i2] = A03(i2, i);
        }
        if (tArr.length > A002) {
            tArr[A002] = null;
        }
        return tArr;
    }
}
