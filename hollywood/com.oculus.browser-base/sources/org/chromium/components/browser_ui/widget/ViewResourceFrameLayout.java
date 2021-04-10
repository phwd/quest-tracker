package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import org.chromium.ui.widget.OptimizedFrameLayout;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ViewResourceFrameLayout extends OptimizedFrameLayout {
    public View$OnLayoutChangeListenerC2948hv1 G;
    public Rect H;

    public ViewResourceFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public View$OnLayoutChangeListenerC2948hv1 d() {
        return new View$OnLayoutChangeListenerC2948hv1(this);
    }

    public boolean e() {
        return true;
    }

    public ViewParent invalidateChildInParent(int[] iArr, Rect rect) {
        ViewParent invalidateChildInParent = super.invalidateChildInParent(iArr, rect);
        if (e()) {
            this.G.f(rect);
        }
        return invalidateChildInParent;
    }

    public void onDescendantInvalidated(View view, View view2) {
        super.onDescendantInvalidated(view, view2);
        if (e()) {
            if (this.H == null) {
                this.H = new Rect();
            }
            int floor = (int) Math.floor((double) view.getX());
            int floor2 = (int) Math.floor((double) view.getY());
            this.H.set(floor, floor2, view.getWidth() + floor, view.getHeight() + floor2);
            this.G.f(this.H);
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.G = d();
    }
}
