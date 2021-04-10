package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.ObjIntConsumer;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public interface Multiset<E> extends Collection<E> {

    public interface Entry<E> {
        boolean equals(Object obj);

        int getCount();

        E getElement();

        int hashCode();

        String toString();
    }

    @CanIgnoreReturnValue
    int add(@NullableDecl E e, int i);

    @Override // java.util.Collection
    @CanIgnoreReturnValue
    boolean add(E e);

    boolean contains(@NullableDecl Object obj);

    @Override // java.util.Collection
    boolean containsAll(Collection<?> collection);

    int count(@NullableDecl @CompatibleWith("E") Object obj);

    Set<E> elementSet();

    Set<Entry<E>> entrySet();

    boolean equals(@NullableDecl Object obj);

    int hashCode();

    @Override // java.util.Collection, java.lang.Iterable
    Iterator<E> iterator();

    @CanIgnoreReturnValue
    int remove(@NullableDecl @CompatibleWith("E") Object obj, int i);

    @CanIgnoreReturnValue
    boolean remove(@NullableDecl Object obj);

    @Override // java.util.Collection
    @CanIgnoreReturnValue
    boolean removeAll(Collection<?> collection);

    @Override // java.util.Collection
    @CanIgnoreReturnValue
    boolean retainAll(Collection<?> collection);

    @CanIgnoreReturnValue
    int setCount(E e, int i);

    @CanIgnoreReturnValue
    boolean setCount(E e, int i, int i2);

    int size();

    String toString();

    @Beta
    default void forEachEntry(ObjIntConsumer<? super E> objIntConsumer) {
        Preconditions.checkNotNull(objIntConsumer);
        entrySet().forEach(new Consumer(objIntConsumer) {
            /* class com.google.common.collect.$$Lambda$Multiset$y63SmnatRR1Ke2x1fqDeFclRelQ */
            private final /* synthetic */ ObjIntConsumer f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Multiset.Entry entry;
                this.f$0.accept(entry.getElement(), ((Multiset.Entry) obj).getCount());
            }
        });
    }

    @Override // java.lang.Iterable
    default void forEach(Consumer<? super E> consumer) {
        Preconditions.checkNotNull(consumer);
        entrySet().forEach(new Consumer(consumer) {
            /* class com.google.common.collect.$$Lambda$Multiset$yTxkVZMQHaYOoZD4ry_up4hmmvA */
            private final /* synthetic */ Consumer f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Multiset.lambda$forEach$1(this.f$0, (Multiset.Entry) obj);
            }
        });
    }

    static /* synthetic */ default void lambda$forEach$1(Consumer consumer, Entry entry) {
        Object element = entry.getElement();
        int count = entry.getCount();
        for (int i = 0; i < count; i++) {
            consumer.accept(element);
        }
    }

    @Override // java.util.Collection, java.lang.Iterable
    default Spliterator<E> spliterator() {
        return Multisets.spliteratorImpl(this);
    }
}
