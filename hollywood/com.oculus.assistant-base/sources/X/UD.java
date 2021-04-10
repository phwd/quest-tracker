package X;

import com.google.common.collect.LinkedListMultimap;
import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.Map;

public final class UD extends AbstractSequentialList<Map.Entry<K, V>> {
    public final /* synthetic */ LinkedListMultimap A00;

    public UD(LinkedListMultimap linkedListMultimap) {
        this.A00 = linkedListMultimap;
    }

    @Override // java.util.List, java.util.AbstractList, java.util.AbstractSequentialList
    public final ListIterator listIterator(int i) {
        return new UG(this.A00, i);
    }

    public final int size() {
        return this.A00.A01;
    }
}
