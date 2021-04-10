package java.util.stream;

import java.util.DoubleSummaryStatistics;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntFunction;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;
import java.util.stream.IntPipeline;
import java.util.stream.LongPipeline;
import java.util.stream.MatchOps;
import java.util.stream.Node;
import java.util.stream.ReferencePipeline;
import java.util.stream.Sink;
import java.util.stream.StreamSpliterators;

public abstract class DoublePipeline<E_IN> extends AbstractPipeline<E_IN, Double, DoubleStream> implements DoubleStream {
    @Override // java.util.stream.AbstractPipeline, java.util.stream.BaseStream, java.util.stream.DoubleStream, java.util.stream.DoubleStream
    public /* bridge */ /* synthetic */ DoubleStream parallel() {
        return (DoubleStream) super.parallel();
    }

    @Override // java.util.stream.AbstractPipeline, java.util.stream.BaseStream, java.util.stream.DoubleStream, java.util.stream.DoubleStream
    public /* bridge */ /* synthetic */ DoubleStream sequential() {
        return (DoubleStream) super.sequential();
    }

    DoublePipeline(Supplier<? extends Spliterator<Double>> source, int sourceFlags, boolean parallel) {
        super(source, sourceFlags, parallel);
    }

    DoublePipeline(Spliterator<Double> source, int sourceFlags, boolean parallel) {
        super(source, sourceFlags, parallel);
    }

    DoublePipeline(AbstractPipeline<?, E_IN, ?> upstream, int opFlags) {
        super(upstream, opFlags);
    }

    private static DoubleConsumer adapt(Sink<Double> sink) {
        if (sink instanceof DoubleConsumer) {
            return (DoubleConsumer) sink;
        }
        if (Tripwire.ENABLED) {
            Tripwire.trip(AbstractPipeline.class, "using DoubleStream.adapt(Sink<Double> s)");
        }
        Objects.requireNonNull(sink);
        return new DoubleConsumer() {
            /* class java.util.stream.$$Lambda$G0LLxk8pWitjFgsOx2bYtROrGg */

            @Override // java.util.function.DoubleConsumer
            public final void accept(double d) {
                Sink.this.accept(d);
            }
        };
    }

    /* access modifiers changed from: private */
    public static Spliterator.OfDouble adapt(Spliterator<Double> s) {
        if (s instanceof Spliterator.OfDouble) {
            return (Spliterator.OfDouble) s;
        }
        if (Tripwire.ENABLED) {
            Tripwire.trip(AbstractPipeline.class, "using DoubleStream.adapt(Spliterator<Double> s)");
        }
        throw new UnsupportedOperationException("DoubleStream.adapt(Spliterator<Double> s)");
    }

    @Override // java.util.stream.AbstractPipeline
    public final StreamShape getOutputShape() {
        return StreamShape.DOUBLE_VALUE;
    }

    @Override // java.util.stream.AbstractPipeline
    public final <P_IN> Node<Double> evaluateToNode(PipelineHelper<Double> helper, Spliterator<P_IN> spliterator, boolean flattenTree, IntFunction<Double[]> intFunction) {
        return Nodes.collectDouble(helper, spliterator, flattenTree);
    }

    @Override // java.util.stream.AbstractPipeline
    public final <P_IN> Spliterator<Double> wrap(PipelineHelper<Double> ph, Supplier<Spliterator<P_IN>> supplier, boolean isParallel) {
        return new StreamSpliterators.DoubleWrappingSpliterator(ph, supplier, isParallel);
    }

