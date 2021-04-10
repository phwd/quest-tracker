package defpackage;

import org.chromium.base.ThreadUtils;

/* renamed from: qm0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4458qm0 implements Runnable {
    public final /* synthetic */ AbstractC0804Ne F;
    public final /* synthetic */ AbstractC4798sm0 G;

    public RunnableC4458qm0(AbstractC4798sm0 sm0, AbstractC0804Ne ne) {
        this.G = sm0;
        this.F = ne;
    }

    public void run() {
        Object obj = ThreadUtils.f10596a;
        if (!this.G.b) {
            this.F.a(true);
        }
    }
}
