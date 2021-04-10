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
/* renamed from: X.Xz  reason: case insensitive filesystem */
public final class C0252Xz implements Pq {
    @Nullable
    public List<Pv> A00;
    @Nullable
    public Set<Class<?>> A01;
    @Nullable
    public List<Pr> A02;
    @Nullable
    public List<Class<?>> A03;
    @Nullable
    public Map<C0475qE, QR> A04;
    @Nullable
    public Map<Class<? extends Annotation>, AbstractC0133Qc> A05;
    @Nullable
    public Set<C0475qE> A06;
    @Nullable
    public Set<C0475qE> A07;
    public final Class A08;
    public final AbstractC0096Hu A09;

    private <T> Pr<T> A00(C0475qE<T> qEVar) {
        List list = this.A02;
        if (list == null) {
            list = new ArrayList();
            this.A02 = list;
        }
        Pr<T> pr = new Pr<>();
        pr.A02 = this.A08;
        pr.A01 = qEVar;
        list.add(pr);
        return pr;
    }

    private <T> QR<T> A01(C0475qE<T> qEVar) {
        Map map = this.A04;
        if (map == null) {
            map = new HashMap();
            this.A04 = map;
        }
        QR<T> qr = (QR) map.get(qEVar);
        if (qr != null) {
            return qr;
        }
        QR<T> qr2 = new QR<>(qEVar);
        this.A04.put(qEVar, qr2);
        return qr2;
    }

    @Override // X.Pq
    public final <T> void A1G(C0475qE<T> qEVar) {
        Set set = this.A06;
        if (set == null) {
            set = new HashSet();
            this.A06 = set;
        }
        set.add(qEVar);
    }

    @Override // X.Pq
    public final <T> void A1H(Class<T> cls) {
        Set set = this.A06;
        if (set == null) {
            set = new HashSet();
            this.A06 = set;
        }
        set.add(new C0475qE(cls, Rp.INSTANCE));
    }

    @Override // X.Pq
    public final <T> AnnotatedBindingBuilder<T> A1L(Class<T> cls) {
        return new C00188a(A00(new C0475qE<>(cls, Rp.INSTANCE)));
    }

    @Override // X.Pq
    public final <T> LinkedComponentBindingBuilder<T> A1N(Class<T> cls) {
        C0475qE<T> qEVar = new C0475qE<>(cls, Rp.INSTANCE);
        List list = this.A00;
        if (list == null) {
            list = new ArrayList();
            this.A00 = list;
        }
        Pv pv = new Pv();
        pv.A01 = this.A08;
        pv.A00 = qEVar;
        list.add(pv);
        return new C0241Xo(pv);
    }

    @Override // X.Pq
    public final <T> AnnotatedBindingBuilder<T> A1P(Class<T> cls) {
        Pr<T> A002 = A00(new C0475qE<>(cls, Rp.INSTANCE));
        A002.A00 = (byte) (1 | A002.A00);
        return new C00188a(A002);
    }

    @Override // X.Pq
    public final <T> QR<T> A1R(Class<T> cls) {
        return A01(new C0475qE<>(cls, Rp.INSTANCE));
    }

    @Override // X.Pq
    public final void A1T(Class<? extends Annotation> cls, AbstractC0133Qc qc) {
        Map map = this.A05;
        if (map == null) {
            map = new HashMap();
            this.A05 = map;
        }
        map.put(cls, qc);
    }

    @Override // X.Pq
    public final void A1k(C0475qE<?> qEVar) {
        Set set = this.A07;
        if (set == null) {
            set = new HashSet();
            this.A07 = set;
        }
        set.add(qEVar);
    }

    @Override // X.Pq
    public final void A1l(Class<?> cls) {
        A1k(new C0475qE<>(cls, Rp.INSTANCE));
    }

    @Override // X.Pq
    public final List<Pv> A2O() {
        List<Pv> list = this.A00;
        if (list == null) {
            return ImmutableList.of();
        }
        return list;
    }

    @Override // X.Pq
    public final Set<C0475qE> A2g() {
        Set<C0475qE> set = this.A07;
        if (set == null) {
            return RegularImmutableSet.A05;
        }
        return set;
    }

    @Override // X.Pq
    public final Map<C0475qE, QR> A2h() {
        Map<C0475qE, QR> map = this.A04;
        if (map == null) {
            return RegularImmutableMap.A03;
        }
        return map;
    }

    @Override // X.Pq
    public final List<Class<?>> A2o() {
        List<Class<?>> list = this.A03;
        if (list == null) {
            return ImmutableList.of();
        }
        return list;
    }

    @Override // X.Pq
    public final Map<Class<? extends Annotation>, AbstractC0133Qc> A2p() {
        Map<Class<? extends Annotation>, AbstractC0133Qc> map = this.A05;
        if (map == null) {
            return RegularImmutableMap.A03;
        }
        return map;
    }

    @Override // X.Pq
    public final void A4m(Class<?> cls) {
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

    public C0252Xz(AbstractC0096Hu hu, Class cls) {
        this.A09 = hu;
        this.A08 = cls;
    }

    @Override // X.Pq
    public final <T> LinkedBindingBuilder<T> A1K(C0475qE<T> qEVar) {
        return new C00188a(A00(qEVar));
    }

    @Override // X.Pq
    public final <T> void A1M(Class<? extends AssistedProvider<T>> cls) {
        A1L(cls).A00.A04 = new C00218e(cls);
    }

    @Override // X.Pq
    public final <T> LinkedBindingBuilder<T> A1O(C0475qE<T> qEVar) {
        Pr<T> A002 = A00(qEVar);
        A002.A00 = (byte) (1 | A002.A00);
        return new C00188a(A002);
    }

    @Override // X.Pq
    public final <T> QR<T> A1Q(C0475qE<T> qEVar) {
        return A01(qEVar);
    }

    @Override // X.Pq
    public final <T> QR<T> A1S(Class<T> cls, Class<? extends Annotation> cls2) {
        return A01(C0475qE.A01(cls, cls2));
    }

    @Override // X.Pq
    public final void A1m(Class<?> cls, Class<? extends Annotation> cls2) {
        A1k(C0475qE.A01(cls, cls2));
    }

    @Override // X.Pq
    public final List<Pr> A2K() {
        ImmutableList.Builder A022 = ImmutableList.A02();
        List<Pr> list = this.A02;
        if (list != null) {
            HashSet hashSet = new HashSet(list.size());
            for (Pr pr : this.A02) {
                Integer valueOf = Integer.valueOf(C0523tY.A00(pr.A01));
                if (!hashSet.contains(valueOf)) {
                    hashSet.add(valueOf);
                    A022.add((Object) pr);
                }
            }
        }
        return A022.build();
    }

    @Override // X.Pq
    public final QD A2V() {
        return this.A09;
    }
}
