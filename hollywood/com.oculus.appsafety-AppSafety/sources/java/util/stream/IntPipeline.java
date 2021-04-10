package java.util.stream;

import java.util.IntSummaryStatistics;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.stream.DoublePipeline;
import java.util.stream.LongPipeline;
import java.util.stream.MatchOps;
import java.util.stream.Node;
import java.util.stream.ReferencePipeline;
import java.util.stream.Sink;
import java.util.stream.StreamSpliterators;

public abstract class IntPipeline<E_IN> extends AbstractPipeline<E_IN, Integer, IntStream> implements IntStream {
    @Override // java.util.stream.IntStream, java.util.stream.IntStream, java.util.stream.AbstractPipeline, java.util.stream.BaseStream
    public /* bridge */ /* synthetic */ IntStream parallel() {
        return (IntStream) super.parallel();
    }

    @Override // java.util.stream.IntStream, java.util.stream.IntStream, java.util.stream.AbstractPipeline, java.util.stream.BaseStream
    public /* bridge */ /* synthetic */ IntStream sequential() {
        return (IntStream) super.sequential();
    }

    IntPipeline(Supplier<? extends Spliterator<Integer>> source, int sourceFlags, boolean parallel) {
        super(source, sourceFlags, parallel);
    }

    IntPipeline(Spliterator<Integer> source, int sourceFlags, boolean parallel) {
        super(source, sourceFlags, parallel);
    }

    IntPipeline(AbstractPipeline<?, E_IN, ?> upstream, int opFlags) {
        super(upstream, opFlags);
    }

    private static IntConsumer adapt(Sink<Integer> sink) {
        if (sink instanceof IntConsumer) {
            return (IntConsumer) sink;
        }
        if (Tripwire.ENABLED) {
            Tripwire.trip(AbstractPipeline.class, "using IntStream.adapt(Sink<Integer> s)");
        }
        Objects.requireNonNull(sink);
        return new IntConsumer() {
            /* class java.util.stream.$$Lambda$wDsxx48ovPSGeNEb3P6H9u7YX0k */

            @Override // java.util.function.IntConsumer
            public final void accept(int i) {
                Sink.this.accept(i);
            }
        };
    }

    /* access modifiers changed from: private */
    public static Spliterator.OfInt adapt(Spliterator<Integer> s) {
        if (s instanceof Spliterator.OfInt) {
            return (Spliterator.OfInt) s;
        }
        if (Tripwire.ENABLED) {
            Tripwire.trip(AbstractPipeline.class, "using IntStream.adapt(Spliterator<Integer> s)");
        }
        throw new UnsupportedOperationException("IntStream.adapt(Spliterator<Integer> s)");
    }

    @Override // java.util.stream.AbstractPipeline
    public final StreamShape getOutputShape() {
        return StreamShape.INT_VALUE;
    }

    @Override // java.util.stream.AbstractPipeline
    public final <P_IN> Node<Integer> evaluateToNode(PipelineHelper<Integer> helper, Spliterator<P_IN> spliterator, boolean flattenTree, IntFunction<Integer[]> intFunction) {
        return Nodes.collectInt(helper, spliterator, flattenTree);
    }

    @Override // java.util.stream.AbstractPipeline
    public final <P_IN> Spliterator<Integer> wrap(PipelineHelper<Integer> ph, Supplier<Spliterator<P_IN>> supplier, boolean isParallel) {
        return new StreamSpliterators.IntWrappingSpliterator(ph, supplier, isParallel);
    }

