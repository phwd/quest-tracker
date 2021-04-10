package com.oculus.ocui;

import android.content.res.Resources;
import android.util.TypedValue;
import com.oculus.common.ocui.R;

public class OCConstants {
    public static float getOpacity(Resources resources, boolean z) {
        TypedValue typedValue = new TypedValue();
        resources.getValue(z ? R.dimen.ocopacity_enabled_opacity : R.dimen.ocopacity_disabled_opacity, typedValue, true);
        return typedValue.getFloat();
    }
}
