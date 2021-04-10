package X;

/* renamed from: X.75  reason: invalid class name */
public class AnonymousClass75 extends A4 {
    public static final long serialVersionUID = 4611641304150899138L;
    public final RZ _elementType;

    @Override // X.RZ
    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            AnonymousClass75 r5 = (AnonymousClass75) obj;
            if (this._class != r5._class || !this._elementType.equals(r5._elementType)) {
                return false;
            }
        }
        return true;
    }

    @Override // X.RZ
    public String toString() {
        StringBuilder sb = new StringBuilder("[collection-like type; class ");
        sb.append(this._class.getName());
        sb.append(", contains ");
        sb.append(this._elementType);
        sb.append("]");
        return sb.toString();
    }
}
