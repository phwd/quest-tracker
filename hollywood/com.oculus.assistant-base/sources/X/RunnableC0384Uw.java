package X;

import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* renamed from: X.Uw  reason: case insensitive filesystem */
public final class RunnableC0384Uw implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.Futures$CallbackListener";
    public final AbstractC0383Uv A00;
    public final Future A01;

    public final void run() {
        try {
            Future future = this.A01;
            Preconditions.checkState(future.isDone(), "Future was expected to be done: %s", future);
            this.A00.A4N(V8.A00(future));
        } catch (ExecutionException e) {
            this.A00.A49(e.getCause());
        } catch (Error | RuntimeException e2) {
            this.A00.A49(e2);
        }
    }

    public RunnableC0384Uw(Future future, AbstractC0383Uv uv) {
        this.A01 = future;
        this.A00 = uv;
    }

    public final String toString() {
        MoreObjects$ToStringHelper moreObjects$ToStringHelper = new MoreObjects$ToStringHelper(getClass().getSimpleName());
        AbstractC0383Uv uv = this.A00;
        MoreObjects$ToStringHelper.ValueHolder valueHolder = new MoreObjects$ToStringHelper.ValueHolder();
        moreObjects$ToStringHelper.holderTail.next = valueHolder;
        moreObjects$ToStringHelper.holderTail = valueHolder;
        valueHolder.value = uv;
        return moreObjects$ToStringHelper.toString();
    }
}
