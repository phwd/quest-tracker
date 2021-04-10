package org.chromium.chrome.browser.browsing_data;

import J.N;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.profiles.Profile;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ClearBrowsingDataTabsFragment extends AbstractComponentCallbacksC3550lS {
    public ClearBrowsingDataFetcher y0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void C0(Bundle bundle) {
        bundle.putParcelable("clearBrowsingDataFetcher", this.y0);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void h0(Bundle bundle) {
        super.h0(bundle);
        V0(true);
        if (bundle == null) {
            ClearBrowsingDataFetcher clearBrowsingDataFetcher = new ClearBrowsingDataFetcher();
            this.y0 = clearBrowsingDataFetcher;
            Objects.requireNonNull(clearBrowsingDataFetcher);
            N.MCILE93S(Profile.b(), clearBrowsingDataFetcher);
            ClearBrowsingDataFetcher clearBrowsingDataFetcher2 = this.y0;
            Objects.requireNonNull(clearBrowsingDataFetcher2);
            int i = DialogFragmentC5669xt0.F;
            if (!NU0.f8549a.d("org.chromium.chrome.browser.settings.privacy.PREF_OTHER_FORMS_OF_HISTORY_DIALOG_SHOWN", false)) {
                BrowsingDataBridge c = BrowsingDataBridge.c();
                Objects.requireNonNull(c);
                N.MxCHuwXz(c, Profile.b(), clearBrowsingDataFetcher2);
            }
        } else {
            this.y0 = (ClearBrowsingDataFetcher) bundle.getParcelable("clearBrowsingDataFetcher");
        }
        AbstractC3535lK0.a("ClearBrowsingData_DialogCreated");
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void k0(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        MenuItem add = menu.add(0, R.id.menu_id_targeted_help, 0, R.string.f54570_resource_name_obfuscated_RES_2131952774);
        add.setIcon(Fs1.a(I(), R.drawable.f30670_resource_name_obfuscated_RES_2131231107, u().getTheme()));
        add.setShowAsAction(1);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.f37280_resource_name_obfuscated_RES_2131624037, viewGroup, false);
        ViewPager2 viewPager2 = (ViewPager2) inflate.findViewById(R.id.clear_browsing_data_viewpager);
        C0841Nu nu = new C0841Nu(this.y0, (AbstractActivityC3892nS) u());
        AbstractC5750yK0 yk0 = viewPager2.P.T;
        Su1 su1 = (Su1) viewPager2.a0;
        Objects.requireNonNull(su1);
        if (yk0 != null) {
            yk0.F.unregisterObserver(su1.c);
        }
        if (yk0 != null) {
            yk0.F.unregisterObserver(viewPager2.L);
        }
        viewPager2.P.q0(nu);
        viewPager2.f9488J = 0;
        viewPager2.b();
        Su1 su12 = (Su1) viewPager2.a0;
        su12.e();
        nu.F.registerObserver(su12.c);
        nu.F.registerObserver(viewPager2.L);
        TabLayout tabLayout = (TabLayout) inflate.findViewById(R.id.clear_browsing_data_tabs);
        L81 l81 = new L81(tabLayout, viewPager2, new C0719Lu(this));
        if (!l81.e) {
            AbstractC5750yK0 yk02 = viewPager2.P.T;
            l81.d = yk02;
            if (yk02 != null) {
                l81.e = true;
                J81 j81 = new J81(tabLayout);
                l81.f = j81;
                viewPager2.I.f10461a.add(j81);
                K81 k81 = new K81(viewPager2, true);
                l81.g = k81;
                if (!tabLayout.m0.contains(k81)) {
                    tabLayout.m0.add(k81);
                }
                I81 i81 = new I81(l81);
                l81.h = i81;
                l81.d.F.registerObserver(i81);
                l81.a();
                tabLayout.q(viewPager2.f9488J, 0.0f, true, true);
                BrowsingDataBridge c = BrowsingDataBridge.c();
                Objects.requireNonNull(c);
                D81 i = tabLayout.i(N.MD5TSIMJ(c));
                if (i != null) {
                    i.b();
                }
                C0902Ou ou = new C0902Ou(null);
                if (!tabLayout.m0.contains(ou)) {
                    tabLayout.m0.add(ou);
                }
                C5859z.a(u());
                throw null;
            }
            throw new IllegalStateException("TabLayoutMediator attached before ViewPager2 has an adapter");
        }
        throw new IllegalStateException("TabLayoutMediator is already attached");
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public boolean u0(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.menu_id_targeted_help) {
            return false;
        }
        C2535fX.a().b(u(), O(R.string.f52500_resource_name_obfuscated_RES_2131952567), Profile.b(), null);
        return true;
    }
}
