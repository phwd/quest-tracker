package com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise;

import androidx.databinding.BindingAdapter;
import com.oculus.ocui.OCButton;
import com.oculus.vrshell.panels.DensityUtils;

public class BindingUtils {
    @BindingAdapter({"paddingStartDip"})
    public static void updatePaddingStartDip(OCButton oCButton, Integer num) {
        if (num != null) {
            int dipToPixelsInt = dipToPixelsInt(oCButton, num.intValue());
            int paddingEnd = oCButton.getPaddingEnd();
            int paddingTop = oCButton.getPaddingTop();
            int paddingBottom = oCButton.getPaddingBottom();
            boolean z = true;
            if (oCButton.getLayoutDirection() != 1) {
                z = false;
            }
            int i = z ? paddingEnd : dipToPixelsInt;
            if (!z) {
                dipToPixelsInt = paddingEnd;
            }
            oCButton.setPadding(i, paddingTop, dipToPixelsInt, paddingBottom);
        }
    }

    private static int dipToPixelsInt(OCButton oCButton, int i) {
        return DensityUtils.dipToPixelsInt((float) i, oCButton.getContext().getResources().getDisplayMetrics());
    }
}
