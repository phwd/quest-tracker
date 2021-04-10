package X;

/* renamed from: X.0C7  reason: invalid class name */
public final class AnonymousClass0C7 extends AbstractC00960Oa {
    public static final long serialVersionUID = -800374828948534376L;
    public final String[] _typeNames;
    public final AbstractC02190iF[] _typeParameters;

    public static AnonymousClass0C7 A00(Class<?> cls) {
        return new AnonymousClass0C7(cls, null, null, null, null, false);
    }

    @Override // X.AbstractC02190iF
    public final boolean A0N() {
        return false;
    }

    @Override // X.AbstractC02190iF
    public final boolean equals(Object obj) {
        int length;
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            AnonymousClass0C7 r9 = (AnonymousClass0C7) obj;
            if (r9._class == this._class) {
                AbstractC02190iF[] r5 = this._typeParameters;
                AbstractC02190iF[] r4 = r9._typeParameters;
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

    @Override // X.AbstractC02190iF
    public final int A03() {
        AbstractC02190iF[] r0 = this._typeParameters;
        if (r0 == null) {
            return 0;
        }
        return r0.length;
    }

    @Override // X.AbstractC02190iF
    public final AbstractC02190iF A06(int i) {
        AbstractC02190iF[] r1;
        if (i < 0 || (r1 = this._typeParameters) == null || i >= r1.length) {
            return null;
        }
        return r1[i];
    }

    @Override // X.AbstractC02190iF
    public final AbstractC02190iF A09(Class<?> cls) {
        return new AnonymousClass0C7(cls, this._typeNames, this._typeParameters, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC02190iF
    public final AbstractC02190iF A0A(Class<?> cls) {
        throw new IllegalArgumentException("Internal error: SimpleType.narrowContentsBy() should never be called");
    }

    @Override // X.AbstractC02190iF
    public final AbstractC02190iF A0B(Class<?> cls) {
        throw new IllegalArgumentException("Internal error: SimpleType.widenContentsBy() should never be called");
    }

    @Override // X.AbstractC02190iF
    public final AbstractC02190iF A0C(Object obj) {
        throw new IllegalArgumentException("Simple types have no content types; can not call withContenTypeHandler()");
    }

    @Override // X.AbstractC02190iF
    public final AbstractC02190iF A0D(Object obj) {
        throw new IllegalArgumentException("Simple types have no content types; can not call withContenValueHandler()");
    }

    @Override // X.AbstractC02190iF
    public final AbstractC02190iF A0E(Object obj) {
        return new AnonymousClass0C7(this._class, this._typeNames, this._typeParameters, this._valueHandler, obj, this._asStatic);
    }

    @Override // X.AbstractC02190iF
    public final AbstractC02190iF A0F(Object obj) {
        if (obj == this._valueHandler) {
            return this;
        }
        return new AnonymousClass0C7(this._class, this._typeNames, this._typeParameters, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC02190iF
    public final String A0G(int i) {
        String[] strArr;
        if (i < 0 || (strArr = this._typeNames) == null || i >= strArr.length) {
            return null;
        }
        return strArr[i];
    }

    @Override // X.AbstractC00960Oa
    public final String A0O() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        AbstractC02190iF[] r5 = this._typeParameters;
        if (r5 != null && (r4 = r5.length) > 0) {
            sb.append('<');
            boolean z = true;
            for (AbstractC02190iF r1 : r5) {
                if (z) {
                    z = false;
                } else {
                    sb.append(',');
                }
                sb.append(r1.A02());
            }
            sb.append('>');
        }
        return sb.toString();
    }

    @Override // X.AbstractC02190iF
    public final String toString() {
        StringBuilder sb = new StringBuilder(40);
        sb.append("[simple type, class ");
        sb.append(A0O());
        sb.append(']');
        return sb.toString();
    }

    public AnonymousClass0C7(Class<?> cls) {
        this(cls, null, null, null, null, false);
    }

    public AnonymousClass0C7(Class<?> cls, String[] strArr, AbstractC02190iF[] r10, Object obj, Object obj2, boolean z) {
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
