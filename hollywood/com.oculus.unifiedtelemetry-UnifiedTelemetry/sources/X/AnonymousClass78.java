package X;

import java.lang.reflect.Array;

/* renamed from: X.78  reason: invalid class name */
public final class AnonymousClass78 extends Bt {
    public static final long serialVersionUID = 9040058063449087477L;
    public final AbstractC0224Wl _componentType;
    public final Object _emptyArray;

    public static AnonymousClass78 A00(AbstractC0224Wl wl) {
        return new AnonymousClass78(wl, Array.newInstance(wl._class, 0), null, null, false);
    }

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
    public final boolean A0E() {
        return false;
    }

    @Override // X.AbstractC0224Wl
    public final boolean A0F() {
        return true;
    }

    @Override // X.AbstractC0224Wl
    public final boolean A0H() {
        return true;
    }

    @Override // X.AbstractC0224Wl
    public final boolean A0J() {
        return true;
    }

    @Override // X.AbstractC0224Wl
    public final AbstractC0224Wl A05(int i) {
        if (i == 0) {
            return this._componentType;
        }
        return null;
    }

    @Override // X.AbstractC0224Wl
    public final AbstractC0224Wl A08(Class<?> cls) {
        AbstractC0224Wl wl = this._componentType;
        if (cls == wl._class) {
            return this;
        }
        return A00(wl.A06(cls));
    }

    @Override // X.AbstractC0224Wl
    public final AbstractC0224Wl A09(Object obj) {
        AbstractC0224Wl wl = this._componentType;
        if (obj == wl._typeHandler) {
            return this;
        }
        return new AnonymousClass78(wl.A0B(obj), this._emptyArray, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC0224Wl
    public final AbstractC0224Wl A0A(Object obj) {
        AbstractC0224Wl wl = this._componentType;
        if (obj == wl._valueHandler) {
            return this;
        }
        return new AnonymousClass78(wl.A0C(obj), this._emptyArray, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AbstractC0224Wl
    public final AbstractC0224Wl A0B(Object obj) {
        if (obj == this._typeHandler) {
            return this;
        }
        return new AnonymousClass78(this._componentType, this._emptyArray, this._valueHandler, obj, this._asStatic);
    }

    @Override // X.AbstractC0224Wl
    public final AbstractC0224Wl A0C(Object obj) {
        if (obj == this._valueHandler) {
            return this;
        }
        return new AnonymousClass78(this._componentType, this._emptyArray, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.Bt
    public final String A0K() {
        return this._class.getName();
    }

    @Override // X.AbstractC0224Wl
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return this._componentType.equals(((AnonymousClass78) obj)._componentType);
    }

    @Override // X.AbstractC0224Wl
    public final String toString() {
        StringBuilder sb = new StringBuilder("[array type, component type: ");
        sb.append(this._componentType);
        sb.append("]");
        return sb.toString();
    }

    public AnonymousClass78(AbstractC0224Wl wl, Object obj, Object obj2, Object obj3, boolean z) {
        super(obj.getClass(), wl.hashCode(), obj2, obj3, z);
        this._componentType = wl;
        this._emptyArray = obj;
    }

    @Override // X.AbstractC0224Wl
    public final AbstractC0224Wl A07(Class<?> cls) {
        if (cls.isArray()) {
            return A00(NT.A02.A09(cls.getComponentType(), null));
        }
        throw new IllegalArgumentException(AnonymousClass06.A06("Incompatible narrowing operation: trying to narrow ", toString(), " to class ", cls.getName()));
    }

    @Override // X.AbstractC0224Wl
    public final AbstractC0224Wl A03() {
        return this._componentType;
    }
}
