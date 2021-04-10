package defpackage;

import android.widget.CompoundButton;

/* renamed from: F00  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class F00 implements CompoundButton.OnCheckedChangeListener {
    public final /* synthetic */ H00 F;

    public F00(H00 h00) {
        this.F = h00;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        H00 h00 = this.F;
        UH0 uh0 = h00.f8128a;
        QH0 qh0 = I00.b;
        if (z != uh0.h(qh0)) {
            h00.f8128a.j(qh0, z);
            h00.b.d();
            h00.b.e(z);
        }
    }
}
