package org.chromium.chrome.browser.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.oculus.browser.R;
import org.chromium.ui.widget.ButtonCompat;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SadTabView extends ScrollView {
    public int F;
    public float G;

    public SadTabView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        float f = context.getResources().getDisplayMetrics().density;
        this.G = f;
        this.F = (int) (f * 620.0f);
    }

    public void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        ButtonCompat buttonCompat = (ButtonCompat) findViewById(R.id.sad_tab_button);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) buttonCompat.getLayoutParams();
        if ((size > size2 || size > this.F) && buttonCompat.getWidth() <= size) {
            layoutParams.width = -2;
            layoutParams.gravity = 8388613;
        } else {
            layoutParams.width = -1;
            layoutParams.gravity = 7;
        }
        buttonCompat.setLayoutParams(layoutParams);
        super.onMeasure(i, i2);
    }
}
