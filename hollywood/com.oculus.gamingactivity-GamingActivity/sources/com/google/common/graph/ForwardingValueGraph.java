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
    public Set<N> adjacentNodes(N node) {
        return delegate().adjacentNodes(node);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction, com.google.common.graph.ValueGraph
    public Set<N> predecessors(N node) {
        return delegate().predecessors((Object) node);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.ValueGraph, com.google.common.graph.SuccessorsFunction
    public Set<N> successors(N node) {
        return delegate().successors((Object) node);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.ValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.AbstractValueGraph
    public int degree(N node) {
        return delegate().degree(node);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.ValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.AbstractValueGraph
    public int inDegree(N node) {
        return delegate().inDegree(node);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.ValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.AbstractValueGraph
    public int outDegree(N node) {
        return delegate().outDegree(node);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.ValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.AbstractValueGraph
    public boolean hasEdgeConnecting(N nodeU, N nodeV) {
        return delegate().hasEdgeConnecting(nodeU, nodeV);
    }

    @Override // com.google.common.graph.ValueGraph
    @NullableDecl
    public V edgeValueOrDefault(N nodeU, N nodeV, @NullableDecl V defaultValue) {
        return delegate().edgeValueOrDefault(nodeU, nodeV, defaultValue);
    }
}
