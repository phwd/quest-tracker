package X;

import java.util.concurrent.Callable;

/* renamed from: X.0ee  reason: invalid class name and case insensitive filesystem */
public final class C01460ee extends AnonymousClass122<V> {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.TrustedListenableFutureTask$TrustedFutureInterruptibleTask";
    public final Callable<V> callable;
    public final /* synthetic */ AnonymousClass00C this$0;

    public C01460ee(AnonymousClass00C r2, Callable<V> callable2) {
        this.this$0 = r2;
        if (callable2 != null) {
            this.callable = callable2;
            return;
        }
        throw null;
    }

    @Override // X.AnonymousClass122
    public final V A00() throws Exception {
        return this.callable.call();
    }

    @Override // X.AnonymousClass122
    public final String A01() {
        return this.callable.toString();
    }

    @Override // X.AnonymousClass122
    public final void A02(V v, Throwable th) {
        if (th == null) {
            this.this$0.set(v);
        } else {
            this.this$0.setException(th);
        }
    }

    @Override // X.AnonymousClass122
    public final boolean A03() {
        return this.this$0.isDone();
    }
}
