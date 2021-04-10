package java.util.stream;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.CountedCompleter;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.stream.Node;
import java.util.stream.Sink;
import java.util.stream.SpinedBuffer;

/* access modifiers changed from: package-private */
public final class Nodes {
    static final String BAD_SIZE = "Stream size exceeds max array size";
    private static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
    private static final Node.OfDouble EMPTY_DOUBLE_NODE = new EmptyNode.OfDouble();
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final Node.OfInt EMPTY_INT_NODE = new EmptyNode.OfInt();
    private static final long[] EMPTY_LONG_ARRAY = new long[0];
    private static final Node.OfLong EMPTY_LONG_NODE = new EmptyNode.OfLong();
    private static final Node EMPTY_NODE = new EmptyNode.OfRef(null);
    static final long MAX_ARRAY_SIZE = 2147483639;

    private Nodes() {
        throw new Error("no instances");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: java.util.stream.Nodes$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$util$stream$StreamShape = new int[StreamShape.values().length];

        static {
            try {
                $SwitchMap$java$util$stream$StreamShape[StreamShape.REFERENCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$java$util$stream$StreamShape[StreamShape.INT_VALUE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$java$util$stream$StreamShape[StreamShape.LONG_VALUE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$java$util$stream$StreamShape[StreamShape.DOUBLE_VALUE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    static <T> Node<T> emptyNode(StreamShape shape) {
        int i = AnonymousClass1.$SwitchMap$java$util$stream$StreamShape[shape.ordinal()];
        if (i == 1) {
            return EMPTY_NODE;
        }
        if (i == 2) {
            return EMPTY_INT_NODE;
        }
        if (i == 3) {
            return EMPTY_LONG_NODE;
        }
        if (i == 4) {
            return EMPTY_DOUBLE_NODE;
        }
        throw new IllegalStateException("Unknown shape " + ((Object) shape));
    }

    static <T> Node<T> conc(StreamShape shape, Node<T> left, Node<T> right) {
        int i = AnonymousClass1.$SwitchMap$java$util$stream$StreamShape[shape.ordinal()];
        if (i == 1) {
            return new ConcNode(left, right);
        }
        if (i == 2) {
            return new ConcNode.OfInt((Node.OfInt) left, (Node.OfInt) right);
        }
        if (i == 3) {
            return new ConcNode.OfLong((Node.OfLong) left, (Node.OfLong) right);
        }
        if (i == 4) {
            return new ConcNode.OfDouble((Node.OfDouble) left, (Node.OfDouble) right);
        }
        throw new IllegalStateException("Unknown shape " + ((Object) shape));
    }

    static <T> Node<T> node(T[] array) {
        return new ArrayNode(array);
    }

    static <T> Node<T> node(Collection<T> c) {
        return new CollectionNode(c);
    }

    /* access modifiers changed from: package-private */
    public static <T> Node.Builder<T> builder(long exactSizeIfKnown, IntFunction<T[]> generator) {
        if (exactSizeIfKnown < 0 || exactSizeIfKnown >= MAX_ARRAY_SIZE) {
            return builder();
        }
        return new FixedNodeBuilder(exactSizeIfKnown, generator);
    }

    static <T> Node.Builder<T> builder() {
        return new SpinedNodeBuilder();
    }

    static Node.OfInt node(int[] array) {
        return new IntArrayNode(array);
    }

    static Node.Builder.OfInt intBuilder(long exactSizeIfKnown) {
        if (exactSizeIfKnown < 0 || exactSizeIfKnown >= MAX_ARRAY_SIZE) {
            return intBuilder();
        }
        return new IntFixedNodeBuilder(exactSizeIfKnown);
    }

    static Node.Builder.OfInt intBuilder() {
        return new IntSpinedNodeBuilder();
    }

    static Node.OfLong node(long[] array) {
        return new LongArrayNode(array);
    }

    static Node.Builder.OfLong longBuilder(long exactSizeIfKnown) {
        if (exactSizeIfKnown < 0 || exactSizeIfKnown >= MAX_ARRAY_SIZE) {
            return longBuilder();
        }
        return new LongFixedNodeBuilder(exactSizeIfKnown);
    }

    static Node.Builder.OfLong longBuilder() {
        return new LongSpinedNodeBuilder();
    }

    static Node.OfDouble node(double[] array) {
        return new DoubleArrayNode(array);
    }

    static Node.Builder.OfDouble doubleBuilder(long exactSizeIfKnown) {
        if (exactSizeIfKnown < 0 || exactSizeIfKnown >= MAX_ARRAY_SIZE) {
            return doubleBuilder();
        }
        return new DoubleFixedNodeBuilder(exactSizeIfKnown);
    }

    static Node.Builder.OfDouble doubleBuilder() {
        return new DoubleSpinedNodeBuilder();
    }

    public static <P_IN, P_OUT> Node<P_OUT> collect(PipelineHelper<P_OUT> helper, Spliterator<P_IN> spliterator, boolean flattenTree, IntFunction<P_OUT[]> generator) {
        long size = helper.exactOutputSizeIfKnown(spliterator);
        if (size < 0 || !spliterator.hasCharacteristics(16384)) {
            Node<P_OUT> node = (Node) new CollectorTask.OfRef(helper, generator, spliterator).invoke();
            return flattenTree ? flatten(node, generator) : node;
        } else if (size < MAX_ARRAY_SIZE) {
            P_OUT[] array = generator.apply((int) size);
            new SizedCollectorTask.OfRef(spliterator, helper, array).invoke();
            return node(array);
        } else {
            throw new IllegalArgumentException(BAD_SIZE);
        }
    }

    public static <P_IN> Node.OfInt collectInt(PipelineHelper<Integer> helper, Spliterator<P_IN> spliterator, boolean flattenTree) {
        long size = helper.exactOutputSizeIfKnown(spliterator);
        if (size < 0 || !spliterator.hasCharacteristics(16384)) {
            Node.OfInt node = (Node.OfInt) new CollectorTask.OfInt(helper, spliterator).invoke();
            return flattenTree ? flattenInt(node) : node;
        } else if (size < MAX_ARRAY_SIZE) {
            int[] array = new int[((int) size)];
            new SizedCollectorTask.OfInt(spliterator, helper, array).invoke();
            return node(array);
        } else {
            throw new IllegalArgumentException(BAD_SIZE);
        }
    }

    public static <P_IN> Node.OfLong collectLong(PipelineHelper<Long> helper, Spliterator<P_IN> spliterator, boolean flattenTree) {
        long size = helper.exactOutputSizeIfKnown(spliterator);
        if (size < 0 || !spliterator.hasCharacteristics(16384)) {
            Node.OfLong node = (Node.OfLong) new CollectorTask.OfLong(helper, spliterator).invoke();
            return flattenTree ? flattenLong(node) : node;
        } else if (size < MAX_ARRAY_SIZE) {
            long[] array = new long[((int) size)];
            new SizedCollectorTask.OfLong(spliterator, helper, array).invoke();
            return node(array);
        } else {
            throw new IllegalArgumentException(BAD_SIZE);
        }
    }

    public static <P_IN> Node.OfDouble collectDouble(PipelineHelper<Double> helper, Spliterator<P_IN> spliterator, boolean flattenTree) {
        long size = helper.exactOutputSizeIfKnown(spliterator);
        if (size < 0 || !spliterator.hasCharacteristics(16384)) {
            Node.OfDouble node = (Node.OfDouble) new CollectorTask.OfDouble(helper, spliterator).invoke();
            return flattenTree ? flattenDouble(node) : node;
        } else if (size < MAX_ARRAY_SIZE) {
            double[] array = new double[((int) size)];
            new SizedCollectorTask.OfDouble(spliterator, helper, array).invoke();
            return node(array);
        } else {
            throw new IllegalArgumentException(BAD_SIZE);
        }
    }

    public static <T> Node<T> flatten(Node<T> node, IntFunction<T[]> generator) {
        if (node.getChildCount() <= 0) {
            return node;
        }
        long size = node.count();
        if (size < MAX_ARRAY_SIZE) {
            T[] array = generator.apply((int) size);
            new ToArrayTask.OfRef(node, array, 0, null).invoke();
            return node(array);
        }
        throw new IllegalArgumentException(BAD_SIZE);
    }

    public static Node.OfInt flattenInt(Node.OfInt node) {
        if (node.getChildCount() <= 0) {
            return node;
        }
        long size = node.count();
        if (size < MAX_ARRAY_SIZE) {
            int[] array = new int[((int) size)];
            new ToArrayTask.OfInt(node, array, 0, null).invoke();
            return node(array);
        }
        throw new IllegalArgumentException(BAD_SIZE);
    }

    public static Node.OfLong flattenLong(Node.OfLong node) {
        if (node.getChildCount() <= 0) {
            return node;
        }
        long size = node.count();
        if (size < MAX_ARRAY_SIZE) {
            long[] array = new long[((int) size)];
            new ToArrayTask.OfLong(node, array, 0, null).invoke();
            return node(array);
        }
        throw new IllegalArgumentException(BAD_SIZE);
    }

    public static Node.OfDouble flattenDouble(Node.OfDouble node) {
        if (node.getChildCount() <= 0) {
            return node;
        }
        long size = node.count();
        if (size < MAX_ARRAY_SIZE) {
            double[] array = new double[((int) size)];
            new ToArrayTask.OfDouble(node, array, 0, null).invoke();
            return node(array);
        }
        throw new IllegalArgumentException(BAD_SIZE);
    }

    private static abstract class EmptyNode<T, T_ARR, T_CONS> implements Node<T> {
        EmptyNode() {
        }

        @Override // java.util.stream.Node
        public T[] asArray(IntFunction<T[]> generator) {
            return generator.apply(0);
        }

        public void copyInto(T_ARR t_arr, int offset) {
        }

        @Override // java.util.stream.Node
        public long count() {
            return 0;
        }

        public void forEach(T_CONS t_cons) {
        }

        private static class OfRef<T> extends EmptyNode<T, T[], Consumer<? super T>> {
            /* synthetic */ OfRef(AnonymousClass1 x0) {
                this();
            }

            @Override // java.util.stream.Node
            public /* bridge */ /* synthetic */ void copyInto(Object[] objArr, int i) {
                super.copyInto((Object) objArr, i);
            }

            @Override // java.util.stream.Node
            public /* bridge */ /* synthetic */ void forEach(Consumer consumer) {
                super.forEach((Object) consumer);
            }

            private OfRef() {
            }

            @Override // java.util.stream.Node
            public Spliterator<T> spliterator() {
                return Spliterators.emptySpliterator();
            }
        }

        private static final class OfInt extends EmptyNode<Integer, int[], IntConsumer> implements Node.OfInt {
            OfInt() {
            }

            @Override // java.util.stream.Node.OfPrimitive, java.util.stream.Node.OfPrimitive, java.util.stream.Node
            public Spliterator.OfInt spliterator() {
                return Spliterators.emptyIntSpliterator();
            }

            @Override // java.util.stream.Node.OfPrimitive
            public int[] asPrimitiveArray() {
                return Nodes.EMPTY_INT_ARRAY;
            }
        }

        private static final class OfLong extends EmptyNode<Long, long[], LongConsumer> implements Node.OfLong {
            OfLong() {
            }

            @Override // java.util.stream.Node.OfPrimitive, java.util.stream.Node.OfPrimitive, java.util.stream.Node
            public Spliterator.OfLong spliterator() {
                return Spliterators.emptyLongSpliterator();
            }

            @Override // java.util.stream.Node.OfPrimitive
            public long[] asPrimitiveArray() {
                return Nodes.EMPTY_LONG_ARRAY;
            }
        }

        private static final class OfDouble extends EmptyNode<Double, double[], DoubleConsumer> implements Node.OfDouble {
            OfDouble() {
            }

            @Override // java.util.stream.Node.OfPrimitive, java.util.stream.Node.OfPrimitive, java.util.stream.Node
            public Spliterator.OfDouble spliterator() {
                return Spliterators.emptyDoubleSpliterator();
            }

            @Override // java.util.stream.Node.OfPrimitive
            public double[] asPrimitiveArray() {
                return Nodes.EMPTY_DOUBLE_ARRAY;
            }
        }
    }

    /* access modifiers changed from: private */
    public static class ArrayNode<T> implements Node<T> {
        final T[] array;
        int curSize;

        ArrayNode(long size, IntFunction<T[]> generator) {
            if (size < Nodes.MAX_ARRAY_SIZE) {
                this.array = generator.apply((int) size);
                this.curSize = 0;
                return;
            }
            throw new IllegalArgumentException(Nodes.BAD_SIZE);
        }

        ArrayNode(T[] array2) {
            this.array = array2;
            this.curSize = array2.length;
        }

        @Override // java.util.stream.Node
        public Spliterator<T> spliterator() {
            return Arrays.spliterator(this.array, 0, this.curSize);
        }

        @Override // java.util.stream.Node
        public void copyInto(T[] dest, int destOffset) {
            System.arraycopy(this.array, 0, dest, destOffset, this.curSize);
        }

        @Override // java.util.stream.Node
        public T[] asArray(IntFunction<T[]> intFunction) {
            T[] tArr = this.array;
            if (tArr.length == this.curSize) {
                return tArr;
            }
            throw new IllegalStateException();
        }

        @Override // java.util.stream.Node
        public long count() {
            return (long) this.curSize;
        }

        @Override // java.util.stream.Node
        public void forEach(Consumer<? super T> consumer) {
            for (int i = 0; i < this.curSize; i++) {
                consumer.accept(this.array[i]);
            }
        }

        public String toString() {
            return String.format("ArrayNode[%d][%s]", Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array));
        }
    }

    private static final class CollectionNode<T> implements Node<T> {
        private final Collection<T> c;

        CollectionNode(Collection<T> c2) {
            this.c = c2;
        }

        @Override // java.util.stream.Node
        public Spliterator<T> spliterator() {
            return this.c.stream().spliterator();
        }

        @Override // java.util.stream.Node
        public void copyInto(T[] array, int offset) {
            for (T t : this.c) {
                array[offset] = t;
                offset++;
            }
        }

        @Override // java.util.stream.Node
        public T[] asArray(IntFunction<T[]> generator) {
            Collection<T> collection = this.c;
            return (T[]) collection.toArray(generator.apply(collection.size()));
        }

        @Override // java.util.stream.Node
        public long count() {
            return (long) this.c.size();
        }

        @Override // java.util.stream.Node
        public void forEach(Consumer<? super T> consumer) {
            this.c.forEach(consumer);
        }

        public String toString() {
            return String.format("CollectionNode[%d][%s]", Integer.valueOf(this.c.size()), this.c);
        }
    }

    private static abstract class AbstractConcNode<T, T_NODE extends Node<T>> implements Node<T> {
        protected final T_NODE left;
        protected final T_NODE right;
        private final long size;

        AbstractConcNode(T_NODE left2, T_NODE right2) {
            this.left = left2;
            this.right = right2;
            this.size = left2.count() + right2.count();
        }

        @Override // java.util.stream.Node
        public int getChildCount() {
            return 2;
        }

        @Override // java.util.stream.Node
        public T_NODE getChild(int i) {
            if (i == 0) {
                return this.left;
            }
            if (i == 1) {
                return this.right;
            }
            throw new IndexOutOfBoundsException();
        }

        @Override // java.util.stream.Node
        public long count() {
            return this.size;
        }
    }

    /* access modifiers changed from: package-private */
    public static final class ConcNode<T> extends AbstractConcNode<T, Node<T>> implements Node<T> {
        ConcNode(Node<T> left, Node<T> right) {
            super(left, right);
        }

        @Override // java.util.stream.Node
        public Spliterator<T> spliterator() {
            return new InternalNodeSpliterator.OfRef(this);
        }

        @Override // java.util.stream.Node
        public void copyInto(T[] array, int offset) {
            Objects.requireNonNull(array);
            this.left.copyInto(array, offset);
            this.right.copyInto(array, ((int) this.left.count()) + offset);
        }

        @Override // java.util.stream.Node
        public T[] asArray(IntFunction<T[]> generator) {
            long size = count();
            if (size < Nodes.MAX_ARRAY_SIZE) {
                T[] array = generator.apply((int) size);
                copyInto(array, 0);
                return array;
            }
            throw new IllegalArgumentException(Nodes.BAD_SIZE);
        }

        @Override // java.util.stream.Node
        public void forEach(Consumer<? super T> consumer) {
            this.left.forEach(consumer);
            this.right.forEach(consumer);
        }

        @Override // java.util.stream.Node
        public Node<T> truncate(long from, long to, IntFunction<T[]> generator) {
            if (from == 0 && to == count()) {
                return this;
            }
            long leftCount = this.left.count();
            if (from >= leftCount) {
                return this.right.truncate(from - leftCount, to - leftCount, generator);
            }
            if (to <= leftCount) {
                return this.left.truncate(from, to, generator);
            }
            return Nodes.conc(getShape(), this.left.truncate(from, leftCount, generator), this.right.truncate(0, to - leftCount, generator));
        }

        public String toString() {
            if (count() < 32) {
                return String.format("ConcNode[%s.%s]", this.left, this.right);
            }
            return String.format("ConcNode[size=%d]", Long.valueOf(count()));
        }

        private static abstract class OfPrimitive<E, T_CONS, T_ARR, T_SPLITR extends Spliterator.OfPrimitive<E, T_CONS, T_SPLITR>, T_NODE extends Node.OfPrimitive<E, T_CONS, T_ARR, T_SPLITR, T_NODE>> extends AbstractConcNode<E, T_NODE> implements Node.OfPrimitive<E, T_CONS, T_ARR, T_SPLITR, T_NODE> {
            @Override // java.util.stream.Nodes.AbstractConcNode, java.util.stream.Node.OfPrimitive, java.util.stream.Node.OfPrimitive, java.util.stream.Node
            public /* bridge */ /* synthetic */ Node.OfPrimitive getChild(int i) {
                return (Node.OfPrimitive) super.getChild(i);
            }

            OfPrimitive(T_NODE left, T_NODE right) {
                super(left, right);
            }

            @Override // java.util.stream.Node.OfPrimitive
            public void forEach(T_CONS consumer) {
                ((Node.OfPrimitive) this.left).forEach((Object) consumer);
                ((Node.OfPrimitive) this.right).forEach((Object) consumer);
            }

            @Override // java.util.stream.Node.OfPrimitive
            public void copyInto(T_ARR array, int offset) {
                ((Node.OfPrimitive) this.left).copyInto(array, offset);
                ((Node.OfPrimitive) this.right).copyInto(array, ((int) ((Node.OfPrimitive) this.left).count()) + offset);
            }

            @Override // java.util.stream.Node.OfPrimitive
            public T_ARR asPrimitiveArray() {
                long size = count();
                if (size < Nodes.MAX_ARRAY_SIZE) {
                    T_ARR array = newArray((int) size);
                    copyInto(array, 0);
                    return array;
                }
                throw new IllegalArgumentException(Nodes.BAD_SIZE);
            }

            public String toString() {
                if (count() < 32) {
                    return String.format("%s[%s.%s]", getClass().getName(), this.left, this.right);
                }
                return String.format("%s[size=%d]", getClass().getName(), Long.valueOf(count()));
            }
        }

        /* access modifiers changed from: package-private */
        public static final class OfInt extends OfPrimitive<Integer, IntConsumer, int[], Spliterator.OfInt, Node.OfInt> implements Node.OfInt {
            OfInt(Node.OfInt left, Node.OfInt right) {
                super(left, right);
            }

            @Override // java.util.stream.Node.OfPrimitive, java.util.stream.Node.OfPrimitive, java.util.stream.Node
            public Spliterator.OfInt spliterator() {
                return new InternalNodeSpliterator.OfInt(this);
            }
        }

        /* access modifiers changed from: package-private */
        public static final class OfLong extends OfPrimitive<Long, LongConsumer, long[], Spliterator.OfLong, Node.OfLong> implements Node.OfLong {
            OfLong(Node.OfLong left, Node.OfLong right) {
                super(left, right);
            }

            @Override // java.util.stream.Node.OfPrimitive, java.util.stream.Node.OfPrimitive, java.util.stream.Node
            public Spliterator.OfLong spliterator() {
                return new InternalNodeSpliterator.OfLong(this);
            }
        }

        /* access modifiers changed from: package-private */
        public static final class OfDouble extends OfPrimitive<Double, DoubleConsumer, double[], Spliterator.OfDouble, Node.OfDouble> implements Node.OfDouble {
            OfDouble(Node.OfDouble left, Node.OfDouble right) {
                super(left, right);
            }

            @Override // java.util.stream.Node.OfPrimitive, java.util.stream.Node.OfPrimitive, java.util.stream.Node
            public Spliterator.OfDouble spliterator() {
                return new InternalNodeSpliterator.OfDouble(this);
            }
        }
    }

    private static abstract class InternalNodeSpliterator<T, S extends Spliterator<T>, N extends Node<T>> implements Spliterator<T> {
        int curChildIndex;
        N curNode;
        S lastNodeSpliterator;
        S tryAdvanceSpliterator;
        Deque<N> tryAdvanceStack;

        InternalNodeSpliterator(N curNode2) {
            this.curNode = curNode2;
        }

        /* access modifiers changed from: protected */
        public final Deque<N> initStack() {
            ArrayDeque arrayDeque = new ArrayDeque(8);
            int i = this.curNode.getChildCount();
            while (true) {
                i--;
                if (i < this.curChildIndex) {
                    return arrayDeque;
                }
                arrayDeque.addFirst(this.curNode.getChild(i));
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: java.util.Deque<N extends java.util.stream.Node<T>> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: protected */
        public final N findNextLeafNode(Deque<N> stack) {
            while (true) {
                N n = (N) ((Node) stack.pollFirst());
                if (n == null) {
                    return null;
                }
                if (n.getChildCount() != 0) {
                    for (int i = n.getChildCount() - 1; i >= 0; i--) {
                        stack.addFirst(n.getChild(i));
                    }
                } else if (n.count() > 0) {
                    return n;
                }
            }
        }

        /* access modifiers changed from: protected */
        public final boolean initTryAdvance() {
            if (this.curNode == null) {
                return false;
            }
            if (this.tryAdvanceSpliterator != null) {
                return true;
            }
            S s = this.lastNodeSpliterator;
            if (s == null) {
                this.tryAdvanceStack = initStack();
                N leaf = findNextLeafNode(this.tryAdvanceStack);
                if (leaf != null) {
                    this.tryAdvanceSpliterator = (S) leaf.spliterator();
                    return true;
                }
                this.curNode = null;
                return false;
            }
            this.tryAdvanceSpliterator = s;
            return true;
        }

        /* JADX WARN: Type inference failed for: r0v12, types: [S extends java.util.Spliterator<T>, java.util.Spliterator] */
        /* JADX WARN: Type inference failed for: r0v19, types: [S extends java.util.Spliterator<T>, java.util.Spliterator] */
        @Override // java.util.Spliterator
        public final S trySplit() {
            N n = this.curNode;
            if (n == null || this.tryAdvanceSpliterator != null) {
                return null;
            }
            S s = this.lastNodeSpliterator;
            if (s != null) {
                return (S) s.trySplit();
            }
            if (this.curChildIndex < n.getChildCount() - 1) {
                N n2 = this.curNode;
                int i = this.curChildIndex;
                this.curChildIndex = i + 1;
                return n2.getChild(i).spliterator();
            }
            this.curNode = (N) this.curNode.getChild(this.curChildIndex);
            if (this.curNode.getChildCount() == 0) {
                this.lastNodeSpliterator = (S) this.curNode.spliterator();
                return (S) this.lastNodeSpliterator.trySplit();
            }
            this.curChildIndex = 0;
            N n3 = this.curNode;
            int i2 = this.curChildIndex;
            this.curChildIndex = i2 + 1;
            return n3.getChild(i2).spliterator();
        }

        @Override // java.util.Spliterator
        public final long estimateSize() {
            if (this.curNode == null) {
                return 0;
            }
            S s = this.lastNodeSpliterator;
            if (s != null) {
                return s.estimateSize();
            }
            long size = 0;
            for (int i = this.curChildIndex; i < this.curNode.getChildCount(); i++) {
                size += this.curNode.getChild(i).count();
            }
            return size;
        }

        @Override // java.util.Spliterator
        public final int characteristics() {
            return 64;
        }

        private static final class OfRef<T> extends InternalNodeSpliterator<T, Spliterator<T>, Node<T>> {
            OfRef(Node<T> curNode) {
                super(curNode);
            }

            @Override // java.util.Spliterator
            public boolean tryAdvance(Consumer<? super T> consumer) {
                Node<T> leaf;
                if (!initTryAdvance()) {
                    return false;
                }
                boolean hasNext = this.tryAdvanceSpliterator.tryAdvance(consumer);
                if (!hasNext) {
                    if (this.lastNodeSpliterator != null || (leaf = findNextLeafNode(this.tryAdvanceStack)) == null) {
                        this.curNode = null;
                    } else {
                        this.tryAdvanceSpliterator = leaf.spliterator();
                        return this.tryAdvanceSpliterator.tryAdvance(consumer);
                    }
                }
                return hasNext;
            }

            @Override // java.util.Spliterator
            public void forEachRemaining(Consumer<? super T> consumer) {
                if (this.curNode != null) {
                    if (this.tryAdvanceSpliterator != null) {
                        do {
                        } while (tryAdvance(consumer));
                    } else if (this.lastNodeSpliterator == null) {
                        Deque<N> initStack = initStack();
                        while (true) {
                            Node<T> leaf = findNextLeafNode(initStack);
                            if (leaf != null) {
                                leaf.forEach(consumer);
                            } else {
                                this.curNode = null;
                                return;
                            }
                        }
                    } else {
                        this.lastNodeSpliterator.forEachRemaining(consumer);
                    }
                }
            }
        }

        private static abstract class OfPrimitive<T, T_CONS, T_ARR, T_SPLITR extends Spliterator.OfPrimitive<T, T_CONS, T_SPLITR>, N extends Node.OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, N>> extends InternalNodeSpliterator<T, T_SPLITR, N> implements Spliterator.OfPrimitive<T, T_CONS, T_SPLITR> {
            @Override // java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.stream.Nodes.InternalNodeSpliterator, java.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
                return (Spliterator.OfPrimitive) super.trySplit();
            }

            OfPrimitive(N cur) {
                super(cur);
            }

            @Override // java.util.Spliterator.OfPrimitive
            public boolean tryAdvance(T_CONS consumer) {
                Node.OfPrimitive ofPrimitive;
                if (!initTryAdvance()) {
                    return false;
                }
                boolean hasNext = ((Spliterator.OfPrimitive) this.tryAdvanceSpliterator).tryAdvance((Object) consumer);
                if (!hasNext) {
                    if (this.lastNodeSpliterator != null || (ofPrimitive = (Node.OfPrimitive) findNextLeafNode(this.tryAdvanceStack)) == null) {
                        this.curNode = null;
                    } else {
                        this.tryAdvanceSpliterator = ofPrimitive.spliterator();
                        return ((Spliterator.OfPrimitive) this.tryAdvanceSpliterator).tryAdvance((Object) consumer);
                    }
                }
                return hasNext;
            }

            @Override // java.util.Spliterator.OfPrimitive
            public void forEachRemaining(T_CONS consumer) {
                if (this.curNode != null) {
                    if (this.tryAdvanceSpliterator != null) {
                        do {
                        } while (tryAdvance((Object) consumer));
                    } else if (this.lastNodeSpliterator == null) {
                        Deque<N> initStack = initStack();
                        while (true) {
                            Node.OfPrimitive ofPrimitive = (Node.OfPrimitive) findNextLeafNode(initStack);
                            if (ofPrimitive != null) {
                                ofPrimitive.forEach((Object) consumer);
                            } else {
                                this.curNode = null;
                                return;
                            }
                        }
                    } else {
                        ((Spliterator.OfPrimitive) this.lastNodeSpliterator).forEachRemaining((Object) consumer);
                    }
                }
            }
        }

        /* access modifiers changed from: private */
        public static final class OfInt extends OfPrimitive<Integer, IntConsumer, int[], Spliterator.OfInt, Node.OfInt> implements Spliterator.OfInt {
            @Override // java.util.Spliterator.OfInt
            public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
                super.forEachRemaining((Object) intConsumer);
            }

            @Override // java.util.Spliterator.OfInt
            public /* bridge */ /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
                return super.tryAdvance((Object) intConsumer);
            }

            @Override // java.util.Spliterator.OfInt, java.util.Spliterator.OfInt, java.util.Spliterator.OfInt, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.stream.Nodes.InternalNodeSpliterator, java.util.stream.Nodes.InternalNodeSpliterator.OfPrimitive, java.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfInt trySplit() {
                return (Spliterator.OfInt) super.trySplit();
            }

            OfInt(Node.OfInt cur) {
                super(cur);
            }
        }

        /* access modifiers changed from: private */
        public static final class OfLong extends OfPrimitive<Long, LongConsumer, long[], Spliterator.OfLong, Node.OfLong> implements Spliterator.OfLong {
            @Override // java.util.Spliterator.OfLong
            public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
                super.forEachRemaining((Object) longConsumer);
            }

            @Override // java.util.Spliterator.OfLong
            public /* bridge */ /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
                return super.tryAdvance((Object) longConsumer);
            }

            @Override // java.util.Spliterator.OfLong, java.util.Spliterator.OfLong, java.util.Spliterator.OfLong, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.stream.Nodes.InternalNodeSpliterator, java.util.stream.Nodes.InternalNodeSpliterator.OfPrimitive, java.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfLong trySplit() {
                return (Spliterator.OfLong) super.trySplit();
            }

            OfLong(Node.OfLong cur) {
                super(cur);
            }
        }

        /* access modifiers changed from: private */
        public static final class OfDouble extends OfPrimitive<Double, DoubleConsumer, double[], Spliterator.OfDouble, Node.OfDouble> implements Spliterator.OfDouble {
            @Override // java.util.Spliterator.OfDouble
            public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
                super.forEachRemaining((Object) doubleConsumer);
            }

            @Override // java.util.Spliterator.OfDouble
            public /* bridge */ /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
                return super.tryAdvance((Object) doubleConsumer);
            }

            @Override // java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfPrimitive, java.util.Spliterator.OfDouble, java.util.Spliterator.OfDouble, java.util.Spliterator.OfDouble, java.util.stream.Nodes.InternalNodeSpliterator, java.util.stream.Nodes.InternalNodeSpliterator.OfPrimitive, java.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfDouble trySplit() {
                return (Spliterator.OfDouble) super.trySplit();
            }

