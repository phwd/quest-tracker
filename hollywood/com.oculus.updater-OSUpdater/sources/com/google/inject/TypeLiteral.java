package com.google.inject;

import com.google.inject.internal.MoreTypes;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TypeLiteral<T> {
    final int hashCode;
    final Class<? super T> rawType;
    final Type type;

    protected TypeLiteral() {
        this.type = getSuperclassTypeParameter(getClass());
        this.rawType = (Class<? super T>) MoreTypes.getRawType(this.type);
        this.hashCode = this.type.hashCode();
        checkPrimitiveType();
    }

    TypeLiteral(Type type2) {
        if (type2 != null) {
            this.type = MoreTypes.canonicalize(type2);
            this.rawType = (Class<? super T>) MoreTypes.getRawType(this.type);
            this.hashCode = this.type.hashCode();
            checkPrimitiveType();
            return;
        }
        throw new NullPointerException("type is null");
    }

    static Type getSuperclassTypeParameter(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (!(genericSuperclass instanceof Class)) {
            return MoreTypes.canonicalize(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
        }
        throw new RuntimeException("Missing type parameter.");
    }

    static TypeLiteral<?> fromSuperclassTypeParameter(Class<?> cls) {
        return new TypeLiteral<>(getSuperclassTypeParameter(cls));
    }

    public final Class<? super T> getRawType() {
        return this.rawType;
    }

    public final Type getType() {
        return this.type;
    }

    public final int hashCode() {
        return this.hashCode;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof TypeLiteral) && MoreTypes.equals(this.type, ((TypeLiteral) obj).type);
    }

    public final String toString() {
        return MoreTypes.typeToString(this.type);
    }

    public static TypeLiteral<?> get(Type type2) {
        return new TypeLiteral<>(type2);
    }

    private void checkPrimitiveType() {
        if (this.rawType.isPrimitive()) {
            throw new IllegalArgumentException("Primitive types are not allowed: " + this.rawType.getName());
        }
    }
}
