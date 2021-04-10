package X;

import com.google.common.base.Function;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.Throwable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.03  reason: invalid class name */
public final class AnonymousClass03<V, X extends Throwable> extends AnonymousClass0E<V, X, Function<? super X, ? extends V>, V> {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.AbstractCatchingFuture$CatchingFuture";

    @Override // X.AnonymousClass0E
    @NullableDecl
    public final Object A00(Object obj, Throwable th) throws Exception {
        return ((Function) obj).apply(th);
    }

    @Override // X.AnonymousClass0E
    public final void A01(@NullableDecl V v) {
        set(v);
    }

    public AnonymousClass03(ListenableFuture<? extends V> listenableFuture, Class<X> cls, Function<? super X, ? extends V> function) {
        super(listenableFuture, cls, function);
    }
}
