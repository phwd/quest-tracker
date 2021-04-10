package X;

import com.google.common.collect.LinkedListMultimap;
import java.util.AbstractSequentialList;
import java.util.ListIterator;

/* renamed from: X.0v3  reason: invalid class name */
public class AnonymousClass0v3 extends AbstractSequentialList<V> {
    public final /* synthetic */ LinkedListMultimap A00;
    public final /* synthetic */ Object A01;

    public AnonymousClass0v3(LinkedListMultimap linkedListMultimap, Object obj) {
        this.A00 = linkedListMultimap;
        this.A01 = obj;
    }

    @Override // java.util.List, java.util.AbstractList, java.util.AbstractSequentialList
    public final ListIterator<V> listIterator(int i) {
        return new AnonymousClass0v9(this.A00, this.A01, i);
    }

    public final int size() {
        AnonymousClass0v7<K, V> r0 = this.A00.A04.get(this.A01);
        if (r0 == null) {
            return 0;
        }
        return r0.A00;
    }
}
