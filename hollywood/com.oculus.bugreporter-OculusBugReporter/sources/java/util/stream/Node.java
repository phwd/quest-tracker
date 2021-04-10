package java.util.stream;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;
import java.util.stream.Sink;

public interface Node<T> {

    public interface Builder<T> extends Sink<T> {

        public interface OfDouble extends Builder<Double>, Sink.OfDouble {
            /* Return type fixed from 'java.util.stream.Node$OfDouble' to match base method */
            @Override // java.util.stream.Node.Builder
            Node<Double> build();
        }

        public interface OfInt extends Builder<Integer>, Sink.OfInt {
            /* Return type fixed from 'java.util.stream.Node$OfInt' to match base method */
            @Override // java.util.stream.Node.Builder
            Node<Integer> build();
        }

        public interface OfLong extends Builder<Long>, Sink.OfLong {
            /* Return type fixed from 'java.util.stream.Node$OfLong' to match base method */
            @Override // java.util.stream.Node.Builder
            Node<Long> build();
        }

        Node<T> build();
    }

    T[] asArray(IntFunction<T[]> intFunction);

    void copyInto(T[] tArr, int i);

    long count();

    void forEach(Consumer<? super T> consumer);

    Spliterator<T> spliterator();

    default int getChildCount() {
        return 0;
    }

    default Node<T> getChild(int i) {
        throw new IndexOutOfBoundsException();
    }

    default Node<T> truncate(long from, long to, IntFunction<T[]> generator) {
        if (from == 0 && to == count()) {
            return this;
        }
        Spliterator<T> spliterator = spliterator();
        long size = to - from;
        Builder<T> nodeBuilder = Nodes.builder(size, generator);
        nodeBuilder.begin(size);
        for (int i = 0; ((long) i) < from && spliterator.tryAdvance($$Lambda$Node$fa69PlTVbbnR3yr46T9Wo0_bIhg.INSTANCE); i++) {
        }
        for (int i2 = 0; ((long) i2) < size && spliterator.tryAdvance(nodeBuilder); i2++) {
        }
        nodeBuilder.end();
        return nodeBuilder.build();
    }

    static /* synthetic */ default void lambda$truncate$0(Object e) {
    }

    default StreamShape getShape() {
        return StreamShape.REFERENCE;
    }

    public interface OfPrimitive<T, T_CONS, T_ARR, T_SPLITR extends Spliterator.OfPrimitive<T, T_CONS, T_SPLITR>, T_NODE extends OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, T_NODE>> extends Node<T> {
        T_ARR asPrimitiveArray();

        void copyInto(T_ARR t_arr, int i);

        void forEach(T_CONS t_cons);

        T_ARR newArray(int i);

        @Override // java.util.stream.Node
        T_SPLITR spliterator();

        @Override // java.util.stream.Node
        T_NODE truncate(long j, long j2, IntFunction<T[]> intFunction);

        @Override // java.util.stream.Node
        default T_NODE getChild(int i) {
            throw new IndexOutOfBoundsException();
        }

        @Override // java.util.stream.Node
        default T[] asArray(IntFunction<T[]> generator) {
            if (Tripwire.ENABLED) {
                Tripwire.trip(getClass(), "{0} calling Node.OfPrimitive.asArray");
            }
            if (count() < 2147483639) {
                T[] boxed = generator.apply((int) count());
                copyInto((Object[]) boxed, 0);
                return boxed;
            }
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
    }

    public interface OfInt extends OfPrimitive<Integer, IntConsumer, int[], Spliterator.OfInt, OfInt> {
        @Override // java.util.stream.Node
        default void forEach(Consumer<? super Integer> consumer) {
            if (consumer instanceof IntConsumer) {
                forEach((IntConsumer) consumer);
                return;
            }
            if (Tripwire.ENABLED) {
                Tripwire.trip(getClass(), "{0} calling Node.OfInt.forEachRemaining(Consumer)");
            }
            ((Spliterator.OfInt) spliterator()).forEachRemaining(consumer);
        }

        default void copyInto(Integer[] boxed, int offset) {
            if (Tripwire.ENABLED) {
                Tripwire.trip(getClass(), "{0} calling Node.OfInt.copyInto(Integer[], int)");
            }
            int[] array = (int[]) asPrimitiveArray();
            for (int i = 0; i < array.length; i++) {
                boxed[offset + i] = Integer.valueOf(array[i]);
            }
        }

