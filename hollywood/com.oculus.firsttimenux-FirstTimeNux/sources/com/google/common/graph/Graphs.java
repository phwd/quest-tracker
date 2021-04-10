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
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
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
        int numEdges = graph.edges().size();
        if (numEdges == 0) {
            return false;
        }
        if (!graph.isDirected() && numEdges >= graph.nodes().size()) {
            return true;
        }
        Map<Object, NodeVisitState> visitedNodes = Maps.newHashMapWithExpectedSize(graph.nodes().size());
        for (N node : graph.nodes()) {
            if (subgraphHasCycle(graph, visitedNodes, node, null)) {
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

    private static <N> boolean subgraphHasCycle(Graph<N> graph, Map<Object, NodeVisitState> visitedNodes, N node, @NullableDecl N previousNode) {
        NodeVisitState state = visitedNodes.get(node);
        if (state == NodeVisitState.COMPLETE) {
            return false;
        }
        if (state == NodeVisitState.PENDING) {
            return true;
        }
        visitedNodes.put(node, NodeVisitState.PENDING);
        for (N nextNode : graph.successors((Object) node)) {
            if (canTraverseWithoutReusingEdge(graph, nextNode, previousNode) && subgraphHasCycle(graph, visitedNodes, nextNode, node)) {
                return true;
            }
        }
        visitedNodes.put(node, NodeVisitState.COMPLETE);
        return false;
    }

    private static boolean canTraverseWithoutReusingEdge(Graph<?> graph, Object nextNode, @NullableDecl Object previousNode) {
        if (graph.isDirected() || !Objects.equal(previousNode, nextNode)) {
            return true;
        }
        return false;
    }

    public static <N> Graph<N> transitiveClosure(Graph<N> graph) {
        MutableGraph<N1> build = GraphBuilder.from(graph).allowsSelfLoops(true).build();
        if (graph.isDirected()) {
            for (N node : graph.nodes()) {
                for (N1 n1 : reachableNodes(graph, node)) {
                    build.putEdge(node, n1);
                }
            }
        } else {
            Set<N> visitedNodes = new HashSet<>();
            for (N node2 : graph.nodes()) {
                if (!visitedNodes.contains(node2)) {
                    Set<N> reachableNodes = reachableNodes(graph, node2);
                    visitedNodes.addAll(reachableNodes);
                    int pairwiseMatch = 1;
                    for (N n : reachableNodes) {
                        int pairwiseMatch2 = pairwiseMatch + 1;
                        for (N1 n12 : Iterables.limit(reachableNodes, pairwiseMatch)) {
                            build.putEdge(n, n12);
                        }
                        pairwiseMatch = pairwiseMatch2;
                    }
                }
            }
        }
        return build;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: com.google.common.graph.Graph<N> */
    /* JADX WARN: Multi-variable type inference failed */
    public static <N> Set<N> reachableNodes(Graph<N> graph, N node) {
        Preconditions.checkArgument(graph.nodes().contains(node), "Node %s is not an element of this graph.", node);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArrayDeque arrayDeque = new ArrayDeque();
        linkedHashSet.add(node);
        arrayDeque.add(node);
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
        return new TransposedGraph<>(graph);
    }

    public static <N, V> ValueGraph<N, V> transpose(ValueGraph<N, V> graph) {
        if (!graph.isDirected()) {
            return graph;
        }
        if (graph instanceof TransposedValueGraph) {
            return ((TransposedValueGraph) graph).graph;
        }
        return new TransposedValueGraph<>(graph);
    }

    public static <N, E> Network<N, E> transpose(Network<N, E> network) {
        if (!network.isDirected()) {
            return network;
        }
        if (network instanceof TransposedNetwork) {
            return ((TransposedNetwork) network).network;
        }
        return new TransposedNetwork<>(network);
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

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction, com.google.common.graph.ForwardingGraph, com.google.common.graph.ForwardingGraph, com.google.common.graph.Graph
        public Set<N> predecessors(N node) {
            return delegate().successors((Object) node);
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction, com.google.common.graph.ForwardingGraph, com.google.common.graph.ForwardingGraph, com.google.common.graph.Graph
        public Set<N> successors(N node) {
            return delegate().predecessors((Object) node);
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.ForwardingGraph, com.google.common.graph.Graph
        public int inDegree(N node) {
            return delegate().outDegree(node);
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.ForwardingGraph, com.google.common.graph.Graph
        public int outDegree(N node) {
            return delegate().inDegree(node);
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.ForwardingGraph, com.google.common.graph.Graph
        public boolean hasEdgeConnecting(N nodeU, N nodeV) {
            return delegate().hasEdgeConnecting(nodeV, nodeU);
        }
    }

    private static class TransposedValueGraph<N, V> extends ForwardingValueGraph<N, V> {
        private final ValueGraph<N, V> graph;

        TransposedValueGraph(ValueGraph<N, V> graph2) {
            this.graph = graph2;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.graph.ForwardingValueGraph
        public ValueGraph<N, V> delegate() {
            return this.graph;
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction, com.google.common.graph.ValueGraph, com.google.common.graph.ForwardingValueGraph, com.google.common.graph.ForwardingValueGraph
        public Set<N> predecessors(N node) {
            return delegate().successors((Object) node);
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.ValueGraph, com.google.common.graph.SuccessorsFunction, com.google.common.graph.ForwardingValueGraph, com.google.common.graph.ForwardingValueGraph
        public Set<N> successors(N node) {
            return delegate().predecessors((Object) node);
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.ValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.ForwardingValueGraph, com.google.common.graph.AbstractValueGraph
        public int inDegree(N node) {
            return delegate().outDegree(node);
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.ValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.ForwardingValueGraph, com.google.common.graph.AbstractValueGraph
        public int outDegree(N node) {
            return delegate().inDegree(node);
        }

        @Override // com.google.common.graph.BaseGraph, com.google.common.graph.ValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.ForwardingValueGraph, com.google.common.graph.AbstractValueGraph
        public boolean hasEdgeConnecting(N nodeU, N nodeV) {
            return delegate().hasEdgeConnecting(nodeV, nodeU);
        }

        @Override // com.google.common.graph.ValueGraph, com.google.common.graph.ForwardingValueGraph
        @NullableDecl
        public V edgeValueOrDefault(N nodeU, N nodeV, @NullableDecl V defaultValue) {
            return delegate().edgeValueOrDefault(nodeV, nodeU, defaultValue);
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

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.ForwardingNetwork, com.google.common.graph.PredecessorsFunction, com.google.common.graph.Network
        public Set<N> predecessors(N node) {
            return delegate().successors((Object) node);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.ForwardingNetwork, com.google.common.graph.SuccessorsFunction, com.google.common.graph.Network
        public Set<N> successors(N node) {
            return delegate().predecessors((Object) node);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public int inDegree(N node) {
            return delegate().outDegree(node);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public int outDegree(N node) {
            return delegate().inDegree(node);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.Network
        public Set<E> inEdges(N node) {
            return delegate().outEdges(node);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.Network
        public Set<E> outEdges(N node) {
            return delegate().inEdges(node);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.Network
        public EndpointPair<N> incidentNodes(E edge) {
            EndpointPair<N> endpointPair = delegate().incidentNodes(edge);
            return EndpointPair.of((Network<?, ?>) this.network, (Object) endpointPair.nodeV(), (Object) endpointPair.nodeU());
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public Set<E> edgesConnecting(N nodeU, N nodeV) {
            return delegate().edgesConnecting(nodeV, nodeU);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public E edgeConnectingOrNull(N nodeU, N nodeV) {
            return delegate().edgeConnectingOrNull(nodeV, nodeU);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public boolean hasEdgeConnecting(N nodeU, N nodeV) {
            return delegate().hasEdgeConnecting(nodeV, nodeU);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v8, resolved type: com.google.common.graph.MutableGraph */
    /* JADX WARN: Multi-variable type inference failed */
    public static <N> MutableGraph<N> inducedSubgraph(Graph<N> graph, Iterable<? extends N> nodes) {
        MutableGraph subgraph = nodes instanceof Collection ? (MutableGraph<N1>) GraphBuilder.from(graph).expectedNodeCount(((Collection) nodes).size()).build() : (MutableGraph<N>) GraphBuilder.from(graph).build();
        Iterator<? extends N> it = nodes.iterator();
        while (it.hasNext()) {
            subgraph.addNode(it.next());
        }
        for (N node : subgraph.nodes()) {
            for (N successorNode : graph.successors((Object) node)) {
                if (subgraph.nodes().contains(successorNode)) {
                    subgraph.putEdge(node, successorNode);
                }
            }
        }
        return (MutableGraph<N>) subgraph;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v8, resolved type: com.google.common.graph.MutableValueGraph */
    /* JADX WARN: Multi-variable type inference failed */
    public static <N, V> MutableValueGraph<N, V> inducedSubgraph(ValueGraph<N, V> graph, Iterable<? extends N> nodes) {
        MutableValueGraph subgraph = nodes instanceof Collection ? (MutableValueGraph<N1, V1>) ValueGraphBuilder.from(graph).expectedNodeCount(((Collection) nodes).size()).build() : (MutableValueGraph<N, V>) ValueGraphBuilder.from(graph).build();
        Iterator<? extends N> it = nodes.iterator();
        while (it.hasNext()) {
            subgraph.addNode(it.next());
        }
        for (N node : subgraph.nodes()) {
            for (N successorNode : graph.successors((Object) node)) {
                if (subgraph.nodes().contains(successorNode)) {
                    subgraph.putEdgeValue(node, successorNode, graph.edgeValueOrDefault(node, successorNode, null));
                }
            }
        }
        return (MutableValueGraph<N, V>) subgraph;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v8, resolved type: com.google.common.graph.MutableNetwork */
    /* JADX WARN: Multi-variable type inference failed */
    public static <N, E> MutableNetwork<N, E> inducedSubgraph(Network<N, E> network, Iterable<? extends N> nodes) {
        MutableNetwork subgraph = nodes instanceof Collection ? (MutableNetwork<N1, E1>) NetworkBuilder.from(network).expectedNodeCount(((Collection) nodes).size()).build() : (MutableNetwork<N, E>) NetworkBuilder.from(network).build();
        Iterator<? extends N> it = nodes.iterator();
        while (it.hasNext()) {
            subgraph.addNode(it.next());
        }
        for (N n : subgraph.nodes()) {
            for (E edge : network.outEdges(n)) {
                N successorNode = network.incidentNodes(edge).adjacentNode(n);
                if (subgraph.nodes().contains(successorNode)) {
                    subgraph.addEdge(n, successorNode, edge);
                }
            }
        }
        return (MutableNetwork<N, E>) subgraph;
    }

    public static <N> MutableGraph<N> copyOf(Graph<N> graph) {
        MutableGraph mutableGraph = (MutableGraph<N1>) GraphBuilder.from(graph).expectedNodeCount(graph.nodes().size()).build();
        for (N node : graph.nodes()) {
            mutableGraph.addNode(node);
        }
        for (EndpointPair<N> edge : graph.edges()) {
            mutableGraph.putEdge(edge.nodeU(), edge.nodeV());
        }
        return mutableGraph;
    }

    public static <N, V> MutableValueGraph<N, V> copyOf(ValueGraph<N, V> graph) {
        MutableValueGraph mutableValueGraph = (MutableValueGraph<N1, V1>) ValueGraphBuilder.from(graph).expectedNodeCount(graph.nodes().size()).build();
        for (N node : graph.nodes()) {
            mutableValueGraph.addNode(node);
        }
        for (EndpointPair<N> edge : graph.edges()) {
            mutableValueGraph.putEdgeValue(edge.nodeU(), edge.nodeV(), graph.edgeValueOrDefault(edge.nodeU(), edge.nodeV(), null));
        }
        return mutableValueGraph;
    }

    public static <N, E> MutableNetwork<N, E> copyOf(Network<N, E> network) {
        MutableNetwork mutableNetwork = (MutableNetwork<N1, E1>) NetworkBuilder.from(network).expectedNodeCount(network.nodes().size()).expectedEdgeCount(network.edges().size()).build();
        for (N node : network.nodes()) {
            mutableNetwork.addNode(node);
        }
        for (E edge : network.edges()) {
            EndpointPair<N> endpointPair = network.incidentNodes(edge);
            mutableNetwork.addEdge(endpointPair.nodeU(), endpointPair.nodeV(), edge);
        }
        return mutableNetwork;
    }

    @CanIgnoreReturnValue
    static int checkNonNegative(int value) {
        Preconditions.checkArgument(value >= 0, "Not true that %s is non-negative.", value);
        return value;
    }

    @CanIgnoreReturnValue
    static long checkNonNegative(long value) {
        Preconditions.checkArgument(value >= 0, "Not true that %s is non-negative.", value);
        return value;
    }

    @CanIgnoreReturnValue
    static int checkPositive(int value) {
        Preconditions.checkArgument(value > 0, "Not true that %s is positive.", value);
        return value;
    }

    @CanIgnoreReturnValue
    static long checkPositive(long value) {
        Preconditions.checkArgument(value > 0, "Not true that %s is positive.", value);
        return value;
    }
}
