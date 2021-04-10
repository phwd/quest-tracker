package X;

/* renamed from: X.77  reason: invalid class name */
public final class AnonymousClass77 extends A4 {
    public static final long serialVersionUID = 9040058063449087477L;
    public final RZ _componentType;
    public final Object _emptyArray;

    @Override // X.RZ
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return this._componentType.equals(((AnonymousClass77) obj)._componentType);
    }

    @Override // X.RZ
    public final String toString() {
        StringBuilder sb = new StringBuilder("[array type, component type: ");
        sb.append(this._componentType);
        sb.append("]");
        return sb.toString();
    }
}
