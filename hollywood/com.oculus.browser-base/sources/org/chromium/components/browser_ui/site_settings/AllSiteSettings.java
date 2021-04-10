package org.chromium.components.browser_ui.site_settings;

import android.os.Bundle;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AllSiteSettings extends SiteSettingsPreferenceFragment implements View.OnClickListener {
    public Button H0;
    public RecyclerView I0;
    public TextView J0;
    public MenuItem K0;
    public QX0 L0;
    public String M0;
    public List N0;
    public Set O0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void B0() {
        MenuItem menuItem;
        this.i0 = true;
        if (this.M0 == null && (menuItem = this.K0) != null) {
            RQ0.a(menuItem, u());
            this.M0 = null;
        }
        k1();
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void b0(Bundle bundle) {
        AbstractC2870hT0.a(this, R.xml.f76070_resource_name_obfuscated_RES_2132213763);
        String string = this.K.getString("title");
        if (string != null) {
            u().setTitle(string);
        }
        this.O0 = this.K.containsKey("selected_domains") ? new HashSet(this.K.getStringArrayList("selected_domains")) : null;
        V0(true);
        this.i0 = true;
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void k0(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menuInflater.inflate(R.menu.f42510_resource_name_obfuscated_RES_2131689485, menu);
        MenuItem findItem = menu.findItem(R.id.search);
        this.K0 = findItem;
        RQ0.d(findItem, this.M0, u(), new H4(this));
        Objects.requireNonNull(this.G0);
        menu.add(0, R.id.menu_id_site_settings_help, 0, R.string.f54570_resource_name_obfuscated_RES_2131952774).setIcon(Fs1.a(I(), R.drawable.f30670_resource_name_obfuscated_RES_2131231107, x().getTheme()));
    }

    public final void k1() {
        new Dy1(this.G0.b, false).c(this.L0, new J4(this, null));
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        BrowserContextHandle browserContextHandle = this.G0.b;
        Bundle bundle2 = this.K;
        if (bundle2 != null) {
            this.L0 = QX0.e(browserContextHandle, bundle2.getString("category", ""));
        }
        if (this.L0 == null) {
            this.L0 = QX0.f(browserContextHandle, 0);
        }
        if (this.L0.r(0) || this.L0.r(22)) {
            ViewGroup viewGroup2 = (ViewGroup) super.l0(layoutInflater, viewGroup, bundle);
            if (this.L0.r(22)) {
                layoutInflater.inflate(R.layout.f41600_resource_name_obfuscated_RES_2131624469, viewGroup2, true);
                this.J0 = (TextView) viewGroup2.findViewById(R.id.empty_storage);
                Button button = (Button) viewGroup2.findViewById(R.id.clear_button);
                this.H0 = button;
                button.setOnClickListener(this);
            }
            RecyclerView recyclerView = this.A0;
            this.I0 = recyclerView;
            recyclerView.s0(null);
            i1(null);
            return viewGroup2;
        }
        throw new IllegalArgumentException("Use SingleCategorySettings instead.");
    }

    public void onClick(View view) {
        boolean z;
        if (x() != null && view == this.H0) {
            long j = 0;
            Objects.requireNonNull(this.G0);
            Set a2 = AbstractC2957hy1.f10115a.a();
            List<Fy1> list = this.N0;
            if (list != null) {
                z = false;
                for (Fy1 fy1 : list) {
                    j += fy1.B0.j();
                    if (!z) {
                        z = ((HashSet) a2).contains(fy1.B0.F.d());
                    }
                }
            } else {
                z = false;
            }
            C2290e4 e4Var = new C2290e4(x());
            View inflate = ((LayoutInflater) x().getSystemService("layout_inflater")).inflate(R.layout.f37300_resource_name_obfuscated_RES_2131624039, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(16908299);
            ((TextView) inflate.findViewById(R.id.signed_out_text)).setText(R.string.f65720_resource_name_obfuscated_RES_2131953889);
            ((TextView) inflate.findViewById(R.id.offline_text)).setText(R.string.f65700_resource_name_obfuscated_RES_2131953887);
            textView.setText(P(z ? R.string.f65690_resource_name_obfuscated_RES_2131953886 : R.string.f65660_resource_name_obfuscated_RES_2131953883, Formatter.formatShortFileSize(x(), j)));
            C1598a4 a4Var = e4Var.f9828a;
            a4Var.r = inflate;
            a4Var.q = 0;
            e4Var.e(R.string.f62380_resource_name_obfuscated_RES_2131953555, new I4(this));
            e4Var.d(R.string.f48470_resource_name_obfuscated_RES_2131952164, null);
            e4Var.g(R.string.f62400_resource_name_obfuscated_RES_2131953557);
            e4Var.a().show();
        }
    }

    @Override // defpackage.AbstractC2324eF0, defpackage.AbstractC4204pF0
    public boolean q(Preference preference) {
        if (preference instanceof Fy1) {
            Fy1 fy1 = (Fy1) preference;
            fy1.S = SingleWebsiteSettings.class.getName();
            fy1.j().putSerializable("org.chromium.chrome.preferences.site", fy1.B0);
            fy1.j().putInt("org.chromium.chrome.preferences.navigation_source", this.K.getInt("org.chromium.chrome.preferences.navigation_source", 0));
        }
        return super.q(preference);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public boolean u0(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.menu_id_site_settings_help) {
            this.G0.b(u());
            return true;
        }
        boolean z = false;
        if (!RQ0.c(menuItem, this.K0, this.M0, u())) {
            return false;
        }
        String str = this.M0;
        if (str != null && !str.isEmpty()) {
            z = true;
        }
        this.M0 = null;
        if (z) {
            k1();
        }
        return true;
    }
}
