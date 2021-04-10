package libcore.reflect;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Objects;

public final class GenericArrayTypeImpl implements GenericArrayType {
    private final Type componentType;

    public GenericArrayTypeImpl(Type type) {
        this.componentType = type;
    }

    @Override // java.lang.reflect.GenericArrayType
    public Type getGenericComponentType() {
        try {
            return ((ParameterizedTypeImpl) this.componentType).getResolvedType();
        } catch (ClassCastException unused) {
            return this.componentType;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GenericArrayType)) {
            return false;
        }
        return Objects.equals(getGenericComponentType(), ((GenericArrayType) obj).getGenericComponentType());
    }

    public int hashCode() {
        return Objects.hashCode(getGenericComponentType());
    }

    public String toString() {
        return this.componentType.toString() + "[]";
    }
}
