package defpackage;

import android.view.View;
import java.util.Objects;

/* renamed from: N61  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class N61 implements View.OnClickListener {
    public final U61 F;

    public N61(U61 u61) {
        this.F = u61;
    }

    public void onClick(View view) {
        U61 u61 = this.F;
        Objects.requireNonNull(u61);
        if (!AbstractC4772sd1.d()) {
            u61.b.j(AbstractC5033u71.v, false);
        }
        u61.h(true);
        AbstractC3535lK0.a("TabGridDialog.Exit");
    }
}
