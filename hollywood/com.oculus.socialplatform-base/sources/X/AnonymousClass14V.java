package X;

import com.google.common.base.Preconditions;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import javax.inject.Provider;

/* renamed from: X.14V  reason: invalid class name */
public final class AnonymousClass14V {
    public static final Type[] A00 = new Type[0];

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0043 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean A07(java.lang.reflect.Type r3, java.lang.reflect.Type r4) {
        /*
        // Method dump skipped, instructions count: 167
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass14V.A07(java.lang.reflect.Type, java.lang.reflect.Type):boolean");
    }

    public static <T> AnonymousClass14R<T> A00(AnonymousClass14R<T> r4) {
        Type type = r4.type;
        if (!A06(type)) {
            StringBuilder sb = new StringBuilder("Key not fully specified ");
            sb.append(r4);
            throw new RuntimeException(sb.toString());
        } else if (r4.rawType == Provider.class) {
            return new AnonymousClass14R<>(new AnonymousClass0cv(null, Provider.class, ((ParameterizedType) type).getActualTypeArguments()[0]));
        } else {
            return r4;
        }
    }

    public static Class<?> A01(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            Preconditions.checkArgument(rawType instanceof Class, "Expected a Class, but <%s> is of type %s", type, type.getClass().getName());
            return (Class) rawType;
        } else if (type instanceof GenericArrayType) {
            return Array.newInstance(A01(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        } else {
            if (type instanceof TypeVariable) {
                return Object.class;
            }
            StringBuilder sb = new StringBuilder("Expected a Class, ParameterizedType, or GenericArrayType, but <");
            sb.append(type);
            sb.append("> is of type ");
            sb.append(type.getClass().getName());
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public static String A02(Type type) {
        if (type instanceof Class) {
            return ((Class) type).getName();
        }
        return type.toString();
    }

    public static Type A03(Type type) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            boolean isArray = cls.isArray();
            Type type2 = cls;
            if (isArray) {
                type2 = new C01280cw(A03(cls.getComponentType()));
            }
            return type2;
        }
        if (!(type instanceof AnonymousClass14U)) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                return new AnonymousClass0cv(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
            } else if (type instanceof GenericArrayType) {
                return new C01280cw(((GenericArrayType) type).getGenericComponentType());
            } else {
                if (type instanceof WildcardType) {
                    WildcardType wildcardType = (WildcardType) type;
                    return new AnonymousClass0cu(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
                }
            }
        }
        return type;
    }

    public static Type A04(Type type, Class<?> cls, Class<?> cls2) {
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
                    return A04(type2, cls3, cls2);
                }
                cls = cls3;
            }
        }
        return cls2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000c, code lost:
        if (((java.lang.Class) r2).isPrimitive() == false) goto L_0x000e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A05(java.lang.reflect.Type r2, java.lang.String r3) {
        /*
            boolean r0 = r2 instanceof java.lang.Class
            if (r0 == 0) goto L_0x000e
            r0 = r2
            java.lang.Class r0 = (java.lang.Class) r0
            boolean r0 = r0.isPrimitive()
            r1 = 0
            if (r0 != 0) goto L_0x000f
        L_0x000e:
            r1 = 1
        L_0x000f:
            java.lang.String r0 = "Primitive types are not allowed in %s: %s"
            com.google.common.base.Preconditions.checkArgument(r1, r0, r3, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass14V.A05(java.lang.reflect.Type, java.lang.String):void");
    }

    public static boolean A06(Type type) {
        if (type instanceof Class) {
            return true;
        }
        if (!(type instanceof AnonymousClass14U)) {
            if (type instanceof TypeVariable) {
                return false;
            }
            type = A03(type);
        }
        return ((AnonymousClass14U) type).A5z();
    }
}
