package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.ui.messages.infobar.SimpleConfirmInfoBarBuilder;

/* renamed from: Up1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Up1 implements SimpleConfirmInfoBarBuilder.Listener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Wp1 f9050a;

    public Up1(Wp1 wp1) {
        this.f9050a = wp1;
    }

    @Override // org.chromium.chrome.browser.ui.messages.infobar.SimpleConfirmInfoBarBuilder.Listener
    public boolean a(boolean z) {
        return false;
    }

    @Override // org.chromium.chrome.browser.ui.messages.infobar.SimpleConfirmInfoBarBuilder.Listener
    public void b() {
    }

    @Override // org.chromium.chrome.browser.ui.messages.infobar.SimpleConfirmInfoBarBuilder.Listener
    public boolean c() {
        Objects.requireNonNull(this.f9050a);
        AbstractC4981tq1.f11374a.c(1);
        return false;
    }
}
