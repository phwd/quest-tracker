package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.net.spdyproxy.DataReductionProxySettings;
import org.chromium.chrome.browser.settings.MainSettings;

/* renamed from: Gb0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0370Gb0 extends AbstractC0896Or {
    public C0370Gb0(MainSettings mainSettings) {
    }

    @Override // defpackage.AbstractC1467Yb0, defpackage.AbstractC1528Zb0
    public boolean b(Preference preference) {
        if ("data_reduction".equals(preference.Q)) {
            DataReductionProxySettings d = DataReductionProxySettings.d();
            return d.f() && !d.e();
        } else if ("search_engine".equals(preference.Q)) {
            return AbstractC0444Hf1.a().f();
        } else {
            return d(preference);
        }
    }

    @Override // defpackage.AbstractC1528Zb0
    public boolean d(Preference preference) {
        if ("data_reduction".equals(preference.Q)) {
            return DataReductionProxySettings.d().f();
        }
        if ("search_engine".equals(preference.Q)) {
            return AbstractC0444Hf1.a().f();
        }
        return false;
    }
}
