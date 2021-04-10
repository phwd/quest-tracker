package X;

/* renamed from: X.02Z  reason: invalid class name */
public final class AnonymousClass02Z extends AnonymousClass073 {
    public static final long serialVersionUID = -7834910259750909424L;

    public static AnonymousClass02Z A00(Class<?> cls, AbstractC04000gb r7) {
        return new AnonymousClass02Z(cls, r7, null, null, false);
    }

    @Override // X.AbstractC04000gb, X.AnonymousClass073
    public final AbstractC04000gb A07(Class<?> cls) {
        return new AnonymousClass02Z(cls, this._elementType, null, null, this._asStatic);
    }

    @Override // X.AbstractC04000gb, X.AnonymousClass073
    public final AbstractC04000gb A08(Class<?> cls) {
        AbstractC04000gb r1 = this._elementType;
        if (cls == r1._class) {
            return this;
        }
        return new AnonymousClass02Z(this._class, r1.A06(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC04000gb, X.AnonymousClass073
    public final /* bridge */ /* synthetic */ AbstractC04000gb A09(Object obj) {
        return new AnonymousClass02Z(this._class, this._elementType.A0B(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC04000gb, X.AnonymousClass073
    public final /* bridge */ /* synthetic */ AbstractC04000gb A0A(Object obj) {
        return new AnonymousClass02Z(this._class, this._elementType.A0C(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC04000gb, X.AnonymousClass073
    public final /* bridge */ /* synthetic */ AbstractC04000gb A0B(Object obj) {
        return new AnonymousClass02Z(this._class, this._elementType, this._valueHandler, obj, this._asStatic);
    }

    @Override // X.AbstractC04000gb, X.AnonymousClass073
    public final /* bridge */ /* synthetic */ AbstractC04000gb A0C(Object obj) {
        return new AnonymousClass02Z(this._class, this._elementType, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass073
    public final /* bridge */ /* synthetic */ AnonymousClass073 A0L(Object obj) {
        return new AnonymousClass02Z(this._class, this._elementType.A0B(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass073
    public final /* bridge */ /* synthetic */ AnonymousClass073 A0M(Object obj) {
        return new AnonymousClass02Z(this._class, this._elementType.A0C(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass073
    public final /* bridge */ /* synthetic */ AnonymousClass073 A0N(Object obj) {
        return new AnonymousClass02Z(this._class, this._elementType, this._valueHandler, obj, this._asStatic);
    }

    @Override // X.AnonymousClass073
    public final /* bridge */ /* synthetic */ AnonymousClass073 A0O(Object obj) {
        return new AnonymousClass02Z(this._class, this._elementType, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC04000gb, X.AnonymousClass073
    public final String toString() {
        StringBuilder sb = new StringBuilder("[collection type; class ");
        sb.append(this._class.getName());
        sb.append(", contains ");
        sb.append(this._elementType);
        sb.append("]");
        return sb.toString();
    }

    public AnonymousClass02Z(Class<?> cls, AbstractC04000gb r2, Object obj, Object obj2, boolean z) {
        super(cls, r2, obj, obj2, z);
    }
}
