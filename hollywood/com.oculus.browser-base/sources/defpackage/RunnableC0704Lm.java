package defpackage;

import android.view.MenuItem;

/* renamed from: Lm  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC0704Lm implements Runnable {
    public final /* synthetic */ C0825Nm F;
    public final /* synthetic */ MenuItem G;
    public final /* synthetic */ C4616ri0 H;
    public final /* synthetic */ C0764Mm I;

    public RunnableC0704Lm(C0764Mm mm, C0825Nm nm, MenuItem menuItem, C4616ri0 ri0) {
        this.I = mm;
        this.F = nm;
        this.G = menuItem;
        this.H = ri0;
    }

    public void run() {
        C0825Nm nm = this.F;
        if (nm != null) {
            this.I.F.f0 = true;
            nm.b.c(false);
            this.I.F.f0 = false;
        }
        if (this.G.isEnabled() && this.G.hasSubMenu()) {
            this.H.q(this.G, 4);
        }
    }
}
