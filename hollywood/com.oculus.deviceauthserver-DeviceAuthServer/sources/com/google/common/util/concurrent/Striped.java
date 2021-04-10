package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.math.IntMath;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Beta
public abstract class Striped<L> {
    private static final int ALL_SET = -1;
    private static final int LARGE_LAZY_CUTOFF = 1024;
    private static final Supplier<ReadWriteLock> READ_WRITE_LOCK_SUPPLIER = new Supplier<ReadWriteLock>() {
        /* class com.google.common.util.concurrent.Striped.AnonymousClass5 */

        @Override // com.google.common.base.Supplier
        public ReadWriteLock get() {
            return new ReentrantReadWriteLock();
        }
    };

    public abstract L get(Object obj);

    public abstract L getAt(int i);

    /* access modifiers changed from: package-private */
    public abstract int indexFor(Object obj);

    public abstract int size();

    private Striped() {
    }

    public Iterable<L> bulkGet(Iterable<?> keys) {
        Object[] array = Iterables.toArray(keys, Object.class);
        if (array.length == 0) {
            return ImmutableList.of();
        }
        int[] stripes = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            stripes[i] = indexFor(array[i]);
        }
        Arrays.sort(stripes);
        int previousStripe = stripes[0];
        array[0] = getAt(previousStripe);
        for (int i2 = 1; i2 < array.length; i2++) {
            int currentStripe = stripes[i2];
            if (currentStripe == previousStripe) {
                array[i2] = array[i2 - 1];
            } else {
                array[i2] = getAt(currentStripe);
                previousStripe = currentStripe;
            }
        }
        return Collections.unmodifiableList(Arrays.asList(array));
    }

    public static Striped<Lock> lock(int stripes) {
        return new CompactStriped(stripes, new Supplier<Lock>() {
            /* class com.google.common.util.concurrent.Striped.AnonymousClass1 */

            @Override // com.google.common.base.Supplier
            public Lock get() {
                return new PaddedLock();
            }
        });
    }

    public static Striped<Lock> lazyWeakLock(int stripes) {
        return lazy(stripes, new Supplier<Lock>() {
            /* class com.google.common.util.concurrent.Striped.AnonymousClass2 */

            @Override // com.google.common.base.Supplier
            public Lock get() {
                return new ReentrantLock(false);
            }
        });
    }

    private static <L> Striped<L> lazy(int stripes, Supplier<L> supplier) {
        if (stripes < LARGE_LAZY_CUTOFF) {
            return new SmallLazyStriped(stripes, supplier);
        }
        return new LargeLazyStriped(stripes, supplier);
    }

    public static Striped<Semaphore> semaphore(int stripes, final int permits) {
        return new CompactStriped(stripes, new Supplier<Semaphore>() {
            /* class com.google.common.util.concurrent.Striped.AnonymousClass3 */

            @Override // com.google.common.base.Supplier
            public Semaphore get() {
                return new PaddedSemaphore(permits);
            }
        });
    }

    public static Striped<Semaphore> lazyWeakSemaphore(int stripes, final int permits) {
        return lazy(stripes, new Supplier<Semaphore>() {
            /* class com.google.common.util.concurrent.Striped.AnonymousClass4 */

            @Override // com.google.common.base.Supplier
            public Semaphore get() {
                return new Semaphore(permits, false);
            }
        });
    }

    public static Striped<ReadWriteLock> readWriteLock(int stripes) {
        return new CompactStriped(stripes, READ_WRITE_LOCK_SUPPLIER);
    }

    public static Striped<ReadWriteLock> lazyWeakReadWriteLock(int stripes) {
        return lazy(stripes, READ_WRITE_LOCK_SUPPLIER);
    }

    private static abstract class PowerOfTwoStriped<L> extends Striped<L> {
        final int mask;

        PowerOfTwoStriped(int stripes) {
            super();
            Preconditions.checkArgument(stripes > 0, "Stripes must be positive");
            this.mask = stripes > 1073741824 ? -1 : Striped.ceilToPowerOfTwo(stripes) - 1;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.Striped
        public final int indexFor(Object key) {
            return this.mask & Striped.smear(key.hashCode());
        }

        @Override // com.google.common.util.concurrent.Striped
        public final L get(Object key) {
            return getAt(indexFor(key));
        }
    }

    private static class CompactStriped<L> extends PowerOfTwoStriped<L> {
        private final Object[] array;

        private CompactStriped(int stripes, Supplier<L> supplier) {
            super(stripes);
            Preconditions.checkArgument(stripes <= 1073741824, "Stripes must be <= 2^30)");
            this.array = new Object[(this.mask + 1)];
            int i = 0;
            while (true) {
                Object[] objArr = this.array;
                if (i < objArr.length) {
                    objArr[i] = supplier.get();
                    i++;
                } else {
                    return;
                }
            }
        }

        @Override // com.google.common.util.concurrent.Striped
        public L getAt(int index) {
            return (L) this.array[index];
        }

        @Override // com.google.common.util.concurrent.Striped
        public int size() {
            return this.array.length;
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public static class SmallLazyStriped<L> extends PowerOfTwoStriped<L> {
        final AtomicReferenceArray<ArrayReference<? extends L>> locks;
        final ReferenceQueue<L> queue = new ReferenceQueue<>();
        final int size;
        final Supplier<L> supplier;

        SmallLazyStriped(int stripes, Supplier<L> supplier2) {
            super(stripes);
            this.size = this.mask == -1 ? Integer.MAX_VALUE : this.mask + 1;
            this.locks = new AtomicReferenceArray<>(this.size);
            this.supplier = supplier2;
        }

        @Override // com.google.common.util.concurrent.Striped
        public L getAt(int index) {
            if (this.size != Integer.MAX_VALUE) {
                Preconditions.checkElementIndex(index, size());
            }
            ArrayReference<? extends L> existingRef = this.locks.get(index);
            L existing = existingRef == null ? null : (L) existingRef.get();
            if (existing != null) {
                return existing;
            }
            L created = this.supplier.get();
            ArrayReference<L> newRef = new ArrayReference<>(created, index, this.queue);
            while (!this.locks.compareAndSet(index, existingRef, newRef)) {
                existingRef = this.locks.get(index);
                L existing2 = existingRef == null ? null : (L) existingRef.get();
                if (existing2 != null) {
                    return existing2;
                }
            }
            drainQueue();
            return created;
        }

        private void drainQueue() {
            while (true) {
                Reference<? extends L> ref = this.queue.poll();
                if (ref != null) {
                    ArrayReference<? extends L> arrayRef = (ArrayReference) ref;
                    this.locks.compareAndSet(arrayRef.index, arrayRef, null);
                } else {
                    return;
                }
            }
        }

        @Override // com.google.common.util.concurrent.Striped
        public int size() {
            return this.size;
        }

        /* access modifiers changed from: private */
        public static final class ArrayReference<L> extends WeakReference<L> {
            final int index;

            ArrayReference(L referent, int index2, ReferenceQueue<L> queue) {
                super(referent, queue);
                this.index = index2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public static class LargeLazyStriped<L> extends PowerOfTwoStriped<L> {
        final ConcurrentMap<Integer, L> locks;
        final int size;
        final Supplier<L> supplier;

        /* JADX WARN: Type inference failed for: r0v3, types: [com.google.common.collect.MapMaker] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        LargeLazyStriped(int r3, com.google.common.base.Supplier<L> r4) {
            /*
                r2 = this;
                r2.<init>(r3)
                int r0 = r2.mask
                r1 = -1
                if (r0 != r1) goto L_0x000c
                r0 = 2147483647(0x7fffffff, float:NaN)
                goto L_0x0010
            L_0x000c:
                int r0 = r2.mask
                int r0 = r0 + 1
            L_0x0010:
                r2.size = r0
                r2.supplier = r4
                com.google.common.collect.MapMaker r0 = new com.google.common.collect.MapMaker
                r0.<init>()
                com.google.common.collect.MapMaker r0 = r0.weakValues()
                java.util.concurrent.ConcurrentMap r0 = r0.makeMap()
                r2.locks = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Striped.LargeLazyStriped.<init>(int, com.google.common.base.Supplier):void");
        }

        @Override // com.google.common.util.concurrent.Striped
        public L getAt(int index) {
            if (this.size != Integer.MAX_VALUE) {
                Preconditions.checkElementIndex(index, size());
            }
            L existing = this.locks.get(Integer.valueOf(index));
            if (existing != null) {
                return existing;
            }
            L created = this.supplier.get();
            return (L) MoreObjects.firstNonNull(this.locks.putIfAbsent(Integer.valueOf(index), created), created);
        }

        @Override // com.google.common.util.concurrent.Striped
        public int size() {
            return this.size;
        }
    }

    /* access modifiers changed from: private */
    public static int ceilToPowerOfTwo(int x) {
        return 1 << IntMath.log2(x, RoundingMode.CEILING);
    }

    /* access modifiers changed from: private */
    public static int smear(int hashCode) {
        int hashCode2 = hashCode ^ ((hashCode >>> 20) ^ (hashCode >>> 12));
        return ((hashCode2 >>> 7) ^ hashCode2) ^ (hashCode2 >>> 4);
    }

    /* access modifiers changed from: private */
    public static class PaddedLock extends ReentrantLock {
        long q1;
        long q2;
        long q3;

        PaddedLock() {
            super(false);
        }
    }

    /* access modifiers changed from: private */
    public static class PaddedSemaphore extends Semaphore {
        long q1;
        long q2;
        long q3;

        PaddedSemaphore(int permits) {
            super(permits, false);
        }
    }
}
