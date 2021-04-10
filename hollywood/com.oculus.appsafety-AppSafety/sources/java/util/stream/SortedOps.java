package java.util.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.stream.DoublePipeline;
import java.util.stream.IntPipeline;
import java.util.stream.LongPipeline;
import java.util.stream.Node;
import java.util.stream.ReferencePipeline;
import java.util.stream.Sink;
import java.util.stream.SpinedBuffer;

final class SortedOps {
    private SortedOps() {
    }

    static <T> Stream<T> makeRef(AbstractPipeline<?, T, ?> upstream) {
        return new OfRef(upstream);
    }

    static <T> Stream<T> makeRef(AbstractPipeline<?, T, ?> upstream, Comparator<? super T> comparator) {
        return new OfRef(upstream, comparator);
    }

    static <T> IntStream makeInt(AbstractPipeline<?, Integer, ?> upstream) {
        return new OfInt(upstream);
    }

    static <T> LongStream makeLong(AbstractPipeline<?, Long, ?> upstream) {
        return new OfLong(upstream);
    }

    static <T> DoubleStream makeDouble(AbstractPipeline<?, Double, ?> upstream) {
        return new OfDouble(upstream);
    }

    /* access modifiers changed from: private */
    public static final class OfRef<T> extends ReferencePipeline.StatefulOp<T, T> {
        private final Comparator<? super T> comparator;
        private final boolean isNaturalSort;

        OfRef(AbstractPipeline<?, T, ?> upstream) {
            super(upstream, StreamShape.REFERENCE, StreamOpFlag.IS_ORDERED | StreamOpFlag.IS_SORTED);
            this.isNaturalSort = true;
            this.comparator = Comparator.naturalOrder();
        }

        OfRef(AbstractPipeline<?, T, ?> upstream, Comparator<? super T> comparator2) {
            super(upstream, StreamShape.REFERENCE, StreamOpFlag.IS_ORDERED | StreamOpFlag.NOT_SORTED);
            this.isNaturalSort = false;
            this.comparator = (Comparator) Objects.requireNonNull(comparator2);
        }

        @Override // java.util.stream.AbstractPipeline
        public Sink<T> opWrapSink(int flags, Sink<T> sink) {
            Objects.requireNonNull(sink);
            if (StreamOpFlag.SORTED.isKnown(flags) && this.isNaturalSort) {
                return sink;
            }
            if (StreamOpFlag.SIZED.isKnown(flags)) {
                return new SizedRefSortingSink(sink, this.comparator);
            }
            return new RefSortingSink(sink, this.comparator);
        }

        @Override // java.util.stream.ReferencePipeline.StatefulOp, java.util.stream.AbstractPipeline
        public <P_IN> Node<T> opEvaluateParallel(PipelineHelper<T> helper, Spliterator<P_IN> spliterator, IntFunction<T[]> generator) {
            if (StreamOpFlag.SORTED.isKnown(helper.getStreamAndOpFlags()) && this.isNaturalSort) {
                return helper.evaluate(spliterator, false, generator);
            }
            T[] flattenedData = helper.evaluate(spliterator, true, generator).asArray(generator);
            Arrays.parallelSort(flattenedData, this.comparator);
            return Nodes.node(flattenedData);
        }
    }

    /* access modifiers changed from: private */
    public static final class OfInt extends IntPipeline.StatefulOp<Integer> {
        OfInt(AbstractPipeline<?, Integer, ?> upstream) {
            super(upstream, StreamShape.INT_VALUE, StreamOpFlag.IS_ORDERED | StreamOpFlag.IS_SORTED);
        }

        @Override // java.util.stream.AbstractPipeline
        public Sink<Integer> opWrapSink(int flags, Sink<Integer> sink) {
            Objects.requireNonNull(sink);
            if (StreamOpFlag.SORTED.isKnown(flags)) {
                return sink;
            }
            if (StreamOpFlag.SIZED.isKnown(flags)) {
                return new SizedIntSortingSink(sink);
            }
            return new IntSortingSink(sink);
        }

