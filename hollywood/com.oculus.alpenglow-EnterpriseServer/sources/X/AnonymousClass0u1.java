package X;

import java.util.ConcurrentModificationException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0u1  reason: invalid class name */
public final class AnonymousClass0u1<T> {
    @NullableDecl
    public T A00;

    public final void A00(@NullableDecl T t, T t2) {
        if (this.A00 == t) {
            this.A00 = t2;
            return;
        }
        throw new ConcurrentModificationException();
    }
}
