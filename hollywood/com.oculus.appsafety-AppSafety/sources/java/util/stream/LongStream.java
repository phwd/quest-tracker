package java.util.stream;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LongSummaryStatistics;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiConsumer;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;
import java.util.stream.StreamSpliterators;
import java.util.stream.Streams;

public interface LongStream extends BaseStream<Long, LongStream> {
    boolean allMatch(LongPredicate longPredicate);

    boolean anyMatch(LongPredicate longPredicate);

    DoubleStream asDoubleStream();

    OptionalDouble average();

    Stream<Long> boxed();

    <R> R collect(Supplier<R> supplier, ObjLongConsumer<R> objLongConsumer, BiConsumer<R, R> biConsumer);

    long count();

    LongStream distinct();

    LongStream filter(LongPredicate longPredicate);

    OptionalLong findAny();

    OptionalLong findFirst();

    LongStream flatMap(LongFunction<? extends LongStream> longFunction);

    void forEach(LongConsumer longConsumer);

    void forEachOrdered(LongConsumer longConsumer);

    /* Return type fixed from 'java.util.PrimitiveIterator$OfLong' to match base method */
    @Override // java.util.stream.BaseStream
    Iterator<Long> iterator();

    LongStream limit(long j);

    LongStream map(LongUnaryOperator longUnaryOperator);

    DoubleStream mapToDouble(LongToDoubleFunction longToDoubleFunction);

    IntStream mapToInt(LongToIntFunction longToIntFunction);

    <U> Stream<U> mapToObj(LongFunction<? extends U> longFunction);

    OptionalLong max();

    OptionalLong min();

    boolean noneMatch(LongPredicate longPredicate);

    @Override // java.util.stream.BaseStream
    LongStream parallel();

    LongStream peek(LongConsumer longConsumer);

    long reduce(long j, LongBinaryOperator longBinaryOperator);

    OptionalLong reduce(LongBinaryOperator longBinaryOperator);

    @Override // java.util.stream.BaseStream
    LongStream sequential();

    LongStream skip(long j);

    LongStream sorted();

    /* Return type fixed from 'java.util.Spliterator$OfLong' to match base method */
    @Override // java.util.stream.BaseStream
    Spliterator<Long> spliterator();

    long sum();

    LongSummaryStatistics summaryStatistics();

    long[] toArray();

    static default Builder builder() {
        return new Streams.LongStreamBuilderImpl();
    }

    static default LongStream empty() {
        return StreamSupport.longStream(Spliterators.emptyLongSpliterator(), false);
    }

    static default LongStream of(long t) {
        return StreamSupport.longStream(new Streams.LongStreamBuilderImpl(t), false);
    }

    static default LongStream of(long... values) {
        return Arrays.stream(values);
    }

    static default LongStream iterate(final long seed, final LongUnaryOperator f) {
        Objects.requireNonNull(f);
        return StreamSupport.longStream(Spliterators.spliteratorUnknownSize(new PrimitiveIterator.OfLong() {
            /* class java.util.stream.LongStream.AnonymousClass1 */
            long t = seed;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return true;
            }

            @Override // java.util.PrimitiveIterator.OfLong
            public long nextLong() {
                long v = this.t;
                this.t = f.applyAsLong(this.t);
                return v;
            }
        }, 1296), false);
    }

    static default LongStream generate(LongSupplier s) {
        Objects.requireNonNull(s);
        return StreamSupport.longStream(new StreamSpliterators.InfiniteSupplyingSpliterator.OfLong(Long.MAX_VALUE, s), false);
    }

    static default LongStream range(long startInclusive, long endExclusive) {
        if (startInclusive >= endExclusive) {
            return empty();
        }
        if (endExclusive - startInclusive >= 0) {
            return StreamSupport.longStream(new Streams.RangeLongSpliterator(startInclusive, endExclusive, false), false);
        }
        long m = Long.divideUnsigned(endExclusive - startInclusive, 2) + startInclusive + 1;
        return concat(range(startInclusive, m), range(m, endExclusive));
    }

    static default LongStream rangeClosed(long startInclusive, long endInclusive) {
        if (startInclusive > endInclusive) {
            return empty();
        }
        if ((endInclusive - startInclusive) + 1 > 0) {
            return StreamSupport.longStream(new Streams.RangeLongSpliterator(startInclusive, endInclusive, true), false);
        }
        long m = Long.divideUnsigned(endInclusive - startInclusive, 2) + startInclusive + 1;
        return concat(range(startInclusive, m), rangeClosed(m, endInclusive));
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.Spliterator$OfLong] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.Spliterator$OfLong] */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static default java.util.stream.LongStream concat(java.util.stream.LongStream r3, java.util.stream.LongStream r4) {
        /*
            java.util.Objects.requireNonNull(r3)
            java.util.Objects.requireNonNull(r4)
            java.util.stream.Streams$ConcatSpliterator$OfLong r0 = new java.util.stream.Streams$ConcatSpliterator$OfLong
            java.util.Spliterator$OfLong r1 = r3.spliterator()
            java.util.Spliterator$OfLong r2 = r4.spliterator()
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
            java.util.stream.LongStream r1 = java.util.stream.StreamSupport.longStream(r0, r1)
            java.lang.Runnable r2 = java.util.stream.Streams.composedClose(r3, r4)
            java.util.stream.BaseStream r2 = r1.onClose(r2)
            java.util.stream.LongStream r2 = (java.util.stream.LongStream) r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.stream.LongStream.concat(java.util.stream.LongStream, java.util.stream.LongStream):java.util.stream.LongStream");
    }

    public interface Builder extends LongConsumer {
        @Override // java.util.function.LongConsumer
        void accept(long j);

        LongStream build();

        default Builder add(long t) {
            accept(t);
            return this;
        }
    }
}
