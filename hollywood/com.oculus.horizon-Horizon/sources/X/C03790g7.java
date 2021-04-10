package X;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: X.0g7  reason: invalid class name and case insensitive filesystem */
public final class C03790g7 extends AbstractC05180kU {
    public C05770lp A00;
    public AnonymousClass07O A01;
    public AnonymousClass07O A02;
    public C06230mk A03;
    public Map<Object, AnonymousClass0g9> A04;
    public Set<String> A05;
    public final AbstractC03910gQ<?> A06;
    public final AbstractC04040gi A07;
    public final C03810gA A08;
    public final List<AnonymousClass0g5> A09;

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00aa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static X.C03790g7 A00(X.C05780lq r6) {
        /*
        // Method dump skipped, instructions count: 181
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C03790g7.A00(X.0lq):X.0g7");
    }

    private final boolean A01(AnonymousClass07O r5) {
        if (!super.A00._class.isAssignableFrom(r5.A00.getReturnType()) || (!this.A07.A0b(r5) && !"valueOf".equals(r5.A0K()))) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC05180kU
    public final C04690j1 A02(C04690j1 r3) {
        C04690j1 A012;
        AbstractC04040gi r1 = this.A07;
        if (r1 == null || (A012 = r1.A01(this.A08)) == null) {
            return r3;
        }
        return A012;
    }

    @Override // X.AbstractC05180kU
    public final AbstractC04000gb A03(Type type) {
        if (type == null) {
            return null;
        }
        C06230mk A0B = A0B();
        return A0B.A03.A09(type, A0B);
    }

    @Override // X.AbstractC05180kU
    public final C05310km A04() {
        AbstractC04040gi r1 = this.A07;
        if (r1 == null) {
            return null;
        }
        return r1.A06(this.A08);
    }

    @Override // X.AbstractC05180kU
    public final AnonymousClass07P A06() {
        C03810gA r1 = this.A08;
        if (!r1.A06) {
            C03810gA.A06(r1);
        }
        return r1.A00;
    }

    @Override // X.AbstractC05180kU
    public final AnonymousClass07O A07() throws IllegalArgumentException {
        Class A0X;
        AnonymousClass07O r0 = this.A01;
        if (r0 == null || (A0X = r0.A0X()) == String.class || A0X == Object.class) {
            return this.A01;
        }
        throw new IllegalArgumentException(AnonymousClass006.A08("Invalid 'any-setter' annotation on method ", this.A01.A0K(), "(): first argument not of type String or Object, but ", A0X.getName()));
    }

    @Override // X.AbstractC05180kU
    public final AnonymousClass07O A09(String str, Class<?>[] clsArr) {
        C03810gA r1 = this.A08;
        if (r1.A01 == null) {
            C03810gA.A07(r1);
        }
        LinkedHashMap<C05760lo, AnonymousClass07O> linkedHashMap = r1.A01.A00;
        if (linkedHashMap == null) {
            return null;
        }
        return linkedHashMap.get(new C05760lo(str, clsArr));
    }

    @Override // X.AbstractC05180kU
    public final C06230mk A0B() {
        C06230mk r4 = this.A03;
        if (r4 != null) {
            return r4;
        }
        C06240ml r3 = this.A06._base._typeFactory;
        AbstractC04000gb r2 = super.A00;
        C06230mk r42 = new C06230mk(r3, null, r2._class, r2);
        this.A03 = r42;
        return r42;
    }

    @Override // X.AbstractC05180kU
    public final AbstractC06280mp A0C() {
        C03810gA r1 = this.A08;
        if (r1.A02 == null) {
            C03810gA.A05(r1);
        }
        return r1.A02;
    }

    @Override // X.AbstractC05180kU
    public final AbstractC06340mv<Object, Object> A0D() {
        Object A0N;
        StringBuilder sb;
        String str;
        AbstractC04040gi r1 = this.A07;
        if (!(r1 == null || (A0N = r1.A0N(this.A08)) == null)) {
            if (A0N instanceof Class) {
                Class cls = (Class) A0N;
                if (!(cls == AnonymousClass0fm.class || cls == C05340ku.class)) {
                    if (AbstractC06340mv.class.isAssignableFrom(cls)) {
                        C06330mu.A02(cls, this.A06.A05(EnumC03960gV.CAN_OVERRIDE_ACCESS_MODIFIERS));
                    } else {
                        sb = new StringBuilder("AnnotationIntrospector returned Class ");
                        sb.append(cls.getName());
                        str = "; expected Class<Converter>";
                    }
                }
            } else {
                sb = new StringBuilder("AnnotationIntrospector returned Converter definition of type ");
                sb.append(A0N.getClass().getName());
                str = "; expected type Converter or Class<Converter> instead";
            }
            sb.append(str);
            throw new IllegalStateException(sb.toString());
        }
        return null;
    }

    @Override // X.AbstractC05180kU
    public final Class<?> A0E() {
        AbstractC04040gi r1 = this.A07;
        if (r1 == null) {
            return null;
        }
        return r1.A0L(this.A08);
    }

    @Override // X.AbstractC05180kU
    public final Constructor<?> A0F(Class<?>... clsArr) {
        Class<?> cls;
        C03810gA r1 = this.A08;
        if (!r1.A06) {
            C03810gA.A06(r1);
        }
        for (AnonymousClass07P r4 : r1.A03) {
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

    @Override // X.AbstractC05180kU
    public final Method A0G(Class<?>... clsArr) {
        C03810gA r1 = this.A08;
        if (!r1.A06) {
            C03810gA.A06(r1);
        }
        for (AnonymousClass07O r4 : r1.A04) {
            if (A01(r4)) {
                Class A0X = r4.A0X();
                for (Class<?> cls : clsArr) {
                    if (A0X.isAssignableFrom(cls)) {
                        return r4.A00;
                    }
                }
                continue;
            }
        }
        return null;
    }

    @Override // X.AbstractC05180kU
    public final List<AnonymousClass07P> A0I() {
        C03810gA r1 = this.A08;
        if (!r1.A06) {
            C03810gA.A06(r1);
        }
        return r1.A03;
    }

    @Override // X.AbstractC05180kU
    public final List<AnonymousClass07O> A0J() {
        C03810gA r1 = this.A08;
        if (!r1.A06) {
            C03810gA.A06(r1);
        }
        List<AnonymousClass07O> list = r1.A04;
        if (list.isEmpty()) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (AnonymousClass07O r12 : list) {
            if (A01(r12)) {
                arrayList.add(r12);
            }
        }
        return arrayList;
    }

    @Override // X.AbstractC05180kU
    public final Map<String, AnonymousClass0g9> A0K() {
        C05170kT A032;
        HashMap hashMap = null;
        for (AnonymousClass0g5 r0 : this.A09) {
            AnonymousClass0g9 A092 = r0.A09();
            if (!(A092 == null || (A032 = this.A07.A03(A092)) == null || A032.A00 != AnonymousClass007.A01)) {
                if (hashMap == null) {
                    hashMap = new HashMap();
                }
                String str = A032.A01;
                if (hashMap.put(str, A092) != null) {
                    throw new IllegalArgumentException(AnonymousClass006.A07("Multiple back-reference properties with name '", str, "'"));
                }
            }
        }
        return hashMap;
    }

    @Override // X.AbstractC05180kU
    public final Set<String> A0M() {
        Set<String> set = this.A05;
        if (set == null) {
            return Collections.emptySet();
        }
        return set;
    }

    public C03790g7(AbstractC03910gQ<?> r2, AbstractC04000gb r3, C03810gA r4, List<AnonymousClass0g5> list) {
        super(r3);
        AbstractC04040gi A012;
        this.A06 = r2;
        if (r2 == null) {
            A012 = null;
        } else {
            A012 = r2.A01();
        }
        this.A07 = A012;
        this.A08 = r4;
        this.A09 = list;
    }

    @Override // X.AbstractC05180kU
    public final C03810gA A05() {
        return this.A08;
    }

    @Override // X.AbstractC05180kU
    public final AnonymousClass07O A08() {
        return this.A02;
    }

    @Override // X.AbstractC05180kU
    public final C05770lp A0A() {
        return this.A00;
    }

    @Override // X.AbstractC05180kU
    public final List<AnonymousClass0g5> A0H() {
        return this.A09;
    }

    @Override // X.AbstractC05180kU
    public final Map<Object, AnonymousClass0g9> A0L() {
        return this.A04;
    }
}
