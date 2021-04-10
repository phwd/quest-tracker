package X;

/* renamed from: X.0CA  reason: invalid class name */
public class AnonymousClass0CA extends AbstractC01610Jq {
    public static final long serialVersionUID = 416067702302823522L;
    public final AnonymousClass0aI _keyType;
    public final AnonymousClass0aI _valueType;

    @Override // X.AnonymousClass0aI
    public final int A03() {
        return 2;
    }

    @Override // X.AnonymousClass0aI
    public final String A0G(int i) {
        if (i == 0) {
            return "K";
        }
        if (i == 1) {
            return "V";
        }
        return null;
    }

    @Override // X.AnonymousClass0aI
    public final boolean A0M() {
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
            AnonymousClass0CA r5 = (AnonymousClass0CA) obj;
            if (this._class != r5._class || !this._keyType.equals(r5._keyType) || !this._valueType.equals(r5._valueType)) {
                return false;
            }
        }
        return true;
    }

    @Override // X.AnonymousClass0aI
    public final AnonymousClass0aI A04() {
        return this._valueType;
    }

    @Override // X.AnonymousClass0aI
    public final AnonymousClass0aI A05() {
        return this._keyType;
    }

    @Override // X.AnonymousClass0aI
    public final AnonymousClass0aI A06(int i) {
        if (i == 0) {
            return this._keyType;
        }
        if (i == 1) {
            return this._valueType;
        }
        return null;
    }

    @Override // X.AnonymousClass0aI
    public AnonymousClass0aI A09(Class<?> cls) {
        return new AnonymousClass0CA(cls, this._keyType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0aI
    public AnonymousClass0aI A0A(Class<?> cls) {
        AnonymousClass0aI r1 = this._valueType;
        if (cls == r1._class) {
            return this;
        }
        return new AnonymousClass0CA(this._class, this._keyType, r1.A07(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0aI
    public AnonymousClass0aI A0B(Class<?> cls) {
        AnonymousClass0aI r1 = this._valueType;
        if (cls == r1._class) {
            return this;
        }
        return new AnonymousClass0CA(this._class, this._keyType, r1.A08(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC01610Jq
    public final String A0O() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        AnonymousClass0aI r1 = this._keyType;
        if (r1 != null) {
            sb.append('<');
            sb.append(r1.A02());
            sb.append(',');
            sb.append(this._valueType.A02());
            sb.append('>');
        }
        return sb.toString();
    }

    public AnonymousClass0aI A0P(Class<?> cls) {
        AnonymousClass0aI r1 = this._keyType;
        if (cls == r1._class) {
            return this;
        }
        return new AnonymousClass0CA(this._class, r1.A07(cls), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    /* renamed from: A0Q */
    public AnonymousClass0CA A0C(Object obj) {
        return new AnonymousClass0CA(this._class, this._keyType, this._valueType.A0E(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    /* renamed from: A0R */
    public AnonymousClass0CA A0D(Object obj) {
        return new AnonymousClass0CA(this._class, this._keyType, this._valueType.A0F(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    public AnonymousClass0CA A0S(Object obj) {
        return new AnonymousClass0CA(this._class, this._keyType.A0F(obj), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    /* renamed from: A0T */
    public AnonymousClass0CA A0E(Object obj) {
        return new AnonymousClass0CA(this._class, this._keyType, this._valueType, this._valueHandler, obj, this._asStatic);
    }

    /* renamed from: A0U */
    public AnonymousClass0CA A0F(Object obj) {
        return new AnonymousClass0CA(this._class, this._keyType, this._valueType, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0aI
    public String toString() {
        return "[map-like type; class " + this._class.getName() + ", " + this._keyType + " -> " + this._valueType + "]";
    }

    public AnonymousClass0CA(Class<?> cls, AnonymousClass0aI r8, AnonymousClass0aI r9, Object obj, Object obj2, boolean z) {
        super(cls, r8.hashCode() ^ r9.hashCode(), obj, obj2, z);
        this._keyType = r8;
        this._valueType = r9;
    }
}
