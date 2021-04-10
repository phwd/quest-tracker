package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.errorprone.annotations.ForOverride;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.03t  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractRunnableC002103t<I, O, F, T> extends AnonymousClass063<O> implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.AbstractTransformFuture";
    @NullableDecl
    public ListenableFuture<? extends I> A00;
    @NullableDecl
    public F A01;

    @NullableDecl
    @ForOverride
    public abstract T A00(F f, @NullableDecl I i) throws Exception;

    @ForOverride
    public abstract void A01(@NullableDecl T t);

    @Override // X.AnonymousClass0BX
    public final void afterDone() {
        maybePropagateCancellationTo(this.A00);
        this.A00 = null;
        this.A01 = null;
    }

    @Override // X.AnonymousClass0BX
    public final String pendingToString() {
        String str;
        ListenableFuture<? extends I> listenableFuture = this.A00;
        F f = this.A01;
        String pendingToString = super.pendingToString();
        if (listenableFuture != null) {
            str = "inputFuture=[" + listenableFuture + "], ";
        } else {
            str = "";
        }
        if (f != null) {
            return str + "function=[" + ((Object) f) + "]";
        } else if (pendingToString != null) {
            return AnonymousClass006.A05(str, pendingToString);
        } else {
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: X.03t<I, O, F, T> */
    /* JADX WARN: Multi-variable type inference failed */
    public final void run() {
        Throwable e;
        ListenableFuture<? extends I> listenableFuture = this.A00;
        F f = this.A01;
        boolean isCancelled = isCancelled();
        boolean z = true;
        boolean z2 = false;
        if (listenableFuture == null) {
            z2 = true;
        }
        boolean z3 = isCancelled | z2;
        if (f != null) {
            z = false;
        }
        if (!z3 && !z) {
            this.A00 = null;
            try {
                Preconditions.checkState(listenableFuture.isDone(), "Future was expected to be done: %s", listenableFuture);
                try {
                    Object A002 = A00(f, AnonymousClass00Y.A00(listenableFuture));
                    this.A01 = null;
                    A01(A002);
                } catch (Throwable th) {
                    this.A01 = null;
                    throw th;
                }
            } catch (CancellationException unused) {
                cancel(false);
            } catch (ExecutionException e2) {
                e = e2.getCause();
                setException(e);
            } catch (Error | RuntimeException e3) {
                e = e3;
                setException(e);
            }
        }
    }

    public AbstractRunnableC002103t(ListenableFuture<? extends I> listenableFuture, F f) {
        this.A00 = listenableFuture;
        if (f != null) {
            this.A01 = f;
            return;
        }
        throw null;
    }
}
