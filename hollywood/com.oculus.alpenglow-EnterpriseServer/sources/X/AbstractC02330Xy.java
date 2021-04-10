package X;

import com.google.common.collect.Multiset;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0Xy  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC02330Xy<E> extends AnonymousClass0tZ<Multiset.Entry<E>> {
    public abstract AnonymousClass0tC<E> A00();

    public final boolean contains(@NullableDecl Object obj) {
        if (!(obj instanceof AnonymousClass0Y0)) {
            return false;
        }
        AnonymousClass0Y0 r4 = (AnonymousClass0Y0) obj;
        if (r4.A00() <= 0 || A00().A1s(r4.A01()) != r4.A00()) {
            return false;
        }
        return true;
    }

    public final boolean remove(Object obj) {
        if (obj instanceof AnonymousClass0Y0) {
            AnonymousClass0Y0 r5 = (AnonymousClass0Y0) obj;
            E e = (E) r5.A01();
            int A00 = r5.A00();
            if (A00 != 0) {
                return A00().A7r(e, A00, 0);
            }
        }
        return false;
    }

    public final void clear() {
        A00().clear();
    }
}
