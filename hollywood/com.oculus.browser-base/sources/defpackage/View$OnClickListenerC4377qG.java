package defpackage;

import J.N;
import android.view.View;
import java.util.Objects;
import org.chromium.chrome.browser.dom_distiller.DistilledPagePrefsView;

/* renamed from: qG  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC4377qG implements View.OnClickListener {
    public final /* synthetic */ int F;
    public final /* synthetic */ DistilledPagePrefsView G;

    public View$OnClickListenerC4377qG(DistilledPagePrefsView distilledPagePrefsView, int i) {
        this.G = distilledPagePrefsView;
        this.F = i;
    }

    public void onClick(View view) {
        C3864nG nGVar = this.G.I;
        int i = this.F;
        Objects.requireNonNull(nGVar);
        AbstractC0934Pg1.a(i);
        N.MJBehZGI(nGVar.f10479a, nGVar, i);
    }
}
