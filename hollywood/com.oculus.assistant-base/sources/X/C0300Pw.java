package X;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: X.Pw  reason: case insensitive filesystem */
public final class C0300Pw implements Serializable {
    public static final C0300Pw A02 = new C0300Pw();
    public static final AbstractC1024qt[] A03 = new AbstractC1024qt[0];
    public static final fF A04 = new fF(Boolean.TYPE);
    public static final fF A05 = new fF(Integer.TYPE);
    public static final fF A06 = new fF(Long.TYPE);
    public static final fF A07 = new fF(String.class);
    public static final long serialVersionUID = 1;
    public transient C0298Pu A00;
    public transient C0298Pu A01;
    public final AbstractC0301Px[] _modifiers = null;
    public final C0303Pz _parser = new C0303Pz(this);
    public final QA _typeCache = new QA(16, 100);

    public static final AbstractC1024qt A02(Class cls, AbstractC1024qt[] qtVarArr) {
        TypeVariable[] typeParameters = cls.getTypeParameters();
        int length = typeParameters.length;
        int length2 = qtVarArr.length;
        if (length == length2) {
            String[] strArr = new String[length];
            for (int i = 0; i < length; i++) {
                strArr[i] = typeParameters[i].getName();
            }
            return new fF(cls, strArr, qtVarArr, null, null, false);
        }
        StringBuilder sb = new StringBuilder("Parameter type mismatch for ");
        sb.append(cls.getName());
        sb.append(": expected ");
        sb.append(length);
        sb.append(" parameters, was given ");
        sb.append(length2);
        throw new IllegalArgumentException(sb.toString());
    }

    private final C0298Pu A05(Type type, Class cls) {
        C0298Pu pu = new C0298Pu(type);
        Class cls2 = pu.A02;
        if (cls2 == cls) {
            return new C0298Pu(type);
        }
        if (cls2 == HashMap.class && cls == Map.class) {
            synchronized (this) {
                C0298Pu pu2 = this.A01;
                if (pu2 == null) {
                    C0298Pu A002 = pu.A00();
                    A03(A002, Map.class);
                    pu2 = A002.A01;
                    this.A01 = pu2;
                }
                C0298Pu A003 = pu2.A00();
                pu.A01 = A003;
                A003.A00 = pu;
            }
        } else if (cls2 != ArrayList.class || cls != List.class) {
            return A03(pu, cls);
        } else {
            synchronized (this) {
                C0298Pu pu3 = this.A00;
                if (pu3 == null) {
                    C0298Pu A004 = pu.A00();
                    A03(A004, List.class);
                    pu3 = A004.A01;
                    this.A00 = pu3;
                }
                C0298Pu A005 = pu3.A00();
                pu.A01 = A005;
                A005.A00 = pu;
            }
            return pu;
        }
        return pu;
    }

    public static AbstractC1024qt A00(C0300Pw pw, Class cls) {
        AbstractC1024qt qtVar;
        AbstractC1024qt[] A062 = pw.A06(cls, Collection.class, new C0299Pv(pw, null, cls, null));
        if (A062 == null) {
            qtVar = new fF(Object.class);
        } else if (A062.length == 1) {
            qtVar = A062[0];
        } else {
            throw new IllegalArgumentException(AnonymousClass08.A05("Strange Collection type ", cls.getName(), ": can not determine type parameters"));
        }
        return C00323q.A00(cls, qtVar);
    }

    public static AbstractC1024qt A01(C0300Pw pw, Class cls) {
        AbstractC1024qt qtVar;
        AbstractC1024qt qtVar2;
        AbstractC1024qt[] A062 = pw.A06(cls, Map.class, new C0299Pv(pw, null, cls, null));
        if (A062 == null) {
            qtVar = new fF(Object.class);
            qtVar2 = new fF(Object.class);
        } else if (A062.length == 2) {
            qtVar = A062[0];
            qtVar2 = A062[1];
        } else {
            throw new IllegalArgumentException(AnonymousClass08.A05("Strange Map type ", cls.getName(), ": can not determine type parameters"));
        }
        return C00313p.A00(cls, qtVar, qtVar2);
    }

