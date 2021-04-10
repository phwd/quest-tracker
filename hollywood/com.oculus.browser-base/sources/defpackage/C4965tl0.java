package defpackage;

import org.chromium.chrome.browser.ui.messages.infobar.SimpleConfirmInfoBarBuilder;

/* renamed from: tl0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4965tl0 implements SimpleConfirmInfoBarBuilder.Listener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C5305vl0 f11367a;

    public C4965tl0(C5305vl0 vl0) {
        this.f11367a = vl0;
    }

    @Override // org.chromium.chrome.browser.ui.messages.infobar.SimpleConfirmInfoBarBuilder.Listener
    public boolean a(boolean z) {
        AbstractC5135ul0 ul0 = this.f11367a.c;
        if (ul0 == null) {
            return false;
        }
        ul0.a(z);
        return false;
    }

    @Override // org.chromium.chrome.browser.ui.messages.infobar.SimpleConfirmInfoBarBuilder.Listener
    public void b() {
        AbstractC5135ul0 ul0 = this.f11367a.c;
        if (ul0 != null) {
            ul0.a(false);
        }
    }

    @Override // org.chromium.chrome.browser.ui.messages.infobar.SimpleConfirmInfoBarBuilder.Listener
    public boolean c() {
        return false;
    }
}
