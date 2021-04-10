package X;

import com.facebook.assistant.oacr.OacrConstants;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;

/* renamed from: X.1Q  reason: invalid class name */
public abstract class AnonymousClass1Q extends AbstractC00141v implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.AbstractCatchingFuture";
    public ListenableFuture A00;
    public Class A01;
    public Object A02;

    @Override // X.SH
    public final void afterDone() {
        maybePropagateCancellationTo(this.A00);
        this.A00 = null;
        this.A01 = null;
        this.A02 = null;
    }

    @Override // X.SH
    public final String pendingToString() {
        String str;
        ListenableFuture listenableFuture = this.A00;
        Class cls = this.A01;
        Object obj = this.A02;
        String pendingToString = super.pendingToString();
        if (listenableFuture != null) {
            StringBuilder sb = new StringBuilder("inputFuture=[");
            sb.append(listenableFuture);
            sb.append("], ");
            str = sb.toString();
        } else {
            str = OacrConstants.AUTO_SPEECH_DOMAIN;
        }
        if (cls != null && obj != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append("exceptionType=[");
            sb2.append(cls);
            sb2.append("], fallback=[");
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
        Object obj;
        Throwable th;
        ListenableFuture listenableFuture = this.A00;
        Class cls = this.A01;
        Object obj2 = this.A02;
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
        if (obj2 != null) {
            z = false;
        }
        if (!(z | z4) && !isCancelled()) {
            this.A00 = null;
            try {
                Preconditions.checkState(listenableFuture.isDone(), "Future was expected to be done: %s", listenableFuture);
                obj = V8.A00(listenableFuture);
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
            boolean z5 = this instanceof C00130y;
            if (!z5) {
                obj = ((AbstractC0382Ut) obj2).A1F(th);
                Preconditions.checkNotNull(obj, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)?");
            } else {
                obj = ((Function) obj2).apply(th);
            }
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

    public AnonymousClass1Q(ListenableFuture listenableFuture, Class cls, Object obj) {
        this.A00 = listenableFuture;
        if (cls != null) {
            this.A01 = cls;
            if (obj != null) {
                this.A02 = obj;
                return;
            }
            throw null;
        }
        throw null;
    }
}
