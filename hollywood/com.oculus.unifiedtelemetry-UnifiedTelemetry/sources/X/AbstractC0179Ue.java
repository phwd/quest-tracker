package X;

import com.google.common.collect.Multiset;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.Ue  reason: case insensitive filesystem */
public abstract class AbstractC0179Ue<E> extends AnonymousClass6w<Multiset.Entry<E>> {
    public abstract AnonymousClass34<E> A00();

    public final boolean contains(@NullableDecl Object obj) {
        if (!(obj instanceof AbstractC0181Ug)) {
            return false;
        }
        AbstractC0181Ug ug = (AbstractC0181Ug) obj;
        if (ug.A00() <= 0 || A00().A1c(ug.A01()) != ug.A00()) {
            return false;
        }
        return true;
    }

    public final boolean remove(Object obj) {
        if (obj instanceof AbstractC0181Ug) {
            AbstractC0181Ug ug = (AbstractC0181Ug) obj;
            E e = (E) ug.A01();
            int A00 = ug.A00();
            if (A00 != 0) {
                return A00().A4y(e, A00, 0);
            }
        }
        return false;
    }

    public final void clear() {
        A00().clear();
    }
}
