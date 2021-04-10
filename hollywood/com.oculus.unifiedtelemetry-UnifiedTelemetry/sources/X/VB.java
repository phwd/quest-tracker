package X;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class VB<T> implements Iterator<T> {
    public int A00;
    public VC<K, V> A01 = null;
    public VC<K, V> A02;
    public final /* synthetic */ VD A03;

    public VB(VD vd) {
        this.A03 = vd;
        this.A02 = vd.header.A01;
        this.A00 = vd.modCount;
    }

    public final VC<K, V> A00() {
        VC<K, V> vc = this.A02;
        VD vd = this.A03;
        if (vc == vd.header) {
            throw new NoSuchElementException();
        } else if (vd.modCount == this.A00) {
            this.A02 = vc.A01;
            this.A01 = vc;
            return vc;
        } else {
            throw new ConcurrentModificationException();
        }
    }

    public final boolean hasNext() {
        if (this.A02 != this.A03.header) {
            return true;
        }
        return false;
    }

    public final void remove() {
        VC<K, V> vc = this.A01;
        if (vc != null) {
            VD vd = this.A03;
            vd.A06(vc, true);
            this.A01 = null;
            this.A00 = vd.modCount;
            return;
        }
        throw new IllegalStateException();
    }
}
