package defpackage;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import org.chromium.base.TraceEvent;

/* renamed from: ab  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1681ab extends FutureTask {
    public final /* synthetic */ AbstractC2032cb F;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1681ab(AbstractC2032cb cbVar, Callable callable) {
        super(callable);
        this.F = cbVar;
    }

    public void done() {
        try {
            AbstractC2032cb.a(this.F, get());
        } catch (InterruptedException e) {
            AbstractC1220Ua0.f("AsyncTask", e.toString(), new Object[0]);
        } catch (ExecutionException e2) {
            throw new RuntimeException("An error occurred while executing doInBackground()", e2.getCause());
        } catch (CancellationException unused) {
            AbstractC2032cb.a(this.F, null);
        }
    }

    public void run() {
        StringBuilder i = AbstractC2531fV.i("AsyncTask.run: ");
        i.append(this.F.e.F.getClass());
        TraceEvent j0 = TraceEvent.j0(i.toString());
        try {
            super.run();
            if (j0 != null) {
                j0.close();
                return;
            }
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
