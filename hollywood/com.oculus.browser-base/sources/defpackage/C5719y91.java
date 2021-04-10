package defpackage;

import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.GURL;

/* renamed from: y91  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5719y91 extends WK {
    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void K(Tab tab, GURL gurl) {
        if (tab.l() != null) {
            PostTask.b(Zo1.d, new RunnableC5549x91(tab), 0);
            tab.I(AbstractC5889z91.f11729a);
        }
    }
}
