package X;

import com.google.common.collect.LinkedListMultimap;
import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.Map;

/* renamed from: X.0v4  reason: invalid class name and case insensitive filesystem */
public class C05260v4 extends AbstractSequentialList<Map.Entry<K, V>> {
    public final /* synthetic */ LinkedListMultimap A00;

    public C05260v4(LinkedListMultimap linkedListMultimap) {
        this.A00 = linkedListMultimap;
    }

    @Override // java.util.List, java.util.AbstractList, java.util.AbstractSequentialList
    public final ListIterator<Map.Entry<K, V>> listIterator(int i) {
        return new AnonymousClass0v8(this.A00, i);
    }

    public final int size() {
        return this.A00.A01;
    }
}
