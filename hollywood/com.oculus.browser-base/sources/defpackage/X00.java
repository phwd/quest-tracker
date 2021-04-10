package defpackage;

import com.oculus.browser.R;
import org.chromium.chrome.browser.toolbar.IncognitoToggleTabLayout;

/* renamed from: X00  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class X00 implements AbstractC5716y81 {
    public final /* synthetic */ IncognitoToggleTabLayout F;

    public X00(IncognitoToggleTabLayout incognitoToggleTabLayout) {
        this.F = incognitoToggleTabLayout;
    }

    @Override // defpackage.AbstractC5546x81
    public void a(D81 d81) {
    }

    @Override // defpackage.AbstractC5546x81
    public void c(D81 d81) {
    }

    @Override // defpackage.AbstractC5546x81
    public void f(D81 d81) {
        IncognitoToggleTabLayout incognitoToggleTabLayout = this.F;
        boolean a2 = incognitoToggleTabLayout.y0.a();
        AbstractC0124Ca1 ca1 = incognitoToggleTabLayout.G0;
        if (ca1 != null && a2 != ((AbstractC0246Ea1) ca1).r()) {
            incognitoToggleTabLayout.G0.d();
            incognitoToggleTabLayout.G0.e(a2);
            incognitoToggleTabLayout.announceForAccessibility(incognitoToggleTabLayout.getResources().getString(a2 ? R.string.f45970_resource_name_obfuscated_RES_2131951914 : R.string.f45990_resource_name_obfuscated_RES_2131951916));
        }
    }
}
