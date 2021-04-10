package defpackage;

import android.view.View;

/* renamed from: w3  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnLayoutChangeListenerC5359w3 implements View.OnLayoutChangeListener {
    public final /* synthetic */ View$OnClickListenerC5699y3 F;

    public View$OnLayoutChangeListenerC5359w3(View$OnClickListenerC5699y3 y3Var) {
        this.F = y3Var;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (this.F.P.getMeasuredHeight() == this.F.f11658J.getMeasuredHeight() && this.F.f11658J.getBackground() != null) {
            this.F.f11658J.getLayoutParams().height = this.F.f11658J.getPaddingBottom() + this.F.P.getMeasuredHeight();
            view.requestLayout();
            view.removeOnLayoutChangeListener(this);
        }
    }
}
