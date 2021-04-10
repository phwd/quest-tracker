package X;

import java.util.ConcurrentModificationException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0wc  reason: invalid class name and case insensitive filesystem */
public final class C05660wc<T> {
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
