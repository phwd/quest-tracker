package X;

import com.facebook.acra.util.JavaProcFileReader;

/* renamed from: X.02Y  reason: invalid class name */
public final class AnonymousClass02Y extends AnonymousClass072 {
    public static final long serialVersionUID = -811146779148281500L;

    public static AnonymousClass02Y A00(Class<?> cls, AbstractC04000gb r8, AbstractC04000gb r9) {
        return new AnonymousClass02Y(cls, r8, r9, null, null, false);
    }

    @Override // X.AnonymousClass072, X.AbstractC04000gb
    public final AbstractC04000gb A07(Class<?> cls) {
        return new AnonymousClass02Y(cls, this._keyType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass072, X.AbstractC04000gb
    public final AbstractC04000gb A08(Class<?> cls) {
        AbstractC04000gb r1 = this._valueType;
        if (cls == r1._class) {
            return this;
        }
        return new AnonymousClass02Y(this._class, this._keyType, r1.A06(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass072, X.AbstractC04000gb
    public final /* bridge */ /* synthetic */ AbstractC04000gb A09(Object obj) {
        return new AnonymousClass02Y(this._class, this._keyType, this._valueType.A0B(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass072, X.AbstractC04000gb
    public final /* bridge */ /* synthetic */ AbstractC04000gb A0A(Object obj) {
        return new AnonymousClass02Y(this._class, this._keyType, this._valueType.A0C(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass072, X.AbstractC04000gb
    public final /* bridge */ /* synthetic */ AbstractC04000gb A0B(Object obj) {
        return new AnonymousClass02Y(this._class, this._keyType, this._valueType, this._valueHandler, obj, this._asStatic);
    }

    @Override // X.AnonymousClass072, X.AbstractC04000gb
    public final /* bridge */ /* synthetic */ AbstractC04000gb A0C(Object obj) {
        return new AnonymousClass02Y(this._class, this._keyType, this._valueType, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass072
    public final AbstractC04000gb A0L(Class<?> cls) {
        AbstractC04000gb r1 = this._keyType;
        if (cls == r1._class) {
            return this;
        }
        return new AnonymousClass02Y(this._class, r1.A06(cls), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass072
    public final /* bridge */ /* synthetic */ AnonymousClass072 A0M(Object obj) {
        return new AnonymousClass02Y(this._class, this._keyType, this._valueType.A0B(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass072
    public final /* bridge */ /* synthetic */ AnonymousClass072 A0N(Object obj) {
        return new AnonymousClass02Y(this._class, this._keyType, this._valueType.A0C(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass072
    public final AnonymousClass072 A0O(Object obj) {
        return new AnonymousClass02Y(this._class, this._keyType.A0C(obj), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass072
    public final /* bridge */ /* synthetic */ AnonymousClass072 A0P(Object obj) {
        return new AnonymousClass02Y(this._class, this._keyType, this._valueType, this._valueHandler, obj, this._asStatic);
    }

    @Override // X.AnonymousClass072
    public final /* bridge */ /* synthetic */ AnonymousClass072 A0Q(Object obj) {
        return new AnonymousClass02Y(this._class, this._keyType, this._valueType, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass072, X.AbstractC04000gb
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

    public AnonymousClass02Y(Class<?> cls, AbstractC04000gb r2, AbstractC04000gb r3, Object obj, Object obj2, boolean z) {
        super(cls, r2, r3, obj, obj2, z);
    }
}
