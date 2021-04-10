package X;

import java.lang.reflect.Array;

/* renamed from: X.0CA  reason: invalid class name */
public final class AnonymousClass0CA extends AbstractC00960Oa {
    public static final long serialVersionUID = 9040058063449087477L;
    public final AbstractC02190iF _componentType;
    public final Object _emptyArray;

    public static AnonymousClass0CA A00(AbstractC02190iF r5) {
        return new AnonymousClass0CA(r5, Array.newInstance(r5._class, 0), null, null, false);
    }

    @Override // X.AbstractC02190iF
    public final int A03() {
        return 1;
    }

    @Override // X.AbstractC02190iF
    public final String A0G(int i) {
        if (i == 0) {
            return "E";
        }
        return null;
    }

    @Override // X.AbstractC02190iF
    public final boolean A0I() {
        return false;
    }

    @Override // X.AbstractC02190iF
    public final boolean A0J() {
        return true;
    }

    @Override // X.AbstractC02190iF
    public final boolean A0L() {
        return true;
    }

    @Override // X.AbstractC02190iF
    public final boolean A0N() {
        return true;
    }

    @Override // X.AbstractC02190iF
    public final AbstractC02190iF A06(int i) {
        if (i == 0) {
            return this._componentType;
        }
        return null;
    }

    @Override // X.AbstractC02190iF
    public final AbstractC02190iF A0A(Class<?> cls) {
        AbstractC02190iF r1 = this._componentType;
        if (cls == r1._class) {
            return this;
        }
        return A00(r1.A07(cls));
    }

    @Override // X.AbstractC02190iF
    public final AbstractC02190iF A0B(Class<?> cls) {
        AbstractC02190iF r1 = this._componentType;
        if (cls == r1._class) {
            return this;
        }
        return A00(r1.A08(cls));
    }

    @Override // X.AbstractC02190iF
    public final AbstractC02190iF A0C(Object obj) {
        AbstractC02190iF r1 = this._componentType;
        if (obj == r1._typeHandler) {
            return this;
        }
        return new AnonymousClass0CA(r1.A0E(obj), this._emptyArray, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC02190iF
    public final AbstractC02190iF A0D(Object obj) {
        AbstractC02190iF r1 = this._componentType;
        if (obj == r1._valueHandler) {
            return this;
        }
        return new AnonymousClass0CA(r1.A0F(obj), this._emptyArray, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC02190iF
    public final AbstractC02190iF A0E(Object obj) {
        if (obj == this._typeHandler) {
            return this;
        }
        return new AnonymousClass0CA(this._componentType, this._emptyArray, this._valueHandler, obj, this._asStatic);
    }

    @Override // X.AbstractC02190iF
    public final AbstractC02190iF A0F(Object obj) {
        if (obj == this._valueHandler) {
            return this;
        }
        return new AnonymousClass0CA(this._componentType, this._emptyArray, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC02190iF
    public final boolean A0H() {
        return this._componentType.A0H();
    }

    @Override // X.AbstractC00960Oa
    public final String A0O() {
        return this._class.getName();
    }

    @Override // X.AbstractC02190iF
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return this._componentType.equals(((AnonymousClass0CA) obj)._componentType);
    }

    @Override // X.AbstractC02190iF
    public final String toString() {
        StringBuilder sb = new StringBuilder("[array type, component type: ");
        sb.append(this._componentType);
        sb.append("]");
        return sb.toString();
    }

    public AnonymousClass0CA(AbstractC02190iF r7, Object obj, Object obj2, Object obj3, boolean z) {
        super(obj.getClass(), r7.hashCode(), obj2, obj3, z);
        this._componentType = r7;
        this._emptyArray = obj;
    }

    @Override // X.AbstractC02190iF
    public final AbstractC02190iF A04() {
        return this._componentType;
    }

    @Override // X.AbstractC02190iF
    public final AbstractC02190iF A09(Class<?> cls) {
        if (cls.isArray()) {
            return A00(AnonymousClass0r9.A02.A09(cls.getComponentType(), null));
        }
        throw new IllegalArgumentException(AnonymousClass006.A0B("Incompatible narrowing operation: trying to narrow ", toString(), " to class ", cls.getName()));
    }
}
