package X;

import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* renamed from: X.11s  reason: invalid class name */
public final class AnonymousClass11s<V> implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.Futures$CallbackListener";
    public final AbstractC057411o<? super V> A00;
    public final Future<V> A01;

    public final void run() {
        try {
            Future<V> future = this.A01;
            Preconditions.checkState(future.isDone(), "Future was expected to be done: %s", future);
            this.A00.onSuccess(AnonymousClass12r.A00(future));
        } catch (ExecutionException e) {
            this.A00.onFailure(e.getCause());
        } catch (Error | RuntimeException e2) {
            this.A00.onFailure(e2);
        }
    }

    public AnonymousClass11s(Future<V> future, AbstractC057411o<? super V> r2) {
        this.A01 = future;
        this.A00 = r2;
    }

    public final String toString() {
        MoreObjects$ToStringHelper moreObjects$ToStringHelper = new MoreObjects$ToStringHelper(getClass().getSimpleName());
        AbstractC057411o<? super V> r2 = this.A00;
        MoreObjects$ToStringHelper.ValueHolder valueHolder = new MoreObjects$ToStringHelper.ValueHolder();
        moreObjects$ToStringHelper.holderTail.next = valueHolder;
        moreObjects$ToStringHelper.holderTail = valueHolder;
        valueHolder.value = r2;
        return moreObjects$ToStringHelper.toString();
    }
}
