package defpackage;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* renamed from: ta  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4931ta extends BW0 implements Map {
    public C3908na M;
    public C4250pa N;
    public C4591ra O;

    public C4931ta() {
    }

    public static boolean l(Set set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                return set.size() == set2.size() && set.containsAll(set2);
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    @Override // java.util.Map
    public Set entrySet() {
        C3908na naVar = this.M;
        if (naVar != null) {
            return naVar;
        }
        C3908na naVar2 = new C3908na(this);
        this.M = naVar2;
        return naVar2;
    }

    @Override // java.util.Map
    public Set keySet() {
        C4250pa paVar = this.N;
        if (paVar != null) {
            return paVar;
        }
        C4250pa paVar2 = new C4250pa(this);
        this.N = paVar2;
        return paVar2;
    }

    public boolean m(Collection collection) {
        int i = this.L;
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (!collection.contains(h(i2))) {
                i(i2);
            }
        }
        return i != this.L;
    }

    public Object[] n(Object[] objArr, int i) {
        int i2 = this.L;
        if (objArr.length < i2) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i2);
        }
        for (int i3 = 0; i3 < i2; i3++) {
            objArr[i3] = this.K[(i3 << 1) + i];
        }
        if (objArr.length > i2) {
            objArr[i2] = null;
        }
        return objArr;
    }

    @Override // java.util.Map
    public void putAll(Map map) {
        b(map.size() + this.L);
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public Collection values() {
        C4591ra raVar = this.O;
        if (raVar != null) {
            return raVar;
        }
        C4591ra raVar2 = new C4591ra(this);
        this.O = raVar2;
        return raVar2;
    }

    public C4931ta(int i) {
        super(i);
    }

    public C4931ta(BW0 bw0) {
        if (bw0 != null) {
            int i = bw0.L;
            b(this.L + i);
            if (this.L != 0) {
                for (int i2 = 0; i2 < i; i2++) {
                    put(bw0.h(i2), bw0.k(i2));
                }
            } else if (i > 0) {
                System.arraycopy(bw0.f7743J, 0, this.f7743J, 0, i);
                System.arraycopy(bw0.K, 0, this.K, 0, i << 1);
                this.L = i;
            }
        }
    }
}
