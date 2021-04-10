package defpackage;

import android.view.View;
import android.widget.PopupWindow;

/* renamed from: zM  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5923zM implements PopupWindow.OnDismissListener {
    public final View F;
    public final Tm1 G;

    public C5923zM(View view, Tm1 tm1) {
        this.F = view;
        this.G = tm1;
    }

    public void onDismiss() {
        View view = this.F;
        Tm1 tm1 = this.G;
        AbstractC3628lu1.b(view);
        tm1.dismissed("IPH_ExploreSitesTile");
    }
}
