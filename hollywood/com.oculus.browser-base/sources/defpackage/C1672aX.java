package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import com.oculus.browser.R;

/* renamed from: aX  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1672aX extends DW0 {
    public final TextView F;
    public final ImageView G;
    public boolean H;
    public Runnable I;

    public C1672aX(Context context) {
        super(context);
        TypedValue typedValue = new TypedValue();
        getContext().getTheme().resolveAttribute(R.attr.f7490_resource_name_obfuscated_RES_2130969195, typedValue, true);
        setBackgroundResource(typedValue.resourceId);
        setClickable(true);
        setFocusable(true);
        TextView textView = new TextView(context);
        this.F = textView;
        textView.setLayoutParams(CW0.a());
        textView.setMaxLines(1);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setAllCaps(true);
        textView.setTextAppearance(R.style.f72130_resource_name_obfuscated_RES_2132017786);
        textView.setMinHeight(context.getResources().getDimensionPixelSize(R.dimen.f23320_resource_name_obfuscated_RES_2131165951));
        textView.setGravity(16);
        textView.setTextAlignment(5);
        textView.setPaddingRelative(context.getResources().getDimensionPixelSize(R.dimen.f23330_resource_name_obfuscated_RES_2131165952), 0, 0, 0);
        addView(textView);
        AppCompatImageView appCompatImageView = new AppCompatImageView(context, null);
        this.G = appCompatImageView;
        appCompatImageView.setScaleType(ImageView.ScaleType.CENTER);
        appCompatImageView.setImageResource(R.drawable.f30150_resource_name_obfuscated_RES_2131231055);
        appCompatImageView.setLayoutParams(new CW0(getResources().getDimensionPixelSize(R.dimen.f23240_resource_name_obfuscated_RES_2131165943), -1));
        addView(appCompatImageView);
        AbstractC1920bu1.n(this, new ZW(this));
    }

    public boolean isFocused() {
        return super.isFocused() || (isSelected() && !isInTouchMode());
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = true;
        if (getLayoutDirection() != 1) {
            z = false;
        }
        if ((z || !R40.d(keyEvent)) && (!z || !R40.c(keyEvent))) {
            return super.onKeyDown(i, keyEvent);
        }
        return performClick();
    }

    public void setSelected(boolean z) {
        Runnable runnable;
        super.setSelected(z);
        if (z && (runnable = this.I) != null) {
            runnable.run();
        }
    }
}
