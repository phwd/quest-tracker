package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

@Beta
public final class NetworkBuilder<N, E> extends AbstractGraphBuilder<N> {
    boolean allowsParallelEdges = false;
    ElementOrder<? super E> edgeOrder = ElementOrder.insertion();
    Optional<Integer> expectedEdgeCount = Optional.absent();

    private NetworkBuilder(boolean directed) {
        super(directed);
    }

    public static NetworkBuilder<Object, Object> directed() {
        return new NetworkBuilder<>(true);
    }

    public static NetworkBuilder<Object, Object> undirected() {
        return new NetworkBuilder<>(false);
    }

    /* JADX DEBUG: Type inference failed for r0v4. Raw type applied. Possible types: com.google.common.graph.NetworkBuilder<N1 extends N, E1 extends E>, com.google.common.graph.NetworkBuilder<N, E> */
    public static <N, E> NetworkBuilder<N, E> from(Network<N, E> network) {
        return (NetworkBuilder<N1, E1>) new NetworkBuilder(network.isDirected()).allowsParallelEdges(network.allowsParallelEdges()).allowsSelfLoops(network.allowsSelfLoops()).nodeOrder(network.nodeOrder()).edgeOrder(network.edgeOrder());
    }

    public NetworkBuilder<N, E> allowsParallelEdges(boolean allowsParallelEdges2) {
        this.allowsParallelEdges = allowsParallelEdges2;
        return this;
    }

    public NetworkBuilder<N, E> allowsSelfLoops(boolean allowsSelfLoops) {
        this.allowsSelfLoops = allowsSelfLoops;
        return this;
    }

    public NetworkBuilder<N, E> expectedNodeCount(int expectedNodeCount) {
        this.expectedNodeCount = Optional.of(Integer.valueOf(Graphs.checkNonNegative(expectedNodeCount)));
        return this;
    }

    public NetworkBuilder<N, E> expectedEdgeCount(int expectedEdgeCount2) {
        this.expectedEdgeCount = Optional.of(Integer.valueOf(Graphs.checkNonNegative(expectedEdgeCount2)));
        return this;
    }

    public <N1 extends N> NetworkBuilder<N1, E> nodeOrder(ElementOrder<N1> nodeOrder) {
        NetworkBuilder networkBuilder = (NetworkBuilder<N1, E1>) cast();
        networkBuilder.nodeOrder = (ElementOrder) Preconditions.checkNotNull(nodeOrder);
        return networkBuilder;
    }

    public <E1 extends E> NetworkBuilder<N, E1> edgeOrder(ElementOrder<E1> edgeOrder2) {
        NetworkBuilder networkBuilder = (NetworkBuilder<N1, E1>) cast();
        networkBuilder.edgeOrder = (ElementOrder) Preconditions.checkNotNull(edgeOrder2);
        return networkBuilder;
    }

    public <N1 extends N, E1 extends E> MutableNetwork<N1, E1> build() {
        return new ConfigurableMutableNetwork(this);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.graph.NetworkBuilder<N, E> */
    /* JADX WARN: Multi-variable type inference failed */
    private <N1 extends N, E1 extends E> NetworkBuilder<N1, E1> cast() {
        return this;
    }
}
