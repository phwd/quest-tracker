package defpackage;

import android.os.SystemClock;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: rX0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4586rX0 extends AbstractC5783ya1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C4926tX0 f11203a;

    public C4586rX0(C4926tX0 tx0) {
        this.f11203a = tx0;
    }

    @Override // defpackage.AbstractC5783ya1
    public void C(Tab tab, int i, int i2) {
        C4926tX0 tx0 = this.f11203a;
        tx0.i = false;
        tx0.i(tab);
        if (i != 0) {
            C4926tX0 tx02 = this.f11203a;
            if (!tx02.h) {
                tx02.g.a(SystemClock.uptimeMillis(), tab.getId());
                return;
            }
        }
        this.f11203a.h = false;
    }
}
