package X;

import java.io.Serializable;

/* renamed from: X.0fg  reason: invalid class name */
public final class AnonymousClass0fg extends AbstractC06490nE implements Serializable {
    public static final long serialVersionUID = 1;
    public final Class<?>[] _views;

    @Override // X.AbstractC06490nE
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

    public AnonymousClass0fg(Class<?>[] clsArr) {
        this._views = clsArr;
    }
}
