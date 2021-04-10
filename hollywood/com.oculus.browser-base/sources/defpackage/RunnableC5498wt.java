package defpackage;

import android.text.TextUtils;

/* renamed from: wt  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC5498wt implements Runnable {
    public final AbstractActivityC2601fu F;

    public RunnableC5498wt(AbstractActivityC2601fu fuVar) {
        this.F = fuVar;
    }

    public void run() {
        AbstractActivityC2601fu fuVar = this.F;
        if (!fuVar.v()) {
            boolean z = false;
            fuVar.x1 = false;
            if (!fuVar.P1()) {
                String b = QX.b();
                if (TextUtils.isEmpty(b) || AbstractC5154ur1.g(b)) {
                    b = "chrome-native://newtab/";
                }
                fuVar.w1(false).f(b, 11);
            }
            int i = fuVar.Y.k;
            if (i == 2 || i == 3) {
                z = true;
            }
            if (z) {
                fuVar.J1();
            }
        }
    }
}
