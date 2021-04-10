package defpackage;

import android.content.Intent;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Ya1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1465Ya1 extends WK implements Qr1 {
    public final Tab F;
    public Q31 G;
    public Intent H;

    public C1465Ya1(Tab tab) {
        this.F = tab;
        tab.A(this);
    }

    @Override // defpackage.Qr1
    public void destroy() {
        this.F.I(this);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void l(Tab tab) {
        if ((this.G.get() == tab) && this.H != null && AbstractC5112ud1.b(tab).getIntent() != this.H) {
            AbstractC5112ud1.b(tab).startActivity(this.H);
        }
    }
}
