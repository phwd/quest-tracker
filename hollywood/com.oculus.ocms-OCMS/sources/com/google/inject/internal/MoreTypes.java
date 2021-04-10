package com.google.inject.internal;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.inject.TypeLiteral;
import com.google.inject.util.Types;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;
import javax.inject.Provider;

public class MoreTypes {
    public static final Type[] EMPTY_TYPE_ARRAY = new Type[0];

    /* access modifiers changed from: private */
    public interface CompositeType {
        boolean isFullySpecified();
    }

    private MoreTypes() {
    }

    public static <T> TypeLiteral<T> canonicalizeForKey(TypeLiteral<T> typeLiteral) {
        Type type = typeLiteral.getType();
        if (isFullySpecified(type)) {
            return typeLiteral.getRawType() == Provider.class ? (TypeLiteral<T>) TypeLiteral.get(Types.providerOf(((ParameterizedType) type).getActualTypeArguments()[0])) : typeLiteral;
        }
        throw new RuntimeException("Key not fully specified " + typeLiteral);
    }

    /* access modifiers changed from: private */
    public static boolean isFullySpecified(Type type) {
        if (type instanceof Class) {
            return true;
        }
        if (type instanceof CompositeType) {
            return ((CompositeType) type).isFullySpecified();
        }
        if (type instanceof TypeVariable) {
            return false;
        }
        return ((CompositeType) canonicalize(type)).isFullySpecified();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v9, types: [com.google.inject.internal.MoreTypes$GenericArrayTypeImpl] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.reflect.Type canonicalize(java.lang.reflect.Type r3) {
        /*
            boolean r0 = r3 instanceof java.lang.Class
            if (r0 == 0) goto L_0x001d
            java.lang.Class r3 = (java.lang.Class) r3
            boolean r0 = r3.isArray()
            if (r0 == 0) goto L_0x001a
            com.google.inject.internal.MoreTypes$GenericArrayTypeImpl r0 = new com.google.inject.internal.MoreTypes$GenericArrayTypeImpl
            java.lang.Class r3 = r3.getComponentType()
            java.lang.reflect.Type r3 = canonicalize(r3)
            r0.<init>(r3)
            r3 = r0
        L_0x001a:
            java.lang.reflect.Type r3 = (java.lang.reflect.Type) r3
            return r3
        L_0x001d:
            boolean r0 = r3 instanceof com.google.inject.internal.MoreTypes.CompositeType
            if (r0 == 0) goto L_0x0022
            return r3
        L_0x0022:
            boolean r0 = r3 instanceof java.lang.reflect.ParameterizedType
            if (r0 == 0) goto L_0x003a
            java.lang.reflect.ParameterizedType r3 = (java.lang.reflect.ParameterizedType) r3
            com.google.inject.internal.MoreTypes$ParameterizedTypeImpl r0 = new com.google.inject.internal.MoreTypes$ParameterizedTypeImpl
            java.lang.reflect.Type r1 = r3.getOwnerType()
            java.lang.reflect.Type r2 = r3.getRawType()
            java.lang.reflect.Type[] r3 = r3.getActualTypeArguments()
            r0.<init>(r1, r2, r3)
            return r0
        L_0x003a:
            boolean r0 = r3 instanceof java.lang.reflect.GenericArrayType
            if (r0 == 0) goto L_0x004a
            java.lang.reflect.GenericArrayType r3 = (java.lang.reflect.GenericArrayType) r3
            com.google.inject.internal.MoreTypes$GenericArrayTypeImpl r0 = new com.google.inject.internal.MoreTypes$GenericArrayTypeImpl
            java.lang.reflect.Type r3 = r3.getGenericComponentType()
            r0.<init>(r3)
            return r0
        L_0x004a:
            boolean r0 = r3 instanceof java.lang.reflect.WildcardType
            if (r0 == 0) goto L_0x005e
            java.lang.reflect.WildcardType r3 = (java.lang.reflect.WildcardType) r3
            com.google.inject.internal.MoreTypes$WildcardTypeImpl r0 = new com.google.inject.internal.MoreTypes$WildcardTypeImpl
            java.lang.reflect.Type[] r1 = r3.getUpperBounds()
            java.lang.reflect.Type[] r3 = r3.getLowerBounds()
            r0.<init>(r1, r3)
            return r0
        L_0x005e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.inject.internal.MoreTypes.canonicalize(java.lang.reflect.Type):java.lang.reflect.Type");
    }

    public static Class<?> getRawType(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            Preconditions.checkArgument(rawType instanceof Class, "Expected a Class, but <%s> is of type %s", type, type.getClass().getName());
            return (Class) rawType;
        } else if (type instanceof GenericArrayType) {
            return Array.newInstance(getRawType(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        } else {
            if (type instanceof TypeVariable) {
                return Object.class;
            }
            throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + type.getClass().getName());
        }
    }

    public static boolean equals(Type type, Type type2) {
        if (type == type2) {
            return true;
        }
        if (type instanceof Class) {
            return type.equals(type2);
        }
        if (type instanceof ParameterizedType) {
            if (!(type2 instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            ParameterizedType parameterizedType2 = (ParameterizedType) type2;
            if (!Objects.equal(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) || !parameterizedType.getRawType().equals(parameterizedType2.getRawType()) || !Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments())) {
                return false;
            }
            return true;
        } else if (type instanceof GenericArrayType) {
            if (!(type2 instanceof GenericArrayType)) {
                return false;
            }
            return equals(((GenericArrayType) type).getGenericComponentType(), ((GenericArrayType) type2).getGenericComponentType());
        } else if (type instanceof WildcardType) {
            if (!(type2 instanceof WildcardType)) {
                return false;
            }
            WildcardType wildcardType = (WildcardType) type;
            WildcardType wildcardType2 = (WildcardType) type2;
            if (!Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) || !Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds())) {
                return false;
            }
            return true;
        } else if (!(type instanceof TypeVariable) || !(type2 instanceof TypeVariable)) {
            return false;
        } else {
            TypeVariable typeVariable = (TypeVariable) type;
            TypeVariable typeVariable2 = (TypeVariable) type2;
            if (typeVariable.getGenericDeclaration() != typeVariable2.getGenericDeclaration() || !typeVariable.getName().equals(typeVariable2.getName())) {
                return false;
            }
            return true;
        }
    }

    /* access modifiers changed from: private */
    public static int hashCodeOrZero(@Nullable Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public static String typeToString(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    public static Type getGenericSupertype(Type type, Class<?> cls, Class<?> cls2) {
        if (cls2 == cls) {
            return type;
        }
        if (cls2.isInterface()) {
            Class<?>[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            for (int i = 0; i < length; i++) {
                if (interfaces[i] == cls2) {
                    return cls.getGenericInterfaces()[i];
                }
                if (cls2.isAssignableFrom(interfaces[i])) {
                    return getGenericSupertype(cls.getGenericInterfaces()[i], interfaces[i], cls2);
                }
            }
        }
        if (!cls.isInterface()) {
            while (cls != Object.class) {
                Class<? super Object> superclass = cls.getSuperclass();
                if (superclass == cls2) {
                    return cls.getGenericSuperclass();
                }
                if (cls2.isAssignableFrom(superclass)) {
                    return getGenericSupertype(cls.getGenericSuperclass(), superclass, cls2);
                }
                cls = superclass;
            }
        }
        return cls2;
    }

    public static Type resolveTypeVariable(Type type, Class<?> cls, TypeVariable typeVariable) {
        Class<?> declaringClassOf = declaringClassOf(typeVariable);
        if (declaringClassOf == null) {
            return typeVariable;
        }
        Type genericSupertype = getGenericSupertype(type, cls, declaringClassOf);
        if (!(genericSupertype instanceof ParameterizedType)) {
            return typeVariable;
        }
        return ((ParameterizedType) genericSupertype).getActualTypeArguments()[indexOf(declaringClassOf.getTypeParameters(), typeVariable)];
    }

    private static int indexOf(Object[] objArr, Object obj) {
        for (int i = 0; i < objArr.length; i++) {
            if (obj.equals(objArr[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    @Nullable
    private static Class<?> declaringClassOf(TypeVariable typeVariable) {
        GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
        if (genericDeclaration instanceof Class) {
            return (Class) genericDeclaration;
        }
        return null;
    }

    public static class ParameterizedTypeImpl implements ParameterizedType, Serializable, CompositeType {
        private static final long serialVersionUID = 0;
        private final Type ownerType;
        private final Type rawType;
        private final Type[] typeArguments;

        public ParameterizedTypeImpl(@Nullable Type type, Type type2, Type... typeArr) {
            Type type3;
            int i = 0;
            if (type2 instanceof Class) {
                Class cls = (Class) type2;
                boolean z = true;
                Preconditions.checkArgument(type != null || cls.getEnclosingClass() == null, "No owner type for enclosed %s", type2);
                if (type != null && cls.getEnclosingClass() == null) {
                    z = false;
                }
                Preconditions.checkArgument(z, "Owner type for unenclosed %s", type2);
            }
            if (type == null) {
                type3 = null;
            } else {
                type3 = MoreTypes.canonicalize(type);
            }
            this.ownerType = type3;
            this.rawType = MoreTypes.canonicalize(type2);
            this.typeArguments = (Type[]) typeArr.clone();
            while (true) {
                Type[] typeArr2 = this.typeArguments;
                if (i < typeArr2.length) {
                    Preconditions.checkNotNull(typeArr2[i], "type parameter");
                    MoreTypes.checkNotPrimitive(this.typeArguments[i], "type parameters");
                    Type[] typeArr3 = this.typeArguments;
                    typeArr3[i] = MoreTypes.canonicalize(typeArr3[i]);
                    i++;
                } else {
                    return;
                }
            }
        }

        public Type[] getActualTypeArguments() {
            return (Type[]) this.typeArguments.clone();
        }

        public Type getRawType() {
            return this.rawType;
        }

        public Type getOwnerType() {
            return this.ownerType;
        }

        @Override // com.google.inject.internal.MoreTypes.CompositeType
        public boolean isFullySpecified() {
            Type type = this.ownerType;
            if (!((type == null || MoreTypes.isFullySpecified(type)) && MoreTypes.isFullySpecified(this.rawType))) {
                return false;
            }
            for (Type type2 : this.typeArguments) {
                if (!MoreTypes.isFullySpecified(type2)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object obj) {
            return (obj instanceof ParameterizedType) && MoreTypes.equals(this, (ParameterizedType) obj);
        }

        public int hashCode() {
            return (Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode()) ^ MoreTypes.hashCodeOrZero(this.ownerType);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder((this.typeArguments.length + 1) * 30);
            sb.append(MoreTypes.typeToString(this.rawType));
            if (this.typeArguments.length == 0) {
                return sb.toString();
            }
            sb.append("<");
            sb.append(MoreTypes.typeToString(this.typeArguments[0]));
            for (int i = 1; i < this.typeArguments.length; i++) {
                sb.append(", ");
                sb.append(MoreTypes.typeToString(this.typeArguments[i]));
            }
            sb.append(">");
            return sb.toString();
        }
    }

    public static class GenericArrayTypeImpl implements GenericArrayType, Serializable, CompositeType {
        private static final long serialVersionUID = 0;
        private final Type componentType;

        public GenericArrayTypeImpl(Type type) {
            this.componentType = MoreTypes.canonicalize(type);
        }

        public Type getGenericComponentType() {
            return this.componentType;
        }

        @Override // com.google.inject.internal.MoreTypes.CompositeType
        public boolean isFullySpecified() {
            return MoreTypes.isFullySpecified(this.componentType);
        }

        public boolean equals(Object obj) {
            return (obj instanceof GenericArrayType) && MoreTypes.equals(this, (GenericArrayType) obj);
        }

        public int hashCode() {
            return this.componentType.hashCode();
        }

        public String toString() {
            return MoreTypes.typeToString(this.componentType) + "[]";
        }
    }

    public static class WildcardTypeImpl implements WildcardType, Serializable, CompositeType {
        private static final long serialVersionUID = 0;
        private final Type lowerBound;
        private final Type upperBound;

        public WildcardTypeImpl(Type[] typeArr, Type[] typeArr2) {
            boolean z = true;
            Preconditions.checkArgument(typeArr2.length <= 1, "Must have at most one lower bound.");
            Preconditions.checkArgument(typeArr.length == 1, "Must have exactly one upper bound.");
            if (typeArr2.length == 1) {
                Preconditions.checkNotNull(typeArr2[0], "lowerBound");
                MoreTypes.checkNotPrimitive(typeArr2[0], "wildcard bounds");
                Preconditions.checkArgument(typeArr[0] != Object.class ? false : z, "bounded both ways");
                this.lowerBound = MoreTypes.canonicalize(typeArr2[0]);
                this.upperBound = Object.class;
                return;
            }
            Preconditions.checkNotNull(typeArr[0], "upperBound");
            MoreTypes.checkNotPrimitive(typeArr[0], "wildcard bounds");
            this.lowerBound = null;
            this.upperBound = MoreTypes.canonicalize(typeArr[0]);
        }

        public Type[] getUpperBounds() {
            return new Type[]{this.upperBound};
        }

        public Type[] getLowerBounds() {
            Type type = this.lowerBound;
            if (type == null) {
                return MoreTypes.EMPTY_TYPE_ARRAY;
            }
            return new Type[]{type};
        }

        @Override // com.google.inject.internal.MoreTypes.CompositeType
        public boolean isFullySpecified() {
            Type type;
            return MoreTypes.isFullySpecified(this.upperBound) && ((type = this.lowerBound) == null || MoreTypes.isFullySpecified(type));
        }

        public boolean equals(Object obj) {
            return (obj instanceof WildcardType) && MoreTypes.equals(this, (WildcardType) obj);
        }

        public int hashCode() {
            Type type = this.lowerBound;
            return (type != null ? type.hashCode() + 31 : 1) ^ (this.upperBound.hashCode() + 31);
        }

        public String toString() {
            if (this.lowerBound != null) {
                return "? super " + MoreTypes.typeToString(this.lowerBound);
            } else if (this.upperBound == Object.class) {
                return "?";
            } else {
                return "? extends " + MoreTypes.typeToString(this.upperBound);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void checkNotPrimitive(Type type, String str) {
        Preconditions.checkArgument(!(type instanceof Class) || !((Class) type).isPrimitive(), "Primitive types are not allowed in %s: %s", str, type);
    }
}
