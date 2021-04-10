package X;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: X.0r9  reason: invalid class name */
public final class AnonymousClass0r9 implements Serializable {
    public static final AnonymousClass0r9 A02 = new AnonymousClass0r9();
    public static final AbstractC02190iF[] A03 = new AbstractC02190iF[0];
    public static final AnonymousClass0C7 A04 = new AnonymousClass0C7(Boolean.TYPE);
    public static final AnonymousClass0C7 A05 = new AnonymousClass0C7(Integer.TYPE);
    public static final AnonymousClass0C7 A06 = new AnonymousClass0C7(Long.TYPE);
    public static final AnonymousClass0C7 A07 = new AnonymousClass0C7(String.class);
    public static final long serialVersionUID = 1;
    public transient AnonymousClass0r7 A00;
    public transient AnonymousClass0r7 A01;
    public final AbstractC04730rA[] _modifiers;
    public final C04750rC _parser;
    public final C04850rP<C04720r6, AbstractC02190iF> _typeCache;

    public static final AbstractC02190iF A02(Class<?> cls, AbstractC02190iF[] r10) {
        TypeVariable<Class<?>>[] typeParameters = cls.getTypeParameters();
        int length = typeParameters.length;
        int length2 = r10.length;
        if (length == length2) {
            String[] strArr = new String[length];
            for (int i = 0; i < length; i++) {
                strArr[i] = typeParameters[i].getName();
            }
            return new AnonymousClass0C7(cls, strArr, r10, null, null, false);
        }
        StringBuilder sb = new StringBuilder("Parameter type mismatch for ");
        sb.append(cls.getName());
        sb.append(": expected ");
        sb.append(length);
        sb.append(" parameters, was given ");
        sb.append(length2);
        throw new IllegalArgumentException(sb.toString());
    }

    private final AnonymousClass0r7 A05(Type type, Class<?> cls) {
        AnonymousClass0r7 r3 = new AnonymousClass0r7(type);
        Class<?> cls2 = r3.A02;
        if (cls2 == cls) {
            return new AnonymousClass0r7(type);
        }
        if (cls2 == HashMap.class && cls == Map.class) {
            synchronized (this) {
                AnonymousClass0r7 r0 = this.A01;
                if (r0 == null) {
                    AnonymousClass0r7 A002 = r3.A00();
                    A03(A002, Map.class);
                    r0 = A002.A01;
                    this.A01 = r0;
                }
                AnonymousClass0r7 A003 = r0.A00();
                r3.A01 = A003;
                A003.A00 = r3;
            }
        } else if (cls2 != ArrayList.class || cls != List.class) {
            return A03(r3, cls);
        } else {
            synchronized (this) {
                AnonymousClass0r7 r02 = this.A00;
                if (r02 == null) {
                    AnonymousClass0r7 A004 = r3.A00();
                    A03(A004, List.class);
                    r02 = A004.A01;
                    this.A00 = r02;
                }
                AnonymousClass0r7 A005 = r02.A00();
                r3.A01 = A005;
                A005.A00 = r3;
            }
            return r3;
        }
        return r3;
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;)LX/0iF; */
    public static AbstractC02190iF A00(AnonymousClass0r9 r3, Class cls) {
        AbstractC02190iF r0;
        AbstractC02190iF[] A062 = r3.A06(cls, Collection.class, new AnonymousClass0r8(r3, null, cls, null));
        if (A062 == null) {
            r0 = new AnonymousClass0C7(Object.class);
        } else if (A062.length == 1) {
            r0 = A062[0];
        } else {
            throw new IllegalArgumentException(AnonymousClass006.A09("Strange Collection type ", cls.getName(), ": can not determine type parameters"));
        }
        return AnonymousClass03E.A00(cls, r0);
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;)LX/0iF; */
    public static AbstractC02190iF A01(AnonymousClass0r9 r3, Class cls) {
        AbstractC02190iF r1;
        AbstractC02190iF r0;
        AbstractC02190iF[] A062 = r3.A06(cls, Map.class, new AnonymousClass0r8(r3, null, cls, null));
        if (A062 == null) {
            r1 = new AnonymousClass0C7(Object.class);
            r0 = new AnonymousClass0C7(Object.class);
        } else if (A062.length == 2) {
            r1 = A062[0];
            r0 = A062[1];
        } else {
            throw new IllegalArgumentException(AnonymousClass006.A09("Strange Map type ", cls.getName(), ": can not determine type parameters"));
        }
        return AnonymousClass03D.A00(cls, r1, r0);
    }

