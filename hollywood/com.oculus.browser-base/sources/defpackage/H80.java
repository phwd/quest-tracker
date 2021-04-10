package defpackage;

import J.N;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.oculus.browser.R;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* renamed from: H80  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class H80 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8139a;
    public final View$OnLayoutChangeListenerC5940zU0 b;
    public final String c;
    public final String d;
    public final Tab e;
    public AbstractC4270pg1 f;
    public boolean g = false;

    public H80(Context context, Tab tab, View$OnLayoutChangeListenerC5940zU0 zu0, String str, String str2) {
        this.f8139a = context;
        this.b = zu0;
        this.c = str;
        this.d = str2;
        this.e = tab;
        tab.A(this);
        if (!N.MnwPB_N7(new GURL(str))) {
            N.MTjPIm8h();
            W("");
        } else if (tab.l().N() != tab.l().H()) {
            N.MsJ9wL5w();
            W("");
        } else {
            C3826n30 h = tab.l().N().h();
            int i = AbstractC4270pg1.B;
            AbstractC4270pg1 pg1 = (AbstractC4270pg1) h.a(AbstractC5631xg1.f11624a);
            this.f = pg1;
            ((C4611rg1) pg1).f0(new G80(this));
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void A(Tab tab, int i) {
        N.M4FuWPE5();
        V();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void R(Tab tab, GURL gurl) {
        N.Mj3TJ$8D();
        V();
    }

    public final void V() {
        AbstractC4270pg1 pg1 = this.f;
        if (pg1 != null) {
            ((AbstractC2459f30) pg1).close();
        }
        this.g = true;
        this.e.I(this);
    }

    public void W(String str) {
        if (!this.g) {
            WindowAndroid i = this.e.i();
            String str2 = this.c;
            if (!str.isEmpty()) {
                Uri.Builder buildUpon = Uri.parse(str2).buildUpon();
                str2 = buildUpon.encodedFragment(":~:text=" + str).toString();
            }
            String format = String.format("\"%s\"\n", this.d);
            if (!TextUtils.isEmpty(str2)) {
                str2 = HG.a(str2);
            }
            C2189dU0 du0 = new C2189dU0(i, "", format, str2, null, null, null, null, null, null);
            View$OnLayoutChangeListenerC5940zU0 zu0 = this.b;
            C1915bt btVar = new C1915bt(false, false, false, null, false, false, null);
            long currentTimeMillis = System.currentTimeMillis();
            zu0.N = true;
            zu0.h(du0, btVar, currentTimeMillis);
            if (str.isEmpty()) {
                C1184Ti1 b2 = C1184Ti1.b(this.f8139a, this.f8139a.getResources().getString(R.string.f54140_resource_name_obfuscated_RES_2131952731), 0);
                b2.b.setGravity(b2.b.getGravity(), b2.b.getXOffset(), this.f8139a.getResources().getDimensionPixelSize(R.dimen.f26790_resource_name_obfuscated_RES_2131166298));
                b2.b.show();
            }
            V();
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void q(Tab tab) {
        N.MUdHyZWa();
        V();
    }
}
