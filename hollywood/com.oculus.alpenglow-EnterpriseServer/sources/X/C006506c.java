package X;

/* renamed from: X.06c  reason: invalid class name and case insensitive filesystem */
public final class C006506c extends AnonymousClass0CA {
    public static final long serialVersionUID = -811146779148281500L;

    public static C006506c A00(Class<?> cls, AnonymousClass0aI r8, AnonymousClass0aI r9) {
        return new C006506c(cls, r8, r9, null, null, false);
    }

    @Override // X.AnonymousClass0CA, X.AnonymousClass0aI
    public final AnonymousClass0aI A09(Class<?> cls) {
        return new C006506c(cls, this._keyType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0CA, X.AnonymousClass0aI
    public final AnonymousClass0aI A0A(Class<?> cls) {
        AnonymousClass0aI r1 = this._valueType;
        if (cls == r1._class) {
            return this;
        }
        return new C006506c(this._class, this._keyType, r1.A07(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0CA, X.AnonymousClass0aI
    public final AnonymousClass0aI A0B(Class<?> cls) {
        AnonymousClass0aI r1 = this._valueType;
        if (cls == r1._class) {
            return this;
        }
        return new C006506c(this._class, this._keyType, r1.A08(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0CA, X.AnonymousClass0aI
    public final /* bridge */ /* synthetic */ AnonymousClass0aI A0C(Object obj) {
        return new C006506c(this._class, this._keyType, this._valueType.A0E(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0CA, X.AnonymousClass0aI
    public final /* bridge */ /* synthetic */ AnonymousClass0aI A0D(Object obj) {
        return new C006506c(this._class, this._keyType, this._valueType.A0F(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0CA, X.AnonymousClass0aI
    public final /* bridge */ /* synthetic */ AnonymousClass0aI A0E(Object obj) {
        return new C006506c(this._class, this._keyType, this._valueType, this._valueHandler, obj, this._asStatic);
    }

    @Override // X.AnonymousClass0CA, X.AnonymousClass0aI
    public final /* bridge */ /* synthetic */ AnonymousClass0aI A0F(Object obj) {
        return new C006506c(this._class, this._keyType, this._valueType, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0CA
    public final AnonymousClass0aI A0P(Class<?> cls) {
        AnonymousClass0aI r1 = this._keyType;
        if (cls == r1._class) {
            return this;
        }
        return new C006506c(this._class, r1.A07(cls), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0CA
    public final /* bridge */ /* synthetic */ AnonymousClass0CA A0Q(Object obj) {
        return new C006506c(this._class, this._keyType, this._valueType.A0E(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0CA
    public final /* bridge */ /* synthetic */ AnonymousClass0CA A0R(Object obj) {
        return new C006506c(this._class, this._keyType, this._valueType.A0F(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0CA
    public final AnonymousClass0CA A0S(Object obj) {
        return new C006506c(this._class, this._keyType.A0F(obj), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0CA
    public final /* bridge */ /* synthetic */ AnonymousClass0CA A0T(Object obj) {
        return new C006506c(this._class, this._keyType, this._valueType, this._valueHandler, obj, this._asStatic);
    }

    @Override // X.AnonymousClass0CA
    public final /* bridge */ /* synthetic */ AnonymousClass0CA A0U(Object obj) {
        return new C006506c(this._class, this._keyType, this._valueType, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0CA, X.AnonymousClass0aI
    public final String toString() {
        return "[map type; class " + this._class.getName() + ", " + this._keyType + " -> " + this._valueType + "]";
    }

    public C006506c(Class<?> cls, AnonymousClass0aI r2, AnonymousClass0aI r3, Object obj, Object obj2, boolean z) {
        super(cls, r2, r3, obj, obj2, z);
    }
}
