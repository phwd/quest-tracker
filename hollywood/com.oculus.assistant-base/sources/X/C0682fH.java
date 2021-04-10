package X;

/* renamed from: X.fH  reason: case insensitive filesystem */
public class C0682fH extends JF {
    public static final long serialVersionUID = 4611641304150899138L;
    public final AbstractC1024qt _elementType;

    @Override // X.AbstractC1024qt
    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            C0682fH fHVar = (C0682fH) obj;
            if (this._class != fHVar._class || !this._elementType.equals(fHVar._elementType)) {
                return false;
            }
        }
        return true;
    }

    @Override // X.AbstractC1024qt
    public String toString() {
        StringBuilder sb = new StringBuilder("[collection-like type; class ");
        sb.append(this._class.getName());
        sb.append(", contains ");
        sb.append(this._elementType);
        sb.append("]");
        return sb.toString();
    }

    public C0682fH(Class cls, AbstractC1024qt qtVar, Object obj, Object obj2, boolean z) {
        super(cls, qtVar.hashCode(), obj, obj2, z);
        this._elementType = qtVar;
    }
}
