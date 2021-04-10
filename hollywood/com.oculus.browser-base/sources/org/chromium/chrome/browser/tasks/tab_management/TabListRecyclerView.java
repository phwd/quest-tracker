package org.chromium.chrome.browser.tasks.tab_management;

import J.N;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TabListRecyclerView extends RecyclerView implements E91 {
    public static final /* synthetic */ int k1 = 0;
    public final int l1 = toString().hashCode();
    public ValueAnimator m1;
    public ValueAnimator n1;
    public P91 o1;
    public IJ p1;
    public View$OnLayoutChangeListenerC2948hv1 q1;
    public boolean r1;
    public long s1;
    public ImageView t1;
    public int u1;
    public O91 v1;
    public EW0 w1;

    public TabListRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static float C0(TabListRecyclerView tabListRecyclerView) {
        Objects.requireNonNull(tabListRecyclerView);
        try {
            return Float.valueOf(N.MMltG$kc("TabGridLayoutAndroid", "max-duty-cycle")).floatValue();
        } catch (NumberFormatException unused) {
            return 0.2f;
        }
    }

    public final void D0() {
        IJ ij;
        if (!this.r1 && (ij = this.p1) != null) {
            ij.c.put(this.l1, this.q1);
            this.r1 = true;
        }
    }

    public void E0(boolean z) {
        if (this.t1 == null) {
            Context context = getContext();
            ImageView imageView = new ImageView(context);
            this.t1 = imageView;
            imageView.setImageDrawable(AbstractC5544x8.a(context, R.drawable.f33760_resource_name_obfuscated_RES_2131231416));
            this.t1.setScaleType(ImageView.ScaleType.FIT_XY);
            Resources resources = context.getResources();
            if (getParent() instanceof FrameLayout) {
                this.t1.setLayoutParams(new FrameLayout.LayoutParams(-1, resources.getDimensionPixelSize(R.dimen.f26400_resource_name_obfuscated_RES_2131166259), 48));
                this.t1.setTranslationY((float) this.u1);
                ((FrameLayout) getParent()).addView(this.t1);
            } else if (getParent() instanceof RelativeLayout) {
                RelativeLayout relativeLayout = (RelativeLayout) getParent();
                View childAt = relativeLayout.getChildAt(0);
                if (childAt instanceof TabGroupUiToolbarView) {
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, resources.getDimensionPixelSize(R.dimen.f26400_resource_name_obfuscated_RES_2131166259));
                    layoutParams.addRule(3, childAt.getId());
                    relativeLayout.addView(this.t1, layoutParams);
                } else {
                    return;
                }
            }
        }
        if (z && this.t1.getVisibility() != 0) {
            this.t1.setVisibility(0);
        } else if (!z && this.t1.getVisibility() != 8) {
            this.t1.setVisibility(8);
        }
    }

    public final void F0() {
        IJ ij;
        if (this.r1 && (ij = this.p1) != null) {
            ij.d(this.l1);
            this.r1 = false;
        }
    }

    public ViewParent invalidateChildInParent(int[] iArr, Rect rect) {
        ViewParent invalidateChildInParent = super.invalidateChildInParent(iArr, rect);
        View$OnLayoutChangeListenerC2948hv1 hv1 = this.q1;
        if (hv1 != null) {
            hv1.f(rect);
        }
        return invalidateChildInParent;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        O91 o91 = new O91(this, null);
        this.v1 = o91;
        i(o91);
    }

    public void onDescendantInvalidated(View view, View view2) {
        super.onDescendantInvalidated(view, view2);
        View$OnLayoutChangeListenerC2948hv1 hv1 = this.q1;
        if (hv1 != null) {
            hv1.f(null);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ImageView imageView = this.t1;
        if (imageView != null) {
            removeViewInLayout(imageView);
            this.t1 = null;
        }
        O91 o91 = this.v1;
        if (o91 != null) {
            k0(o91);
            this.v1 = null;
        }
    }
}
