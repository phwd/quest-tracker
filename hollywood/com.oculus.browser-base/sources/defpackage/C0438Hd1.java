package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: Hd1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0438Hd1 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractC0499Id1 f8167a;

    public C0438Hd1(AbstractC0499Id1 id1) {
        this.f8167a = id1;
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void n(Tab tab) {
        if (this.f8167a.F != tab.l()) {
            AbstractC0499Id1 id1 = this.f8167a;
            WebContents webContents = id1.F;
            if (webContents != null) {
                id1.c(webContents);
            }
            this.f8167a.F = tab.l();
            AbstractC0499Id1 id12 = this.f8167a;
            WebContents webContents2 = id12.F;
            if (webContents2 != null) {
                id12.h(webContents2);
            }
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void r(Tab tab) {
        tab.I(this);
    }
}
