package X;

import java.util.concurrent.Callable;

/* renamed from: X.0cn  reason: invalid class name and case insensitive filesystem */
public final class C03380cn extends AbstractRunnableC08660xD<V> {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.TrustedListenableFutureTask$TrustedFutureInterruptibleTask";
    public final Callable<V> callable;
    public final /* synthetic */ AnonymousClass009 this$0;

    public C03380cn(AnonymousClass009 r2, Callable<V> callable2) {
        this.this$0 = r2;
        if (callable2 != null) {
            this.callable = callable2;
            return;
        }
        throw null;
    }

    @Override // X.AbstractRunnableC08660xD
    public final V A00() throws Exception {
        return this.callable.call();
    }

    @Override // X.AbstractRunnableC08660xD
    public final String A01() {
        return this.callable.toString();
    }

    @Override // X.AbstractRunnableC08660xD
    public final void A02(V v, Throwable th) {
        if (th == null) {
            this.this$0.set(v);
        } else {
            this.this$0.setException(th);
        }
    }

    @Override // X.AbstractRunnableC08660xD
    public final boolean A03() {
        return this.this$0.isDone();
    }
}