        @Override // java.util.stream.AbstractPipeline, java.util.stream.IntPipeline.StatefulOp
        public <P_IN> Node<Integer> opEvaluateParallel(PipelineHelper<Integer> helper, Spliterator<P_IN> spliterator, IntFunction<Integer[]> generator) {
            if (StreamOpFlag.SORTED.isKnown(helper.getStreamAndOpFlags())) {
                return helper.evaluate(spliterator, false, generator);
            }
            int[] content = (int[]) ((Node.OfInt) helper.evaluate(spliterator, true, generator)).asPrimitiveArray();
            Arrays.parallelSort(content);
            return Nodes.node(content);
        }
    }

    /* access modifiers changed from: private */
    public static final class OfLong extends LongPipeline.StatefulOp<Long> {
        OfLong(AbstractPipeline<?, Long, ?> upstream) {
            super(upstream, StreamShape.LONG_VALUE, StreamOpFlag.IS_ORDERED | StreamOpFlag.IS_SORTED);
        }

        @Override // java.util.stream.AbstractPipeline
        public Sink<Long> opWrapSink(int flags, Sink<Long> sink) {
            Objects.requireNonNull(sink);
            if (StreamOpFlag.SORTED.isKnown(flags)) {
                return sink;
            }
            if (StreamOpFlag.SIZED.isKnown(flags)) {
                return new SizedLongSortingSink(sink);
            }
            return new LongSortingSink(sink);
        }

        @Override // java.util.stream.AbstractPipeline, java.util.stream.LongPipeline.StatefulOp
        public <P_IN> Node<Long> opEvaluateParallel(PipelineHelper<Long> helper, Spliterator<P_IN> spliterator, IntFunction<Long[]> generator) {
            if (StreamOpFlag.SORTED.isKnown(helper.getStreamAndOpFlags())) {
                return helper.evaluate(spliterator, false, generator);
            }
            long[] content = (long[]) ((Node.OfLong) helper.evaluate(spliterator, true, generator)).asPrimitiveArray();
            Arrays.parallelSort(content);
            return Nodes.node(content);
        }
    }

    /* access modifiers changed from: private */
    public static final class OfDouble extends DoublePipeline.StatefulOp<Double> {
        OfDouble(AbstractPipeline<?, Double, ?> upstream) {
            super(upstream, StreamShape.DOUBLE_VALUE, StreamOpFlag.IS_ORDERED | StreamOpFlag.IS_SORTED);
        }

        @Override // java.util.stream.AbstractPipeline
        public Sink<Double> opWrapSink(int flags, Sink<Double> sink) {
            Objects.requireNonNull(sink);
            if (StreamOpFlag.SORTED.isKnown(flags)) {
                return sink;
            }
            if (StreamOpFlag.SIZED.isKnown(flags)) {
                return new SizedDoubleSortingSink(sink);
            }
            return new DoubleSortingSink(sink);
        }

        @Override // java.util.stream.DoublePipeline.StatefulOp, java.util.stream.AbstractPipeline
        public <P_IN> Node<Double> opEvaluateParallel(PipelineHelper<Double> helper, Spliterator<P_IN> spliterator, IntFunction<Double[]> generator) {
            if (StreamOpFlag.SORTED.isKnown(helper.getStreamAndOpFlags())) {
                return helper.evaluate(spliterator, false, generator);
            }
            double[] content = (double[]) ((Node.OfDouble) helper.evaluate(spliterator, true, generator)).asPrimitiveArray();
            Arrays.parallelSort(content);
            return Nodes.node(content);
        }
    }

    private static abstract class AbstractRefSortingSink<T> extends Sink.ChainedReference<T, T> {
        protected boolean cancellationWasRequested;
        protected final Comparator<? super T> comparator;

        AbstractRefSortingSink(Sink<? super T> downstream, Comparator<? super T> comparator2) {
            super(downstream);
            this.comparator = comparator2;
        }

        @Override // java.util.stream.Sink.ChainedReference, java.util.stream.Sink
        public final boolean cancellationRequested() {
            this.cancellationWasRequested = true;
            return false;
        }
    }

