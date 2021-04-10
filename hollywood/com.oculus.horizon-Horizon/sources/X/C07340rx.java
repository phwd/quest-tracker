package X;

import java.util.ConcurrentModificationException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0rx  reason: invalid class name and case insensitive filesystem */
public final class C07340rx<T> {
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
