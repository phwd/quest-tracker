package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.UnmodifiableIterator;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
public abstract class MultiEdgesConnecting<E> extends AbstractSet<E> {
    private final Map<E, ?> outEdgeToNode;
    private final Object targetNode;

    MultiEdgesConnecting(Map<E, ?> outEdgeToNode2, Object targetNode2) {
        this.outEdgeToNode = (Map) Preconditions.checkNotNull(outEdgeToNode2);
        this.targetNode = Preconditions.checkNotNull(targetNode2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public UnmodifiableIterator<E> iterator() {
        final Iterator<? extends Map.Entry<E, ?>> entries = this.outEdgeToNode.entrySet().iterator();
        return new AbstractIterator<E>() {
            /* class com.google.common.graph.MultiEdgesConnecting.AnonymousClass1 */

            /* access modifiers changed from: protected */
            @Override // com.google.common.collect.AbstractIterator
            public E computeNext() {
                while (entries.hasNext()) {
                    Map.Entry<E, ?> entry = (Map.Entry) entries.next();
                    if (MultiEdgesConnecting.this.targetNode.equals(entry.getValue())) {
                        return entry.getKey();
                    }
                }
                return (E) endOfData();
            }
        };
    }

    public boolean contains(@NullableDecl Object edge) {
        return this.targetNode.equals(this.outEdgeToNode.get(edge));
    }
}