    private static final class SizedRefSortingSink<T> extends AbstractRefSortingSink<T> {
        private T[] array;
        private int offset;

        SizedRefSortingSink(Sink<? super T> sink, Comparator<? super T> comparator) {
            super(sink, comparator);
        }

        @Override // java.util.stream.Sink.ChainedReference, java.util.stream.Sink
        public void begin(long size) {
            if (size < 2147483639) {
                this.array = (T[]) new Object[((int) size)];
                return;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }

        @Override // java.util.stream.Sink.ChainedReference, java.util.stream.Sink
        public void end() {
            Arrays.sort(this.array, 0, this.offset, this.comparator);
            this.downstream.begin((long) this.offset);
            if (!this.cancellationWasRequested) {
                for (int i = 0; i < this.offset; i++) {
                    this.downstream.accept(this.array[i]);
                }
            } else {
                for (int i2 = 0; i2 < this.offset && !this.downstream.cancellationRequested(); i2++) {
                    this.downstream.accept(this.array[i2]);
                }
            }
            this.downstream.end();
            this.array = null;
        }

        @Override // java.util.function.Consumer
        public void accept(T t) {
            T[] tArr = this.array;
            int i = this.offset;
            this.offset = i + 1;
            tArr[i] = t;
        }
    }

    private static final class RefSortingSink<T> extends AbstractRefSortingSink<T> {
        private ArrayList<T> list;

        RefSortingSink(Sink<? super T> sink, Comparator<? super T> comparator) {
            super(sink, comparator);
        }

        @Override // java.util.stream.Sink.ChainedReference, java.util.stream.Sink
        public void begin(long size) {
            if (size < 2147483639) {
                this.list = size >= 0 ? new ArrayList<>((int) size) : new ArrayList<>();
                return;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }

        @Override // java.util.stream.Sink.ChainedReference, java.util.stream.Sink
        public void end() {
            this.list.sort(this.comparator);
            this.downstream.begin((long) this.list.size());
            if (!this.cancellationWasRequested) {
                ArrayList<T> arrayList = this.list;
                Sink sink = this.downstream;
                Objects.requireNonNull(sink);
                arrayList.forEach(new Consumer() {
                    /* class java.util.stream.$$Lambda$Abl7XfE0Z4AgkViLas9vhsO9mjw */

                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        Sink.this.accept(obj);
                    }
                });
            } else {
                Iterator<T> it = this.list.iterator();
                while (it.hasNext()) {
                    T t = it.next();
                    if (this.downstream.cancellationRequested()) {
                        break;
                    }
                    this.downstream.accept(t);
                }
            }
            this.downstream.end();
            this.list = null;
        }

        @Override // java.util.function.Consumer
        public void accept(T t) {
            this.list.add(t);
        }
    }

    private static abstract class AbstractIntSortingSink extends Sink.ChainedInt<Integer> {
        protected boolean cancellationWasRequested;

        AbstractIntSortingSink(Sink<? super Integer> downstream) {
            super(downstream);
        }

        @Override // java.util.stream.Sink.ChainedInt, java.util.stream.Sink
        public final boolean cancellationRequested() {
            this.cancellationWasRequested = true;
            return false;
        }
    }

    private static final class SizedIntSortingSink extends AbstractIntSortingSink {
        private int[] array;
        private int offset;

        SizedIntSortingSink(Sink<? super Integer> downstream) {
            super(downstream);
        }

        @Override // java.util.stream.Sink.ChainedInt, java.util.stream.Sink
        public void begin(long size) {
            if (size < 2147483639) {
                this.array = new int[((int) size)];
                return;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }

        @Override // java.util.stream.Sink.ChainedInt, java.util.stream.Sink
        public void end() {
            Arrays.sort(this.array, 0, this.offset);
            this.downstream.begin((long) this.offset);
            if (!this.cancellationWasRequested) {
                for (int i = 0; i < this.offset; i++) {
                    this.downstream.accept(this.array[i]);
                }
            } else {
                for (int i2 = 0; i2 < this.offset && !this.downstream.cancellationRequested(); i2++) {
                    this.downstream.accept(this.array[i2]);
                }
            }
            this.downstream.end();
            this.array = null;
        }

        @Override // java.util.stream.Sink.OfInt, java.util.function.IntConsumer, java.util.stream.Sink
        public void accept(int t) {
            int[] iArr = this.array;
            int i = this.offset;
            this.offset = i + 1;
            iArr[i] = t;
        }
    }

