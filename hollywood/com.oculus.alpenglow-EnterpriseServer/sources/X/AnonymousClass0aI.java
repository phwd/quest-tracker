package X;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

/* renamed from: X.0aI  reason: invalid class name */
public abstract class AnonymousClass0aI extends AnonymousClass0mE implements Serializable, Type {
    public static final long serialVersionUID = 6774285981275451126L;
    public final boolean _asStatic;
    public final Class<?> _class;
    public final int _hashCode;
    public final Object _typeHandler;
    public final Object _valueHandler;

    public int A03() {
        return 0;
    }

    public AnonymousClass0aI A04() {
        return null;
    }

    public AnonymousClass0aI A05() {
        return null;
    }

    public AnonymousClass0aI A06(int i) {
        return null;
    }

    public abstract AnonymousClass0aI A09(Class<?> cls);

    public abstract AnonymousClass0aI A0A(Class<?> cls);

    public abstract AnonymousClass0aI A0B(Class<?> cls);

    public abstract AnonymousClass0aI A0C(Object obj);

    public abstract AnonymousClass0aI A0D(Object obj);

    public abstract AnonymousClass0aI A0E(Object obj);

    public abstract AnonymousClass0aI A0F(Object obj);

    public String A0G(int i) {
        return null;
    }

    public boolean A0J() {
        return false;
    }

    public boolean A0K() {
        return false;
    }

    public boolean A0M() {
        return false;
    }

    public abstract boolean A0N();

    public abstract boolean equals(Object obj);

    public abstract String toString();

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;Ljava/lang/Class<*>;)V */
    private final void A01(Class cls) {
        if (!this._class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException(AnonymousClass006.A08("Class ", cls.getName(), " is not assignable to ", this._class.getName()));
        }
    }

    public final AnonymousClass0aI A07(Class<?> cls) {
        if (cls == this._class) {
            return this;
        }
        A01(cls);
        AnonymousClass0aI A09 = A09(cls);
        Object obj = this._valueHandler;
        if (obj != A09._valueHandler) {
            A09 = A09.A0F(obj);
        }
        Object obj2 = this._typeHandler;
        if (obj2 != A09._typeHandler) {
            return A09.A0E(obj2);
        }
        return A09;
    }

    public final AnonymousClass0aI A08(Class<?> cls) {
        Class<?> cls2 = this._class;
        if (cls == cls2) {
            return this;
        }
        A01(cls2);
        return A09(cls);
    }

    public boolean A0I() {
        return Modifier.isAbstract(this._class.getModifiers());
    }

    public boolean A0L() {
        if ((this._class.getModifiers() & 1536) == 0 || this._class.isPrimitive()) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this._hashCode;
    }

    public AnonymousClass0aI(Class<?> cls, int i, Object obj, Object obj2, boolean z) {
        this._class = cls;
        this._hashCode = cls.getName().hashCode() + i;
        this._valueHandler = obj;
        this._typeHandler = obj2;
        this._asStatic = z;
    }

    public boolean A0H() {
        if (A03() > 0) {
            return true;
        }
        return false;
    }
}
