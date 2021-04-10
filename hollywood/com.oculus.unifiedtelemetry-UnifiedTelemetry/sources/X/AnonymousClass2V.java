package X;

import com.facebook.acra.util.JavaProcFileReader;

/* renamed from: X.2V  reason: invalid class name */
public final class AnonymousClass2V extends AnonymousClass74 {
    public static final long serialVersionUID = -811146779148281500L;

    public static AnonymousClass2V A00(Class<?> cls, AbstractC0224Wl wl, AbstractC0224Wl wl2) {
        return new AnonymousClass2V(cls, wl, wl2, null, null, false);
    }

    @Override // X.AbstractC0224Wl, X.AnonymousClass74
    public final AbstractC0224Wl A07(Class<?> cls) {
        return new AnonymousClass2V(cls, this._keyType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC0224Wl, X.AnonymousClass74
    public final AbstractC0224Wl A08(Class<?> cls) {
        AbstractC0224Wl wl = this._valueType;
        if (cls == wl._class) {
            return this;
        }
        return new AnonymousClass2V(this._class, this._keyType, wl.A06(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC0224Wl, X.AnonymousClass74
    public final /* bridge */ /* synthetic */ AbstractC0224Wl A09(Object obj) {
        return new AnonymousClass2V(this._class, this._keyType, this._valueType.A0B(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC0224Wl, X.AnonymousClass74
    public final /* bridge */ /* synthetic */ AbstractC0224Wl A0A(Object obj) {
        return new AnonymousClass2V(this._class, this._keyType, this._valueType.A0C(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC0224Wl, X.AnonymousClass74
    public final /* bridge */ /* synthetic */ AbstractC0224Wl A0B(Object obj) {
        return new AnonymousClass2V(this._class, this._keyType, this._valueType, this._valueHandler, obj, this._asStatic);
    }

    @Override // X.AbstractC0224Wl, X.AnonymousClass74
    public final /* bridge */ /* synthetic */ AbstractC0224Wl A0C(Object obj) {
        return new AnonymousClass2V(this._class, this._keyType, this._valueType, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass74
    public final AbstractC0224Wl A0L(Class<?> cls) {
        AbstractC0224Wl wl = this._keyType;
        if (cls == wl._class) {
            return this;
        }
        return new AnonymousClass2V(this._class, wl.A06(cls), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass74
    public final /* bridge */ /* synthetic */ AnonymousClass74 A0M(Object obj) {
        return new AnonymousClass2V(this._class, this._keyType, this._valueType.A0B(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass74
    public final /* bridge */ /* synthetic */ AnonymousClass74 A0N(Object obj) {
        return new AnonymousClass2V(this._class, this._keyType, this._valueType.A0C(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass74
    public final AnonymousClass74 A0O(Object obj) {
        return new AnonymousClass2V(this._class, this._keyType.A0C(obj), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass74
    public final /* bridge */ /* synthetic */ AnonymousClass74 A0P(Object obj) {
        return new AnonymousClass2V(this._class, this._keyType, this._valueType, this._valueHandler, obj, this._asStatic);
    }

    @Override // X.AnonymousClass74
    public final /* bridge */ /* synthetic */ AnonymousClass74 A0Q(Object obj) {
        return new AnonymousClass2V(this._class, this._keyType, this._valueType, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC0224Wl, X.AnonymousClass74
    public final String toString() {
        StringBuilder sb = new StringBuilder("[map type; class ");
        sb.append(this._class.getName());
        sb.append(", ");
        sb.append(this._keyType);
        sb.append(JavaProcFileReader.LS_SYMLINK_ARROW);
        sb.append(this._valueType);
        sb.append("]");
        return sb.toString();
    }

    public AnonymousClass2V(Class<?> cls, AbstractC0224Wl wl, AbstractC0224Wl wl2, Object obj, Object obj2, boolean z) {
        super(cls, wl, wl2, obj, obj2, z);
    }
}
