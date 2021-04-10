package X;

import java.io.Serializable;

public final class ON implements Comparable<ON>, Serializable {
    public static final long serialVersionUID = 1;
    public Class<?> _class;
    public String _className;
    public int _hashCode;

    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && ((ON) obj)._class == this._class;
        }
        return true;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.lang.Comparable
    public final int compareTo(ON on) {
        return this._className.compareTo(on._className);
    }

    public final int hashCode() {
        return this._hashCode;
    }

    public final String toString() {
        return this._className;
    }

    public ON() {
        this._class = null;
        this._className = null;
        this._hashCode = 0;
    }

    public ON(Class<?> cls) {
        this._class = cls;
        String name = cls.getName();
        this._className = name;
        this._hashCode = name.hashCode();
    }
}
