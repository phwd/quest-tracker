package org.chromium.chrome.browser.explore_sites;

import J.N;
import android.content.Context;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.profiles.Profile;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ExploreSitesBackgroundTask extends AbstractC4798sm0 {
    public AbstractC0804Ne f;
    public Profile g;

    public static void l(boolean z) {
        AbstractC2898hf.b().a(ContextUtils.getApplicationContext(), 100);
        C1416Xe1 xe1 = new C1416Xe1();
        xe1.f9225a = 90000000;
        xe1.b = 7200000;
        xe1.c = true;
        C1477Ye1 a2 = xe1.a();
        C1111Se1 se1 = new C1111Se1(101);
        se1.g = a2;
        se1.c = 1;
        se1.e = true;
        se1.f = z;
        AbstractC2898hf.b().b(ContextUtils.getApplicationContext(), se1.a());
    }

    @Override // defpackage.AbstractC0865Oe
    public void c(Context context) {
        l(true);
    }

    @Override // defpackage.AbstractC4798sm0
    public int e(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        return C5222vE.d(context) == 6 ? 1 : 0;
    }

    @Override // defpackage.AbstractC4798sm0
    public void f(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        if (!(N.MwBQ$0Eq() == 0)) {
            AbstractC2898hf.b().a(ContextUtils.getApplicationContext(), 101);
            return;
        }
        this.f = ne;
        if (this.g == null) {
            this.g = Profile.b();
        }
        N.MYfYpI3c(this.g, false, new C5753yM(this));
        AbstractC3364kK0.g("ExploreSites.CatalogUpdateRequestSource", 2, 3);
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean g(Context context, C2046cf1 cf1) {
        return false;
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean h(Context context, C2046cf1 cf1) {
        return false;
    }

    public final /* synthetic */ void k() {
        this.f.a(false);
    }
}