    private final AnonymousClass0r7 A03(AnonymousClass0r7 r6, Class<?> cls) {
        AnonymousClass0r7 r0;
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

    private final AnonymousClass0r7 A04(Type type, Class<?> cls) {
        AnonymousClass0r7 A042;
        AnonymousClass0r7 r1 = new AnonymousClass0r7(type);
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

    public final AbstractC02190iF A07(AbstractC02190iF r4, Class<?> cls) {
        if (!(r4 instanceof AnonymousClass0C7) || (!cls.isArray() && !Map.class.isAssignableFrom(cls) && !Collection.class.isAssignableFrom(cls))) {
            return r4.A07(cls);
        }
        if (r4._class.isAssignableFrom(cls)) {
            new AnonymousClass0r8(this, null, r4._class, null);
            AbstractC02190iF A08 = A08(cls);
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
        StringBuilder sb = new StringBuilder("Class ");
        sb.append(cls.getClass().getName());
        sb.append(" not subtype of ");
        sb.append(r4);
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;LX/0r8;)LX/0iF; */
    public final AbstractC02190iF A08(Class cls) {
        AbstractC02190iF r0;
        AbstractC02190iF r1;
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
        C04720r6 r3 = new C04720r6(cls);
        synchronized (this._typeCache) {
            r0 = this._typeCache.get(r3);
        }
        if (r0 != null) {
            return r0;
        }
        if (cls.isArray()) {
            r1 = AnonymousClass0CA.A00(A09(cls.getComponentType(), null));
        } else {
            if (!cls.isEnum()) {
                if (Map.class.isAssignableFrom(cls)) {
                    r1 = A01(this, cls);
                } else if (Collection.class.isAssignableFrom(cls)) {
                    r1 = A00(this, cls);
                }
            }
            r1 = new AnonymousClass0C7(cls);
        }
        synchronized (this._typeCache) {
            this._typeCache.put(r3, r1);
        }
        return r1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AbstractC02190iF A09(java.lang.reflect.Type r10, X.AnonymousClass0r8 r11) {
        /*
        // Method dump skipped, instructions count: 333
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0r9.A09(java.lang.reflect.Type, X.0r8):X.0iF");
    }

    public final AbstractC02190iF[] A0A(AbstractC02190iF r5, Class<?> cls) {
        Class<?> cls2 = r5._class;
        if (cls2 != cls) {
            return A06(cls2, cls, new AnonymousClass0r8(this, null, cls2, r5));
        }
        int A032 = r5.A03();
        if (A032 == 0) {
            return null;
        }
        AbstractC02190iF[] r2 = new AbstractC02190iF[A032];
        for (int i = 0; i < A032; i++) {
            r2[i] = r5.A06(i);
        }
        return r2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000a, code lost:
        if (r3 != null) goto L_0x000c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final X.AbstractC02190iF[] A06(java.lang.Class<?> r9, java.lang.Class<?> r10, X.AnonymousClass0r8 r11) {
        /*
        // Method dump skipped, instructions count: 192
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0r9.A06(java.lang.Class, java.lang.Class, X.0r8):X.0iF[]");
    }

    public AnonymousClass0r9() {
        this._typeCache = new C04850rP<>(16, 100);
        this._parser = new C04750rC(this);
        this._modifiers = null;
    }

    public AnonymousClass0r9(C04750rC r4, AbstractC04730rA[] r5) {
        this._typeCache = new C04850rP<>(16, 100);
        this._parser = r4;
        this._modifiers = r5;
    }
}
