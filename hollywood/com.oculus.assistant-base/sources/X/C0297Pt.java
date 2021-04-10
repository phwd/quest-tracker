package X;

import java.io.Serializable;

/* renamed from: X.Pt  reason: case insensitive filesystem */
public final class C0297Pt implements Comparable, Serializable {
    public static final long serialVersionUID = 1;
    public Class _class;
    public String _className;
    public int _hashCode;

    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && ((C0297Pt) obj)._class == this._class;
        }
        return true;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this._className.compareTo(((C0297Pt) obj)._className);
    }

    public final int hashCode() {
        return this._hashCode;
    }

    public final String toString() {
        return this._className;
    }

    public C0297Pt() {
        this._class = null;
        this._className = null;
        this._hashCode = 0;
    }

    public C0297Pt(Class cls) {
        this._class = cls;
        String name = cls.getName();
        this._className = name;
        this._hashCode = name.hashCode();
    }
}
