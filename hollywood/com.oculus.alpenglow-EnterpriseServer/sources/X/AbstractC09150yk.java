package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.Serializable;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0yk  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC09150yk<T> implements Serializable {
    public static final long serialVersionUID = 0;

    public abstract boolean equals(@Nullable Object obj);

    public abstract int hashCode();

    public abstract String toString();

    public static <T> AbstractC09150yk<T> A00(T t) {
        if (t != null) {
            return new AnonymousClass0yR(t);
        }
        throw null;
    }

    public final T A01() {
        if (this instanceof AnonymousClass0yR) {
            return ((AnonymousClass0yR) this).reference;
        }
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    public final boolean A02() {
        if (!(this instanceof AnonymousClass0yR)) {
            return false;
        }
        return true;
    }
}
