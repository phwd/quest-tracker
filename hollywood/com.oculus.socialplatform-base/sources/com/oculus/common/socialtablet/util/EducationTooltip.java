package com.oculus.common.socialtablet.util;

import android.content.Context;
import android.widget.TextView;
import com.oculus.ocui.OCTooltip;
import com.oculus.socialplatform.R;

public class EducationTooltip extends OCTooltip {
    public static final int TEXT_VIEW_ABSOLUTE_MAX_LINES = 4;

    public void setTextViewCustomMaxLines(int i) {
        if (i <= 4) {
            ((TextView) getContentView().findViewById(R.id.tooltip_text)).setMaxLines(i);
        }
    }

    public EducationTooltip(Context context) {
        super(context);
        setAnimationStyle(R.style.EducationTooltipFadeAnimation);
    }

    public void setTextViewCustomWidth(int i) {
        ((TextView) getContentView().findViewById(R.id.tooltip_text)).setMaxWidth(i);
    }
}
