package X;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.0DC  reason: invalid class name */
public final class AnonymousClass0DC<TResult> {
    public static AnonymousClass0DC<?> A06 = new AnonymousClass0DC<>(true);
    public static AnonymousClass0DC<Boolean> A07 = new AnonymousClass0DC<>((Boolean) false);
    public static AnonymousClass0DC<?> A08 = new AnonymousClass0DC<>((Object) null);
    public static AnonymousClass0DC<Boolean> A09 = new AnonymousClass0DC<>((Boolean) true);
    public static final Executor A0A;
    public static final Executor A0B = C00750Cw.A03.A00;
    public static final ExecutorService A0C;
    public Exception A00;
    public boolean A01;
    public TResult A02;
    public List<AnonymousClass0D4<TResult, Void>> A03 = new ArrayList();
    public boolean A04;
    public final Object A05 = new Object();

    public final <TContinuationResult> AnonymousClass0DC<TContinuationResult> A0C(AnonymousClass0D4<TResult, TContinuationResult> r3, Executor executor) {
        return A02(this, new C07120rA(this, null, r3), executor);
    }

    static {
        AnonymousClass0Cy r1 = AnonymousClass0Cy.A03;
        A0C = r1.A01;
        A0A = r1.A00;
    }

    /* JADX DEBUG: Type inference failed for r0v7. Raw type applied. Possible types: X.0DC<?>, X.0DC<java.lang.Void> */
    /* JADX DEBUG: Type inference failed for r0v10. Raw type applied. Possible types: X.0DC<?>, X.0DC<java.lang.Void> */
    public static AnonymousClass0DC<Void> A00(long j, ScheduledExecutorService scheduledExecutorService, AnonymousClass0Cz r8) {
        if (r8 != null && r8.A00.A03()) {
            return A06;
        }
        if (j <= 0) {
            return A08;
        }
        AnonymousClass0DD r4 = new AnonymousClass0DD();
        ScheduledFuture<?> schedule = scheduledExecutorService.schedule(new AnonymousClass0D8(r4), j, TimeUnit.MILLISECONDS);
        if (r8 != null) {
            AnonymousClass0D9 r0 = new AnonymousClass0D9(schedule, r4);
            AnonymousClass0D2 r3 = r8.A00;
            synchronized (r3.A02) {
                AnonymousClass0D2.A00(r3);
                AnonymousClass0D0 r1 = new AnonymousClass0D0(r3, r0);
                if (r3.A00) {
                    r1.A00();
                } else {
                    r3.A03.add(r1);
                }
            }
        }
        return r4.A00;
    }

    /* JADX WARN: Incorrect types in method signature: <TContinuationResult:Ljava/lang/Object;>(LX/0D4<TTResult;TTContinuationResult;>;Ljava/util/concurrent/Executor;LX/0Cz;)LX/0DC<TTContinuationResult;>; */
    public static final AnonymousClass0DC A01(AnonymousClass0DC r5, AnonymousClass0D4 r6, Executor executor) {
        boolean A0J;
        AnonymousClass0DD r4 = new AnonymousClass0DD();
        synchronized (r5.A05) {
            A0J = r5.A0J();
            if (!A0J) {
                r5.A03.add(new C07140rG(r5, r4, r6, executor));
            }
        }
        if (A0J) {
            try {
                executor.execute(new AnonymousClass0D6(r4, r6, r5));
            } catch (Exception e) {
                r4.A01(new AnonymousClass0D5(e));
            }
        }
        return r4.A00;
    }

    /* JADX WARN: Incorrect types in method signature: <TContinuationResult:Ljava/lang/Object;>(LX/0D4<TTResult;LX/0DC<TTContinuationResult;>;>;Ljava/util/concurrent/Executor;LX/0Cz;)LX/0DC<TTContinuationResult;>; */
    public static final AnonymousClass0DC A02(AnonymousClass0DC r5, AnonymousClass0D4 r6, Executor executor) {
        boolean A0J;
        AnonymousClass0DD r4 = new AnonymousClass0DD();
        synchronized (r5.A05) {
            A0J = r5.A0J();
            if (!A0J) {
                r5.A03.add(new AnonymousClass0rD(r5, r4, r6, executor));
            }
        }
        if (A0J) {
            try {
                executor.execute(new AnonymousClass0D7(r4, r6, r5));
            } catch (Exception e) {
                r4.A01(new AnonymousClass0D5(e));
            }
        }
        return r4.A00;
    }

    public static <TResult> AnonymousClass0DC<TResult> A03(Exception exc) {
        AnonymousClass0DD r0 = new AnonymousClass0DD();
        r0.A01(exc);
        return r0.A00;
    }

    public static <TResult> AnonymousClass0DC<TResult> A04(TResult tresult) {
        if (tresult == null) {
            return (AnonymousClass0DC<TResult>) A08;
        }
        if (tresult instanceof Boolean) {
            return tresult.booleanValue() ? (AnonymousClass0DC<TResult>) A09 : (AnonymousClass0DC<TResult>) A07;
        }
        AnonymousClass0DD r0 = new AnonymousClass0DD();
        r0.A02(tresult);
        return r0.A00;
    }

