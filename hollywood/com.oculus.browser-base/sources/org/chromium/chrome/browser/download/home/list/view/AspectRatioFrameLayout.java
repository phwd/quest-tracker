package org.chromium.chrome.browser.download.home.list.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AspectRatioFrameLayout extends FrameLayout {
    public AspectRatioFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    @Override // android.widget.FrameLayout, android.widget.FrameLayout
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new C5611xa(-1, -1);
    }

    @Override // android.widget.FrameLayout, android.widget.FrameLayout, android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C5611xa(getContext(), attributeSet);
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getLayoutParams() instanceof C5611xa) {
                C5611xa xaVar = (C5611xa) childAt.getLayoutParams();
                if (xaVar.b) {
                    ((FrameLayout.LayoutParams) xaVar).width = xaVar.d;
                }
                if (xaVar.c) {
                    ((FrameLayout.LayoutParams) xaVar).height = xaVar.e;
                }
                xaVar.b = false;
                xaVar.c = false;
            }
        }
    }

    public void onMeasure(int i, int i2) {
        int size = (View.MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight();
        int size2 = (View.MeasureSpec.getSize(i2) - getPaddingTop()) - getPaddingBottom();
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getLayoutParams() instanceof C5611xa) {
                C5611xa xaVar = (C5611xa) childAt.getLayoutParams();
                int i4 = xaVar.f;
                xaVar.d = i4;
                int i5 = xaVar.g;
                xaVar.e = i5;
                float f = xaVar.f11615a;
                if (f > 0.0f) {
                    boolean z = xaVar.b || i4 == 0;
                    boolean z2 = xaVar.c || i5 == 0;
                    if (z) {
                        int i6 = ((FrameLayout.LayoutParams) xaVar).height;
                        if (i6 == -1) {
                            i6 = size2;
                        }
                        ((FrameLayout.LayoutParams) xaVar).width = Math.round(((float) i6) * f);
                        xaVar.b = true;
                    }
                    if (z2) {
                        int i7 = ((FrameLayout.LayoutParams) xaVar).width;
                        if (i7 == -1) {
                            i7 = size;
                        }
                        ((FrameLayout.LayoutParams) xaVar).height = Math.round(((float) i7) / f);
                        xaVar.c = true;
                    }
                }
            }
        }
        super.onMeasure(i, i2);
    }

    @Override // android.widget.FrameLayout, android.widget.FrameLayout
    /* renamed from: generateDefaultLayoutParams  reason: collision with other method in class */
    public FrameLayout.LayoutParams m6generateDefaultLayoutParams() {
        return new C5611xa(-1, -1);
    }

    @Override // android.widget.FrameLayout, android.widget.FrameLayout, android.view.ViewGroup
    /* renamed from: generateLayoutParams  reason: collision with other method in class */
    public FrameLayout.LayoutParams m7generateLayoutParams(AttributeSet attributeSet) {
        return new C5611xa(getContext(), attributeSet);
    }
}
