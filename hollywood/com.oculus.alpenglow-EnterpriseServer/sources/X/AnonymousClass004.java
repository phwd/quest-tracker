package X;

import com.google.common.util.concurrent.ListenableFuture;
import java.lang.Throwable;

/* renamed from: X.004  reason: invalid class name */
public final class AnonymousClass004<V, X extends Throwable> extends AbstractRunnableC002403w<V, X, AnonymousClass0Fa<? super X, ? extends V>, ListenableFuture<? extends V>> {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.AbstractCatchingFuture$AsyncCatchingFuture";

    @Override // X.AbstractRunnableC002403w
    public final void A01(Object obj) {
        setFuture((ListenableFuture) obj);
    }

    @Override // X.AbstractRunnableC002403w
    public final /* bridge */ /* synthetic */ Object A00(Object obj, Throwable th) throws Exception {
        throw null;
    }

    public AnonymousClass004(ListenableFuture<? extends V> listenableFuture, Class<X> cls, AnonymousClass0Fa<? super X, ? extends V> r3) {
        super(listenableFuture, cls, r3);
    }
}
