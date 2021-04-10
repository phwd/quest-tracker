package org.chromium.chrome.browser.keyboard_accessory.bar_component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.tabs.TabLayout;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class KeyboardAccessoryView extends LinearLayout {
    public static final /* synthetic */ int F = 0;
    public RecyclerView G;
    public TabLayout H;
    public ViewPropertyAnimator I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f10689J;
    public boolean K;

    public KeyboardAccessoryView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final /* synthetic */ boolean a() {
        performClick();
        return true;
    }

    public void b() {
    }

    public void c(boolean z) {
        if (!z || getVisibility() != 0) {
            this.G.p0(0);
        }
        if (z) {
            bringToFront();
            ViewPropertyAnimator viewPropertyAnimator = this.I;
            if (viewPropertyAnimator != null) {
                viewPropertyAnimator.cancel();
            }
            if (this.K) {
                this.I = null;
                setVisibility(0);
                return;
            }
            if (getVisibility() != 0) {
                setAlpha(0.0f);
            }
            this.I = animate().alpha(1.0f).setDuration(150).setInterpolator(new AccelerateInterpolator()).withStartAction(new X50(this));
            announceForAccessibility(getContentDescription());
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator2 = this.I;
        if (viewPropertyAnimator2 != null) {
            viewPropertyAnimator2.cancel();
        }
        if (this.f10689J || this.K) {
            this.I = null;
            setVisibility(8);
            return;
        }
        this.I = animate().alpha(0.0f).setInterpolator(new AccelerateInterpolator()).setStartDelay(50).setDuration(100).withEndAction(new Y50(this));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v5, resolved type: androidx.recyclerview.widget.RecyclerView */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [boolean, int] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onFinishInflate() {
        /*
        // Method dump skipped, instructions count: 109
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.keyboard_accessory.bar_component.KeyboardAccessoryView.onFinishInflate():void");
    }
}
