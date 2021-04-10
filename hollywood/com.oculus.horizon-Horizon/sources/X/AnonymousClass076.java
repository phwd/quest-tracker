package X;

import java.lang.reflect.Array;

/* renamed from: X.076  reason: invalid class name */
public final class AnonymousClass076 extends AnonymousClass0GF {
    public static final long serialVersionUID = 9040058063449087477L;
    public final AbstractC04000gb _componentType;
    public final Object _emptyArray;

    public static AnonymousClass076 A00(AbstractC04000gb r5) {
        return new AnonymousClass076(r5, Array.newInstance(r5._class, 0), null, null, false);
    }

    @Override // X.AbstractC04000gb
    public final int A02() {
        return 1;
    }

    @Override // X.AbstractC04000gb
    public final String A0D(int i) {
        if (i == 0) {
            return "E";
        }
        return null;
    }

    @Override // X.AbstractC04000gb
    public final boolean A0E() {
        return false;
    }

    @Override // X.AbstractC04000gb
    public final boolean A0F() {
        return true;
    }

    @Override // X.AbstractC04000gb
    public final boolean A0H() {
        return true;
    }

    @Override // X.AbstractC04000gb
    public final boolean A0J() {
        return true;
    }

    @Override // X.AbstractC04000gb
    public final AbstractC04000gb A05(int i) {
        if (i == 0) {
            return this._componentType;
        }
        return null;
    }

    @Override // X.AbstractC04000gb
    public final AbstractC04000gb A08(Class<?> cls) {
        AbstractC04000gb r1 = this._componentType;
        if (cls == r1._class) {
            return this;
        }
        return A00(r1.A06(cls));
    }

    @Override // X.AbstractC04000gb
    public final AbstractC04000gb A09(Object obj) {
        AbstractC04000gb r1 = this._componentType;
        if (obj == r1._typeHandler) {
            return this;
        }
        return new AnonymousClass076(r1.A0B(obj), this._emptyArray, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC04000gb
    public final AbstractC04000gb A0A(Object obj) {
        AbstractC04000gb r1 = this._componentType;
        if (obj == r1._valueHandler) {
            return this;
        }
        return new AnonymousClass076(r1.A0C(obj), this._emptyArray, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC04000gb
    public final AbstractC04000gb A0B(Object obj) {
        if (obj == this._typeHandler) {
            return this;
        }
        return new AnonymousClass076(this._componentType, this._emptyArray, this._valueHandler, obj, this._asStatic);
    }

    @Override // X.AbstractC04000gb
    public final AbstractC04000gb A0C(Object obj) {
        if (obj == this._valueHandler) {
            return this;
        }
        return new AnonymousClass076(this._componentType, this._emptyArray, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0GF
    public final String A0K() {
        return this._class.getName();
    }

    @Override // X.AbstractC04000gb
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return this._componentType.equals(((AnonymousClass076) obj)._componentType);
    }

    @Override // X.AbstractC04000gb
    public final String toString() {
        StringBuilder sb = new StringBuilder("[array type, component type: ");
        sb.append(this._componentType);
        sb.append("]");
        return sb.toString();
    }

    public AnonymousClass076(AbstractC04000gb r7, Object obj, Object obj2, Object obj3, boolean z) {
        super(obj.getClass(), r7.hashCode(), obj2, obj3, z);
        this._componentType = r7;
        this._emptyArray = obj;
    }

    @Override // X.AbstractC04000gb
    public final AbstractC04000gb A03() {
        return this._componentType;
    }

    @Override // X.AbstractC04000gb
    public final AbstractC04000gb A07(Class<?> cls) {
        if (cls.isArray()) {
            return A00(C06240ml.A02.A09(cls.getComponentType(), null));
        }
        throw new IllegalArgumentException(AnonymousClass006.A08("Incompatible narrowing operation: trying to narrow ", toString(), " to class ", cls.getName()));
    }
}
