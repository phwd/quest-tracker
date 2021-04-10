package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Iterator;
import javax.annotation.Nullable;

@GwtCompatible(serializable = true)
final class PairwiseEquivalence<T> extends Equivalence<Iterable<T>> implements Serializable {
    private static final long serialVersionUID = 1;
    final Equivalence<? super T> elementEquivalence;

    /* access modifiers changed from: protected */
    @Override // com.google.common.base.Equivalence
    public /* bridge */ /* synthetic */ boolean doEquivalent(Object obj, Object obj2) {
        return doEquivalent((Iterable) ((Iterable) obj), (Iterable) ((Iterable) obj2));
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.base.Equivalence
    public /* bridge */ /* synthetic */ int doHash(Object obj) {
        return doHash((Iterable) ((Iterable) obj));
    }

    PairwiseEquivalence(Equivalence<? super T> elementEquivalence2) {
        this.elementEquivalence = (Equivalence) Preconditions.checkNotNull(elementEquivalence2);
    }

    /* access modifiers changed from: protected */
    public boolean doEquivalent(Iterable<T> iterableA, Iterable<T> iterableB) {
        Iterator<T> iteratorA = iterableA.iterator();
        Iterator<T> iteratorB = iterableB.iterator();
        while (iteratorA.hasNext() && iteratorB.hasNext()) {
            if (!this.elementEquivalence.equivalent(iteratorA.next(), iteratorB.next())) {
                return false;
            }
        }
        if (iteratorA.hasNext() || iteratorB.hasNext()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public int doHash(Iterable<T> iterable) {
        int hash = 78721;
        for (T element : iterable) {
            hash = (hash * 24943) + this.elementEquivalence.hash(element);
        }
        return hash;
    }

    public boolean equals(@Nullable Object object) {
        if (object instanceof PairwiseEquivalence) {
            return this.elementEquivalence.equals(((PairwiseEquivalence) object).elementEquivalence);
        }
        return false;
    }

    public int hashCode() {
        return this.elementEquivalence.hashCode() ^ 1185147655;
    }

    public String toString() {
        return this.elementEquivalence + ".pairwise()";
    }
}
