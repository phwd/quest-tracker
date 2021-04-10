package defpackage;

import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.tabs.TabLayout;
import com.oculus.browser.R;

/* renamed from: E50  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class E50 extends AbstractC2127d60 {
    public H50 Z;
    public TabLayout a0;

    public E50(ViewGroup viewGroup) {
        super(viewGroup, R.layout.f39040_resource_name_obfuscated_RES_2131624213);
    }

    @Override // defpackage.AbstractC2127d60
    public void x(G50 g50, View view) {
        H50 h50 = (H50) g50;
        TabLayout tabLayout = (TabLayout) view;
        this.Z = h50;
        this.a0 = tabLayout;
        L50 l50 = h50.c;
        if (!l50.f8404a.c.containsKey(tabLayout)) {
            P50 p50 = l50.f8404a;
            p50.c.put(tabLayout, new O50(p50, tabLayout));
        }
    }

    @Override // defpackage.AbstractC2127d60
    public void y() {
        H50 h50 = this.Z;
        O50 o50 = (O50) h50.c.f8404a.c.remove(this.a0);
        if (o50 != null) {
            R50 r50 = o50.c.b;
            r50.H.remove(o50.b);
            o50.f8598a.b();
            o50.b = null;
        }
    }
}
