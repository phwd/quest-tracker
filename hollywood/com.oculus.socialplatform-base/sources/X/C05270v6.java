package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.LinkedListMultimap;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0v6  reason: invalid class name and case insensitive filesystem */
public class C05270v6 implements Iterator<K> {
    public int A00;
    @NullableDecl
    public AnonymousClass0fE<K, V> A01;
    public AnonymousClass0fE<K, V> A02;
    public final Set<K> A03;
    public final /* synthetic */ LinkedListMultimap A04;

    public C05270v6(LinkedListMultimap linkedListMultimap) {
        this.A04 = linkedListMultimap;
        this.A03 = AnonymousClass0wE.A01(linkedListMultimap.keySet().size());
        LinkedListMultimap linkedListMultimap2 = this.A04;
        this.A02 = linkedListMultimap2.A02;
        this.A00 = linkedListMultimap2.A00;
    }

    private void A00() {
        if (this.A04.A00 != this.A00) {
            throw new ConcurrentModificationException();
        }
    }

    public final boolean hasNext() {
        A00();
        if (this.A02 != null) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final K next() {
        AnonymousClass0fE<K, V> r0;
        A00();
        AnonymousClass0fE<K, V> r02 = this.A02;
        if (r02 != null) {
            this.A01 = r02;
            Set<K> set = this.A03;
            set.add(r02.A05);
            do {
                r0 = this.A02.A03;
                this.A02 = r0;
                if (r0 == null) {
                    break;
                }
            } while (!set.add(r0.A05));
            return this.A01.A05;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        A00();
        boolean z = false;
        if (this.A01 != null) {
            z = true;
        }
        Preconditions.checkState(z, "no calls to next() since the last call to remove()");
        LinkedListMultimap linkedListMultimap = this.A04;
        AnonymousClass0v9 r1 = new AnonymousClass0v9(linkedListMultimap, this.A01.A05);
        while (r1.hasNext()) {
            r1.next();
            r1.remove();
        }
        this.A01 = null;
        this.A00 = linkedListMultimap.A00;
    }
}
