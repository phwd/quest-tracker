package X;

import com.google.common.base.Function;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.Throwable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.003  reason: invalid class name */
public final class AnonymousClass003<V, X extends Throwable> extends AbstractRunnableC002403w<V, X, Function<? super X, ? extends V>, V> {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.AbstractCatchingFuture$CatchingFuture";

    @Override // X.AbstractRunnableC002403w
    @NullableDecl
    public final Object A00(Object obj, Throwable th) throws Exception {
        return ((Function) obj).apply(th);
    }

    @Override // X.AbstractRunnableC002403w
    public final void A01(@NullableDecl V v) {
        set(v);
    }

    public AnonymousClass003(ListenableFuture<? extends V> listenableFuture, Class<X> cls, Function<? super X, ? extends V> function) {
        super(listenableFuture, cls, function);
    }
}
