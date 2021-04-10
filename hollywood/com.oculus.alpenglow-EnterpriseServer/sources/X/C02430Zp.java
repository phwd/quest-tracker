package X;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: X.0Zp  reason: invalid class name and case insensitive filesystem */
public final class C02430Zp extends AbstractC06260mR {
    public AbstractC02450Zr A00;
    public AnonymousClass0EC A01;
    public AnonymousClass0EC A02;
    public C06720nk A03;
    public C07030oc A04;
    public Map<Object, AbstractC02450Zr> A05;
    public Set<String> A06;
    public final List<AbstractC02410Zn> A07;
    public final AbstractC02590aM A08;
    public final AnonymousClass0a7<?> A09;
    public final C02460Zs A0A;

    private final AbstractC07140on<Object, Object> A01(Object obj) {
        StringBuilder sb;
        String str;
        if (obj != null) {
            if (obj instanceof Class) {
                Class cls = (Class) obj;
                if (!(cls == AnonymousClass0ZO.class || cls == C06410mq.class)) {
                    if (AbstractC07140on.class.isAssignableFrom(cls)) {
                        C07130om.A02(cls, this.A09.A05(EnumC02540aC.CAN_OVERRIDE_ACCESS_MODIFIERS));
                    } else {
                        sb = new StringBuilder("AnnotationIntrospector returned Class ");
                        sb.append(cls.getName());
                        str = "; expected Class<Converter>";
                    }
                }
            } else {
                sb = new StringBuilder("AnnotationIntrospector returned Converter definition of type ");
                sb.append(obj.getClass().getName());
                str = "; expected type Converter or Class<Converter> instead";
            }
            sb.append(str);
            throw new IllegalStateException(sb.toString());
        }
        return null;
    }

    public static C02430Zp A00(C06730nl r4) {
        AnonymousClass0EC r0;
        C02430Zp r1 = new C02430Zp(r4);
        LinkedList<AnonymousClass0EC> linkedList = r4.A03;
        if (linkedList == null) {
            r0 = null;
        } else if (linkedList.size() > 1) {
            C06730nl.A04(r4, "Multiple 'any-setters' defined (" + linkedList.get(0) + " vs " + r4.A03.get(1) + ")");
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } else {
            r0 = linkedList.getFirst();
        }
        r1.A02 = r0;
        r1.A06 = r4.A00;
        r1.A05 = r4.A01;
        r1.A01 = r4.A06();
        return r1;
    }

