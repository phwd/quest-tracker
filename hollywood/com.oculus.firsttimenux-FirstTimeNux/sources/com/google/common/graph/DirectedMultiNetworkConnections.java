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

final class DirectedMultiNetworkConnections<N, E> extends AbstractDirectedNetworkConnections<N, E> {
    @LazyInit
    private transient Reference<Multiset<N>> predecessorsReference;
    @LazyInit
    private transient Reference<Multiset<N>> successorsReference;

    private DirectedMultiNetworkConnections(Map<E, N> inEdges, Map<E, N> outEdges, int selfLoopCount) {
        super(inEdges, outEdges, selfLoopCount);
    }

    static <N, E> DirectedMultiNetworkConnections<N, E> of() {
        return new DirectedMultiNetworkConnections<>(new HashMap(2, 1.0f), new HashMap(2, 1.0f), 0);
    }

    static <N, E> DirectedMultiNetworkConnections<N, E> ofImmutable(Map<E, N> inEdges, Map<E, N> outEdges, int selfLoopCount) {
        return new DirectedMultiNetworkConnections<>(ImmutableMap.copyOf(inEdges), ImmutableMap.copyOf(outEdges), selfLoopCount);
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<N> predecessors() {
        return Collections.unmodifiableSet(predecessorsMultiset().elementSet());
    }

    private Multiset<N> predecessorsMultiset() {
        Multiset<N> predecessors = (Multiset) getReference(this.predecessorsReference);
        if (predecessors != null) {
            return predecessors;
        }
        Multiset<N> predecessors2 = HashMultiset.create(this.inEdgeMap.values());
        this.predecessorsReference = new SoftReference(predecessors2);
        return predecessors2;
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<N> successors() {
        return Collections.unmodifiableSet(successorsMultiset().elementSet());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Multiset<N> successorsMultiset() {
        Multiset<N> successors = (Multiset) getReference(this.successorsReference);
        if (successors != null) {
            return successors;
        }
        Multiset<N> successors2 = HashMultiset.create(this.outEdgeMap.values());
        this.successorsReference = new SoftReference(successors2);
        return successors2;
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> edgesConnecting(final N node) {
        return new MultiEdgesConnecting<E>(this.outEdgeMap, node) {
            /* class com.google.common.graph.DirectedMultiNetworkConnections.AnonymousClass1 */

            public int size() {
                return DirectedMultiNetworkConnections.this.successorsMultiset().count(node);
            }
        };
    }

    @Override // com.google.common.graph.NetworkConnections, com.google.common.graph.AbstractDirectedNetworkConnections
    public N removeInEdge(E edge, boolean isSelfLoop) {
        N node = (N) super.removeInEdge(edge, isSelfLoop);
        Multiset<N> predecessors = (Multiset) getReference(this.predecessorsReference);
        if (predecessors != null) {
            Preconditions.checkState(predecessors.remove(node));
        }
        return node;
    }

    @Override // com.google.common.graph.NetworkConnections, com.google.common.graph.AbstractDirectedNetworkConnections
    public N removeOutEdge(E edge) {
        N node = (N) super.removeOutEdge(edge);
        Multiset<N> successors = (Multiset) getReference(this.successorsReference);
        if (successors != null) {
            Preconditions.checkState(successors.remove(node));
        }
        return node;
    }

    @Override // com.google.common.graph.NetworkConnections, com.google.common.graph.AbstractDirectedNetworkConnections
    public void addInEdge(E edge, N node, boolean isSelfLoop) {
        super.addInEdge(edge, node, isSelfLoop);
        Multiset<N> predecessors = (Multiset) getReference(this.predecessorsReference);
        if (predecessors != null) {
            Preconditions.checkState(predecessors.add(node));
        }
    }

    @Override // com.google.common.graph.NetworkConnections, com.google.common.graph.AbstractDirectedNetworkConnections
    public void addOutEdge(E edge, N node) {
        super.addOutEdge(edge, node);
        Multiset<N> successors = (Multiset) getReference(this.successorsReference);
        if (successors != null) {
            Preconditions.checkState(successors.add(node));
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
