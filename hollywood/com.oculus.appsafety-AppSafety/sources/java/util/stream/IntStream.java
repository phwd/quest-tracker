package java.util.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiConsumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.stream.StreamSpliterators;
import java.util.stream.Streams;

public interface IntStream extends BaseStream<Integer, IntStream> {
    boolean allMatch(IntPredicate intPredicate);

    boolean anyMatch(IntPredicate intPredicate);

    DoubleStream asDoubleStream();

    LongStream asLongStream();

    OptionalDouble average();

    Stream<Integer> boxed();

    <R> R collect(Supplier<R> supplier, ObjIntConsumer<R> objIntConsumer, BiConsumer<R, R> biConsumer);

    long count();

    IntStream distinct();

    IntStream filter(IntPredicate intPredicate);

    OptionalInt findAny();

    OptionalInt findFirst();

    IntStream flatMap(IntFunction<? extends IntStream> intFunction);

    void forEach(IntConsumer intConsumer);

    void forEachOrdered(IntConsumer intConsumer);

    /* Return type fixed from 'java.util.PrimitiveIterator$OfInt' to match base method */
    @Override // java.util.stream.BaseStream
    Iterator<Integer> iterator();

    IntStream limit(long j);

    IntStream map(IntUnaryOperator intUnaryOperator);

    DoubleStream mapToDouble(IntToDoubleFunction intToDoubleFunction);

    LongStream mapToLong(IntToLongFunction intToLongFunction);

    <U> Stream<U> mapToObj(IntFunction<? extends U> intFunction);

    OptionalInt max();

    OptionalInt min();

    boolean noneMatch(IntPredicate intPredicate);

    @Override // java.util.stream.BaseStream
    IntStream parallel();

    IntStream peek(IntConsumer intConsumer);

    int reduce(int i, IntBinaryOperator intBinaryOperator);

    OptionalInt reduce(IntBinaryOperator intBinaryOperator);

    @Override // java.util.stream.BaseStream
    IntStream sequential();

    IntStream skip(long j);

    IntStream sorted();

    /* Return type fixed from 'java.util.Spliterator$OfInt' to match base method */
    @Override // java.util.stream.BaseStream
    Spliterator<Integer> spliterator();

    int sum();

    IntSummaryStatistics summaryStatistics();

    int[] toArray();

    static default Builder builder() {
        return new Streams.IntStreamBuilderImpl();
    }

    static default IntStream empty() {
        return StreamSupport.intStream(Spliterators.emptyIntSpliterator(), false);
    }

    static default IntStream of(int t) {
        return StreamSupport.intStream(new Streams.IntStreamBuilderImpl(t), false);
    }

    static default IntStream of(int... values) {
        return Arrays.stream(values);
    }

    static default IntStream iterate(final int seed, final IntUnaryOperator f) {
        Objects.requireNonNull(f);
        return StreamSupport.intStream(Spliterators.spliteratorUnknownSize(new PrimitiveIterator.OfInt() {
            /* class java.util.stream.IntStream.AnonymousClass1 */
            int t = seed;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return true;
            }

            @Override // java.util.PrimitiveIterator.OfInt
            public int nextInt() {
                int v = this.t;
                this.t = f.applyAsInt(this.t);
                return v;
            }
        }, 1296), false);
    }

    static default IntStream generate(IntSupplier s) {
        Objects.requireNonNull(s);
        return StreamSupport.intStream(new StreamSpliterators.InfiniteSupplyingSpliterator.OfInt(Long.MAX_VALUE, s), false);
    }

    static default IntStream range(int startInclusive, int endExclusive) {
        if (startInclusive >= endExclusive) {
            return empty();
        }
        return StreamSupport.intStream(new Streams.RangeIntSpliterator(startInclusive, endExclusive, false), false);
    }

    static default IntStream rangeClosed(int startInclusive, int endInclusive) {
        if (startInclusive > endInclusive) {
            return empty();
        }
        return StreamSupport.intStream(new Streams.RangeIntSpliterator(startInclusive, endInclusive, true), false);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.Spliterator$OfInt] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.Spliterator$OfInt] */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static default java.util.stream.IntStream concat(java.util.stream.IntStream r3, java.util.stream.IntStream r4) {
        /*
            java.util.Objects.requireNonNull(r3)
            java.util.Objects.requireNonNull(r4)
            java.util.stream.Streams$ConcatSpliterator$OfInt r0 = new java.util.stream.Streams$ConcatSpliterator$OfInt
            java.util.Spliterator$OfInt r1 = r3.spliterator()
            java.util.Spliterator$OfInt r2 = r4.spliterator()
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
            java.util.stream.IntStream r1 = java.util.stream.StreamSupport.intStream(r0, r1)
            java.lang.Runnable r2 = java.util.stream.Streams.composedClose(r3, r4)
            java.util.stream.BaseStream r2 = r1.onClose(r2)
            java.util.stream.IntStream r2 = (java.util.stream.IntStream) r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.stream.IntStream.concat(java.util.stream.IntStream, java.util.stream.IntStream):java.util.stream.IntStream");
    }

    public interface Builder extends IntConsumer {
        @Override // java.util.function.IntConsumer
        void accept(int i);

        IntStream build();

        default Builder add(int t) {
            accept(t);
            return this;
        }
    }
}
