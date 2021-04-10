package X;

import com.google.common.util.concurrent.ListenableFuture;
import java.lang.Throwable;

/* renamed from: X.04  reason: invalid class name */
public final class AnonymousClass04<V, X extends Throwable> extends AnonymousClass0E<V, X, B4<? super X, ? extends V>, ListenableFuture<? extends V>> {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.AbstractCatchingFuture$AsyncCatchingFuture";

    @Override // X.AnonymousClass0E
    public final void A01(Object obj) {
        setFuture((ListenableFuture) obj);
    }

    @Override // X.AnonymousClass0E
    public final /* bridge */ /* synthetic */ Object A00(Object obj, Throwable th) throws Exception {
        throw null;
    }

    public AnonymousClass04(ListenableFuture<? extends V> listenableFuture, Class<X> cls, B4<? super X, ? extends V> b4) {
        super(listenableFuture, cls, b4);
    }
}
