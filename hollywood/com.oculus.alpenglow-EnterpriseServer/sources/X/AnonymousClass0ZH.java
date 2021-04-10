package X;

import java.io.Serializable;

/* renamed from: X.0ZH  reason: invalid class name */
public final class AnonymousClass0ZH extends AbstractC07310p7 implements Serializable {
    public static final long serialVersionUID = 1;
    public final Class<?>[] _views;

    @Override // X.AbstractC07310p7
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

    public AnonymousClass0ZH(Class<?>[] clsArr) {
        this._views = clsArr;
    }
}
