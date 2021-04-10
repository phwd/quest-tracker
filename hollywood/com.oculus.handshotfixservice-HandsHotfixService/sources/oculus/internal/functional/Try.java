package oculus.internal.functional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import oculus.internal.functional.Try;

public final class Try<V> {
    private final Optional<Exception> _failure;
    private final Optional<V> _success;

    @FunctionalInterface
    public interface F0<R> {
        R get() throws Exception;
    }

    @FunctionalInterface
    public interface F1<Y, R> {
        R get(Y y) throws Exception;
    }

    public static <T> Try<T> Try(F0<T> f) {
        try {
            return success(f.get());
        } catch (Exception ex) {
            return fail(ex);
        }
    }

    public static <T, A, R> Collector<Try<T>, ?, Try<R>> fold(Collector<? super T, A, ? extends R> downstream) {
        final Supplier<A> downstreamSupplier = downstream.supplier();
        final BiConsumer<A, ? super T> downstreamAccumulator = downstream.accumulator();
        final BinaryOperator<A> downstreamCombiner = downstream.combiner();
        final Function<A, ? extends R> downstreamFinisher = downstream.finisher();
        final Set<Collector.Characteristics> characteristics = new HashSet<>();
        characteristics.addAll(downstream.characteristics());
        characteristics.remove(Collector.Characteristics.IDENTITY_FINISH);
        return new Collector<Try<T>, Accumulator<A>, Try<R>>() {
            /* class oculus.internal.functional.Try.AnonymousClass1 */

            @Override // java.util.stream.Collector
            public Supplier<Accumulator<A>> supplier() {
                return new Supplier(downstreamSupplier) {
                    /* class oculus.internal.functional.$$Lambda$Try$1$oFfDBQZDWRlHWjAb_3GFomH1vA */
                    private final /* synthetic */ Supplier f$0;

                    {
                        this.f$0 = r1;
                    }

                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Try.AnonymousClass1.lambda$supplier$0(this.f$0);
                    }
                };
            }

            @Override // java.util.stream.Collector
            public BiConsumer<Accumulator<A>, Try<T>> accumulator() {
                return new BiConsumer(downstreamAccumulator) {
                    /* class oculus.internal.functional.$$Lambda$Try$1$CCQdwokJPaLeldfA3G43_nWdE */
                    private final /* synthetic */ BiConsumer f$0;

                    {
                        this.f$0 = r1;
                    }

                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        Try.AnonymousClass1.lambda$accumulator$1(this.f$0, (Try.Accumulator) obj, (Try) obj2);
                    }
                };
            }

            @Override // java.util.stream.Collector
            public BinaryOperator<Accumulator<A>> combiner() {
                return new BinaryOperator(downstreamCombiner) {
                    /* class oculus.internal.functional.$$Lambda$Try$1$f2q24sR9721uW0vZgaduAETinns */
                    private final /* synthetic */ BinaryOperator f$0;

                    {
                        this.f$0 = r1;
                    }

                    @Override // java.util.function.BiFunction
                    public final Object apply(Object obj, Object obj2) {
                        return Try.AnonymousClass1.lambda$combiner$2(this.f$0, (Try.Accumulator) obj, (Try.Accumulator) obj2);
                    }
                };
            }

