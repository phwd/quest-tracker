package X;

import java.io.Serializable;

public final class PO implements Serializable {
    public static final long serialVersionUID = 1;
    public final Class _class;
    public final int _hashCode;
    public String _name;

    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && this._class == ((PO) obj)._class;
        }
        return true;
    }

    public final String toString() {
        String A05;
        String name = this._class.getName();
        String str = this._name;
        if (str == null) {
            A05 = "null";
        } else {
            A05 = AnonymousClass08.A05("'", str, "'");
        }
        return AnonymousClass08.A07("[NamedType, class ", name, ", name: ", A05, "]");
    }

    public PO(Class cls, String str) {
        this._class = cls;
        this._hashCode = cls.getName().hashCode();
        this._name = (str == null || str.length() == 0) ? null : str;
    }

    public final int hashCode() {
        return this._hashCode;
    }
}
