package java.util.stream;

import java.util.Comparator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.function.LongConsumer;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import java.util.stream.Sink;
import java.util.stream.SpinedBuffer;
import java.util.stream.StreamSpliterators;

class StreamSpliterators {
    StreamSpliterators() {
    }

    /* access modifiers changed from: private */
    public static abstract class AbstractWrappingSpliterator<P_IN, P_OUT, T_BUFFER extends AbstractSpinedBuffer> implements Spliterator<P_OUT> {
        T_BUFFER buffer;
        Sink<P_IN> bufferSink;
        boolean finished;
        final boolean isParallel;
        long nextToConsume;
        final PipelineHelper<P_OUT> ph;
        BooleanSupplier pusher;
        Spliterator<P_IN> spliterator;
        private Supplier<Spliterator<P_IN>> spliteratorSupplier;

        /* access modifiers changed from: package-private */
        public abstract void initPartialTraversalState();

        /* access modifiers changed from: package-private */
        public abstract AbstractWrappingSpliterator<P_IN, P_OUT, ?> wrap(Spliterator<P_IN> spliterator2);

        AbstractWrappingSpliterator(PipelineHelper<P_OUT> ph2, Supplier<Spliterator<P_IN>> spliteratorSupplier2, boolean parallel) {
            this.ph = ph2;
            this.spliteratorSupplier = spliteratorSupplier2;
            this.spliterator = null;
            this.isParallel = parallel;
        }

        AbstractWrappingSpliterator(PipelineHelper<P_OUT> ph2, Spliterator<P_IN> spliterator2, boolean parallel) {
            this.ph = ph2;
            this.spliteratorSupplier = null;
            this.spliterator = spliterator2;
            this.isParallel = parallel;
        }

        /* access modifiers changed from: package-private */
        public final void init() {
            if (this.spliterator == null) {
                this.spliterator = this.spliteratorSupplier.get();
                this.spliteratorSupplier = null;
            }
        }

        /* access modifiers changed from: package-private */
        public final boolean doAdvance() {
            T_BUFFER t_buffer = this.buffer;
            boolean hasNext = false;
            if (t_buffer != null) {
                this.nextToConsume++;
                if (this.nextToConsume < t_buffer.count()) {
                    hasNext = true;
                }
                if (hasNext) {
                    return hasNext;
                }
                this.nextToConsume = 0;
                this.buffer.clear();
                return fillBuffer();
            } else if (this.finished) {
                return false;
            } else {
                init();
                initPartialTraversalState();
                this.nextToConsume = 0;
                this.bufferSink.begin(this.spliterator.getExactSizeIfKnown());
                return fillBuffer();
            }
        }

        @Override // java.util.Spliterator
        public Spliterator<P_OUT> trySplit() {
            if (!this.isParallel || this.finished) {
                return null;
            }
            init();
            Spliterator<P_IN> split = this.spliterator.trySplit();
            if (split == null) {
                return null;
            }
            return wrap(split);
        }

        private boolean fillBuffer() {
            while (this.buffer.count() == 0) {
                if (this.bufferSink.cancellationRequested() || !this.pusher.getAsBoolean()) {
                    if (this.finished) {
                        return false;
                    }
                    this.bufferSink.end();
                    this.finished = true;
                }
            }
            return true;
        }

        @Override // java.util.Spliterator
        public final long estimateSize() {
            init();
            return this.spliterator.estimateSize();
        }

        @Override // java.util.Spliterator
        public final long getExactSizeIfKnown() {
            init();
            if (StreamOpFlag.SIZED.isKnown(this.ph.getStreamAndOpFlags())) {
                return this.spliterator.getExactSizeIfKnown();
            }
            return -1;
        }

        @Override // java.util.Spliterator
        public final int characteristics() {
            init();
            int c = StreamOpFlag.toCharacteristics(StreamOpFlag.toStreamFlags(this.ph.getStreamAndOpFlags()));
            if ((c & 64) != 0) {
                return (c & -16449) | (this.spliterator.characteristics() & 16448);
            }
            return c;
        }

        @Override // java.util.Spliterator
        public Comparator<? super P_OUT> getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
        }