        /* JADX WARN: Type inference failed for: r4v4, types: [java.util.stream.Node$OfInt] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // java.util.stream.Node.OfPrimitive, java.util.stream.Node.OfPrimitive, java.util.stream.Node
        /* Code decompiled incorrectly, please refer to instructions dump. */
        default java.util.stream.Node.OfInt truncate(long r8, long r10, java.util.function.IntFunction<java.lang.Integer[]> r12) {
            /*
                r7 = this;
                r0 = 0
                int r0 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
                if (r0 != 0) goto L_0x000f
                long r0 = r7.count()
                int r0 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
                if (r0 != 0) goto L_0x000f
                return r7
            L_0x000f:
                long r0 = r10 - r8
                java.util.Spliterator$OfPrimitive r2 = r7.spliterator()
                java.util.Spliterator$OfInt r2 = (java.util.Spliterator.OfInt) r2
                java.util.stream.Node$Builder$OfInt r3 = java.util.stream.Nodes.intBuilder(r0)
                r3.begin(r0)
                r4 = 0
            L_0x001f:
                long r5 = (long) r4
                int r5 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
                if (r5 >= 0) goto L_0x002f
                java.util.stream.-$$Lambda$Node$OfInt$SR5qcq7S0oCtehCDXAgbRdnvBbw r5 = java.util.stream.$$Lambda$Node$OfInt$SR5qcq7S0oCtehCDXAgbRdnvBbw.INSTANCE
                boolean r5 = r2.tryAdvance(r5)
                if (r5 == 0) goto L_0x002f
                int r4 = r4 + 1
                goto L_0x001f
            L_0x002f:
                r4 = 0
            L_0x0030:
                long r5 = (long) r4
                int r5 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
                if (r5 >= 0) goto L_0x003e
                boolean r5 = r2.tryAdvance(r3)
                if (r5 == 0) goto L_0x003e
                int r4 = r4 + 1
                goto L_0x0030
            L_0x003e:
                r3.end()
                java.util.stream.Node$OfInt r4 = r3.build()
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.stream.Node.OfInt.truncate(long, long, java.util.function.IntFunction):java.util.stream.Node$OfInt");
        }

        static /* synthetic */ default void lambda$truncate$0(int e) {
        }

        @Override // java.util.stream.Node.OfPrimitive
        default int[] newArray(int count) {
            return new int[count];
        }

        @Override // java.util.stream.Node
        default StreamShape getShape() {
            return StreamShape.INT_VALUE;
        }
    }

    public interface OfLong extends OfPrimitive<Long, LongConsumer, long[], Spliterator.OfLong, OfLong> {
        @Override // java.util.stream.Node
        default void forEach(Consumer<? super Long> consumer) {
            if (consumer instanceof LongConsumer) {
                forEach((LongConsumer) consumer);
                return;
            }
            if (Tripwire.ENABLED) {
                Tripwire.trip(getClass(), "{0} calling Node.OfLong.forEachRemaining(Consumer)");
            }
            ((Spliterator.OfLong) spliterator()).forEachRemaining(consumer);
        }

        default void copyInto(Long[] boxed, int offset) {
            if (Tripwire.ENABLED) {
                Tripwire.trip(getClass(), "{0} calling Node.OfInt.copyInto(Long[], int)");
            }
            long[] array = (long[]) asPrimitiveArray();
            for (int i = 0; i < array.length; i++) {
                boxed[offset + i] = Long.valueOf(array[i]);
            }
        }

        /* JADX WARN: Type inference failed for: r4v4, types: [java.util.stream.Node$OfLong] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // java.util.stream.Node.OfPrimitive, java.util.stream.Node.OfPrimitive, java.util.stream.Node
        /* Code decompiled incorrectly, please refer to instructions dump. */
        default java.util.stream.Node.OfLong truncate(long r8, long r10, java.util.function.IntFunction<java.lang.Long[]> r12) {
            /*
                r7 = this;
                r0 = 0
                int r0 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
                if (r0 != 0) goto L_0x000f
                long r0 = r7.count()
                int r0 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
                if (r0 != 0) goto L_0x000f
                return r7
            L_0x000f:
                long r0 = r10 - r8
                java.util.Spliterator$OfPrimitive r2 = r7.spliterator()
                java.util.Spliterator$OfLong r2 = (java.util.Spliterator.OfLong) r2
                java.util.stream.Node$Builder$OfLong r3 = java.util.stream.Nodes.longBuilder(r0)
                r3.begin(r0)
                r4 = 0
            L_0x001f:
                long r5 = (long) r4
                int r5 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
                if (r5 >= 0) goto L_0x002f
                java.util.stream.-$$Lambda$Node$OfLong$bPdsg_NFhPin-ja_QQPm0P0wq9s r5 = java.util.stream.$$Lambda$Node$OfLong$bPdsg_NFhPinja_QQPm0P0wq9s.INSTANCE
                boolean r5 = r2.tryAdvance(r5)
                if (r5 == 0) goto L_0x002f
                int r4 = r4 + 1
                goto L_0x001f
            L_0x002f:
                r4 = 0
            L_0x0030:
                long r5 = (long) r4
                int r5 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
                if (r5 >= 0) goto L_0x003e
                boolean r5 = r2.tryAdvance(r3)
                if (r5 == 0) goto L_0x003e
                int r4 = r4 + 1
                goto L_0x0030
            L_0x003e:
                r3.end()
                java.util.stream.Node$OfLong r4 = r3.build()
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.stream.Node.OfLong.truncate(long, long, java.util.function.IntFunction):java.util.stream.Node$OfLong");
        }

        static /* synthetic */ default void lambda$truncate$0(long e) {
        }

        @Override // java.util.stream.Node.OfPrimitive
        default long[] newArray(int count) {
            return new long[count];
        }

        @Override // java.util.stream.Node
        default StreamShape getShape() {
            return StreamShape.LONG_VALUE;
        }
    }

