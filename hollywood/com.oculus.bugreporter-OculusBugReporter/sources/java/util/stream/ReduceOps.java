package java.util.stream;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.Spliterator;
import java.util.concurrent.CountedCompleter;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;
import java.util.stream.Collector;

/* access modifiers changed from: package-private */
public final class ReduceOps {

    /* access modifiers changed from: private */
    public interface AccumulatingSink<T, R, K extends AccumulatingSink<T, R, K>> extends TerminalSink<T, R> {
        void combine(K k);
    }

    private ReduceOps() {
    }

    public static <T, U> TerminalOp<T, U> makeRef(final U seed, final BiFunction<U, ? super T, U> reducer, final BinaryOperator<U> combiner) {
        Objects.requireNonNull(reducer);
        Objects.requireNonNull(combiner);
        return new ReduceOp<T, U, AnonymousClass1ReducingSink>(StreamShape.REFERENCE) {
            /* class java.util.stream.ReduceOps.AnonymousClass1 */

            @Override // java.util.stream.ReduceOps.ReduceOp
            public AnonymousClass1ReducingSink makeSink() {
                return new AccumulatingSink<T, U, AnonymousClass1ReducingSink>(reducer, combiner) {
                    /* class java.util.stream.ReduceOps.AnonymousClass1ReducingSink */
                    final /* synthetic */ BinaryOperator val$combiner;
                    final /* synthetic */ BiFunction val$reducer;

                    /* JADX WARN: Incorrect args count in method signature: ()V */
                    {
                        this.val$reducer = r2;
                        this.val$combiner = r3;
                    }

                    @Override // java.util.stream.Sink
                    public void begin(long size) {
                        this.state = Object.this;
                    }

                    @Override // java.util.function.Consumer
                    public void accept(T t) {
                        this.state = this.val$reducer.apply(this.state, t);
                    }

                    public void combine(AnonymousClass1ReducingSink other) {
                        this.state = this.val$combiner.apply(this.state, other.state);
                    }
                };
            }
        };
    }

    public static <T> TerminalOp<T, Optional<T>> makeRef(final BinaryOperator<T> operator) {
        Objects.requireNonNull(operator);
        return new ReduceOp<T, Optional<T>, AnonymousClass2ReducingSink>(StreamShape.REFERENCE) {
            /* class java.util.stream.ReduceOps.AnonymousClass2 */

            @Override // java.util.stream.ReduceOps.ReduceOp
            public AnonymousClass2ReducingSink makeSink() {
                return new AccumulatingSink<T, Optional<T>, AnonymousClass2ReducingSink>() {
                    /* class java.util.stream.ReduceOps.AnonymousClass2ReducingSink */
                    private boolean empty;
                    private T state;

                    @Override // java.util.stream.Sink
                    public void begin(long size) {
                        this.empty = true;
                        this.state = null;
                    }

                    @Override // java.util.function.Consumer
                    public void accept(T t) {
                        if (this.empty) {
                            this.empty = false;
                            this.state = t;
                            return;
                        }
                        this.state = (T) BinaryOperator.this.apply(this.state, t);
                    }

                    @Override // java.util.function.Supplier
                    public Optional<T> get() {
                        return this.empty ? Optional.empty() : Optional.of(this.state);
                    }

                    public void combine(AnonymousClass2ReducingSink other) {
                        if (!other.empty) {
                            accept(other.state);
                        }
                    }
                };
            }
        };
    }

    public static <T, I> TerminalOp<T, I> makeRef(final Collector<? super T, I, ?> collector) {
        final Supplier<I> supplier = ((Collector) Objects.requireNonNull(collector)).supplier();
        final BiConsumer<I, ? super T> accumulator = collector.accumulator();
        final BinaryOperator<I> combiner = collector.combiner();
        return new ReduceOp<T, I, AnonymousClass3ReducingSink>(StreamShape.REFERENCE) {
            /* class java.util.stream.ReduceOps.AnonymousClass3 */

            @Override // java.util.stream.ReduceOps.ReduceOp
            public AnonymousClass3ReducingSink makeSink() {
                return new AccumulatingSink<T, I, AnonymousClass3ReducingSink>(accumulator, combiner) {
                    /* class java.util.stream.ReduceOps.AnonymousClass3ReducingSink */
                    final /* synthetic */ BiConsumer val$accumulator;
                    final /* synthetic */ BinaryOperator val$combiner;

                    /* JADX WARN: Incorrect args count in method signature: ()V */
                    {
                        this.val$accumulator = r2;
                        this.val$combiner = r3;
                    }

                    @Override // java.util.stream.Sink
                    public void begin(long size) {
                        this.state = Supplier.this.get();
                    }

                    @Override // java.util.function.Consumer
                    public void accept(T t) {
                        this.val$accumulator.accept(this.state, t);
                    }

                    public void combine(AnonymousClass3ReducingSink other) {
                        this.state = this.val$combiner.apply(this.state, other.state);
                    }
                };
            }

            @Override // java.util.stream.TerminalOp
            public int getOpFlags() {
                if (collector.characteristics().contains(Collector.Characteristics.UNORDERED)) {
                    return StreamOpFlag.NOT_ORDERED;
                }
                return 0;
            }
        };
    }

