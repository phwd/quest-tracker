package X;

import java.io.File;
import java.util.Iterator;

public class Fm implements Iterator<Fo> {
    public final /* synthetic */ ZA A00;
    public final /* synthetic */ Iterator A01;

    public Fm(ZA za, Iterator it) {
        this.A00 = za;
        this.A01 = it;
    }

    public final boolean hasNext() {
        return this.A01.hasNext();
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.Iterator
    public final Fo next() {
        return this.A00.A01((File) this.A01.next());
    }

    public final void remove() {
        this.A01.remove();
    }
}
