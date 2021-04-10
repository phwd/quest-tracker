package X;

import com.facebook.acra.util.JavaProcFileReader;

/* renamed from: X.74  reason: invalid class name */
public class AnonymousClass74 extends Bt {
    public static final long serialVersionUID = 416067702302823522L;
    public final AbstractC0224Wl _keyType;
    public final AbstractC0224Wl _valueType;

    @Override // X.AbstractC0224Wl
    public final int A02() {
        return 2;
    }

    @Override // X.AbstractC0224Wl
    public final String A0D(int i) {
        if (i == 0) {
            return "K";
        }
        if (i == 1) {
            return "V";
        }
        return null;
    }

    @Override // X.AbstractC0224Wl
    public final boolean A0I() {
        return true;
    }

    @Override // X.AbstractC0224Wl
    public final boolean A0J() {
        return true;
    }

    @Override // X.AbstractC0224Wl
    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            AnonymousClass74 r5 = (AnonymousClass74) obj;
            if (this._class != r5._class || !this._keyType.equals(r5._keyType) || !this._valueType.equals(r5._valueType)) {
                return false;
            }
        }
        return true;
    }

    @Override // X.AbstractC0224Wl
    public final AbstractC0224Wl A05(int i) {
        if (i == 0) {
            return this._keyType;
        }
        if (i == 1) {
            return this._valueType;
        }
        return null;
    }

    @Override // X.AbstractC0224Wl
    public AbstractC0224Wl A07(Class<?> cls) {
        return new AnonymousClass74(cls, this._keyType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC0224Wl
    public AbstractC0224Wl A08(Class<?> cls) {
        AbstractC0224Wl wl = this._valueType;
        if (cls == wl._class) {
            return this;
        }
        return new AnonymousClass74(this._class, this._keyType, wl.A06(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.Bt
    public final String A0K() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        AbstractC0224Wl wl = this._keyType;
        if (wl != null) {
            sb.append('<');
            sb.append(wl.A01());
            sb.append(',');
            sb.append(this._valueType.A01());
            sb.append('>');
        }
        return sb.toString();
    }

    public AbstractC0224Wl A0L(Class<?> cls) {
        AbstractC0224Wl wl = this._keyType;
        if (cls == wl._class) {
            return this;
        }
        return new AnonymousClass74(this._class, wl.A06(cls), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    /* renamed from: A0M */
    public AnonymousClass74 A09(Object obj) {
        return new AnonymousClass74(this._class, this._keyType, this._valueType.A0B(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    /* renamed from: A0N */
    public AnonymousClass74 A0A(Object obj) {
        return new AnonymousClass74(this._class, this._keyType, this._valueType.A0C(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    public AnonymousClass74 A0O(Object obj) {
        return new AnonymousClass74(this._class, this._keyType.A0C(obj), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    /* renamed from: A0P */
    public AnonymousClass74 A0B(Object obj) {
        return new AnonymousClass74(this._class, this._keyType, this._valueType, this._valueHandler, obj, this._asStatic);
    }

    /* renamed from: A0Q */
    public AnonymousClass74 A0C(Object obj) {
        return new AnonymousClass74(this._class, this._keyType, this._valueType, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC0224Wl
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

    public AnonymousClass74(Class<?> cls, AbstractC0224Wl wl, AbstractC0224Wl wl2, Object obj, Object obj2, boolean z) {
        super(cls, wl.hashCode() ^ wl2.hashCode(), obj, obj2, z);
        this._keyType = wl;
        this._valueType = wl2;
    }

    @Override // X.AbstractC0224Wl
    public final AbstractC0224Wl A03() {
        return this._valueType;
    }

    @Override // X.AbstractC0224Wl
    public final AbstractC0224Wl A04() {
        return this._keyType;
    }
}
