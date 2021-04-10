package org.chromium.ui.widget;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ButtonCompat extends L7 {
    public ButtonCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.style.f68610_resource_name_obfuscated_RES_2132017434);
    }

    public ButtonCompat(Context context, AttributeSet attributeSet, int i) {
        super(new ContextThemeWrapper(context, i), attributeSet, 16842824);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, FJ0.v, 16842824, 0);
        int resourceId = obtainStyledAttributes.getResourceId(0, R.color.f10020_resource_name_obfuscated_RES_2131099692);
        int resourceId2 = obtainStyledAttributes.getResourceId(2, R.color.f12390_resource_name_obfuscated_RES_2131099929);
        boolean z = obtainStyledAttributes.getBoolean(1, true);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(3, getResources().getDimensionPixelSize(R.dimen.f16920_resource_name_obfuscated_RES_2131165311));
        obtainStyledAttributes.recycle();
        new C1833bN0(this, resourceId, resourceId2, getResources().getDimensionPixelSize(R.dimen.f16930_resource_name_obfuscated_RES_2131165312), 17170445, R.dimen.f18120_resource_name_obfuscated_RES_2131165431, dimensionPixelSize);
        StateListAnimator stateListAnimator = null;
        if (z) {
            TypedArray obtainStyledAttributes2 = getContext().obtainStyledAttributes(null, new int[]{16843848}, 0, 16974424);
            int resourceId3 = obtainStyledAttributes2.getResourceId(0, 0);
            obtainStyledAttributes2.recycle();
            setStateListAnimator(resourceId3 != 0 ? AnimatorInflater.loadStateListAnimator(getContext(), resourceId3) : stateListAnimator);
            return;
        }
        setElevation(0.0f);
        setStateListAnimator(null);
    }
}
