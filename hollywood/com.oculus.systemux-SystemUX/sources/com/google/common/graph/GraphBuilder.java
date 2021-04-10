package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

@Beta
public final class GraphBuilder<N> extends AbstractGraphBuilder<N> {
    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.graph.GraphBuilder<N> */
    /* JADX WARN: Multi-variable type inference failed */
    private <N1 extends N> GraphBuilder<N1> cast() {
        return this;
    }

    private GraphBuilder(boolean z) {
        super(z);
    }

    public static GraphBuilder<Object> directed() {
        return new GraphBuilder<>(true);
    }

    public static GraphBuilder<Object> undirected() {
        return new GraphBuilder<>(false);
    }

    /* JADX DEBUG: Type inference failed for r2v2. Raw type applied. Possible types: com.google.common.graph.GraphBuilder<N1 extends N>, com.google.common.graph.GraphBuilder<N> */
    public static <N> GraphBuilder<N> from(Graph<N> graph) {
        return (GraphBuilder<N1>) new GraphBuilder(graph.isDirected()).allowsSelfLoops(graph.allowsSelfLoops()).nodeOrder(graph.nodeOrder());
    }

    public GraphBuilder<N> allowsSelfLoops(boolean z) {
        this.allowsSelfLoops = z;
        return this;
    }

    public GraphBuilder<N> expectedNodeCount(int i) {
        this.expectedNodeCount = Optional.of(Integer.valueOf(Graphs.checkNonNegative(i)));
        return this;
    }

    public <N1 extends N> GraphBuilder<N1> nodeOrder(ElementOrder<N1> elementOrder) {
        GraphBuilder<N1> cast = cast();
        cast.nodeOrder = (ElementOrder) Preconditions.checkNotNull(elementOrder);
        return cast;
    }

    public <N1 extends N> MutableGraph<N1> build() {
        return new ConfigurableMutableGraph(this);
    }
}
