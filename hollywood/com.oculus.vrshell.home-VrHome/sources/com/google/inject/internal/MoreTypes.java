package com.google.inject.internal;

import com.adobe.xmp.XMPConst;
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

    public static Type canonicalize(Type type) {
        if (type instanceof Class) {
            Class<?> c = (Class) type;
            return c.isArray() ? new GenericArrayTypeImpl(canonicalize(c.getComponentType())) : c;
        } else if (type instanceof CompositeType) {
            return type;
        } else {
            if (type instanceof ParameterizedType) {
                ParameterizedType p = (ParameterizedType) type;
                return new ParameterizedTypeImpl(p.getOwnerType(), p.getRawType(), p.getActualTypeArguments());
            } else if (type instanceof GenericArrayType) {
                return new GenericArrayTypeImpl(((GenericArrayType) type).getGenericComponentType());
            } else {
                if (!(type instanceof WildcardType)) {
                    return type;
                }
                WildcardType w = (WildcardType) type;
                return new WildcardTypeImpl(w.getUpperBounds(), w.getLowerBounds());
            }
        }
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

    public static boolean equals(Type a, Type b) {
        boolean z = true;
        if (a == b) {
            return true;
        }
        if (a instanceof Class) {
            return a.equals(b);
        }
        if (a instanceof ParameterizedType) {
            if (!(b instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType pa = (ParameterizedType) a;
            ParameterizedType pb = (ParameterizedType) b;
            if (!Objects.equal(pa.getOwnerType(), pb.getOwnerType()) || !pa.getRawType().equals(pb.getRawType()) || !Arrays.equals(pa.getActualTypeArguments(), pb.getActualTypeArguments())) {
                z = false;
            }
            return z;
        } else if (a instanceof GenericArrayType) {
            if (b instanceof GenericArrayType) {
                return equals(((GenericArrayType) a).getGenericComponentType(), ((GenericArrayType) b).getGenericComponentType());
            }
            return false;
        } else if (a instanceof WildcardType) {
            if (!(b instanceof WildcardType)) {
                return false;
            }
            WildcardType wa = (WildcardType) a;
            WildcardType wb = (WildcardType) b;
            if (!Arrays.equals(wa.getUpperBounds(), wb.getUpperBounds()) || !Arrays.equals(wa.getLowerBounds(), wb.getLowerBounds())) {
                z = false;
            }
            return z;
        } else if (!(a instanceof TypeVariable) || !(b instanceof TypeVariable)) {
            return false;
        } else {
            TypeVariable<?> va = (TypeVariable) a;
            TypeVariable<?> vb = (TypeVariable) b;
            if (va.getGenericDeclaration() != vb.getGenericDeclaration() || !va.getName().equals(vb.getName())) {
                z = false;
            }
            return z;
        }
    }

    /* access modifiers changed from: private */
    public static int hashCodeOrZero(@Nullable Object o) {
        if (o != null) {
            return o.hashCode();
        }
        return 0;
    }

    public static String typeToString(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    public static Type getGenericSupertype(Type type, Class<?> rawType, Class<?> toResolve) {
        if (toResolve == rawType) {
            return type;
        }
        if (toResolve.isInterface()) {
            Class[] interfaces = rawType.getInterfaces();
            int length = interfaces.length;
            for (int i = 0; i < length; i++) {
                if (interfaces[i] == toResolve) {
                    return rawType.getGenericInterfaces()[i];
                }
                if (toResolve.isAssignableFrom(interfaces[i])) {
                    return getGenericSupertype(rawType.getGenericInterfaces()[i], interfaces[i], toResolve);
                }
            }
        }
        if (!rawType.isInterface()) {
            while (rawType != Object.class) {
                Class<?> rawSupertype = rawType.getSuperclass();
                if (rawSupertype == toResolve) {
                    return rawType.getGenericSuperclass();
                }
                if (toResolve.isAssignableFrom(rawSupertype)) {
                    return getGenericSupertype(rawType.getGenericSuperclass(), rawSupertype, toResolve);
                }
                rawType = rawSupertype;
            }
        }
        return toResolve;
    }

    public static Type resolveTypeVariable(Type type, Class<?> rawType, TypeVariable unknown) {
        Class<?> declaredByRaw = declaringClassOf(unknown);
        if (declaredByRaw == null) {
            return unknown;
        }
        Type declaredBy = getGenericSupertype(type, rawType, declaredByRaw);
        if (!(declaredBy instanceof ParameterizedType)) {
            return unknown;
        }
        return ((ParameterizedType) declaredBy).getActualTypeArguments()[indexOf(declaredByRaw.getTypeParameters(), unknown)];
    }

    private static int indexOf(Object[] array, Object toFind) {
        for (int i = 0; i < array.length; i++) {
            if (toFind.equals(array[i])) {
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

    public static class ParameterizedTypeImpl implements CompositeType, Serializable, ParameterizedType {
        private final Type ownerType;
        private final Type rawType;
        private final Type[] typeArguments;

        public ParameterizedTypeImpl(@Nullable Type ownerType2, Type rawType2, Type... typeArguments2) {
            boolean z = false;
            if (rawType2 instanceof Class) {
                Class rawTypeAsClass = (Class) rawType2;
                Preconditions.checkArgument(ownerType2 != null || rawTypeAsClass.getEnclosingClass() == null, "No owner type for enclosed %s", rawType2);
                Preconditions.checkArgument((ownerType2 == null || rawTypeAsClass.getEnclosingClass() != null) ? true : z, "Owner type for unenclosed %s", rawType2);
            }
            this.ownerType = ownerType2 == null ? null : MoreTypes.canonicalize(ownerType2);
            this.rawType = MoreTypes.canonicalize(rawType2);
            this.typeArguments = (Type[]) typeArguments2.clone();
            for (int t = 0; t < this.typeArguments.length; t++) {
                Preconditions.checkNotNull(this.typeArguments[t], "type parameter");
                MoreTypes.checkNotPrimitive(this.typeArguments[t], "type parameters");
                this.typeArguments[t] = MoreTypes.canonicalize(this.typeArguments[t]);
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
            if (!((this.ownerType == null || MoreTypes.isFullySpecified(this.ownerType)) && MoreTypes.isFullySpecified(this.rawType))) {
                return false;
            }
            for (Type type : this.typeArguments) {
                if (!MoreTypes.isFullySpecified(type)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object other) {
            return (other instanceof ParameterizedType) && MoreTypes.equals(this, (ParameterizedType) other);
        }

        public int hashCode() {
            return (Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode()) ^ MoreTypes.hashCodeOrZero(this.ownerType);
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder((this.typeArguments.length + 1) * 30);
            stringBuilder.append(MoreTypes.typeToString(this.rawType));
            if (this.typeArguments.length == 0) {
                return stringBuilder.toString();
            }
            stringBuilder.append("<").append(MoreTypes.typeToString(this.typeArguments[0]));
            for (int i = 1; i < this.typeArguments.length; i++) {
                stringBuilder.append(", ").append(MoreTypes.typeToString(this.typeArguments[i]));
            }
            return stringBuilder.append(">").toString();
        }
    }

    public static class GenericArrayTypeImpl implements CompositeType, Serializable, GenericArrayType {
        private final Type componentType;

        public GenericArrayTypeImpl(Type componentType2) {
            this.componentType = MoreTypes.canonicalize(componentType2);
        }

        public Type getGenericComponentType() {
            return this.componentType;
        }

        @Override // com.google.inject.internal.MoreTypes.CompositeType
        public boolean isFullySpecified() {
            return MoreTypes.isFullySpecified(this.componentType);
        }

        public boolean equals(Object o) {
            return (o instanceof GenericArrayType) && MoreTypes.equals(this, (GenericArrayType) o);
        }

        public int hashCode() {
            return this.componentType.hashCode();
        }

        public String toString() {
            return MoreTypes.typeToString(this.componentType) + XMPConst.ARRAY_ITEM_NAME;
        }
    }

    public static class WildcardTypeImpl implements CompositeType, Serializable, WildcardType {
        private final Type lowerBound;
        private final Type upperBound;

        public WildcardTypeImpl(Type[] upperBounds, Type[] lowerBounds) {
            boolean z;
            boolean z2 = true;
            Preconditions.checkArgument(lowerBounds.length <= 1, "Must have at most one lower bound.");
            if (upperBounds.length == 1) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "Must have exactly one upper bound.");
            if (lowerBounds.length == 1) {
                Preconditions.checkNotNull(lowerBounds[0], "lowerBound");
                MoreTypes.checkNotPrimitive(lowerBounds[0], "wildcard bounds");
                Preconditions.checkArgument(upperBounds[0] != Object.class ? false : z2, "bounded both ways");
                this.lowerBound = MoreTypes.canonicalize(lowerBounds[0]);
                this.upperBound = Object.class;
                return;
            }
            Preconditions.checkNotNull(upperBounds[0], "upperBound");
            MoreTypes.checkNotPrimitive(upperBounds[0], "wildcard bounds");
            this.lowerBound = null;
            this.upperBound = MoreTypes.canonicalize(upperBounds[0]);
        }

        public Type[] getUpperBounds() {
            return new Type[]{this.upperBound};
        }

        public Type[] getLowerBounds() {
            if (this.lowerBound == null) {
                return MoreTypes.EMPTY_TYPE_ARRAY;
            }
            return new Type[]{this.lowerBound};
        }

        @Override // com.google.inject.internal.MoreTypes.CompositeType
        public boolean isFullySpecified() {
            return MoreTypes.isFullySpecified(this.upperBound) && (this.lowerBound == null || MoreTypes.isFullySpecified(this.lowerBound));
        }

        public boolean equals(Object other) {
            return (other instanceof WildcardType) && MoreTypes.equals(this, (WildcardType) other);
        }

        public int hashCode() {
            return (this.lowerBound != null ? this.lowerBound.hashCode() + 31 : 1) ^ (this.upperBound.hashCode() + 31);
        }

        public String toString() {
            if (this.lowerBound != null) {
                return "? super " + MoreTypes.typeToString(this.lowerBound);
            }
            if (this.upperBound == Object.class) {
                return "?";
            }
            return "? extends " + MoreTypes.typeToString(this.upperBound);
        }
    }

    /* access modifiers changed from: private */
    public static void checkNotPrimitive(Type type, String use) {
        Preconditions.checkArgument(!(type instanceof Class) || !((Class) type).isPrimitive(), "Primitive types are not allowed in %s: %s", use, type);
    }
}
