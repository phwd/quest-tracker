package X;

import java.io.Serializable;

/* renamed from: X.0mi  reason: invalid class name and case insensitive filesystem */
public final class C06210mi implements Comparable<C06210mi>, Serializable {
    public static final long serialVersionUID = 1;
    public Class<?> _class;
    public String _className;
    public int _hashCode;

    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && ((C06210mi) obj)._class == this._class;
        }
        return true;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.lang.Comparable
    public final int compareTo(C06210mi r3) {
        return this._className.compareTo(r3._className);
    }

    public final int hashCode() {
        return this._hashCode;
    }

    public final String toString() {
        return this._className;
    }

    public C06210mi() {
        this._class = null;
        this._className = null;
        this._hashCode = 0;
    }

    public C06210mi(Class<?> cls) {
        this._class = cls;
        String name = cls.getName();
        this._className = name;
        this._hashCode = name.hashCode();
    }
}
