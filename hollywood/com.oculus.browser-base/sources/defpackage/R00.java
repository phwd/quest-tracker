package defpackage;

import J.N;
import android.view.Window;

/* renamed from: R00  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class R00 extends VK {
    public final Window F;
    public final AbstractC0124Ca1 G;
    public boolean H;

    public R00(Window window, AbstractC3838n70 n70, AbstractC0124Ca1 ca1) {
        this.F = window;
        this.G = ca1;
        n70.y0.b(new Q00(this));
        ((AbstractC0246Ea1) ca1).c(this);
    }

    public void a() {
        boolean z = true;
        boolean z2 = false;
        boolean z3 = (this.F.getAttributes().flags & 8192) == 8192;
        if (!((AbstractC0246Ea1) this.G).i().a() && (AbstractC4772sd1.b() || !this.H || ((AbstractC0246Ea1) this.G).l(true).getCount() <= 0)) {
            z = false;
        }
        if (!AbstractC4226pO.a() || !N.M09VlOh_("IncognitoScreenshot")) {
            z2 = z;
        }
        if (z3 != z2) {
            if (z2) {
                this.F.addFlags(8192);
            } else {
                this.F.clearFlags(8192);
            }
        }
    }

    @Override // defpackage.VK, defpackage.AbstractC0612Ka1
    public void e() {
        a();
    }
}
