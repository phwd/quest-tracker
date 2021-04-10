package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.BoundType;
import com.google.common.collect.Multiset;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.Set;

@GwtCompatible(emulated = true)
public interface BA<E> extends UX<E>, AnonymousClass6z<E> {
    BA<E> A1o();

    NavigableSet<E> A1s();

    Multiset.Entry<E> A2C();

    BA<E> A30(E e, BoundType boundType);

    Multiset.Entry<E> A3M();

    Multiset.Entry<E> A4E();

    Multiset.Entry<E> A4F();

    BA<E> A5Q(E e, BoundType boundType, E e2, BoundType boundType2);

    BA<E> A5T(E e, BoundType boundType);

    @Override // X.AnonymousClass6z
    Comparator<? super E> comparator();

    @Override // X.AnonymousClass34
    Set<Multiset.Entry<E>> entrySet();
}
