package X;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

/* renamed from: X.Wl  reason: case insensitive filesystem */
public abstract class AbstractC0224Wl extends AbstractC0442k5 implements Serializable, Type {
    public static final long serialVersionUID = 6774285981275451126L;
    public final boolean _asStatic;
    public final Class<?> _class;
    public final int _hashCode;
    public final Object _typeHandler;
    public final Object _valueHandler;

    public int A02() {
        return 0;
    }

    public AbstractC0224Wl A03() {
        return null;
    }

    public AbstractC0224Wl A04() {
        return null;
    }

    public AbstractC0224Wl A05(int i) {
        return null;
    }

    public abstract AbstractC0224Wl A07(Class<?> cls);

    public abstract AbstractC0224Wl A08(Class<?> cls);

    public abstract AbstractC0224Wl A09(Object obj);

    public abstract AbstractC0224Wl A0A(Object obj);

    public abstract AbstractC0224Wl A0B(Object obj);

    public abstract AbstractC0224Wl A0C(Object obj);

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

    public final AbstractC0224Wl A06(Class<?> cls) {
        Class<?> cls2 = this._class;
        if (cls == cls2) {
            return this;
        }
        if (cls2.isAssignableFrom(cls)) {
            AbstractC0224Wl A07 = A07(cls);
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
        throw new IllegalArgumentException(AnonymousClass06.A06("Class ", cls.getName(), " is not assignable to ", this._class.getName()));
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

    public AbstractC0224Wl(Class<?> cls, int i, Object obj, Object obj2, boolean z) {
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
