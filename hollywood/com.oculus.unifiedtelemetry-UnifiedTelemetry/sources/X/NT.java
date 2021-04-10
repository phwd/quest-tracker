package X;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class NT implements Serializable {
    public static final NT A02 = new NT();
    public static final AbstractC0224Wl[] A03 = new AbstractC0224Wl[0];
    public static final AnonymousClass73 A04 = new AnonymousClass73(Boolean.TYPE);
    public static final AnonymousClass73 A05 = new AnonymousClass73(Integer.TYPE);
    public static final AnonymousClass73 A06 = new AnonymousClass73(Long.TYPE);
    public static final AnonymousClass73 A07 = new AnonymousClass73(String.class);
    public static final long serialVersionUID = 1;
    public transient OB A00;
    public transient OB A01;
    public final NS[] _modifiers = null;
    public final NC _parser = new NC(this);
    public final C0109Kf<ON, AbstractC0224Wl> _typeCache = new C0109Kf<>(16, 100);

    public static final AbstractC0224Wl A02(Class<?> cls, AbstractC0224Wl[] wlArr) {
        TypeVariable<Class<?>>[] typeParameters = cls.getTypeParameters();
        int length = typeParameters.length;
        int length2 = wlArr.length;
        if (length == length2) {
            String[] strArr = new String[length];
            for (int i = 0; i < length; i++) {
                strArr[i] = typeParameters[i].getName();
            }
            return new AnonymousClass73(cls, strArr, wlArr, null, null, false);
        }
        StringBuilder sb = new StringBuilder("Parameter type mismatch for ");
        sb.append(cls.getName());
        sb.append(": expected ");
        sb.append(length);
        sb.append(" parameters, was given ");
        sb.append(length2);
        throw new IllegalArgumentException(sb.toString());
    }

    private final OB A05(Type type, Class<?> cls) {
        OB ob;
        OB ob2 = new OB(type);
        Class<?> cls2 = ob2.A02;
        if (cls2 == cls) {
            return new OB(type);
        }
        if (cls2 == HashMap.class && cls == Map.class) {
            synchronized (this) {
                OB ob3 = this.A01;
                if (ob3 == null) {
                    OB A002 = ob2.A00();
                    A03(A002, Map.class);
                    ob3 = A002.A01;
                    this.A01 = ob3;
                }
                ob = ob3.A00();
                ob2.A01 = ob;
            }
        } else if (cls2 != ArrayList.class || cls != List.class) {
            return A03(ob2, cls);
        } else {
            synchronized (this) {
                OB ob4 = this.A00;
                if (ob4 == null) {
                    OB A003 = ob2.A00();
                    A03(A003, List.class);
                    ob4 = A003.A01;
                    this.A00 = ob4;
                }
                ob = ob4.A00();
                ob2.A01 = ob;
            }
            return ob2;
        }
        ob.A00 = ob2;
        return ob2;
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;)LX/Wl; */
    public static AbstractC0224Wl A00(NT nt, Class cls) {
        AbstractC0224Wl wl;
        AbstractC0224Wl[] A062 = nt.A06(cls, Collection.class, new O4(nt, null, cls, null));
        if (A062 == null) {
            wl = new AnonymousClass73(Object.class);
        } else if (A062.length == 1) {
            wl = A062[0];
        } else {
            throw new IllegalArgumentException(AnonymousClass06.A05("Strange Collection type ", cls.getName(), ": can not determine type parameters"));
        }
        return AnonymousClass2W.A00(cls, wl);
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;)LX/Wl; */
    public static AbstractC0224Wl A01(NT nt, Class cls) {
        AbstractC0224Wl wl;
        AbstractC0224Wl wl2;
        AbstractC0224Wl[] A062 = nt.A06(cls, Map.class, new O4(nt, null, cls, null));
        if (A062 == null) {
            wl = new AnonymousClass73(Object.class);
            wl2 = new AnonymousClass73(Object.class);
        } else if (A062.length == 2) {
            wl = A062[0];
            wl2 = A062[1];
        } else {
            throw new IllegalArgumentException(AnonymousClass06.A05("Strange Map type ", cls.getName(), ": can not determine type parameters"));
        }
        return AnonymousClass2V.A00(cls, wl, wl2);
    }

    private final OB A03(OB ob, Class<?> cls) {
        OB ob2;
        Class<?> cls2 = ob.A02;
        Type[] genericInterfaces = cls2.getGenericInterfaces();
        if (genericInterfaces != null) {
            int length = genericInterfaces.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    ob2 = A05(genericInterfaces[i], cls);
                    if (ob2 != null) {
                        break;
                    }
                    i++;
                } else {
                    break;
                }
            }
        }
        Type genericSuperclass = cls2.getGenericSuperclass();
        if (genericSuperclass == null || (ob2 = A05(genericSuperclass, cls)) == null) {
            return null;
        }
        ob2.A00 = ob;
        ob.A01 = ob2;
        return ob;
    }

    private final OB A04(Type type, Class<?> cls) {
        OB A042;
        OB ob = new OB(type);
        Class<?> cls2 = ob.A02;
        if (cls2 != cls) {
            Type genericSuperclass = cls2.getGenericSuperclass();
            if (genericSuperclass == null || (A042 = A04(genericSuperclass, cls)) == null) {
                return null;
            }
            A042.A00 = ob;
            ob.A01 = A042;
        }
        return ob;
    }

    public final AbstractC0224Wl A07(AbstractC0224Wl wl, Class<?> cls) {
        if (!(wl instanceof AnonymousClass73) || (!cls.isArray() && !Map.class.isAssignableFrom(cls) && !Collection.class.isAssignableFrom(cls))) {
            return wl.A06(cls);
        }
        if (wl._class.isAssignableFrom(cls)) {
            new O4(this, null, wl._class, null);
            AbstractC0224Wl A08 = A08(cls);
            Object obj = wl._valueHandler;
            if (obj != null) {
                A08 = A08.A0C(obj);
            }
            Object obj2 = wl._typeHandler;
            if (obj2 != null) {
                return A08.A0B(obj2);
            }
            return A08;
        }
        StringBuilder sb = new StringBuilder("Class ");
        sb.append(cls.getClass().getName());
        sb.append(" not subtype of ");
        sb.append(wl);
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;LX/O4;)LX/Wl; */
    public final AbstractC0224Wl A08(Class cls) {
        AbstractC0224Wl wl;
        AbstractC0224Wl r1;
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
        ON on = new ON(cls);
        synchronized (this._typeCache) {
            wl = this._typeCache.get(on);
        }
        if (wl != null) {
            return wl;
        }
        if (cls.isArray()) {
            r1 = AnonymousClass78.A00(A09(cls.getComponentType(), null));
        } else {
            if (!cls.isEnum()) {
                if (Map.class.isAssignableFrom(cls)) {
                    r1 = A01(this, cls);
                } else if (Collection.class.isAssignableFrom(cls)) {
                    r1 = A00(this, cls);
                }
            }
            r1 = new AnonymousClass73(cls);
        }
        synchronized (this._typeCache) {
            this._typeCache.put(on, r1);
        }
        return r1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0065  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AbstractC0224Wl A09(java.lang.reflect.Type r10, X.O4 r11) {
        /*
        // Method dump skipped, instructions count: 285
        */
        throw new UnsupportedOperationException("Method not decompiled: X.NT.A09(java.lang.reflect.Type, X.O4):X.Wl");
    }

    public final AbstractC0224Wl[] A0A(AbstractC0224Wl wl, Class<?> cls) {
        Class<?> cls2 = wl._class;
        if (cls2 != cls) {
            return A06(cls2, cls, new O4(this, null, cls2, wl));
        }
        int A022 = wl.A02();
        if (A022 == 0) {
            return null;
        }
        AbstractC0224Wl[] wlArr = new AbstractC0224Wl[A022];
        for (int i = 0; i < A022; i++) {
            wlArr[i] = wl.A05(i);
        }
        return wlArr;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000a, code lost:
        if (r3 != null) goto L_0x000c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final X.AbstractC0224Wl[] A06(java.lang.Class<?> r9, java.lang.Class<?> r10, X.O4 r11) {
        /*
        // Method dump skipped, instructions count: 192
        */
        throw new UnsupportedOperationException("Method not decompiled: X.NT.A06(java.lang.Class, java.lang.Class, X.O4):X.Wl[]");
    }
}
