package X;

import com.google.common.base.Function;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.Throwable;

/* renamed from: X.03  reason: invalid class name */
public final class AnonymousClass03<V, X extends Throwable> extends AnonymousClass0G<V, X, Function<? super X, ? extends V>, V> {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.AbstractCatchingFuture$CatchingFuture";

    public AnonymousClass03(ListenableFuture<? extends V> listenableFuture, Class<X> cls, Function<? super X, ? extends V> function) {
        super(listenableFuture, cls, function);
    }
}
