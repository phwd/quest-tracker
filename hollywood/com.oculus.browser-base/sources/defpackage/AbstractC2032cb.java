package defpackage;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;

/* renamed from: cb  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2032cb {

    /* renamed from: a  reason: collision with root package name */
    public static final Executor f9616a = new ExecutorC1463Ya();
    public static final Executor b = new ES0();
    public static final RejectedExecutionHandlerC1861bb c = new RejectedExecutionHandlerC1861bb(null);
    public final Callable d;
    public final C1681ab e;
    public volatile int f = 0;
    public final AtomicBoolean g = new AtomicBoolean();
    public final AtomicBoolean h = new AtomicBoolean();

    public AbstractC2032cb() {
        CallableC1524Za za = new CallableC1524Za(this);
        this.d = za;
        this.e = new C1681ab(this, za);
    }

    public static void a(AbstractC2032cb cbVar, Object obj) {
        if (cbVar.h.get()) {
            return;
        }
        if (cbVar instanceof AbstractC0500Ie) {
            cbVar.f = 2;
        } else {
            ThreadUtils.d(new RunnableC1402Xa(cbVar, obj));
        }
    }

    public final boolean b(boolean z) {
        this.g.set(true);
        return this.e.cancel(z);
    }

    public abstract Object c();

    public final AbstractC2032cb d(Executor executor) {
        f();
        executor.execute(this.e);
        return this;
    }

    public final AbstractC2032cb e(AbstractC2387ef1 ef1) {
        f();
        ef1.b(this.e);
        return this;
    }

    public final void f() {
        if (this.f != 0) {
            int i = this.f;
            if (i == 1) {
                throw new IllegalStateException("Cannot execute task: the task is already running.");
            } else if (i == 2) {
                throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.f = 1;
        l();
    }

    public final Object g() {
        String str;
        if (this.f == 2 || !ThreadUtils.i()) {
            return this.e.get();
        }
        StackTraceElement[] stackTrace = new Exception().getStackTrace();
        if (stackTrace.length > 1) {
            str = stackTrace[1].getClassName() + '.' + stackTrace[1].getMethodName() + '.';
        } else {
            str = "";
        }
        TraceEvent j0 = TraceEvent.j0(str + "AsyncTask.get");
        try {
            Object obj = this.e.get();
            if (j0 == null) {
                return obj;
            }
            j0.close();
            return obj;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public final boolean h() {
        return this.g.get();
    }

    public void i() {
    }

    public void j(Object obj) {
        i();
    }

    public abstract void k(Object obj);

    public void l() {
    }
}