    /* Return type fixed from 'java.util.Spliterator$OfInt' to match base method */
    @Override // java.util.stream.AbstractPipeline
    public final Spliterator<Integer> lazySpliterator(Supplier<? extends Spliterator<Integer>> supplier) {
        return new StreamSpliterators.DelegatingSpliterator.OfInt(supplier);
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000e  */
    @Override // java.util.stream.AbstractPipeline
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void forEachWithCancel(java.util.Spliterator<java.lang.Integer> r4, java.util.stream.Sink<java.lang.Integer> r5) {
        /*
            r3 = this;
            java.util.Spliterator$OfInt r0 = adapt(r4)
            java.util.function.IntConsumer r1 = adapt(r5)
        L_0x0008:
            boolean r2 = r5.cancellationRequested()
            if (r2 != 0) goto L_0x0014
            boolean r2 = r0.tryAdvance(r1)
            if (r2 != 0) goto L_0x0008
        L_0x0014:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.stream.IntPipeline.forEachWithCancel(java.util.Spliterator, java.util.stream.Sink):void");
    }

    @Override // java.util.stream.AbstractPipeline, java.util.stream.PipelineHelper
    public final Node.Builder<Integer> makeNodeBuilder(long exactSizeIfKnown, IntFunction<Integer[]> intFunction) {
        return Nodes.intBuilder(exactSizeIfKnown);
    }

    /* Return type fixed from 'java.util.PrimitiveIterator$OfInt' to match base method */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Spliterator$OfInt] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // java.util.stream.IntStream, java.util.stream.IntStream, java.util.stream.BaseStream
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Iterator<java.lang.Integer> iterator() {
        /*
            r1 = this;
            java.util.Spliterator$OfInt r0 = r1.spliterator()
            java.util.PrimitiveIterator$OfInt r0 = java.util.Spliterators.iterator(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.stream.IntPipeline.iterator():java.util.PrimitiveIterator$OfInt");
    }

    /* Return type fixed from 'java.util.Spliterator$OfInt' to match base method */
    @Override // java.util.stream.IntStream, java.util.stream.IntStream, java.util.stream.AbstractPipeline, java.util.stream.BaseStream
    public final Spliterator<Integer> spliterator() {
        return adapt(super.spliterator());
    }

    @Override // java.util.stream.IntStream
    public final LongStream asLongStream() {
        return new LongPipeline.StatelessOp<Integer>(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            /* class java.util.stream.IntPipeline.AnonymousClass1 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Integer> opWrapSink(int flags, Sink<Long> sink) {
                return new Sink.ChainedInt<Long>(sink) {
                    /* class java.util.stream.IntPipeline.AnonymousClass1.AnonymousClass1 */

                    @Override // java.util.stream.Sink.OfInt, java.util.function.IntConsumer, java.util.stream.Sink
                    public void accept(int t) {
                        this.downstream.accept((long) t);
                    }
                };
            }
        };
    }

