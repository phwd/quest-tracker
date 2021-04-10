package java.util.stream;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.MatchOps;
import java.util.stream.Sink;

final class MatchOps {
    private MatchOps() {
    }

    /* access modifiers changed from: package-private */
    public enum MatchKind {
        ANY(true, true),
        ALL(false, false),
        NONE(true, false);
        
        private final boolean shortCircuitResult;
        private final boolean stopOnPredicateMatches;

        private MatchKind(boolean stopOnPredicateMatches2, boolean shortCircuitResult2) {
            this.stopOnPredicateMatches = stopOnPredicateMatches2;
            this.shortCircuitResult = shortCircuitResult2;
        }
    }

    public static <T> TerminalOp<T, Boolean> makeRef(Predicate<? super T> predicate, MatchKind matchKind) {
        Objects.requireNonNull(predicate);
        Objects.requireNonNull(matchKind);
        return new MatchOp(StreamShape.REFERENCE, matchKind, new Supplier(predicate) {
            /* class java.util.stream.$$Lambda$MatchOps$_LtFSpSMfVwoPv8p_1cMGGcaHA */
            private final /* synthetic */ Predicate f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                return MatchOps.lambda$makeRef$0(MatchOps.MatchKind.this, this.f$1);
            }
        });
    }

    static /* synthetic */ BooleanTerminalSink lambda$makeRef$0(MatchKind matchKind, Predicate predicate) {
        return new BooleanTerminalSink<T>(predicate) {
            /* class java.util.stream.MatchOps.AnonymousClass1MatchSink */
            final /* synthetic */ Predicate val$predicate;

            /* JADX WARN: Incorrect args count in method signature: ()V */
            {
                this.val$predicate = r2;
            }

            @Override // java.util.function.Consumer
            public void accept(T t) {
                if (!this.stop && this.val$predicate.test(t) == MatchKind.this.stopOnPredicateMatches) {
                    this.stop = true;
                    this.value = MatchKind.this.shortCircuitResult;
                }
            }
        };
    }

    public static TerminalOp<Integer, Boolean> makeInt(IntPredicate predicate, MatchKind matchKind) {
        Objects.requireNonNull(predicate);
        Objects.requireNonNull(matchKind);
        return new MatchOp(StreamShape.INT_VALUE, matchKind, new Supplier(predicate) {
            /* class java.util.stream.$$Lambda$MatchOps$emK14UX33I4nqH2o5l7hLEVAy8 */
            private final /* synthetic */ IntPredicate f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                return MatchOps.lambda$makeInt$1(MatchOps.MatchKind.this, this.f$1);
            }
        });
    }

    static /* synthetic */ BooleanTerminalSink lambda$makeInt$1(MatchKind matchKind, IntPredicate predicate) {
        return new Sink.OfInt(predicate) {
            /* class java.util.stream.MatchOps.AnonymousClass2MatchSink */
            final /* synthetic */ IntPredicate val$predicate;

            /* JADX WARN: Incorrect args count in method signature: ()V */
            {
                this.val$predicate = r2;
            }

            @Override // java.util.stream.Sink.OfInt, java.util.function.IntConsumer, java.util.stream.Sink
            public void accept(int t) {
                if (!this.stop && this.val$predicate.test(t) == MatchKind.this.stopOnPredicateMatches) {
                    this.stop = true;
                    this.value = MatchKind.this.shortCircuitResult;
                }
            }
        };
    }

    public static TerminalOp<Long, Boolean> makeLong(LongPredicate predicate, MatchKind matchKind) {
        Objects.requireNonNull(predicate);
        Objects.requireNonNull(matchKind);
        return new MatchOp(StreamShape.LONG_VALUE, matchKind, new Supplier(predicate) {
            /* class java.util.stream.$$Lambda$MatchOps$kCrOdGmndcbZklBaJ6Z4blQ1F5M */
            private final /* synthetic */ LongPredicate f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                return MatchOps.lambda$makeLong$2(MatchOps.MatchKind.this, this.f$1);
            }
        });
    }

    static /* synthetic */ BooleanTerminalSink lambda$makeLong$2(MatchKind matchKind, LongPredicate predicate) {
        return new Sink.OfLong(predicate) {
            /* class java.util.stream.MatchOps.AnonymousClass3MatchSink */
            final /* synthetic */ LongPredicate val$predicate;

            /* JADX WARN: Incorrect args count in method signature: ()V */
            {
                this.val$predicate = r2;
            }

            @Override // java.util.stream.Sink.OfLong, java.util.stream.Sink, java.util.function.LongConsumer
            public void accept(long t) {
                if (!this.stop && this.val$predicate.test(t) == MatchKind.this.stopOnPredicateMatches) {
                    this.stop = true;
                    this.value = MatchKind.this.shortCircuitResult;
                }
            }
        };
    }

    public static TerminalOp<Double, Boolean> makeDouble(DoublePredicate predicate, MatchKind matchKind) {
        Objects.requireNonNull(predicate);
        Objects.requireNonNull(matchKind);
        return new MatchOp(StreamShape.DOUBLE_VALUE, matchKind, new Supplier(predicate) {
            /* class java.util.stream.$$Lambda$MatchOps$VXR1J72V6WzQCN3NkesXDVJ1uc */
            private final /* synthetic */ DoublePredicate f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                return MatchOps.lambda$makeDouble$3(MatchOps.MatchKind.this, this.f$1);
            }
        });
    }

    static /* synthetic */ BooleanTerminalSink lambda$makeDouble$3(MatchKind matchKind, DoublePredicate predicate) {
        return new Sink.OfDouble(predicate) {
            /* class java.util.stream.MatchOps.AnonymousClass4MatchSink */
            final /* synthetic */ DoublePredicate val$predicate;

            /* JADX WARN: Incorrect args count in method signature: ()V */
            {
                this.val$predicate = r2;
            }

            @Override // java.util.stream.Sink.OfDouble, java.util.function.DoubleConsumer, java.util.stream.Sink
            public void accept(double t) {
                if (!this.stop && this.val$predicate.test(t) == MatchKind.this.stopOnPredicateMatches) {
                    this.stop = true;
                    this.value = MatchKind.this.shortCircuitResult;
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public static final class MatchOp<T> implements TerminalOp<T, Boolean> {
        private final StreamShape inputShape;
        final MatchKind matchKind;
        final Supplier<BooleanTerminalSink<T>> sinkSupplier;

        MatchOp(StreamShape shape, MatchKind matchKind2, Supplier<BooleanTerminalSink<T>> sinkSupplier2) {
            this.inputShape = shape;
            this.matchKind = matchKind2;
            this.sinkSupplier = sinkSupplier2;
        }

        @Override // java.util.stream.TerminalOp
        public int getOpFlags() {
            return StreamOpFlag.IS_SHORT_CIRCUIT | StreamOpFlag.NOT_ORDERED;
        }

        @Override // java.util.stream.TerminalOp
        public StreamShape inputShape() {
            return this.inputShape;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.util.Spliterator<S> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.stream.TerminalOp
        public <S> Boolean evaluateSequential(PipelineHelper<T> helper, Spliterator<S> spliterator) {
            return Boolean.valueOf(((BooleanTerminalSink) helper.wrapAndCopyInto(this.sinkSupplier.get(), spliterator)).getAndClearState());
        }

        @Override // java.util.stream.TerminalOp
        public <S> Boolean evaluateParallel(PipelineHelper<T> helper, Spliterator<S> spliterator) {
            return (Boolean) new MatchTask(this, helper, spliterator).invoke();
        }
    }

    /* access modifiers changed from: private */
    public static abstract class BooleanTerminalSink<T> implements Sink<T> {
        boolean stop;
        boolean value;

        BooleanTerminalSink(MatchKind matchKind) {
            this.value = !matchKind.shortCircuitResult;
        }

        public boolean getAndClearState() {
            return this.value;
        }

        @Override // java.util.stream.Sink
        public boolean cancellationRequested() {
            return this.stop;
        }
    }

    /* access modifiers changed from: private */
    public static final class MatchTask<P_IN, P_OUT> extends AbstractShortCircuitTask<P_IN, P_OUT, Boolean, MatchTask<P_IN, P_OUT>> {
        private final MatchOp<P_OUT> op;

        MatchTask(MatchOp<P_OUT> op2, PipelineHelper<P_OUT> helper, Spliterator<P_IN> spliterator) {
            super(helper, spliterator);
            this.op = op2;
        }

        MatchTask(MatchTask<P_IN, P_OUT> parent, Spliterator<P_IN> spliterator) {
            super(parent, spliterator);
            this.op = parent.op;
        }

        /* access modifiers changed from: protected */
        @Override // java.util.stream.AbstractTask
        public MatchTask<P_IN, P_OUT> makeChild(Spliterator<P_IN> spliterator) {
            return new MatchTask<>(this, spliterator);
        }

        /* access modifiers changed from: protected */
        @Override // java.util.stream.AbstractTask
        public Boolean doLeaf() {
            boolean b = ((BooleanTerminalSink) this.helper.wrapAndCopyInto(this.op.sinkSupplier.get(), this.spliterator)).getAndClearState();
            if (b != this.op.matchKind.shortCircuitResult) {
                return null;
            }
            shortCircuit(Boolean.valueOf(b));
            return null;
        }

        /* access modifiers changed from: protected */
        @Override // java.util.stream.AbstractShortCircuitTask
        public Boolean getEmptyResult() {
            return Boolean.valueOf(!this.op.matchKind.shortCircuitResult);
        }
    }
}
