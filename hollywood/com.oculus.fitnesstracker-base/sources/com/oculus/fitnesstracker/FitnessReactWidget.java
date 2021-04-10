package com.oculus.fitnesstracker;

import android.content.Context;
import com.oculus.widgets.BaseWidget;
import com.oculus.widgets.ReactWidget;

public class FitnessReactWidget extends ReactWidget {
    private static final String TAG = "FitnessReactWidget";

    @Override // com.oculus.widgets.ReactWidget
    public String getAppName() {
        return "FitnessTracker";
    }

    @Override // com.oculus.widgets.ReactWidget
    public int getHeight() {
        return 256;
    }

    @Override // com.oculus.widgets.BaseWidget
    public float getOffsetPitch() {
        return 0.2f;
    }

    @Override // com.oculus.widgets.BaseWidget
    public float getOffsetX() {
        return 0.0f;
    }

    @Override // com.oculus.widgets.BaseWidget
    public float getOffsetY() {
        return 2.2f;
    }

    @Override // com.oculus.widgets.BaseWidget
    public float getOffsetYaw() {
        return 0.0f;
    }

    @Override // com.oculus.widgets.ReactWidget
    public String getWidgetName() {
        return "widget";
    }

    @Override // com.oculus.widgets.ReactWidget
    public int getWidth() {
        return 192;
    }

    public FitnessReactWidget(Context context) {
        super(context);
    }

    @Override // com.oculus.widgets.BaseWidget
    public BaseWidget.WidgetPosition getPosition() {
        return BaseWidget.WidgetPosition.TOP;
    }
}
