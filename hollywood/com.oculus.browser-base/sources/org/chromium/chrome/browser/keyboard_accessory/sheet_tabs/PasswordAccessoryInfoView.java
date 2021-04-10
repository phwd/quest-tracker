package org.chromium.chrome.browser.keyboard_accessory.sheet_tabs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.ui.widget.ChipView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PasswordAccessoryInfoView extends LinearLayout {
    public TextView F;
    public ImageView G;
    public ChipView H;
    public ChipView I;

    public PasswordAccessoryInfoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(Drawable drawable) {
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.f20290_resource_name_obfuscated_RES_2131165648);
        if (drawable != null) {
            drawable.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
        }
        this.G.setImageDrawable(drawable);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.F = (TextView) findViewById(R.id.password_info_title);
        this.G = (ImageView) findViewById(R.id.favicon);
        this.H = (ChipView) findViewById(R.id.suggestion_text);
        this.I = (ChipView) findViewById(R.id.password_text);
    }
}
