package org.chromium.components.browser_ui.site_settings;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.widget.SearchView;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.components.browser_ui.settings.ChromeImageViewPreference;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ChosenObjectSettings extends SiteSettingsPreferenceFragment {
    public QX0 H0;
    public ArrayList I0;
    public ArrayList J0;
    public SearchView K0;
    public String L0 = "";

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void B0() {
        this.i0 = true;
        if (this.J0 == null) {
            k1();
        } else {
            o1();
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void b0(Bundle bundle) {
        i1(null);
        this.H0 = QX0.d(this.G0.b, this.K.getInt("org.chromium.chrome.preferences.content_settings_type"));
        this.I0 = (ArrayList) this.K.getSerializable("org.chromium.chrome.preferences.object_infos");
        this.J0 = (ArrayList) this.K.getSerializable("org.chromium.chrome.preferences.site_set");
        String string = this.K.getString("title");
        if (string != null) {
            u().setTitle(string);
        }
        V0(true);
        this.i0 = true;
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        C4375qF0 qf0 = this.z0;
        j1(qf0.a(qf0.f11127a));
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void k0(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menuInflater.inflate(R.menu.f42510_resource_name_obfuscated_RES_2131689485, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        this.K0 = searchView;
        searchView.V.setImeOptions(33554432);
        this.K0.r0 = new C5996zp(this);
        Objects.requireNonNull(this.G0);
        menu.add(0, R.id.menu_id_site_settings_help, 0, R.string.f54570_resource_name_obfuscated_RES_2131952774).setIcon(Fs1.a(I(), R.drawable.f30670_resource_name_obfuscated_RES_2131231107, x().getTheme()));
    }

    public final void k1() {
        new Dy1(this.G0.b, false).c(this.H0, new C0100Bp(this, null));
    }

    public final void l1() {
        Iterator it = this.I0.iterator();
        boolean z = false;
        while (it.hasNext()) {
            C5316vp vpVar = (C5316vp) it.next();
            if (vpVar.K) {
                z = true;
            } else {
                vpVar.a(this.G0.b);
            }
        }
        if (z) {
            Context x = x();
            C1184Ti1.b(x, x.getString(R.string.f54380_resource_name_obfuscated_RES_2131952755), 1).b.show();
        } else {
            u().finish();
        }
        k1();
    }

    public final void m1(String str) {
        C2290e4 e4Var = new C2290e4(x(), R.style.f72700_resource_name_obfuscated_RES_2132017843);
        e4Var.g(R.string.f60290_resource_name_obfuscated_RES_2131953346);
        e4Var.f9828a.f = str;
        e4Var.e(R.string.f60290_resource_name_obfuscated_RES_2131953346, new DialogInterface$OnClickListenerC5826yp(this));
        e4Var.d(R.string.f48470_resource_name_obfuscated_RES_2131952164, null);
        e4Var.i();
    }

    public final void n1(C5316vp vpVar) {
        vpVar.a(this.G0.b);
        k1();
    }

    public final void o1() {
        PreferenceScreen preferenceScreen = this.z0.g;
        preferenceScreen.e0();
        C4375qF0 qf0 = this.z0;
        PreferenceScreen preferenceScreen2 = qf0.g;
        ChromeImageViewPreference chromeImageViewPreference = new ChromeImageViewPreference(qf0.f11127a);
        int i = 0;
        String str = ((C5316vp) this.I0.get(0)).I;
        String format = String.format(this.k0.getContext().getString(R.string.f48680_resource_name_obfuscated_RES_2131952185), str);
        chromeImageViewPreference.V(str);
        chromeImageViewPreference.b0(R.drawable.f29850_resource_name_obfuscated_RES_2131231025, R.string.f65570_resource_name_obfuscated_RES_2131953874, new View$OnClickListenerC5486wp(this, format));
        preferenceScreen2.a0(chromeImageViewPreference);
        Preference preference = new Preference(this.z0.f11127a, null);
        preference.k0 = R.layout.f38020_resource_name_obfuscated_RES_2131624111;
        preferenceScreen2.a0(preference);
        while (i < this.J0.size() && i < this.I0.size()) {
            C3469ky1 ky1 = (C3469ky1) this.J0.get(i);
            C5316vp vpVar = (C5316vp) this.I0.get(i);
            Fy1 fy1 = new Fy1(this.z0.f11127a, this.G0, ky1, this.H0);
            fy1.j().putSerializable("org.chromium.chrome.preferences.site", ky1);
            fy1.S = SingleWebsiteSettings.class.getCanonicalName();
            fy1.b0(R.drawable.f29850_resource_name_obfuscated_RES_2131231025, R.string.f65580_resource_name_obfuscated_RES_2131953875, new View$OnClickListenerC5656xp(this, vpVar));
            C0039Ap ap = new C0039Ap(this, this.G0.a(), vpVar);
            fy1.t0 = ap;
            AbstractC1865bc0.b(ap, fy1);
            preferenceScreen.a0(fy1);
            i++;
        }
        this.J0 = null;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public boolean u0(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.menu_id_site_settings_help) {
            return false;
        }
        this.G0.b(u());
        return true;
    }
}
