package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Beta
public abstract class Traverser<N> {

    private enum Order {
        PREORDER,
        POSTORDER
    }

    public abstract Iterable<N> breadthFirst(Iterable<? extends N> iterable);

    public abstract Iterable<N> breadthFirst(N n);

    public abstract Iterable<N> depthFirstPostOrder(Iterable<? extends N> iterable);

    public abstract Iterable<N> depthFirstPostOrder(N n);

    public abstract Iterable<N> depthFirstPreOrder(Iterable<? extends N> iterable);

    public abstract Iterable<N> depthFirstPreOrder(N n);

    public static <N> Traverser<N> forGraph(SuccessorsFunction<N> successorsFunction) {
        Preconditions.checkNotNull(successorsFunction);
        return new GraphTraverser(successorsFunction);
    }

    public static <N> Traverser<N> forTree(SuccessorsFunction<N> successorsFunction) {
        Preconditions.checkNotNull(successorsFunction);
        if (successorsFunction instanceof BaseGraph) {
            Preconditions.checkArgument(((BaseGraph) successorsFunction).isDirected(), "Undirected graphs can never be trees.");
        }
        if (successorsFunction instanceof Network) {
            Preconditions.checkArgument(((Network) successorsFunction).isDirected(), "Undirected networks can never be trees.");
        }
        return new TreeTraverser(successorsFunction);
    }

    private Traverser() {
    }

    /* access modifiers changed from: private */
    public static final class GraphTraverser<N> extends Traverser<N> {
        private final SuccessorsFunction<N> graph;

