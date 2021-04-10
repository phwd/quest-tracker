package com.oculus.widgets;

import android.content.Context;
import android.graphics.Bitmap;

public abstract class BaseWidget {
    private static final int API_VERSION = 1;

    public enum WidgetPosition {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT
    }

    public static final int getApiVersion() {
        return 1;
    }

    public abstract void destroy();

    public abstract float getOffsetPitch();

    public abstract float getOffsetX();

    public abstract float getOffsetY();

    public abstract float getOffsetYaw();

    public abstract WidgetPosition getPosition();

    public abstract Bitmap refresh();

    public BaseWidget(Context context) {
    }
}
