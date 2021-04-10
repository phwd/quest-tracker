package X;

/* renamed from: X.3q  reason: invalid class name and case insensitive filesystem */
public final class C00323q extends C0682fH {
    public static final long serialVersionUID = -7834910259750909424L;

    public static C00323q A00(Class cls, AbstractC1024qt qtVar) {
        return new C00323q(cls, qtVar, null, null, false);
    }

    @Override // X.C0682fH, X.AbstractC1024qt
    public final String toString() {
        StringBuilder sb = new StringBuilder("[collection type; class ");
        sb.append(this._class.getName());
        sb.append(", contains ");
        sb.append(this._elementType);
        sb.append("]");
        return sb.toString();
    }

    public C00323q(Class cls, AbstractC1024qt qtVar, Object obj, Object obj2, boolean z) {
        super(cls, qtVar, obj, obj2, z);
    }
}
