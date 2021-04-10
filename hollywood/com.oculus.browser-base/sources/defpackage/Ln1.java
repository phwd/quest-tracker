package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TrustedCdn;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: Ln1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Ln1 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TrustedCdn f8440a;

    public Ln1(TrustedCdn trustedCdn) {
        this.f8440a = trustedCdn;
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
        TrustedCdn trustedCdn = this.f8440a;
        Objects.requireNonNull(trustedCdn);
        trustedCdn.I = windowAndroid != null ? (Mn1) Mn1.C.e(windowAndroid.U) : null;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void r(Tab tab) {
        tab.I(this);
    }
}
