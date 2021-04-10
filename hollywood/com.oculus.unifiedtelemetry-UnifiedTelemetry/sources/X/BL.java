package X;

import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public final class BL<V> implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.Futures$CallbackListener";
    public final B6<? super V> A00;
    public final Future<V> A01;

    public final void run() {
        try {
            Future<V> future = this.A01;
            Preconditions.checkState(future.isDone(), "Future was expected to be done: %s", future);
            DE.A00(future);
        } catch (ExecutionException e) {
            e.getCause();
        } catch (Error | RuntimeException unused) {
            throw null;
        }
        throw null;
    }

    public BL(Future<V> future, B6<? super V> b6) {
        this.A01 = future;
        this.A00 = b6;
    }

    public final String toString() {
        MoreObjects$ToStringHelper moreObjects$ToStringHelper = new MoreObjects$ToStringHelper(getClass().getSimpleName());
        MoreObjects$ToStringHelper.ValueHolder valueHolder = new MoreObjects$ToStringHelper.ValueHolder();
        moreObjects$ToStringHelper.holderTail.next = valueHolder;
        moreObjects$ToStringHelper.holderTail = valueHolder;
        valueHolder.value = null;
        return moreObjects$ToStringHelper.toString();
    }
}
