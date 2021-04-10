package X;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public abstract class RZ extends AnonymousClass7A implements Serializable, Type {
    public static final long serialVersionUID = 6774285981275451126L;
    public final boolean _asStatic;
    public final Class<?> _class;
    public final int _hashCode;
    public final Object _typeHandler;
    public final Object _valueHandler;

    public abstract boolean equals(Object obj);

    public abstract String toString();

    public final boolean A00() {
        if (!(this instanceof AnonymousClass77)) {
            return Modifier.isAbstract(this._class.getModifiers());
        }
        return false;
    }

    public final int hashCode() {
        return this._hashCode;
    }
}
