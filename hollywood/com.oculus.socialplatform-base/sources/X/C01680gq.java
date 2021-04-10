package X;

import java.io.Serializable;

/* renamed from: X.0gq  reason: invalid class name and case insensitive filesystem */
public final class C01680gq extends AbstractC04970rd implements Serializable {
    public static final long serialVersionUID = 1;
    public final Class<?> _view;

    @Override // X.AbstractC04970rd
    public final boolean A00(Class<?> cls) {
        Class<?> cls2 = this._view;
        if (cls == cls2 || cls2.isAssignableFrom(cls)) {
            return true;
        }
        return false;
    }

    public C01680gq(Class<?> cls) {
        this._view = cls;
    }
}
