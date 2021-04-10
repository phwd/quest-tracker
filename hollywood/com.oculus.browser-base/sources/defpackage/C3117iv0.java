package defpackage;

import J.N;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.preference.Preference;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.settings.ChromeImageViewPreference;
import org.chromium.components.content_settings.CookieControlsBridge;
import org.chromium.components.page_info.PageInfoController;
import org.chromium.components.page_info.PageInfoCookiesPreference;
import org.chromium.components.page_info.PageInfoRowView;

/* renamed from: iv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3117iv0 implements AbstractC0174Cv0, EA {
    public AbstractC5675xv0 F;
    public PageInfoRowView G;
    public CookieControlsBridge H;
    public AbstractC1922bv0 I;

    /* renamed from: J  reason: collision with root package name */
    public String f10173J;
    public String K;
    public PageInfoCookiesPreference L;
    public int M;
    public int N;
    public int O;
    public boolean P;
    public C3469ky1 Q;

    public C3117iv0(AbstractC5675xv0 xv0, PageInfoRowView pageInfoRowView, AbstractC1922bv0 bv0, String str) {
        this.F = xv0;
        this.G = pageInfoRowView;
        this.I = bv0;
        this.f10173J = str;
        String string = pageInfoRowView.getContext().getResources().getString(R.string.f50180_resource_name_obfuscated_RES_2131952335);
        this.K = string;
        C0113Bv0 bv02 = new C0113Bv0();
        bv02.f7772a = bv0.c;
        bv02.d = string;
        bv02.b = R.drawable.f34470_resource_name_obfuscated_RES_2131231487;
        bv02.g = true;
        bv02.f = new RunnableC2093cv0(this);
        this.G.a(bv02);
    }

    @Override // defpackage.EA
    public void a(int i, int i2) {
        String str;
        this.M = i;
        this.N = i2;
        int i3 = 0;
        if (i2 > 0) {
            str = this.G.getContext().getResources().getQuantityString(R.plurals.f42680_resource_name_obfuscated_RES_2131820560, i2, Integer.valueOf(i2));
        } else {
            str = null;
        }
        PageInfoRowView pageInfoRowView = this.G;
        pageInfoRowView.I.setText(str);
        TextView textView = pageInfoRowView.I;
        if (str == null) {
            i3 = 8;
        }
        textView.setVisibility(i3);
        PageInfoCookiesPreference pageInfoCookiesPreference = this.L;
        if (pageInfoCookiesPreference != null) {
            pageInfoCookiesPreference.o1(i, i2);
        }
    }

    @Override // defpackage.AbstractC0174Cv0
    public String b() {
        return this.K;
    }

    @Override // defpackage.AbstractC0174Cv0
    public View c(ViewGroup viewGroup) {
        PageInfoCookiesPreference pageInfoCookiesPreference = new PageInfoCookiesPreference();
        this.L = pageInfoCookiesPreference;
        AbstractC1922bv0 bv0 = this.I;
        C4985ts tsVar = (C4985ts) bv0;
        pageInfoCookiesPreference.G0 = new C2427et(tsVar.k, tsVar.l);
        C0317Fe fe = new C0317Fe(bv0.a());
        fe.b(this.L, null);
        fe.h();
        C4143ov0 ov0 = new C4143ov0();
        AbstractC1922bv0 bv02 = this.I;
        ov0.f11035a = bv02.d;
        ov0.b = new C2263dv0(this);
        ov0.c = new RunnableC2434ev0(this);
        ov0.d = new RunnableC2605fv0(bv02);
        ov0.e = N.M9l6T3Dg(((PageInfoController) this.F).g(), this.f10173J);
        PageInfoCookiesPreference pageInfoCookiesPreference2 = this.L;
        Preference f1 = pageInfoCookiesPreference2.f1("cookie_summary");
        C4467qp0 qp0 = new C4467qp0(pageInfoCookiesPreference2.I(), new C3288jv0(ov0));
        f1.T(FY0.a(pageInfoCookiesPreference2.O(R.string.f57030_resource_name_obfuscated_RES_2131953020), new EY0("<link>", "</link>", qp0)));
        pageInfoCookiesPreference2.I0.W(ov0.f11035a);
        pageInfoCookiesPreference2.I0.f9480J = new C3459kv0(ov0);
        ChromeImageViewPreference chromeImageViewPreference = pageInfoCookiesPreference2.J0;
        Drawable b = AbstractC2870hT0.b(pageInfoCookiesPreference2.x(), R.drawable.f34470_resource_name_obfuscated_RES_2131231487);
        if (chromeImageViewPreference.P != b) {
            chromeImageViewPreference.P = b;
            chromeImageViewPreference.O = 0;
            chromeImageViewPreference.s();
        }
        pageInfoCookiesPreference2.J0.b0(R.drawable.f29850_resource_name_obfuscated_RES_2131231025, R.string.f57000_resource_name_obfuscated_RES_2131953017, null);
        ChromeImageViewPreference chromeImageViewPreference2 = pageInfoCookiesPreference2.J0;
        if (chromeImageViewPreference2.y0) {
            chromeImageViewPreference2.y0 = false;
            chromeImageViewPreference2.a0();
        }
        pageInfoCookiesPreference2.M0 = ov0.e;
        pageInfoCookiesPreference2.J0.K = new C3630lv0(pageInfoCookiesPreference2);
        pageInfoCookiesPreference2.p1();
        pageInfoCookiesPreference2.K0 = ov0.c;
        this.L.o1(this.M, this.N);
        this.L.n1(this.O, this.P);
        new Dy1(((PageInfoController) this.F).g(), false).c(QX0.f(((PageInfoController) this.F).g(), 22), new C2776gv0(this));
        return this.L.Q0();
    }

    @Override // defpackage.AbstractC0174Cv0
    public void d() {
        KS a2 = this.I.a();
        PageInfoCookiesPreference pageInfoCookiesPreference = this.L;
        this.L = null;
        if (a2 != null && !a2.U()) {
            C0317Fe fe = new C0317Fe(a2);
            fe.p(pageInfoCookiesPreference);
            fe.h();
        }
    }

    @Override // defpackage.EA
    public void e(int i, int i2) {
        this.O = i;
        boolean z = i2 != 0;
        this.P = z;
        PageInfoCookiesPreference pageInfoCookiesPreference = this.L;
        if (pageInfoCookiesPreference != null) {
            pageInfoCookiesPreference.n1(i, z);
        }
    }
}
