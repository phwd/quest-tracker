package defpackage;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.chromium.chrome.browser.net.spdyproxy.DataReductionProxySettings;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: QC  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class QC extends AbstractC5079uP {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f8742a;

    public QC(Profile profile) {
        this.f8742a = profile.g();
    }

    @Override // defpackage.AbstractC5249vP, defpackage.AbstractC5079uP
    public Map c() {
        if (this.f8742a) {
            return null;
        }
        DataReductionProxySettings d = DataReductionProxySettings.d();
        Objects.requireNonNull(d);
        HashMap hashMap = new HashMap();
        hashMap.put("Data Reduction Proxy Enabled", String.valueOf(d.e()));
        return hashMap;
    }
}
