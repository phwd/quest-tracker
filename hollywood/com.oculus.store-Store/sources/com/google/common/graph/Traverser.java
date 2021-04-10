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

    public static <N> Traverser<N> forGraph(SuccessorsFunction<N> graph) {
        Preconditions.checkNotNull(graph);
        return new GraphTraverser(graph);
    }

    public static <N> Traverser<N> forTree(SuccessorsFunction<N> tree) {
        Preconditions.checkNotNull(tree);
        if (tree instanceof BaseGraph) {
            Preconditions.checkArgument(((BaseGraph) tree).isDirected(), "Undirected graphs can never be trees.");
        }
        if (tree instanceof Network) {
            Preconditions.checkArgument(((Network) tree).isDirected(), "Undirected networks can never be trees.");
        }
        return new TreeTraverser(tree);
    }

    private Traverser() {
    }

    /* access modifiers changed from: private */
    public static final class GraphTraverser<N> extends Traverser<N> {
        private final SuccessorsFunction<N> graph;

        GraphTraverser(SuccessorsFunction<N> graph2) {
            super();
            this.graph = (SuccessorsFunction) Preconditions.checkNotNull(graph2);
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> breadthFirst(N startNode) {
            Preconditions.checkNotNull(startNode);
            return breadthFirst((Iterable) ImmutableSet.of(startNode));
        }

        /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.google.common.graph.Traverser$GraphTraverser<N> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.Traverser
        public Iterable<N> breadthFirst(final Iterable<? extends N> startNodes) {
            Preconditions.checkNotNull(startNodes);
            if (Iterables.isEmpty(startNodes)) {
                return ImmutableSet.of();
            }
            Iterator<? extends N> it = startNodes.iterator();
            while (it.hasNext()) {
                checkThatNodeIsInGraph(it.next());
            }
            return new Iterable<N>() {
                /* class com.google.common.graph.Traverser.GraphTraverser.AnonymousClass1 */

                @Override // java.lang.Iterable
                public Iterator<N> iterator() {
                    return new BreadthFirstIterator(startNodes);
                }
            };
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPreOrder(N startNode) {
            Preconditions.checkNotNull(startNode);
            return depthFirstPreOrder((Iterable) ImmutableSet.of(startNode));
        }

        /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.google.common.graph.Traverser$GraphTraverser<N> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPreOrder(final Iterable<? extends N> startNodes) {
            Preconditions.checkNotNull(startNodes);
            if (Iterables.isEmpty(startNodes)) {
                return ImmutableSet.of();
            }
            Iterator<? extends N> it = startNodes.iterator();
            while (it.hasNext()) {
                checkThatNodeIsInGraph(it.next());
            }
            return new Iterable<N>() {
                /* class com.google.common.graph.Traverser.GraphTraverser.AnonymousClass2 */

                @Override // java.lang.Iterable
                public Iterator<N> iterator() {
                    return new DepthFirstIterator(startNodes, Order.PREORDER);
                }
            };
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPostOrder(N startNode) {
            Preconditions.checkNotNull(startNode);
            return depthFirstPostOrder((Iterable) ImmutableSet.of(startNode));
        }

        /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.google.common.graph.Traverser$GraphTraverser<N> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPostOrder(final Iterable<? extends N> startNodes) {
            Preconditions.checkNotNull(startNodes);
            if (Iterables.isEmpty(startNodes)) {
                return ImmutableSet.of();
            }
            Iterator<? extends N> it = startNodes.iterator();
            while (it.hasNext()) {
                checkThatNodeIsInGraph(it.next());
            }
            return new Iterable<N>() {
                /* class com.google.common.graph.Traverser.GraphTraverser.AnonymousClass3 */

                @Override // java.lang.Iterable
                public Iterator<N> iterator() {
                    return new DepthFirstIterator(startNodes, Order.POSTORDER);
                }
            };
        }

        private void checkThatNodeIsInGraph(N startNode) {
            this.graph.successors(startNode);
        }

        private final class BreadthFirstIterator extends UnmodifiableIterator<N> {
            private final Queue<N> queue = new ArrayDeque();
            private final Set<N> visited = new HashSet();

            /* JADX DEBUG: Multi-variable search result rejected for r2v1, resolved type: java.util.Set<N> */
            /* JADX DEBUG: Multi-variable search result rejected for r2v3, resolved type: java.util.Queue<N> */
            /* JADX WARN: Multi-variable type inference failed */
            BreadthFirstIterator(Iterable<? extends N> roots) {
                for (Object obj : roots) {
                    if (this.visited.add(obj)) {
                        this.queue.add(obj);
                    }
                }
            }

            public boolean hasNext() {
                return !this.queue.isEmpty();
            }

            /* JADX DEBUG: Multi-variable search result rejected for r3v1, resolved type: java.util.Set<N> */
            /* JADX DEBUG: Multi-variable search result rejected for r3v3, resolved type: java.util.Queue<N> */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Iterator
            public N next() {
                N current = this.queue.remove();
                for (Object obj : GraphTraverser.this.graph.successors(current)) {
                    if (this.visited.add(obj)) {
                        this.queue.add(obj);
                    }
                }
                return current;
            }
        }

        private final class DepthFirstIterator extends AbstractIterator<N> {
            private final Order order;
            private final Deque<GraphTraverser<N>.DepthFirstIterator.NodeAndSuccessors> stack = new ArrayDeque();
            private final Set<N> visited = new HashSet();

            DepthFirstIterator(Iterable<? extends N> roots, Order order2) {
                this.stack.push(new NodeAndSuccessors(null, roots));
                this.order = order2;
            }

            /* JADX DEBUG: Multi-variable search result rejected for r9v0, resolved type: com.google.common.graph.Traverser$GraphTraverser$DepthFirstIterator */
            /* JADX WARN: Multi-variable type inference failed */
            /* access modifiers changed from: protected */
            @Override // com.google.common.collect.AbstractIterator
            public N computeNext() {
                boolean lastVisit;
                boolean produceNode;
                while (!this.stack.isEmpty()) {
                    GraphTraverser<N>.DepthFirstIterator.NodeAndSuccessors nodeAndSuccessors = this.stack.getFirst();
                    boolean firstVisit = this.visited.add(nodeAndSuccessors.node);
                    if (!nodeAndSuccessors.successorIterator.hasNext()) {
                        lastVisit = true;
                    } else {
                        lastVisit = false;
                    }
                    if ((!firstVisit || this.order != Order.PREORDER) && (!lastVisit || this.order != Order.POSTORDER)) {
                        produceNode = false;
                    } else {
                        produceNode = true;
                    }
                    if (lastVisit) {
                        this.stack.pop();
                    } else {
                        Object next = nodeAndSuccessors.successorIterator.next();
                        if (!this.visited.contains(next)) {
                            this.stack.push(withSuccessors(next));
                        }
                    }
                    if (produceNode && nodeAndSuccessors.node != null) {
                        return nodeAndSuccessors.node;
                    }
                }
                return (N) endOfData();
            }

            /* access modifiers changed from: package-private */
            public GraphTraverser<N>.DepthFirstIterator.NodeAndSuccessors withSuccessors(N node) {
                return new NodeAndSuccessors(node, GraphTraverser.this.graph.successors(node));
            }

            /* access modifiers changed from: private */
            public final class NodeAndSuccessors {
                @NullableDecl
                final N node;
                final Iterator<? extends N> successorIterator;

                NodeAndSuccessors(@NullableDecl N node2, Iterable<? extends N> successors) {
                    this.node = node2;
                    this.successorIterator = successors.iterator();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static final class TreeTraverser<N> extends Traverser<N> {
        private final SuccessorsFunction<N> tree;

        TreeTraverser(SuccessorsFunction<N> tree2) {
            super();
            this.tree = (SuccessorsFunction) Preconditions.checkNotNull(tree2);
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> breadthFirst(N startNode) {
            Preconditions.checkNotNull(startNode);
            return breadthFirst((Iterable) ImmutableSet.of(startNode));
        }

        /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.google.common.graph.Traverser$TreeTraverser<N> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.Traverser
        public Iterable<N> breadthFirst(final Iterable<? extends N> startNodes) {
            Preconditions.checkNotNull(startNodes);
            if (Iterables.isEmpty(startNodes)) {
                return ImmutableSet.of();
            }
            Iterator<? extends N> it = startNodes.iterator();
            while (it.hasNext()) {
                checkThatNodeIsInTree(it.next());
            }
            return new Iterable<N>() {
                /* class com.google.common.graph.Traverser.TreeTraverser.AnonymousClass1 */

                @Override // java.lang.Iterable
                public Iterator<N> iterator() {
                    return new BreadthFirstIterator(startNodes);
                }
            };
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPreOrder(N startNode) {
            Preconditions.checkNotNull(startNode);
            return depthFirstPreOrder((Iterable) ImmutableSet.of(startNode));
        }

        /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.google.common.graph.Traverser$TreeTraverser<N> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPreOrder(final Iterable<? extends N> startNodes) {
            Preconditions.checkNotNull(startNodes);
            if (Iterables.isEmpty(startNodes)) {
                return ImmutableSet.of();
            }
            Iterator<? extends N> it = startNodes.iterator();
            while (it.hasNext()) {
                checkThatNodeIsInTree(it.next());
            }
            return new Iterable<N>() {
                /* class com.google.common.graph.Traverser.TreeTraverser.AnonymousClass2 */

                @Override // java.lang.Iterable
                public Iterator<N> iterator() {
                    return new DepthFirstPreOrderIterator(startNodes);
                }
            };
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPostOrder(N startNode) {
            Preconditions.checkNotNull(startNode);
            return depthFirstPostOrder((Iterable) ImmutableSet.of(startNode));
        }

        /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.google.common.graph.Traverser$TreeTraverser<N> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPostOrder(final Iterable<? extends N> startNodes) {
            Preconditions.checkNotNull(startNodes);
            if (Iterables.isEmpty(startNodes)) {
                return ImmutableSet.of();
            }
            Iterator<? extends N> it = startNodes.iterator();
            while (it.hasNext()) {
                checkThatNodeIsInTree(it.next());
            }
            return new Iterable<N>() {
                /* class com.google.common.graph.Traverser.TreeTraverser.AnonymousClass3 */

                @Override // java.lang.Iterable
                public Iterator<N> iterator() {
                    return new DepthFirstPostOrderIterator(startNodes);
                }
            };
        }

        private void checkThatNodeIsInTree(N startNode) {
            this.tree.successors(startNode);
        }

        private final class BreadthFirstIterator extends UnmodifiableIterator<N> {
            private final Queue<N> queue = new ArrayDeque();

            /* JADX DEBUG: Multi-variable search result rejected for r2v1, resolved type: java.util.Queue<N> */
            /* JADX WARN: Multi-variable type inference failed */
            BreadthFirstIterator(Iterable<? extends N> roots) {
                Iterator<? extends N> it = roots.iterator();
                while (it.hasNext()) {
                    this.queue.add(it.next());
                }
            }

            public boolean hasNext() {
                return !this.queue.isEmpty();
            }

            @Override // java.util.Iterator
            public N next() {
                N current = this.queue.remove();
                Iterables.addAll(this.queue, TreeTraverser.this.tree.successors(current));
                return current;
            }
        }

        private final class DepthFirstPreOrderIterator extends UnmodifiableIterator<N> {
            private final Deque<Iterator<? extends N>> stack = new ArrayDeque();

            DepthFirstPreOrderIterator(Iterable<? extends N> roots) {
                this.stack.addLast(roots.iterator());
            }

            public boolean hasNext() {
                return !this.stack.isEmpty();
            }

            @Override // java.util.Iterator
            public N next() {
                Iterator<? extends N> iterator = this.stack.getLast();
                N result = (N) Preconditions.checkNotNull(iterator.next());
                if (!iterator.hasNext()) {
                    this.stack.removeLast();
                }
                Iterator<? extends N> childIterator = TreeTraverser.this.tree.successors(result).iterator();
                if (childIterator.hasNext()) {
                    this.stack.addLast(childIterator);
                }
                return result;
            }
        }

        private final class DepthFirstPostOrderIterator extends AbstractIterator<N> {
            private final ArrayDeque<TreeTraverser<N>.DepthFirstPostOrderIterator.NodeAndChildren> stack = new ArrayDeque<>();

            DepthFirstPostOrderIterator(Iterable<? extends N> roots) {
                this.stack.addLast(new NodeAndChildren(null, roots));
            }

            /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.google.common.graph.Traverser$TreeTraverser$DepthFirstPostOrderIterator */
            /* JADX WARN: Multi-variable type inference failed */
            /* access modifiers changed from: protected */
            @Override // com.google.common.collect.AbstractIterator
            public N computeNext() {
                while (!this.stack.isEmpty()) {
                    TreeTraverser<N>.DepthFirstPostOrderIterator.NodeAndChildren top = this.stack.getLast();
                    if (top.childIterator.hasNext()) {
                        this.stack.addLast(withChildren(top.childIterator.next()));
                    } else {
                        this.stack.removeLast();
                        if (top.node != null) {
                            return top.node;
                        }
                    }
                }
                return (N) endOfData();
            }

            /* access modifiers changed from: package-private */
            public TreeTraverser<N>.DepthFirstPostOrderIterator.NodeAndChildren withChildren(N node) {
                return new NodeAndChildren(node, TreeTraverser.this.tree.successors(node));
            }

            /* access modifiers changed from: private */
            public final class NodeAndChildren {
                final Iterator<? extends N> childIterator;
                @NullableDecl
                final N node;

                NodeAndChildren(@NullableDecl N node2, Iterable<? extends N> children) {
                    this.node = node2;
                    this.childIterator = children.iterator();
                }
            }
        }
    }
}
