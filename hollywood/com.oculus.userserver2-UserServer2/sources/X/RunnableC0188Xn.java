package X;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* renamed from: X.Xn  reason: case insensitive filesystem */
public final class RunnableC0188Xn<V> implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.Futures$CallbackListener";
    public final XV<? super V> A00;
    public final Future<V> A01;

    public final void run() {
        try {
            Future<V> future = this.A01;
            Preconditions.checkState(future.isDone(), "Future was expected to be done: %s", future);
            gM.A00(future);
        } catch (ExecutionException e) {
            e.getCause();
        } catch (Error | RuntimeException unused) {
            throw null;
        }
        throw null;
    }

    public RunnableC0188Xn(Future<V> future, XV<? super V> xv) {
        this.A01 = future;
        this.A00 = xv;
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
