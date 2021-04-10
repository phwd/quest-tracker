package X;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public final class DB<TResult> {
    public static DB<?> A06 = new DB<>(true);
    public static DB<Boolean> A07 = new DB<>((Boolean) false);
    public static DB<?> A08 = new DB<>((Object) null);
    public static DB<Boolean> A09 = new DB<>((Boolean) true);
    public static final Executor A0A;
    public static final Executor A0B = C0076Cv.A03.A00;
    public static final ExecutorService A0C;
    public Exception A00;
    public TResult A01;
    public List<D3<TResult, Void>> A02 = new ArrayList();
    public boolean A03;
    public boolean A04;
    public final Object A05 = new Object();

    static {
        C0078Cx cx = C0078Cx.A03;
        A0C = cx.A01;
        A0A = cx.A00;
    }

    /* JADX WARN: Incorrect args count in method signature: <TResult:Ljava/lang/Object;>(Ljava/util/concurrent/Callable<TTResult;>;Ljava/util/concurrent/Executor;LX/Cy;)LX/DB<TTResult;>; */
    public static DB A00(Callable callable, Executor executor) {
        DC dc = new DC();
        try {
            executor.execute(new D9(dc, callable));
        } catch (Exception e) {
            dc.A01(new D4(e));
        }
        return dc.A00;
    }

    public static void A01(DB db) {
        synchronized (db.A05) {
            for (D3<TResult, Void> d3 : db.A02) {
                try {
                    d3.A5U(db);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            }
            db.A02 = null;
        }
    }

    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: X.DB<TResult>, X.DB<TContinuationResult> */
    public final <TContinuationResult> DB<TContinuationResult> A02(D3<TResult, DB<TContinuationResult>> d3) {
        boolean A052;
        Executor executor = A0A;
        DC dc = new DC();
        synchronized (this.A05) {
            A052 = A05();
            if (!A052) {
                this.A02.add(new C0277Zc(this, dc, d3, executor));
            }
        }
        if (A052) {
            try {
                executor.execute(new D6(dc, d3, this));
            } catch (Exception e) {
                dc.A01(new D4(e));
            }
        }
        return (DB<TResult>) dc.A00;
    }

    public final Exception A03() {
        Exception exc;
        synchronized (this.A05) {
            exc = this.A00;
        }
        return exc;
    }

    /* JADX WARN: Incorrect return type in method signature: <TContinuationResult:Ljava/lang/Object;>(LX/D3<TTResult;TTContinuationResult;>;)LX/DB<TTContinuationResult;>; */
    public final void A04(D3 d3) {
        boolean A052;
        Executor executor = A0A;
        DC dc = new DC();
        synchronized (this.A05) {
            A052 = A05();
            if (!A052) {
                this.A02.add(new C0278Zd(this, dc, d3, executor));
            }
        }
        if (A052) {
            try {
                executor.execute(new D5(dc, d3, this));
            } catch (Exception e) {
                dc.A01(new D4(e));
            }
        }
    }

    public final boolean A05() {
        boolean z;
        synchronized (this.A05) {
            z = this.A04;
        }
        return z;
    }

    public final boolean A06() {
        boolean z;
        synchronized (this.A05) {
            z = false;
            if (A03() != null) {
                z = true;
            }
        }
        return z;
    }

    public final boolean A07() {
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

    public final boolean A08(TResult tresult) {
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

    public DB() {
    }

    public DB(TResult tresult) {
        A08(tresult);
    }

    public DB(boolean z) {
        A07();
    }
}
