package X;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: X.0ml  reason: invalid class name and case insensitive filesystem */
public final class C06240ml implements Serializable {
    public static final C06240ml A02 = new C06240ml();
    public static final AbstractC04000gb[] A03 = new AbstractC04000gb[0];
    public static final AnonymousClass071 A04 = new AnonymousClass071(Boolean.TYPE);
    public static final AnonymousClass071 A05 = new AnonymousClass071(Integer.TYPE);
    public static final AnonymousClass071 A06 = new AnonymousClass071(Long.TYPE);
    public static final AnonymousClass071 A07 = new AnonymousClass071(String.class);
    public static final long serialVersionUID = 1;
    public transient C06220mj A00;
    public transient C06220mj A01;
    public final AbstractC06250mm[] _modifiers = null;
    public final C06270mo _parser = new C06270mo(this);
    public final C06390n0<C06210mi, AbstractC04000gb> _typeCache = new C06390n0<>(16, 100);

    public static final AbstractC04000gb A02(Class<?> cls, AbstractC04000gb[] r10) {
        TypeVariable<Class<?>>[] typeParameters = cls.getTypeParameters();
        int length = typeParameters.length;
        int length2 = r10.length;
        if (length == length2) {
            String[] strArr = new String[length];
            for (int i = 0; i < length; i++) {
                strArr[i] = typeParameters[i].getName();
            }
            return new AnonymousClass071(cls, strArr, r10, null, null, false);
        }
        StringBuilder sb = new StringBuilder("Parameter type mismatch for ");
        sb.append(cls.getName());
        sb.append(": expected ");
        sb.append(length);
        sb.append(" parameters, was given ");
        sb.append(length2);
        throw new IllegalArgumentException(sb.toString());
    }

    private final C06220mj A05(Type type, Class<?> cls) {
        C06220mj r0;
        C06220mj r3 = new C06220mj(type);
        Class<?> cls2 = r3.A02;
        if (cls2 == cls) {
            return new C06220mj(type);
        }
        if (cls2 == HashMap.class && cls == Map.class) {
            synchronized (this) {
                C06220mj r02 = this.A01;
                if (r02 == null) {
                    C06220mj A002 = r3.A00();
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
                C06220mj r03 = this.A00;
                if (r03 == null) {
                    C06220mj A003 = r3.A00();
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

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;)LX/0gb; */
    public static AbstractC04000gb A00(C06240ml r3, Class cls) {
        AbstractC04000gb r0;
        AbstractC04000gb[] A062 = r3.A06(cls, Collection.class, new C06230mk(r3, null, cls, null));
        if (A062 == null) {
            r0 = new AnonymousClass071(Object.class);
        } else if (A062.length == 1) {
            r0 = A062[0];
        } else {
            throw new IllegalArgumentException(AnonymousClass006.A07("Strange Collection type ", cls.getName(), ": can not determine type parameters"));
        }
        return AnonymousClass02Z.A00(cls, r0);
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;)LX/0gb; */
    public static AbstractC04000gb A01(C06240ml r3, Class cls) {
        AbstractC04000gb r1;
        AbstractC04000gb r0;
        AbstractC04000gb[] A062 = r3.A06(cls, Map.class, new C06230mk(r3, null, cls, null));
        if (A062 == null) {
            r1 = new AnonymousClass071(Object.class);
            r0 = new AnonymousClass071(Object.class);
        } else if (A062.length == 2) {
            r1 = A062[0];
            r0 = A062[1];
        } else {
            throw new IllegalArgumentException(AnonymousClass006.A07("Strange Map type ", cls.getName(), ": can not determine type parameters"));
        }
        return AnonymousClass02Y.A00(cls, r1, r0);
    }

    private final C06220mj A03(C06220mj r6, Class<?> cls) {
        C06220mj r0;
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

    private final C06220mj A04(Type type, Class<?> cls) {
        C06220mj A042;
        C06220mj r1 = new C06220mj(type);
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

    public final AbstractC04000gb A07(AbstractC04000gb r4, Class<?> cls) {
        if (!(r4 instanceof AnonymousClass071) || (!cls.isArray() && !Map.class.isAssignableFrom(cls) && !Collection.class.isAssignableFrom(cls))) {
            return r4.A06(cls);
        }
        if (r4._class.isAssignableFrom(cls)) {
            new C06230mk(this, null, r4._class, null);
            AbstractC04000gb A08 = A08(cls);
            Object obj = r4._valueHandler;
            if (obj != null) {
                A08 = A08.A0C(obj);
            }
            Object obj2 = r4._typeHandler;
            if (obj2 != null) {
                return A08.A0B(obj2);
            }
            return A08;
        }
        StringBuilder sb = new StringBuilder("Class ");
        sb.append(cls.getClass().getName());
        sb.append(" not subtype of ");
        sb.append(r4);
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;LX/0mk;)LX/0gb; */
    public final AbstractC04000gb A08(Class cls) {
        AbstractC04000gb r0;
        AbstractC04000gb r1;
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
        C06210mi r3 = new C06210mi(cls);
        synchronized (this._typeCache) {
            r0 = this._typeCache.get(r3);
        }
        if (r0 != null) {
            return r0;
        }
        if (cls.isArray()) {
            r1 = AnonymousClass076.A00(A09(cls.getComponentType(), null));
        } else {
            if (!cls.isEnum()) {
                if (Map.class.isAssignableFrom(cls)) {
                    r1 = A01(this, cls);
                } else if (Collection.class.isAssignableFrom(cls)) {
                    r1 = A00(this, cls);
                }
            }
            r1 = new AnonymousClass071(cls);
        }
        synchronized (this._typeCache) {
            this._typeCache.put(r3, r1);
        }
        return r1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0065  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AbstractC04000gb A09(java.lang.reflect.Type r10, X.C06230mk r11) {
        /*
        // Method dump skipped, instructions count: 285
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C06240ml.A09(java.lang.reflect.Type, X.0mk):X.0gb");
    }

    public final AbstractC04000gb[] A0A(AbstractC04000gb r5, Class<?> cls) {
        Class<?> cls2 = r5._class;
        if (cls2 != cls) {
            return A06(cls2, cls, new C06230mk(this, null, cls2, r5));
        }
        int A022 = r5.A02();
        if (A022 == 0) {
            return null;
        }
        AbstractC04000gb[] r2 = new AbstractC04000gb[A022];
        for (int i = 0; i < A022; i++) {
            r2[i] = r5.A05(i);
        }
        return r2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000a, code lost:
        if (r3 != null) goto L_0x000c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final X.AbstractC04000gb[] A06(java.lang.Class<?> r9, java.lang.Class<?> r10, X.C06230mk r11) {
        /*
        // Method dump skipped, instructions count: 192
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C06240ml.A06(java.lang.Class, java.lang.Class, X.0mk):X.0gb[]");
    }
}
