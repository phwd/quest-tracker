package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.math.IntMath;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
public abstract class AbstractDirectedNetworkConnections<N, E> implements NetworkConnections<N, E> {
    protected final Map<E, N> inEdgeMap;
    protected final Map<E, N> outEdgeMap;
    private int selfLoopCount;

    protected AbstractDirectedNetworkConnections(Map<E, N> inEdgeMap2, Map<E, N> outEdgeMap2, int selfLoopCount2) {
        this.inEdgeMap = (Map) Preconditions.checkNotNull(inEdgeMap2);
        this.outEdgeMap = (Map) Preconditions.checkNotNull(outEdgeMap2);
        this.selfLoopCount = Graphs.checkNonNegative(selfLoopCount2);
        Preconditions.checkState(selfLoopCount2 <= inEdgeMap2.size() && selfLoopCount2 <= outEdgeMap2.size());
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<N> adjacentNodes() {
        return Sets.union(predecessors(), successors());
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> incidentEdges() {
        return new AbstractSet<E>() {
            /* class com.google.common.graph.AbstractDirectedNetworkConnections.AnonymousClass1 */

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public UnmodifiableIterator<E> iterator() {
                Iterable<E> incidentEdges;
                if (AbstractDirectedNetworkConnections.this.selfLoopCount == 0) {
                    incidentEdges = Iterables.concat(AbstractDirectedNetworkConnections.this.inEdgeMap.keySet(), AbstractDirectedNetworkConnections.this.outEdgeMap.keySet());
                } else {
                    incidentEdges = Sets.union(AbstractDirectedNetworkConnections.this.inEdgeMap.keySet(), AbstractDirectedNetworkConnections.this.outEdgeMap.keySet());
                }
                return Iterators.unmodifiableIterator(incidentEdges.iterator());
            }

            public int size() {
                return IntMath.saturatedAdd(AbstractDirectedNetworkConnections.this.inEdgeMap.size(), AbstractDirectedNetworkConnections.this.outEdgeMap.size() - AbstractDirectedNetworkConnections.this.selfLoopCount);
            }

            public boolean contains(@NullableDecl Object obj) {
                return AbstractDirectedNetworkConnections.this.inEdgeMap.containsKey(obj) || AbstractDirectedNetworkConnections.this.outEdgeMap.containsKey(obj);
            }
        };
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> inEdges() {
        return Collections.unmodifiableSet(this.inEdgeMap.keySet());
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> outEdges() {
        return Collections.unmodifiableSet(this.outEdgeMap.keySet());
    }

    @Override // com.google.common.graph.NetworkConnections
    public N adjacentNode(E edge) {
        return (N) Preconditions.checkNotNull(this.outEdgeMap.get(edge));
    }

    @Override // com.google.common.graph.NetworkConnections
    public N removeInEdge(E edge, boolean isSelfLoop) {
        if (isSelfLoop) {
            int i = this.selfLoopCount - 1;
            this.selfLoopCount = i;
            Graphs.checkNonNegative(i);
        }
        return (N) Preconditions.checkNotNull(this.inEdgeMap.remove(edge));
    }

    @Override // com.google.common.graph.NetworkConnections
    public N removeOutEdge(E edge) {
        return (N) Preconditions.checkNotNull(this.outEdgeMap.remove(edge));
    }

    @Override // com.google.common.graph.NetworkConnections
    public void addInEdge(E edge, N node, boolean isSelfLoop) {
        if (isSelfLoop) {
            int i = this.selfLoopCount + 1;
            this.selfLoopCount = i;
            Graphs.checkPositive(i);
        }
        Preconditions.checkState(this.inEdgeMap.put(edge, node) == null);
    }

    @Override // com.google.common.graph.NetworkConnections
    public void addOutEdge(E edge, N node) {
        Preconditions.checkState(this.outEdgeMap.put(edge, node) == null);
    }
}
