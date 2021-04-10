package defpackage;

import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.browsing_data.ClearBrowsingDataTabsFragment;

/* renamed from: L81  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class L81 {

    /* renamed from: a  reason: collision with root package name */
    public final TabLayout f8408a;
    public final ViewPager2 b;
    public final C0719Lu c;
    public AbstractC5750yK0 d;
    public boolean e;
    public J81 f;
    public AbstractC5716y81 g;
    public AK0 h;

    public L81(TabLayout tabLayout, ViewPager2 viewPager2, C0719Lu lu) {
        this.f8408a = tabLayout;
        this.b = viewPager2;
        this.c = lu;
    }

    public void a() {
        int min;
        String str;
        this.f8408a.n();
        AbstractC5750yK0 yk0 = this.d;
        if (yk0 != null) {
            int b2 = yk0.b();
            for (int i = 0; i < b2; i++) {
                D81 l = this.f8408a.l();
                ClearBrowsingDataTabsFragment clearBrowsingDataTabsFragment = this.c.f8444a;
                Objects.requireNonNull(clearBrowsingDataTabsFragment);
                if (i == 0) {
                    str = clearBrowsingDataTabsFragment.u().getString(R.string.f48800_resource_name_obfuscated_RES_2131952197);
                } else if (i == 1) {
                    str = clearBrowsingDataTabsFragment.u().getString(R.string.f59250_resource_name_obfuscated_RES_2131953242);
                } else {
                    throw new RuntimeException(AbstractC2531fV.w("invalid position: ", i));
                }
                l.d(str);
                this.f8408a.d(l, false);
            }
            if (b2 > 0 && (min = Math.min(this.b.f9488J, this.f8408a.j() - 1)) != this.f8408a.h()) {
                TabLayout tabLayout = this.f8408a;
                tabLayout.o(tabLayout.i(min), true);
            }
        }
    }
}
