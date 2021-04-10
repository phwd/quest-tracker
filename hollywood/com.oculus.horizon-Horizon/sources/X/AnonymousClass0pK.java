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
/* renamed from: X.0pK  reason: invalid class name */
public final class AnonymousClass0pK implements AnonymousClass0Pp {
    @Nullable
    public List<AnonymousClass0Pu> A00;
    @Nullable
    public Set<Class<?>> A01;
    @Nullable
    public List<AnonymousClass0Pq> A02;
    @Nullable
    public List<Class<?>> A03;
    @Nullable
    public Map<C09160zY, AnonymousClass0QR> A04;
    @Nullable
    public Map<Class<? extends Annotation>, AbstractC01320Qc> A05;
    @Nullable
    public Set<C09160zY> A06;
    @Nullable
    public Set<C09160zY> A07;
    public final Class A08;
    public final AnonymousClass0J2 A09;

    private <T> AnonymousClass0Pq<T> A00(C09160zY<T> r4) {
        List list = this.A02;
        if (list == null) {
            list = new ArrayList();
            this.A02 = list;
        }
        AnonymousClass0Pq<T> r1 = new AnonymousClass0Pq<>();
        r1.A02 = this.A08;
        r1.A01 = r4;
        list.add(r1);
        return r1;
    }

    private <T> AnonymousClass0QR<T> A01(C09160zY<T> r3) {
        Map map = this.A04;
        if (map == null) {
            map = new HashMap();
            this.A04 = map;
        }
        AnonymousClass0QR<T> r1 = (AnonymousClass0QR) map.get(r3);
        if (r1 != null) {
            return r1;
        }
        AnonymousClass0QR<T> r12 = new AnonymousClass0QR<>(r3);
        this.A04.put(r3, r12);
        return r12;
    }

    @Override // X.AnonymousClass0Pp
    public final <T> void A1I(C09160zY<T> r2) {
        Set set = this.A06;
        if (set == null) {
            set = new HashSet();
            this.A06 = set;
        }
        set.add(r2);
    }

    @Override // X.AnonymousClass0Pp
    public final <T> void A1J(Class<T> cls) {
        Set set = this.A06;
        if (set == null) {
            set = new HashSet();
            this.A06 = set;
        }
        set.add(new C09160zY(cls, AnonymousClass0US.INSTANCE));
    }

    @Override // X.AnonymousClass0Pp
    public final <T> AnnotatedBindingBuilder<T> A1M(Class<T> cls) {
        return new C002908x(A00(new C09160zY<>(cls, AnonymousClass0US.INSTANCE)));
    }

    @Override // X.AnonymousClass0Pp
    public final <T> LinkedComponentBindingBuilder<T> A1O(Class<T> cls) {
        C09160zY<T> r3 = new C09160zY<>(cls, AnonymousClass0US.INSTANCE);
        List list = this.A00;
        if (list == null) {
            list = new ArrayList();
            this.A00 = list;
        }
        AnonymousClass0Pu r1 = new AnonymousClass0Pu();
        r1.A02 = this.A08;
        r1.A01 = r3;
        list.add(r1);
        return new AnonymousClass0oj(r1);
    }

    @Override // X.AnonymousClass0Pp
    public final <T> AnnotatedBindingBuilder<T> A1Q(Class<T> cls) {
        AnonymousClass0Pq<T> A002 = A00(new C09160zY<>(cls, AnonymousClass0US.INSTANCE));
        A002.A00 = (byte) (1 | A002.A00);
        return new C002908x(A002);
    }

    @Override // X.AnonymousClass0Pp
    public final <T> AnonymousClass0QR<T> A1S(Class<T> cls) {
        return A01(new C09160zY<>(cls, AnonymousClass0US.INSTANCE));
    }

    @Override // X.AnonymousClass0Pp
    public final void A1U(Class<? extends Annotation> cls, AbstractC01320Qc r3) {
        Map map = this.A05;
        if (map == null) {
            map = new HashMap();
            this.A05 = map;
        }
        map.put(cls, r3);
    }

