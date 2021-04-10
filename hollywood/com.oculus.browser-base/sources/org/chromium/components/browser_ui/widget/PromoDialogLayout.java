package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class PromoDialogLayout extends BoundedLinearLayout {
    public LinearLayout I;

    /* renamed from: J  reason: collision with root package name */
    public ViewGroup f10822J;
    public LinearLayout K;
    public ImageView L;
    public TextView M;
    public TextView N;
    public TextView O;
    public CH0 P;

    public PromoDialogLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        this.I = (LinearLayout) findViewById(R.id.full_promo_content);
        this.f10822J = (ViewGroup) findViewById(R.id.promo_container);
        this.K = (LinearLayout) findViewById(R.id.scrollable_promo_content);
        this.L = (ImageView) findViewById(R.id.illustration);
        this.M = (TextView) findViewById(R.id.header);
        this.O = (TextView) findViewById(R.id.subheader);
        super.onFinishInflate();
    }

    @Override // org.chromium.components.browser_ui.widget.BoundedLinearLayout
    public void onMeasure(int i, int i2) {
        boolean z;
        LinearLayout linearLayout;
        boolean z2 = false;
        if (((double) View.MeasureSpec.getSize(i)) > ((double) View.MeasureSpec.getSize(i2)) * 1.5d) {
            this.I.setOrientation(0);
        } else {
            this.I.setOrientation(1);
        }
        super.onMeasure(i, i2);
        Objects.requireNonNull(this.P);
        if (this.P.f7799a == 0) {
            if (this.f10822J.getMeasuredHeight() < getResources().getDimensionPixelSize(R.dimen.f24440_resource_name_obfuscated_RES_2131166063)) {
                linearLayout = this.K;
                z = false;
            } else {
                linearLayout = this;
                z = true;
            }
            if (this.M.getParent() != linearLayout) {
                ((ViewGroup) this.M.getParent()).removeView(this.M);
                linearLayout.addView(this.M, 0);
                int dimensionPixelSize = z ? getResources().getDimensionPixelSize(R.dimen.f24450_resource_name_obfuscated_RES_2131166064) : 0;
                TextView textView = this.M;
                AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                textView.setPaddingRelative(dimensionPixelSize, 0, dimensionPixelSize, 0);
                z2 = true;
            }
        }
        if (z2) {
            super.onMeasure(i, i2);
        }
    }
}
