package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchPreferenceFragment;

/* renamed from: lA  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC3504lA implements Runnable {
    public final /* synthetic */ C3675mA F;

    public RunnableC3504lA(C3675mA mAVar) {
        this.F = mAVar;
    }

    public void run() {
        Context context = this.F.H;
        String name = ContextualSearchPreferenceFragment.class.getName();
        Intent l = AbstractC2531fV.l(context, XS0.class);
        if (!(context instanceof Activity)) {
            l.addFlags(268435456);
            l.addFlags(67108864);
        }
        if (name != null) {
            l.putExtra("show_fragment", name);
        }
        U20.q(context, l);
    }
}
