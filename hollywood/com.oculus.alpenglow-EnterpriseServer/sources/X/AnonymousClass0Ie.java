package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.BoundType;
import com.google.common.collect.Multiset;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.Set;

@GwtCompatible(emulated = true)
/* renamed from: X.0Ie  reason: invalid class name */
public interface AnonymousClass0Ie<E> extends AbstractC02290Xp<E>, AbstractC07460te<E> {
    AnonymousClass0Ie<E> A26();

    NavigableSet<E> A2F();

    Multiset.Entry<E> A2o();

    AnonymousClass0Ie<E> A52(E e, BoundType boundType);

    Multiset.Entry<E> A5d();

    Multiset.Entry<E> A6q();

    Multiset.Entry<E> A6r();

    AnonymousClass0Ie<E> A8V(E e, BoundType boundType, E e2, BoundType boundType2);

    AnonymousClass0Ie<E> A8Y(E e, BoundType boundType);

    @Override // X.AbstractC07460te
    Comparator<? super E> comparator();

    @Override // X.AnonymousClass0tC
    Set<Multiset.Entry<E>> entrySet();
}
