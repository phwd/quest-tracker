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
                            public EndpointPair<N> apply(E edge) {
                                return AbstractNetwork.this.incidentNodes(edge);
                            }
                        });
                    }

                    public int size() {
                        return AbstractNetwork.this.edges().size();
                    }

                    /* JADX DEBUG: Multi-variable search result rejected for r2v6, resolved type: com.google.common.graph.AbstractNetwork$1 */
                    /* JADX WARN: Multi-variable type inference failed */
                    public boolean contains(@NullableDecl Object obj) {
                        if (!(obj instanceof EndpointPair)) {
                            return false;
                        }
                        EndpointPair<?> endpointPair = (EndpointPair) obj;
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
            public Set<N> adjacentNodes(N node) {
                return AbstractNetwork.this.adjacentNodes(node);
            }

            @Override // com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction, com.google.common.graph.Graph
            public Set<N> predecessors(N node) {
                return AbstractNetwork.this.predecessors((Object) node);
            }

            @Override // com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
            public Set<N> successors(N node) {
                return AbstractNetwork.this.successors((Object) node);
            }
        };
    }

    @Override // com.google.common.graph.Network
    public int degree(N node) {
        if (isDirected()) {
            return IntMath.saturatedAdd(inEdges(node).size(), outEdges(node).size());
        }
        return IntMath.saturatedAdd(incidentEdges(node).size(), edgesConnecting(node, node).size());
    }

    @Override // com.google.common.graph.Network
    public int inDegree(N node) {
        return isDirected() ? inEdges(node).size() : degree(node);
    }

    @Override // com.google.common.graph.Network
    public int outDegree(N node) {
        return isDirected() ? outEdges(node).size() : degree(node);
    }

    @Override // com.google.common.graph.Network
    public Set<E> adjacentEdges(E edge) {
        EndpointPair<N> endpointPair = incidentNodes(edge);
        return Sets.difference(Sets.union(incidentEdges(endpointPair.nodeU()), incidentEdges(endpointPair.nodeV())), ImmutableSet.of(edge));
    }

    @Override // com.google.common.graph.Network
    public Set<E> edgesConnecting(N nodeU, N nodeV) {
        Set<E> outEdgesU = outEdges(nodeU);
        Set<E> inEdgesV = inEdges(nodeV);
        if (outEdgesU.size() <= inEdgesV.size()) {
            return Collections.unmodifiableSet(Sets.filter(outEdgesU, connectedPredicate(nodeU, nodeV)));
        }
        return Collections.unmodifiableSet(Sets.filter(inEdgesV, connectedPredicate(nodeV, nodeU)));
    }

    private Predicate<E> connectedPredicate(final N nodePresent, final N nodeToCheck) {
        return new Predicate<E>() {
            /* class com.google.common.graph.AbstractNetwork.AnonymousClass2 */

            @Override // com.google.common.base.Predicate
            public boolean apply(E edge) {
                return AbstractNetwork.this.incidentNodes(edge).adjacentNode(nodePresent).equals(nodeToCheck);
            }
        };
    }

    @Override // com.google.common.graph.Network
    @NullableDecl
    public E edgeConnectingOrNull(N nodeU, N nodeV) {
        Set<E> edgesConnecting = edgesConnecting(nodeU, nodeV);
        switch (edgesConnecting.size()) {
            case 0:
                return null;
            case 1:
                return edgesConnecting.iterator().next();
            default:
                throw new IllegalArgumentException(String.format("Cannot call edgeConnecting() when parallel edges exist between %s and %s. Consider calling edgesConnecting() instead.", nodeU, nodeV));
        }
    }

    @Override // com.google.common.graph.Network
    public boolean hasEdgeConnecting(N nodeU, N nodeV) {
        return !edgesConnecting(nodeU, nodeV).isEmpty();
    }

    @Override // com.google.common.graph.Network
    public final boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Network)) {
            return false;
        }
        Network<?, ?> other = (Network) obj;
        return isDirected() == other.isDirected() && nodes().equals(other.nodes()) && edgeIncidentNodesMap(this).equals(edgeIncidentNodesMap(other));
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
            public EndpointPair<N> apply(E edge) {
                return Network.this.incidentNodes(edge);
            }
        });
    }
}
