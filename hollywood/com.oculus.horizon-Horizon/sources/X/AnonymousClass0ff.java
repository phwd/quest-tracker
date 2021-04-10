package X;

import java.io.Serializable;

/* renamed from: X.0ff  reason: invalid class name */
public final class AnonymousClass0ff extends AbstractC06490nE implements Serializable {
    public static final long serialVersionUID = 1;
    public final Class<?> _view;

    @Override // X.AbstractC06490nE
    public final boolean A00(Class<?> cls) {
        Class<?> cls2 = this._view;
        if (cls == cls2 || cls2.isAssignableFrom(cls)) {
            return true;
        }
        return false;
    }

    public AnonymousClass0ff(Class<?> cls) {
        this._view = cls;
    }
}
