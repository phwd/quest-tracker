package X;

import android.os.Handler;
import android.os.Message;
import io.reactivex.annotations.NonNull;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* renamed from: X.1xL  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC12361xL {
    public static final long A00 = TimeUnit.MINUTES.toNanos(Long.getLong("rx2.scheduler.drift-tolerance", 15).longValue());

    @NonNull
    public final AbstractC12411xQ A00() {
        if (this instanceof C12591xq) {
            return new C12601xr();
        }
        if (this instanceof C12191wy) {
            return new AnonymousClass1x3(((C12191wy) this).A01.get());
        }
        if (this instanceof C12211x5) {
            return new AnonymousClass1x2(((C12211x5) this).A00);
        }
        if (this instanceof C12421xS) {
            return new AnonymousClass1xT(((C12421xS) this).A01.get());
        }
        if (this instanceof AnonymousClass1xJ) {
            return new RunnableC12351xK(((AnonymousClass1xJ) this).A00);
        }
        if (!(this instanceof C12201wz)) {
            return new C12661xx(((C12671xy) this).A00);
        }
        return new AnonymousClass1x4(((C12201wz) this).A01.get().A00());
    }

    @NonNull
    public final AbstractC12271xB A01(@NonNull Runnable runnable) {
        if (this instanceof C12591xq) {
            AnonymousClass219.A01(runnable, "run is null");
            runnable.run();
            return AnonymousClass1z1.INSTANCE;
        } else if (!(this instanceof AnonymousClass1xJ)) {
            return A03(runnable, 0, TimeUnit.NANOSECONDS);
        } else {
            AnonymousClass1xJ r1 = (AnonymousClass1xJ) this;
            AnonymousClass219.A01(runnable, "run is null");
            try {
                Executor executor = r1.A00;
                if (executor instanceof ExecutorService) {
                    CallableC12161wv r0 = new CallableC12161wv(runnable);
                    r0.A00(((ExecutorService) executor).submit(r0));
                    return r0;
                }
                AnonymousClass1xX r02 = new AnonymousClass1xX(runnable);
                executor.execute(r02);
                return r02;
            } catch (RejectedExecutionException e) {
                AnonymousClass1y3.A01(e);
                return AnonymousClass1z1.INSTANCE;
            }
        }
    }

    @NonNull
    public final AbstractC12271xB A03(@NonNull Runnable runnable, long j, @NonNull TimeUnit timeUnit) {
        Future<?> future;
        Future<?> future2;
        if (this instanceof C12591xq) {
            try {
                timeUnit.sleep(j);
                AnonymousClass219.A01(runnable, "run is null");
                runnable.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                AnonymousClass1y3.A01(e);
            }
            return AnonymousClass1z1.INSTANCE;
        } else if (this instanceof C12191wy) {
            C12191wy r4 = (C12191wy) this;
            AnonymousClass219.A01(runnable, "run is null");
            CallableC12161wv r0 = new CallableC12161wv(runnable);
            if (j <= 0) {
                try {
                    future = r4.A01.get().submit(r0);
                } catch (RejectedExecutionException e2) {
                    AnonymousClass1y3.A01(e2);
                    return AnonymousClass1z1.INSTANCE;
                }
            } else {
                future = r4.A01.get().schedule(r0, j, timeUnit);
            }
            r0.A00(future);
            return r0;
        } else if (this instanceof AnonymousClass1xJ) {
            AnonymousClass1xJ r3 = (AnonymousClass1xJ) this;
            AnonymousClass219.A01(runnable, "run is null");
            Executor executor = r3.A00;
            if (executor instanceof ScheduledExecutorService) {
                try {
                    CallableC12161wv r2 = new CallableC12161wv(runnable);
                    r2.A00(((ScheduledExecutorService) executor).schedule(r2, j, timeUnit));
                    return r2;
                } catch (RejectedExecutionException e3) {
                    AnonymousClass1y3.A01(e3);
                    return AnonymousClass1z1.INSTANCE;
                }
            } else {
                RunnableC12381xN r22 = new RunnableC12381xN(runnable);
                EnumC12511xi.replace(r22.timed, AnonymousClass1xJ.A01.A03(new RunnableC12391xO(r3, r22), j, timeUnit));
                return r22;
            }
        } else if (this instanceof C12201wz) {
            AnonymousClass1x1 A002 = ((C12201wz) this).A01.get().A00();
            AnonymousClass219.A01(runnable, "run is null");
            CallableC12161wv r32 = new CallableC12161wv(runnable);
            if (j <= 0) {
                try {
                    future2 = A002.A00.submit(r32);
                } catch (RejectedExecutionException e4) {
                    AnonymousClass1y3.A01(e4);
                    return AnonymousClass1z1.INSTANCE;
                }
            } else {
                future2 = A002.A00.schedule(r32, j, timeUnit);
            }
            r32.A00(future2);
            return r32;
        } else if (!(this instanceof C12671xy)) {
            AbstractC12411xQ A003 = A00();
            AnonymousClass219.A01(runnable, "run is null");
            RunnableC12221x6 r02 = new RunnableC12221x6(runnable, A003);
            A003.A02(r02, j, timeUnit);
            return r02;
        } else {
            C12671xy r03 = (C12671xy) this;
            if (runnable == null) {
                throw new NullPointerException("run == null");
            } else if (timeUnit != null) {
                Handler handler = r03.A00;
                RunnableC12681xz r33 = new RunnableC12681xz(handler, runnable);
                handler.sendMessageDelayed(Message.obtain(handler, r33), timeUnit.toMillis(j));
                return r33;
            } else {
                throw new NullPointerException("unit == null");
            }
        }
    }

    @NonNull
    public AbstractC12271xB A02(@NonNull Runnable runnable, long j, long j2, @NonNull TimeUnit timeUnit) {
        AbstractC12411xQ A002 = A00();
        AnonymousClass219.A01(runnable, "run is null");
        RunnableC12291xD r9 = new RunnableC12291xD(runnable, A002);
        AnonymousClass1xW r2 = new AnonymousClass1xW();
        AnonymousClass1xW r12 = new AnonymousClass1xW(r2);
        long nanos = timeUnit.toNanos(j2);
        long convert = TimeUnit.NANOSECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        AbstractC12271xB A02 = A002.A02(new RunnableC12371xM(A002, convert + timeUnit.toNanos(j), r9, convert, r12, nanos), j, timeUnit);
        AnonymousClass1z1 r0 = AnonymousClass1z1.INSTANCE;
        if (A02 != r0) {
            EnumC12511xi.replace(r2, A02);
            A02 = r12;
        }
        if (A02 == r0) {
            return A02;
        }
        return r9;
    }
}
