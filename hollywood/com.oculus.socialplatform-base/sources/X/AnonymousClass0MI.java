package X;

import java.util.Iterator;

/* renamed from: X.0MI  reason: invalid class name */
public class AnonymousClass0MI extends AnonymousClass0ft<E> {
    public final Iterator<? extends E> A00;
    public final Iterator<? extends E> A01 = this.A02.A01.iterator();
    public final /* synthetic */ C01570eu A02;

    public AnonymousClass0MI(C01570eu r2) {
        this.A02 = r2;
        this.A00 = r2.A00.iterator();
    }

    @Override // X.AnonymousClass0ft
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
                super.A00 = AnonymousClass007.A03;
                return null;
            }
        } while (this.A02.A00.contains(e));
        return e;
    }
}
