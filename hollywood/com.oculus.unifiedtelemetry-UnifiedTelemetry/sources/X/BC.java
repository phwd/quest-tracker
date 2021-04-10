package X;

import java.util.Iterator;

public class BC extends AbstractC0193Uy<E> {
    public final Iterator<? extends E> A00;
    public final Iterator<? extends E> A01 = this.A02.A01.iterator();
    public final /* synthetic */ UY A02;

    public BC(UY uy) {
        this.A02 = uy;
        this.A00 = uy.A00.iterator();
    }

    @Override // X.AbstractC0193Uy
    public final E A00() {
        E e;
        Iterator<? extends E> it = this.A00;
        if (it.hasNext()) {
            return (E) it.next();
        }
        do {
            Iterator<? extends E> it2 = this.A01;
            if (it2.hasNext()) {
                e = (E) it2.next();
            } else {
                super.A00 = AnonymousClass07.A02;
                return null;
            }
        } while (this.A02.A00.contains(e));
        return e;
    }
}
