package defpackage;

import android.util.Pair;
import android.view.View;

/* renamed from: lQ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnClickListenerC3547lQ0 implements View.OnClickListener {
    public final C3889nQ0 F;
    public final View.OnClickListener G;

    public View$OnClickListenerC3547lQ0(C3889nQ0 nq0, View.OnClickListener onClickListener) {
        this.F = nq0;
        this.G = onClickListener;
    }

    public void onClick(View view) {
        C3889nQ0 nq0 = this.F;
        View.OnClickListener onClickListener = this.G;
        if (!nq0.G.h(AbstractC4060oQ0.n)) {
            onClickListener.onClick(view);
        } else {
            nq0.G.m(AbstractC4060oQ0.h, Pair.create((String) nq0.G.g(AbstractC4060oQ0.m), Boolean.TRUE));
            throw null;
        }
    }
}
