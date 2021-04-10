package defpackage;

import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;

/* renamed from: lM0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3539lM0 implements Runnable {
    public final RunnableC3710mM0 F;

    public RunnableC3539lM0(RunnableC3710mM0 mm0) {
        this.F = mm0;
    }

    public void run() {
        RunnableC3710mM0 mm0 = this.F;
        Objects.requireNonNull(mm0);
        TraceEvent j0 = TraceEvent.j0("ResourceExtractor.ExtractTask.onPostExecute");
        try {
            Object obj = ThreadUtils.f10596a;
            for (int i = 0; i < mm0.F.size(); i++) {
                ((Runnable) mm0.F.get(i)).run();
            }
            mm0.F.clear();
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
