package java.util.stream;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.Spliterator;
import java.util.concurrent.CountedCompleter;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Sink;

final class FindOps {
    private FindOps() {
    }

    public static <T> TerminalOp<T, Optional<T>> makeRef(boolean mustFindFirst) {
        return new FindOp(mustFindFirst, StreamShape.REFERENCE, Optional.empty(), $$Lambda$bjSXRjZ5UYwAzkWXPKwqbJ9BRQ.INSTANCE, $$Lambda$opQ7JxjVCJzqzgTxGU3LVtqC7is.INSTANCE);
    }

    public static TerminalOp<Integer, OptionalInt> makeInt(boolean mustFindFirst) {
        return new FindOp(mustFindFirst, StreamShape.INT_VALUE, OptionalInt.empty(), $$Lambda$timJ2_RnT5GwsTSax4Q0EMpi4pc.INSTANCE, $$Lambda$mpgi0fNdNmnu9LkjGowG335UgGc.INSTANCE);
    }

    public static TerminalOp<Long, OptionalLong> makeLong(boolean mustFindFirst) {
        return new FindOp(mustFindFirst, StreamShape.LONG_VALUE, OptionalLong.empty(), $$Lambda$XcCQq8gYss3OrVBeBIbyvBZpOz8.INSTANCE, $$Lambda$YpedFjT304pmSbvYSkjP1adjrAo.INSTANCE);
    }

    public static TerminalOp<Double, OptionalDouble> makeDouble(boolean mustFindFirst) {
        return new FindOp(mustFindFirst, StreamShape.DOUBLE_VALUE, OptionalDouble.empty(), $$Lambda$yrGzfUbU_IPNM4mz8V8FlMUHCw4.INSTANCE, $$Lambda$l1vHMFuOMPAI8WfDQT6zNBh_B7U.INSTANCE);
    }

    /* access modifiers changed from: private */
    public static final class FindOp<T, O> implements TerminalOp<T, O> {
        final O emptyValue;
        final boolean mustFindFirst;
        final Predicate<O> presentPredicate;
        private final StreamShape shape;
        final Supplier<TerminalSink<T, O>> sinkSupplier;

        FindOp(boolean mustFindFirst2, StreamShape shape2, O emptyValue2, Predicate<O> presentPredicate2, Supplier<TerminalSink<T, O>> sinkSupplier2) {
            this.mustFindFirst = mustFindFirst2;
            this.shape = shape2;
            this.emptyValue = emptyValue2;
            this.presentPredicate = presentPredicate2;
            this.sinkSupplier = sinkSupplier2;
        }

        @Override // java.util.stream.TerminalOp
        public int getOpFlags() {
            return StreamOpFlag.IS_SHORT_CIRCUIT | (this.mustFindFirst ? 0 : StreamOpFlag.NOT_ORDERED);
        }

        @Override // java.util.stream.TerminalOp
        public StreamShape inputShape() {
            return this.shape;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.util.Spliterator<S> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.stream.TerminalOp
        public <S> O evaluateSequential(PipelineHelper<T> helper, Spliterator<S> spliterator) {
            O result = (O) ((TerminalSink) helper.wrapAndCopyInto(this.sinkSupplier.get(), spliterator)).get();
            return result != null ? result : this.emptyValue;
        }

        @Override // java.util.stream.TerminalOp
        public <P_IN> O evaluateParallel(PipelineHelper<T> helper, Spliterator<P_IN> spliterator) {
            return (O) new FindTask(this, helper, spliterator).invoke();
        }
    }

    private static abstract class FindSink<T, O> implements TerminalSink<T, O> {
        boolean hasValue;
        T value;

        FindSink() {
        }

        @Override // java.util.function.Consumer
        public void accept(T value2) {
            if (!this.hasValue) {
                this.hasValue = true;
                this.value = value2;
            }
        }

        @Override // java.util.stream.Sink
        public boolean cancellationRequested() {
            return this.hasValue;
        }

        static final class OfRef<T> extends FindSink<T, Optional<T>> {
            OfRef() {
            }

            @Override // java.util.function.Supplier
            public Optional<T> get() {
                if (this.hasValue) {
                    return Optional.of(this.value);
                }
                return null;
            }
        }

        static final class OfInt extends FindSink<Integer, OptionalInt> implements Sink.OfInt {
            OfInt() {
            }

            @Override // java.util.stream.Sink.OfInt
            public /* bridge */ /* synthetic */ void accept(Integer num) {
                super.accept((Object) num);
            }

