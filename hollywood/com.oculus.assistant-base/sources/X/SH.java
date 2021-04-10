package X;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
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

public abstract class SH extends Be {
    public static final AbstractC0375Ul ATOMIC_HELPER;
    public static final boolean GENERATE_CANCELLATION_CAUSES = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
    public static final Object NULL = new Object();
    public static final long SPIN_THRESHOLD_NANOS = 1000;
    public static final Logger log = Logger.getLogger(SH.class.getName());
    public volatile C0379Up listeners;
    public volatile Object value;
    public volatile Us waiters;

    public static void complete(SH sh) {
        C0379Up up = null;
        while (true) {
            sh.releaseWaiters();
            sh.afterDone();
            C0379Up clearListeners = sh.clearListeners(up);
            while (true) {
                if (clearListeners != null) {
                    up = clearListeners.A00;
                    Runnable runnable = clearListeners.A01;
                    if (runnable instanceof RunnableC0380Uq) {
                        RunnableC0380Uq uq = (RunnableC0380Uq) runnable;
                        sh = uq.A00;
                        if (sh.value == uq) {
                            if (ATOMIC_HELPER.A04(sh, uq, getFutureValue(uq.A01))) {
                            }
                        } else {
                            continue;
                        }
                    } else {
                        executeListener(runnable, clearListeners.A02);
                    }
                    clearListeners = up;
                } else {
                    return;
                }
            }
        }
    }

    private void removeWaiter(Us us) {
        us.thread = null;
        while (true) {
            Us us2 = this.waiters;
            if (us2 != Us.A00) {
                Us us3 = null;
                while (us2 != null) {
                    Us us4 = us2.next;
                    if (us2.thread != null) {
                        us3 = us2;
                    } else if (us3 != null) {
                        us3.next = us4;
                        if (us3.thread == null) {
                        }
                    } else if (!ATOMIC_HELPER.A03(this, us2, us4)) {
                    }
                    us2 = us4;
                }
                return;
            }
            return;
        }
    }

    public void afterDone() {
    }

    public void interruptTask() {
    }

    public final void maybePropagateCancellationTo(Future future) {
        boolean z = false;
        if (future != null) {
            z = true;
        }
        if (z && isCancelled()) {
            future.cancel(wasInterrupted());
        }
    }

