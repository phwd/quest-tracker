package X;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.inject.AssistedProvider;
import com.facebook.inject.binder.AnnotatedBindingBuilder;
import com.facebook.inject.binder.LinkedBindingBuilder;
import com.facebook.inject.binder.LinkedComponentBindingBuilder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableMap;
import com.google.common.collect.RegularImmutableSet;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@Nullsafe(trustOnly = @Nullsafe.TrustList({}), value = Nullsafe.Mode.LOCAL)
/* renamed from: X.0bO  reason: invalid class name */
public final class AnonymousClass0bO implements AnonymousClass0Ql {
    @Nullable
    public List<AnonymousClass0Qq> A00;
    @Nullable
    public Set<Class<?>> A01;
    @Nullable
    public List<AnonymousClass0Qm> A02;
    @Nullable
    public List<Class<?>> A03;
    @Nullable
    public Map<C01440Gz, AnonymousClass0RM> A04;
    @Nullable
    public Map<Class<? extends Annotation>, AnonymousClass0RX> A05;
    @Nullable
    public Set<C01440Gz> A06;
    @Nullable
    public Set<C01440Gz> A07;
    public final Class A08;
    public final AnonymousClass0Lh A09;

    private <T> AnonymousClass0Qm<T> A00(C01440Gz<T> r4) {
        List list = this.A02;
        if (list == null) {
            list = new ArrayList();
            this.A02 = list;
        }
        AnonymousClass0Qm<T> r1 = new AnonymousClass0Qm<>();
        r1.A02 = this.A08;
        r1.A01 = r4;
        list.add(r1);
        return r1;
    }

    private <T> AnonymousClass0RM<T> A01(C01440Gz<T> r3) {
        Map map = this.A04;
        if (map == null) {
            map = new HashMap();
            this.A04 = map;
        }
        AnonymousClass0RM<T> r1 = (AnonymousClass0RM) map.get(r3);
        if (r1 != null) {
            return r1;
        }
        AnonymousClass0RM<T> r12 = new AnonymousClass0RM<>(r3);
        this.A04.put(r3, r12);
        return r12;
    }

    @Override // X.AnonymousClass0Ql
    public final <T> void A1C(C01440Gz<T> r2) {
        Set set = this.A06;
        if (set == null) {
            set = new HashSet();
            this.A06 = set;
        }
        set.add(r2);
    }

    @Override // X.AnonymousClass0Ql
    public final <T> void A1D(Class<T> cls) {
        Set set = this.A06;
        if (set == null) {
            set = new HashSet();
            this.A06 = set;
        }
        set.add(new C01440Gz(cls, AnonymousClass0Vx.INSTANCE));
    }

    @Override // X.AnonymousClass0Ql
    public final <T> AnnotatedBindingBuilder<T> A1J(Class<T> cls) {
        return new C01320Ge(A00(new C01440Gz<>(cls, AnonymousClass0Vx.INSTANCE)));
    }

    @Override // X.AnonymousClass0Ql
    public final <T> LinkedComponentBindingBuilder<T> A1M(Class<T> cls) {
        C01440Gz<T> r3 = new C01440Gz<>(cls, AnonymousClass0Vx.INSTANCE);
        List list = this.A00;
        if (list == null) {
            list = new ArrayList();
            this.A00 = list;
        }
        AnonymousClass0Qq r1 = new AnonymousClass0Qq();
        r1.A01 = this.A08;
        r1.A00 = r3;
        list.add(r1);
        return new C02960bD(r1);
    }

    @Override // X.AnonymousClass0Ql
    public final <T> AnnotatedBindingBuilder<T> A1O(Class<T> cls) {
        AnonymousClass0Qm<T> A002 = A00(new C01440Gz<>(cls, AnonymousClass0Vx.INSTANCE));
        A002.A00 = (byte) (1 | A002.A00);
        return new C01320Ge(A002);
    }

    @Override // X.AnonymousClass0Ql
    public final <T> AnonymousClass0RM<T> A1S(Class<T> cls) {
        return A01(new C01440Gz<>(cls, AnonymousClass0Vx.INSTANCE));
    }

    @Override // X.AnonymousClass0Ql
    public final void A1V(Class<? extends Annotation> cls, AnonymousClass0RX r3) {
        Map map = this.A05;
        if (map == null) {
            map = new HashMap();
            this.A05 = map;
        }
        map.put(cls, r3);
    }

