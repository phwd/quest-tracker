package org.chromium.components.dom_distiller.core;

import J.N;
import android.widget.RadioButton;
import java.util.Objects;
import org.chromium.chrome.browser.dom_distiller.DistilledPagePrefsView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DistilledPagePrefs$DistilledPagePrefsObserverWrapper {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC3693mG f10836a;
    public final long b = N.MxAdC41V(this);

    public DistilledPagePrefs$DistilledPagePrefsObserverWrapper(AbstractC3693mG mGVar) {
        this.f10836a = mGVar;
    }

    public final void onChangeFontFamily(int i) {
        BR.a(i);
        DistilledPagePrefsView distilledPagePrefsView = (DistilledPagePrefsView) this.f10836a;
        Objects.requireNonNull(distilledPagePrefsView);
        BR.a(i);
        distilledPagePrefsView.L.setSelection(i);
    }

    public final void onChangeFontScaling(float f) {
        ((DistilledPagePrefsView) this.f10836a).b(f);
    }

    public final void onChangeTheme(int i) {
        AbstractC0934Pg1.a(i);
        DistilledPagePrefsView distilledPagePrefsView = (DistilledPagePrefsView) this.f10836a;
        Objects.requireNonNull(distilledPagePrefsView);
        AbstractC0934Pg1.a(i);
        ((RadioButton) distilledPagePrefsView.H.get(Integer.valueOf(i))).setChecked(true);
    }
}
