package X;

import com.facebook.acra.util.JavaProcFileReader;

/* renamed from: X.0C8  reason: invalid class name */
public class AnonymousClass0C8 extends AbstractC00960Oa {
    public static final long serialVersionUID = 416067702302823522L;
    public final AbstractC02190iF _keyType;
    public final AbstractC02190iF _valueType;

    @Override // X.AbstractC02190iF
    public final int A03() {
        return 2;
    }

    @Override // X.AbstractC02190iF
    public final String A0G(int i) {
        if (i == 0) {
            return "K";
        }
        if (i == 1) {
            return "V";
        }
        return null;
    }

    @Override // X.AbstractC02190iF
    public final boolean A0M() {
        return true;
    }

    @Override // X.AbstractC02190iF
    public final boolean A0N() {
        return true;
    }

    @Override // X.AbstractC02190iF
    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            AnonymousClass0C8 r5 = (AnonymousClass0C8) obj;
            if (this._class != r5._class || !this._keyType.equals(r5._keyType) || !this._valueType.equals(r5._valueType)) {
                return false;
            }
        }
        return true;
    }

    @Override // X.AbstractC02190iF
    public final AbstractC02190iF A06(int i) {
        if (i == 0) {
            return this._keyType;
        }
        if (i == 1) {
            return this._valueType;
        }
        return null;
    }

    @Override // X.AbstractC02190iF
    public AbstractC02190iF A09(Class<?> cls) {
        return new AnonymousClass0C8(cls, this._keyType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC02190iF
    public AbstractC02190iF A0A(Class<?> cls) {
        AbstractC02190iF r1 = this._valueType;
        if (cls == r1._class) {
            return this;
        }
        return new AnonymousClass0C8(this._class, this._keyType, r1.A07(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC02190iF
    public AbstractC02190iF A0B(Class<?> cls) {
        AbstractC02190iF r1 = this._valueType;
        if (cls == r1._class) {
            return this;
        }
        return new AnonymousClass0C8(this._class, this._keyType, r1.A08(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC00960Oa
    public final String A0O() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        AbstractC02190iF r1 = this._keyType;
        if (r1 != null) {
            sb.append('<');
            sb.append(r1.A02());
            sb.append(',');
            sb.append(this._valueType.A02());
            sb.append('>');
        }
        return sb.toString();
    }

    public AbstractC02190iF A0P(Class<?> cls) {
        AbstractC02190iF r1 = this._keyType;
        if (cls == r1._class) {
            return this;
        }
        return new AnonymousClass0C8(this._class, r1.A07(cls), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    /* renamed from: A0Q */
    public AnonymousClass0C8 A0C(Object obj) {
        return new AnonymousClass0C8(this._class, this._keyType, this._valueType.A0E(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    /* renamed from: A0R */
    public AnonymousClass0C8 A0D(Object obj) {
        return new AnonymousClass0C8(this._class, this._keyType, this._valueType.A0F(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    public AnonymousClass0C8 A0S(Object obj) {
        return new AnonymousClass0C8(this._class, this._keyType.A0F(obj), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    /* renamed from: A0T */
    public AnonymousClass0C8 A0E(Object obj) {
        return new AnonymousClass0C8(this._class, this._keyType, this._valueType, this._valueHandler, obj, this._asStatic);
    }

    /* renamed from: A0U */
    public AnonymousClass0C8 A0F(Object obj) {
        return new AnonymousClass0C8(this._class, this._keyType, this._valueType, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC02190iF
    public String toString() {
        StringBuilder sb = new StringBuilder("[map-like type; class ");
        sb.append(this._class.getName());
        sb.append(", ");
        sb.append(this._keyType);
        sb.append(JavaProcFileReader.LS_SYMLINK_ARROW);
        sb.append(this._valueType);
        sb.append("]");
        return sb.toString();
    }

    public AnonymousClass0C8(Class<?> cls, AbstractC02190iF r8, AbstractC02190iF r9, Object obj, Object obj2, boolean z) {
        super(cls, r8.hashCode() ^ r9.hashCode(), obj, obj2, z);
        this._keyType = r8;
        this._valueType = r9;
    }

    @Override // X.AbstractC02190iF
    public final AbstractC02190iF A04() {
        return this._valueType;
    }

    @Override // X.AbstractC02190iF
    public final AbstractC02190iF A05() {
        return this._keyType;
    }
}