    @Override // java.util.stream.IntStream
    public final DoubleStream asDoubleStream() {
        return new DoublePipeline.StatelessOp<Integer>(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            /* class java.util.stream.IntPipeline.AnonymousClass2 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Integer> opWrapSink(int flags, Sink<Double> sink) {
                return new Sink.ChainedInt<Double>(sink) {
                    /* class java.util.stream.IntPipeline.AnonymousClass2.AnonymousClass1 */

                    @Override // java.util.stream.Sink.OfInt, java.util.function.IntConsumer, java.util.stream.Sink
                    public void accept(int t) {
                        this.downstream.accept((double) t);
                    }
                };
            }
        };
    }

    @Override // java.util.stream.IntStream
    public final Stream<Integer> boxed() {
        return mapToObj($$Lambda$wFoizRiPqYBPe0X4aSzbj2iL3g.INSTANCE);
    }

    @Override // java.util.stream.IntStream
    public final IntStream map(final IntUnaryOperator mapper) {
        Objects.requireNonNull(mapper);
        return new StatelessOp<Integer>(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            /* class java.util.stream.IntPipeline.AnonymousClass3 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Integer> opWrapSink(int flags, Sink<Integer> sink) {
                return new Sink.ChainedInt<Integer>(sink) {
                    /* class java.util.stream.IntPipeline.AnonymousClass3.AnonymousClass1 */

                    @Override // java.util.stream.Sink.OfInt, java.util.function.IntConsumer, java.util.stream.Sink
                    public void accept(int t) {
                        this.downstream.accept(mapper.applyAsInt(t));
                    }
                };
            }
        };
    }

    @Override // java.util.stream.IntStream
    public final <U> Stream<U> mapToObj(final IntFunction<? extends U> mapper) {
        Objects.requireNonNull(mapper);
        return new ReferencePipeline.StatelessOp<Integer, U>(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            /* class java.util.stream.IntPipeline.AnonymousClass4 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Integer> opWrapSink(int flags, Sink<U> sink) {
                return new Sink.ChainedInt<U>(sink) {
                    /* class java.util.stream.IntPipeline.AnonymousClass4.AnonymousClass1 */

                    @Override // java.util.stream.Sink.OfInt, java.util.function.IntConsumer, java.util.stream.Sink
                    public void accept(int t) {
                        this.downstream.accept(mapper.apply(t));
                    }
                };
            }
        };
    }

    @Override // java.util.stream.IntStream
    public final LongStream mapToLong(final IntToLongFunction mapper) {
        Objects.requireNonNull(mapper);
        return new LongPipeline.StatelessOp<Integer>(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            /* class java.util.stream.IntPipeline.AnonymousClass5 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Integer> opWrapSink(int flags, Sink<Long> sink) {
                return new Sink.ChainedInt<Long>(sink) {
                    /* class java.util.stream.IntPipeline.AnonymousClass5.AnonymousClass1 */

                    @Override // java.util.stream.Sink.OfInt, java.util.function.IntConsumer, java.util.stream.Sink
                    public void accept(int t) {
                        this.downstream.accept(mapper.applyAsLong(t));
                    }
                };
            }
        };
    }

    @Override // java.util.stream.IntStream
    public final DoubleStream mapToDouble(final IntToDoubleFunction mapper) {
        Objects.requireNonNull(mapper);
        return new DoublePipeline.StatelessOp<Integer>(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            /* class java.util.stream.IntPipeline.AnonymousClass6 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Integer> opWrapSink(int flags, Sink<Double> sink) {
                return new Sink.ChainedInt<Double>(sink) {
                    /* class java.util.stream.IntPipeline.AnonymousClass6.AnonymousClass1 */

                    @Override // java.util.stream.Sink.OfInt, java.util.function.IntConsumer, java.util.stream.Sink
                    public void accept(int t) {
                        this.downstream.accept(mapper.applyAsDouble(t));
                    }
                };
            }
        };
    }

    @Override // java.util.stream.IntStream
    public final IntStream flatMap(final IntFunction<? extends IntStream> mapper) {
        return new StatelessOp<Integer>(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) {
            /* class java.util.stream.IntPipeline.AnonymousClass7 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Integer> opWrapSink(int flags, Sink<Integer> sink) {
                return new Sink.ChainedInt<Integer>(sink) {
                    /* class java.util.stream.IntPipeline.AnonymousClass7.AnonymousClass1 */

                    @Override // java.util.stream.Sink.ChainedInt, java.util.stream.Sink
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
                    @Override // java.util.stream.Sink.OfInt, java.util.function.IntConsumer, java.util.stream.Sink
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void accept(int r5) {
                        /*
                            r4 = this;
                            java.util.stream.IntPipeline$7 r0 = java.util.stream.IntPipeline.AnonymousClass7.this
                            java.util.function.IntFunction r0 = r8
                            java.lang.Object r0 = r0.apply(r5)
                            java.util.stream.IntStream r0 = (java.util.stream.IntStream) r0
                            if (r0 == 0) goto L_0x0025
                            java.util.stream.IntStream r1 = r0.sequential()     // Catch:{ all -> 0x0019 }
                            java.util.stream.-$$Lambda$IntPipeline$7$1$E2wwNE1UnVxs0E9-n47lRWmnJGM r2 = new java.util.stream.-$$Lambda$IntPipeline$7$1$E2wwNE1UnVxs0E9-n47lRWmnJGM     // Catch:{ all -> 0x0019 }
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
                        throw new UnsupportedOperationException("Method not decompiled: java.util.stream.IntPipeline.AnonymousClass7.AnonymousClass1.accept(int):void");
                    }

                    public /* synthetic */ void lambda$accept$0$IntPipeline$7$1(int i) {
                        this.downstream.accept(i);
                    }
                };
            }
        };
    }

    @Override // java.util.stream.BaseStream
    public IntStream unordered() {
        if (!isOrdered()) {
            return this;
        }
        return new StatelessOp<Integer>(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_ORDERED) {
            /* class java.util.stream.IntPipeline.AnonymousClass8 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Integer> opWrapSink(int flags, Sink<Integer> sink) {
                return sink;
            }
        };
    }

    @Override // java.util.stream.IntStream
    public final IntStream filter(final IntPredicate predicate) {
        Objects.requireNonNull(predicate);
        return new StatelessOp<Integer>(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_SIZED) {
            /* class java.util.stream.IntPipeline.AnonymousClass9 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Integer> opWrapSink(int flags, Sink<Integer> sink) {
                return new Sink.ChainedInt<Integer>(sink) {
                    /* class java.util.stream.IntPipeline.AnonymousClass9.AnonymousClass1 */

                    @Override // java.util.stream.Sink.ChainedInt, java.util.stream.Sink
                    public void begin(long size) {
                        this.downstream.begin(-1);
                    }

                    @Override // java.util.stream.Sink.OfInt, java.util.function.IntConsumer, java.util.stream.Sink
                    public void accept(int t) {
                        if (predicate.test(t)) {
                            this.downstream.accept(t);
                        }
                    }
                };
            }
        };
    }

    @Override // java.util.stream.IntStream
    public final IntStream peek(final IntConsumer action) {
        Objects.requireNonNull(action);
        return new StatelessOp<Integer>(this, StreamShape.INT_VALUE, 0) {
            /* class java.util.stream.IntPipeline.AnonymousClass10 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Integer> opWrapSink(int flags, Sink<Integer> sink) {
                return new Sink.ChainedInt<Integer>(sink) {
                    /* class java.util.stream.IntPipeline.AnonymousClass10.AnonymousClass1 */

                    @Override // java.util.stream.Sink.OfInt, java.util.function.IntConsumer, java.util.stream.Sink
                    public void accept(int t) {
                        action.accept(t);
                        this.downstream.accept(t);
                    }
                };
            }
        };
    }

    @Override // java.util.stream.IntStream
    public final IntStream limit(long maxSize) {
        if (maxSize >= 0) {
            return SliceOps.makeInt(this, 0, maxSize);
        }
        throw new IllegalArgumentException(Long.toString(maxSize));
    }

    @Override // java.util.stream.IntStream
    public final IntStream skip(long n) {
        if (n < 0) {
            throw new IllegalArgumentException(Long.toString(n));
        } else if (n == 0) {
            return this;
        } else {
            return SliceOps.makeInt(this, n, -1);
        }
    }

    @Override // java.util.stream.IntStream
    public final IntStream sorted() {
        return SortedOps.makeInt(this);
    }

    @Override // java.util.stream.IntStream
    public final IntStream distinct() {
        return boxed().distinct().mapToInt($$Lambda$IntPipeline$RE7oGjPWog3HR9X8MdhU1ZGRE.INSTANCE);
    }

    @Override // java.util.stream.IntStream
    public void forEach(IntConsumer action) {
        evaluate((TerminalOp<E_OUT, R>) ForEachOps.makeInt(action, false));
    }

    @Override // java.util.stream.IntStream
    public void forEachOrdered(IntConsumer action) {
        evaluate((TerminalOp<E_OUT, R>) ForEachOps.makeInt(action, true));
    }

    @Override // java.util.stream.IntStream
    public final int sum() {
        return reduce(0, $$Lambda$ono9Bp0lMrKbIRfAAYdycY0_qag.INSTANCE);
    }

    @Override // java.util.stream.IntStream
    public final OptionalInt min() {
        return reduce($$Lambda$FZ2W1z3RReutoY2tFnI_NsF0lTk.INSTANCE);
    }

    @Override // java.util.stream.IntStream
    public final OptionalInt max() {
        return reduce($$Lambda$HJTpjoyUrBGPZyR69XwKllqU1YY.INSTANCE);
    }

    static /* synthetic */ long lambda$count$1(int e) {
        return 1;
    }

    @Override // java.util.stream.IntStream
    public final long count() {
        return mapToLong($$Lambda$IntPipeline$Q_Wb7uDnZZMCasMbsGNAwSlprMo.INSTANCE).sum();
    }

    static /* synthetic */ long[] lambda$average$2() {
        return new long[2];
    }

    @Override // java.util.stream.IntStream
    public final OptionalDouble average() {
        long[] avg = (long[]) collect($$Lambda$IntPipeline$MrivqBp4YhHB_ix11jxmkPQ1lbE.INSTANCE, $$Lambda$IntPipeline$0s_rkIyKzlnj_MbqfCTpum_W2c.INSTANCE, $$Lambda$IntPipeline$hMFCZ84F0UujzJhdWtPfESTkN2A.INSTANCE);
        if (avg[0] > 0) {
            return OptionalDouble.of(((double) avg[1]) / ((double) avg[0]));
        }
        return OptionalDouble.empty();
    }

    static /* synthetic */ void lambda$average$3(long[] ll, int i) {
        ll[0] = ll[0] + 1;
        ll[1] = ll[1] + ((long) i);
    }

    static /* synthetic */ void lambda$average$4(long[] ll, long[] rr) {
        ll[0] = ll[0] + rr[0];
        ll[1] = ll[1] + rr[1];
    }

    @Override // java.util.stream.IntStream
    public final IntSummaryStatistics summaryStatistics() {
        return (IntSummaryStatistics) collect($$Lambda$_Ea_sNpqZAwihIOCRBaP7hHgWWI.INSTANCE, $$Lambda$UowTf7vzuMsu4sv1eMs5iEeNh0.INSTANCE, $$Lambda$YcgMAuDDScc4HC6CSMDq1R0qa40.INSTANCE);
    }

    @Override // java.util.stream.IntStream
    public final int reduce(int identity, IntBinaryOperator op) {
        return ((Integer) evaluate((TerminalOp<E_OUT, R>) ReduceOps.makeInt(identity, op))).intValue();
    }

    @Override // java.util.stream.IntStream
    public final OptionalInt reduce(IntBinaryOperator op) {
        return (OptionalInt) evaluate((TerminalOp<E_OUT, R>) ReduceOps.makeInt(op));
    }

    @Override // java.util.stream.IntStream
    public final <R> R collect(Supplier<R> supplier, ObjIntConsumer<R> accumulator, BiConsumer<R, R> combiner) {
        return (R) evaluate(ReduceOps.makeInt(supplier, accumulator, new BinaryOperator() {
            /* class java.util.stream.$$Lambda$IntPipeline$gTDhYg7hsRI2br4NmAxtQnW5i6Y */

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return BiConsumer.this.accept(obj, obj2);
            }
        }));
    }

    @Override // java.util.stream.IntStream
    public final boolean anyMatch(IntPredicate predicate) {
        return ((Boolean) evaluate((TerminalOp<E_OUT, R>) MatchOps.makeInt(predicate, MatchOps.MatchKind.ANY))).booleanValue();
    }

    @Override // java.util.stream.IntStream
    public final boolean allMatch(IntPredicate predicate) {
        return ((Boolean) evaluate((TerminalOp<E_OUT, R>) MatchOps.makeInt(predicate, MatchOps.MatchKind.ALL))).booleanValue();
    }

    @Override // java.util.stream.IntStream
    public final boolean noneMatch(IntPredicate predicate) {
        return ((Boolean) evaluate((TerminalOp<E_OUT, R>) MatchOps.makeInt(predicate, MatchOps.MatchKind.NONE))).booleanValue();
    }

    @Override // java.util.stream.IntStream
    public final OptionalInt findFirst() {
        return (OptionalInt) evaluate((TerminalOp<E_OUT, R>) FindOps.makeInt(true));
    }

    @Override // java.util.stream.IntStream
    public final OptionalInt findAny() {
        return (OptionalInt) evaluate((TerminalOp<E_OUT, R>) FindOps.makeInt(false));
    }

    static /* synthetic */ Integer[] lambda$toArray$6(int x$0) {
        return new Integer[x$0];
    }

    @Override // java.util.stream.IntStream
    public final int[] toArray() {
        return (int[]) Nodes.flattenInt((Node.OfInt) evaluateToArrayNode($$Lambda$IntPipeline$ozedusDMANE_B8aDthWCd1Lna4.INSTANCE)).asPrimitiveArray();
    }

    public static class Head<E_IN> extends IntPipeline<E_IN> {
        @Override // java.util.stream.IntStream, java.util.stream.IntStream, java.util.stream.IntPipeline, java.util.stream.AbstractPipeline, java.util.stream.BaseStream
        public /* bridge */ /* synthetic */ IntStream parallel() {
            return (IntStream) IntPipeline.super.parallel();
        }

        @Override // java.util.stream.IntStream, java.util.stream.IntStream, java.util.stream.IntPipeline, java.util.stream.AbstractPipeline, java.util.stream.BaseStream
        public /* bridge */ /* synthetic */ IntStream sequential() {
            return (IntStream) IntPipeline.super.sequential();
        }

        public Head(Supplier<? extends Spliterator<Integer>> source, int sourceFlags, boolean parallel) {
            super(source, sourceFlags, parallel);
        }

        public Head(Spliterator<Integer> source, int sourceFlags, boolean parallel) {
            super(source, sourceFlags, parallel);
        }

        @Override // java.util.stream.AbstractPipeline
        public final boolean opIsStateful() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.stream.AbstractPipeline
        public final Sink<E_IN> opWrapSink(int flags, Sink<Integer> sink) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.stream.IntStream, java.util.stream.IntPipeline
        public void forEach(IntConsumer action) {
            if (!isParallel()) {
                IntPipeline.adapt(sourceStageSpliterator()).forEachRemaining(action);
            } else {
                IntPipeline.super.forEach(action);
            }
        }

        @Override // java.util.stream.IntStream, java.util.stream.IntPipeline
        public void forEachOrdered(IntConsumer action) {
            if (!isParallel()) {
                IntPipeline.adapt(sourceStageSpliterator()).forEachRemaining(action);
            } else {
                IntPipeline.super.forEachOrdered(action);
            }
        }
    }

    public static abstract class StatelessOp<E_IN> extends IntPipeline<E_IN> {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        @Override // java.util.stream.IntStream, java.util.stream.IntStream, java.util.stream.IntPipeline, java.util.stream.AbstractPipeline, java.util.stream.BaseStream
        public /* bridge */ /* synthetic */ IntStream parallel() {
            return (IntStream) IntPipeline.super.parallel();
        }

        @Override // java.util.stream.IntStream, java.util.stream.IntStream, java.util.stream.IntPipeline, java.util.stream.AbstractPipeline, java.util.stream.BaseStream
        public /* bridge */ /* synthetic */ IntStream sequential() {
            return (IntStream) IntPipeline.super.sequential();
        }

        public StatelessOp(AbstractPipeline<?, E_IN, ?> upstream, StreamShape inputShape, int opFlags) {
            super(upstream, opFlags);
        }

        @Override // java.util.stream.AbstractPipeline
        public final boolean opIsStateful() {
            return false;
        }
    }

    public static abstract class StatefulOp<E_IN> extends IntPipeline<E_IN> {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        @Override // java.util.stream.AbstractPipeline
        public abstract <P_IN> Node<Integer> opEvaluateParallel(PipelineHelper<Integer> pipelineHelper, Spliterator<P_IN> spliterator, IntFunction<Integer[]> intFunction);

        @Override // java.util.stream.IntStream, java.util.stream.IntStream, java.util.stream.IntPipeline, java.util.stream.AbstractPipeline, java.util.stream.BaseStream
        public /* bridge */ /* synthetic */ IntStream parallel() {
            return (IntStream) IntPipeline.super.parallel();
        }

        @Override // java.util.stream.IntStream, java.util.stream.IntStream, java.util.stream.IntPipeline, java.util.stream.AbstractPipeline, java.util.stream.BaseStream
        public /* bridge */ /* synthetic */ IntStream sequential() {
            return (IntStream) IntPipeline.super.sequential();
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
