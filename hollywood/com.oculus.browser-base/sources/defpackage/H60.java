package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;

/* renamed from: H60  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class H60 extends UI {
    public H60(Context context) {
        super(context);
        this.P = new E60(this);
    }

    @Override // defpackage.AbstractC5750yK0
    public void j(XK0 xk0, int i) {
        G60 g60 = (G60) xk0;
        B60 b60 = (B60) this.L.get(i);
        g60.Z.setText(b60.b);
        if (TextUtils.equals(b60.b, b60.c)) {
            g60.a0.setVisibility(8);
        } else {
            g60.a0.setVisibility(0);
            g60.a0.setText(b60.c);
        }
        g60.c0.f(b60.b);
        g60.b0.setVisibility(8);
        g60.c0.setVisibility(8);
    }

    @Override // defpackage.AbstractC5750yK0
    public XK0 m(ViewGroup viewGroup, int i) {
        return new G60(AbstractC2531fV.r(viewGroup, R.layout.f36550_resource_name_obfuscated_RES_2131623964, viewGroup, false));
    }

    public void t(List list) {
        this.L = new ArrayList(list);
        this.F.b();
    }
}
