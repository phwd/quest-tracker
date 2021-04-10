package java.util.stream;

import java.util.LongSummaryStatistics;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.IntFunction;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;
import java.util.stream.DoublePipeline;
import java.util.stream.IntPipeline;
import java.util.stream.MatchOps;
import java.util.stream.Node;
import java.util.stream.ReferencePipeline;
import java.util.stream.Sink;
import java.util.stream.StreamSpliterators;

public abstract class LongPipeline<E_IN> extends AbstractPipeline<E_IN, Long, LongStream> implements LongStream {
    @Override // java.util.stream.LongStream, java.util.stream.LongStream, java.util.stream.AbstractPipeline, java.util.stream.BaseStream
    public /* bridge */ /* synthetic */ LongStream parallel() {
        return (LongStream) super.parallel();
    }

    @Override // java.util.stream.LongStream, java.util.stream.LongStream, java.util.stream.AbstractPipeline, java.util.stream.BaseStream
    public /* bridge */ /* synthetic */ LongStream sequential() {
        return (LongStream) super.sequential();
    }

    LongPipeline(Supplier<? extends Spliterator<Long>> source, int sourceFlags, boolean parallel) {
        super(source, sourceFlags, parallel);
    }

    LongPipeline(Spliterator<Long> source, int sourceFlags, boolean parallel) {
        super(source, sourceFlags, parallel);
    }

    LongPipeline(AbstractPipeline<?, E_IN, ?> upstream, int opFlags) {
        super(upstream, opFlags);
    }

    private static LongConsumer adapt(Sink<Long> sink) {
        if (sink instanceof LongConsumer) {
            return (LongConsumer) sink;
        }
        if (Tripwire.ENABLED) {
            Tripwire.trip(AbstractPipeline.class, "using LongStream.adapt(Sink<Long> s)");
        }
        Objects.requireNonNull(sink);
        return new LongConsumer() {
            /* class java.util.stream.$$Lambda$zQ9PoGPFOA3MjNNbaERnRB6ik */

            @Override // java.util.function.LongConsumer
            public final void accept(long j) {
                Sink.this.accept(j);
            }
        };
    }

    /* access modifiers changed from: private */
    public static Spliterator.OfLong adapt(Spliterator<Long> s) {
        if (s instanceof Spliterator.OfLong) {
            return (Spliterator.OfLong) s;
        }
        if (Tripwire.ENABLED) {
            Tripwire.trip(AbstractPipeline.class, "using LongStream.adapt(Spliterator<Long> s)");
        }
        throw new UnsupportedOperationException("LongStream.adapt(Spliterator<Long> s)");
    }

    @Override // java.util.stream.AbstractPipeline
    public final StreamShape getOutputShape() {
        return StreamShape.LONG_VALUE;
    }

    @Override // java.util.stream.AbstractPipeline
    public final <P_IN> Node<Long> evaluateToNode(PipelineHelper<Long> helper, Spliterator<P_IN> spliterator, boolean flattenTree, IntFunction<Long[]> intFunction) {
        return Nodes.collectLong(helper, spliterator, flattenTree);
    }

    @Override // java.util.stream.AbstractPipeline
    public final <P_IN> Spliterator<Long> wrap(PipelineHelper<Long> ph, Supplier<Spliterator<P_IN>> supplier, boolean isParallel) {
        return new StreamSpliterators.LongWrappingSpliterator(ph, supplier, isParallel);
    }

