package defpackage;

import android.view.View;

/* renamed from: hg0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC2902hg0 implements View.OnClickListener {
    public final /* synthetic */ AbstractC3072ig0 F;

    public View$OnClickListenerC2902hg0(AbstractC3072ig0 ig0) {
        this.F = ig0;
    }

    public void onClick(View view) {
        DialogC5460wg0 wg0 = this.F.c0;
        if (wg0.Z != null) {
            wg0.U.removeMessages(2);
        }
        AbstractC3072ig0 ig0 = this.F;
        ig0.c0.Z = ig0.Z;
        int i = 1;
        boolean z = !view.isActivated();
        if (z) {
            i = 0;
        } else {
            AbstractC3072ig0 ig02 = this.F;
            Integer num = (Integer) ig02.c0.a0.get(ig02.Z.c);
            if (num != null) {
                i = Math.max(1, num.intValue());
            }
        }
        this.F.y(z);
        this.F.b0.setProgress(i);
        this.F.Z.k(i);
        this.F.c0.U.sendEmptyMessageDelayed(2, 500);
    }
}
