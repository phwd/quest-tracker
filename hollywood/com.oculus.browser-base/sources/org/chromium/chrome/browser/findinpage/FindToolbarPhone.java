package org.chromium.chrome.browser.findinpage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FindToolbarPhone extends BQ {
    public FindToolbarPhone(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // defpackage.BQ
    public int f(boolean z, boolean z2) {
        if (!z2) {
            return super.f(z, z2);
        }
        return getContext().getResources().getColor(z ? R.color.f11430_resource_name_obfuscated_RES_2131099833 : R.color.f15580_resource_name_obfuscated_RES_2131100248);
    }

    @Override // defpackage.BQ
    public void g() {
        setVisibility(0);
        super.g();
    }

    @Override // defpackage.BQ
    public void h(boolean z) {
        setVisibility(8);
        super.h(z);
    }

    @Override // defpackage.BQ
    public void p(boolean z) {
        int i;
        int i2;
        int i3;
        if (z) {
            setBackgroundColor(AbstractC2934hr.a(getResources(), true));
            ColorStateList c = AbstractC2934hr.c(getContext(), true);
            this.f7735J.setImageTintList(c);
            this.I.setImageTintList(c);
            this.H.setImageTintList(c);
            i = R.color.f12430_resource_name_obfuscated_RES_2131099933;
            i3 = R.color.f12420_resource_name_obfuscated_RES_2131099932;
            i2 = R.color.f15530_resource_name_obfuscated_RES_2131100243;
        } else {
            setBackgroundColor(AbstractC2934hr.a(getResources(), false));
            ColorStateList c2 = AbstractC2934hr.c(getContext(), false);
            this.f7735J.setImageTintList(c2);
            this.I.setImageTintList(c2);
            this.H.setImageTintList(c2);
            i = R.color.f11450_resource_name_obfuscated_RES_2131099835;
            i3 = R.color.f12410_resource_name_obfuscated_RES_2131099931;
            i2 = R.color.f12150_resource_name_obfuscated_RES_2131099905;
        }
        this.G.setTextColor(getContext().getResources().getColor(i));
        this.G.setHintTextColor(getContext().getResources().getColor(i3));
        this.K.setBackgroundResource(i2);
    }
}
