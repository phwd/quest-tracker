package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Mr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0775Mr0 extends AbstractC4758sY0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractC0124Ca1 f8507a;

    public C0775Mr0(AbstractC0124Ca1 ca1) {
        this.f8507a = ca1;
    }

    @Override // defpackage.AbstractC4758sY0
    public void c(Object obj) {
        int intValue = ((Integer) obj).intValue();
        AbstractC3535lK0.a("OfflinePages.ReloadButtonClicked");
        Tab o = ((AbstractC0246Ea1) this.f8507a).o(intValue);
        if (o != null) {
            if (!AbstractC2254ds0.h(o.l())) {
                AbstractC3535lK0.a("OfflinePages.ReloadButtonClickedViewingUntrustedPage");
            }
            o.q();
        }
    }

    @Override // defpackage.AbstractC4758sY0
    public void d(Object obj) {
        AbstractC3535lK0.a("OfflinePages.ReloadButtonNotClicked");
    }
}
