package java.util.stream;

import java.util.Objects;
import java.util.Spliterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountedCompleter;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;
import java.util.stream.Node;
import java.util.stream.Sink;

/* access modifiers changed from: package-private */
public final class ForEachOps {
    private ForEachOps() {
    }

    public static <T> TerminalOp<T, Void> makeRef(Consumer<? super T> action, boolean ordered) {
        Objects.requireNonNull(action);
        return new ForEachOp.OfRef(action, ordered);
    }

    public static TerminalOp<Integer, Void> makeInt(IntConsumer action, boolean ordered) {
        Objects.requireNonNull(action);
        return new ForEachOp.OfInt(action, ordered);
    }

    public static TerminalOp<Long, Void> makeLong(LongConsumer action, boolean ordered) {
        Objects.requireNonNull(action);
        return new ForEachOp.OfLong(action, ordered);
    }

    public static TerminalOp<Double, Void> makeDouble(DoubleConsumer action, boolean ordered) {
        Objects.requireNonNull(action);
        return new ForEachOp.OfDouble(action, ordered);
    }

    static abstract class ForEachOp<T> implements TerminalOp<T, Void>, TerminalSink<T, Void> {
        private final boolean ordered;

        protected ForEachOp(boolean ordered2) {
            this.ordered = ordered2;
        }

