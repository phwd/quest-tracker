package X;

import java.io.Serializable;

/* renamed from: X.0qX  reason: invalid class name */
public final class AnonymousClass0qX implements Serializable {
    public static final long serialVersionUID = 1;
    public final Class<?> _class;
    public final int _hashCode;
    public String _name;

    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && this._class == ((AnonymousClass0qX) obj)._class;
        }
        return true;
    }

    public final String toString() {
        String A09;
        String name = this._class.getName();
        String str = this._name;
        if (str == null) {
            A09 = "null";
        } else {
            A09 = AnonymousClass006.A09("'", str, "'");
        }
        return AnonymousClass006.A0C("[NamedType, class ", name, ", name: ", A09, "]");
    }

    public AnonymousClass0qX(Class<?> cls, String str) {
        this._class = cls;
        this._hashCode = cls.getName().hashCode();
        this._name = (str == null || str.length() == 0) ? null : str;
    }

    public final int hashCode() {
        return this._hashCode;
    }
}
