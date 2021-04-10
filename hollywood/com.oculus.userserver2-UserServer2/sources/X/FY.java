package X;

import bolts.Continuation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public final class FY<TResult> {
    public static FY<?> A05 = new FY<>(true);
    public static FY<Boolean> A06 = new FY<>((Boolean) false);
    public static FY<?> A07 = new FY<>((Object) null);
    public static FY<Boolean> A08 = new FY<>((Boolean) true);
    public static final Executor A09 = FL.A03.A00;
    public static final ExecutorService A0A;
    public static final Executor A0B;
    public Exception A00;
    public TResult A01;
    public boolean A02;
    public List<Continuation<TResult, Void>> A03 = new ArrayList();
    public final Object A04 = new Object();

    static {
        FN fn = FN.A03;
        A0A = fn.A01;
        A0B = fn.A00;
    }

    public static void A00(FY fy) {
        synchronized (fy.A04) {
            Iterator<Continuation<TResult, Void>> it = fy.A03.iterator();
            if (it.hasNext()) {
                it.next();
                try {
                    throw null;
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            } else {
                fy.A03 = null;
            }
        }
    }

    public final Exception A01() {
        Exception exc;
        synchronized (this.A04) {
            exc = this.A00;
        }
        return exc;
    }

    public final boolean A02() {
        boolean z;
        Object obj = this.A04;
        synchronized (obj) {
            z = false;
            if (!this.A02) {
                z = true;
                this.A02 = true;
                obj.notifyAll();
                A00(this);
            }
        }
        return z;
    }

    public final boolean A03(long j, TimeUnit timeUnit) throws InterruptedException {
        boolean z;
        Object obj = this.A04;
        synchronized (obj) {
            if (!this.A02) {
                obj.wait(timeUnit.toMillis(j));
            }
            z = this.A02;
        }
        return z;
    }

    public final boolean A04(TResult tresult) {
        boolean z;
        Object obj = this.A04;
        synchronized (obj) {
            if (this.A02) {
                z = false;
            } else {
                z = true;
                this.A02 = true;
                this.A01 = tresult;
                obj.notifyAll();
                A00(this);
            }
        }
        return z;
    }

    public FY() {
    }

    public FY(TResult tresult) {
        A04(tresult);
    }

    public FY(boolean z) {
        A02();
    }
}
