package X;

import X.AbstractC09351hm;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: X.1hZ  reason: invalid class name */
public final class AnonymousClass1hZ<K extends AbstractC09351hm, V> {
    public final C09301he<K, V> A00 = new C09301he<>(null);
    public final Map<K, C09301he<K, V>> A01 = new HashMap();

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r3v2. Raw type applied. Possible types: X.1he<K, V>, java.lang.Object, X.1he */
    @Nullable
    public final V A00() {
        int size;
        V remove;
        C09301he r3 = (C09301he<K, V>) this.A00;
        C09301he r32 = r3;
        while (true) {
            C09301he r33 = (C09301he<K, V>) r32.A01;
            if (r33.equals(r3)) {
                return null;
            }
            List<V> list = r33.A02;
            if (list != null && (size = list.size()) > 0 && (remove = r33.A02.remove(size - 1)) != null) {
                return remove;
            }
            C09301he<K, V> r1 = r33.A01;
            r1.A00 = r33.A00;
            r33.A00.A01 = r1;
            Map<K, C09301he<K, V>> map = this.A01;
            K k = r33.A03;
            map.remove(k);
            k.A6b();
            r32 = r33;
        }
    }

    @Nullable
    public final V A01(K k) {
        int size;
        Map<K, C09301he<K, V>> map = this.A01;
        C09301he<K, V> r2 = map.get(k);
        if (r2 == null) {
            r2 = new C09301he<>(k);
            map.put(k, r2);
        } else {
            k.A6b();
        }
        C09301he<K, V> r1 = r2.A01;
        r1.A00 = r2.A00;
        r2.A00.A01 = r1;
        C09301he<K, V> r0 = this.A00;
        r2.A01 = r0;
        C09301he<K, V> r02 = r0.A00;
        r2.A00 = r02;
        r02.A01 = r2;
        r2.A01.A00 = r2;
        List<V> list = r2.A02;
        if (list == null || (size = list.size()) <= 0) {
            return null;
        }
        return r2.A02.remove(size - 1);
    }

    public final void A02(K k, V v) {
        Map<K, C09301he<K, V>> map = this.A01;
        C09301he<K, V> r2 = map.get(k);
        if (r2 == null) {
            r2 = new C09301he<>(k);
            C09301he<K, V> r1 = r2.A01;
            r1.A00 = r2.A00;
            r2.A00.A01 = r1;
            C09301he<K, V> r12 = this.A00;
            r2.A01 = r12.A01;
            r2.A00 = r12;
            r12.A01 = r2;
            r2.A01.A00 = r2;
            map.put(k, r2);
        } else {
            k.A6b();
        }
        List list = r2.A02;
        if (list == null) {
            list = new ArrayList();
            r2.A02 = list;
        }
        list.add(v);
    }

    public final String toString() {
        int i;
        StringBuilder sb = new StringBuilder("GroupedLinkedMap( ");
        C09301he<K, V> r3 = this.A00;
        boolean z = false;
        for (C09301he<K, V> r2 = r3.A00; !r2.equals(r3); r2 = r2.A00) {
            z = true;
            sb.append('{');
            sb.append((Object) r2.A03);
            sb.append(':');
            List<V> list = r2.A02;
            if (list != null) {
                i = list.size();
            } else {
                i = 0;
            }
            sb.append(i);
            sb.append("}, ");
        }
        if (z) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append(" )");
        return sb.toString();
    }
}
