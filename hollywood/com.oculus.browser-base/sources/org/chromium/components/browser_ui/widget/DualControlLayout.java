package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.oculus.browser.R;
import org.chromium.ui.widget.ButtonCompat;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class DualControlLayout extends ViewGroup {
    public final int F = getContext().getResources().getDimensionPixelSize(R.dimen.f18930_resource_name_obfuscated_RES_2131165512);
    public int G = 0;
    public int H;
    public boolean I;

    /* renamed from: J  reason: collision with root package name */
    public View f10817J;
    public View K;

    public DualControlLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, FJ0.f8009J, 0, 0);
            if (obtainStyledAttributes.hasValue(5)) {
                this.H = obtainStyledAttributes.getDimensionPixelSize(5, 0);
            }
            String string = obtainStyledAttributes.hasValue(3) ? obtainStyledAttributes.getString(3) : null;
            if (!TextUtils.isEmpty(string)) {
                addView(a(getContext(), true, string, null));
            }
            String string2 = obtainStyledAttributes.hasValue(4) ? obtainStyledAttributes.getString(4) : null;
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                addView(a(getContext(), false, string2, null));
            }
            if (obtainStyledAttributes.hasValue(0)) {
                this.G = obtainStyledAttributes.getInt(0, 0);
            }
            obtainStyledAttributes.recycle();
        }
    }

    public static Button a(Context context, boolean z, String str, View.OnClickListener onClickListener) {
        if (z) {
            ButtonCompat buttonCompat = new ButtonCompat(context, null, R.style.f68620_resource_name_obfuscated_RES_2132017435);
            buttonCompat.setId(R.id.button_primary);
            buttonCompat.setOnClickListener(onClickListener);
            buttonCompat.setText(str);
            return buttonCompat;
        }
        ButtonCompat buttonCompat2 = new ButtonCompat(context, null, R.style.f72390_resource_name_obfuscated_RES_2132017812);
        buttonCompat2.setId(R.id.button_secondary);
        buttonCompat2.setOnClickListener(onClickListener);
        buttonCompat2.setText(str);
        buttonCompat2.setTextAppearance(buttonCompat2.getContext(), R.style.f70900_resource_name_obfuscated_RES_2132017663);
        return buttonCompat2;
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int i8 = i3 - i;
        boolean z2 = false;
        boolean z3 = getLayoutDirection() == 1;
        if ((z3 && this.G == 0) || (!z3 && ((i7 = this.G) == 2 || i7 == 1))) {
            z2 = true;
        }
        if (z2) {
            i5 = i8 - paddingRight;
        } else {
            i5 = this.f10817J.getMeasuredWidth() + paddingLeft;
        }
        int measuredWidth = i5 - this.f10817J.getMeasuredWidth();
        int paddingTop = getPaddingTop();
        int measuredHeight = this.f10817J.getMeasuredHeight() + paddingTop;
        this.f10817J.layout(measuredWidth, paddingTop, i5, measuredHeight);
        if (this.I) {
            int i9 = measuredHeight + this.H;
            View view = this.K;
            view.layout(paddingLeft, i9, view.getMeasuredWidth() + paddingLeft, this.K.getMeasuredHeight() + i9);
            return;
        }
        View view2 = this.K;
        if (view2 != null) {
            int measuredHeight2 = view2.getMeasuredHeight();
            int i10 = ((paddingTop + measuredHeight) / 2) - (measuredHeight2 / 2);
            int i11 = measuredHeight2 + i10;
            if (this.G == 2) {
                if (!z2) {
                    paddingLeft = (i8 - paddingRight) - this.K.getMeasuredWidth();
                }
                i6 = this.K.getMeasuredWidth() + paddingLeft;
            } else if (z2) {
                if (this.f10817J.getMeasuredWidth() > 0) {
                    measuredWidth -= this.F;
                }
                paddingLeft = measuredWidth - this.K.getMeasuredWidth();
                i6 = measuredWidth;
            } else {
                if (this.f10817J.getMeasuredWidth() > 0) {
                    i5 += this.F;
                }
                i6 = this.K.getMeasuredWidth() + i5;
                paddingLeft = i5;
            }
            this.K.layout(paddingLeft, i10, i6, i11);
        }
    }

    public void onMeasure(int i, int i2) {
        int i3;
        this.I = false;
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        if (View.MeasureSpec.getMode(i) == 0) {
            i3 = Integer.MAX_VALUE;
        } else {
            i3 = View.MeasureSpec.getSize(i) - paddingRight;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        measureChild(this.f10817J, makeMeasureSpec, makeMeasureSpec);
        int measuredWidth = this.f10817J.getMeasuredWidth();
        int measuredHeight = this.f10817J.getMeasuredHeight();
        View view = this.K;
        if (view != null) {
            measureChild(view, makeMeasureSpec, makeMeasureSpec);
            int measuredWidth2 = this.K.getMeasuredWidth() + this.f10817J.getMeasuredWidth();
            if (this.f10817J.getMeasuredWidth() > 0 && this.K.getMeasuredWidth() > 0) {
                measuredWidth2 += this.F;
            }
            if (measuredWidth2 > i3) {
                this.I = true;
                int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i3, 1073741824);
                this.f10817J.measure(makeMeasureSpec2, makeMeasureSpec);
                this.K.measure(makeMeasureSpec2, makeMeasureSpec);
                measuredHeight = this.K.getMeasuredHeight() + this.f10817J.getMeasuredHeight() + this.H;
            } else {
                measuredHeight = Math.max(measuredHeight, this.K.getMeasuredHeight());
                i3 = measuredWidth2;
            }
        } else {
            i3 = measuredWidth;
        }
        setMeasuredDimension(ViewGroup.resolveSize(i3 + paddingRight, i), ViewGroup.resolveSize(measuredHeight + paddingBottom, i2));
    }

    public void onViewAdded(View view) {
        super.onViewAdded(view);
        if (this.f10817J == null) {
            this.f10817J = view;
        } else if (this.K == null) {
            this.K = view;
        } else {
            throw new IllegalStateException("Too many children added to DualControlLayout");
        }
    }
}
