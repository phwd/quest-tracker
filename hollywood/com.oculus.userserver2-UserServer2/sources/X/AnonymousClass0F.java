package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.0F  reason: invalid class name */
public abstract class AnonymousClass0F<I, O, F, T> extends AbstractC00040n<O> implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.AbstractTransformFuture";
    @NullableDecl
    public ListenableFuture<? extends I> A00;
    @NullableDecl
    public F A01;

    @Override // X.AnonymousClass6f
    public final void afterDone() {
        maybePropagateCancellationTo(this.A00);
        this.A00 = null;
        this.A01 = null;
    }

    @Override // X.AnonymousClass6f
    public final String pendingToString() {
        String str;
        ListenableFuture<? extends I> listenableFuture = this.A00;
        F f = this.A01;
        String pendingToString = super.pendingToString();
        if (listenableFuture != null) {
            StringBuilder sb = new StringBuilder("inputFuture=[");
            sb.append(listenableFuture);
            sb.append("], ");
            str = sb.toString();
        } else {
            str = "";
        }
        if (f != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append("function=[");
            sb2.append((Object) f);
            sb2.append("]");
            return sb2.toString();
        } else if (pendingToString != null) {
            return AnonymousClass06.A03(str, pendingToString);
        } else {
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v1, resolved type: F */
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
                Object A002 = gM.A00(listenableFuture);
                try {
                    boolean z4 = this instanceof AnonymousClass00;
                    if (!z4) {
                        throw null;
                    }
                    Object apply = f.apply(A002);
                    if (!z4) {
                        setFuture((ListenableFuture) apply);
                    } else {
                        set(apply);
                    }
                } catch (Throwable th) {
                    setException(th);
                } finally {
                    this.A01 = null;
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

    public AnonymousClass0F(ListenableFuture<? extends I> listenableFuture, F f) {
        this.A00 = listenableFuture;
        if (f != null) {
            this.A01 = f;
            return;
        }
        throw null;
    }
}
