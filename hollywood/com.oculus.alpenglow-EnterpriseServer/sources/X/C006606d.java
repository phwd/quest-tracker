package X;

/* renamed from: X.06d  reason: invalid class name and case insensitive filesystem */
public final class C006606d extends AnonymousClass0CB {
    public static final long serialVersionUID = -7834910259750909424L;

    public static C006606d A00(Class<?> cls, AnonymousClass0aI r7) {
        return new C006606d(cls, r7, null, null, false);
    }

    @Override // X.AnonymousClass0CB, X.AnonymousClass0aI
    public final AnonymousClass0aI A09(Class<?> cls) {
        return new C006606d(cls, this._elementType, null, null, this._asStatic);
    }

    @Override // X.AnonymousClass0CB, X.AnonymousClass0aI
    public final AnonymousClass0aI A0A(Class<?> cls) {
        AnonymousClass0aI r1 = this._elementType;
        if (cls == r1._class) {
            return this;
        }
        return new C006606d(this._class, r1.A07(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0CB, X.AnonymousClass0aI
    public final AnonymousClass0aI A0B(Class<?> cls) {
        AnonymousClass0aI r1 = this._elementType;
        if (cls == r1._class) {
            return this;
        }
        return new C006606d(this._class, r1.A08(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0CB, X.AnonymousClass0aI
    public final /* bridge */ /* synthetic */ AnonymousClass0aI A0C(Object obj) {
        return new C006606d(this._class, this._elementType.A0E(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0CB, X.AnonymousClass0aI
    public final /* bridge */ /* synthetic */ AnonymousClass0aI A0D(Object obj) {
        return new C006606d(this._class, this._elementType.A0F(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0CB, X.AnonymousClass0aI
    public final /* bridge */ /* synthetic */ AnonymousClass0aI A0E(Object obj) {
        return new C006606d(this._class, this._elementType, this._valueHandler, obj, this._asStatic);
    }

    @Override // X.AnonymousClass0CB, X.AnonymousClass0aI
    public final /* bridge */ /* synthetic */ AnonymousClass0aI A0F(Object obj) {
        return new C006606d(this._class, this._elementType, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0CB
    public final /* bridge */ /* synthetic */ AnonymousClass0CB A0P(Object obj) {
        return new C006606d(this._class, this._elementType.A0E(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0CB
    public final /* bridge */ /* synthetic */ AnonymousClass0CB A0Q(Object obj) {
        return new C006606d(this._class, this._elementType.A0F(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0CB
    public final /* bridge */ /* synthetic */ AnonymousClass0CB A0R(Object obj) {
        return new C006606d(this._class, this._elementType, this._valueHandler, obj, this._asStatic);
    }

    @Override // X.AnonymousClass0CB
    public final /* bridge */ /* synthetic */ AnonymousClass0CB A0S(Object obj) {
        return new C006606d(this._class, this._elementType, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0CB, X.AnonymousClass0aI
    public final String toString() {
        return "[collection type; class " + this._class.getName() + ", contains " + this._elementType + "]";
    }

    public C006606d(Class<?> cls, AnonymousClass0aI r2, Object obj, Object obj2, boolean z) {
        super(cls, r2, obj, obj2, z);
    }
}
