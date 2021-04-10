package X;

/* renamed from: X.1S  reason: invalid class name */
public final class AnonymousClass1S extends AnonymousClass75 {
    public static final long serialVersionUID = -7834910259750909424L;

    @Override // X.RZ, X.AnonymousClass75
    public final String toString() {
        StringBuilder sb = new StringBuilder("[collection type; class ");
        sb.append(this._class.getName());
        sb.append(", contains ");
        sb.append(this._elementType);
        sb.append("]");
        return sb.toString();
    }
}
