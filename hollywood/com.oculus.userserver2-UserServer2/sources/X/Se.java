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
public final class Se implements OQ {
    @Nullable
    public List<OV> A00;
    @Nullable
    public Set<Class<?>> A01;
    @Nullable
    public List<OR> A02;
    @Nullable
    public List<Class<?>> A03;
    @Nullable
    public Map<gz, P1> A04;
    @Nullable
    public Map<Class<? extends Annotation>, PC> A05;
    @Nullable
    public Set<gz> A06;
    @Nullable
    public Set<gz> A07;
    public final Class A08;
    public final BZ A09;

    private <T> OR<T> A00(gz<T> gzVar) {
        List list = this.A02;
        if (list == null) {
            list = new ArrayList();
            this.A02 = list;
        }
        OR<T> or = new OR<>();
        or.A02 = this.A08;
        or.A01 = gzVar;
        list.add(or);
        return or;
    }

    private <T> P1<T> A01(gz<T> gzVar) {
        Map map = this.A04;
        if (map == null) {
            map = new HashMap();
            this.A04 = map;
        }
        P1<T> p1 = (P1) map.get(gzVar);
        if (p1 != null) {
            return p1;
        }
        P1<T> p12 = new P1<>(gzVar);
        this.A04.put(gzVar, p12);
        return p12;
    }

    @Override // X.OQ
    public final <T> void A0r(gz<T> gzVar) {
        Set set = this.A06;
        if (set == null) {
            set = new HashSet();
            this.A06 = set;
        }
        set.add(gzVar);
    }

    @Override // X.OQ
    public final <T> void A0s(Class<T> cls) {
        Set set = this.A06;
        if (set == null) {
            set = new HashSet();
            this.A06 = set;
        }
        set.add(new gz(cls, KA.INSTANCE));
    }

    @Override // X.OQ
    public final <T> AnnotatedBindingBuilder<T> A0w(Class<T> cls) {
        return new AnonymousClass8A(A00(new gz<>(cls, KA.INSTANCE)));
    }

    @Override // X.OQ
    public final <T> LinkedComponentBindingBuilder<T> A0y(Class<T> cls) {
        gz<T> gzVar = new gz<>(cls, KA.INSTANCE);
        List list = this.A00;
        if (list == null) {
            list = new ArrayList();
            this.A00 = list;
        }
        OV ov = new OV();
        ov.A01 = this.A08;
        ov.A00 = gzVar;
        list.add(ov);
        return new SU(ov);
    }

    @Override // X.OQ
    public final <T> AnnotatedBindingBuilder<T> A10(Class<T> cls) {
        OR<T> A002 = A00(new gz<>(cls, KA.INSTANCE));
        A002.A00 = (byte) (1 | A002.A00);
        return new AnonymousClass8A(A002);
    }

    @Override // X.OQ
    public final <T> P1<T> A12(Class<T> cls) {
        return A01(new gz<>(cls, KA.INSTANCE));
    }

    @Override // X.OQ
    public final void A14(Class<? extends Annotation> cls, PC pc) {
        Map map = this.A05;
        if (map == null) {
            map = new HashMap();
            this.A05 = map;
        }
        map.put(cls, pc);
    }

    @Override // X.OQ
    public final void A1J(gz<?> gzVar) {
        Set set = this.A07;
        if (set == null) {
            set = new HashSet();
            this.A07 = set;
        }
        set.add(gzVar);
    }

    @Override // X.OQ
    public final void A1K(Class<?> cls) {
        A1J(new gz<>(cls, KA.INSTANCE));
    }

    @Override // X.OQ
    public final List<OV> A1d() {
        List<OV> list = this.A00;
        if (list == null) {
            return ImmutableList.of();
        }
        return list;
    }

    @Override // X.OQ
    public final Set<gz> A1o() {
        Set<gz> set = this.A07;
        if (set == null) {
            return RegularImmutableSet.A05;
        }
        return set;
    }

    @Override // X.OQ
    public final Map<gz, P1> A1p() {
        Map<gz, P1> map = this.A04;
        if (map == null) {
            return RegularImmutableMap.A03;
        }
        return map;
    }

    @Override // X.OQ
    public final List<Class<?>> A1w() {
        List<Class<?>> list = this.A03;
        if (list == null) {
            return ImmutableList.of();
        }
        return list;
    }

    @Override // X.OQ
    public final Map<Class<? extends Annotation>, PC> A1x() {
        Map<Class<? extends Annotation>, PC> map = this.A05;
        if (map == null) {
            return RegularImmutableMap.A03;
        }
        return map;
    }

    @Override // X.OQ
    public final void A3O(Class<?> cls) {
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

    public Se(BZ bz, Class cls) {
        this.A09 = bz;
        this.A08 = cls;
    }

    @Override // X.OQ
    public final <T> LinkedBindingBuilder<T> A0v(gz<T> gzVar) {
        return new AnonymousClass8A(A00(gzVar));
    }

    @Override // X.OQ
    public final <T> void A0x(Class<? extends AssistedProvider<T>> cls) {
        A0w(cls).A00.A04 = new AnonymousClass8F(cls);
    }

    @Override // X.OQ
    public final <T> LinkedBindingBuilder<T> A0z(gz<T> gzVar) {
        OR<T> A002 = A00(gzVar);
        A002.A00 = (byte) (1 | A002.A00);
        return new AnonymousClass8A(A002);
    }

    @Override // X.OQ
    public final <T> P1<T> A11(gz<T> gzVar) {
        return A01(gzVar);
    }

    @Override // X.OQ
    public final <T> P1<T> A13(Class<T> cls, Class<? extends Annotation> cls2) {
        return A01(gz.A01(cls, cls2));
    }

    @Override // X.OQ
    public final void A1L(Class<?> cls, Class<? extends Annotation> cls2) {
        A1J(gz.A01(cls, cls2));
    }

    @Override // X.OQ
    public final List<OR> A1c() {
        ImmutableList.Builder A022 = ImmutableList.A02();
        List<OR> list = this.A02;
        if (list != null) {
            HashSet hashSet = new HashSet(list.size());
            for (OR or : this.A02) {
                if (!hashSet.contains(0)) {
                    hashSet.add(0);
                    A022.add((Object) or);
                }
            }
        }
        return A022.build();
    }

    @Override // X.OQ
    public final On A1i() {
        return this.A09;
    }
}
