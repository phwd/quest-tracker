package com.oculus.panelapp.anytimeui.v2.tablet.apps;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryAppUtils;

public class LibraryAppTileFooter extends View {
    private final Context mContext;

    public LibraryAppTileFooter(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(getMeasuredWidth(), LibraryAppUtils.getAppTileFooterHeight(this.mContext));
    }
}
