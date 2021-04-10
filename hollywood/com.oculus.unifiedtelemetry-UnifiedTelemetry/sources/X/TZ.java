package X;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

public final class TZ<E> extends AbstractC0131Ob<Collection<E>> {
    public final AbstractC0131Ob<E> A00;
    public final VE<? extends Collection<E>> A01;

    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, Object obj) throws IOException {
        Collection<E> collection = (Collection) obj;
        if (collection == null) {
            mmVar.A0B();
            return;
        }
        mmVar.A07();
        for (E e : collection) {
            this.A00.A03(mmVar, e);
        }
        mmVar.A09();
    }

    public TZ(HY hy, Type type, AbstractC0131Ob<E> ob, VE<? extends Collection<E>> ve) {
        this.A00 = new C0162Sx(hy, ob, type);
        this.A01 = ve;
    }

    @Override // X.AbstractC0131Ob
    public final Object A02(lk lkVar) throws IOException {
        if (lkVar.A0G() == AnonymousClass07.A08) {
            lkVar.A0P();
            return null;
        }
        Collection collection = (Collection) this.A01.A1b();
        lkVar.A0L();
        while (lkVar.A0R()) {
            collection.add(this.A00.A02(lkVar));
        }
        lkVar.A0N();
        return collection;
    }
}
