package X;

import android.os.Handler;
import android.os.Message;
import io.reactivex.annotations.NonNull;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* renamed from: X.1xQ  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC12411xQ implements AbstractC12271xB {
    @NonNull
    public final AbstractC12271xB A01(@NonNull Runnable runnable) {
        if (this instanceof C12601xr) {
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            return C12601xr.A00((C12601xr) this, runnable, timeUnit.convert(System.currentTimeMillis(), timeUnit));
        } else if (this instanceof AnonymousClass1x2) {
            return A02(runnable, 0, null);
        } else {
            if (this instanceof RunnableC12351xK) {
                RunnableC12351xK r3 = (RunnableC12351xK) this;
                if (r3.A04) {
                    return AnonymousClass1z1.INSTANCE;
                }
                AnonymousClass219.A01(runnable, "run is null");
                AnonymousClass1xX r1 = new AnonymousClass1xX(runnable);
                C12431xa<Runnable> r2 = r3.A01;
                r2.offer(r1);
                if (r3.A03.getAndIncrement() != 0) {
                    return r1;
                }
                try {
                    r3.A02.execute(r3);
                    return r1;
                } catch (RejectedExecutionException e) {
                    r3.A04 = true;
                    r2.clear();
                    AnonymousClass1y3.A01(e);
                    return AnonymousClass1z1.INSTANCE;
                }
            } else if (!(this instanceof AnonymousClass1x4)) {
                return A02(runnable, 0, TimeUnit.NANOSECONDS);
            } else {
                AnonymousClass1x4 r12 = (AnonymousClass1x4) this;
                if (r12.A04) {
                    return AnonymousClass1z1.INSTANCE;
                }
                return r12.A02.A03(runnable, 0, TimeUnit.MILLISECONDS, r12.A01);
            }
        }
    }

    @NonNull
    public final AbstractC12271xB A02(@NonNull Runnable runnable, long j, @NonNull TimeUnit timeUnit) {
        Future<?> future;
        if (this instanceof C12601xr) {
            C12601xr r4 = (C12601xr) this;
            TimeUnit timeUnit2 = TimeUnit.MILLISECONDS;
            long convert = timeUnit2.convert(System.currentTimeMillis(), timeUnit2) + timeUnit.toMillis(j);
            return C12601xr.A00(r4, new RunnableC12611xs(runnable, r4, convert), convert);
        } else if (!(this instanceof AnonymousClass1x3)) {
            if (this instanceof AnonymousClass1x2) {
                AnonymousClass1x2 r5 = (AnonymousClass1x2) this;
                if (!r5.A01) {
                    return r5.A03(runnable, j, timeUnit, null);
                }
            } else if (!(this instanceof AnonymousClass1xT)) {
                if (this instanceof RunnableC12351xK) {
                    RunnableC12351xK r52 = (RunnableC12351xK) this;
                    if (j <= 0) {
                        return r52.A01(runnable);
                    }
                    if (!r52.A04) {
                        AnonymousClass1xW r3 = new AnonymousClass1xW();
                        AnonymousClass1xW r42 = new AnonymousClass1xW(r3);
                        AnonymousClass219.A01(runnable, "run is null");
                        RunnableC12401xP r1 = new RunnableC12401xP(r52, r42, runnable);
                        AnonymousClass1xU r0 = r52.A00;
                        RunnableC12241x8 r2 = new RunnableC12241x8(r1, r0);
                        r0.A1D(r2);
                        Executor executor = r52.A02;
                        if (executor instanceof ScheduledExecutorService) {
                            try {
                                r2.A00(((ScheduledExecutorService) executor).schedule((Callable) r2, j, timeUnit));
                            } catch (RejectedExecutionException e) {
                                r52.A04 = true;
                                AnonymousClass1y3.A01(e);
                                return AnonymousClass1z1.INSTANCE;
                            }
                        } else {
                            r2.A00(new FutureC12281xC(AnonymousClass1xJ.A01.A03(r2, j, timeUnit)));
                        }
                        EnumC12511xi.replace(r3, r2);
                        return r42;
                    }
                } else if (!(this instanceof AnonymousClass1x4)) {
                    C12661xx r53 = (C12661xx) this;
                    if (runnable == null) {
                        throw new NullPointerException("run == null");
                    } else if (timeUnit == null) {
                        throw new NullPointerException("unit == null");
                    } else if (!r53.A01) {
                        Handler handler = r53.A00;
                        RunnableC12681xz r43 = new RunnableC12681xz(handler, runnable);
                        Message obtain = Message.obtain(handler, r43);
                        obtain.obj = r53;
                        handler.sendMessageDelayed(obtain, timeUnit.toMillis(j));
                        if (!r53.A01) {
                            return r43;
                        }
                        handler.removeCallbacks(r43);
                    }
                } else {
                    AnonymousClass1x4 r12 = (AnonymousClass1x4) this;
                    if (!r12.A04) {
                        return r12.A02.A03(runnable, j, timeUnit, r12.A00);
                    }
                }
                return AnonymousClass1z1.INSTANCE;
            } else {
                AnonymousClass1xT r13 = (AnonymousClass1xT) this;
                AnonymousClass1xU r10 = r13.A01;
                if (!r10.A01) {
                    return r13.A02.A03(runnable, j, timeUnit, r10);
                }
            }
            return AnonymousClass1z1.INSTANCE;
        } else {
            AnonymousClass1x3 r44 = (AnonymousClass1x3) this;
            if (r44.A02) {
                return AnonymousClass1z1.INSTANCE;
            }
            AnonymousClass219.A01(runnable, "run is null");
            AnonymousClass1xU r02 = r44.A00;
            RunnableC12241x8 r32 = new RunnableC12241x8(runnable, r02);
            r02.A1D(r32);
            if (j <= 0) {
                try {
                    future = r44.A01.submit((Callable) r32);
                } catch (RejectedExecutionException e2) {
                    r44.dispose();
                    AnonymousClass1y3.A01(e2);
                    return AnonymousClass1z1.INSTANCE;
                }
            } else {
                future = r44.A01.schedule((Callable) r32, j, timeUnit);
            }
            r32.A00(future);
            return r32;
        }
    }
}
