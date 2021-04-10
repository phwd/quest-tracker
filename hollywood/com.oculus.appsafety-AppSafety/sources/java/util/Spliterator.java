package java.util;

import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

public interface Spliterator<T> {
    public static final int CONCURRENT = 4096;
    public static final int DISTINCT = 1;
    public static final int IMMUTABLE = 1024;
    public static final int NONNULL = 256;
    public static final int ORDERED = 16;
    public static final int SIZED = 64;
    public static final int SORTED = 4;
    public static final int SUBSIZED = 16384;

    int characteristics();

    long estimateSize();

    boolean tryAdvance(Consumer<? super T> consumer);

    Spliterator<T> trySplit();

    default void forEachRemaining(Consumer<? super T> action) {
        do {
        } while (tryAdvance(action));
    }

    default long getExactSizeIfKnown() {
        if ((characteristics() & 64) == 0) {
            return -1;
        }
        return estimateSize();
    }

    default boolean hasCharacteristics(int characteristics) {
        return (characteristics() & characteristics) == characteristics;
    }

    default Comparator<? super T> getComparator() {
        throw new IllegalStateException();
    }

    public interface OfPrimitive<T, T_CONS, T_SPLITR extends OfPrimitive<T, T_CONS, T_SPLITR>> extends Spliterator<T> {
        boolean tryAdvance(T_CONS t_cons);

        @Override // java.util.Spliterator
        T_SPLITR trySplit();

        default void forEachRemaining(T_CONS action) {
            do {
            } while (tryAdvance((Object) action));
        }
    }

    public interface OfInt extends OfPrimitive<Integer, IntConsumer, OfInt> {
        boolean tryAdvance(IntConsumer intConsumer);

        @Override // java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        OfInt trySplit();

        default void forEachRemaining(IntConsumer action) {
            do {
            } while (tryAdvance(action));
        }

        @Override // java.util.Spliterator
        default boolean tryAdvance(Consumer<? super Integer> action) {
            if (action instanceof IntConsumer) {
                return tryAdvance((IntConsumer) action);
            }
            if (Tripwire.ENABLED) {
                Tripwire.trip(getClass(), "{0} calling Spliterator.OfInt.tryAdvance((IntConsumer) action::accept)");
            }
            Objects.requireNonNull(action);
            return tryAdvance((IntConsumer) new IntConsumer() {
                /* class java.util.$$Lambda$E08DiBhfezKzcLFK72WvmuOUJs */

                @Override // java.util.function.IntConsumer
                public final void accept(int i) {
                    Consumer.this.accept(Integer.valueOf(i));
                }
            });
        }

        @Override // java.util.Spliterator
        default void forEachRemaining(Consumer<? super Integer> action) {
            if (action instanceof IntConsumer) {
                forEachRemaining((IntConsumer) action);
                return;
            }
            if (Tripwire.ENABLED) {
                Tripwire.trip(getClass(), "{0} calling Spliterator.OfInt.forEachRemaining((IntConsumer) action::accept)");
            }
            Objects.requireNonNull(action);
            forEachRemaining((IntConsumer) new IntConsumer() {
                /* class java.util.$$Lambda$E08DiBhfezKzcLFK72WvmuOUJs */

                @Override // java.util.function.IntConsumer
                public final void accept(int i) {
                    Consumer.this.accept(Integer.valueOf(i));
                }
            });
        }
    }

    public interface OfLong extends OfPrimitive<Long, LongConsumer, OfLong> {
        boolean tryAdvance(LongConsumer longConsumer);

        @Override // java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        OfLong trySplit();

        default void forEachRemaining(LongConsumer action) {
            do {
            } while (tryAdvance(action));
        }

        @Override // java.util.Spliterator
        default boolean tryAdvance(Consumer<? super Long> action) {
            if (action instanceof LongConsumer) {
                return tryAdvance((LongConsumer) action);
            }
            if (Tripwire.ENABLED) {
                Tripwire.trip(getClass(), "{0} calling Spliterator.OfLong.tryAdvance((LongConsumer) action::accept)");
            }
            Objects.requireNonNull(action);
            return tryAdvance((LongConsumer) new LongConsumer() {
                /* class java.util.$$Lambda$9llQTmDvC2fDrGds5d6BexJH00 */

                @Override // java.util.function.LongConsumer
                public final void accept(long j) {
                    Consumer.this.accept(Long.valueOf(j));
                }
            });
        }

        @Override // java.util.Spliterator
        default void forEachRemaining(Consumer<? super Long> action) {
            if (action instanceof LongConsumer) {
                forEachRemaining((LongConsumer) action);
                return;
            }
            if (Tripwire.ENABLED) {
                Tripwire.trip(getClass(), "{0} calling Spliterator.OfLong.forEachRemaining((LongConsumer) action::accept)");
            }
            Objects.requireNonNull(action);
            forEachRemaining((LongConsumer) new LongConsumer() {
                /* class java.util.$$Lambda$9llQTmDvC2fDrGds5d6BexJH00 */

                @Override // java.util.function.LongConsumer
                public final void accept(long j) {
                    Consumer.this.accept(Long.valueOf(j));
                }
            });
        }
    }

    public interface OfDouble extends OfPrimitive<Double, DoubleConsumer, OfDouble> {
        boolean tryAdvance(DoubleConsumer doubleConsumer);

        @Override // java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        OfDouble trySplit();

        default void forEachRemaining(DoubleConsumer action) {
            do {
            } while (tryAdvance(action));
        }

        @Override // java.util.Spliterator
        default boolean tryAdvance(Consumer<? super Double> action) {
            if (action instanceof DoubleConsumer) {
                return tryAdvance((DoubleConsumer) action);
            }
            if (Tripwire.ENABLED) {
                Tripwire.trip(getClass(), "{0} calling Spliterator.OfDouble.tryAdvance((DoubleConsumer) action::accept)");
            }
            Objects.requireNonNull(action);
            return tryAdvance((DoubleConsumer) new DoubleConsumer() {
                /* class java.util.$$Lambda$2CyTD4Tuo1NS84gjxzFA3u1LWl0 */

                @Override // java.util.function.DoubleConsumer
                public final void accept(double d) {
                    Consumer.this.accept(Double.valueOf(d));
                }
            });
        }

        @Override // java.util.Spliterator
        default void forEachRemaining(Consumer<? super Double> action) {
            if (action instanceof DoubleConsumer) {
                forEachRemaining((DoubleConsumer) action);
                return;
            }
            if (Tripwire.ENABLED) {
                Tripwire.trip(getClass(), "{0} calling Spliterator.OfDouble.forEachRemaining((DoubleConsumer) action::accept)");
            }
            Objects.requireNonNull(action);
            forEachRemaining((DoubleConsumer) new DoubleConsumer() {
                /* class java.util.$$Lambda$2CyTD4Tuo1NS84gjxzFA3u1LWl0 */

                @Override // java.util.function.DoubleConsumer
                public final void accept(double d) {
                    Consumer.this.accept(Double.valueOf(d));
                }
            });
        }
    }
}
