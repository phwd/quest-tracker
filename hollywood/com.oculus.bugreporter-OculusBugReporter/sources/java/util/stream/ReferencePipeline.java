package java.util.stream;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collector;
import java.util.stream.DoublePipeline;
import java.util.stream.IntPipeline;
import java.util.stream.LongPipeline;
import java.util.stream.MatchOps;
import java.util.stream.Node;
import java.util.stream.Sink;
import java.util.stream.StreamSpliterators;

public abstract class ReferencePipeline<P_IN, P_OUT> extends AbstractPipeline<P_IN, P_OUT, Stream<P_OUT>> implements Stream<P_OUT> {
    ReferencePipeline(Supplier<? extends Spliterator<?>> source, int sourceFlags, boolean parallel) {
        super(source, sourceFlags, parallel);
    }

    ReferencePipeline(Spliterator<?> source, int sourceFlags, boolean parallel) {
        super(source, sourceFlags, parallel);
    }

    ReferencePipeline(AbstractPipeline<?, P_IN, ?> upstream, int opFlags) {
        super(upstream, opFlags);
    }

    @Override // java.util.stream.AbstractPipeline
    public final StreamShape getOutputShape() {
        return StreamShape.REFERENCE;
    }

    @Override // java.util.stream.AbstractPipeline
    public final <P_IN> Node<P_OUT> evaluateToNode(PipelineHelper<P_OUT> helper, Spliterator<P_IN> spliterator, boolean flattenTree, IntFunction<P_OUT[]> generator) {
        return Nodes.collect(helper, spliterator, flattenTree, generator);
    }

    @Override // java.util.stream.AbstractPipeline
    public final <P_IN> Spliterator<P_OUT> wrap(PipelineHelper<P_OUT> ph, Supplier<Spliterator<P_IN>> supplier, boolean isParallel) {
        return new StreamSpliterators.WrappingSpliterator(ph, supplier, isParallel);
    }

    @Override // java.util.stream.AbstractPipeline
    public final Spliterator<P_OUT> lazySpliterator(Supplier<? extends Spliterator<P_OUT>> supplier) {
        return new StreamSpliterators.DelegatingSpliterator(supplier);
    }

