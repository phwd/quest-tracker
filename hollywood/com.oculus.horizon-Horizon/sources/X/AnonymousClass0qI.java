package X;

import com.google.common.base.Preconditions;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0qI  reason: invalid class name */
public class AnonymousClass0qI<T> implements Iterator<T> {
    @NullableDecl
    public Deque<Iterator<? extends Iterator<? extends T>>> A00;
    public Iterator<? extends Iterator<? extends T>> A01;
    public Iterator<? extends T> A02 = AnonymousClass06h.A01;
    @NullableDecl
    public Iterator<? extends T> A03;

    public final boolean hasNext() {
        Iterator<? extends Iterator<? extends T>> it;
        while (true) {
            Iterator<? extends T> it2 = this.A02;
            if (it2 == null) {
                throw null;
            } else if (it2.hasNext()) {
                return true;
            } else {
                while (true) {
                    Iterator<? extends Iterator<? extends T>> it3 = this.A01;
                    if (it3 != null && it3.hasNext()) {
                        it = this.A01;
                        break;
                    }
                    Deque<Iterator<? extends Iterator<? extends T>>> deque = this.A00;
                    if (deque == null || deque.isEmpty()) {
                        it = null;
                    } else {
                        this.A01 = this.A00.removeFirst();
                    }
                }
                it = null;
                this.A01 = it;
                if (it == null) {
                    return false;
                }
                Iterator<? extends T> it4 = (Iterator) it.next();
                this.A02 = it4;
                if (it4 instanceof AnonymousClass0qI) {
                    AnonymousClass0qI r2 = (AnonymousClass0qI) it4;
                    this.A02 = r2.A02;
                    Deque deque2 = this.A00;
                    if (deque2 == null) {
                        deque2 = new ArrayDeque();
                        this.A00 = deque2;
                    }
                    deque2.addFirst(this.A01);
                    if (r2.A00 != null) {
                        while (!r2.A00.isEmpty()) {
                            this.A00.addFirst(r2.A00.removeLast());
                        }
                    }
                    this.A01 = r2.A01;
                }
            }
        }
    }

    public final void remove() {
        boolean z = false;
        if (this.A03 != null) {
            z = true;
        }
        Preconditions.checkState(z, "no calls to next() since the last call to remove()");
        this.A03.remove();
        this.A03 = null;
    }

    public AnonymousClass0qI(Iterator<? extends Iterator<? extends T>> it) {
        this.A01 = it;
    }

    @Override // java.util.Iterator
    public final T next() {
        if (hasNext()) {
            Iterator<? extends T> it = this.A02;
            this.A03 = it;
            return (T) it.next();
        }
        throw new NoSuchElementException();
    }
}
