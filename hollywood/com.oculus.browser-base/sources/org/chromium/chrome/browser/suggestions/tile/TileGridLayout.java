package org.chromium.chrome.browser.suggestions.tile;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TileGridLayout extends FrameLayout {
    public final int F;
    public final int G;
    public final int H = Integer.MAX_VALUE;
    public final int I = Integer.MAX_VALUE;

    public TileGridLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Resources resources = getResources();
        this.F = resources.getDimensionPixelOffset(R.dimen.f26090_resource_name_obfuscated_RES_2131166228);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.O0);
        this.G = obtainStyledAttributes.getDimensionPixelOffset(0, resources.getDimensionPixelOffset(R.dimen.f26060_resource_name_obfuscated_RES_2131166225));
        obtainStyledAttributes.recycle();
    }

    public Pair a(boolean z, int i, int i2) {
        float f;
        int i3 = 0;
        if (z) {
            f = ((float) i) / ((float) (i2 + 1));
            int round = Math.round(f);
            if (f < ((float) this.G)) {
                return a(false, i, i2);
            }
            i3 = round;
        } else {
            int i4 = this.H;
            int i5 = i2 - 1;
            long j = ((long) i) - (((long) i4) * ((long) i5));
            if (j > 0) {
                i3 = (int) (j / 2);
                f = (float) i4;
            } else {
                f = ((float) i) / ((float) Math.max(1, i5));
            }
        }
        return Pair.create(Integer.valueOf(i3), Integer.valueOf(Math.round(f)));
    }

    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int min = Math.min(View.MeasureSpec.getSize(i), this.I);
        int childCount = getChildCount();
        int i7 = 0;
        if (childCount == 0) {
            setMeasuredDimension(min, FrameLayout.resolveSize(0, i2));
            return;
        }
        for (int i8 = 0; i8 < childCount; i8++) {
            measureChild(getChildAt(i8), 0, 0);
        }
        int measuredHeight = getChildAt(0).getMeasuredHeight();
        int measuredWidth = getChildAt(0).getMeasuredWidth();
        int i9 = this.G;
        int c = AbstractC4089od0.c((min + i9) / (i9 + measuredWidth), 1, 0);
        Pair a2 = a(true, Math.max(0, min - (c * measuredWidth)), c);
        int intValue = ((Integer) a2.first).intValue();
        int intValue2 = ((Integer) a2.second).intValue();
        int min2 = Math.min(childCount, 0 * c);
        int i10 = ((min2 + c) - 1) / c;
        int paddingTop = getPaddingTop();
        boolean z = getLayoutDirection() == 1;
        int i11 = 0;
        while (i11 < min2) {
            View childAt = getChildAt(i11);
            childAt.setVisibility(i7);
            int i12 = (this.F + measuredHeight) * (i11 / c);
            int i13 = ((measuredWidth + intValue2) * (i11 % c)) + intValue;
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
            int i14 = z ? 0 : i13;
            if (z) {
                i3 = intValue;
                i5 = 0;
                i4 = intValue2;
                i6 = i13;
            } else {
                i4 = intValue2;
                i3 = intValue;
                i6 = 0;
                i5 = 0;
            }
            marginLayoutParams.setMargins(i14, i12, i6, i5);
            childAt.setLayoutParams(marginLayoutParams);
            i11++;
            i7 = i5;
            intValue2 = i4;
            c = c;
            intValue = i3;
            measuredWidth = measuredWidth;
        }
        while (min2 < childCount) {
            getChildAt(min2).setVisibility(8);
            min2++;
        }
        setMeasuredDimension(min, FrameLayout.resolveSize(((i10 - 1) * this.F) + (measuredHeight * i10) + getPaddingBottom() + paddingTop, i2));
    }
}
