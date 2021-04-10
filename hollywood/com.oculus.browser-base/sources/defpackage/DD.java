package defpackage;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.widget.RoundedCornerImageView;

/* renamed from: DD  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DD extends DW0 {
    public final RoundedCornerImageView F;
    public View G;

    public DD(Context context) {
        super(context);
        setClickable(true);
        setFocusable(true);
        RoundedCornerImageView roundedCornerImageView = new RoundedCornerImageView(getContext(), null, 0);
        this.F = roundedCornerImageView;
        roundedCornerImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        roundedCornerImageView.setLayoutParams(new CW0(getResources().getDimensionPixelSize(R.dimen.f23340_resource_name_obfuscated_RES_2131165953), -2));
        addView(roundedCornerImageView);
    }

    public boolean isFocused() {
        return super.isFocused() || (isSelected() && !isInTouchMode());
    }
}