            OfDouble(Node.OfDouble cur) {
                super(cur);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final class FixedNodeBuilder<T> extends ArrayNode<T> implements Node.Builder<T> {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        FixedNodeBuilder(long size, IntFunction<T[]> generator) {
            super(size, generator);
        }

        @Override // java.util.stream.Node.Builder
        public Node<T> build() {
            if (this.curSize >= this.array.length) {
                return this;
            }
            throw new IllegalStateException(String.format("Current size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)));
        }

        @Override // java.util.stream.Sink
        public void begin(long size) {
            if (size == ((long) this.array.length)) {
                this.curSize = 0;
            } else {
                throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d", Long.valueOf(size), Integer.valueOf(this.array.length)));
            }
        }

        @Override // java.util.function.Consumer
        public void accept(T t) {
            if (this.curSize < this.array.length) {
                Object[] objArr = this.array;
                int i = this.curSize;
                this.curSize = i + 1;
                objArr[i] = t;
                return;
            }
            throw new IllegalStateException(String.format("Accept exceeded fixed size of %d", Integer.valueOf(this.array.length)));
        }

        @Override // java.util.stream.Sink
        public void end() {
            if (this.curSize < this.array.length) {
                throw new IllegalStateException(String.format("End size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)));
            }
        }

        @Override // java.util.stream.Nodes.ArrayNode
        public String toString() {
            return String.format("FixedNodeBuilder[%d][%s]", Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array));
        }
    }

    /* access modifiers changed from: private */
    public static final class SpinedNodeBuilder<T> extends SpinedBuffer<T> implements Node<T>, Node.Builder<T> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private boolean building = false;

        SpinedNodeBuilder() {
        }

        @Override // java.util.stream.SpinedBuffer, java.util.stream.Node, java.lang.Iterable
        public Spliterator<T> spliterator() {
            return super.spliterator();
        }

        /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.util.function.Consumer<? super T> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.stream.SpinedBuffer, java.util.stream.Node, java.lang.Iterable
        public void forEach(Consumer<? super T> consumer) {
            super.forEach(consumer);
        }

        @Override // java.util.stream.Sink
        public void begin(long size) {
            this.building = true;
            clear();
            ensureCapacity(size);
        }

        @Override // java.util.function.Consumer, java.util.stream.SpinedBuffer
        public void accept(T t) {
            super.accept(t);
        }

        @Override // java.util.stream.Sink
        public void end() {
            this.building = false;
        }

        @Override // java.util.stream.SpinedBuffer, java.util.stream.Node
        public void copyInto(T[] array, int offset) {
            super.copyInto(array, offset);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.function.IntFunction<T[]> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.stream.SpinedBuffer, java.util.stream.Node
        public T[] asArray(IntFunction<T[]> arrayFactory) {
            return (T[]) super.asArray(arrayFactory);
        }

        @Override // java.util.stream.Node.Builder
        public Node<T> build() {
            return this;
        }
    }

    /* access modifiers changed from: private */
    public static class IntArrayNode implements Node.OfInt {
        final int[] array;
        int curSize;

        IntArrayNode(long size) {
            if (size < Nodes.MAX_ARRAY_SIZE) {
                this.array = new int[((int) size)];
                this.curSize = 0;
                return;
            }
            throw new IllegalArgumentException(Nodes.BAD_SIZE);
        }

        IntArrayNode(int[] array2) {
            this.array = array2;
            this.curSize = array2.length;
        }

        @Override // java.util.stream.Node.OfPrimitive, java.util.stream.Node.OfPrimitive, java.util.stream.Node
        public Spliterator.OfInt spliterator() {
            return Arrays.spliterator(this.array, 0, this.curSize);
        }

        @Override // java.util.stream.Node.OfPrimitive
        public int[] asPrimitiveArray() {
            int[] iArr = this.array;
            int length = iArr.length;
            int i = this.curSize;
            if (length == i) {
                return iArr;
            }
            return Arrays.copyOf(iArr, i);
        }

        public void copyInto(int[] dest, int destOffset) {
            System.arraycopy((Object) this.array, 0, (Object) dest, destOffset, this.curSize);
        }

        @Override // java.util.stream.Node
        public long count() {
            return (long) this.curSize;
        }

        public void forEach(IntConsumer consumer) {
            for (int i = 0; i < this.curSize; i++) {
                consumer.accept(this.array[i]);
            }
        }

        public String toString() {
            return String.format("IntArrayNode[%d][%s]", Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array));
        }
    }

    /* access modifiers changed from: private */
    public static class LongArrayNode implements Node.OfLong {
        final long[] array;
        int curSize;

        LongArrayNode(long size) {
            if (size < Nodes.MAX_ARRAY_SIZE) {
                this.array = new long[((int) size)];
                this.curSize = 0;
                return;
            }
            throw new IllegalArgumentException(Nodes.BAD_SIZE);
        }

        LongArrayNode(long[] array2) {
            this.array = array2;
            this.curSize = array2.length;
        }

        @Override // java.util.stream.Node.OfPrimitive, java.util.stream.Node.OfPrimitive, java.util.stream.Node
        public Spliterator.OfLong spliterator() {
            return Arrays.spliterator(this.array, 0, this.curSize);
        }

        @Override // java.util.stream.Node.OfPrimitive
        public long[] asPrimitiveArray() {
            long[] jArr = this.array;
            int length = jArr.length;
            int i = this.curSize;
            if (length == i) {
                return jArr;
            }
            return Arrays.copyOf(jArr, i);
        }

        public void copyInto(long[] dest, int destOffset) {
            System.arraycopy((Object) this.array, 0, (Object) dest, destOffset, this.curSize);
        }

        @Override // java.util.stream.Node
        public long count() {
            return (long) this.curSize;
        }

        public void forEach(LongConsumer consumer) {
            for (int i = 0; i < this.curSize; i++) {
                consumer.accept(this.array[i]);
            }
        }

        public String toString() {
            return String.format("LongArrayNode[%d][%s]", Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array));
        }
    }

    /* access modifiers changed from: private */
    public static class DoubleArrayNode implements Node.OfDouble {
        final double[] array;
        int curSize;

        DoubleArrayNode(long size) {
            if (size < Nodes.MAX_ARRAY_SIZE) {
                this.array = new double[((int) size)];
                this.curSize = 0;
                return;
            }
            throw new IllegalArgumentException(Nodes.BAD_SIZE);
        }

        DoubleArrayNode(double[] array2) {
            this.array = array2;
            this.curSize = array2.length;
        }

        @Override // java.util.stream.Node.OfPrimitive, java.util.stream.Node.OfPrimitive, java.util.stream.Node
        public Spliterator.OfDouble spliterator() {
            return Arrays.spliterator(this.array, 0, this.curSize);
        }

        @Override // java.util.stream.Node.OfPrimitive
        public double[] asPrimitiveArray() {
            double[] dArr = this.array;
            int length = dArr.length;
            int i = this.curSize;
            if (length == i) {
                return dArr;
            }
            return Arrays.copyOf(dArr, i);
        }

        public void copyInto(double[] dest, int destOffset) {
            System.arraycopy((Object) this.array, 0, (Object) dest, destOffset, this.curSize);
        }

        @Override // java.util.stream.Node
        public long count() {
            return (long) this.curSize;
        }

        public void forEach(DoubleConsumer consumer) {
            for (int i = 0; i < this.curSize; i++) {
                consumer.accept(this.array[i]);
            }
        }

        public String toString() {
            return String.format("DoubleArrayNode[%d][%s]", Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array));
        }
    }

    /* access modifiers changed from: private */
    public static final class IntFixedNodeBuilder extends IntArrayNode implements Node.Builder.OfInt {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        IntFixedNodeBuilder(long size) {
            super(size);
        }

        /* Return type fixed from 'java.util.stream.Node$OfInt' to match base method */
        @Override // java.util.stream.Node.Builder, java.util.stream.Node.Builder.OfInt, java.util.stream.Node.Builder.OfInt
        public Node<Integer> build() {
            if (this.curSize >= this.array.length) {
                return this;
            }
            throw new IllegalStateException(String.format("Current size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)));
        }

        @Override // java.util.stream.Sink
        public void begin(long size) {
            if (size == ((long) this.array.length)) {
                this.curSize = 0;
            } else {
                throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d", Long.valueOf(size), Integer.valueOf(this.array.length)));
            }
        }

        @Override // java.util.stream.Sink.OfInt, java.util.function.IntConsumer, java.util.stream.Sink
        public void accept(int i) {
            if (this.curSize < this.array.length) {
                int[] iArr = this.array;
                int i2 = this.curSize;
                this.curSize = i2 + 1;
                iArr[i2] = i;
                return;
            }
            throw new IllegalStateException(String.format("Accept exceeded fixed size of %d", Integer.valueOf(this.array.length)));
        }

        @Override // java.util.stream.Sink
        public void end() {
            if (this.curSize < this.array.length) {
                throw new IllegalStateException(String.format("End size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)));
            }
        }

        @Override // java.util.stream.Nodes.IntArrayNode
        public String toString() {
            return String.format("IntFixedNodeBuilder[%d][%s]", Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array));
        }
    }

    /* access modifiers changed from: private */
    public static final class LongFixedNodeBuilder extends LongArrayNode implements Node.Builder.OfLong {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        LongFixedNodeBuilder(long size) {
            super(size);
        }

        /* Return type fixed from 'java.util.stream.Node$OfLong' to match base method */
        @Override // java.util.stream.Node.Builder, java.util.stream.Node.Builder.OfLong, java.util.stream.Node.Builder.OfLong
        public Node<Long> build() {
            if (this.curSize >= this.array.length) {
                return this;
            }
            throw new IllegalStateException(String.format("Current size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)));
        }

        @Override // java.util.stream.Sink
        public void begin(long size) {
            if (size == ((long) this.array.length)) {
                this.curSize = 0;
            } else {
                throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d", Long.valueOf(size), Integer.valueOf(this.array.length)));
            }
        }

        @Override // java.util.stream.Sink.OfLong, java.util.stream.Sink, java.util.function.LongConsumer
        public void accept(long i) {
            if (this.curSize < this.array.length) {
                long[] jArr = this.array;
                int i2 = this.curSize;
                this.curSize = i2 + 1;
                jArr[i2] = i;
                return;
            }
            throw new IllegalStateException(String.format("Accept exceeded fixed size of %d", Integer.valueOf(this.array.length)));
        }

        @Override // java.util.stream.Sink
        public void end() {
            if (this.curSize < this.array.length) {
                throw new IllegalStateException(String.format("End size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)));
            }
        }

        @Override // java.util.stream.Nodes.LongArrayNode
        public String toString() {
            return String.format("LongFixedNodeBuilder[%d][%s]", Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array));
        }
    }

    /* access modifiers changed from: private */
    public static final class DoubleFixedNodeBuilder extends DoubleArrayNode implements Node.Builder.OfDouble {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        DoubleFixedNodeBuilder(long size) {
            super(size);
        }

        /* Return type fixed from 'java.util.stream.Node$OfDouble' to match base method */
        @Override // java.util.stream.Node.Builder, java.util.stream.Node.Builder.OfDouble, java.util.stream.Node.Builder.OfDouble
        public Node<Double> build() {
            if (this.curSize >= this.array.length) {
                return this;
            }
            throw new IllegalStateException(String.format("Current size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)));
        }

        @Override // java.util.stream.Sink
        public void begin(long size) {
            if (size == ((long) this.array.length)) {
                this.curSize = 0;
            } else {
                throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d", Long.valueOf(size), Integer.valueOf(this.array.length)));
            }
        }

        @Override // java.util.stream.Sink.OfDouble, java.util.function.DoubleConsumer, java.util.stream.Sink
        public void accept(double i) {
            if (this.curSize < this.array.length) {
                double[] dArr = this.array;
                int i2 = this.curSize;
                this.curSize = i2 + 1;
                dArr[i2] = i;
                return;
            }
            throw new IllegalStateException(String.format("Accept exceeded fixed size of %d", Integer.valueOf(this.array.length)));
        }

        @Override // java.util.stream.Sink
        public void end() {
            if (this.curSize < this.array.length) {
                throw new IllegalStateException(String.format("End size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)));
            }
        }

        @Override // java.util.stream.Nodes.DoubleArrayNode
        public String toString() {
            return String.format("DoubleFixedNodeBuilder[%d][%s]", Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array));
        }
    }

    /* access modifiers changed from: private */
    public static final class IntSpinedNodeBuilder extends SpinedBuffer.OfInt implements Node.OfInt, Node.Builder.OfInt {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private boolean building = false;

        IntSpinedNodeBuilder() {
        }

        @Override // java.util.stream.SpinedBuffer.OfInt, java.util.stream.SpinedBuffer.OfInt, java.util.stream.Node.OfPrimitive, java.util.stream.Node.OfPrimitive, java.util.stream.Node, java.lang.Iterable
        public Spliterator.OfInt spliterator() {
            return super.spliterator();
        }

        public void forEach(IntConsumer consumer) {
            super.forEach((Object) consumer);
        }

        @Override // java.util.stream.Sink
        public void begin(long size) {
            this.building = true;
            clear();
            ensureCapacity(size);
        }

        @Override // java.util.stream.SpinedBuffer.OfInt, java.util.function.IntConsumer, java.util.stream.Sink.OfInt, java.util.stream.Sink
        public void accept(int i) {
            super.accept(i);
        }

        @Override // java.util.stream.Sink
        public void end() {
            this.building = false;
        }

        public void copyInto(int[] array, int offset) throws IndexOutOfBoundsException {
            super.copyInto((Object) array, offset);
        }

        @Override // java.util.stream.Node.OfPrimitive, java.util.stream.SpinedBuffer.OfPrimitive
        public int[] asPrimitiveArray() {
            return (int[]) super.asPrimitiveArray();
        }

        /* Return type fixed from 'java.util.stream.Node$OfInt' to match base method */
        @Override // java.util.stream.Node.Builder, java.util.stream.Node.Builder.OfInt, java.util.stream.Node.Builder.OfInt
        public Node<Integer> build() {
            return this;
        }
    }

    /* access modifiers changed from: private */
    public static final class LongSpinedNodeBuilder extends SpinedBuffer.OfLong implements Node.OfLong, Node.Builder.OfLong {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private boolean building = false;

        LongSpinedNodeBuilder() {
        }

        @Override // java.util.stream.Node.OfPrimitive, java.util.stream.Node.OfPrimitive, java.util.stream.Node, java.lang.Iterable, java.util.stream.SpinedBuffer.OfLong, java.util.stream.SpinedBuffer.OfLong
        public Spliterator.OfLong spliterator() {
            return super.spliterator();
        }

        public void forEach(LongConsumer consumer) {
            super.forEach((Object) consumer);
        }

        @Override // java.util.stream.Sink
        public void begin(long size) {
            this.building = true;
            clear();
            ensureCapacity(size);
        }

        @Override // java.util.function.LongConsumer, java.util.stream.SpinedBuffer.OfLong, java.util.stream.Sink.OfLong, java.util.stream.Sink
        public void accept(long i) {
            super.accept(i);
        }

        @Override // java.util.stream.Sink
        public void end() {
            this.building = false;
        }

        public void copyInto(long[] array, int offset) {
            super.copyInto((Object) array, offset);
        }

        @Override // java.util.stream.Node.OfPrimitive, java.util.stream.SpinedBuffer.OfPrimitive
        public long[] asPrimitiveArray() {
            return (long[]) super.asPrimitiveArray();
        }

        /* Return type fixed from 'java.util.stream.Node$OfLong' to match base method */
        @Override // java.util.stream.Node.Builder, java.util.stream.Node.Builder.OfLong, java.util.stream.Node.Builder.OfLong
        public Node<Long> build() {
            return this;
        }
    }

    /* access modifiers changed from: private */
    public static final class DoubleSpinedNodeBuilder extends SpinedBuffer.OfDouble implements Node.OfDouble, Node.Builder.OfDouble {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private boolean building = false;

        DoubleSpinedNodeBuilder() {
        }

        @Override // java.util.stream.Node.OfPrimitive, java.util.stream.Node.OfPrimitive, java.util.stream.Node, java.lang.Iterable, java.util.stream.SpinedBuffer.OfDouble, java.util.stream.SpinedBuffer.OfDouble
        public Spliterator.OfDouble spliterator() {
            return super.spliterator();
        }

        public void forEach(DoubleConsumer consumer) {
            super.forEach((Object) consumer);
        }

        @Override // java.util.stream.Sink
        public void begin(long size) {
            this.building = true;
            clear();
            ensureCapacity(size);
        }

        @Override // java.util.function.DoubleConsumer, java.util.stream.Sink.OfDouble, java.util.stream.SpinedBuffer.OfDouble, java.util.stream.Sink
        public void accept(double i) {
            super.accept(i);
        }

        @Override // java.util.stream.Sink
        public void end() {
            this.building = false;
        }

        public void copyInto(double[] array, int offset) {
            super.copyInto((Object) array, offset);
        }

        @Override // java.util.stream.Node.OfPrimitive, java.util.stream.SpinedBuffer.OfPrimitive
        public double[] asPrimitiveArray() {
            return (double[]) super.asPrimitiveArray();
        }

        /* Return type fixed from 'java.util.stream.Node$OfDouble' to match base method */
        @Override // java.util.stream.Node.Builder, java.util.stream.Node.Builder.OfDouble, java.util.stream.Node.Builder.OfDouble
        public Node<Double> build() {
            return this;
        }
    }

    private static abstract class SizedCollectorTask<P_IN, P_OUT, T_SINK extends Sink<P_OUT>, K extends SizedCollectorTask<P_IN, P_OUT, T_SINK, K>> extends CountedCompleter<Void> implements Sink<P_OUT> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        protected int fence;
        protected final PipelineHelper<P_OUT> helper;
        protected int index;
        protected long length;
        protected long offset;
        protected final Spliterator<P_IN> spliterator;
        protected final long targetSize;

        /* access modifiers changed from: package-private */
        public abstract K makeChild(Spliterator<P_IN> spliterator2, long j, long j2);

        SizedCollectorTask(Spliterator<P_IN> spliterator2, PipelineHelper<P_OUT> helper2, int arrayLength) {
            this.spliterator = spliterator2;
            this.helper = helper2;
            this.targetSize = AbstractTask.suggestTargetSize(spliterator2.estimateSize());
            this.offset = 0;
            this.length = (long) arrayLength;
        }

        SizedCollectorTask(K parent, Spliterator<P_IN> spliterator2, long offset2, long length2, int arrayLength) {
            super(parent);
            this.spliterator = spliterator2;
            this.helper = parent.helper;
            this.targetSize = parent.targetSize;
            this.offset = offset2;
            this.length = length2;
            if (offset2 < 0 || length2 < 0 || (offset2 + length2) - 1 >= ((long) arrayLength)) {
                throw new IllegalArgumentException(String.format("offset and length interval [%d, %d + %d) is not within array size interval [0, %d)", Long.valueOf(offset2), Long.valueOf(offset2), Long.valueOf(length2), Integer.valueOf(arrayLength)));
            }
        }

        @Override // java.util.concurrent.CountedCompleter
        public void compute() {
            Spliterator<P_IN> leftSplit;
            SizedCollectorTask<P_IN, P_OUT, T_SINK, K> task = this;
            Spliterator<P_IN> rightSplit = this.spliterator;
            while (rightSplit.estimateSize() > task.targetSize && (leftSplit = rightSplit.trySplit()) != null) {
                task.setPendingCount(1);
                long leftSplitSize = leftSplit.estimateSize();
                task.makeChild(leftSplit, task.offset, leftSplitSize).fork();
                task = task.makeChild(rightSplit, task.offset + leftSplitSize, task.length - leftSplitSize);
            }
            task.helper.wrapAndCopyInto(task, rightSplit);
            task.propagateCompletion();
        }

        @Override // java.util.stream.Sink
        public void begin(long size) {
            long j = this.length;
            if (size <= j) {
                this.index = (int) this.offset;
                this.fence = this.index + ((int) j);
                return;
            }
            throw new IllegalStateException("size passed to Sink.begin exceeds array length");
        }

        static final class OfRef<P_IN, P_OUT> extends SizedCollectorTask<P_IN, P_OUT, Sink<P_OUT>, OfRef<P_IN, P_OUT>> implements Sink<P_OUT> {
            private final P_OUT[] array;

            OfRef(Spliterator<P_IN> spliterator, PipelineHelper<P_OUT> helper, P_OUT[] array2) {
                super(spliterator, helper, array2.length);
                this.array = array2;
            }

            OfRef(OfRef<P_IN, P_OUT> parent, Spliterator<P_IN> spliterator, long offset, long length) {
                super(parent, spliterator, offset, length, parent.array.length);
                this.array = parent.array;
            }

            /* access modifiers changed from: package-private */
            @Override // java.util.stream.Nodes.SizedCollectorTask
            public OfRef<P_IN, P_OUT> makeChild(Spliterator<P_IN> spliterator, long offset, long size) {
                return new OfRef<>(this, spliterator, offset, size);
            }

            @Override // java.util.function.Consumer
            public void accept(P_OUT value) {
                if (this.index < this.fence) {
                    P_OUT[] p_outArr = this.array;
                    int i = this.index;
                    this.index = i + 1;
                    p_outArr[i] = value;
                    return;
                }
                throw new IndexOutOfBoundsException(Integer.toString(this.index));
            }
        }

        static final class OfInt<P_IN> extends SizedCollectorTask<P_IN, Integer, Sink.OfInt, OfInt<P_IN>> implements Sink.OfInt {
            private final int[] array;

            OfInt(Spliterator<P_IN> spliterator, PipelineHelper<Integer> helper, int[] array2) {
                super(spliterator, helper, array2.length);
                this.array = array2;
            }

            OfInt(OfInt<P_IN> parent, Spliterator<P_IN> spliterator, long offset, long length) {
                super(parent, spliterator, offset, length, parent.array.length);
                this.array = parent.array;
            }

            /* access modifiers changed from: package-private */
            @Override // java.util.stream.Nodes.SizedCollectorTask
            public OfInt<P_IN> makeChild(Spliterator<P_IN> spliterator, long offset, long size) {
                return new OfInt<>(this, spliterator, offset, size);
            }

            @Override // java.util.stream.Sink.OfInt, java.util.function.IntConsumer, java.util.stream.Sink
            public void accept(int value) {
                if (this.index < this.fence) {
                    int[] iArr = this.array;
                    int i = this.index;
                    this.index = i + 1;
                    iArr[i] = value;
                    return;
                }
                throw new IndexOutOfBoundsException(Integer.toString(this.index));
            }
        }

        static final class OfLong<P_IN> extends SizedCollectorTask<P_IN, Long, Sink.OfLong, OfLong<P_IN>> implements Sink.OfLong {
            private final long[] array;

            OfLong(Spliterator<P_IN> spliterator, PipelineHelper<Long> helper, long[] array2) {
                super(spliterator, helper, array2.length);
                this.array = array2;
            }

            OfLong(OfLong<P_IN> parent, Spliterator<P_IN> spliterator, long offset, long length) {
                super(parent, spliterator, offset, length, parent.array.length);
                this.array = parent.array;
            }

            /* access modifiers changed from: package-private */
            @Override // java.util.stream.Nodes.SizedCollectorTask
            public OfLong<P_IN> makeChild(Spliterator<P_IN> spliterator, long offset, long size) {
                return new OfLong<>(this, spliterator, offset, size);
            }

            @Override // java.util.stream.Sink.OfLong, java.util.stream.Sink, java.util.function.LongConsumer
            public void accept(long value) {
                if (this.index < this.fence) {
                    long[] jArr = this.array;
                    int i = this.index;
                    this.index = i + 1;
                    jArr[i] = value;
                    return;
                }
                throw new IndexOutOfBoundsException(Integer.toString(this.index));
            }
        }

        static final class OfDouble<P_IN> extends SizedCollectorTask<P_IN, Double, Sink.OfDouble, OfDouble<P_IN>> implements Sink.OfDouble {
            private final double[] array;

            OfDouble(Spliterator<P_IN> spliterator, PipelineHelper<Double> helper, double[] array2) {
                super(spliterator, helper, array2.length);
                this.array = array2;
            }

            OfDouble(OfDouble<P_IN> parent, Spliterator<P_IN> spliterator, long offset, long length) {
                super(parent, spliterator, offset, length, parent.array.length);
                this.array = parent.array;
            }

            /* access modifiers changed from: package-private */
            @Override // java.util.stream.Nodes.SizedCollectorTask
            public OfDouble<P_IN> makeChild(Spliterator<P_IN> spliterator, long offset, long size) {
                return new OfDouble<>(this, spliterator, offset, size);
            }

            @Override // java.util.stream.Sink.OfDouble, java.util.function.DoubleConsumer, java.util.stream.Sink
            public void accept(double value) {
                if (this.index < this.fence) {
                    double[] dArr = this.array;
                    int i = this.index;
                    this.index = i + 1;
                    dArr[i] = value;
                    return;
                }
                throw new IndexOutOfBoundsException(Integer.toString(this.index));
            }
        }
    }

    private static abstract class ToArrayTask<T, T_NODE extends Node<T>, K extends ToArrayTask<T, T_NODE, K>> extends CountedCompleter<Void> {
        protected final T_NODE node;
        protected final int offset;

        /* access modifiers changed from: package-private */
        public abstract void copyNodeToArray();

        /* access modifiers changed from: package-private */
        public abstract K makeChild(int i, int i2);

        ToArrayTask(T_NODE node2, int offset2) {
            this.node = node2;
            this.offset = offset2;
        }

        ToArrayTask(K parent, T_NODE node2, int offset2) {
            super(parent);
            this.node = node2;
            this.offset = offset2;
        }

        @Override // java.util.concurrent.CountedCompleter
        public void compute() {
            ToArrayTask<T, T_NODE, K> task = this;
            while (task.node.getChildCount() != 0) {
                task.setPendingCount(task.node.getChildCount() - 1);
                int size = 0;
                int i = 0;
                while (i < task.node.getChildCount() - 1) {
                    K leftTask = task.makeChild(i, task.offset + size);
                    size = (int) (((long) size) + leftTask.node.count());
                    leftTask.fork();
                    i++;
                }
                task = task.makeChild(i, task.offset + size);
            }
            task.copyNodeToArray();
            task.propagateCompletion();
        }

        /* access modifiers changed from: private */
        public static final class OfRef<T> extends ToArrayTask<T, Node<T>, OfRef<T>> {
            private final T[] array;

            /* synthetic */ OfRef(Node x0, Object[] x1, int x2, AnonymousClass1 x3) {
                this(x0, x1, x2);
            }

            private OfRef(Node<T> node, T[] array2, int offset) {
                super(node, offset);
                this.array = array2;
            }

            private OfRef(OfRef<T> parent, Node<T> node, int offset) {
                super(parent, node, offset);
                this.array = parent.array;
            }

            /* access modifiers changed from: package-private */
            @Override // java.util.stream.Nodes.ToArrayTask
            public OfRef<T> makeChild(int childIndex, int offset) {
                return new OfRef<>(this, this.node.getChild(childIndex), offset);
            }

            /* access modifiers changed from: package-private */
            @Override // java.util.stream.Nodes.ToArrayTask
            public void copyNodeToArray() {
                this.node.copyInto(this.array, this.offset);
            }
        }

        private static class OfPrimitive<T, T_CONS, T_ARR, T_SPLITR extends Spliterator.OfPrimitive<T, T_CONS, T_SPLITR>, T_NODE extends Node.OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, T_NODE>> extends ToArrayTask<T, T_NODE, OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, T_NODE>> {
            private final T_ARR array;

            /* synthetic */ OfPrimitive(Node.OfPrimitive x0, Object x1, int x2, AnonymousClass1 x3) {
                this(x0, x1, x2);
            }

            private OfPrimitive(T_NODE node, T_ARR array2, int offset) {
                super(node, offset);
                this.array = array2;
            }

            private OfPrimitive(OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, T_NODE> parent, T_NODE node, int offset) {
                super(parent, node, offset);
                this.array = parent.array;
            }

            /* access modifiers changed from: package-private */
            @Override // java.util.stream.Nodes.ToArrayTask
            public OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, T_NODE> makeChild(int childIndex, int offset) {
                return new OfPrimitive<>(this, ((Node.OfPrimitive) this.node).getChild(childIndex), offset);
            }

            /* access modifiers changed from: package-private */
            @Override // java.util.stream.Nodes.ToArrayTask
            public void copyNodeToArray() {
                ((Node.OfPrimitive) this.node).copyInto(this.array, this.offset);
            }
        }

        /* access modifiers changed from: private */
        public static final class OfInt extends OfPrimitive<Integer, IntConsumer, int[], Spliterator.OfInt, Node.OfInt> {
            /* synthetic */ OfInt(Node.OfInt x0, int[] x1, int x2, AnonymousClass1 x3) {
                this(x0, x1, x2);
            }

            private OfInt(Node.OfInt node, int[] array, int offset) {
                super(node, array, offset, null);
            }
        }

        /* access modifiers changed from: private */
        public static final class OfLong extends OfPrimitive<Long, LongConsumer, long[], Spliterator.OfLong, Node.OfLong> {
            /* synthetic */ OfLong(Node.OfLong x0, long[] x1, int x2, AnonymousClass1 x3) {
                this(x0, x1, x2);
            }

            private OfLong(Node.OfLong node, long[] array, int offset) {
                super(node, array, offset, null);
            }
        }

        /* access modifiers changed from: private */
        public static final class OfDouble extends OfPrimitive<Double, DoubleConsumer, double[], Spliterator.OfDouble, Node.OfDouble> {
            /* synthetic */ OfDouble(Node.OfDouble x0, double[] x1, int x2, AnonymousClass1 x3) {
                this(x0, x1, x2);
            }

            private OfDouble(Node.OfDouble node, double[] array, int offset) {
                super(node, array, offset, null);
            }
        }
    }

    private static class CollectorTask<P_IN, P_OUT, T_NODE extends Node<P_OUT>, T_BUILDER extends Node.Builder<P_OUT>> extends AbstractTask<P_IN, P_OUT, T_NODE, CollectorTask<P_IN, P_OUT, T_NODE, T_BUILDER>> {
        protected final LongFunction<T_BUILDER> builderFactory;
        protected final BinaryOperator<T_NODE> concFactory;
        protected final PipelineHelper<P_OUT> helper;

        CollectorTask(PipelineHelper<P_OUT> helper2, Spliterator<P_IN> spliterator, LongFunction<T_BUILDER> builderFactory2, BinaryOperator<T_NODE> concFactory2) {
            super(helper2, spliterator);
            this.helper = helper2;
            this.builderFactory = builderFactory2;
            this.concFactory = concFactory2;
        }

        CollectorTask(CollectorTask<P_IN, P_OUT, T_NODE, T_BUILDER> parent, Spliterator<P_IN> spliterator) {
            super(parent, spliterator);
            this.helper = parent.helper;
            this.builderFactory = parent.builderFactory;
            this.concFactory = parent.concFactory;
        }

        /* access modifiers changed from: protected */
        @Override // java.util.stream.AbstractTask
        public CollectorTask<P_IN, P_OUT, T_NODE, T_BUILDER> makeChild(Spliterator<P_IN> spliterator) {
            return new CollectorTask<>(this, spliterator);
        }

        /* access modifiers changed from: protected */
        @Override // java.util.stream.AbstractTask
        public T_NODE doLeaf() {
            return (T_NODE) ((Node.Builder) this.helper.wrapAndCopyInto(this.builderFactory.apply(this.helper.exactOutputSizeIfKnown(this.spliterator)), this.spliterator)).build();
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: java.util.function.BinaryOperator<T_NODE extends java.util.stream.Node<P_OUT>> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.CountedCompleter, java.util.stream.AbstractTask
        public void onCompletion(CountedCompleter<?> caller) {
            if (!isLeaf()) {
                setLocalResult((Node) this.concFactory.apply((Node) ((CollectorTask) this.leftChild).getLocalResult(), (Node) ((CollectorTask) this.rightChild).getLocalResult()));
            }
            super.onCompletion(caller);
        }

        /* access modifiers changed from: private */
        public static final class OfRef<P_IN, P_OUT> extends CollectorTask<P_IN, P_OUT, Node<P_OUT>, Node.Builder<P_OUT>> {
            OfRef(PipelineHelper<P_OUT> helper, IntFunction<P_OUT[]> generator, Spliterator<P_IN> spliterator) {
                super(helper, spliterator, new LongFunction() {
                    /* class java.util.stream.$$Lambda$Nodes$CollectorTask$OfRef$Zd2fdoBmZW0DbPHybIpYjfPyo */

                    @Override // java.util.function.LongFunction
                    public final Object apply(long j) {
                        return Nodes.builder(j, IntFunction.this);
                    }
                }, $$Lambda$Mo9ryI3XUGyoHfpnRL3BoFhaqY.INSTANCE);
            }
        }

        private static final class OfInt<P_IN> extends CollectorTask<P_IN, Integer, Node.OfInt, Node.Builder.OfInt> {
            OfInt(PipelineHelper<Integer> helper, Spliterator<P_IN> spliterator) {
                super(helper, spliterator, $$Lambda$B6rBjxAejI5kqKK9J3AHwY_L9ag.INSTANCE, $$Lambda$O4iFzVwtlyKFZkWcnfXHIHbxaTY.INSTANCE);
            }
        }

        private static final class OfLong<P_IN> extends CollectorTask<P_IN, Long, Node.OfLong, Node.Builder.OfLong> {
            OfLong(PipelineHelper<Long> helper, Spliterator<P_IN> spliterator) {
                super(helper, spliterator, $$Lambda$8ABiL5PN53c8rr14_yI2_4o5Zlo.INSTANCE, $$Lambda$eeRvX3cGN3C3qCAoKtOxCHIW8Lo.INSTANCE);
            }
        }

        private static final class OfDouble<P_IN> extends CollectorTask<P_IN, Double, Node.OfDouble, Node.Builder.OfDouble> {
            OfDouble(PipelineHelper<Double> helper, Spliterator<P_IN> spliterator) {
                super(helper, spliterator, $$Lambda$LfPL0444L8HcP6gPtdKqQiCTSfM.INSTANCE, $$Lambda$KTexUmxMdHIv08L4oU8j9HXK_go.INSTANCE);
            }
        }
    }
}
