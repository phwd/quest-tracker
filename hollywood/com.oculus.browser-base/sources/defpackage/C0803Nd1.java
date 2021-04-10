package defpackage;

import J.N;
import android.content.Context;
import android.view.View;
import com.oculus.browser.R;
import org.chromium.chrome.browser.datareduction.DataReductionMainMenuItem;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.net.spdyproxy.DataReductionProxySettings;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Nd1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0803Nd1 extends F9 {
    public C0803Nd1(Context context, C1595a3 a3Var, C3261jm0 jm0, AbstractC0124Ca1 ca1, Uk1 uk1, View view, AbstractC5207v9 v9Var, AbstractC1509Ys0 ys0, AbstractC0956Pq0 pq0, C2746gl0 gl0) {
        super(context, a3Var, jm0, ca1, uk1, view, ys0, pq0, gl0);
    }

    @Override // defpackage.F9, defpackage.B9
    public int a() {
        return 0;
    }

    @Override // defpackage.F9, defpackage.B9
    public boolean b() {
        return CachedFeatureFlags.isEnabled("TabbedAppOverflowMenuIcons") || CachedFeatureFlags.isEnabled("TabbedAppOverflowMenuThreeButtonActionbar");
    }

    @Override // defpackage.F9, defpackage.B9
    public void c(AbstractC5717y9 y9Var, View view) {
    }

    @Override // defpackage.F9, defpackage.B9
    public int d() {
        if (o()) {
            return R.layout.f37720_resource_name_obfuscated_RES_2131624081;
        }
        return 0;
    }

    @Override // defpackage.F9, defpackage.B9
    public void e(AbstractC5717y9 y9Var, View view) {
        if (view instanceof DataReductionMainMenuItem) {
            view.findViewById(R.id.data_reduction_menu_divider).setVisibility(8);
        }
    }

    @Override // defpackage.F9, defpackage.B9
    public boolean f(int i) {
        if (!o() || ((float) i) >= this.e.getResources().getDimension(R.dimen.f17960_resource_name_obfuscated_RES_2131165415)) {
            return true;
        }
        return false;
    }

    @Override // defpackage.F9
    public boolean l(Tab tab) {
        return CachedFeatureFlags.isEnabled("AndroidManagedByMenuItem") && N.MJ3ey9tX(Profile.a(tab.l()));
    }

    public final boolean o() {
        AbstractC2260du0 du0 = this.s;
        if (du0 == null || !((AbstractC3838n70) du0).C()) {
            DataReductionProxySettings d = DataReductionProxySettings.d();
            if (d.e() && d.a() / 1024 >= 100) {
                return true;
            }
        }
        return false;
    }
}
