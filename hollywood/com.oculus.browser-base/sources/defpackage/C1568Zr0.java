package defpackage;

import J.N;
import java.util.ArrayList;
import org.chromium.chrome.browser.offlinepages.ClientId;
import org.chromium.chrome.browser.offlinepages.OfflinePageBridge;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Zr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1568Zr0 extends AbstractC0855Oa1 {
    public static final C1913bs0 c = new C1913bs0(null);
    public AbstractC0124Ca1 d;

    public C1568Zr0(AbstractC0124Ca1 ca1) {
        super(ca1);
        this.d = ca1;
    }

    @Override // defpackage.AbstractC5783ya1
    public void A(int i, boolean z) {
        OfflinePageBridge a2 = OfflinePageBridge.a(((AbstractC0246Ea1) this.d).l(z).b());
        if (a2 != null) {
            ClientId clientId = new ClientId("last_n", Integer.toString(i));
            ArrayList arrayList = new ArrayList();
            arrayList.add(clientId);
            C1507Yr0 yr0 = new C1507Yr0(this);
            String[] strArr = new String[arrayList.size()];
            String[] strArr2 = new String[arrayList.size()];
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                strArr[i2] = ((ClientId) arrayList.get(i2)).f10716a;
                strArr2[i2] = ((ClientId) arrayList.get(i2)).b;
            }
            N.Mwp3hyBt(a2.f10718a, a2, strArr, strArr2, yr0);
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void K(Tab tab, boolean z) {
        WebContents l;
        OfflinePageBridge a2 = OfflinePageBridge.a(((AbstractC0246Ea1) this.d).l(tab.a()).b());
        if (a2 != null && (l = tab.l()) != null) {
            N.MIDiWOi_(a2.f10718a, a2, l);
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void z(Tab tab, int i, int i2) {
        tab.A(c);
    }
}
