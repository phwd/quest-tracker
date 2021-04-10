package X;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.ForOverride;
import com.google.j2objc.annotations.ReflectionSupport;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true)
@ReflectionSupport(ReflectionSupport.Level.FULL)
/* renamed from: X.6k  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC00136k<V> extends AbstractC0060Ay<V> {
    public static final AS ATOMIC_HELPER;
    public static final boolean GENERATE_CANCELLATION_CAUSES = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
    public static final Object NULL = new Object();
    public static final long SPIN_THRESHOLD_NANOS = 1000;
    public static final Logger log = Logger.getLogger(AbstractC00136k.class.getName());
    @NullableDecl
    public volatile C0048Al listeners;
    @NullableDecl
    public volatile Object value;
    @NullableDecl
    public volatile B0 waiters;

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:13:0x0001 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [X.6k<?>] */
    /* JADX WARN: Type inference failed for: r4v1, types: [X.6k] */
    /* JADX WARN: Type inference failed for: r4v2, types: [X.6k, X.6k<V>] */
    /* JADX WARN: Type inference failed for: r0v4, types: [X.AS] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void complete(X.AbstractC00136k<?> r4) {
        /*
            r3 = 0
        L_0x0001:
            r4.releaseWaiters()
            r4.afterDone()
            X.Al r1 = r4.clearListeners(r3)
        L_0x000b:
            if (r1 == 0) goto L_0x0033
            X.Al r3 = r1.A00
            java.lang.Runnable r2 = r1.A01
            boolean r0 = r2 instanceof X.RunnableC0051Ap
            if (r0 == 0) goto L_0x002c
            X.Ap r2 = (X.RunnableC0051Ap) r2
            X.6k<V> r4 = r2.A00
            java.lang.Object r0 = r4.value
            if (r0 != r2) goto L_0x0031
            com.google.common.util.concurrent.ListenableFuture<? extends V> r0 = r2.A01
            java.lang.Object r1 = getFutureValue(r0)
            X.AS r0 = X.AbstractC00136k.ATOMIC_HELPER
            boolean r0 = r0.A04(r4, r2, r1)
            if (r0 == 0) goto L_0x0031
            goto L_0x0001
        L_0x002c:
            java.util.concurrent.Executor r0 = r1.A02
            executeListener(r2, r0)
        L_0x0031:
            r1 = r3
            goto L_0x000b
        L_0x0033:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC00136k.complete(X.6k):void");
    }

    private void removeWaiter(B0 b0) {
        b0.thread = null;
        while (true) {
            B0 b02 = this.waiters;
            if (b02 != B0.A00) {
                B0 b03 = null;
                while (b02 != null) {
                    B0 b04 = b02.next;
                    if (b02.thread != null) {
                        b03 = b02;
                    } else if (b03 != null) {
                        b03.next = b04;
                        if (b03.thread == null) {
                        }
                    } else if (!ATOMIC_HELPER.A03(this, b02, b04)) {
                    }
                    b02 = b04;
                }
                return;
            }
            return;
        }
    }

    @Beta
    @ForOverride
    public void afterDone() {
    }

    public void interruptTask() {
    }

    public final void maybePropagateCancellationTo(@NullableDecl Future<?> future) {
        boolean z = false;
        if (future != null) {
            z = true;
        }
        if (z && isCancelled()) {
            future.cancel(wasInterrupted());
        }
    }

    static {
        AS as;
        Throwable th;
        Throwable th2 = null;
        try {
            as = new UJ();
            th = null;
        } catch (Throwable th3) {
            th2 = th3;
            as = new UK();
        }
        ATOMIC_HELPER = as;
        if (th2 != null) {
            Logger logger = log;
            Level level = Level.SEVERE;
            logger.log(level, "UnsafeAtomicHelper is broken!", th);
            logger.log(level, "SafeAtomicHelper is broken!", th2);
        }
    }

    private void addDoneString(StringBuilder sb) {
        String str;
        try {
            Preconditions.checkState(isDone(), "Future was expected to be done: %s", this);
            Object A00 = DE.A00(this);
            sb.append("SUCCESS, result=[");
            sb.append(userObjectToString(A00));
            sb.append("]");
        } catch (ExecutionException e) {
            sb.append("FAILURE, cause=[");
            sb.append(e.getCause());
            sb.append("]");
        } catch (CancellationException unused) {
            str = "CANCELLED";
            sb.append(str);
        } catch (RuntimeException e2) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e2.getClass());
            str = " thrown from get()]";
            sb.append(str);
        }
    }

    public static CancellationException cancellationExceptionWithCause(@NullableDecl String str, @NullableDecl Throwable th) {
        CancellationException cancellationException = new CancellationException(str);
        cancellationException.initCause(th);
        return cancellationException;
    }

    private C0048Al clearListeners(C0048Al al) {
        C0048Al al2;
        do {
            al2 = this.listeners;
        } while (!ATOMIC_HELPER.A02(this, al2, C0048Al.A03));
        while (al2 != null) {
            C0048Al al3 = al2.A00;
            al2.A00 = al;
            al = al2;
            al2 = al3;
        }
        return al;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    private V getDoneValue(Object obj) throws ExecutionException {
        if (obj instanceof AY) {
            throw cancellationExceptionWithCause("Task was cancelled.", ((AY) obj).A00);
        } else if (obj instanceof Ab) {
            throw new ExecutionException(((Ab) obj).A00);
        } else if (obj == NULL) {
            return null;
        } else {
            return obj;
        }
    }

    public static Object getFutureValue(ListenableFuture<?> listenableFuture) {
        Throwable th;
        if (listenableFuture instanceof AbstractC00030p) {
            Object obj = ((AbstractC00136k) listenableFuture).value;
            if (!(obj instanceof AY)) {
                return obj;
            }
            AY ay = (AY) obj;
            if (!ay.A01) {
                return obj;
            }
            Throwable th2 = ay.A00;
            if (th2 != null) {
                return new AY(false, th2);
            }
            return AY.A02;
        }
        try {
            Preconditions.checkState(listenableFuture.isDone(), "Future was expected to be done: %s", listenableFuture);
            Object A00 = DE.A00(listenableFuture);
            if (A00 == null) {
                return NULL;
            }
            return A00;
        } catch (ExecutionException e) {
            th = e.getCause();
            return new Ab(th);
        } catch (CancellationException e2) {
            return new AY(false, e2);
        } catch (Throwable th3) {
            th = th3;
            return new Ab(th);
        }
    }

    private void releaseWaiters() {
        B0 b0;
        do {
            b0 = this.waiters;
        } while (!ATOMIC_HELPER.A03(this, b0, B0.A00));
        while (b0 != null) {
            Thread thread = b0.thread;
            if (thread != null) {
                b0.thread = null;
                LockSupport.unpark(thread);
            }
            b0 = b0.next;
        }
    }

    private String userObjectToString(Object obj) {
        if (obj == this) {
            return "this future";
        }
        return String.valueOf(obj);
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable runnable, Executor executor) {
        Preconditions.checkNotNull(runnable, "Runnable was null.");
        Preconditions.checkNotNull(executor, "Executor was null.");
        C0048Al al = this.listeners;
        C0048Al al2 = C0048Al.A03;
        if (al != al2) {
            C0048Al al3 = new C0048Al(runnable, executor);
            do {
                al3.A00 = al;
                if (!ATOMIC_HELPER.A02(this, al, al3)) {
                    al = this.listeners;
                } else {
                    return;
                }
            } while (al != al2);
        }
        executeListener(runnable, executor);
    }

    @CanIgnoreReturnValue
    public boolean cancel(boolean z) {
        AY ay;
        Object obj = this.value;
        boolean z2 = false;
        if (obj == null) {
            z2 = true;
        }
        if (!z2 && !(obj instanceof RunnableC0051Ap)) {
            return false;
        }
        if (GENERATE_CANCELLATION_CAUSES) {
            ay = new AY(z, new CancellationException("Future.cancel() was called."));
        } else if (z) {
            ay = AY.A03;
        } else {
            ay = AY.A02;
        }
        boolean z3 = false;
        AbstractC00136k<V> r2 = this;
        while (true) {
            if (ATOMIC_HELPER.A04(r2, obj, ay)) {
                complete(r2);
                if (!(obj instanceof RunnableC0051Ap)) {
                    break;
                }
                ListenableFuture<? extends V> listenableFuture = ((RunnableC0051Ap) obj).A01;
                if (listenableFuture instanceof AbstractC00030p) {
                    r2 = (AbstractC00136k) listenableFuture;
                    obj = r2.value;
                    boolean z4 = false;
                    if (obj == null) {
                        z4 = true;
                    }
                    if (!z4 && !(obj instanceof RunnableC0051Ap)) {
                        break;
                    }
                    z3 = true;
                } else {
                    listenableFuture.cancel(z);
                    return true;
                }
            } else {
                obj = r2.value;
                if (!(obj instanceof RunnableC0051Ap)) {
                    return z3;
                }
            }
        }
        return true;
    }

    public boolean isCancelled() {
        return this.value instanceof AY;
    }

    public boolean isDone() {
        Object obj = this.value;
        boolean z = true;
        boolean z2 = false;
        if (obj != null) {
            z2 = true;
        }
        if (obj instanceof RunnableC0051Ap) {
            z = false;
        }
        return z2 & z;
    }

    @NullableDecl
    public String pendingToString() {
        Object obj = this.value;
        if (obj instanceof RunnableC0051Ap) {
            return AnonymousClass06.A05("setFuture=[", userObjectToString(((RunnableC0051Ap) obj).A01), "]");
        }
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        StringBuilder sb = new StringBuilder("remaining delay=[");
        sb.append(((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS));
        sb.append(" ms]");
        return sb.toString();
    }

    @CanIgnoreReturnValue
    public boolean set(@NullableDecl V v) {
        if (v == null) {
            v = (V) NULL;
        }
        if (!ATOMIC_HELPER.A04(this, null, v)) {
            return false;
        }
        complete(this);
        return true;
    }

    @CanIgnoreReturnValue
    public boolean setException(Throwable th) {
        if (th != null) {
            if (!ATOMIC_HELPER.A04(this, null, new Ab(th))) {
                return false;
            }
            complete(this);
            return true;
        }
        throw null;
    }

    @CanIgnoreReturnValue
    @Beta
    public boolean setFuture(ListenableFuture<? extends V> listenableFuture) {
        RunnableC0051Ap ap;
        AS as;
        Ab ab;
        if (listenableFuture != null) {
            Object obj = this.value;
            if (obj == null) {
                if (listenableFuture.isDone()) {
                    if (ATOMIC_HELPER.A04(this, null, getFutureValue(listenableFuture))) {
                        complete(this);
                        return true;
                    }
                    return false;
                }
                ap = new RunnableC0051Ap(this, listenableFuture);
                as = ATOMIC_HELPER;
                if (as.A04(this, null, ap)) {
                    try {
                        listenableFuture.addListener(ap, EnumC0063Bj.INSTANCE);
                        return true;
                    } catch (Throwable unused) {
                        ab = Ab.A01;
                    }
                } else {
                    obj = this.value;
                }
            }
            if (obj instanceof AY) {
                listenableFuture.cancel(((AY) obj).A01);
            }
            return false;
        }
        throw null;
        as.A04(this, ap, ab);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004b, code lost:
        if (r2.isEmpty() != false) goto L_0x004d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String toString() {
        /*
        // Method dump skipped, instructions count: 105
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC00136k.toString():java.lang.String");
    }

    public final Throwable trustedGetException() {
        return ((Ab) this.value).A00;
    }

    public final boolean wasInterrupted() {
        Object obj = this.value;
        if (!(obj instanceof AY) || !((AY) obj).A01) {
            return false;
        }
        return true;
    }

    public static void executeListener(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger logger = log;
            Level level = Level.SEVERE;
            StringBuilder sb = new StringBuilder("RuntimeException while executing runnable ");
            sb.append(runnable);
            sb.append(" with executor ");
            sb.append(executor);
            logger.log(level, sb.toString(), (Throwable) e);
        }
    }

    @Override // java.util.concurrent.Future
    @CanIgnoreReturnValue
    public V get() throws InterruptedException, ExecutionException {
        boolean z;
        boolean z2;
        if (!Thread.interrupted()) {
            Object obj = this.value;
            boolean z3 = false;
            if (obj != null) {
                z3 = true;
            }
            boolean z4 = false;
            if (!(obj instanceof RunnableC0051Ap)) {
                z4 = true;
            }
            if (!z3 || !z4) {
                B0 b0 = this.waiters;
                B0 b02 = B0.A00;
                if (b0 != b02) {
                    B0 b03 = new B0();
                    while (true) {
                        AS as = ATOMIC_HELPER;
                        as.A00(b03, b0);
                        if (!as.A03(this, b0, b03)) {
                            b0 = this.waiters;
                            if (b0 == b02) {
                                break;
                            }
                        } else {
                            do {
                                LockSupport.park(this);
                                if (!Thread.interrupted()) {
                                    obj = this.value;
                                    z = false;
                                    if (obj != null) {
                                        z = true;
                                    }
                                    z2 = false;
                                    if (!(obj instanceof RunnableC0051Ap)) {
                                        z2 = true;
                                    }
                                } else {
                                    removeWaiter(b03);
                                }
                            } while (!(z & z2));
                        }
                    }
                    obj = this.value;
                } else {
                    obj = this.value;
                }
            }
            return getDoneValue(obj);
        }
        throw new InterruptedException();
    }

    @Override // java.util.concurrent.Future
    @CanIgnoreReturnValue
    public V get(long j, TimeUnit timeUnit) throws InterruptedException, TimeoutException, ExecutionException {
        long j2;
        StringBuilder sb;
        Object obj;
        long nanos = timeUnit.toNanos(j);
        if (!Thread.interrupted()) {
            Object obj2 = this.value;
            boolean z = false;
            if (obj2 != null) {
                z = true;
            }
            boolean z2 = false;
            if (!(obj2 instanceof RunnableC0051Ap)) {
                z2 = true;
            }
            if (z && z2) {
                return getDoneValue(obj2);
            }
            if (nanos > 0) {
                j2 = System.nanoTime() + nanos;
            } else {
                j2 = 0;
            }
            if (nanos >= 1000) {
                B0 b0 = this.waiters;
                B0 b02 = B0.A00;
                if (b0 != b02) {
                    B0 b03 = new B0();
                    do {
                        AS as = ATOMIC_HELPER;
                        as.A00(b03, b0);
                        if (as.A03(this, b0, b03)) {
                            while (true) {
                                LockSupport.parkNanos(this, nanos);
                                if (Thread.interrupted()) {
                                    removeWaiter(b03);
                                    break;
                                }
                                obj = this.value;
                                boolean z3 = false;
                                if (obj != null) {
                                    z3 = true;
                                }
                                boolean z4 = false;
                                if (!(obj instanceof RunnableC0051Ap)) {
                                    z4 = true;
                                }
                                if (z3 && z4) {
                                    break;
                                }
                                nanos = j2 - System.nanoTime();
                                if (nanos < 1000) {
                                    removeWaiter(b03);
                                    break;
                                }
                            }
                        } else {
                            b0 = this.waiters;
                        }
                    } while (b0 != b02);
                }
                return getDoneValue(this.value);
            }
            while (nanos > 0) {
                obj = this.value;
                boolean z5 = false;
                if (obj != null) {
                    z5 = true;
                }
                boolean z6 = false;
                if (!(obj instanceof RunnableC0051Ap)) {
                    z6 = true;
                }
                if (z5 && z6) {
                    return getDoneValue(obj);
                }
                if (!Thread.interrupted()) {
                    nanos = j2 - System.nanoTime();
                }
            }
            String obj3 = toString();
            if (isDone()) {
                sb = new StringBuilder();
                sb.append("Waited ");
                sb.append(j);
                sb.append(" ");
                sb.append(timeUnit.toString().toLowerCase(Locale.ROOT));
                sb.append(" but future completed as timeout expired");
            } else {
                sb = new StringBuilder();
                sb.append("Waited ");
                sb.append(j);
                sb.append(" ");
                sb.append(timeUnit.toString().toLowerCase(Locale.ROOT));
                sb.append(" for ");
                sb.append(obj3);
            }
            throw new TimeoutException(sb.toString());
        }
        throw new InterruptedException();
    }
}
