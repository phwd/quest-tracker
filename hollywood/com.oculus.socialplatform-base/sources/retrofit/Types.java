package retrofit;

import X.AnonymousClass006;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.NoSuchElementException;

public final class Types {
    public static final Type[] EMPTY_TYPE_ARRAY = new Type[0];

    public static final class GenericArrayTypeImpl implements GenericArrayType {
        public final Type componentType;

        public boolean equals(Object obj) {
            if (!(obj instanceof GenericArrayType) || !Types.equals(this, (Type) obj)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.componentType.hashCode();
        }

        public String toString() {
            return AnonymousClass006.A07(Types.typeToString(this.componentType), "[]");
        }

        public GenericArrayTypeImpl(Type type) {
            this.componentType = type;
        }

        public Type getGenericComponentType() {
            return this.componentType;
        }
    }

    public static final class ParameterizedTypeImpl implements ParameterizedType {
        public final Type ownerType;
        public final Type rawType;
        public final Type[] typeArguments;

        public boolean equals(Object obj) {
            if (!(obj instanceof ParameterizedType) || !Types.equals(this, (Type) obj)) {
                return false;
            }
            return true;
        }

        public Type[] getActualTypeArguments() {
            return (Type[]) this.typeArguments.clone();
        }

        public int hashCode() {
            return (Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode()) ^ Types.hashCodeOrZero(this.ownerType);
        }

        public String toString() {
            int i = 1;
            StringBuilder sb = new StringBuilder((this.typeArguments.length + 1) * 30);
            sb.append(Types.typeToString(this.rawType));
            Type[] typeArr = this.typeArguments;
            if (typeArr.length != 0) {
                sb.append("<");
                sb.append(Types.typeToString(typeArr[0]));
                while (true) {
                    Type[] typeArr2 = this.typeArguments;
                    if (i >= typeArr2.length) {
                        break;
                    }
                    sb.append(", ");
                    sb.append(Types.typeToString(typeArr2[i]));
                    i++;
                }
                sb.append(">");
            }
            return sb.toString();
        }

        public ParameterizedTypeImpl(Type type, Type type2, Type... typeArr) {
            if (type2 instanceof Class) {
                if ((type == null) != (((Class) type2).getEnclosingClass() != null ? false : true)) {
                    throw new IllegalArgumentException();
                }
            }
            this.ownerType = type;
            this.rawType = type2;
            Type[] typeArr2 = (Type[]) typeArr.clone();
            this.typeArguments = typeArr2;
            for (Type type3 : typeArr2) {
                if (type3 != null) {
                    Types.checkNotPrimitive(type3);
                } else {
                    throw null;
                }
            }
        }

        public Type getOwnerType() {
            return this.ownerType;
        }

        public Type getRawType() {
            return this.rawType;
        }
    }

    public static final class WildcardTypeImpl implements WildcardType {
        public final Type lowerBound;
        public final Type upperBound;

        public boolean equals(Object obj) {
            if (!(obj instanceof WildcardType) || !Types.equals(this, (Type) obj)) {
                return false;
            }
            return true;
        }

        public Type[] getLowerBounds() {
            Type type = this.lowerBound;
            if (type != null) {
                return new Type[]{type};
            }
            return Types.EMPTY_TYPE_ARRAY;
        }

        public Type[] getUpperBounds() {
            return new Type[]{this.upperBound};
        }

        public int hashCode() {
            int i;
            Type type = this.lowerBound;
            if (type != null) {
                i = type.hashCode() + 31;
            } else {
                i = 1;
            }
            return i ^ (this.upperBound.hashCode() + 31);
        }

        public String toString() {
            String str;
            Type type = this.lowerBound;
            if (type != null) {
                str = "? super ";
            } else {
                type = this.upperBound;
                if (type == Object.class) {
                    return "?";
                }
                str = "? extends ";
            }
            StringBuilder sb = new StringBuilder(str);
            sb.append(Types.typeToString(type));
            return sb.toString();
        }

