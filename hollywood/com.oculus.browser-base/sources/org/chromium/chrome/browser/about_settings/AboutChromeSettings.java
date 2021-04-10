package org.chromium.chrome.browser.about_settings;

import J.N;
import android.os.Bundle;
import androidx.preference.Preference;
import com.oculus.browser.R;
import java.util.Calendar;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AboutChromeSettings extends AbstractC2324eF0 implements YE0 {
    public int G0;
    public C1184Ti1 H0;

    public AboutChromeSettings() {
        this.G0 = NU0.f8549a.d("developer", false) ? -1 : 7;
    }

    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        String str;
        int i = this.G0;
        if (i > 0) {
            int i2 = i - 1;
            this.G0 = i2;
            if (i2 == 0) {
                NU0.f8549a.m("developer", true);
                C1184Ti1 ti1 = this.H0;
                if (ti1 != null) {
                    ti1.b.cancel();
                }
                C1184Ti1 b = C1184Ti1.b(u(), "Developer options are now enabled.", 1);
                this.H0 = b;
                b.b.show();
            } else if (i2 > 0 && i2 < 5) {
                C1184Ti1 ti12 = this.H0;
                if (ti12 != null) {
                    ti12.b.cancel();
                }
                int i3 = this.G0;
                if (i3 == 1) {
                    str = "1 more tap to enable Developer options.";
                } else {
                    str = String.format("%s more taps to enable Developer options.", Integer.valueOf(i3));
                }
                C1184Ti1 b2 = C1184Ti1.b(u(), str, 0);
                this.H0 = b2;
                b2.b.show();
            }
        } else if (i < 0) {
            C1184Ti1 ti13 = this.H0;
            if (ti13 != null) {
                ti13.b.cancel();
            }
            C1184Ti1 b3 = C1184Ti1.b(u(), "Developer options are already enabled.", 1);
            this.H0 = b3;
            b3.b.show();
        }
        return true;
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        u().setTitle(R.string.f59040_resource_name_obfuscated_RES_2131953221);
        AbstractC2870hT0.a(this, R.xml.f76040_resource_name_obfuscated_RES_2132213760);
        Preference f1 = f1("application_version");
        u();
        f1.T(N.MMSdy2S5());
        f1.K = this;
        f1("os_version").T(N.M6bT9QjF());
        f1("legal_information").T(P(R.string.f54080_resource_name_obfuscated_RES_2131952725, Integer.valueOf(Calendar.getInstance().get(1))));
    }
}
