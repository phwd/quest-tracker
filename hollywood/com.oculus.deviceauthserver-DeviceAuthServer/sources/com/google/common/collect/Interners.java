package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.MapMakerInternalMap;
import java.util.concurrent.ConcurrentMap;

@Beta
public final class Interners {
    private Interners() {
    }

    public static <E> Interner<E> newStrongInterner() {
        final ConcurrentMap<E, E> map = new MapMaker().makeMap();
        return new Interner<E>() {
            /* class com.google.common.collect.Interners.AnonymousClass1 */

            @Override // com.google.common.collect.Interner
            public E intern(E sample) {
                E canonical = (E) map.putIfAbsent(Preconditions.checkNotNull(sample), sample);
                return canonical == null ? sample : canonical;
            }
        };
    }

    @GwtIncompatible("java.lang.ref.WeakReference")
    public static <E> Interner<E> newWeakInterner() {
        return new WeakInterner();
    }

    private static class WeakInterner<E> implements Interner<E> {
        private final MapMakerInternalMap<E, Dummy> map;

        private enum Dummy {
            VALUE
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [com.google.common.collect.MapMaker] */
        /* JADX WARN: Type inference failed for: r0v2, types: [com.google.common.collect.MapMaker] */
        /* JADX WARNING: Unknown variable types count: 2 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private WeakInterner() {
            /*
                r2 = this;
                r2.<init>()
                com.google.common.collect.MapMaker r0 = new com.google.common.collect.MapMaker
                r0.<init>()
                com.google.common.collect.MapMaker r0 = r0.weakKeys()
                com.google.common.base.Equivalence r1 = com.google.common.base.Equivalence.equals()
                com.google.common.collect.MapMaker r0 = r0.keyEquivalence(r1)
                com.google.common.collect.MapMakerInternalMap r0 = r0.makeCustomMap()
                r2.map = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.Interners.WeakInterner.<init>():void");
        }

        @Override // com.google.common.collect.Interner
        public E intern(E sample) {
            E canonical;
            do {
                MapMakerInternalMap.ReferenceEntry<E, Dummy> entry = this.map.getEntry(sample);
                if (entry != null && (canonical = entry.getKey()) != null) {
                    return canonical;
                }
            } while (this.map.putIfAbsent(sample, Dummy.VALUE) != null);
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
