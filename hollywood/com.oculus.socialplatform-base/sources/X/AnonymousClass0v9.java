package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.LinkedListMultimap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0v9  reason: invalid class name */
public class AnonymousClass0v9 implements ListIterator<V> {
    public int A00;
    @NullableDecl
    public AnonymousClass0fE<K, V> A01;
    @NullableDecl
    public AnonymousClass0fE<K, V> A02;
    @NullableDecl
    public AnonymousClass0fE<K, V> A03;
    @NullableDecl
    public final Object A04;
    public final /* synthetic */ LinkedListMultimap A05;

    @Override // java.util.ListIterator
    public final void add(V v) {
        this.A03 = LinkedListMultimap.A00(this.A05, this.A04, v, this.A02);
        this.A00++;
        this.A01 = null;
    }

    public final boolean hasNext() {
        if (this.A02 != null) {
            return true;
        }
        return false;
    }

    public final boolean hasPrevious() {
        if (this.A03 != null) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    @CanIgnoreReturnValue
    public final V next() {
        AnonymousClass0fE<K, V> r1 = this.A02;
        if (r1 != null) {
            this.A01 = r1;
            this.A03 = r1;
            this.A02 = r1.A00;
            this.A00++;
            return r1.A02;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    @CanIgnoreReturnValue
    public final V previous() {
        AnonymousClass0fE<K, V> r1 = this.A03;
        if (r1 != null) {
            this.A01 = r1;
            this.A02 = r1;
            this.A03 = r1.A01;
            this.A00--;
            return r1.A02;
        }
        throw new NoSuchElementException();
    }

    public final int previousIndex() {
        return this.A00 - 1;
    }

    public final void remove() {
        boolean z = false;
        if (this.A01 != null) {
            z = true;
        }
        Preconditions.checkState(z, "no calls to next() since the last call to remove()");
        AnonymousClass0fE<K, V> r1 = this.A01;
        if (r1 != this.A02) {
            this.A03 = r1.A01;
            this.A00--;
        } else {
            this.A02 = r1.A00;
        }
        LinkedListMultimap.A01(this.A05, r1);
        this.A01 = null;
    }

    @Override // java.util.ListIterator
    public final void set(V v) {
        AnonymousClass0fE<K, V> r1 = this.A01;
        boolean z = false;
        if (r1 != null) {
            z = true;
        }
        Preconditions.checkState(z);
        r1.A02 = v;
    }

    public final int nextIndex() {
        return this.A00;
    }

    public AnonymousClass0v9(@NullableDecl LinkedListMultimap linkedListMultimap, Object obj) {
        AnonymousClass0fE<K, V> r0;
        this.A05 = linkedListMultimap;
        this.A04 = obj;
        AnonymousClass0v7<K, V> r02 = linkedListMultimap.A04.get(obj);
        if (r02 == null) {
            r0 = null;
        } else {
            r0 = r02.A01;
        }
        this.A02 = r0;
    }

    public AnonymousClass0v9(@NullableDecl LinkedListMultimap linkedListMultimap, Object obj, int i) {
        int i2;
        AnonymousClass0fE<K, V> r0;
        AnonymousClass0fE<K, V> r02;
        this.A05 = linkedListMultimap;
        AnonymousClass0v7<K, V> r3 = linkedListMultimap.A04.get(obj);
        if (r3 == null) {
            i2 = 0;
        } else {
            i2 = r3.A00;
        }
        Preconditions.checkPositionIndex(i, i2);
        if (i < (i2 >> 1)) {
            if (r3 == null) {
                r0 = null;
            } else {
                r0 = r3.A01;
            }
            this.A02 = r0;
            while (true) {
                int i3 = i - 1;
                if (i <= 0) {
                    break;
                }
                next();
                i = i3;
            }
        } else {
            if (r3 == null) {
                r02 = null;
            } else {
                r02 = r3.A02;
            }
            this.A03 = r02;
            this.A00 = i2;
            while (true) {
                int i4 = i + 1;
                if (i >= i2) {
                    break;
                }
                previous();
                i = i4;
            }
        }
        this.A04 = obj;
        this.A01 = null;
    }
}
