package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.BoundType;
import com.google.common.collect.Multiset;
import com.oculus.common.build.BuildConfig;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.Set;

@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD)
/* renamed from: X.9K  reason: invalid class name */
public interface AnonymousClass9K<E> extends MR<E>, S1<E> {
    AnonymousClass9K<E> A1M();

    NavigableSet<E> A1Q();

    Multiset.Entry<E> A1Y();

    AnonymousClass9K<E> A26(E e, BoundType boundType);

    Multiset.Entry<E> A2G();

    Multiset.Entry<E> A2r();

    Multiset.Entry<E> A2s();

    AnonymousClass9K<E> A3n(E e, BoundType boundType, E e2, BoundType boundType2);

    AnonymousClass9K<E> A3p(E e, BoundType boundType);

    @Override // X.S1
    Comparator<? super E> comparator();

    @Override // X.AbstractC0120Qz
    Set<Multiset.Entry<E>> entrySet();
}
