package X;

/* renamed from: X.2W  reason: invalid class name */
public final class AnonymousClass2W extends AnonymousClass75 {
    public static final long serialVersionUID = -7834910259750909424L;

    public static AnonymousClass2W A00(Class<?> cls, AbstractC0224Wl wl) {
        return new AnonymousClass2W(cls, wl, null, null, false);
    }

    @Override // X.AbstractC0224Wl, X.AnonymousClass75
    public final AbstractC0224Wl A07(Class<?> cls) {
        return new AnonymousClass2W(cls, this._elementType, null, null, this._asStatic);
    }

    @Override // X.AbstractC0224Wl, X.AnonymousClass75
    public final AbstractC0224Wl A08(Class<?> cls) {
        AbstractC0224Wl wl = this._elementType;
        if (cls == wl._class) {
            return this;
        }
        return new AnonymousClass2W(this._class, wl.A06(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC0224Wl, X.AnonymousClass75
    public final /* bridge */ /* synthetic */ AbstractC0224Wl A09(Object obj) {
        return new AnonymousClass2W(this._class, this._elementType.A0B(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC0224Wl, X.AnonymousClass75
    public final /* bridge */ /* synthetic */ AbstractC0224Wl A0A(Object obj) {
        return new AnonymousClass2W(this._class, this._elementType.A0C(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC0224Wl, X.AnonymousClass75
    public final /* bridge */ /* synthetic */ AbstractC0224Wl A0B(Object obj) {
        return new AnonymousClass2W(this._class, this._elementType, this._valueHandler, obj, this._asStatic);
    }

    @Override // X.AbstractC0224Wl, X.AnonymousClass75
    public final /* bridge */ /* synthetic */ AbstractC0224Wl A0C(Object obj) {
        return new AnonymousClass2W(this._class, this._elementType, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass75
    public final /* bridge */ /* synthetic */ AnonymousClass75 A0L(Object obj) {
        return new AnonymousClass2W(this._class, this._elementType.A0B(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass75
    public final /* bridge */ /* synthetic */ AnonymousClass75 A0M(Object obj) {
        return new AnonymousClass2W(this._class, this._elementType.A0C(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass75
    public final /* bridge */ /* synthetic */ AnonymousClass75 A0N(Object obj) {
        return new AnonymousClass2W(this._class, this._elementType, this._valueHandler, obj, this._asStatic);
    }

    @Override // X.AnonymousClass75
    public final /* bridge */ /* synthetic */ AnonymousClass75 A0O(Object obj) {
        return new AnonymousClass2W(this._class, this._elementType, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC0224Wl, X.AnonymousClass75
    public final String toString() {
        StringBuilder sb = new StringBuilder("[collection type; class ");
        sb.append(this._class.getName());
        sb.append(", contains ");
        sb.append(this._elementType);
        sb.append("]");
        return sb.toString();
    }

    public AnonymousClass2W(Class<?> cls, AbstractC0224Wl wl, Object obj, Object obj2, boolean z) {
        super(cls, wl, obj, obj2, z);
    }
}
