package X;

import java.io.Serializable;

/* renamed from: X.0m6  reason: invalid class name and case insensitive filesystem */
public final class C05910m6 implements Serializable {
    public static final long serialVersionUID = 1;
    public final Class<?> _class;
    public final int _hashCode;
    public String _name;

    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && this._class == ((C05910m6) obj)._class;
        }
        return true;
    }

    public final String toString() {
        String A07;
        String name = this._class.getName();
        String str = this._name;
        if (str == null) {
            A07 = "null";
        } else {
            A07 = AnonymousClass006.A07("'", str, "'");
        }
        return AnonymousClass006.A09("[NamedType, class ", name, ", name: ", A07, "]");
    }

    public C05910m6(Class<?> cls, String str) {
        this._class = cls;
        this._hashCode = cls.getName().hashCode();
        this._name = (str == null || str.length() == 0) ? null : str;
    }

    public final int hashCode() {
        return this._hashCode;
    }
}