    /* Return type fixed from 'java.util.Spliterator$OfLong' to match base method */
    @Override // java.util.stream.AbstractPipeline
    public final Spliterator<Long> lazySpliterator(Supplier<? extends Spliterator<Long>> supplier) {
        return new StreamSpliterators.DelegatingSpliterator.OfLong(supplier);
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000e  */
    @Override // java.util.stream.AbstractPipeline
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void forEachWithCancel(java.util.Spliterator<java.lang.Long> r4, java.util.stream.Sink<java.lang.Long> r5) {
        /*
            r3 = this;
            java.util.Spliterator$OfLong r0 = adapt(r4)
            java.util.function.LongConsumer r1 = adapt(r5)
        L_0x0008:
            boolean r2 = r5.cancellationRequested()
            if (r2 != 0) goto L_0x0014
            boolean r2 = r0.tryAdvance(r1)
            if (r2 != 0) goto L_0x0008
        L_0x0014:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.stream.LongPipeline.forEachWithCancel(java.util.Spliterator, java.util.stream.Sink):void");
    }

    @Override // java.util.stream.AbstractPipeline, java.util.stream.PipelineHelper
    public final Node.Builder<Long> makeNodeBuilder(long exactSizeIfKnown, IntFunction<Long[]> intFunction) {
        return Nodes.longBuilder(exactSizeIfKnown);
    }

    /* Return type fixed from 'java.util.PrimitiveIterator$OfLong' to match base method */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Spliterator$OfLong] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // java.util.stream.LongStream, java.util.stream.LongStream, java.util.stream.BaseStream
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Iterator<java.lang.Long> iterator() {
        /*
            r1 = this;
            java.util.Spliterator$OfLong r0 = r1.spliterator()
            java.util.PrimitiveIterator$OfLong r0 = java.util.Spliterators.iterator(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.stream.LongPipeline.iterator():java.util.PrimitiveIterator$OfLong");
    }

    /* Return type fixed from 'java.util.Spliterator$OfLong' to match base method */
    @Override // java.util.stream.LongStream, java.util.stream.LongStream, java.util.stream.AbstractPipeline, java.util.stream.BaseStream
    public final Spliterator<Long> spliterator() {
        return adapt(super.spliterator());
    }

    @Override // java.util.stream.LongStream
    public final DoubleStream asDoubleStream() {
        return new DoublePipeline.StatelessOp<Long>(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            /* class java.util.stream.LongPipeline.AnonymousClass1 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Long> opWrapSink(int flags, Sink<Double> sink) {
                return new Sink.ChainedLong<Double>(sink) {
                    /* class java.util.stream.LongPipeline.AnonymousClass1.AnonymousClass1 */

                    @Override // java.util.stream.Sink.OfLong, java.util.stream.Sink, java.util.function.LongConsumer
                    public void accept(long t) {
                        this.downstream.accept((double) t);
                    }
                };
            }
        };
    }

    @Override // java.util.stream.LongStream
    public final Stream<Long> boxed() {
        return mapToObj($$Lambda$w4zz3RuWVbX94KiVllUNB6u_ygA.INSTANCE);
    }

