package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.datareduction.settings.DataReductionPreferenceFragment;
import org.chromium.chrome.browser.net.spdyproxy.DataReductionProxySettings;

/* renamed from: LC  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class LC extends AbstractC0896Or {
    @Override // defpackage.AbstractC1528Zb0
    public boolean d(Preference preference) {
        int i = DataReductionPreferenceFragment.G0;
        return AbstractC1575Zv.e().g("enable-spdy-proxy-auth") || DataReductionProxySettings.d().f();
    }
}
