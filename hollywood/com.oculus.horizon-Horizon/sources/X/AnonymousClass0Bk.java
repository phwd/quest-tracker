package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.BoundType;
import com.google.common.collect.Multiset;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.Set;

@GwtCompatible(emulated = true)
/* renamed from: X.0Bk  reason: invalid class name */
public interface AnonymousClass0Bk<E> extends AbstractC03520d9<E>, AbstractC07200rb<E> {
    AnonymousClass0Bk<E> A2E();

    NavigableSet<E> A2M();

    Multiset.Entry<E> A2p();

    AnonymousClass0Bk<E> A4j(E e, BoundType boundType);

    Multiset.Entry<E> A5C();

    Multiset.Entry<E> A7M();

    Multiset.Entry<E> A7N();

    AnonymousClass0Bk<E> A9W(E e, BoundType boundType, E e2, BoundType boundType2);

    AnonymousClass0Bk<E> A9c(E e, BoundType boundType);

    @Override // X.AbstractC07200rb
    Comparator<? super E> comparator();

    @Override // X.AnonymousClass0r9
    Set<Multiset.Entry<E>> entrySet();
}
