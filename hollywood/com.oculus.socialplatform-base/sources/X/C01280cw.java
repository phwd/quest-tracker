package X;

import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/* renamed from: X.0cw  reason: invalid class name and case insensitive filesystem */
public class C01280cw implements GenericArrayType, AnonymousClass14U, Serializable {
    public static final long serialVersionUID = 0;
    public final Type componentType;

    @Override // X.AnonymousClass14U
    public final boolean A5z() {
        return AnonymousClass14V.A06(this.componentType);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof GenericArrayType) || !AnonymousClass14V.A07(this, (Type) obj)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.componentType.hashCode();
    }

    public final String toString() {
        return AnonymousClass006.A07(AnonymousClass14V.A02(this.componentType), "[]");
    }

    public C01280cw(Type type) {
        this.componentType = AnonymousClass14V.A03(type);
    }

    public final Type getGenericComponentType() {
        return this.componentType;
    }
}
