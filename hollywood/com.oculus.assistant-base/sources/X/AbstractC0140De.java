package X;

import java.util.Set;

/* renamed from: X.De  reason: case insensitive filesystem */
public abstract class AbstractC0140De<E> extends u6<E> implements UM<E> {
    @Override // X.UM
    public final int A19(Object obj, int i) {
        return ((AnonymousClass24) ((YG) this)).A00.A19(obj, i);
    }

    @Override // X.UM
    public final int A1V(Object obj) {
        return ((AnonymousClass24) ((YG) this)).A00.A1V(obj);
    }

    @Override // X.UM
    public final int A4m(Object obj, int i) {
        return ((AnonymousClass24) ((YG) this)).A00.A4m(obj, i);
    }

    @Override // X.UM
    public final int A4z(Object obj, int i) {
        return ((AnonymousClass24) ((YG) this)).A00.A4z(obj, i);
    }

    @Override // X.UM
    public final boolean A50(Object obj, int i, int i2) {
        return ((AnonymousClass24) ((YG) this)).A00.A50(obj, i, i2);
    }

    @Override // X.UM
    public final int hashCode() {
        return ((AnonymousClass24) ((YG) this)).A00.hashCode();
    }

    @Override // X.UM
    public final Set A1m() {
        if (!(this instanceof YG)) {
            return ((AnonymousClass24) ((YG) this)).A00.A1m();
        }
        return ((YG) this).A1l();
    }

    @Override // X.UM
    public final Set entrySet() {
        if (!(this instanceof YG)) {
            return ((AnonymousClass24) ((YG) this)).A00.entrySet();
        }
        YG yg = (YG) this;
        Set set = yg.A00;
        if (set != null) {
            return set;
        }
        C0143Dh dh = new C0143Dh(yg);
        yg.A00 = dh;
        return dh;
    }

    @Override // X.UM
    public final boolean equals(Object obj) {
        if (obj == this || ((AnonymousClass24) ((YG) this)).A00.equals(obj)) {
            return true;
        }
        return false;
    }
}
