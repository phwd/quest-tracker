package org.chromium.chrome.browser.toolbar.top;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.oculus.browser.R;
import org.chromium.chrome.browser.toolbar.NewTabButton;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class StartSurfaceToolbarView extends RelativeLayout {
    public NewTabButton F;
    public View G;
    public View H;
    public ImageButton I;

    /* renamed from: J  reason: collision with root package name */
    public ColorStateList f10788J;
    public ViewPropertyAnimator K;
    public Rect L = new Rect();
    public Rect M = new Rect();
    public boolean N;
    public boolean O;
    public boolean P;

    public StartSurfaceToolbarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void a(boolean z) {
        setAlpha(1.0f);
        setVisibility(z ? 0 : 8);
        this.K = null;
    }

    public void b() {
        this.F.d();
    }

    public void c() {
        ((RelativeLayout.LayoutParams) this.I.getLayoutParams()).removeRule(16);
    }

    public final void d(boolean z, boolean z2) {
        if (z != this.P) {
            ViewPropertyAnimator viewPropertyAnimator = this.K;
            if (viewPropertyAnimator != null) {
                viewPropertyAnimator.cancel();
                a(z);
            }
            this.P = z;
            if (C5052uE.a()) {
                a(z);
                return;
            }
            int i = 0;
            if (!z2) {
                if (!z) {
                    i = 8;
                }
                setVisibility(i);
                return;
            }
            setVisibility(0);
            float f = 0.0f;
            setAlpha(z ? 0.0f : 1.0f);
            ViewPropertyAnimator animate = animate();
            if (z) {
                f = 1.0f;
            }
            this.K = animate.alpha(f).setDuration(200).setInterpolator(G30.d).withEndAction(new RunnableC1591a11(this, z));
        }
    }

    public final void e(boolean z) {
        setBackgroundColor(AbstractC2934hr.b(getResources(), z));
        if (this.f10788J == null) {
            Context context = getContext();
            ThreadLocal threadLocal = AbstractC5544x8.f11592a;
            this.f10788J = context.getColorStateList(R.color.f11330_resource_name_obfuscated_RES_2131099823);
            getContext().getColorStateList(R.color.f11390_resource_name_obfuscated_RES_2131099829);
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.F = (NewTabButton) findViewById(R.id.new_tab_button);
        this.G = findViewById(R.id.incognito_switch);
        this.H = findViewById(R.id.logo);
        this.I = (ImageButton) findViewById(R.id.identity_disc_button);
        e(false);
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.H.getVisibility() != 8) {
            this.L.set(this.H.getLeft(), this.H.getTop(), this.H.getRight(), this.H.getBottom());
            for (int i5 = 0; i5 < getChildCount(); i5++) {
                View childAt = getChildAt(i5);
                if (!(childAt == this.H || childAt.getVisibility() == 8)) {
                    this.M.set(childAt.getLeft(), childAt.getTop(), childAt.getRight(), childAt.getBottom());
                    if (Rect.intersects(this.L, this.M)) {
                        this.H.setVisibility(8);
                        return;
                    }
                }
            }
        }
    }
}
