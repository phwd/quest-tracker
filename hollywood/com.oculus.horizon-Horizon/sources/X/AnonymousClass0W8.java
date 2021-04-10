package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.Serializable;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0W8  reason: invalid class name */
public abstract class AnonymousClass0W8<T> implements Serializable {
    public static final long serialVersionUID = 0;

    public abstract T A01();

    public abstract boolean A02();

    public abstract boolean equals(@Nullable Object obj);

    public abstract int hashCode();

    public abstract String toString();

    public static <T> AnonymousClass0W8<T> A00(T t) {
        if (t != null) {
            return new AnonymousClass0nZ(t);
        }
        throw null;
    }
}
