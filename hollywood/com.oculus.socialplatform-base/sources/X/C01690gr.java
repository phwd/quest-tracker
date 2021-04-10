package X;

import java.io.Serializable;

/* renamed from: X.0gr  reason: invalid class name and case insensitive filesystem */
public final class C01690gr extends AbstractC04970rd implements Serializable {
    public static final long serialVersionUID = 1;
    public final Class<?>[] _views;

    @Override // X.AbstractC04970rd
    public final boolean A00(Class<?> cls) {
        int length = this._views.length;
        for (int i = 0; i < length; i++) {
            Class<?> cls2 = this._views[i];
            if (cls == cls2 || cls2.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    public C01690gr(Class<?>[] clsArr) {
        this._views = clsArr;
    }
}
