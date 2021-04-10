package org.chromium.chrome.browser.feed.shared.ui;

import J.N;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import com.oculus.browser.R;
import org.chromium.base.TraceEvent;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MaterialSpinnerView extends AppCompatImageView {
    public final C0049Au H;
    public final boolean I;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MaterialSpinnerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        boolean z = false;
        TraceEvent.Y("MaterialSpinnerView", null);
        C0049Au au = new C0049Au(context);
        this.H = au;
        au.b(7.5f, 2.5f, 10.0f, 5.0f);
        au.invalidateSelf();
        setImageDrawable(au);
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.f4660_resource_name_obfuscated_RES_2130968912, typedValue, true);
        int[] iArr = {typedValue.data};
        C6011zu zuVar = au.I;
        zuVar.i = iArr;
        zuVar.a(0);
        au.I.a(0);
        au.invalidateSelf();
        this.I = AbstractC4226pO.a() ? N.M09VlOh_("InterestFeedSpinnerAlwaysAnimate") : z;
        c(isAttachedToWindow());
        TraceEvent.f0("MaterialSpinnerView");
    }

    public final void c(boolean z) {
        C0049Au au = this.H;
        if (au != null) {
            if (!this.I) {
                boolean z2 = isShown() && z;
                if (this.H.isRunning() && !z2) {
                    this.H.stop();
                } else if (!this.H.isRunning() && z2) {
                    this.H.start();
                }
            } else if (!au.isRunning()) {
                this.H.start();
            }
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        c(true);
    }

    public void onDetachedFromWindow() {
        c(false);
        super.onDetachedFromWindow();
    }

    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        c(isAttachedToWindow());
    }
}
