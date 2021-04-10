package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.LinkedListMultimap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0v8  reason: invalid class name */
public class AnonymousClass0v8 implements ListIterator<Map.Entry<K, V>> {
    public int A00;
    public int A01;
    @NullableDecl
    public AnonymousClass0fE<K, V> A02;
    @NullableDecl
    public AnonymousClass0fE<K, V> A03;
    @NullableDecl
    public AnonymousClass0fE<K, V> A04;
    public final /* synthetic */ LinkedListMultimap A05;

    public AnonymousClass0v8(LinkedListMultimap linkedListMultimap, int i) {
        this.A05 = linkedListMultimap;
        this.A00 = linkedListMultimap.A00;
        int size = linkedListMultimap.size();
        Preconditions.checkPositionIndex(i, size);
        if (i < (size >> 1)) {
            this.A03 = linkedListMultimap.A02;
            while (true) {
                int i2 = i - 1;
                if (i <= 0) {
                    break;
                }
                A00();
                AnonymousClass0fE<K, V> r0 = this.A03;
                if (r0 != null) {
                    this.A02 = r0;
                    this.A04 = r0;
                    this.A03 = r0.A03;
                    this.A01++;
                    i = i2;
                } else {
                    throw new NoSuchElementException();
                }
            }
        } else {
            this.A04 = linkedListMultimap.A03;
            this.A01 = size;
            while (true) {
                int i3 = i + 1;
                if (i >= size) {
                    break;
                }
                A00();
                AnonymousClass0fE<K, V> r02 = this.A04;
                if (r02 != null) {
                    this.A02 = r02;
                    this.A03 = r02;
                    this.A04 = r02.A04;
                    this.A01--;
                    i = i3;
                } else {
                    throw new NoSuchElementException();
                }
            }
        }
        this.A02 = null;
    }

    private void A00() {
        if (this.A05.A00 != this.A00) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final int previousIndex() {
        return this.A01 - 1;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final boolean hasNext() {
        A00();
        if (this.A03 != null) {
            return true;
        }
        return false;
    }

    public final boolean hasPrevious() {
        A00();
        if (this.A04 != null) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    @CanIgnoreReturnValue
    public final /* bridge */ /* synthetic */ Object next() {
        A00();
        AnonymousClass0fE<K, V> r1 = this.A03;
        if (r1 != null) {
            this.A02 = r1;
            this.A04 = r1;
            this.A03 = r1.A03;
            this.A01++;
            return r1;
        }
        throw new NoSuchElementException();
    }

    public final int nextIndex() {
        return this.A01;
    }

    @Override // java.util.ListIterator
    @CanIgnoreReturnValue
    public final /* bridge */ /* synthetic */ Object previous() {
        A00();
        AnonymousClass0fE<K, V> r1 = this.A04;
        if (r1 != null) {
            this.A02 = r1;
            this.A03 = r1;
            this.A04 = r1.A04;
            this.A01--;
            return r1;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        A00();
        boolean z = false;
        if (this.A02 != null) {
            z = true;
        }
        Preconditions.checkState(z, "no calls to next() since the last call to remove()");
        AnonymousClass0fE<K, V> r2 = this.A02;
        if (r2 != this.A03) {
            this.A04 = r2.A04;
            this.A01--;
        } else {
            this.A03 = r2.A03;
        }
        LinkedListMultimap linkedListMultimap = this.A05;
        LinkedListMultimap.A01(linkedListMultimap, r2);
        this.A02 = null;
        this.A00 = linkedListMultimap.A00;
    }
}
