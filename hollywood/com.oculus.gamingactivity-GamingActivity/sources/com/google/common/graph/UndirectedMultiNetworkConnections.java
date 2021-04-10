package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class UndirectedMultiNetworkConnections<N, E> extends AbstractUndirectedNetworkConnections<N, E> {
    @LazyInit
    private transient Reference<Multiset<N>> adjacentNodesReference;

    private UndirectedMultiNetworkConnections(Map<E, N> incidentEdges) {
        super(incidentEdges);
    }

    static <N, E> UndirectedMultiNetworkConnections<N, E> of() {
        return new UndirectedMultiNetworkConnections<>(new HashMap(2, 1.0f));
    }

    static <N, E> UndirectedMultiNetworkConnections<N, E> ofImmutable(Map<E, N> incidentEdges) {
        return new UndirectedMultiNetworkConnections<>(ImmutableMap.copyOf(incidentEdges));
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<N> adjacentNodes() {
        return Collections.unmodifiableSet(adjacentNodesMultiset().elementSet());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Multiset<N> adjacentNodesMultiset() {
        Multiset<N> adjacentNodes = (Multiset) getReference(this.adjacentNodesReference);
        if (adjacentNodes != null) {
            return adjacentNodes;
        }
        Multiset<N> adjacentNodes2 = HashMultiset.create(this.incidentEdgeMap.values());
        this.adjacentNodesReference = new SoftReference(adjacentNodes2);
        return adjacentNodes2;
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> edgesConnecting(final N node) {
        return new MultiEdgesConnecting<E>(this.incidentEdgeMap, node) {
            /* class com.google.common.graph.UndirectedMultiNetworkConnections.AnonymousClass1 */

            public int size() {
                return UndirectedMultiNetworkConnections.this.adjacentNodesMultiset().count(node);
            }
        };
    }

    @Override // com.google.common.graph.NetworkConnections, com.google.common.graph.AbstractUndirectedNetworkConnections
    public N removeInEdge(E edge, boolean isSelfLoop) {
        if (!isSelfLoop) {
            return removeOutEdge(edge);
        }
        return null;
    }

    @Override // com.google.common.graph.NetworkConnections, com.google.common.graph.AbstractUndirectedNetworkConnections
    public N removeOutEdge(E edge) {
        N node = (N) super.removeOutEdge(edge);
        Multiset<N> adjacentNodes = (Multiset) getReference(this.adjacentNodesReference);
        if (adjacentNodes != null) {
            Preconditions.checkState(adjacentNodes.remove(node));
        }
        return node;
    }

    @Override // com.google.common.graph.NetworkConnections, com.google.common.graph.AbstractUndirectedNetworkConnections
    public void addInEdge(E edge, N node, boolean isSelfLoop) {
        if (!isSelfLoop) {
            addOutEdge(edge, node);
        }
    }

    @Override // com.google.common.graph.NetworkConnections, com.google.common.graph.AbstractUndirectedNetworkConnections
    public void addOutEdge(E edge, N node) {
        super.addOutEdge(edge, node);
        Multiset<N> adjacentNodes = (Multiset) getReference(this.adjacentNodesReference);
        if (adjacentNodes != null) {
            Preconditions.checkState(adjacentNodes.add(node));
        }
    }

    @NullableDecl
    private static <T> T getReference(@NullableDecl Reference<T> reference) {
        if (reference == null) {
            return null;
        }
        return reference.get();
    }
}
