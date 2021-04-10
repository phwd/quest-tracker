package org.chromium.components.browser_ui.settings;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.preference.Preference;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ChromeBasePreference extends Preference {
    public ColorStateList t0;
    public AbstractC1528Zb0 u0;
    public Boolean v0;
    public Boolean w0;

    public ChromeBasePreference(Context context) {
        this(context, null);
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        ColorStateList colorStateList;
        int i;
        super.x(tf0);
        if (this.P == null && (i = this.O) != 0) {
            this.P = AbstractC5544x8.a(this.F, i);
        }
        Drawable drawable = this.P;
        if (!(drawable == null || (colorStateList = this.t0) == null)) {
            drawable.setColorFilter(colorStateList.getDefaultColor(), PorterDuff.Mode.SRC_IN);
        }
        AbstractC1865bc0.c(this.u0, this, tf0.G);
        Boolean bool = this.v0;
        if (bool != null) {
            tf0.a0 = bool.booleanValue();
        }
        Boolean bool2 = this.w0;
        if (bool2 != null) {
            tf0.b0 = bool2.booleanValue();
        }
    }

    @Override // androidx.preference.Preference
    public void y() {
        if (AbstractC1865bc0.d(this.u0, this)) {
        }
    }

    public ChromeBasePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        R(false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.C);
        this.t0 = obtainStyledAttributes.getColorStateList(0);
        obtainStyledAttributes.recycle();
    }
}
