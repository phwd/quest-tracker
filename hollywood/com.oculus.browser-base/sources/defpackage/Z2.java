package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Z2  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Z2 implements W2 {
    @Override // defpackage.W2
    public final void a(Tab tab, boolean z) {
        if (!z) {
            b(tab);
        }
    }

    public abstract void b(Tab tab);
}
