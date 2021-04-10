package org.chromium.chrome.browser.firstrun;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TosAndUmaFragmentView extends RelativeLayout {
    public ScrollView F;
    public LinearLayout G;
    public View H;
    public View I;

    /* renamed from: J  reason: collision with root package name */
    public View f10675J;
    public View K;
    public View L;
    public View M;
    public View N;
    public View O;
    public int P;
    public int Q;
    public int R;
    public int S;
    public int T;
    public int U;
    public int V;
    public int W;
    public int a0;
    public int b0;
    public int c0;
    public int d0;
    public int e0;
    public int f0;

    public TosAndUmaFragmentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final boolean a() {
        int i = this.F.canScrollVertically(1) ? 0 : 8;
        if (i == this.O.getVisibility()) {
            return false;
        }
        this.O.setVisibility(i);
        return true;
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.F = (ScrollView) findViewById(R.id.scroll_view);
        this.G = (LinearLayout) findViewById(R.id.fre_main_layout);
        this.H = findViewById(R.id.fre_title_and_content);
        this.I = findViewById(R.id.fre_content_wrapper);
        this.f10675J = findViewById(R.id.fre_bottom_group);
        this.K = findViewById(R.id.title);
        this.L = findViewById(R.id.image);
        this.M = findViewById(R.id.loading_view_container);
        this.N = findViewById(R.id.privacy_disclaimer);
        this.O = findViewById(R.id.shadow);
        this.F.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver$OnScrollChangedListenerC4117om1(this));
        this.F.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver$OnGlobalLayoutListenerC4288pm1(this));
        this.R = getResources().getDimensionPixelSize(R.dimen.f19610_resource_name_obfuscated_RES_2131165580);
        this.S = getResources().getDimensionPixelSize(R.dimen.f19720_resource_name_obfuscated_RES_2131165591);
        this.T = getResources().getDimensionPixelSize(R.dimen.f19710_resource_name_obfuscated_RES_2131165590);
        this.U = getResources().getDimensionPixelSize(R.dimen.f19640_resource_name_obfuscated_RES_2131165583);
        this.V = getResources().getDimensionPixelSize(R.dimen.f19630_resource_name_obfuscated_RES_2131165582);
        this.W = getResources().getDimensionPixelSize(R.dimen.f19730_resource_name_obfuscated_RES_2131165592);
        this.a0 = getResources().getDimensionPixelSize(R.dimen.f19600_resource_name_obfuscated_RES_2131165579);
        this.b0 = getResources().getDimensionPixelSize(R.dimen.f20870_resource_name_obfuscated_RES_2131165706);
        this.c0 = getResources().getDimensionPixelSize(R.dimen.f19580_resource_name_obfuscated_RES_2131165577);
        this.d0 = getResources().getDimensionPixelSize(R.dimen.f19590_resource_name_obfuscated_RES_2131165578);
        int i = this.c0;
        this.e0 = i;
        this.f0 = i;
    }

    public void onMeasure(int i, int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        int i3;
        int i4;
        int i5;
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        boolean z = true;
        boolean z2 = false;
        if (!(size == this.Q && size2 == this.P)) {
            this.P = size2;
            this.Q = size;
            int i6 = (size2 < (((this.c0 * 2) + this.b0) * 2) + this.T || ((double) size) <= ((double) size2) * 1.5d) ? 0 : 1;
            this.G.setOrientation(i6 ^ 1);
            View view = this.H;
            view.setPaddingRelative(view.getPaddingStart(), i6 != 0 ? this.V : 0, this.H.getPaddingEnd(), this.H.getPaddingBottom());
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.L.getLayoutParams();
            if (i6 != 0) {
                layoutParams.topMargin = Math.max(0, (size2 - this.T) / 2);
                layoutParams.gravity = 49;
            } else {
                layoutParams.topMargin = Math.max(this.S, ((size2 / 2) - (this.T + this.R)) - this.W);
                layoutParams.gravity = 81;
            }
            int i7 = 17;
            ((LinearLayout.LayoutParams) this.K.getLayoutParams()).gravity = i6 != 0 ? 8388611 : 17;
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.M.getLayoutParams();
            if (i6 != 0) {
                int max = Math.max(0, ((size / 2) - ((this.S * 2) + this.T)) - (this.U / 2));
                int max2 = Math.max(0, ((size2 / 2) - (this.W + this.V)) - (this.U / 2));
                layoutParams2.gravity = 8388611;
                layoutParams2.setMarginStart(max);
                layoutParams2.topMargin = max2;
            } else {
                int i8 = this.R;
                layoutParams2.gravity = 1;
                layoutParams2.setMarginStart(0);
                layoutParams2.topMargin = i8;
            }
            this.M.setLayoutParams(layoutParams2);
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.I.getLayoutParams();
            if (i6 != 0) {
                i4 = 0;
            } else {
                i4 = this.a0;
            }
            marginLayoutParams2.setMarginStart(i4);
            this.I.setLayoutParams(marginLayoutParams2);
            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.N.getLayoutParams();
            if (i6 != 0) {
                i7 = 8388611;
            }
            layoutParams3.gravity = i7;
            if (i6 != 0) {
                i5 = 0;
            } else {
                i5 = this.a0;
            }
            layoutParams3.setMarginStart(i5);
            this.N.setLayoutParams(layoutParams3);
            RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) this.f10675J.getLayoutParams();
            int i9 = 14;
            int i10 = i6 != 0 ? 14 : 21;
            if (i6 != 0) {
                i9 = 21;
            }
            layoutParams4.removeRule(i10);
            layoutParams4.addRule(i9);
            int i11 = this.Q > this.P ? this.f0 : this.e0;
            layoutParams4.setMargins(layoutParams4.leftMargin, i11, layoutParams4.rightMargin, i11);
            this.f10675J.setLayoutParams(layoutParams4);
        }
        super.onMeasure(i, i2);
        boolean a2 = a();
        if (getHeight() == this.P && getWidth() == this.Q && ((this.F.canScrollVertically(1) || this.F.canScrollVertically(-1)) && (marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f10675J.getLayoutParams()).bottomMargin != (i3 = this.d0))) {
            if (this.Q > this.P) {
                z2 = true;
            }
            if (z2) {
                this.f0 = i3;
            } else {
                this.e0 = i3;
            }
            marginLayoutParams.setMargins(marginLayoutParams.leftMargin, i3, marginLayoutParams.rightMargin, i3);
            this.f10675J.setLayoutParams(marginLayoutParams);
        } else {
            z = false;
        }
        if (a2 || z) {
            super.onMeasure(i, i2);
        }
    }
}