            @Override // java.util.stream.Collector
            public Function<Accumulator<A>, Try<R>> finisher() {
                return new Function(downstreamFinisher) {
                    /* class oculus.internal.functional.$$Lambda$Try$1$YGlH0V4vLEVekremuhYCGz3F3oE */
                    private final /* synthetic */ Function f$0;

                    {
                        this.f$0 = r1;
                    }

                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return Try.AnonymousClass1.lambda$finisher$3(this.f$0, (Try.Accumulator) obj);
                    }
                };
            }

            @Override // java.util.stream.Collector
            public Set<Collector.Characteristics> characteristics() {
                return characteristics;
            }
        };
    }

    public static <T> Try<T> success(T value) {
        return new Try<>(Optional.empty(), Optional.of(value));
    }

    public static <T> Try<T> fail(Exception t) {
        return new Try<>(Optional.of(t), Optional.empty());
    }

    public boolean isFailure() {
        return this._failure.isPresent();
    }

    public boolean isSuccess() {
        return this._success.isPresent();
    }

    public V orElse(V defaultValue) {
        return this._success.orElse(defaultValue);
    }

    public V orElseGet(Supplier<? extends V> f) {
        return this._success.orElseGet(f);
    }

    public /* synthetic */ Exception lambda$orElseThrow$0$Try() {
        return this._failure.get();
    }

    public V orElseThrow() throws Exception {
        return this._success.orElseThrow(new Supplier() {
            /* class oculus.internal.functional.$$Lambda$Try$QpBkAORb9RrFE80u2kUil95iAS0 */

            @Override // java.util.function.Supplier
            public final Object get() {
                return Try.this.lambda$orElseThrow$0$Try();
            }
        });
    }

    static /* synthetic */ Try lambda$flatMap$1(Function f, Object val) {
        return (Try) f.apply(val);
    }

    public <X> Try<X> flatMap(Function<? super V, Try<X>> f) {
        return this._success.map(new Function(f) {
            /* class oculus.internal.functional.$$Lambda$Try$CPaSDPgLe6GDuP_gsZDabpkgQP4 */
            private final /* synthetic */ Function f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Try.lambda$flatMap$1(this.f$0, obj);
            }
        }).orElseGet(new Supplier() {
            /* class oculus.internal.functional.$$Lambda$Try$7dAqIpfRQ5p2ZlscB1kVltz4MI */

            @Override // java.util.function.Supplier
            public final Object get() {
                return Try.m0lambda$7dAqIpfRQ5p2ZlscB1kVltz4MI(Try.this);
            }
        });
    }

    public <X> Try<X> map(Function<? super V, ? extends X> f) {
        return flatMap(new Function(f) {
            /* class oculus.internal.functional.$$Lambda$Try$yUvco3J1R7ioIMaqmxnZ30F2NGM */
            private final /* synthetic */ Function f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Try.lambda$map$2(this.f$0, obj);
            }
        });
    }

    public <X> Try<X> tryMap(F1<? super V, ? extends X> f) {
        return flatMap(new Function() {
            /* class oculus.internal.functional.$$Lambda$Try$Uk3DPPLGskljWg3hRpqu7TniOSQ */

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Try.lambda$tryMap$4(Try.F1.this, obj);
            }
        });
    }

    public Try<V> onSuccess(Consumer<V> handler) {
        this._success.ifPresent(handler);
        return this;
    }

    public Try<V> onFailure(Consumer<Exception> handler) {
        this._failure.ifPresent(handler);
        return this;
    }

    /* access modifiers changed from: private */
    public <X> Try<X> asFailure() {
        return fail(this._failure.get());
    }

    private Try(Optional<Exception> failure, Optional<V> success) {
        this._failure = failure;
        this._success = success;
    }

    /* access modifiers changed from: private */
    public static class Accumulator<V> {
        private Optional<Exception> _failure;
        private Optional<V> _success;

        Accumulator(Optional<Exception> failure, Optional<V> success) {
            this._success = success;
            this._failure = failure;
        }

        public Try<V> toTry() {
            return new Try<>(this._failure, this._success);
        }

        public static <T> Accumulator<T> success(T value) {
            return new Accumulator<>(Optional.empty(), Optional.of(value));
        }

        public static <T> Accumulator<T> fail(Exception ex) {
            return new Accumulator<>(Optional.of(ex), Optional.empty());
        }

        static /* synthetic */ Accumulator lambda$flatMap$0(Function mapper, Object s) {
            return (Accumulator) mapper.apply(s);
        }

        /* access modifiers changed from: package-private */
        public <X> Accumulator<X> flatMap(Function<? super V, Accumulator<X>> mapper) {
            return this._success.map(new Function(mapper) {
                /* class oculus.internal.functional.$$Lambda$Try$Accumulator$XOowzfg2bvo1jTDxm2qSr2oM1tw */
                private final /* synthetic */ Function f$0;

                {
                    this.f$0 = r1;
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Try.Accumulator.lambda$flatMap$0(this.f$0, obj);
                }
            }).orElseGet(new Supplier() {
                /* class oculus.internal.functional.$$Lambda$Try$Accumulator$PePuQKzwDQIesONL4m7uu9kRA4 */

                @Override // java.util.function.Supplier
                public final Object get() {
                    return Try.Accumulator.this.lambda$flatMap$1$Try$Accumulator();
                }
            });
        }

        public /* synthetic */ Accumulator lambda$flatMap$1$Try$Accumulator() {
            return fail(this._failure.get());
        }

        /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: oculus.internal.functional.Try$Accumulator<X>, oculus.internal.functional.Try$Accumulator<V> */
        /* access modifiers changed from: package-private */
        public Accumulator<V> join(Accumulator<V> other, BinaryOperator<V> downstream) {
            return (Accumulator<X>) flatMap(new Function(downstream) {
                /* class oculus.internal.functional.$$Lambda$Try$Accumulator$26obkF9_xsrVfm6D7r0leZ00Mc */
                private final /* synthetic */ BinaryOperator f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Try.Accumulator.lambda$join$3(Try.Accumulator.this, this.f$1, obj);
                }
            });
        }

        /* access modifiers changed from: package-private */
        public <X> void accept(Try<X> other, BiConsumer<V, ? super X> f) {
            if (!this._failure.isPresent()) {
                if (other.isFailure()) {
                    this._success = Optional.empty();
                    this._failure = Optional.of((Exception) ((Try) other)._failure.get());
                    return;
                }
                f.accept(this._success.get(), ((Try) other)._success.get());
            }
        }
    }
}
