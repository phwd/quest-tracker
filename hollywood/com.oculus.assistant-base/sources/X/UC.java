package X;

import com.google.common.collect.LinkedListMultimap;
import java.util.AbstractSequentialList;
import java.util.ListIterator;

public final class UC extends AbstractSequentialList<V> {
    public final /* synthetic */ LinkedListMultimap A00;
    public final /* synthetic */ Object A01;

    public UC(LinkedListMultimap linkedListMultimap, Object obj) {
        this.A00 = linkedListMultimap;
        this.A01 = obj;
    }

    @Override // java.util.List, java.util.AbstractList, java.util.AbstractSequentialList
    public final ListIterator listIterator(int i) {
        return new UH(this.A00, this.A01, i);
    }

    public final int size() {
        UF uf = (UF) this.A00.A04.get(this.A01);
        if (uf == null) {
            return 0;
        }
        return uf.A00;
    }
}
