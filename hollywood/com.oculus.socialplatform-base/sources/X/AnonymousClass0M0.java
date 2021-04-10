package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Executor;

@GwtCompatible
@CanIgnoreReturnValue
/* renamed from: X.0M0  reason: invalid class name */
public abstract class AnonymousClass0M0<V> extends AbstractFutureC01490ei<V> implements ListenableFuture<V> {
    /* renamed from: A02 */
    public abstract ListenableFuture<? extends V> A01();

    @Override // com.google.common.util.concurrent.ListenableFuture
    public final void addListener(Runnable runnable, Executor executor) {
        A01().addListener(runnable, executor);
    }
}
