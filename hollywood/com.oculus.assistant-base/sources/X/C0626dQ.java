package X;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.Set;

/* renamed from: X.dQ  reason: case insensitive filesystem */
public final class C0626dQ<K, V> extends AnonymousClass0m<K, V> implements Map<K, V> {
    public AbstractC00060l A00;

    @Override // java.util.Map
    public final Set entrySet() {
        AbstractC00060l r1 = this.A00;
        if (r1 == null) {
            r1 = new C0625dP(this);
            this.A00 = r1;
        }
        C00030h r0 = r1.A00;
        if (r0 != null) {
            return r0;
        }
        C00030h r02 = new C00030h(r1);
        r1.A00 = r02;
        return r02;
    }

    @Override // java.util.Map
    public final Set keySet() {
        AbstractC00060l r1 = this.A00;
        if (r1 == null) {
            r1 = new C0625dP(this);
            this.A00 = r1;
        }
        C00040i r0 = r1.A01;
        if (r0 != null) {
            return r0;
        }
        C00040i r02 = new C00040i(r1);
        r1.A01 = r02;
        return r02;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        int size = super.A00 + map.size();
        int i = super.A00;
        int[] iArr = this.A01;
        if (iArr.length < size) {
            Object[] objArr = this.A02;
            AnonymousClass0m.A02(this, size);
            if (super.A00 > 0) {
                System.arraycopy(iArr, 0, this.A01, 0, i);
                System.arraycopy(objArr, 0, this.A02, 0, i << 1);
            }
            AnonymousClass0m.A03(iArr, objArr, i);
        }
        if (super.A00 == i) {
            for (Map.Entry<K, V> entry : map.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
            return;
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Map
    public final Collection values() {
        AbstractC00060l r1 = this.A00;
        if (r1 == null) {
            r1 = new C0625dP(this);
            this.A00 = r1;
        }
        C00050k r0 = r1.A02;
        if (r0 != null) {
            return r0;
        }
        C00050k r02 = new C00050k(r1);
        r1.A02 = r02;
        return r02;
    }

    public C0626dQ() {
    }

    public C0626dQ(int i) {
        super(i);
    }
}
