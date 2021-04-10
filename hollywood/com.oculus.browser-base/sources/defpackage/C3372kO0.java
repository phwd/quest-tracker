package defpackage;

import android.content.Context;
import android.text.SpannableString;
import android.view.View;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.ui.widget.ChromeBulletSpan;
import org.chromium.url.GURL;

/* renamed from: kO0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3372kO0 extends WK implements Qr1, AbstractC0072Bd1 {
    public final TabImpl F;
    public View G;
    public int H;

    public C3372kO0(Tab tab) {
        TabImpl tabImpl = (TabImpl) tab;
        this.F = tabImpl;
        tabImpl.P.b(this);
    }

    public static SpannableString V(Context context, int i) {
        SpannableString spannableString = new SpannableString(context.getString(i));
        spannableString.setSpan(new ChromeBulletSpan(context), 0, spannableString.length(), 0);
        return spannableString;
    }

    public static boolean W(Tab tab) {
        C3372kO0 ko0;
        return (tab == null || !tab.isInitialized() || (ko0 = (C3372kO0) tab.M().c(C3372kO0.class)) == null || ko0.G == null || !ko0.F.O.b(ko0)) ? false : true;
    }

    public static void X(boolean z, int i) {
        if (z) {
            AbstractC3364kK0.g("Tabs.SadTab.Feedback.Event", i, 3);
        } else {
            AbstractC3364kK0.g("Tabs.SadTab.Reload.Event", i, 3);
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void G(Tab tab, LoadUrlParams loadUrlParams, int i) {
        Y();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void J(Tab tab, GURL gurl) {
        this.H = 0;
        Y();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void K(Tab tab, GURL gurl) {
        Y();
    }

    public void Y() {
        this.F.O.c(this);
        this.G = null;
    }

    @Override // defpackage.AbstractC0072Bd1
    public View b() {
        return this.G;
    }

    @Override // defpackage.AbstractC0072Bd1
    public int c() {
        return 1;
    }

    @Override // defpackage.AbstractC0072Bd1
    public void d() {
    }

    @Override // defpackage.Qr1
    public void destroy() {
        this.F.P.c(this);
    }

    @Override // defpackage.AbstractC0072Bd1
    public void e() {
    }
}
