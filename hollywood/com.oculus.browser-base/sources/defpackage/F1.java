package defpackage;

import android.text.TextUtils;
import java.util.Iterator;

/* renamed from: F1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class F1 implements VG0 {
    public final J1 F;

    public F1(J1 j1) {
        this.F = j1;
    }

    @Override // defpackage.VG0
    public void D(String str) {
        J1 j1 = this.F;
        Iterator it = j1.f8264a.iterator();
        while (it.hasNext()) {
            C4765sb0 sb0 = (C4765sb0) it.next();
            if (sb0.f11283a == 1) {
                UH0 uh0 = sb0.b;
                TH0 th0 = M1.f8455a;
                if (TextUtils.equals(str, ((C3522lG) uh0.g(th0)).f10337a)) {
                    uh0.m(th0, j1.c.W(str));
                    return;
                }
            }
        }
    }
}