    public static <T, R> TerminalOp<T, R> makeRef(final Supplier<R> seedFactory, final BiConsumer<R, ? super T> accumulator, final BiConsumer<R, R> reducer) {
        Objects.requireNonNull(seedFactory);
        Objects.requireNonNull(accumulator);
        Objects.requireNonNull(reducer);
        return new ReduceOp<T, R, AnonymousClass4ReducingSink>(StreamShape.REFERENCE) {
            /* class java.util.stream.ReduceOps.AnonymousClass4 */

            @Override // java.util.stream.ReduceOps.ReduceOp
            public AnonymousClass4ReducingSink makeSink() {
                return new AccumulatingSink<T, R, AnonymousClass4ReducingSink>(accumulator, reducer) {
                    /* class java.util.stream.ReduceOps.AnonymousClass4ReducingSink */
                    final /* synthetic */ BiConsumer val$accumulator;
                    final /* synthetic */ BiConsumer val$reducer;

                    /* JADX WARN: Incorrect args count in method signature: ()V */
                    {
                        this.val$accumulator = r2;
                        this.val$reducer = r3;
                    }

                    @Override // java.util.stream.Sink
                    public void begin(long size) {
                        this.state = Supplier.this.get();
                    }

                    @Override // java.util.function.Consumer
                    public void accept(T t) {
                        this.val$accumulator.accept(this.state, t);
                    }

                    public void combine(AnonymousClass4ReducingSink other) {
                        this.val$reducer.accept(this.state, other.state);
                    }
                };
            }
        };
    }

    public static TerminalOp<Integer, Integer> makeInt(final int identity, final IntBinaryOperator operator) {
        Objects.requireNonNull(operator);
        return new ReduceOp<Integer, Integer, AnonymousClass5ReducingSink>(StreamShape.INT_VALUE) {
            /* class java.util.stream.ReduceOps.AnonymousClass5 */

            @Override // java.util.stream.ReduceOps.ReduceOp
            public AnonymousClass5ReducingSink makeSink() {
                return new Object(identity, operator) {
                    /* class java.util.stream.ReduceOps.AnonymousClass5ReducingSink */
                    private int state;
                    final /* synthetic */ int val$identity;
                    final /* synthetic */ IntBinaryOperator val$operator;

                    /* JADX WARN: Incorrect args count in method signature: ()V */
                    {
                        this.val$identity = r1;
                        this.val$operator = r2;
                    }

                    @Override // java.util.stream.Sink
                    public void begin(long size) {
                        this.state = this.val$identity;
                    }

                    @Override // java.util.stream.Sink.OfInt, java.util.function.IntConsumer, java.util.stream.Sink
                    public void accept(int t) {
                        this.state = this.val$operator.applyAsInt(this.state, t);
                    }

                    @Override // java.util.function.Supplier
                    public Integer get() {
                        return Integer.valueOf(this.state);
                    }

                    public void combine(AnonymousClass5ReducingSink other) {
                        accept(other.state);
                    }
                };
            }
        };
    }

    public static TerminalOp<Integer, OptionalInt> makeInt(final IntBinaryOperator operator) {
        Objects.requireNonNull(operator);
        return new ReduceOp<Integer, OptionalInt, AnonymousClass6ReducingSink>(StreamShape.INT_VALUE) {
            /* class java.util.stream.ReduceOps.AnonymousClass6 */

            @Override // java.util.stream.ReduceOps.ReduceOp
            public AnonymousClass6ReducingSink makeSink() {
                return new Object() {
                    /* class java.util.stream.ReduceOps.AnonymousClass6ReducingSink */
                    private boolean empty;
                    private int state;

                    @Override // java.util.stream.Sink
                    public void begin(long size) {
                        this.empty = true;
                        this.state = 0;
                    }

                    @Override // java.util.stream.Sink.OfInt, java.util.function.IntConsumer, java.util.stream.Sink
                    public void accept(int t) {
                        if (this.empty) {
                            this.empty = false;
                            this.state = t;
                            return;
                        }
                        this.state = IntBinaryOperator.this.applyAsInt(this.state, t);
                    }

                    @Override // java.util.function.Supplier
                    public OptionalInt get() {
                        return this.empty ? OptionalInt.empty() : OptionalInt.of(this.state);
                    }

                    public void combine(AnonymousClass6ReducingSink other) {
                        if (!other.empty) {
                            accept(other.state);
                        }
                    }
                };
            }
        };
    }

