package X;

import java.io.Serializable;

/* renamed from: X.Vr  reason: case insensitive filesystem */
public final class C0207Vr extends AbstractC0098Hw implements Serializable {
    public static final long serialVersionUID = 1;
    public final Class<?>[] _views;

    @Override // X.AbstractC0098Hw
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

    public C0207Vr(Class<?>[] clsArr) {
        this._views = clsArr;
    }
}
