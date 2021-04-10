package org.chromium.chrome.browser.password_manager.settings;

import J.N;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.password_manager.PasswordManagerLauncher;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.webauthn.CableAuthenticatorModuleProvider;
import org.chromium.components.browser_ui.settings.ChromeBasePreference;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;
import org.chromium.components.browser_ui.settings.TextMessagePreference;
import org.chromium.components.prefs.PrefService;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PasswordSettings extends AbstractC2324eF0 implements AbstractC4323py0, YE0 {
    public boolean G0;
    public boolean H0;
    public MenuItem I0;
    public MenuItem J0;
    public String K0;
    public Preference L0;
    public Preference M0;
    public ChromeSwitchPreference N0;
    public ChromeSwitchPreference O0;
    public ChromeBasePreference P0;
    public TextMessagePreference Q0;
    public C0909Ox0 R0;
    public int S0;
    public SM T0 = new SM();

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void B0() {
        this.i0 = true;
        SM sm = this.T0;
        if (sm.f8891a == 1) {
            if (!AbstractC2852hK0.a(1)) {
                ExportWarningDialogFragment exportWarningDialogFragment = sm.f;
                if (exportWarningDialogFragment != null) {
                    exportWarningDialogFragment.f1(false, false);
                }
                sm.f8891a = 0;
            } else if (sm.f == null) {
                sm.a();
            }
        }
        t1();
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public void C0(Bundle bundle) {
        super.C0(bundle);
        SM sm = this.T0;
        bundle.putInt("saved-state-export-state", sm.f8891a);
        Integer num = sm.c;
        if (num != null) {
            bundle.putInt("saved-state-entries-count", num.intValue());
        }
        Uri uri = sm.b;
        if (uri != null) {
            bundle.putString("saved-state-export-file-uri", uri.toString());
        }
        String str = this.K0;
        if (str != null) {
            bundle.putString("saved-state-search-query", str);
        }
        bundle.putInt("manage-passwords-referrer", this.S0);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public void F0(View view, Bundle bundle) {
        super.F0(view, bundle);
        this.A0.s0(null);
    }

    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        if (preference == this.L0) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(N.MTEEGYRd()));
            intent.setPackage(u().getPackageName());
            u().startActivity(intent);
        } else if (N.M09VlOh_("EditPasswordsInSettings")) {
            C4834sy0 sy0 = AbstractC4664ry0.f11238a;
            Objects.requireNonNull(sy0);
            Object obj = ThreadUtils.f10596a;
            PasswordUIView passwordUIView = sy0.F;
            N.MH0CF$4w(passwordUIView.f10742a, passwordUIView, x(), preference.j().getInt("id"));
        } else {
            Bundle bundle = new Bundle(preference.j());
            bundle.putBoolean("found_via_search_args", this.K0 != null);
            Activity u = u();
            String name = PasswordEntryViewer.class.getName();
            Intent intent2 = new Intent();
            intent2.setClass(u, XS0.class);
            if (!(u instanceof Activity)) {
                intent2.addFlags(268435456);
                intent2.addFlags(67108864);
            }
            intent2.putExtra("show_fragment", name);
            intent2.putExtra("show_fragment_args", bundle);
            U20.q(u, intent2);
        }
        return true;
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        int i;
        SM sm = this.T0;
        sm.g = new C0058Ay0(this);
        if (bundle != null) {
            if (bundle.containsKey("saved-state-export-state")) {
                int i2 = bundle.getInt("saved-state-export-state");
                sm.f8891a = i2;
                if (i2 == 2) {
                    sm.d();
                }
            }
            if (bundle.containsKey("saved-state-export-file-uri")) {
                String string = bundle.getString("saved-state-export-file-uri");
                if (string.isEmpty()) {
                    sm.b = Uri.EMPTY;
                } else {
                    sm.b = Uri.parse(string);
                }
            }
            if (bundle.containsKey("saved-state-entries-count")) {
                sm.c = Integer.valueOf(bundle.getInt("saved-state-entries-count"));
            }
        }
        u().setTitle(R.string.f58060_resource_name_obfuscated_RES_2131953123);
        C4375qF0 qf0 = this.z0;
        j1(qf0.a(qf0.f11127a));
        AbstractC4664ry0.f11238a.a(this);
        V0(true);
        if (bundle == null || !bundle.containsKey("manage-passwords-referrer")) {
            i = this.K.getInt("manage-passwords-referrer");
        } else {
            i = bundle.getInt("manage-passwords-referrer");
        }
        this.S0 = i;
        if (bundle != null && bundle.containsKey("saved-state-search-query")) {
            this.K0 = bundle.getString("saved-state-search-query");
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public void h0(Bundle bundle) {
        super.h0(bundle);
        this.R0 = AbstractC0848Nx0.b(new C2528fT0());
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00fa A[SYNTHETIC] */
    @Override // defpackage.AbstractC4323py0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void j(int r12) {
        /*
        // Method dump skipped, instructions count: 322
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.password_manager.settings.PasswordSettings.j(int):void");
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void k0(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menuInflater.inflate(R.menu.f42490_resource_name_obfuscated_RES_2131689483, menu);
        menu.findItem(R.id.export_passwords).setVisible(true);
        menu.findItem(R.id.export_passwords).setEnabled(false);
        MenuItem findItem = menu.findItem(R.id.menu_id_search);
        this.J0 = findItem;
        findItem.setVisible(true);
        this.I0 = menu.findItem(R.id.menu_id_targeted_help);
        RQ0.d(this.J0, this.K0, u(), new C5004ty0(this));
    }

    public final void k1() {
        TextMessagePreference textMessagePreference = new TextMessagePreference(this.z0.f11127a, null);
        this.Q0 = textMessagePreference;
        textMessagePreference.S(R.string.f60890_resource_name_obfuscated_RES_2131953406);
        this.Q0.O("saved_passwords_no_text");
        this.Q0.P(7);
        TextMessagePreference textMessagePreference2 = this.Q0;
        Boolean bool = Boolean.FALSE;
        textMessagePreference2.v0 = bool;
        textMessagePreference2.w0 = bool;
        this.z0.g.a0(textMessagePreference2);
    }

    @Override // defpackage.AbstractC4323py0
    public void l(int i) {
        if (this.K0 == null) {
            u1("exceptions");
            v1();
            boolean z = i == 0;
            this.H0 = z;
            if (!z) {
                l1();
                PreferenceCategory preferenceCategory = new PreferenceCategory(this.z0.f11127a, null);
                preferenceCategory.O("exceptions");
                preferenceCategory.U(R.string.f61150_resource_name_obfuscated_RES_2131953432);
                preferenceCategory.P(6);
                this.z0.g.a0(preferenceCategory);
                for (int i2 = 0; i2 < i; i2++) {
                    C4834sy0 sy0 = AbstractC4664ry0.f11238a;
                    Objects.requireNonNull(sy0);
                    Object obj = ThreadUtils.f10596a;
                    PasswordUIView passwordUIView = sy0.F;
                    String Mtl3_dvG = N.Mtl3_dvG(passwordUIView.f10742a, passwordUIView, i2);
                    Preference preference = new Preference(this.z0.f11127a, null);
                    preference.V(Mtl3_dvG);
                    preference.K = this;
                    Bundle j = preference.j();
                    j.putString("url", Mtl3_dvG);
                    j.putInt("id", i2);
                    preferenceCategory.a0(preference);
                }
            } else if (this.G0) {
                k1();
            }
        }
    }

    public final void l1() {
        if (PasswordManagerLauncher.a()) {
            if ((this.K0 == null || this.G0) && this.z0.g.b0("manage_account_link") == null) {
                Preference preference = this.L0;
                if (preference != null) {
                    this.z0.g.a0(preference);
                    return;
                }
                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(I().getColor(R.color.f11590_resource_name_obfuscated_RES_2131099849));
                SpannableString a2 = FY0.a(O(R.string.f54300_resource_name_obfuscated_RES_2131952747), new EY0("<link>", "</link>", foregroundColorSpan));
                ChromeBasePreference chromeBasePreference = new ChromeBasePreference(this.z0.f11127a);
                this.L0 = chromeBasePreference;
                chromeBasePreference.O("manage_account_link");
                this.L0.V(a2);
                Preference preference2 = this.L0;
                preference2.K = this;
                preference2.P(3);
                this.z0.g.a0(this.L0);
            }
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void m0() {
        this.i0 = true;
        AbstractC4664ry0.f11238a.b(this);
        if (u().isFinishing() && this.R0 != null && this.S0 != 0) {
            AbstractC0848Nx0.a();
        }
    }

    public final PrefService m1() {
        return Wr1.a(Profile.b());
    }

    public final boolean n1(Object obj) {
        PrefService m1 = m1();
        N.Mf2ABpoH(m1.f10883a, "credentials_enable_autosignin", ((Boolean) obj).booleanValue());
        return true;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void o0() {
        this.i0 = true;
        AbstractC2852hK0.f10064a = null;
        AbstractC2852hK0.b = 0;
    }

    public final boolean o1() {
        return N.MrEgF7hX(m1().f10883a, "credentials_enable_autosignin");
    }

    public final boolean p1() {
        AbstractC0848Nx0.b(new C2528fT0()).a(this.z0.f11127a, 0);
        return true;
    }

    public final boolean q1(Object obj) {
        PrefService m1 = m1();
        N.Mf2ABpoH(m1.f10883a, "credentials_enable_service", ((Boolean) obj).booleanValue());
        return true;
    }

    public final boolean r1() {
        return N.MrEgF7hX(m1().f10883a, "credentials_enable_service");
    }

    public final boolean s1() {
        Activity u = u();
        String name = CableAuthenticatorModuleProvider.class.getName();
        Intent intent = new Intent();
        intent.setClass(u, XS0.class);
        if (!(u instanceof Activity)) {
            intent.addFlags(268435456);
            intent.addFlags(67108864);
        }
        if (name != null) {
            intent.putExtra("show_fragment", name);
        }
        U20.q(u, intent);
        return true;
    }

    public void t1() {
        this.G0 = false;
        this.H0 = false;
        this.z0.g.e0();
        if (this.K0 == null) {
            ChromeSwitchPreference chromeSwitchPreference = new ChromeSwitchPreference(this.z0.f11127a, null);
            this.N0 = chromeSwitchPreference;
            chromeSwitchPreference.O("save_passwords_switch");
            this.N0.U(R.string.f58050_resource_name_obfuscated_RES_2131953122);
            this.N0.P(0);
            this.N0.d0(R.string.f63330_resource_name_obfuscated_RES_2131953650);
            this.N0.b0(R.string.f63320_resource_name_obfuscated_RES_2131953649);
            ChromeSwitchPreference chromeSwitchPreference2 = this.N0;
            chromeSwitchPreference2.f9480J = new C5174uy0(this);
            C5344vy0 vy0 = new C5344vy0(this);
            chromeSwitchPreference2.B0 = vy0;
            AbstractC1865bc0.b(vy0, chromeSwitchPreference2);
            P21 f0 = P21.f0();
            try {
                this.z0.g.a0(this.N0);
                f0.close();
                this.N0.a0(N.MzIXnlkD(m1().f10883a, "credentials_enable_service"));
                ChromeSwitchPreference chromeSwitchPreference3 = new ChromeSwitchPreference(this.z0.f11127a, null);
                this.O0 = chromeSwitchPreference3;
                chromeSwitchPreference3.O("autosignin_switch");
                this.O0.U(R.string.f58090_resource_name_obfuscated_RES_2131953126);
                this.O0.P(1);
                this.O0.S(R.string.f58080_resource_name_obfuscated_RES_2131953125);
                ChromeSwitchPreference chromeSwitchPreference4 = this.O0;
                chromeSwitchPreference4.f9480J = new C5514wy0(this);
                C5684xy0 xy0 = new C5684xy0(this);
                chromeSwitchPreference4.B0 = xy0;
                AbstractC1865bc0.b(xy0, chromeSwitchPreference4);
                this.z0.g.a0(this.O0);
                this.O0.a0(N.MzIXnlkD(m1().f10883a, "credentials_enable_autosignin"));
                if (this.R0 != null) {
                    ChromeBasePreference chromeBasePreference = new ChromeBasePreference(this.z0.f11127a);
                    this.P0 = chromeBasePreference;
                    chromeBasePreference.O("check_passwords");
                    this.P0.U(R.string.f58110_resource_name_obfuscated_RES_2131953128);
                    this.P0.P(2);
                    this.P0.S(R.string.f58100_resource_name_obfuscated_RES_2131953127);
                    ChromeBasePreference chromeBasePreference2 = this.P0;
                    chromeBasePreference2.K = new C5854yy0(this);
                    this.z0.g.a0(chromeBasePreference2);
                }
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        }
        C4834sy0 sy0 = AbstractC4664ry0.f11238a;
        Objects.requireNonNull(sy0);
        Object obj = ThreadUtils.f10596a;
        PasswordUIView passwordUIView = sy0.F;
        N.MG_PqeQw(passwordUIView.f10742a, passwordUIView);
        return;
        throw th;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public boolean u0(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.export_passwords) {
            SM sm = this.T0;
            sm.f8891a = 1;
            sm.c = null;
            C4834sy0 sy0 = AbstractC4664ry0.f11238a;
            Objects.requireNonNull(sy0);
            Object obj = ThreadUtils.f10596a;
            PasswordUIView passwordUIView = sy0.F;
            P21 g0 = P21.g0();
            try {
                String str = ContextUtils.getApplicationContext().getCacheDir() + "/passwords";
                g0.close();
                N.MihpS3i5(passwordUIView.f10742a, passwordUIView, str, new LM(sm), new MM(sm));
                if (!AbstractC2852hK0.c(sm.g.a().getApplicationContext())) {
                    C1184Ti1.a(sm.g.a().getApplicationContext(), R.string.f57890_resource_name_obfuscated_RES_2131953106, 1).b.show();
                    sm.f8891a = 0;
                } else {
                    AbstractC2852hK0.b(R.string.f54210_resource_name_obfuscated_RES_2131952738, sm.g.f7709a.k0.getId(), sm.g.f7709a.W, 1);
                }
                return true;
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        } else if (RQ0.c(menuItem, this.J0, this.K0, u())) {
            this.K0 = null;
            this.I0.setShowAsAction(1);
            t1();
            return true;
        } else if (itemId != R.id.menu_id_targeted_help) {
            return false;
        } else {
            C2535fX.a().b(u(), O(R.string.f52580_resource_name_obfuscated_RES_2131952575), Profile.b(), null);
            return true;
        }
        throw th;
    }

    public final void u1(String str) {
        PreferenceCategory preferenceCategory = (PreferenceCategory) this.z0.g.b0(str);
        if (preferenceCategory != null) {
            preferenceCategory.e0();
            PreferenceScreen preferenceScreen = this.z0.g;
            preferenceScreen.g0(preferenceCategory);
            preferenceScreen.u();
        }
    }

    public final void v1() {
        Preference b0 = this.z0.g.b0("saved_passwords_no_text");
        if (b0 != null) {
            PreferenceScreen preferenceScreen = this.z0.g;
            preferenceScreen.g0(b0);
            preferenceScreen.u();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0016, code lost:
        if ((r3.T0.f8891a != 0) == false) goto L_0x001a;
     */
    @Override // defpackage.AbstractComponentCallbacksC3550lS
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void y0(android.view.Menu r4) {
        /*
            r3 = this;
            r0 = 2131427899(0x7f0b023b, float:1.8477427E38)
            android.view.MenuItem r4 = r4.findItem(r0)
            boolean r0 = r3.G0
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0019
            SM r0 = r3.T0
            int r0 = r0.f8891a
            if (r0 == 0) goto L_0x0015
            r0 = r1
            goto L_0x0016
        L_0x0015:
            r0 = r2
        L_0x0016:
            if (r0 != 0) goto L_0x0019
            goto L_0x001a
        L_0x0019:
            r1 = r2
        L_0x001a:
            r4.setEnabled(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.password_manager.settings.PasswordSettings.y0(android.view.Menu):void");
    }
}
