package X;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* renamed from: X.0x3  reason: invalid class name and case insensitive filesystem */
public final class RunnableC08640x3<V> implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.Futures$CallbackListener";
    public final AbstractC08630wz<? super V> A00;
    public final Future<V> A01;

    public final void run() {
        try {
            Future<V> future = this.A01;
            Preconditions.checkState(future.isDone(), "Future was expected to be done: %s", future);
            C08740y3.A00(future);
        } catch (ExecutionException e) {
            e.getCause();
        } catch (Error | RuntimeException unused) {
            throw null;
        }
        throw null;
    }

    public RunnableC08640x3(Future<V> future, AbstractC08630wz<? super V> r2) {
        this.A01 = future;
        this.A00 = r2;
    }

    public final String toString() {
        MoreObjects.ToStringHelper toStringHelper = new MoreObjects.ToStringHelper(getClass().getSimpleName());
        MoreObjects.ToStringHelper.ValueHolder valueHolder = new MoreObjects.ToStringHelper.ValueHolder();
        toStringHelper.holderTail.next = valueHolder;
        toStringHelper.holderTail = valueHolder;
        valueHolder.value = null;
        return toStringHelper.toString();
    }
}
