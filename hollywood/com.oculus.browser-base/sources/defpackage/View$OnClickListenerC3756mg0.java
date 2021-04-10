package defpackage;

import android.util.Log;
import android.view.View;
import java.util.Collections;
import java.util.Objects;

/* renamed from: mg0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC3756mg0 implements View.OnClickListener {
    public final /* synthetic */ C3927ng0 F;

    public View$OnClickListenerC3756mg0(C3927ng0 ng0) {
        this.F = ng0;
    }

    public void onClick(View view) {
        C3927ng0 ng0 = this.F;
        C3246jh0 jh0 = ng0.f0.R.I;
        C2392eh0 eh0 = ng0.e0;
        Objects.requireNonNull(jh0);
        C3246jh0.b();
        C1543Zg0 zg0 = C3246jh0.f10229a;
        if (zg0.s instanceof AbstractC0202Dg0) {
            C2222dh0 b = zg0.r.b(eh0);
            if (b != null) {
                C0141Cg0 cg0 = b.f9800a;
                if (cg0 != null && cg0.e) {
                    ((AbstractC0202Dg0) zg0.s).o(Collections.singletonList(eh0.b));
                    this.F.a0.setVisibility(4);
                    this.F.b0.setVisibility(0);
                    return;
                }
            }
            Log.w("MediaRouter", "Ignoring attempt to transfer to a non-transferable route.");
            this.F.a0.setVisibility(4);
            this.F.b0.setVisibility(0);
            return;
        }
        throw new IllegalStateException("There is no currently selected dynamic group route.");
    }
}
