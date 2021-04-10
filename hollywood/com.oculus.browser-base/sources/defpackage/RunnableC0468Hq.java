package defpackage;

import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.base.LocaleUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;

/* renamed from: Hq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0468Hq implements Runnable {
    public final C1321Vq F;
    public final AbstractC3083ik G;

    public RunnableC0468Hq(C1321Vq vq, AbstractC3083ik ikVar) {
        this.F = vq;
        this.G = ikVar;
    }

    public void run() {
        C1321Vq vq = this.F;
        AbstractC3083ik ikVar = this.G;
        Objects.requireNonNull(vq);
        if (!ikVar.v()) {
            Object obj = ThreadUtils.f10596a;
            if (!vq.f) {
                C3881nM0.b().c = Zo1.e;
                C3881nM0 b = C3881nM0.b();
                String b2 = LocaleUtils.b(ContextUtils.getApplicationContext().getResources().getString(R.string.f50260_resource_name_obfuscated_RES_2131952343));
                if (b.b == null) {
                    if (C3881nM0.e()) {
                        PostTask.b(C3070if1.f10154a, new RunnableC3368kM0(b), 0);
                    } else {
                        RunnableC3710mM0 mm0 = new RunnableC3710mM0(b, b2);
                        b.b = mm0;
                        PostTask.b(C3070if1.e, mm0, 0);
                    }
                }
                vq.f = true;
            }
            ikVar.K();
        }
    }
}
