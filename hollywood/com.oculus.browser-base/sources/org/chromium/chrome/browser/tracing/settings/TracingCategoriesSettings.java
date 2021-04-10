package org.chromium.chrome.browser.tracing.settings;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.chromium.components.browser_ui.settings.ChromeBaseCheckBoxPreference;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TracingCategoriesSettings extends AbstractC2324eF0 implements XE0 {
    public int G0;
    public Set H0;
    public List I0;
    public ChromeBaseCheckBoxPreference J0;

    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        if (TextUtils.equals("select-all", preference.Q)) {
            for (ChromeBaseCheckBoxPreference chromeBaseCheckBoxPreference : this.I0) {
                chromeBaseCheckBoxPreference.a0(booleanValue);
                chromeBaseCheckBoxPreference.f(Boolean.valueOf(chromeBaseCheckBoxPreference.t0));
            }
            return true;
        }
        if (booleanValue) {
            this.H0.add(preference.Q);
        } else {
            this.H0.remove(preference.Q);
        }
        this.J0.a0(this.H0.size() == this.I0.size());
        int i = this.G0;
        Set set = this.H0;
        Map map = TracingSettings.G0;
        HashSet hashSet = new HashSet(set);
        for (String str : TracingSettings.l1()) {
            if (i != TracingSettings.k1(str)) {
                hashSet.add(str);
            }
        }
        NU0.f8549a.q("tracing_categories", hashSet);
        return true;
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        u().setTitle("Select categories");
        C4375qF0 qf0 = this.z0;
        PreferenceScreen a2 = qf0.a(qf0.f11127a);
        boolean z = true;
        a2.w0 = true;
        this.G0 = this.K.getInt("type");
        this.H0 = new HashSet(TracingSettings.m1(this.G0));
        this.I0 = new ArrayList();
        ArrayList arrayList = new ArrayList(Lm1.a().e);
        Collections.sort(arrayList);
        ChromeBaseCheckBoxPreference chromeBaseCheckBoxPreference = new ChromeBaseCheckBoxPreference(this.z0.f11127a, null);
        this.J0 = chromeBaseCheckBoxPreference;
        chromeBaseCheckBoxPreference.O("select-all");
        this.J0.V("Select all");
        Preference preference = this.J0;
        preference.X = false;
        preference.f9480J = this;
        a2.a0(preference);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (TracingSettings.k1(str2) == this.G0) {
                ChromeBaseCheckBoxPreference chromeBaseCheckBoxPreference2 = new ChromeBaseCheckBoxPreference(this.z0.f11127a, null);
                chromeBaseCheckBoxPreference2.O(str2);
                chromeBaseCheckBoxPreference2.V(str2.startsWith("disabled-by-default-") ? str2.substring(20) : str2);
                chromeBaseCheckBoxPreference2.a0(this.H0.contains(str2));
                chromeBaseCheckBoxPreference2.X = false;
                chromeBaseCheckBoxPreference2.f9480J = this;
                this.I0.add(chromeBaseCheckBoxPreference2);
                a2.a0(chromeBaseCheckBoxPreference2);
            }
        }
        ChromeBaseCheckBoxPreference chromeBaseCheckBoxPreference3 = this.J0;
        if (this.H0.size() != this.I0.size()) {
            z = false;
        }
        chromeBaseCheckBoxPreference3.a0(z);
        j1(a2);
    }
}
