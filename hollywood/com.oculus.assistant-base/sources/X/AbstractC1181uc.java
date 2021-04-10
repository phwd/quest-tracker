package X;

/* renamed from: X.uc  reason: case insensitive filesystem */
public abstract class AbstractC1181uc extends UV {
    public final void clear() {
        UM um;
        if (!(this instanceof C0143Dh)) {
            um = ((Dj) this).A00;
        } else {
            um = ((C0143Dh) this).A00;
        }
        um.clear();
    }

    public final boolean contains(Object obj) {
        UM um;
        if (!(obj instanceof AbstractC1179ua)) {
            return false;
        }
        AbstractC1179ua uaVar = (AbstractC1179ua) obj;
        if (uaVar.A00() <= 0) {
            return false;
        }
        if (!(this instanceof C0143Dh)) {
            um = ((Dj) this).A00;
        } else {
            um = ((C0143Dh) this).A00;
        }
        if (um.A1V(uaVar.A01()) == uaVar.A00()) {
            return true;
        }
        return false;
    }

    public final boolean remove(Object obj) {
        UM um;
        if (obj instanceof AbstractC1179ua) {
            AbstractC1179ua uaVar = (AbstractC1179ua) obj;
            Object A01 = uaVar.A01();
            int A00 = uaVar.A00();
            if (A00 != 0) {
                if (!(this instanceof C0143Dh)) {
                    um = ((Dj) this).A00;
                } else {
                    um = ((C0143Dh) this).A00;
                }
                return um.A50(A01, A00, 0);
            }
        }
        return false;
    }
}
