package X;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

public abstract class Bb extends AbstractFutureC1191us implements ListenableFuture {
    @Override // com.google.common.util.concurrent.ListenableFuture
    public final void addListener(Runnable runnable, Executor executor) {
        ((SE) this).A00.addListener(runnable, executor);
    }
}
