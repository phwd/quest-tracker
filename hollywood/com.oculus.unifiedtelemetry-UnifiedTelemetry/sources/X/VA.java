package X;

import java.util.AbstractSet;
import java.util.Iterator;

public final class VA extends AbstractSet<K> {
    public final /* synthetic */ VD A00;

    public VA(VD vd) {
        this.A00 = vd;
    }

    public final void clear() {
        this.A00.clear();
    }

    public final boolean contains(Object obj) {
        return this.A00.containsKey(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<K> iterator() {
        return new Th(this);
    }

    public final boolean remove(Object obj) {
        VD vd = this.A00;
        VC vc = null;
        if (obj == null) {
            return false;
        }
        try {
            vc = VD.A00(vd, obj, false);
        } catch (ClassCastException unused) {
        }
        if (vc == null) {
            return false;
        }
        vd.A06(vc, true);
        return true;
    }

    public final int size() {
        return this.A00.size;
    }
}
