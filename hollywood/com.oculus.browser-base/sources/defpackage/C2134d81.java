package defpackage;

import org.chromium.chrome.browser.ui.messages.infobar.SimpleConfirmInfoBarBuilder;

/* renamed from: d81  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2134d81 implements SimpleConfirmInfoBarBuilder.Listener {
    public C2134d81(C2475f81 f81) {
    }

    @Override // org.chromium.chrome.browser.ui.messages.infobar.SimpleConfirmInfoBarBuilder.Listener
    public boolean a(boolean z) {
        if (!z) {
            NU0.f8549a.m("Chrome.ConditionalTabStrip.OptOut", true);
        }
        AbstractC3293jx.d(-1);
        return false;
    }

    @Override // org.chromium.chrome.browser.ui.messages.infobar.SimpleConfirmInfoBarBuilder.Listener
    public void b() {
    }

    @Override // org.chromium.chrome.browser.ui.messages.infobar.SimpleConfirmInfoBarBuilder.Listener
    public boolean c() {
        return false;
    }
}
