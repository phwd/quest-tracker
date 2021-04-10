package X;

import androidx.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.02n  reason: invalid class name */
public abstract class AnonymousClass02n<K, V> {
    @Nullable
    public AnonymousClass02n<K, V>.EntrySet A00;
    @Nullable
    public AnonymousClass02n<K, V>.KeySet A01;
    @Nullable
    public AnonymousClass02n<K, V>.ValuesCollection A02;

    public abstract int A01();

    public abstract int A02(Object obj);

    public abstract int A03(Object obj);

    public abstract Object A04(int i, int i2);

    public abstract V A05(int i, V v);

    public abstract Map<K, V> A06();

    public abstract void A07();

    public abstract void A08(int i);

    public abstract void A09(K k, V v);

    public static <K, V> boolean A00(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        Iterator<K> it = map.keySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
            }
        }
        if (size != map.size()) {
            return true;
        }
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v7, resolved type: T[] */
    /* JADX WARN: Multi-variable type inference failed */
    public final <T> T[] A0A(T[] tArr, int i) {
        int A012 = A01();
        if (tArr.length < A012) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), A012));
        }
        for (int i2 = 0; i2 < A012; i2++) {
            tArr[i2] = A04(i2, i);
        }
        if (tArr.length > A012) {
            tArr[A012] = null;
        }
        return tArr;
    }
}
