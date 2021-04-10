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
/* renamed from: X.0ls  reason: invalid class name and case insensitive filesystem */
public final class C03260ls implements AnonymousClass0Qr {
    @Nullable
    public List<AnonymousClass0Qw> A00;
    @Nullable
    public Set<Class<?>> A01;
    @Nullable
    public List<AnonymousClass0Qs> A02;
    @Nullable
    public List<Class<?>> A03;
    @Nullable
    public Map<AnonymousClass14P, AnonymousClass0RT> A04;
    @Nullable
    public Map<Class<? extends Annotation>, AbstractC01120Rf> A05;
    @Nullable
    public Set<AnonymousClass14P> A06;
    @Nullable
    public Set<AnonymousClass14P> A07;
    public final Class A08;
    public final AnonymousClass0VF A09;

    private <T> AnonymousClass0Qs<T> A00(AnonymousClass14P<T> r4) {
        List list = this.A02;
        if (list == null) {
            list = new ArrayList();
            this.A02 = list;
        }
        AnonymousClass0Qs<T> r1 = new AnonymousClass0Qs<>();
        r1.A02 = this.A08;
        r1.A01 = r4;
        list.add(r1);
        return r1;
    }

    private <T> AnonymousClass0RT<T> A01(AnonymousClass14P<T> r3) {
        Map map = this.A04;
        if (map == null) {
            map = new HashMap();
            this.A04 = map;
        }
        AnonymousClass0RT<T> r1 = (AnonymousClass0RT) map.get(r3);
        if (r1 != null) {
            return r1;
        }
        AnonymousClass0RT<T> r12 = new AnonymousClass0RT<>(r3);
        this.A04.put(r3, r12);
        return r12;
    }

    @Override // X.AnonymousClass0Qr
    public final <T> void A1V(AnonymousClass14P<T> r2) {
        Set set = this.A06;
        if (set == null) {
            set = new HashSet();
            this.A06 = set;
        }
        set.add(r2);
    }

    @Override // X.AnonymousClass0Qr
    public final <T> void A1W(Class<T> cls) {
        Set set = this.A06;
        if (set == null) {
            set = new HashSet();
            this.A06 = set;
        }
        set.add(new AnonymousClass14P(cls, AnonymousClass0cy.INSTANCE));
    }

    @Override // X.AnonymousClass0Qr
    public final <T> AnnotatedBindingBuilder<T> A1c(Class<T> cls) {
        return new AnonymousClass0Hq(A00(new AnonymousClass14P<>(cls, AnonymousClass0cy.INSTANCE)));
    }

    @Override // X.AnonymousClass0Qr
    public final <T> LinkedComponentBindingBuilder<T> A1e(Class<T> cls) {
        AnonymousClass14P<T> r3 = new AnonymousClass14P<>(cls, AnonymousClass0cy.INSTANCE);
        List list = this.A00;
        if (list == null) {
            list = new ArrayList();
            this.A00 = list;
        }
        AnonymousClass0Qw r1 = new AnonymousClass0Qw();
        r1.A02 = this.A08;
        r1.A01 = r3;
        list.add(r1);
        return new AnonymousClass0lX(r1);
    }

    @Override // X.AnonymousClass0Qr
    public final <T> AnnotatedBindingBuilder<T> A1g(Class<T> cls) {
        AnonymousClass0Qs<T> A002 = A00(new AnonymousClass14P<>(cls, AnonymousClass0cy.INSTANCE));
        A002.A00 = (byte) (1 | A002.A00);
        return new AnonymousClass0Hq(A002);
    }

    @Override // X.AnonymousClass0Qr
    public final <T> AnonymousClass0RT<T> A1i(Class<T> cls) {
        return A01(new AnonymousClass14P<>(cls, AnonymousClass0cy.INSTANCE));
    }

    @Override // X.AnonymousClass0Qr
    public final void A1k(Class<? extends Annotation> cls, AbstractC01120Rf r3) {
        Map map = this.A05;
        if (map == null) {
            map = new HashMap();
            this.A05 = map;
        }
        map.put(cls, r3);
    }

