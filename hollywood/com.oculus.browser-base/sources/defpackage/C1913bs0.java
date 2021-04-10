package defpackage;

import J.N;
import org.chromium.chrome.browser.offlinepages.OfflinePageItem;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.url.GURL;

/* renamed from: bs0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1913bs0 extends WK {
    public C1913bs0(C1202Tr0 tr0) {
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void I(Tab tab, int i) {
        if (((TabImpl) tab).Z) {
            AbstractC2254ds0.a(8, null);
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void J(Tab tab, GURL gurl) {
        if (tab.J()) {
            boolean e = AbstractC2254ds0.e();
            OfflinePageItem d = AbstractC2254ds0.d(tab.l());
            if (d != null) {
                e |= true;
                if (d.c.f10716a.equals("last_n")) {
                    e |= true;
                }
            } else if (!N.MXyz2Okt(tab.s()) || tab.a()) {
                e |= true;
            }
            int i = 5;
            if (!e) {
                i = 4;
            } else if (e) {
                i = 0;
            } else if (!e) {
                if (e) {
                    i = 1;
                } else if (e) {
                    i = 6;
                } else if (e) {
                    i = 2;
                } else if (e) {
                    i = 7;
                } else if (e) {
                    i = 3;
                } else {
                    return;
                }
            }
            AbstractC2254ds0.a(i, tab.s());
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void q(Tab tab) {
        if (tab.J()) {
            AbstractC2254ds0.a(9, null);
        }
    }
}
