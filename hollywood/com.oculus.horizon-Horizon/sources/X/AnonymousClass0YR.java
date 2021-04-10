package X;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

/* renamed from: X.0YR  reason: invalid class name */
public final class AnonymousClass0YR<E> extends AnonymousClass0yl<Collection<E>> {
    public final AnonymousClass0yl<E> A00;
    public final AbstractC09010zC<? extends Collection<E>> A01;

    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r4, Object obj) throws IOException {
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

    public AnonymousClass0YR(C08780ya r2, Type type, AnonymousClass0yl<E> r4, AbstractC09010zC<? extends Collection<E>> r5) {
        this.A00 = new AnonymousClass0VA(r2, r4, type);
        this.A01 = r5;
    }

    @Override // X.AnonymousClass0yl
    public final Object A02(C09120zR r3) throws IOException {
        if (r3.A0G() == AnonymousClass007.A0I) {
            r3.A0P();
            return null;
        }
        Collection collection = (Collection) this.A01.A1q();
        r3.A0L();
        while (r3.A0R()) {
            collection.add(this.A00.A02(r3));
        }
        r3.A0N();
        return collection;
    }
}
