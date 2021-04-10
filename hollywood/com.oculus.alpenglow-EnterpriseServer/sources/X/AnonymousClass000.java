package X;

import com.google.common.base.Function;
import com.google.common.util.concurrent.ListenableFuture;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.000  reason: invalid class name */
public final class AnonymousClass000<I, O> extends AbstractRunnableC002103t<I, O, Function<? super I, ? extends O>, O> {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.AbstractTransformFuture$TransformFuture";

    @Override // X.AbstractRunnableC002103t
    @NullableDecl
    public final Object A00(Object obj, @NullableDecl Object obj2) throws Exception {
        return ((Function) obj).apply(obj2);
    }

    public AnonymousClass000(ListenableFuture<? extends I> listenableFuture, Function<? super I, ? extends O> function) {
        super(listenableFuture, function);
    }

    @Override // X.AbstractRunnableC002103t
    public final void A01(@NullableDecl O o) {
        set(o);
    }
}