    /* Return type fixed from 'java.util.Spliterator$OfDouble' to match base method */
    @Override // java.util.stream.AbstractPipeline
    public final Spliterator<Double> lazySpliterator(Supplier<? extends Spliterator<Double>> supplier) {
        return new StreamSpliterators.DelegatingSpliterator.OfDouble(supplier);
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000e  */
    @Override // java.util.stream.AbstractPipeline
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void forEachWithCancel(java.util.Spliterator<java.lang.Double> r4, java.util.stream.Sink<java.lang.Double> r5) {
        /*
            r3 = this;
            java.util.Spliterator$OfDouble r0 = adapt(r4)
            java.util.function.DoubleConsumer r1 = adapt(r5)
        L_0x0008:
            boolean r2 = r5.cancellationRequested()
            if (r2 != 0) goto L_0x0014
            boolean r2 = r0.tryAdvance(r1)
            if (r2 != 0) goto L_0x0008
        L_0x0014:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.stream.DoublePipeline.forEachWithCancel(java.util.Spliterator, java.util.stream.Sink):void");
    }

    @Override // java.util.stream.AbstractPipeline, java.util.stream.PipelineHelper
    public final Node.Builder<Double> makeNodeBuilder(long exactSizeIfKnown, IntFunction<Double[]> intFunction) {
        return Nodes.doubleBuilder(exactSizeIfKnown);
    }

    /* Return type fixed from 'java.util.PrimitiveIterator$OfDouble' to match base method */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Spliterator$OfDouble] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // java.util.stream.BaseStream, java.util.stream.DoubleStream, java.util.stream.DoubleStream
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Iterator<java.lang.Double> iterator() {
        /*
            r1 = this;
            java.util.Spliterator$OfDouble r0 = r1.spliterator()
            java.util.PrimitiveIterator$OfDouble r0 = java.util.Spliterators.iterator(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.stream.DoublePipeline.iterator():java.util.PrimitiveIterator$OfDouble");
    }

    /* Return type fixed from 'java.util.Spliterator$OfDouble' to match base method */
    @Override // java.util.stream.AbstractPipeline, java.util.stream.BaseStream, java.util.stream.DoubleStream, java.util.stream.DoubleStream
    public final Spliterator<Double> spliterator() {
        return adapt(super.spliterator());
    }

    @Override // java.util.stream.DoubleStream
    public final Stream<Double> boxed() {
        return mapToObj($$Lambda$0HimmAYr5h1pFdNckEhxJ9y9Zqk.INSTANCE);
    }

    @Override // java.util.stream.DoubleStream
    public final DoubleStream map(final DoubleUnaryOperator mapper) {
        Objects.requireNonNull(mapper);
        return new StatelessOp<Double>(this, StreamShape.DOUBLE_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            /* class java.util.stream.DoublePipeline.AnonymousClass1 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Double> opWrapSink(int flags, Sink<Double> sink) {
                return new Sink.ChainedDouble<Double>(sink) {
                    /* class java.util.stream.DoublePipeline.AnonymousClass1.AnonymousClass1 */

                    @Override // java.util.stream.Sink.OfDouble, java.util.function.DoubleConsumer, java.util.stream.Sink
                    public void accept(double t) {
                        this.downstream.accept(mapper.applyAsDouble(t));
                    }
                };
            }
        };
    }

