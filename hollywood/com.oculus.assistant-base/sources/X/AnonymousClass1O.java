package X;

import com.facebook.assistant.oacr.OacrConstants;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* renamed from: X.1O  reason: invalid class name */
public abstract class AnonymousClass1O extends AbstractC00141v implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.AbstractTransformFuture";
    public ListenableFuture A00;
    public Object A01;

    public static ListenableFuture A00(ListenableFuture listenableFuture, Function function, Executor executor) {
        if (function != null) {
            C00110v r1 = new C00110v(listenableFuture, function);
            if (executor != null) {
                if (executor != V3.INSTANCE) {
                    executor = new V2(executor, r1);
                }
                listenableFuture.addListener(r1, executor);
                return r1;
            }
            throw null;
        }
        throw null;
    }

    public static ListenableFuture A01(ListenableFuture listenableFuture, AbstractC0382Ut ut, Executor executor) {
        if (executor != null) {
            C00120w r1 = new C00120w(listenableFuture, ut);
            if (executor != V3.INSTANCE) {
                executor = new V2(executor, r1);
            }
            listenableFuture.addListener(r1, executor);
            return r1;
        }
        throw null;
    }

    @Override // X.SH
    public final void afterDone() {
        maybePropagateCancellationTo(this.A00);
        this.A00 = null;
        this.A01 = null;
    }

    @Override // X.SH
    public final String pendingToString() {
        String str;
        ListenableFuture listenableFuture = this.A00;
        Object obj = this.A01;
        String pendingToString = super.pendingToString();
        if (listenableFuture != null) {
            StringBuilder sb = new StringBuilder("inputFuture=[");
            sb.append(listenableFuture);
            sb.append("], ");
            str = sb.toString();
        } else {
            str = OacrConstants.AUTO_SPEECH_DOMAIN;
        }
        if (obj != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append("function=[");
            sb2.append(obj);
            sb2.append("]");
            return sb2.toString();
        } else if (pendingToString != null) {
            return AnonymousClass08.A04(str, pendingToString);
        } else {
            return null;
        }
    }

    public final void run() {
        Throwable e;
        Object obj;
        ListenableFuture listenableFuture = this.A00;
        Object obj2 = this.A01;
        boolean isCancelled = isCancelled();
        boolean z = true;
        boolean z2 = false;
        if (listenableFuture == null) {
            z2 = true;
        }
        boolean z3 = isCancelled | z2;
        if (obj2 != null) {
            z = false;
        }
        if (!z3 && !z) {
            this.A00 = null;
            try {
                Preconditions.checkState(listenableFuture.isDone(), "Future was expected to be done: %s", listenableFuture);
                Object A002 = V8.A00(listenableFuture);
                try {
                    boolean z4 = this instanceof C00110v;
                    if (!z4) {
                        obj = ((AbstractC0382Ut) obj2).A1F(A002);
                        Preconditions.checkNotNull(obj, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)?");
                    } else {
                        obj = ((Function) obj2).apply(A002);
                    }
                    if (!z4) {
                        setFuture((ListenableFuture) obj);
                    } else {
                        set(obj);
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

    public AnonymousClass1O(ListenableFuture listenableFuture, Object obj) {
        if (listenableFuture != null) {
            this.A00 = listenableFuture;
            if (obj != null) {
                this.A01 = obj;
                return;
            }
            throw null;
        }
        throw null;
    }
}