        public WildcardTypeImpl(Type[] typeArr, Type[] typeArr2) {
            int length = typeArr2.length;
            if (length > 1) {
                throw new IllegalArgumentException();
            } else if (typeArr.length != 1) {
                throw new IllegalArgumentException();
            } else if (length == 1) {
                Type type = typeArr2[0];
                if (type != null) {
                    Types.checkNotPrimitive(type);
                    if (typeArr[0] == Object.class) {
                        this.lowerBound = typeArr2[0];
                        this.upperBound = Object.class;
                        return;
                    }
                    throw new IllegalArgumentException();
                }
                throw null;
            } else {
                Type type2 = typeArr[0];
                if (type2 != null) {
                    Types.checkNotPrimitive(type2);
                    this.lowerBound = null;
                    this.upperBound = typeArr[0];
                    return;
                }
                throw null;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0043 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean equals(java.lang.reflect.Type r3, java.lang.reflect.Type r4) {
        /*
        // Method dump skipped, instructions count: 167
        */
        throw new UnsupportedOperationException("Method not decompiled: retrofit.Types.equals(java.lang.reflect.Type, java.lang.reflect.Type):boolean");
    }

    public static int indexOf(Object[] objArr, Object obj) {
        for (int i = 0; i < objArr.length; i++) {
            if (obj.equals(objArr[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    public static void checkNotPrimitive(Type type) {
        if ((type instanceof Class) && ((Class) type).isPrimitive()) {
            throw new IllegalArgumentException();
        }
    }

    public static boolean equal(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || !obj.equals(obj2)) {
            return false;
        }
        return true;
    }

    public static Type getGenericSupertype(Type type, Class<?> cls, Class<?> cls2) {
        Class<?> cls3;
        Type type2;
        if (cls2 == cls) {
            return type;
        }
        if (cls2.isInterface()) {
            Class<?>[] interfaces = cls.getInterfaces();
            int i = 0;
            int length = interfaces.length;
            while (true) {
                if (i >= length) {
                    break;
                } else if (interfaces[i] == cls2) {
                    return cls.getGenericInterfaces()[i];
                } else {
                    if (cls2.isAssignableFrom(interfaces[i])) {
                        type2 = cls.getGenericInterfaces()[i];
                        cls3 = interfaces[i];
                        break;
                    }
                    i++;
                }
            }
        }
        if (!cls.isInterface()) {
            while (cls != Object.class) {
                cls3 = cls.getSuperclass();
                if (cls3 == cls2) {
                    return cls.getGenericSuperclass();
                }
                if (cls2.isAssignableFrom(cls3)) {
                    type2 = cls.getGenericSuperclass();
                    return getGenericSupertype(type2, cls3, cls2);
                }
                cls = cls3;
            }
        }
        return cls2;
    }

    public static Class<?> getRawType(Type type) {
        String name;
        if (!(type instanceof Class)) {
            if (type instanceof ParameterizedType) {
                type = ((ParameterizedType) type).getRawType();
                if (!(type instanceof Class)) {
                    throw new IllegalArgumentException();
                }
            } else if (type instanceof GenericArrayType) {
                return Array.newInstance(getRawType(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
            } else {
                if (type instanceof TypeVariable) {
                    return Object.class;
                }
                if (type instanceof WildcardType) {
                    return getRawType(((WildcardType) type).getUpperBounds()[0]);
                }
                if (type == null) {
                    name = "null";
                } else {
                    name = type.getClass().getName();
                }
                StringBuilder sb = new StringBuilder("Expected a Class, ParameterizedType, or GenericArrayType, but <");
                sb.append(type);
                sb.append("> is of type ");
                sb.append(name);
                throw new IllegalArgumentException(sb.toString());
            }
        }
        return (Class) type;
    }

    public static int hashCodeOrZero(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:55:0x002b */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.lang.Class] */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.lang.reflect.Type] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.reflect.Type resolve(java.lang.reflect.Type r7, java.lang.Class<?> r8, java.lang.reflect.Type r9) {
        /*
        // Method dump skipped, instructions count: 194
        */
        throw new UnsupportedOperationException("Method not decompiled: retrofit.Types.resolve(java.lang.reflect.Type, java.lang.Class, java.lang.reflect.Type):java.lang.reflect.Type");
    }

    public static String typeToString(Type type) {
        if (type instanceof Class) {
            return ((Class) type).getName();
        }
        return type.toString();
    }

    public static Class<?> declaringClassOf(TypeVariable<?> typeVariable) {
        GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
        if (genericDeclaration instanceof Class) {
            return (Class) genericDeclaration;
        }
        return null;
    }

    public static Type getSupertype(Type type, Class<?> cls, Class<?> cls2) {
        if (cls2.isAssignableFrom(cls)) {
            return resolve(type, cls, getGenericSupertype(type, cls, cls2));
        }
        throw new IllegalArgumentException();
    }

    public static Type resolveTypeVariable(Type type, Class<?> cls, TypeVariable<?> typeVariable) {
        Class<?> declaringClassOf = declaringClassOf(typeVariable);
        if (declaringClassOf != null) {
            Type genericSupertype = getGenericSupertype(type, cls, declaringClassOf);
            if (genericSupertype instanceof ParameterizedType) {
                return ((ParameterizedType) genericSupertype).getActualTypeArguments()[indexOf(declaringClassOf.getTypeParameters(), typeVariable)];
            }
        }
        return typeVariable;
    }
}
