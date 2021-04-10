package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.errorprone.annotations.ForOverride;
import java.lang.Throwable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.03w  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractRunnableC002403w<V, X extends Throwable, F, T> extends AnonymousClass063<V> implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.AbstractCatchingFuture";
    @NullableDecl
    public ListenableFuture<? extends V> A00;
    @NullableDecl
    public Class<X> A01;
    @NullableDecl
    public F A02;

    @NullableDecl
    @ForOverride
    public abstract T A00(F f, X x) throws Exception;

    @ForOverride
    public abstract void A01(@NullableDecl T t);

    @Override // X.AnonymousClass0BX
    public final void afterDone() {
        maybePropagateCancellationTo(this.A00);
        this.A00 = null;
        this.A01 = null;
        this.A02 = null;
    }

    @Override // X.AnonymousClass0BX
    public final String pendingToString() {
        String str;
        ListenableFuture<? extends V> listenableFuture = this.A00;
        Class<X> cls = this.A01;
        F f = this.A02;
        String pendingToString = super.pendingToString();
        if (listenableFuture != null) {
            str = "inputFuture=[" + listenableFuture + "], ";
        } else {
            str = "";
        }
        if (cls != null && f != null) {
            return str + "exceptionType=[" + cls + "], fallback=[" + ((Object) f) + "]";
        } else if (pendingToString != null) {
            return AnonymousClass006.A05(str, pendingToString);
        } else {
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: X.03w<V, X extends java.lang.Throwable, F, T> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0042 A[SYNTHETIC, Splitter:B:25:0x0042] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r6 = this;
            com.google.common.util.concurrent.ListenableFuture<? extends V> r5 = r6.A00
            java.lang.Class<X extends java.lang.Throwable> r4 = r6.A01
            F r3 = r6.A02
            r2 = 1
            r1 = 0
            if (r5 != 0) goto L_0x000b
            r1 = 1
        L_0x000b:
            r0 = 0
            if (r4 != 0) goto L_0x000f
            r0 = 1
        L_0x000f:
            r1 = r1 | r0
            if (r3 == 0) goto L_0x0013
            r2 = 0
        L_0x0013:
            r2 = r2 | r1
            boolean r0 = r6.isCancelled()
            r2 = r2 | r0
            if (r2 != 0) goto L_0x005d
            r2 = 0
            r6.A00 = r2
            boolean r1 = r5.isDone()     // Catch:{ ExecutionException -> 0x002f, all -> 0x0037 }
            java.lang.String r0 = "Future was expected to be done: %s"
            com.google.common.base.Preconditions.checkState(r1, r0, r5)     // Catch:{ ExecutionException -> 0x002f, all -> 0x0037 }
            java.lang.Object r0 = X.AnonymousClass00Y.A00(r5)     // Catch:{ ExecutionException -> 0x002f, all -> 0x0037 }
            r6.set(r0)
            return
        L_0x002f:
            r0 = move-exception
            java.lang.Throwable r1 = r0.getCause()
            if (r1 != 0) goto L_0x0038
            throw r2
        L_0x0037:
            r1 = move-exception
        L_0x0038:
            boolean r0 = r4.isInstance(r1)
            if (r0 != 0) goto L_0x0042
            r6.setException(r1)
            return
        L_0x0042:
            java.lang.Object r0 = r6.A00(r3, r1)     // Catch:{ all -> 0x004e }
            r6.A01 = r2
            r6.A02 = r2
            r6.A01(r0)
            return
        L_0x004e:
            r0 = move-exception
            r6.setException(r0)     // Catch:{ all -> 0x0057 }
            r6.A01 = r2
            r6.A02 = r2
            return
        L_0x0057:
            r0 = move-exception
            r6.A01 = r2
            r6.A02 = r2
            throw r0
        L_0x005d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractRunnableC002403w.run():void");
    }

    public AbstractRunnableC002403w(ListenableFuture<? extends V> listenableFuture, Class<X> cls, F f) {
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
