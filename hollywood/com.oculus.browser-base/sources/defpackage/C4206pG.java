package defpackage;

import J.N;
import android.view.View;
import android.widget.AdapterView;
import java.util.Objects;
import org.chromium.chrome.browser.dom_distiller.DistilledPagePrefsView;

/* renamed from: pG  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4206pG implements AdapterView.OnItemSelectedListener {
    public final /* synthetic */ DistilledPagePrefsView F;

    public C4206pG(DistilledPagePrefsView distilledPagePrefsView) {
        this.F = distilledPagePrefsView;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
        if (i >= 0 && i <= 2) {
            C3864nG nGVar = this.F.I;
            Objects.requireNonNull(nGVar);
            BR.a(i);
            N.MfkxLC88(nGVar.f10479a, nGVar, i);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView adapterView) {
    }
}
