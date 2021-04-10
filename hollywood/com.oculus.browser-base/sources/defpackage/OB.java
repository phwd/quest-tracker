package defpackage;

import android.app.PendingIntent;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.contextmenu.ContextMenuPopulatorFactory;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;

/* renamed from: OB  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OB implements C61 {

    /* renamed from: a  reason: collision with root package name */
    public final int f8606a = 1;
    public final String b = null;
    public final int c;
    public final boolean d;
    public final AbstractC2742gk e;
    public final YM f;
    public final C3432km0 g;
    public final PendingIntent h;
    public final Is1 i;
    public final boolean j;
    public AbstractC0133Cd1 k;
    public C2003cN l;
    public M70 m;

    public OB(ChromeActivity chromeActivity, boolean z, boolean z2, String str, int i2, boolean z3, AbstractC2742gk gkVar, YM ym, C3432km0 km0, PendingIntent pendingIntent, Is1 is1, M70 m70, boolean z4) {
        this.c = i2;
        this.d = z3;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.m = m70;
        this.j = z4;
    }

    @Override // defpackage.C61
    public AbstractC0133Cd1 a(Tab tab) {
        NB nb = new NB(tab, null, this.f8606a, this.b, this.c, this.g, this.d, this.h);
        this.k = nb;
        return nb;
    }

    @Override // defpackage.C61
    public AbstractC5818ym0 b(String str, AbstractC5818ym0 ym0, Tab tab) {
        return null;
    }

    @Override // defpackage.C61
    public C3198jN c(Tab tab) {
        MB mb = new MB(tab, this.f, this.i, this.f8606a);
        this.l = mb;
        return new C3198jN(mb);
    }

    @Override // defpackage.C61
    public AbstractC2742gk d(Tab tab) {
        KB kb = new KB(this, tab);
        AbstractC2742gk gkVar = this.e;
        if (gkVar == null) {
            return kb;
        }
        return new C3461kw(kb, gkVar);
    }

    @Override // defpackage.C61
    public ContextMenuPopulatorFactory e(Tab tab) {
        HB hb;
        int i2 = this.f8606a;
        int i3 = 1;
        if (i2 == 3 || i2 == 4) {
            i3 = 2;
        }
        AbstractC0124Ca1 X = ((TabImpl) tab).X();
        boolean z = ((TabImpl) tab).H;
        if (DL.b()) {
            M70 m70 = this.m;
            m70.getClass();
            hb = new HB(m70);
        } else {
            hb = new IB();
        }
        return new C5662xr(new LB(this, tab, X, hb, new JB(), null, z), null, i3, YM.f9268a);
    }
}
