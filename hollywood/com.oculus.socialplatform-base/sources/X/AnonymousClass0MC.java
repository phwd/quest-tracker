package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.BoundType;
import com.google.common.collect.Multiset;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.Set;

@GwtCompatible(emulated = true)
/* renamed from: X.0MC  reason: invalid class name */
public interface AnonymousClass0MC<E> extends AbstractC01560et<E>, AnonymousClass0wF<E> {
    AnonymousClass0MC<E> A2a();

    NavigableSet<E> A2j();

    Multiset.Entry<E> A3E();

    AnonymousClass0MC<E> A5T(E e, BoundType boundType);

    Multiset.Entry<E> A6F();

    Multiset.Entry<E> A8P();

    Multiset.Entry<E> A8Q();

    AnonymousClass0MC<E> AAW(E e, BoundType boundType, E e2, BoundType boundType2);

    AnonymousClass0MC<E> AAg(E e, BoundType boundType);

    @Override // X.AnonymousClass0wF
    Comparator<? super E> comparator();

    @Override // X.AbstractC05490vp
    Set<Multiset.Entry<E>> entrySet();
}
