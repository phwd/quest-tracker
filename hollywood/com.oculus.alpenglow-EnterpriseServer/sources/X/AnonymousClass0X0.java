package X;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

/* renamed from: X.0X0  reason: invalid class name */
public final class AnonymousClass0X0<E> extends AnonymousClass0Bd<Collection<E>> {
    public final AnonymousClass0Bd<E> A00;
    public final AbstractC01170Du<? extends Collection<E>> A01;

    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r4, Object obj) throws IOException {
        Collection<E> collection = (Collection) obj;
        if (collection == null) {
            r4.A0A();
            return;
        }
        r4.A06();
        for (E e : collection) {
            this.A00.A03(r4, e);
        }
        r4.A08();
    }

    public AnonymousClass0X0(AnonymousClass08D r2, Type type, AnonymousClass0Bd<E> r4, AbstractC01170Du<? extends Collection<E>> r5) {
        this.A00 = new AnonymousClass0Wf(r2, r4, type);
        this.A01 = r5;
    }

    @Override // X.AnonymousClass0Bd
    public final Object A02(AnonymousClass0Fo r3) throws IOException {
        if (r3.A0D() == AnonymousClass007.A0I) {
            r3.A0L();
            return null;
        }
        Collection collection = (Collection) this.A01.A1o();
        r3.A0H();
        while (r3.A0N()) {
            collection.add(this.A00.A02(r3));
        }
        r3.A0J();
        return collection;
    }
}
