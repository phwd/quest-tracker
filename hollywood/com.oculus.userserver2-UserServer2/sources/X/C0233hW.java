package X;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.HashSet;

/* renamed from: X.hW  reason: case insensitive filesystem */
public final class C0233hW {
    public static final Type[] A00 = new Type[0];

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0047 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean A07(java.lang.reflect.Type r3, java.lang.reflect.Type r4) {
        /*
        // Method dump skipped, instructions count: 171
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0233hW.A07(java.lang.reflect.Type, java.lang.reflect.Type):boolean");
    }

    public static Class<?> A00(Type type) {
        String name;
        if (!(type instanceof Class)) {
            if (type instanceof ParameterizedType) {
                type = ((ParameterizedType) type).getRawType();
                C0236ha.A00(type instanceof Class);
            } else if (type instanceof GenericArrayType) {
                return Array.newInstance(A00(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
            } else {
                if (type instanceof TypeVariable) {
                    return Object.class;
                }
                if (type instanceof WildcardType) {
                    return A00(((WildcardType) type).getUpperBounds()[0]);
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

    public static String A01(Type type) {
        if (type instanceof Class) {
            return ((Class) type).getName();
        }
        return type.toString();
    }

    public static Type A02(Type type) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            boolean isArray = cls.isArray();
            Type type2 = cls;
            if (isArray) {
                type2 = new hZ(A02(cls.getComponentType()));
            }
            return type2;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return new C0235hY(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        } else if (type instanceof GenericArrayType) {
            return new hZ(((GenericArrayType) type).getGenericComponentType());
        } else {
            if (!(type instanceof WildcardType)) {
                return type;
            }
            WildcardType wildcardType = (WildcardType) type;
            return new C0234hX(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
        }
    }

    public static Type A03(Type type, Class<?> cls, Class<?> cls2) {
        if (type instanceof WildcardType) {
            type = ((WildcardType) type).getUpperBounds()[0];
        }
        C0236ha.A00(cls2.isAssignableFrom(cls));
        return A05(type, cls, A04(type, cls, cls2), new HashSet());
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

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:84:? */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:83:0x003c */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:46:0x00ac */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v2, types: [java.lang.reflect.WildcardType] */
    /* JADX WARN: Type inference failed for: r9v3, types: [java.lang.reflect.ParameterizedType] */
    /* JADX WARN: Type inference failed for: r9v5, types: [java.lang.reflect.GenericArrayType] */
    /* JADX WARN: Type inference failed for: r9v7, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r9v9 */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x003e A[EDGE_INSN: B:81:0x003e->B:16:0x003e ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.reflect.Type A05(java.lang.reflect.Type r7, java.lang.Class<?> r8, java.lang.reflect.Type r9, java.util.Collection<java.lang.reflect.TypeVariable> r10) {
        /*
        // Method dump skipped, instructions count: 283
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0233hW.A05(java.lang.reflect.Type, java.lang.Class, java.lang.reflect.Type, java.util.Collection):java.lang.reflect.Type");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000b, code lost:
        if (((java.lang.Class) r1).isPrimitive() == false) goto L_0x000d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A06(java.lang.reflect.Type r1) {
        /*
            boolean r0 = r1 instanceof java.lang.Class
            if (r0 == 0) goto L_0x000d
            java.lang.Class r1 = (java.lang.Class) r1
            boolean r1 = r1.isPrimitive()
            r0 = 0
            if (r1 != 0) goto L_0x000e
        L_0x000d:
            r0 = 1
        L_0x000e:
            X.C0236ha.A00(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0233hW.A06(java.lang.reflect.Type):void");
    }
}