    @Override // X.AnonymousClass0Ql
    public final void A22(C01440Gz<?> r2) {
        Set set = this.A07;
        if (set == null) {
            set = new HashSet();
            this.A07 = set;
        }
        set.add(r2);
    }

    @Override // X.AnonymousClass0Ql
    public final void A23(Class<?> cls) {
        A22(new C01440Gz<>(cls, AnonymousClass0Vx.INSTANCE));
    }

    @Override // X.AnonymousClass0Ql
    public final List<AnonymousClass0Qq> A3B() {
        List<AnonymousClass0Qq> list = this.A00;
        if (list == null) {
            return ImmutableList.of();
        }
        return list;
    }

    @Override // X.AnonymousClass0Ql
    public final AnonymousClass0R8 A3l() {
        return this.A09;
    }

    @Override // X.AnonymousClass0Ql
    public final Set<C01440Gz> A47() {
        Set<C01440Gz> set = this.A07;
        if (set == null) {
            return RegularImmutableSet.A05;
        }
        return set;
    }

    @Override // X.AnonymousClass0Ql
    public final Map<C01440Gz, AnonymousClass0RM> A48() {
        Map<C01440Gz, AnonymousClass0RM> map = this.A04;
        if (map == null) {
            return RegularImmutableMap.A03;
        }
        return map;
    }

    @Override // X.AnonymousClass0Ql
    public final List<Class<?>> A4Q() {
        List<Class<?>> list = this.A03;
        if (list == null) {
            return ImmutableList.of();
        }
        return list;
    }

    @Override // X.AnonymousClass0Ql
    public final Map<Class<? extends Annotation>, AnonymousClass0RX> A4S() {
        Map<Class<? extends Annotation>, AnonymousClass0RX> map = this.A05;
        if (map == null) {
            return RegularImmutableMap.A03;
        }
        return map;
    }

    @Override // X.AnonymousClass0Ql
    public final void A7S(Class<?> cls) {
        List list = this.A03;
        if (list == null) {
            list = new ArrayList();
            this.A03 = list;
        }
        list.add(cls);
        Set set = this.A01;
        if (set == null) {
            set = new HashSet();
            this.A01 = set;
        }
        set.add(cls);
    }

    public AnonymousClass0bO(AnonymousClass0Lh r1, Class cls) {
        this.A09 = r1;
        this.A08 = cls;
    }

    @Override // X.AnonymousClass0Ql
    public final <T> LinkedBindingBuilder<T> A1I(C01440Gz<T> r3) {
        return new C01320Ge(A00(r3));
    }

    @Override // X.AnonymousClass0Ql
    public final <T> void A1K(Class<? extends AssistedProvider<T>> cls) {
        A1J(cls).A00.A05 = new C01350Gi(cls);
    }

    @Override // X.AnonymousClass0Ql
    public final <T> LinkedBindingBuilder<T> A1N(C01440Gz<T> r4) {
        AnonymousClass0Qm<T> A002 = A00(r4);
        A002.A00 = (byte) (1 | A002.A00);
        return new C01320Ge(A002);
    }

    @Override // X.AnonymousClass0Ql
    public final <T> AnonymousClass0RM<T> A1R(C01440Gz<T> r2) {
        return A01(r2);
    }

    @Override // X.AnonymousClass0Ql
    public final <T> AnonymousClass0RM<T> A1T(Class<T> cls, Class<? extends Annotation> cls2) {
        return A01(C01440Gz.A01(cls, cls2));
    }

    @Override // X.AnonymousClass0Ql
    public final void A24(Class<?> cls, Class<? extends Annotation> cls2) {
        A22(C01440Gz.A01(cls, cls2));
    }

    @Override // X.AnonymousClass0Ql
    public final List<AnonymousClass0Qm> A35() {
        ImmutableList.Builder builder = ImmutableList.builder();
        List<AnonymousClass0Qm> list = this.A02;
        if (list != null) {
            HashSet hashSet = new HashSet(list.size());
            for (AnonymousClass0Qm r2 : this.A02) {
                Integer valueOf = Integer.valueOf(AnonymousClass162.A00(r2.A01));
                if (!hashSet.contains(valueOf)) {
                    hashSet.add(valueOf);
                    builder.add((Object) r2);
                }
            }
        }
        return builder.build();
    }
}
