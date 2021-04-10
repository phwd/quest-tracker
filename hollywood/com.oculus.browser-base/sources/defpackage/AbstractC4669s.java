package defpackage;

import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;

/* renamed from: s  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4669s implements AbstractFutureC5208v90 {
    public static final boolean F = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
    public static final AbstractC3302k G;
    public static final Object H = new Object();
    public volatile Object I;

    /* renamed from: J  reason: collision with root package name */
    public volatile C3986o f11242J;
    public volatile r K;

    static {
        AbstractC3302k kVar;
        Object th;
        try {
            kVar = new C4157p(AtomicReferenceFieldUpdater.newUpdater(r.class, Thread.class, "b"), AtomicReferenceFieldUpdater.newUpdater(r.class, r.class, "c"), AtomicReferenceFieldUpdater.newUpdater(AbstractC4669s.class, r.class, "K"), AtomicReferenceFieldUpdater.newUpdater(AbstractC4669s.class, C3986o.class, "J"), AtomicReferenceFieldUpdater.newUpdater(AbstractC4669s.class, Object.class, "I"));
            th = null;
        } catch (Throwable th2) {
            th = th2;
            kVar = new C4328q();
        }
        G = kVar;
        if (th == null) {
        } else {
            Level level = Level.SEVERE;
            throw null;
        }
    }

    public static void c(AbstractC4669s sVar) {
        r rVar;
        C3986o oVar;
        C3986o oVar2;
        do {
            rVar = sVar.K;
        } while (!G.c(sVar, rVar, r.f11175a));
        while (true) {
            oVar = null;
            if (rVar == null) {
                break;
            }
            Thread thread = rVar.b;
            if (thread != null) {
                rVar.b = null;
                LockSupport.unpark(thread);
            }
            rVar = rVar.c;
        }
        do {
            oVar2 = sVar.f11242J;
        } while (!G.a(sVar, oVar2, C3986o.f10525a));
        while (oVar2 != null) {
            C3986o oVar3 = oVar2.d;
            oVar2.d = oVar;
            oVar = oVar2;
            oVar2 = oVar3;
        }
        while (oVar != null) {
            C3986o oVar4 = oVar.d;
            d(oVar.b, oVar.c);
            oVar = oVar4;
        }
    }

    public static void d(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException unused) {
            Level level = Level.SEVERE;
            String str = "RuntimeException while executing runnable " + runnable + " with executor " + executor;
            throw null;
        }
    }

    public static Object f(Future future) {
        Object obj;
        boolean z = false;
        while (true) {
            try {
                obj = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    @Override // defpackage.AbstractFutureC5208v90
    public final void a(Runnable runnable, Executor executor) {
        Objects.requireNonNull(runnable);
        Objects.requireNonNull(executor);
        C3986o oVar = this.f11242J;
        if (oVar != C3986o.f10525a) {
            C3986o oVar2 = new C3986o(runnable, executor);
            do {
                oVar2.d = oVar;
                if (!G.a(this, oVar, oVar2)) {
                    oVar = this.f11242J;
                } else {
                    return;
                }
            } while (oVar != C3986o.f10525a);
        }
        d(runnable, executor);
    }

    public final void b(StringBuilder sb) {
        String str;
        try {
            Object f = f(this);
            sb.append("SUCCESS, result=[");
            if (f == this) {
                str = "this future";
            } else {
                str = String.valueOf(f);
            }
            sb.append(str);
            sb.append("]");
        } catch (ExecutionException e) {
            sb.append("FAILURE, cause=[");
            sb.append(e.getCause());
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append("CANCELLED");
        } catch (RuntimeException e2) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e2.getClass());
            sb.append(" thrown from get()]");
        }
    }

    public final boolean cancel(boolean z) {
        C3473l lVar;
        Object obj = this.I;
        if ((obj == null) || false) {
            if (F) {
                lVar = new C3473l(z, new CancellationException("Future.cancel() was called."));
            } else if (z) {
                lVar = C3473l.f10319a;
            } else {
                lVar = C3473l.b;
            }
            if (G.b(this, obj, lVar)) {
                c(this);
                return true;
            }
        }
        return false;
    }

    public final Object e(Object obj) {
        if (obj instanceof C3473l) {
            Throwable th = ((C3473l) obj).d;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        } else if (obj instanceof C3815n) {
            throw new ExecutionException(((C3815n) obj).f10467a);
        } else if (obj == H) {
            return null;
        } else {
            return obj;
        }
    }

    public String g() {
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        StringBuilder i = AbstractC2531fV.i("remaining delay=[");
        i.append(((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS));
        i.append(" ms]");
        return i.toString();
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j, TimeUnit timeUnit) {
        long nanos = timeUnit.toNanos(j);
        if (!Thread.interrupted()) {
            Object obj = this.I;
            boolean z = true;
            if ((obj != null) && true) {
                return e(obj);
            }
            long nanoTime = nanos > 0 ? System.nanoTime() + nanos : 0;
            if (nanos >= 1000) {
                r rVar = this.K;
                if (rVar != r.f11175a) {
                    r rVar2 = new r();
                    do {
                        AbstractC3302k kVar = G;
                        kVar.d(rVar2, rVar);
                        if (kVar.c(this, rVar, rVar2)) {
                            do {
                                LockSupport.parkNanos(this, nanos);
                                if (!Thread.interrupted()) {
                                    Object obj2 = this.I;
                                    if ((obj2 != null) && true) {
                                        return e(obj2);
                                    }
                                    nanos = nanoTime - System.nanoTime();
                                } else {
                                    h(rVar2);
                                    throw new InterruptedException();
                                }
                            } while (nanos >= 1000);
                            h(rVar2);
                        } else {
                            rVar = this.K;
                        }
                    } while (rVar != r.f11175a);
                }
                return e(this.I);
            }
            while (nanos > 0) {
                Object obj3 = this.I;
                if ((obj3 != null) && true) {
                    return e(obj3);
                }
                if (!Thread.interrupted()) {
                    nanos = nanoTime - System.nanoTime();
                } else {
                    throw new InterruptedException();
                }
            }
            String sVar = toString();
            String timeUnit2 = timeUnit.toString();
            Locale locale = Locale.ROOT;
            String lowerCase = timeUnit2.toLowerCase(locale);
            String str = "Waited " + j + " " + timeUnit.toString().toLowerCase(locale);
            if (nanos + 1000 < 0) {
                String f = AbstractC2531fV.f(str, " (plus ");
                long j2 = -nanos;
                long convert = timeUnit.convert(j2, TimeUnit.NANOSECONDS);
                long nanos2 = j2 - timeUnit.toNanos(convert);
                int i = (convert > 0 ? 1 : (convert == 0 ? 0 : -1));
                if (i != 0 && nanos2 <= 1000) {
                    z = false;
                }
                if (i > 0) {
                    String str2 = f + convert + " " + lowerCase;
                    if (z) {
                        str2 = AbstractC2531fV.f(str2, ",");
                    }
                    f = AbstractC2531fV.f(str2, " ");
                }
                if (z) {
                    f = f + nanos2 + " nanoseconds ";
                }
                str = AbstractC2531fV.f(f, "delay)");
            }
            if (isDone()) {
                throw new TimeoutException(AbstractC2531fV.f(str, " but future completed as timeout expired"));
            }
            throw new TimeoutException(AbstractC2531fV.g(str, " for ", sVar));
        }
        throw new InterruptedException();
    }

    public final void h(r rVar) {
        rVar.b = null;
        while (true) {
            r rVar2 = this.K;
            if (rVar2 != r.f11175a) {
                r rVar3 = null;
                while (rVar2 != null) {
                    r rVar4 = rVar2.c;
                    if (rVar2.b != null) {
                        rVar3 = rVar2;
                    } else if (rVar3 != null) {
                        rVar3.c = rVar4;
                        if (rVar3.b == null) {
                        }
                    } else if (!G.c(this, rVar2, rVar4)) {
                    }
                    rVar2 = rVar4;
                }
                return;
            }
            return;
        }
    }

    public boolean i(Object obj) {
        if (obj == null) {
            obj = H;
        }
        if (!G.b(this, null, obj)) {
            return false;
        }
        c(this);
        return true;
    }

    public final boolean isCancelled() {
        return this.I instanceof C3473l;
    }

    public final boolean isDone() {
        return (this.I != null) & true;
    }

    public boolean j(Throwable th) {
        Objects.requireNonNull(th);
        if (!G.b(this, null, new C3815n(th))) {
            return false;
        }
        c(this);
        return true;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (this.I instanceof C3473l) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            b(sb);
        } else {
            try {
                str = g();
            } catch (RuntimeException e) {
                StringBuilder i = AbstractC2531fV.i("Exception thrown from implementation: ");
                i.append(e.getClass());
                str = i.toString();
            }
            if (str != null && !str.isEmpty()) {
                sb.append("PENDING, info=[");
                sb.append(str);
                sb.append("]");
            } else if (isDone()) {
                b(sb);
            } else {
                sb.append("PENDING");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.I;
            if ((obj2 != null) && true) {
                return e(obj2);
            }
            r rVar = this.K;
            if (rVar != r.f11175a) {
                r rVar2 = new r();
                do {
                    AbstractC3302k kVar = G;
                    kVar.d(rVar2, rVar);
                    if (kVar.c(this, rVar, rVar2)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.I;
                            } else {
                                h(rVar2);
                                throw new InterruptedException();
                            }
                        } while (!((obj != null) & true));
                        return e(obj);
                    }
                    rVar = this.K;
                } while (rVar != r.f11175a);
            }
            return e(this.I);
        }
        throw new InterruptedException();
    }
}
