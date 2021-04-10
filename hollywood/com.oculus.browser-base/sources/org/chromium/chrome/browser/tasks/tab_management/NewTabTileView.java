package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NewTabTileView extends RelativeLayout {
    public float F = 1.0f;
    public int G;
    public ImageView H;

    public NewTabTileView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.H = (ImageView) findViewById(R.id.new_tab_button);
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        Context context = getContext();
        int measuredWidth = getMeasuredWidth();
        int dimension = ((int) (context.getResources().getDimension(R.dimen.f25410_resource_name_obfuscated_RES_2131166160) + (((float) getMeasuredWidth()) / this.F))) + this.G;
        setMeasuredDimension(measuredWidth, dimension);
        View findViewById = findViewById(R.id.new_tab_button);
        findViewById.setTranslationX((float) ((measuredWidth - findViewById.getMeasuredWidth()) / 2));
        findViewById.setTranslationY((float) ((dimension - findViewById.getMeasuredHeight()) / 2));
    }
}