    public interface OfDouble extends OfPrimitive<Double, DoubleConsumer, double[], Spliterator.OfDouble, OfDouble> {
        @Override // java.util.stream.Node
        default void forEach(Consumer<? super Double> consumer) {
            if (consumer instanceof DoubleConsumer) {
                forEach((DoubleConsumer) consumer);
                return;
            }
            if (Tripwire.ENABLED) {
                Tripwire.trip(getClass(), "{0} calling Node.OfLong.forEachRemaining(Consumer)");
            }
            ((Spliterator.OfDouble) spliterator()).forEachRemaining(consumer);
        }

        default void copyInto(Double[] boxed, int offset) {
            if (Tripwire.ENABLED) {
                Tripwire.trip(getClass(), "{0} calling Node.OfDouble.copyInto(Double[], int)");
            }
            double[] array = (double[]) asPrimitiveArray();
            for (int i = 0; i < array.length; i++) {
                boxed[offset + i] = Double.valueOf(array[i]);
            }
        }

        /* JADX WARN: Type inference failed for: r4v4, types: [java.util.stream.Node$OfDouble] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // java.util.stream.Node.OfPrimitive, java.util.stream.Node.OfPrimitive, java.util.stream.Node
        /* Code decompiled incorrectly, please refer to instructions dump. */
        default java.util.stream.Node.OfDouble truncate(long r8, long r10, java.util.function.IntFunction<java.lang.Double[]> r12) {
            /*
                r7 = this;
                r0 = 0
                int r0 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
                if (r0 != 0) goto L_0x000f
                long r0 = r7.count()
                int r0 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
                if (r0 != 0) goto L_0x000f
                return r7
            L_0x000f:
                long r0 = r10 - r8
                java.util.Spliterator$OfPrimitive r2 = r7.spliterator()
                java.util.Spliterator$OfDouble r2 = (java.util.Spliterator.OfDouble) r2
                java.util.stream.Node$Builder$OfDouble r3 = java.util.stream.Nodes.doubleBuilder(r0)
                r3.begin(r0)
                r4 = 0
            L_0x001f:
                long r5 = (long) r4
                int r5 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
                if (r5 >= 0) goto L_0x002f
                java.util.stream.-$$Lambda$Node$OfDouble$5XMtiGLC0wkQzF2OIEVEnEBoYWM r5 = java.util.stream.$$Lambda$Node$OfDouble$5XMtiGLC0wkQzF2OIEVEnEBoYWM.INSTANCE
                boolean r5 = r2.tryAdvance(r5)
                if (r5 == 0) goto L_0x002f
                int r4 = r4 + 1
                goto L_0x001f
            L_0x002f:
                r4 = 0
            L_0x0030:
                long r5 = (long) r4
                int r5 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
                if (r5 >= 0) goto L_0x003e
                boolean r5 = r2.tryAdvance(r3)
                if (r5 == 0) goto L_0x003e
                int r4 = r4 + 1
                goto L_0x0030
            L_0x003e:
                r3.end()
                java.util.stream.Node$OfDouble r4 = r3.build()
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.stream.Node.OfDouble.truncate(long, long, java.util.function.IntFunction):java.util.stream.Node$OfDouble");
        }

        static /* synthetic */ default void lambda$truncate$0(double e) {
        }

        @Override // java.util.stream.Node.OfPrimitive
        default double[] newArray(int count) {
            return new double[count];
        }

        @Override // java.util.stream.Node
        default StreamShape getShape() {
            return StreamShape.DOUBLE_VALUE;
        }
    }
}