        @Override // java.util.stream.TerminalOp
        public int getOpFlags() {
            if (this.ordered) {
                return 0;
            }
            return StreamOpFlag.NOT_ORDERED;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.util.Spliterator<S> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.stream.TerminalOp
        public <S> Void evaluateSequential(PipelineHelper<T> helper, Spliterator<S> spliterator) {
            return ((ForEachOp) helper.wrapAndCopyInto(this, spliterator)).get();
        }

        @Override // java.util.stream.TerminalOp
        public <S> Void evaluateParallel(PipelineHelper<T> helper, Spliterator<S> spliterator) {
            if (this.ordered) {
                new ForEachOrderedTask(helper, spliterator, this).invoke();
                return null;
            }
            new ForEachTask(helper, spliterator, helper.wrapSink(this)).invoke();
            return null;
        }

        @Override // java.util.function.Supplier
        public Void get() {
            return null;
        }

        static final class OfRef<T> extends ForEachOp<T> {
            final Consumer<? super T> consumer;

            OfRef(Consumer<? super T> consumer2, boolean ordered) {
                super(ordered);
                this.consumer = consumer2;
            }

            @Override // java.util.function.Consumer
            public void accept(T t) {
                this.consumer.accept(t);
            }
        }

        static final class OfInt extends ForEachOp<Integer> implements Sink.OfInt {
            final IntConsumer consumer;

            OfInt(IntConsumer consumer2, boolean ordered) {
                super(ordered);
                this.consumer = consumer2;
            }

            @Override // java.util.stream.TerminalOp
            public StreamShape inputShape() {
                return StreamShape.INT_VALUE;
            }

            @Override // java.util.stream.Sink.OfInt, java.util.function.IntConsumer, java.util.stream.Sink
            public void accept(int t) {
                this.consumer.accept(t);
            }
        }

        static final class OfLong extends ForEachOp<Long> implements Sink.OfLong {
            final LongConsumer consumer;

            OfLong(LongConsumer consumer2, boolean ordered) {
                super(ordered);
                this.consumer = consumer2;
            }

            @Override // java.util.stream.TerminalOp
            public StreamShape inputShape() {
                return StreamShape.LONG_VALUE;
            }

            @Override // java.util.stream.Sink.OfLong, java.util.stream.Sink, java.util.function.LongConsumer
            public void accept(long t) {
                this.consumer.accept(t);
            }
        }

        /* access modifiers changed from: package-private */
        public static final class OfDouble extends ForEachOp<Double> implements Sink.OfDouble {
            final DoubleConsumer consumer;

            OfDouble(DoubleConsumer consumer2, boolean ordered) {
                super(ordered);
                this.consumer = consumer2;
            }

            @Override // java.util.stream.TerminalOp
            public StreamShape inputShape() {
                return StreamShape.DOUBLE_VALUE;
            }

            @Override // java.util.stream.Sink.OfDouble, java.util.function.DoubleConsumer, java.util.stream.Sink
            public void accept(double t) {
                this.consumer.accept(t);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class ForEachTask<S, T> extends CountedCompleter<Void> {
        private final PipelineHelper<T> helper;
        private final Sink<S> sink;
        private Spliterator<S> spliterator;
        private long targetSize;

        ForEachTask(PipelineHelper<T> helper2, Spliterator<S> spliterator2, Sink<S> sink2) {
            super(null);
            this.sink = sink2;
            this.helper = helper2;
            this.spliterator = spliterator2;
            this.targetSize = 0;
        }

        ForEachTask(ForEachTask<S, T> parent, Spliterator<S> spliterator2) {
            super(parent);
            this.spliterator = spliterator2;
            this.sink = parent.sink;
            this.targetSize = parent.targetSize;
            this.helper = parent.helper;
        }

        @Override // java.util.concurrent.CountedCompleter
        public void compute() {
            Spliterator<S> leftSplit;
            ForEachTask<S, T> taskToFork;
            Spliterator<S> rightSplit = this.spliterator;
            long sizeEstimate = rightSplit.estimateSize();
            long j = this.targetSize;
            long sizeThreshold = j;
            if (j == 0) {
                long suggestTargetSize = AbstractTask.suggestTargetSize(sizeEstimate);
                sizeThreshold = suggestTargetSize;
                this.targetSize = suggestTargetSize;
            }
            boolean isShortCircuit = StreamOpFlag.SHORT_CIRCUIT.isKnown(this.helper.getStreamAndOpFlags());
            boolean forkRight = false;
            Sink<S> taskSink = this.sink;
            ForEachTask<S, T> task = this;
            while (true) {
                if (isShortCircuit && taskSink.cancellationRequested()) {
                    break;
                } else if (sizeEstimate <= sizeThreshold || (leftSplit = rightSplit.trySplit()) == null) {
                    task.helper.copyInto(taskSink, rightSplit);
                } else {
                    ForEachTask<S, T> leftTask = new ForEachTask<>(task, leftSplit);
                    task.addToPendingCount(1);
                    if (forkRight) {
                        forkRight = false;
                        rightSplit = leftSplit;
                        taskToFork = task;
                        task = leftTask;
                    } else {
                        forkRight = true;
                        taskToFork = leftTask;
                    }
                    taskToFork.fork();
                    sizeEstimate = rightSplit.estimateSize();
                }
            }
            task.helper.copyInto(taskSink, rightSplit);
            task.spliterator = null;
            task.propagateCompletion();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class ForEachOrderedTask<S, T> extends CountedCompleter<Void> {
        private final Sink<T> action;
        private final ConcurrentHashMap<ForEachOrderedTask<S, T>, ForEachOrderedTask<S, T>> completionMap;
        private final PipelineHelper<T> helper;
        private final ForEachOrderedTask<S, T> leftPredecessor;
        private Node<T> node;
        private Spliterator<S> spliterator;
        private final long targetSize;

        protected ForEachOrderedTask(PipelineHelper<T> helper2, Spliterator<S> spliterator2, Sink<T> action2) {
            super(null);
            this.helper = helper2;
            this.spliterator = spliterator2;
            this.targetSize = AbstractTask.suggestTargetSize(spliterator2.estimateSize());
            this.completionMap = new ConcurrentHashMap<>(Math.max(16, AbstractTask.LEAF_TARGET << 1));
            this.action = action2;
            this.leftPredecessor = null;
        }

        ForEachOrderedTask(ForEachOrderedTask<S, T> parent, Spliterator<S> spliterator2, ForEachOrderedTask<S, T> leftPredecessor2) {
            super(parent);
            this.helper = parent.helper;
            this.spliterator = spliterator2;
            this.targetSize = parent.targetSize;
            this.completionMap = parent.completionMap;
            this.action = parent.action;
            this.leftPredecessor = leftPredecessor2;
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            doCompute(this);
        }

        private static <S, T> void doCompute(ForEachOrderedTask<S, T> task) {
            Spliterator<S> leftSplit;
            ForEachOrderedTask<S, T> taskToFork;
            Spliterator<S> rightSplit = ((ForEachOrderedTask) task).spliterator;
            long sizeThreshold = ((ForEachOrderedTask) task).targetSize;
            boolean forkRight = false;
            while (rightSplit.estimateSize() > sizeThreshold && (leftSplit = rightSplit.trySplit()) != null) {
                ForEachOrderedTask<S, T> leftChild = new ForEachOrderedTask<>(task, leftSplit, ((ForEachOrderedTask) task).leftPredecessor);
                ForEachOrderedTask<S, T> rightChild = new ForEachOrderedTask<>(task, rightSplit, leftChild);
                task.addToPendingCount(1);
                rightChild.addToPendingCount(1);
                ((ForEachOrderedTask) task).completionMap.put(leftChild, rightChild);
                if (((ForEachOrderedTask) task).leftPredecessor != null) {
                    leftChild.addToPendingCount(1);
                    if (((ForEachOrderedTask) task).completionMap.replace(((ForEachOrderedTask) task).leftPredecessor, task, leftChild)) {
                        task.addToPendingCount(-1);
                    } else {
                        leftChild.addToPendingCount(-1);
                    }
                }
                if (forkRight) {
                    forkRight = false;
                    rightSplit = leftSplit;
                    task = leftChild;
                    taskToFork = rightChild;
                } else {
                    forkRight = true;
                    task = rightChild;
                    taskToFork = leftChild;
                }
                taskToFork.fork();
            }
            if (task.getPendingCount() > 0) {
                IntFunction<T[]> generator = $$Lambda$ForEachOps$ForEachOrderedTask$XLqga2XPr4V7tlS8H12fizIno.INSTANCE;
                PipelineHelper<T> pipelineHelper = ((ForEachOrderedTask) task).helper;
                ((ForEachOrderedTask) task).node = ((Node.Builder) ((ForEachOrderedTask) task).helper.wrapAndCopyInto(pipelineHelper.makeNodeBuilder(pipelineHelper.exactOutputSizeIfKnown(rightSplit), generator), rightSplit)).build();
                ((ForEachOrderedTask) task).spliterator = null;
            }
            task.tryComplete();
        }

        static /* synthetic */ Object[] lambda$doCompute$0(int size) {
            return new Object[size];
        }

        @Override // java.util.concurrent.CountedCompleter
        public void onCompletion(CountedCompleter<?> countedCompleter) {
            Node<T> node2 = this.node;
            if (node2 != null) {
                node2.forEach(this.action);
                this.node = null;
            } else {
                Spliterator<S> spliterator2 = this.spliterator;
                if (spliterator2 != null) {
                    this.helper.wrapAndCopyInto(this.action, spliterator2);
                    this.spliterator = null;
                }
            }
            ForEachOrderedTask<S, T> leftDescendant = this.completionMap.remove(this);
            if (leftDescendant != null) {
                leftDescendant.tryComplete();
            }
        }
    }
}
