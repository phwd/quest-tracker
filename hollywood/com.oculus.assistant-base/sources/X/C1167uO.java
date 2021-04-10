package X;

import com.google.common.collect.LinkedListMultimap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* renamed from: X.uO  reason: case insensitive filesystem */
public final class C1167uO extends UV<K> {
    public final /* synthetic */ LinkedListMultimap A00;

    public C1167uO(LinkedListMultimap linkedListMultimap) {
        this.A00 = linkedListMultimap;
    }

    public final boolean contains(Object obj) {
        return this.A00.containsKey(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator iterator() {
        return new UE(this.A00);
    }

    public final boolean remove(Object obj) {
        LinkedListMultimap linkedListMultimap = this.A00;
        UH uh = new UH(linkedListMultimap, obj);
        ArrayList arrayList = new ArrayList();
        UB.A01(arrayList, uh);
        List unmodifiableList = Collections.unmodifiableList(arrayList);
        UB.A00(new UH(linkedListMultimap, obj));
        return !unmodifiableList.isEmpty();
    }

    public final int size() {
        return this.A00.A04.size();
    }
}
