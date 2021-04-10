package defpackage;

import android.view.View;

/* renamed from: OT  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OT implements View.OnLayoutChangeListener {
    public final /* synthetic */ View F;
    public final /* synthetic */ ST G;

    public OT(ST st, View view) {
        this.G = st;
        this.F = view;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (i4 - i2 < i8 - i6) {
            C1786b61.j(this.G.U, 1, true);
            this.F.removeOnLayoutChangeListener(this);
        }
    }
}
