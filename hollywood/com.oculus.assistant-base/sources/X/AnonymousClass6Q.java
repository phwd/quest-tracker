package X;

import java.io.File;
import java.util.Iterator;

/* renamed from: X.6Q  reason: invalid class name */
public final class AnonymousClass6Q implements Iterator {
    public final /* synthetic */ AbstractC0671ep A00;
    public final /* synthetic */ Iterator A01;

    public AnonymousClass6Q(AbstractC0671ep epVar, Iterator it) {
        this.A00 = epVar;
        this.A01 = it;
    }

    public final boolean hasNext() {
        return this.A01.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        AbstractC0671ep epVar = this.A00;
        File file = (File) this.A01.next();
        if (epVar instanceof e5) {
            return new C00212o(file);
        }
        if (epVar instanceof C0661e7) {
            return new e5(file);
        }
        if (epVar instanceof C0662e8) {
            return new C0661e7(file);
        }
        if (!(epVar instanceof C00202n)) {
            return new C00202n(file);
        }
        return new C0670eo(file);
    }

    public final void remove() {
        this.A01.remove();
    }
}
