package defpackage;

import android.view.View;
import com.oculus.browser.R;

/* renamed from: TW0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class TW0 implements Runnable {
    public final WW0 F;

    public TW0(WW0 ww0) {
        this.F = ww0;
    }

    public void run() {
        WW0 ww0 = this.F;
        C3594lj0 lj0 = ww0.f9152a;
        C3081ij0 ij0 = ww0.f;
        ij0.getClass();
        lj0.f10366a.I.m(AbstractC4619rj0.p, new UW0(ij0));
        C3594lj0 lj02 = ww0.f9152a;
        View view = lj02.b;
        view.announceForAccessibility(((String) lj02.c.g(AbstractC4619rj0.d)) + " " + lj02.b.getResources().getString(R.string.f54940_resource_name_obfuscated_RES_2131952811));
        C3081ij0 ij02 = ww0.f;
        VW0 vw0 = new VW0(ww0);
        ij02.b = vw0;
        ij02.c.postDelayed(vw0, ij02.f10158a);
    }
}
