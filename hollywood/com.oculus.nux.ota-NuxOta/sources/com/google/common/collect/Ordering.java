package com.google.common.collect;

import com.google.common.base.Function;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Ordering<T> implements Comparator<T> {

    static class IncomparableValueException extends ClassCastException {
        private static final long serialVersionUID = 0;
        final Object value;
    }

    @Override // java.util.Comparator
    public abstract int compare(T t, T t2);

    public static <C extends Comparable> Ordering<C> natural() {
        return NaturalOrdering.INSTANCE;
    }

    public static <T> Ordering<T> from(Comparator<T> comparator) {
        if (comparator instanceof Ordering) {
            return (Ordering) comparator;
        }
        return new ComparatorOrdering(comparator);
    }

    public static Ordering<Object> usingToString() {
        return UsingToStringOrdering.INSTANCE;
    }

    static class ArbitraryOrdering extends Ordering<Object> {
        private Map<Object, Integer> uids;

        public String toString() {
            return "Ordering.arbitrary()";
        }

        ArbitraryOrdering() {
            MapMaker mapMaker = new MapMaker();
            Platform.tryWeakKeys(mapMaker);
            this.uids = mapMaker.makeComputingMap(new Function<Object, Integer>() {
                /* class com.google.common.collect.Ordering.ArbitraryOrdering.AnonymousClass1 */
                final AtomicInteger counter = new AtomicInteger(0);

                @Override // com.google.common.base.Function
                public Integer apply(Object obj) {
                    return Integer.valueOf(this.counter.getAndIncrement());
                }
            });
        }

        @Override // com.google.common.collect.Ordering, java.util.Comparator
        public int compare(Object obj, Object obj2) {
            if (obj == obj2) {
                return 0;
            }
            if (obj == null) {
                return -1;
            }
            if (obj2 == null) {
                return 1;
            }
            int identityHashCode = identityHashCode(obj);
            int identityHashCode2 = identityHashCode(obj2);
            if (identityHashCode != identityHashCode2) {
                return identityHashCode < identityHashCode2 ? -1 : 1;
            }
            int compareTo = this.uids.get(obj).compareTo(this.uids.get(obj2));
            if (compareTo != 0) {
                return compareTo;
            }
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public int identityHashCode(Object obj) {
            return System.identityHashCode(obj);
        }
    }

    protected Ordering() {
    }

    public <S extends T> Ordering<S> reverse() {
        return new ReverseOrdering(this);
    }

    public <F> Ordering<F> onResultOf(Function<F, ? extends T> function) {
        return new ByFunctionOrdering(function, this);
    }

    /* access modifiers changed from: package-private */
    public <T2 extends T> Ordering<Map.Entry<T2, ?>> onKeys() {
        return onResultOf(Maps.keyFunction());
    }
}
