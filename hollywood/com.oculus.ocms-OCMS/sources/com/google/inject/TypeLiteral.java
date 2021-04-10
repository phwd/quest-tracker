package com.google.inject;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.inject.internal.MoreTypes;
import com.google.inject.util.Types;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.List;
import javax.inject.Provider;

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

    /* access modifiers changed from: package-private */
    public final TypeLiteral<Provider<T>> providerType() {
        return (TypeLiteral<Provider<T>>) get(Types.providerOf(getType()));
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

    public static <T> TypeLiteral<T> get(Class<T> cls) {
        return new TypeLiteral<>(cls);
    }

    private List<TypeLiteral<?>> resolveAll(Type[] typeArr) {
        TypeLiteral[] typeLiteralArr = new TypeLiteral[typeArr.length];
        for (int i = 0; i < typeArr.length; i++) {
            typeLiteralArr[i] = resolve(typeArr[i]);
        }
        return ImmutableList.copyOf(typeLiteralArr);
    }

    /* access modifiers changed from: package-private */
    public TypeLiteral<?> resolve(Type type2) {
        return get(resolveType(type2));
    }

    /* access modifiers changed from: package-private */
    public Type resolveType(Type type2) {
        while (type2 instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) type2;
            Type resolveTypeVariable = MoreTypes.resolveTypeVariable(this.type, this.rawType, typeVariable);
            if (resolveTypeVariable == typeVariable) {
                return resolveTypeVariable;
            }
            type2 = resolveTypeVariable;
        }
        if (type2 instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) type2;
            Type genericComponentType = genericArrayType.getGenericComponentType();
            Type resolveType = resolveType(genericComponentType);
            return genericComponentType == resolveType ? genericArrayType : Types.arrayOf(resolveType);
        }
        if (type2 instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type2;
            Type ownerType = parameterizedType.getOwnerType();
            Type resolveType2 = resolveType(ownerType);
            boolean z = resolveType2 != ownerType;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            int length = actualTypeArguments.length;
            for (int i = 0; i < length; i++) {
                Type resolveType3 = resolveType(actualTypeArguments[i]);
                if (resolveType3 != actualTypeArguments[i]) {
                    if (!z) {
                        actualTypeArguments = (Type[]) actualTypeArguments.clone();
                        z = true;
                    }
                    actualTypeArguments[i] = resolveType3;
                }
            }
            return z ? Types.newParameterizedTypeWithOwner(resolveType2, parameterizedType.getRawType(), actualTypeArguments) : parameterizedType;
        } else if (!(type2 instanceof WildcardType)) {
            return type2;
        } else {
            WildcardType wildcardType = (WildcardType) type2;
            Type[] lowerBounds = wildcardType.getLowerBounds();
            Type[] upperBounds = wildcardType.getUpperBounds();
            if (lowerBounds.length == 1) {
                Type resolveType4 = resolveType(lowerBounds[0]);
                if (resolveType4 != lowerBounds[0]) {
                    return Types.supertypeOf(resolveType4);
                }
                return wildcardType;
            } else if (upperBounds.length != 1) {
                return wildcardType;
            } else {
                Type resolveType5 = resolveType(upperBounds[0]);
                return resolveType5 != upperBounds[0] ? Types.subtypeOf(resolveType5) : wildcardType;
            }
        }
    }

    public TypeLiteral<?> getSupertype(Class<?> cls) {
        Preconditions.checkArgument(cls.isAssignableFrom(this.rawType), "%s is not a supertype of %s", cls, this.type);
        return resolve(MoreTypes.getGenericSupertype(this.type, this.rawType, cls));
    }

    public TypeLiteral<?> getFieldType(Field field) {
        Preconditions.checkArgument(field.getDeclaringClass().isAssignableFrom(this.rawType), "%s is not defined by a supertype of %s", field, this.type);
        return resolve(field.getGenericType());
    }

    public List<TypeLiteral<?>> getParameterTypes(Member member) {
        Type[] typeArr;
        if (member instanceof Method) {
            Method method = (Method) member;
            Preconditions.checkArgument(method.getDeclaringClass().isAssignableFrom(this.rawType), "%s is not defined by a supertype of %s", method, this.type);
            typeArr = method.getGenericParameterTypes();
        } else if (member instanceof Constructor) {
            Constructor constructor = (Constructor) member;
            Preconditions.checkArgument(constructor.getDeclaringClass().isAssignableFrom(this.rawType), "%s does not construct a supertype of %s", constructor, this.type);
            typeArr = constructor.getGenericParameterTypes();
        } else {
            throw new IllegalArgumentException("Not a method or a constructor: " + member);
        }
        return resolveAll(typeArr);
    }

    public List<TypeLiteral<?>> getExceptionTypes(Member member) {
        Type[] typeArr;
        if (member instanceof Method) {
            Method method = (Method) member;
            Preconditions.checkArgument(method.getDeclaringClass().isAssignableFrom(this.rawType), "%s is not defined by a supertype of %s", method, this.type);
            typeArr = method.getGenericExceptionTypes();
        } else if (member instanceof Constructor) {
            Constructor constructor = (Constructor) member;
            Preconditions.checkArgument(constructor.getDeclaringClass().isAssignableFrom(this.rawType), "%s does not construct a supertype of %s", constructor, this.type);
            typeArr = constructor.getGenericExceptionTypes();
        } else {
            throw new IllegalArgumentException("Not a method or a constructor: " + member);
        }
        return resolveAll(typeArr);
    }

    public TypeLiteral<?> getReturnType(Method method) {
        Preconditions.checkArgument(method.getDeclaringClass().isAssignableFrom(this.rawType), "%s is not defined by a supertype of %s", method, this.type);
        return resolve(method.getGenericReturnType());
    }

    private void checkPrimitiveType() {
        if (this.rawType.isPrimitive()) {
            throw new IllegalArgumentException("Primitive types are not allowed: " + this.rawType.getName());
        }
    }
}
