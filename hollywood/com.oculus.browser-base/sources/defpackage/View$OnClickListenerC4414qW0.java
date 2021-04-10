package defpackage;

import android.view.View;

/* renamed from: qW0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnClickListenerC4414qW0 implements View.OnClickListener {
    public final C5264vW0 F;
    public final AbstractC5094uW0 G;

    public View$OnClickListenerC4414qW0(C5264vW0 vw0, AbstractC5094uW0 uw0) {
        this.F = vw0;
        this.G = uw0;
    }

    public void onClick(View view) {
        C5264vW0 vw0 = this.F;
        AbstractC5094uW0 uw0 = this.G;
        vw0.s = true;
        AbstractC3364kK0.c(vw0.n, vw0.a());
        uw0.onDismiss();
    }
}
