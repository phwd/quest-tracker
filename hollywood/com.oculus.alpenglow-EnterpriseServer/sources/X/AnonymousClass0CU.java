package X;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* renamed from: X.0CU  reason: invalid class name */
public final class AnonymousClass0CU<V> implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.Futures$CallbackListener";
    public final AnonymousClass0Cg<? super V> A00;
    public final Future<V> A01;

    public final void run() {
        try {
            Future<V> future = this.A01;
            Preconditions.checkState(future.isDone(), "Future was expected to be done: %s", future);
            this.A00.onSuccess(AnonymousClass00Y.A00(future));
        } catch (ExecutionException e) {
            this.A00.onFailure(e.getCause());
        } catch (Error | RuntimeException e2) {
            this.A00.onFailure(e2);
        }
    }

    public AnonymousClass0CU(Future<V> future, AnonymousClass0Cg<? super V> r2) {
        this.A01 = future;
        this.A00 = r2;
    }

    public final String toString() {
        MoreObjects.ToStringHelper toStringHelper = new MoreObjects.ToStringHelper(getClass().getSimpleName());
        AnonymousClass0Cg<? super V> r2 = this.A00;
        MoreObjects.ToStringHelper.ValueHolder valueHolder = new MoreObjects.ToStringHelper.ValueHolder();
        toStringHelper.holderTail.next = valueHolder;
        toStringHelper.holderTail = valueHolder;
        valueHolder.value = r2;
        return toStringHelper.toString();
    }
}
