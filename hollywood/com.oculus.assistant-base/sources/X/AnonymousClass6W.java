package X;

import java.io.FileFilter;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Iterator;

/* renamed from: X.6W  reason: invalid class name */
public final class AnonymousClass6W implements Iterator {
    public static final FileFilter A03 = new AnonymousClass6O();
    public static final Comparator A04 = new AnonymousClass6P();
    public AnonymousClass6U A00;
    public boolean A01;
    public final ArrayDeque A02 = new ArrayDeque();

    public final boolean hasNext() {
        AnonymousClass6U r1;
        if (!this.A01) {
            this.A01 = true;
            while (true) {
                ArrayDeque arrayDeque = this.A02;
                r1 = null;
                if (arrayDeque.isEmpty()) {
                    break;
                }
                AnonymousClass6V r3 = (AnonymousClass6V) arrayDeque.getFirst();
                AnonymousClass6S r2 = r3.A02;
                Iterator it = r3.A01;
                if (it == null) {
                    it = r2.A00();
                    r3.A01 = it;
                }
                if (it.hasNext()) {
                    r3.A00++;
                    Iterator it2 = r3.A01;
                    if (it2 == null) {
                        it2 = r2.A00();
                        r3.A01 = it2;
                    }
                    arrayDeque.addFirst(new AnonymousClass6V((AnonymousClass6S) it2.next()));
                    if (r3.A00 == 1) {
                        r1 = new AnonymousClass6U(r2, 1);
                        break;
                    }
                } else {
                    arrayDeque.removeFirst();
                    int i = 3;
                    if (r2 instanceof AbstractC0671ep) {
                        i = 2;
                    }
                    r1 = new AnonymousClass6U(r2, i);
                }
            }
            this.A00 = r1;
        }
        if (this.A00 == null) {
            return false;
        }
        return true;
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public AnonymousClass6W(C0662e8 e8Var) {
        Iterator A002 = e8Var.A00();
        while (A002.hasNext()) {
            this.A02.addLast(new AnonymousClass6V((AnonymousClass6S) A002.next()));
        }
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        if (hasNext()) {
            AnonymousClass6U r1 = this.A00;
            this.A00 = null;
            this.A01 = false;
            return r1;
        }
        throw new IllegalStateException();
    }
}