    private static final class IntSortingSink extends AbstractIntSortingSink {
        private SpinedBuffer.OfInt b;

        IntSortingSink(Sink<? super Integer> sink) {
            super(sink);
        }

        @Override // java.util.stream.Sink.ChainedInt, java.util.stream.Sink
        public void begin(long size) {
            if (size < 2147483639) {
                this.b = size > 0 ? new SpinedBuffer.OfInt((int) size) : new SpinedBuffer.OfInt();
                return;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }

        @Override // java.util.stream.Sink.ChainedInt, java.util.stream.Sink
        public void end() {
            int[] ints = (int[]) this.b.asPrimitiveArray();
            Arrays.sort(ints);
            this.downstream.begin((long) ints.length);
            int i = 0;
            if (!this.cancellationWasRequested) {
                int length = ints.length;
                while (i < length) {
                    this.downstream.accept(ints[i]);
                    i++;
                }
            } else {
                int length2 = ints.length;
                while (i < length2) {
                    int anInt = ints[i];
                    if (this.downstream.cancellationRequested()) {
                        break;
                    }
                    this.downstream.accept(anInt);
                    i++;
                }
            }
            this.downstream.end();
        }

        @Override // java.util.stream.Sink.OfInt, java.util.function.IntConsumer, java.util.stream.Sink
        public void accept(int t) {
            this.b.accept(t);
        }
    }

    private static abstract class AbstractLongSortingSink extends Sink.ChainedLong<Long> {
        protected boolean cancellationWasRequested;

        AbstractLongSortingSink(Sink<? super Long> downstream) {
            super(downstream);
        }

        @Override // java.util.stream.Sink.ChainedLong, java.util.stream.Sink
        public final boolean cancellationRequested() {
            this.cancellationWasRequested = true;
            return false;
        }
    }

    private static final class SizedLongSortingSink extends AbstractLongSortingSink {
        private long[] array;
        private int offset;

        SizedLongSortingSink(Sink<? super Long> downstream) {
            super(downstream);
        }

        @Override // java.util.stream.Sink.ChainedLong, java.util.stream.Sink
        public void begin(long size) {
            if (size < 2147483639) {
                this.array = new long[((int) size)];
                return;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }

        @Override // java.util.stream.Sink.ChainedLong, java.util.stream.Sink
        public void end() {
            Arrays.sort(this.array, 0, this.offset);
            this.downstream.begin((long) this.offset);
            if (!this.cancellationWasRequested) {
                for (int i = 0; i < this.offset; i++) {
                    this.downstream.accept(this.array[i]);
                }
            } else {
                for (int i2 = 0; i2 < this.offset && !this.downstream.cancellationRequested(); i2++) {
                    this.downstream.accept(this.array[i2]);
                }
            }
            this.downstream.end();
            this.array = null;
        }

        @Override // java.util.stream.Sink.OfLong, java.util.stream.Sink, java.util.function.LongConsumer
        public void accept(long t) {
            long[] jArr = this.array;
            int i = this.offset;
            this.offset = i + 1;
            jArr[i] = t;
        }
    }

    private static final class LongSortingSink extends AbstractLongSortingSink {
        private SpinedBuffer.OfLong b;

        LongSortingSink(Sink<? super Long> sink) {
            super(sink);
        }

