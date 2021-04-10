package java.util.stream;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.EnumSet;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public final class Collectors {
    static final Set<Collector.Characteristics> CH_CONCURRENT_ID = Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.CONCURRENT, Collector.Characteristics.UNORDERED, Collector.Characteristics.IDENTITY_FINISH));
    static final Set<Collector.Characteristics> CH_CONCURRENT_NOID = Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.CONCURRENT, Collector.Characteristics.UNORDERED));
    static final Set<Collector.Characteristics> CH_ID = Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
    static final Set<Collector.Characteristics> CH_NOID = Collections.emptySet();
    static final Set<Collector.Characteristics> CH_UNORDERED_ID = Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.UNORDERED, Collector.Characteristics.IDENTITY_FINISH));

    private Collectors() {
    }

    static /* synthetic */ Object lambda$throwingMerger$0(Object u, Object v) {
        throw new IllegalStateException(String.format("Duplicate key %s", u));
    }

    private static <T> BinaryOperator<T> throwingMerger() {
        return $$Lambda$Collectors$kXZFmh6iM6xf9lJWimhd2Ef6NEs.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static <I, R> Function<I, R> castingIdentity() {
        return $$Lambda$Collectors$f0IPpRuyw9HZC8FIP30mNjUUUhw.INSTANCE;
    }

    static /* synthetic */ Object lambda$castingIdentity$1(Object i) {
        return i;
    }

    /* access modifiers changed from: package-private */
    public static class CollectorImpl<T, A, R> implements Collector<T, A, R> {
        private final BiConsumer<A, T> accumulator;
        private final Set<Collector.Characteristics> characteristics;
        private final BinaryOperator<A> combiner;
        private final Function<A, R> finisher;
        private final Supplier<A> supplier;

        CollectorImpl(Supplier<A> supplier2, BiConsumer<A, T> accumulator2, BinaryOperator<A> combiner2, Function<A, R> finisher2, Set<Collector.Characteristics> characteristics2) {
            this.supplier = supplier2;
            this.accumulator = accumulator2;
            this.combiner = combiner2;
            this.finisher = finisher2;
            this.characteristics = characteristics2;
        }

        CollectorImpl(Supplier<A> supplier2, BiConsumer<A, T> accumulator2, BinaryOperator<A> combiner2, Set<Collector.Characteristics> characteristics2) {
            this(supplier2, accumulator2, combiner2, Collectors.castingIdentity(), characteristics2);
        }

        @Override // java.util.stream.Collector
        public BiConsumer<A, T> accumulator() {
            return this.accumulator;
        }

        @Override // java.util.stream.Collector
        public Supplier<A> supplier() {
            return this.supplier;
        }

        @Override // java.util.stream.Collector
        public BinaryOperator<A> combiner() {
            return this.combiner;
        }

        @Override // java.util.stream.Collector
        public Function<A, R> finisher() {
            return this.finisher;
        }

        @Override // java.util.stream.Collector
        public Set<Collector.Characteristics> characteristics() {
            return this.characteristics;
        }
    }

    public static <T, C extends Collection<T>> Collector<T, ?, C> toCollection(Supplier<C> collectionFactory) {
        return new CollectorImpl(collectionFactory, $$Lambda$sKPaOkcQePGTRevrwmKVVhCTmTo.INSTANCE, $$Lambda$Collectors$iab6bVO3ydceISRiUEq_MRHYzoU.INSTANCE, CH_ID);
    }

    public static <T> Collector<T, ?, List<T>> toList() {
        return new CollectorImpl($$Lambda$yTqQxkqu88ZhKI6fWaTTLwOLF60.INSTANCE, $$Lambda$ihOtgw0eLCrsEBOphyN7SwoAlDg.INSTANCE, $$Lambda$Collectors$0y_EMl863H_U7B4kxyGscB4vAag.INSTANCE, CH_ID);
    }

    public static <T> Collector<T, ?, Set<T>> toSet() {
        return new CollectorImpl($$Lambda$r8H_R_mZJjp9wd0XTLoEAHMNQ0.INSTANCE, $$Lambda$uJ6CkL42Bk73jN5EzP0Fx7o1eVA.INSTANCE, $$Lambda$Collectors$SMVdf7W0ks2OOmS3zJw7DHcNhc.INSTANCE, CH_UNORDERED_ID);
    }

    public static Collector<CharSequence, ?, String> joining() {
        return new CollectorImpl($$Lambda$cfwqIEDg0Z3A7MGDNrQPTyjrF9M.INSTANCE, $$Lambda$o8baRh54JSyOHAKgObeucNn1Zos.INSTANCE, $$Lambda$Collectors$Fu6GEjokQxdzbR0jNzU39PLUqs.INSTANCE, $$Lambda$02PZAQlwu7SKkigJ7EI4kdTzqnI.INSTANCE, CH_NOID);
    }

    public static Collector<CharSequence, ?, String> joining(CharSequence delimiter) {
        return joining(delimiter, "", "");
    }

    public static Collector<CharSequence, ?, String> joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix) {
        return new CollectorImpl(new Supplier(prefix, suffix) {
            /* class java.util.stream.$$Lambda$Collectors$pzPeDl3rCgtNVSeZPHZk5f2se60 */
            private final /* synthetic */ CharSequence f$1;
            private final /* synthetic */ CharSequence f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                return Collectors.lambda$joining$6(CharSequence.this, this.f$1, this.f$2);
            }
        }, $$Lambda$Drw47GGUtPrz9CklhlT0v26u5c.INSTANCE, $$Lambda$i0Jl5dMkfWphZviqg6QdkkWPWRI.INSTANCE, $$Lambda$okJigbB9kSn__oCZ5Do9uFNyF6A.INSTANCE, CH_NOID);
    }

    static /* synthetic */ StringJoiner lambda$joining$6(CharSequence delimiter, CharSequence prefix, CharSequence suffix) {
        return new StringJoiner(delimiter, prefix, suffix);
    }

    private static <K, V, M extends Map<K, V>> BinaryOperator<M> mapMerger(BinaryOperator<V> mergeFunction) {
        return new BinaryOperator() {
            /* class java.util.stream.$$Lambda$Collectors$TzSZZBK0laNSWMge_uuxANwkkMo */

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return Collectors.lambda$mapMerger$7(BinaryOperator.this, (Map) obj, (Map) obj2);
            }
        };
    }

    static /* synthetic */ Map lambda$mapMerger$7(BinaryOperator mergeFunction, Map m1, Map m2) {
        for (Map.Entry<K, V> e : m2.entrySet()) {
            m1.merge(e.getKey(), e.getValue(), mergeFunction);
        }
        return m1;
    }

    public static <T, U, A, R> Collector<T, ?, R> mapping(Function<? super T, ? extends U> mapper, Collector<? super U, A, R> downstream) {
        return new CollectorImpl(downstream.supplier(), new BiConsumer(mapper) {
            /* class java.util.stream.$$Lambda$Collectors$vmLceJDpkkH4HVeqPcL08DnO8yg */
            private final /* synthetic */ Function f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                BiConsumer.this.accept(obj, this.f$1.apply(obj2));
            }
        }, downstream.combiner(), downstream.finisher(), downstream.characteristics());
    }

    public static <T, A, R, RR> Collector<T, A, RR> collectingAndThen(Collector<T, A, R> downstream, Function<R, RR> finisher) {
        Set<Collector.Characteristics> characteristics = downstream.characteristics();
        if (characteristics.contains(Collector.Characteristics.IDENTITY_FINISH)) {
            if (characteristics.size() == 1) {
                characteristics = CH_NOID;
            } else {
                Set<Collector.Characteristics> characteristics2 = EnumSet.copyOf(characteristics);
                characteristics2.remove(Collector.Characteristics.IDENTITY_FINISH);
                characteristics = Collections.unmodifiableSet(characteristics2);
            }
        }
        return new CollectorImpl(downstream.supplier(), downstream.accumulator(), downstream.combiner(), downstream.finisher().andThen(finisher), characteristics);
    }

    public static <T> Collector<T, ?, Long> counting() {
        return reducing(0L, $$Lambda$Collectors$QJ1yoZI6qBLk2NHgWkdN8XN8yQ.INSTANCE, $$Lambda$R8aE88Z140TFfTli76Hdc3YzhU4.INSTANCE);
    }

    static /* synthetic */ Long lambda$counting$9(Object e) {
        return 1L;
    }

    public static <T> Collector<T, ?, Optional<T>> minBy(Comparator<? super T> comparator) {
        return reducing(BinaryOperator.minBy(comparator));
    }

    public static <T> Collector<T, ?, Optional<T>> maxBy(Comparator<? super T> comparator) {
        return reducing(BinaryOperator.maxBy(comparator));
    }

    public static <T> Collector<T, ?, Integer> summingInt(ToIntFunction<? super T> mapper) {
        return new CollectorImpl($$Lambda$Collectors$LdN_EEy4nb6271v6ZFmttzZvNwg.INSTANCE, new BiConsumer() {
            /* class java.util.stream.$$Lambda$Collectors$PkbZGUBauY6u1ZrRakcFQjTln0 */

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                Collectors.lambda$summingInt$11(ToIntFunction.this, (int[]) obj, obj2);
            }
        }, $$Lambda$Collectors$_Q0YP_0ibAqPtQetwby3vyMbjys.INSTANCE, $$Lambda$Collectors$dk4em1jXH8zNd7gZJwP7T1HgMsc.INSTANCE, CH_NOID);
    }

    static /* synthetic */ int[] lambda$summingInt$10() {
        return new int[1];
    }

    static /* synthetic */ void lambda$summingInt$11(ToIntFunction mapper, int[] a, Object t) {
        a[0] = a[0] + mapper.applyAsInt(t);
    }

    static /* synthetic */ int[] lambda$summingInt$12(int[] a, int[] b) {
        a[0] = a[0] + b[0];
        return a;
    }

    public static <T> Collector<T, ?, Long> summingLong(ToLongFunction<? super T> mapper) {
        return new CollectorImpl($$Lambda$Collectors$l57H1aRUHKWOcUoknXahrBTCBzM.INSTANCE, new BiConsumer() {
            /* class java.util.stream.$$Lambda$Collectors$jDXjrt99im0xRBROpkCAqoLkqR4 */

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                Collectors.lambda$summingLong$15(ToLongFunction.this, (long[]) obj, obj2);
            }
        }, $$Lambda$Collectors$efY8VAGaEUr4IwuGz_Jkpfl5lH8.INSTANCE, $$Lambda$Collectors$PH40qXgNc8jDetutxHAPBu2pw.INSTANCE, CH_NOID);
    }

    static /* synthetic */ long[] lambda$summingLong$14() {
        return new long[1];
    }

    static /* synthetic */ void lambda$summingLong$15(ToLongFunction mapper, long[] a, Object t) {
        a[0] = a[0] + mapper.applyAsLong(t);
    }

    static /* synthetic */ long[] lambda$summingLong$16(long[] a, long[] b) {
        a[0] = a[0] + b[0];
        return a;
    }

    public static <T> Collector<T, ?, Double> summingDouble(ToDoubleFunction<? super T> mapper) {
        return new CollectorImpl($$Lambda$Collectors$8qmKppCC_QJwGcQ9bUPPu3l5Bg.INSTANCE, new BiConsumer() {
            /* class java.util.stream.$$Lambda$Collectors$zuotCFMPpEd_pFOpcqCzvWNwmcE */

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                Collectors.lambda$summingDouble$19(ToDoubleFunction.this, (double[]) obj, obj2);
            }
        }, $$Lambda$Collectors$rc3oE9Q2XtMlukYSsVA6bDVPVLY.INSTANCE, $$Lambda$Collectors$cFIm0q5Hl8z7ocxLjJsCwiKceEI.INSTANCE, CH_NOID);
    }

    static /* synthetic */ double[] lambda$summingDouble$18() {
        return new double[3];
    }

    static /* synthetic */ void lambda$summingDouble$19(ToDoubleFunction mapper, double[] a, Object t) {
        sumWithCompensation(a, mapper.applyAsDouble(t));
        a[2] = a[2] + mapper.applyAsDouble(t);
    }

    static /* synthetic */ double[] lambda$summingDouble$20(double[] a, double[] b) {
        sumWithCompensation(a, b[0]);
        a[2] = a[2] + b[2];
        return sumWithCompensation(a, b[1]);
    }

    static double[] sumWithCompensation(double[] intermediateSum, double value) {
        double tmp = value - intermediateSum[1];
        double sum = intermediateSum[0];
        double velvel = sum + tmp;
        intermediateSum[1] = (velvel - sum) - tmp;
        intermediateSum[0] = velvel;
        return intermediateSum;
    }

    static double computeFinalSum(double[] summands) {
        double tmp = summands[0] + summands[1];
        double simpleSum = summands[summands.length - 1];
        if (!Double.isNaN(tmp) || !Double.isInfinite(simpleSum)) {
            return tmp;
        }
        return simpleSum;
    }

    public static <T> Collector<T, ?, Double> averagingInt(ToIntFunction<? super T> mapper) {
        return new CollectorImpl($$Lambda$Collectors$WTLZnkfYyw3lURYA1kkAz43vGhw.INSTANCE, new BiConsumer() {
            /* class java.util.stream.$$Lambda$Collectors$5sBFPk12YoFTd83smSoPj46DB_A */

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                Collectors.lambda$averagingInt$23(ToIntFunction.this, (long[]) obj, obj2);
            }
        }, $$Lambda$Collectors$ZKsdGxiJ4anQHtwhYmSwD0W3jAc.INSTANCE, $$Lambda$Collectors$8wf84PB5r3lvvidSVfjAcFbHXg.INSTANCE, CH_NOID);
    }

    static /* synthetic */ long[] lambda$averagingInt$22() {
        return new long[2];
    }

    static /* synthetic */ void lambda$averagingInt$23(ToIntFunction mapper, long[] a, Object t) {
        a[0] = a[0] + ((long) mapper.applyAsInt(t));
        a[1] = a[1] + 1;
    }

    static /* synthetic */ long[] lambda$averagingInt$24(long[] a, long[] b) {
        a[0] = a[0] + b[0];
        a[1] = a[1] + b[1];
        return a;
    }

    static /* synthetic */ Double lambda$averagingInt$25(long[] a) {
        return Double.valueOf(a[1] == 0 ? 0.0d : ((double) a[0]) / ((double) a[1]));
    }

    public static <T> Collector<T, ?, Double> averagingLong(ToLongFunction<? super T> mapper) {
        return new CollectorImpl($$Lambda$Collectors$bqboixg1w_MiqJsFAc1DEO_aI.INSTANCE, new BiConsumer() {
            /* class java.util.stream.$$Lambda$Collectors$AI86aRAtMJ_TFLEAdUERhggkI */

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                Collectors.lambda$averagingLong$27(ToLongFunction.this, (long[]) obj, obj2);
            }
        }, $$Lambda$Collectors$jubWCCHaIIJ_0UWKVLWqWh9ZDdk.INSTANCE, $$Lambda$Collectors$J_JAZ0tPN2SF0mqc6rtXc_aK03k.INSTANCE, CH_NOID);
    }

    static /* synthetic */ long[] lambda$averagingLong$26() {
        return new long[2];
    }

    static /* synthetic */ void lambda$averagingLong$27(ToLongFunction mapper, long[] a, Object t) {
        a[0] = a[0] + mapper.applyAsLong(t);
        a[1] = a[1] + 1;
    }

    static /* synthetic */ long[] lambda$averagingLong$28(long[] a, long[] b) {
        a[0] = a[0] + b[0];
        a[1] = a[1] + b[1];
        return a;
    }

    static /* synthetic */ Double lambda$averagingLong$29(long[] a) {
        return Double.valueOf(a[1] == 0 ? 0.0d : ((double) a[0]) / ((double) a[1]));
    }

    public static <T> Collector<T, ?, Double> averagingDouble(ToDoubleFunction<? super T> mapper) {
        return new CollectorImpl($$Lambda$Collectors$bL3PuvFDr85ZCgwmUxFPHSRIrgE.INSTANCE, new BiConsumer() {
            /* class java.util.stream.$$Lambda$Collectors$kPvxljbEEMQktNHySoxXCZ5cu4 */

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                Collectors.lambda$averagingDouble$31(ToDoubleFunction.this, (double[]) obj, obj2);
            }
        }, $$Lambda$Collectors$KLieyPxAsuZwFd2lh9gJpGlzE.INSTANCE, $$Lambda$Collectors$xmuLwoAlFtOfbPB5C1jdMenPWHE.INSTANCE, CH_NOID);
    }

    static /* synthetic */ double[] lambda$averagingDouble$30() {
        return new double[4];
    }

    static /* synthetic */ void lambda$averagingDouble$31(ToDoubleFunction mapper, double[] a, Object t) {
        sumWithCompensation(a, mapper.applyAsDouble(t));
        a[2] = a[2] + 1.0d;
        a[3] = a[3] + mapper.applyAsDouble(t);
    }

    static /* synthetic */ double[] lambda$averagingDouble$32(double[] a, double[] b) {
        sumWithCompensation(a, b[0]);
        sumWithCompensation(a, b[1]);
        a[2] = a[2] + b[2];
        a[3] = a[3] + b[3];
        return a;
    }

    static /* synthetic */ Double lambda$averagingDouble$33(double[] a) {
        double d = 0.0d;
        if (a[2] != 0.0d) {
            d = computeFinalSum(a) / a[2];
        }
        return Double.valueOf(d);
    }

    public static <T> Collector<T, ?, T> reducing(T identity, BinaryOperator<T> op) {
        return new CollectorImpl(boxSupplier(identity), new BiConsumer() {
            /* class java.util.stream.$$Lambda$Collectors$hPhWWwdsY4xpBbhcwNVuU8dcJDw */

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                Collectors.lambda$reducing$34(BinaryOperator.this, (Object[]) obj, obj2);
            }
        }, new BinaryOperator() {
            /* class java.util.stream.$$Lambda$Collectors$ad8ZNOGiSpsXDYHtABmtE9E1UA */

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return Collectors.lambda$reducing$35(BinaryOperator.this, (Object[]) obj, (Object[]) obj2);
            }
        }, $$Lambda$Collectors$il2gZbqqrAa81yW3okSBWeJddGk.INSTANCE, CH_NOID);
    }

    static /* synthetic */ void lambda$reducing$34(BinaryOperator op, Object[] a, Object t) {
        a[0] = op.apply(a[0], t);
    }

    static /* synthetic */ Object[] lambda$reducing$35(BinaryOperator op, Object[] a, Object[] b) {
        a[0] = op.apply(a[0], b[0]);
        return a;
    }

    static /* synthetic */ Object lambda$reducing$36(Object[] a) {
        return a[0];
    }

    private static <T> Supplier<T[]> boxSupplier(T identity) {
        return new Supplier() {
            /* class java.util.stream.$$Lambda$Collectors$EsYSkkQ7p405z0qsCjjJsUR3Wfk */

            @Override // java.util.function.Supplier
            public final Object get() {
                return Collectors.lambda$boxSupplier$37(Object.this);
            }
        };
    }

    static /* synthetic */ Object[] lambda$boxSupplier$37(Object identity) {
        return new Object[]{identity};
    }

    public static <T> Collector<T, ?, Optional<T>> reducing(BinaryOperator<T> op) {
        return new CollectorImpl(new Supplier() {
            /* class java.util.stream.$$Lambda$Collectors$BwzHl6O1mjAgxLE58ctIeFoVBAM */

            @Override // java.util.function.Supplier
            public final Object get() {
                return Collectors.lambda$reducing$38(BinaryOperator.this);
            }
        }, $$Lambda$S3JQ3ufQOqvkvYGc3Sza87R0.INSTANCE, $$Lambda$Collectors$o_hw0Qu0KMLozTwiY9jr8AKG0_0.INSTANCE, $$Lambda$Collectors$o91Aj5OVnHv9w5Hj_r9fFUMiDY.INSTANCE, CH_NOID);
    }

    static /* synthetic */ AnonymousClass1OptionalBox lambda$reducing$38(final BinaryOperator op) {
        return new Consumer<T>() {
            /* class java.util.stream.Collectors.AnonymousClass1OptionalBox */
            boolean present = false;
            T value = null;

            @Override // java.util.function.Consumer
            public void accept(T t) {
                if (this.present) {
                    this.value = (T) BinaryOperator.this.apply(this.value, t);
                    return;
                }
                this.value = t;
                this.present = true;
            }
        };
    }

    static /* synthetic */ AnonymousClass1OptionalBox lambda$reducing$39(AnonymousClass1OptionalBox a, AnonymousClass1OptionalBox b) {
        if (b.present) {
            a.accept(b.value);
        }
        return a;
    }

    public static <T, U> Collector<T, ?, U> reducing(U identity, Function<? super T, ? extends U> mapper, BinaryOperator<U> op) {
        return new CollectorImpl(boxSupplier(identity), new BiConsumer(mapper) {
            /* class java.util.stream.$$Lambda$Collectors$PTd6jsJ7t0481HRFfH8tnGifDqw */
            private final /* synthetic */ Function f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                Collectors.lambda$reducing$41(BinaryOperator.this, this.f$1, (Object[]) obj, obj2);
            }
        }, new BinaryOperator() {
            /* class java.util.stream.$$Lambda$Collectors$xWcI8qM0AfOYfxDSjg08D8ztBFg */

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return Collectors.lambda$reducing$42(BinaryOperator.this, (Object[]) obj, (Object[]) obj2);
            }
        }, $$Lambda$Collectors$Nv3GWD5X6YPdKFD8ca_9SG7qCDI.INSTANCE, CH_NOID);
    }

    static /* synthetic */ void lambda$reducing$41(BinaryOperator op, Function mapper, Object[] a, Object t) {
        a[0] = op.apply(a[0], mapper.apply(t));
    }

    static /* synthetic */ Object[] lambda$reducing$42(BinaryOperator op, Object[] a, Object[] b) {
        a[0] = op.apply(a[0], b[0]);
        return a;
    }

    static /* synthetic */ Object lambda$reducing$43(Object[] a) {
        return a[0];
    }

    public static <T, K> Collector<T, ?, Map<K, List<T>>> groupingBy(Function<? super T, ? extends K> classifier) {
        return groupingBy(classifier, toList());
    }

    public static <T, K, A, D> Collector<T, ?, Map<K, D>> groupingBy(Function<? super T, ? extends K> classifier, Collector<? super T, A, D> downstream) {
        return groupingBy(classifier, $$Lambda$ry7iWszBr7beYy31SdRxibDyciQ.INSTANCE, downstream);
    }

    public static <T, K, D, A, M extends Map<K, D>> Collector<T, ?, M> groupingBy(Function<? super T, ? extends K> classifier, Supplier<M> mapFactory, Collector<? super T, A, D> downstream) {
        BiConsumer<Map<K, A>, T> accumulator = new BiConsumer(downstream.supplier(), downstream.accumulator()) {
            /* class java.util.stream.$$Lambda$Collectors$F7we3W7I2plNaGHqh_d2lzmvho */
            private final /* synthetic */ Supplier f$1;
            private final /* synthetic */ BiConsumer f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.f$2.accept(((Map) obj).computeIfAbsent(Objects.requireNonNull(Function.this.apply(obj2), "element cannot be mapped to a null key"), new Function() {
                    /* class java.util.stream.$$Lambda$Collectors$f68RHYk8qNU7alEHPPrPoFuCJO4 */

                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return Supplier.this.get();
                    }
                }), obj2);
            }
        };
        BinaryOperator<Map<K, A>> merger = mapMerger(downstream.combiner());
        if (downstream.characteristics().contains(Collector.Characteristics.IDENTITY_FINISH)) {
            return new CollectorImpl(mapFactory, accumulator, merger, CH_ID);
        }
        return new CollectorImpl(mapFactory, accumulator, merger, new Function() {
            /* class java.util.stream.$$Lambda$Collectors$xODDB9V1Y7uzjD3f8OY1Ck4IN4k */

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Map) obj).replaceAll(new BiFunction() {
                    /* class java.util.stream.$$Lambda$Collectors$hNSw8Kk0nIafeklCUz0r3g25T08 */

                    @Override // java.util.function.BiFunction
                    public final Object apply(Object obj, Object obj2) {
                        return Function.this.apply(obj2);
                    }
                });
            }
        }, CH_NOID);
    }

    public static <T, K> Collector<T, ?, ConcurrentMap<K, List<T>>> groupingByConcurrent(Function<? super T, ? extends K> classifier) {
        return groupingByConcurrent(classifier, $$Lambda$lG52Z65fM3qwbieoOBUupMhmr2E.INSTANCE, toList());
    }

    public static <T, K, A, D> Collector<T, ?, ConcurrentMap<K, D>> groupingByConcurrent(Function<? super T, ? extends K> classifier, Collector<? super T, A, D> downstream) {
        return groupingByConcurrent(classifier, $$Lambda$lG52Z65fM3qwbieoOBUupMhmr2E.INSTANCE, downstream);
    }

    public static <T, K, A, D, M extends ConcurrentMap<K, D>> Collector<T, ?, M> groupingByConcurrent(Function<? super T, ? extends K> classifier, Supplier<M> mapFactory, Collector<? super T, A, D> downstream) {
        BiConsumer<ConcurrentMap<K, A>, T> accumulator;
        Supplier<A> downstreamSupplier = downstream.supplier();
        BiConsumer<A, ? super T> downstreamAccumulator = downstream.accumulator();
        BinaryOperator<ConcurrentMap<K, A>> merger = mapMerger(downstream.combiner());
        if (downstream.characteristics().contains(Collector.Characteristics.CONCURRENT)) {
            accumulator = new BiConsumer(downstreamSupplier, downstreamAccumulator) {
                /* class java.util.stream.$$Lambda$Collectors$AfO_bLozmdhHTtbBN7DysDzpfYM */
                private final /* synthetic */ Supplier f$1;
                private final /* synthetic */ BiConsumer f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    this.f$2.accept(((ConcurrentMap) obj).computeIfAbsent(Objects.requireNonNull(Function.this.apply(obj2), "element cannot be mapped to a null key"), new Function() {
                        /* class java.util.stream.$$Lambda$Collectors$eESkXUxzUQd_kZxyXI8noD7gpIw */

                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return Supplier.this.get();
                        }
                    }), obj2);
                }
            };
        } else {
            accumulator = new BiConsumer(downstreamSupplier, downstreamAccumulator) {
                /* class java.util.stream.$$Lambda$Collectors$TRHsqgEycZfemtQqwivCY4ecHDM */
                private final /* synthetic */ Supplier f$1;
                private final /* synthetic */ BiConsumer f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    Collectors.lambda$groupingByConcurrent$51(Function.this, this.f$1, this.f$2, (ConcurrentMap) obj, obj2);
                }
            };
        }
        if (downstream.characteristics().contains(Collector.Characteristics.IDENTITY_FINISH)) {
            return new CollectorImpl(mapFactory, accumulator, merger, CH_CONCURRENT_ID);
        }
        return new CollectorImpl(mapFactory, accumulator, merger, new Function() {
            /* class java.util.stream.$$Lambda$Collectors$oKi5061mJjDn56eRJcmESyO7x9k */

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ConcurrentMap) obj).replaceAll(new BiFunction() {
                    /* class java.util.stream.$$Lambda$Collectors$h1ksXokknmXSWBYxKkYfY6ov7ME */

                    @Override // java.util.function.BiFunction
                    public final Object apply(Object obj, Object obj2) {
                        return Function.this.apply(obj2);
                    }
                });
            }
        }, CH_CONCURRENT_NOID);
    }

    static /* synthetic */ void lambda$groupingByConcurrent$51(Function classifier, Supplier downstreamSupplier, BiConsumer downstreamAccumulator, ConcurrentMap m, Object t) {
        Object computeIfAbsent = m.computeIfAbsent(Objects.requireNonNull(classifier.apply(t), "element cannot be mapped to a null key"), new Function() {
            /* class java.util.stream.$$Lambda$Collectors$WGmHV8Rrm8a3qqTLJPLQv6fpb8o */

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Supplier.this.get();
            }
        });
        synchronized (computeIfAbsent) {
            downstreamAccumulator.accept(computeIfAbsent, t);
        }
    }

    public static <T> Collector<T, ?, Map<Boolean, List<T>>> partitioningBy(Predicate<? super T> predicate) {
        return partitioningBy(predicate, toList());
    }

    public static <T, D, A> Collector<T, ?, Map<Boolean, D>> partitioningBy(Predicate<? super T> predicate, Collector<? super T, A, D> downstream) {
        BiConsumer<Partition<A>, T> accumulator = new BiConsumer(predicate) {
            /* class java.util.stream.$$Lambda$Collectors$DPpNNyjGqDgBuGvO0w_46Z3Jl8 */
            private final /* synthetic */ Predicate f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                Collectors.lambda$partitioningBy$54(BiConsumer.this, this.f$1, (Collectors.Partition) obj, obj2);
            }
        };
        BinaryOperator<Partition<A>> merger = new BinaryOperator() {
            /* class java.util.stream.$$Lambda$Collectors$GYNZB08upNxJa3yuZVQ1065S1eU */

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return Collectors.lambda$partitioningBy$55(BinaryOperator.this, (Collectors.Partition) obj, (Collectors.Partition) obj2);
            }
        };
        Supplier<Partition<A>> supplier = new Supplier() {
            /* class java.util.stream.$$Lambda$Collectors$IFopD1JhOBkpZfx3JTKDGTwaQTo */

            @Override // java.util.function.Supplier
            public final Object get() {
                return Collectors.lambda$partitioningBy$56(Collector.this);
            }
        };
        if (downstream.characteristics().contains(Collector.Characteristics.IDENTITY_FINISH)) {
            return new CollectorImpl(supplier, accumulator, merger, CH_ID);
        }
        return new CollectorImpl(supplier, accumulator, merger, new Function() {
            /* class java.util.stream.$$Lambda$Collectors$chTZg9lp1htTW8rBDwbte7JEOo */

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Collectors.lambda$partitioningBy$57(Collector.this, (Collectors.Partition) obj);
            }
        }, CH_NOID);
    }

    static /* synthetic */ void lambda$partitioningBy$54(BiConsumer downstreamAccumulator, Predicate predicate, Partition result, Object t) {
        downstreamAccumulator.accept(predicate.test(t) ? result.forTrue : result.forFalse, t);
    }

    static /* synthetic */ Partition lambda$partitioningBy$55(BinaryOperator op, Partition left, Partition right) {
        return new Partition(op.apply(left.forTrue, right.forTrue), op.apply(left.forFalse, right.forFalse));
    }

    static /* synthetic */ Partition lambda$partitioningBy$56(Collector downstream) {
        return new Partition(downstream.supplier().get(), downstream.supplier().get());
    }

    static /* synthetic */ Map lambda$partitioningBy$57(Collector downstream, Partition par) {
        return new Partition(downstream.finisher().apply(par.forTrue), downstream.finisher().apply(par.forFalse));
    }

    public static <T, K, U> Collector<T, ?, Map<K, U>> toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper) {
        return toMap(keyMapper, valueMapper, throwingMerger(), $$Lambda$ry7iWszBr7beYy31SdRxibDyciQ.INSTANCE);
    }

    public static <T, K, U> Collector<T, ?, Map<K, U>> toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper, BinaryOperator<U> mergeFunction) {
        return toMap(keyMapper, valueMapper, mergeFunction, $$Lambda$ry7iWszBr7beYy31SdRxibDyciQ.INSTANCE);
    }

    public static <T, K, U, M extends Map<K, U>> Collector<T, ?, M> toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper, BinaryOperator<U> mergeFunction, Supplier<M> mapSupplier) {
        return new CollectorImpl(mapSupplier, new BiConsumer(valueMapper, mergeFunction) {
            /* class java.util.stream.$$Lambda$Collectors$nKlT6uFghrTzWO44dlFAJFeRr34 */
            private final /* synthetic */ Function f$1;
            private final /* synthetic */ BinaryOperator f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                Map map = (Map) obj;
                map.merge(Function.this.apply(obj2), this.f$1.apply(obj2), this.f$2);
            }
        }, mapMerger(mergeFunction), CH_ID);
    }

    public static <T, K, U> Collector<T, ?, ConcurrentMap<K, U>> toConcurrentMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper) {
        return toConcurrentMap(keyMapper, valueMapper, throwingMerger(), $$Lambda$lG52Z65fM3qwbieoOBUupMhmr2E.INSTANCE);
    }

    public static <T, K, U> Collector<T, ?, ConcurrentMap<K, U>> toConcurrentMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper, BinaryOperator<U> mergeFunction) {
        return toConcurrentMap(keyMapper, valueMapper, mergeFunction, $$Lambda$lG52Z65fM3qwbieoOBUupMhmr2E.INSTANCE);
    }

    public static <T, K, U, M extends ConcurrentMap<K, U>> Collector<T, ?, M> toConcurrentMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper, BinaryOperator<U> mergeFunction, Supplier<M> mapSupplier) {
        return new CollectorImpl(mapSupplier, new BiConsumer(valueMapper, mergeFunction) {
            /* class java.util.stream.$$Lambda$Collectors$ZHtBI7Du2F_qzRSEqDnG6y4R0Lw */
            private final /* synthetic */ Function f$1;
            private final /* synthetic */ BinaryOperator f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ConcurrentMap concurrentMap = (ConcurrentMap) obj;
                concurrentMap.merge(Function.this.apply(obj2), this.f$1.apply(obj2), this.f$2);
            }
        }, mapMerger(mergeFunction), CH_CONCURRENT_ID);
    }

    public static <T> Collector<T, ?, IntSummaryStatistics> summarizingInt(ToIntFunction<? super T> mapper) {
        return new CollectorImpl($$Lambda$_Ea_sNpqZAwihIOCRBaP7hHgWWI.INSTANCE, new BiConsumer() {
            /* class java.util.stream.$$Lambda$Collectors$49j2hRW8u6KMoxsVt77YSpMRb1g */

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((IntSummaryStatistics) obj).accept(ToIntFunction.this.applyAsInt(obj2));
            }
        }, $$Lambda$Collectors$HtCSWMKsL2vCjP_AudE9j5Li4Q4.INSTANCE, CH_ID);
    }

    public static <T> Collector<T, ?, LongSummaryStatistics> summarizingLong(ToLongFunction<? super T> mapper) {
        return new CollectorImpl($$Lambda$kZuTETptiPwvB1J27Na7j760aLU.INSTANCE, new BiConsumer() {
            /* class java.util.stream.$$Lambda$Collectors$xyNVxvHgGD7IIanzX9Sm9NxmODA */

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((LongSummaryStatistics) obj).accept((ToLongFunction) ToLongFunction.this.applyAsLong(obj2));
            }
        }, $$Lambda$Collectors$GoEBVVc1WwW27RacBqhtFczthrA.INSTANCE, CH_ID);
    }

    public static <T> Collector<T, ?, DoubleSummaryStatistics> summarizingDouble(ToDoubleFunction<? super T> mapper) {
        return new CollectorImpl($$Lambda$745FUy7cYwYu7KrMQTYh2DNqh1I.INSTANCE, new BiConsumer() {
            /* class java.util.stream.$$Lambda$Collectors$0hO4kVeN9EGHZquOI_a_qZ75htQ */

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((DoubleSummaryStatistics) obj).accept(ToDoubleFunction.this.applyAsDouble(obj2));
            }
        }, $$Lambda$Collectors$oMCfAR_eVSty8GsYzK5sec1Kag.INSTANCE, CH_ID);
    }

    /* access modifiers changed from: private */
    public static final class Partition<T> extends AbstractMap<Boolean, T> implements Map<Boolean, T> {
        final T forFalse;
        final T forTrue;

        Partition(T forTrue2, T forFalse2) {
            this.forTrue = forTrue2;
            this.forFalse = forFalse2;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<Boolean, T>> entrySet() {
            return new AbstractSet<Map.Entry<Boolean, T>>() {
                /* class java.util.stream.Collectors.Partition.AnonymousClass1 */

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
                public Iterator<Map.Entry<Boolean, T>> iterator() {
                    return Arrays.asList(new AbstractMap.SimpleImmutableEntry<>(false, Partition.this.forFalse), new AbstractMap.SimpleImmutableEntry<>(true, Partition.this.forTrue)).iterator();
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                public int size() {
                    return 2;
                }
            };
        }
    }
}
