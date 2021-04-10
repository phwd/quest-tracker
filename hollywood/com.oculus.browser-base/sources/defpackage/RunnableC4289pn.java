package defpackage;

import android.util.Pair;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;

/* renamed from: pn  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4289pn implements Runnable {
    public final /* synthetic */ C4630rn F;

    public RunnableC4289pn(C4630rn rnVar) {
        this.F = rnVar;
    }

    public void run() {
        if (!this.F.f11221a.isEmpty()) {
            Pair pair = (Pair) this.F.f11221a.pop();
            StringBuilder i = AbstractC2531fV.i("ChainedTask.run: ");
            i.append(((Runnable) pair.second).getClass().getName());
            TraceEvent j0 = TraceEvent.j0(i.toString());
            try {
                ((Runnable) pair.second).run();
                if (j0 != null) {
                    j0.close();
                }
                if (!this.F.f11221a.isEmpty()) {
                    PostTask.b((C3070if1) ((Pair) this.F.f11221a.peek()).first, this, 0);
                    return;
                }
                return;
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        } else {
            return;
        }
        throw th;
    }
}
