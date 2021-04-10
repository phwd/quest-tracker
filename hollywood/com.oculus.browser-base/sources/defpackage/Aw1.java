package defpackage;

import android.os.SystemClock;

/* renamed from: Aw1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Aw1 extends AbstractC6022zx1 {
    public final /* synthetic */ Bw1 G;

    public Aw1(Bw1 bw1, C6019zw1 zw1) {
        this.G = bw1;
    }

    @Override // defpackage.AbstractC6022zx1
    public void renderProcessGone(boolean z) {
        AbstractC3364kK0.i("CustomTabs.SpareWebContents.TimeBeforeDeath", SystemClock.elapsedRealtime() - this.G.g);
        if (this.G.i) {
            AbstractC3364kK0.g("CustomTabs.SpareWebContents.Status2", 2, 5);
        }
        Bw1 bw1 = this.G;
        bw1.f.Q(bw1.h);
        bw1.f.destroy();
        bw1.f = null;
        bw1.h = null;
    }
}
