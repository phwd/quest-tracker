package defpackage;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: o90  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4015o90 implements Runnable {
    public final /* synthetic */ AbstractC4186p90 F;

    public RunnableC4015o90(AbstractC4186p90 p90) {
        this.F = p90;
    }

    public void run() {
        C1823bJ bJVar = this.F.f11052J;
        if (bJVar != null) {
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            if (bJVar.isAttachedToWindow() && this.F.f11052J.getCount() > this.F.f11052J.getChildCount()) {
                int childCount = this.F.f11052J.getChildCount();
                AbstractC4186p90 p90 = this.F;
                if (childCount <= p90.T) {
                    p90.g0.setInputMethodMode(2);
                    this.F.d();
                }
            }
        }
    }
}
