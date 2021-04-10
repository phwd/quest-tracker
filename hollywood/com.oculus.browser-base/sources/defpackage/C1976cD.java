package defpackage;

import java.io.Serializable;
import java.util.Comparator;

/* renamed from: cD  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1976cD implements Comparator, Serializable {
    public C1976cD(WC wc) {
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        HC hc = (HC) obj;
        HC hc2 = (HC) obj2;
        if ("Other".equals(hc.f8142a)) {
            return 1;
        }
        if ("Other".equals(hc2.f8142a)) {
            return -1;
        }
        return hc.f8142a.compareTo(hc2.f8142a);
    }
}
