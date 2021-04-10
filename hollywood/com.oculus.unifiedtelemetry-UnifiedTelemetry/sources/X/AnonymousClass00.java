package X;

import com.google.common.base.Function;
import com.google.common.util.concurrent.ListenableFuture;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.00  reason: invalid class name */
public final class AnonymousClass00<I, O> extends AnonymousClass0D<I, O, Function<? super I, ? extends O>, O> {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.AbstractTransformFuture$TransformFuture";

    @Override // X.AnonymousClass0D
    @NullableDecl
    public final Object A00(Object obj, @NullableDecl Object obj2) throws Exception {
        return ((Function) obj).apply(obj2);
    }

    public AnonymousClass00(ListenableFuture<? extends I> listenableFuture, Function<? super I, ? extends O> function) {
        super(listenableFuture, function);
    }

    @Override // X.AnonymousClass0D
    public final void A01(@NullableDecl O o) {
        set(o);
    }
}