    @Override // java.util.stream.DoubleStream
    public final <U> Stream<U> mapToObj(final DoubleFunction<? extends U> mapper) {
        Objects.requireNonNull(mapper);
        return new ReferencePipeline.StatelessOp<Double, U>(this, StreamShape.DOUBLE_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            /* class java.util.stream.DoublePipeline.AnonymousClass2 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Double> opWrapSink(int flags, Sink<U> sink) {
                return new Sink.ChainedDouble<U>(sink) {
                    /* class java.util.stream.DoublePipeline.AnonymousClass2.AnonymousClass1 */

                    @Override // java.util.stream.Sink.OfDouble, java.util.function.DoubleConsumer, java.util.stream.Sink
                    public void accept(double t) {
                        this.downstream.accept(mapper.apply(t));
                    }
                };
            }
        };
    }

    @Override // java.util.stream.DoubleStream
    public final IntStream mapToInt(final DoubleToIntFunction mapper) {
        Objects.requireNonNull(mapper);
        return new IntPipeline.StatelessOp<Double>(this, StreamShape.DOUBLE_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            /* class java.util.stream.DoublePipeline.AnonymousClass3 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Double> opWrapSink(int flags, Sink<Integer> sink) {
                return new Sink.ChainedDouble<Integer>(sink) {
                    /* class java.util.stream.DoublePipeline.AnonymousClass3.AnonymousClass1 */

                    @Override // java.util.stream.Sink.OfDouble, java.util.function.DoubleConsumer, java.util.stream.Sink
                    public void accept(double t) {
                        this.downstream.accept(mapper.applyAsInt(t));
                    }
                };
            }
        };
    }

    @Override // java.util.stream.DoubleStream
    public final LongStream mapToLong(final DoubleToLongFunction mapper) {
        Objects.requireNonNull(mapper);
        return new LongPipeline.StatelessOp<Double>(this, StreamShape.DOUBLE_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            /* class java.util.stream.DoublePipeline.AnonymousClass4 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Double> opWrapSink(int flags, Sink<Long> sink) {
                return new Sink.ChainedDouble<Long>(sink) {
                    /* class java.util.stream.DoublePipeline.AnonymousClass4.AnonymousClass1 */

                    @Override // java.util.stream.Sink.OfDouble, java.util.function.DoubleConsumer, java.util.stream.Sink
                    public void accept(double t) {
                        this.downstream.accept(mapper.applyAsLong(t));
                    }
                };
            }
        };
    }

    @Override // java.util.stream.DoubleStream
    public final DoubleStream flatMap(final DoubleFunction<? extends DoubleStream> mapper) {
        return new StatelessOp<Double>(this, StreamShape.DOUBLE_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) {
            /* class java.util.stream.DoublePipeline.AnonymousClass5 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Double> opWrapSink(int flags, Sink<Double> sink) {
                return new Sink.ChainedDouble<Double>(sink) {
                    /* class java.util.stream.DoublePipeline.AnonymousClass5.AnonymousClass1 */

                    @Override // java.util.stream.Sink.ChainedDouble, java.util.stream.Sink
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
                    @Override // java.util.stream.Sink.OfDouble, java.util.function.DoubleConsumer, java.util.stream.Sink
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void accept(double r5) {
                        /*
                            r4 = this;
                            java.util.stream.DoublePipeline$5 r0 = java.util.stream.DoublePipeline.AnonymousClass5.this
                            java.util.function.DoubleFunction r0 = r8
                            java.lang.Object r0 = r0.apply(r5)
                            java.util.stream.DoubleStream r0 = (java.util.stream.DoubleStream) r0
                            if (r0 == 0) goto L_0x0025
                            java.util.stream.DoubleStream r1 = r0.sequential()     // Catch:{ all -> 0x0019 }
                            java.util.stream.-$$Lambda$DoublePipeline$5$1$kqJiVK7sQB3kKvPk9DB9gInHJq4 r2 = new java.util.stream.-$$Lambda$DoublePipeline$5$1$kqJiVK7sQB3kKvPk9DB9gInHJq4     // Catch:{ all -> 0x0019 }
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
                        throw new UnsupportedOperationException("Method not decompiled: java.util.stream.DoublePipeline.AnonymousClass5.AnonymousClass1.accept(double):void");
                    }

                    public /* synthetic */ void lambda$accept$0$DoublePipeline$5$1(double i) {
                        this.downstream.accept(i);
                    }
                };
            }
        };
    }

    @Override // java.util.stream.BaseStream
    public DoubleStream unordered() {
        if (!isOrdered()) {
            return this;
        }
        return new StatelessOp<Double>(this, StreamShape.DOUBLE_VALUE, StreamOpFlag.NOT_ORDERED) {
            /* class java.util.stream.DoublePipeline.AnonymousClass6 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Double> opWrapSink(int flags, Sink<Double> sink) {
                return sink;
            }
        };
    }

    @Override // java.util.stream.DoubleStream
    public final DoubleStream filter(final DoublePredicate predicate) {
        Objects.requireNonNull(predicate);
        return new StatelessOp<Double>(this, StreamShape.DOUBLE_VALUE, StreamOpFlag.NOT_SIZED) {
            /* class java.util.stream.DoublePipeline.AnonymousClass7 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Double> opWrapSink(int flags, Sink<Double> sink) {
                return new Sink.ChainedDouble<Double>(sink) {
                    /* class java.util.stream.DoublePipeline.AnonymousClass7.AnonymousClass1 */

                    @Override // java.util.stream.Sink.ChainedDouble, java.util.stream.Sink
                    public void begin(long size) {
                        this.downstream.begin(-1);
                    }

                    @Override // java.util.stream.Sink.OfDouble, java.util.function.DoubleConsumer, java.util.stream.Sink
                    public void accept(double t) {
                        if (predicate.test(t)) {
                            this.downstream.accept(t);
                        }
                    }
                };
            }
        };
    }

    @Override // java.util.stream.DoubleStream
    public final DoubleStream peek(final DoubleConsumer action) {
        Objects.requireNonNull(action);
        return new StatelessOp<Double>(this, StreamShape.DOUBLE_VALUE, 0) {
            /* class java.util.stream.DoublePipeline.AnonymousClass8 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<Double> opWrapSink(int flags, Sink<Double> sink) {
                return new Sink.ChainedDouble<Double>(sink) {
                    /* class java.util.stream.DoublePipeline.AnonymousClass8.AnonymousClass1 */

                    @Override // java.util.stream.Sink.OfDouble, java.util.function.DoubleConsumer, java.util.stream.Sink
                    public void accept(double t) {
                        action.accept(t);
                        this.downstream.accept(t);
                    }
                };
            }
        };
    }

    @Override // java.util.stream.DoubleStream
    public final DoubleStream limit(long maxSize) {
        if (maxSize >= 0) {
            return SliceOps.makeDouble(this, 0, maxSize);
        }
        throw new IllegalArgumentException(Long.toString(maxSize));
    }

    @Override // java.util.stream.DoubleStream
    public final DoubleStream skip(long n) {
        if (n < 0) {
            throw new IllegalArgumentException(Long.toString(n));
        } else if (n == 0) {
            return this;
        } else {
            return SliceOps.makeDouble(this, n, -1);
        }
    }

    @Override // java.util.stream.DoubleStream
    public final DoubleStream sorted() {
        return SortedOps.makeDouble(this);
    }

    @Override // java.util.stream.DoubleStream
    public final DoubleStream distinct() {
        return boxed().distinct().mapToDouble($$Lambda$DoublePipeline$gq0fD9NZ938fl5Zgm1Lwm9G2tpI.INSTANCE);
    }

    @Override // java.util.stream.DoubleStream
    public void forEach(DoubleConsumer consumer) {
        evaluate((TerminalOp<E_OUT, R>) ForEachOps.makeDouble(consumer, false));
    }

    @Override // java.util.stream.DoubleStream
    public void forEachOrdered(DoubleConsumer consumer) {
        evaluate((TerminalOp<E_OUT, R>) ForEachOps.makeDouble(consumer, true));
    }

    static /* synthetic */ double[] lambda$sum$1() {
        return new double[3];
    }

    @Override // java.util.stream.DoubleStream
    public final double sum() {
        return Collectors.computeFinalSum((double[]) collect($$Lambda$DoublePipeline$jsM76ecD5K_oP4TaArM1RdmdjOw.INSTANCE, $$Lambda$DoublePipeline$btJQIF5a5bk658mbj9AIl0UV19Q.INSTANCE, $$Lambda$DoublePipeline$KYIKJiRuFnKlAv02sN6Y0G5US7E.INSTANCE));
    }

    static /* synthetic */ void lambda$sum$2(double[] ll, double d) {
        Collectors.sumWithCompensation(ll, d);
        ll[2] = ll[2] + d;
    }

    static /* synthetic */ void lambda$sum$3(double[] ll, double[] rr) {
        Collectors.sumWithCompensation(ll, rr[0]);
        Collectors.sumWithCompensation(ll, rr[1]);
        ll[2] = ll[2] + rr[2];
    }

    @Override // java.util.stream.DoubleStream
    public final OptionalDouble min() {
        return reduce($$Lambda$Xsl4nKeYydTETtdRjTtEXmjJItE.INSTANCE);
    }

    @Override // java.util.stream.DoubleStream
    public final OptionalDouble max() {
        return reduce($$Lambda$xi7ZBZfKmkbt5CSsaL8qlNeHupc.INSTANCE);
    }

    static /* synthetic */ double[] lambda$average$4() {
        return new double[4];
    }

    @Override // java.util.stream.DoubleStream
    public final OptionalDouble average() {
        double[] avg = (double[]) collect($$Lambda$DoublePipeline$O7F4ENrC3oYj9E0vblCKW9Dec60.INSTANCE, $$Lambda$DoublePipeline$lWQTyY6EPN0Xvhyjp5Lr5ZKBDCA.INSTANCE, $$Lambda$DoublePipeline$8lpXAdS4oGMq6Yo_dNhNdoPgg0.INSTANCE);
        if (avg[2] > 0.0d) {
            return OptionalDouble.of(Collectors.computeFinalSum(avg) / avg[2]);
        }
        return OptionalDouble.empty();
    }

    static /* synthetic */ void lambda$average$5(double[] ll, double d) {
        ll[2] = ll[2] + 1.0d;
        Collectors.sumWithCompensation(ll, d);
        ll[3] = ll[3] + d;
    }

    static /* synthetic */ void lambda$average$6(double[] ll, double[] rr) {
        Collectors.sumWithCompensation(ll, rr[0]);
        Collectors.sumWithCompensation(ll, rr[1]);
        ll[2] = ll[2] + rr[2];
        ll[3] = ll[3] + rr[3];
    }

    static /* synthetic */ long lambda$count$7(double e) {
        return 1;
    }

    @Override // java.util.stream.DoubleStream
    public final long count() {
        return mapToLong($$Lambda$DoublePipeline$V2mM4_kocaa0EZ7g04Qc6_Yd13E.INSTANCE).sum();
    }

    @Override // java.util.stream.DoubleStream
    public final DoubleSummaryStatistics summaryStatistics() {
        return (DoubleSummaryStatistics) collect($$Lambda$745FUy7cYwYu7KrMQTYh2DNqh1I.INSTANCE, $$Lambda$9clh6DyAY2rGfAxuH1sO9aEBuU.INSTANCE, $$Lambda$BZcmU4lh1MU8ke57orLk6ELdvT4.INSTANCE);
    }

    @Override // java.util.stream.DoubleStream
    public final double reduce(double identity, DoubleBinaryOperator op) {
        return ((Double) evaluate((TerminalOp<E_OUT, R>) ReduceOps.makeDouble(identity, op))).doubleValue();
    }

    @Override // java.util.stream.DoubleStream
    public final OptionalDouble reduce(DoubleBinaryOperator op) {
        return (OptionalDouble) evaluate((TerminalOp<E_OUT, R>) ReduceOps.makeDouble(op));
    }

    @Override // java.util.stream.DoubleStream
    public final <R> R collect(Supplier<R> supplier, ObjDoubleConsumer<R> accumulator, BiConsumer<R, R> combiner) {
        return (R) evaluate(ReduceOps.makeDouble(supplier, accumulator, new BinaryOperator() {
            /* class java.util.stream.$$Lambda$DoublePipeline$IBZGhEgRy1ddKsqLtAJJIbQPE8 */

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return BiConsumer.this.accept(obj, obj2);
            }
        }));
    }

    @Override // java.util.stream.DoubleStream
    public final boolean anyMatch(DoublePredicate predicate) {
        return ((Boolean) evaluate((TerminalOp<E_OUT, R>) MatchOps.makeDouble(predicate, MatchOps.MatchKind.ANY))).booleanValue();
    }

    @Override // java.util.stream.DoubleStream
    public final boolean allMatch(DoublePredicate predicate) {
        return ((Boolean) evaluate((TerminalOp<E_OUT, R>) MatchOps.makeDouble(predicate, MatchOps.MatchKind.ALL))).booleanValue();
    }

    @Override // java.util.stream.DoubleStream
    public final boolean noneMatch(DoublePredicate predicate) {
        return ((Boolean) evaluate((TerminalOp<E_OUT, R>) MatchOps.makeDouble(predicate, MatchOps.MatchKind.NONE))).booleanValue();
    }

    @Override // java.util.stream.DoubleStream
    public final OptionalDouble findFirst() {
        return (OptionalDouble) evaluate((TerminalOp<E_OUT, R>) FindOps.makeDouble(true));
    }

    @Override // java.util.stream.DoubleStream
    public final OptionalDouble findAny() {
        return (OptionalDouble) evaluate((TerminalOp<E_OUT, R>) FindOps.makeDouble(false));
    }

    static /* synthetic */ Double[] lambda$toArray$9(int x$0) {
        return new Double[x$0];
    }

    @Override // java.util.stream.DoubleStream
    public final double[] toArray() {
        return (double[]) Nodes.flattenDouble((Node.OfDouble) evaluateToArrayNode($$Lambda$DoublePipeline$VwL6T93St4bY9lzEXgl24N_DcA4.INSTANCE)).asPrimitiveArray();
    }

    public static class Head<E_IN> extends DoublePipeline<E_IN> {
        @Override // java.util.stream.DoublePipeline, java.util.stream.AbstractPipeline, java.util.stream.BaseStream, java.util.stream.DoubleStream, java.util.stream.DoubleStream
        public /* bridge */ /* synthetic */ DoubleStream parallel() {
            return (DoubleStream) DoublePipeline.super.parallel();
        }

        @Override // java.util.stream.DoublePipeline, java.util.stream.AbstractPipeline, java.util.stream.BaseStream, java.util.stream.DoubleStream, java.util.stream.DoubleStream
        public /* bridge */ /* synthetic */ DoubleStream sequential() {
            return (DoubleStream) DoublePipeline.super.sequential();
        }

        public Head(Supplier<? extends Spliterator<Double>> source, int sourceFlags, boolean parallel) {
            super(source, sourceFlags, parallel);
        }

        public Head(Spliterator<Double> source, int sourceFlags, boolean parallel) {
            super(source, sourceFlags, parallel);
        }

        @Override // java.util.stream.AbstractPipeline
        public final boolean opIsStateful() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.stream.AbstractPipeline
        public final Sink<E_IN> opWrapSink(int flags, Sink<Double> sink) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.stream.DoublePipeline, java.util.stream.DoubleStream
        public void forEach(DoubleConsumer consumer) {
            if (!isParallel()) {
                DoublePipeline.adapt(sourceStageSpliterator()).forEachRemaining(consumer);
            } else {
                DoublePipeline.super.forEach(consumer);
            }
        }

        @Override // java.util.stream.DoublePipeline, java.util.stream.DoubleStream
        public void forEachOrdered(DoubleConsumer consumer) {
            if (!isParallel()) {
                DoublePipeline.adapt(sourceStageSpliterator()).forEachRemaining(consumer);
            } else {
                DoublePipeline.super.forEachOrdered(consumer);
            }
        }
    }

    public static abstract class StatelessOp<E_IN> extends DoublePipeline<E_IN> {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        @Override // java.util.stream.DoublePipeline, java.util.stream.AbstractPipeline, java.util.stream.BaseStream, java.util.stream.DoubleStream, java.util.stream.DoubleStream
        public /* bridge */ /* synthetic */ DoubleStream parallel() {
            return (DoubleStream) DoublePipeline.super.parallel();
        }

        @Override // java.util.stream.DoublePipeline, java.util.stream.AbstractPipeline, java.util.stream.BaseStream, java.util.stream.DoubleStream, java.util.stream.DoubleStream
        public /* bridge */ /* synthetic */ DoubleStream sequential() {
            return (DoubleStream) DoublePipeline.super.sequential();
        }

        public StatelessOp(AbstractPipeline<?, E_IN, ?> upstream, StreamShape inputShape, int opFlags) {
            super(upstream, opFlags);
        }

        @Override // java.util.stream.AbstractPipeline
        public final boolean opIsStateful() {
            return false;
        }
    }

    public static abstract class StatefulOp<E_IN> extends DoublePipeline<E_IN> {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        @Override // java.util.stream.AbstractPipeline
        public abstract <P_IN> Node<Double> opEvaluateParallel(PipelineHelper<Double> pipelineHelper, Spliterator<P_IN> spliterator, IntFunction<Double[]> intFunction);

        @Override // java.util.stream.DoublePipeline, java.util.stream.AbstractPipeline, java.util.stream.BaseStream, java.util.stream.DoubleStream, java.util.stream.DoubleStream
        public /* bridge */ /* synthetic */ DoubleStream parallel() {
            return (DoubleStream) DoublePipeline.super.parallel();
        }

        @Override // java.util.stream.DoublePipeline, java.util.stream.AbstractPipeline, java.util.stream.BaseStream, java.util.stream.DoubleStream, java.util.stream.DoubleStream
        public /* bridge */ /* synthetic */ DoubleStream sequential() {
            return (DoubleStream) DoublePipeline.super.sequential();
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
