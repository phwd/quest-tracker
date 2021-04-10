package defpackage;

import android.os.SystemClock;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: rZ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4590rZ0 extends AbstractC0855Oa1 {
    public final /* synthetic */ AbstractC5780yZ0 c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4590rZ0(AbstractC5780yZ0 yz0, AbstractC0124Ca1 ca1) {
        super(ca1);
        this.c = yz0;
    }

    @Override // defpackage.AbstractC5783ya1
    public void G(Tab tab) {
        if (this.c.x()) {
            AbstractC5780yZ0 yz0 = this.c;
            long uptimeMillis = SystemClock.uptimeMillis();
            int id = tab.getId();
            AbstractC2882hZ0 hz0 = (AbstractC2882hZ0) ((C3565lZ0) yz0).a0.get(tab.a() ? 1 : 0);
            hz0.l(true);
            if (hz0.f != null) {
                int i = 0;
                while (true) {
                    IZ0[] iz0Arr = hz0.f;
                    if (i < iz0Arr.length) {
                        IZ0 iz0 = iz0Arr[i];
                        if (iz0.a() == id) {
                            iz0.r = hz0.s();
                            iz0.w = false;
                            iz0.C.k(J70.B, hz0.u());
                        }
                        i++;
                    } else {
                        hz0.e = hz0.i(iz0Arr.length);
                        hz0.V(uptimeMillis, 7, -1, false);
                        return;
                    }
                }
            }
        }
    }
}
