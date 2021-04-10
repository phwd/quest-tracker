package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.MapMaker;
import com.google.common.collect.MapMakerInternalMap;

@Beta
@GwtIncompatible
public final class Interners {
    private Interners() {
    }

    public static class InternerBuilder {
        private final MapMaker mapMaker;
        private boolean strong;

        private InternerBuilder() {
            this.mapMaker = new MapMaker();
            this.strong = true;
        }

        public InternerBuilder strong() {
            this.strong = true;
            return this;
        }

        @GwtIncompatible("java.lang.ref.WeakReference")
        public InternerBuilder weak() {
            this.strong = false;
            return this;
        }

        public InternerBuilder concurrencyLevel(int concurrencyLevel) {
            this.mapMaker.concurrencyLevel(concurrencyLevel);
            return this;
        }

        public <E> Interner<E> build() {
            if (!this.strong) {
                this.mapMaker.weakKeys();
            }
            return new InternerImpl(this.mapMaker);
        }
    }

    public static InternerBuilder newBuilder() {
        return new InternerBuilder();
    }

    public static <E> Interner<E> newStrongInterner() {
        return newBuilder().strong().build();
    }

    @GwtIncompatible("java.lang.ref.WeakReference")
    public static <E> Interner<E> newWeakInterner() {
        return newBuilder().weak().build();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public static final class InternerImpl<E> implements Interner<E> {
        @VisibleForTesting
        final MapMakerInternalMap<E, MapMaker.Dummy, ?, ?> map;

        private InternerImpl(MapMaker mapMaker) {
            this.map = MapMakerInternalMap.createWithDummyValues(mapMaker.keyEquivalence(Equivalence.equals()));
        }

        @Override // com.google.common.collect.Interner
        public E intern(E sample) {
            E canonical;
            do {
                MapMakerInternalMap.InternalEntry<E, MapMaker.Dummy, ?> entry = this.map.getEntry(sample);
                if (entry != null && (canonical = entry.getKey()) != null) {
                    return canonical;
                }
            } while (this.map.putIfAbsent(sample, MapMaker.Dummy.VALUE) != null);
            return sample;
        }
    }

    public static <E> Function<E, E> asFunction(Interner<E> interner) {
        return new InternerFunction((Interner) Preconditions.checkNotNull(interner));
    }

    private static class InternerFunction<E> implements Function<E, E> {
        private final Interner<E> interner;

        public InternerFunction(Interner<E> interner2) {
            this.interner = interner2;
        }

        @Override // com.google.common.base.Function
        public E apply(E input) {
            return this.interner.intern(input);
        }

        public int hashCode() {
            return this.interner.hashCode();
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object other) {
            if (other instanceof InternerFunction) {
                return this.interner.equals(((InternerFunction) other).interner);
            }
            return false;
        }
    }
}
