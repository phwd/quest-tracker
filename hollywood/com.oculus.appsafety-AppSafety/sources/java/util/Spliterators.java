package java.util;

import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

public final class Spliterators {
    private static final Spliterator.OfDouble EMPTY_DOUBLE_SPLITERATOR = new EmptySpliterator.OfDouble();
    private static final Spliterator.OfInt EMPTY_INT_SPLITERATOR = new EmptySpliterator.OfInt();
    private static final Spliterator.OfLong EMPTY_LONG_SPLITERATOR = new EmptySpliterator.OfLong();
    private static final Spliterator<Object> EMPTY_SPLITERATOR = new EmptySpliterator.OfRef();

    private Spliterators() {
    }

    public static <T> Spliterator<T> emptySpliterator() {
        return (Spliterator<T>) EMPTY_SPLITERATOR;
    }

    public static Spliterator.OfInt emptyIntSpliterator() {
        return EMPTY_INT_SPLITERATOR;
    }

    public static Spliterator.OfLong emptyLongSpliterator() {
        return EMPTY_LONG_SPLITERATOR;
    }

    public static Spliterator.OfDouble emptyDoubleSpliterator() {
        return EMPTY_DOUBLE_SPLITERATOR;
    }

    public static <T> Spliterator<T> spliterator(Object[] array, int additionalCharacteristics) {
        return new ArraySpliterator((Object[]) Objects.requireNonNull(array), additionalCharacteristics);
    }

    public static <T> Spliterator<T> spliterator(Object[] array, int fromIndex, int toIndex, int additionalCharacteristics) {
        checkFromToBounds(((Object[]) Objects.requireNonNull(array)).length, fromIndex, toIndex);
        return new ArraySpliterator(array, fromIndex, toIndex, additionalCharacteristics);
    }

    public static Spliterator.OfInt spliterator(int[] array, int additionalCharacteristics) {
        return new IntArraySpliterator((int[]) Objects.requireNonNull(array), additionalCharacteristics);
    }

    public static Spliterator.OfInt spliterator(int[] array, int fromIndex, int toIndex, int additionalCharacteristics) {
        checkFromToBounds(((int[]) Objects.requireNonNull(array)).length, fromIndex, toIndex);
        return new IntArraySpliterator(array, fromIndex, toIndex, additionalCharacteristics);
    }

    public static Spliterator.OfLong spliterator(long[] array, int additionalCharacteristics) {
        return new LongArraySpliterator((long[]) Objects.requireNonNull(array), additionalCharacteristics);
    }

    public static Spliterator.OfLong spliterator(long[] array, int fromIndex, int toIndex, int additionalCharacteristics) {
        checkFromToBounds(((long[]) Objects.requireNonNull(array)).length, fromIndex, toIndex);
        return new LongArraySpliterator(array, fromIndex, toIndex, additionalCharacteristics);
    }

    public static Spliterator.OfDouble spliterator(double[] array, int additionalCharacteristics) {
        return new DoubleArraySpliterator((double[]) Objects.requireNonNull(array), additionalCharacteristics);
    }

    public static Spliterator.OfDouble spliterator(double[] array, int fromIndex, int toIndex, int additionalCharacteristics) {
        checkFromToBounds(((double[]) Objects.requireNonNull(array)).length, fromIndex, toIndex);
        return new DoubleArraySpliterator(array, fromIndex, toIndex, additionalCharacteristics);
    }

    private static void checkFromToBounds(int arrayLength, int origin, int fence) {
        if (origin > fence) {
            throw new ArrayIndexOutOfBoundsException("origin(" + origin + ") > fence(" + fence + ")");
        } else if (origin < 0) {
            throw new ArrayIndexOutOfBoundsException(origin);
        } else if (fence > arrayLength) {
            throw new ArrayIndexOutOfBoundsException(fence);
        }
    }

    public static <T> Spliterator<T> spliterator(Collection<? extends T> c, int characteristics) {
        return new IteratorSpliterator((Collection) Objects.requireNonNull(c), characteristics);
    }

    public static <T> Spliterator<T> spliterator(Iterator<? extends T> iterator, long size, int characteristics) {
        return new IteratorSpliterator((Iterator) Objects.requireNonNull(iterator), size, characteristics);
    }

    public static <T> Spliterator<T> spliteratorUnknownSize(Iterator<? extends T> iterator, int characteristics) {
        return new IteratorSpliterator((Iterator) Objects.requireNonNull(iterator), characteristics);
    }

