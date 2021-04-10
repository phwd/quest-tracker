package X;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/* renamed from: X.uj  reason: case insensitive filesystem */
public final class C1184uj extends UW<E> {
    public final /* synthetic */ Set A00;
    public final /* synthetic */ Set A01;

    public C1184uj(Set set, Set set2) {
        this.A00 = set;
        this.A01 = set2;
    }

    public final boolean contains(Object obj) {
        if (!this.A00.contains(obj) || !this.A01.contains(obj)) {
            return false;
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean containsAll(Collection collection) {
        if (!this.A00.containsAll(collection) || !this.A01.containsAll(collection)) {
            return false;
        }
        return true;
    }

    public final boolean isEmpty() {
        return Collections.disjoint(this.A00, this.A01);
    }

    public final int size() {
        int i = 0;
        for (Object obj : this.A00) {
            if (this.A01.contains(obj)) {
                i++;
            }
        }
        return i;
    }
}
