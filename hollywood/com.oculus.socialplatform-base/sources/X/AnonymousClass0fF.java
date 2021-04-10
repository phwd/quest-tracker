package X;

import com.google.common.collect.LinkedListMultimap;
import java.util.Iterator;

/* renamed from: X.0fF  reason: invalid class name */
public class AnonymousClass0fF extends AbstractC05580wA<K> {
    public final /* synthetic */ LinkedListMultimap A00;

    public AnonymousClass0fF(LinkedListMultimap linkedListMultimap) {
        this.A00 = linkedListMultimap;
    }

    public final boolean contains(Object obj) {
        return this.A00.containsKey(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<K> iterator() {
        return new C05270v6(this.A00);
    }

    public final boolean remove(Object obj) {
        return !this.A00.A96(obj).isEmpty();
    }

    public final int size() {
        return this.A00.A04.size();
    }
}
