package defpackage;

import android.view.View;
import android.widget.FrameLayout;

/* renamed from: PS  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PS implements View.OnLayoutChangeListener {
    public final /* synthetic */ FrameLayout F;
    public final /* synthetic */ DT G;
    public final /* synthetic */ AbstractC1664aT H;

    public PS(AbstractC1664aT aTVar, FrameLayout frameLayout, DT dt) {
        this.H = aTVar;
        this.F = frameLayout;
        this.G = dt;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (this.F.getParent() != null) {
            this.F.removeOnLayoutChangeListener(this);
            this.H.x(this.G);
        }
    }
}
