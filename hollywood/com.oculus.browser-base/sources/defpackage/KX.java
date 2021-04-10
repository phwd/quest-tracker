package defpackage;

/* renamed from: KX  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class KX implements Runnable {
    public final MX F;

    public KX(MX mx) {
        this.F = mx;
    }

    public void run() {
        MX mx = this.F;
        mx.M = null;
        C4241pV0 pv0 = mx.I;
        if (!(pv0 == null || pv0.getParent() == null)) {
            Runnable runnable = mx.M;
            if (runnable != null) {
                mx.I.removeCallbacks(runnable);
                mx.M = null;
            }
            mx.removeView(mx.I);
        }
    }
}
