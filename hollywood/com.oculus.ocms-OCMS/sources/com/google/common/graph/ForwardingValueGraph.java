package com.google.common.graph;

import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
public abstract class ForwardingValueGraph<N, V> extends AbstractValueGraph<N, V> {
    /* access modifiers changed from: protected */
    public abstract ValueGraph<N, V> delegate();

    ForwardingValueGraph() {
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.ValueGraph
    public Set<N> nodes() {
        return delegate().nodes();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.graph.AbstractBaseGraph
    public long edgeCount() {
        return (long) delegate().edges().size();
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.ValueGraph
    public boolean isDirected() {
        return delegate().isDirected();
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.ValueGraph
    public boolean allowsSelfLoops() {
        return delegate().allowsSelfLoops();
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.ValueGraph
    public ElementOrder<N> nodeOrder() {
        return delegate().nodeOrder();
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.ValueGraph
    public Set<N> adjacentNodes(N n) {
        return delegate().adjacentNodes(n);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction, com.google.common.graph.ValueGraph
    public Set<N> predecessors(N n) {
        return delegate().predecessors((Object) n);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.ValueGraph, com.google.common.graph.SuccessorsFunction
    public Set<N> successors(N n) {
        return delegate().successors((Object) n);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.ValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.AbstractValueGraph
    public int degree(N n) {
        return delegate().degree(n);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.ValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.AbstractValueGraph
    public int inDegree(N n) {
        return delegate().inDegree(n);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.ValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.AbstractValueGraph
    public int outDegree(N n) {
        return delegate().outDegree(n);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.ValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.AbstractValueGraph
    public boolean hasEdgeConnecting(N n, N n2) {
        return delegate().hasEdgeConnecting(n, n2);
    }

    @Override // com.google.common.graph.ValueGraph
    @NullableDecl
    public V edgeValueOrDefault(N n, N n2, @NullableDecl V v) {
        return delegate().edgeValueOrDefault(n, n2, v);
    }
}
