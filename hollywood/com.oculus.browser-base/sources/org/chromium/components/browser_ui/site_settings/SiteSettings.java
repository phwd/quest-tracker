package org.chromium.components.browser_ui.site_settings;

import J.N;
import android.os.Bundle;
import androidx.preference.Preference;
import com.oculus.browser.R;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SiteSettings extends SiteSettingsPreferenceFragment implements YE0 {
    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void B0() {
        this.i0 = true;
        l1();
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void b0(Bundle bundle) {
        this.i0 = true;
        i1(null);
    }

    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        preference.j().putString("category", preference.Q);
        preference.j().putString("title", preference.M.toString());
        return false;
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        AbstractC2870hT0.a(this, R.xml.f76400_resource_name_obfuscated_RES_2132213796);
        u().setTitle(x().getString(R.string.f59280_resource_name_obfuscated_RES_2131953245));
        for (int i = 0; i < 23; i++) {
            Objects.requireNonNull(this.G0);
            boolean z = true;
            if (i == 1) {
                z = QX0.a();
            } else if (i == 5) {
                z = AbstractC1575Zv.e().g("enable-experimental-web-platform-features");
            } else if (i == 13) {
                z = N.Mudil8Bg("WebNFC");
            } else if (i == 20) {
                z = N.Mudil8Bg("WebBluetoothNewPermissionsBackend");
            }
            if (!z) {
                this.z0.g.f0(k1(i));
            }
        }
        l1();
    }

    public final Preference k1(int i) {
        return f1(QX0.p(i));
    }

    /* JADX WARNING: Removed duplicated region for block: B:56:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00f4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void l1() {
        /*
        // Method dump skipped, instructions count: 286
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.browser_ui.site_settings.SiteSettings.l1():void");
    }
}
