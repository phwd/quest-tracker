package defpackage;

import org.chromium.chrome.browser.accessibility_tab_switcher.AccessibilityTabModelListItem;

/* renamed from: M  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class M implements Runnable {
    public final /* synthetic */ AccessibilityTabModelListItem F;

    public M(AccessibilityTabModelListItem accessibilityTabModelListItem) {
        this.F = accessibilityTabModelListItem;
    }

    public void run() {
        AccessibilityTabModelListItem accessibilityTabModelListItem = this.F;
        int i = AccessibilityTabModelListItem.F;
        accessibilityTabModelListItem.c();
    }
}
