package X;

/* renamed from: X.03E  reason: invalid class name */
public final class AnonymousClass03E extends AnonymousClass0C9 {
    public static final long serialVersionUID = -7834910259750909424L;

    public static AnonymousClass03E A00(Class<?> cls, AbstractC02190iF r7) {
        return new AnonymousClass03E(cls, r7, null, null, false);
    }

    @Override // X.AbstractC02190iF, X.AnonymousClass0C9
    public final AbstractC02190iF A09(Class<?> cls) {
        return new AnonymousClass03E(cls, this._elementType, null, null, this._asStatic);
    }

    @Override // X.AbstractC02190iF, X.AnonymousClass0C9
    public final AbstractC02190iF A0A(Class<?> cls) {
        AbstractC02190iF r1 = this._elementType;
        if (cls == r1._class) {
            return this;
        }
        return new AnonymousClass03E(this._class, r1.A07(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC02190iF, X.AnonymousClass0C9
    public final AbstractC02190iF A0B(Class<?> cls) {
        AbstractC02190iF r1 = this._elementType;
        if (cls == r1._class) {
            return this;
        }
        return new AnonymousClass03E(this._class, r1.A08(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC02190iF, X.AnonymousClass0C9
    public final /* bridge */ /* synthetic */ AbstractC02190iF A0C(Object obj) {
        return new AnonymousClass03E(this._class, this._elementType.A0E(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC02190iF, X.AnonymousClass0C9
    public final /* bridge */ /* synthetic */ AbstractC02190iF A0D(Object obj) {
        return new AnonymousClass03E(this._class, this._elementType.A0F(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC02190iF, X.AnonymousClass0C9
    public final /* bridge */ /* synthetic */ AbstractC02190iF A0E(Object obj) {
        return new AnonymousClass03E(this._class, this._elementType, this._valueHandler, obj, this._asStatic);
    }

    @Override // X.AbstractC02190iF, X.AnonymousClass0C9
    public final /* bridge */ /* synthetic */ AbstractC02190iF A0F(Object obj) {
        return new AnonymousClass03E(this._class, this._elementType, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0C9
    public final /* bridge */ /* synthetic */ AnonymousClass0C9 A0P(Object obj) {
        return new AnonymousClass03E(this._class, this._elementType.A0E(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0C9
    public final /* bridge */ /* synthetic */ AnonymousClass0C9 A0Q(Object obj) {
        return new AnonymousClass03E(this._class, this._elementType.A0F(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0C9
    public final /* bridge */ /* synthetic */ AnonymousClass0C9 A0R(Object obj) {
        return new AnonymousClass03E(this._class, this._elementType, this._valueHandler, obj, this._asStatic);
    }

    @Override // X.AnonymousClass0C9
    public final /* bridge */ /* synthetic */ AnonymousClass0C9 A0S(Object obj) {
        return new AnonymousClass03E(this._class, this._elementType, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC02190iF, X.AnonymousClass0C9
    public final String toString() {
        StringBuilder sb = new StringBuilder("[collection type; class ");
        sb.append(this._class.getName());
        sb.append(", contains ");
        sb.append(this._elementType);
        sb.append("]");
        return sb.toString();
    }

    public AnonymousClass03E(Class<?> cls, AbstractC02190iF r2, Object obj, Object obj2, boolean z) {
        super(cls, r2, obj, obj2, z);
    }
}
