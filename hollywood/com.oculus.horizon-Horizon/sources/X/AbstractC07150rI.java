package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ReverseOrdering;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Comparator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.0rI  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC07150rI<T> implements Comparator<T> {
    @Override // java.util.Comparator
    @CanIgnoreReturnValue
    public abstract int compare(@NullableDecl T t, @NullableDecl T t2);

    @GwtCompatible(serializable = true)
    public <S extends T> AbstractC07150rI<S> A00() {
        return new ReverseOrdering(this);
    }
}
