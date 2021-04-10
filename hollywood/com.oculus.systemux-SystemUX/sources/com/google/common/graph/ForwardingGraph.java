package com.google.common.graph;

import java.util.Set;

/* access modifiers changed from: package-private */
public abstract class ForwardingGraph<N> extends AbstractGraph<N> {
    /* access modifiers changed from: protected */
    public abstract BaseGraph<N> delegate();

    ForwardingGraph() {
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public Set<N> nodes() {
        return delegate().nodes();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.graph.AbstractBaseGraph
    public long edgeCount() {
        return (long) delegate().edges().size();
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public boolean isDirected() {
        return delegate().isDirected();
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public boolean allowsSelfLoops() {
        return delegate().allowsSelfLoops();
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public ElementOrder<N> nodeOrder() {
        return delegate().nodeOrder();
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public Set<N> adjacentNodes(N n) {
        return delegate().adjacentNodes(n);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction, com.google.common.graph.Graph, com.google.common.graph.Graph
    public Set<N> predecessors(N n) {
        return delegate().predecessors((Object) n);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph, com.google.common.graph.Graph
    public Set<N> successors(N n) {
        return delegate().successors((Object) n);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.Graph
    public int degree(N n) {
        return delegate().degree(n);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.Graph
    public int inDegree(N n) {
        return delegate().inDegree(n);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.Graph
    public int outDegree(N n) {
        return delegate().outDegree(n);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.Graph
    public boolean hasEdgeConnecting(N n, N n2) {
        return delegate().hasEdgeConnecting(n, n2);
    }
}
