package X;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Iterator;
import javax.annotation.Nullable;

public final class Ft implements Iterator<Fr> {
    public static final FileFilter A03 = new Fk();
    public static final Comparator<File> A04 = new Fl();
    @Nullable
    public Fr A00;
    public boolean A01;
    public final ArrayDeque<Fs> A02 = new ArrayDeque<>();

    public final boolean hasNext() {
        Fr fr;
        if (!this.A01) {
            this.A01 = true;
            while (true) {
                ArrayDeque<Fs> arrayDeque = this.A02;
                fr = null;
                if (arrayDeque.isEmpty()) {
                    break;
                }
                Fs first = arrayDeque.getFirst();
                Fo fo = first.A02;
                Iterator<Fo> it = first.A01;
                if (it == null) {
                    it = fo.A00();
                    first.A01 = it;
                }
                if (it.hasNext()) {
                    first.A00++;
                    Iterator<Fo> it2 = first.A01;
                    if (it2 == null) {
                        it2 = fo.A00();
                        first.A01 = it2;
                    }
                    arrayDeque.addFirst(new Fs(it2.next()));
                    if (first.A00 == 1) {
                        fr = new Fr(fo, 1);
                        break;
                    }
                } else {
                    arrayDeque.removeFirst();
                    int i = 3;
                    if (fo instanceof ZA) {
                        i = 2;
                    }
                    fr = new Fr(fo, i);
                }
            }
            this.A00 = fr;
        }
        if (this.A00 == null) {
            return false;
        }
        return true;
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public Ft(IR ir) {
        Iterator<Fo> A002 = ir.A00();
        while (A002.hasNext()) {
            this.A02.addLast(new Fs(A002.next()));
        }
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Fr next() {
        if (hasNext()) {
            Fr fr = this.A00;
            this.A00 = null;
            this.A01 = false;
            return fr;
        }
        throw new IllegalStateException();
    }
}