        @Override // java.util.stream.Sink.ChainedLong, java.util.stream.Sink
        public void begin(long size) {
            if (size < 2147483639) {
                this.b = size > 0 ? new SpinedBuffer.OfLong((int) size) : new SpinedBuffer.OfLong();
                return;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }

        @Override // java.util.stream.Sink.ChainedLong, java.util.stream.Sink
        public void end() {
            long[] longs = (long[]) this.b.asPrimitiveArray();
            Arrays.sort(longs);
            this.downstream.begin((long) longs.length);
            int i = 0;
            if (!this.cancellationWasRequested) {
                int length = longs.length;
                while (i < length) {
                    this.downstream.accept(longs[i]);
                    i++;
                }
            } else {
                int length2 = longs.length;
                while (i < length2) {
                    long aLong = longs[i];
                    if (this.downstream.cancellationRequested()) {
                        break;
                    }
                    this.downstream.accept(aLong);
                    i++;
                }
            }
            this.downstream.end();
        }

        @Override // java.util.stream.Sink.OfLong, java.util.stream.Sink, java.util.function.LongConsumer
        public void accept(long t) {
            this.b.accept(t);
        }
    }

    private static abstract class AbstractDoubleSortingSink extends Sink.ChainedDouble<Double> {
        protected boolean cancellationWasRequested;

        AbstractDoubleSortingSink(Sink<? super Double> downstream) {
            super(downstream);
        }

        @Override // java.util.stream.Sink.ChainedDouble, java.util.stream.Sink
        public final boolean cancellationRequested() {
            this.cancellationWasRequested = true;
            return false;
        }
    }

    private static final class SizedDoubleSortingSink extends AbstractDoubleSortingSink {
        private double[] array;
        private int offset;

        SizedDoubleSortingSink(Sink<? super Double> downstream) {
            super(downstream);
        }

        @Override // java.util.stream.Sink.ChainedDouble, java.util.stream.Sink
        public void begin(long size) {
            if (size < 2147483639) {
                this.array = new double[((int) size)];
                return;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }

        @Override // java.util.stream.Sink.ChainedDouble, java.util.stream.Sink
        public void end() {
            Arrays.sort(this.array, 0, this.offset);
            this.downstream.begin((long) this.offset);
            if (!this.cancellationWasRequested) {
                for (int i = 0; i < this.offset; i++) {
                    this.downstream.accept(this.array[i]);
                }
            } else {
                for (int i2 = 0; i2 < this.offset && !this.downstream.cancellationRequested(); i2++) {
                    this.downstream.accept(this.array[i2]);
                }
            }
            this.downstream.end();
            this.array = null;
        }

        @Override // java.util.stream.Sink.OfDouble, java.util.function.DoubleConsumer, java.util.stream.Sink
        public void accept(double t) {
            double[] dArr = this.array;
            int i = this.offset;
            this.offset = i + 1;
            dArr[i] = t;
        }
    }

    private static final class DoubleSortingSink extends AbstractDoubleSortingSink {
        private SpinedBuffer.OfDouble b;

        DoubleSortingSink(Sink<? super Double> sink) {
            super(sink);
        }

        @Override // java.util.stream.Sink.ChainedDouble, java.util.stream.Sink
        public void begin(long size) {
            if (size < 2147483639) {
                this.b = size > 0 ? new SpinedBuffer.OfDouble((int) size) : new SpinedBuffer.OfDouble();
                return;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }

        @Override // java.util.stream.Sink.ChainedDouble, java.util.stream.Sink
        public void end() {
            double[] doubles = (double[]) this.b.asPrimitiveArray();
            Arrays.sort(doubles);
            this.downstream.begin((long) doubles.length);
            int i = 0;
            if (!this.cancellationWasRequested) {
                int length = doubles.length;
                while (i < length) {
                    this.downstream.accept(doubles[i]);
                    i++;
                }
            } else {
                int length2 = doubles.length;
                while (i < length2) {
                    double aDouble = doubles[i];
                    if (this.downstream.cancellationRequested()) {
                        break;
                    }
                    this.downstream.accept(aDouble);
                    i++;
                }
            }
            this.downstream.end();
        }

        @Override // java.util.stream.Sink.OfDouble, java.util.function.DoubleConsumer, java.util.stream.Sink
        public void accept(double t) {
            this.b.accept(t);
        }
    }
}
