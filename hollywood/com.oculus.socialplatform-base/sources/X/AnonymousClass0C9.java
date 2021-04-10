package X;

/* renamed from: X.0C9  reason: invalid class name */
public class AnonymousClass0C9 extends AbstractC00960Oa {
    public static final long serialVersionUID = 4611641304150899138L;
    public final AbstractC02190iF _elementType;

    @Override // X.AbstractC02190iF
    public final int A03() {
        return 1;
    }

    @Override // X.AbstractC02190iF
    public final String A0G(int i) {
        if (i == 0) {
            return "E";
        }
        return null;
    }

    @Override // X.AbstractC02190iF
    public final boolean A0K() {
        return true;
    }

    @Override // X.AbstractC02190iF
    public final boolean A0N() {
        return true;
    }

    @Override // X.AbstractC02190iF
    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            AnonymousClass0C9 r5 = (AnonymousClass0C9) obj;
            if (this._class != r5._class || !this._elementType.equals(r5._elementType)) {
                return false;
            }
        }
        return true;
    }

    @Override // X.AbstractC02190iF
    public final AbstractC02190iF A06(int i) {
        if (i == 0) {
            return this._elementType;
        }
        return null;
    }

    @Override // X.AbstractC02190iF
    public AbstractC02190iF A09(Class<?> cls) {
        return new AnonymousClass0C9(cls, this._elementType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC02190iF
    public AbstractC02190iF A0A(Class<?> cls) {
        AbstractC02190iF r1 = this._elementType;
        if (cls == r1._class) {
            return this;
        }
        return new AnonymousClass0C9(this._class, r1.A07(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC02190iF
    public AbstractC02190iF A0B(Class<?> cls) {
        AbstractC02190iF r1 = this._elementType;
        if (cls == r1._class) {
            return this;
        }
        return new AnonymousClass0C9(this._class, r1.A08(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC00960Oa
    public final String A0O() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        AbstractC02190iF r1 = this._elementType;
        if (r1 != null) {
            sb.append('<');
            sb.append(r1.A02());
            sb.append('>');
        }
        return sb.toString();
    }

    /* renamed from: A0P */
    public AnonymousClass0C9 A0C(Object obj) {
        return new AnonymousClass0C9(this._class, this._elementType.A0E(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    /* renamed from: A0Q */
    public AnonymousClass0C9 A0D(Object obj) {
        return new AnonymousClass0C9(this._class, this._elementType.A0F(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    /* renamed from: A0R */
    public AnonymousClass0C9 A0E(Object obj) {
        return new AnonymousClass0C9(this._class, this._elementType, this._valueHandler, obj, this._asStatic);
    }

    /* renamed from: A0S */
    public AnonymousClass0C9 A0F(Object obj) {
        return new AnonymousClass0C9(this._class, this._elementType, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC02190iF
    public String toString() {
        StringBuilder sb = new StringBuilder("[collection-like type; class ");
        sb.append(this._class.getName());
        sb.append(", contains ");
        sb.append(this._elementType);
        sb.append("]");
        return sb.toString();
    }

    public AnonymousClass0C9(Class<?> cls, AbstractC02190iF r8, Object obj, Object obj2, boolean z) {
        super(cls, r8.hashCode(), obj, obj2, z);
        this._elementType = r8;
    }

    @Override // X.AbstractC02190iF
    public final AbstractC02190iF A04() {
        return this._elementType;
    }
}
