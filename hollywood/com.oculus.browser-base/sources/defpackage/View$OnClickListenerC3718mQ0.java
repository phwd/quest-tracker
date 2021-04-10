package defpackage;

import android.view.View;

/* renamed from: mQ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnClickListenerC3718mQ0 implements View.OnClickListener {
    public final C3889nQ0 F;

    public View$OnClickListenerC3718mQ0(C3889nQ0 nq0) {
        this.F = nq0;
    }

    public void onClick(View view) {
        for (View.OnClickListener onClickListener : this.F.I) {
            onClickListener.onClick(view);
        }
    }
}