    public static <TResult> AnonymousClass0DC<TResult> A06(Callable<TResult> callable) {
        return A07(callable, A0C, null);
    }

    public static <TResult> AnonymousClass0DC<TResult> A07(Callable<TResult> callable, Executor executor, AnonymousClass0Cz r5) {
        AnonymousClass0DD r2 = new AnonymousClass0DD();
        try {
            executor.execute(new AnonymousClass0DA(r5, r2, callable));
        } catch (Exception e) {
            r2.A01(new AnonymousClass0D5(e));
        }
        return r2.A00;
    }

    public static void A08(AnonymousClass0DC r3) {
        synchronized (r3.A05) {
            for (AnonymousClass0D4<TResult, Void> r0 : r3.A03) {
                try {
                    r0.then(r3);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            }
            r3.A03 = null;
        }
    }

    public final <TContinuationResult> AnonymousClass0DC<TContinuationResult> A09(AnonymousClass0D4<TResult, TContinuationResult> r2) {
        return A01(this, r2, A0A);
    }

    public final <TContinuationResult> AnonymousClass0DC<TContinuationResult> A0A(AnonymousClass0D4<TResult, AnonymousClass0DC<TContinuationResult>> r2) {
        return A02(this, r2, A0A);
    }

    public final <TContinuationResult> AnonymousClass0DC<TContinuationResult> A0B(AnonymousClass0D4<TResult, TContinuationResult> r4) {
        return A02(this, new C07120rA(this, null, r4), A0A);
    }

    public final <TContinuationResult> AnonymousClass0DC<TContinuationResult> A0D(AnonymousClass0D4<TResult, AnonymousClass0DC<TContinuationResult>> r2, Executor executor) {
        return A02(this, new AnonymousClass0r8(this, r2), executor);
    }

    public final AnonymousClass0DC<Void> A0E(Callable<Boolean> callable, AnonymousClass0D4<Void, AnonymousClass0DC<Void>> r8) {
        Executor executor = A0A;
        AnonymousClass0D3 r5 = new AnonymousClass0D3();
        r5.A00 = (T) new AnonymousClass0r1(this, callable, r8, executor, r5);
        return A02(A0A(new C07110r6(this)), r5.A00, executor);
    }

    public final Exception A0F() {
        Exception exc;
        synchronized (this.A05) {
            exc = this.A00;
        }
        return exc;
    }

    public final TResult A0G() {
        TResult tresult;
        synchronized (this.A05) {
            tresult = this.A02;
        }
        return tresult;
    }

    public final void A0H() throws InterruptedException {
        Object obj = this.A05;
        synchronized (obj) {
            if (!A0J()) {
                obj.wait();
            }
        }
    }

    public final boolean A0I() {
        boolean z;
        synchronized (this.A05) {
            z = this.A04;
        }
        return z;
    }

    public final boolean A0J() {
        boolean z;
        synchronized (this.A05) {
            z = this.A01;
        }
        return z;
    }

    public final boolean A0K() {
        boolean z;
        synchronized (this.A05) {
            z = false;
            if (A0F() != null) {
                z = true;
            }
        }
        return z;
    }

    public final boolean A0L() {
        boolean z;
        Object obj = this.A05;
        synchronized (obj) {
            z = false;
            if (!this.A01) {
                z = true;
                this.A01 = true;
                this.A04 = true;
                obj.notifyAll();
                A08(this);
            }
        }
        return z;
    }

    public final boolean A0M(long j, TimeUnit timeUnit) throws InterruptedException {
        boolean A0J;
        Object obj = this.A05;
        synchronized (obj) {
            if (!A0J()) {
                obj.wait(timeUnit.toMillis(j));
            }
            A0J = A0J();
        }
        return A0J;
    }

    public final boolean A0N(TResult tresult) {
        boolean z;
        Object obj = this.A05;
        synchronized (obj) {
            if (this.A01) {
                z = false;
            } else {
                z = true;
                this.A01 = true;
                this.A02 = tresult;
                obj.notifyAll();
                A08(this);
            }
        }
        return z;
    }

    /* JADX DEBUG: Type inference failed for r0v7. Raw type applied. Possible types: X.0DC<?>, X.0DC<java.lang.Void> */
    public static AnonymousClass0DC<Void> A05(Collection<? extends AnonymousClass0DC<?>> collection) {
        if (collection.size() == 0) {
            return A08;
        }
        AnonymousClass0DD r7 = new AnonymousClass0DD();
        ArrayList arrayList = new ArrayList();
        Object obj = new Object();
        AtomicInteger atomicInteger = new AtomicInteger(collection.size());
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        Iterator<? extends AnonymousClass0DC<?>> it = collection.iterator();
        while (it.hasNext()) {
            ((AnonymousClass0DC) it.next()).A09(new C07070r2(obj, arrayList, atomicBoolean, atomicInteger, r7));
        }
        return r7.A00;
    }

    public AnonymousClass0DC() {
    }

    public AnonymousClass0DC(TResult tresult) {
        A0N(tresult);
    }

    public AnonymousClass0DC(boolean z) {
        A0L();
    }
}
