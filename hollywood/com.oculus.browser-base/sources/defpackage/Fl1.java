package defpackage;

import android.view.View;
import android.view.Window;

/* renamed from: Fl1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Fl1 implements View.OnClickListener {
    public final C3138j2 F;
    public final /* synthetic */ Hl1 G;

    public Fl1(Hl1 hl1) {
        this.G = hl1;
        this.F = new C3138j2(hl1.f8179a.getContext(), 0, 16908332, 0, hl1.i);
    }

    public void onClick(View view) {
        Hl1 hl1 = this.G;
        Window.Callback callback = hl1.l;
        if (callback != null && hl1.m) {
            callback.onMenuItemSelected(0, this.F);
        }
    }
}
