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
        if (type2 == null) {
            throw new NullPointerException("type is null");
        }
        this.type = MoreTypes.canonicalize(type2);
        this.rawType = (Class<? super T>) MoreTypes.getRawType(this.type);
        this.hashCode = this.type.hashCode();
        checkPrimitiveType();
    }

    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (!(superclass instanceof Class)) {
            return MoreTypes.canonicalize(((ParameterizedType) superclass).getActualTypeArguments()[0]);
        }
        throw new RuntimeException("Missing type parameter.");
    }

    static TypeLiteral<?> fromSuperclassTypeParameter(Class<?> subclass) {
        return new TypeLiteral<>(getSuperclassTypeParameter(subclass));
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

    public final boolean equals(Object o) {
        return (o instanceof TypeLiteral) && MoreTypes.equals(this.type, ((TypeLiteral) o).type);
    }

    public final String toString() {
        return MoreTypes.typeToString(this.type);
    }

    public static TypeLiteral<?> get(Type type2) {
        return new TypeLiteral<>(type2);
    }

    public static <T> TypeLiteral<T> get(Class<T> type2) {
        return new TypeLiteral<>(type2);
    }

    private List<TypeLiteral<?>> resolveAll(Type[] types) {
        TypeLiteral<?>[] result = new TypeLiteral[types.length];
        for (int t = 0; t < types.length; t++) {
            result[t] = resolve(types[t]);
        }
        return ImmutableList.copyOf(result);
    }

    /* access modifiers changed from: package-private */
    public TypeLiteral<?> resolve(Type toResolve) {
        return get(resolveType(toResolve));
    }

    /* JADX INFO: Multiple debug info for r8v2 java.lang.reflect.WildcardType: [D('original' java.lang.reflect.WildcardType), D('original' java.lang.reflect.TypeVariable)] */
    /* JADX INFO: Multiple debug info for r8v3 java.lang.reflect.WildcardType: [D('original' java.lang.reflect.WildcardType), D('original' java.lang.reflect.TypeVariable)] */
    /* JADX INFO: Multiple debug info for r8v4 java.lang.reflect.WildcardType: [D('original' java.lang.reflect.WildcardType), D('original' java.lang.reflect.TypeVariable)] */
    /* JADX INFO: Multiple debug info for r8v6 java.lang.reflect.ParameterizedType: [D('original' java.lang.reflect.TypeVariable), D('original' java.lang.reflect.ParameterizedType)] */
    /* JADX INFO: Multiple debug info for r8v7 java.lang.reflect.ParameterizedType: [D('original' java.lang.reflect.ParameterizedType), D('original' java.lang.reflect.TypeVariable)] */
    /* JADX INFO: Multiple debug info for r8v9 java.lang.reflect.GenericArrayType: [D('original' java.lang.reflect.GenericArrayType), D('original' java.lang.reflect.TypeVariable)] */
    /* JADX INFO: Multiple debug info for r8v10 java.lang.reflect.GenericArrayType: [D('original' java.lang.reflect.GenericArrayType), D('original' java.lang.reflect.TypeVariable)] */
    /* access modifiers changed from: package-private */
    public Type resolveType(Type toResolve) {
        Type upperBound;
        while (toResolve instanceof TypeVariable) {
            TypeVariable original = (TypeVariable) toResolve;
            toResolve = MoreTypes.resolveTypeVariable(this.type, this.rawType, original);
            if (toResolve == original) {
                return toResolve;
            }
        }
        if (toResolve instanceof GenericArrayType) {
            GenericArrayType original2 = (GenericArrayType) toResolve;
            Type componentType = original2.getGenericComponentType();
            Type newComponentType = resolveType(componentType);
            if (componentType != newComponentType) {
                return Types.arrayOf(newComponentType);
            }
            return original2;
        } else if (toResolve instanceof ParameterizedType) {
            ParameterizedType original3 = (ParameterizedType) toResolve;
            Type ownerType = original3.getOwnerType();
            Type newOwnerType = resolveType(ownerType);
            boolean changed = newOwnerType != ownerType;
            Type[] args = original3.getActualTypeArguments();
            int length = args.length;
            for (int t = 0; t < length; t++) {
                Type resolvedTypeArgument = resolveType(args[t]);
                if (resolvedTypeArgument != args[t]) {
                    if (!changed) {
                        args = (Type[]) args.clone();
                        changed = true;
                    }
                    args[t] = resolvedTypeArgument;
                }
            }
            if (changed) {
                return Types.newParameterizedTypeWithOwner(newOwnerType, original3.getRawType(), args);
            }
            return original3;
        } else if (!(toResolve instanceof WildcardType)) {
            return toResolve;
        } else {
            WildcardType original4 = (WildcardType) toResolve;
            Type[] originalLowerBound = original4.getLowerBounds();
            Type[] originalUpperBound = original4.getUpperBounds();
            if (originalLowerBound.length == 1) {
                Type lowerBound = resolveType(originalLowerBound[0]);
                if (lowerBound != originalLowerBound[0]) {
                    return Types.supertypeOf(lowerBound);
                }
                return original4;
            } else if (originalUpperBound.length != 1 || (upperBound = resolveType(originalUpperBound[0])) == originalUpperBound[0]) {
                return original4;
            } else {
                return Types.subtypeOf(upperBound);
            }
        }
    }

    public TypeLiteral<?> getSupertype(Class<?> supertype) {
        Preconditions.checkArgument(supertype.isAssignableFrom(this.rawType), "%s is not a supertype of %s", supertype, this.type);
        return resolve(MoreTypes.getGenericSupertype(this.type, this.rawType, supertype));
    }

    public TypeLiteral<?> getFieldType(Field field) {
        Preconditions.checkArgument(field.getDeclaringClass().isAssignableFrom(this.rawType), "%s is not defined by a supertype of %s", field, this.type);
        return resolve(field.getGenericType());
    }

    public List<TypeLiteral<?>> getParameterTypes(Member methodOrConstructor) {
        Type[] genericParameterTypes;
        if (methodOrConstructor instanceof Method) {
            Method method = (Method) methodOrConstructor;
            Preconditions.checkArgument(method.getDeclaringClass().isAssignableFrom(this.rawType), "%s is not defined by a supertype of %s", method, this.type);
            genericParameterTypes = method.getGenericParameterTypes();
        } else if (methodOrConstructor instanceof Constructor) {
            Constructor<?> constructor = (Constructor) methodOrConstructor;
            Preconditions.checkArgument(constructor.getDeclaringClass().isAssignableFrom(this.rawType), "%s does not construct a supertype of %s", constructor, this.type);
            genericParameterTypes = constructor.getGenericParameterTypes();
        } else {
            throw new IllegalArgumentException("Not a method or a constructor: " + methodOrConstructor);
        }
        return resolveAll(genericParameterTypes);
    }

    public List<TypeLiteral<?>> getExceptionTypes(Member methodOrConstructor) {
        Type[] genericExceptionTypes;
        if (methodOrConstructor instanceof Method) {
            Method method = (Method) methodOrConstructor;
            Preconditions.checkArgument(method.getDeclaringClass().isAssignableFrom(this.rawType), "%s is not defined by a supertype of %s", method, this.type);
            genericExceptionTypes = method.getGenericExceptionTypes();
        } else if (methodOrConstructor instanceof Constructor) {
            Constructor<?> constructor = (Constructor) methodOrConstructor;
            Preconditions.checkArgument(constructor.getDeclaringClass().isAssignableFrom(this.rawType), "%s does not construct a supertype of %s", constructor, this.type);
            genericExceptionTypes = constructor.getGenericExceptionTypes();
        } else {
            throw new IllegalArgumentException("Not a method or a constructor: " + methodOrConstructor);
        }
        return resolveAll(genericExceptionTypes);
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
