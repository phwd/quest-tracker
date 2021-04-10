package X;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: X.0od  reason: invalid class name and case insensitive filesystem */
public final class C07040od implements Serializable {
    public static final C07040od A02 = new C07040od();
    public static final AnonymousClass0aI[] A03 = new AnonymousClass0aI[0];
    public static final AnonymousClass0C9 A04 = new AnonymousClass0C9(Boolean.TYPE);
    public static final AnonymousClass0C9 A05 = new AnonymousClass0C9(Integer.TYPE);
    public static final AnonymousClass0C9 A06 = new AnonymousClass0C9(Long.TYPE);
    public static final AnonymousClass0C9 A07 = new AnonymousClass0C9(String.class);
    public static final long serialVersionUID = 1;
    public transient C07020ob A00;
    public transient C07020ob A01;
    public final AbstractC07050oe[] _modifiers = null;
    public final C07070og _parser = new C07070og(this);
    public final C07180ot<C07010oa, AnonymousClass0aI> _typeCache = new C07180ot<>(16, 100);

    public static final AnonymousClass0aI A02(Class<?> cls, AnonymousClass0aI[] r10) {
        TypeVariable<Class<?>>[] typeParameters = cls.getTypeParameters();
        int length = typeParameters.length;
        int length2 = r10.length;
        if (length == length2) {
            String[] strArr = new String[length];
            for (int i = 0; i < length; i++) {
                strArr[i] = typeParameters[i].getName();
            }
            return new AnonymousClass0C9(cls, strArr, r10, null, null, false);
        }
        throw new IllegalArgumentException("Parameter type mismatch for " + cls.getName() + ": expected " + length + " parameters, was given " + length2);
    }

    private final C07020ob A05(Type type, Class<?> cls) {
        C07020ob r0;
        C07020ob r3 = new C07020ob(type);
        Class<?> cls2 = r3.A02;
        if (cls2 == cls) {
            return new C07020ob(type);
        }
        if (cls2 == HashMap.class && cls == Map.class) {
            synchronized (this) {
                C07020ob r02 = this.A01;
                if (r02 == null) {
                    C07020ob A002 = r3.A00();
                    A03(A002, Map.class);
                    r02 = A002.A01;
                    this.A01 = r02;
                }
                r0 = r02.A00();
                r3.A01 = r0;
            }
        } else if (cls2 != ArrayList.class || cls != List.class) {
            return A03(r3, cls);
        } else {
            synchronized (this) {
                C07020ob r03 = this.A00;
                if (r03 == null) {
                    C07020ob A003 = r3.A00();
                    A03(A003, List.class);
                    r03 = A003.A01;
                    this.A00 = r03;
                }
                r0 = r03.A00();
                r3.A01 = r0;
            }
            return r3;
        }
        r0.A00 = r3;
        return r3;
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;)LX/0aI; */
    public static AnonymousClass0aI A00(C07040od r3, Class cls) {
        AnonymousClass0aI r0;
        AnonymousClass0aI[] A062 = r3.A06(cls, Collection.class, new C07030oc(r3, null, cls, null));
        if (A062 == null) {
            r0 = new AnonymousClass0C9(Object.class);
        } else if (A062.length == 1) {
            r0 = A062[0];
        } else {
            throw new IllegalArgumentException(AnonymousClass006.A07("Strange Collection type ", cls.getName(), ": can not determine type parameters"));
        }
        return C006606d.A00(cls, r0);
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;)LX/0aI; */
    public static AnonymousClass0aI A01(C07040od r3, Class cls) {
        AnonymousClass0aI r1;
        AnonymousClass0aI r0;
        AnonymousClass0aI[] A062 = r3.A06(cls, Map.class, new C07030oc(r3, null, cls, null));
        if (A062 == null) {
            r1 = new AnonymousClass0C9(Object.class);
            r0 = new AnonymousClass0C9(Object.class);
        } else if (A062.length == 2) {
            r1 = A062[0];
            r0 = A062[1];
        } else {
            throw new IllegalArgumentException(AnonymousClass006.A07("Strange Map type ", cls.getName(), ": can not determine type parameters"));
        }
        return C006506c.A00(cls, r1, r0);
    }

