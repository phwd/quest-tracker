package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
public final class DirectedGraphConnections<N, V> implements GraphConnections<N, V> {
    private static final Object PRED = new Object();
    private final Map<N, Object> adjacentNodeValues;
    private int predecessorCount;
    private int successorCount;

    /* access modifiers changed from: private */
    public static final class PredAndSucc {
        private final Object successorValue;

        PredAndSucc(Object successorValue2) {
            this.successorValue = successorValue2;
        }
    }

    private DirectedGraphConnections(Map<N, Object> adjacentNodeValues2, int predecessorCount2, int successorCount2) {
        this.adjacentNodeValues = (Map) Preconditions.checkNotNull(adjacentNodeValues2);
        this.predecessorCount = Graphs.checkNonNegative(predecessorCount2);
        this.successorCount = Graphs.checkNonNegative(successorCount2);
        Preconditions.checkState(predecessorCount2 <= adjacentNodeValues2.size() && successorCount2 <= adjacentNodeValues2.size());
    }

    static <N, V> DirectedGraphConnections<N, V> of() {
        return new DirectedGraphConnections<>(new HashMap(4, 1.0f), 0, 0);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.HashMap */
    /* JADX WARN: Multi-variable type inference failed */
    static <N, V> DirectedGraphConnections<N, V> ofImmutable(Set<N> predecessors, Map<N, V> successorValues) {
        Map<N, Object> adjacentNodeValues2 = new HashMap<>();
        adjacentNodeValues2.putAll(successorValues);
        for (N predecessor : predecessors) {
            Object value = adjacentNodeValues2.put(predecessor, PRED);
            if (value != null) {
                adjacentNodeValues2.put(predecessor, new PredAndSucc(value));
            }
        }
        return new DirectedGraphConnections<>(ImmutableMap.copyOf(adjacentNodeValues2), predecessors.size(), successorValues.size());
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> adjacentNodes() {
        return Collections.unmodifiableSet(this.adjacentNodeValues.keySet());
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> predecessors() {
        return new AbstractSet<N>() {
            /* class com.google.common.graph.DirectedGraphConnections.AnonymousClass1 */

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public UnmodifiableIterator<N> iterator() {
                final Iterator<Map.Entry<N, Object>> entries = DirectedGraphConnections.this.adjacentNodeValues.entrySet().iterator();
                return new AbstractIterator<N>() {
                    /* class com.google.common.graph.DirectedGraphConnections.AnonymousClass1.AnonymousClass1 */

                    /* access modifiers changed from: protected */
                    @Override // com.google.common.collect.AbstractIterator
                    public N computeNext() {
                        while (entries.hasNext()) {
                            Map.Entry<N, Object> entry = (Map.Entry) entries.next();
                            if (DirectedGraphConnections.isPredecessor(entry.getValue())) {
                                return entry.getKey();
                            }
                        }
                        return (N) endOfData();
                    }
                };
            }

            public int size() {
                return DirectedGraphConnections.this.predecessorCount;
            }

            public boolean contains(@NullableDecl Object obj) {
                return DirectedGraphConnections.isPredecessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
            }
        };
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> successors() {
        return new AbstractSet<N>() {
            /* class com.google.common.graph.DirectedGraphConnections.AnonymousClass2 */

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public UnmodifiableIterator<N> iterator() {
                final Iterator<Map.Entry<N, Object>> entries = DirectedGraphConnections.this.adjacentNodeValues.entrySet().iterator();
                return new AbstractIterator<N>() {
                    /* class com.google.common.graph.DirectedGraphConnections.AnonymousClass2.AnonymousClass1 */

                    /* access modifiers changed from: protected */
                    @Override // com.google.common.collect.AbstractIterator
                    public N computeNext() {
                        while (entries.hasNext()) {
                            Map.Entry<N, Object> entry = (Map.Entry) entries.next();
                            if (DirectedGraphConnections.isSuccessor(entry.getValue())) {
                                return entry.getKey();
                            }
                        }
                        return (N) endOfData();
                    }
                };
            }

            public int size() {
                return DirectedGraphConnections.this.successorCount;
            }

            public boolean contains(@NullableDecl Object obj) {
                return DirectedGraphConnections.isSuccessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
            }
        };
    }

    @Override // com.google.common.graph.GraphConnections
    public V value(N node) {
        V v = (V) this.adjacentNodeValues.get(node);
        if (v == PRED) {
            return null;
        }
        return v instanceof PredAndSucc ? (V) ((PredAndSucc) v).successorValue : v;
    }

    @Override // com.google.common.graph.GraphConnections
    public void removePredecessor(N node) {
        Object previousValue = this.adjacentNodeValues.get(node);
        if (previousValue == PRED) {
            this.adjacentNodeValues.remove(node);
            int i = this.predecessorCount - 1;
            this.predecessorCount = i;
            Graphs.checkNonNegative(i);
        } else if (previousValue instanceof PredAndSucc) {
            this.adjacentNodeValues.put(node, ((PredAndSucc) previousValue).successorValue);
            int i2 = this.predecessorCount - 1;
            this.predecessorCount = i2;
            Graphs.checkNonNegative(i2);
        }
    }

    @Override // com.google.common.graph.GraphConnections
    public V removeSuccessor(Object node) {
        V v = (V) this.adjacentNodeValues.get(node);
        if (v == null || v == PRED) {
            return null;
        }
        if (v instanceof PredAndSucc) {
            this.adjacentNodeValues.put(node, PRED);
            int i = this.successorCount - 1;
            this.successorCount = i;
            Graphs.checkNonNegative(i);
            return (V) ((PredAndSucc) v).successorValue;
        }
        this.adjacentNodeValues.remove(node);
        int i2 = this.successorCount - 1;
        this.successorCount = i2;
        Graphs.checkNonNegative(i2);
        return v;
    }

    @Override // com.google.common.graph.GraphConnections
    public void addPredecessor(N node, V v) {
        Object previousValue = this.adjacentNodeValues.put(node, PRED);
        if (previousValue == null) {
            int i = this.predecessorCount + 1;
            this.predecessorCount = i;
            Graphs.checkPositive(i);
        } else if (previousValue instanceof PredAndSucc) {
            this.adjacentNodeValues.put(node, previousValue);
        } else if (previousValue != PRED) {
            this.adjacentNodeValues.put(node, new PredAndSucc(previousValue));
            int i2 = this.predecessorCount + 1;
            this.predecessorCount = i2;
            Graphs.checkPositive(i2);
        }
    }

    @Override // com.google.common.graph.GraphConnections
    public V addSuccessor(N node, V value) {
        V v = (V) this.adjacentNodeValues.put(node, value);
        if (v == null) {
            int i = this.successorCount + 1;
            this.successorCount = i;
            Graphs.checkPositive(i);
            return null;
        } else if (v instanceof PredAndSucc) {
            this.adjacentNodeValues.put(node, new PredAndSucc(value));
            return (V) ((PredAndSucc) v).successorValue;
        } else if (v != PRED) {
            return v;
        } else {
            this.adjacentNodeValues.put(node, new PredAndSucc(value));
            int i2 = this.successorCount + 1;
            this.successorCount = i2;
            Graphs.checkPositive(i2);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static boolean isPredecessor(@NullableDecl Object value) {
        return value == PRED || (value instanceof PredAndSucc);
    }

    /* access modifiers changed from: private */
    public static boolean isSuccessor(@NullableDecl Object value) {
        return (value == PRED || value == null) ? false : true;
    }
}
