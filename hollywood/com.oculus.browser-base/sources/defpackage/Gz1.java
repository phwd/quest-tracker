package defpackage;

import android.view.ViewGroup;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.Calendar;
import java.util.Locale;

/* renamed from: Gz1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Gz1 extends AbstractC5750yK0 {
    public final C1104Sc0 I;

    public Gz1(C1104Sc0 sc0) {
        this.I = sc0;
    }

    @Override // defpackage.AbstractC5750yK0
    public int b() {
        return this.I.B0.f9691J;
    }

    @Override // defpackage.AbstractC5750yK0
    public void j(XK0 xk0, int i) {
        Fz1 fz1 = (Fz1) xk0;
        int i2 = this.I.B0.F.I + i;
        String string = fz1.Z.getContext().getString(R.string.f55560_resource_name_obfuscated_RES_2131952873);
        fz1.Z.setText(String.format(Locale.getDefault(), "%d", Integer.valueOf(i2)));
        fz1.Z.setContentDescription(String.format(string, Integer.valueOf(i2)));
        C0702Ll ll = this.I.E0;
        Calendar d = AbstractC2255ds1.d();
        C0641Kl kl = d.get(1) == i2 ? ll.f : ll.d;
        for (Long l : this.I.A0.M()) {
            d.setTimeInMillis(l.longValue());
            if (d.get(1) == i2) {
                kl = ll.e;
            }
        }
        kl.b(fz1.Z);
        fz1.Z.setOnClickListener(new Ez1(this, i2));
    }

    @Override // defpackage.AbstractC5750yK0
    public XK0 m(ViewGroup viewGroup, int i) {
        return new Fz1((TextView) AbstractC2531fV.r(viewGroup, R.layout.f39640_resource_name_obfuscated_RES_2131624273, viewGroup, false));
    }

    public int s(int i) {
        return i - this.I.B0.F.I;
    }
}
