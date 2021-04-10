package X;

/* renamed from: X.071  reason: invalid class name */
public final class AnonymousClass071 extends AnonymousClass0GF {
    public static final long serialVersionUID = -800374828948534376L;
    public final String[] _typeNames;
    public final AbstractC04000gb[] _typeParameters;

    public static AnonymousClass071 A00(Class<?> cls) {
        return new AnonymousClass071(cls, null, null, null, null, false);
    }

    @Override // X.AbstractC04000gb
    public final boolean A0J() {
        return false;
    }

    @Override // X.AbstractC04000gb
    public final boolean equals(Object obj) {
        int length;
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            AnonymousClass071 r9 = (AnonymousClass071) obj;
            if (r9._class == this._class) {
                AbstractC04000gb[] r5 = this._typeParameters;
                AbstractC04000gb[] r4 = r9._typeParameters;
                if (r5 == null) {
                    return r4 == null || r4.length == 0;
                }
                if (r4 != null && (length = r5.length) == r4.length) {
                    for (int i = 0; i < length; i++) {
                        if (r5[i].equals(r4[i])) {
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override // X.AbstractC04000gb
    public final int A02() {
        AbstractC04000gb[] r0 = this._typeParameters;
        if (r0 == null) {
            return 0;
        }
        return r0.length;
    }

    @Override // X.AbstractC04000gb
    public final AbstractC04000gb A05(int i) {
        AbstractC04000gb[] r1;
        if (i < 0 || (r1 = this._typeParameters) == null || i >= r1.length) {
            return null;
        }
        return r1[i];
    }

    @Override // X.AbstractC04000gb
    public final AbstractC04000gb A07(Class<?> cls) {
        return new AnonymousClass071(cls, this._typeNames, this._typeParameters, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC04000gb
    public final AbstractC04000gb A08(Class<?> cls) {
        throw new IllegalArgumentException("Internal error: SimpleType.narrowContentsBy() should never be called");
    }

    @Override // X.AbstractC04000gb
    public final AbstractC04000gb A09(Object obj) {
        throw new IllegalArgumentException("Simple types have no content types; can not call withContenTypeHandler()");
    }

    @Override // X.AbstractC04000gb
    public final AbstractC04000gb A0A(Object obj) {
        throw new IllegalArgumentException("Simple types have no content types; can not call withContenValueHandler()");
    }

    @Override // X.AbstractC04000gb
    public final AbstractC04000gb A0B(Object obj) {
        return new AnonymousClass071(this._class, this._typeNames, this._typeParameters, this._valueHandler, obj, this._asStatic);
    }

    @Override // X.AbstractC04000gb
    public final AbstractC04000gb A0C(Object obj) {
        if (obj == this._valueHandler) {
            return this;
        }
        return new AnonymousClass071(this._class, this._typeNames, this._typeParameters, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC04000gb
    public final String A0D(int i) {
        String[] strArr;
        if (i < 0 || (strArr = this._typeNames) == null || i >= strArr.length) {
            return null;
        }
        return strArr[i];
    }

    @Override // X.AnonymousClass0GF
    public final String A0K() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        AbstractC04000gb[] r5 = this._typeParameters;
        if (r5 != null && (r4 = r5.length) > 0) {
            sb.append('<');
            boolean z = true;
            for (AbstractC04000gb r1 : r5) {
                if (z) {
                    z = false;
                } else {
                    sb.append(',');
                }
                sb.append(r1.A01());
            }
            sb.append('>');
        }
        return sb.toString();
    }

    @Override // X.AbstractC04000gb
    public final String toString() {
        StringBuilder sb = new StringBuilder(40);
        sb.append("[simple type, class ");
        sb.append(A0K());
        sb.append(']');
        return sb.toString();
    }

    public AnonymousClass071(Class<?> cls) {
        this(cls, null, null, null, null, false);
    }

    public AnonymousClass071(Class<?> cls, String[] strArr, AbstractC04000gb[] r10, Object obj, Object obj2, boolean z) {
        super(cls, 0, obj, obj2, z);
        if (strArr == null || strArr.length == 0) {
            this._typeNames = null;
            this._typeParameters = null;
            return;
        }
        this._typeNames = strArr;
        this._typeParameters = r10;
    }
}
