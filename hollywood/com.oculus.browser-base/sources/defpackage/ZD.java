package defpackage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import org.chromium.base.ThreadUtils;

/* renamed from: ZD  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ZD implements AbstractC0624Ke1 {

    /* renamed from: a  reason: collision with root package name */
    public final Map f9329a = new HashMap();

    @Override // defpackage.AbstractC0624Ke1
    public BS0 a(C3070if1 if1) {
        if (if1.o) {
            return e();
        }
        return new CS0(if1);
    }

    @Override // defpackage.AbstractC0624Ke1
    public synchronized void b(C3070if1 if1, Runnable runnable, long j) {
        if (if1.m != 0) {
            c(if1).a(runnable, j);
        } else {
            AbstractC2387ef1 ef1 = (AbstractC2387ef1) this.f9329a.get(if1);
            if (ef1 == null) {
                ef1 = c(if1);
                this.f9329a.put(if1, ef1);
            }
            ef1.a(runnable, j);
        }
    }

    @Override // defpackage.AbstractC0624Ke1
    public AbstractC2387ef1 c(C3070if1 if1) {
        if (if1.o) {
            return e();
        }
        C2900hf1 hf1 = new C2900hf1(if1, "TaskRunnerImpl", 0);
        C2900hf1.d();
        return hf1;
    }

    @Override // defpackage.AbstractC0624Ke1
    public boolean d(C3070if1 if1) {
        return false;
    }

    public final synchronized C5146up e() {
        FutureTask futureTask;
        XD xd = new XD();
        Object obj = ThreadUtils.f10596a;
        try {
            futureTask = new FutureTask(xd);
            ThreadUtils.f(futureTask);
            try {
            } catch (InterruptedException e) {
                throw new RuntimeException("Interrupted waiting for callable", e);
            }
        } catch (ExecutionException e2) {
            throw new RuntimeException("Error occurred waiting for callable", e2);
        }
        return (C5146up) futureTask.get();
    }
}
