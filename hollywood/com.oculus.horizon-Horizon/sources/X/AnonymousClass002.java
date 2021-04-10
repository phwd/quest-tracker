package X;

import com.google.common.util.concurrent.ListenableFuture;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.002  reason: invalid class name */
public final class AnonymousClass002<I, O> extends AnonymousClass00D<I, O, AbstractC08460wc<? super I, ? extends O>, ListenableFuture<? extends O>> {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.AbstractTransformFuture$AsyncTransformFuture";

    @Override // X.AnonymousClass00D
    public final void A01(Object obj) {
        setFuture((ListenableFuture) obj);
    }

    public AnonymousClass002(ListenableFuture<? extends I> listenableFuture, AbstractC08460wc<? super I, ? extends O> r2) {
        super(listenableFuture, r2);
    }

    @Override // X.AnonymousClass00D
    public final /* bridge */ /* synthetic */ Object A00(Object obj, @NullableDecl Object obj2) throws Exception {
        throw null;
    }
}
