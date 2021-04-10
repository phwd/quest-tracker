package defpackage;

import android.app.Activity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.net.NetworkChangeNotifier;

/* renamed from: Kr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0654Kr0 implements Z9 {
    @Override // defpackage.Z9
    public void t(Activity activity, int i) {
        C0897Or0 or0;
        if (i == 6 && (or0 = (C0897Or0) C0897Or0.F.remove(activity)) != null) {
            or0.f8653J.destroy();
            if (!or0.K.isEmpty()) {
                for (Integer num : or0.K.keySet()) {
                    Tab o = ((AbstractC0246Ea1) or0.I).o(num.intValue());
                    if (o != null) {
                        o.I(or0);
                    }
                }
                or0.K.clear();
            }
            if (or0.L) {
                NetworkChangeNotifier.j(or0);
                or0.L = false;
            }
        }
    }
}
