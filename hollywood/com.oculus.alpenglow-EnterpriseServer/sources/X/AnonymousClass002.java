package X;

import com.google.common.util.concurrent.ListenableFuture;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.002  reason: invalid class name */
public final class AnonymousClass002<I, O> extends AbstractRunnableC002103t<I, O, AnonymousClass0Fa<? super I, ? extends O>, ListenableFuture<? extends O>> {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.AbstractTransformFuture$AsyncTransformFuture";

    @Override // X.AbstractRunnableC002103t
    public final void A01(Object obj) {
        setFuture((ListenableFuture) obj);
    }

    public AnonymousClass002(ListenableFuture<? extends I> listenableFuture, AnonymousClass0Fa<? super I, ? extends O> r2) {
        super(listenableFuture, r2);
    }

    @Override // X.AbstractRunnableC002103t
    public final /* bridge */ /* synthetic */ Object A00(Object obj, @NullableDecl Object obj2) throws Exception {
        throw null;
    }
}
