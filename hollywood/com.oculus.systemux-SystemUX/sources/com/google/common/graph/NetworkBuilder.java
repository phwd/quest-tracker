package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

@Beta
public final class NetworkBuilder<N, E> extends AbstractGraphBuilder<N> {
    boolean allowsParallelEdges = false;
    ElementOrder<? super E> edgeOrder = ElementOrder.insertion();
    Optional<Integer> expectedEdgeCount = Optional.absent();

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.graph.NetworkBuilder<N, E> */
    /* JADX WARN: Multi-variable type inference failed */
    private <N1 extends N, E1 extends E> NetworkBuilder<N1, E1> cast() {
        return this;
    }

    private NetworkBuilder(boolean z) {
        super(z);
    }

    public static NetworkBuilder<Object, Object> directed() {
        return new NetworkBuilder<>(true);
    }

    public static NetworkBuilder<Object, Object> undirected() {
        return new NetworkBuilder<>(false);
    }

    /* JADX DEBUG: Type inference failed for r2v2. Raw type applied. Possible types: com.google.common.graph.NetworkBuilder<N1 extends N, E1 extends E>, com.google.common.graph.NetworkBuilder<N, E> */
    public static <N, E> NetworkBuilder<N, E> from(Network<N, E> network) {
        return (NetworkBuilder<N1, E1>) new NetworkBuilder(network.isDirected()).allowsParallelEdges(network.allowsParallelEdges()).allowsSelfLoops(network.allowsSelfLoops()).nodeOrder(network.nodeOrder()).edgeOrder(network.edgeOrder());
    }

    public NetworkBuilder<N, E> allowsParallelEdges(boolean z) {
        this.allowsParallelEdges = z;
        return this;
    }

    public NetworkBuilder<N, E> allowsSelfLoops(boolean z) {
        this.allowsSelfLoops = z;
        return this;
    }

    public NetworkBuilder<N, E> expectedNodeCount(int i) {
        this.expectedNodeCount = Optional.of(Integer.valueOf(Graphs.checkNonNegative(i)));
        return this;
    }

    public NetworkBuilder<N, E> expectedEdgeCount(int i) {
        this.expectedEdgeCount = Optional.of(Integer.valueOf(Graphs.checkNonNegative(i)));
        return this;
    }

    public <N1 extends N> NetworkBuilder<N1, E> nodeOrder(ElementOrder<N1> elementOrder) {
        NetworkBuilder networkBuilder = (NetworkBuilder<N1, E1>) cast();
        networkBuilder.nodeOrder = (ElementOrder) Preconditions.checkNotNull(elementOrder);
        return networkBuilder;
    }

    public <E1 extends E> NetworkBuilder<N, E1> edgeOrder(ElementOrder<E1> elementOrder) {
        NetworkBuilder networkBuilder = (NetworkBuilder<N1, E1>) cast();
        networkBuilder.edgeOrder = (ElementOrder) Preconditions.checkNotNull(elementOrder);
        return networkBuilder;
    }

    public <N1 extends N, E1 extends E> MutableNetwork<N1, E1> build() {
        return new ConfigurableMutableNetwork(this);
    }
}