        public final String toString() {
            return String.format("%s[%s]", getClass().getName(), this.spliterator);
        }
    }

    static final class WrappingSpliterator<P_IN, P_OUT> extends AbstractWrappingSpliterator<P_IN, P_OUT, SpinedBuffer<P_OUT>> {
        WrappingSpliterator(PipelineHelper<P_OUT> ph, Supplier<Spliterator<P_IN>> supplier, boolean parallel) {
            super(ph, supplier, parallel);
        }

        WrappingSpliterator(PipelineHelper<P_OUT> ph, Spliterator<P_IN> spliterator, boolean parallel) {
            super(ph, spliterator, parallel);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.stream.StreamSpliterators.AbstractWrappingSpliterator
        public WrappingSpliterator<P_IN, P_OUT> wrap(Spliterator<P_IN> s) {
            return new WrappingSpliterator<>(this.ph, s, this.isParallel);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.stream.StreamSpliterators.AbstractWrappingSpliterator
        public void initPartialTraversalState() {
            SpinedBuffer<P_OUT> b = new SpinedBuffer<>();
            this.buffer = b;
            PipelineHelper pipelineHelper = this.ph;
            Objects.requireNonNull(b);
            this.bufferSink = pipelineHelper.wrapSink(new Sink() {
                /* class java.util.stream.$$Lambda$GFs38TgrG6hfxe__ZFdhGpwPw */

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    SpinedBuffer.this.accept(obj);
                }
            });
            this.pusher = new BooleanSupplier() {
                /* class java.util.stream.$$Lambda$StreamSpliterators$WrappingSpliterator$Ky6g3CKkCccuRWAvbAL1cAsdkNk */

                @Override // java.util.function.BooleanSupplier
                public final boolean getAsBoolean() {
                    return StreamSpliterators.WrappingSpliterator.this.lambda$initPartialTraversalState$0$StreamSpliterators$WrappingSpliterator();
                }
            };
        }

        public /* synthetic */ boolean lambda$initPartialTraversalState$0$StreamSpliterators$WrappingSpliterator() {
            return this.spliterator.tryAdvance(this.bufferSink);
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super P_OUT> consumer) {
            Objects.requireNonNull(consumer);
            boolean hasNext = doAdvance();
            if (hasNext) {
                consumer.accept((Object) ((SpinedBuffer) this.buffer).get(this.nextToConsume));
            }
            return hasNext;
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super P_OUT> consumer) {
            if (this.buffer != null || this.finished) {
                do {
                } while (tryAdvance(consumer));
                return;
            }
            Objects.requireNonNull(consumer);
            init();
            PipelineHelper pipelineHelper = this.ph;
            Objects.requireNonNull(consumer);
            pipelineHelper.wrapAndCopyInto(new Sink() {
                /* class java.util.stream.$$Lambda$btpzqYSQDsLykCcQbI2_g5D3zs */

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Consumer.this.accept(obj);
                }
            }, this.spliterator);
            this.finished = true;
        }
    }

    static final class IntWrappingSpliterator<P_IN> extends AbstractWrappingSpliterator<P_IN, Integer, SpinedBuffer.OfInt> implements Spliterator.OfInt {
        IntWrappingSpliterator(PipelineHelper<Integer> ph, Supplier<Spliterator<P_IN>> supplier, boolean parallel) {
            super(ph, supplier, parallel);
        }

        IntWrappingSpliterator(PipelineHelper<Integer> ph, Spliterator<P_IN> spliterator, boolean parallel) {
            super(ph, spliterator, parallel);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.stream.StreamSpliterators.AbstractWrappingSpliterator
        public AbstractWrappingSpliterator<P_IN, Integer, ?> wrap(Spliterator<P_IN> s) {
            return new IntWrappingSpliterator(this.ph, s, this.isParallel);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.stream.StreamSpliterators.AbstractWrappingSpliterator
        public void initPartialTraversalState() {
            SpinedBuffer.OfInt b = new SpinedBuffer.OfInt();
            this.buffer = b;
            PipelineHelper pipelineHelper = this.ph;
            Objects.requireNonNull(b);
            this.bufferSink = pipelineHelper.wrapSink(new Sink.OfInt() {
                /* class java.util.stream.$$Lambda$ZgCkHA78fnu8poGzKYmvyaev3U */

                @Override // java.util.stream.Sink.OfInt, java.util.function.IntConsumer, java.util.stream.Sink
                public final void accept(int i) {
                    SpinedBuffer.OfInt.this.accept(i);
                }
            });
            this.pusher = new BooleanSupplier() {
                /* class java.util.stream.$$Lambda$StreamSpliterators$IntWrappingSpliterator$js67IRBzuEwtfp5Z3OTFGfmUTw */

                @Override // java.util.function.BooleanSupplier
                public final boolean getAsBoolean() {
                    return StreamSpliterators.IntWrappingSpliterator.this.lambda$initPartialTraversalState$0$StreamSpliterators$IntWrappingSpliterator();
                }
            };
        }

        public /* synthetic */ boolean lambda$initPartialTraversalState$0$StreamSpliterators$IntWrappingSpliterator() {
            return this.spliterator.tryAdvance(this.bufferSink);
        }

        /* Return type fixed from 'java.util.Spliterator$OfInt' to match base method */
        @Override // java.util.Spliterator.OfInt, java.util.Spliterator.OfInt, java.util.Spliterator.OfInt, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.stream.StreamSpliterators.AbstractWrappingSpliterator, java.util.Spliterator
        public Spliterator<Integer> trySplit() {
            return (Spliterator.OfInt) super.trySplit();
        }

        @Override // java.util.Spliterator.OfInt
        public boolean tryAdvance(IntConsumer consumer) {
            Objects.requireNonNull(consumer);
            boolean hasNext = doAdvance();
            if (hasNext) {
                consumer.accept(((SpinedBuffer.OfInt) this.buffer).get(this.nextToConsume));
            }
            return hasNext;
        }

        @Override // java.util.Spliterator.OfInt
        public void forEachRemaining(IntConsumer consumer) {
            if (this.buffer != null || this.finished) {
                do {
                } while (tryAdvance(consumer));
                return;
            }
            Objects.requireNonNull(consumer);
            init();
            PipelineHelper pipelineHelper = this.ph;
            Objects.requireNonNull(consumer);
            pipelineHelper.wrapAndCopyInto(new Sink.OfInt() {
                /* class java.util.stream.$$Lambda$C9lt_0CgSARhdNFJsMyHSsCsGA */

                @Override // java.util.stream.Sink.OfInt, java.util.function.IntConsumer, java.util.stream.Sink
                public final void accept(int i) {
                    IntConsumer.this.accept(i);
                }
            }, this.spliterator);
            this.finished = true;
        }
    }

    static final class LongWrappingSpliterator<P_IN> extends AbstractWrappingSpliterator<P_IN, Long, SpinedBuffer.OfLong> implements Spliterator.OfLong {
        LongWrappingSpliterator(PipelineHelper<Long> ph, Supplier<Spliterator<P_IN>> supplier, boolean parallel) {
            super(ph, supplier, parallel);
        }

        LongWrappingSpliterator(PipelineHelper<Long> ph, Spliterator<P_IN> spliterator, boolean parallel) {
            super(ph, spliterator, parallel);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.stream.StreamSpliterators.AbstractWrappingSpliterator
        public AbstractWrappingSpliterator<P_IN, Long, ?> wrap(Spliterator<P_IN> s) {
            return new LongWrappingSpliterator(this.ph, s, this.isParallel);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.stream.StreamSpliterators.AbstractWrappingSpliterator
        public void initPartialTraversalState() {
            SpinedBuffer.OfLong b = new SpinedBuffer.OfLong();
            this.buffer = b;
            PipelineHelper pipelineHelper = this.ph;
            Objects.requireNonNull(b);
            this.bufferSink = pipelineHelper.wrapSink(new Sink.OfLong() {
                /* class java.util.stream.$$Lambda$6BdNjvJJOqgXMfHsEogzyrab60 */

                @Override // java.util.stream.Sink.OfLong, java.util.stream.Sink, java.util.function.LongConsumer
                public final void accept(long j) {
                    SpinedBuffer.OfLong.this.accept(j);
                }
            });
            this.pusher = new BooleanSupplier() {
                /* class java.util.stream.$$Lambda$StreamSpliterators$LongWrappingSpliterator$sXmxiR9mZHUX9mr52PfuVCxTtPw */

                @Override // java.util.function.BooleanSupplier
                public final boolean getAsBoolean() {
                    return StreamSpliterators.LongWrappingSpliterator.this.lambda$initPartialTraversalState$0$StreamSpliterators$LongWrappingSpliterator();
                }
            };
        }

        public /* synthetic */ boolean lambda$initPartialTraversalState$0$StreamSpliterators$LongWrappingSpliterator() {
            return this.spliterator.tryAdvance(this.bufferSink);
        }

        /* Return type fixed from 'java.util.Spliterator$OfLong' to match base method */
        @Override // java.util.Spliterator.OfLong, java.util.Spliterator.OfLong, java.util.Spliterator.OfLong, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.stream.StreamSpliterators.AbstractWrappingSpliterator, java.util.Spliterator
        public Spliterator<Long> trySplit() {
            return (Spliterator.OfLong) super.trySplit();
        }

        @Override // java.util.Spliterator.OfLong
        public boolean tryAdvance(LongConsumer consumer) {
            Objects.requireNonNull(consumer);
            boolean hasNext = doAdvance();
            if (hasNext) {
                consumer.accept(((SpinedBuffer.OfLong) this.buffer).get(this.nextToConsume));
            }
            return hasNext;
        }

        @Override // java.util.Spliterator.OfLong
        public void forEachRemaining(LongConsumer consumer) {
            if (this.buffer != null || this.finished) {
                do {
                } while (tryAdvance(consumer));
                return;
            }
            Objects.requireNonNull(consumer);
            init();
            PipelineHelper pipelineHelper = this.ph;
            Objects.requireNonNull(consumer);
            pipelineHelper.wrapAndCopyInto(new Sink.OfLong() {
                /* class java.util.stream.$$Lambda$G3FiaNZPcIIAnGkHVY7Mdu42X5g */

                @Override // java.util.stream.Sink.OfLong, java.util.stream.Sink, java.util.function.LongConsumer
                public final void accept(long j) {
                    LongConsumer.this.accept(j);
                }
            }, this.spliterator);
            this.finished = true;
        }
    }

    static final class DoubleWrappingSpliterator<P_IN> extends AbstractWrappingSpliterator<P_IN, Double, SpinedBuffer.OfDouble> implements Spliterator.OfDouble {
        DoubleWrappingSpliterator(PipelineHelper<Double> ph, Supplier<Spliterator<P_IN>> supplier, boolean parallel) {
            super(ph, supplier, parallel);
        }

        DoubleWrappingSpliterator(PipelineHelper<Double> ph, Spliterator<P_IN> spliterator, boolean parallel) {
            super(ph, spliterator, parallel);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.stream.StreamSpliterators.AbstractWrappingSpliterator
        public AbstractWrappingSpliterator<P_IN, Double, ?> wrap(Spliterator<P_IN> s) {
            return new DoubleWrappingSpliterator(this.ph, s, this.isParallel);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.stream.StreamSpliterators.AbstractWrappingSpliterator
        public void initPartialTraversalState() {
            SpinedBuffer.OfDouble b = new SpinedBuffer.OfDouble();
            this.buffer = b;
            PipelineHelper pipelineHelper = this.ph;
            Objects.requireNonNull(b);
            this.bufferSink = pipelineHelper.wrapSink(new Sink.OfDouble() {
                /* class java.util.stream.$$Lambda$xWqUKnt_aBWo9sD9bohYsGFiXg */

                @Override // java.util.stream.Sink.OfDouble, java.util.function.DoubleConsumer, java.util.stream.Sink
                public final void accept(double d) {
                    SpinedBuffer.OfDouble.this.accept(d);
                }
            });
            this.pusher = new BooleanSupplier() {
                /* class java.util.stream.$$Lambda$StreamSpliterators$DoubleWrappingSpliterator$vGvekEV3XchaSAEI93tmYCeVG9A */

                @Override // java.util.function.BooleanSupplier
                public final boolean getAsBoolean() {
                    return StreamSpliterators.DoubleWrappingSpliterator.this.lambda$initPartialTraversalState$0$StreamSpliterators$DoubleWrappingSpliterator();
                }
            };
        }

        public /* synthetic */ boolean lambda$initPartialTraversalState$0$StreamSpliterators$DoubleWrappingSpliterator() {
            return this.spliterator.tryAdvance(this.bufferSink);
        }

        /* Return type fixed from 'java.util.Spliterator$OfDouble' to match base method */
        @Override // java.util.Spliterator.OfDouble, java.util.Spliterator.OfDouble, java.util.Spliterator.OfDouble, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.stream.StreamSpliterators.AbstractWrappingSpliterator, java.util.Spliterator
        public Spliterator<Double> trySplit() {
            return (Spliterator.OfDouble) super.trySplit();
        }

        @Override // java.util.Spliterator.OfDouble
        public boolean tryAdvance(DoubleConsumer consumer) {
            Objects.requireNonNull(consumer);
            boolean hasNext = doAdvance();
            if (hasNext) {
                consumer.accept(((SpinedBuffer.OfDouble) this.buffer).get(this.nextToConsume));
            }
            return hasNext;
        }

        @Override // java.util.Spliterator.OfDouble
        public void forEachRemaining(DoubleConsumer consumer) {
            if (this.buffer != null || this.finished) {
                do {
                } while (tryAdvance(consumer));
                return;
            }
            Objects.requireNonNull(consumer);
            init();
            PipelineHelper pipelineHelper = this.ph;
            Objects.requireNonNull(consumer);
            pipelineHelper.wrapAndCopyInto(new Sink.OfDouble() {
                /* class java.util.stream.$$Lambda$fgFAI1gk0hw2h3IP9CmHWlY3YkM */

                @Override // java.util.stream.Sink.OfDouble, java.util.function.DoubleConsumer, java.util.stream.Sink
                public final void accept(double d) {
                    DoubleConsumer.this.accept(d);
                }
            }, this.spliterator);
            this.finished = true;
        }
    }

    static class DelegatingSpliterator<T, T_SPLITR extends Spliterator<T>> implements Spliterator<T> {
        private T_SPLITR s;
        private final Supplier<? extends T_SPLITR> supplier;

        DelegatingSpliterator(Supplier<? extends T_SPLITR> supplier2) {
            this.supplier = supplier2;
        }

        /* access modifiers changed from: package-private */
        public T_SPLITR get() {
            if (this.s == null) {
                this.s = (T_SPLITR) ((Spliterator) this.supplier.get());
            }
            return this.s;
        }

        @Override // java.util.Spliterator
        public T_SPLITR trySplit() {
            return (T_SPLITR) get().trySplit();
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super T> consumer) {
            return get().tryAdvance(consumer);
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super T> consumer) {
            get().forEachRemaining(consumer);
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return get().estimateSize();
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return get().characteristics();
        }

        @Override // java.util.Spliterator
        public Comparator<? super T> getComparator() {
            return get().getComparator();
        }

        @Override // java.util.Spliterator
        public long getExactSizeIfKnown() {
            return get().getExactSizeIfKnown();
        }

        public String toString() {
            return getClass().getName() + "[" + ((Object) get()) + "]";
        }

        static class OfPrimitive<T, T_CONS, T_SPLITR extends Spliterator.OfPrimitive<T, T_CONS, T_SPLITR>> extends DelegatingSpliterator<T, T_SPLITR> implements Spliterator.OfPrimitive<T, T_CONS, T_SPLITR> {
            @Override // java.util.stream.StreamSpliterators.DelegatingSpliterator, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
                return (Spliterator.OfPrimitive) super.trySplit();
            }

            OfPrimitive(Supplier<? extends T_SPLITR> supplier) {
                super(supplier);
            }

            @Override // java.util.Spliterator.OfPrimitive
            public boolean tryAdvance(T_CONS consumer) {
                return ((Spliterator.OfPrimitive) get()).tryAdvance((Object) consumer);
            }

            @Override // java.util.Spliterator.OfPrimitive
            public void forEachRemaining(T_CONS consumer) {
                ((Spliterator.OfPrimitive) get()).forEachRemaining((Object) consumer);
            }
        }

        /* access modifiers changed from: package-private */
        public static final class OfInt extends OfPrimitive<Integer, IntConsumer, Spliterator.OfInt> implements Spliterator.OfInt {
            @Override // java.util.Spliterator.OfInt
            public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
                super.forEachRemaining((Object) intConsumer);
            }

            @Override // java.util.Spliterator.OfInt
            public /* bridge */ /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
                return super.tryAdvance((Object) intConsumer);
            }

            @Override // java.util.stream.StreamSpliterators.DelegatingSpliterator, java.util.Spliterator.OfInt, java.util.Spliterator.OfInt, java.util.Spliterator.OfInt, java.util.stream.StreamSpliterators.DelegatingSpliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfInt trySplit() {
                return (Spliterator.OfInt) super.trySplit();
            }

            OfInt(Supplier<Spliterator.OfInt> supplier) {
                super(supplier);
            }
        }

        /* access modifiers changed from: package-private */
        public static final class OfLong extends OfPrimitive<Long, LongConsumer, Spliterator.OfLong> implements Spliterator.OfLong {
            @Override // java.util.Spliterator.OfLong
            public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
                super.forEachRemaining((Object) longConsumer);
            }

            @Override // java.util.Spliterator.OfLong
            public /* bridge */ /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
                return super.tryAdvance((Object) longConsumer);
            }

            @Override // java.util.stream.StreamSpliterators.DelegatingSpliterator, java.util.stream.StreamSpliterators.DelegatingSpliterator.OfPrimitive, java.util.Spliterator.OfLong, java.util.Spliterator.OfLong, java.util.Spliterator.OfLong, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfLong trySplit() {
                return (Spliterator.OfLong) super.trySplit();
            }

            OfLong(Supplier<Spliterator.OfLong> supplier) {
                super(supplier);
            }
        }

        /* access modifiers changed from: package-private */
        public static final class OfDouble extends OfPrimitive<Double, DoubleConsumer, Spliterator.OfDouble> implements Spliterator.OfDouble {
            @Override // java.util.Spliterator.OfDouble
            public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
                super.forEachRemaining((Object) doubleConsumer);
            }

            @Override // java.util.Spliterator.OfDouble
            public /* bridge */ /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
                return super.tryAdvance((Object) doubleConsumer);
            }

            @Override // java.util.stream.StreamSpliterators.DelegatingSpliterator, java.util.stream.StreamSpliterators.DelegatingSpliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfDouble, java.util.Spliterator.OfDouble, java.util.Spliterator.OfDouble, java.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfDouble trySplit() {
                return (Spliterator.OfDouble) super.trySplit();
            }

            OfDouble(Supplier<Spliterator.OfDouble> supplier) {
                super(supplier);
            }
        }
    }

    static abstract class SliceSpliterator<T, T_SPLITR extends Spliterator<T>> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        long fence;
        long index;
        T_SPLITR s;
        final long sliceFence;
        final long sliceOrigin;

        /* access modifiers changed from: protected */
        public abstract T_SPLITR makeSpliterator(T_SPLITR t_splitr, long j, long j2, long j3, long j4);

        SliceSpliterator(T_SPLITR s2, long sliceOrigin2, long sliceFence2, long origin, long fence2) {
            this.s = s2;
            this.sliceOrigin = sliceOrigin2;
            this.sliceFence = sliceFence2;
            this.index = origin;
            this.fence = fence2;
        }

        public T_SPLITR trySplit() {
            long j = this.sliceOrigin;
            long j2 = this.fence;
            if (j >= j2 || this.index >= j2) {
                return null;
            }
            while (true) {
                T_SPLITR leftSplit = (T_SPLITR) this.s.trySplit();
                if (leftSplit == null) {
                    return null;
                }
                long leftSplitFenceUnbounded = this.index + leftSplit.estimateSize();
                long leftSplitFence = Math.min(leftSplitFenceUnbounded, this.sliceFence);
                long j3 = this.sliceOrigin;
                if (j3 >= leftSplitFence) {
                    this.index = leftSplitFence;
                } else {
                    long j4 = this.sliceFence;
                    if (leftSplitFence >= j4) {
                        this.s = leftSplit;
                        this.fence = leftSplitFence;
                    } else if (this.index < j3 || leftSplitFenceUnbounded > j4) {
                        long j5 = this.sliceOrigin;
                        long j6 = this.sliceFence;
                        long j7 = this.index;
                        this.index = leftSplitFence;
                        return makeSpliterator(leftSplit, j5, j6, j7, leftSplitFence);
                    } else {
                        this.index = leftSplitFence;
                        return leftSplit;
                    }
                }
            }
        }

        public long estimateSize() {
            long j = this.sliceOrigin;
            long j2 = this.fence;
            if (j < j2) {
                return j2 - Math.max(j, this.index);
            }
            return 0;
        }

        public int characteristics() {
            return this.s.characteristics();
        }

        /* access modifiers changed from: package-private */
        public static final class OfRef<T> extends SliceSpliterator<T, Spliterator<T>> implements Spliterator<T> {
            OfRef(Spliterator<T> s, long sliceOrigin, long sliceFence) {
                this(s, sliceOrigin, sliceFence, 0, Math.min(s.estimateSize(), sliceFence));
            }

            private OfRef(Spliterator<T> s, long sliceOrigin, long sliceFence, long origin, long fence) {
                super(s, sliceOrigin, sliceFence, origin, fence);
            }

            /* access modifiers changed from: protected */
            @Override // java.util.stream.StreamSpliterators.SliceSpliterator
            public Spliterator<T> makeSpliterator(Spliterator<T> s, long sliceOrigin, long sliceFence, long origin, long fence) {
                return new OfRef(s, sliceOrigin, sliceFence, origin, fence);
            }

            @Override // java.util.Spliterator
            public boolean tryAdvance(Consumer<? super T> action) {
                Objects.requireNonNull(action);
                if (this.sliceOrigin >= this.fence) {
                    return false;
                }
                while (this.sliceOrigin > this.index) {
                    this.s.tryAdvance($$Lambda$StreamSpliterators$SliceSpliterator$OfRef$WQsOrB6TN5sHvsPJU2O20DZGElU.INSTANCE);
                    this.index++;
                }
                if (this.index >= this.fence) {
                    return false;
                }
                this.index++;
                return this.s.tryAdvance(action);
            }

            static /* synthetic */ void lambda$tryAdvance$0(Object e) {
            }

            @Override // java.util.Spliterator
            public void forEachRemaining(Consumer<? super T> action) {
                Objects.requireNonNull(action);
                if (this.sliceOrigin >= this.fence || this.index >= this.fence) {
                    return;
                }
                if (this.index < this.sliceOrigin || this.index + this.s.estimateSize() > this.sliceFence) {
                    while (this.sliceOrigin > this.index) {
                        this.s.tryAdvance($$Lambda$StreamSpliterators$SliceSpliterator$OfRef$NUGTWbZg9cfpPm623I8CORYtfns.INSTANCE);
                        this.index++;
                    }
                    while (this.index < this.fence) {
                        this.s.tryAdvance(action);
                        this.index++;
                    }
                    return;
                }
                this.s.forEachRemaining(action);
                this.index = this.fence;
            }

            static /* synthetic */ void lambda$forEachRemaining$1(Object e) {
            }
        }

        static abstract class OfPrimitive<T, T_SPLITR extends Spliterator.OfPrimitive<T, T_CONS, T_SPLITR>, T_CONS> extends SliceSpliterator<T, T_SPLITR> implements Spliterator.OfPrimitive<T, T_CONS, T_SPLITR> {
            /* access modifiers changed from: protected */
            public abstract T_CONS emptyConsumer();

            @Override // java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.stream.StreamSpliterators.SliceSpliterator, java.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
                return (Spliterator.OfPrimitive) super.trySplit();
            }

            OfPrimitive(T_SPLITR s, long sliceOrigin, long sliceFence) {
                this(s, sliceOrigin, sliceFence, 0, Math.min(s.estimateSize(), sliceFence));
            }

            private OfPrimitive(T_SPLITR s, long sliceOrigin, long sliceFence, long origin, long fence) {
                super(s, sliceOrigin, sliceFence, origin, fence);
            }

            @Override // java.util.Spliterator.OfPrimitive
            public boolean tryAdvance(T_CONS action) {
                Objects.requireNonNull(action);
                if (this.sliceOrigin >= this.fence) {
                    return false;
                }
                while (this.sliceOrigin > this.index) {
                    ((Spliterator.OfPrimitive) this.s).tryAdvance((Object) emptyConsumer());
                    this.index++;
                }
                if (this.index >= this.fence) {
                    return false;
                }
                this.index++;
                return ((Spliterator.OfPrimitive) this.s).tryAdvance((Object) action);
            }

            @Override // java.util.Spliterator.OfPrimitive
            public void forEachRemaining(T_CONS action) {
                Objects.requireNonNull(action);
                if (this.sliceOrigin >= this.fence || this.index >= this.fence) {
                    return;
                }
                if (this.index < this.sliceOrigin || this.index + ((Spliterator.OfPrimitive) this.s).estimateSize() > this.sliceFence) {
                    while (this.sliceOrigin > this.index) {
                        ((Spliterator.OfPrimitive) this.s).tryAdvance((Object) emptyConsumer());
                        this.index++;
                    }
                    while (this.index < this.fence) {
                        ((Spliterator.OfPrimitive) this.s).tryAdvance((Object) action);
                        this.index++;
                    }
                    return;
                }
                ((Spliterator.OfPrimitive) this.s).forEachRemaining((Object) action);
                this.index = this.fence;
            }
        }

        /* access modifiers changed from: package-private */
        public static final class OfInt extends OfPrimitive<Integer, Spliterator.OfInt, IntConsumer> implements Spliterator.OfInt {
            @Override // java.util.Spliterator.OfInt
            public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
                super.forEachRemaining((Object) intConsumer);
            }

            @Override // java.util.Spliterator.OfInt
            public /* bridge */ /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
                return super.tryAdvance((Object) intConsumer);
            }

            @Override // java.util.Spliterator.OfInt, java.util.Spliterator.OfInt, java.util.Spliterator.OfInt, java.util.stream.StreamSpliterators.SliceSpliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.stream.StreamSpliterators.SliceSpliterator, java.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfInt trySplit() {
                return (Spliterator.OfInt) super.trySplit();
            }

            OfInt(Spliterator.OfInt s, long sliceOrigin, long sliceFence) {
                super(s, sliceOrigin, sliceFence);
            }

            OfInt(Spliterator.OfInt s, long sliceOrigin, long sliceFence, long origin, long fence) {
                super(s, sliceOrigin, sliceFence, origin, fence);
            }

            /* access modifiers changed from: protected */
            public Spliterator.OfInt makeSpliterator(Spliterator.OfInt s, long sliceOrigin, long sliceFence, long origin, long fence) {
                return new OfInt(s, sliceOrigin, sliceFence, origin, fence);
            }

            static /* synthetic */ void lambda$emptyConsumer$0(int e) {
            }

            /* access modifiers changed from: protected */
            @Override // java.util.stream.StreamSpliterators.SliceSpliterator.OfPrimitive
            public IntConsumer emptyConsumer() {
                return $$Lambda$StreamSpliterators$SliceSpliterator$OfInt$GDCU9wlqIN8fnp3lkzlBdIGmvc.INSTANCE;
            }
        }

        /* access modifiers changed from: package-private */
        public static final class OfLong extends OfPrimitive<Long, Spliterator.OfLong, LongConsumer> implements Spliterator.OfLong {
            @Override // java.util.Spliterator.OfLong
            public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
                super.forEachRemaining((Object) longConsumer);
            }

            @Override // java.util.Spliterator.OfLong
            public /* bridge */ /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
                return super.tryAdvance((Object) longConsumer);
            }

            @Override // java.util.stream.StreamSpliterators.SliceSpliterator.OfPrimitive, java.util.Spliterator.OfLong, java.util.Spliterator.OfLong, java.util.Spliterator.OfLong, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.stream.StreamSpliterators.SliceSpliterator, java.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfLong trySplit() {
                return (Spliterator.OfLong) super.trySplit();
            }

            OfLong(Spliterator.OfLong s, long sliceOrigin, long sliceFence) {
                super(s, sliceOrigin, sliceFence);
            }

            OfLong(Spliterator.OfLong s, long sliceOrigin, long sliceFence, long origin, long fence) {
                super(s, sliceOrigin, sliceFence, origin, fence);
            }

            /* access modifiers changed from: protected */
            public Spliterator.OfLong makeSpliterator(Spliterator.OfLong s, long sliceOrigin, long sliceFence, long origin, long fence) {
                return new OfLong(s, sliceOrigin, sliceFence, origin, fence);
            }

            static /* synthetic */ void lambda$emptyConsumer$0(long e) {
            }

            /* access modifiers changed from: protected */
            @Override // java.util.stream.StreamSpliterators.SliceSpliterator.OfPrimitive
            public LongConsumer emptyConsumer() {
                return $$Lambda$StreamSpliterators$SliceSpliterator$OfLong$gbTno_el7bKUjUiBqsBq7RYjcY8.INSTANCE;
            }
        }

        /* access modifiers changed from: package-private */
        public static final class OfDouble extends OfPrimitive<Double, Spliterator.OfDouble, DoubleConsumer> implements Spliterator.OfDouble {
            @Override // java.util.Spliterator.OfDouble
            public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
                super.forEachRemaining((Object) doubleConsumer);
            }

            @Override // java.util.Spliterator.OfDouble
            public /* bridge */ /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
                return super.tryAdvance((Object) doubleConsumer);
            }

            @Override // java.util.stream.StreamSpliterators.SliceSpliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfDouble, java.util.Spliterator.OfDouble, java.util.Spliterator.OfDouble, java.util.stream.StreamSpliterators.SliceSpliterator, java.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfDouble trySplit() {
                return (Spliterator.OfDouble) super.trySplit();
            }

            OfDouble(Spliterator.OfDouble s, long sliceOrigin, long sliceFence) {
                super(s, sliceOrigin, sliceFence);
            }

            OfDouble(Spliterator.OfDouble s, long sliceOrigin, long sliceFence, long origin, long fence) {
                super(s, sliceOrigin, sliceFence, origin, fence);
            }

            /* access modifiers changed from: protected */
            public Spliterator.OfDouble makeSpliterator(Spliterator.OfDouble s, long sliceOrigin, long sliceFence, long origin, long fence) {
                return new OfDouble(s, sliceOrigin, sliceFence, origin, fence);
            }

            static /* synthetic */ void lambda$emptyConsumer$0(double e) {
            }

            /* access modifiers changed from: protected */
            @Override // java.util.stream.StreamSpliterators.SliceSpliterator.OfPrimitive
            public DoubleConsumer emptyConsumer() {
                return $$Lambda$StreamSpliterators$SliceSpliterator$OfDouble$F1bBlpqcoM_HwaVPMQ3Q9zUwTCw.INSTANCE;
            }
        }
    }

    static abstract class UnorderedSliceSpliterator<T, T_SPLITR extends Spliterator<T>> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        static final int CHUNK_SIZE = 128;
        private final AtomicLong permits;
        protected final T_SPLITR s;
        private final long skipThreshold;
        protected final boolean unlimited;

        /* access modifiers changed from: package-private */
        public enum PermitStatus {
            NO_MORE,
            MAYBE_MORE,
            UNLIMITED
        }

        /* access modifiers changed from: protected */
        public abstract T_SPLITR makeSpliterator(T_SPLITR t_splitr);

        UnorderedSliceSpliterator(T_SPLITR s2, long skip, long limit) {
            this.s = s2;
            this.unlimited = limit < 0;
            this.skipThreshold = limit >= 0 ? limit : 0;
            this.permits = new AtomicLong(limit >= 0 ? skip + limit : skip);
        }

        UnorderedSliceSpliterator(T_SPLITR s2, UnorderedSliceSpliterator<T, T_SPLITR> parent) {
            this.s = s2;
            this.unlimited = parent.unlimited;
            this.permits = parent.permits;
            this.skipThreshold = parent.skipThreshold;
        }

        /* access modifiers changed from: protected */
        public final long acquirePermits(long numElements) {
            long remainingPermits;
            long grabbing;
            do {
                remainingPermits = this.permits.get();
                if (remainingPermits != 0) {
                    grabbing = Math.min(remainingPermits, numElements);
                    if (grabbing <= 0) {
                        break;
                    }
                } else if (this.unlimited) {
                    return numElements;
                } else {
                    return 0;
                }
            } while (!this.permits.compareAndSet(remainingPermits, remainingPermits - grabbing));
            if (this.unlimited) {
                return Math.max(numElements - grabbing, 0L);
            }
            long j = this.skipThreshold;
            if (remainingPermits > j) {
                return Math.max(grabbing - (remainingPermits - j), 0L);
            }
            return grabbing;
        }

        /* access modifiers changed from: protected */
        public final PermitStatus permitStatus() {
            if (this.permits.get() > 0) {
                return PermitStatus.MAYBE_MORE;
            }
            return this.unlimited ? PermitStatus.UNLIMITED : PermitStatus.NO_MORE;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.util.stream.StreamSpliterators$UnorderedSliceSpliterator<T, T_SPLITR extends java.util.Spliterator<T>> */
        /* JADX WARN: Multi-variable type inference failed */
        public final T_SPLITR trySplit() {
            Spliterator<T> trySplit;
            if (this.permits.get() == 0 || (trySplit = this.s.trySplit()) == null) {
                return null;
            }
            return (T_SPLITR) makeSpliterator(trySplit);
        }

        public final long estimateSize() {
            return this.s.estimateSize();
        }

        public final int characteristics() {
            return this.s.characteristics() & -16465;
        }

        /* access modifiers changed from: package-private */
        public static final class OfRef<T> extends UnorderedSliceSpliterator<T, Spliterator<T>> implements Spliterator<T>, Consumer<T> {
            T tmpSlot;

            OfRef(Spliterator<T> s, long skip, long limit) {
                super(s, skip, limit);
            }

            OfRef(Spliterator<T> s, OfRef<T> parent) {
                super(s, parent);
            }

            @Override // java.util.function.Consumer
            public final void accept(T t) {
                this.tmpSlot = t;
            }

            @Override // java.util.Spliterator
            public boolean tryAdvance(Consumer<? super T> action) {
                Objects.requireNonNull(action);
                while (permitStatus() != PermitStatus.NO_MORE && this.s.tryAdvance(this)) {
                    if (acquirePermits(1) == 1) {
                        action.accept(this.tmpSlot);
                        this.tmpSlot = null;
                        return true;
                    }
                }
                return false;
            }

            @Override // java.util.Spliterator
            public void forEachRemaining(Consumer<? super T> action) {
                Objects.requireNonNull(action);
                ArrayBuffer.OfRef<T> sb = null;
                while (true) {
                    PermitStatus permitStatus = permitStatus();
                    if (permitStatus == PermitStatus.NO_MORE) {
                        return;
                    }
                    if (permitStatus == PermitStatus.MAYBE_MORE) {
                        if (sb == null) {
                            sb = new ArrayBuffer.OfRef<>(128);
                        } else {
                            sb.reset();
                        }
                        long permitsRequested = 0;
                        while (this.s.tryAdvance(sb)) {
                            long j = 1 + permitsRequested;
                            permitsRequested = j;
                            if (j >= 128) {
                                break;
                            }
                        }
                        if (permitsRequested != 0) {
                            sb.forEach(action, acquirePermits(permitsRequested));
                        } else {
                            return;
                        }
                    } else {
                        this.s.forEachRemaining(action);
                        return;
                    }
                }
            }

            /* access modifiers changed from: protected */
            @Override // java.util.stream.StreamSpliterators.UnorderedSliceSpliterator
            public Spliterator<T> makeSpliterator(Spliterator<T> s) {
                return new OfRef(s, this);
            }
        }

        static abstract class OfPrimitive<T, T_CONS, T_BUFF extends ArrayBuffer.OfPrimitive<T_CONS>, T_SPLITR extends Spliterator.OfPrimitive<T, T_CONS, T_SPLITR>> extends UnorderedSliceSpliterator<T, T_SPLITR> implements Spliterator.OfPrimitive<T, T_CONS, T_SPLITR> {
            /* access modifiers changed from: protected */
            public abstract void acceptConsumed(T_CONS t_cons);

            /* access modifiers changed from: protected */
            public abstract T_BUFF bufferCreate(int i);

            @Override // java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.stream.StreamSpliterators.UnorderedSliceSpliterator, java.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
                return (Spliterator.OfPrimitive) super.trySplit();
            }

            OfPrimitive(T_SPLITR s, long skip, long limit) {
                super(s, skip, limit);
            }

            OfPrimitive(T_SPLITR s, OfPrimitive<T, T_CONS, T_BUFF, T_SPLITR> parent) {
                super(s, parent);
            }

            @Override // java.util.Spliterator.OfPrimitive
            public boolean tryAdvance(T_CONS action) {
                Objects.requireNonNull(action);
                while (permitStatus() != PermitStatus.NO_MORE && ((Spliterator.OfPrimitive) this.s).tryAdvance(this)) {
                    if (acquirePermits(1) == 1) {
                        acceptConsumed(action);
                        return true;
                    }
                }
                return false;
            }

            @Override // java.util.Spliterator.OfPrimitive
            public void forEachRemaining(T_CONS action) {
                Objects.requireNonNull(action);
                T_BUFF sb = null;
                while (true) {
                    PermitStatus permitStatus = permitStatus();
                    if (permitStatus == PermitStatus.NO_MORE) {
                        return;
                    }
                    if (permitStatus == PermitStatus.MAYBE_MORE) {
                        if (sb == null) {
                            sb = bufferCreate(128);
                        } else {
                            sb.reset();
                        }
                        long permitsRequested = 0;
                        while (((Spliterator.OfPrimitive) this.s).tryAdvance(sb)) {
                            long j = 1 + permitsRequested;
                            permitsRequested = j;
                            if (j >= 128) {
                                break;
                            }
                        }
                        if (permitsRequested != 0) {
                            sb.forEach(action, acquirePermits(permitsRequested));
                        } else {
                            return;
                        }
                    } else {
                        ((Spliterator.OfPrimitive) this.s).forEachRemaining((Object) action);
                        return;
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public static final class OfInt extends OfPrimitive<Integer, IntConsumer, ArrayBuffer.OfInt, Spliterator.OfInt> implements Spliterator.OfInt, IntConsumer {
            int tmpValue;

            @Override // java.util.Spliterator.OfInt
            public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
                super.forEachRemaining((Object) intConsumer);
            }

            @Override // java.util.Spliterator.OfInt
            public /* bridge */ /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
                return super.tryAdvance((Object) intConsumer);
            }

            @Override // java.util.stream.StreamSpliterators.UnorderedSliceSpliterator.OfPrimitive, java.util.Spliterator.OfInt, java.util.Spliterator.OfInt, java.util.Spliterator.OfInt, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.stream.StreamSpliterators.UnorderedSliceSpliterator, java.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfInt trySplit() {
                return (Spliterator.OfInt) super.trySplit();
            }

            OfInt(Spliterator.OfInt s, long skip, long limit) {
                super(s, skip, limit);
            }

            OfInt(Spliterator.OfInt s, OfInt parent) {
                super(s, parent);
            }

            @Override // java.util.function.IntConsumer
            public void accept(int value) {
                this.tmpValue = value;
            }

            /* access modifiers changed from: protected */
            public void acceptConsumed(IntConsumer action) {
                action.accept(this.tmpValue);
            }

            /* access modifiers changed from: protected */
            @Override // java.util.stream.StreamSpliterators.UnorderedSliceSpliterator.OfPrimitive
            public ArrayBuffer.OfInt bufferCreate(int initialCapacity) {
                return new ArrayBuffer.OfInt(initialCapacity);
            }

            /* access modifiers changed from: protected */
            public Spliterator.OfInt makeSpliterator(Spliterator.OfInt s) {
                return new OfInt(s, this);
            }
        }

        /* access modifiers changed from: package-private */
        public static final class OfLong extends OfPrimitive<Long, LongConsumer, ArrayBuffer.OfLong, Spliterator.OfLong> implements Spliterator.OfLong, LongConsumer {
            long tmpValue;

            @Override // java.util.Spliterator.OfLong
            public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
                super.forEachRemaining((Object) longConsumer);
            }

            @Override // java.util.Spliterator.OfLong
            public /* bridge */ /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
                return super.tryAdvance((Object) longConsumer);
            }

            @Override // java.util.stream.StreamSpliterators.UnorderedSliceSpliterator.OfPrimitive, java.util.Spliterator.OfLong, java.util.Spliterator.OfLong, java.util.Spliterator.OfLong, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.stream.StreamSpliterators.UnorderedSliceSpliterator, java.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfLong trySplit() {
                return (Spliterator.OfLong) super.trySplit();
            }

            OfLong(Spliterator.OfLong s, long skip, long limit) {
                super(s, skip, limit);
            }

            OfLong(Spliterator.OfLong s, OfLong parent) {
                super(s, parent);
            }

            @Override // java.util.function.LongConsumer
            public void accept(long value) {
                this.tmpValue = value;
            }

            /* access modifiers changed from: protected */
            public void acceptConsumed(LongConsumer action) {
                action.accept(this.tmpValue);
            }

            /* access modifiers changed from: protected */
            @Override // java.util.stream.StreamSpliterators.UnorderedSliceSpliterator.OfPrimitive
            public ArrayBuffer.OfLong bufferCreate(int initialCapacity) {
                return new ArrayBuffer.OfLong(initialCapacity);
            }

            /* access modifiers changed from: protected */
            public Spliterator.OfLong makeSpliterator(Spliterator.OfLong s) {
                return new OfLong(s, this);
            }
        }

        /* access modifiers changed from: package-private */
        public static final class OfDouble extends OfPrimitive<Double, DoubleConsumer, ArrayBuffer.OfDouble, Spliterator.OfDouble> implements Spliterator.OfDouble, DoubleConsumer {
            double tmpValue;

            @Override // java.util.Spliterator.OfDouble
            public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
                super.forEachRemaining((Object) doubleConsumer);
            }

            @Override // java.util.Spliterator.OfDouble
            public /* bridge */ /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
                return super.tryAdvance((Object) doubleConsumer);
            }

            @Override // java.util.stream.StreamSpliterators.UnorderedSliceSpliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfDouble, java.util.Spliterator.OfDouble, java.util.Spliterator.OfDouble, java.util.stream.StreamSpliterators.UnorderedSliceSpliterator, java.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfDouble trySplit() {
                return (Spliterator.OfDouble) super.trySplit();
            }

            OfDouble(Spliterator.OfDouble s, long skip, long limit) {
                super(s, skip, limit);
            }

            OfDouble(Spliterator.OfDouble s, OfDouble parent) {
                super(s, parent);
            }

            @Override // java.util.function.DoubleConsumer
            public void accept(double value) {
                this.tmpValue = value;
            }

            /* access modifiers changed from: protected */
            public void acceptConsumed(DoubleConsumer action) {
                action.accept(this.tmpValue);
            }

            /* access modifiers changed from: protected */
            @Override // java.util.stream.StreamSpliterators.UnorderedSliceSpliterator.OfPrimitive
            public ArrayBuffer.OfDouble bufferCreate(int initialCapacity) {
                return new ArrayBuffer.OfDouble(initialCapacity);
            }

            /* access modifiers changed from: protected */
            public Spliterator.OfDouble makeSpliterator(Spliterator.OfDouble s) {
                return new OfDouble(s, this);
            }
        }
    }

    static final class DistinctSpliterator<T> implements Spliterator<T>, Consumer<T> {
        private static final Object NULL_VALUE = new Object();
        private final Spliterator<T> s;
        private final ConcurrentHashMap<T, Boolean> seen;
        private T tmpSlot;

        DistinctSpliterator(Spliterator<T> s2) {
            this(s2, new ConcurrentHashMap());
        }

        private DistinctSpliterator(Spliterator<T> s2, ConcurrentHashMap<T, Boolean> seen2) {
            this.s = s2;
            this.seen = seen2;
        }

        @Override // java.util.function.Consumer
        public void accept(T t) {
            this.tmpSlot = t;
        }

        private T mapNull(T t) {
            return t != null ? t : (T) NULL_VALUE;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super T> action) {
            while (this.s.tryAdvance(this)) {
                if (this.seen.putIfAbsent(mapNull(this.tmpSlot), Boolean.TRUE) == null) {
                    action.accept(this.tmpSlot);
                    this.tmpSlot = null;
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super T> action) {
            this.s.forEachRemaining(new Consumer(action) {
                /* class java.util.stream.$$Lambda$StreamSpliterators$DistinctSpliterator$ojMHxa6O4MX3G2cGvIRG3GI58 */
                private final /* synthetic */ Consumer f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    StreamSpliterators.DistinctSpliterator.this.lambda$forEachRemaining$0$StreamSpliterators$DistinctSpliterator(this.f$1, obj);
                }
            });
        }

        /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: java.lang.Object */
        /* JADX WARN: Multi-variable type inference failed */
        public /* synthetic */ void lambda$forEachRemaining$0$StreamSpliterators$DistinctSpliterator(Consumer action, Object t) {
            if (this.seen.putIfAbsent(mapNull(t), Boolean.TRUE) == null) {
                action.accept(t);
            }
        }

        @Override // java.util.Spliterator
        public Spliterator<T> trySplit() {
            Spliterator<T> split = this.s.trySplit();
            if (split != null) {
                return new DistinctSpliterator(split, this.seen);
            }
            return null;
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.s.estimateSize();
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return (this.s.characteristics() & -16469) | 1;
        }

        @Override // java.util.Spliterator
        public Comparator<? super T> getComparator() {
            return this.s.getComparator();
        }
    }

    static abstract class InfiniteSupplyingSpliterator<T> implements Spliterator<T> {
        long estimate;

        protected InfiniteSupplyingSpliterator(long estimate2) {
            this.estimate = estimate2;
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.estimate;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 1024;
        }

        static final class OfRef<T> extends InfiniteSupplyingSpliterator<T> {
            final Supplier<T> s;

            OfRef(long size, Supplier<T> s2) {
                super(size);
                this.s = s2;
            }

            @Override // java.util.Spliterator
            public boolean tryAdvance(Consumer<? super T> action) {
                Objects.requireNonNull(action);
                action.accept(this.s.get());
                return true;
            }

            @Override // java.util.Spliterator
            public Spliterator<T> trySplit() {
                if (this.estimate == 0) {
                    return null;
                }
                long j = this.estimate >>> 1;
                this.estimate = j;
                return new OfRef(j, this.s);
            }
        }

        static final class OfInt extends InfiniteSupplyingSpliterator<Integer> implements Spliterator.OfInt {
            final IntSupplier s;

            OfInt(long size, IntSupplier s2) {
                super(size);
                this.s = s2;
            }

            @Override // java.util.Spliterator.OfInt
            public boolean tryAdvance(IntConsumer action) {
                Objects.requireNonNull(action);
                action.accept(this.s.getAsInt());
                return true;
            }

            @Override // java.util.Spliterator.OfInt, java.util.Spliterator.OfInt, java.util.Spliterator.OfInt, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator
            public Spliterator.OfInt trySplit() {
                if (this.estimate == 0) {
                    return null;
                }
                long j = this.estimate >>> 1;
                this.estimate = j;
                return new OfInt(j, this.s);
            }
        }

        static final class OfLong extends InfiniteSupplyingSpliterator<Long> implements Spliterator.OfLong {
            final LongSupplier s;

            OfLong(long size, LongSupplier s2) {
                super(size);
                this.s = s2;
            }

            @Override // java.util.Spliterator.OfLong
            public boolean tryAdvance(LongConsumer action) {
                Objects.requireNonNull(action);
                action.accept(this.s.getAsLong());
                return true;
            }

            @Override // java.util.Spliterator.OfLong, java.util.Spliterator.OfLong, java.util.Spliterator.OfLong, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator
            public Spliterator.OfLong trySplit() {
                if (this.estimate == 0) {
                    return null;
                }
                long j = this.estimate >>> 1;
                this.estimate = j;
                return new OfLong(j, this.s);
            }
        }

        static final class OfDouble extends InfiniteSupplyingSpliterator<Double> implements Spliterator.OfDouble {
            final DoubleSupplier s;

            OfDouble(long size, DoubleSupplier s2) {
                super(size);
                this.s = s2;
            }

            @Override // java.util.Spliterator.OfDouble
            public boolean tryAdvance(DoubleConsumer action) {
                Objects.requireNonNull(action);
                action.accept(this.s.getAsDouble());
                return true;
            }

            @Override // java.util.Spliterator.OfDouble, java.util.Spliterator.OfDouble, java.util.Spliterator.OfDouble, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator
            public Spliterator.OfDouble trySplit() {
                if (this.estimate == 0) {
                    return null;
                }
                long j = this.estimate >>> 1;
                this.estimate = j;
                return new OfDouble(j, this.s);
            }
        }
    }

    static abstract class ArrayBuffer {
        int index;

        ArrayBuffer() {
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            this.index = 0;
        }

        static final class OfRef<T> extends ArrayBuffer implements Consumer<T> {
            final Object[] array;

            OfRef(int size) {
                this.array = new Object[size];
            }

            @Override // java.util.function.Consumer
            public void accept(T t) {
                Object[] objArr = this.array;
                int i = this.index;
                this.index = i + 1;
                objArr[i] = t;
            }

            public void forEach(Consumer<? super T> action, long fence) {
                for (int i = 0; ((long) i) < fence; i++) {
                    action.accept(this.array[i]);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public static abstract class OfPrimitive<T_CONS> extends ArrayBuffer {
            int index;

            /* access modifiers changed from: package-private */
            public abstract void forEach(T_CONS t_cons, long j);

            OfPrimitive() {
            }

            /* access modifiers changed from: package-private */
            @Override // java.util.stream.StreamSpliterators.ArrayBuffer
            public void reset() {
                this.index = 0;
            }
        }

        /* access modifiers changed from: package-private */
        public static final class OfInt extends OfPrimitive<IntConsumer> implements IntConsumer {
            final int[] array;

            OfInt(int size) {
                this.array = new int[size];
            }

            @Override // java.util.function.IntConsumer
            public void accept(int t) {
                int[] iArr = this.array;
                int i = this.index;
                this.index = i + 1;
                iArr[i] = t;
            }

            public void forEach(IntConsumer action, long fence) {
                for (int i = 0; ((long) i) < fence; i++) {
                    action.accept(this.array[i]);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public static final class OfLong extends OfPrimitive<LongConsumer> implements LongConsumer {
            final long[] array;

            OfLong(int size) {
                this.array = new long[size];
            }

            @Override // java.util.function.LongConsumer
            public void accept(long t) {
                long[] jArr = this.array;
                int i = this.index;
                this.index = i + 1;
                jArr[i] = t;
            }

            public void forEach(LongConsumer action, long fence) {
                for (int i = 0; ((long) i) < fence; i++) {
                    action.accept(this.array[i]);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public static final class OfDouble extends OfPrimitive<DoubleConsumer> implements DoubleConsumer {
            final double[] array;

            OfDouble(int size) {
                this.array = new double[size];
            }

            @Override // java.util.function.DoubleConsumer
            public void accept(double t) {
                double[] dArr = this.array;
                int i = this.index;
                this.index = i + 1;
                dArr[i] = t;
            }

            /* access modifiers changed from: package-private */
            public void forEach(DoubleConsumer action, long fence) {
                for (int i = 0; ((long) i) < fence; i++) {
                    action.accept(this.array[i]);
                }
            }
        }
    }
}