    @Override // X.AnonymousClass0Qr
    public final void A2S(AnonymousClass14P<?> r2) {
        Set set = this.A07;
        if (set == null) {
            set = new HashSet();
            this.A07 = set;
        }
        set.add(r2);
    }

    @Override // X.AnonymousClass0Qr
    public final void A2T(Class<?> cls) {
        A2S(new AnonymousClass14P<>(cls, AnonymousClass0cy.INSTANCE));
    }

    @Override // X.AnonymousClass0Qr
    public final List<AnonymousClass0Qw> A3b() {
        List<AnonymousClass0Qw> list = this.A00;
        if (list == null) {
            return ImmutableList.of();
        }
        return list;
    }

    @Override // X.AnonymousClass0Qr
    public final Set<AnonymousClass14P> A4W() {
        Set<AnonymousClass14P> set = this.A07;
        if (set == null) {
            return RegularImmutableSet.A05;
        }
        return set;
    }

    @Override // X.AnonymousClass0Qr
    public final Map<AnonymousClass14P, AnonymousClass0RT> A4X() {
        Map<AnonymousClass14P, AnonymousClass0RT> map = this.A04;
        if (map == null) {
            return RegularImmutableMap.A03;
        }
        return map;
    }

    @Override // X.AnonymousClass0Qr
    public final List<Class<?>> A4m() {
        List<Class<?>> list = this.A03;
        if (list == null) {
            return ImmutableList.of();
        }
        return list;
    }

    @Override // X.AnonymousClass0Qr
    public final Map<Class<? extends Annotation>, AbstractC01120Rf> A4s() {
        Map<Class<? extends Annotation>, AbstractC01120Rf> map = this.A05;
        if (map == null) {
            return RegularImmutableMap.A03;
        }
        return map;
    }

    @Override // X.AnonymousClass0Qr
    public final void A9H(Class<?> cls) {
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

    public C03260ls(AnonymousClass0VF r1, Class cls) {
        this.A09 = r1;
        this.A08 = cls;
    }

    @Override // X.AnonymousClass0Qr
    public final <T> LinkedBindingBuilder<T> A1b(AnonymousClass14P<T> r3) {
        return new AnonymousClass0Hq(A00(r3));
    }

    @Override // X.AnonymousClass0Qr
    public final <T> void A1d(Class<? extends AssistedProvider<T>> cls) {
        A1c(cls).A00.A05 = new AnonymousClass0Ht(cls);
    }

    @Override // X.AnonymousClass0Qr
    public final <T> LinkedBindingBuilder<T> A1f(AnonymousClass14P<T> r4) {
        AnonymousClass0Qs<T> A002 = A00(r4);
        A002.A00 = (byte) (1 | A002.A00);
        return new AnonymousClass0Hq(A002);
    }

    @Override // X.AnonymousClass0Qr
    public final <T> AnonymousClass0RT<T> A1h(AnonymousClass14P<T> r2) {
        return A01(r2);
    }

    @Override // X.AnonymousClass0Qr
    public final <T> AnonymousClass0RT<T> A1j(Class<T> cls, Class<? extends Annotation> cls2) {
        return A01(AnonymousClass14P.A01(cls, cls2));
    }

    @Override // X.AnonymousClass0Qr
    public final void A2U(Class<?> cls, Class<? extends Annotation> cls2) {
        A2S(AnonymousClass14P.A01(cls, cls2));
    }

    @Override // X.AnonymousClass0Qr
    public final List<AnonymousClass0Qs> A3S() {
        ImmutableList.Builder A022 = ImmutableList.A02();
        List<AnonymousClass0Qs> list = this.A02;
        if (list != null) {
            HashSet hashSet = new HashSet(list.size());
            for (AnonymousClass0Qs r2 : this.A02) {
                Integer valueOf = Integer.valueOf(AnonymousClass1Tu.A00(r2.A01));
                if (!hashSet.contains(valueOf)) {
                    hashSet.add(valueOf);
                    A022.add((Object) r2);
                }
            }
        }
        return A022.build();
    }

    @Override // X.AnonymousClass0Qr
    public final AnonymousClass0RF A4A() {
        return this.A09;
    }
}