            /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.util.stream.FindOps$FindSink$OfInt */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.stream.Sink.OfInt, java.util.function.IntConsumer, java.util.stream.Sink
            public void accept(int value) {
                accept((Object) Integer.valueOf(value));
            }

            @Override // java.util.function.Supplier
            public OptionalInt get() {
                if (this.hasValue) {
                    return OptionalInt.of(((Integer) this.value).intValue());
                }
                return null;
            }
        }

        static final class OfLong extends FindSink<Long, OptionalLong> implements Sink.OfLong {
            OfLong() {
            }

            @Override // java.util.stream.Sink.OfLong
            public /* bridge */ /* synthetic */ void accept(Long l) {
                super.accept((Object) l);
            }

            /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.util.stream.FindOps$FindSink$OfLong */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.stream.Sink.OfLong, java.util.stream.Sink, java.util.function.LongConsumer
            public void accept(long value) {
                accept((Object) Long.valueOf(value));
            }

            @Override // java.util.function.Supplier
            public OptionalLong get() {
                if (this.hasValue) {
                    return OptionalLong.of(((Long) this.value).longValue());
                }
                return null;
            }
        }

        static final class OfDouble extends FindSink<Double, OptionalDouble> implements Sink.OfDouble {
            OfDouble() {
            }

            @Override // java.util.stream.Sink.OfDouble
            public /* bridge */ /* synthetic */ void accept(Double d) {
                super.accept((Object) d);
            }

            /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.util.stream.FindOps$FindSink$OfDouble */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.stream.Sink.OfDouble, java.util.function.DoubleConsumer, java.util.stream.Sink
            public void accept(double value) {
                accept((Object) Double.valueOf(value));
            }

            @Override // java.util.function.Supplier
            public OptionalDouble get() {
                if (this.hasValue) {
                    return OptionalDouble.of(((Double) this.value).doubleValue());
                }
                return null;
            }
        }
    }

    private static final class FindTask<P_IN, P_OUT, O> extends AbstractShortCircuitTask<P_IN, P_OUT, O, FindTask<P_IN, P_OUT, O>> {
        private final FindOp<P_OUT, O> op;

        FindTask(FindOp<P_OUT, O> op2, PipelineHelper<P_OUT> helper, Spliterator<P_IN> spliterator) {
            super(helper, spliterator);
            this.op = op2;
        }

        FindTask(FindTask<P_IN, P_OUT, O> parent, Spliterator<P_IN> spliterator) {
            super(parent, spliterator);
            this.op = parent.op;
        }

        /* access modifiers changed from: protected */
        @Override // java.util.stream.AbstractTask
        public FindTask<P_IN, P_OUT, O> makeChild(Spliterator<P_IN> spliterator) {
            return new FindTask<>(this, spliterator);
        }

        /* access modifiers changed from: protected */
        @Override // java.util.stream.AbstractShortCircuitTask
        public O getEmptyResult() {
            return this.op.emptyValue;
        }

        private void foundResult(O answer) {
            if (isLeftmostNode()) {
                shortCircuit(answer);
            } else {
                cancelLaterNodes();
            }
        }

        /* access modifiers changed from: protected */
        @Override // java.util.stream.AbstractTask
        public O doLeaf() {
            O result = (O) ((TerminalSink) this.helper.wrapAndCopyInto(this.op.sinkSupplier.get(), this.spliterator)).get();
            if (!this.op.mustFindFirst) {
                if (result != null) {
                    shortCircuit(result);
                }
                return null;
            } else if (result == null) {
                return null;
            } else {
                foundResult(result);
                return result;
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.util.stream.FindOps$FindTask<P_IN, P_OUT, O> */
        /* JADX DEBUG: Multi-variable search result rejected for r3v1, resolved type: java.util.function.Predicate<O> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.CountedCompleter, java.util.stream.AbstractTask
        public void onCompletion(CountedCompleter<?> caller) {
            if (this.op.mustFindFirst) {
                FindTask<P_IN, P_OUT, O> child = (FindTask) this.leftChild;
                FindTask<P_IN, P_OUT, O> p = null;
                while (true) {
                    if (child != p) {
                        Object localResult = child.getLocalResult();
                        if (localResult != null && this.op.presentPredicate.test(localResult)) {
                            setLocalResult(localResult);
                            foundResult(localResult);
                            break;
                        }
                        p = child;
                        child = (FindTask) this.rightChild;
                    } else {
                        break;
                    }
                }
            }
            super.onCompletion(caller);
        }
    }
}
