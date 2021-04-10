package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.math.IntMath;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Beta
public abstract class AbstractNetwork<N, E> implements Network<N, E> {
    @Override // com.google.common.graph.Network
    public Graph<N> asGraph() {
        return new AbstractGraph<N>() {
            /* class com.google.common.graph.AbstractNetwork.AnonymousClass1 */

            @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
            public Set<N> nodes() {
                return AbstractNetwork.this.nodes();
            }

            @Override // com.google.common.graph.BaseGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.Graph
            public Set<EndpointPair<N>> edges() {
                if (AbstractNetwork.this.allowsParallelEdges()) {
                    return super.edges();
                }
                return new AbstractSet<EndpointPair<N>>() {
                    /* class com.google.common.graph.AbstractNetwork.AnonymousClass1.AnonymousClass1 */

                    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
                    public Iterator<EndpointPair<N>> iterator() {
                        return Iterators.transform(AbstractNetwork.this.edges().iterator(), new Function<E, EndpointPair<N>>() {
                            /* class com.google.common.graph.AbstractNetwork.AnonymousClass1.AnonymousClass1.AnonymousClass1 */

                            @Override // com.google.common.base.Function
                            public EndpointPair<N> apply(E e) {
                                return AbstractNetwork.this.incidentNodes(e);
                            }
                        });
                    }

                    public int size() {
                        return AbstractNetwork.this.edges().size();
                    }

                    /* JADX DEBUG: Multi-variable search result rejected for r0v6, resolved type: com.google.common.graph.AbstractNetwork$1 */
                    /* JADX WARN: Multi-variable type inference failed */
                    public boolean contains(@NullableDecl Object obj) {
                        if (!(obj instanceof EndpointPair)) {
                            return false;
                        }
                        EndpointPair endpointPair = (EndpointPair) obj;
                        if (AnonymousClass1.this.isDirected() != endpointPair.isOrdered() || !AnonymousClass1.this.nodes().contains(endpointPair.nodeU()) || !AnonymousClass1.this.successors(endpointPair.nodeU()).contains(endpointPair.nodeV())) {
                            return false;
                        }
                        return true;
                    }
                };
            }

            @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
            public ElementOrder<N> nodeOrder() {
                return AbstractNetwork.this.nodeOrder();
            }

            @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
            public boolean isDirected() {
                return AbstractNetwork.this.isDirected();
            }

            @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
            public boolean allowsSelfLoops() {
                return AbstractNetwork.this.allowsSelfLoops();
            }

            @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
            public Set<N> adjacentNodes(N n) {
                return AbstractNetwork.this.adjacentNodes(n);
            }

            @Override // com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction, com.google.common.graph.Graph
            public Set<N> predecessors(N n) {
                return AbstractNetwork.this.predecessors((Object) n);
            }

            @Override // com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
            public Set<N> successors(N n) {
                return AbstractNetwork.this.successors((Object) n);
            }
        };
    }

    @Override // com.google.common.graph.Network
    public int degree(N n) {
        if (isDirected()) {
            return IntMath.saturatedAdd(inEdges(n).size(), outEdges(n).size());
        }
        return IntMath.saturatedAdd(incidentEdges(n).size(), edgesConnecting(n, n).size());
    }

    @Override // com.google.common.graph.Network
    public int inDegree(N n) {
        return isDirected() ? inEdges(n).size() : degree(n);
    }

    @Override // com.google.common.graph.Network
    public int outDegree(N n) {
        return isDirected() ? outEdges(n).size() : degree(n);
    }

    @Override // com.google.common.graph.Network
    public Set<E> adjacentEdges(E e) {
        EndpointPair<N> incidentNodes = incidentNodes(e);
        return Sets.difference(Sets.union(incidentEdges(incidentNodes.nodeU()), incidentEdges(incidentNodes.nodeV())), ImmutableSet.of(e));
    }

    @Override // com.google.common.graph.Network
    public Set<E> edgesConnecting(N n, N n2) {
        Set<E> outEdges = outEdges(n);
        Set<E> inEdges = inEdges(n2);
        if (outEdges.size() <= inEdges.size()) {
            return Collections.unmodifiableSet(Sets.filter(outEdges, connectedPredicate(n, n2)));
        }
        return Collections.unmodifiableSet(Sets.filter(inEdges, connectedPredicate(n2, n)));
    }

    private Predicate<E> connectedPredicate(final N n, final N n2) {
        return new Predicate<E>() {
            /* class com.google.common.graph.AbstractNetwork.AnonymousClass2 */

            @Override // com.google.common.base.Predicate
            public boolean apply(E e) {
                return AbstractNetwork.this.incidentNodes(e).adjacentNode(n).equals(n2);
            }
        };
    }

    @Override // com.google.common.graph.Network
    @NullableDecl
    public E edgeConnectingOrNull(N n, N n2) {
        Set<E> edgesConnecting = edgesConnecting(n, n2);
        int size = edgesConnecting.size();
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            return edgesConnecting.iterator().next();
        }
        throw new IllegalArgumentException(String.format("Cannot call edgeConnecting() when parallel edges exist between %s and %s. Consider calling edgesConnecting() instead.", n, n2));
    }

    @Override // com.google.common.graph.Network
    public boolean hasEdgeConnecting(N n, N n2) {
        return !edgesConnecting(n, n2).isEmpty();
    }

    @Override // com.google.common.graph.Network
    public final boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Network)) {
            return false;
        }
        Network network = (Network) obj;
        if (isDirected() != network.isDirected() || !nodes().equals(network.nodes()) || !edgeIncidentNodesMap(this).equals(edgeIncidentNodesMap(network))) {
            return false;
        }
        return true;
    }

    @Override // com.google.common.graph.Network
    public final int hashCode() {
        return edgeIncidentNodesMap(this).hashCode();
    }

    public String toString() {
        return "isDirected: " + isDirected() + ", allowsParallelEdges: " + allowsParallelEdges() + ", allowsSelfLoops: " + allowsSelfLoops() + ", nodes: " + nodes() + ", edges: " + edgeIncidentNodesMap(this);
    }

    private static <N, E> Map<E, EndpointPair<N>> edgeIncidentNodesMap(final Network<N, E> network) {
        return Maps.asMap(network.edges(), new Function<E, EndpointPair<N>>() {
            /* class com.google.common.graph.AbstractNetwork.AnonymousClass3 */

            @Override // com.google.common.base.Function
            public EndpointPair<N> apply(E e) {
                return Network.this.incidentNodes(e);
            }
        });
    }
}
