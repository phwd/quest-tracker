package X;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

/* renamed from: X.0e1  reason: invalid class name */
public final class AnonymousClass0e1<E> extends AnonymousClass13Y<Collection<E>> {
    public final AnonymousClass13Y<E> A00;
    public final AnonymousClass143<? extends Collection<E>> A01;

    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r4, Object obj) throws IOException {
        Collection<E> collection = (Collection) obj;
        if (collection == null) {
            r4.A09();
            return;
        }
        r4.A05();
        for (E e : collection) {
            this.A00.A03(r4, e);
        }
        r4.A07();
    }

    public AnonymousClass0e1(AnonymousClass13N r2, Type type, AnonymousClass13Y<E> r4, AnonymousClass143<? extends Collection<E>> r5) {
        this.A00 = new AnonymousClass0dg(r2, r4, type);
        this.A01 = r5;
    }

    @Override // X.AnonymousClass13Y
    public final Object A02(AnonymousClass14I r3) throws IOException {
        if (r3.A0G() == AnonymousClass007.A09) {
            r3.A0P();
            return null;
        }
        Collection collection = (Collection) this.A01.A2F();
        r3.A0L();
        while (r3.A0R()) {
            collection.add(this.A00.A02(r3));
        }
        r3.A0N();
        return collection;
    }
}
