package org.chromium.chrome.browser.download.settings;

import J.N;
import android.os.Bundle;
import androidx.preference.Preference;
import com.oculus.browser.R;
import org.chromium.chrome.browser.download.DownloadDialogBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileKey;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;
import org.chromium.components.prefs.PrefService;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DownloadSettings extends AbstractC2324eF0 implements XE0 {
    public PrefService G0;
    public DownloadLocationPreference H0;
    public ChromeSwitchPreference I0;
    public ChromeSwitchPreference J0;
    public ChromeSwitchPreference K0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void B0() {
        boolean z = true;
        this.i0 = true;
        this.H0.a0();
        if (N.M09VlOh_("DownloadLater")) {
            this.I0.a0(N.MzGf81GW(this.G0.f10883a, "download.download_later_prompt_status") != 2);
        }
        if (DownloadDialogBridge.b() == 2) {
            z = false;
        }
        this.J0.a0(z);
        ChromeSwitchPreference chromeSwitchPreference = this.K0;
        if (chromeSwitchPreference != null) {
            chromeSwitchPreference.a0(N.MNfhveva(ProfileKey.a()));
            k1();
        }
    }

    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        if ("download_later_prompt_enabled".equals(preference.Q)) {
            if (!N.M09VlOh_("DownloadLater")) {
                return false;
            }
            int MzGf81GW = N.MzGf81GW(this.G0.f10883a, "download.download_later_prompt_status");
            if (!((Boolean) obj).booleanValue()) {
                N.MPBZLcVx(this.G0.f10883a, "download.download_later_prompt_status", 2);
                return true;
            } else if (MzGf81GW != 0) {
                N.MPBZLcVx(this.G0.f10883a, "download.download_later_prompt_status", 1);
            }
        } else if ("location_prompt_enabled".equals(preference.Q)) {
            if (!((Boolean) obj).booleanValue()) {
                DownloadDialogBridge.f(2);
            } else if (DownloadDialogBridge.b() != 0) {
                DownloadDialogBridge.f(1);
            }
        } else if ("prefetching_enabled".equals(preference.Q)) {
            N.MBd5XB3K(ProfileKey.a(), ((Boolean) obj).booleanValue());
            k1();
        }
        return true;
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        u().setTitle(R.string.f54520_resource_name_obfuscated_RES_2131952769);
        AbstractC2870hT0.a(this, R.xml.f76180_resource_name_obfuscated_RES_2132213774);
        this.G0 = Wr1.a(Profile.b());
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f1("download_later_prompt_enabled");
        this.I0 = chromeSwitchPreference;
        chromeSwitchPreference.f9480J = this;
        if (!N.M09VlOh_("DownloadLater")) {
            this.z0.g.f0(f1("download_later_prompt_enabled"));
        }
        ChromeSwitchPreference chromeSwitchPreference2 = (ChromeSwitchPreference) f1("location_prompt_enabled");
        this.J0 = chromeSwitchPreference2;
        chromeSwitchPreference2.f9480J = this;
        this.H0 = (DownloadLocationPreference) f1("location_change");
        if (AbstractC5056uF0.a()) {
            ChromeSwitchPreference chromeSwitchPreference3 = (ChromeSwitchPreference) f1("prefetching_enabled");
            this.K0 = chromeSwitchPreference3;
            chromeSwitchPreference3.f9480J = this;
            k1();
            return;
        }
        this.z0.g.f0(f1("prefetching_enabled"));
    }

    @Override // defpackage.AbstractC2324eF0, defpackage.AbstractC3862nF0
    public void k(Preference preference) {
        if (preference instanceof DownloadLocationPreference) {
            DownloadLocationPreferenceDialog downloadLocationPreferenceDialog = new DownloadLocationPreferenceDialog();
            Bundle bundle = new Bundle(1);
            bundle.putString("key", ((DownloadLocationPreference) preference).Q);
            downloadLocationPreferenceDialog.U0(bundle);
            downloadLocationPreferenceDialog.b1(this, 0);
            downloadLocationPreferenceDialog.k1(this.W, "DownloadLocationPreferenceDialog");
            return;
        }
        super.k(preference);
    }

    public final void k1() {
        ProfileKey a2 = ProfileKey.a();
        if (N.MmFeqmtn(a2)) {
            ChromeSwitchPreference chromeSwitchPreference = this.K0;
            chromeSwitchPreference.u0 = "";
            if (chromeSwitchPreference.t0) {
                chromeSwitchPreference.s();
            }
        } else if (!N.MNfhveva(a2)) {
        } else {
            if (N.M94kN9ol(a2)) {
                this.K0.d0(R.string.f51690_resource_name_obfuscated_RES_2131952486);
            } else {
                this.K0.d0(R.string.f51700_resource_name_obfuscated_RES_2131952487);
            }
        }
    }
}
