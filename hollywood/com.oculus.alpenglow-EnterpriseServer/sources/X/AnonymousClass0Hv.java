package X;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* renamed from: X.0Hv  reason: invalid class name */
public final class AnonymousClass0Hv<TResult> {
    public static AnonymousClass0Hv<Boolean> A06 = new AnonymousClass0Hv<>((Boolean) false);
    public static AnonymousClass0Hv<?> A07 = new AnonymousClass0Hv<>((Object) null);
    public static AnonymousClass0Hv<Boolean> A08 = new AnonymousClass0Hv<>((Boolean) true);
    public static AnonymousClass0Hv<?> A09 = new AnonymousClass0Hv<>(true);
    public static final Executor A0A;
    public static final Executor A0B = C01450Hh.A03.A00;
    public static final ExecutorService A0C;
    public Exception A00;
    public TResult A01;
    public List<AnonymousClass0Hn<TResult, Void>> A02 = new ArrayList();
    public boolean A03;
    public boolean A04;
    public final Object A05 = new Object();

    static {
        AnonymousClass0Hj r1 = AnonymousClass0Hj.A03;
        A0C = r1.A01;
        A0A = r1.A00;
    }

    public static <TResult> AnonymousClass0Hv<TResult> A00(Callable<TResult> callable, Executor executor) {
        C01520Hw r2 = new C01520Hw();
        try {
            executor.execute(new RunnableC01500Ht(r2, callable));
        } catch (Exception e) {
            r2.A01(new C01470Ho(e));
        }
        return r2.A00;
    }

    public static void A01(AnonymousClass0Hv r3) {
        synchronized (r3.A05) {
            for (AnonymousClass0Hn<TResult, Void> r0 : r3.A02) {
                try {
                    r0.A8Z(r3);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            }
            r3.A02 = null;
        }
    }

    /* JADX WARN: Incorrect args count in method signature: <TContinuationResult:Ljava/lang/Object;TResult:Ljava/lang/Object;>(LX/0Hw<TTContinuationResult;>;LX/0Hn<TTResult;LX/0Hv<TTContinuationResult;>;>;LX/0Hv<TTResult;>;Ljava/util/concurrent/Executor;Lbolts/CancellationToken;)V */
    public static void A02(C01520Hw r2, AnonymousClass0Hn r3, AnonymousClass0Hv r4, Executor executor) {
        try {
            executor.execute(new RunnableC01490Hq(r2, r3, r4));
        } catch (Exception e) {
            r2.A01(new C01470Ho(e));
        }
    }

    public final Exception A03() {
        Exception exc;
        synchronized (this.A05) {
            exc = this.A00;
        }
        return exc;
    }

    /* JADX WARN: Incorrect return type in method signature: <TContinuationResult:Ljava/lang/Object;>(LX/0Hn<TTResult;TTContinuationResult;>;)LX/0Hv<TTContinuationResult;>; */
    public final void A04(AnonymousClass0Hn r7) {
        boolean z;
        Executor executor = A0A;
        C01520Hw r4 = new C01520Hw();
        synchronized (this.A05) {
            z = this.A04;
            if (!z) {
                this.A02.add(new C03310c0(this, r4, r7, executor));
            }
        }
        if (z) {
            try {
                executor.execute(new RunnableC01480Hp(r4, r7, this));
            } catch (Exception e) {
                r4.A01(new C01470Ho(e));
            }
        }
    }

    public final boolean A05() {
        boolean z;
        synchronized (this.A05) {
            z = false;
            if (A03() != null) {
                z = true;
            }
        }
        return z;
    }

    public final boolean A06() {
        boolean z;
        Object obj = this.A05;
        synchronized (obj) {
            z = false;
            if (!this.A04) {
                z = true;
                this.A04 = true;
                this.A03 = true;
                obj.notifyAll();
                A01(this);
            }
        }
        return z;
    }

    public final boolean A07(TResult tresult) {
        boolean z;
        Object obj = this.A05;
        synchronized (obj) {
            if (this.A04) {
                z = false;
            } else {
                z = true;
                this.A04 = true;
                this.A01 = tresult;
                obj.notifyAll();
                A01(this);
            }
        }
        return z;
    }

    public AnonymousClass0Hv() {
    }

    public AnonymousClass0Hv(TResult tresult) {
        A07(tresult);
    }

    public AnonymousClass0Hv(boolean z) {
        A06();
    }
}
