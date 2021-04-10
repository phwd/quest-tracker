package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.MapMaker;
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
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Beta
@GwtIncompatible
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
    private static final Supplier<ReadWriteLock> WEAK_SAFE_READ_WRITE_LOCK_SUPPLIER = new Supplier<ReadWriteLock>() {
        /* class com.google.common.util.concurrent.Striped.AnonymousClass6 */

        @Override // com.google.common.base.Supplier
        public ReadWriteLock get() {
            return new WeakSafeReadWriteLock();
        }
    };

    /* access modifiers changed from: private */
    public static int smear(int i) {
        int i2 = i ^ ((i >>> 20) ^ (i >>> 12));
        return (i2 >>> 4) ^ ((i2 >>> 7) ^ i2);
    }

    public abstract L get(Object obj);

    public abstract L getAt(int i);

    /* access modifiers changed from: package-private */
    public abstract int indexFor(Object obj);

    public abstract int size();

    private Striped() {
    }

    public Iterable<L> bulkGet(Iterable<?> iterable) {
        Object[] array = Iterables.toArray(iterable, Object.class);
        if (array.length == 0) {
            return ImmutableList.of();
        }
        int[] iArr = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            iArr[i] = indexFor(array[i]);
        }
        Arrays.sort(iArr);
        int i2 = iArr[0];
        array[0] = getAt(i2);
        for (int i3 = 1; i3 < array.length; i3++) {
            int i4 = iArr[i3];
            if (i4 == i2) {
                array[i3] = array[i3 - 1];
            } else {
                array[i3] = getAt(i4);
                i2 = i4;
            }
        }
        return Collections.unmodifiableList(Arrays.asList(array));
    }

    static <L> Striped<L> custom(int i, Supplier<L> supplier) {
        return new CompactStriped(i, supplier);
    }

    public static Striped<Lock> lock(int i) {
        return custom(i, new Supplier<Lock>() {
            /* class com.google.common.util.concurrent.Striped.AnonymousClass1 */

            @Override // com.google.common.base.Supplier
            public Lock get() {
                return new PaddedLock();
            }
        });
    }

    public static Striped<Lock> lazyWeakLock(int i) {
        return lazy(i, new Supplier<Lock>() {
            /* class com.google.common.util.concurrent.Striped.AnonymousClass2 */

            @Override // com.google.common.base.Supplier
            public Lock get() {
                return new ReentrantLock(false);
            }
        });
    }

    private static <L> Striped<L> lazy(int i, Supplier<L> supplier) {
        if (i < 1024) {
            return new SmallLazyStriped(i, supplier);
        }
        return new LargeLazyStriped(i, supplier);
    }

    public static Striped<Semaphore> semaphore(int i, final int i2) {
        return custom(i, new Supplier<Semaphore>() {
            /* class com.google.common.util.concurrent.Striped.AnonymousClass3 */

            @Override // com.google.common.base.Supplier
            public Semaphore get() {
                return new PaddedSemaphore(i2);
            }
        });
    }

    public static Striped<Semaphore> lazyWeakSemaphore(int i, final int i2) {
        return lazy(i, new Supplier<Semaphore>() {
            /* class com.google.common.util.concurrent.Striped.AnonymousClass4 */

            @Override // com.google.common.base.Supplier
            public Semaphore get() {
                return new Semaphore(i2, false);
            }
        });
    }

    public static Striped<ReadWriteLock> readWriteLock(int i) {
        return custom(i, READ_WRITE_LOCK_SUPPLIER);
    }

    public static Striped<ReadWriteLock> lazyWeakReadWriteLock(int i) {
        return lazy(i, WEAK_SAFE_READ_WRITE_LOCK_SUPPLIER);
    }

    /* access modifiers changed from: private */
    public static final class WeakSafeReadWriteLock implements ReadWriteLock {
        private final ReadWriteLock delegate = new ReentrantReadWriteLock();

        WeakSafeReadWriteLock() {
        }

        public Lock readLock() {
            return new WeakSafeLock(this.delegate.readLock(), this);
        }

        public Lock writeLock() {
            return new WeakSafeLock(this.delegate.writeLock(), this);
        }
    }

    private static final class WeakSafeLock extends ForwardingLock {
        private final Lock delegate;
        private final WeakSafeReadWriteLock strongReference;

        WeakSafeLock(Lock lock, WeakSafeReadWriteLock weakSafeReadWriteLock) {
            this.delegate = lock;
            this.strongReference = weakSafeReadWriteLock;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.ForwardingLock
        public Lock delegate() {
            return this.delegate;
        }

        @Override // com.google.common.util.concurrent.ForwardingLock
        public Condition newCondition() {
            return new WeakSafeCondition(this.delegate.newCondition(), this.strongReference);
        }
    }

    private static final class WeakSafeCondition extends ForwardingCondition {
        private final Condition delegate;
        private final WeakSafeReadWriteLock strongReference;

        WeakSafeCondition(Condition condition, WeakSafeReadWriteLock weakSafeReadWriteLock) {
            this.delegate = condition;
            this.strongReference = weakSafeReadWriteLock;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.ForwardingCondition
        public Condition delegate() {
            return this.delegate;
        }
    }

    private static abstract class PowerOfTwoStriped<L> extends Striped<L> {
        final int mask;

        PowerOfTwoStriped(int i) {
            super();
            int i2;
            Preconditions.checkArgument(i > 0, "Stripes must be positive");
            if (i > 1073741824) {
                i2 = -1;
            } else {
                i2 = Striped.ceilToPowerOfTwo(i) - 1;
            }
            this.mask = i2;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.Striped
        public final int indexFor(Object obj) {
            return Striped.smear(obj.hashCode()) & this.mask;
        }

        @Override // com.google.common.util.concurrent.Striped
        public final L get(Object obj) {
            return getAt(indexFor(obj));
        }
    }

    /* access modifiers changed from: private */
    public static class CompactStriped<L> extends PowerOfTwoStriped<L> {
        private final Object[] array;

        private CompactStriped(int i, Supplier<L> supplier) {
            super(i);
            int i2 = 0;
            Preconditions.checkArgument(i <= 1073741824, "Stripes must be <= 2^30)");
            this.array = new Object[(this.mask + 1)];
            while (true) {
                Object[] objArr = this.array;
                if (i2 < objArr.length) {
                    objArr[i2] = supplier.get();
                    i2++;
                } else {
                    return;
                }
            }
        }

        @Override // com.google.common.util.concurrent.Striped
        public L getAt(int i) {
            return (L) this.array[i];
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

        SmallLazyStriped(int i, Supplier<L> supplier2) {
            super(i);
            this.size = this.mask == -1 ? Integer.MAX_VALUE : this.mask + 1;
            this.locks = new AtomicReferenceArray<>(this.size);
            this.supplier = supplier2;
        }

        @Override // com.google.common.util.concurrent.Striped
        public L getAt(int i) {
            L l;
            if (this.size != Integer.MAX_VALUE) {
                Preconditions.checkElementIndex(i, size());
            }
            ArrayReference<? extends L> arrayReference = this.locks.get(i);
            L l2 = arrayReference == null ? null : (L) arrayReference.get();
            if (l2 != null) {
                return l2;
            }
            L l3 = this.supplier.get();
            ArrayReference<? extends L> arrayReference2 = new ArrayReference<>(l3, i, this.queue);
            while (!this.locks.compareAndSet(i, arrayReference, arrayReference2)) {
                arrayReference = this.locks.get(i);
                if (arrayReference == null) {
                    l = null;
                    continue;
                } else {
                    l = (L) arrayReference.get();
                    continue;
                }
                if (l != null) {
                    return l;
                }
            }
            drainQueue();
            return l3;
        }

        private void drainQueue() {
            while (true) {
                Reference<? extends L> poll = this.queue.poll();
                if (poll != null) {
                    ArrayReference<? extends L> arrayReference = (ArrayReference) poll;
                    this.locks.compareAndSet(arrayReference.index, arrayReference, null);
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

            ArrayReference(L l, int i, ReferenceQueue<L> referenceQueue) {
                super(l, referenceQueue);
                this.index = i;
            }
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public static class LargeLazyStriped<L> extends PowerOfTwoStriped<L> {
        final ConcurrentMap<Integer, L> locks;
        final int size;
        final Supplier<L> supplier;

        LargeLazyStriped(int i, Supplier<L> supplier2) {
            super(i);
            this.size = this.mask == -1 ? Integer.MAX_VALUE : this.mask + 1;
            this.supplier = supplier2;
            this.locks = new MapMaker().weakValues().makeMap();
        }

        @Override // com.google.common.util.concurrent.Striped
        public L getAt(int i) {
            if (this.size != Integer.MAX_VALUE) {
                Preconditions.checkElementIndex(i, size());
            }
            L l = this.locks.get(Integer.valueOf(i));
            if (l != null) {
                return l;
            }
            L l2 = this.supplier.get();
            return (L) MoreObjects.firstNonNull(this.locks.putIfAbsent(Integer.valueOf(i), l2), l2);
        }

        @Override // com.google.common.util.concurrent.Striped
        public int size() {
            return this.size;
        }
    }

    /* access modifiers changed from: private */
    public static int ceilToPowerOfTwo(int i) {
        return 1 << IntMath.log2(i, RoundingMode.CEILING);
    }

    /* access modifiers changed from: private */
    public static class PaddedLock extends ReentrantLock {
        long unused1;
        long unused2;
        long unused3;

        PaddedLock() {
            super(false);
        }
    }

    /* access modifiers changed from: private */
    public static class PaddedSemaphore extends Semaphore {
        long unused1;
        long unused2;
        long unused3;

        PaddedSemaphore(int i) {
            super(i, false);
        }
    }
}
