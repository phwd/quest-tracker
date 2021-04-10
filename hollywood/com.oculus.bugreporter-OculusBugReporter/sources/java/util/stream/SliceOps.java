package java.util.stream;

import java.util.Spliterator;
import java.util.concurrent.CountedCompleter;
import java.util.function.IntFunction;
import java.util.stream.DoublePipeline;
import java.util.stream.IntPipeline;
import java.util.stream.LongPipeline;
import java.util.stream.Node;
import java.util.stream.ReferencePipeline;
import java.util.stream.Sink;
import java.util.stream.StreamSpliterators;

final class SliceOps {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    private SliceOps() {
    }

    /* access modifiers changed from: private */
    public static long calcSize(long size, long skip, long limit) {
        if (size >= 0) {
            return Math.max(-1L, Math.min(size - skip, limit));
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public static long calcSliceFence(long skip, long limit) {
        long sliceFence = limit >= 0 ? skip + limit : Long.MAX_VALUE;
        if (sliceFence >= 0) {
            return sliceFence;
        }
        return Long.MAX_VALUE;
    }

    /* access modifiers changed from: private */
    public static <P_IN> Spliterator<P_IN> sliceSpliterator(StreamShape shape, Spliterator<P_IN> s, long skip, long limit) {
        long sliceFence = calcSliceFence(skip, limit);
        int i = AnonymousClass5.$SwitchMap$java$util$stream$StreamShape[shape.ordinal()];
        if (i == 1) {
            return new StreamSpliterators.SliceSpliterator.OfRef(s, skip, sliceFence);
        }
        if (i == 2) {
            return new StreamSpliterators.SliceSpliterator.OfInt((Spliterator.OfInt) s, skip, sliceFence);
        }
        if (i == 3) {
            return new StreamSpliterators.SliceSpliterator.OfLong((Spliterator.OfLong) s, skip, sliceFence);
        }
        if (i == 4) {
            return new StreamSpliterators.SliceSpliterator.OfDouble((Spliterator.OfDouble) s, skip, sliceFence);
        }
        throw new IllegalStateException("Unknown shape " + ((Object) shape));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: java.util.stream.SliceOps$5  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$java$util$stream$StreamShape = new int[StreamShape.values().length];

        static {
            try {
                $SwitchMap$java$util$stream$StreamShape[StreamShape.REFERENCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$java$util$stream$StreamShape[StreamShape.INT_VALUE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$java$util$stream$StreamShape[StreamShape.LONG_VALUE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$java$util$stream$StreamShape[StreamShape.DOUBLE_VALUE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* access modifiers changed from: private */
    public static <T> IntFunction<T[]> castingArray() {
        return $$Lambda$SliceOps$T0eS2B9nWeCpmA7G2QlMnW3G2UA.INSTANCE;
    }

    static /* synthetic */ Object[] lambda$castingArray$0(int size) {
        return new Object[size];
    }

    public static <T> Stream<T> makeRef(AbstractPipeline<?, T, ?> upstream, final long skip, final long limit) {
        if (skip >= 0) {
            return new ReferencePipeline.StatefulOp<T, T>(upstream, StreamShape.REFERENCE, flags(limit)) {
                /* class java.util.stream.SliceOps.AnonymousClass1 */

                /* access modifiers changed from: package-private */
                public Spliterator<T> unorderedSkipLimitSpliterator(Spliterator<T> s, long skip, long limit, long sizeIfKnown) {
                    if (skip <= sizeIfKnown) {
                        limit = limit >= 0 ? Math.min(limit, sizeIfKnown - skip) : sizeIfKnown - skip;
                        skip = 0;
                    }
                    return new StreamSpliterators.UnorderedSliceSpliterator.OfRef(s, skip, limit);
                }

                @Override // java.util.stream.AbstractPipeline
                public <P_IN> Spliterator<T> opEvaluateParallelLazy(PipelineHelper<T> helper, Spliterator<P_IN> spliterator) {
                    long size = helper.exactOutputSizeIfKnown(spliterator);
                    if (size > 0) {
                        if (spliterator.hasCharacteristics(16384)) {
                            Spliterator<T> wrapSpliterator = helper.wrapSpliterator(spliterator);
                            long j = skip;
                            return new StreamSpliterators.SliceSpliterator.OfRef(wrapSpliterator, j, SliceOps.calcSliceFence(j, limit));
                        }
                    }
                    if (!StreamOpFlag.ORDERED.isKnown(helper.getStreamAndOpFlags())) {
                        return unorderedSkipLimitSpliterator(helper.wrapSpliterator(spliterator), skip, limit, size);
                    }
                    return ((Node) new SliceTask(this, helper, spliterator, SliceOps.castingArray(), skip, limit).invoke()).spliterator();
                }

                @Override // java.util.stream.ReferencePipeline.StatefulOp, java.util.stream.AbstractPipeline
                public <P_IN> Node<T> opEvaluateParallel(PipelineHelper<T> helper, Spliterator<P_IN> spliterator, IntFunction<T[]> generator) {
                    long size = helper.exactOutputSizeIfKnown(spliterator);
                    if (size > 0) {
                        if (spliterator.hasCharacteristics(16384)) {
                            return Nodes.collect(helper, SliceOps.sliceSpliterator(helper.getSourceShape(), spliterator, skip, limit), true, generator);
                        }
                    }
                    if (!StreamOpFlag.ORDERED.isKnown(helper.getStreamAndOpFlags())) {
                        return Nodes.collect(this, unorderedSkipLimitSpliterator(helper.wrapSpliterator(spliterator), skip, limit, size), true, generator);
                    }
                    return (Node) new SliceTask(this, helper, spliterator, generator, skip, limit).invoke();
                }

                @Override // java.util.stream.AbstractPipeline
                public Sink<T> opWrapSink(int flags, Sink<T> sink) {
                    return new Sink.ChainedReference<T, T>(sink) {
                        /* class java.util.stream.SliceOps.AnonymousClass1.AnonymousClass1 */
                        long m;
                        long n = skip;

                        {
                            this.m = limit >= 0 ? limit : Long.MAX_VALUE;
                        }

                        @Override // java.util.stream.Sink.ChainedReference, java.util.stream.Sink
                        public void begin(long size) {
                            this.downstream.begin(SliceOps.calcSize(size, skip, this.m));
                        }

                        @Override // java.util.function.Consumer
                        public void accept(T t) {
                            long j = this.n;
                            if (j == 0) {
                                long j2 = this.m;
                                if (j2 > 0) {
                                    this.m = j2 - 1;
                                    this.downstream.accept(t);
                                    return;
                                }
                                return;
                            }
                            this.n = j - 1;
                        }

                        @Override // java.util.stream.Sink.ChainedReference, java.util.stream.Sink
                        public boolean cancellationRequested() {
                            return this.m == 0 || this.downstream.cancellationRequested();
                        }
                    };
                }
            };
        }
        throw new IllegalArgumentException("Skip must be non-negative: " + skip);
    }

    public static IntStream makeInt(AbstractPipeline<?, Integer, ?> upstream, final long skip, final long limit) {
        if (skip >= 0) {
            return new IntPipeline.StatefulOp<Integer>(upstream, StreamShape.INT_VALUE, flags(limit)) {
                /* class java.util.stream.SliceOps.AnonymousClass2 */

                /* access modifiers changed from: package-private */
                public Spliterator.OfInt unorderedSkipLimitSpliterator(Spliterator.OfInt s, long skip, long limit, long sizeIfKnown) {
                    if (skip <= sizeIfKnown) {
                        limit = limit >= 0 ? Math.min(limit, sizeIfKnown - skip) : sizeIfKnown - skip;
                        skip = 0;
                    }
                    return new StreamSpliterators.UnorderedSliceSpliterator.OfInt(s, skip, limit);
                }

                @Override // java.util.stream.AbstractPipeline
                public <P_IN> Spliterator<Integer> opEvaluateParallelLazy(PipelineHelper<Integer> helper, Spliterator<P_IN> spliterator) {
                    long size = helper.exactOutputSizeIfKnown(spliterator);
                    if (size > 0) {
                        if (spliterator.hasCharacteristics(16384)) {
                            long j = skip;
                            return new StreamSpliterators.SliceSpliterator.OfInt((Spliterator.OfInt) helper.wrapSpliterator(spliterator), j, SliceOps.calcSliceFence(j, limit));
                        }
                    }
                    if (!StreamOpFlag.ORDERED.isKnown(helper.getStreamAndOpFlags())) {
                        return unorderedSkipLimitSpliterator((Spliterator.OfInt) helper.wrapSpliterator(spliterator), skip, limit, size);
                    }
                    return ((Node) new SliceTask(this, helper, spliterator, $$Lambda$SliceOps$2$pJKvYyBs7HGPiOPTm_fxpciSsG8.INSTANCE, skip, limit).invoke()).spliterator();
                }

                static /* synthetic */ Integer[] lambda$opEvaluateParallelLazy$0(int x$0) {
                    return new Integer[x$0];
                }

                @Override // java.util.stream.AbstractPipeline, java.util.stream.IntPipeline.StatefulOp
                public <P_IN> Node<Integer> opEvaluateParallel(PipelineHelper<Integer> helper, Spliterator<P_IN> spliterator, IntFunction<Integer[]> generator) {
                    long size = helper.exactOutputSizeIfKnown(spliterator);
                    if (size > 0) {
                        if (spliterator.hasCharacteristics(16384)) {
                            return Nodes.collectInt(helper, SliceOps.sliceSpliterator(helper.getSourceShape(), spliterator, skip, limit), true);
                        }
                    }
                    if (!StreamOpFlag.ORDERED.isKnown(helper.getStreamAndOpFlags())) {
                        return Nodes.collectInt(this, unorderedSkipLimitSpliterator((Spliterator.OfInt) helper.wrapSpliterator(spliterator), skip, limit, size), true);
                    }
                    return (Node) new SliceTask(this, helper, spliterator, generator, skip, limit).invoke();
                }

                @Override // java.util.stream.AbstractPipeline
                public Sink<Integer> opWrapSink(int flags, Sink<Integer> sink) {
                    return new Sink.ChainedInt<Integer>(sink) {
                        /* class java.util.stream.SliceOps.AnonymousClass2.AnonymousClass1 */
                        long m;
                        long n = skip;

                        {
                            this.m = limit >= 0 ? limit : Long.MAX_VALUE;
                        }

                        @Override // java.util.stream.Sink.ChainedInt, java.util.stream.Sink
                        public void begin(long size) {
                            this.downstream.begin(SliceOps.calcSize(size, skip, this.m));
                        }

                        @Override // java.util.stream.Sink.OfInt, java.util.function.IntConsumer, java.util.stream.Sink
                        public void accept(int t) {
                            long j = this.n;
                            if (j == 0) {
                                long j2 = this.m;
                                if (j2 > 0) {
                                    this.m = j2 - 1;
                                    this.downstream.accept(t);
                                    return;
                                }
                                return;
                            }
                            this.n = j - 1;
                        }

                        @Override // java.util.stream.Sink.ChainedInt, java.util.stream.Sink
                        public boolean cancellationRequested() {
                            return this.m == 0 || this.downstream.cancellationRequested();
                        }
                    };
                }
            };
        }
        throw new IllegalArgumentException("Skip must be non-negative: " + skip);
    }

    public static LongStream makeLong(AbstractPipeline<?, Long, ?> upstream, final long skip, final long limit) {
        if (skip >= 0) {
            return new LongPipeline.StatefulOp<Long>(upstream, StreamShape.LONG_VALUE, flags(limit)) {
                /* class java.util.stream.SliceOps.AnonymousClass3 */

                /* access modifiers changed from: package-private */
                public Spliterator.OfLong unorderedSkipLimitSpliterator(Spliterator.OfLong s, long skip, long limit, long sizeIfKnown) {
                    if (skip <= sizeIfKnown) {
                        limit = limit >= 0 ? Math.min(limit, sizeIfKnown - skip) : sizeIfKnown - skip;
                        skip = 0;
                    }
                    return new StreamSpliterators.UnorderedSliceSpliterator.OfLong(s, skip, limit);
                }

                @Override // java.util.stream.AbstractPipeline
                public <P_IN> Spliterator<Long> opEvaluateParallelLazy(PipelineHelper<Long> helper, Spliterator<P_IN> spliterator) {
                    long size = helper.exactOutputSizeIfKnown(spliterator);
                    if (size > 0) {
                        if (spliterator.hasCharacteristics(16384)) {
                            long j = skip;
                            return new StreamSpliterators.SliceSpliterator.OfLong((Spliterator.OfLong) helper.wrapSpliterator(spliterator), j, SliceOps.calcSliceFence(j, limit));
                        }
                    }
                    if (!StreamOpFlag.ORDERED.isKnown(helper.getStreamAndOpFlags())) {
                        return unorderedSkipLimitSpliterator((Spliterator.OfLong) helper.wrapSpliterator(spliterator), skip, limit, size);
                    }
                    return ((Node) new SliceTask(this, helper, spliterator, $$Lambda$SliceOps$3$iKJ8R9VMhJpW3rzcr1q11o2TH4.INSTANCE, skip, limit).invoke()).spliterator();
                }

                static /* synthetic */ Long[] lambda$opEvaluateParallelLazy$0(int x$0) {
                    return new Long[x$0];
                }

                @Override // java.util.stream.AbstractPipeline, java.util.stream.LongPipeline.StatefulOp
                public <P_IN> Node<Long> opEvaluateParallel(PipelineHelper<Long> helper, Spliterator<P_IN> spliterator, IntFunction<Long[]> generator) {
                    long size = helper.exactOutputSizeIfKnown(spliterator);
                    if (size > 0) {
                        if (spliterator.hasCharacteristics(16384)) {
                            return Nodes.collectLong(helper, SliceOps.sliceSpliterator(helper.getSourceShape(), spliterator, skip, limit), true);
                        }
                    }
                    if (!StreamOpFlag.ORDERED.isKnown(helper.getStreamAndOpFlags())) {
                        return Nodes.collectLong(this, unorderedSkipLimitSpliterator((Spliterator.OfLong) helper.wrapSpliterator(spliterator), skip, limit, size), true);
                    }
                    return (Node) new SliceTask(this, helper, spliterator, generator, skip, limit).invoke();
                }

                @Override // java.util.stream.AbstractPipeline
                public Sink<Long> opWrapSink(int flags, Sink<Long> sink) {
                    return new Sink.ChainedLong<Long>(sink) {
                        /* class java.util.stream.SliceOps.AnonymousClass3.AnonymousClass1 */
                        long m;
                        long n = skip;

                        {
                            this.m = limit >= 0 ? limit : Long.MAX_VALUE;
                        }

                        @Override // java.util.stream.Sink.ChainedLong, java.util.stream.Sink
                        public void begin(long size) {
                            this.downstream.begin(SliceOps.calcSize(size, skip, this.m));
                        }

                        @Override // java.util.stream.Sink.OfLong, java.util.stream.Sink, java.util.function.LongConsumer
                        public void accept(long t) {
                            long j = this.n;
                            if (j == 0) {
                                long j2 = this.m;
                                if (j2 > 0) {
                                    this.m = j2 - 1;
                                    this.downstream.accept(t);
                                    return;
                                }
                                return;
                            }
                            this.n = j - 1;
                        }

                        @Override // java.util.stream.Sink.ChainedLong, java.util.stream.Sink
                        public boolean cancellationRequested() {
                            return this.m == 0 || this.downstream.cancellationRequested();
                        }
                    };
                }
            };
        }
        throw new IllegalArgumentException("Skip must be non-negative: " + skip);
    }

    public static DoubleStream makeDouble(AbstractPipeline<?, Double, ?> upstream, final long skip, final long limit) {
        if (skip >= 0) {
            return new DoublePipeline.StatefulOp<Double>(upstream, StreamShape.DOUBLE_VALUE, flags(limit)) {
                /* class java.util.stream.SliceOps.AnonymousClass4 */

                /* access modifiers changed from: package-private */
                public Spliterator.OfDouble unorderedSkipLimitSpliterator(Spliterator.OfDouble s, long skip, long limit, long sizeIfKnown) {
                    if (skip <= sizeIfKnown) {
                        limit = limit >= 0 ? Math.min(limit, sizeIfKnown - skip) : sizeIfKnown - skip;
                        skip = 0;
                    }
                    return new StreamSpliterators.UnorderedSliceSpliterator.OfDouble(s, skip, limit);
                }

                @Override // java.util.stream.AbstractPipeline
                public <P_IN> Spliterator<Double> opEvaluateParallelLazy(PipelineHelper<Double> helper, Spliterator<P_IN> spliterator) {
                    long size = helper.exactOutputSizeIfKnown(spliterator);
                    if (size > 0) {
                        if (spliterator.hasCharacteristics(16384)) {
                            long j = skip;
                            return new StreamSpliterators.SliceSpliterator.OfDouble((Spliterator.OfDouble) helper.wrapSpliterator(spliterator), j, SliceOps.calcSliceFence(j, limit));
                        }
                    }
                    if (!StreamOpFlag.ORDERED.isKnown(helper.getStreamAndOpFlags())) {
                        return unorderedSkipLimitSpliterator((Spliterator.OfDouble) helper.wrapSpliterator(spliterator), skip, limit, size);
                    }
                    return ((Node) new SliceTask(this, helper, spliterator, $$Lambda$SliceOps$4$JdMLhF4N5dBS3gGxMct4lK2SQ04.INSTANCE, skip, limit).invoke()).spliterator();
                }

                static /* synthetic */ Double[] lambda$opEvaluateParallelLazy$0(int x$0) {
                    return new Double[x$0];
                }

                @Override // java.util.stream.DoublePipeline.StatefulOp, java.util.stream.AbstractPipeline
                public <P_IN> Node<Double> opEvaluateParallel(PipelineHelper<Double> helper, Spliterator<P_IN> spliterator, IntFunction<Double[]> generator) {
                    long size = helper.exactOutputSizeIfKnown(spliterator);
                    if (size > 0) {
                        if (spliterator.hasCharacteristics(16384)) {
                            return Nodes.collectDouble(helper, SliceOps.sliceSpliterator(helper.getSourceShape(), spliterator, skip, limit), true);
                        }
                    }
                    if (!StreamOpFlag.ORDERED.isKnown(helper.getStreamAndOpFlags())) {
                        return Nodes.collectDouble(this, unorderedSkipLimitSpliterator((Spliterator.OfDouble) helper.wrapSpliterator(spliterator), skip, limit, size), true);
                    }
                    return (Node) new SliceTask(this, helper, spliterator, generator, skip, limit).invoke();
                }

                @Override // java.util.stream.AbstractPipeline
                public Sink<Double> opWrapSink(int flags, Sink<Double> sink) {
                    return new Sink.ChainedDouble<Double>(sink) {
                        /* class java.util.stream.SliceOps.AnonymousClass4.AnonymousClass1 */
                        long m;
                        long n = skip;

                        {
                            this.m = limit >= 0 ? limit : Long.MAX_VALUE;
                        }

                        @Override // java.util.stream.Sink.ChainedDouble, java.util.stream.Sink
                        public void begin(long size) {
                            this.downstream.begin(SliceOps.calcSize(size, skip, this.m));
                        }

                        @Override // java.util.stream.Sink.OfDouble, java.util.function.DoubleConsumer, java.util.stream.Sink
                        public void accept(double t) {
                            long j = this.n;
                            if (j == 0) {
                                long j2 = this.m;
                                if (j2 > 0) {
                                    this.m = j2 - 1;
                                    this.downstream.accept(t);
                                    return;
                                }
                                return;
                            }
                            this.n = j - 1;
                        }

                        @Override // java.util.stream.Sink.ChainedDouble, java.util.stream.Sink
                        public boolean cancellationRequested() {
                            return this.m == 0 || this.downstream.cancellationRequested();
                        }
                    };
                }
            };
        }
        throw new IllegalArgumentException("Skip must be non-negative: " + skip);
    }

    private static int flags(long limit) {
        return StreamOpFlag.NOT_SIZED | (limit != -1 ? StreamOpFlag.IS_SHORT_CIRCUIT : 0);
    }

    private static final class SliceTask<P_IN, P_OUT> extends AbstractShortCircuitTask<P_IN, P_OUT, Node<P_OUT>, SliceTask<P_IN, P_OUT>> {
        private volatile boolean completed;
        private final IntFunction<P_OUT[]> generator;
        private final AbstractPipeline<P_OUT, P_OUT, ?> op;
        private final long targetOffset;
        private final long targetSize;
        private long thisNodeSize;

        SliceTask(AbstractPipeline<P_OUT, P_OUT, ?> op2, PipelineHelper<P_OUT> helper, Spliterator<P_IN> spliterator, IntFunction<P_OUT[]> generator2, long offset, long size) {
            super(helper, spliterator);
            this.op = op2;
            this.generator = generator2;
            this.targetOffset = offset;
            this.targetSize = size;
        }

        SliceTask(SliceTask<P_IN, P_OUT> parent, Spliterator<P_IN> spliterator) {
            super(parent, spliterator);
            this.op = parent.op;
            this.generator = parent.generator;
            this.targetOffset = parent.targetOffset;
            this.targetSize = parent.targetSize;
        }

        /* access modifiers changed from: protected */
        @Override // java.util.stream.AbstractTask
        public SliceTask<P_IN, P_OUT> makeChild(Spliterator<P_IN> spliterator) {
            return new SliceTask<>(this, spliterator);
        }

        /* access modifiers changed from: protected */
        @Override // java.util.stream.AbstractShortCircuitTask
        public final Node<P_OUT> getEmptyResult() {
            return Nodes.emptyNode(this.op.getOutputShape());
        }

        /* access modifiers changed from: protected */
        @Override // java.util.stream.AbstractTask
        public final Node<P_OUT> doLeaf() {
            long sizeIfKnown = -1;
            if (isRoot()) {
                if (StreamOpFlag.SIZED.isPreserved(this.op.sourceOrOpFlags)) {
                    sizeIfKnown = this.op.exactOutputSizeIfKnown(this.spliterator);
                }
                Node.Builder<P_OUT> nb = this.op.makeNodeBuilder(sizeIfKnown, this.generator);
                this.helper.copyIntoWithCancel(this.helper.wrapSink(this.op.opWrapSink(this.helper.getStreamAndOpFlags(), nb)), this.spliterator);
                return nb.build();
            }
            Node<P_OUT> node = ((Node.Builder) this.helper.wrapAndCopyInto(this.helper.makeNodeBuilder(-1, this.generator), this.spliterator)).build();
            this.thisNodeSize = node.count();
            this.completed = true;
            this.spliterator = null;
            return node;
        }

        @Override // java.util.concurrent.CountedCompleter, java.util.stream.AbstractTask
        public final void onCompletion(CountedCompleter<?> caller) {
            Node<P_OUT> result;
            if (!isLeaf()) {
                this.thisNodeSize = ((SliceTask) this.leftChild).thisNodeSize + ((SliceTask) this.rightChild).thisNodeSize;
                if (this.canceled) {
                    this.thisNodeSize = 0;
                    result = getEmptyResult();
                } else if (this.thisNodeSize == 0) {
                    result = getEmptyResult();
                } else if (((SliceTask) this.leftChild).thisNodeSize == 0) {
                    result = (Node) ((SliceTask) this.rightChild).getLocalResult();
                } else {
                    result = Nodes.conc(this.op.getOutputShape(), (Node) ((SliceTask) this.leftChild).getLocalResult(), (Node) ((SliceTask) this.rightChild).getLocalResult());
                }
                setLocalResult(isRoot() ? doTruncate(result) : result);
                this.completed = true;
            }
            if (this.targetSize >= 0 && !isRoot() && isLeftCompleted(this.targetOffset + this.targetSize)) {
                cancelLaterNodes();
            }
            super.onCompletion(caller);
        }

        /* access modifiers changed from: protected */
        @Override // java.util.stream.AbstractShortCircuitTask
        public void cancel() {
            super.cancel();
            if (this.completed) {
                setLocalResult(getEmptyResult());
            }
        }

        private Node<P_OUT> doTruncate(Node<P_OUT> input) {
            return input.truncate(this.targetOffset, this.targetSize >= 0 ? Math.min(input.count(), this.targetOffset + this.targetSize) : this.thisNodeSize, this.generator);
        }

        private boolean isLeftCompleted(long target) {
            SliceTask<P_IN, P_OUT> left;
            long size = this.completed ? this.thisNodeSize : completedSize(target);
            if (size >= target) {
                return true;
            }
            SliceTask<P_IN, P_OUT> node = this;
            for (SliceTask<P_IN, P_OUT> parent = (SliceTask) getParent(); parent != null; parent = (SliceTask) parent.getParent()) {
                if (node == parent.rightChild && (left = (SliceTask) parent.leftChild) != null) {
                    size += left.completedSize(target);
                    if (size >= target) {
                        return true;
                    }
                }
                node = parent;
            }
            if (size >= target) {
                return true;
            }
            return false;
        }

        private long completedSize(long target) {
            if (this.completed) {
                return this.thisNodeSize;
            }
            SliceTask<P_IN, P_OUT> left = (SliceTask) this.leftChild;
            SliceTask<P_IN, P_OUT> right = (SliceTask) this.rightChild;
            if (left == null || right == null) {
                return this.thisNodeSize;
            }
            long leftSize = left.completedSize(target);
            return leftSize >= target ? leftSize : right.completedSize(target) + leftSize;
        }
    }
}
