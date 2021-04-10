package defpackage;

import android.view.View;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: MK  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MK implements View.OnAttachStateChangeListener {
    public final /* synthetic */ NK F;

    public MK(NK nk) {
        this.F = nk;
    }

    public void onViewAttachedToWindow(View view) {
    }

    public void onViewDetachedFromWindow(View view) {
        NK nk = this.F;
        for (TabModel tabModel : ((AbstractC0246Ea1) nk.b).f7969a) {
            tabModel.w(nk.d);
        }
        AbstractC0124Ca1 ca1 = nk.b;
        ((AbstractC0246Ea1) ca1).f.c(nk.e);
    }
}
