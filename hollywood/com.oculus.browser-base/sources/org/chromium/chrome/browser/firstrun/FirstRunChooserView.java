package org.chromium.chrome.browser.firstrun;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FirstRunChooserView extends ScrollView {
    public View F;

    public FirstRunChooserView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public float getTopFadingEdgeStrength() {
        return 0.0f;
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.F = findViewById(R.id.chooser_title);
    }

    public void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        ViewGroup.LayoutParams layoutParams = this.F.getLayoutParams();
        if (size2 > size) {
            layoutParams.height = (size * 9) / 16;
            View view = this.F;
            view.setPadding(view.getPaddingLeft(), 0, this.F.getPaddingRight(), this.F.getPaddingBottom());
        } else {
            layoutParams.height = -2;
            View view2 = this.F;
            view2.setPadding(view2.getPaddingLeft(), getResources().getDimensionPixelOffset(R.dimen.f25100_resource_name_obfuscated_RES_2131166129), this.F.getPaddingRight(), this.F.getPaddingBottom());
        }
        this.F.setLayoutParams(layoutParams);
        super.onMeasure(i, i2);
    }
}
