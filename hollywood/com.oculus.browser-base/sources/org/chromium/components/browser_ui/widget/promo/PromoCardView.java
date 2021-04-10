package org.chromium.components.browser_ui.widget.promo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.ui.widget.ButtonCompat;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PromoCardView extends LinearLayout {
    public ImageView F;
    public TextView G;
    public ButtonCompat H;
    public TextView I;

    /* renamed from: J  reason: collision with root package name */
    public ButtonCompat f10826J;

    public PromoCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.F = (ImageView) findViewById(R.id.promo_image);
        this.G = (TextView) findViewById(R.id.promo_title);
        this.I = (TextView) findViewById(R.id.promo_description);
        this.H = (ButtonCompat) findViewById(R.id.promo_primary_button);
        this.f10826J = (ButtonCompat) findViewById(R.id.promo_secondary_button);
    }
}
