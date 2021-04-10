package X;

/* renamed from: X.073  reason: invalid class name */
public class AnonymousClass073 extends AnonymousClass0GF {
    public static final long serialVersionUID = 4611641304150899138L;
    public final AbstractC04000gb _elementType;

    @Override // X.AbstractC04000gb
    public final int A02() {
        return 1;
    }

    @Override // X.AbstractC04000gb
    public final String A0D(int i) {
        if (i == 0) {
            return "E";
        }
        return null;
    }

    @Override // X.AbstractC04000gb
    public final boolean A0G() {
        return true;
    }

    @Override // X.AbstractC04000gb
    public final boolean A0J() {
        return true;
    }

    @Override // X.AbstractC04000gb
    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            AnonymousClass073 r5 = (AnonymousClass073) obj;
            if (this._class != r5._class || !this._elementType.equals(r5._elementType)) {
                return false;
            }
        }
        return true;
    }

    @Override // X.AbstractC04000gb
    public final AbstractC04000gb A05(int i) {
        if (i == 0) {
            return this._elementType;
        }
        return null;
    }

    @Override // X.AbstractC04000gb
    public AbstractC04000gb A07(Class<?> cls) {
        return new AnonymousClass073(cls, this._elementType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC04000gb
    public AbstractC04000gb A08(Class<?> cls) {
        AbstractC04000gb r1 = this._elementType;
        if (cls == r1._class) {
            return this;
        }
        return new AnonymousClass073(this._class, r1.A06(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0GF
    public final String A0K() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        AbstractC04000gb r1 = this._elementType;
        if (r1 != null) {
            sb.append('<');
            sb.append(r1.A01());
            sb.append('>');
        }
        return sb.toString();
    }

    /* renamed from: A0L */
    public AnonymousClass073 A09(Object obj) {
        return new AnonymousClass073(this._class, this._elementType.A0B(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    /* renamed from: A0M */
    public AnonymousClass073 A0A(Object obj) {
        return new AnonymousClass073(this._class, this._elementType.A0C(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    /* renamed from: A0N */
    public AnonymousClass073 A0B(Object obj) {
        return new AnonymousClass073(this._class, this._elementType, this._valueHandler, obj, this._asStatic);
    }

    /* renamed from: A0O */
    public AnonymousClass073 A0C(Object obj) {
        return new AnonymousClass073(this._class, this._elementType, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC04000gb
    public String toString() {
        StringBuilder sb = new StringBuilder("[collection-like type; class ");
        sb.append(this._class.getName());
        sb.append(", contains ");
        sb.append(this._elementType);
        sb.append("]");
        return sb.toString();
    }

    public AnonymousClass073(Class<?> cls, AbstractC04000gb r8, Object obj, Object obj2, boolean z) {
        super(cls, r8.hashCode(), obj, obj2, z);
        this._elementType = r8;
    }

    @Override // X.AbstractC04000gb
    public final AbstractC04000gb A03() {
        return this._elementType;
    }
}
