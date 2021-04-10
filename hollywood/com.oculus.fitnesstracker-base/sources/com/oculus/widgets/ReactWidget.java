package com.oculus.widgets;

import android.content.Context;
import android.graphics.Bitmap;

public abstract class ReactWidget extends BaseWidget {
    private native void createWidget(Context context, String str, String str2, int i, int i2);

    private native void destroyWidget(String str, String str2);

    private native void refreshWidget(String str, String str2, Bitmap bitmap);

    public abstract String getAppName();

    public abstract int getHeight();

    public abstract String getWidgetName();

    public abstract int getWidth();

    public ReactWidget(Context context) {
        super(context);
        createWidget(context, getAppName(), getWidgetName(), getWidth(), getHeight());
    }

    @Override // com.oculus.widgets.BaseWidget
    public final Bitmap refresh() {
        Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        refreshWidget(getAppName(), getWidgetName(), createBitmap);
        return createBitmap;
    }

    @Override // com.oculus.widgets.BaseWidget
    public void destroy() {
        destroyWidget(getAppName(), getWidgetName());
    }
}
