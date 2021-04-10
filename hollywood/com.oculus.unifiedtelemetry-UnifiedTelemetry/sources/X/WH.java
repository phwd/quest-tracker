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

public final class WH extends jm {
    public VN A00;
    public AnonymousClass7P A01;
    public AnonymousClass7P A02;
    public O4 A03;
    public Map<Object, WJ> A04;
    public Set<String> A05;
    public final WZ<?> A06;
    public final Wp A07;
    public final WK A08;
    public final List<WF> A09;

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00aa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static X.WH A00(X.VM r6) {
        /*
        // Method dump skipped, instructions count: 181
        */
        throw new UnsupportedOperationException("Method not decompiled: X.WH.A00(X.VM):X.WH");
    }

    private final boolean A01(AnonymousClass7P r5) {
        if (!super.A00._class.isAssignableFrom(r5.A00.getReturnType()) || (!this.A07.A0c(r5) && !"valueOf".equals(r5.A0K()))) {
            return false;
        }
        return true;
    }

    @Override // X.jm
    public final pN A02(pN pNVar) {
        pN A012;
        Wp wp = this.A07;
        if (wp == null || (A012 = wp.A01(this.A08)) == null) {
            return pNVar;
        }
        return A012;
    }

    @Override // X.jm
    public final AbstractC0224Wl A03(Type type) {
        if (type == null) {
            return null;
        }
        O4 A0B = A0B();
        return A0B.A03.A09(type, A0B);
    }

    @Override // X.jm
    public final C0410hE A04() {
        Wp wp = this.A07;
        if (wp == null) {
            return null;
        }
        return wp.A07(this.A08);
    }

    @Override // X.jm
    public final AnonymousClass7Q A06() {
        WK wk = this.A08;
        if (!wk.A06) {
            WK.A06(wk);
        }
        return wk.A00;
    }

    @Override // X.jm
    public final AnonymousClass7P A07() throws IllegalArgumentException {
        Class A0X;
        AnonymousClass7P r0 = this.A01;
        if (r0 == null || (A0X = r0.A0X()) == String.class || A0X == Object.class) {
            return this.A01;
        }
        throw new IllegalArgumentException(AnonymousClass06.A06("Invalid 'any-setter' annotation on method ", this.A01.A0K(), "(): first argument not of type String or Object, but ", A0X.getName()));
    }

    @Override // X.jm
    public final AnonymousClass7P A09(String str, Class<?>[] clsArr) {
        WK wk = this.A08;
        if (wk.A01 == null) {
            WK.A07(wk);
        }
        LinkedHashMap<VO, AnonymousClass7P> linkedHashMap = wk.A01.A00;
        if (linkedHashMap == null) {
            return null;
        }
        return linkedHashMap.get(new VO(str, clsArr));
    }

    @Override // X.jm
    public final O4 A0B() {
        O4 o4 = this.A03;
        if (o4 != null) {
            return o4;
        }
        NT nt = this.A06._base._typeFactory;
        AbstractC0224Wl wl = super.A00;
        O4 o42 = new O4(nt, null, wl._class, wl);
        this.A03 = o42;
        return o42;
    }

    @Override // X.jm
    public final N6 A0C() {
        WK wk = this.A08;
        if (wk.A02 == null) {
            WK.A05(wk);
        }
        return wk.A02;
    }

    @Override // X.jm
    public final MH<Object, Object> A0D() {
        Object A0O;
        StringBuilder sb;
        String str;
        Wp wp = this.A07;
        if (!(wp == null || (A0O = wp.A0O(this.A08)) == null)) {
            if (A0O instanceof Class) {
                Class cls = (Class) A0O;
                if (!(cls == AbstractC0213Vx.class || cls == dY.class)) {
                    if (MH.class.isAssignableFrom(cls)) {
                        Mv.A02(cls, this.A06.A05(EnumC0220Wf.CAN_OVERRIDE_ACCESS_MODIFIERS));
                    } else {
                        sb = new StringBuilder("AnnotationIntrospector returned Class ");
                        sb.append(cls.getName());
                        str = "; expected Class<Converter>";
                    }
                }
            } else {
                sb = new StringBuilder("AnnotationIntrospector returned Converter definition of type ");
                sb.append(A0O.getClass().getName());
                str = "; expected type Converter or Class<Converter> instead";
            }
            sb.append(str);
            throw new IllegalStateException(sb.toString());
        }
        return null;
    }

    @Override // X.jm
    public final Class<?> A0E() {
        Wp wp = this.A07;
        if (wp == null) {
            return null;
        }
        return wp.A0M(this.A08);
    }

    @Override // X.jm
    public final Constructor<?> A0F(Class<?>... clsArr) {
        Class<?> cls;
        WK wk = this.A08;
        if (!wk.A06) {
            WK.A06(wk);
        }
        for (AnonymousClass7Q r4 : wk.A03) {
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

    @Override // X.jm
    public final Method A0G(Class<?>... clsArr) {
        WK wk = this.A08;
        if (!wk.A06) {
            WK.A06(wk);
        }
        for (AnonymousClass7P r4 : wk.A04) {
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

    @Override // X.jm
    public final List<AnonymousClass7Q> A0I() {
        WK wk = this.A08;
        if (!wk.A06) {
            WK.A06(wk);
        }
        return wk.A03;
    }

    @Override // X.jm
    public final List<AnonymousClass7P> A0J() {
        WK wk = this.A08;
        if (!wk.A06) {
            WK.A06(wk);
        }
        List<AnonymousClass7P> list = wk.A04;
        if (list.isEmpty()) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (AnonymousClass7P r1 : list) {
            if (A01(r1)) {
                arrayList.add(r1);
            }
        }
        return arrayList;
    }

    @Override // X.jm
    public final Map<String, WJ> A0K() {
        jn A032;
        HashMap hashMap = null;
        for (WF wf : this.A09) {
            WJ A092 = wf.A09();
            if (!(A092 == null || (A032 = this.A07.A03(A092)) == null || A032.A00 != AnonymousClass07.A01)) {
                if (hashMap == null) {
                    hashMap = new HashMap();
                }
                String str = A032.A01;
                if (hashMap.put(str, A092) != null) {
                    throw new IllegalArgumentException(AnonymousClass06.A05("Multiple back-reference properties with name '", str, "'"));
                }
            }
        }
        return hashMap;
    }

    @Override // X.jm
    public final Set<String> A0M() {
        Set<String> set = this.A05;
        if (set == null) {
            return Collections.emptySet();
        }
        return set;
    }

    public WH(WZ<?> wz, AbstractC0224Wl wl, WK wk, List<WF> list) {
        super(wl);
        Wp A012;
        this.A06 = wz;
        if (wz == null) {
            A012 = null;
        } else {
            A012 = wz.A01();
        }
        this.A07 = A012;
        this.A08 = wk;
        this.A09 = list;
    }

    @Override // X.jm
    public final WK A05() {
        return this.A08;
    }

    @Override // X.jm
    public final AnonymousClass7P A08() {
        return this.A02;
    }

    @Override // X.jm
    public final VN A0A() {
        return this.A00;
    }

    @Override // X.jm
    public final List<WF> A0H() {
        return this.A09;
    }

    @Override // X.jm
    public final Map<Object, WJ> A0L() {
        return this.A04;
    }
}
