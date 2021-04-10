package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Executor;

@GwtCompatible
@CanIgnoreReturnValue
/* renamed from: X.0Bg  reason: invalid class name */
public abstract class AnonymousClass0Bg<V> extends AbstractFutureC03410cs<V> implements ListenableFuture<V> {
    /* renamed from: A02 */
    public abstract ListenableFuture<? extends V> A01();

    @Override // com.google.common.util.concurrent.ListenableFuture
    public final void addListener(Runnable runnable, Executor executor) {
        A01().addListener(runnable, executor);
    }
}
