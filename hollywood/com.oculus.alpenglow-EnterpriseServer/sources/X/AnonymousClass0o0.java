package X;

import java.io.Serializable;

/* renamed from: X.0o0  reason: invalid class name */
public final class AnonymousClass0o0 implements Serializable {
    public static final long serialVersionUID = 1;
    public final Class<?> _class;
    public final int _hashCode;
    public String _name;

    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && this._class == ((AnonymousClass0o0) obj)._class;
        }
        return true;
    }

    public final int hashCode() {
        return this._hashCode;
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

    public AnonymousClass0o0(Class<?> cls, String str) {
        this._class = cls;
        this._hashCode = cls.getName().hashCode();
        this._name = (str == null || str.length() == 0) ? null : str;
    }
}
