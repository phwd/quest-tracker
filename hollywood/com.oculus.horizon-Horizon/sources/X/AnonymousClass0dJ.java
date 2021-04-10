package X;

import com.google.common.collect.Multiset;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0dJ  reason: invalid class name */
public abstract class AnonymousClass0dJ<E> extends AnonymousClass0rW<Multiset.Entry<E>> {
    public abstract AnonymousClass0r9<E> A00();

    public final boolean contains(@NullableDecl Object obj) {
        if (!(obj instanceof AnonymousClass0dN)) {
            return false;
        }
        AnonymousClass0dN r4 = (AnonymousClass0dN) obj;
        if (r4.A00() <= 0 || A00().A1v(r4.A01()) != r4.A00()) {
            return false;
        }
        return true;
    }

    public final boolean remove(Object obj) {
        if (obj instanceof AnonymousClass0dN) {
            AnonymousClass0dN r5 = (AnonymousClass0dN) obj;
            E e = (E) r5.A01();
            int A00 = r5.A00();
            if (A00 != 0) {
                return A00().A8e(e, A00, 0);
            }
        }
        return false;
    }

    public final void clear() {
        A00().clear();
    }
}
