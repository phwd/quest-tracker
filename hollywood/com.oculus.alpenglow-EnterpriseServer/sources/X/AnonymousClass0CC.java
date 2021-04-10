package X;

import java.lang.reflect.Array;

/* renamed from: X.0CC  reason: invalid class name */
public final class AnonymousClass0CC extends AbstractC01610Jq {
    public static final long serialVersionUID = 9040058063449087477L;
    public final AnonymousClass0aI _componentType;
    public final Object _emptyArray;

    public static AnonymousClass0CC A00(AnonymousClass0aI r5) {
        return new AnonymousClass0CC(r5, Array.newInstance(r5._class, 0), null, null, false);
    }

    @Override // X.AnonymousClass0aI
    public final int A03() {
        return 1;
    }

    @Override // X.AnonymousClass0aI
    public final String A0G(int i) {
        if (i == 0) {
            return "E";
        }
        return null;
    }

    @Override // X.AnonymousClass0aI
    public final boolean A0I() {
        return false;
    }

    @Override // X.AnonymousClass0aI
    public final boolean A0J() {
        return true;
    }

    @Override // X.AnonymousClass0aI
    public final boolean A0L() {
        return true;
    }

    @Override // X.AnonymousClass0aI
    public final boolean A0N() {
        return true;
    }

    @Override // X.AnonymousClass0aI
    public final AnonymousClass0aI A04() {
        return this._componentType;
    }

    @Override // X.AnonymousClass0aI
    public final AnonymousClass0aI A06(int i) {
        if (i == 0) {
            return this._componentType;
        }
        return null;
    }

    @Override // X.AnonymousClass0aI
    public final AnonymousClass0aI A0A(Class<?> cls) {
        AnonymousClass0aI r1 = this._componentType;
        if (cls == r1._class) {
            return this;
        }
        return A00(r1.A07(cls));
    }

    @Override // X.AnonymousClass0aI
    public final AnonymousClass0aI A0B(Class<?> cls) {
        AnonymousClass0aI r1 = this._componentType;
        if (cls == r1._class) {
            return this;
        }
        return A00(r1.A08(cls));
    }

    @Override // X.AnonymousClass0aI
    public final AnonymousClass0aI A0C(Object obj) {
        AnonymousClass0aI r1 = this._componentType;
        if (obj == r1._typeHandler) {
            return this;
        }
        return new AnonymousClass0CC(r1.A0E(obj), this._emptyArray, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0aI
    public final AnonymousClass0aI A0D(Object obj) {
        AnonymousClass0aI r1 = this._componentType;
        if (obj == r1._valueHandler) {
            return this;
        }
        return new AnonymousClass0CC(r1.A0F(obj), this._emptyArray, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0aI
    public final AnonymousClass0aI A0E(Object obj) {
        if (obj == this._typeHandler) {
            return this;
        }
        return new AnonymousClass0CC(this._componentType, this._emptyArray, this._valueHandler, obj, this._asStatic);
    }

    @Override // X.AnonymousClass0aI
    public final AnonymousClass0aI A0F(Object obj) {
        if (obj == this._valueHandler) {
            return this;
        }
        return new AnonymousClass0CC(this._componentType, this._emptyArray, obj, this._typeHandler, this._asStatic);
    }

    @Override // X.AnonymousClass0aI
    public final boolean A0H() {
        return this._componentType.A0H();
    }

    @Override // X.AbstractC01610Jq
    public final String A0O() {
        return this._class.getName();
    }

    @Override // X.AnonymousClass0aI
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return this._componentType.equals(((AnonymousClass0CC) obj)._componentType);
    }

    @Override // X.AnonymousClass0aI
    public final String toString() {
        return "[array type, component type: " + this._componentType + "]";
    }

    public AnonymousClass0CC(AnonymousClass0aI r7, Object obj, Object obj2, Object obj3, boolean z) {
        super(obj.getClass(), r7.hashCode(), obj2, obj3, z);
        this._componentType = r7;
        this._emptyArray = obj;
    }

    @Override // X.AnonymousClass0aI
    public final AnonymousClass0aI A09(Class<?> cls) {
        if (cls.isArray()) {
            return A00(C07040od.A02.A09(cls.getComponentType(), null));
        }
        throw new IllegalArgumentException(AnonymousClass006.A08("Incompatible narrowing operation: trying to narrow ", toString(), " to class ", cls.getName()));
    }
}
