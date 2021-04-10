package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: DU0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DU0 {
    public boolean a(Tab tab) {
        String s = tab.s();
        return !(s.startsWith("chrome://") || s.startsWith("chrome-native://")) && !s.startsWith("data");
    }
}