    public static Spliterator.OfInt spliterator(PrimitiveIterator.OfInt iterator, long size, int characteristics) {
        return new IntIteratorSpliterator((PrimitiveIterator.OfInt) Objects.requireNonNull(iterator), size, characteristics);
    }

    public static Spliterator.OfInt spliteratorUnknownSize(PrimitiveIterator.OfInt iterator, int characteristics) {
        return new IntIteratorSpliterator((PrimitiveIterator.OfInt) Objects.requireNonNull(iterator), characteristics);
    }

    public static Spliterator.OfLong spliterator(PrimitiveIterator.OfLong iterator, long size, int characteristics) {
        return new LongIteratorSpliterator((PrimitiveIterator.OfLong) Objects.requireNonNull(iterator), size, characteristics);
    }

    public static Spliterator.OfLong spliteratorUnknownSize(PrimitiveIterator.OfLong iterator, int characteristics) {
        return new LongIteratorSpliterator((PrimitiveIterator.OfLong) Objects.requireNonNull(iterator), characteristics);
    }

    public static Spliterator.OfDouble spliterator(PrimitiveIterator.OfDouble iterator, long size, int characteristics) {
        return new DoubleIteratorSpliterator((PrimitiveIterator.OfDouble) Objects.requireNonNull(iterator), size, characteristics);
    }

    public static Spliterator.OfDouble spliteratorUnknownSize(PrimitiveIterator.OfDouble iterator, int characteristics) {
        return new DoubleIteratorSpliterator((PrimitiveIterator.OfDouble) Objects.requireNonNull(iterator), characteristics);
    }

    public static <T> Iterator<T> iterator(final Spliterator<? extends T> spliterator) {
        Objects.requireNonNull(spliterator);
        return new Object() {
            /* class java.util.Spliterators.AnonymousClass1Adapter */
            T nextElement;
            boolean valueReady = false;

            @Override // java.util.function.Consumer
            public void accept(T t) {
                this.valueReady = true;
                this.nextElement = t;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (!this.valueReady) {
                    Spliterator.this.tryAdvance(this);
                }
                return this.valueReady;
            }

            @Override // java.util.Iterator
            public T next() {
                if (this.valueReady || hasNext()) {
                    this.valueReady = false;
                    return this.nextElement;
                }
                throw new NoSuchElementException();
            }
        };
    }

    public static PrimitiveIterator.OfInt iterator(final Spliterator.OfInt spliterator) {
        Objects.requireNonNull(spliterator);
        return new Object() {
            /* class java.util.Spliterators.AnonymousClass2Adapter */
            int nextElement;
            boolean valueReady = false;

            @Override // java.util.function.IntConsumer
            public void accept(int t) {
                this.valueReady = true;
                this.nextElement = t;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (!this.valueReady) {
                    Spliterator.OfInt.this.tryAdvance((IntConsumer) this);
                }
                return this.valueReady;
            }

            @Override // java.util.PrimitiveIterator.OfInt
            public int nextInt() {
                if (this.valueReady || hasNext()) {
                    this.valueReady = false;
                    return this.nextElement;
                }
                throw new NoSuchElementException();
            }
        };
    }

    public static PrimitiveIterator.OfLong iterator(final Spliterator.OfLong spliterator) {
        Objects.requireNonNull(spliterator);
        return new Object() {
            /* class java.util.Spliterators.AnonymousClass3Adapter */
            long nextElement;
            boolean valueReady = false;

            @Override // java.util.function.LongConsumer
            public void accept(long t) {
                this.valueReady = true;
                this.nextElement = t;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (!this.valueReady) {
                    Spliterator.OfLong.this.tryAdvance((LongConsumer) this);
                }
                return this.valueReady;
            }

            @Override // java.util.PrimitiveIterator.OfLong
            public long nextLong() {
                if (this.valueReady || hasNext()) {
                    this.valueReady = false;
                    return this.nextElement;
                }
                throw new NoSuchElementException();
            }
        };
    }

    public static PrimitiveIterator.OfDouble iterator(final Spliterator.OfDouble spliterator) {
        Objects.requireNonNull(spliterator);
        return new Object() {
            /* class java.util.Spliterators.AnonymousClass4Adapter */
            double nextElement;
            boolean valueReady = false;

            @Override // java.util.function.DoubleConsumer
            public void accept(double t) {
                this.valueReady = true;
                this.nextElement = t;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (!this.valueReady) {
                    Spliterator.OfDouble.this.tryAdvance((DoubleConsumer) this);
                }
                return this.valueReady;
            }

            @Override // java.util.PrimitiveIterator.OfDouble
            public double nextDouble() {
                if (this.valueReady || hasNext()) {
                    this.valueReady = false;
                    return this.nextElement;
                }
                throw new NoSuchElementException();
            }
        };
    }

