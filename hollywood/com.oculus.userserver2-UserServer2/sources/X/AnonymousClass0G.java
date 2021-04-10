package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.Throwable;
import java.util.concurrent.ExecutionException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.0G  reason: invalid class name */
public abstract class AnonymousClass0G<V, X extends Throwable, F, T> extends AbstractC00040n<V> implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.AbstractCatchingFuture";
    @NullableDecl
    public ListenableFuture<? extends V> A00;
    @NullableDecl
    public Class<X> A01;
    @NullableDecl
    public F A02;

    @Override // X.AnonymousClass6f
    public final void afterDone() {
        maybePropagateCancellationTo(this.A00);
        this.A00 = null;
        this.A01 = null;
        this.A02 = null;
    }

    @Override // X.AnonymousClass6f
    public final String pendingToString() {
        String str;
        ListenableFuture<? extends V> listenableFuture = this.A00;
        Class<X> cls = this.A01;
        F f = this.A02;
        String pendingToString = super.pendingToString();
        if (listenableFuture != null) {
            StringBuilder sb = new StringBuilder("inputFuture=[");
            sb.append(listenableFuture);
            sb.append("], ");
            str = sb.toString();
        } else {
            str = "";
        }
        if (cls != null && f != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append("exceptionType=[");
            sb2.append(cls);
            sb2.append("], fallback=[");
            sb2.append((Object) f);
            sb2.append("]");
            return sb2.toString();
        } else if (pendingToString != null) {
            return AnonymousClass06.A03(str, pendingToString);
        } else {
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r7v0, resolved type: X.0G<V, X extends java.lang.Throwable, F, T> */
    /* JADX DEBUG: Multi-variable search result rejected for r4v1, resolved type: F */
    /* JADX WARN: Multi-variable type inference failed */
    public final void run() {
        Object obj;
        Throwable th;
        ListenableFuture<? extends V> listenableFuture = this.A00;
        Class<X> cls = this.A01;
        F f = this.A02;
        boolean z = true;
        boolean z2 = false;
        if (listenableFuture == null) {
            z2 = true;
        }
        boolean z3 = false;
        if (cls == null) {
            z3 = true;
        }
        boolean z4 = z2 | z3;
        if (f != null) {
            z = false;
        }
        if (!(z | z4) && !isCancelled()) {
            this.A00 = null;
            try {
                Preconditions.checkState(listenableFuture.isDone(), "Future was expected to be done: %s", listenableFuture);
                obj = gM.A00(listenableFuture);
            } catch (ExecutionException e) {
                th = e.getCause();
                if (th == null) {
                    throw null;
                }
            } catch (Throwable th2) {
                th = th2;
            }
            set(obj);
        }
        return;
        if (!cls.isInstance(th)) {
            setException(th);
            return;
        }
        try {
            boolean z5 = this instanceof AnonymousClass03;
            if (!z5) {
                throw null;
            }
            obj = f.apply(th);
            if (!z5) {
                setFuture((ListenableFuture) obj);
                return;
            }
            set(obj);
        } catch (Throwable th3) {
            setException(th3);
        } finally {
            this.A01 = null;
            this.A02 = null;
        }
    }

    public AnonymousClass0G(ListenableFuture<? extends V> listenableFuture, Class<X> cls, F f) {
        this.A00 = listenableFuture;
        if (cls != null) {
            this.A01 = cls;
            if (f != null) {
                this.A02 = f;
                return;
            }
        }
        throw null;
    }
}
