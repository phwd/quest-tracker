package X;

import java.util.AbstractSet;
import java.util.Iterator;

public final class hP extends AbstractSet<K> {
    public final /* synthetic */ hM A00;

    public hP(hM hMVar) {
        this.A00 = hMVar;
    }

    public final void clear() {
        this.A00.clear();
    }

    public final boolean contains(Object obj) {
        return this.A00.containsKey(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<K> iterator() {
        return new C0084Lf(this);
    }

    public final boolean remove(Object obj) {
        hM hMVar = this.A00;
        hN hNVar = null;
        if (obj == null) {
            return false;
        }
        try {
            hNVar = hM.A00(hMVar, obj, false);
        } catch (ClassCastException unused) {
        }
        if (hNVar == null) {
            return false;
        }
        hMVar.A06(hNVar, true);
        return true;
    }

    public final int size() {
        return this.A00.size;
    }
}
