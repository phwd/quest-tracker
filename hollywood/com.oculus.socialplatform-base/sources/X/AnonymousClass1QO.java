package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.Serializable;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1QO  reason: invalid class name */
public abstract class AnonymousClass1QO<T> implements Serializable {
    public static final long serialVersionUID = 0;

    public abstract boolean equals(@Nullable Object obj);

    public abstract int hashCode();

    public abstract String toString();

    public static <T> AnonymousClass1QO<T> A00(T t) {
        if (t != null) {
            return new AnonymousClass1QL(t);
        }
        throw null;
    }

    public final T A01() {
        if (this instanceof AnonymousClass1QL) {
            return ((AnonymousClass1QL) this).reference;
        }
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    public final boolean A02() {
        if (!(this instanceof AnonymousClass1QL)) {
            return false;
        }
        return true;
    }
}
