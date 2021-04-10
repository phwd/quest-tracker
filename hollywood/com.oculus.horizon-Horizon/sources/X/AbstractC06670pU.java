package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Absent;
import com.google.common.base.Optional;

@GwtCompatible(emulated = true)
/* renamed from: X.0pU  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC06670pU<E> implements Iterable<E> {
    public final Optional<Iterable<E>> A00 = Absent.INSTANCE;

    public final String toString() {
        return C06900qE.A00(this.A00.or(this));
    }
}
