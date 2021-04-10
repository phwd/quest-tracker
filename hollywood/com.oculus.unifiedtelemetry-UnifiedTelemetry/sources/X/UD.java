package X;

import java.util.concurrent.Callable;

public final class UD extends Bd<V> {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.TrustedListenableFutureTask$TrustedFutureInterruptibleTask";
    public final Callable<V> callable;
    public final /* synthetic */ AnonymousClass09 this$0;

    public UD(AnonymousClass09 r2, Callable<V> callable2) {
        this.this$0 = r2;
        if (callable2 != null) {
            this.callable = callable2;
            return;
        }
        throw null;
    }

    @Override // X.Bd
    public final V A00() throws Exception {
        return this.callable.call();
    }

    @Override // X.Bd
    public final String A01() {
        return this.callable.toString();
    }

    @Override // X.Bd
    public final void A02(V v, Throwable th) {
        if (th == null) {
            this.this$0.set(v);
        } else {
            this.this$0.setException(th);
        }
    }

    @Override // X.Bd
    public final boolean A03() {
        return this.this$0.isDone();
    }
}
