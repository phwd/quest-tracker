package com.google.common.graph;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import java.util.AbstractSet;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
public abstract class AbstractBaseGraph<N> implements BaseGraph<N> {
    AbstractBaseGraph() {
    }

    /* access modifiers changed from: protected */
    public long edgeCount() {
        long degreeSum = 0;
        for (N node : nodes()) {
            degreeSum += (long) degree(node);
        }
        Preconditions.checkState((1 & degreeSum) == 0);
        return degreeSum >>> 1;
    }

    @Override // com.google.common.graph.BaseGraph
    public Set<EndpointPair<N>> edges() {
        return new AbstractSet<EndpointPair<N>>() {
            /* class com.google.common.graph.AbstractBaseGraph.AnonymousClass1 */

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public UnmodifiableIterator<EndpointPair<N>> iterator() {
                return EndpointPairIterator.of(AbstractBaseGraph.this);
            }

            public int size() {
                return Ints.saturatedCast(AbstractBaseGraph.this.edgeCount());
            }

            public boolean remove(Object o) {
                throw new UnsupportedOperationException();
            }

            /* JADX DEBUG: Multi-variable search result rejected for r2v6, resolved type: com.google.common.graph.AbstractBaseGraph */
            /* JADX WARN: Multi-variable type inference failed */
            public boolean contains(@NullableDecl Object obj) {
                if (!(obj instanceof EndpointPair)) {
                    return false;
                }
                EndpointPair<?> endpointPair = (EndpointPair) obj;
                if (AbstractBaseGraph.this.isDirected() != endpointPair.isOrdered() || !AbstractBaseGraph.this.nodes().contains(endpointPair.nodeU()) || !AbstractBaseGraph.this.successors(endpointPair.nodeU()).contains(endpointPair.nodeV())) {
                    return false;
                }
                return true;
            }
        };
    }

    @Override // com.google.common.graph.BaseGraph
    public Set<EndpointPair<N>> incidentEdges(N node) {
        Preconditions.checkNotNull(node);
        Preconditions.checkArgument(nodes().contains(node), "Node %s is not an element of this graph.", node);
        return IncidentEdgeSet.of(this, node);
    }

    @Override // com.google.common.graph.BaseGraph
    public int degree(N node) {
        if (isDirected()) {
            return IntMath.saturatedAdd(predecessors((Object) node).size(), successors((Object) node).size());
        }
        Set<N> neighbors = adjacentNodes(node);
        return IntMath.saturatedAdd(neighbors.size(), (!allowsSelfLoops() || !neighbors.contains(node)) ? 0 : 1);
    }

    @Override // com.google.common.graph.BaseGraph
    public int inDegree(N node) {
        return isDirected() ? predecessors((Object) node).size() : degree(node);
    }

    @Override // com.google.common.graph.BaseGraph
    public int outDegree(N node) {
        return isDirected() ? successors((Object) node).size() : degree(node);
    }

    @Override // com.google.common.graph.BaseGraph
    public boolean hasEdgeConnecting(N nodeU, N nodeV) {
        Preconditions.checkNotNull(nodeU);
        Preconditions.checkNotNull(nodeV);
        return nodes().contains(nodeU) && successors(nodeU).contains(nodeV);
    }

    private static abstract class IncidentEdgeSet<N> extends AbstractSet<EndpointPair<N>> {
        protected final BaseGraph<N> graph;
        protected final N node;

        public static <N> IncidentEdgeSet<N> of(BaseGraph<N> graph2, N node2) {
            return graph2.isDirected() ? new Directed(graph2, node2) : new Undirected(graph2, node2);
        }

        private IncidentEdgeSet(BaseGraph<N> graph2, N node2) {
            this.graph = graph2;
            this.node = node2;
        }

        public boolean remove(Object o) {
            throw new UnsupportedOperationException();
        }

        /* access modifiers changed from: private */
        public static final class Directed<N> extends IncidentEdgeSet<N> {
            private Directed(BaseGraph<N> graph, N node) {
                super(graph, node);
            }

