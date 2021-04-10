package org.chromium.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ChipView extends LinearLayout {
    public final TextView F;
    public final ChromeImageView G;
    public final boolean H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public final int f11028J;
    public final int K;
    public final int L;
    public final int M;
    public final int N;
    public ViewGroup O;
    public TextView P;

    public ChipView(Context context, AttributeSet attributeSet) {
        super(new ContextThemeWrapper(context, (int) R.style.f70120_resource_name_obfuscated_RES_2132017585), attributeSet, R.attr.f2850_resource_name_obfuscated_RES_2130968731);
        int i;
        int i2;
        int i3;
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, FJ0.B, R.attr.f2850_resource_name_obfuscated_RES_2130968731, 0);
        boolean z = obtainStyledAttributes.getBoolean(6, false);
        if (z) {
            i = getResources().getDimensionPixelSize(R.dimen.f17190_resource_name_obfuscated_RES_2131165338);
        } else {
            i = getResources().getDimensionPixelSize(R.dimen.f17200_resource_name_obfuscated_RES_2131165339);
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.f17230_resource_name_obfuscated_RES_2131165342);
        if (z) {
            i2 = getResources().getDimensionPixelSize(R.dimen.f17210_resource_name_obfuscated_RES_2131165340);
        } else {
            i2 = getResources().getDimensionPixelSize(R.dimen.f17220_resource_name_obfuscated_RES_2131165341);
        }
        this.L = i2;
        if (z) {
            i3 = getResources().getDimensionPixelSize(R.dimen.f17250_resource_name_obfuscated_RES_2131165344);
        } else {
            i3 = getResources().getDimensionPixelSize(R.dimen.f17240_resource_name_obfuscated_RES_2131165343);
        }
        this.M = i3;
        int i4 = obtainStyledAttributes.getBoolean(12, false) ? R.dimen.f17290_resource_name_obfuscated_RES_2131165348 : R.dimen.f17160_resource_name_obfuscated_RES_2131165335;
        int resourceId = obtainStyledAttributes.getResourceId(1, R.color.f10250_resource_name_obfuscated_RES_2131099715);
        int resourceId2 = obtainStyledAttributes.getResourceId(10, R.color.f10290_resource_name_obfuscated_RES_2131099719);
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(3, getContext().getResources().getDimensionPixelSize(R.dimen.f17170_resource_name_obfuscated_RES_2131165336));
        this.N = dimensionPixelSize2;
        int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(8, getResources().getDimensionPixelSize(R.dimen.f17260_resource_name_obfuscated_RES_2131165345));
        int dimensionPixelSize4 = obtainStyledAttributes.getDimensionPixelSize(7, getResources().getDimensionPixelSize(R.dimen.f17260_resource_name_obfuscated_RES_2131165345));
        boolean z2 = obtainStyledAttributes.getBoolean(14, false);
        this.H = z2;
        int resourceId3 = obtainStyledAttributes.getResourceId(9, R.style.f70960_resource_name_obfuscated_RES_2132017669);
        this.f11028J = obtainStyledAttributes.getDimensionPixelSize(5, getResources().getDimensionPixelSize(R.dimen.f17260_resource_name_obfuscated_RES_2131165345));
        this.K = obtainStyledAttributes.getDimensionPixelSize(4, getResources().getDimensionPixelSize(R.dimen.f17260_resource_name_obfuscated_RES_2131165345));
        this.I = obtainStyledAttributes.getResourceId(11, R.style.f70960_resource_name_obfuscated_RES_2132017669);
        int dimensionPixelSize5 = obtainStyledAttributes.getDimensionPixelSize(15, getResources().getDimensionPixelSize(R.dimen.f17150_resource_name_obfuscated_RES_2131165334));
        boolean z3 = obtainStyledAttributes.getBoolean(0, false);
        boolean z4 = obtainStyledAttributes.getBoolean(13, false);
        obtainStyledAttributes.recycle();
        ChromeImageView chromeImageView = new ChromeImageView(getContext());
        this.G = chromeImageView;
        chromeImageView.setLayoutParams(new LinearLayout.LayoutParams(dimensionPixelSize3, dimensionPixelSize4));
        addView(chromeImageView);
        i = z2 ? (getResources().getDimensionPixelOffset(R.dimen.f17180_resource_name_obfuscated_RES_2131165337) - dimensionPixelSize4) / 2 : i;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        setPaddingRelative(i, 0, dimensionPixelSize, 0);
        TextView textView = new TextView(new ContextThemeWrapper(getContext(), (int) R.style.f68390_resource_name_obfuscated_RES_2132017412));
        this.F = textView;
        AbstractC3153j7.i(textView, resourceId3);
        if (z3) {
            textView.setMaxLines(2);
            int dimensionPixelSize6 = getResources().getDimensionPixelSize(R.dimen.f17300_resource_name_obfuscated_RES_2131165349);
            textView.setPaddingRelative(textView.getPaddingStart(), dimensionPixelSize6, textView.getPaddingEnd(), dimensionPixelSize6);
        }
        if (z4) {
            textView.setTextAlignment(5);
        }
        addView(textView);
        new C1833bN0(this, resourceId, resourceId2, dimensionPixelSize2, R.color.f10390_resource_name_obfuscated_RES_2131099729, i4, dimensionPixelSize5);
        c(-1, false);
    }

    public void a() {
        if (this.O == null) {
            ChromeImageView chromeImageView = new ChromeImageView(getContext());
            chromeImageView.setImageResource(R.drawable.f28430_resource_name_obfuscated_RES_2131230883);
            chromeImageView.setImageTintList(this.F.getTextColors());
            FrameLayout frameLayout = new FrameLayout(getContext());
            this.O = frameLayout;
            frameLayout.setId(R.id.chip_cancel_btn);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.f11028J, this.K);
            layoutParams.setMarginStart(this.L);
            layoutParams.setMarginEnd(this.M);
            layoutParams.gravity = 16;
            this.O.addView(chromeImageView, layoutParams);
            addView(this.O, new LinearLayout.LayoutParams(-2, -1));
            int paddingStart = getPaddingStart();
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            setPaddingRelative(paddingStart, paddingTop, 0, paddingBottom);
        }
    }

    public TextView b() {
        if (this.P == null) {
            TextView textView = new TextView(new ContextThemeWrapper(getContext(), (int) R.style.f68390_resource_name_obfuscated_RES_2132017412));
            this.P = textView;
            AbstractC3153j7.i(textView, this.I);
            this.P.setSelected(isSelected());
            this.P.setEnabled(isEnabled());
            addView(this.P);
        }
        return this.P;
    }

    public void c(int i, boolean z) {
        if (i == -1) {
            this.G.setVisibility(8);
            return;
        }
        this.G.setVisibility(0);
        this.G.setImageResource(i);
        e(z);
    }

    public void d(View.OnClickListener onClickListener) {
        this.O.setOnClickListener(onClickListener);
        String charSequence = this.F.getText().toString();
        this.O.setContentDescription(this.F.getContext().getString(R.string.f48650_resource_name_obfuscated_RES_2131952182, charSequence));
    }

    public final void e(boolean z) {
        if (this.F.getTextColors() == null || !z) {
            this.G.setImageTintList(null);
        } else {
            this.G.setImageTintList(this.F.getTextColors());
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.F.setEnabled(z);
        TextView textView = this.P;
        if (textView != null) {
            textView.setEnabled(z);
        }
    }
}
