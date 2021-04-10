package org.chromium.chrome.browser.payments.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PaymentRequestBottomBar extends ViewGroup {
    public ImageView F;
    public ImageView G;
    public View H;
    public View I;

    /* renamed from: J  reason: collision with root package name */
    public View f10746J;

    public PaymentRequestBottomBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        ImageView imageView = (ImageView) findViewById(R.id.logo_name);
        this.F = imageView;
        imageView.setImageResource(R.drawable.f34700_resource_name_obfuscated_RES_2131231510);
        ImageView imageView2 = (ImageView) findViewById(R.id.logo);
        this.G = imageView2;
        imageView2.setImageResource(R.drawable.f29310_resource_name_obfuscated_RES_2131230971);
        this.H = findViewById(R.id.button_primary);
        this.I = findViewById(R.id.button_secondary);
        this.f10746J = findViewById(R.id.space);
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        int paddingLeft = getPaddingLeft() + marginLayoutParams.leftMargin;
        int paddingRight = getPaddingRight() + marginLayoutParams.rightMargin;
        int paddingTop = getPaddingTop() + marginLayoutParams.topMargin;
        int i5 = i3 - i;
        int paddingBottom = ((i4 - i2) - paddingTop) - (getPaddingBottom() + marginLayoutParams.bottomMargin);
        boolean z2 = true;
        if (getLayoutDirection() != 1) {
            z2 = false;
        }
        if (z2) {
            paddingLeft = i5 - paddingRight;
        }
        int i6 = paddingLeft;
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                if (z2) {
                    paddingLeft = i6 - measuredWidth;
                } else {
                    i6 = paddingLeft + measuredWidth;
                }
                int i8 = ((paddingBottom - measuredHeight) / 2) + paddingTop;
                childAt.layout(paddingLeft, i8, i6, measuredHeight + i8);
                paddingLeft = i6;
                i6 = paddingLeft;
            }
        }
    }

    public void onMeasure(int i, int i2) {
        int i3;
        measureChild(this.F, i, i2);
        int measuredWidth = this.F.getMeasuredWidth();
        measureChild(this.G, i, i2);
        int measuredWidth2 = this.G.getMeasuredWidth();
        measureChild(this.H, i, i2);
        int measuredWidth3 = this.H.getMeasuredWidth();
        measureChild(this.I, i, i2);
        int measuredWidth4 = this.I.getMeasuredWidth();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        int paddingRight = getPaddingRight() + getPaddingLeft() + measuredWidth3 + measuredWidth4 + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
        int size = View.MeasureSpec.getSize(i);
        int i4 = 0;
        if (paddingRight + measuredWidth <= size) {
            this.G.setVisibility(8);
            this.F.setVisibility(0);
            i3 = (size - paddingRight) - measuredWidth;
        } else if (paddingRight + measuredWidth2 <= size) {
            this.G.setVisibility(0);
            this.F.setVisibility(8);
            i3 = (size - paddingRight) - measuredWidth2;
        } else {
            this.G.setVisibility(8);
            this.F.setVisibility(8);
            if (size >= paddingRight) {
                i4 = size - paddingRight;
            }
            i3 = i4;
        }
        this.f10746J.getLayoutParams().width = i3;
        measureChild(this.f10746J, View.MeasureSpec.makeMeasureSpec(i3, 1073741824), i2);
        setMeasuredDimension(i, View.MeasureSpec.makeMeasureSpec(getPaddingBottom() + getPaddingTop() + Math.max(this.F.getMeasuredHeight(), Math.max(this.H.getMeasuredHeight(), this.I.getMeasuredHeight())) + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, 1073741824));
    }
}
