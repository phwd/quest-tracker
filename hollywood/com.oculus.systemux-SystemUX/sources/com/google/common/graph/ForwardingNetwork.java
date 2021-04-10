package com.google.common.graph;

import java.util.Optional;
import java.util.Set;

/* access modifiers changed from: package-private */
public abstract class ForwardingNetwork<N, E> extends AbstractNetwork<N, E> {
    /* access modifiers changed from: protected */
    public abstract Network<N, E> delegate();

    ForwardingNetwork() {
    }

    @Override // com.google.common.graph.Network
    public Set<N> nodes() {
        return delegate().nodes();
    }

    @Override // com.google.common.graph.Network
    public Set<E> edges() {
        return delegate().edges();
    }

    @Override // com.google.common.graph.Network
    public boolean isDirected() {
        return delegate().isDirected();
    }

    @Override // com.google.common.graph.Network
    public boolean allowsParallelEdges() {
        return delegate().allowsParallelEdges();
    }

    @Override // com.google.common.graph.Network
    public boolean allowsSelfLoops() {
        return delegate().allowsSelfLoops();
    }

    @Override // com.google.common.graph.Network
    public ElementOrder<N> nodeOrder() {
        return delegate().nodeOrder();
    }

    @Override // com.google.common.graph.Network
    public ElementOrder<E> edgeOrder() {
        return delegate().edgeOrder();
    }

    @Override // com.google.common.graph.Network
    public Set<N> adjacentNodes(N n) {
        return delegate().adjacentNodes(n);
    }

    @Override // com.google.common.graph.PredecessorsFunction, com.google.common.graph.Network, com.google.common.graph.Network
    public Set<N> predecessors(N n) {
        return delegate().predecessors((Object) n);
    }

    @Override // com.google.common.graph.SuccessorsFunction, com.google.common.graph.Network, com.google.common.graph.Network
    public Set<N> successors(N n) {
        return delegate().successors((Object) n);
    }

    @Override // com.google.common.graph.Network
    public Set<E> incidentEdges(N n) {
        return delegate().incidentEdges(n);
    }

    @Override // com.google.common.graph.Network
    public Set<E> inEdges(N n) {
        return delegate().inEdges(n);
    }

    @Override // com.google.common.graph.Network
    public Set<E> outEdges(N n) {
        return delegate().outEdges(n);
    }

    @Override // com.google.common.graph.Network
    public EndpointPair<N> incidentNodes(E e) {
        return delegate().incidentNodes(e);
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public Set<E> adjacentEdges(E e) {
        return delegate().adjacentEdges(e);
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public int degree(N n) {
        return delegate().degree(n);
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public int inDegree(N n) {
        return delegate().inDegree(n);
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public int outDegree(N n) {
        return delegate().outDegree(n);
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public Set<E> edgesConnecting(N n, N n2) {
        return delegate().edgesConnecting(n, n2);
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public Optional<E> edgeConnecting(N n, N n2) {
        return delegate().edgeConnecting(n, n2);
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public E edgeConnectingOrNull(N n, N n2) {
        return delegate().edgeConnectingOrNull(n, n2);
    }

    @Override // com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
    public boolean hasEdgeConnecting(N n, N n2) {
        return delegate().hasEdgeConnecting(n, n2);
    }
}
