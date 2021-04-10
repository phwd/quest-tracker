package org.chromium.chrome.browser.signin.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SigninScrollView extends ScrollView {
    public final ViewTreeObserver.OnGlobalLayoutListener F = new ViewTreeObserver$OnGlobalLayoutListenerC5434wW0(this);
    public final ViewTreeObserver.OnScrollChangedListener G = new ViewTreeObserver$OnScrollChangedListenerC5604xW0(this);
    public Runnable H;

    public SigninScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void a() {
        if (this.H != null) {
            if (getChildCount() == 0) {
                this.H.run();
                return;
            }
            if (getScrollY() + getHeight() >= getChildAt(getChildCount() - 1).getBottom()) {
                this.H.run();
            }
        }
    }

    public final void b() {
        if (this.H != null) {
            this.H = null;
            getViewTreeObserver().removeOnGlobalLayoutListener(this.F);
            getViewTreeObserver().removeOnScrollChangedListener(this.G);
        }
    }

    public void c(Runnable runnable) {
        b();
        if (runnable != null) {
            this.H = runnable;
            getViewTreeObserver().addOnGlobalLayoutListener(this.F);
            getViewTreeObserver().addOnScrollChangedListener(this.G);
        }
    }

    public float getTopFadingEdgeStrength() {
        return 0.0f;
    }

    public void onDetachedFromWindow() {
        b();
        super.onDetachedFromWindow();
    }
}
