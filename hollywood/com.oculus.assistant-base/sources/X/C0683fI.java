package X;

import java.lang.reflect.Array;

/* renamed from: X.fI  reason: case insensitive filesystem */
public final class C0683fI extends JF {
    public static final long serialVersionUID = 9040058063449087477L;
    public final AbstractC1024qt _componentType;
    public final Object _emptyArray;

    public static C0683fI A00(AbstractC1024qt qtVar) {
        return new C0683fI(qtVar, Array.newInstance(qtVar._class, 0), null, null, false);
    }

    @Override // X.AbstractC1024qt
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return this._componentType.equals(((C0683fI) obj)._componentType);
    }

    @Override // X.AbstractC1024qt
    public final String toString() {
        StringBuilder sb = new StringBuilder("[array type, component type: ");
        sb.append(this._componentType);
        sb.append("]");
        return sb.toString();
    }

    public C0683fI(AbstractC1024qt qtVar, Object obj, Object obj2, Object obj3, boolean z) {
        super(obj.getClass(), qtVar.hashCode(), obj2, obj3, z);
        this._componentType = qtVar;
        this._emptyArray = obj;
    }
}
