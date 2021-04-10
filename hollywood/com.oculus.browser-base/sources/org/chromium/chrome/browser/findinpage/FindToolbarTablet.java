package org.chromium.chrome.browser.findinpage;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FindToolbarTablet extends BQ {
    public ObjectAnimator g0;
    public ObjectAnimator h0;
    public ObjectAnimator i0;
    public final int j0;

    public FindToolbarTablet(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j0 = (int) (context.getResources().getDisplayMetrics().density * 8.0f);
    }

    @Override // defpackage.BQ
    public void c() {
        super.c();
        r(false);
    }

    @Override // defpackage.BQ
    public void e(Rect rect) {
        float f = getContext().getResources().getDisplayMetrics().density;
        boolean z = false;
        if (rect != null && rect.intersects((int) (((float) getLeft()) / f), 0, (int) (((float) getRight()) / f), (int) (((float) getHeight()) / f))) {
            z = true;
        }
        r(z);
    }

    @Override // defpackage.BQ
    public void g() {
        if (this.g0 != this.h0) {
            s(true);
        }
    }

    @Override // defpackage.BQ
    public void h(boolean z) {
        if (this.g0 != this.i0) {
            s(false);
        }
        super.h(z);
    }

    @Override // defpackage.BQ
    public void onFinishInflate() {
        super.onFinishInflate();
        setVisibility(8);
        Resources resources = getContext().getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.f19450_resource_name_obfuscated_RES_2131165564) + resources.getDimensionPixelSize(R.dimen.f19460_resource_name_obfuscated_RES_2131165565);
        float f = (float) dimensionPixelOffset;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, View.TRANSLATION_X, f, 0.0f);
        this.h0 = ofFloat;
        ofFloat.setDuration(200L);
        ObjectAnimator objectAnimator = this.h0;
        DecelerateInterpolator decelerateInterpolator = G30.f8058a;
        objectAnimator.setInterpolator(decelerateInterpolator);
        this.h0.addListener(new FQ(this));
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, View.TRANSLATION_X, 0.0f, f);
        this.i0 = ofFloat2;
        ofFloat2.setDuration(200L);
        this.i0.setInterpolator(decelerateInterpolator);
        this.i0.addListener(new GQ(this));
    }

    public final void r(boolean z) {
        float f = z ? (float) (-(getHeight() - this.j0)) : 0.0f;
        if (f != getTranslationY()) {
            ObjectAnimator objectAnimator = this.g0;
            if (objectAnimator != null) {
                if (objectAnimator == this.h0 || objectAnimator == this.i0) {
                    objectAnimator.end();
                } else {
                    objectAnimator.cancel();
                }
            }
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, f);
            this.g0 = ofFloat;
            ofFloat.setDuration(200L);
            this.g0.setInterpolator(G30.f8058a);
            this.g0.addListener(new HQ(this));
            this.R.I0(this.g0);
        }
    }

    public final void s(boolean z) {
        ObjectAnimator objectAnimator;
        ObjectAnimator objectAnimator2;
        if (z && getVisibility() != 0 && this.g0 != this.h0) {
            View findViewById = getRootView().findViewById(R.id.toolbar);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
            layoutParams.topMargin = findViewById.getBottom() - this.j0;
            setLayoutParams(layoutParams);
            objectAnimator = this.h0;
        } else if (z || getVisibility() == 8 || this.g0 == (objectAnimator2 = this.i0)) {
            objectAnimator = null;
        } else {
            m(false);
            objectAnimator = objectAnimator2;
        }
        if (objectAnimator != null) {
            ObjectAnimator objectAnimator3 = this.g0;
            if (objectAnimator3 != null) {
                objectAnimator3.cancel();
            }
            this.g0 = objectAnimator;
            this.R.I0(objectAnimator);
            postInvalidateOnAnimation();
        }
    }
}
