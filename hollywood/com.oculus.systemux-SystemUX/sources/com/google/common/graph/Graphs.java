package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Beta
public final class Graphs {

    /* access modifiers changed from: private */
    public enum NodeVisitState {
        PENDING,
        COMPLETE
    }

    private Graphs() {
    }

    public static <N> boolean hasCycle(Graph<N> graph) {
        int size = graph.edges().size();
        if (size == 0) {
            return false;
        }
        if (!graph.isDirected() && size >= graph.nodes().size()) {
            return true;
        }
        HashMap newHashMapWithExpectedSize = Maps.newHashMapWithExpectedSize(graph.nodes().size());
        for (N n : graph.nodes()) {
            if (subgraphHasCycle(graph, newHashMapWithExpectedSize, n, null)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasCycle(Network<?, ?> network) {
        if (network.isDirected() || !network.allowsParallelEdges() || network.edges().size() <= network.asGraph().edges().size()) {
            return hasCycle(network.asGraph());
        }
        return true;
    }

    private static <N> boolean subgraphHasCycle(Graph<N> graph, Map<Object, NodeVisitState> map, N n, @NullableDecl N n2) {
        NodeVisitState nodeVisitState = map.get(n);
        if (nodeVisitState == NodeVisitState.COMPLETE) {
            return false;
        }
        if (nodeVisitState == NodeVisitState.PENDING) {
            return true;
        }
        map.put(n, NodeVisitState.PENDING);
        for (N n3 : graph.successors((Object) n)) {
            if (canTraverseWithoutReusingEdge(graph, n3, n2) && subgraphHasCycle(graph, map, n3, n)) {
                return true;
            }
        }
        map.put(n, NodeVisitState.COMPLETE);
        return false;
    }

    private static boolean canTraverseWithoutReusingEdge(Graph<?> graph, Object obj, @NullableDecl Object obj2) {
        return graph.isDirected() || !Objects.equal(obj2, obj);
    }

    public static <N> Graph<N> transitiveClosure(Graph<N> graph) {
        MutableGraph<N1> build = GraphBuilder.from(graph).allowsSelfLoops(true).build();
        if (graph.isDirected()) {
            for (N n : graph.nodes()) {
                for (N1 n1 : reachableNodes(graph, n)) {
                    build.putEdge(n, n1);
                }
            }
        } else {
            HashSet hashSet = new HashSet();
            for (N n2 : graph.nodes()) {
                if (!hashSet.contains(n2)) {
                    Set<N1> reachableNodes = reachableNodes(graph, n2);
                    hashSet.addAll(reachableNodes);
                    int i = 1;
                    for (N1 n12 : reachableNodes) {
                        int i2 = i + 1;
                        for (N1 n13 : Iterables.limit(reachableNodes, i)) {
                            build.putEdge(n12, n13);
                        }
                        i = i2;
                    }
                }
            }
        }
        return build;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.google.common.graph.Graph<N> */
    /* JADX WARN: Multi-variable type inference failed */
    public static <N> Set<N> reachableNodes(Graph<N> graph, N n) {
        Preconditions.checkArgument(graph.nodes().contains(n), "Node %s is not an element of this graph.", n);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArrayDeque arrayDeque = new ArrayDeque();
        linkedHashSet.add(n);
        arrayDeque.add(n);
        while (!arrayDeque.isEmpty()) {
            for (Object obj : graph.successors(arrayDeque.remove())) {
                if (linkedHashSet.add(obj)) {
                    arrayDeque.add(obj);
                }
            }
        }
        return Collections.unmodifiableSet(linkedHashSet);
    }

    public static <N> Graph<N> transpose(Graph<N> graph) {
        if (!graph.isDirected()) {
            return graph;
        }
        if (graph instanceof TransposedGraph) {
            return ((TransposedGraph) graph).graph;
        }
        return new TransposedGraph(graph);
    }

    public static <N, V> ValueGraph<N, V> transpose(ValueGraph<N, V> valueGraph) {
        if (!valueGraph.isDirected()) {
            return valueGraph;
        }
        if (valueGraph instanceof TransposedValueGraph) {
            return ((TransposedValueGraph) valueGraph).graph;
        }
        return new TransposedValueGraph(valueGraph);
    }

    public static <N, E> Network<N, E> transpose(Network<N, E> network) {
        if (!network.isDirected()) {
            return network;
        }
        if (network instanceof TransposedNetwork) {
            return ((TransposedNetwork) network).network;
        }
        return new TransposedNetwork(network);
    }

    private static class TransposedGraph<N> extends ForwardingGraph<N> {
        private final Graph<N> graph;

        TransposedGraph(Graph<N> graph2) {
            this.graph = graph2;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.graph.ForwardingGraph
        public Graph<N> delegate() {
            return this.graph;
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction, com.google.common.graph.ForwardingGraph, com.google.common.graph.ForwardingGraph, com.google.common.graph.Graph, com.google.common.graph.Graph
        public Set<N> predecessors(N n) {
            return delegate().successors((Object) n);
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction, com.google.common.graph.ForwardingGraph, com.google.common.graph.ForwardingGraph, com.google.common.graph.Graph, com.google.common.graph.Graph
        public Set<N> successors(N n) {
            return delegate().predecessors((Object) n);
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.ForwardingGraph, com.google.common.graph.Graph
        public int inDegree(N n) {
            return delegate().outDegree(n);
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.ForwardingGraph, com.google.common.graph.Graph
        public int outDegree(N n) {
            return delegate().inDegree(n);
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.ForwardingGraph, com.google.common.graph.Graph
        public boolean hasEdgeConnecting(N n, N n2) {
            return delegate().hasEdgeConnecting(n2, n);
        }
    }

    private static class TransposedValueGraph<N, V> extends ForwardingValueGraph<N, V> {
        private final ValueGraph<N, V> graph;

        TransposedValueGraph(ValueGraph<N, V> valueGraph) {
            this.graph = valueGraph;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.graph.ForwardingValueGraph
        public ValueGraph<N, V> delegate() {
            return this.graph;
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction, com.google.common.graph.ValueGraph, com.google.common.graph.ValueGraph, com.google.common.graph.ForwardingValueGraph, com.google.common.graph.ForwardingValueGraph
        public Set<N> predecessors(N n) {
            return delegate().successors((Object) n);
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.BaseGraph, com.google.common.graph.ValueGraph, com.google.common.graph.ValueGraph, com.google.common.graph.SuccessorsFunction, com.google.common.graph.ForwardingValueGraph, com.google.common.graph.ForwardingValueGraph
        public Set<N> successors(N n) {
            return delegate().predecessors((Object) n);
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.ValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.ForwardingValueGraph, com.google.common.graph.AbstractValueGraph
        public int inDegree(N n) {
            return delegate().outDegree(n);
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.ValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.ForwardingValueGraph, com.google.common.graph.AbstractValueGraph
        public int outDegree(N n) {
            return delegate().inDegree(n);
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.ValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.ForwardingValueGraph, com.google.common.graph.AbstractValueGraph
        public boolean hasEdgeConnecting(N n, N n2) {
            return delegate().hasEdgeConnecting(n2, n);
        }

        @Override // com.google.common.graph.ValueGraph, com.google.common.graph.ForwardingValueGraph, com.google.common.graph.AbstractValueGraph
        public Optional<V> edgeValue(N n, N n2) {
            return delegate().edgeValue(n2, n);
        }

        @Override // com.google.common.graph.ValueGraph, com.google.common.graph.ForwardingValueGraph
        @NullableDecl
        public V edgeValueOrDefault(N n, N n2, @NullableDecl V v) {
            return delegate().edgeValueOrDefault(n2, n, v);
        }
    }

    private static class TransposedNetwork<N, E> extends ForwardingNetwork<N, E> {
        private final Network<N, E> network;

        TransposedNetwork(Network<N, E> network2) {
            this.network = network2;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.graph.ForwardingNetwork
        public Network<N, E> delegate() {
            return this.network;
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.ForwardingNetwork, com.google.common.graph.PredecessorsFunction, com.google.common.graph.Network, com.google.common.graph.Network
        public Set<N> predecessors(N n) {
            return delegate().successors((Object) n);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.ForwardingNetwork, com.google.common.graph.SuccessorsFunction, com.google.common.graph.Network, com.google.common.graph.Network
        public Set<N> successors(N n) {
            return delegate().predecessors((Object) n);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public int inDegree(N n) {
            return delegate().outDegree(n);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public int outDegree(N n) {
            return delegate().inDegree(n);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.Network
        public Set<E> inEdges(N n) {
            return delegate().outEdges(n);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.Network
        public Set<E> outEdges(N n) {
            return delegate().inEdges(n);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.Network
        public EndpointPair<N> incidentNodes(E e) {
            EndpointPair<N> incidentNodes = delegate().incidentNodes(e);
            return EndpointPair.of((Network<?, ?>) this.network, (Object) incidentNodes.nodeV(), (Object) incidentNodes.nodeU());
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public Set<E> edgesConnecting(N n, N n2) {
            return delegate().edgesConnecting(n2, n);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public Optional<E> edgeConnecting(N n, N n2) {
            return delegate().edgeConnecting(n2, n);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public E edgeConnectingOrNull(N n, N n2) {
            return delegate().edgeConnectingOrNull(n2, n);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public boolean hasEdgeConnecting(N n, N n2) {
            return delegate().hasEdgeConnecting(n2, n);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v11, resolved type: com.google.common.graph.MutableGraph */
    /* JADX WARN: Multi-variable type inference failed */
    public static <N> MutableGraph<N> inducedSubgraph(Graph<N> graph, Iterable<? extends N> iterable) {
        MutableGraph mutableGraph = iterable instanceof Collection ? (MutableGraph<N1>) GraphBuilder.from(graph).expectedNodeCount(((Collection) iterable).size()).build() : (MutableGraph<N>) GraphBuilder.from(graph).build();
        Iterator<? extends N> it = iterable.iterator();
        while (it.hasNext()) {
            mutableGraph.addNode(it.next());
        }
        for (N n : mutableGraph.nodes()) {
            for (N n2 : graph.successors((Object) n)) {
                if (mutableGraph.nodes().contains(n2)) {
                    mutableGraph.putEdge(n, n2);
                }
            }
        }
        return (MutableGraph<N>) mutableGraph;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v11, resolved type: com.google.common.graph.MutableValueGraph */
    /* JADX WARN: Multi-variable type inference failed */
    public static <N, V> MutableValueGraph<N, V> inducedSubgraph(ValueGraph<N, V> valueGraph, Iterable<? extends N> iterable) {
        MutableValueGraph mutableValueGraph = iterable instanceof Collection ? (MutableValueGraph<N1, V1>) ValueGraphBuilder.from(valueGraph).expectedNodeCount(((Collection) iterable).size()).build() : (MutableValueGraph<N, V>) ValueGraphBuilder.from(valueGraph).build();
        Iterator<? extends N> it = iterable.iterator();
        while (it.hasNext()) {
            mutableValueGraph.addNode(it.next());
        }
        for (N n : mutableValueGraph.nodes()) {
            for (N n2 : valueGraph.successors((Object) n)) {
                if (mutableValueGraph.nodes().contains(n2)) {
                    mutableValueGraph.putEdgeValue(n, n2, valueGraph.edgeValueOrDefault(n, n2, null));
                }
            }
        }
        return (MutableValueGraph<N, V>) mutableValueGraph;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v11, resolved type: com.google.common.graph.MutableNetwork */
    /* JADX WARN: Multi-variable type inference failed */
    public static <N, E> MutableNetwork<N, E> inducedSubgraph(Network<N, E> network, Iterable<? extends N> iterable) {
        MutableNetwork mutableNetwork = iterable instanceof Collection ? (MutableNetwork<N1, E1>) NetworkBuilder.from(network).expectedNodeCount(((Collection) iterable).size()).build() : (MutableNetwork<N, E>) NetworkBuilder.from(network).build();
        Iterator<? extends N> it = iterable.iterator();
        while (it.hasNext()) {
            mutableNetwork.addNode(it.next());
        }
        for (E e : mutableNetwork.nodes()) {
            for (E e2 : network.outEdges(e)) {
                N adjacentNode = network.incidentNodes(e2).adjacentNode(e);
                if (mutableNetwork.nodes().contains(adjacentNode)) {
                    mutableNetwork.addEdge(e, adjacentNode, e2);
                }
            }
        }
        return (MutableNetwork<N, E>) mutableNetwork;
    }

    public static <N> MutableGraph<N> copyOf(Graph<N> graph) {
        MutableGraph mutableGraph = (MutableGraph<N1>) GraphBuilder.from(graph).expectedNodeCount(graph.nodes().size()).build();
        for (N n : graph.nodes()) {
            mutableGraph.addNode(n);
        }
        for (EndpointPair<N> endpointPair : graph.edges()) {
            mutableGraph.putEdge(endpointPair.nodeU(), endpointPair.nodeV());
        }
        return mutableGraph;
    }

    public static <N, V> MutableValueGraph<N, V> copyOf(ValueGraph<N, V> valueGraph) {
        MutableValueGraph mutableValueGraph = (MutableValueGraph<N1, V1>) ValueGraphBuilder.from(valueGraph).expectedNodeCount(valueGraph.nodes().size()).build();
        for (N n : valueGraph.nodes()) {
            mutableValueGraph.addNode(n);
        }
        for (EndpointPair<N> endpointPair : valueGraph.edges()) {
            mutableValueGraph.putEdgeValue(endpointPair.nodeU(), endpointPair.nodeV(), valueGraph.edgeValueOrDefault(endpointPair.nodeU(), endpointPair.nodeV(), null));
        }
        return mutableValueGraph;
    }

    public static <N, E> MutableNetwork<N, E> copyOf(Network<N, E> network) {
        MutableNetwork mutableNetwork = (MutableNetwork<N1, E1>) NetworkBuilder.from(network).expectedNodeCount(network.nodes().size()).expectedEdgeCount(network.edges().size()).build();
        for (N n : network.nodes()) {
            mutableNetwork.addNode(n);
        }
        for (E e : network.edges()) {
            EndpointPair<N> incidentNodes = network.incidentNodes(e);
            mutableNetwork.addEdge(incidentNodes.nodeU(), incidentNodes.nodeV(), e);
        }
        return mutableNetwork;
    }

    @CanIgnoreReturnValue
    static int checkNonNegative(int i) {
        Preconditions.checkArgument(i >= 0, "Not true that %s is non-negative.", i);
        return i;
    }

    @CanIgnoreReturnValue
    static long checkNonNegative(long j) {
        Preconditions.checkArgument(j >= 0, "Not true that %s is non-negative.", j);
        return j;
    }

    @CanIgnoreReturnValue
    static int checkPositive(int i) {
        Preconditions.checkArgument(i > 0, "Not true that %s is positive.", i);
        return i;
    }

    @CanIgnoreReturnValue
    static long checkPositive(long j) {
        Preconditions.checkArgument(j > 0, "Not true that %s is positive.", j);
        return j;
    }
}