    /* JADX WARNING: Removed duplicated region for block: B:2:0x0006  */
    @Override // java.util.stream.AbstractPipeline
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void forEachWithCancel(java.util.Spliterator<P_OUT> r2, java.util.stream.Sink<P_OUT> r3) {
        /*
            r1 = this;
        L_0x0000:
            boolean r0 = r3.cancellationRequested()
            if (r0 != 0) goto L_0x000c
            boolean r0 = r2.tryAdvance(r3)
            if (r0 != 0) goto L_0x0000
        L_0x000c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.stream.ReferencePipeline.forEachWithCancel(java.util.Spliterator, java.util.stream.Sink):void");
    }

    @Override // java.util.stream.AbstractPipeline, java.util.stream.PipelineHelper
    public final Node.Builder<P_OUT> makeNodeBuilder(long exactSizeIfKnown, IntFunction<P_OUT[]> generator) {
        return Nodes.builder(exactSizeIfKnown, generator);
    }

    @Override // java.util.stream.BaseStream
    public final Iterator<P_OUT> iterator() {
        return Spliterators.iterator(spliterator());
    }

    @Override // java.util.stream.BaseStream
    public Stream<P_OUT> unordered() {
        if (!isOrdered()) {
            return this;
        }
        return new StatelessOp<P_OUT, P_OUT>(this, StreamShape.REFERENCE, StreamOpFlag.NOT_ORDERED) {
            /* class java.util.stream.ReferencePipeline.AnonymousClass1 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<P_OUT> opWrapSink(int flags, Sink<P_OUT> sink) {
                return sink;
            }
        };
    }

    @Override // java.util.stream.Stream
    public final Stream<P_OUT> filter(final Predicate<? super P_OUT> predicate) {
        Objects.requireNonNull(predicate);
        return new StatelessOp<P_OUT, P_OUT>(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SIZED) {
            /* class java.util.stream.ReferencePipeline.AnonymousClass2 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<P_OUT> opWrapSink(int flags, Sink<P_OUT> sink) {
                return new Sink.ChainedReference<P_OUT, P_OUT>(sink) {
                    /* class java.util.stream.ReferencePipeline.AnonymousClass2.AnonymousClass1 */

                    @Override // java.util.stream.Sink.ChainedReference, java.util.stream.Sink
                    public void begin(long size) {
                        this.downstream.begin(-1);
                    }

                    @Override // java.util.function.Consumer
                    public void accept(P_OUT u) {
                        if (predicate.test(u)) {
                            this.downstream.accept(u);
                        }
                    }
                };
            }
        };
    }

    @Override // java.util.stream.Stream
    public final <R> Stream<R> map(final Function<? super P_OUT, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new StatelessOp<P_OUT, R>(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            /* class java.util.stream.ReferencePipeline.AnonymousClass3 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<P_OUT> opWrapSink(int flags, Sink<R> sink) {
                return new Sink.ChainedReference<P_OUT, R>(sink) {
                    /* class java.util.stream.ReferencePipeline.AnonymousClass3.AnonymousClass1 */

                    @Override // java.util.function.Consumer
                    public void accept(P_OUT u) {
                        this.downstream.accept(mapper.apply(u));
                    }
                };
            }
        };
    }

    @Override // java.util.stream.Stream
    public final IntStream mapToInt(final ToIntFunction<? super P_OUT> mapper) {
        Objects.requireNonNull(mapper);
        return new IntPipeline.StatelessOp<P_OUT>(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            /* class java.util.stream.ReferencePipeline.AnonymousClass4 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<P_OUT> opWrapSink(int flags, Sink<Integer> sink) {
                return new Sink.ChainedReference<P_OUT, Integer>(sink) {
                    /* class java.util.stream.ReferencePipeline.AnonymousClass4.AnonymousClass1 */

                    @Override // java.util.function.Consumer
                    public void accept(P_OUT u) {
                        this.downstream.accept(mapper.applyAsInt(u));
                    }
                };
            }
        };
    }

    @Override // java.util.stream.Stream
    public final LongStream mapToLong(final ToLongFunction<? super P_OUT> mapper) {
        Objects.requireNonNull(mapper);
        return new LongPipeline.StatelessOp<P_OUT>(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            /* class java.util.stream.ReferencePipeline.AnonymousClass5 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<P_OUT> opWrapSink(int flags, Sink<Long> sink) {
                return new Sink.ChainedReference<P_OUT, Long>(sink) {
                    /* class java.util.stream.ReferencePipeline.AnonymousClass5.AnonymousClass1 */

                    @Override // java.util.function.Consumer
                    public void accept(P_OUT u) {
                        this.downstream.accept(mapper.applyAsLong(u));
                    }
                };
            }
        };
    }

    @Override // java.util.stream.Stream
    public final DoubleStream mapToDouble(final ToDoubleFunction<? super P_OUT> mapper) {
        Objects.requireNonNull(mapper);
        return new DoublePipeline.StatelessOp<P_OUT>(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            /* class java.util.stream.ReferencePipeline.AnonymousClass6 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<P_OUT> opWrapSink(int flags, Sink<Double> sink) {
                return new Sink.ChainedReference<P_OUT, Double>(sink) {
                    /* class java.util.stream.ReferencePipeline.AnonymousClass6.AnonymousClass1 */

                    @Override // java.util.function.Consumer
                    public void accept(P_OUT u) {
                        this.downstream.accept(mapper.applyAsDouble(u));
                    }
                };
            }
        };
    }

    @Override // java.util.stream.Stream
    public final <R> Stream<R> flatMap(final Function<? super P_OUT, ? extends Stream<? extends R>> mapper) {
        Objects.requireNonNull(mapper);
        return new StatelessOp<P_OUT, R>(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) {
            /* class java.util.stream.ReferencePipeline.AnonymousClass7 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<P_OUT> opWrapSink(int flags, Sink<R> sink) {
                return new Sink.ChainedReference<P_OUT, R>(sink) {
                    /* class java.util.stream.ReferencePipeline.AnonymousClass7.AnonymousClass1 */

                    @Override // java.util.stream.Sink.ChainedReference, java.util.stream.Sink
                    public void begin(long size) {
                        this.downstream.begin(-1);
                    }

                    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001f, code lost:
                        r3 = move-exception;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
                        r1.addSuppressed(r3);
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0023, code lost:
                        throw r2;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001a, code lost:
                        r2 = move-exception;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
                        r0.close();
                     */
                    @Override // java.util.function.Consumer
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void accept(P_OUT r5) {
                        /*
                            r4 = this;
                            java.util.stream.ReferencePipeline$7 r0 = java.util.stream.ReferencePipeline.AnonymousClass7.this
                            java.util.function.Function r0 = r8
                            java.lang.Object r0 = r0.apply(r5)
                            java.util.stream.Stream r0 = (java.util.stream.Stream) r0
                            if (r0 == 0) goto L_0x0024
                            java.util.stream.BaseStream r1 = r0.sequential()     // Catch:{ all -> 0x0018 }
                            java.util.stream.Stream r1 = (java.util.stream.Stream) r1     // Catch:{ all -> 0x0018 }
                            java.util.stream.Sink r2 = r4.downstream     // Catch:{ all -> 0x0018 }
                            r1.forEach(r2)     // Catch:{ all -> 0x0018 }
                            goto L_0x0024
                        L_0x0018:
                            r1 = move-exception
                            throw r1     // Catch:{ all -> 0x001a }
                        L_0x001a:
                            r2 = move-exception
                            r0.close()     // Catch:{ all -> 0x001f }
                            goto L_0x0023
                        L_0x001f:
                            r3 = move-exception
                            r1.addSuppressed(r3)
                        L_0x0023:
                            throw r2
                        L_0x0024:
                            if (r0 == 0) goto L_0x0029
                            r0.close()
                        L_0x0029:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: java.util.stream.ReferencePipeline.AnonymousClass7.AnonymousClass1.accept(java.lang.Object):void");
                    }
                };
            }
        };
    }

    @Override // java.util.stream.Stream
    public final IntStream flatMapToInt(final Function<? super P_OUT, ? extends IntStream> mapper) {
        Objects.requireNonNull(mapper);
        return new IntPipeline.StatelessOp<P_OUT>(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) {
            /* class java.util.stream.ReferencePipeline.AnonymousClass8 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<P_OUT> opWrapSink(int flags, Sink<Integer> sink) {
                return new Sink.ChainedReference<P_OUT, Integer>(sink) {
                    /* class java.util.stream.ReferencePipeline.AnonymousClass8.AnonymousClass1 */
                    IntConsumer downstreamAsInt;

                    {
                        Sink sink = this.downstream;
                        Objects.requireNonNull(sink);
                        this.downstreamAsInt = new IntConsumer() {
                            /* class java.util.stream.$$Lambda$wDsxx48ovPSGeNEb3P6H9u7YX0k */

                            @Override // java.util.function.IntConsumer
                            public final void accept(int i) {
                                Sink.this.accept(i);
                            }
                        };
                    }

                    @Override // java.util.stream.Sink.ChainedReference, java.util.stream.Sink
                    public void begin(long size) {
                        this.downstream.begin(-1);
                    }

                    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001d, code lost:
                        r3 = move-exception;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
                        r1.addSuppressed(r3);
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
                        throw r2;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0018, code lost:
                        r2 = move-exception;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
                        r0.close();
                     */
                    @Override // java.util.function.Consumer
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void accept(P_OUT r5) {
                        /*
                            r4 = this;
                            java.util.stream.ReferencePipeline$8 r0 = java.util.stream.ReferencePipeline.AnonymousClass8.this
                            java.util.function.Function r0 = r8
                            java.lang.Object r0 = r0.apply(r5)
                            java.util.stream.IntStream r0 = (java.util.stream.IntStream) r0
                            if (r0 == 0) goto L_0x0022
                            java.util.stream.IntStream r1 = r0.sequential()     // Catch:{ all -> 0x0016 }
                            java.util.function.IntConsumer r2 = r4.downstreamAsInt     // Catch:{ all -> 0x0016 }
                            r1.forEach(r2)     // Catch:{ all -> 0x0016 }
                            goto L_0x0022
                        L_0x0016:
                            r1 = move-exception
                            throw r1     // Catch:{ all -> 0x0018 }
                        L_0x0018:
                            r2 = move-exception
                            r0.close()     // Catch:{ all -> 0x001d }
                            goto L_0x0021
                        L_0x001d:
                            r3 = move-exception
                            r1.addSuppressed(r3)
                        L_0x0021:
                            throw r2
                        L_0x0022:
                            if (r0 == 0) goto L_0x0027
                            r0.close()
                        L_0x0027:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: java.util.stream.ReferencePipeline.AnonymousClass8.AnonymousClass1.accept(java.lang.Object):void");
                    }
                };
            }
        };
    }

    @Override // java.util.stream.Stream
    public final DoubleStream flatMapToDouble(final Function<? super P_OUT, ? extends DoubleStream> mapper) {
        Objects.requireNonNull(mapper);
        return new DoublePipeline.StatelessOp<P_OUT>(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) {
            /* class java.util.stream.ReferencePipeline.AnonymousClass9 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<P_OUT> opWrapSink(int flags, Sink<Double> sink) {
                return new Sink.ChainedReference<P_OUT, Double>(sink) {
                    /* class java.util.stream.ReferencePipeline.AnonymousClass9.AnonymousClass1 */
                    DoubleConsumer downstreamAsDouble;

                    {
                        Sink sink = this.downstream;
                        Objects.requireNonNull(sink);
                        this.downstreamAsDouble = new DoubleConsumer() {
                            /* class java.util.stream.$$Lambda$G0LLxk8pWitjFgsOx2bYtROrGg */

                            @Override // java.util.function.DoubleConsumer
                            public final void accept(double d) {
                                Sink.this.accept(d);
                            }
                        };
                    }

                    @Override // java.util.stream.Sink.ChainedReference, java.util.stream.Sink
                    public void begin(long size) {
                        this.downstream.begin(-1);
                    }

                    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001d, code lost:
                        r3 = move-exception;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
                        r1.addSuppressed(r3);
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
                        throw r2;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0018, code lost:
                        r2 = move-exception;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
                        r0.close();
                     */
                    @Override // java.util.function.Consumer
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void accept(P_OUT r5) {
                        /*
                            r4 = this;
                            java.util.stream.ReferencePipeline$9 r0 = java.util.stream.ReferencePipeline.AnonymousClass9.this
                            java.util.function.Function r0 = r8
                            java.lang.Object r0 = r0.apply(r5)
                            java.util.stream.DoubleStream r0 = (java.util.stream.DoubleStream) r0
                            if (r0 == 0) goto L_0x0022
                            java.util.stream.DoubleStream r1 = r0.sequential()     // Catch:{ all -> 0x0016 }
                            java.util.function.DoubleConsumer r2 = r4.downstreamAsDouble     // Catch:{ all -> 0x0016 }
                            r1.forEach(r2)     // Catch:{ all -> 0x0016 }
                            goto L_0x0022
                        L_0x0016:
                            r1 = move-exception
                            throw r1     // Catch:{ all -> 0x0018 }
                        L_0x0018:
                            r2 = move-exception
                            r0.close()     // Catch:{ all -> 0x001d }
                            goto L_0x0021
                        L_0x001d:
                            r3 = move-exception
                            r1.addSuppressed(r3)
                        L_0x0021:
                            throw r2
                        L_0x0022:
                            if (r0 == 0) goto L_0x0027
                            r0.close()
                        L_0x0027:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: java.util.stream.ReferencePipeline.AnonymousClass9.AnonymousClass1.accept(java.lang.Object):void");
                    }
                };
            }
        };
    }

    @Override // java.util.stream.Stream
    public final LongStream flatMapToLong(final Function<? super P_OUT, ? extends LongStream> mapper) {
        Objects.requireNonNull(mapper);
        return new LongPipeline.StatelessOp<P_OUT>(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) {
            /* class java.util.stream.ReferencePipeline.AnonymousClass10 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<P_OUT> opWrapSink(int flags, Sink<Long> sink) {
                return new Sink.ChainedReference<P_OUT, Long>(sink) {
                    /* class java.util.stream.ReferencePipeline.AnonymousClass10.AnonymousClass1 */
                    LongConsumer downstreamAsLong;

                    {
                        Sink sink = this.downstream;
                        Objects.requireNonNull(sink);
                        this.downstreamAsLong = new LongConsumer() {
                            /* class java.util.stream.$$Lambda$zQ9PoGPFOA3MjNNbaERnRB6ik */

                            @Override // java.util.function.LongConsumer
                            public final void accept(long j) {
                                Sink.this.accept(j);
                            }
                        };
                    }

                    @Override // java.util.stream.Sink.ChainedReference, java.util.stream.Sink
                    public void begin(long size) {
                        this.downstream.begin(-1);
                    }

                    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001d, code lost:
                        r3 = move-exception;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
                        r1.addSuppressed(r3);
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
                        throw r2;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0018, code lost:
                        r2 = move-exception;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
                        r0.close();
                     */
                    @Override // java.util.function.Consumer
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void accept(P_OUT r5) {
                        /*
                            r4 = this;
                            java.util.stream.ReferencePipeline$10 r0 = java.util.stream.ReferencePipeline.AnonymousClass10.this
                            java.util.function.Function r0 = r8
                            java.lang.Object r0 = r0.apply(r5)
                            java.util.stream.LongStream r0 = (java.util.stream.LongStream) r0
                            if (r0 == 0) goto L_0x0022
                            java.util.stream.LongStream r1 = r0.sequential()     // Catch:{ all -> 0x0016 }
                            java.util.function.LongConsumer r2 = r4.downstreamAsLong     // Catch:{ all -> 0x0016 }
                            r1.forEach(r2)     // Catch:{ all -> 0x0016 }
                            goto L_0x0022
                        L_0x0016:
                            r1 = move-exception
                            throw r1     // Catch:{ all -> 0x0018 }
                        L_0x0018:
                            r2 = move-exception
                            r0.close()     // Catch:{ all -> 0x001d }
                            goto L_0x0021
                        L_0x001d:
                            r3 = move-exception
                            r1.addSuppressed(r3)
                        L_0x0021:
                            throw r2
                        L_0x0022:
                            if (r0 == 0) goto L_0x0027
                            r0.close()
                        L_0x0027:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: java.util.stream.ReferencePipeline.AnonymousClass10.AnonymousClass1.accept(java.lang.Object):void");
                    }
                };
            }
        };
    }

    @Override // java.util.stream.Stream
    public final Stream<P_OUT> peek(final Consumer<? super P_OUT> action) {
        Objects.requireNonNull(action);
        return new StatelessOp<P_OUT, P_OUT>(this, StreamShape.REFERENCE, 0) {
            /* class java.util.stream.ReferencePipeline.AnonymousClass11 */

            @Override // java.util.stream.AbstractPipeline
            public Sink<P_OUT> opWrapSink(int flags, Sink<P_OUT> sink) {
                return new Sink.ChainedReference<P_OUT, P_OUT>(sink) {
                    /* class java.util.stream.ReferencePipeline.AnonymousClass11.AnonymousClass1 */

                    @Override // java.util.function.Consumer
                    public void accept(P_OUT u) {
                        action.accept(u);
                        this.downstream.accept(u);
                    }
                };
            }
        };
    }

    @Override // java.util.stream.Stream
    public final Stream<P_OUT> distinct() {
        return DistinctOps.makeRef(this);
    }

    @Override // java.util.stream.Stream
    public final Stream<P_OUT> sorted() {
        return SortedOps.makeRef(this);
    }

    @Override // java.util.stream.Stream
    public final Stream<P_OUT> sorted(Comparator<? super P_OUT> comparator) {
        return SortedOps.makeRef(this, comparator);
    }

    @Override // java.util.stream.Stream
    public final Stream<P_OUT> limit(long maxSize) {
        if (maxSize >= 0) {
            return SliceOps.makeRef(this, 0, maxSize);
        }
        throw new IllegalArgumentException(Long.toString(maxSize));
    }

    @Override // java.util.stream.Stream
    public final Stream<P_OUT> skip(long n) {
        if (n < 0) {
            throw new IllegalArgumentException(Long.toString(n));
        } else if (n == 0) {
            return this;
        } else {
            return SliceOps.makeRef(this, n, -1);
        }
    }

    @Override // java.util.stream.Stream
    public void forEach(Consumer<? super P_OUT> action) {
        evaluate(ForEachOps.makeRef(action, false));
    }

    @Override // java.util.stream.Stream
    public void forEachOrdered(Consumer<? super P_OUT> action) {
        evaluate(ForEachOps.makeRef(action, true));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.util.function.IntFunction<A[]> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.stream.Stream
    public final <A> A[] toArray(IntFunction<A[]> generator) {
        return (A[]) Nodes.flatten(evaluateToArrayNode(generator), generator).asArray(generator);
    }

    static /* synthetic */ Object[] lambda$toArray$0(int x$0) {
        return new Object[x$0];
    }

    @Override // java.util.stream.Stream
    public final Object[] toArray() {
        return toArray($$Lambda$ReferencePipeline$n3O_UMTjTSOeDSKD1yhh_2N2rRU.INSTANCE);
    }

    @Override // java.util.stream.Stream
    public final boolean anyMatch(Predicate<? super P_OUT> predicate) {
        return ((Boolean) evaluate(MatchOps.makeRef(predicate, MatchOps.MatchKind.ANY))).booleanValue();
    }

    @Override // java.util.stream.Stream
    public final boolean allMatch(Predicate<? super P_OUT> predicate) {
        return ((Boolean) evaluate(MatchOps.makeRef(predicate, MatchOps.MatchKind.ALL))).booleanValue();
    }

    @Override // java.util.stream.Stream
    public final boolean noneMatch(Predicate<? super P_OUT> predicate) {
        return ((Boolean) evaluate(MatchOps.makeRef(predicate, MatchOps.MatchKind.NONE))).booleanValue();
    }

    @Override // java.util.stream.Stream
    public final Optional<P_OUT> findFirst() {
        return (Optional) evaluate(FindOps.makeRef(true));
    }

    @Override // java.util.stream.Stream
    public final Optional<P_OUT> findAny() {
        return (Optional) evaluate(FindOps.makeRef(false));
    }

    @Override // java.util.stream.Stream
    public final P_OUT reduce(P_OUT identity, BinaryOperator<P_OUT> accumulator) {
        return (P_OUT) evaluate(ReduceOps.makeRef(identity, accumulator, accumulator));
    }

    @Override // java.util.stream.Stream
    public final Optional<P_OUT> reduce(BinaryOperator<P_OUT> accumulator) {
        return (Optional) evaluate(ReduceOps.makeRef(accumulator));
    }

    @Override // java.util.stream.Stream
    public final <R> R reduce(R identity, BiFunction<R, ? super P_OUT, R> accumulator, BinaryOperator<R> combiner) {
        return (R) evaluate(ReduceOps.makeRef(identity, accumulator, combiner));
    }

    @Override // java.util.stream.Stream
    public final <R, A> R collect(Collector<? super P_OUT, A, R> collector) {
        A container;
        if (!isParallel() || !collector.characteristics().contains(Collector.Characteristics.CONCURRENT) || (isOrdered() && !collector.characteristics().contains(Collector.Characteristics.UNORDERED))) {
            container = (A) evaluate(ReduceOps.makeRef(collector));
        } else {
            container = collector.supplier().get();
            forEach(new Consumer(container) {
                /* class java.util.stream.$$Lambda$ReferencePipeline$iWMOWwbzoH_veRIvtIHIFvko */
                private final /* synthetic */ Object f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    BiConsumer.this.accept(this.f$1, obj);
                }
            });
        }
        return collector.characteristics().contains(Collector.Characteristics.IDENTITY_FINISH) ? (R) container : collector.finisher().apply(container);
    }

    @Override // java.util.stream.Stream
    public final <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super P_OUT> accumulator, BiConsumer<R, R> combiner) {
        return (R) evaluate(ReduceOps.makeRef(supplier, accumulator, combiner));
    }

    @Override // java.util.stream.Stream
    public final Optional<P_OUT> max(Comparator<? super P_OUT> comparator) {
        return reduce(BinaryOperator.maxBy(comparator));
    }

    @Override // java.util.stream.Stream
    public final Optional<P_OUT> min(Comparator<? super P_OUT> comparator) {
        return reduce(BinaryOperator.minBy(comparator));
    }

    static /* synthetic */ long lambda$count$2(Object e) {
        return 1;
    }

    @Override // java.util.stream.Stream
    public final long count() {
        return mapToLong($$Lambda$ReferencePipeline$mk6xSsLZAKvG89IyN8pzBoM6otw.INSTANCE).sum();
    }

    public static class Head<E_IN, E_OUT> extends ReferencePipeline<E_IN, E_OUT> {
        public Head(Supplier<? extends Spliterator<?>> source, int sourceFlags, boolean parallel) {
            super(source, sourceFlags, parallel);
        }

        public Head(Spliterator<?> source, int sourceFlags, boolean parallel) {
            super(source, sourceFlags, parallel);
        }

        @Override // java.util.stream.AbstractPipeline
        public final boolean opIsStateful() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.stream.AbstractPipeline
        public final Sink<E_IN> opWrapSink(int flags, Sink<E_OUT> sink) {
            throw new UnsupportedOperationException();
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.function.Consumer<? super E_OUT> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.stream.Stream, java.util.stream.ReferencePipeline
        public void forEach(Consumer<? super E_OUT> action) {
            if (!isParallel()) {
                sourceStageSpliterator().forEachRemaining(action);
            } else {
                ReferencePipeline.super.forEach(action);
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.function.Consumer<? super E_OUT> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.stream.Stream, java.util.stream.ReferencePipeline
        public void forEachOrdered(Consumer<? super E_OUT> action) {
            if (!isParallel()) {
                sourceStageSpliterator().forEachRemaining(action);
            } else {
                ReferencePipeline.super.forEachOrdered(action);
            }
        }
    }

    public static abstract class StatelessOp<E_IN, E_OUT> extends ReferencePipeline<E_IN, E_OUT> {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        public StatelessOp(AbstractPipeline<?, E_IN, ?> upstream, StreamShape inputShape, int opFlags) {
            super(upstream, opFlags);
        }

        @Override // java.util.stream.AbstractPipeline
        public final boolean opIsStateful() {
            return false;
        }
    }

    public static abstract class StatefulOp<E_IN, E_OUT> extends ReferencePipeline<E_IN, E_OUT> {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        @Override // java.util.stream.AbstractPipeline
        public abstract <P_IN> Node<E_OUT> opEvaluateParallel(PipelineHelper<E_OUT> pipelineHelper, Spliterator<P_IN> spliterator, IntFunction<E_OUT[]> intFunction);

        public StatefulOp(AbstractPipeline<?, E_IN, ?> upstream, StreamShape inputShape, int opFlags) {
            super(upstream, opFlags);
        }

        @Override // java.util.stream.AbstractPipeline
        public final boolean opIsStateful() {
            return true;
        }
    }
}
