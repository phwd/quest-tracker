package org.chromium.chrome.browser.firstrun;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.oculus.browser.R;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FirstRunView extends FrameLayout {
    public View F;
    public LinearLayout G;
    public LinearLayout H;

    public FirstRunView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.F = findViewById(R.id.fre_main_layout);
        this.G = (LinearLayout) findViewById(R.id.fre_image_and_content);
        this.H = (LinearLayout) findViewById(R.id.fre_content_wrapper);
    }

    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.H.getLayoutParams();
        if (((float) size) < getResources().getDimension(R.dimen.f25070_resource_name_obfuscated_RES_2131166126) * 2.0f || size <= size2) {
            this.G.setOrientation(1);
            marginLayoutParams.width = -2;
            marginLayoutParams.height = 0;
            marginLayoutParams.topMargin = getResources().getDimensionPixelSize(R.dimen.f19720_resource_name_obfuscated_RES_2131165591);
            i3 = getResources().getDimensionPixelSize(R.dimen.f19720_resource_name_obfuscated_RES_2131165591) + getResources().getDimensionPixelSize(R.dimen.f19620_resource_name_obfuscated_RES_2131165581) + getResources().getDimensionPixelSize(R.dimen.f19720_resource_name_obfuscated_RES_2131165591) + getResources().getDimensionPixelSize(R.dimen.f19730_resource_name_obfuscated_RES_2131165592);
            i4 = 0;
        } else {
            this.G.setOrientation(0);
            i4 = getResources().getDimensionPixelSize(R.dimen.f19650_resource_name_obfuscated_RES_2131165584);
            marginLayoutParams.width = 0;
            marginLayoutParams.height = -2;
            marginLayoutParams.topMargin = 0;
            i3 = (getResources().getDimensionPixelSize(R.dimen.f19620_resource_name_obfuscated_RES_2131165581) / 2) + getResources().getDimensionPixelSize(R.dimen.f19720_resource_name_obfuscated_RES_2131165591) + getResources().getDimensionPixelSize(R.dimen.f19730_resource_name_obfuscated_RES_2131165592);
        }
        int max = Math.max(0, (size2 / 2) - i3);
        View view = this.F;
        view.setPadding(view.getPaddingLeft(), max, this.F.getPaddingRight(), this.F.getPaddingBottom());
        LinearLayout linearLayout = this.G;
        int paddingTop = linearLayout.getPaddingTop();
        LinearLayout linearLayout2 = this.G;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        linearLayout.setPaddingRelative(i4, paddingTop, linearLayout2.getPaddingEnd(), this.G.getPaddingBottom());
        this.H.setLayoutParams(marginLayoutParams);
        super.onMeasure(i, i2);
    }
}