    @Override // X.AnonymousClass0Pp
    public final void A28(C09160zY<?> r2) {
        Set set = this.A07;
        if (set == null) {
            set = new HashSet();
            this.A07 = set;
        }
        set.add(r2);
    }

    @Override // X.AnonymousClass0Pp
    public final void A29(Class<?> cls) {
        A28(new C09160zY<>(cls, AnonymousClass0US.INSTANCE));
    }

    @Override // X.AnonymousClass0Pp
    public final List<AnonymousClass0Pu> A3E() {
        List<AnonymousClass0Pu> list = this.A00;
        if (list == null) {
            return ImmutableList.of();
        }
        return list;
    }

    @Override // X.AnonymousClass0Pp
    public final Set<C09160zY> A3x() {
        Set<C09160zY> set = this.A07;
        if (set == null) {
            return RegularImmutableSet.A05;
        }
        return set;
    }

    @Override // X.AnonymousClass0Pp
    public final Map<C09160zY, AnonymousClass0QR> A3y() {
        Map<C09160zY, AnonymousClass0QR> map = this.A04;
        if (map == null) {
            return RegularImmutableMap.A03;
        }
        return map;
    }

    @Override // X.AnonymousClass0Pp
    public final List<Class<?>> A4H() {
        List<Class<?>> list = this.A03;
        if (list == null) {
            return ImmutableList.of();
        }
        return list;
    }

    @Override // X.AnonymousClass0Pp
    public final Map<Class<? extends Annotation>, AbstractC01320Qc> A4J() {
        Map<Class<? extends Annotation>, AbstractC01320Qc> map = this.A05;
        if (map == null) {
            return RegularImmutableMap.A03;
        }
        return map;
    }

    @Override // X.AnonymousClass0Pp
    public final void A8J(Class<?> cls) {
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

    public AnonymousClass0pK(AnonymousClass0J2 r1, Class cls) {
        this.A09 = r1;
        this.A08 = cls;
    }

    @Override // X.AnonymousClass0Pp
    public final <T> LinkedBindingBuilder<T> A1L(C09160zY<T> r3) {
        return new C002908x(A00(r3));
    }

    @Override // X.AnonymousClass0Pp
    public final <T> void A1N(Class<? extends AssistedProvider<T>> cls) {
        A1M(cls).A00.A05 = new AnonymousClass090(cls);
    }

    @Override // X.AnonymousClass0Pp
    public final <T> LinkedBindingBuilder<T> A1P(C09160zY<T> r4) {
        AnonymousClass0Pq<T> A002 = A00(r4);
        A002.A00 = (byte) (1 | A002.A00);
        return new C002908x(A002);
    }

    @Override // X.AnonymousClass0Pp
    public final <T> AnonymousClass0QR<T> A1R(C09160zY<T> r2) {
        return A01(r2);
    }

    @Override // X.AnonymousClass0Pp
    public final <T> AnonymousClass0QR<T> A1T(Class<T> cls, Class<? extends Annotation> cls2) {
        return A01(C09160zY.A01(cls, cls2));
    }

    @Override // X.AnonymousClass0Pp
    public final void A2A(Class<?> cls, Class<? extends Annotation> cls2) {
        A28(C09160zY.A01(cls, cls2));
    }

    @Override // X.AnonymousClass0Pp
    public final List<AnonymousClass0Pq> A35() {
        ImmutableList.Builder A022 = ImmutableList.A02();
        List<AnonymousClass0Pq> list = this.A02;
        if (list != null) {
            HashSet hashSet = new HashSet(list.size());
            for (AnonymousClass0Pq r2 : this.A02) {
                Integer valueOf = Integer.valueOf(AnonymousClass11P.A00(r2.A01));
                if (!hashSet.contains(valueOf)) {
                    hashSet.add(valueOf);
                    A022.add((Object) r2);
                }
            }
        }
        return A022.build();
    }

    @Override // X.AnonymousClass0Pp
    public final AnonymousClass0QD A3Y() {
        return this.A09;
    }
}