    private static abstract class EmptySpliterator<T, S extends Spliterator<T>, C> {
        EmptySpliterator() {
        }

        public S trySplit() {
            return null;
        }

        public boolean tryAdvance(C consumer) {
            Objects.requireNonNull(consumer);
            return false;
        }

        public void forEachRemaining(C consumer) {
            Objects.requireNonNull(consumer);
        }

        public long estimateSize() {
            return 0;
        }

        public int characteristics() {
            return 16448;
        }

        private static final class OfRef<T> extends EmptySpliterator<T, Spliterator<T>, Consumer<? super T>> implements Spliterator<T> {
            @Override // java.util.Spliterator
            public /* bridge */ /* synthetic */ void forEachRemaining(Consumer consumer) {
                super.forEachRemaining((Object) consumer);
            }

            @Override // java.util.Spliterator
            public /* bridge */ /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return super.tryAdvance((Object) consumer);
            }

            OfRef() {
            }
        }

        private static final class OfInt extends EmptySpliterator<Integer, Spliterator.OfInt, IntConsumer> implements Spliterator.OfInt {
            @Override // java.util.Spliterator.OfInt
            public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
                super.forEachRemaining((Object) intConsumer);
            }

            @Override // java.util.Spliterator.OfInt
            public /* bridge */ /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
                return super.tryAdvance((Object) intConsumer);
            }

            OfInt() {
            }
        }

        private static final class OfLong extends EmptySpliterator<Long, Spliterator.OfLong, LongConsumer> implements Spliterator.OfLong {
            @Override // java.util.Spliterator.OfLong
            public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
                super.forEachRemaining((Object) longConsumer);
            }

            @Override // java.util.Spliterator.OfLong
            public /* bridge */ /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
                return super.tryAdvance((Object) longConsumer);
            }

            OfLong() {
            }
        }

        private static final class OfDouble extends EmptySpliterator<Double, Spliterator.OfDouble, DoubleConsumer> implements Spliterator.OfDouble {
            @Override // java.util.Spliterator.OfDouble
            public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
                super.forEachRemaining((Object) doubleConsumer);
            }

            @Override // java.util.Spliterator.OfDouble
            public /* bridge */ /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
                return super.tryAdvance((Object) doubleConsumer);
            }

            OfDouble() {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class ArraySpliterator<T> implements Spliterator<T> {
        private final Object[] array;
        private final int characteristics;
        private final int fence;
        private int index;

        public ArraySpliterator(Object[] array2, int additionalCharacteristics) {
            this(array2, 0, array2.length, additionalCharacteristics);
        }

        public ArraySpliterator(Object[] array2, int origin, int fence2, int additionalCharacteristics) {
            this.array = array2;
            this.index = origin;
            this.fence = fence2;
            this.characteristics = additionalCharacteristics | 64 | 16384;
        }

        @Override // java.util.Spliterator
        public Spliterator<T> trySplit() {
            int lo = this.index;
            int mid = (this.fence + lo) >>> 1;
            if (lo >= mid) {
                return null;
            }
            Object[] objArr = this.array;
            this.index = mid;
            return new ArraySpliterator(objArr, lo, mid, this.characteristics);
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super T> action) {
            if (action != null) {
                Object[] a = this.array;
                int length = a.length;
                int hi = this.fence;
                if (length >= hi) {
                    int i = this.index;
                    int i2 = i;
                    if (i >= 0) {
                        this.index = hi;
                        if (i2 < hi) {
                            do {
                                action.accept(a[i2]);
                                i2++;
                            } while (i2 < hi);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super T> action) {
            if (action != null) {
                int i = this.index;
                if (i < 0 || i >= this.fence) {
                    return false;
                }
                Object[] objArr = this.array;
                this.index = i + 1;
                action.accept(objArr[i]);
                return true;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return (long) (this.fence - this.index);
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.characteristics;
        }

        @Override // java.util.Spliterator
        public Comparator<? super T> getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class IntArraySpliterator implements Spliterator.OfInt {
        private final int[] array;
        private final int characteristics;
        private final int fence;
        private int index;

        public IntArraySpliterator(int[] array2, int additionalCharacteristics) {
            this(array2, 0, array2.length, additionalCharacteristics);
        }

        public IntArraySpliterator(int[] array2, int origin, int fence2, int additionalCharacteristics) {
            this.array = array2;
            this.index = origin;
            this.fence = fence2;
            this.characteristics = additionalCharacteristics | 64 | 16384;
        }

        @Override // java.util.Spliterator.OfInt, java.util.Spliterator.OfInt, java.util.Spliterator.OfInt, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public Spliterator.OfInt trySplit() {
            int lo = this.index;
            int mid = (this.fence + lo) >>> 1;
            if (lo >= mid) {
                return null;
            }
            int[] iArr = this.array;
            this.index = mid;
            return new IntArraySpliterator(iArr, lo, mid, this.characteristics);
        }

        @Override // java.util.Spliterator.OfInt
        public void forEachRemaining(IntConsumer action) {
            if (action != null) {
                int[] a = this.array;
                int length = a.length;
                int hi = this.fence;
                if (length >= hi) {
                    int i = this.index;
                    int i2 = i;
                    if (i >= 0) {
                        this.index = hi;
                        if (i2 < hi) {
                            do {
                                action.accept(a[i2]);
                                i2++;
                            } while (i2 < hi);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator.OfInt
        public boolean tryAdvance(IntConsumer action) {
            if (action != null) {
                int i = this.index;
                if (i < 0 || i >= this.fence) {
                    return false;
                }
                int[] iArr = this.array;
                this.index = i + 1;
                action.accept(iArr[i]);
                return true;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return (long) (this.fence - this.index);
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.characteristics;
        }

        @Override // java.util.Spliterator
        public Comparator<? super Integer> getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class LongArraySpliterator implements Spliterator.OfLong {
        private final long[] array;
        private final int characteristics;
        private final int fence;
        private int index;

        public LongArraySpliterator(long[] array2, int additionalCharacteristics) {
            this(array2, 0, array2.length, additionalCharacteristics);
        }

        public LongArraySpliterator(long[] array2, int origin, int fence2, int additionalCharacteristics) {
            this.array = array2;
            this.index = origin;
            this.fence = fence2;
            this.characteristics = additionalCharacteristics | 64 | 16384;
        }

        @Override // java.util.Spliterator.OfLong, java.util.Spliterator.OfLong, java.util.Spliterator.OfLong, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public Spliterator.OfLong trySplit() {
            int lo = this.index;
            int mid = (this.fence + lo) >>> 1;
            if (lo >= mid) {
                return null;
            }
            long[] jArr = this.array;
            this.index = mid;
            return new LongArraySpliterator(jArr, lo, mid, this.characteristics);
        }

        @Override // java.util.Spliterator.OfLong
        public void forEachRemaining(LongConsumer action) {
            if (action != null) {
                long[] a = this.array;
                int length = a.length;
                int hi = this.fence;
                if (length >= hi) {
                    int i = this.index;
                    int i2 = i;
                    if (i >= 0) {
                        this.index = hi;
                        if (i2 < hi) {
                            do {
                                action.accept(a[i2]);
                                i2++;
                            } while (i2 < hi);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator.OfLong
        public boolean tryAdvance(LongConsumer action) {
            if (action != null) {
                int i = this.index;
                if (i < 0 || i >= this.fence) {
                    return false;
                }
                long[] jArr = this.array;
                this.index = i + 1;
                action.accept(jArr[i]);
                return true;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return (long) (this.fence - this.index);
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.characteristics;
        }

        @Override // java.util.Spliterator
        public Comparator<? super Long> getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class DoubleArraySpliterator implements Spliterator.OfDouble {
        private final double[] array;
        private final int characteristics;
        private final int fence;
        private int index;

        public DoubleArraySpliterator(double[] array2, int additionalCharacteristics) {
            this(array2, 0, array2.length, additionalCharacteristics);
        }

        public DoubleArraySpliterator(double[] array2, int origin, int fence2, int additionalCharacteristics) {
            this.array = array2;
            this.index = origin;
            this.fence = fence2;
            this.characteristics = additionalCharacteristics | 64 | 16384;
        }

        @Override // java.util.Spliterator.OfDouble, java.util.Spliterator.OfDouble, java.util.Spliterator.OfDouble, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public Spliterator.OfDouble trySplit() {
            int lo = this.index;
            int mid = (this.fence + lo) >>> 1;
            if (lo >= mid) {
                return null;
            }
            double[] dArr = this.array;
            this.index = mid;
            return new DoubleArraySpliterator(dArr, lo, mid, this.characteristics);
        }

        @Override // java.util.Spliterator.OfDouble
        public void forEachRemaining(DoubleConsumer action) {
            if (action != null) {
                double[] a = this.array;
                int length = a.length;
                int hi = this.fence;
                if (length >= hi) {
                    int i = this.index;
                    int i2 = i;
                    if (i >= 0) {
                        this.index = hi;
                        if (i2 < hi) {
                            do {
                                action.accept(a[i2]);
                                i2++;
                            } while (i2 < hi);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator.OfDouble
        public boolean tryAdvance(DoubleConsumer action) {
            if (action != null) {
                int i = this.index;
                if (i < 0 || i >= this.fence) {
                    return false;
                }
                double[] dArr = this.array;
                this.index = i + 1;
                action.accept(dArr[i]);
                return true;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return (long) (this.fence - this.index);
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.characteristics;
        }

        @Override // java.util.Spliterator
        public Comparator<? super Double> getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
        }
    }

    public static abstract class AbstractSpliterator<T> implements Spliterator<T> {
        static final int BATCH_UNIT = 1024;
        static final int MAX_BATCH = 33554432;
        private int batch;
        private final int characteristics;
        private long est;

        protected AbstractSpliterator(long est2, int additionalCharacteristics) {
            int i;
            this.est = est2;
            if ((additionalCharacteristics & 64) != 0) {
                i = additionalCharacteristics | 16384;
            } else {
                i = additionalCharacteristics;
            }
            this.characteristics = i;
        }

        static final class HoldingConsumer<T> implements Consumer<T> {
            Object value;

            HoldingConsumer() {
            }

            @Override // java.util.function.Consumer
            public void accept(T value2) {
                this.value = value2;
            }
        }

        @Override // java.util.Spliterator
        public Spliterator<T> trySplit() {
            HoldingConsumer<T> holder = new HoldingConsumer<>();
            long s = this.est;
            if (s <= 1 || !tryAdvance(holder)) {
                return null;
            }
            int n = this.batch + 1024;
            if (((long) n) > s) {
                n = (int) s;
            }
            if (n > MAX_BATCH) {
                n = MAX_BATCH;
            }
            Object[] a = new Object[n];
            int j = 0;
            do {
                a[j] = holder.value;
                j++;
                if (j >= n) {
                    break;
                }
            } while (tryAdvance(holder));
            this.batch = j;
            long j2 = this.est;
            if (j2 != Long.MAX_VALUE) {
                this.est = j2 - ((long) j);
            }
            return new ArraySpliterator(a, 0, j, characteristics());
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.est;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.characteristics;
        }
    }

    public static abstract class AbstractIntSpliterator implements Spliterator.OfInt {
        static final int BATCH_UNIT = 1024;
        static final int MAX_BATCH = 33554432;
        private int batch;
        private final int characteristics;
        private long est;

        protected AbstractIntSpliterator(long est2, int additionalCharacteristics) {
            int i;
            this.est = est2;
            if ((additionalCharacteristics & 64) != 0) {
                i = additionalCharacteristics | 16384;
            } else {
                i = additionalCharacteristics;
            }
            this.characteristics = i;
        }

        /* access modifiers changed from: package-private */
        public static final class HoldingIntConsumer implements IntConsumer {
            int value;

            HoldingIntConsumer() {
            }

            @Override // java.util.function.IntConsumer
            public void accept(int value2) {
                this.value = value2;
            }
        }

        @Override // java.util.Spliterator.OfInt, java.util.Spliterator.OfInt, java.util.Spliterator.OfInt, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public Spliterator.OfInt trySplit() {
            HoldingIntConsumer holder = new HoldingIntConsumer();
            long s = this.est;
            if (s <= 1 || !tryAdvance((IntConsumer) holder)) {
                return null;
            }
            int n = this.batch + 1024;
            if (((long) n) > s) {
                n = (int) s;
            }
            if (n > MAX_BATCH) {
                n = MAX_BATCH;
            }
            int[] a = new int[n];
            int j = 0;
            do {
                a[j] = holder.value;
                j++;
                if (j >= n) {
                    break;
                }
            } while (tryAdvance((IntConsumer) holder));
            this.batch = j;
            long j2 = this.est;
            if (j2 != Long.MAX_VALUE) {
                this.est = j2 - ((long) j);
            }
            return new IntArraySpliterator(a, 0, j, characteristics());
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.est;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.characteristics;
        }
    }

    public static abstract class AbstractLongSpliterator implements Spliterator.OfLong {
        static final int BATCH_UNIT = 1024;
        static final int MAX_BATCH = 33554432;
        private int batch;
        private final int characteristics;
        private long est;

        protected AbstractLongSpliterator(long est2, int additionalCharacteristics) {
            int i;
            this.est = est2;
            if ((additionalCharacteristics & 64) != 0) {
                i = additionalCharacteristics | 16384;
            } else {
                i = additionalCharacteristics;
            }
            this.characteristics = i;
        }

        /* access modifiers changed from: package-private */
        public static final class HoldingLongConsumer implements LongConsumer {
            long value;

            HoldingLongConsumer() {
            }

            @Override // java.util.function.LongConsumer
            public void accept(long value2) {
                this.value = value2;
            }
        }

        @Override // java.util.Spliterator.OfLong, java.util.Spliterator.OfLong, java.util.Spliterator.OfLong, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public Spliterator.OfLong trySplit() {
            HoldingLongConsumer holder = new HoldingLongConsumer();
            long s = this.est;
            if (s <= 1 || !tryAdvance((LongConsumer) holder)) {
                return null;
            }
            int n = this.batch + 1024;
            if (((long) n) > s) {
                n = (int) s;
            }
            if (n > MAX_BATCH) {
                n = MAX_BATCH;
            }
            long[] a = new long[n];
            int j = 0;
            do {
                a[j] = holder.value;
                j++;
                if (j >= n) {
                    break;
                }
            } while (tryAdvance((LongConsumer) holder));
            this.batch = j;
            long j2 = this.est;
            if (j2 != Long.MAX_VALUE) {
                this.est = j2 - ((long) j);
            }
            return new LongArraySpliterator(a, 0, j, characteristics());
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.est;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.characteristics;
        }
    }

    public static abstract class AbstractDoubleSpliterator implements Spliterator.OfDouble {
        static final int BATCH_UNIT = 1024;
        static final int MAX_BATCH = 33554432;
        private int batch;
        private final int characteristics;
        private long est;

        protected AbstractDoubleSpliterator(long est2, int additionalCharacteristics) {
            int i;
            this.est = est2;
            if ((additionalCharacteristics & 64) != 0) {
                i = additionalCharacteristics | 16384;
            } else {
                i = additionalCharacteristics;
            }
            this.characteristics = i;
        }

        /* access modifiers changed from: package-private */
        public static final class HoldingDoubleConsumer implements DoubleConsumer {
            double value;

            HoldingDoubleConsumer() {
            }

            @Override // java.util.function.DoubleConsumer
            public void accept(double value2) {
                this.value = value2;
            }
        }

        @Override // java.util.Spliterator.OfDouble, java.util.Spliterator.OfDouble, java.util.Spliterator.OfDouble, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public Spliterator.OfDouble trySplit() {
            HoldingDoubleConsumer holder = new HoldingDoubleConsumer();
            long s = this.est;
            if (s <= 1 || !tryAdvance((DoubleConsumer) holder)) {
                return null;
            }
            int n = this.batch + 1024;
            if (((long) n) > s) {
                n = (int) s;
            }
            if (n > MAX_BATCH) {
                n = MAX_BATCH;
            }
            double[] a = new double[n];
            int j = 0;
            do {
                a[j] = holder.value;
                j++;
                if (j >= n) {
                    break;
                }
            } while (tryAdvance((DoubleConsumer) holder));
            this.batch = j;
            long j2 = this.est;
            if (j2 != Long.MAX_VALUE) {
                this.est = j2 - ((long) j);
            }
            return new DoubleArraySpliterator(a, 0, j, characteristics());
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.est;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.characteristics;
        }
    }

    /* access modifiers changed from: package-private */
    public static class IteratorSpliterator<T> implements Spliterator<T> {
        static final int BATCH_UNIT = 1024;
        static final int MAX_BATCH = 33554432;
        private int batch;
        private final int characteristics;
        private final Collection<? extends T> collection;
        private long est;
        private Iterator<? extends T> it;

        public IteratorSpliterator(Collection<? extends T> collection2, int characteristics2) {
            int i;
            this.collection = collection2;
            this.it = null;
            if ((characteristics2 & 4096) == 0) {
                i = characteristics2 | 64 | 16384;
            } else {
                i = characteristics2;
            }
            this.characteristics = i;
        }

        public IteratorSpliterator(Iterator<? extends T> iterator, long size, int characteristics2) {
            int i;
            this.collection = null;
            this.it = iterator;
            this.est = size;
            if ((characteristics2 & 4096) == 0) {
                i = characteristics2 | 64 | 16384;
            } else {
                i = characteristics2;
            }
            this.characteristics = i;
        }

        public IteratorSpliterator(Iterator<? extends T> iterator, int characteristics2) {
            this.collection = null;
            this.it = iterator;
            this.est = Long.MAX_VALUE;
            this.characteristics = characteristics2 & -16449;
        }

        @Override // java.util.Spliterator
        public Spliterator<T> trySplit() {
            long s;
            Iterator<? extends T> it2 = this.it;
            Iterator<? extends T> i = it2;
            if (it2 == null) {
                Iterator<? extends T> it3 = this.collection.iterator();
                this.it = it3;
                i = it3;
                s = (long) this.collection.size();
                this.est = s;
            } else {
                s = this.est;
            }
            if (s <= 1 || !i.hasNext()) {
                return null;
            }
            int n = this.batch + 1024;
            if (((long) n) > s) {
                n = (int) s;
            }
            if (n > MAX_BATCH) {
                n = MAX_BATCH;
            }
            Object[] a = new Object[n];
            int j = 0;
            do {
                a[j] = i.next();
                j++;
                if (j >= n) {
                    break;
                }
            } while (i.hasNext());
            this.batch = j;
            long j2 = this.est;
            if (j2 != Long.MAX_VALUE) {
                this.est = j2 - ((long) j);
            }
            return new ArraySpliterator(a, 0, j, this.characteristics);
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super T> action) {
            if (action != null) {
                Iterator<? extends T> it2 = this.it;
                Iterator<? extends T> i = it2;
                if (it2 == null) {
                    Iterator<? extends T> it3 = this.collection.iterator();
                    this.it = it3;
                    i = it3;
                    this.est = (long) this.collection.size();
                }
                i.forEachRemaining(action);
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super T> action) {
            if (action != null) {
                if (this.it == null) {
                    this.it = this.collection.iterator();
                    this.est = (long) this.collection.size();
                }
                if (!this.it.hasNext()) {
                    return false;
                }
                action.accept((Object) this.it.next());
                return true;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            if (this.it != null) {
                return this.est;
            }
            this.it = this.collection.iterator();
            long size = (long) this.collection.size();
            this.est = size;
            return size;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.characteristics;
        }

        @Override // java.util.Spliterator
        public Comparator<? super T> getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class IntIteratorSpliterator implements Spliterator.OfInt {
        static final int BATCH_UNIT = 1024;
        static final int MAX_BATCH = 33554432;
        private int batch;
        private final int characteristics;
        private long est;
        private PrimitiveIterator.OfInt it;

        public IntIteratorSpliterator(PrimitiveIterator.OfInt iterator, long size, int characteristics2) {
            int i;
            this.it = iterator;
            this.est = size;
            if ((characteristics2 & 4096) == 0) {
                i = characteristics2 | 64 | 16384;
            } else {
                i = characteristics2;
            }
            this.characteristics = i;
        }

        public IntIteratorSpliterator(PrimitiveIterator.OfInt iterator, int characteristics2) {
            this.it = iterator;
            this.est = Long.MAX_VALUE;
            this.characteristics = characteristics2 & -16449;
        }

        @Override // java.util.Spliterator.OfInt, java.util.Spliterator.OfInt, java.util.Spliterator.OfInt, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public Spliterator.OfInt trySplit() {
            PrimitiveIterator.OfInt i = this.it;
            long s = this.est;
            if (s <= 1 || !i.hasNext()) {
                return null;
            }
            int n = this.batch + 1024;
            if (((long) n) > s) {
                n = (int) s;
            }
            if (n > MAX_BATCH) {
                n = MAX_BATCH;
            }
            int[] a = new int[n];
            int j = 0;
            do {
                a[j] = i.nextInt();
                j++;
                if (j >= n) {
                    break;
                }
            } while (i.hasNext());
            this.batch = j;
            long j2 = this.est;
            if (j2 != Long.MAX_VALUE) {
                this.est = j2 - ((long) j);
            }
            return new IntArraySpliterator(a, 0, j, this.characteristics);
        }

        @Override // java.util.Spliterator.OfInt
        public void forEachRemaining(IntConsumer action) {
            if (action != null) {
                this.it.forEachRemaining(action);
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator.OfInt
        public boolean tryAdvance(IntConsumer action) {
            if (action == null) {
                throw new NullPointerException();
            } else if (!this.it.hasNext()) {
                return false;
            } else {
                action.accept(this.it.nextInt());
                return true;
            }
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.est;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.characteristics;
        }

        @Override // java.util.Spliterator
        public Comparator<? super Integer> getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
        }
    }

    static final class LongIteratorSpliterator implements Spliterator.OfLong {
        static final int BATCH_UNIT = 1024;
        static final int MAX_BATCH = 33554432;
        private int batch;
        private final int characteristics;
        private long est;
        private PrimitiveIterator.OfLong it;

        public LongIteratorSpliterator(PrimitiveIterator.OfLong iterator, long size, int characteristics2) {
            int i;
            this.it = iterator;
            this.est = size;
            if ((characteristics2 & 4096) == 0) {
                i = characteristics2 | 64 | 16384;
            } else {
                i = characteristics2;
            }
            this.characteristics = i;
        }

        public LongIteratorSpliterator(PrimitiveIterator.OfLong iterator, int characteristics2) {
            this.it = iterator;
            this.est = Long.MAX_VALUE;
            this.characteristics = characteristics2 & -16449;
        }

        @Override // java.util.Spliterator.OfLong, java.util.Spliterator.OfLong, java.util.Spliterator.OfLong, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public Spliterator.OfLong trySplit() {
            PrimitiveIterator.OfLong i = this.it;
            long s = this.est;
            if (s <= 1 || !i.hasNext()) {
                return null;
            }
            int n = this.batch + 1024;
            if (((long) n) > s) {
                n = (int) s;
            }
            if (n > MAX_BATCH) {
                n = MAX_BATCH;
            }
            long[] a = new long[n];
            int j = 0;
            do {
                a[j] = i.nextLong();
                j++;
                if (j >= n) {
                    break;
                }
            } while (i.hasNext());
            this.batch = j;
            long j2 = this.est;
            if (j2 != Long.MAX_VALUE) {
                this.est = j2 - ((long) j);
            }
            return new LongArraySpliterator(a, 0, j, this.characteristics);
        }

        @Override // java.util.Spliterator.OfLong
        public void forEachRemaining(LongConsumer action) {
            if (action != null) {
                this.it.forEachRemaining(action);
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator.OfLong
        public boolean tryAdvance(LongConsumer action) {
            if (action == null) {
                throw new NullPointerException();
            } else if (!this.it.hasNext()) {
                return false;
            } else {
                action.accept(this.it.nextLong());
                return true;
            }
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.est;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.characteristics;
        }

        @Override // java.util.Spliterator
        public Comparator<? super Long> getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
        }
    }

    static final class DoubleIteratorSpliterator implements Spliterator.OfDouble {
        static final int BATCH_UNIT = 1024;
        static final int MAX_BATCH = 33554432;
        private int batch;
        private final int characteristics;
        private long est;
        private PrimitiveIterator.OfDouble it;

        public DoubleIteratorSpliterator(PrimitiveIterator.OfDouble iterator, long size, int characteristics2) {
            int i;
            this.it = iterator;
            this.est = size;
            if ((characteristics2 & 4096) == 0) {
                i = characteristics2 | 64 | 16384;
            } else {
                i = characteristics2;
            }
            this.characteristics = i;
        }

        public DoubleIteratorSpliterator(PrimitiveIterator.OfDouble iterator, int characteristics2) {
            this.it = iterator;
            this.est = Long.MAX_VALUE;
            this.characteristics = characteristics2 & -16449;
        }

        @Override // java.util.Spliterator.OfDouble, java.util.Spliterator.OfDouble, java.util.Spliterator.OfDouble, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public Spliterator.OfDouble trySplit() {
            PrimitiveIterator.OfDouble i = this.it;
            long s = this.est;
            if (s <= 1 || !i.hasNext()) {
                return null;
            }
            int n = this.batch + 1024;
            if (((long) n) > s) {
                n = (int) s;
            }
            if (n > MAX_BATCH) {
                n = MAX_BATCH;
            }
            double[] a = new double[n];
            int j = 0;
            do {
                a[j] = i.nextDouble();
                j++;
                if (j >= n) {
                    break;
                }
            } while (i.hasNext());
            this.batch = j;
            long j2 = this.est;
            if (j2 != Long.MAX_VALUE) {
                this.est = j2 - ((long) j);
            }
            return new DoubleArraySpliterator(a, 0, j, this.characteristics);
        }

        @Override // java.util.Spliterator.OfDouble
        public void forEachRemaining(DoubleConsumer action) {
            if (action != null) {
                this.it.forEachRemaining(action);
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator.OfDouble
        public boolean tryAdvance(DoubleConsumer action) {
            if (action == null) {
                throw new NullPointerException();
            } else if (!this.it.hasNext()) {
                return false;
            } else {
                action.accept(this.it.nextDouble());
                return true;
            }
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.est;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.characteristics;
        }

        @Override // java.util.Spliterator
        public Comparator<? super Double> getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
        }
    }
}
