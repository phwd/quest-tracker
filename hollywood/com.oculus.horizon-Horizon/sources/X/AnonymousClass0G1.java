package X;

import java.io.File;
import java.util.Iterator;

/* renamed from: X.0G1  reason: invalid class name */
public class AnonymousClass0G1 implements Iterator<AnonymousClass0G3> {
    public final /* synthetic */ AbstractC06960qO A00;
    public final /* synthetic */ Iterator A01;

    public AnonymousClass0G1(AbstractC06960qO r1, Iterator it) {
        this.A00 = r1;
        this.A01 = it;
    }

    public final boolean hasNext() {
        return this.A01.hasNext();
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.Iterator
    public final AnonymousClass0G3 next() {
        return this.A00.A01((File) this.A01.next());
    }

    public final void remove() {
        this.A01.remove();
    }
}
