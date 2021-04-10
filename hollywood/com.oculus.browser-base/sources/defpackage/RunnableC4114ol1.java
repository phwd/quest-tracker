package defpackage;

/* renamed from: ol1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4114ol1 implements Runnable {
    public final /* synthetic */ C4456ql1 F;

    public RunnableC4114ol1(C4456ql1 ql1) {
        this.F = ql1;
    }

    public void run() {
        C4456ql1 ql1 = this.F;
        if (ql1.f11161J) {
            C3697mH0 mh0 = ql1.L;
            mh0.f10411a = ql1.H;
            mh0.b = 0.0f;
            ql1.c0.start();
            C4456ql1 ql12 = this.F;
            if (ql12.S != null) {
                int abs = Math.abs(ql12.getDrawable().getBounds().right - this.F.getDrawable().getBounds().left);
                C4456ql1 ql13 = this.F;
                ql13.S.a(ql13.H * ((float) abs));
                C4966tl1 tl1 = this.F.S;
                tl1.L = false;
                if (!tl1.I.isStarted()) {
                    tl1.c();
                    tl1.I.setStartDelay(0);
                    tl1.setScaleX(0.0f);
                    tl1.setTranslationX(0.0f);
                    tl1.I.start();
                    tl1.animate().alpha(1.0f).setDuration(500).setInterpolator(animation.InterpolatorC5286vf.g);
                }
            }
        }
    }
}
