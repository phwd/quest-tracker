package X;

/* renamed from: X.73  reason: invalid class name */
public final class AnonymousClass73 extends Bt {
    public static final long serialVersionUID = -800374828948534376L;
    public final String[] _typeNames;
    public final AbstractC0224Wl[] _typeParameters;

    public static AnonymousClass73 A00(Class<?> cls) {
        return new AnonymousClass73(cls, null, null, null, null, false);
    }

    @Override // X.AbstractC0224Wl
    public final boolean A0J() {
        return false;
    }

    @Override // X.AbstractC0224Wl
    public final boolean equals(Object obj) {
        int length;
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            AnonymousClass73 r9 = (AnonymousClass73) obj;
            if (r9._class == this._class) {
                AbstractC0224Wl[] wlArr = this._typeParameters;
                AbstractC0224Wl[] wlArr2 = r9._typeParameters;
                if (wlArr == null) {
                    return wlArr2 == null || wlArr2.length == 0;
                }
                if (wlArr2 != null && (length = wlArr.length) == wlArr2.length) {
                    for (int i = 0; i < length; i++) {
                        if (wlArr[i].equals(wlArr2[i])) {
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override // X.AbstractC0224Wl
    public final int A02() {
        AbstractC0224Wl[] wlArr = this._typeParameters;
        if (wlArr == null) {
            return 0;
        }
        return wlArr.length;
    }

    @Override // X.AbstractC0224Wl
    public final AbstractC0224Wl A05(int i) {
        AbstractC0224Wl[] wlArr;
        if (i < 0 || (wlArr = this._typeParameters) == null || i >= wlArr.length) {
            return null;
        }
        return wlArr[i];
    }

    @Override // X.AbstractC0224Wl
    public final AbstractC0224Wl A07(Class<?> cls) {
        return new AnonymousClass73(cls, this._typeNames, this._typeParameters, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC0224Wl
    public final AbstractC0224Wl A08(Class<?> cls) {
        throw new IllegalArgumentException("Internal error: SimpleType.narrowContentsBy() should never be called");
    }

    @Override // X.AbstractC0224Wl
    public final AbstractC0224Wl A09(Object obj) {
        throw new IllegalArgumentException("Simple types have no content types; can not call withContenTypeHandler()");
    }

    @Override // X.AbstractC0224Wl
    public final AbstractC0224Wl A0A(Object obj) {
        throw new IllegalArgumentException("Simple types have no content types; can not call withContenValueHandler()");
    }

    @Override // X.AbstractC0224Wl
    public final AbstractC0224Wl A0B(Object obj) {
        return new AnonymousClass73(this._class, this._typeNames, this._typeParameters, this._valueHandler, obj, this._asStatic);
    }

    @Override // X.AbstractC0224Wl
    public final AbstractC0224Wl A0C(Object obj) {
        if (obj == this._valueHandler) {
            return this;
        }
        return new AnonymousClass73(this._class, this._typeNames, this._typeParameters, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC0224Wl
    public final String A0D(int i) {
        String[] strArr;
        if (i < 0 || (strArr = this._typeNames) == null || i >= strArr.length) {
            return null;
        }
        return strArr[i];
    }

    @Override // X.Bt
    public final String A0K() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        AbstractC0224Wl[] wlArr = this._typeParameters;
        if (wlArr != null && (r4 = wlArr.length) > 0) {
            sb.append('<');
            boolean z = true;
            for (AbstractC0224Wl wl : wlArr) {
                if (z) {
                    z = false;
                } else {
                    sb.append(',');
                }
                sb.append(wl.A01());
            }
            sb.append('>');
        }
        return sb.toString();
    }

    @Override // X.AbstractC0224Wl
    public final String toString() {
        StringBuilder sb = new StringBuilder(40);
        sb.append("[simple type, class ");
        sb.append(A0K());
        sb.append(']');
        return sb.toString();
    }

    public AnonymousClass73(Class<?> cls) {
        this(cls, null, null, null, null, false);
    }

    public AnonymousClass73(Class<?> cls, String[] strArr, AbstractC0224Wl[] wlArr, Object obj, Object obj2, boolean z) {
        super(cls, 0, obj, obj2, z);
        if (strArr == null || strArr.length == 0) {
            this._typeNames = null;
            this._typeParameters = null;
            return;
        }
        this._typeNames = strArr;
        this._typeParameters = wlArr;
    }
}
