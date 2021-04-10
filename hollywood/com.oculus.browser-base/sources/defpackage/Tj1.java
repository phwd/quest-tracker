package defpackage;

import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;

/* renamed from: Tj1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Tj1 implements Runnable {
    public final Wj1 F;

    public Tj1(Wj1 wj1) {
        this.F = wj1;
    }

    public void run() {
        Wj1 wj1 = this.F;
        ViewGroup viewGroup = (ViewGroup) wj1.getRootView().findViewById(R.id.control_container);
        AbstractC2417ep1.g(viewGroup, wj1.L, (View) wj1.getParent());
        wj1.L.O = viewGroup;
    }
}
