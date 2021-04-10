package com.oculus.ocui;

import android.content.res.Resources;
import android.util.TypedValue;
import com.oculus.socialplatform.R;

public class OCConstants {
    public static float getOpacity(Resources resources, boolean z) {
        TypedValue typedValue = new TypedValue();
        int i = R.dimen.ocopacity_disabled_opacity;
        if (z) {
            i = R.dimen.ocopacity_enabled_opacity;
        }
        resources.getValue(i, typedValue, true);
        return typedValue.getFloat();
    }
}
