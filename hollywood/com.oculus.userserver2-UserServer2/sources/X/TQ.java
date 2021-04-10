package X;

import java.util.ConcurrentModificationException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class TQ<T> {
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
