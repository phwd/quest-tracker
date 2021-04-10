package X;

import java.io.Serializable;

/* renamed from: X.0ZG  reason: invalid class name */
public final class AnonymousClass0ZG extends AbstractC07310p7 implements Serializable {
    public static final long serialVersionUID = 1;
    public final Class<?> _view;

    @Override // X.AbstractC07310p7
    public final boolean A00(Class<?> cls) {
        Class<?> cls2 = this._view;
        if (cls == cls2 || cls2.isAssignableFrom(cls)) {
            return true;
        }
        return false;
    }

    public AnonymousClass0ZG(Class<?> cls) {
        this._view = cls;
    }
}
