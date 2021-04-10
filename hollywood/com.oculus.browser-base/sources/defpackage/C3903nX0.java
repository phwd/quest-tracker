package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.oculus.browser.R;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tasks.SingleTabView;

/* renamed from: nX0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3903nX0 implements AbstractC5959zc1 {
    public final C4926tX0 F;
    public final C2307e91 G;
    public final AbstractC5789yc1 H;

    public C3903nX0(ChromeActivity chromeActivity, ViewGroup viewGroup) {
        UH0 uh0 = new UH0(AbstractC5096uX0.e);
        SingleTabView singleTabView = (SingleTabView) LayoutInflater.from(chromeActivity).inflate(R.layout.f41520_resource_name_obfuscated_RES_2131624461, viewGroup, false);
        viewGroup.addView(singleTabView);
        ZH0.a(uh0, singleTabView, new C3561lX0());
        C2307e91 e91 = new C2307e91(chromeActivity, false);
        this.G = e91;
        this.F = new C4926tX0(uh0, chromeActivity.P(), e91);
        if (CachedFeatureFlags.isEnabled("InstantStart")) {
            new X51(chromeActivity.P());
        }
        this.H = new C3732mX0(this);
    }

    @Override // defpackage.AbstractC5959zc1
    public AbstractC5279vc1 h() {
        return this.F;
    }

    @Override // defpackage.AbstractC5959zc1
    public void i(AbstractC5449wc1 wc1) {
        this.F.g = wc1;
    }

    @Override // defpackage.AbstractC5959zc1
    public AbstractC5789yc1 n() {
        return this.H;
    }

    @Override // defpackage.AbstractC5959zc1
    public void o(Context context, TabContentManager tabContentManager, IJ ij, AbstractC4928tY0 ty0, C2746gl0 gl0) {
        TabModel l;
        int index;
        this.G.e(Profile.b());
        C4926tX0 tx0 = this.F;
        if (!tx0.l) {
            AbstractC0246Ea1 ea1 = (AbstractC0246Ea1) tx0.b;
            if (ea1.h && (index = (l = ea1.l(false)).index()) != -1) {
                tx0.d.b(l.getTabAt(index).s(), false, new C4245pX0(tx0));
                tx0.l = true;
            }
        }
    }

    @Override // defpackage.AbstractC5959zc1
    public Q31 p() {
        return null;
    }
}
