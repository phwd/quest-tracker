package defpackage;

import android.view.View;

/* renamed from: Dg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnLongClickListenerC0201Dg implements View.OnLongClickListener {
    public final Runnable F;

    public View$OnLongClickListenerC0201Dg(Runnable runnable) {
        this.F = runnable;
    }

    public boolean onLongClick(View view) {
        this.F.run();
        return true;
    }
}
