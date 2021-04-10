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

/* renamed from: X.0hk  reason: invalid class name and case insensitive filesystem */
public final class C01980hk extends AbstractC04010oz {
    public AbstractC01990hm A00;
    public AnonymousClass0Cr A01;
    public AnonymousClass0Cr A02;
    public C04480qJ A03;
    public AnonymousClass0r8 A04;
    public Map<Object, AbstractC01990hm> A05;
    public Set<String> A06;
    public final List<AbstractC01960hi> A07;
    public final AbstractC02230iJ A08;
    public final AbstractC02110i2<?> A09;
    public final C02000hn A0A;

    private final AnonymousClass0rJ<Object, Object> A01(Object obj) {
        if (obj != null) {
            if (obj instanceof Class) {
                Class cls = (Class) obj;
                if (!(cls == AbstractC01760gy.class || cls == C04130pP.class)) {
                    if (AnonymousClass0rJ.class.isAssignableFrom(cls)) {
                        C04810rI.A02(cls, this.A09.A05(EnumC02160i9.CAN_OVERRIDE_ACCESS_MODIFIERS));
                    } else {
                        throw new IllegalStateException(AnonymousClass006.A09("AnnotationIntrospector returned Class ", cls.getName(), "; expected Class<Converter>"));
                    }
                }
            } else {
                throw new IllegalStateException(AnonymousClass006.A09("AnnotationIntrospector returned Converter definition of type ", obj.getClass().getName(), "; expected type Converter or Class<Converter> instead"));
            }
        }
        return null;
    }