    @Override // java.util.stream.LongStream
    public final LongStream map(final LongUnaryOperator mapper) {
        Objects.requireNonNull(mapper);
        return new StatelessOp<Long>(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            /* class java.util.stream.LongPipeline.AnonymousClass2 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Long> opWrapSink(int flags, Sink<Long> sink) {
                return new Sink.ChainedLong<Long>(sink) {
                    /* class java.util.stream.LongPipeline.AnonymousClass2.AnonymousClass1 */

                    @Override // java.util.stream.Sink.OfLong, java.util.stream.Sink, java.util.function.LongConsumer
                    public void accept(long t) {
                        this.downstream.accept(mapper.applyAsLong(t));
                    }
                };
            }
        };
    }

    @Override // java.util.stream.LongStream
    public final <U> Stream<U> mapToObj(final LongFunction<? extends U> mapper) {
        Objects.requireNonNull(mapper);
        return new ReferencePipeline.StatelessOp<Long, U>(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            /* class java.util.stream.LongPipeline.AnonymousClass3 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Long> opWrapSink(int flags, Sink<U> sink) {
                return new Sink.ChainedLong<U>(sink) {
                    /* class java.util.stream.LongPipeline.AnonymousClass3.AnonymousClass1 */

                    @Override // java.util.stream.Sink.OfLong, java.util.stream.Sink, java.util.function.LongConsumer
                    public void accept(long t) {
                        this.downstream.accept(mapper.apply(t));
                    }
                };
            }
        };
    }

    @Override // java.util.stream.LongStream
    public final IntStream mapToInt(final LongToIntFunction mapper) {
        Objects.requireNonNull(mapper);
        return new IntPipeline.StatelessOp<Long>(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            /* class java.util.stream.LongPipeline.AnonymousClass4 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Long> opWrapSink(int flags, Sink<Integer> sink) {
                return new Sink.ChainedLong<Integer>(sink) {
                    /* class java.util.stream.LongPipeline.AnonymousClass4.AnonymousClass1 */

                    @Override // java.util.stream.Sink.OfLong, java.util.stream.Sink, java.util.function.LongConsumer
                    public void accept(long t) {
                        this.downstream.accept(mapper.applyAsInt(t));
                    }
                };
            }
        };
    }

    @Override // java.util.stream.LongStream
    public final DoubleStream mapToDouble(final LongToDoubleFunction mapper) {
        Objects.requireNonNull(mapper);
        return new DoublePipeline.StatelessOp<Long>(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            /* class java.util.stream.LongPipeline.AnonymousClass5 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Long> opWrapSink(int flags, Sink<Double> sink) {
                return new Sink.ChainedLong<Double>(sink) {
                    /* class java.util.stream.LongPipeline.AnonymousClass5.AnonymousClass1 */

                    @Override // java.util.stream.Sink.OfLong, java.util.stream.Sink, java.util.function.LongConsumer
                    public void accept(long t) {
                        this.downstream.accept(mapper.applyAsDouble(t));
                    }
                };
            }
        };
    }

    @Override // java.util.stream.LongStream
    public final LongStream flatMap(final LongFunction<? extends LongStream> mapper) {
        return new StatelessOp<Long>(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) {
            /* class java.util.stream.LongPipeline.AnonymousClass6 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Long> opWrapSink(int flags, Sink<Long> sink) {
                return new Sink.ChainedLong<Long>(sink) {
                    /* class java.util.stream.LongPipeline.AnonymousClass6.AnonymousClass1 */

                    @Override // java.util.stream.Sink.ChainedLong, java.util.stream.Sink
                    public void begin(long size) {
                        this.downstream.begin(-1);
                    }

                    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0020, code lost:
                        r3 = move-exception;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0021, code lost:
                        r1.addSuppressed(r3);
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0024, code lost:
                        throw r2;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001b, code lost:
                        r2 = move-exception;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
                        r0.close();
                     */
                    @Override // java.util.stream.Sink.OfLong, java.util.stream.Sink, java.util.function.LongConsumer
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void accept(long r5) {
                        /*
                            r4 = this;
                            java.util.stream.LongPipeline$6 r0 = java.util.stream.LongPipeline.AnonymousClass6.this
                            java.util.function.LongFunction r0 = r8
                            java.lang.Object r0 = r0.apply(r5)
                            java.util.stream.LongStream r0 = (java.util.stream.LongStream) r0
                            if (r0 == 0) goto L_0x0025
                            java.util.stream.LongStream r1 = r0.sequential()     // Catch:{ all -> 0x0019 }
                            java.util.stream.-$$Lambda$LongPipeline$6$1$fLvJH_Wq0Kv-MEJSFU3IOaEtvxk r2 = new java.util.stream.-$$Lambda$LongPipeline$6$1$fLvJH_Wq0Kv-MEJSFU3IOaEtvxk     // Catch:{ all -> 0x0019 }
                            r2.<init>()     // Catch:{ all -> 0x0019 }
                            r1.forEach(r2)     // Catch:{ all -> 0x0019 }
                            goto L_0x0025
                        L_0x0019:
                            r1 = move-exception
                            throw r1     // Catch:{ all -> 0x001b }
                        L_0x001b:
                            r2 = move-exception
                            r0.close()     // Catch:{ all -> 0x0020 }
                            goto L_0x0024
                        L_0x0020:
                            r3 = move-exception
                            r1.addSuppressed(r3)
                        L_0x0024:
                            throw r2
                        L_0x0025:
                            if (r0 == 0) goto L_0x002a
                            r0.close()
                        L_0x002a:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: java.util.stream.LongPipeline.AnonymousClass6.AnonymousClass1.accept(long):void");
                    }

                    public /* synthetic */ void lambda$accept$0$LongPipeline$6$1(long i) {
                        this.downstream.accept(i);
                    }
                };
            }
        };
    }

    @Override // java.util.stream.BaseStream
    public LongStream unordered() {
        if (!isOrdered()) {
            return this;
        }
        return new StatelessOp<Long>(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_ORDERED) {
            /* class java.util.stream.LongPipeline.AnonymousClass7 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Long> opWrapSink(int flags, Sink<Long> sink) {
                return sink;
            }
        };
    }

    @Override // java.util.stream.LongStream
    public final LongStream filter(final LongPredicate predicate) {
        Objects.requireNonNull(predicate);
        return new StatelessOp<Long>(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_SIZED) {
            /* class java.util.stream.LongPipeline.AnonymousClass8 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Long> opWrapSink(int flags, Sink<Long> sink) {
                return new Sink.ChainedLong<Long>(sink) {
                    /* class java.util.stream.LongPipeline.AnonymousClass8.AnonymousClass1 */

                    @Override // java.util.stream.Sink.ChainedLong, java.util.stream.Sink
                    public void begin(long size) {
                        this.downstream.begin(-1);
                    }

                    @Override // java.util.stream.Sink.OfLong, java.util.stream.Sink, java.util.function.LongConsumer
                    public void accept(long t) {
                        if (predicate.test(t)) {
                            this.downstream.accept(t);
                        }
                    }
                };
            }
        };
    }

    @Override // java.util.stream.LongStream
    public final LongStream peek(final LongConsumer action) {
        Objects.requireNonNull(action);
        return new StatelessOp<Long>(this, StreamShape.LONG_VALUE, 0) {
            /* class java.util.stream.LongPipeline.AnonymousClass9 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Long> opWrapSink(int flags, Sink<Long> sink) {
                return new Sink.ChainedLong<Long>(sink) {
                    /* class java.util.stream.LongPipeline.AnonymousClass9.AnonymousClass1 */

                    @Override // java.util.stream.Sink.OfLong, java.util.stream.Sink, java.util.function.LongConsumer
                    public void accept(long t) {
                        action.accept(t);
                        this.downstream.accept(t);
                    }
                };
            }
        };
    }

    @Override // java.util.stream.LongStream
    public final LongStream limit(long maxSize) {
        if (maxSize >= 0) {
            return SliceOps.makeLong(this, 0, maxSize);
        }
        throw new IllegalArgumentException(Long.toString(maxSize));
    }

    @Override // java.util.stream.LongStream
    public final LongStream skip(long n) {
        if (n < 0) {
            throw new IllegalArgumentException(Long.toString(n));
        } else if (n == 0) {
            return this;
        } else {
            return SliceOps.makeLong(this, n, -1);
        }
    }

    @Override // java.util.stream.LongStream
    public final LongStream sorted() {
        return SortedOps.makeLong(this);
    }

    @Override // java.util.stream.LongStream
    public final LongStream distinct() {
        return boxed().distinct().mapToLong($$Lambda$LongPipeline$doop4YO9hzEFGaLnLB3xKA404M4.INSTANCE);
    }

    @Override // java.util.stream.LongStream
    public void forEach(LongConsumer action) {
        evaluate((TerminalOp<E_OUT, R>) ForEachOps.makeLong(action, false));
    }

    @Override // java.util.stream.LongStream
    public void forEachOrdered(LongConsumer action) {
        evaluate((TerminalOp<E_OUT, R>) ForEachOps.makeLong(action, true));
    }

    @Override // java.util.stream.LongStream
    public final long sum() {
        return reduce(0, $$Lambda$dplkPhACWDPIy18ogwdupEQaN40.INSTANCE);
    }

    @Override // java.util.stream.LongStream
    public final OptionalLong min() {
        return reduce($$Lambda$OExyAlU04fvFLvnsXWOUeFS6K6Y.INSTANCE);
    }

    @Override // java.util.stream.LongStream
    public final OptionalLong max() {
        return reduce($$Lambda$6eeAyFpmvaed9kw3uuEs0ErN7sg.INSTANCE);
    }

    static /* synthetic */ long[] lambda$average$1() {
        return new long[2];
    }

    @Override // java.util.stream.LongStream
    public final OptionalDouble average() {
        long[] avg = (long[]) collect($$Lambda$LongPipeline$C2qxkG7ctBwIL2ufjYSA46AbOM.INSTANCE, $$Lambda$LongPipeline$sfTgyfHS4klE7h4z5MNXsSIFcQ.INSTANCE, $$Lambda$LongPipeline$unkecqyY0oPqnMvfYdq_wAGb9pY.INSTANCE);
        if (avg[0] > 0) {
            return OptionalDouble.of(((double) avg[1]) / ((double) avg[0]));
        }
        return OptionalDouble.empty();
    }

    static /* synthetic */ void lambda$average$2(long[] ll, long i) {
        ll[0] = ll[0] + 1;
        ll[1] = ll[1] + i;
    }

    static /* synthetic */ void lambda$average$3(long[] ll, long[] rr) {
        ll[0] = ll[0] + rr[0];
        ll[1] = ll[1] + rr[1];
    }

    static /* synthetic */ long lambda$count$4(long e) {
        return 1;
    }

    @Override // java.util.stream.LongStream
    public final long count() {
        return map($$Lambda$LongPipeline$HjmjwoQcQfPYnTF2E4GrQONBjyM.INSTANCE).sum();
    }

    @Override // java.util.stream.LongStream
    public final LongSummaryStatistics summaryStatistics() {
        return (LongSummaryStatistics) collect($$Lambda$kZuTETptiPwvB1J27Na7j760aLU.INSTANCE, $$Lambda$Y_fORtDI6zkwP_Z_VGSwO2GcnS0.INSTANCE, $$Lambda$JNjUhnscc8mcsjlQNaAi4qIfRDQ.INSTANCE);
    }

    @Override // java.util.stream.LongStream
    public final long reduce(long identity, LongBinaryOperator op) {
        return ((Long) evaluate((TerminalOp<E_OUT, R>) ReduceOps.makeLong(identity, op))).longValue();
    }

    @Override // java.util.stream.LongStream
    public final OptionalLong reduce(LongBinaryOperator op) {
        return (OptionalLong) evaluate((TerminalOp<E_OUT, R>) ReduceOps.makeLong(op));
    }

    @Override // java.util.stream.LongStream
    public final <R> R collect(Supplier<R> supplier, ObjLongConsumer<R> accumulator, BiConsumer<R, R> combiner) {
        return (R) evaluate(ReduceOps.makeLong(supplier, accumulator, new BinaryOperator() {
            /* class java.util.stream.$$Lambda$LongPipeline$BxZA1c1Y79VaVw54W8s5K5ji_0 */

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return BiConsumer.this.accept(obj, obj2);
            }
        }));
    }

    @Override // java.util.stream.LongStream
    public final boolean anyMatch(LongPredicate predicate) {
        return ((Boolean) evaluate((TerminalOp<E_OUT, R>) MatchOps.makeLong(predicate, MatchOps.MatchKind.ANY))).booleanValue();
    }

    @Override // java.util.stream.LongStream
    public final boolean allMatch(LongPredicate predicate) {
        return ((Boolean) evaluate((TerminalOp<E_OUT, R>) MatchOps.makeLong(predicate, MatchOps.MatchKind.ALL))).booleanValue();
    }

    @Override // java.util.stream.LongStream
    public final boolean noneMatch(LongPredicate predicate) {
        return ((Boolean) evaluate((TerminalOp<E_OUT, R>) MatchOps.makeLong(predicate, MatchOps.MatchKind.NONE))).booleanValue();
    }

    @Override // java.util.stream.LongStream
    public final OptionalLong findFirst() {
        return (OptionalLong) evaluate((TerminalOp<E_OUT, R>) FindOps.makeLong(true));
    }

    @Override // java.util.stream.LongStream
    public final OptionalLong findAny() {
        return (OptionalLong) evaluate((TerminalOp<E_OUT, R>) FindOps.makeLong(false));
    }

    static /* synthetic */ Long[] lambda$toArray$6(int x$0) {
        return new Long[x$0];
    }

    @Override // java.util.stream.LongStream
    public final long[] toArray() {
        return (long[]) Nodes.flattenLong((Node.OfLong) evaluateToArrayNode($$Lambda$LongPipeline$LTFlNC6dzl63DE63FJGCsG7H_c.INSTANCE)).asPrimitiveArray();
    }

    public static class Head<E_IN> extends LongPipeline<E_IN> {
        @Override // java.util.stream.LongPipeline, java.util.stream.LongStream, java.util.stream.LongStream, java.util.stream.AbstractPipeline, java.util.stream.BaseStream
        public /* bridge */ /* synthetic */ LongStream parallel() {
            return (LongStream) LongPipeline.super.parallel();
        }

        @Override // java.util.stream.LongPipeline, java.util.stream.LongStream, java.util.stream.LongStream, java.util.stream.AbstractPipeline, java.util.stream.BaseStream
        public /* bridge */ /* synthetic */ LongStream sequential() {
            return (LongStream) LongPipeline.super.sequential();
        }

        public Head(Supplier<? extends Spliterator<Long>> source, int sourceFlags, boolean parallel) {
            super(source, sourceFlags, parallel);
        }

        public Head(Spliterator<Long> source, int sourceFlags, boolean parallel) {
            super(source, sourceFlags, parallel);
        }

        @Override // java.util.stream.AbstractPipeline
        public final boolean opIsStateful() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.stream.AbstractPipeline
        public final Sink<E_IN> opWrapSink(int flags, Sink<Long> sink) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.stream.LongPipeline, java.util.stream.LongStream
        public void forEach(LongConsumer action) {
            if (!isParallel()) {
                LongPipeline.adapt(sourceStageSpliterator()).forEachRemaining(action);
            } else {
                LongPipeline.super.forEach(action);
            }
        }

        @Override // java.util.stream.LongPipeline, java.util.stream.LongStream
        public void forEachOrdered(LongConsumer action) {
            if (!isParallel()) {
                LongPipeline.adapt(sourceStageSpliterator()).forEachRemaining(action);
            } else {
                LongPipeline.super.forEachOrdered(action);
            }
        }
    }

    public static abstract class StatelessOp<E_IN> extends LongPipeline<E_IN> {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        @Override // java.util.stream.LongPipeline, java.util.stream.LongStream, java.util.stream.LongStream, java.util.stream.AbstractPipeline, java.util.stream.BaseStream
        public /* bridge */ /* synthetic */ LongStream parallel() {
            return (LongStream) LongPipeline.super.parallel();
        }

        @Override // java.util.stream.LongPipeline, java.util.stream.LongStream, java.util.stream.LongStream, java.util.stream.AbstractPipeline, java.util.stream.BaseStream
        public /* bridge */ /* synthetic */ LongStream sequential() {
            return (LongStream) LongPipeline.super.sequential();
        }

        public StatelessOp(AbstractPipeline<?, E_IN, ?> upstream, StreamShape inputShape, int opFlags) {
            super(upstream, opFlags);
        }

        @Override // java.util.stream.AbstractPipeline
        public final boolean opIsStateful() {
            return false;
        }
    }

    public static abstract class StatefulOp<E_IN> extends LongPipeline<E_IN> {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        @Override // java.util.stream.AbstractPipeline
        public abstract <P_IN> Node<Long> opEvaluateParallel(PipelineHelper<Long> pipelineHelper, Spliterator<P_IN> spliterator, IntFunction<Long[]> intFunction);

        @Override // java.util.stream.LongPipeline, java.util.stream.LongStream, java.util.stream.LongStream, java.util.stream.AbstractPipeline, java.util.stream.BaseStream
        public /* bridge */ /* synthetic */ LongStream parallel() {
            return (LongStream) LongPipeline.super.parallel();
        }

        @Override // java.util.stream.LongPipeline, java.util.stream.LongStream, java.util.stream.LongStream, java.util.stream.AbstractPipeline, java.util.stream.BaseStream
        public /* bridge */ /* synthetic */ LongStream sequential() {
            return (LongStream) LongPipeline.super.sequential();
        }

        public StatefulOp(AbstractPipeline<?, E_IN, ?> upstream, StreamShape inputShape, int opFlags) {
            super(upstream, opFlags);
        }

        @Override // java.util.stream.AbstractPipeline
        public final boolean opIsStateful() {
            return true;
        }
    }
}
