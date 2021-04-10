package X;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Iterator;
import javax.annotation.Nullable;

/* renamed from: X.0G8  reason: invalid class name */
public final class AnonymousClass0G8 implements Iterator<AnonymousClass0G6> {
    public static final FileFilter A03 = new AnonymousClass0Fz();
    public static final Comparator<File> A04 = new AnonymousClass0G0();
    @Nullable
    public AnonymousClass0G6 A00;
    public boolean A01;
    public final ArrayDeque<AnonymousClass0G7> A02 = new ArrayDeque<>();

    public final boolean hasNext() {
        AnonymousClass0G6 r1;
        if (!this.A01) {
            this.A01 = true;
            while (true) {
                ArrayDeque<AnonymousClass0G7> arrayDeque = this.A02;
                r1 = null;
                if (arrayDeque.isEmpty()) {
                    break;
                }
                AnonymousClass0G7 first = arrayDeque.getFirst();
                AnonymousClass0G3 r2 = first.A02;
                Iterator<AnonymousClass0G3> it = first.A01;
                if (it == null) {
                    it = r2.A00();
                    first.A01 = it;
                }
                if (it.hasNext()) {
                    first.A00++;
                    Iterator<AnonymousClass0G3> it2 = first.A01;
                    if (it2 == null) {
                        it2 = r2.A00();
                        first.A01 = it2;
                    }
                    arrayDeque.addFirst(new AnonymousClass0G7(it2.next()));
                    if (first.A00 == 1) {
                        r1 = new AnonymousClass0G6(r2, 1);
                        break;
                    }
                } else {
                    arrayDeque.removeFirst();
                    int i = 3;
                    if (r2 instanceof AbstractC06960qO) {
                        i = 2;
                    }
                    r1 = new AnonymousClass0G6(r2, i);
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

    public AnonymousClass0G8(AnonymousClass0JW r5) {
        Iterator<AnonymousClass0G3> A002 = r5.A00();
        while (A002.hasNext()) {
            this.A02.addLast(new AnonymousClass0G7(A002.next()));
        }
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ AnonymousClass0G6 next() {
        if (hasNext()) {
            AnonymousClass0G6 r1 = this.A00;
            this.A00 = null;
            this.A01 = false;
            return r1;
        }
        throw new IllegalStateException();
    }
}
