package X;

import com.facebook.acra.util.JavaProcFileReader;

/* renamed from: X.072  reason: invalid class name */
public class AnonymousClass072 extends AnonymousClass0GF {
    public static final long serialVersionUID = 416067702302823522L;
    public final AbstractC04000gb _keyType;
    public final AbstractC04000gb _valueType;

    @Override // X.AbstractC04000gb
    public final int A02() {
        return 2;
    }

    @Override // X.AbstractC04000gb
    public final String A0D(int i) {
        if (i == 0) {
            return "K";
        }
        if (i == 1) {
            return "V";
        }
        return null;
    }

    @Override // X.AbstractC04000gb
    public final boolean A0I() {
        return true;
    }

    @Override // X.AbstractC04000gb
    public final boolean A0J() {
        return true;
    }

    @Override // X.AbstractC04000gb
    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            AnonymousClass072 r5 = (AnonymousClass072) obj;
            if (this._class != r5._class || !this._keyType.equals(r5._keyType) || !this._valueType.equals(r5._valueType)) {
                return false;
            }
        }
        return true;
    }

    @Override // X.AbstractC04000gb
    public final AbstractC04000gb A05(int i) {
        if (i == 0) {
            return this._keyType;
        }
        if (i == 1) {
            return this._valueType;
        }
        return null;
    }

    @Override // X.AbstractC04000gb
    public AbstractC04000gb A07(Class<?> cls) {
        return new AnonymousClass072(cls, this._keyType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC04000gb
    public AbstractC04000gb A08(Class<?> cls) {
        AbstractC04000gb r1 = this._valueType;
        if (cls == r1._class) {
            return this;
        }
        return new AnonymousClass072(this._class, this._keyType, r1.A06(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0GF
    public final String A0K() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        AbstractC04000gb r1 = this._keyType;
        if (r1 != null) {
            sb.append('<');
            sb.append(r1.A01());
            sb.append(',');
            sb.append(this._valueType.A01());
            sb.append('>');
        }
        return sb.toString();
    }

    public AbstractC04000gb A0L(Class<?> cls) {
        AbstractC04000gb r1 = this._keyType;
        if (cls == r1._class) {
            return this;
        }
        return new AnonymousClass072(this._class, r1.A06(cls), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    /* renamed from: A0M */
    public AnonymousClass072 A09(Object obj) {
        return new AnonymousClass072(this._class, this._keyType, this._valueType.A0B(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    /* renamed from: A0N */
    public AnonymousClass072 A0A(Object obj) {
        return new AnonymousClass072(this._class, this._keyType, this._valueType.A0C(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    public AnonymousClass072 A0O(Object obj) {
        return new AnonymousClass072(this._class, this._keyType.A0C(obj), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    /* renamed from: A0P */
    public AnonymousClass072 A0B(Object obj) {
        return new AnonymousClass072(this._class, this._keyType, this._valueType, this._valueHandler, obj, this._asStatic);
    }

    /* renamed from: A0Q */
    public AnonymousClass072 A0C(Object obj) {
        return new AnonymousClass072(this._class, this._keyType, this._valueType, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC04000gb
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

    public AnonymousClass072(Class<?> cls, AbstractC04000gb r8, AbstractC04000gb r9, Object obj, Object obj2, boolean z) {
        super(cls, r8.hashCode() ^ r9.hashCode(), obj, obj2, z);
        this._keyType = r8;
        this._valueType = r9;
    }

    @Override // X.AbstractC04000gb
    public final AbstractC04000gb A03() {
        return this._valueType;
    }

    @Override // X.AbstractC04000gb
    public final AbstractC04000gb A04() {
        return this._keyType;
    }
}
