package X;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.ForOverride;
import com.google.j2objc.annotations.ReflectionSupport;
import com.oculus.common.build.BuildConfig;
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

@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD)
@ReflectionSupport(ReflectionSupport.Level.FULL)
/* renamed from: X.6f  reason: invalid class name */
public abstract class AnonymousClass6f<V> extends AnonymousClass9H<V> {
    public static final U8 ATOMIC_HELPER;
    public static final boolean GENERATE_CANCELLATION_CAUSES = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
    public static final Object NULL = new Object();
    public static final long SPIN_THRESHOLD_NANOS = 1000;
    public static final Logger log = Logger.getLogger(AnonymousClass6f.class.getName());
    @NullableDecl
    public volatile X6 listeners;
    @NullableDecl
    public volatile Object value;
    @NullableDecl
    public volatile X9 waiters;

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:13:0x0001 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [X.6f<?>] */
    /* JADX WARN: Type inference failed for: r4v1, types: [X.6f] */
    /* JADX WARN: Type inference failed for: r4v2, types: [X.6f<V>, X.6f] */
    /* JADX WARN: Type inference failed for: r0v4, types: [X.U8] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void complete(X.AnonymousClass6f<?> r4) {
        /*
            r3 = 0
        L_0x0001:
            r4.releaseWaiters()
            r4.afterDone()
            X.X6 r1 = r4.clearListeners(r3)
        L_0x000b:
            if (r1 == 0) goto L_0x0033
            X.X6 r3 = r1.A00
            java.lang.Runnable r2 = r1.A01
            boolean r0 = r2 instanceof X.X7
            if (r0 == 0) goto L_0x002c
            X.X7 r2 = (X.X7) r2
            X.6f<V> r4 = r2.A00
            java.lang.Object r0 = r4.value
            if (r0 != r2) goto L_0x0031
            com.google.common.util.concurrent.ListenableFuture<? extends V> r0 = r2.A01
            java.lang.Object r1 = getFutureValue(r0)
            X.U8 r0 = X.AnonymousClass6f.ATOMIC_HELPER
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
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass6f.complete(X.6f):void");
    }

    private void removeWaiter(X9 x9) {
        x9.thread = null;
        while (true) {
            X9 x92 = this.waiters;
            if (x92 != X9.A00) {
                X9 x93 = null;
                while (x92 != null) {
                    X9 x94 = x92.next;
                    if (x92.thread != null) {
                        x93 = x92;
                    } else if (x93 != null) {
                        x93.next = x94;
                        if (x93.thread == null) {
                        }
                    } else if (!ATOMIC_HELPER.A03(this, x92, x94)) {
                    }
                    x92 = x94;
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
        U8 u8;
        Throwable th;
        Throwable th2 = null;
        try {
            u8 = new ML();
            th = null;
        } catch (Throwable th3) {
            th2 = th3;
            u8 = new MM();
        }
        ATOMIC_HELPER = u8;
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
            Object A00 = gM.A00(this);
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

    private X6 clearListeners(X6 x6) {
        X6 x62;
        do {
            x62 = this.listeners;
        } while (!ATOMIC_HELPER.A02(this, x62, X6.A03));
        while (x62 != null) {
            X6 x63 = x62.A00;
            x62.A00 = x6;
            x6 = x62;
            x62 = x63;
        }
        return x6;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    private V getDoneValue(Object obj) throws ExecutionException {
        if (obj instanceof UM) {
            throw cancellationExceptionWithCause("Task was cancelled.", ((UM) obj).A00);
        } else if (obj instanceof C0152Wd) {
            throw new ExecutionException(((C0152Wd) obj).A00);
        } else if (obj == NULL) {
            return null;
        } else {
            return obj;
        }
    }

    public static Object getFutureValue(ListenableFuture<?> listenableFuture) {
        Throwable th;
        if (listenableFuture instanceof AbstractC00040n) {
            Object obj = ((AnonymousClass6f) listenableFuture).value;
            if (!(obj instanceof UM)) {
                return obj;
            }
            UM um = (UM) obj;
            if (!um.A01) {
                return obj;
            }
            Throwable th2 = um.A00;
            if (th2 != null) {
                return new UM(false, th2);
            }
            return UM.A02;
        }
        try {
            Preconditions.checkState(listenableFuture.isDone(), "Future was expected to be done: %s", listenableFuture);
            Object A00 = gM.A00(listenableFuture);
            if (A00 == null) {
                return NULL;
            }
            return A00;
        } catch (ExecutionException e) {
            th = e.getCause();
            return new C0152Wd(th);
        } catch (CancellationException e2) {
            return new UM(false, e2);
        } catch (Throwable th3) {
            th = th3;
            return new C0152Wd(th);
        }
    }

    private void releaseWaiters() {
        X9 x9;
        do {
            x9 = this.waiters;
        } while (!ATOMIC_HELPER.A03(this, x9, X9.A00));
        while (x9 != null) {
            Thread thread = x9.thread;
            if (thread != null) {
                x9.thread = null;
                LockSupport.unpark(thread);
            }
            x9 = x9.next;
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
        X6 x6 = this.listeners;
        X6 x62 = X6.A03;
        if (x6 != x62) {
            X6 x63 = new X6(runnable, executor);
            do {
                x63.A00 = x6;
                if (!ATOMIC_HELPER.A02(this, x6, x63)) {
                    x6 = this.listeners;
                } else {
                    return;
                }
            } while (x6 != x62);
        }
        executeListener(runnable, executor);
    }

    @CanIgnoreReturnValue
    public boolean cancel(boolean z) {
        UM um;
        Object obj = this.value;
        boolean z2 = false;
        if (obj == null) {
            z2 = true;
        }
        if (!z2 && !(obj instanceof X7)) {
            return false;
        }
        if (GENERATE_CANCELLATION_CAUSES) {
            um = new UM(z, new CancellationException("Future.cancel() was called."));
        } else if (z) {
            um = UM.A03;
        } else {
            um = UM.A02;
        }
        boolean z3 = false;
        AnonymousClass6f<V> r2 = this;
        while (true) {
            if (ATOMIC_HELPER.A04(r2, obj, um)) {
                complete(r2);
                if (!(obj instanceof X7)) {
                    break;
                }
                ListenableFuture<? extends V> listenableFuture = ((X7) obj).A01;
                if (listenableFuture instanceof AbstractC00040n) {
                    r2 = (AnonymousClass6f) listenableFuture;
                    obj = r2.value;
                    boolean z4 = false;
                    if (obj == null) {
                        z4 = true;
                    }
                    if (!z4 && !(obj instanceof X7)) {
                        break;
                    }
                    z3 = true;
                } else {
                    listenableFuture.cancel(z);
                    return true;
                }
            } else {
                obj = r2.value;
                if (!(obj instanceof X7)) {
                    return z3;
                }
            }
        }
        return true;
    }

    public boolean isCancelled() {
        return this.value instanceof UM;
    }

    public boolean isDone() {
        Object obj = this.value;
        boolean z = true;
        boolean z2 = false;
        if (obj != null) {
            z2 = true;
        }
        if (obj instanceof X7) {
            z = false;
        }
        return z2 & z;
    }

    @NullableDecl
    public String pendingToString() {
        Object obj = this.value;
        if (obj instanceof X7) {
            return AnonymousClass06.A04("setFuture=[", userObjectToString(((X7) obj).A01), "]");
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
            if (!ATOMIC_HELPER.A04(this, null, new C0152Wd(th))) {
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
        X7 x7;
        U8 u8;
        C0152Wd wd;
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
                x7 = new X7(this, listenableFuture);
                u8 = ATOMIC_HELPER;
                if (u8.A04(this, null, x7)) {
                    try {
                        listenableFuture.addListener(x7, g1.INSTANCE);
                        return true;
                    } catch (Throwable unused) {
                        wd = C0152Wd.A01;
                    }
                } else {
                    obj = this.value;
                }
            }
            if (obj instanceof UM) {
                listenableFuture.cancel(((UM) obj).A01);
            }
            return false;
        }
        throw null;
        u8.A04(this, x7, wd);
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
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass6f.toString():java.lang.String");
    }

    public final Throwable trustedGetException() {
        return ((C0152Wd) this.value).A00;
    }

    public final boolean wasInterrupted() {
        Object obj = this.value;
        if (!(obj instanceof UM) || !((UM) obj).A01) {
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
            if (!(obj instanceof X7)) {
                z4 = true;
            }
            if (!z3 || !z4) {
                X9 x9 = this.waiters;
                X9 x92 = X9.A00;
                if (x9 != x92) {
                    X9 x93 = new X9();
                    while (true) {
                        U8 u8 = ATOMIC_HELPER;
                        u8.A00(x93, x9);
                        if (!u8.A03(this, x9, x93)) {
                            x9 = this.waiters;
                            if (x9 == x92) {
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
                                    if (!(obj instanceof X7)) {
                                        z2 = true;
                                    }
                                } else {
                                    removeWaiter(x93);
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
            if (!(obj2 instanceof X7)) {
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
                X9 x9 = this.waiters;
                X9 x92 = X9.A00;
                if (x9 != x92) {
                    X9 x93 = new X9();
                    do {
                        U8 u8 = ATOMIC_HELPER;
                        u8.A00(x93, x9);
                        if (u8.A03(this, x9, x93)) {
                            while (true) {
                                LockSupport.parkNanos(this, nanos);
                                if (Thread.interrupted()) {
                                    removeWaiter(x93);
                                    break;
                                }
                                obj = this.value;
                                boolean z3 = false;
                                if (obj != null) {
                                    z3 = true;
                                }
                                boolean z4 = false;
                                if (!(obj instanceof X7)) {
                                    z4 = true;
                                }
                                if (z3 && z4) {
                                    break;
                                }
                                nanos = j2 - System.nanoTime();
                                if (nanos < 1000) {
                                    removeWaiter(x93);
                                    break;
                                }
                            }
                        } else {
                            x9 = this.waiters;
                        }
                    } while (x9 != x92);
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
                if (!(obj instanceof X7)) {
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