    public static C01980hk A00(AnonymousClass0qK r4) {
        AnonymousClass0Cr r0;
        C01980hk r1 = new C01980hk(r4);
        LinkedList<AnonymousClass0Cr> linkedList = r4.A03;
        if (linkedList == null) {
            r0 = null;
        } else if (linkedList.size() > 1) {
            StringBuilder sb = new StringBuilder("Multiple 'any-setters' defined (");
            sb.append(linkedList.get(0));
            sb.append(" vs ");
            sb.append(r4.A03.get(1));
            sb.append(")");
            AnonymousClass0qK.A04(r4, sb.toString());
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

    private final boolean A02(AnonymousClass0Cr r5) {
        if (!super.A00._class.isAssignableFrom(r5.A00.getReturnType()) || (!this.A08.A0o(r5) && !"valueOf".equals(r5.A0L()))) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC04010oz
    public final C03550nX A03(C03550nX r3) {
        C03550nX A012;
        AbstractC02230iJ r1 = this.A08;
        if (r1 == null || (A012 = r1.A01(this.A0A)) == null) {
            return r3;
        }
        return A012;
    }

    @Override // X.AbstractC04010oz
    public final EnumC03560nf A04(EnumC03560nf r3) {
        AbstractC02230iJ r1 = this.A08;
        if (r1 != null) {
            return r1.A03(this.A0A, r3);
        }
        return r3;
    }

    @Override // X.AbstractC04010oz
    public final AbstractC02190iF A05(Type type) {
        if (type == null) {
            return null;
        }
        AnonymousClass0r8 A0E = A0E();
        return A0E.A03.A09(type, A0E);
    }

    @Override // X.AbstractC04010oz
    public final C04100pH A06() {
        AbstractC02230iJ r1 = this.A08;
        if (r1 == null) {
            return null;
        }
        return r1.A08(this.A0A);
    }

    @Override // X.AbstractC04010oz
    public final AnonymousClass0Cs A08() {
        C02000hn r1 = this.A0A;
        if (!r1.A06) {
            C02000hn.A06(r1);
        }
        return r1.A00;
    }

    @Override // X.AbstractC04010oz
    public final AbstractC01990hm A09() throws IllegalArgumentException {
        AbstractC01990hm r0 = this.A00;
        if (r0 == null || Map.class.isAssignableFrom(r0.A0K())) {
            return this.A00;
        }
        throw new IllegalArgumentException(AnonymousClass006.A09("Invalid 'any-getter' annotation on method ", this.A00.A0L(), "(): return type is not instance of java.util.Map"));
    }

    @Override // X.AbstractC04010oz
    public final AnonymousClass0Cr A0A() throws IllegalArgumentException {
        Class A0a;
        AnonymousClass0Cr r0 = this.A02;
        if (r0 == null || (A0a = r0.A0a()) == String.class || A0a == Object.class) {
            return this.A02;
        }
        throw new IllegalArgumentException(AnonymousClass006.A0B("Invalid 'any-setter' annotation on method ", this.A02.A0L(), "(): first argument not of type String or Object, but ", A0a.getName()));
    }

    @Override // X.AbstractC04010oz
    public final AnonymousClass0Cr A0C(String str, Class<?>[] clsArr) {
        C02000hn r1 = this.A0A;
        if (r1.A01 == null) {
            C02000hn.A07(r1);
        }
        LinkedHashMap<C04470qI, AnonymousClass0Cr> linkedHashMap = r1.A01.A00;
        if (linkedHashMap == null) {
            return null;
        }
        return linkedHashMap.get(new C04470qI(str, clsArr));
    }

    @Override // X.AbstractC04010oz
    public final AnonymousClass0r8 A0E() {
        AnonymousClass0r8 r4 = this.A04;
        if (r4 != null) {
            return r4;
        }
        AnonymousClass0r9 r3 = this.A09._base._typeFactory;
        AbstractC02190iF r2 = super.A00;
        AnonymousClass0r8 r42 = new AnonymousClass0r8(r3, null, r2._class, r2);
        this.A04 = r42;
        return r42;
    }

    @Override // X.AbstractC04010oz
    public final AbstractC04760rD A0F() {
        C02000hn r1 = this.A0A;
        if (r1.A02 == null) {
            C02000hn.A05(r1);
        }
        return r1.A02;
    }

    @Override // X.AbstractC04010oz
    public final AnonymousClass0rJ<Object, Object> A0G() {
        AbstractC02230iJ r1 = this.A08;
        if (r1 == null) {
            return null;
        }
        return A01(r1.A0V(this.A0A));
    }

    @Override // X.AbstractC04010oz
    public final AnonymousClass0rJ<Object, Object> A0H() {
        AbstractC02230iJ r1 = this.A08;
        if (r1 == null) {
            return null;
        }
        return A01(r1.A0Z(this.A0A));
    }

    @Override // X.AbstractC04010oz
    public final Class<?> A0I() {
        AbstractC02230iJ r1 = this.A08;
        if (r1 == null) {
            return null;
        }
        return r1.A0S(this.A0A);
    }

    @Override // X.AbstractC04010oz
    public final Object A0J(boolean z) {
        C02000hn r3 = this.A0A;
        if (!r3.A06) {
            C02000hn.A06(r3);
        }
        AnonymousClass0Cs r1 = r3.A00;
        if (r1 == null) {
            return null;
        }
        if (z) {
            C04810rI.A06(r1.A0R());
        }
        try {
            return r1._constructor.newInstance(new Object[0]);
        } catch (Exception e) {
            e = e;
            while (e.getCause() != null) {
                e = e.getCause();
            }
            if (e instanceof Error) {
                throw e;
            } else if (e instanceof RuntimeException) {
                throw e;
            } else {
                throw new IllegalArgumentException(AnonymousClass006.A0D("Failed to instantiate bean of type ", r3.A09.getName(), ": (", e.getClass().getName(), ") ", e.getMessage()), e);
            }
        }
    }

    @Override // X.AbstractC04010oz
    public final Constructor<?> A0K(Class<?>... clsArr) {
        Class<?> cls;
        C02000hn r1 = this.A0A;
        if (!r1.A06) {
            C02000hn.A06(r1);
        }
        for (AnonymousClass0Cs r4 : r1.A03) {
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

    @Override // X.AbstractC04010oz
    public final Method A0L(Class<?>... clsArr) {
        C02000hn r1 = this.A0A;
        if (!r1.A06) {
            C02000hn.A06(r1);
        }
        for (AnonymousClass0Cr r4 : r1.A04) {
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

    @Override // X.AbstractC04010oz
    public final List<AnonymousClass0Cs> A0N() {
        C02000hn r1 = this.A0A;
        if (!r1.A06) {
            C02000hn.A06(r1);
        }
        return r1.A03;
    }

    @Override // X.AbstractC04010oz
    public final List<AnonymousClass0Cr> A0O() {
        C02000hn r1 = this.A0A;
        if (!r1.A06) {
            C02000hn.A06(r1);
        }
        List<AnonymousClass0Cr> list = r1.A04;
        if (list.isEmpty()) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (AnonymousClass0Cr r12 : list) {
            if (A02(r12)) {
                arrayList.add(r12);
            }
        }
        return arrayList;
    }

    @Override // X.AbstractC04010oz
    public final Map<String, AbstractC01990hm> A0P() {
        C04000oy A042;
        HashMap hashMap = null;
        for (AbstractC01960hi r0 : this.A07) {
            AbstractC01990hm A0A2 = r0.A0A();
            if (!(A0A2 == null || (A042 = this.A08.A04(A0A2)) == null || A042.A00 != AnonymousClass007.A01)) {
                if (hashMap == null) {
                    hashMap = new HashMap();
                }
                String str = A042.A01;
                if (hashMap.put(str, A0A2) != null) {
                    throw new IllegalArgumentException(AnonymousClass006.A09("Multiple back-reference properties with name '", str, "'"));
                }
            }
        }
        return hashMap;
    }

    @Override // X.AbstractC04010oz
    public final Set<String> A0R() {
        Set<String> set = this.A06;
        if (set == null) {
            return Collections.emptySet();
        }
        return set;
    }

    @Override // X.AbstractC04010oz
    public final boolean A0S() {
        C02000hn r1 = this.A0A;
        if (r1.A02 == null) {
            C02000hn.A05(r1);
        }
        HashMap<Class<? extends Annotation>, Annotation> hashMap = r1.A02.A00;
        if (hashMap == null || hashMap.size() <= 0) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC04010oz
    public final C02000hn A07() {
        return this.A0A;
    }

    @Override // X.AbstractC04010oz
    public final AnonymousClass0Cr A0B() {
        return this.A01;
    }

    @Override // X.AbstractC04010oz
    public final C04480qJ A0D() {
        return this.A03;
    }

    @Override // X.AbstractC04010oz
    public final List<AbstractC01960hi> A0M() {
        return this.A07;
    }

    @Override // X.AbstractC04010oz
    public final Map<Object, AbstractC01990hm> A0Q() {
        return this.A05;
    }

    public C01980hk(AbstractC02110i2<?> r2, AbstractC02190iF r3, C02000hn r4, List<AbstractC01960hi> list) {
        super(r3);
        AbstractC02230iJ A012;
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
    public C01980hk(X.AnonymousClass0qK r6) {
        /*
            r5 = this;
            X.0i2<?> r4 = r6.A08
            X.0iF r3 = r6.A07
            X.0hn r2 = r6.A09
            java.util.LinkedHashMap<java.lang.String, X.0Ot> r0 = r6.A0C
            java.util.Collection r1 = r0.values()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>(r1)
            r5.<init>(r4, r3, r2, r0)
            X.0iJ r1 = r6.A06
            if (r1 != 0) goto L_0x001c
            r0 = 0
        L_0x0019:
            r5.A03 = r0
            return
        L_0x001c:
            X.0qJ r0 = r1.A0A(r2)
            if (r0 == 0) goto L_0x0019
            X.0qJ r0 = r1.A0B(r2, r0)
            goto L_0x0019
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C01980hk.<init>(X.0qK):void");
    }
}