    static {
        AbstractC0375Ul ul;
        Throwable th;
        Throwable th2 = null;
        try {
            ul = new C1189uq();
            th = null;
        } catch (Throwable th3) {
            th2 = th3;
            ul = new C1188up();
        }
        ATOMIC_HELPER = ul;
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
            Object A00 = V8.A00(this);
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

    public static CancellationException cancellationExceptionWithCause(String str, Throwable th) {
        CancellationException cancellationException = new CancellationException(str);
        cancellationException.initCause(th);
        return cancellationException;
    }

    private C0379Up clearListeners(C0379Up up) {
        C0379Up up2;
        do {
            up2 = this.listeners;
        } while (!ATOMIC_HELPER.A02(this, up2, C0379Up.A03));
        while (up2 != null) {
            C0379Up up3 = up2.A00;
            up2.A00 = up;
            up = up2;
            up2 = up3;
        }
        return up;
    }

    private Object getDoneValue(Object obj) {
        if (obj instanceof C0376Um) {
            throw cancellationExceptionWithCause("Task was cancelled.", ((C0376Um) obj).A00);
        } else if (obj instanceof C0378Uo) {
            throw new ExecutionException(((C0378Uo) obj).A00);
        } else if (obj == NULL) {
            return null;
        } else {
            return obj;
        }
    }

    public static Object getFutureValue(ListenableFuture listenableFuture) {
        Throwable th;
        if (listenableFuture instanceof AbstractC00141v) {
            Object obj = ((SH) listenableFuture).value;
            if (!(obj instanceof C0376Um)) {
                return obj;
            }
            C0376Um um = (C0376Um) obj;
            if (!um.A01) {
                return obj;
            }
            Throwable th2 = um.A00;
            if (th2 != null) {
                return new C0376Um(false, th2);
            }
            return C0376Um.A02;
        }
        try {
            Preconditions.checkState(listenableFuture.isDone(), "Future was expected to be done: %s", listenableFuture);
            Object A00 = V8.A00(listenableFuture);
            if (A00 == null) {
                return NULL;
            }
            return A00;
        } catch (ExecutionException e) {
            th = e.getCause();
            return new C0378Uo(th);
        } catch (CancellationException e2) {
            return new C0376Um(false, e2);
        } catch (Throwable th3) {
            th = th3;
            return new C0378Uo(th);
        }
    }

    private void releaseWaiters() {
        Us us;
        do {
            us = this.waiters;
        } while (!ATOMIC_HELPER.A03(this, us, Us.A00));
        while (us != null) {
            Thread thread = us.thread;
            if (thread != null) {
                us.thread = null;
                LockSupport.unpark(thread);
            }
            us = us.next;
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
        C0379Up up = this.listeners;
        C0379Up up2 = C0379Up.A03;
        if (up != up2) {
            C0379Up up3 = new C0379Up(runnable, executor);
            do {
                up3.A00 = up;
                if (!ATOMIC_HELPER.A02(this, up, up3)) {
                    up = this.listeners;
                } else {
                    return;
                }
            } while (up != up2);
        }
        executeListener(runnable, executor);
    }

    public boolean cancel(boolean z) {
        C0376Um um;
        Object obj = this.value;
        boolean z2 = false;
        if (obj == null) {
            z2 = true;
        }
        if (!z2 && !(obj instanceof RunnableC0380Uq)) {
            return false;
        }
        if (GENERATE_CANCELLATION_CAUSES) {
            um = new C0376Um(z, new CancellationException("Future.cancel() was called."));
        } else if (z) {
            um = C0376Um.A03;
        } else {
            um = C0376Um.A02;
        }
        boolean z3 = false;
        SH sh = this;
        while (true) {
            if (ATOMIC_HELPER.A04(sh, obj, um)) {
                complete(sh);
                if (!(obj instanceof RunnableC0380Uq)) {
                    break;
                }
                ListenableFuture listenableFuture = ((RunnableC0380Uq) obj).A01;
                if (listenableFuture instanceof AbstractC00141v) {
                    sh = (SH) listenableFuture;
                    obj = sh.value;
                    boolean z4 = false;
                    if (obj == null) {
                        z4 = true;
                    }
                    if (!z4 && !(obj instanceof RunnableC0380Uq)) {
                        break;
                    }
                    z3 = true;
                } else {
                    listenableFuture.cancel(z);
                    return true;
                }
            } else {
                obj = sh.value;
                if (!(obj instanceof RunnableC0380Uq)) {
                    return z3;
                }
            }
        }
        return true;
    }

    public boolean isCancelled() {
        return this.value instanceof C0376Um;
    }

    public boolean isDone() {
        Object obj = this.value;
        boolean z = true;
        boolean z2 = false;
        if (obj != null) {
            z2 = true;
        }
        if (obj instanceof RunnableC0380Uq) {
            z = false;
        }
        return z2 & z;
    }

    public String pendingToString() {
        Object obj = this.value;
        if (obj instanceof RunnableC0380Uq) {
            return AnonymousClass08.A05("setFuture=[", userObjectToString(((RunnableC0380Uq) obj).A01), "]");
        }
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        StringBuilder sb = new StringBuilder("remaining delay=[");
        sb.append(((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS));
        sb.append(" ms]");
        return sb.toString();
    }

    public boolean set(Object obj) {
        if (obj == null) {
            obj = NULL;
        }
        if (!ATOMIC_HELPER.A04(this, null, obj)) {
            return false;
        }
        complete(this);
        return true;
    }

    public boolean setException(Throwable th) {
        if (th != null) {
            if (!ATOMIC_HELPER.A04(this, null, new C0378Uo(th))) {
                return false;
            }
            complete(this);
            return true;
        }
        throw null;
    }

    public boolean setFuture(ListenableFuture listenableFuture) {
        RunnableC0380Uq uq;
        AbstractC0375Ul ul;
        C0378Uo uo;
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
                uq = new RunnableC0380Uq(this, listenableFuture);
                ul = ATOMIC_HELPER;
                if (ul.A04(this, null, uq)) {
                    try {
                        listenableFuture.addListener(uq, V3.INSTANCE);
                        return true;
                    } catch (Throwable unused) {
                        uo = C0378Uo.A01;
                    }
                } else {
                    obj = this.value;
                }
            }
            if (obj instanceof C0376Um) {
                listenableFuture.cancel(((C0376Um) obj).A01);
            }
            return false;
        }
        throw null;
        ul.A04(this, uq, uo);
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
        throw new UnsupportedOperationException("Method not decompiled: X.SH.toString():java.lang.String");
    }

    public final Throwable trustedGetException() {
        return ((C0378Uo) this.value).A00;
    }

    public final boolean wasInterrupted() {
        Object obj = this.value;
        if (!(obj instanceof C0376Um) || !((C0376Um) obj).A01) {
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
    public Object get() {
        boolean z;
        boolean z2;
        if (!Thread.interrupted()) {
            Object obj = this.value;
            boolean z3 = false;
            if (obj != null) {
                z3 = true;
            }
            boolean z4 = false;
            if (!(obj instanceof RunnableC0380Uq)) {
                z4 = true;
            }
            if (!z3 || !z4) {
                Us us = this.waiters;
                Us us2 = Us.A00;
                if (us != us2) {
                    Us us3 = new Us();
                    while (true) {
                        AbstractC0375Ul ul = ATOMIC_HELPER;
                        ul.A00(us3, us);
                        if (!ul.A03(this, us, us3)) {
                            us = this.waiters;
                            if (us == us2) {
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
                                    if (!(obj instanceof RunnableC0380Uq)) {
                                        z2 = true;
                                    }
                                } else {
                                    removeWaiter(us3);
                                    throw new InterruptedException();
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
    public Object get(long j, TimeUnit timeUnit) {
        long j2;
        Object obj;
        long nanos = timeUnit.toNanos(j);
        if (!Thread.interrupted()) {
            Object obj2 = this.value;
            boolean z = false;
            if (obj2 != null) {
                z = true;
            }
            boolean z2 = false;
            if (!(obj2 instanceof RunnableC0380Uq)) {
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
                Us us = this.waiters;
                Us us2 = Us.A00;
                if (us != us2) {
                    Us us3 = new Us();
                    do {
                        AbstractC0375Ul ul = ATOMIC_HELPER;
                        ul.A00(us3, us);
                        if (!ul.A03(this, us, us3)) {
                            us = this.waiters;
                        } else {
                            while (true) {
                                LockSupport.parkNanos(this, nanos);
                                if (!Thread.interrupted()) {
                                    obj = this.value;
                                    boolean z3 = false;
                                    if (obj != null) {
                                        z3 = true;
                                    }
                                    boolean z4 = false;
                                    if (!(obj instanceof RunnableC0380Uq)) {
                                        z4 = true;
                                    }
                                    if (z3 && z4) {
                                        break;
                                    }
                                    nanos = j2 - System.nanoTime();
                                    if (nanos < 1000) {
                                        removeWaiter(us3);
                                        break;
                                    }
                                } else {
                                    removeWaiter(us3);
                                    throw new InterruptedException();
                                }
                            }
                            return getDoneValue(obj);
                        }
                    } while (us != us2);
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
                if (!(obj instanceof RunnableC0380Uq)) {
                    z6 = true;
                }
                if (z5 && z6) {
                    return getDoneValue(obj);
                }
                if (!Thread.interrupted()) {
                    nanos = j2 - System.nanoTime();
                } else {
                    throw new InterruptedException();
                }
            }
            String obj3 = toString();
            if (isDone()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Waited ");
                sb.append(j);
                sb.append(" ");
                sb.append(timeUnit.toString().toLowerCase(Locale.ROOT));
                sb.append(" but future completed as timeout expired");
                throw new TimeoutException(sb.toString());
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Waited ");
            sb2.append(j);
            sb2.append(" ");
            sb2.append(timeUnit.toString().toLowerCase(Locale.ROOT));
            sb2.append(" for ");
            sb2.append(obj3);
            throw new TimeoutException(sb2.toString());
        }
        throw new InterruptedException();
    }
}
