package defpackage;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import org.chromium.content.browser.input.ImeAdapterImpl;

/* renamed from: ch1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2052ch1 implements Runnable {
    public final C5464wh1 F;
    public final SJ0 G;
    public final SJ0 H;

    public RunnableC2052ch1(C5464wh1 wh1, SJ0 sj0, SJ0 sj02) {
        this.F = wh1;
        this.G = sj0;
        this.H = sj02;
    }

    public void run() {
        C5464wh1 wh1 = this.F;
        SJ0 sj0 = this.G;
        SJ0 sj02 = this.H;
        ImeAdapterImpl imeAdapterImpl = wh1.f;
        int i = sj0.f8889a;
        int i2 = sj0.b;
        int i3 = sj02.f8889a;
        int i4 = sj02.b;
        S10 s10 = imeAdapterImpl.G;
        View t0 = imeAdapterImpl.t0();
        InputMethodManager b = s10.b();
        if (b != null) {
            b.updateSelection(t0, i, i2, i3, i4);
        }
    }
}
