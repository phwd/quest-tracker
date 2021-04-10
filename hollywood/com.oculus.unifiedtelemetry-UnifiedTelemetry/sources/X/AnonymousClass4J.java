package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ReverseOrdering;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Comparator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.4J  reason: invalid class name */
public abstract class AnonymousClass4J<T> implements Comparator<T> {
    @Override // java.util.Comparator
    @CanIgnoreReturnValue
    public abstract int compare(@NullableDecl T t, @NullableDecl T t2);

    @GwtCompatible(serializable = true)
    public <S extends T> AnonymousClass4J<S> A00() {
        return new ReverseOrdering(this);
    }
}
