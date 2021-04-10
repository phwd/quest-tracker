package defpackage;

import android.graphics.Point;
import android.os.Handler;
import android.view.View;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Tw  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnLayoutChangeListenerC1211Tw implements View.OnLayoutChangeListener {
    public final /* synthetic */ CompositorViewHolder F;

    public View$OnLayoutChangeListenerC1211Tw(CompositorViewHolder compositorViewHolder) {
        this.F = compositorViewHolder;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        CompositorViewHolder compositorViewHolder = this.F;
        int i9 = CompositorViewHolder.F;
        Tab e = compositorViewHolder.e();
        if (e != null && e.isNativePage() && CompositorViewHolder.y(e.b())) {
            Point o = this.F.o();
            this.F.D(e.l(), e.b(), o.x, o.y);
        }
        this.F.B();
        if (this.F.S != null) {
            new Handler().postDelayed(this.F.S, 30);
            this.F.S = null;
        }
    }
}
