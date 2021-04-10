package X;

import com.google.common.collect.Multiset;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0f0  reason: invalid class name */
public abstract class AnonymousClass0f0<E> extends AbstractC05580wA<Multiset.Entry<E>> {
    public abstract AbstractC05490vp<E> A00();

    public final boolean contains(@NullableDecl Object obj) {
        if (!(obj instanceof AnonymousClass0f2)) {
            return false;
        }
        AnonymousClass0f2 r4 = (AnonymousClass0f2) obj;
        if (r4.A00() <= 0 || A00().A2J(r4.A01()) != r4.A00()) {
            return false;
        }
        return true;
    }

    public final boolean remove(Object obj) {
        if (obj instanceof AnonymousClass0f2) {
            AnonymousClass0f2 r5 = (AnonymousClass0f2) obj;
            E e = (E) r5.A01();
            int A00 = r5.A00();
            if (A00 != 0) {
                return A00().A9o(e, A00, 0);
            }
        }
        return false;
    }

    public final void clear() {
        A00().clear();
    }
}
