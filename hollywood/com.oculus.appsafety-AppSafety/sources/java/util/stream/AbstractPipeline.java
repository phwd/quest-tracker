package java.util.stream;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.BaseStream;
import java.util.stream.Node;

public abstract class AbstractPipeline<E_IN, E_OUT, S extends BaseStream<E_OUT, S>> extends PipelineHelper<E_OUT> implements BaseStream<E_OUT, S> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String MSG_CONSUMED = "source already consumed or closed";
    private static final String MSG_STREAM_LINKED = "stream has already been operated upon or closed";
    private int combinedFlags;
    private int depth;
    private boolean linkedOrConsumed;
    private AbstractPipeline nextStage;
    private boolean parallel;
    private final AbstractPipeline previousStage;
    private boolean sourceAnyStateful;
    private Runnable sourceCloseAction;
    protected final int sourceOrOpFlags;
    private Spliterator<?> sourceSpliterator;
    private final AbstractPipeline sourceStage;
    private Supplier<? extends Spliterator<?>> sourceSupplier;

    public abstract <P_IN> Node<E_OUT> evaluateToNode(PipelineHelper<E_OUT> pipelineHelper, Spliterator<P_IN> spliterator, boolean z, IntFunction<E_OUT[]> intFunction);

    public abstract void forEachWithCancel(Spliterator<E_OUT> spliterator, Sink<E_OUT> sink);

    public abstract StreamShape getOutputShape();

    public abstract Spliterator<E_OUT> lazySpliterator(Supplier<? extends Spliterator<E_OUT>> supplier);

    @Override // java.util.stream.PipelineHelper
    public abstract Node.Builder<E_OUT> makeNodeBuilder(long j, IntFunction<E_OUT[]> intFunction);

    public abstract boolean opIsStateful();

    public abstract Sink<E_IN> opWrapSink(int i, Sink<E_OUT> sink);

    public abstract <P_IN> Spliterator<E_OUT> wrap(PipelineHelper<E_OUT> pipelineHelper, Supplier<Spliterator<P_IN>> supplier, boolean z);

    AbstractPipeline(Supplier<? extends Spliterator<?>> source, int sourceFlags, boolean parallel2) {
        this.previousStage = null;
        this.sourceSupplier = source;
        this.sourceStage = this;
        this.sourceOrOpFlags = StreamOpFlag.STREAM_MASK & sourceFlags;
        this.combinedFlags = (~(this.sourceOrOpFlags << 1)) & StreamOpFlag.INITIAL_OPS_VALUE;
        this.depth = 0;
        this.parallel = parallel2;
    }

    AbstractPipeline(Spliterator<?> source, int sourceFlags, boolean parallel2) {
        this.previousStage = null;
        this.sourceSpliterator = source;
        this.sourceStage = this;
        this.sourceOrOpFlags = StreamOpFlag.STREAM_MASK & sourceFlags;
        this.combinedFlags = (~(this.sourceOrOpFlags << 1)) & StreamOpFlag.INITIAL_OPS_VALUE;
        this.depth = 0;
        this.parallel = parallel2;
    }

    AbstractPipeline(AbstractPipeline<?, E_IN, ?> previousStage2, int opFlags) {
        if (!previousStage2.linkedOrConsumed) {
            previousStage2.linkedOrConsumed = true;
            previousStage2.nextStage = this;
            this.previousStage = previousStage2;
            this.sourceOrOpFlags = StreamOpFlag.OP_MASK & opFlags;
            this.combinedFlags = StreamOpFlag.combineOpFlags(opFlags, previousStage2.combinedFlags);
            this.sourceStage = previousStage2.sourceStage;
            if (opIsStateful()) {
                this.sourceStage.sourceAnyStateful = true;
            }
            this.depth = previousStage2.depth + 1;
            return;
        }
        throw new IllegalStateException(MSG_STREAM_LINKED);
    }

    /* access modifiers changed from: package-private */
    public final <R> R evaluate(TerminalOp<E_OUT, R> terminalOp) {
        if (!this.linkedOrConsumed) {
            this.linkedOrConsumed = true;
            if (isParallel()) {
                return terminalOp.evaluateParallel(this, sourceSpliterator(terminalOp.getOpFlags()));
            }
            return terminalOp.evaluateSequential(this, sourceSpliterator(terminalOp.getOpFlags()));
        }
        throw new IllegalStateException(MSG_STREAM_LINKED);
    }

    public final Node<E_OUT> evaluateToArrayNode(IntFunction<E_OUT[]> generator) {
        if (!this.linkedOrConsumed) {
            this.linkedOrConsumed = true;
            if (!isParallel() || this.previousStage == null || !opIsStateful()) {
                return evaluate(sourceSpliterator(0), true, generator);
            }
            this.depth = 0;
            AbstractPipeline abstractPipeline = this.previousStage;
            return opEvaluateParallel(abstractPipeline, abstractPipeline.sourceSpliterator(0), generator);
        }
        throw new IllegalStateException(MSG_STREAM_LINKED);
    }

    /* access modifiers changed from: package-private */
    public final Spliterator<E_OUT> sourceStageSpliterator() {
        AbstractPipeline<E_IN, E_OUT, S> abstractPipeline = this.sourceStage;
        if (this != abstractPipeline) {
            throw new IllegalStateException();
        } else if (!this.linkedOrConsumed) {
            this.linkedOrConsumed = true;
            if (abstractPipeline.sourceSpliterator != null) {
                Spliterator<E_OUT> s = (Spliterator<E_OUT>) abstractPipeline.sourceSpliterator;
                abstractPipeline.sourceSpliterator = null;
                return s;
            }
            Supplier<? extends Spliterator<?>> supplier = abstractPipeline.sourceSupplier;
            if (supplier != null) {
                Spliterator<E_OUT> s2 = (Spliterator) supplier.get();
                this.sourceStage.sourceSupplier = null;
                return s2;
            }
            throw new IllegalStateException(MSG_CONSUMED);
        } else {
            throw new IllegalStateException(MSG_STREAM_LINKED);
        }
    }

    @Override // java.util.stream.BaseStream
    public final S sequential() {
        this.sourceStage.parallel = false;
        return this;
    }

    @Override // java.util.stream.BaseStream
    public final S parallel() {
        this.sourceStage.parallel = true;
        return this;
    }

    @Override // java.util.stream.BaseStream, java.lang.AutoCloseable
    public void close() {
        this.linkedOrConsumed = true;
        this.sourceSupplier = null;
        this.sourceSpliterator = null;
        AbstractPipeline abstractPipeline = this.sourceStage;
        if (abstractPipeline.sourceCloseAction != null) {
            Runnable closeAction = abstractPipeline.sourceCloseAction;
            abstractPipeline.sourceCloseAction = null;
            closeAction.run();
        }
    }

    @Override // java.util.stream.BaseStream
    public S onClose(Runnable closeHandler) {
        Runnable runnable;
        AbstractPipeline abstractPipeline = this.sourceStage;
        Runnable existingHandler = abstractPipeline.sourceCloseAction;
        if (existingHandler == null) {
            runnable = closeHandler;
        } else {
            runnable = Streams.composeWithExceptions(existingHandler, closeHandler);
        }
        abstractPipeline.sourceCloseAction = runnable;
        return this;
    }

    @Override // java.util.stream.BaseStream
    public Spliterator<E_OUT> spliterator() {
        if (!this.linkedOrConsumed) {
            this.linkedOrConsumed = true;
            AbstractPipeline<E_IN, E_OUT, S> abstractPipeline = this.sourceStage;
            if (this != abstractPipeline) {
                return wrap(this, new Supplier() {
                    /* class java.util.stream.$$Lambda$AbstractPipeline$ImXhRLJT29W8lJFXpTT_PieAotg */

                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return AbstractPipeline.this.lambda$spliterator$0$AbstractPipeline();
                    }
                }, isParallel());
            }
            if (abstractPipeline.sourceSpliterator != null) {
                Spliterator<E_OUT> s = (Spliterator<E_OUT>) abstractPipeline.sourceSpliterator;
                abstractPipeline.sourceSpliterator = null;
                return s;
            } else if (abstractPipeline.sourceSupplier != null) {
                Supplier<? extends Spliterator<?>> supplier = abstractPipeline.sourceSupplier;
                abstractPipeline.sourceSupplier = null;
                return lazySpliterator(supplier);
            } else {
                throw new IllegalStateException(MSG_CONSUMED);
            }
        } else {
            throw new IllegalStateException(MSG_STREAM_LINKED);
        }
    }

    public /* synthetic */ Spliterator lambda$spliterator$0$AbstractPipeline() {
        return sourceSpliterator(0);
    }

    @Override // java.util.stream.BaseStream
    public final boolean isParallel() {
        return this.sourceStage.parallel;
    }

    public final int getStreamFlags() {
        return StreamOpFlag.toStreamFlags(this.combinedFlags);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:30:0x0061 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:28:0x0072 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: java.util.Spliterator<?> */
    private Spliterator<?> sourceSpliterator(int terminalFlags) {
        Spliterator spliterator;
        int i;
        AbstractPipeline abstractPipeline = this.sourceStage;
        if (abstractPipeline.sourceSpliterator != null) {
            abstractPipeline.sourceSpliterator = null;
            spliterator = (Spliterator<P_IN>) abstractPipeline.sourceSpliterator;
        } else {
            Supplier<? extends Spliterator<?>> supplier = abstractPipeline.sourceSupplier;
            if (supplier != null) {
                this.sourceStage.sourceSupplier = null;
                spliterator = (Spliterator<P_IN>) ((Spliterator) supplier.get());
            } else {
                throw new IllegalStateException(MSG_CONSUMED);
            }
        }
        if (isParallel()) {
            AbstractPipeline abstractPipeline2 = this.sourceStage;
            if (abstractPipeline2.sourceAnyStateful) {
                int depth2 = 1;
                AbstractPipeline u = this.sourceStage;
                spliterator = spliterator;
                AbstractPipeline p = abstractPipeline2.nextStage;
                while (u != this) {
                    int thisOpFlags = p.sourceOrOpFlags;
                    if (p.opIsStateful()) {
                        depth2 = 0;
                        if (StreamOpFlag.SHORT_CIRCUIT.isKnown(thisOpFlags)) {
                            thisOpFlags &= ~StreamOpFlag.IS_SHORT_CIRCUIT;
                        }
                        spliterator = (Spliterator<E_OUT>) p.opEvaluateParallelLazy(u, spliterator);
                        if (spliterator.hasCharacteristics(64)) {
                            i = ((~StreamOpFlag.NOT_SIZED) & thisOpFlags) | StreamOpFlag.IS_SIZED;
                        } else {
                            i = ((~StreamOpFlag.IS_SIZED) & thisOpFlags) | StreamOpFlag.NOT_SIZED;
                        }
                        thisOpFlags = i;
                    }
                    p.depth = depth2;
                    p.combinedFlags = StreamOpFlag.combineOpFlags(thisOpFlags, u.combinedFlags);
                    u = p;
                    depth2++;
                    spliterator = spliterator;
                    p = p.nextStage;
                }
            }
        }
        if (terminalFlags != 0) {
            this.combinedFlags = StreamOpFlag.combineOpFlags(terminalFlags, this.combinedFlags);
        }
        return spliterator;
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.stream.PipelineHelper
    public final StreamShape getSourceShape() {
        AbstractPipeline p = this;
        while (p.depth > 0) {
            p = p.previousStage;
        }
        return p.getOutputShape();
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.stream.PipelineHelper
    public final <P_IN> long exactOutputSizeIfKnown(Spliterator<P_IN> spliterator) {
        if (StreamOpFlag.SIZED.isKnown(getStreamAndOpFlags())) {
            return spliterator.getExactSizeIfKnown();
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.stream.PipelineHelper
    public final <P_IN, S extends Sink<E_OUT>> S wrapAndCopyInto(S sink, Spliterator<P_IN> spliterator) {
        copyInto(wrapSink((Sink) Objects.requireNonNull(sink)), spliterator);
        return sink;
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.stream.PipelineHelper
    public final <P_IN> void copyInto(Sink<P_IN> wrappedSink, Spliterator<P_IN> spliterator) {
        Objects.requireNonNull(wrappedSink);
        if (!StreamOpFlag.SHORT_CIRCUIT.isKnown(getStreamAndOpFlags())) {
            wrappedSink.begin(spliterator.getExactSizeIfKnown());
            spliterator.forEachRemaining(wrappedSink);
            wrappedSink.end();
            return;
        }
        copyIntoWithCancel(wrappedSink, spliterator);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.util.stream.Sink<P_IN> */
    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: java.util.Spliterator<P_IN> */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: package-private */
    @Override // java.util.stream.PipelineHelper
    public final <P_IN> void copyIntoWithCancel(Sink<P_IN> wrappedSink, Spliterator<P_IN> spliterator) {
        AbstractPipeline p = this;
        while (p.depth > 0) {
            p = p.previousStage;
        }
        wrappedSink.begin(spliterator.getExactSizeIfKnown());
        p.forEachWithCancel(spliterator, wrappedSink);
        wrappedSink.end();
    }

    @Override // java.util.stream.PipelineHelper
    public final int getStreamAndOpFlags() {
        return this.combinedFlags;
    }

    /* access modifiers changed from: package-private */
    public final boolean isOrdered() {
        return StreamOpFlag.ORDERED.isKnown(this.combinedFlags);
    }

    @Override // java.util.stream.PipelineHelper
    public final <P_IN> Sink<P_IN> wrapSink(Sink<E_OUT> sink) {
        Objects.requireNonNull(sink);
        AbstractPipeline p = this;
        Consumer consumer = sink;
        while (p.depth > 0) {
            p = p.previousStage;
            consumer = (Sink<E_IN>) p.opWrapSink(p.previousStage.combinedFlags, (Sink) consumer);
        }
        return (Sink<P_IN>) consumer;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.util.Spliterator<P_IN> */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: package-private */
    @Override // java.util.stream.PipelineHelper
    public final <P_IN> Spliterator<E_OUT> wrapSpliterator(Spliterator<P_IN> sourceSpliterator2) {
        if (this.depth == 0) {
            return sourceSpliterator2;
        }
        return wrap(this, new Supplier() {
            /* class java.util.stream.$$Lambda$AbstractPipeline$i18ybop3uEZwLQyKh7zmnuTXiFw */

            @Override // java.util.function.Supplier
            public final Object get() {
                return AbstractPipeline.lambda$wrapSpliterator$1(Spliterator.this);
            }
        }, isParallel());
    }

    static /* synthetic */ Spliterator lambda$wrapSpliterator$1(Spliterator sourceSpliterator2) {
        return sourceSpliterator2;
    }

    @Override // java.util.stream.PipelineHelper
    public final <P_IN> Node<E_OUT> evaluate(Spliterator<P_IN> spliterator, boolean flatten, IntFunction<E_OUT[]> generator) {
        if (isParallel()) {
            return evaluateToNode(this, spliterator, flatten, generator);
        }
        return ((Node.Builder) wrapAndCopyInto(makeNodeBuilder(exactOutputSizeIfKnown(spliterator), generator), spliterator)).build();
    }

    public <P_IN> Node<E_OUT> opEvaluateParallel(PipelineHelper<E_OUT> pipelineHelper, Spliterator<P_IN> spliterator, IntFunction<E_OUT[]> intFunction) {
        throw new UnsupportedOperationException("Parallel evaluation is not supported");
    }

    static /* synthetic */ Object[] lambda$opEvaluateParallelLazy$2(int i) {
        return new Object[i];
    }

    public <P_IN> Spliterator<E_OUT> opEvaluateParallelLazy(PipelineHelper<E_OUT> helper, Spliterator<P_IN> spliterator) {
        return opEvaluateParallel(helper, spliterator, $$Lambda$AbstractPipeline$wEsmW74nQaCA9FYTjN7e9qkJaXE.INSTANCE).spliterator();
    }
}
