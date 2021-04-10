package X;

import com.google.common.util.concurrent.ListenableFuture;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.02  reason: invalid class name */
public final class AnonymousClass02<I, O> extends AnonymousClass0D<I, O, B4<? super I, ? extends O>, ListenableFuture<? extends O>> {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.AbstractTransformFuture$AsyncTransformFuture";

    @Override // X.AnonymousClass0D
    public final void A01(Object obj) {
        setFuture((ListenableFuture) obj);
    }

    public AnonymousClass02(ListenableFuture<? extends I> listenableFuture, B4<? super I, ? extends O> b4) {
        super(listenableFuture, b4);
    }

    @Override // X.AnonymousClass0D
    public final /* bridge */ /* synthetic */ Object A00(Object obj, @NullableDecl Object obj2) throws Exception {
        throw null;
    }
}
