package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: nd  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3917nd extends Z2 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ChromeActivity f10503a;
    public final /* synthetic */ Callback b;

    public C3917nd(ChromeActivity chromeActivity, Callback callback) {
        this.f10503a = chromeActivity;
        this.b = callback;
    }

    @Override // defpackage.Z2
    public void b(Tab tab) {
        if (tab != null) {
            this.f10503a.W0.F.c(this);
            this.b.onResult(tab);
        }
    }
}
