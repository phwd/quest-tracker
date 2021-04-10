package defpackage;

import org.chromium.chrome.browser.firstrun.TosAndUmaFirstRunFragmentWithEnterpriseSupport;

/* renamed from: km1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3433km1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final TosAndUmaFirstRunFragmentWithEnterpriseSupport f10302a;

    public C3433km1(TosAndUmaFirstRunFragmentWithEnterpriseSupport tosAndUmaFirstRunFragmentWithEnterpriseSupport) {
        this.f10302a = tosAndUmaFirstRunFragmentWithEnterpriseSupport;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Boolean bool = (Boolean) obj;
        this.f10302a.n1();
    }
}
