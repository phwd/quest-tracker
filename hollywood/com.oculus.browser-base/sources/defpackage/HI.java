package defpackage;

import android.view.View;
import java.util.Objects;

/* renamed from: HI  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class HI implements View.OnClickListener {
    public final KI F;

    public HI(KI ki) {
        this.F = ki;
    }

    public void onClick(View view) {
        KI ki = this.F;
        ki.I = true;
        JI ji = ki.H;
        int i = ki.F;
        int i2 = ki.G;
        C2329eH eHVar = (C2329eH) ji;
        eHVar.e.set(11, i);
        eHVar.e.set(12, i2);
        ZG zg = eHVar.d;
        long timeInMillis = eHVar.e.getTimeInMillis();
        NH nh = (NH) zg;
        Objects.requireNonNull(nh);
        RH.a(4);
        nh.b(timeInMillis);
        eHVar.e.clear();
        ki.dismiss();
    }
}
