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
/* renamed from: X.0BX  reason: invalid class name */
public abstract class AnonymousClass0BX<V> extends AnonymousClass0IP<V> {
    public static final AbstractC01530Hx ATOMIC_HELPER;
    public static final boolean GENERATE_CANCELLATION_CAUSES = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
    public static final Object NULL = new Object();
    public static final long SPIN_THRESHOLD_NANOS = 1000;
    public static final Logger log = Logger.getLogger(AnonymousClass0BX.class.getName());
    @NullableDecl
    public volatile AnonymousClass0HF listeners;
    @NullableDecl
    public volatile Object value;
    @NullableDecl
    public volatile AnonymousClass0H4 waiters;

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:13:0x0001 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [X.0BX<?>] */
    /* JADX WARN: Type inference failed for: r4v1, types: [X.0BX] */
    /* JADX WARN: Type inference failed for: r4v2, types: [X.0BX<V>, X.0BX] */
    /* JADX WARN: Type inference failed for: r0v4, types: [X.0Hx] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void complete(X.AnonymousClass0BX<?> r4) {
        /*
            r3 = 0
        L_0x0001:
            r4.releaseWaiters()
            r4.afterDone()
            X.0HF r1 = r4.clearListeners(r3)
        L_0x000b:
            if (r1 == 0) goto L_0x0033
            X.0HF r3 = r1.A00
            java.lang.Runnable r2 = r1.A01
            boolean r0 = r2 instanceof X.AnonymousClass0H6
            if (r0 == 0) goto L_0x002c
            X.0H6 r2 = (X.AnonymousClass0H6) r2
            X.0BX<V> r4 = r2.A00
            java.lang.Object r0 = r4.value
            if (r0 != r2) goto L_0x0031
            com.google.common.util.concurrent.ListenableFuture<? extends V> r0 = r2.A01
            java.lang.Object r1 = getFutureValue(r0)
            X.0Hx r0 = X.AnonymousClass0BX.ATOMIC_HELPER
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
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0BX.complete(X.0BX):void");
    }

    private void removeWaiter(AnonymousClass0H4 r6) {
        r6.thread = null;
        while (true) {
            AnonymousClass0H4 r3 = this.waiters;
            if (r3 != AnonymousClass0H4.A00) {
                AnonymousClass0H4 r2 = null;
                while (r3 != null) {
                    AnonymousClass0H4 r1 = r3.next;
                    if (r3.thread != null) {
                        r2 = r3;
                    } else if (r2 != null) {
                        r2.next = r1;
                        if (r2.thread == null) {
                        }
                    } else if (!ATOMIC_HELPER.A03(this, r3, r1)) {
                    }
                    r3 = r1;
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
        AbstractC01530Hx r5;
        Throwable th;
        Throwable th2 = null;
        try {
            r5 = new C02250Xj();
            th = null;
        } catch (Throwable th3) {
            th2 = th3;
            r5 = new C02260Xk();
        }
        ATOMIC_HELPER = r5;
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
            Object A00 = AnonymousClass00Y.A00(this);
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

    private AnonymousClass0HF clearListeners(AnonymousClass0HF r4) {
        AnonymousClass0HF r2;
        do {
            r2 = this.listeners;
        } while (!ATOMIC_HELPER.A02(this, r2, AnonymousClass0HF.A03));
        while (r2 != null) {
            AnonymousClass0HF r0 = r2.A00;
            r2.A00 = r4;
            r4 = r2;
            r2 = r0;
        }
        return r4;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    private V getDoneValue(Object obj) throws ExecutionException {
        if (obj instanceof C01510Hu) {
            throw cancellationExceptionWithCause("Task was cancelled.", ((C01510Hu) obj).A00);
        } else if (obj instanceof AnonymousClass0HH) {
            throw new ExecutionException(((AnonymousClass0HH) obj).A00);
        } else if (obj == NULL) {
            return null;
        } else {
            return obj;
        }
    }

    public static Object getFutureValue(ListenableFuture<?> listenableFuture) {
        Throwable th;
        if (listenableFuture instanceof AnonymousClass063) {
            Object obj = ((AnonymousClass0BX) listenableFuture).value;
            if (!(obj instanceof C01510Hu)) {
                return obj;
            }
            C01510Hu r1 = (C01510Hu) obj;
            if (!r1.A01) {
                return obj;
            }
            Throwable th2 = r1.A00;
            if (th2 != null) {
                return new C01510Hu(false, th2);
            }
            return C01510Hu.A02;
        }
        try {
            Preconditions.checkState(listenableFuture.isDone(), "Future was expected to be done: %s", listenableFuture);
            Object A00 = AnonymousClass00Y.A00(listenableFuture);
            if (A00 == null) {
                return NULL;
            }
            return A00;
        } catch (ExecutionException e) {
            th = e.getCause();
            return new AnonymousClass0HH(th);
        } catch (CancellationException e2) {
            return new C01510Hu(false, e2);
        } catch (Throwable th3) {
            th = th3;
            return new AnonymousClass0HH(th);
        }
    }

    private void releaseWaiters() {
        AnonymousClass0H4 r2;
        do {
            r2 = this.waiters;
        } while (!ATOMIC_HELPER.A03(this, r2, AnonymousClass0H4.A00));
        while (r2 != null) {
            Thread thread = r2.thread;
            if (thread != null) {
                r2.thread = null;
                LockSupport.unpark(thread);
            }
            r2 = r2.next;
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
        AnonymousClass0HF r3 = this.listeners;
        AnonymousClass0HF r2 = AnonymousClass0HF.A03;
        if (r3 != r2) {
            AnonymousClass0HF r1 = new AnonymousClass0HF(runnable, executor);
            do {
                r1.A00 = r3;
                if (!ATOMIC_HELPER.A02(this, r3, r1)) {
                    r3 = this.listeners;
                } else {
                    return;
                }
            } while (r3 != r2);
        }
        executeListener(runnable, executor);
    }

    @CanIgnoreReturnValue
    public boolean cancel(boolean z) {
        C01510Hu r3;
        Object obj = this.value;
        boolean z2 = false;
        if (obj == null) {
            z2 = true;
        }
        if (!z2 && !(obj instanceof AnonymousClass0H6)) {
            return false;
        }
        if (GENERATE_CANCELLATION_CAUSES) {
            r3 = new C01510Hu(z, new CancellationException("Future.cancel() was called."));
        } else if (z) {
            r3 = C01510Hu.A03;
        } else {
            r3 = C01510Hu.A02;
        }
        boolean z3 = false;
        AnonymousClass0BX<V> r2 = this;
        while (true) {
            if (ATOMIC_HELPER.A04(r2, obj, r3)) {
                complete(r2);
                if (!(obj instanceof AnonymousClass0H6)) {
                    break;
                }
                ListenableFuture<? extends V> listenableFuture = ((AnonymousClass0H6) obj).A01;
                if (listenableFuture instanceof AnonymousClass063) {
                    r2 = (AnonymousClass0BX) listenableFuture;
                    obj = r2.value;
                    boolean z4 = false;
                    if (obj == null) {
                        z4 = true;
                    }
                    if (!z4 && !(obj instanceof AnonymousClass0H6)) {
                        break;
                    }
                    z3 = true;
                } else {
                    listenableFuture.cancel(z);
                    return true;
                }
            } else {
                obj = r2.value;
                if (!(obj instanceof AnonymousClass0H6)) {
                    return z3;
                }
            }
        }
        return true;
    }

    public boolean isCancelled() {
        return this.value instanceof C01510Hu;
    }

    public boolean isDone() {
        Object obj = this.value;
        boolean z = true;
        boolean z2 = false;
        if (obj != null) {
            z2 = true;
        }
        if (obj instanceof AnonymousClass0H6) {
            z = false;
        }
        return z2 & z;
    }

    @NullableDecl
    public String pendingToString() {
        Object obj = this.value;
        if (obj instanceof AnonymousClass0H6) {
            return AnonymousClass006.A07("setFuture=[", userObjectToString(((AnonymousClass0H6) obj).A01), "]");
        }
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
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
            if (!ATOMIC_HELPER.A04(this, null, new AnonymousClass0HH(th))) {
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
        AnonymousClass0H6 r3;
        AbstractC01530Hx r2;
        AnonymousClass0HH r0;
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
                r3 = new AnonymousClass0H6(this, listenableFuture);
                r2 = ATOMIC_HELPER;
                if (r2.A04(this, null, r3)) {
                    try {
                        listenableFuture.addListener(r3, AnonymousClass08h.INSTANCE);
                        return true;
                    } catch (Throwable unused) {
                        r0 = AnonymousClass0HH.A01;
                    }
                } else {
                    obj = this.value;
                }
            }
            if (obj instanceof C01510Hu) {
                listenableFuture.cancel(((C01510Hu) obj).A01);
            }
            return false;
        }
        throw null;
        r2.A04(this, r3, r0);
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
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0BX.toString():java.lang.String");
    }

    public final Throwable trustedGetException() {
        return ((AnonymousClass0HH) this.value).A00;
    }

    public final boolean wasInterrupted() {
        Object obj = this.value;
        if (!(obj instanceof C01510Hu) || !((C01510Hu) obj).A01) {
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
            logger.log(level, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e);
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
            if (!(obj instanceof AnonymousClass0H6)) {
                z4 = true;
            }
            if (!z3 || !z4) {
                AnonymousClass0H4 r2 = this.waiters;
                AnonymousClass0H4 r1 = AnonymousClass0H4.A00;
                if (r2 != r1) {
                    AnonymousClass0H4 r3 = new AnonymousClass0H4();
                    while (true) {
                        AbstractC01530Hx r0 = ATOMIC_HELPER;
                        r0.A00(r3, r2);
                        if (!r0.A03(this, r2, r3)) {
                            r2 = this.waiters;
                            if (r2 == r1) {
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
                                    if (!(obj instanceof AnonymousClass0H6)) {
                                        z2 = true;
                                    }
                                } else {
                                    removeWaiter(r3);
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
            if (!(obj2 instanceof AnonymousClass0H6)) {
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
                AnonymousClass0H4 r5 = this.waiters;
                AnonymousClass0H4 r1 = AnonymousClass0H4.A00;
                if (r5 != r1) {
                    AnonymousClass0H4 r4 = new AnonymousClass0H4();
                    do {
                        AbstractC01530Hx r0 = ATOMIC_HELPER;
                        r0.A00(r4, r5);
                        if (r0.A03(this, r5, r4)) {
                            while (true) {
                                LockSupport.parkNanos(this, nanos);
                                if (Thread.interrupted()) {
                                    removeWaiter(r4);
                                    break;
                                }
                                obj = this.value;
                                boolean z3 = false;
                                if (obj != null) {
                                    z3 = true;
                                }
                                boolean z4 = false;
                                if (!(obj instanceof AnonymousClass0H6)) {
                                    z4 = true;
                                }
                                if (z3 && z4) {
                                    break;
                                }
                                nanos = j2 - System.nanoTime();
                                if (nanos < 1000) {
                                    removeWaiter(r4);
                                    break;
                                }
                            }
                        } else {
                            r5 = this.waiters;
                        }
                    } while (r5 != r1);
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
                if (!(obj instanceof AnonymousClass0H6)) {
                    z6 = true;
                }
                if (z5 && z6) {
                    return getDoneValue(obj);
                }
                if (!Thread.interrupted()) {
                    nanos = j2 - System.nanoTime();
                }
            }
            String r3 = toString();
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
                sb.append(r3);
            }
            throw new TimeoutException(sb.toString());
        }
        throw new InterruptedException();
    }
}
