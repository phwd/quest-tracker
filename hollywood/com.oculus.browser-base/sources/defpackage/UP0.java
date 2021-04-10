package defpackage;

import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.view.View;
import org.chromium.chrome.browser.toolbar.bottom.ScrollingBottomViewResourceFrameLayout;

/* renamed from: UP0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UP0 extends View$OnLayoutChangeListenerC2948hv1 {
    public final /* synthetic */ ScrollingBottomViewResourceFrameLayout L;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UP0(ScrollingBottomViewResourceFrameLayout scrollingBottomViewResourceFrameLayout, View view) {
        super(view);
        this.L = scrollingBottomViewResourceFrameLayout;
    }

    @Override // defpackage.View$OnLayoutChangeListenerC2948hv1
    public void h(Canvas canvas, Rect rect) {
        this.L.I.set(rect);
        ScrollingBottomViewResourceFrameLayout scrollingBottomViewResourceFrameLayout = this.L;
        if (scrollingBottomViewResourceFrameLayout.I.intersect(0, 0, scrollingBottomViewResourceFrameLayout.getWidth(), this.L.f10786J)) {
            canvas.save();
            canvas.clipRect(this.L.I);
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            canvas.restore();
        }
    }
}