    private final C07020ob A03(C07020ob r6, Class<?> cls) {
        C07020ob r0;
        Class<?> cls2 = r6.A02;
        Type[] genericInterfaces = cls2.getGenericInterfaces();
        if (genericInterfaces != null) {
            int length = genericInterfaces.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    r0 = A05(genericInterfaces[i], cls);
                    if (r0 != null) {
                        break;
                    }
                    i++;
                } else {
                    break;
                }
            }
        }
        Type genericSuperclass = cls2.getGenericSuperclass();
        if (genericSuperclass == null || (r0 = A05(genericSuperclass, cls)) == null) {
            return null;
        }
        r0.A00 = r6;
        r6.A01 = r0;
        return r6;
    }

    private final C07020ob A04(Type type, Class<?> cls) {
        C07020ob A042;
        C07020ob r1 = new C07020ob(type);
        Class<?> cls2 = r1.A02;
        if (cls2 != cls) {
            Type genericSuperclass = cls2.getGenericSuperclass();
            if (genericSuperclass == null || (A042 = A04(genericSuperclass, cls)) == null) {
                return null;
            }
            A042.A00 = r1;
            r1.A01 = A042;
        }
        return r1;
    }

    public final AnonymousClass0aI A07(AnonymousClass0aI r4, Class<?> cls) {
        if (!(r4 instanceof AnonymousClass0C9) || (!cls.isArray() && !Map.class.isAssignableFrom(cls) && !Collection.class.isAssignableFrom(cls))) {
            return r4.A07(cls);
        }
        if (r4._class.isAssignableFrom(cls)) {
            new C07030oc(this, null, r4._class, null);
            AnonymousClass0aI A08 = A08(cls);
            Object obj = r4._valueHandler;
            if (obj != null) {
                A08 = A08.A0F(obj);
            }
            Object obj2 = r4._typeHandler;
            if (obj2 != null) {
                return A08.A0E(obj2);
            }
            return A08;
        }
        throw new IllegalArgumentException("Class " + cls.getClass().getName() + " not subtype of " + r4);
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;LX/0oc;)LX/0aI; */
    public final AnonymousClass0aI A08(Class cls) {
        AnonymousClass0aI r0;
        AnonymousClass0aI r1;
        if (cls == String.class) {
            return A07;
        }
        if (cls == Boolean.TYPE) {
            return A04;
        }
        if (cls == Integer.TYPE) {
            return A05;
        }
        if (cls == Long.TYPE) {
            return A06;
        }
        C07010oa r3 = new C07010oa(cls);
        synchronized (this._typeCache) {
            r0 = this._typeCache.get(r3);
        }
        if (r0 != null) {
            return r0;
        }
        if (cls.isArray()) {
            r1 = AnonymousClass0CC.A00(A09(cls.getComponentType(), null));
        } else {
            if (!cls.isEnum()) {
                if (Map.class.isAssignableFrom(cls)) {
                    r1 = A01(this, cls);
                } else if (Collection.class.isAssignableFrom(cls)) {
                    r1 = A00(this, cls);
                }
            }
            r1 = new AnonymousClass0C9(cls);
        }
        synchronized (this._typeCache) {
            this._typeCache.put(r3, r1);
        }
        return r1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0065  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AnonymousClass0aI A09(java.lang.reflect.Type r10, X.C07030oc r11) {
        /*
        // Method dump skipped, instructions count: 285
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C07040od.A09(java.lang.reflect.Type, X.0oc):X.0aI");
    }

    public final AnonymousClass0aI[] A0A(AnonymousClass0aI r5, Class<?> cls) {
        Class<?> cls2 = r5._class;
        if (cls2 != cls) {
            return A06(cls2, cls, new C07030oc(this, null, cls2, r5));
        }
        int A032 = r5.A03();
        if (A032 == 0) {
            return null;
        }
        AnonymousClass0aI[] r2 = new AnonymousClass0aI[A032];
        for (int i = 0; i < A032; i++) {
            r2[i] = r5.A06(i);
        }
        return r2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000a, code lost:
        if (r3 != null) goto L_0x000c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final X.AnonymousClass0aI[] A06(java.lang.Class<?> r9, java.lang.Class<?> r10, X.C07030oc r11) {
        /*
        // Method dump skipped, instructions count: 192
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C07040od.A06(java.lang.Class, java.lang.Class, X.0oc):X.0aI[]");
    }
}