    public static <R> TerminalOp<Integer, R> makeInt(final Supplier<R> supplier, final ObjIntConsumer<R> accumulator, final BinaryOperator<R> combiner) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(accumulator);
        Objects.requireNonNull(combiner);
        return new ReduceOp<Integer, R, AnonymousClass7ReducingSink>(StreamShape.INT_VALUE) {
            /* class java.util.stream.ReduceOps.AnonymousClass7 */

            @Override // java.util.stream.ReduceOps.ReduceOp
            public AnonymousClass7ReducingSink makeSink() {
                return new Box<R>(accumulator, combiner) {
                    /* class java.util.stream.ReduceOps.AnonymousClass7ReducingSink */
                    final /* synthetic */ ObjIntConsumer val$accumulator;
                    final /* synthetic */ BinaryOperator val$combiner;

                    /* JADX WARN: Incorrect args count in method signature: ()V */
                    {
                        this.val$accumulator = r2;
                        this.val$combiner = r3;
                    }

                    @Override // java.util.stream.Sink
                    public void begin(long size) {
                        this.state = Supplier.this.get();
                    }

                    @Override // java.util.stream.Sink.OfInt, java.util.function.IntConsumer, java.util.stream.Sink
                    public void accept(int t) {
                        this.val$accumulator.accept(this.state, t);
                    }

                    public void combine(AnonymousClass7ReducingSink other) {
                        this.state = this.val$combiner.apply(this.state, other.state);
                    }
                };
            }
        };
    }

    public static TerminalOp<Long, Long> makeLong(final long identity, final LongBinaryOperator operator) {
        Objects.requireNonNull(operator);
        return new ReduceOp<Long, Long, AnonymousClass8ReducingSink>(StreamShape.LONG_VALUE) {
            /* class java.util.stream.ReduceOps.AnonymousClass8 */

            @Override // java.util.stream.ReduceOps.ReduceOp
            public AnonymousClass8ReducingSink makeSink() {
                return new Object(identity, operator) {
                    /* class java.util.stream.ReduceOps.AnonymousClass8ReducingSink */
                    private long state;
                    final /* synthetic */ long val$identity;
                    final /* synthetic */ LongBinaryOperator val$operator;

                    /* JADX WARN: Incorrect args count in method signature: ()V */
                    {
                        this.val$identity = r1;
                        this.val$operator = r3;
                    }

                    @Override // java.util.stream.Sink
                    public void begin(long size) {
                        this.state = this.val$identity;
                    }

                    @Override // java.util.stream.Sink.OfLong, java.util.stream.Sink, java.util.function.LongConsumer
                    public void accept(long t) {
                        this.state = this.val$operator.applyAsLong(this.state, t);
                    }

                    @Override // java.util.function.Supplier
                    public Long get() {
                        return Long.valueOf(this.state);
                    }

                    public void combine(AnonymousClass8ReducingSink other) {
                        accept(other.state);
                    }
                };
            }
        };
    }

    public static TerminalOp<Long, OptionalLong> makeLong(final LongBinaryOperator operator) {
        Objects.requireNonNull(operator);
        return new ReduceOp<Long, OptionalLong, AnonymousClass9ReducingSink>(StreamShape.LONG_VALUE) {
            /* class java.util.stream.ReduceOps.AnonymousClass9 */

            @Override // java.util.stream.ReduceOps.ReduceOp
            public AnonymousClass9ReducingSink makeSink() {
                return new Object() {
                    /* class java.util.stream.ReduceOps.AnonymousClass9ReducingSink */
                    private boolean empty;
                    private long state;

                    @Override // java.util.stream.Sink
                    public void begin(long size) {
                        this.empty = true;
                        this.state = 0;
                    }

                    @Override // java.util.stream.Sink.OfLong, java.util.stream.Sink, java.util.function.LongConsumer
                    public void accept(long t) {
                        if (this.empty) {
                            this.empty = false;
                            this.state = t;
                            return;
                        }
                        this.state = LongBinaryOperator.this.applyAsLong(this.state, t);
                    }

                    @Override // java.util.function.Supplier
                    public OptionalLong get() {
                        return this.empty ? OptionalLong.empty() : OptionalLong.of(this.state);
                    }

                    public void combine(AnonymousClass9ReducingSink other) {
                        if (!other.empty) {
                            accept(other.state);
                        }
                    }
                };
            }
        };
    }

    public static <R> TerminalOp<Long, R> makeLong(final Supplier<R> supplier, final ObjLongConsumer<R> accumulator, final BinaryOperator<R> combiner) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(accumulator);
        Objects.requireNonNull(combiner);
        return new ReduceOp<Long, R, AnonymousClass10ReducingSink>(StreamShape.LONG_VALUE) {
            /* class java.util.stream.ReduceOps.AnonymousClass10 */

            @Override // java.util.stream.ReduceOps.ReduceOp
            public AnonymousClass10ReducingSink makeSink() {
                return new Box<R>(accumulator, combiner) {
                    /* class java.util.stream.ReduceOps.AnonymousClass10ReducingSink */
                    final /* synthetic */ ObjLongConsumer val$accumulator;
                    final /* synthetic */ BinaryOperator val$combiner;

                    /* JADX WARN: Incorrect args count in method signature: ()V */
                    {
                        this.val$accumulator = r2;
                        this.val$combiner = r3;
                    }

                    @Override // java.util.stream.Sink
                    public void begin(long size) {
                        this.state = Supplier.this.get();
                    }

                    @Override // java.util.stream.Sink.OfLong, java.util.stream.Sink, java.util.function.LongConsumer
                    public void accept(long t) {
                        this.val$accumulator.accept(this.state, t);
                    }

                    public void combine(AnonymousClass10ReducingSink other) {
                        this.state = this.val$combiner.apply(this.state, other.state);
                    }
                };
            }
        };
    }

    public static TerminalOp<Double, Double> makeDouble(final double identity, final DoubleBinaryOperator operator) {
        Objects.requireNonNull(operator);
        return new ReduceOp<Double, Double, AnonymousClass11ReducingSink>(StreamShape.DOUBLE_VALUE) {
            /* class java.util.stream.ReduceOps.AnonymousClass11 */

            @Override // java.util.stream.ReduceOps.ReduceOp
            public AnonymousClass11ReducingSink makeSink() {
                return new Object(identity, operator) {
                    /* class java.util.stream.ReduceOps.AnonymousClass11ReducingSink */
                    private double state;
                    final /* synthetic */ double val$identity;
                    final /* synthetic */ DoubleBinaryOperator val$operator;

                    /* JADX WARN: Incorrect args count in method signature: ()V */
                    {
                        this.val$identity = r1;
                        this.val$operator = r3;
                    }

                    @Override // java.util.stream.Sink
                    public void begin(long size) {
                        this.state = this.val$identity;
                    }

                    @Override // java.util.stream.Sink.OfDouble, java.util.function.DoubleConsumer, java.util.stream.Sink
                    public void accept(double t) {
                        this.state = this.val$operator.applyAsDouble(this.state, t);
                    }

                    @Override // java.util.function.Supplier
                    public Double get() {
                        return Double.valueOf(this.state);
                    }

                    public void combine(AnonymousClass11ReducingSink other) {
                        accept(other.state);
                    }
                };
            }
        };
    }

    public static TerminalOp<Double, OptionalDouble> makeDouble(final DoubleBinaryOperator operator) {
        Objects.requireNonNull(operator);
        return new ReduceOp<Double, OptionalDouble, AnonymousClass12ReducingSink>(StreamShape.DOUBLE_VALUE) {
            /* class java.util.stream.ReduceOps.AnonymousClass12 */

            @Override // java.util.stream.ReduceOps.ReduceOp
            public AnonymousClass12ReducingSink makeSink() {
                return new Object() {
                    /* class java.util.stream.ReduceOps.AnonymousClass12ReducingSink */
                    private boolean empty;
                    private double state;

                    @Override // java.util.stream.Sink
                    public void begin(long size) {
                        this.empty = true;
                        this.state = 0.0d;
                    }

                    @Override // java.util.stream.Sink.OfDouble, java.util.function.DoubleConsumer, java.util.stream.Sink
                    public void accept(double t) {
                        if (this.empty) {
                            this.empty = false;
                            this.state = t;
                            return;
                        }
                        this.state = DoubleBinaryOperator.this.applyAsDouble(this.state, t);
                    }

                    @Override // java.util.function.Supplier
                    public OptionalDouble get() {
                        return this.empty ? OptionalDouble.empty() : OptionalDouble.of(this.state);
                    }

                    public void combine(AnonymousClass12ReducingSink other) {
                        if (!other.empty) {
                            accept(other.state);
                        }
                    }
                };
            }
        };
    }

    public static <R> TerminalOp<Double, R> makeDouble(final Supplier<R> supplier, final ObjDoubleConsumer<R> accumulator, final BinaryOperator<R> combiner) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(accumulator);
        Objects.requireNonNull(combiner);
        return new ReduceOp<Double, R, AnonymousClass13ReducingSink>(StreamShape.DOUBLE_VALUE) {
            /* class java.util.stream.ReduceOps.AnonymousClass13 */

            @Override // java.util.stream.ReduceOps.ReduceOp
            public AnonymousClass13ReducingSink makeSink() {
                return new Box<R>(accumulator, combiner) {
                    /* class java.util.stream.ReduceOps.AnonymousClass13ReducingSink */
                    final /* synthetic */ ObjDoubleConsumer val$accumulator;
                    final /* synthetic */ BinaryOperator val$combiner;

                    /* JADX WARN: Incorrect args count in method signature: ()V */
                    {
                        this.val$accumulator = r2;
                        this.val$combiner = r3;
                    }

                    @Override // java.util.stream.Sink
                    public void begin(long size) {
                        this.state = Supplier.this.get();
                    }

                    @Override // java.util.stream.Sink.OfDouble, java.util.function.DoubleConsumer, java.util.stream.Sink
                    public void accept(double t) {
                        this.val$accumulator.accept(this.state, t);
                    }

                    public void combine(AnonymousClass13ReducingSink other) {
                        this.state = this.val$combiner.apply(this.state, other.state);
                    }
                };
            }
        };
    }

    private static abstract class Box<U> {
        U state;

        Box() {
        }

        public U get() {
            return this.state;
        }
    }

    /* access modifiers changed from: private */
    public static abstract class ReduceOp<T, R, S extends AccumulatingSink<T, R, S>> implements TerminalOp<T, R> {
        private final StreamShape inputShape;

        public abstract S makeSink();

        ReduceOp(StreamShape shape) {
            this.inputShape = shape;
        }

        @Override // java.util.stream.TerminalOp
        public StreamShape inputShape() {
            return this.inputShape;
        }

        @Override // java.util.stream.TerminalOp
        public <P_IN> R evaluateSequential(PipelineHelper<T> helper, Spliterator<P_IN> spliterator) {
            return (R) ((AccumulatingSink) helper.wrapAndCopyInto(makeSink(), spliterator)).get();
        }

        @Override // java.util.stream.TerminalOp
        public <P_IN> R evaluateParallel(PipelineHelper<T> helper, Spliterator<P_IN> spliterator) {
            return (R) ((AccumulatingSink) new ReduceTask(this, helper, spliterator).invoke()).get();
        }
    }

    private static final class ReduceTask<P_IN, P_OUT, R, S extends AccumulatingSink<P_OUT, R, S>> extends AbstractTask<P_IN, P_OUT, S, ReduceTask<P_IN, P_OUT, R, S>> {
        private final ReduceOp<P_OUT, R, S> op;

        ReduceTask(ReduceOp<P_OUT, R, S> op2, PipelineHelper<P_OUT> helper, Spliterator<P_IN> spliterator) {
            super(helper, spliterator);
            this.op = op2;
        }

        ReduceTask(ReduceTask<P_IN, P_OUT, R, S> parent, Spliterator<P_IN> spliterator) {
            super(parent, spliterator);
            this.op = parent.op;
        }

        /* access modifiers changed from: protected */
        @Override // java.util.stream.AbstractTask
        public ReduceTask<P_IN, P_OUT, R, S> makeChild(Spliterator<P_IN> spliterator) {
            return new ReduceTask<>(this, spliterator);
        }

        /* access modifiers changed from: protected */
        @Override // java.util.stream.AbstractTask
        public S doLeaf() {
            return (S) ((AccumulatingSink) this.helper.wrapAndCopyInto(this.op.makeSink(), this.spliterator));
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.stream.ReduceOps$ReduceTask<P_IN, P_OUT, R, S extends java.util.stream.ReduceOps$AccumulatingSink<P_OUT, R, S>> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.CountedCompleter, java.util.stream.AbstractTask
        public void onCompletion(CountedCompleter<?> caller) {
            if (!isLeaf()) {
                AccumulatingSink accumulatingSink = (AccumulatingSink) ((ReduceTask) this.leftChild).getLocalResult();
                accumulatingSink.combine((AccumulatingSink) ((ReduceTask) this.rightChild).getLocalResult());
                setLocalResult(accumulatingSink);
            }
            super.onCompletion(caller);
        }
    }
}
