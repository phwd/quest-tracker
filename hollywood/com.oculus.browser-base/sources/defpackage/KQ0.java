package defpackage;

import java.util.HashSet;
import java.util.Iterator;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: KQ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class KQ0 extends Pr1 {
    public HashSet F = new HashSet();

    public static KQ0 c(Tab tab) {
        return (KQ0) tab.M().c(KQ0.class);
    }

    public void e() {
        Iterator it = this.F.iterator();
        while (it.hasNext()) {
            ((HQ0) it.next()).f8157a.clear();
        }
    }
}
