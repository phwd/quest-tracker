package org.chromium.chrome.browser.autofill.settings;

import android.os.Bundle;
import androidx.preference.Preference;
import com.oculus.browser.R;
import org.chromium.chrome.browser.autofill.PersonalDataManager;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AutofillServerProfileFragment extends AbstractC2324eF0 implements YE0 {
    public String G0;

    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        AbstractActivityC5822yn1.r1(preference.F, "https://payments.google.com/#paymentMethods");
        return true;
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        AbstractC2870hT0.a(this, R.xml.f76100_resource_name_obfuscated_RES_2132213766);
        u().setTitle(R.string.f47380_resource_name_obfuscated_RES_2131952055);
        Bundle bundle2 = this.K;
        if (bundle2 != null) {
            this.G0 = bundle2.getString("guid");
        }
        PersonalDataManager.AutofillProfile e = PersonalDataManager.c().e(this.G0);
        if (e == null) {
            u().finish();
            return;
        }
        Preference f1 = f1("server_profile_description");
        f1.V(e.getFullName());
        f1.T(e.getStreetAddress());
        f1("server_profile_edit_link").K = this;
    }
}