        GraphTraverser(SuccessorsFunction<N> successorsFunction) {
            super();
            this.graph = (SuccessorsFunction) Preconditions.checkNotNull(successorsFunction);
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> breadthFirst(N n) {
            Preconditions.checkNotNull(n);
            return breadthFirst((Iterable) ImmutableSet.of(n));
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.google.common.graph.Traverser$GraphTraverser<N> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.Traverser
        public Iterable<N> breadthFirst(final Iterable<? extends N> iterable) {
            Preconditions.checkNotNull(iterable);
            if (Iterables.isEmpty(iterable)) {
                return ImmutableSet.of();
            }
            Iterator<? extends N> it = iterable.iterator();
            while (it.hasNext()) {
                checkThatNodeIsInGraph(it.next());
            }
            return new Iterable<N>() {
                /* class com.google.common.graph.Traverser.GraphTraverser.AnonymousClass1 */

                @Override // java.lang.Iterable
                public Iterator<N> iterator() {
                    return new BreadthFirstIterator(iterable);
                }
            };
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPreOrder(N n) {
            Preconditions.checkNotNull(n);
            return depthFirstPreOrder((Iterable) ImmutableSet.of(n));
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.google.common.graph.Traverser$GraphTraverser<N> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPreOrder(final Iterable<? extends N> iterable) {
            Preconditions.checkNotNull(iterable);
            if (Iterables.isEmpty(iterable)) {
                return ImmutableSet.of();
            }
            Iterator<? extends N> it = iterable.iterator();
            while (it.hasNext()) {
                checkThatNodeIsInGraph(it.next());
            }
            return new Iterable<N>() {
                /* class com.google.common.graph.Traverser.GraphTraverser.AnonymousClass2 */

                @Override // java.lang.Iterable
                public Iterator<N> iterator() {
                    return new DepthFirstIterator(iterable, Order.PREORDER);
                }
            };
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPostOrder(N n) {
            Preconditions.checkNotNull(n);
            return depthFirstPostOrder((Iterable) ImmutableSet.of(n));
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.google.common.graph.Traverser$GraphTraverser<N> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPostOrder(final Iterable<? extends N> iterable) {
            Preconditions.checkNotNull(iterable);
            if (Iterables.isEmpty(iterable)) {
                return ImmutableSet.of();
            }
            Iterator<? extends N> it = iterable.iterator();
            while (it.hasNext()) {
                checkThatNodeIsInGraph(it.next());
            }
            return new Iterable<N>() {
                /* class com.google.common.graph.Traverser.GraphTraverser.AnonymousClass3 */

                @Override // java.lang.Iterable
                public Iterator<N> iterator() {
                    return new DepthFirstIterator(iterable, Order.POSTORDER);
                }
            };
        }

        private void checkThatNodeIsInGraph(N n) {
            this.graph.successors(n);
        }

        private final class BreadthFirstIterator extends UnmodifiableIterator<N> {
            private final Queue<N> queue = new ArrayDeque();
            private final Set<N> visited = new HashSet();

            /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.Set<N> */
            /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: java.util.Queue<N> */
            /* JADX WARN: Multi-variable type inference failed */
            BreadthFirstIterator(Iterable<? extends N> iterable) {
                for (Object obj : iterable) {
                    if (this.visited.add(obj)) {
                        this.queue.add(obj);
                    }
                }
            }

            public boolean hasNext() {
                return !this.queue.isEmpty();
            }

            /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.util.Set<N> */
            /* JADX DEBUG: Multi-variable search result rejected for r3v2, resolved type: java.util.Queue<N> */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Iterator
            public N next() {
                N remove = this.queue.remove();
                for (Object obj : GraphTraverser.this.graph.successors(remove)) {
                    if (this.visited.add(obj)) {
                        this.queue.add(obj);
                    }
                }
                return remove;
            }
        }

        private final class DepthFirstIterator extends AbstractIterator<N> {
            private final Order order;
            private final Deque<GraphTraverser<N>.DepthFirstIterator.NodeAndSuccessors> stack = new ArrayDeque();
            private final Set<N> visited = new HashSet();

            DepthFirstIterator(Iterable<? extends N> iterable, Order order2) {
                this.stack.push(new NodeAndSuccessors(null, iterable));
                this.order = order2;
            }

            /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: com.google.common.graph.Traverser$GraphTraverser$DepthFirstIterator */
            /* JADX WARN: Multi-variable type inference failed */
            /* access modifiers changed from: protected */
            @Override // com.google.common.collect.AbstractIterator
            public N computeNext() {
                while (!this.stack.isEmpty()) {
                    GraphTraverser<N>.DepthFirstIterator.NodeAndSuccessors first = this.stack.getFirst();
                    boolean add = this.visited.add(first.node);
                    boolean z = true;
                    boolean z2 = !first.successorIterator.hasNext();
                    if ((!add || this.order != Order.PREORDER) && (!z2 || this.order != Order.POSTORDER)) {
                        z = false;
                    }
                    if (z2) {
                        this.stack.pop();
                    } else {
                        Object next = first.successorIterator.next();
                        if (!this.visited.contains(next)) {
                            this.stack.push(withSuccessors(next));
                        }
                    }
                    if (z && first.node != null) {
                        return first.node;
                    }
                }
                return (N) endOfData();
            }

            /* access modifiers changed from: package-private */
            public GraphTraverser<N>.DepthFirstIterator.NodeAndSuccessors withSuccessors(N n) {
                return new NodeAndSuccessors(n, GraphTraverser.this.graph.successors(n));
            }

            /* access modifiers changed from: private */
            public final class NodeAndSuccessors {
                @NullableDecl
                final N node;
                final Iterator<? extends N> successorIterator;

                NodeAndSuccessors(@NullableDecl N n, Iterable<? extends N> iterable) {
                    this.node = n;
                    this.successorIterator = iterable.iterator();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static final class TreeTraverser<N> extends Traverser<N> {
        private final SuccessorsFunction<N> tree;

        TreeTraverser(SuccessorsFunction<N> successorsFunction) {
            super();
            this.tree = (SuccessorsFunction) Preconditions.checkNotNull(successorsFunction);
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> breadthFirst(N n) {
            Preconditions.checkNotNull(n);
            return breadthFirst((Iterable) ImmutableSet.of(n));
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.google.common.graph.Traverser$TreeTraverser<N> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.Traverser
        public Iterable<N> breadthFirst(final Iterable<? extends N> iterable) {
            Preconditions.checkNotNull(iterable);
            if (Iterables.isEmpty(iterable)) {
                return ImmutableSet.of();
            }
            Iterator<? extends N> it = iterable.iterator();
            while (it.hasNext()) {
                checkThatNodeIsInTree(it.next());
            }
            return new Iterable<N>() {
                /* class com.google.common.graph.Traverser.TreeTraverser.AnonymousClass1 */

                @Override // java.lang.Iterable
                public Iterator<N> iterator() {
                    return new BreadthFirstIterator(iterable);
                }
            };
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPreOrder(N n) {
            Preconditions.checkNotNull(n);
            return depthFirstPreOrder((Iterable) ImmutableSet.of(n));
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.google.common.graph.Traverser$TreeTraverser<N> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPreOrder(final Iterable<? extends N> iterable) {
            Preconditions.checkNotNull(iterable);
            if (Iterables.isEmpty(iterable)) {
                return ImmutableSet.of();
            }
            Iterator<? extends N> it = iterable.iterator();
            while (it.hasNext()) {
                checkThatNodeIsInTree(it.next());
            }
            return new Iterable<N>() {
                /* class com.google.common.graph.Traverser.TreeTraverser.AnonymousClass2 */

                @Override // java.lang.Iterable
                public Iterator<N> iterator() {
                    return new DepthFirstPreOrderIterator(iterable);
                }
            };
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPostOrder(N n) {
            Preconditions.checkNotNull(n);
            return depthFirstPostOrder((Iterable) ImmutableSet.of(n));
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.google.common.graph.Traverser$TreeTraverser<N> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPostOrder(final Iterable<? extends N> iterable) {
            Preconditions.checkNotNull(iterable);
            if (Iterables.isEmpty(iterable)) {
                return ImmutableSet.of();
            }
            Iterator<? extends N> it = iterable.iterator();
            while (it.hasNext()) {
                checkThatNodeIsInTree(it.next());
            }
            return new Iterable<N>() {
                /* class com.google.common.graph.Traverser.TreeTraverser.AnonymousClass3 */

                @Override // java.lang.Iterable
                public Iterator<N> iterator() {
                    return new DepthFirstPostOrderIterator(iterable);
                }
            };
        }

        private void checkThatNodeIsInTree(N n) {
            this.tree.successors(n);
        }

        private final class BreadthFirstIterator extends UnmodifiableIterator<N> {
            private final Queue<N> queue = new ArrayDeque();

            /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.Queue<N> */
            /* JADX WARN: Multi-variable type inference failed */
            BreadthFirstIterator(Iterable<? extends N> iterable) {
                Iterator<? extends N> it = iterable.iterator();
                while (it.hasNext()) {
                    this.queue.add(it.next());
                }
            }

            public boolean hasNext() {
                return !this.queue.isEmpty();
            }

            @Override // java.util.Iterator
            public N next() {
                N remove = this.queue.remove();
                Iterables.addAll(this.queue, TreeTraverser.this.tree.successors(remove));
                return remove;
            }
        }

        private final class DepthFirstPreOrderIterator extends UnmodifiableIterator<N> {
            private final Deque<Iterator<? extends N>> stack = new ArrayDeque();

            DepthFirstPreOrderIterator(Iterable<? extends N> iterable) {
                this.stack.addLast(iterable.iterator());
            }

            public boolean hasNext() {
                return !this.stack.isEmpty();
            }

            @Override // java.util.Iterator
            public N next() {
                Iterator<? extends N> last = this.stack.getLast();
                N n = (N) Preconditions.checkNotNull(last.next());
                if (!last.hasNext()) {
                    this.stack.removeLast();
                }
                Iterator<? extends N> it = TreeTraverser.this.tree.successors(n).iterator();
                if (it.hasNext()) {
                    this.stack.addLast(it);
                }
                return n;
            }
        }

        private final class DepthFirstPostOrderIterator extends AbstractIterator<N> {
            private final ArrayDeque<TreeTraverser<N>.DepthFirstPostOrderIterator.NodeAndChildren> stack = new ArrayDeque<>();

            DepthFirstPostOrderIterator(Iterable<? extends N> iterable) {
                this.stack.addLast(new NodeAndChildren(null, iterable));
            }

            /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.google.common.graph.Traverser$TreeTraverser$DepthFirstPostOrderIterator */
            /* JADX WARN: Multi-variable type inference failed */
            /* access modifiers changed from: protected */
            @Override // com.google.common.collect.AbstractIterator
            public N computeNext() {
                while (!this.stack.isEmpty()) {
                    TreeTraverser<N>.DepthFirstPostOrderIterator.NodeAndChildren last = this.stack.getLast();
                    if (last.childIterator.hasNext()) {
                        this.stack.addLast(withChildren(last.childIterator.next()));
                    } else {
                        this.stack.removeLast();
                        if (last.node != null) {
                            return last.node;
                        }
                    }
                }
                return (N) endOfData();
            }

            /* access modifiers changed from: package-private */
            public TreeTraverser<N>.DepthFirstPostOrderIterator.NodeAndChildren withChildren(N n) {
                return new NodeAndChildren(n, TreeTraverser.this.tree.successors(n));
            }

            /* access modifiers changed from: private */
            public final class NodeAndChildren {
                final Iterator<? extends N> childIterator;
                @NullableDecl
                final N node;

                NodeAndChildren(@NullableDecl N n, Iterable<? extends N> iterable) {
                    this.node = n;
                    this.childIterator = iterable.iterator();
                }
            }
        }
    }
}
