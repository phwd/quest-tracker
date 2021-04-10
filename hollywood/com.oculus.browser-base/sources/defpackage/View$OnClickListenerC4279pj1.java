package defpackage;

import android.view.View;
import androidx.appcompat.widget.Toolbar;

/* renamed from: pj1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC4279pj1 implements View.OnClickListener {
    public final /* synthetic */ Toolbar F;

    public View$OnClickListenerC4279pj1(Toolbar toolbar) {
        this.F = toolbar;
    }

    public void onClick(View view) {
        C0817Ni0 ni0;
        C4450qj1 qj1 = this.F.s0;
        if (qj1 == null) {
            ni0 = null;
        } else {
            ni0 = qj1.G;
        }
        if (ni0 != null) {
            ni0.collapseActionView();
        }
    }
}
