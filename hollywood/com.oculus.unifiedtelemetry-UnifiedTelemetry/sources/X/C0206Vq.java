package X;

import java.io.Serializable;

/* renamed from: X.Vq  reason: case insensitive filesystem */
public final class C0206Vq extends AbstractC0098Hw implements Serializable {
    public static final long serialVersionUID = 1;
    public final Class<?> _view;

    @Override // X.AbstractC0098Hw
    public final boolean A00(Class<?> cls) {
        Class<?> cls2 = this._view;
        if (cls == cls2 || cls2.isAssignableFrom(cls)) {
            return true;
        }
        return false;
    }

    public C0206Vq(Class<?> cls) {
        this._view = cls;
    }
}
