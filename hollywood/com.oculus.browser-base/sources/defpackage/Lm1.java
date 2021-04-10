package defpackage;

import J.N;
import java.io.File;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import org.chromium.base.ContextUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.tracing.settings.TracingSettings;
import org.chromium.content.browser.TracingControllerAndroidImpl;

/* renamed from: Lm1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Lm1 {

    /* renamed from: a  reason: collision with root package name */
    public static Lm1 f8438a;
    public Mm1 b;
    public C1322Vq0 c = new C1322Vq0();
    public int d = 0;
    public Set e;
    public File f;

    public Lm1() {
        PostTask.b(C3070if1.b, new Bm1(), 0);
    }

    public static Lm1 a() {
        if (f8438a == null) {
            Lm1 lm1 = new Lm1();
            f8438a = lm1;
            Objects.requireNonNull(lm1);
            TracingControllerAndroidImpl tracingControllerAndroidImpl = new TracingControllerAndroidImpl(ContextUtils.getApplicationContext());
            lm1.b = tracingControllerAndroidImpl;
            Cm1 cm1 = new Cm1(lm1);
            tracingControllerAndroidImpl.a();
            N.MdRNuqnW(tracingControllerAndroidImpl.i, tracingControllerAndroidImpl, cm1);
        }
        return f8438a;
    }

    public final void b(int i) {
        this.d = i;
        if (i == 1) {
            Om1.c();
            File file = this.f;
            if (file != null) {
                PostTask.b(C3070if1.b, new Km1(file), 0);
                this.f = null;
            }
            TracingControllerAndroidImpl tracingControllerAndroidImpl = (TracingControllerAndroidImpl) this.b;
            long j = tracingControllerAndroidImpl.i;
            if (j != 0) {
                N.MLYQdwUF(j, tracingControllerAndroidImpl);
                tracingControllerAndroidImpl.i = 0;
            }
            this.b = null;
        }
        Iterator it = this.c.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((TracingSettings) ((Jm1) uq0.next())).q1();
            } else {
                return;
            }
        }
    }

    public final void c() {
        if (this.d == 3) {
            Mm1 mm1 = this.b;
            Dm1 dm1 = new Dm1(this);
            TracingControllerAndroidImpl tracingControllerAndroidImpl = (TracingControllerAndroidImpl) mm1;
            tracingControllerAndroidImpl.a();
            N.MkLMghix(tracingControllerAndroidImpl.i, tracingControllerAndroidImpl, dm1);
        }
    }
}
