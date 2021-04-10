package X;

import com.facebook.acra.util.JavaProcFileReader;

/* renamed from: X.03D  reason: invalid class name */
public final class AnonymousClass03D extends AnonymousClass0C8 {
    public static final long serialVersionUID = -811146779148281500L;

    public static AnonymousClass03D A00(Class<?> cls, AbstractC02190iF r8, AbstractC02190iF r9) {
        return new AnonymousClass03D(cls, r8, r9, null, null, false);
    }

    @Override // X.AnonymousClass0C8, X.AbstractC02190iF
    public final AbstractC02190iF A09(Class<?> cls) {
        return new AnonymousClass03D(cls, this._keyType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0C8, X.AbstractC02190iF
    public final AbstractC02190iF A0A(Class<?> cls) {
        AbstractC02190iF r1 = this._valueType;
        if (cls == r1._class) {
            return this;
        }
        return new AnonymousClass03D(this._class, this._keyType, r1.A07(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0C8, X.AbstractC02190iF
    public final AbstractC02190iF A0B(Class<?> cls) {
        AbstractC02190iF r1 = this._valueType;
        if (cls == r1._class) {
            return this;
        }
        return new AnonymousClass03D(this._class, this._keyType, r1.A08(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0C8, X.AbstractC02190iF
    public final /* bridge */ /* synthetic */ AbstractC02190iF A0C(Object obj) {
        return new AnonymousClass03D(this._class, this._keyType, this._valueType.A0E(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0C8, X.AbstractC02190iF
    public final /* bridge */ /* synthetic */ AbstractC02190iF A0D(Object obj) {
        return new AnonymousClass03D(this._class, this._keyType, this._valueType.A0F(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0C8, X.AbstractC02190iF
    public final /* bridge */ /* synthetic */ AbstractC02190iF A0E(Object obj) {
        return new AnonymousClass03D(this._class, this._keyType, this._valueType, this._valueHandler, obj, this._asStatic);
    }

    @Override // X.AnonymousClass0C8, X.AbstractC02190iF
    public final /* bridge */ /* synthetic */ AbstractC02190iF A0F(Object obj) {
        return new AnonymousClass03D(this._class, this._keyType, this._valueType, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0C8
    public final AbstractC02190iF A0P(Class<?> cls) {
        AbstractC02190iF r1 = this._keyType;
        if (cls == r1._class) {
            return this;
        }
        return new AnonymousClass03D(this._class, r1.A07(cls), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0C8
    public final /* bridge */ /* synthetic */ AnonymousClass0C8 A0Q(Object obj) {
        return new AnonymousClass03D(this._class, this._keyType, this._valueType.A0E(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0C8
    public final /* bridge */ /* synthetic */ AnonymousClass0C8 A0R(Object obj) {
        return new AnonymousClass03D(this._class, this._keyType, this._valueType.A0F(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0C8
    public final AnonymousClass0C8 A0S(Object obj) {
        return new AnonymousClass03D(this._class, this._keyType.A0F(obj), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0C8
    public final /* bridge */ /* synthetic */ AnonymousClass0C8 A0T(Object obj) {
        return new AnonymousClass03D(this._class, this._keyType, this._valueType, this._valueHandler, obj, this._asStatic);
    }

    @Override // X.AnonymousClass0C8
    public final /* bridge */ /* synthetic */ AnonymousClass0C8 A0U(Object obj) {
        return new AnonymousClass03D(this._class, this._keyType, this._valueType, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0C8, X.AbstractC02190iF
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

    public AnonymousClass03D(Class<?> cls, AbstractC02190iF r2, AbstractC02190iF r3, Object obj, Object obj2, boolean z) {
        super(cls, r2, r3, obj, obj2, z);
    }
}
