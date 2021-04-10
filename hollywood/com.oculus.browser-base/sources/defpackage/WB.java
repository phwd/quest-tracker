package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.ActionMode;
import android.view.View;
import android.widget.ImageButton;
import com.oculus.browser.R;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar;
import org.chromium.chrome.browser.omnibox.UrlBarApi26;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TrustedCdn;
import org.chromium.chrome.browser.toolbar.LocationBarModel;
import org.chromium.ui.base.DeviceFormFactor;

/* renamed from: WB  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WB extends AbstractC4592ra0 implements AbstractC3225ja0, Hq1 {
    public static final /* synthetic */ int F = 0;
    public AbstractC4422qa0 G;
    public Oq1 H;
    public final /* synthetic */ CustomTabToolbar I;

    public WB(CustomTabToolbar customTabToolbar, AbstractC4422qa0 qa0, ActionMode.Callback callback, UrlBarApi26 urlBarApi26) {
        this.I = customTabToolbar;
        this.G = qa0;
        ((LocationBarModel) qa0).m.b(this);
        this.H = new Oq1(urlBarApi26, null, callback, new TB(), this, new YB(null));
        u();
        q();
        p();
        t();
    }

    @Override // defpackage.AbstractC3225ja0
    public Sv1 C() {
        return null;
    }

    @Override // defpackage.AbstractC3225ja0
    public void F() {
    }

    @Override // defpackage.AbstractC3225ja0
    public void H() {
        q();
        p();
        t();
    }

    @Override // defpackage.AbstractC3225ja0
    public void L() {
    }

    @Override // defpackage.AbstractC3225ja0
    public AbstractC1834bO Q() {
        return null;
    }

    @Override // defpackage.AbstractC4371qE
    public void destroy() {
        AbstractC4422qa0 qa0 = this.G;
        if (qa0 != null) {
            qa0.w(this);
            this.G = null;
        }
    }

    @Override // defpackage.Hq1
    public void f(boolean z) {
    }

    @Override // defpackage.Hq1
    public View h() {
        Tab Y = CustomTabToolbar.Y(this.I);
        if (Y == null) {
            return null;
        }
        return Y.b();
    }

    @Override // defpackage.Hq1
    public boolean i() {
        CustomTabToolbar customTabToolbar = this.I;
        Object obj = CustomTabToolbar.U;
        return !customTabToolbar.p();
    }

    @Override // defpackage.Hq1
    public void j() {
    }

    @Override // defpackage.AbstractC4592ra0
    public void l() {
        u();
        q();
        p();
    }

    @Override // defpackage.AbstractC4592ra0
    public void m() {
        q();
    }

    @Override // defpackage.AbstractC4592ra0
    public void n() {
        r();
    }

    @Override // defpackage.AbstractC4592ra0
    public void o() {
        t();
    }

    public final void p() {
        CustomTabToolbar customTabToolbar = this.I;
        Object obj = CustomTabToolbar.U;
        C4456ql1 ql1 = customTabToolbar.L;
        if (ql1 != null) {
            Resources resources = customTabToolbar.getResources();
            int color = this.I.getBackground().getColor();
            if (Pj1.d(resources, false, color)) {
                ql1.setBackgroundColor(resources.getColor(R.color.f14740_resource_name_obfuscated_RES_2131100164));
                ql1.h(resources.getColor(R.color.f14760_resource_name_obfuscated_RES_2131100166));
                return;
            }
            ql1.i(color, false);
        }
    }

    public final void q() {
        CustomTabToolbar customTabToolbar = this.I;
        if (customTabToolbar.o0 != 1) {
            int o = this.G.o(DeviceFormFactor.a(customTabToolbar.getContext()));
            if (o != 0) {
                Context context = this.I.getContext();
                int m = this.G.m();
                ThreadLocal threadLocal = AbstractC5544x8.f11592a;
                this.I.f0.setImageTintList(context.getColorStateList(m));
            }
            KR0 kr0 = this.I.n0.f9326a;
            if (o == 0) {
                kr0.f8364a.setImageDrawable(null);
                if (kr0.c.isStarted()) {
                    kr0.c.cancel();
                }
                if (!kr0.d.isStarted() && kr0.b.getTranslationX() != ((float) (-kr0.e))) {
                    kr0.d.start();
                }
            } else {
                kr0.f8364a.setImageResource(o);
                if (kr0.d.isStarted()) {
                    kr0.d.cancel();
                }
                if (!kr0.c.isStarted() && kr0.f8364a.getVisibility() != 0) {
                    kr0.c.start();
                }
            }
            this.I.f0.setContentDescription(this.I.getContext().getString(this.G.n()));
        }
    }

    public final void r() {
        String q = this.G.q();
        if (!this.G.r() || TextUtils.isEmpty(q)) {
            this.I.d0.setText("");
            return;
        }
        int i = this.I.o0;
        if ((i == 2 || i == 1) && !q.equals(this.G.i()) && !q.equals("about:blank")) {
            PostTask.b(Zo1.f9374a, this.I.s0, 800);
        }
        this.I.d0.setText(q);
    }

    public final void t() {
        String str;
        int i;
        int i2;
        SpannableString spannableString;
        ColorStateList colorStateList;
        Tab Y = CustomTabToolbar.Y(this.I);
        if (Y == null) {
            this.H.k(Pq1.c, 0, 0);
            return;
        }
        String j = TrustedCdn.j(Y);
        if (j != null) {
            str = j;
        } else {
            str = Y.s().trim();
        }
        if (this.I.o0 == 1 && !TextUtils.isEmpty(this.G.q())) {
            r();
        }
        if (AbstractC5818ym0.a(str, CustomTabToolbar.Y(this.I).a()) || "about:blank".equals(str)) {
            this.H.k(Pq1.c, 0, 0);
            return;
        }
        if (j != null) {
            String string = this.I.getContext().getString(R.string.f50270_resource_name_obfuscated_RES_2131952344, AbstractC5154ur1.a(j));
            CustomTabToolbar customTabToolbar = this.I;
            if (customTabToolbar.i0) {
                colorStateList = customTabToolbar.j0;
            } else {
                colorStateList = customTabToolbar.k0;
            }
            Object obj = CustomTabToolbar.U;
            Object obj2 = CustomTabToolbar.U;
            SpannableString a2 = FY0.a(string, new EY0("<pub>", "</pub>", obj2), new EY0("<bg>", "</bg>", new ForegroundColorSpan(colorStateList.getDefaultColor())));
            i2 = a2.getSpanStart(obj2);
            i = a2.getSpanEnd(obj2);
            a2.removeSpan(obj2);
            spannableString = a2;
        } else {
            Pq1 h = this.G.h();
            CharSequence subSequence = h.e.subSequence(h.g, h.h);
            i = subSequence.length();
            i2 = 0;
            spannableString = subSequence;
        }
        boolean z = this.G.v() && this.I.a0.getVisibility() == 0;
        int i3 = 8;
        this.I.b0.setVisibility(z ? 0 : 8);
        View view = this.I.c0;
        if (z) {
            i3 = 0;
        }
        view.setVisibility(i3);
        this.H.k(Pq1.a(str, spannableString, i2, i, str), 1, 0);
    }

    public final void u() {
        CustomTabToolbar customTabToolbar = this.I;
        customTabToolbar.b0(customTabToolbar.h0);
        int childCount = customTabToolbar.g0.getChildCount();
        for (int i = 0; i < childCount; i++) {
            customTabToolbar.b0((ImageButton) customTabToolbar.g0.getChildAt(i));
        }
        customTabToolbar.b0(customTabToolbar.f0);
        if (this.H.H.d(this.I.i0)) {
            t();
        }
        CustomTabToolbar customTabToolbar2 = this.I;
        customTabToolbar2.d0.setTextColor(customTabToolbar2.getResources().getColor(this.I.i0 ? R.color.f11470_resource_name_obfuscated_RES_2131099837 : R.color.f11570_resource_name_obfuscated_RES_2131099847));
    }

    @Override // defpackage.AbstractC3225ja0
    public View w() {
        return this.I;
    }

    @Override // defpackage.AbstractC3225ja0
    public void x() {
    }

    @Override // defpackage.AbstractC3225ja0
    public View y() {
        return this.I.f0;
    }
}
