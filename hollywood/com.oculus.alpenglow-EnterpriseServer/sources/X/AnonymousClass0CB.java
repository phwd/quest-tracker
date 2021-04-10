package X;

/* renamed from: X.0CB  reason: invalid class name */
public class AnonymousClass0CB extends AbstractC01610Jq {
    public static final long serialVersionUID = 4611641304150899138L;
    public final AnonymousClass0aI _elementType;

    @Override // X.AnonymousClass0aI
    public final int A03() {
        return 1;
    }

    @Override // X.AnonymousClass0aI
    public final String A0G(int i) {
        if (i == 0) {
            return "E";
        }
        return null;
    }

    @Override // X.AnonymousClass0aI
    public final boolean A0K() {
        return true;
    }

    @Override // X.AnonymousClass0aI
    public final boolean A0N() {
        return true;
    }

    @Override // X.AnonymousClass0aI
    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            AnonymousClass0CB r5 = (AnonymousClass0CB) obj;
            if (this._class != r5._class || !this._elementType.equals(r5._elementType)) {
                return false;
            }
        }
        return true;
    }

    @Override // X.AnonymousClass0aI
    public final AnonymousClass0aI A04() {
        return this._elementType;
    }

    @Override // X.AnonymousClass0aI
    public final AnonymousClass0aI A06(int i) {
        if (i == 0) {
            return this._elementType;
        }
        return null;
    }

    @Override // X.AnonymousClass0aI
    public AnonymousClass0aI A09(Class<?> cls) {
        return new AnonymousClass0CB(cls, this._elementType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0aI
    public AnonymousClass0aI A0A(Class<?> cls) {
        AnonymousClass0aI r1 = this._elementType;
        if (cls == r1._class) {
            return this;
        }
        return new AnonymousClass0CB(this._class, r1.A07(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0aI
    public AnonymousClass0aI A0B(Class<?> cls) {
        AnonymousClass0aI r1 = this._elementType;
        if (cls == r1._class) {
            return this;
        }
        return new AnonymousClass0CB(this._class, r1.A08(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC01610Jq
    public final String A0O() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        AnonymousClass0aI r1 = this._elementType;
        if (r1 != null) {
            sb.append('<');
            sb.append(r1.A02());
            sb.append('>');
        }
        return sb.toString();
    }

    /* renamed from: A0P */
    public AnonymousClass0CB A0C(Object obj) {
        return new AnonymousClass0CB(this._class, this._elementType.A0E(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    /* renamed from: A0Q */
    public AnonymousClass0CB A0D(Object obj) {
        return new AnonymousClass0CB(this._class, this._elementType.A0F(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    /* renamed from: A0R */
    public AnonymousClass0CB A0E(Object obj) {
        return new AnonymousClass0CB(this._class, this._elementType, this._valueHandler, obj, this._asStatic);
    }

    /* renamed from: A0S */
    public AnonymousClass0CB A0F(Object obj) {
        return new AnonymousClass0CB(this._class, this._elementType, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0aI
    public String toString() {
        return "[collection-like type; class " + this._class.getName() + ", contains " + this._elementType + "]";
    }

    public AnonymousClass0CB(Class<?> cls, AnonymousClass0aI r8, Object obj, Object obj2, boolean z) {
        super(cls, r8.hashCode(), obj, obj2, z);
        this._elementType = r8;
    }
}
