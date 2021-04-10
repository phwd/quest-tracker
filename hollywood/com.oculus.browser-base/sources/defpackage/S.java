package defpackage;

import org.chromium.chrome.browser.accessibility_tab_switcher.AccessibilityTabModelWrapper;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: S  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class S extends VK {
    public final /* synthetic */ AccessibilityTabModelWrapper F;

    public S(AccessibilityTabModelWrapper accessibilityTabModelWrapper) {
        this.F = accessibilityTabModelWrapper;
    }

    @Override // defpackage.VK, defpackage.AbstractC0612Ka1
    public void e() {
        AccessibilityTabModelWrapper accessibilityTabModelWrapper = this.F;
        int i = AccessibilityTabModelWrapper.F;
        accessibilityTabModelWrapper.a().notifyDataSetChanged();
        this.F.d();
    }

    @Override // defpackage.VK, defpackage.AbstractC0612Ka1
    public void f(Tab tab, int i) {
        AccessibilityTabModelWrapper accessibilityTabModelWrapper = this.F;
        int i2 = AccessibilityTabModelWrapper.F;
        accessibilityTabModelWrapper.a().notifyDataSetChanged();
    }
}
