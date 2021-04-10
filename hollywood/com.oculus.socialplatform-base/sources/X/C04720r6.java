package X;

import java.io.Serializable;

/* renamed from: X.0r6  reason: invalid class name and case insensitive filesystem */
public final class C04720r6 implements Comparable<C04720r6>, Serializable {
    public static final long serialVersionUID = 1;
    public Class<?> _class;
    public String _className;
    public int _hashCode;

    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && ((C04720r6) obj)._class == this._class;
        }
        return true;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.lang.Comparable
    public final int compareTo(C04720r6 r3) {
        return this._className.compareTo(r3._className);
    }

    public final int hashCode() {
        return this._hashCode;
    }

    public final String toString() {
        return this._className;
    }

    public C04720r6() {
        this._class = null;
        this._className = null;
        this._hashCode = 0;
    }

    public C04720r6(Class<?> cls) {
        this._class = cls;
        String name = cls.getName();
        this._className = name;
        this._hashCode = name.hashCode();
    }
}
