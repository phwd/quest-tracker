package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.chrome.browser.browsing_data.BrowsingDataBridge;

/* renamed from: Ou  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0902Ou implements AbstractC5716y81 {
    public C0902Ou(AbstractC0780Mu mu) {
    }

    @Override // defpackage.AbstractC5546x81
    public void a(D81 d81) {
    }

    @Override // defpackage.AbstractC5546x81
    public void c(D81 d81) {
    }

    @Override // defpackage.AbstractC5546x81
    public void f(D81 d81) {
        int i = d81.d;
        BrowsingDataBridge c = BrowsingDataBridge.c();
        Objects.requireNonNull(c);
        N.MrfS11o2(c, i);
        if (i == 0) {
            AbstractC3535lK0.a("ClearBrowsingData_SwitchTo_BasicTab");
        } else {
            AbstractC3535lK0.a("ClearBrowsingData_SwitchTo_AdvancedTab");
        }
    }
}
