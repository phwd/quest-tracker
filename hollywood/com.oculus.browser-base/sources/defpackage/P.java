package defpackage;

import android.graphics.Bitmap;
import org.chromium.chrome.browser.accessibility_tab_switcher.AccessibilityTabModelListItem;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: P  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class P extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AccessibilityTabModelListItem f8660a;

    public P(AccessibilityTabModelListItem accessibilityTabModelListItem) {
        this.f8660a = accessibilityTabModelListItem;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void Q(Tab tab) {
        AccessibilityTabModelListItem accessibilityTabModelListItem = this.f8660a;
        int i = AccessibilityTabModelListItem.F;
        accessibilityTabModelListItem.h();
        AccessibilityTabModelListItem.a(this.f8660a, tab);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void S(Tab tab) {
        AccessibilityTabModelListItem accessibilityTabModelListItem = this.f8660a;
        int i = AccessibilityTabModelListItem.F;
        accessibilityTabModelListItem.h();
        AccessibilityTabModelListItem.a(this.f8660a, tab);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void m(Tab tab, boolean z) {
        K k;
        if (z) {
            AccessibilityTabModelListItem accessibilityTabModelListItem = this.f8660a;
            if (!accessibilityTabModelListItem.k0 && (k = accessibilityTabModelListItem.g0) != null) {
                tab.getId();
                k.f8333a.notifyDataSetChanged();
            }
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void x(Tab tab, Bitmap bitmap) {
        AccessibilityTabModelListItem accessibilityTabModelListItem = this.f8660a;
        int i = AccessibilityTabModelListItem.F;
        accessibilityTabModelListItem.g();
        AccessibilityTabModelListItem.a(this.f8660a, tab);
    }
}
