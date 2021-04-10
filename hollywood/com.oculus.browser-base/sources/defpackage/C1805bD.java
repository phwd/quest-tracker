package defpackage;

import java.io.Serializable;
import java.util.Comparator;

/* renamed from: bD  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1805bD implements Comparator, Serializable {
    public C1805bD(WC wc) {
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        HC hc = (HC) obj;
        HC hc2 = (HC) obj2;
        if ("Other".equals(hc.f8142a)) {
            return 1;
        }
        if (!"Other".equals(hc2.f8142a)) {
            int i = (hc.b > hc2.b ? 1 : (hc.b == hc2.b ? 0 : -1));
            if (i < 0) {
                return 1;
            }
            if (i <= 0) {
                return 0;
            }
        }
        return -1;
    }
}
