package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Executor;

@GwtCompatible
@CanIgnoreReturnValue
/* renamed from: X.Ax  reason: case insensitive filesystem */
public abstract class AbstractC0059Ax<V> extends UH<V> implements ListenableFuture<V> {
    /* renamed from: A02 */
    public abstract ListenableFuture<? extends V> A01();

    @Override // com.google.common.util.concurrent.ListenableFuture
    public final void addListener(Runnable runnable, Executor executor) {
        A01().addListener(runnable, executor);
    }
}
