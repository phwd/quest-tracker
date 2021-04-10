package X;

/* renamed from: X.75  reason: invalid class name */
public class AnonymousClass75 extends Bt {
    public static final long serialVersionUID = 4611641304150899138L;
    public final AbstractC0224Wl _elementType;

    @Override // X.AbstractC0224Wl
    public final int A02() {
        return 1;
    }

    @Override // X.AbstractC0224Wl
    public final String A0D(int i) {
        if (i == 0) {
            return "E";
        }
        return null;
    }

    @Override // X.AbstractC0224Wl
    public final boolean A0G() {
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
            AnonymousClass75 r5 = (AnonymousClass75) obj;
            if (this._class != r5._class || !this._elementType.equals(r5._elementType)) {
                return false;
            }
        }
        return true;
    }

    @Override // X.AbstractC0224Wl
    public final AbstractC0224Wl A05(int i) {
        if (i == 0) {
            return this._elementType;
        }
        return null;
    }

    @Override // X.AbstractC0224Wl
    public AbstractC0224Wl A07(Class<?> cls) {
        return new AnonymousClass75(cls, this._elementType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC0224Wl
    public AbstractC0224Wl A08(Class<?> cls) {
        AbstractC0224Wl wl = this._elementType;
        if (cls == wl._class) {
            return this;
        }
        return new AnonymousClass75(this._class, wl.A06(cls), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.Bt
    public final String A0K() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        AbstractC0224Wl wl = this._elementType;
        if (wl != null) {
            sb.append('<');
            sb.append(wl.A01());
            sb.append('>');
        }
        return sb.toString();
    }

    /* renamed from: A0L */
    public AnonymousClass75 A09(Object obj) {
        return new AnonymousClass75(this._class, this._elementType.A0B(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    /* renamed from: A0M */
    public AnonymousClass75 A0A(Object obj) {
        return new AnonymousClass75(this._class, this._elementType.A0C(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    /* renamed from: A0N */
    public AnonymousClass75 A0B(Object obj) {
        return new AnonymousClass75(this._class, this._elementType, this._valueHandler, obj, this._asStatic);
    }

    /* renamed from: A0O */
    public AnonymousClass75 A0C(Object obj) {
        return new AnonymousClass75(this._class, this._elementType, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC0224Wl
    public String toString() {
        StringBuilder sb = new StringBuilder("[collection-like type; class ");
        sb.append(this._class.getName());
        sb.append(", contains ");
        sb.append(this._elementType);
        sb.append("]");
        return sb.toString();
    }

    public AnonymousClass75(Class<?> cls, AbstractC0224Wl wl, Object obj, Object obj2, boolean z) {
        super(cls, wl.hashCode(), obj, obj2, z);
        this._elementType = wl;
    }

    @Override // X.AbstractC0224Wl
    public final AbstractC0224Wl A03() {
        return this._elementType;
    }
}
