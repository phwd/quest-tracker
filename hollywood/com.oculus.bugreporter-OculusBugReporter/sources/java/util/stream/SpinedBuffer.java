package java.util.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;

public class SpinedBuffer<E> extends AbstractSpinedBuffer implements Consumer<E>, Iterable<E> {
    private static final int SPLITERATOR_CHARACTERISTICS = 16464;
    protected E[] curChunk;
    protected E[][] spine;

    @Override // java.util.stream.AbstractSpinedBuffer
    public /* bridge */ /* synthetic */ long count() {
        return super.count();
    }

    @Override // java.util.stream.AbstractSpinedBuffer
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    public SpinedBuffer(int initialCapacity) {
        super(initialCapacity);
        this.curChunk = (E[]) new Object[(1 << this.initialChunkPower)];
    }

    public SpinedBuffer() {
        this.curChunk = (E[]) new Object[(1 << this.initialChunkPower)];
    }

    /* access modifiers changed from: protected */
    public long capacity() {
        if (this.spineIndex == 0) {
            return (long) this.curChunk.length;
        }
        return this.priorElementCount[this.spineIndex] + ((long) this.spine[this.spineIndex].length);
    }

    private void inflateSpine() {
        if (this.spine == null) {
            this.spine = (E[][]) new Object[8][];
            this.priorElementCount = new long[8];
            this.spine[0] = this.curChunk;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v1, resolved type: E[][] */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public final void ensureCapacity(long targetSize) {
        long capacity = capacity();
        if (targetSize > capacity) {
            inflateSpine();
            int i = this.spineIndex;
            while (true) {
                i++;
                if (targetSize > capacity) {
                    E[][] eArr = this.spine;
                    if (i >= eArr.length) {
                        int newSpineSize = eArr.length * 2;
                        this.spine = (E[][]) ((Object[][]) Arrays.copyOf(eArr, newSpineSize));
                        this.priorElementCount = Arrays.copyOf(this.priorElementCount, newSpineSize);
                    }
                    int nextChunkSize = chunkSize(i);
                    this.spine[i] = new Object[nextChunkSize];
                    this.priorElementCount[i] = this.priorElementCount[i - 1] + ((long) this.spine[i - 1].length);
                    capacity += (long) nextChunkSize;
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void increaseCapacity() {
        ensureCapacity(capacity() + 1);
    }

    public E get(long index) {
        if (this.spineIndex == 0) {
            if (index < ((long) this.elementIndex)) {
                return this.curChunk[(int) index];
            }
            throw new IndexOutOfBoundsException(Long.toString(index));
        } else if (index < count()) {
            for (int j = 0; j <= this.spineIndex; j++) {
                long j2 = this.priorElementCount[j];
                E[][] eArr = this.spine;
                if (index < j2 + ((long) eArr[j].length)) {
                    return eArr[j][(int) (index - this.priorElementCount[j])];
                }
            }
            throw new IndexOutOfBoundsException(Long.toString(index));
        } else {
            throw new IndexOutOfBoundsException(Long.toString(index));
        }
    }

    public void copyInto(E[] array, int offset) {
        long finalOffset = ((long) offset) + count();
        if (finalOffset > ((long) array.length) || finalOffset < ((long) offset)) {
            throw new IndexOutOfBoundsException("does not fit");
        } else if (this.spineIndex == 0) {
            System.arraycopy(this.curChunk, 0, array, offset, this.elementIndex);
        } else {
            for (int i = 0; i < this.spineIndex; i++) {
                E[][] eArr = this.spine;
                System.arraycopy(eArr[i], 0, array, offset, eArr[i].length);
                offset += this.spine[i].length;
            }
            if (this.elementIndex > 0) {
                System.arraycopy(this.curChunk, 0, array, offset, this.elementIndex);
            }
        }
    }

    public E[] asArray(IntFunction<E[]> arrayFactory) {
        long size = count();
        if (size < 2147483639) {
            E[] result = arrayFactory.apply((int) size);
            copyInto(result, 0);
            return result;
        }
        throw new IllegalArgumentException("Stream size exceeds max array size");
    }

    @Override // java.util.stream.AbstractSpinedBuffer
    public void clear() {
        E[][] eArr = this.spine;
        if (eArr != null) {
            this.curChunk = eArr[0];
            int i = 0;
            while (true) {
                E[] eArr2 = this.curChunk;
                if (i >= eArr2.length) {
                    break;
                }
                eArr2[i] = null;
                i++;
            }
            this.spine = null;
            this.priorElementCount = null;
        } else {
            for (int i2 = 0; i2 < this.elementIndex; i2++) {
                this.curChunk[i2] = null;
            }
        }
        this.elementIndex = 0;
        this.spineIndex = 0;
    }

    @Override // java.lang.Iterable
    public Iterator<E> iterator() {
        return Spliterators.iterator(spliterator());
    }

    @Override // java.lang.Iterable
    public void forEach(Consumer<? super E> consumer) {
        for (int j = 0; j < this.spineIndex; j++) {
            for (E t : this.spine[j]) {
                consumer.accept(t);
            }
        }
        for (int i = 0; i < this.elementIndex; i++) {
            consumer.accept(this.curChunk[i]);
        }
    }

    @Override // java.util.function.Consumer
    public void accept(E e) {
        if (this.elementIndex == this.curChunk.length) {
            inflateSpine();
            int i = this.spineIndex + 1;
            E[][] eArr = this.spine;
            if (i >= eArr.length || eArr[this.spineIndex + 1] == null) {
                increaseCapacity();
            }
            this.elementIndex = 0;
            this.spineIndex++;
            this.curChunk = this.spine[this.spineIndex];
        }
        E[] eArr2 = this.curChunk;
        int i2 = this.elementIndex;
        this.elementIndex = i2 + 1;
        eArr2[i2] = e;
    }

    public String toString() {
        List<E> list = new ArrayList<>();
        Objects.requireNonNull(list);
        forEach(new Consumer() {
            /* class java.util.stream.$$Lambda$KCZEjYEMhxUzZZN6W26atKG9NZc */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                List.this.add(obj);
            }
        });
        return "SpinedBuffer:" + list.toString();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: java.util.stream.SpinedBuffer$1Splitr  reason: invalid class name */
    public class AnonymousClass1Splitr implements Spliterator<E> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        final int lastSpineElementFence;
        final int lastSpineIndex;
        E[] splChunk;
        int splElementIndex;
        int splSpineIndex;

        {
            this.splSpineIndex = firstSpineIndex;
            this.lastSpineIndex = lastSpineIndex;
            this.splElementIndex = firstSpineElementIndex;
            this.lastSpineElementFence = lastSpineElementFence;
            this.splChunk = SpinedBuffer.this.spine == null ? SpinedBuffer.this.curChunk : SpinedBuffer.this.spine[firstSpineIndex];
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            if (this.splSpineIndex == this.lastSpineIndex) {
                return ((long) this.lastSpineElementFence) - ((long) this.splElementIndex);
            }
            return ((SpinedBuffer.this.priorElementCount[this.lastSpineIndex] + ((long) this.lastSpineElementFence)) - SpinedBuffer.this.priorElementCount[this.splSpineIndex]) - ((long) this.splElementIndex);
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return SpinedBuffer.SPLITERATOR_CHARACTERISTICS;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super E> consumer) {
            Objects.requireNonNull(consumer);
            int i = this.splSpineIndex;
            int i2 = this.lastSpineIndex;
            if (i >= i2 && (i != i2 || this.splElementIndex >= this.lastSpineElementFence)) {
                return false;
            }
            E[] eArr = this.splChunk;
            int i3 = this.splElementIndex;
            this.splElementIndex = i3 + 1;
            consumer.accept(eArr[i3]);
            if (this.splElementIndex == this.splChunk.length) {
                this.splElementIndex = 0;
                this.splSpineIndex++;
                if (SpinedBuffer.this.spine != null && this.splSpineIndex <= this.lastSpineIndex) {
                    this.splChunk = SpinedBuffer.this.spine[this.splSpineIndex];
                }
            }
            return true;
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super E> consumer) {
            int i;
            Objects.requireNonNull(consumer);
            int i2 = this.splSpineIndex;
            int i3 = this.lastSpineIndex;
            if (i2 < i3 || (i2 == i3 && this.splElementIndex < this.lastSpineElementFence)) {
                int i4 = this.splElementIndex;
                int sp = this.splSpineIndex;
                while (true) {
                    i = this.lastSpineIndex;
                    if (sp >= i) {
                        break;
                    }
                    E[] chunk = SpinedBuffer.this.spine[sp];
                    while (i4 < chunk.length) {
                        consumer.accept(chunk[i4]);
                        i4++;
                    }
                    i4 = 0;
                    sp++;
                }
                E[] chunk2 = this.splSpineIndex == i ? this.splChunk : SpinedBuffer.this.spine[this.lastSpineIndex];
                int hElementIndex = this.lastSpineElementFence;
                while (i4 < hElementIndex) {
                    consumer.accept(chunk2[i4]);
                    i4++;
                }
                this.splSpineIndex = this.lastSpineIndex;
                this.splElementIndex = this.lastSpineElementFence;
            }
        }

        @Override // java.util.Spliterator
        public Spliterator<E> trySplit() {
            int i = this.splSpineIndex;
            int i2 = this.lastSpineIndex;
            if (i < i2) {
                SpinedBuffer spinedBuffer = SpinedBuffer.this;
                Spliterator<E> ret = new AnonymousClass1Splitr(i, i2 - 1, this.splElementIndex, spinedBuffer.spine[this.lastSpineIndex - 1].length);
                this.splSpineIndex = this.lastSpineIndex;
                this.splElementIndex = 0;
                this.splChunk = SpinedBuffer.this.spine[this.splSpineIndex];
                return ret;
            } else if (i != i2) {
                return null;
            } else {
                int i3 = this.lastSpineElementFence;
                int i4 = this.splElementIndex;
                int t = (i3 - i4) / 2;
                if (t == 0) {
                    return null;
                }
                Spliterator<E> ret2 = Arrays.spliterator(this.splChunk, i4, i4 + t);
                this.splElementIndex += t;
                return ret2;
            }
        }
    }

    @Override // java.lang.Iterable
    public Spliterator<E> spliterator() {
        return new AnonymousClass1Splitr(0, this.spineIndex, 0, this.elementIndex);
    }

    /* access modifiers changed from: package-private */
    public static abstract class OfPrimitive<E, T_ARR, T_CONS> extends AbstractSpinedBuffer implements Iterable<E> {
        T_ARR curChunk = newArray(1 << this.initialChunkPower);
        T_ARR[] spine;

        /* access modifiers changed from: protected */
        public abstract void arrayForEach(T_ARR t_arr, int i, int i2, T_CONS t_cons);

        /* access modifiers changed from: protected */
        public abstract int arrayLength(T_ARR t_arr);

        @Override // java.lang.Iterable
        public abstract void forEach(Consumer<? super E> consumer);

        @Override // java.lang.Iterable
        public abstract Iterator<E> iterator();

        public abstract T_ARR newArray(int i);

        /* access modifiers changed from: protected */
        public abstract T_ARR[] newArrayArray(int i);

        OfPrimitive(int initialCapacity) {
            super(initialCapacity);
        }

        OfPrimitive() {
        }

        /* access modifiers changed from: protected */
        public long capacity() {
            if (this.spineIndex == 0) {
                return (long) arrayLength(this.curChunk);
            }
            return this.priorElementCount[this.spineIndex] + ((long) arrayLength(this.spine[this.spineIndex]));
        }

        private void inflateSpine() {
            if (this.spine == null) {
                this.spine = newArrayArray(8);
                this.priorElementCount = new long[8];
                this.spine[0] = this.curChunk;
            }
        }

        /* access modifiers changed from: protected */
        public final void ensureCapacity(long targetSize) {
            long capacity = capacity();
            if (targetSize > capacity) {
                inflateSpine();
                int i = this.spineIndex;
                while (true) {
                    i++;
                    if (targetSize > capacity) {
                        T_ARR[] t_arrArr = this.spine;
                        if (i >= t_arrArr.length) {
                            int newSpineSize = t_arrArr.length * 2;
                            this.spine = (T_ARR[]) Arrays.copyOf(t_arrArr, newSpineSize);
                            this.priorElementCount = Arrays.copyOf(this.priorElementCount, newSpineSize);
                        }
                        int nextChunkSize = chunkSize(i);
                        this.spine[i] = newArray(nextChunkSize);
                        this.priorElementCount[i] = this.priorElementCount[i - 1] + ((long) arrayLength(this.spine[i - 1]));
                        capacity += (long) nextChunkSize;
                    } else {
                        return;
                    }
                }
            }
        }

        /* access modifiers changed from: protected */
        public void increaseCapacity() {
            ensureCapacity(capacity() + 1);
        }

        /* access modifiers changed from: protected */
        public int chunkFor(long index) {
            if (this.spineIndex == 0) {
                if (index < ((long) this.elementIndex)) {
                    return 0;
                }
                throw new IndexOutOfBoundsException(Long.toString(index));
            } else if (index < count()) {
                for (int j = 0; j <= this.spineIndex; j++) {
                    if (index < this.priorElementCount[j] + ((long) arrayLength(this.spine[j]))) {
                        return j;
                    }
                }
                throw new IndexOutOfBoundsException(Long.toString(index));
            } else {
                throw new IndexOutOfBoundsException(Long.toString(index));
            }
        }

        public void copyInto(T_ARR array, int offset) {
            long finalOffset = ((long) offset) + count();
            if (finalOffset > ((long) arrayLength(array)) || finalOffset < ((long) offset)) {
                throw new IndexOutOfBoundsException("does not fit");
            } else if (this.spineIndex == 0) {
                System.arraycopy(this.curChunk, 0, array, offset, this.elementIndex);
            } else {
                for (int i = 0; i < this.spineIndex; i++) {
                    T_ARR[] t_arrArr = this.spine;
                    System.arraycopy(t_arrArr[i], 0, array, offset, arrayLength(t_arrArr[i]));
                    offset += arrayLength(this.spine[i]);
                }
                if (this.elementIndex > 0) {
                    System.arraycopy(this.curChunk, 0, array, offset, this.elementIndex);
                }
            }
        }

        public T_ARR asPrimitiveArray() {
            long size = count();
            if (size < 2147483639) {
                T_ARR result = newArray((int) size);
                copyInto(result, 0);
                return result;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }

        /* access modifiers changed from: protected */
        public void preAccept() {
            if (this.elementIndex == arrayLength(this.curChunk)) {
                inflateSpine();
                int i = this.spineIndex + 1;
                T_ARR[] t_arrArr = this.spine;
                if (i >= t_arrArr.length || t_arrArr[this.spineIndex + 1] == null) {
                    increaseCapacity();
                }
                this.elementIndex = 0;
                this.spineIndex++;
                this.curChunk = this.spine[this.spineIndex];
            }
        }

        @Override // java.util.stream.AbstractSpinedBuffer
        public void clear() {
            T_ARR[] t_arrArr = this.spine;
            if (t_arrArr != null) {
                this.curChunk = t_arrArr[0];
                this.spine = null;
                this.priorElementCount = null;
            }
            this.elementIndex = 0;
            this.spineIndex = 0;
        }

        public void forEach(T_CONS consumer) {
            for (int j = 0; j < this.spineIndex; j++) {
                T_ARR[] t_arrArr = this.spine;
                arrayForEach(t_arrArr[j], 0, arrayLength(t_arrArr[j]), consumer);
            }
            arrayForEach(this.curChunk, 0, this.elementIndex, consumer);
        }

        /* JADX WARN: Unknown type variable: T_ARR in type: T_ARR */
        abstract class BaseSpliterator<T_SPLITR extends Spliterator.OfPrimitive<E, T_CONS, T_SPLITR>> implements Spliterator.OfPrimitive<E, T_CONS, T_SPLITR> {
            static final /* synthetic */ boolean $assertionsDisabled = false;
            final int lastSpineElementFence;
            final int lastSpineIndex;
            T_ARR splChunk;
            int splElementIndex;
            int splSpineIndex;

            /* access modifiers changed from: package-private */
            public abstract void arrayForOne(T_ARR t_arr, int i, T_CONS t_cons);

            /* access modifiers changed from: package-private */
            public abstract T_SPLITR arraySpliterator(T_ARR t_arr, int i, int i2);

            /* access modifiers changed from: package-private */
            public abstract T_SPLITR newSpliterator(int i, int i2, int i3, int i4);

            BaseSpliterator(int firstSpineIndex, int lastSpineIndex2, int firstSpineElementIndex, int lastSpineElementFence2) {
                this.splSpineIndex = firstSpineIndex;
                this.lastSpineIndex = lastSpineIndex2;
                this.splElementIndex = firstSpineElementIndex;
                this.lastSpineElementFence = lastSpineElementFence2;
                this.splChunk = OfPrimitive.this.spine == null ? OfPrimitive.this.curChunk : OfPrimitive.this.spine[firstSpineIndex];
            }

            @Override // java.util.Spliterator
            public long estimateSize() {
                if (this.splSpineIndex == this.lastSpineIndex) {
                    return ((long) this.lastSpineElementFence) - ((long) this.splElementIndex);
                }
                return ((OfPrimitive.this.priorElementCount[this.lastSpineIndex] + ((long) this.lastSpineElementFence)) - OfPrimitive.this.priorElementCount[this.splSpineIndex]) - ((long) this.splElementIndex);
            }

            @Override // java.util.Spliterator
            public int characteristics() {
                return SpinedBuffer.SPLITERATOR_CHARACTERISTICS;
            }

            @Override // java.util.Spliterator.OfPrimitive
            public boolean tryAdvance(T_CONS consumer) {
                Objects.requireNonNull(consumer);
                int i = this.splSpineIndex;
                int i2 = this.lastSpineIndex;
                if (i >= i2 && (i != i2 || this.splElementIndex >= this.lastSpineElementFence)) {
                    return false;
                }
                T_ARR t_arr = this.splChunk;
                int i3 = this.splElementIndex;
                this.splElementIndex = i3 + 1;
                arrayForOne(t_arr, i3, consumer);
                if (this.splElementIndex == OfPrimitive.this.arrayLength(this.splChunk)) {
                    this.splElementIndex = 0;
                    this.splSpineIndex++;
                    if (OfPrimitive.this.spine != null && this.splSpineIndex <= this.lastSpineIndex) {
                        this.splChunk = OfPrimitive.this.spine[this.splSpineIndex];
                    }
                }
                return true;
            }

            @Override // java.util.Spliterator.OfPrimitive
            public void forEachRemaining(T_CONS consumer) {
                int i;
                Objects.requireNonNull(consumer);
                int i2 = this.splSpineIndex;
                int i3 = this.lastSpineIndex;
                if (i2 < i3 || (i2 == i3 && this.splElementIndex < this.lastSpineElementFence)) {
                    int i4 = this.splElementIndex;
                    int sp = this.splSpineIndex;
                    while (true) {
                        i = this.lastSpineIndex;
                        if (sp >= i) {
                            break;
                        }
                        T_ARR chunk = OfPrimitive.this.spine[sp];
                        OfPrimitive ofPrimitive = OfPrimitive.this;
                        ofPrimitive.arrayForEach(chunk, i4, ofPrimitive.arrayLength(chunk), consumer);
                        i4 = 0;
                        sp++;
                    }
                    OfPrimitive.this.arrayForEach(this.splSpineIndex == i ? this.splChunk : OfPrimitive.this.spine[this.lastSpineIndex], i4, this.lastSpineElementFence, consumer);
                    this.splSpineIndex = this.lastSpineIndex;
                    this.splElementIndex = this.lastSpineElementFence;
                }
            }

            @Override // java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator
            public T_SPLITR trySplit() {
                int i = this.splSpineIndex;
                int i2 = this.lastSpineIndex;
                if (i < i2) {
                    int i3 = this.splElementIndex;
                    OfPrimitive ofPrimitive = OfPrimitive.this;
                    T_SPLITR ret = newSpliterator(i, i2 - 1, i3, ofPrimitive.arrayLength(ofPrimitive.spine[this.lastSpineIndex - 1]));
                    this.splSpineIndex = this.lastSpineIndex;
                    this.splElementIndex = 0;
                    this.splChunk = OfPrimitive.this.spine[this.splSpineIndex];
                    return ret;
                } else if (i != i2) {
                    return null;
                } else {
                    int i4 = this.lastSpineElementFence;
                    int i5 = this.splElementIndex;
                    int t = (i4 - i5) / 2;
                    if (t == 0) {
                        return null;
                    }
                    T_SPLITR ret2 = arraySpliterator(this.splChunk, i5, t);
                    this.splElementIndex += t;
                    return ret2;
                }
            }
        }
    }

    public static class OfInt extends OfPrimitive<Integer, int[], IntConsumer> implements IntConsumer {
        @Override // java.util.stream.AbstractSpinedBuffer, java.util.stream.SpinedBuffer.OfPrimitive
        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        @Override // java.util.stream.AbstractSpinedBuffer
        public /* bridge */ /* synthetic */ long count() {
            return super.count();
        }

        @Override // java.util.stream.AbstractSpinedBuffer
        public /* bridge */ /* synthetic */ boolean isEmpty() {
            return super.isEmpty();
        }

        public OfInt() {
        }

        public OfInt(int initialCapacity) {
            super(initialCapacity);
        }

        @Override // java.util.stream.SpinedBuffer.OfPrimitive, java.lang.Iterable
        public void forEach(Consumer<? super Integer> consumer) {
            if (consumer instanceof IntConsumer) {
                forEach((IntConsumer) consumer);
                return;
            }
            if (Tripwire.ENABLED) {
                Tripwire.trip(getClass(), "{0} calling SpinedBuffer.OfInt.forEach(Consumer)");
            }
            spliterator().forEachRemaining(consumer);
        }

        /* access modifiers changed from: protected */
        @Override // java.util.stream.SpinedBuffer.OfPrimitive
        public int[][] newArrayArray(int size) {
            return new int[size][];
        }

        @Override // java.util.stream.SpinedBuffer.OfPrimitive
        public int[] newArray(int size) {
            return new int[size];
        }

        /* access modifiers changed from: protected */
        public int arrayLength(int[] array) {
            return array.length;
        }

        /* access modifiers changed from: protected */
        public void arrayForEach(int[] array, int from, int to, IntConsumer consumer) {
            for (int i = from; i < to; i++) {
                consumer.accept(array[i]);
            }
        }

        @Override // java.util.function.IntConsumer
        public void accept(int i) {
            preAccept();
            int i2 = this.elementIndex;
            this.elementIndex = i2 + 1;
            ((int[]) this.curChunk)[i2] = i;
        }

        public int get(long index) {
            int ch = chunkFor(index);
            if (this.spineIndex == 0 && ch == 0) {
                return ((int[]) this.curChunk)[(int) index];
            }
            return ((int[][]) this.spine)[ch][(int) (index - this.priorElementCount[ch])];
        }

        /* Return type fixed from 'java.util.PrimitiveIterator$OfInt' to match base method */
        @Override // java.util.stream.SpinedBuffer.OfPrimitive, java.lang.Iterable
        public Iterator<Integer> iterator() {
            return Spliterators.iterator(spliterator());
        }

        @Override // java.lang.Iterable
        public Spliterator.OfInt spliterator() {
            return new Spliterator.OfInt(0, this.spineIndex, 0, this.elementIndex) {
                /* class java.util.stream.SpinedBuffer.OfInt.AnonymousClass1Splitr */

                @Override // java.util.Spliterator.OfInt
                public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
                    super.forEachRemaining((Object) intConsumer);
                }

                @Override // java.util.Spliterator.OfInt
                public /* bridge */ /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
                    return super.tryAdvance((Object) intConsumer);
                }

                @Override // java.util.Spliterator.OfInt, java.util.Spliterator.OfInt, java.util.Spliterator.OfInt, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.stream.SpinedBuffer.OfPrimitive.BaseSpliterator, java.util.stream.SpinedBuffer.OfPrimitive.BaseSpliterator, java.util.Spliterator
                public /* bridge */ /* synthetic */ Spliterator.OfInt trySplit() {
                    return (Spliterator.OfInt) super.trySplit();
                }

                /* Return type fixed from 'java.util.stream.SpinedBuffer$OfInt$1Splitr' to match base method */
                /* access modifiers changed from: package-private */
                @Override // java.util.stream.SpinedBuffer.OfPrimitive.BaseSpliterator
                public Spliterator.OfInt newSpliterator(int firstSpineIndex, int lastSpineIndex, int firstSpineElementIndex, int lastSpineElementFence) {
                    return 

                    public static class OfLong extends OfPrimitive<Long, long[], LongConsumer> implements LongConsumer {
                        @Override // java.util.stream.AbstractSpinedBuffer, java.util.stream.SpinedBuffer.OfPrimitive
                        public /* bridge */ /* synthetic */ void clear() {
                            super.clear();
                        }

                        @Override // java.util.stream.AbstractSpinedBuffer
                        public /* bridge */ /* synthetic */ long count() {
                            return super.count();
                        }

                        @Override // java.util.stream.AbstractSpinedBuffer
                        public /* bridge */ /* synthetic */ boolean isEmpty() {
                            return super.isEmpty();
                        }

                        public OfLong() {
                        }

                        public OfLong(int initialCapacity) {
                            super(initialCapacity);
                        }

                        @Override // java.util.stream.SpinedBuffer.OfPrimitive, java.lang.Iterable
                        public void forEach(Consumer<? super Long> consumer) {
                            if (consumer instanceof LongConsumer) {
                                forEach((LongConsumer) consumer);
                                return;
                            }
                            if (Tripwire.ENABLED) {
                                Tripwire.trip(getClass(), "{0} calling SpinedBuffer.OfLong.forEach(Consumer)");
                            }
                            spliterator().forEachRemaining(consumer);
                        }

                        /* access modifiers changed from: protected */
                        @Override // java.util.stream.SpinedBuffer.OfPrimitive
                        public long[][] newArrayArray(int size) {
                            return new long[size][];
                        }

                        @Override // java.util.stream.SpinedBuffer.OfPrimitive
                        public long[] newArray(int size) {
                            return new long[size];
                        }

                        /* access modifiers changed from: protected */
                        public int arrayLength(long[] array) {
                            return array.length;
                        }

                        /* access modifiers changed from: protected */
                        public void arrayForEach(long[] array, int from, int to, LongConsumer consumer) {
                            for (int i = from; i < to; i++) {
                                consumer.accept(array[i]);
                            }
                        }

                        @Override // java.util.function.LongConsumer
                        public void accept(long i) {
                            preAccept();
                            int i2 = this.elementIndex;
                            this.elementIndex = i2 + 1;
                            ((long[]) this.curChunk)[i2] = i;
                        }

                        public long get(long index) {
                            int ch = chunkFor(index);
                            if (this.spineIndex == 0 && ch == 0) {
                                return ((long[]) this.curChunk)[(int) index];
                            }
                            return ((long[][]) this.spine)[ch][(int) (index - this.priorElementCount[ch])];
                        }

                        /* Return type fixed from 'java.util.PrimitiveIterator$OfLong' to match base method */
                        @Override // java.util.stream.SpinedBuffer.OfPrimitive, java.lang.Iterable
                        public Iterator<Long> iterator() {
                            return Spliterators.iterator(spliterator());
                        }

                        @Override // java.lang.Iterable
                        public Spliterator.OfLong spliterator() {
                            return new Spliterator.OfLong(0, this.spineIndex, 0, this.elementIndex) {
                                /* class java.util.stream.SpinedBuffer.OfLong.AnonymousClass1Splitr */

                                @Override // java.util.Spliterator.OfLong
                                public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
                                    super.forEachRemaining((Object) longConsumer);
                                }

                                @Override // java.util.Spliterator.OfLong
                                public /* bridge */ /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
                                    return super.tryAdvance((Object) longConsumer);
                                }

                                @Override // java.util.Spliterator.OfLong, java.util.Spliterator.OfLong, java.util.Spliterator.OfLong, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.stream.SpinedBuffer.OfPrimitive.BaseSpliterator, java.util.stream.SpinedBuffer.OfPrimitive.BaseSpliterator, java.util.Spliterator
                                public /* bridge */ /* synthetic */ Spliterator.OfLong trySplit() {
                                    return (Spliterator.OfLong) super.trySplit();
                                }

                                /* Return type fixed from 'java.util.stream.SpinedBuffer$OfLong$1Splitr' to match base method */
                                /* access modifiers changed from: package-private */
                                @Override // java.util.stream.SpinedBuffer.OfPrimitive.BaseSpliterator
                                public Spliterator.OfLong newSpliterator(int firstSpineIndex, int lastSpineIndex, int firstSpineElementIndex, int lastSpineElementFence) {
                                    return 

                                    public static class OfDouble extends OfPrimitive<Double, double[], DoubleConsumer> implements DoubleConsumer {
                                        @Override // java.util.stream.AbstractSpinedBuffer, java.util.stream.SpinedBuffer.OfPrimitive
                                        public /* bridge */ /* synthetic */ void clear() {
                                            super.clear();
                                        }

                                        @Override // java.util.stream.AbstractSpinedBuffer
                                        public /* bridge */ /* synthetic */ long count() {
                                            return super.count();
                                        }

                                        @Override // java.util.stream.AbstractSpinedBuffer
                                        public /* bridge */ /* synthetic */ boolean isEmpty() {
                                            return super.isEmpty();
                                        }

                                        public OfDouble() {
                                        }

                                        public OfDouble(int initialCapacity) {
                                            super(initialCapacity);
                                        }

                                        @Override // java.util.stream.SpinedBuffer.OfPrimitive, java.lang.Iterable
                                        public void forEach(Consumer<? super Double> consumer) {
                                            if (consumer instanceof DoubleConsumer) {
                                                forEach((DoubleConsumer) consumer);
                                                return;
                                            }
                                            if (Tripwire.ENABLED) {
                                                Tripwire.trip(getClass(), "{0} calling SpinedBuffer.OfDouble.forEach(Consumer)");
                                            }
                                            spliterator().forEachRemaining(consumer);
                                        }

                                        /* access modifiers changed from: protected */
                                        @Override // java.util.stream.SpinedBuffer.OfPrimitive
                                        public double[][] newArrayArray(int size) {
                                            return new double[size][];
                                        }

                                        @Override // java.util.stream.SpinedBuffer.OfPrimitive
                                        public double[] newArray(int size) {
                                            return new double[size];
                                        }

                                        /* access modifiers changed from: protected */
                                        public int arrayLength(double[] array) {
                                            return array.length;
                                        }

                                        /* access modifiers changed from: protected */
                                        public void arrayForEach(double[] array, int from, int to, DoubleConsumer consumer) {
                                            for (int i = from; i < to; i++) {
                                                consumer.accept(array[i]);
                                            }
                                        }

                                        @Override // java.util.function.DoubleConsumer
                                        public void accept(double i) {
                                            preAccept();
                                            int i2 = this.elementIndex;
                                            this.elementIndex = i2 + 1;
                                            ((double[]) this.curChunk)[i2] = i;
                                        }

                                        public double get(long index) {
                                            int ch = chunkFor(index);
                                            if (this.spineIndex == 0 && ch == 0) {
                                                return ((double[]) this.curChunk)[(int) index];
                                            }
                                            return ((double[][]) this.spine)[ch][(int) (index - this.priorElementCount[ch])];
                                        }

                                        /* Return type fixed from 'java.util.PrimitiveIterator$OfDouble' to match base method */
                                        @Override // java.util.stream.SpinedBuffer.OfPrimitive, java.lang.Iterable
                                        public Iterator<Double> iterator() {
                                            return Spliterators.iterator(spliterator());
                                        }

                                        @Override // java.lang.Iterable
                                        public Spliterator.OfDouble spliterator() {
                                            return new Spliterator.OfDouble(0, this.spineIndex, 0, this.elementIndex) {
                                                /* class java.util.stream.SpinedBuffer.OfDouble.AnonymousClass1Splitr */

                                                @Override // java.util.Spliterator.OfDouble
                                                public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
                                                    super.forEachRemaining((Object) doubleConsumer);
                                                }

                                                @Override // java.util.Spliterator.OfDouble
                                                public /* bridge */ /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
                                                    return super.tryAdvance((Object) doubleConsumer);
                                                }

                                                @Override // java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfDouble, java.util.Spliterator.OfDouble, java.util.Spliterator.OfDouble, java.util.stream.SpinedBuffer.OfPrimitive.BaseSpliterator, java.util.stream.SpinedBuffer.OfPrimitive.BaseSpliterator, java.util.Spliterator
                                                public /* bridge */ /* synthetic */ Spliterator.OfDouble trySplit() {
                                                    return (Spliterator.OfDouble) super.trySplit();
                                                }

                                                /* Return type fixed from 'java.util.stream.SpinedBuffer$OfDouble$1Splitr' to match base method */
                                                /* access modifiers changed from: package-private */
                                                @Override // java.util.stream.SpinedBuffer.OfPrimitive.BaseSpliterator
                                                public Spliterator.OfDouble newSpliterator(int firstSpineIndex, int lastSpineIndex, int firstSpineElementIndex, int lastSpineElementFence) {
                                                    return 
                                                }