    private final boolean A02(AnonymousClass0EC r5) {
        if (!super.A00._class.isAssignableFrom(r5.A00.getReturnType()) || (!this.A08.A0o(r5) && !"valueOf".equals(r5.A0L()))) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC06260mR
    public final C05750kz A03(C05750kz r3) {
        C05750kz A012;
        AbstractC02590aM r1 = this.A08;
        if (r1 == null || (A012 = r1.A01(this.A0A)) == null) {
            return r3;
        }
        return A012;
    }

    @Override // X.AbstractC06260mR
    public final EnumC05760l7 A04(EnumC05760l7 r3) {
        AbstractC02590aM r1 = this.A08;
        if (r1 != null) {
            return r1.A03(this.A0A, r3);
        }
        return r3;
    }

    @Override // X.AbstractC06260mR
    public final AnonymousClass0aI A05(Type type) {
        if (type == null) {
            return null;
        }
        C07030oc A0E = A0E();
        return A0E.A03.A09(type, A0E);
    }

    @Override // X.AbstractC06260mR
    public final C06380mi A06() {
        AbstractC02590aM r1 = this.A08;
        if (r1 == null) {
            return null;
        }
        return r1.A08(this.A0A);
    }

    @Override // X.AbstractC06260mR
    public final C02460Zs A07() {
        return this.A0A;
    }

    @Override // X.AbstractC06260mR
    public final AnonymousClass0EJ A08() {
        C02460Zs r1 = this.A0A;
        if (!r1.A06) {
            C02460Zs.A06(r1);
        }
        return r1.A00;
    }

    @Override // X.AbstractC06260mR
    public final AbstractC02450Zr A09() throws IllegalArgumentException {
        AbstractC02450Zr r0 = this.A00;
        if (r0 == null || Map.class.isAssignableFrom(r0.A0K())) {
            return this.A00;
        }
        throw new IllegalArgumentException(AnonymousClass006.A07("Invalid 'any-getter' annotation on method ", this.A00.A0L(), "(): return type is not instance of java.util.Map"));
    }

    @Override // X.AbstractC06260mR
    public final AnonymousClass0EC A0A() throws IllegalArgumentException {
        Class A0a;
        AnonymousClass0EC r0 = this.A02;
        if (r0 == null || (A0a = r0.A0a()) == String.class || A0a == Object.class) {
            return this.A02;
        }
        throw new IllegalArgumentException(AnonymousClass006.A08("Invalid 'any-setter' annotation on method ", this.A02.A0L(), "(): first argument not of type String or Object, but ", A0a.getName()));
    }

    @Override // X.AbstractC06260mR
    public final AnonymousClass0EC A0B() {
        return this.A01;
    }

    @Override // X.AbstractC06260mR
    public final AnonymousClass0EC A0C(String str, Class<?>[] clsArr) {
        C02460Zs r1 = this.A0A;
        if (r1.A01 == null) {
            C02460Zs.A07(r1);
        }
        LinkedHashMap<C06710nj, AnonymousClass0EC> linkedHashMap = r1.A01.A00;
        if (linkedHashMap == null) {
            return null;
        }
        return linkedHashMap.get(new C06710nj(str, clsArr));
    }

    @Override // X.AbstractC06260mR
    public final C06720nk A0D() {
        return this.A03;
    }

    @Override // X.AbstractC06260mR
    public final C07030oc A0E() {
        C07030oc r4 = this.A04;
        if (r4 != null) {
            return r4;
        }
        C07040od r3 = this.A09._base._typeFactory;
        AnonymousClass0aI r2 = super.A00;
        C07030oc r42 = new C07030oc(r3, null, r2._class, r2);
        this.A04 = r42;
        return r42;
    }

    @Override // X.AbstractC06260mR
    public final AbstractC07080oh A0F() {
        C02460Zs r1 = this.A0A;
        if (r1.A02 == null) {
            C02460Zs.A05(r1);
        }
        return r1.A02;
    }

    @Override // X.AbstractC06260mR
    public final AbstractC07140on<Object, Object> A0G() {
        AbstractC02590aM r1 = this.A08;
        if (r1 == null) {
            return null;
        }
        return A01(r1.A0V(this.A0A));
    }

    @Override // X.AbstractC06260mR
    public final AbstractC07140on<Object, Object> A0H() {
        AbstractC02590aM r1 = this.A08;
        if (r1 == null) {
            return null;
        }
        return A01(r1.A0Z(this.A0A));
    }

    @Override // X.AbstractC06260mR
    public final Class<?> A0I() {
        AbstractC02590aM r1 = this.A08;
        if (r1 == null) {
            return null;
        }
        return r1.A0S(this.A0A);
    }

    @Override // X.AbstractC06260mR
    public final Object A0J(boolean z) {
        C02460Zs r3 = this.A0A;
        if (!r3.A06) {
            C02460Zs.A06(r3);
        }
        AnonymousClass0EJ r1 = r3.A00;
        if (r1 == null) {
            return null;
        }
        if (z) {
            C07130om.A06(r1.A0R());
        }
        try {
            return r1._constructor.newInstance(new Object[0]);
        } catch (Exception e) {
            e = e;
            while (e.getCause() != null) {
                e = e.getCause();
            }
            if ((e instanceof Error) || (e instanceof RuntimeException)) {
                throw e;
            }
            throw new IllegalArgumentException(AnonymousClass006.A0A("Failed to instantiate bean of type ", r3.A09.getName(), ": (", e.getClass().getName(), ") ", e.getMessage()), e);
        }
    }

    @Override // X.AbstractC06260mR
    public final Constructor<?> A0K(Class<?>... clsArr) {
        Class<?> cls;
        C02460Zs r1 = this.A0A;
        if (!r1.A06) {
            C02460Zs.A06(r1);
        }
        for (AnonymousClass0EJ r4 : r1.A03) {
            if (r4._constructor.getParameterTypes().length == 1) {
                Class<?>[] parameterTypes = r4._constructor.getParameterTypes();
                if (0 >= parameterTypes.length) {
                    cls = null;
                } else {
                    cls = parameterTypes[0];
                }
                for (Class<?> cls2 : clsArr) {
                    if (cls2 == cls) {
                        return r4._constructor;
                    }
                }
                continue;
            }
        }
        return null;
    }

    @Override // X.AbstractC06260mR
    public final Method A0L(Class<?>... clsArr) {
        C02460Zs r1 = this.A0A;
        if (!r1.A06) {
            C02460Zs.A06(r1);
        }
        for (AnonymousClass0EC r4 : r1.A04) {
            if (A02(r4)) {
                Class A0a = r4.A0a();
                for (Class<?> cls : clsArr) {
                    if (A0a.isAssignableFrom(cls)) {
                        return r4.A00;
                    }
                }
                continue;
            }
        }
        return null;
    }

    @Override // X.AbstractC06260mR
    public final List<AbstractC02410Zn> A0M() {
        return this.A07;
    }

    @Override // X.AbstractC06260mR
    public final List<AnonymousClass0EJ> A0N() {
        C02460Zs r1 = this.A0A;
        if (!r1.A06) {
            C02460Zs.A06(r1);
        }
        return r1.A03;
    }

    @Override // X.AbstractC06260mR
    public final List<AnonymousClass0EC> A0O() {
        C02460Zs r1 = this.A0A;
        if (!r1.A06) {
            C02460Zs.A06(r1);
        }
        List<AnonymousClass0EC> list = r1.A04;
        if (list.isEmpty()) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (AnonymousClass0EC r12 : list) {
            if (A02(r12)) {
                arrayList.add(r12);
            }
        }
        return arrayList;
    }

    @Override // X.AbstractC06260mR
    public final Map<String, AbstractC02450Zr> A0P() {
        C06250mQ A042;
        HashMap hashMap = null;
        for (AbstractC02410Zn r0 : this.A07) {
            AbstractC02450Zr A0A2 = r0.A0A();
            if (!(A0A2 == null || (A042 = this.A08.A04(A0A2)) == null || A042.A00 != AnonymousClass007.A01)) {
                if (hashMap == null) {
                    hashMap = new HashMap();
                }
                String str = A042.A01;
                if (hashMap.put(str, A0A2) != null) {
                    throw new IllegalArgumentException(AnonymousClass006.A07("Multiple back-reference properties with name '", str, "'"));
                }
            }
        }
        return hashMap;
    }

    @Override // X.AbstractC06260mR
    public final Map<Object, AbstractC02450Zr> A0Q() {
        return this.A05;
    }

    @Override // X.AbstractC06260mR
    public final Set<String> A0R() {
        Set<String> set = this.A06;
        if (set == null) {
            return Collections.emptySet();
        }
        return set;
    }

    @Override // X.AbstractC06260mR
    public final boolean A0S() {
        C02460Zs r1 = this.A0A;
        if (r1.A02 == null) {
            C02460Zs.A05(r1);
        }
        HashMap<Class<? extends Annotation>, Annotation> hashMap = r1.A02.A00;
        if (hashMap == null || hashMap.size() <= 0) {
            return false;
        }
        return true;
    }

    public C02430Zp(AnonymousClass0a7<?> r2, AnonymousClass0aI r3, C02460Zs r4, List<AbstractC02410Zn> list) {
        super(r3);
        AbstractC02590aM A012;
        this.A09 = r2;
        if (r2 == null) {
            A012 = null;
        } else {
            A012 = r2.A01();
        }
        this.A08 = A012;
        this.A0A = r4;
        this.A07 = list;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C02430Zp(X.C06730nl r6) {
        /*
            r5 = this;
            X.0a7<?> r4 = r6.A08
            X.0aI r3 = r6.A07
            X.0Zs r2 = r6.A09
            java.util.LinkedHashMap<java.lang.String, X.0K7> r0 = r6.A0C
            java.util.Collection r1 = r0.values()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>(r1)
            r5.<init>(r4, r3, r2, r0)
            X.0aM r1 = r6.A06
            if (r1 != 0) goto L_0x001c
            r0 = 0
        L_0x0019:
            r5.A03 = r0
            return
        L_0x001c:
            X.0nk r0 = r1.A0A(r2)
            if (r0 == 0) goto L_0x0019
            X.0nk r0 = r1.A0B(r2, r0)
            goto L_0x0019
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C02430Zp.<init>(X.0nl):void");
    }
}