    private final C0298Pu A03(C0298Pu pu, Class cls) {
        C0298Pu pu2;
        Class cls2 = pu.A02;
        Type[] genericInterfaces = cls2.getGenericInterfaces();
        if (genericInterfaces != null) {
            int length = genericInterfaces.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    pu2 = A05(genericInterfaces[i], cls);
                    if (pu2 != null) {
                        break;
                    }
                    i++;
                } else {
                    break;
                }
            }
        }
        Type genericSuperclass = cls2.getGenericSuperclass();
        if (genericSuperclass == null || (pu2 = A05(genericSuperclass, cls)) == null) {
            return null;
        }
        pu2.A00 = pu;
        pu.A01 = pu2;
        return pu;
    }

    private final C0298Pu A04(Type type, Class cls) {
        C0298Pu A042;
        C0298Pu pu = new C0298Pu(type);
        Class cls2 = pu.A02;
        if (cls2 != cls) {
            Type genericSuperclass = cls2.getGenericSuperclass();
            if (genericSuperclass == null || (A042 = A04(genericSuperclass, cls)) == null) {
                return null;
            }
            A042.A00 = pu;
            pu.A01 = A042;
        }
        return pu;
    }

    public final AbstractC1024qt A07(AbstractC1024qt qtVar, Class cls) {
        if (!(qtVar instanceof fF) || (!cls.isArray() && !Map.class.isAssignableFrom(cls) && !Collection.class.isAssignableFrom(cls))) {
            return qtVar.A09(cls);
        }
        if (qtVar._class.isAssignableFrom(cls)) {
            new C0299Pv(this, null, qtVar._class, null);
            AbstractC1024qt A08 = A08(cls);
            Object obj = qtVar._valueHandler;
            if (obj != null) {
                A08 = A08.A0E(obj);
            }
            Object obj2 = qtVar._typeHandler;
            if (obj2 != null) {
                return A08.A0D(obj2);
            }
            return A08;
        }
        StringBuilder sb = new StringBuilder("Class ");
        sb.append(cls.getClass().getName());
        sb.append(" not subtype of ");
        sb.append(qtVar);
        throw new IllegalArgumentException(sb.toString());
    }

    public final AbstractC1024qt A08(Class cls) {
        AbstractC1024qt qtVar;
        AbstractC1024qt fFVar;
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
        C0297Pt pt = new C0297Pt(cls);
        synchronized (this._typeCache) {
            qtVar = (AbstractC1024qt) this._typeCache.get(pt);
        }
        if (qtVar != null) {
            return qtVar;
        }
        if (cls.isArray()) {
            fFVar = C0683fI.A00(A09(cls.getComponentType(), null));
        } else {
            if (!cls.isEnum()) {
                if (Map.class.isAssignableFrom(cls)) {
                    fFVar = A01(this, cls);
                } else if (Collection.class.isAssignableFrom(cls)) {
                    fFVar = A00(this, cls);
                }
            }
            fFVar = new fF(cls);
        }
        synchronized (this._typeCache) {
            this._typeCache.put(pt, fFVar);
        }
        return fFVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x006b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AbstractC1024qt A09(java.lang.reflect.Type r10, X.C0299Pv r11) {
        /*
        // Method dump skipped, instructions count: 327
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0300Pw.A09(java.lang.reflect.Type, X.Pv):X.qt");
    }

    public final AbstractC1024qt[] A0A(AbstractC1024qt qtVar, Class cls) {
        Class cls2 = qtVar._class;
        if (cls2 != cls) {
            return A06(cls2, cls, new C0299Pv(this, null, cls2, qtVar));
        }
        int A032 = qtVar.A03();
        if (A032 == 0) {
            return null;
        }
        AbstractC1024qt[] qtVarArr = new AbstractC1024qt[A032];
        for (int i = 0; i < A032; i++) {
            qtVarArr[i] = qtVar.A06(i);
        }
        return qtVarArr;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000a, code lost:
        if (r3 != null) goto L_0x000c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final X.AbstractC1024qt[] A06(java.lang.Class r9, java.lang.Class r10, X.C0299Pv r11) {
        /*
        // Method dump skipped, instructions count: 192
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0300Pw.A06(java.lang.Class, java.lang.Class, X.Pv):X.qt[]");
    }
}
