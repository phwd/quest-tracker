package defpackage;

import android.os.SystemClock;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: o70  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4009o70 extends C70 {
    public final /* synthetic */ C4180p70 b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4009o70(C4180p70 p70) {
        super(p70);
        this.b = p70;
    }

    @Override // defpackage.AbstractC5783ya1
    public void K(Tab tab, boolean z) {
        if (z) {
            C4180p70 p70 = this.b;
            int id = tab.getId();
            if (p70.z(id) != null) {
                if (p70.S.t()) {
                    p70.S.C(SystemClock.uptimeMillis(), id);
                } else if (p70.w0) {
                    p70.F(p70.C0, false);
                    p70.S.C(SystemClock.uptimeMillis(), id);
                }
            }
        }
    }
}
