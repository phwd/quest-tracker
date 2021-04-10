package X;

import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/* renamed from: X.13g  reason: invalid class name */
public final class AnonymousClass13g implements GenericArrayType, Serializable {
    public static final long serialVersionUID = 0;
    public final Type componentType;

    public final boolean equals(Object obj) {
        if (!(obj instanceof GenericArrayType) || !AnonymousClass13j.A07(this, (Type) obj)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.componentType.hashCode();
    }

    public final String toString() {
        return AnonymousClass006.A07(AnonymousClass13j.A01(this.componentType), "[]");
    }

    public AnonymousClass13g(Type type) {
        this.componentType = AnonymousClass13j.A02(type);
    }

    public final Type getGenericComponentType() {
        return this.componentType;
    }
}
