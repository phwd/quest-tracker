package java.util.stream;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.Iterator;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiConsumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;
import java.util.stream.StreamSpliterators;
import java.util.stream.Streams;

public interface DoubleStream extends BaseStream<Double, DoubleStream> {
    boolean allMatch(DoublePredicate doublePredicate);

    boolean anyMatch(DoublePredicate doublePredicate);

    OptionalDouble average();

    Stream<Double> boxed();

    <R> R collect(Supplier<R> supplier, ObjDoubleConsumer<R> objDoubleConsumer, BiConsumer<R, R> biConsumer);

    long count();

    DoubleStream distinct();

    DoubleStream filter(DoublePredicate doublePredicate);

    OptionalDouble findAny();

    OptionalDouble findFirst();

    DoubleStream flatMap(DoubleFunction<? extends DoubleStream> doubleFunction);

    void forEach(DoubleConsumer doubleConsumer);

    void forEachOrdered(DoubleConsumer doubleConsumer);

    /* Return type fixed from 'java.util.PrimitiveIterator$OfDouble' to match base method */
    @Override // java.util.stream.BaseStream
    Iterator<Double> iterator();

    DoubleStream limit(long j);

    DoubleStream map(DoubleUnaryOperator doubleUnaryOperator);

    IntStream mapToInt(DoubleToIntFunction doubleToIntFunction);

    LongStream mapToLong(DoubleToLongFunction doubleToLongFunction);

    <U> Stream<U> mapToObj(DoubleFunction<? extends U> doubleFunction);

    OptionalDouble max();

    OptionalDouble min();

    boolean noneMatch(DoublePredicate doublePredicate);

    @Override // java.util.stream.BaseStream
    DoubleStream parallel();

    DoubleStream peek(DoubleConsumer doubleConsumer);

    double reduce(double d, DoubleBinaryOperator doubleBinaryOperator);

    OptionalDouble reduce(DoubleBinaryOperator doubleBinaryOperator);

    @Override // java.util.stream.BaseStream
    DoubleStream sequential();

    DoubleStream skip(long j);

    DoubleStream sorted();

    /* Return type fixed from 'java.util.Spliterator$OfDouble' to match base method */
    @Override // java.util.stream.BaseStream
    Spliterator<Double> spliterator();

    double sum();

    DoubleSummaryStatistics summaryStatistics();

    double[] toArray();

    static default Builder builder() {
        return new Streams.DoubleStreamBuilderImpl();
    }

    static default DoubleStream empty() {
        return StreamSupport.doubleStream(Spliterators.emptyDoubleSpliterator(), false);
    }

    static default DoubleStream of(double t) {
        return StreamSupport.doubleStream(new Streams.DoubleStreamBuilderImpl(t), false);
    }

    static default DoubleStream of(double... values) {
        return Arrays.stream(values);
    }

    static default DoubleStream iterate(final double seed, final DoubleUnaryOperator f) {
        Objects.requireNonNull(f);
        return StreamSupport.doubleStream(Spliterators.spliteratorUnknownSize(new PrimitiveIterator.OfDouble() {
            /* class java.util.stream.DoubleStream.AnonymousClass1 */
            double t = seed;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return true;
            }

            @Override // java.util.PrimitiveIterator.OfDouble
            public double nextDouble() {
                double v = this.t;
                this.t = f.applyAsDouble(this.t);
                return v;
            }
        }, 1296), false);
    }

    static default DoubleStream generate(DoubleSupplier s) {
        Objects.requireNonNull(s);
        return StreamSupport.doubleStream(new StreamSpliterators.InfiniteSupplyingSpliterator.OfDouble(Long.MAX_VALUE, s), false);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.Spliterator$OfDouble] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.Spliterator$OfDouble] */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static default java.util.stream.DoubleStream concat(java.util.stream.DoubleStream r3, java.util.stream.DoubleStream r4) {
        /*
            java.util.Objects.requireNonNull(r3)
            java.util.Objects.requireNonNull(r4)
            java.util.stream.Streams$ConcatSpliterator$OfDouble r0 = new java.util.stream.Streams$ConcatSpliterator$OfDouble
            java.util.Spliterator$OfDouble r1 = r3.spliterator()
            java.util.Spliterator$OfDouble r2 = r4.spliterator()
            r0.<init>(r1, r2)
            boolean r1 = r3.isParallel()
            if (r1 != 0) goto L_0x0022
            boolean r1 = r4.isParallel()
            if (r1 == 0) goto L_0x0020
            goto L_0x0022
        L_0x0020:
            r1 = 0
            goto L_0x0023
        L_0x0022:
            r1 = 1
        L_0x0023:
            java.util.stream.DoubleStream r1 = java.util.stream.StreamSupport.doubleStream(r0, r1)
            java.lang.Runnable r2 = java.util.stream.Streams.composedClose(r3, r4)
            java.util.stream.BaseStream r2 = r1.onClose(r2)
            java.util.stream.DoubleStream r2 = (java.util.stream.DoubleStream) r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.stream.DoubleStream.concat(java.util.stream.DoubleStream, java.util.stream.DoubleStream):java.util.stream.DoubleStream");
    }

    public interface Builder extends DoubleConsumer {
        @Override // java.util.function.DoubleConsumer
        void accept(double d);

        DoubleStream build();

        default Builder add(double t) {
            accept(t);
            return this;
        }
    }
}
