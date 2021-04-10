package defpackage;

import android.view.MenuItem;
import android.view.View;

/* renamed from: op  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnClickListenerC4124op implements View.OnClickListener {
    public final AbstractC4867t9 F;
    public final MenuItem G;

    public View$OnClickListenerC4124op(AbstractC4867t9 t9Var, MenuItem menuItem) {
        this.F = t9Var;
        this.G = menuItem;
    }

    public void onClick(View view) {
        ((View$OnKeyListenerC2476f9) this.F).c(this.G);
    }
}