            /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.graph.BaseGraph */
            /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: com.google.common.graph.BaseGraph */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public UnmodifiableIterator<EndpointPair<N>> iterator() {
                return Iterators.unmodifiableIterator(Iterators.concat(Iterators.transform(this.graph.predecessors(this.node).iterator(), new Function<N, EndpointPair<N>>() {
                    /* class com.google.common.graph.AbstractBaseGraph.IncidentEdgeSet.Directed.AnonymousClass1 */

                    @Override // com.google.common.base.Function
                    public EndpointPair<N> apply(N predecessor) {
                        return EndpointPair.ordered(predecessor, Directed.this.node);
                    }
                }), Iterators.transform(Sets.difference(this.graph.successors(this.node), ImmutableSet.of(this.node)).iterator(), new Function<N, EndpointPair<N>>() {
                    /* class com.google.common.graph.AbstractBaseGraph.IncidentEdgeSet.Directed.AnonymousClass2 */

                    @Override // com.google.common.base.Function
                    public EndpointPair<N> apply(N successor) {
                        return EndpointPair.ordered(Directed.this.node, successor);
                    }
                })));
            }

            /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.graph.BaseGraph */
            /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: com.google.common.graph.BaseGraph */
            /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: com.google.common.graph.BaseGraph */
            /* JADX WARN: Multi-variable type inference failed */
            public int size() {
                return (this.graph.outDegree(this.node) + this.graph.inDegree(this.node)) - (this.graph.successors(this.node).contains(this.node) ? 1 : 0);
            }

            /* JADX DEBUG: Multi-variable search result rejected for r4v6, resolved type: com.google.common.graph.BaseGraph */
            /* JADX DEBUG: Multi-variable search result rejected for r4v9, resolved type: com.google.common.graph.BaseGraph */
            /* JADX WARN: Multi-variable type inference failed */
            public boolean contains(@NullableDecl Object obj) {
                if (!(obj instanceof EndpointPair)) {
                    return false;
                }
                EndpointPair<?> endpointPair = (EndpointPair) obj;
                if (!endpointPair.isOrdered()) {
                    return false;
                }
                Object source = endpointPair.source();
                Object target = endpointPair.target();
                if ((!this.node.equals(source) || !this.graph.successors(this.node).contains(target)) && (!this.node.equals(target) || !this.graph.predecessors(this.node).contains(source))) {
                    return false;
                }
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static final class Undirected<N> extends IncidentEdgeSet<N> {
            private Undirected(BaseGraph<N> graph, N node) {
                super(graph, node);
            }

            /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.graph.BaseGraph */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public UnmodifiableIterator<EndpointPair<N>> iterator() {
                return Iterators.unmodifiableIterator(Iterators.transform(this.graph.adjacentNodes(this.node).iterator(), new Function<N, EndpointPair<N>>() {
                    /* class com.google.common.graph.AbstractBaseGraph.IncidentEdgeSet.Undirected.AnonymousClass1 */

                    @Override // com.google.common.base.Function
                    public EndpointPair<N> apply(N adjacentNode) {
                        return EndpointPair.unordered(Undirected.this.node, adjacentNode);
                    }
                }));
            }

            /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.graph.BaseGraph */
            /* JADX WARN: Multi-variable type inference failed */
            public int size() {
                return this.graph.adjacentNodes(this.node).size();
            }

            /* JADX DEBUG: Multi-variable search result rejected for r5v2, resolved type: com.google.common.graph.BaseGraph */
            /* JADX WARN: Multi-variable type inference failed */
            public boolean contains(@NullableDecl Object obj) {
                if (!(obj instanceof EndpointPair)) {
                    return false;
                }
                EndpointPair<?> endpointPair = (EndpointPair) obj;
                if (endpointPair.isOrdered()) {
                    return false;
                }
                Set<N> adjacent = this.graph.adjacentNodes(this.node);
                Object nodeU = endpointPair.nodeU();
                Object nodeV = endpointPair.nodeV();
                if ((!this.node.equals(nodeV) || !adjacent.contains(nodeU)) && (!this.node.equals(nodeU) || !adjacent.contains(nodeV))) {
                    return false;
                }
                return true;
            }
        }
    }
}
