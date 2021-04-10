package com.google.android.material.appbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.Toolbar;
import com.oculus.browser.R;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MaterialToolbar extends Toolbar {
    public MaterialToolbar(Context context, AttributeSet attributeSet) {
        super(AbstractC3918nd0.a(context, attributeSet, R.attr.f9070_resource_name_obfuscated_RES_2130969353, R.style.f75940_resource_name_obfuscated_RES_2132018167), attributeSet, R.attr.f9070_resource_name_obfuscated_RES_2130969353);
        Context context2 = getContext();
        Drawable background = getBackground();
        if (background == null || (background instanceof ColorDrawable)) {
            C3234jd0 jd0 = new C3234jd0();
            jd0.o(ColorStateList.valueOf(background != null ? ((ColorDrawable) background).getColor() : 0));
            jd0.H.b = new EK(context2);
            jd0.s();
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            jd0.n(getElevation());
            setBackground(jd0);
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable background = getBackground();
        if (background instanceof C3234jd0) {
            AbstractC3405kd0.c(this, (C3234jd0) background);
        }
    }

    public void setElevation(float f) {
        super.setElevation(f);
        AbstractC3405kd0.b(this, f);
    }
}
