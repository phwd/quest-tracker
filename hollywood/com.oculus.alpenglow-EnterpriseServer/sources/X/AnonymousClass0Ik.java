package X;

import java.util.Iterator;

/* renamed from: X.0Ik  reason: invalid class name */
public class AnonymousClass0Ik extends AnonymousClass0YO<E> {
    public final Iterator<E> A00;
    public final /* synthetic */ C02300Xq A01;

    public AnonymousClass0Ik(C02300Xq r2) {
        this.A01 = r2;
        this.A00 = r2.A00.iterator();
    }

    @Override // X.AnonymousClass0YO
    public final E A00() {
        E next;
        do {
            Iterator<E> it = this.A00;
            if (it.hasNext()) {
                next = it.next();
            } else {
                super.A00 = AnonymousClass007.A0C;
                return null;
            }
        } while (!this.A01.A01.contains(next));
        return next;
    }
}
