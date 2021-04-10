package defpackage;

import android.view.View;

/* renamed from: CJ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CJ implements View.OnLayoutChangeListener {
    public final /* synthetic */ EJ F;

    public CJ(EJ ej) {
        this.F = ej;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        EJ ej = this.F;
        if (view == ej.G) {
            ej.b();
        }
    }
}
