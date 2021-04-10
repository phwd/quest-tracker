package defpackage;

import com.oculus.browser.R;
import org.chromium.chrome.browser.accessibility_tab_switcher.AccessibilityTabModelWrapper;

/* renamed from: T  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class T implements AbstractC5716y81 {
    public final /* synthetic */ AccessibilityTabModelWrapper F;

    public T(AccessibilityTabModelWrapper accessibilityTabModelWrapper) {
        this.F = accessibilityTabModelWrapper;
    }

    @Override // defpackage.AbstractC5546x81
    public void a(D81 d81) {
    }

    @Override // defpackage.AbstractC5546x81
    public void c(D81 d81) {
    }

    @Override // defpackage.AbstractC5546x81
    public void f(D81 d81) {
        AccessibilityTabModelWrapper accessibilityTabModelWrapper = this.F;
        boolean a2 = accessibilityTabModelWrapper.K.a();
        AbstractC0124Ca1 ca1 = accessibilityTabModelWrapper.R;
        if (ca1 != null && a2 != ((AbstractC0246Ea1) ca1).r()) {
            accessibilityTabModelWrapper.R.d();
            accessibilityTabModelWrapper.R.e(a2);
            accessibilityTabModelWrapper.b();
            accessibilityTabModelWrapper.announceForAccessibility(accessibilityTabModelWrapper.getResources().getString(a2 ? R.string.f45970_resource_name_obfuscated_RES_2131951914 : R.string.f45990_resource_name_obfuscated_RES_2131951916));
        }
    }
}
