package X;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

/* renamed from: X.0gb  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC04000gb extends AbstractC05100kH implements Serializable, Type {
    public static final long serialVersionUID = 6774285981275451126L;
    public final boolean _asStatic;
    public final Class<?> _class;
    public final int _hashCode;
    public final Object _typeHandler;
    public final Object _valueHandler;

    public int A02() {
        return 0;
    }

    public AbstractC04000gb A03() {
        return null;
    }

    public AbstractC04000gb A04() {
        return null;
    }

    public AbstractC04000gb A05(int i) {
        return null;
    }

    public abstract AbstractC04000gb A07(Class<?> cls);

    public abstract AbstractC04000gb A08(Class<?> cls);

    public abstract AbstractC04000gb A09(Object obj);

    public abstract AbstractC04000gb A0A(Object obj);

    public abstract AbstractC04000gb A0B(Object obj);

    public abstract AbstractC04000gb A0C(Object obj);

    public String A0D(int i) {
        return null;
    }

    public boolean A0F() {
        return false;
    }

    public boolean A0G() {
        return false;
    }

    public boolean A0I() {
        return false;
    }

    public abstract boolean A0J();

    public abstract boolean equals(Object obj);

    public abstract String toString();

    public final AbstractC04000gb A06(Class<?> cls) {
        Class<?> cls2 = this._class;
        if (cls == cls2) {
            return this;
        }
        if (cls2.isAssignableFrom(cls)) {
            AbstractC04000gb A07 = A07(cls);
            Object obj = this._valueHandler;
            if (obj != A07._valueHandler) {
                A07 = A07.A0C(obj);
            }
            Object obj2 = this._typeHandler;
            if (obj2 != A07._typeHandler) {
                return A07.A0B(obj2);
            }
            return A07;
        }
        throw new IllegalArgumentException(AnonymousClass006.A08("Class ", cls.getName(), " is not assignable to ", this._class.getName()));
    }

    public boolean A0E() {
        return Modifier.isAbstract(this._class.getModifiers());
    }

    public boolean A0H() {
        if ((this._class.getModifiers() & 1536) == 0 || this._class.isPrimitive()) {
            return true;
        }
        return false;
    }

    public AbstractC04000gb(Class<?> cls, int i, Object obj, Object obj2, boolean z) {
        this._class = cls;
        this._hashCode = cls.getName().hashCode() + i;
        this._valueHandler = obj;
        this._typeHandler = obj2;
        this._asStatic = z;
    }

    public final int hashCode() {
        return this._hashCode;
    }
}
