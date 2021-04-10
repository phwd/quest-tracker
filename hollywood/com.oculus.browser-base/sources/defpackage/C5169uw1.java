package defpackage;

import com.oculus.browser.VrShellDelegate;
import com.oculus.browser.WebVRNavigationDescriptor;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.url.GURL;

/* renamed from: uw1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5169uw1 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ VrShellDelegate f11447a;

    public C5169uw1(VrShellDelegate vrShellDelegate) {
        this.f11447a = vrShellDelegate;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void I(Tab tab, int i) {
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "requestVrNavigation.onPageLoadFailed, %s", tab.getUrl().h());
        WebVRNavigationDescriptor webVRNavigationDescriptor = this.f11447a.U;
        if (webVRNavigationDescriptor != null && webVRNavigationDescriptor.a() == tab) {
            this.f11447a.c(tab);
        }
        ((TabImpl) tab).P.c(this);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void J(Tab tab, GURL gurl) {
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "requestVrNavigation.onPageLoadFinished, %s: error = %b", tab.getUrl().h(), Boolean.valueOf(tab.p()));
        WebVRNavigationDescriptor webVRNavigationDescriptor = this.f11447a.U;
        if (webVRNavigationDescriptor != null && webVRNavigationDescriptor.b(tab)) {
            this.f11447a.A();
        }
        tab.I(this);
    }
}
